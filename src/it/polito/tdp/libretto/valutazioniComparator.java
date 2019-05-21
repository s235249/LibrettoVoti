package it.polito.tdp.libretto;

import java.util.Comparator;

public class valutazioniComparator implements Comparator<Voto> {


	@Override
	public int compare(Voto v1, Voto v2) {
		
		int val1= v1.getValutazione();
		int val2= v2.getValutazione();
		
		if (val1<val2)
			return 1;
		
		return -1;
	}

}
