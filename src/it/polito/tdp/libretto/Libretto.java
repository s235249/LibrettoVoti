package it.polito.tdp.libretto;

import java.util.ArrayList;
import java.util.List;

public class Libretto {

	private List <Voto> voti;

	public Libretto() {
		super();
		this.voti = new ArrayList<Voto>();
		// ordine di inserimento
	}
	
	// aggiunge voto a libretto con solo voto come parametro
	// delega (uso stesso nome)
	
	

	public void add(Voto v) {
		voti.add(v);
	}
	
	
	
}