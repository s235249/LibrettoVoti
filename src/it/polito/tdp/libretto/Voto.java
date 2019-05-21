package it.polito.tdp.libretto;

import java.time.LocalDate;

public class Voto {
	
	private int valutazione;
	private String corso;
	private LocalDate data;
	
	
	public Voto(int valutazione, String corso, LocalDate data) {
		super();
		this.valutazione = valutazione;
		this.corso = corso;
		this.data = data;
	}


	public int getValutazione() {
		return valutazione;
	}


	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}


	public String getCorso() {
		return corso;
	}


	public void setCorso(String corso) {
		this.corso = corso;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "Voto [voto=" + valutazione + ", corso=" + corso + ", data=" + data + "]";
	}
	
	
	
	
	

}
