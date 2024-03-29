package rbcCycle.retrieve;

import java.util.List;
import java.util.Map;

import persistence.util.Coordinates;

import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import rbcCycle.caseElement.ImmobileDescription;
import rbcCycle.retrieve.localSimilarityFunctions.NeighborsProximity;
import rbcCycle.retrieve.localSimilarityFunctions.POIVerification;
import rbcCycle.retrieve.localSimilarityFunctions.RelativePreferencingGreater;

public class SimilarityConfiguration {
	
	private NNConfig configuration;
	private LocalSimilarityFunction function;
	
	/**
	 * Default constructor. All the attributes have the same weight on the calculation of the similarity.
	 */
	public SimilarityConfiguration(){
		this.configuration = new NNConfig();
		this.defineAttributeConfigurationEqual("state", new Double("1"));
		this.defineAttributeConfigurationEqual("city", new Double("1"));
		this.defineAttributeConfigurationNeighborProximity("neighborhood", new Double("1")); //ver pesos
		this.defineAttributeConfigurationEqual("street", new Double("1")); //ver pesos
		this.defineAttributeConfigurationEqual("name", new Double("1"));
		this.defineAttributeConfigurationEqual("builtArea", new Double("1")); //ver pesos
		this.defineAttributeConfigurationEqual("totalArea", new Double("1")); //ver pesos
		this.defineAttributeConfigurationEqual("garageSpace", new Double("1"));
		this.defineAttributeConfigurationEqual("bedroom", new Double("1"));
		this.defineAttributeConfigurationEqual("suite", new Double("1"));
		this.defineAttributeConfigurationEqual("bathroom", new Double("1"));
		this.defineAttributeConfigurationEqual("type", new Double("1"));
		this.defineAttributeConfigurationInterval("price", new Double("1"), new Double("5")); //ver pesos
		this.defineAttributeConfigurationEqual("businessType", new Double("1"));
		this.configuration.setDescriptionSimFunction(new Average());
	}
	
	public SimilarityConfiguration(Map<String, String> values, Double priceWeight, Double localeWeight, List<String> pointsOfInterest, Coordinates queryCoordinate){
		this.configuration = new NNConfig();
		String blank = "";
		String zeroFloat = "0.0";
		String zeroInteger = "0";
		String infinity = new Double(Double.MAX_VALUE).toString();
		if(!values.get("state").equals(blank)){
			this.defineAttributeConfigurationEqual("state", new Double("3"));
		}
		if(!values.get("city").equals(blank)){
			this.defineAttributeConfigurationEqual("city", new Double("3"));
		}
		if(!values.get("neighborhood").equals(blank)){
			this.defineAttributeConfigurationNeighborProximity("neighborhood", new Double("3"));
		}
		if(!values.get("street").equals(blank)){
			this.defineAttributeConfigurationEqual("street", new Double("3"));
		}
		if(!values.get("name").equals(blank)){
			this.defineAttributeConfigurationEqual("name", new Double("3"));
		}
		if(!values.get("builtArea").equals(zeroFloat)){
			this.defineAttributeConfigurationRelativeGreater("builtArea", new Double("3"));
		}
		if(!values.get("totalArea").equals(zeroFloat)){
			this.defineAttributeConfigurationRelativeGreater("totalArea", new Double("3"));
		}
		if(!values.get("garageSpace").equals(zeroInteger)){
			this.defineAttributeConfigurationRelativeGreater("garageSpace", new Double("3"));
		}
		if(!values.get("bedroom").equals(zeroInteger)){
			this.defineAttributeConfigurationRelativeGreater("bedroom", new Double("3"));
		}
		if(!values.get("suite").equals(zeroInteger)){
			this.defineAttributeConfigurationRelativeGreater("suite", new Double("3"));
		}
		if(!values.get("bathroom").equals(zeroInteger)){
			this.defineAttributeConfigurationRelativeGreater("bathroom", new Double("3"));
		}
		if(!values.get("type").equals(blank)){
			this.defineAttributeConfigurationEqual("type", new Double("3"));
		}
		if(!values.get("price").equals(zeroFloat)){
			this.defineAttributeConfigurationRelativeGreater("price", priceWeight);
		}
		if(!values.get("businessType").equals(blank)){
			this.defineAttributeConfigurationEqual("businessType", new Double("3"));
		}
		if(!values.get("latitude").equals(infinity) && !values.get("longitude").equals(infinity)){
			this.defineAttributeConfigurationThereIsNear("location", localeWeight, pointsOfInterest, queryCoordinate);
		}
		this.configuration.setDescriptionSimFunction(new Average());
	}
	
	private void defineAttributeConfigurationThereIsNear(String attributeName, Double localeWeight, List<String> pointsOfInterest, Coordinates queryCoordinate) {
		Attribute attribute = new Attribute(attributeName, ImmobileDescription.class);
		this.function = new POIVerification(pointsOfInterest, queryCoordinate);
		this.configuration.addMapping(attribute, this.function);
		this.configuration.setWeight(attribute, localeWeight);
	}

	private void defineAttributeConfigurationEqual(String attributeName, Double weight){
		Attribute attribute = new Attribute(attributeName, ImmobileDescription.class);
		function = new Equal();
		this.configuration.addMapping(attribute, function);
		this.configuration.setWeight(attribute, weight);
	}
	
	private void defineAttributeConfigurationInterval(String attributeName, Double weight, Double interval){
		Attribute attribute = new Attribute(attributeName, ImmobileDescription.class);
		this.configuration.addMapping(attribute, new Interval(interval));
		this.configuration.setWeight(attribute, weight);
	}
	
	private void defineAttributeConfigurationNeighborProximity(String attributeName, Double weight){
		Attribute attribute = new Attribute(attributeName, ImmobileDescription.class);
		this.function = new NeighborsProximity();
		this.configuration.addMapping(attribute, this.function);
		this.configuration.setWeight(attribute, weight);
	}
	
	private void defineAttributeConfigurationRelativeGreater(String attributeName, Double weight){
		Attribute attribute = new Attribute(attributeName, ImmobileDescription.class);
		this.function = new RelativePreferencingGreater();
		this.configuration.addMapping(attribute, function);
		this.configuration.setWeight(attribute, weight);
	}
	
	public NNConfig getConfiguration(){
		return this.configuration;
	}
}
