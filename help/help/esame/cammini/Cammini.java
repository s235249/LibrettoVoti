package help.esame.cammini;

public class Cammini {
	
	// componente connessa
	// è presente? 
	// stampa percorso
	
	
	//model
	public Boolean testConnessione(Integer a1, Integer a2) {

		Set<Airport> visitati = new HashSet<Airport>();

		Airport partenza = aIdMap.get(a1);

		Airport destinazione = aIdMap.get(a2);

		System.out.println("Testo connessione tra " + partenza + " e " + destinazione);

		BreadthFirstIterator<Airport, DefaultWeightedEdge> it = new BreadthFirstIterator<>(this.grafo,partenza);

		

		while (it.hasNext())

			visitati.add(it.next());

		

		if(visitati.contains(destinazione))

			return true;

		else

			return false;

		

	}

	

	public List<Airport> trovaPercorso(Integer a1, Integer a2){

		List<Airport> percorso = new ArrayList<Airport>();

		Airport partenza = aIdMap.get(a1);

		Airport destinazione = aIdMap.get(a2);

		System.out.println("Cerco percorso tra " + partenza + " e " + destinazione);

		BreadthFirstIterator<Airport, DefaultWeightedEdge> it = new BreadthFirstIterator<>(this.grafo,partenza);

		

		visita.put(partenza, null);

		

		it.addTraversalListener(new TraversalListener<Airport,DefaultWeightedEdge>(){



			@Override

			public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {

				// TODO Auto-generated method stub

				

			}



			@Override

			public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {

				// TODO Auto-generated method stub

				

			}



			@Override

			public void edgeTraversed(EdgeTraversalEvent<DefaultWeightedEdge> ev) {

				Airport sorgente = grafo.getEdgeSource(ev.getEdge());

				Airport destinazione = grafo.getEdgeTarget(ev.getEdge());

				

				if(!visita.containsKey(destinazione) && visita.containsKey(sorgente)) {

					visita.put(destinazione, sorgente);

				} else if(!visita.containsKey(sorgente) && visita.containsKey(destinazione)){

					visita.put(sorgente, destinazione);

				}



			}



			@Override

			public void vertexFinished(VertexTraversalEvent<Airport> arg0) {

				// TODO Auto-generated method stub

				

			}



			@Override

			public void vertexTraversed(VertexTraversalEvent<Airport> arg0) {

				// TODO Auto-generated method stub

				

			}

			

		});

		

		while(it.hasNext())

			it.next();

		

		if(!visita.containsKey(partenza) || !visita.containsKey(destinazione)) {

			return null;

		}

		

		Airport step = destinazione;

		while(!step.equals(partenza)) {

			percorso.add(step);

			step = visita.get(step);

		}

		percorso.add(step);

		

		return percorso;

		

	}

}

