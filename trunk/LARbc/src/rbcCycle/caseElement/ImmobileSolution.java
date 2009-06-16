package rbcCycle.caseElement;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

/**
 * This class implements the behavior of the solution of the case of the system.
 * @author João Felipe
 *
 */
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
	private int addedBy;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBuiltArea() {
		return builtArea;
	}

	public void setBuiltArea(float builtArea) {
		this.builtArea = builtArea;
	}

	public float getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(float totalArea) {
		this.totalArea = totalArea;
	}

	public int getGarageSpace() {
		return garageSpace;
	}

	public void setGarageSpace(int garageSpace) {
		this.garageSpace = garageSpace;
	}

	public int getBedroom() {
		return bedroom;
	}

	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}

	public int getSuite() {
		return suite;
	}

	public void setSuite(int suite) {
		this.suite = suite;
	}

	public int getBathroom() {
		return bathroom;
	}

	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getBusinessType() {
		return businessType;
	}

	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}

	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", this.getClass());
	}
}
