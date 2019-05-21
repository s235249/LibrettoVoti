package it.polito.tdp.libretto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

	public boolean add(Voto v) {
		
		if (!this.esisteGiaVoto(v) && !this.votoConflitto(v))
			{voti.add(v);
			return true;}
		else 
			{return false;}
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
	
	// se non esiste o ha stesso punteggio, no conflitto --- se esiste con punteggio diverso, conflitto
	
	public boolean votoConflitto(Voto v)
	{

		int i= voti.indexOf(v);
		if (i==-1)
			return false;
		
		else
			return (v.getValutazione() != this.voti.get(i).getValutazione());
	}
	
	public String toString() {
		return this.voti.toString();
	}
	
	public Libretto librettoMigliorato()
	{
		Libretto nuovo= new Libretto();
		
		for (Voto v: this.voti)
			{ 
			 nuovo.add(v.clone());}
		
		for (Voto v: nuovo.voti)
		{
			int punti= v.getValutazione();
			if (punti<24)
				punti= punti+1;
			else if (punti<=28)
				punti=punti+2;
			
			v.setValutazione(punti);
		}
		
		return nuovo;
		
	}
	
	public void cancellaVotiScarsi() {
		List<Voto> cancellare= new ArrayList<Voto>();
		
		for (Voto v: this.voti)
		{
			if (v.getValutazione()<24)
				cancellare.add(v);
		}
		
		this.voti.removeAll(cancellare);
	}
	
	public List<Voto> ordinaCorsi()
	{
		Collections.sort(voti, new votiComparator());
		return voti;
		
	}

	public List<Voto> ordinaValutazioni()
	{
		Collections.sort(voti, new valutazioniComparator());
		return voti;
		
	}

	
	
}