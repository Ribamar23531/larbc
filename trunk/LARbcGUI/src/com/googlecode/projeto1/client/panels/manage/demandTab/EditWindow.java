package com.googlecode.projeto1.client.panels.manage.demandTab;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.LoginManager;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.panels.manage.ManagePanel;
import com.googlecode.projeto1.client.panels.maps.CreateCaseMap;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class EditWindow extends Window{
	
	private static final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);

	private EditWindowPanel demandWindowPanel;
	DemandBean demand;
	
	public EditWindow(DemandBean demand){
		super();
		this.demand = demand;
		this.demandWindowPanel = new EditWindowPanel(demand);
		this.setTitle("Edição de Demanda");		
		this.setClosable(true);
		this.setPlain(true);		
		this.setPaddings(5);  
		this.setButtonAlign(Position.CENTER);
		this.addButton(getOkButton());	
		this.addButton(getMapButton());
		this.setResizable(true);
		this.setCloseAction(Window.HIDE);  
		this.setPlain(true);
		this.add(demandWindowPanel);
		this.setSize("570px", "390px");
		this.setResizable(false);
	}
	
	private Button getOkButton() {
		Button okButton = new Button("Gravar como um novo caso");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				
				String estado = demandWindowPanel.getEstadoTextBox();
				String cidade = demandWindowPanel.getCidadeTextBox();
				String bairro = demandWindowPanel.getBairroTextBox();
				String rua = demandWindowPanel.getRuaTextBox();
				Integer numero = demandWindowPanel.getNumeroTextBox();
				String nomeimovel = demandWindowPanel.getNomeTextBox();
				Float areaContruida = demandWindowPanel.getAreaConstruidaTextBox();
				Float areaTotal = demandWindowPanel.getAreaTotalTextBox();
				Integer garagem = demandWindowPanel.getGaragemTextBox();
				Integer quartos = demandWindowPanel.getQuartosTextBox();
				Integer suites = demandWindowPanel.getSuitesTextBox();
				Integer banheiros = demandWindowPanel.getBanheirosTextBox();
				String tipo = demandWindowPanel.getTipoTextBox();
				Float preco = demandWindowPanel.getPrecoTextBox();
				String tipoNegocio = demandWindowPanel.getTipoNegociolistBox();

				CaseBean caseBean = new CaseBean();
				caseBean.setState(estado);
				caseBean.setCity(cidade);
				caseBean.setNeighborhood(bairro);
				caseBean.setStreet(rua);
				caseBean.setNumber(numero);
				caseBean.setName(nomeimovel);
				caseBean.setBuiltArea(areaContruida);
				caseBean.setTotalArea(areaTotal);
				caseBean.setGarageSpace(garagem);
				caseBean.setBathroom(quartos);
				caseBean.setSuite(suites);
				caseBean.setBedroom(banheiros);
				caseBean.setType(tipo);
				caseBean.setPrice(preco);
				caseBean.setBusinessType(tipoNegocio);
	
				PERSISTENCE_SERVICE.createCaso(LoginManager.getLogedAdministrator(), caseBean, new AsyncCallback<String>() {

					public void onFailure(Throwable arg0) {
						MessageBox.alert("Houve problemas durante o armazenamento desse caso.");
						
					}

					public void onSuccess(String arg0) {
			
						PERSISTENCE_SERVICE.removeDemanda(demand, new AsyncCallback<String>() {

							public void onFailure(Throwable arg0) {
								MessageBox.alert("A demanda não pode ser removida");
							}

							public void onSuccess(String arg0) {
								MessageBox.alert("Caso armazenado com sucesso!");
								PanelSwitcher.switchPanel(new ManagePanel());							
							}
						});
						
						PanelSwitcher.switchPanel(new ManagePanel());	
					}
				});
			}

		});
		return okButton;
	}
	
	private Button getMapButton() {
		final Button button = new Button("Ajustar Coordenadas");

		return button;
	}

}
