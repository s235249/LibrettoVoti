package help.esame.ricorsione;

public class Ricorsione {
	
	public List<String> getPercorsoMassimo(String partenza){

		this.ottima = new LinkedList<String>();

		List<String> parziale = new LinkedList<String>();

		parziale.add(partenza);

	

		cercaPercorso(parziale);

		

		return this.ottima;

	}



	private void cercaPercorso(List<String> parziale) {

		

				

		//ottengo tutti i candidati

		List<String> candidati = this.getSuccessori(parziale.get(parziale.size()-1));

		for(String candidato : candidati) {

			if(!parziale.contains(candidato)) {

				//è un candidato che non ho ancora considerato

				parziale.add(candidato);

				this.cercaPercorso(parziale);

				parziale.remove(parziale.size()-1);

			}

		}

		

		

		//vedere se la soluzione corrente è migliore della ottima corrente

		if(parziale.size() > ottima.size()) {

			this.ottima = new LinkedList(parziale);

		}

		

		

	}
	
	
	

}
