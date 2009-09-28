package com.googlecode.projeto1.client.panels.manage.demandTab;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.google.gwt.user.client.ui.Button;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class DemandPanel extends CaptionPanel{
	
	private DemandBean myDemandBean;
	
	public DemandPanel(DemandBean demandBean, int index){
		super("Demanda " + index);
		this.myDemandBean = demandBean;
		this.setHeight("150px");
		this.setWidth("150px");
		{
			AbsolutePanel absolutePanel_1 = new AbsolutePanel();
			this.setContentWidget(absolutePanel_1);
			absolutePanel_1.setSize("450px", "135px");
			{
				Label enderecoLabel = new Label("Endereço: " + myDemandBean.getRua() + ", " + myDemandBean.getBairro() + ", " + myDemandBean.getCidade());
				absolutePanel_1.add(enderecoLabel, 5, 5);
			}
			{
				Label nomeLabel = new Label("Nome: " + myDemandBean.getNome());
				absolutePanel_1.add(nomeLabel, 5, 31);
			}
			{
				Label precoLabel = new Label("Preço: " + myDemandBean.getPreco());
				absolutePanel_1.add(precoLabel, 5, 57);
			}
			{
				Label situacaoLabel;
				if(myDemandBean.isJahModerado()){
					situacaoLabel = new Label("Situação: Moderado" );
				}
				else{
					situacaoLabel = new Label("Situação: Não Moderado" );
				}
				absolutePanel_1.add(situacaoLabel, 5, 83);
			}
			{
				Button editarButton = new Button("Editar");
				absolutePanel_1.add(editarButton, 335, 96);
			}
		}
	}

}
