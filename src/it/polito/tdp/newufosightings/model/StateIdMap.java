package it.polito.tdp.newufosightings.model;

import java.util.HashMap;
import java.util.Map;



public class StateIdMap {
	
private Map<String, State> map;
	
	public StateIdMap() {
		map = new HashMap<>();
	}
	
	public State get(String StateID) {
		return map.get(StateID);
	}
	
	public State get(State state) {
		State old = map.get(state.getId());
		if (old == null) {
			map.put(state.getId(), state);
			return state;
		}
		return old;
	}
	
	public void put(State state, String stateId) {
		map.put(stateId, state);
	}
}