package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.form.Label;

public class EditTab extends ScrollPanel{
	
	public EditTab(){
		super();
		VerticalPanel verticalPanel = new VerticalPanel();
		this.setWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		{
			CaptionPanel captionPanel = new CaptionPanel("Caso 1");
			captionPanel.setHeight("138px");
			verticalPanel.add(captionPanel);
			{
				AbsolutePanel absolutePanel = new AbsolutePanel();
				captionPanel.setContentWidget(absolutePanel);
				absolutePanel.setSize("428px", "3cm");
				{
					Label enderecoLabel = new Label("Endere\u00E7o:");
					absolutePanel.add(enderecoLabel, 5, 5);
				}
				{
					Label nomeLabel = new Label("Nome:");
					absolutePanel.add(nomeLabel, 5, 31);
				}
				{
					Label precoLabel = new Label("Pre\u00E7o:");
					absolutePanel.add(precoLabel, 5, 57);
				}
				{
					Button editarButton = new Button("New button");
					editarButton.setText("Editar");
					absolutePanel.add(editarButton, 347, 74);
				}
			}
		}
	}

}
