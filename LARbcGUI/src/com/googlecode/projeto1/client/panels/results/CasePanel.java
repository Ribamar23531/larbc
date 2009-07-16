package com.googlecode.projeto1.client.panels.results;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.widgets.Panel;

public class CasePanel extends Panel{
	
	private Image picture;

	public CasePanel(){
		super("Caso");
		picture = Util.createImage(Util.RESIDENTIAL_IMMOBILE_PATH, 120);
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(picture);
		Panel p = new Panel();
		String html = 	"<br>Endereço: Rua Bla-bla-bla Numero X</br> " +
						"<br>Características: Muito bom, pode comprar!!! </br> " +
						"<br>Preço: Quase de graça!!!</br>";
		p.setHtml(html);
		hp.add(p);
		hp.add(new Button("Detalhes"));
		this.add(hp);
		this.setFrame(true);
		
	}
	
}
