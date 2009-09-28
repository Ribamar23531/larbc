package com.googlecode.projeto1.client.panels;

import java.util.List;

import com.googlecode.projeto1.client.panels.exceptions.FieldsNotFilledExeption;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class FieldsChecker {
	
	//TODO CLASSE AINDA EM CONSTRUÇÃO
	
	public static void checkFields(List<String[]> fields) throws FieldsNotFilledExeption{
		String message = "Os seguintes campos não foram preenchidos adequadamente:";
		boolean mustThrowException = false;
		for (String[] tuple : fields) {
			String type = tuple[0];
			String value = tuple[1];
			String field = tuple[2];
			if(type.equals("String") && value.isEmpty()){
				message += "<br> *" + field;
				mustThrowException = true;
			}else if(type.equals("float") && !isFloatFormat(value)){
				message += "<br> *" + field + " (valor numérico com ponto)";
				mustThrowException = true;
			}else if(type.equals("int") && !isIntFormat(value)){
				message += "<br> *" + field + " (valor numérico)";
				mustThrowException = true;
			}
		}
		if(mustThrowException){
			throw new FieldsNotFilledExeption(message);
		}		
		
	}
	
	private static boolean isIntFormat(String value) {
		if(isEmpty(value)){
			return false;
		}
		try{
			Integer.parseInt(value);					
		}catch(Exception e){
			return false;
		}
		return true;
	}

	private static boolean isFloatFormat(String value) {
		if(isEmpty(value)){
			return false;
		}
		try{
			Float.parseFloat(value);					
		}catch(Exception e){
			return false;
		}
		return true;
	}

	private static boolean isEmpty(String value) {
		return value.equals("");		
	}
	
//	String message = "Favor digitar ";		
//	if(isEmpty(cityTextBox)){
//		MessageBox.alert(message + "a cidade.");
////		fields.add("Cidade");
//		return false;
//	}
//	if(isEmpty(neighborhoodTextBox)){
//		MessageBox.alert(message + "o bairro.");
////		fields.add("Bairro");
//		return false;
//	}
//	if(isEmpty(numberTextBox)){
//		MessageBox.alert(message + "o número.");
////		fields.add("Número");
//		return false;
//	}
//	if(stateListBox.getSelectedIndex() == 0){
//		MessageBox.alert(message + "o estado.");
////		fields.add("Estado");
//		return false;
//	}
//	if(isEmpty(streetTextBox)){
//		MessageBox.alert(message + "a rua.");
//		return false;
//	}
//	if(!isFloatFormat(areaConstruidaTextBox)){
//		MessageBox.alert(message + "a área construída.");
//		return false;
//	}
//	if(!isFloatFormat(areaTotalTextBox)){
//		MessageBox.alert(message + "a área total.");
//		return false;
//	}
//	if(!isIntFormat(garageTextBox)){
//		MessageBox.alert(message + "a quantidade de garagens.");
//		return false;
//	}
//	if(!isIntFormat(qteBedroomsTextBox)){
//		MessageBox.alert(message + "a quantidade de quartos.");
//		return false;
//	}
//	if(!isIntFormat(qteSuitesTextBox)){
//		MessageBox.alert(message + "a quantidade de suítes.");
//		return false;
//	}
//	if(!isIntFormat(qteBathroomsTextBox)){
//		MessageBox.alert(message + "a quantidade de banheiros.");
//		return false;
//	}
////	if(typeComboBox.getSelectedIndex() == 0){
////		fields.add(message + "Tipo de Negócio");
////		result = false;
////	}
//	if(!isFloatFormat(priceTextBox)){
//		MessageBox.alert(message + "o preço.");
//		return false;
//	}
//	if(SelectedLocation.getLocation().equals("")){
//		MessageBox.alert("Favor ajustar coordenadas.");
//		return false;
//	}
//	return true;
//}


}
