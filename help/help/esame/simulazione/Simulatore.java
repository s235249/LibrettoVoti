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
	
	
	// simulatore 

}
