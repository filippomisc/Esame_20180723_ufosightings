package it.polito.tdp.newufosightings.model;

import java.util.HashMap;
import java.util.Map;


public class SightingIdMap {
	
private Map<Integer, Sighting> map;
	
	public SightingIdMap() {
		map = new HashMap<>();
	}
	
	public Sighting get(int sightingID) {
		return map.get(sightingID);
	}
	
	public Sighting get(Sighting sighting) {
		Sighting old = map.get(sighting.getId());
		if (old == null) {
			map.put(sighting.getId(), sighting);
			return sighting;
		}
		return old;
	}
	
	public void put(Sighting sighting, int sightingID) {
		map.put(sightingID, sighting);
	}

}