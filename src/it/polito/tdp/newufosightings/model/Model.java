package it.polito.tdp.newufosightings.model;

import java.time.Year;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.ResolutionSyntax;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.slf4j.impl.StaticLoggerBinder;

import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;



public class Model {

//	private List<AnnoEAvvistamento> anniAvvistamenti;
	NewUfoSightingsDAO dao = null;
	
	List<Sighting> sightings;
	List<State> states;
//	List<Neighbor> neighbors;
	
	SightingIdMap sightingIdMap;
	StateIdMap stateIdMap;
//	NeighborIdMap neighborIdMap;
	
	
	private Graph<State, DefaultWeightedEdge> graph;
//	private List<String> stati;
	
	
	
	public Model() {
		
		dao = new NewUfoSightingsDAO();
		
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);	
//		this.stati = new ArrayList<>();
		
		sightingIdMap = new SightingIdMap();
		
		stateIdMap = new StateIdMap();
//		neighborIdMap = new NeighborIdMap();
		
		sightings = dao.getAllSightings(sightingIdMap, stateIdMap);
		System.out.println(sightings.size());
		
		states = dao.getAllState(stateIdMap);
		System.out.println(states.size());
		
//		neighbors = dao.getAllNeighbor(neighborIdMap, stateIdMap);
//		System.out.println(neighbors.size());
		
		
		//per cercare lo stato con l'id MAIUSCOLO
		String daTrasf = "ca";
		System.out.println(stateIdMap.get(daTrasf.toUpperCase()).toString());
}


	public void creaGrafo(Year annOk, int giorni) {
		
		int i=1;

		Graphs.addAllVertices(this.graph, states);
		
		System.out.println("vertici creati: " + this.graph.vertexSet().size());
		
		for (State sOrig : this.graph.vertexSet()) {
			
			List<State> vicini = dao.getStatiConfinanti(sOrig.getId(), stateIdMap);
			
			for (State vicino : vicini) {
				
				int peso = dao.esisteArco(giorni, annOk, sOrig, vicino, stateIdMap);
				
				Graphs.addEdge(this.graph, sOrig, vicino, peso);
				
				System.out.println(i + " " + sOrig.getId() +  " " +  vicino.getId() + " " + peso);
				
				i++;
			}	
		}
		
		System.out.println("archi creati: " + this.graph.edgeSet().size());
	}
	
	public double getPesoTot (State state) {
		
		double result = 0;
		
		for(DefaultWeightedEdge arco : this.graph.edgesOf(stateIdMap.get(state))){
			result += this.graph.getEdgeWeight(arco);
		}
		
		return result;
		
	}
	
	

	public double getPesoTot (String id) {
		
		double result = 0;
		
		for(DefaultWeightedEdge arco : this.graph.edgesOf(stateIdMap.get(id))){
			result += this.graph.getEdgeWeight(arco);
		}
		
		return result;
		
	}
	
	
	public String stampaPesiGrafo() {
		
//		StringBuffer result = new StringBuffer();
		String result = "";
		
		for (State state : this.graph.vertexSet()) {

			result += "\n" + state.getId() + "    peso: " + getPesoTot(state) + "\n";
		}
		
//		return result.toString();
		return result;
		
	}
	
	
	
	
}