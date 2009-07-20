package com.googlecode.projeto1.client.panels.results;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.widgets.Panel;

public class CasePanel extends Panel{
	
	private Image picture;

	public CasePanel(CaseBean caseBean, int index){
		super("Caso " + index);
		picture = Util.createImage(Util.RESIDENTIAL_IMMOBILE_PATH, 120);
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(picture);
		String address = caseBean.getStreet() + ", " + caseBean.getNeighborhood() + ", " + caseBean.getCity();
		String features = "Area total: " + caseBean.getTotalArea() + ", " + caseBean.getBedroom() + " quarto(s), " +
							caseBean.getGarageSpace() + " vaga(s) na garagem.";
		String price = "R$ " + caseBean.getPrice();
		Panel p = new Panel();
		String html = 	"<br>Endereço: " + address + " </br> " +
						"<br>Características: " + features + " </br> " +
						"<br>Preço: " + price + " </br>";
		p.setHtml(html);
		hp.add(p);
		hp.add(new Button("Detalhes"));
		this.add(hp);
		this.setFrame(true);
		
	}
	
}
