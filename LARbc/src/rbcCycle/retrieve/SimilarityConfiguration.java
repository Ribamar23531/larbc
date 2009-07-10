package rbcCycle.retrieve;

import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import rbcCycle.caseElement.ImmobileDescription;
import rbcCycle.retrieve.localSimilarityFunctions.Like;

public class SimilarityConfiguration {
	
	private NNConfig configuration;
	private LocalSimilarityFunction function;
	
	/**
	 * Default constructor. All the attributes have the same weight on the calculation of the similarity.
	 */
	public SimilarityConfiguration(){
		this.configuration = new NNConfig();
//		this.defineAttributeConfigurationEqual("state", new Double("0.1"));
//		this.defineAttributeConfigurationEqual("city", new Double("0.1"));
//		this.defineAttributeConfigurationEqual("neighborhood", new Double("1")); //ver pesos
//		this.defineAttributeConfigurationEqual("street", new Double("0.6")); //ver pesos
//		this.defineAttributeConfigurationEqual("name", new Double("0.1"));
//		this.defineAttributeConfigurationEqual("builtArea", new Double("0.8")); //ver pesos
//		this.defineAttributeConfigurationEqual("totalArea", new Double("0.3")); //ver pesos
//		this.defineAttributeConfigurationEqual("garageSpace", new Double("0.1"));
//		this.defineAttributeConfigurationEqual("bedroom", new Double("0.1"));
//		this.defineAttributeConfigurationEqual("suite", new Double("0.1"));
//		this.defineAttributeConfigurationEqual("bathroom", new Double("0.1"));
//		this.defineAttributeConfigurationEqual("type", new Double("0.1"));
//		this.defineAttributeConfigurationEqual("price", new Double("0.7")); //ver pesos
//		this.defineAttributeConfigurationEqual("businessType", new Double("0.1"));

		this.defineAttributeConfigurationEqual("state", new Double("1"));
		this.defineAttributeConfigurationEqual("city", new Double("1"));
		this.defineAttributeConfigurationLike("neighborhood", new Double("1")); //ver pesos
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
	
	public SimilarityConfiguration(Double stateWeight, Double cityWeight, Double neigborhoodWeight, Double streetWeight, Double nameWeight,
								   Double builtAreaWeight, Double totalAreaWeight, Double garageSpaceWeight, Double bedroomWeight,
								   Double suiteWeight, Double bathroomWeight, Double typeWeight, Double priceWeight, Double businessTypeWeight){
		this.defineAttributeConfigurationEqual("state", stateWeight);
		this.defineAttributeConfigurationEqual("city", cityWeight);
		this.defineAttributeConfigurationLike("neighborhood", neigborhoodWeight); //ver pesos
		this.defineAttributeConfigurationEqual("street", streetWeight); //ver pesos
		this.defineAttributeConfigurationEqual("name", nameWeight);
		this.defineAttributeConfigurationEqual("builtArea", builtAreaWeight); //ver pesos
		this.defineAttributeConfigurationEqual("totalArea", totalAreaWeight); //ver pesos
		this.defineAttributeConfigurationEqual("garageSpace", garageSpaceWeight);
		this.defineAttributeConfigurationEqual("bedroom", bedroomWeight);
		this.defineAttributeConfigurationEqual("suite", suiteWeight);
		this.defineAttributeConfigurationEqual("bathroom", bathroomWeight);
		this.defineAttributeConfigurationEqual("type", typeWeight);
		this.defineAttributeConfigurationInterval("price", priceWeight, new Double("5")); //ver intervalos
		this.defineAttributeConfigurationEqual("businessType", businessTypeWeight);
		this.configuration.setDescriptionSimFunction(new Average());
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
	
	private void defineAttributeConfigurationLike(String attributeName, Double weight){
		Attribute attribute = new Attribute(attributeName, ImmobileDescription.class);
		this.configuration.addMapping(attribute, new Like());
		this.configuration.setWeight(attribute, weight);
	}
	
	public NNConfig getConfiguration(){
		return this.configuration;
	}
}
