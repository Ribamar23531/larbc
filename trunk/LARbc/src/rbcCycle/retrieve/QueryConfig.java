package rbcCycle.retrieve;

import rbcCycle.caseElement.ImmobileDescription;
import jcolibri.cbrcore.CBRQuery;

/**
 * Prepare the query to be done in the retrieve step of the CBR cycle.
 * @author Joao Felipe 
 *
 */
public class QueryConfig {
	
	private CBRQuery query;
	private ImmobileDescription problemDescription;
	
	/**
	 * The constructor of the query configurator.
	 */
	public QueryConfig(){
		this.query = new CBRQuery();
		this.problemDescription = new ImmobileDescription();
	}
	
	/**
	 * Defines the attributes of the query with the given attributes.
	 * @param state The state which the immobile is localized.
	 * @param city The city which the immobile is localized.
	 * @param neighborhood The neighborhood which the immobile is localized. 
	 * @param street The street which the immobile is localized.
	 * @param name The name of the immobile. (if it is a house, don't have a name)
	 * @param builtArea The built area of the immobile. 
	 * @param totalArea The total area of the immobile.
	 * @param garageSpace How many garage spaces there is/are.
	 * @param bedroom How many rooms there is/are.
	 * @param suite How many of these bedrooms have/has a bathroom.
	 * @param bathroom How many social bathrooms there is/are. 
	 * @param type The type of the immobile. 
	 * @param price The price of this.
	 * @param businessType The type of business.
	 */
	public void setQuery(String state, String city, String neighborhood, String street, String name,
						 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
						 int bathroom, String type, float price, int businessType){
		this.problemDescription.setState(state);
		this.problemDescription.setCity(city);
		this.problemDescription.setNeighborhood(neighborhood);
		this.problemDescription.setStreet(street);
		this.problemDescription.setName(name);
		this.problemDescription.setBuiltArea(builtArea);
		this.problemDescription.setTotalArea(totalArea);
		this.problemDescription.setGarageSpace(garageSpace);
		this.problemDescription.setBedroom(bedroom);
		this.problemDescription.setSuite(suite);
		this.problemDescription.setBathroom(bathroom);
		this.problemDescription.setType(type);
		this.problemDescription.setPrice(price);
		this.problemDescription.setBusinessType(businessType);
		this.query.setDescription(this.problemDescription);
	}
	
	/**
	 * Get the query to retrieve the results based on it. 
	 * @return The query.
	 */
	public CBRQuery getQuery(){
		return this.query;
	}

}
