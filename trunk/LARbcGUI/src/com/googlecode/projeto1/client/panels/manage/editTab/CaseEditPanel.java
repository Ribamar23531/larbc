package com.googlecode.projeto1.client.panels.manage.editTab;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.LoginManager;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.panels.manage.ManagePanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class CaseEditPanel extends CaptionPanel{
	
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);
	
	private CaseBean myCaseBean;
	
	public CaseEditPanel(CaseBean caseBean, int index){
		super("Caso " + index);
		this.myCaseBean = caseBean;
		this.setHeight("150px");
		this.setWidth("150px");
		AbsolutePanel absolutePanel = new AbsolutePanel();
		this.setContentWidget(absolutePanel);
		absolutePanel.setSize("450px", "3cm");
		String address = myCaseBean.getStreet() + ", Nº " + myCaseBean.getNumber() + ". " +
							myCaseBean.getCity() + ", " + myCaseBean.getNeighborhood();
		
		Label enderecoLabel = new Label("Endereço: " + address);
		absolutePanel.add(enderecoLabel, 5, 5);

		Label nomeLabel = new Label("Nome: " + myCaseBean.getName());
		absolutePanel.add(nomeLabel, 5, 31);

		Label precoLabel = new Label("Preço: " + myCaseBean.getPrice());
		absolutePanel.add(precoLabel, 5, 57);

		Button editarButton = new Button("Editar");
		editarButton.addClickListener(new ClickListener() {
			
			public void onClick(Widget arg0) {
				new EditWindow(myCaseBean).show();
				
			}
		});		
		absolutePanel.add(editarButton, 338, 74);
		
		Button removeButton = new Button("Remover");
		removeButton.addClickListener(new ClickListener() {
			
			
			public void onClick(Widget arg0) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Remover Caso");
						setMsg("Você deseja realmente remover esse caso?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {
									removeCaso();
								}

							}
						});
					}
				});
			}
		});
		absolutePanel.add(removeButton, 224, 74);
	}
	
	private void removeCaso(){
		PERSISTENCE_SERVICE.removeCaso(LoginManager.getLogedAdministrator(), myCaseBean, new AsyncCallback<String>() {

			public void onFailure(Throwable arg0) {
				MessageBox.alert("Não foi possível remover o caso");
				
			}

			public void onSuccess(String arg0) {
				PanelSwitcher.switchPanel(new ManagePanel());
				MessageBox.alert("Caso removido com sucesso");
				
			}
		});
	}

}
