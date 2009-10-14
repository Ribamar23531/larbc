package com.googlecode.projeto1.client.panels.results;

import java.util.List;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class ResultsPanel extends Panel{
	
	private VerticalPanel vp;	
	
	public ResultsPanel(List<CaseBean> cases){
		super();		
		vp = new VerticalPanel();
		this.setFrame(true);
		vp.add(Util.createImage(Util.IMMOBILE_IMAGE_PATH));
		int index = 1;
		for (CaseBean caseBean : cases) {
			vp.add(new CasePanel(caseBean, index));
			index++;
		}	
		
		this.add(vp, new ColumnLayoutData(60));
		Button button = new Button("Não encontrou o que queria?", new ButtonListenerAdapter() {  
			public void onClick(Button button, EventObject e) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Confirmação");
						setMsg("Você deseja enviar essas informações para o administrador?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {
									new ContactWindow().show();
								}

							}
						});
					}
				});

			}  
		}); 
		vp.add(button);
		button.setSize("117px", "21px");
		this.setAutoScroll(true);
		
	}

}
