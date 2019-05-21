package it.polito.tdp.libretto;

import java.util.Comparator;

public class votiComparator implements Comparator<Voto> {

	@Override
	public int compare(Voto v1, Voto v2) {
	
		String c1= v1.getCorso().toUpperCase();
		String c2= v2.getCorso().toUpperCase();
		return c1.compareTo(c2);
	}

}
