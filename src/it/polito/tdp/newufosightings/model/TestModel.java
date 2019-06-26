package it.polito.tdp.newufosightings.model;

import java.time.Year;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		model.creaGrafo(Year.of(2000), 3);

//		System.out.println(model.getPesoTot("AL"));
		
		System.out.println(model.stampaPesiGrafo());
	}

}