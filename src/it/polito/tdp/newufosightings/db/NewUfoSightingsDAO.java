package it.polito.tdp.newufosightings.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.newufosightings.model.Sighting;
import it.polito.tdp.newufosightings.model.SightingIdMap;
import it.polito.tdp.newufosightings.model.State;
import it.polito.tdp.newufosightings.model.StateIdMap;



public class NewUfoSightingsDAO {
	
	public List<Sighting> getAllSightings(SightingIdMap sightingIdMap, StateIdMap stateIdMap) {
		String sql = "SELECT * FROM sighting" ;
		try {
			Connection conn = ConnectDB.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Sighting> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				
				
				State state = stateIdMap.get(res.getString("state"));
				
				Sighting sighting = new Sighting(res.getInt("id"),
						res.getTimestamp("datetime").toLocalDateTime(),
						res.getString("city"), 
						state, 
						res.getString("country"),
						res.getString("shape"),
						res.getInt("duration"),
						res.getString("duration_hm"),
						res.getString("comments"),
						res.getDate("date_posted").toLocalDate(),
						res.getDouble("latitude"), 
						res.getDouble("longitude"));
				
				list.add(sightingIdMap.get(sighting));
				
//				*****se vogliamo aggiungere ad uno stato tutti i suoi avvistamenti******
//				state.getSightings().add(sightingIdMap.get(sighting));
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	public List<State> getAllState(StateIdMap stateIdMap){
		
		String sql = "SELECT * FROM state" ;
		try {
			Connection conn = ConnectDB.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<State> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				
				
				State state = new State(res.getString("id"), res.getString("Name"), res.getString("Capital"), res.getDouble("Lat"), res.getDouble("Lng"), res.getInt("Area"), res.getInt("Population"), res.getString("Neighbors"));
				
				
				list.add(stateIdMap.get(state)) ;
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

//	public List<Neighbor> getAllNeighbor(NeighborIdMap neighborIdMap, StateIdMap stateIdMap){
//		
//		String sql = "SELECT * FROM neighbor" ;
//		try {
//			Connection conn = DBConnect.getConnection() ;
//
//			PreparedStatement st = conn.prepareStatement(sql) ;
//			
//			List<Neighbor> list = new ArrayList<>() ;
//			
//			ResultSet res = st.executeQuery() ;
//			
//			int counter = 0;
//			
//			while(res.next()) {
//				
//				State state1 = stateIdMap.get(res.getString("state1"));
//				State state2 = stateIdMap.get(res.getString("state2"));
//
//				Neighbor neighbor = new Neighbor(counter, state1, state2);
//				
//				
//				
//				list.add(neighbor) ;
//				counter++;
//				
//				state1.getVicini().add(stateIdMap.get(state2));
//				
//			}
//			
//			conn.close();
//			return list ;
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null ;
//		}
//	}
	
//public int getAllNeighbor(){
//		
//		String sql = "SELECT * FROM neighbor" ;
//		try {
//			Connection conn = DBConnect.getConnection() ;
//
//			PreparedStatement st = conn.prepareStatement(sql) ;
//			
//			List<Neighbor> list = new ArrayList<>() ;
//			
//			ResultSet res = st.executeQuery() ;
//			
//			while(res.next()) {
//				list.add(new Neighbor(res.getString("state1"), res.getString("state2"))) ;
//			}
//			
//			conn.close();
//			return list ;
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null ;
//		}
//	}
	
//	public List<AnnoEAvvistamento> getAnni() {
//		String sql = "select distinct year(datetime) as anno, count(id) as cnt\r\n" + 
//				"from sighting\r\n" + 
//				"where country='us'\r\n" + 
//				"group by anno\r\n" + 
//				"order by anno asc" ;
//		try {
//			Connection conn = DBConnect.getConnection() ;
//
//			PreparedStatement st = conn.prepareStatement(sql) ;
//			
//			List<AnnoEAvvistamento> anni = new ArrayList<>() ;
//			
//			ResultSet res = st.executeQuery() ;
//			
//			while(res.next()) {
//				
//				
//				AnnoEAvvistamento aEa = new AnnoEAvvistamento(Year.of(res.getInt("anno")), res.getInt("cnt"));
//				
//				anni.add(aEa);
//			}
//				
//			
//			conn.close();
//			return anni ;
//
//		} catch (SQLException e) {
//			e.printStackTrace();		
//			return null;
//			}
//}
	
//public boolean esisteArco (Year anno, State s1, State s2, StateIdMap stateIdMap){
//		
//		String sql = "select distinct count(*) as c " + 
//				"from sighting s1, sighting s2 " + 
//				"where s1.country='us' " + 
//				"and s2.country='us' " + 
////				"and s1.state<>s2.state " +
//				"and s1.state=? " + 
//				"and s2.state=? " + 
//				"and year(s1.datetime)=? " + 
//				"and year(s1.datetime)=year(s2.datetime) " + 
//				"and s2.datetime>s1.datetime" ;
//		try {
//			Connection conn = ConnectDB.getConnection() ;
//
//			PreparedStatement st = conn.prepareStatement(sql) ;
//			st.setString(1, stateIdMap.get(s1).getId());
//			st.setString(2, stateIdMap.get(s2).getId());
//			st.setInt(3, anno.getValue());
//					
//			ResultSet res = st.executeQuery();
//			res.first();
//			
////			while(res.next()) {
//				
//				int risultato = res.getInt("c");
//				
//				conn.close();
//
//				
//				if(risultato==0) 
//					return false;
//				else 
//					return true;
//					
////			}
//				
////			conn.close();
////			return false;
//
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		return false;
//		}
//}




//public List<StatePair> getEdges(Year anno, StateIdMap stateIdMap) {
//	String sql = "select s1.state as state1 , s2.state as state2, count(*) as cnt" + 
//			"from sighting s1, sighting s2 " + 
//			"where year(s1.datetime)=year(s2.datetime) " + 
//			"and year(s1.datetime)=? " + 
//			"and s1.country='us' " + 
//			"and s2.country='us' " + 
//			"and s2.datetime>s1.datetime " + 
//			"and s1.state<>s2.state " + 
//			"group by s1.state, s2.state " ;
//	
//	try {
//		Connection conn = DBConnect.getConnection() ;
//		PreparedStatement st = conn.prepareStatement(sql) ;
//		
//		st.setInt(1, anno.getValue());
//		
//		ResultSet res = st.executeQuery() ;
//		
//		List<StatePair> list = new ArrayList<>() ;
//		
//		while(res.next()) {
//			
//			State s1 = stateIdMap.get(res.getString("state1"));
//			State s2 = stateIdMap.get(res.getString("state2"));
//			
//			list.add(new StatePair(s1, s2, res.getInt("cnt")));
//			
//		}
//		conn.close();
//		return list ;
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		return null ;
//	}
//	
//}


//numero di avvistamenti di uno stato in un certo anno
public int getNumAvvistamenti(Year anno, String idStato) {
	
	String sql = "select count(sighting.id) as cnt\r\n" + 
			"from sighting , state\r\n" + 
			"where sighting.state=state.id \r\n" + 
			"and sighting.state=?\r\n" + 
			"and year(sighting.datetime)=?\r\n" + 
			"group by sighting.state";

	try {
		Connection conn = ConnectDB.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		st.setString(1, idStato);
		st.setInt(2, anno.getValue());
				
		ResultSet res = st.executeQuery();
		res.first();
		
//		while(res.next()) {
			
			int risultato = res.getInt("cnt");
			
			conn.close();

			return risultato;
//			if(risultato==0) 
//				return false;
//			else 
//				return true;
				
//		}
			
//		conn.close();
//		return false;


	} catch (SQLException e) {
		e.printStackTrace();
	return 0;
	}
}
	
//numero di stati vicini di uno stato
public int getNumVicini (String idStato) {
	String sql = "select count(*) as cnt\r\n" + 
			"from neighbor\r\n" + 
			"where neighbor.state1=?\r\n" + 
			"group by neighbor.state1";

	try {
		Connection conn = ConnectDB.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		st.setString(1, idStato);
				
		ResultSet res = st.executeQuery();
		res.first();
		
//		while(res.next()) {
			
			int risultato = res.getInt("cnt");
			
			conn.close();

			return risultato;
//			if(risultato==0) 
//				return false;
//			else 
//				return true;
				
//		}
			
//		conn.close();
//		return false;


	} catch (SQLException e) {
		e.printStackTrace();
	return 0;
	}
}

//numero di stati vicini di uno stato
public List<State> getStatiConfinanti (String idStato, StateIdMap stateIdMap) {
	String sql = "select neighbor.state2 as s2\r\n" + 
			"from neighbor\r\n" + 
			"where neighbor.state1=?";

	try {
		Connection conn = ConnectDB.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		st.setString(1, idStato);
				
		List<State> listVicini = new ArrayList<>();
		
		ResultSet res = st.executeQuery();
//		res.first();
		
		while(res.next()) {
			
			
			State state = stateIdMap.get(res.getString("s2"));
			
			
			
			listVicini.add(state) ;
			
			stateIdMap.get(idStato).getVicini().add(state);

				
		}
			
		conn.close();
		return listVicini;


	} catch (SQLException e) {
		e.printStackTrace();
	return null;
	}
	}





public int esisteArco (int giorno, Year anno, State s1, State s2, StateIdMap stateIdMap){

String sql = "select count(distinct s1.id) as cnt\r\n" + 
		"from sighting s1, sighting s2\r\n" + 
		"where s1.state=?\r\n" + 
		"and s2.state=?\r\n" + 
		"and year(s1.datetime)=?\r\n" + 
		"and DATEDIFF(s1.datetime,s2.datetime)<?\r\n" + 
		"and year(s1.datetime)=year(s2.datetime)" ;
try {
	Connection conn = ConnectDB.getConnection() ;

	PreparedStatement st = conn.prepareStatement(sql) ;
	st.setString(1, stateIdMap.get(s1).getId());
	st.setString(2, stateIdMap.get(s2).getId());
	st.setInt(3, anno.getValue());
	st.setInt(4, giorno);
	
			
	ResultSet res = st.executeQuery();
	res.first();
	
//	while(res.next()) {
		
		int risultato = res.getInt("cnt");
		
		conn.close();


			
//	}
		
//	conn.close();
	return risultato;


} catch (SQLException e) {
	e.printStackTrace();
return 0;
}
}
}

