package com.googlecode.projeto1.client.panels.results;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.widgets.Panel;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class CasePanel extends Panel{
	
	private Image picture;
	private CaseBean caseBean;
	private int index;

	public CaseBean getCaseBean() {
		return caseBean;
	}

	public int getIndex() {
		return index;
	}

	public CasePanel(CaseBean caseBean, int index){
		super("Caso " + index);
		this.index = index;
		this.caseBean = caseBean;
		String bodyStyle = "text-align:left;padding:5px 0;" +  
		"border:1px dotted #99bbe8;background:#dfe8f6;" +  
		"color:#15428b;cursor:default;margin:10px;" +  
		"font:normal 12px tahoma,arial,sans-serif;"; 
		picture = Util.createImage(Util.RESIDENTIAL_IMMOBILE_PATH, 120);
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(picture);
		String address = caseBean.getStreet() + ", " + caseBean.getNeighborhood() + ", " + caseBean.getCity();
		String features = "Area total: " + caseBean.getTotalArea() + ", " + caseBean.getBedroom() + " quarto(s), " +
							caseBean.getGarageSpace() + " vaga(s) na garagem.";
		String price = "R$ " + caseBean.getPrice();
		Panel p = new Panel();
		String html = 	"<br>Endereço: " + address + "</br> " + 
						"<br>Características: " + features + " </br> " +
						"<br>Preço: " + price + " </br>";
		p.setHtml(html);
		p.setBodyStyle(bodyStyle);
		hp.add(p);
		Button detalhes = new Button("Detalhes");
		hp.add(detalhes);
		detalhes.addClickListener(new ClickListener(){
			public void onClick(Widget arg0) {
				PanelSwitcher.switchPanel(new DetalhesPanel(getCaseBean(), getIndex()));				
			}
			
		});	
		this.add(hp);
		this.setFrame(true);
		
	}
	
}
