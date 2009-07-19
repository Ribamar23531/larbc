package com.googlecode.projeto1.client.panels.results;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.core.EventObject;  

public class ResultsPanel extends Panel{
	
	private VerticalPanel vp;
	
	public ResultsPanel(){
		super();
		vp = new VerticalPanel();
		this.setFrame(true);
		vp.add(Util.createImage(Util.LARBC_IMAGE_PATH));
		vp.add(Util.createImage(Util.IMMOBILE_IMAGE_PATH));
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
//		Button button = new ButtonListenerAdapter();

		this.add(vp, new ColumnLayoutData(50));
		Button button = new Button("Nao encontrou o que queria?", new ButtonListenerAdapter() {  
			public void onClick(Button button, EventObject e) {  
				MessageBox.confirm("Confirmacao", "Voce deseja enviar essas informacoes para o administrador?",  
						new MessageBox.ConfirmCallback() {  
					public void execute(String btnID) {  
						System.out.println("Button Click : " +  
								Format.format("You clicked the {0} button", btnID));  
					}  
				});  
			}  
		}); 
		vp.add(button);
		button.setSize("117px", "21px");
		this.setAutoScroll(true);
		
	}

}
