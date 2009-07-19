package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.form.Label;

public class DemandsTab extends AbsolutePanel{
	
	public DemandsTab(){
		super();
		ScrollPanel scrollPanel = new ScrollPanel();
		this.add(scrollPanel, 5, 5);
		scrollPanel.setSize("420px", "253px");
		{
			VerticalPanel verticalPanel = new VerticalPanel();
			scrollPanel.setWidget(verticalPanel);
			verticalPanel.setSize("419px", "100%");
			{
				CaptionPanel cptnpnlDemanda = new CaptionPanel("Demanda 1");
				cptnpnlDemanda.setHeight("109px");
				verticalPanel.add(cptnpnlDemanda);
				{
					AbsolutePanel absolutePanel_1 = new AbsolutePanel();
					cptnpnlDemanda.setContentWidget(absolutePanel_1);
					absolutePanel_1.setSize("416px", "135px");
					{
						Label enderecoLabel = new Label("Endere\u00E7o:");
						absolutePanel_1.add(enderecoLabel, 5, 5);
					}
					{
						Label nomeLabel = new Label("Nome:");
						absolutePanel_1.add(nomeLabel, 5, 31);
					}
					{
						Label precoLabel = new Label("Pre\u00E7o:");
						absolutePanel_1.add(precoLabel, 5, 57);
					}
					{
						Label situacaoLabel = new Label("Situa\u00E7\u00E3o:");
						absolutePanel_1.add(situacaoLabel, 5, 83);
					}
					{
						Button editarButton = new Button("Editar");
						absolutePanel_1.add(editarButton, 335, 96);
					}
				}
			}
		}
	}

}
