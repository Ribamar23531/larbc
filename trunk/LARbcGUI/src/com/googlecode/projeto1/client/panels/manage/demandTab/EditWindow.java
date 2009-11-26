package com.googlecode.projeto1.client.panels.manage.demandTab;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.projeto1.client.LoginManager;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.DemandBean;
import com.googlecode.projeto1.client.panels.manage.ManagePanel;
import com.googlecode.projeto1.client.panels.manage.SelectedLocation;
import com.googlecode.projeto1.client.panels.maps.AjustCoordinatesMap;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.NameValuePair;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
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
	private DemandBean demand;
	
	public EditWindow(DemandBean demand){
		super();
		SelectedLocation.setLocation("");
		this.demand = demand;
		this.demandWindowPanel = new EditWindowPanel(demand);
		this.setTitle("Edição de Demanda");		
		this.setClosable(true);
		this.setPlain(true);		
		this.setPaddings(5);  
		this.setButtonAlign(Position.CENTER);
		this.addButton(getMapButton());
		this.addButton(getSaveCaseButton());
		this.addButton(getRemoveDemandButton());
		this.setResizable(true);
		this.setCloseAction(Window.HIDE);  
		this.setPlain(true);
		this.add(demandWindowPanel);
		this.setSize("570px", "390px");
		this.setResizable(false);
	}

	private Button getSaveCaseButton() {
		Button okButton = new Button("Gravar Como novo Imóvel");
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Remover Demanda");
						setMsg("Você deseja realmente gravar como novo imóvel?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {
									save();									
								}

							}

							private void save() {
								Geocoder streetQuery = new Geocoder();
								String query = "Campina Grande, " + demandWindowPanel.getRuaTextBox();
								streetQuery.getLatLng(query, new LatLngCallback() {

									public void onFailure() {
										MessageBox.show(new MessageBoxConfig() {

											{
												setTitle("Rua não Encontrada");
												setMsg("Essa rua não consta entre as existentes em Campina Grande. "
														+ "Deseja salvar esse imóvel assim mesmo?");
												setIconCls(MessageBox.QUESTION);
												setButtons(MessageBox.YESNO);
												setButtons(new NameValuePair[] {
														new NameValuePair("yes", "Sim"),
														new NameValuePair("no", "Não") });
												setCallback(new MessageBox.PromptCallback() {
													public void execute(String btnID, String text) {
														if (btnID.equals("yes")) {
															saveCase();
														}

													}
												});
											}
										});
										
									}

									public void onSuccess(LatLng point) {
										saveCase();
										
									}
									
								});
								
							}

							private void saveCase() {
								if(SelectedLocation.getLocation().equals("")){
									MessageBox.alert("Favor ajustar as coordenadas");
									return;
								}				
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
								caseBean.setLocation(SelectedLocation.getLocation());
					
								PERSISTENCE_SERVICE.createCaso(LoginManager.getLogedAdministrator(), caseBean, new AsyncCallback<String>() {

									public void onFailure(Throwable arg0) {
										MessageBox.alert("Houve problemas durante o armazenamento desse caso.");
										
									}

									public void onSuccess(String arg0) {
							
										PERSISTENCE_SERVICE.removeDemanda(demand, new AsyncCallback<Boolean>() {

											public void onFailure(Throwable arg0) {
												MessageBox.alert("A demanda não pode ser removida");
											}

											public void onSuccess(Boolean removed) {
												if(removed.booleanValue()){
													MessageBox.alert("Caso armazenado com sucesso!");									
												}
//												PanelSwitcher.switchPanel(new ManagePanel());
												close();
											}
										});
										
										PanelSwitcher.switchPanel(new ManagePanel());	
									}
								});
								
							}

							
						});
					}
				});
				
				
			}

		});
		return okButton;
	}
	
	private Button getRemoveDemandButton() {
		Button button = new Button("Descartar Essa Demanda");
		button.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				MessageBox.show(new MessageBoxConfig() {

					{
						setTitle("Remover Demanda");
						setMsg("Você deseja realmente descartar essa demanda?");
						setIconCls(MessageBox.QUESTION);
						setButtons(MessageBox.YESNO);
						setButtons(new NameValuePair[] {
								new NameValuePair("yes", "Sim"),
								new NameValuePair("no", "Não") });
						setCallback(new MessageBox.PromptCallback() {
							public void execute(String btnID, String text) {
								if (btnID.equals("yes")) {
									removeDemand();
								}

							}

							private void removeDemand() {
								PERSISTENCE_SERVICE.removeDemanda(demand, new AsyncCallback<Boolean>() {
									
									public void onSuccess(Boolean result) {
										if(result.booleanValue()){
											close();
											PanelSwitcher.switchPanel(new ManagePanel());	
											MessageBox.alert("Demanda descartada com sucesso");
											
										}else{
											MessageBox.alert("Não foi possível remover essa demanda");
										}
										
									}
									
									public void onFailure(Throwable arg0) {
										MessageBox.alert("Ocorreu o erro: " + arg0);
										
									}
								});								
							}
						});
					}
				});
				
			}
			
		});
		return button;
	}
	
	private Button getMapButton() {
		Button button = new Button("Ajustar Coordenadas");
		button.addListener(new ButtonListenerAdapter(){
			
			public void onClick(Button button, EventObject e) {
				new AjustCoordinatesMap().show(button.getElement());
			}
			
		});
		return button;
	}

}
