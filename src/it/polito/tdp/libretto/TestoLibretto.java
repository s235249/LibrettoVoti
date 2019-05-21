package it.polito.tdp.libretto;

import java.time.LocalDate;
import java.util.List;

public class TestoLibretto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Libretto libr = new Libretto();
		
		libr.add(new Voto(30, "Analisi I", LocalDate.of(2017, 1, 15)));
		libr.add(new Voto(21, "Analisi II", LocalDate.of(2018, 1, 25)));
		libr.add(new Voto(25, "Fisica I", LocalDate.of(2017, 6, 10)));
		libr.add(new Voto(28, "Fisica II", LocalDate.of(2018, 9, 3)));
		libr.add(new Voto(18, "Geometria", LocalDate.of(2017, 9, 1)));
		libr.add(new Voto(20, "Economia", LocalDate.of(2018, 1, 28)));
		libr.add(new Voto(25, "Ricerca Operativa", LocalDate.of(2018, 6, 5)));
		libr.add(new Voto(24, "Complementi di Diritto", LocalDate.of(2018, 2, 15)));
		libr.add(new Voto(25, "Logistica", LocalDate.of(2019, 2, 1)));
		libr.add(new Voto(27, "Programmazione a Oggetti", LocalDate.of(2019, 1, 25)));
		
		List<Voto> venticinque= libr.cercaVoti(25);
		System.out.println(venticinque);
		
		List<Voto> ventinove= libr.cercaVoti(29);
		System.out.println(ventinove);
		
		Voto e= libr.cercaEsame("Economia");
		Voto s= libr.cercaEsame("Statistica");
		
		System.out.println(e);
		System.out.println(s);
		
		Voto giusto = new Voto(18, "Geometria", LocalDate.of(2017, 9, 1));
		Voto sbagliato= new Voto(28, "Geometria", LocalDate.of(2017, 9, 1));
		Voto mancante= new Voto( 19, "Analisi III", LocalDate.now());
		
		System.out.format("Il voto %s è %s\n", 
							giusto.toString(), libr.esisteGiaVoto(giusto) );
		System.out.format("Il voto %s è %s\n", 
				sbagliato.toString(), libr.esisteGiaVoto(sbagliato) );
		System.out.format("Il voto %s è %s\n", 
				mancante.toString(), libr.esisteGiaVoto(mancante) );
		
	
	}

}
