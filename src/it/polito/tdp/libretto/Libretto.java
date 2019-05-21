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
	
	//seleziona sottoinsieme voti che hanno il punteggio specificato (v) -> ritorna lista voti con quel punteggio
	// separare ricerca da formattazione
	
	public List<Voto> cercaVoti(int v)
	{
		List<Voto>result= new ArrayList<Voto>();
		
		for(Voto vo : this.voti)
		{
			if (vo.getValutazione() == v)
			{
				result.add(vo);
			}
		}
		
		return result;
	}
	
	// ricerca voto relativo al corso specificato
	
	public Voto cercaEsame(String esame)
	{
		Voto v= new Voto( 0, esame, null);
		int  pos= this.voti.indexOf(v);
		if (pos==-1)
			return null;
		else 
			return voti.get(pos);
	}
	
	// dato un voto determina se esiste già un voto con uguale corso e uguale punteggio
	
	public boolean esisteGiaVoto(Voto v)
	{
		int i= voti.indexOf(v);
		if (i==-1)
			return false;
		
		else
			return (v.getValutazione() == this.voti.get(i).getValutazione());
		
	}
}