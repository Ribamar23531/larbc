package persistence;

import java.util.ArrayList;
import java.util.List;

import persistence.util.NeighborComparator;

public class GerenteDeListagens {
	
	private NeighborComparator comparator;
	private List<String> estados;
	
	public GerenteDeListagens(){
		this.comparator = NeighborComparator.getInstance();
		this.estados = defineEstadosList();
	}

	private List<String> defineEstadosList() {
		List<String> estados = new ArrayList<String>();
		estados.add("AC");
		estados.add("AL");
		estados.add("AM");
		estados.add("AP");
		estados.add("BA");
		estados.add("CE");
		estados.add("DF");
		estados.add("ES");
		estados.add("GO");
		estados.add("MA");
		estados.add("MG");
		estados.add("MS");
		estados.add("MT");
		estados.add("PA");
		estados.add("PB");
		estados.add("PE");
		estados.add("PI");
		estados.add("PR");
		estados.add("RJ");
		estados.add("RN");
		estados.add("RS");
		estados.add("RO");
		estados.add("RR");
		estados.add("SC");
		estados.add("SE");
		estados.add("TO");
		return estados;
	}

	public List<String> getBairros() {
		return this.comparator.getNeigborList();
	}

	public List<String> getEstados() {
		return this.estados;
	}

}
