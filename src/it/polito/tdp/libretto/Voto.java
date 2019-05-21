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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Voto [voto=" + valutazione + ", corso=" + corso + ", data=" + data + "]";
	}
	
	
	public Voto clone()
	{
		return (new Voto(this.valutazione, this.corso, this.data));
	}
	
	

}
