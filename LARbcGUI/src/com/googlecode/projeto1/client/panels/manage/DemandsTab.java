package com.googlecode.projeto1.client.panels.manage;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.form.Label;

public class DemandsTab extends AbsolutePanel{
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	VerticalPanel verticalPanel;
	
	public DemandsTab(){
		super();
		ScrollPanel scrollPanel = new ScrollPanel();
		this.add(scrollPanel, 5, 5);
		scrollPanel.setSize("420px", "253px");
		{
			verticalPanel = new VerticalPanel();
			scrollPanel.setWidget(verticalPanel);
			verticalPanel.setSize("419px", "100%");
			PERSISTENCE_SERVICE.getDemandas(new AsyncCallback<List<DemandBean>>(){
				
				public void onSuccess(List<DemandBean> demands) {
					int index = 1;
					for (DemandBean demandBean : demands) {
						CaptionPanel cptnpnlDemanda = new CaptionPanel("Demanda " + index);
						cptnpnlDemanda.setHeight("109px");
						verticalPanel.add(cptnpnlDemanda);
						{
							AbsolutePanel absolutePanel_1 = new AbsolutePanel();
							cptnpnlDemanda.setContentWidget(absolutePanel_1);
							absolutePanel_1.setSize("416px", "135px");
							{
								Label enderecoLabel = new Label("Endereço: " + demandBean.getRua() + ", " + demandBean.getBairro() + ", " + demandBean.getCidade());
								absolutePanel_1.add(enderecoLabel, 5, 5);
							}
							{
								Label nomeLabel = new Label("Nome: " + demandBean.getNome());
								absolutePanel_1.add(nomeLabel, 5, 31);
							}
							{
								Label precoLabel = new Label("Preço: " + demandBean.getPreco());
								absolutePanel_1.add(precoLabel, 5, 57);
							}
							{
								Label situacaoLabel;
								if(demandBean.isJahModerado()){
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
						index++;
					}
					
				}
				
				public void onFailure(Throwable arg0) {
					MessageBox.alert("Não foi possível carregar os casos");
					
				}

			});	
//			{
//				CaptionPanel cptnpnlDemanda = new CaptionPanel("Demanda 1");
//				cptnpnlDemanda.setHeight("109px");
//				verticalPanel.add(cptnpnlDemanda);
//				{
//					AbsolutePanel absolutePanel_1 = new AbsolutePanel();
//					cptnpnlDemanda.setContentWidget(absolutePanel_1);
//					absolutePanel_1.setSize("416px", "135px");
//					{
//						Label enderecoLabel = new Label("Endere\u00E7o:");
//						absolutePanel_1.add(enderecoLabel, 5, 5);
//					}
//					{
//						Label nomeLabel = new Label("Nome:");
//						absolutePanel_1.add(nomeLabel, 5, 31);
//					}
//					{
//						Label precoLabel = new Label("Pre\u00E7o:");
//						absolutePanel_1.add(precoLabel, 5, 57);
//					}
//					{
//						Label situacaoLabel = new Label("Situa\u00E7\u00E3o:");
//						absolutePanel_1.add(situacaoLabel, 5, 83);
//					}
//					{
//						Button editarButton = new Button("Editar");
//						absolutePanel_1.add(editarButton, 335, 96);
//					}
//				}
//			}
		}
	}

}
