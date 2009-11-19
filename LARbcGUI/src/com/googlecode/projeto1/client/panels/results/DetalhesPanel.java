package com.googlecode.projeto1.client.panels.results;

import java.util.ArrayList;
import java.util.List;

import beans.Foto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.beans.CaseBean;
import com.googlecode.projeto1.client.beans.PhotoBean;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.query.QueryPanel;
import com.googlecode.projeto1.client.rpcServices.PersistenceService;
import com.googlecode.projeto1.client.rpcServices.PersistenceServiceAsync;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class DetalhesPanel extends Panel{
	
	private Image voltarButtonImage;
	private Image selectedVoltarButtonImage;
	private boolean isSelectedVoltarButton;
	private Panel buttonsVoltarPanel;
	private List<PhotoBean> fotos;
	private final PersistenceServiceAsync PERSISTENCE_SERVICE = (PersistenceServiceAsync) GWT.create(PersistenceService.class);

	public DetalhesPanel(CaseBean caseBean, int index) {
		super();
		this.setTitle("Caso " + index);
		buttonsVoltarPanel = new Panel();
		buttonsVoltarPanel.setLayout(new ColumnLayout());
		this.isSelectedVoltarButton = false;
		createVoltarButton();
		createSelectedVoltarButton();
		buttonsVoltarPanel.add(voltarButtonImage);
		
		String bodyStyle = "text-align:left;padding:5px 0;" +  
		"border:1px dotted #99bbe8;background:#dfe8f6;" +  
		"color:#15428b;cursor:default;margin:10px;" +  
		"font:normal 12px tahoma,arial,sans-serif;"; 
		AbsolutePanel ap = new AbsolutePanel();
		PERSISTENCE_SERVICE.getFotos(caseBean, new AsyncCallback<List<PhotoBean>>() {


			public void onFailure(Throwable arg0) {
				MessageBox.alert("As fotos não puderam ser carregadas do disco");
			}

			public void onSuccess(List<PhotoBean> lista) {
				fotos = lista;				
			}
		});
		for(int i = 0; i< fotos.size(); i++){
			Image foto = new Image(fotos.get(i).getPath());
			ap.add(foto);
		}
		String rua = caseBean.getStreet();
		String bairro = caseBean.getNeighborhood();
		String cidade = caseBean.getCity();
		String estado = caseBean.getState();
		String areaConstruida = caseBean.getBuiltArea() + " m²";
		String areaTotal = caseBean.getTotalArea() + " m²";
		String vagas = "" + caseBean.getGarageSpace();
		String quartos = "" + caseBean.getBathroom();
		String suites = "" + caseBean.getSuite(); 
		String banheiros = "" + caseBean.getBedroom();
		String tipoImovel = caseBean.getType();
		String preco = "R$ " + caseBean.getPrice();
		Panel p = new Panel();
		String html = 	"<br>Rua: " + rua+ "</br> " + 
						"<br>Bairro: " + bairro+ " </br> " +
						"<br>Cidade: " + cidade+ " </br> " +
						"<br>Estado: " + estado+ " </br> " +
						"<br>Área Construída: " + areaConstruida+ " </br> " +
						"<br>Área Total: " + areaTotal+ " </br> " +
						"<br>Vagas na Garagem: " + vagas+ " </br> " +
						"<br>Quartos: " + quartos+ " </br> " +
						"<br>Suítes: " + suites+ " </br> " +
						"<br>Banheiros sociais: " + banheiros+ " </br> " +
						"<br>Tipo do Imóvel: " + tipoImovel+ " </br> " +
						"<br>Preço: " + preco + " </br>";
		p.setHtml(html);
		p.setSize(200, 400);
		p.setBodyStyle(bodyStyle);
		ap.add(p);
		this.add(ap);
		ap.add(buttonsVoltarPanel, 827, 40);

		this.setFrame(true);
		
	}
	
	//BOTAO VOLTAR
	private void createSelectedVoltarButton() {
		selectedVoltarButtonImage = Util.createImage(Util.VOLTAR_SELECTED_BUTTON_IMAGE);
		selectedVoltarButtonImage.setPixelSize(33, 10);
		selectedVoltarButtonImage.addMouseListener(new MouseListenerAdapter(){

			public void onMouseLeave(Widget arg0) {
				rebuildVoltarPanel(voltarButtonImage);
				
			}			
			
		});
		
		selectedVoltarButtonImage.addClickListener(new ClickListener(){

			public void onClick(Widget arg0) {
				PanelSwitcher.switchPanel(new QueryPanel());				
			}
			
		});		
		
	}
	
	private void createVoltarButton() {
		voltarButtonImage = Util.createImage(Util.VOLTAR_BUTTON_IMAGE);
		voltarButtonImage.setPixelSize(33, 10);
		voltarButtonImage.addMouseListener(new MouseListenerAdapter(){
			
			public void onMouseEnter(Widget arg0) {
				rebuildVoltarPanel(selectedVoltarButtonImage);
			}
		});
	}
	
	private void rebuildVoltarPanel(Image buttonImage){
		buttonsVoltarPanel.removeAll();
		if(!isSelectedVoltarButton){
			buttonsVoltarPanel.add(buttonImage);
		}else{
			buttonsVoltarPanel.add(buttonImage);
		}
		buttonsVoltarPanel.doLayout();	
	}
	
}
