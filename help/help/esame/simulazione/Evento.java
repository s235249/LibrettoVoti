package help.esame.simulazione;

public class Evento {
	
	
	// evento migranti
	
	public class Evento implements Comparable<Evento>{

		private int t;

		private int n;//il numero di persone che sono arrivate e che si sposteranno

		private Country stato;//il paese in cui le persone arrivano, e da cui si sposteranno

		

		public Evento(int t, int n, Country stato) {

			super();

			this.t = t;

			this.n = n;

			this.stato = stato;

		}



		public int getT() {

			return t;

		}



		public int getN() {

			return n;

		}



		public Country getStato() {

			return stato;

		}



		@Override

		public int compareTo(Evento o) {

			return this.t - o.t;

		}

		

		

		

	}
	
	-----------------------------------------------------------------------------
	
	// evento poweroutages
	
	public class Evento implements Comparable<Evento>{



		public enum TIPO{

			INIZIO_INTERRUZIONE,

			FINE_INTERRUZIONE,

			

			CANCELLA_PRESTITO

		}

		private TIPO tipo;

		private Nerc nerc;

		private Nerc donatore;

		private LocalDateTime data;

		

		private LocalDateTime dataInizio;

		private LocalDateTime dataFine;

		

		public Evento(TIPO tipo, Nerc nerc, Nerc donatore, LocalDateTime data, LocalDateTime dataInizio,

				LocalDateTime dataFine) {

			super();

			this.tipo = tipo;

			this.nerc = nerc;

			this.donatore = donatore;

			this.data = data;

			this.dataInizio = dataInizio;

			this.dataFine = dataFine;

		}



		public TIPO getTipo() {

			return tipo;

		}



		public void setTipo(TIPO tipo) {

			this.tipo = tipo;

		}



		public Nerc getNerc() {

			return nerc;

		}



		public void setNerc(Nerc nerc) {

			this.nerc = nerc;

		}



		public Nerc getDonatore() {

			return donatore;

		}



		public void setDonatore(Nerc donatore) {

			this.donatore = donatore;

		}



		public LocalDateTime getData() {

			return data;

		}



		public void setData(LocalDateTime data) {

			this.data = data;

		}



		public LocalDateTime getDataInizio() {

			return dataInizio;

		}



		public void setDataInizio(LocalDateTime dataInizio) {

			this.dataInizio = dataInizio;

		}



		public LocalDateTime getDataFine() {

			return dataFine;

		}



		public void setDataFine(LocalDateTime dataFine) {

			this.dataFine = dataFine;

		}



		@Override

		public int compareTo(Evento o) {

			return this.data.compareTo(o.getData());

		}

		

		

	}

}
