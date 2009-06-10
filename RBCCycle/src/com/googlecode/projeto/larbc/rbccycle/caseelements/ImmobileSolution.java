package com.googlecode.projeto.larbc.rbccycle.caseelements;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class ImmobileSolution implements CaseComponent {

	private int id;
	private String state;
	private String city;
	private String neighborhood;
	private String street;
	private int number;
	private String name;
	private float builtArea;
	private float totalArea;
	private int garageSpace;
	private int bedroom;
	private int suite;
	private int bathroom;
	private String type;
	private float price;
	private int businessType;
	
	
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

}
