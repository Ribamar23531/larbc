package com.googlecode.projeto1.client.panels;

import com.google.gwt.user.client.ui.Image;

public class Util {
	
	public final static String LARBC_IMAGE_PATH = "images/larbc.png";
	public final static String FAMILY_IMAGE_PATH= "images/familia.png";
	public final static String SOLTO_MAIOR_IMAGE_PATH = "images/soltoMaior.png";
	public final static String PREENCHIMENTO_PATH = "images/preenchimento.png";
	public final static String ADMINISTRAR_BUTTON_IMAGE = "images/botaoAdministrar.PNG";
	
	public static Image createImage(String path){
		Image image = new Image(path);
		image.setSize("auto", "auto");
		return image;
	}

}
