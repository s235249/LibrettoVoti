package help.esame.query;

public class Query {
	
	public List<Rotta> getRotte(Map<Integer,Airport> aIdMap, int distanzaMedia){

		String sql = "SELECT ORIGIN_AIRPORT_ID as id1, DESTINATION_AIRPORT_ID as id2, AVG(DISTANCE) as avgg " + 

				"FROM flights " + 

				"GROUP BY ORIGIN_AIRPORT_ID, DESTINATION_AIRPORT_ID " + 

				"HAVING avgg > ? ";

		List<Rotta> result = new ArrayList<Rotta>();

		

		try {

			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, distanzaMedia);

			ResultSet rs = st.executeQuery();



			while (rs.next()) {

				Airport partenza = aIdMap.get(rs.getInt("id1"));

				Airport destinazione = aIdMap.get(rs.getInt("id2"));

				

				if(partenza == null || destinazione == null) {

					throw new RuntimeException("Problema in getRotte");

				}



				Rotta rotta = new Rotta(partenza, destinazione, rs.getDouble("avgg"));

				result.add(rotta);

			}



			conn.close();

			return result;



		} catch (SQLException e) {

			e.printStackTrace();

			System.out.println("Errore connessione al database");

			throw new RuntimeException("Error Connection Database");

		}

		

	}

}
