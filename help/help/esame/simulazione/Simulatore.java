package help.esame.simulazione;

public class Simulatore {
	
	
// da mettere nel model
	
	public void simula(Country partenza) {

		sim.init(partenza, this.graph);

		sim.run();

		

		

	}



	public int getLastT() {

		return this.sim.getLastT();

	}



	public List<CountryAndNumber> getStanziali() {

		Map<Country, Integer> stanziali = this.sim.getStanziali();

		List<CountryAndNumber> stanzialiList = new ArrayList<CountryAndNumber>();

		for(Country c : stanziali.keySet()) {

			CountryAndNumber cn = new CountryAndNumber(c,stanziali.get(c));

			stanzialiList.add(cn);

		}

		

		Collections.sort(stanzialiList);

		return stanzialiList;

	}


// simulatore migranti
	
	public class Simulatore {

		

		//Modello -> Stato del sistema ad ogni passo

		private Graph<Country, DefaultEdge> grafo;



		//Tipi di evento/coda prioritaria

		// 1 solo evento

		private PriorityQueue<Evento> queue;

		

		//Parametri della simulazione

		private int N_MIGRANTI = 1000;

		private Country partenza;

		

		//Valori in output

		private int T;

		private Map<Country, Integer> stanziali;

		

		public void init(Country partenza, Graph<Country,DefaultEdge> grafo) {

			//ricevo i parametri

			this.partenza = partenza;

			this.grafo = grafo;

			

			//impostazione dello stato iniziale

			this.T = 1;

			stanziali = new HashMap<Country, Integer>();

			for(Country c : this.grafo.vertexSet()) {

				stanziali.put(c, 0);

			}

			queue = new PriorityQueue<Evento>();

			

			//inserisco il primo evento

			this.queue.add(new Evento(T, N_MIGRANTI,partenza));

		}

		

		public void run() {

			//Estraggo un evento per volta dalla coda e lo eseguo,

			//finchè la coda non si svuota

			Evento e;



			while((e = queue.poll()) != null){

				//ESEGUO L'EVENTO

				this.T = e.getT();

				

				int nPersone = e.getN();

				Country stato = e.getStato();

				List<Country> confinanti = Graphs.neighborListOf(this.grafo, stato);

				int migranti = (nPersone/2) / confinanti.size();

				

				if(migranti > 0) {

					//le persone si possono muovere

					for(Country confinante : confinanti)

						queue.add(new Evento(e.getT() +1,migranti,confinante));

				}

				

				int stanziali = nPersone - migranti* confinanti.size();

				this.stanziali.put(stato, this.stanziali.get(stato) + stanziali);

			}	

		}



		public int getLastT() {

			return T;

		}

		

		public Map<Country, Integer> getStanziali(){

			return this.stanziali;

		}



	}
	
	
	-------------------------------------------------------------
	
	// simulatore poweroutages
	
	public class Simulatore {

		//MODELLO/LO STATO DEL SISTEMA

		private Graph<Nerc,DefaultWeightedEdge> grafo;

		private List<PowerOutage> powerOutages;

		private Map<Nerc, Set<Nerc>> prestiti;

		

		//PARAMETRI DELLA SIMULAZIONE

		private int k;

		

		//VALORI IN OUTPUT

		private int CATASTROFI;

		private Map<Nerc, Long> bonus;

		

		//CODA

		private PriorityQueue<Evento> queue;

		

		

		public void init (int k, List<PowerOutage> powerOutages,

				NercIdMap nercMap, Graph<Nerc,DefaultWeightedEdge> grafo) {

			this.queue = new PriorityQueue<Evento>();

			this.bonus = new HashMap<Nerc,Long>();

			this.prestiti = new HashMap<Nerc, Set<Nerc>>();

			

			for(Nerc n : nercMap.values()) {

				this.bonus.put(n, Long.valueOf(0));

				this.prestiti.put(n, new HashSet<Nerc>());

			}

			

			this.CATASTROFI = 0;

			

			this.k = k;

			this.powerOutages = powerOutages;

			this.grafo = grafo;

			

			//inserisco gli eventi iniziali

			for(PowerOutage po : this.powerOutages) {

				Evento e = new Evento(Evento.TIPO.INIZIO_INTERRUZIONE, 

						po.getNerc(),null, po.getInizio(),po.getInizio(),po.getFine());

				queue.add(e);

			}

		}

		

		public void run() {

			Evento e;

			while((e = queue.poll()) != null) {

				switch(e.getTipo()) {

					case INIZIO_INTERRUZIONE:

						Nerc nerc = e.getNerc();

						System.out.println("INIZIO INTERRUZIONE NERC: " + nerc);



						//cerco se c'è un donatore, altrimenti ... CATASTROFE

						Nerc donatore = null;

						//cerco tra i miei "debitori"

						if(this.prestiti.get(nerc).size() > 0) {

							//scelgo tra i miei debitori

							double min = Long.MAX_VALUE;

							for(Nerc n : this.prestiti.get(nerc)) {

								DefaultWeightedEdge edge = this.grafo.getEdge(nerc, n);

								if(this.grafo.getEdgeWeight(edge) < min) {

									if(!n.getStaPrestando()) {

										donatore = n;

										min = this.grafo.getEdgeWeight(edge);

									}

								}

							}

						} else {

							//prendo quello con peso arco <

							double min = Long.MAX_VALUE;

							List<Nerc> neighbors = Graphs.neighborListOf(this.grafo, nerc);

							for(Nerc n : neighbors) {

								DefaultWeightedEdge edge = this.grafo.getEdge(nerc, n);

								if(this.grafo.getEdgeWeight(edge) < min) {

									if(!n.getStaPrestando()) {

										donatore = n;

										min = this.grafo.getEdgeWeight(edge);

									}

								}

							}

						}

						if(donatore != null) {

							System.out.println("\tTROVATO DONATORE: " + donatore);

							donatore.setStaPrestando(true);

							Evento fine = new Evento(Evento.TIPO.FINE_INTERRUZIONE, e.getNerc(),

									donatore,e.getDataFine(), e.getDataInizio(), e.getDataFine());

							queue.add(fine);

							this.prestiti.get(donatore).add(e.getNerc());

							Evento cancella  = new Evento(Evento.TIPO.CANCELLA_PRESTITO,

									e.getNerc(),donatore,e.getData().plusMonths(k),

									e.getDataInizio(),e.getDataFine());

							this.queue.add(cancella);

						} else {

							//CATASTROFE!!!

							System.out.println("\tCATASTROFE!!!!");

							this.CATASTROFI ++;

						}

						break;

					case FINE_INTERRUZIONE:

						System.out.println("FINE INTERRUZIONE NERC: " + e.getNerc());



						//assegnare un bonus al donatore

						if(e.getDonatore() != null)

							this.bonus.put(e.getDonatore(), bonus.get(e.getDonatore()) + 

									Duration.between(e.getDataInizio(), e.getDataFine()).toDays());

						//dire che il donatore non sta più prestando

						e.getDonatore().setStaPrestando(false);

						

						break;

					case CANCELLA_PRESTITO:

						System.out.println("CANCELLAZIONE PRESTITO: " + e.getDonatore() + "-" + e.getNerc());

						this.prestiti.get(e.getDonatore()).remove(e.getNerc());

						break;

				}

			}

		}

		

		

		public int getCatastrofi() {

			return this.CATASTROFI;

		}

		

		public Map<Nerc,Long> getBonus(){

			return this.bonus;

		}

		

	}

}
