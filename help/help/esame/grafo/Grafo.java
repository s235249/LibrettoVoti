package help.esame.grafo;

public class Grafo {

	
public void creaGrafo(int distanzaMedia) {

		

		//Aggiungere i vertici

		Graphs.addAllVertices(grafo, aIdMap.values());

		

		for(Rotta rotta : dao.getRotte(aIdMap, distanzaMedia)) {

			//controllo se esiste già un arco

			//se esiste, aggiorno il peso

			DefaultWeightedEdge edge = grafo.getEdge(rotta.getPartenza(), rotta.getDestinazione());

			if(edge == null) {

				Graphs.addEdge(grafo, rotta.getPartenza(), rotta.getDestinazione(), rotta.getDistanzaMedia());

			} else {

				double peso = grafo.getEdgeWeight(edge);

				double newPeso = (peso + rotta.getDistanzaMedia())/2;

				System.out.println("Aggiornare peso! Peso vecchio: " + peso + " peso nuovo: " + newPeso);



				grafo.setEdgeWeight(edge, newPeso);

			}

		}
	
	
	
}
