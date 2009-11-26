package com.googlecode.projeto1.client.panels.help;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.projeto1.client.PanelSwitcher;
import com.googlecode.projeto1.client.panels.Util;
import com.googlecode.projeto1.client.panels.modality.ModalityPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.SortDir;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.GroupingStore;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.SortState;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GroupingView;
import com.gwtext.client.widgets.grid.event.GridCellListener;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.FitLayout;

public class Help extends Panel {
	private AbsolutePanel rootPanel;
	private String visaoGeralString = "<html><font color=#000080><p><b><font size=3>Visão Geral</font></b></p><p><br></br></p><p><b>Meu Lar</b> é uma aplicação que sugere imóveis aos usuários, levando em consideração as suas preferências como um apartamento ou casa com certo número de quartos, área útil, dentre outros fatores. </p>"
			+ "	<p><br></br></p><p>No <b>Meu Lar</b> o cliente pode consultar imóveis à venda ou disponíveis para locação e pode cadastrar um imóvel para vender através da imobiliária.</p>" +
					" <p><br></br></p><p> O dono ou funcionário da imobiliária pode administrar a ferramenta gerenciando demandas, que são cadastros de vendas feitos por clientes e consultas realizadas pelos clientes que não retornaram para eles um resultado satisfatório. </p></font></html>"
			;
	private String consultaString = "<html><font color=#000080><p></p><p><b><font size=3>Realizando uma consulta</font></b></p><p><br></br></p><p>Para realizar uma consulta o usuário deve seguir os seguintes passos:</p> " +
			"<p><br></br></p><p>1. Ao iniciar a aplicação clicar no botão <b>ENTRAR</b> </p>" +
			" <p><br></br></p><p>2. Na próxima tela aparecerá a pergunta <b>'O que você deseja?'</b>, então o usuário deve clicar no botão <b>COMPRAR/ALUGAR</b> </p>"
			+ "<p><br></br></p><p> 3. Aparecerá um formulário para que o usuário preencha, com os seguintes itens, todos eles opcionais: </p> " +
			"<p><b>Nome da rua</b> - nome da rua que o cliente deseja morar</p> " +
			"<p><b>Bairro</b> - nome do bairro que o cliente deseja morar </p> " +
			"<p><b>Cidade</b> - nome da cidade que o cliente deseja morar</p> " +
			"<p><b>UF: Estado</b> (Unidade Federativa onde está localizado o imóvel procurado)</p> " +
			"<p><b>Nome do imóvel</b> - Nome do edifício no caso de apartamentos </p> " +
			"<p><b>Área construída</b> - Tamanho da área construída do imóvel (metros)</p>" +
			"<p><b>Área total</b> - Tamanho da área completa ocupada pelo imóvel (incluindo quintal por exemplo)</p> " +
			"<p><b>Vagas na garagem</b> - quantidades de vagas disponíveis na garagem</p> " +
			"<p><b>Quartos</b> - quantidade de quartos no imóvel, incluindo suítes</p> " +
			"<p><b>Suítes</b> - quantidade de suítes no imóvel</p> " +
			"<p><b>Banheiros Sociais</b> - quantidade de banheiros no imóvel, exceto as suítes</p> " +
			"<p><b>Tipo do Imóvel</b> - Tipo do imóvel procurado: casa, apartamento, terreno, área comercial...</p> " +
			"<p><b>Preço</b> - Valor aproximado do que o cliente deseja pagar pelo imóvel</p> " +
			"<p><b>Tipo de Negócio</b> - Aluguel ou Compra </p></font></html>";
	private String loginAdministradorString = "<html><font color=#000080><p></p><p><b><font size=3>Administrando</font></b></p><p><br></br></p><p>Para administrar o <b>Meu Lar</b>, o usuário deve entrar na aplicação. Aparecerá a tela com a pergunta <b>'O que você deseja?'</b>, nessa tela, na parte superior direita está o botão <b>Administrar</b>, onde o usuário deve clicar. </p>" +
			"<p><br></br></p><p>Ao clicar no botão administrar aparecerá a tela de login, pedindo login e senha. Os valores default são login e senha como root. Após entrar na tela de administração o usuário pode criar uma administrador com login e senha de sua preferência.</p></font></html>";
	private String criarDemandaString = "<html><font color=#000080><p></p><p><b><font size=4>Criando uma demanda</font></b></p><p><br></br></p><p>O cliente pode criar uma demanda de duas formas:</p>" +
			"<p><br></br></p><p><b>1. Cadastrando um imóvel para venda :</b></p>" +
			" <p><br></br></p><p>Para cadastrar um imóvel para venda o usuário deve fazer o seguinte: ao iniciar a aplicação clicar no botão <b>ENTRAR</b>, na tela seguinte clicar no botão </b>VENDER</b>. Aparecerá um formulário para ser preenchido pelo cliente com as informações de seu imóvel. Ao preencher o formulário o cliente deve clicar em <b>CADASTRAR</b>.</p>" +
			"<p><br></br></p><p><b>2. Armazenando uma consulta</b></p>" +
			"<p><br></br></p><p>Ao realizar uma consulta no <b>Meu Lar</b> e obter a lista de resultados o cliente pode não ter a respota desejada, nesse caso ele pode clicar no botão <b>'Não encontrou o que queria?'</b>, logo depois aparecerá a pergunta <b>'Deseja enviar os dados para o Administrador?'</b>, é só clicar no botão <b>Sim</b> e a demanda estará criada para que o administrador tente encontrar um imóvel com as características desejadas pelo usuário.</p></font></html>";
	private String criarAdministradorString = "<html><font color=#000080><p></p><p><b><font size=3>Criando um Administrador</font></b></p><p><br></br></p><p>Para criar um administrador o usuário precisa está logado na aplicação, estando no modo de administração o usuário deve utilizar a <b>Aba Administradores</b>.</p>" +
			"<p><br></br></p><p>Em Administradores o usuário deve preencher o formulario para para criar um Administrador. As informações requeridas são: <b>Nome</b>, <b>Login</b> e <b>Senha</b>. Preenchidas essas informações o usuário deve decidir se o Administrador criado tem privilégios de <b>Super Usuário (pode criar outros administradores)</b> ou se é um administrador normal. Tudo isso feito o usuário pode clicar no botão <b>Criar</b>.</p></font></html>";
	private String gerenciarDemandasString = "<html><font color=#000080><p></p><p><b><font size=3>Gerenciando uma demanda</font></b></p><p><br></br></p><p>Para gerenciar demandas, o usuário precisa está no modo de administração.</p>" +
			"<p><br></br></p><p>Estando no Modo Administrador o cliente deve escolher a <b>Aba Moderar Demandas</b>, onde vão estar todas as demandas cadastradas na aplicação. Cada demanda possui um campo <b>Situação</b> que informa se ela já foi ou não moderada.</p>" +
			"<p><br></br></p><p>Para moderar uma demanda específica, o cliente deve escolher a demanda e clicar no boão <b>Editar</b></p></font></html>";

	private Image voltarButtonImage;
	private Image selectedVoltarButtonImage;
	private boolean isSelectedVoltarButton;
	private Panel buttonsVoltarPanel;
	
	public Help() {
		
		buttonsVoltarPanel = new Panel();
		buttonsVoltarPanel.setLayout(new ColumnLayout());
		this.isSelectedVoltarButton = false;
		createVoltarButton();
		createSelectedVoltarButton();
		buttonsVoltarPanel.add(voltarButtonImage);
		
		AbsolutePanel panel = new AbsolutePanel();
		panel.add(buttonsVoltarPanel, 950, 15);

		final Panel ajudaPanel = new Panel();
		ajudaPanel.setTitle("Detalhes");
		ajudaPanel.setIconCls("paste-icon");
		ajudaPanel.setWidth(500);
		ajudaPanel.setHeight(500);
		ajudaPanel.setShadow(true);
		ajudaPanel.setAutoScroll(true);
		ajudaPanel.setFrame(true);
		Image image = new Image("images/welcome.png");
		ajudaPanel.add(image);
		ajudaPanel.setPaddings(40);
		image.setSize("80%", "80%");
		rootPanel = new AbsolutePanel();
		rootPanel.add(ajudaPanel);
		rootPanel.setVisible(true);
		rootPanel.setPixelSize(500, 500);

		MemoryProxy proxy = new MemoryProxy(getCompanyData());
		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("larbc"), new StringFieldDef("categoria") });

		ArrayReader reader = new ArrayReader(recordDef);

		GroupingStore store = new GroupingStore();
		store.setReader(reader);
		store.setDataProxy(proxy);
		store.setSortInfo(new SortState("categoria", SortDir.DESC));
		store.setGroupField("categoria");
		store.load();

		ColumnConfig columnConfigLARbc = new ColumnConfig("LARbc", "larbc",
				160, true, null, "larbc");
		ColumnConfig columnConfigCategoria = new ColumnConfig("Categoria",
				"categoria", 1, true);
		ColumnConfig[] columns = new ColumnConfig[] { columnConfigLARbc,
				columnConfigCategoria };

		ColumnModel columnModel = new ColumnModel(columns);

		GridPanel grid = new GridPanel();
		grid.setStore(store);
		grid.setColumnModel(columnModel);
		grid.setFrame(true);
		grid.setStripeRows(true);
		grid.setAutoExpandColumn("larbc");
		grid.setTitle("Grid Events");

		GroupingView gridView = new GroupingView();
		gridView.setForceFit(true);
		gridView.setGroupTextTpl("{text}");

		grid.setView(gridView);
		grid.setFrame(true);
		grid.setWidth(400);
		grid.setHeight(500);

		grid.setCollapsible(true);
		grid.setAnimCollapse(false);
		grid.setTitle("Ajuda");
		grid.setIconCls("grid-icon");

		grid.addGridCellListener(new GridCellListener() {
			public void onCellClick(GridPanel grid, int rowIndex, int colindex,
					EventObject e) {
				if (rowIndex == 0) {
					ajudaPanel.setHtml(visaoGeralString);
				} else if (rowIndex == 1) {
					ajudaPanel.setHtml(consultaString);
				} else if (rowIndex == 2) {
					ajudaPanel.setHtml(loginAdministradorString);
				} else if (rowIndex == 3) {
					ajudaPanel.setHtml(criarDemandaString);
				} else if (rowIndex == 4) {
					ajudaPanel.setHtml(criarAdministradorString);
				} else {
					ajudaPanel.setHtml(gerenciarDemandasString);
				}
				System.out.println("GridCellListener.onCellClick::row("
						+ rowIndex + "), col(" + colindex + ");");
			}

			public void onCellContextMenu(GridPanel grid, int rowIndex,
					int colIndex, EventObject e) {
				System.out.println("GridCellListener.onCellContextMenu::row("
						+ rowIndex + "), col(" + colIndex + ");");
			}

			public void onCellDblClick(GridPanel grid, int rowIndex,
					int colIndex, EventObject e) {
				System.out.println("GridCellListener.onCellDblClick::row("
						+ rowIndex + "), col(" + colIndex + ");");
			}
		});

		panel.add(grid);
		panel.add(rootPanel, 410, 0);
		this.add(panel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}

	private Object[][] getCompanyData() {
		Object[] visaoGeral = new Object[] { "Visão Geral",
				"Visão geral sobre o LARbc" };
		Object[] consulta = new Object[] { "Realizando uma consulta",
				"Como fazer uma consulta no LARbc" };
		Object[] loginAdministrador = new Object[] {
				"Efetuando login como administrador", "Administrando o LARbc" };
		Object[] criarDemanda = new Object[] { "Criando demandas",
				"Administrando o LARbc" };
		Object[] criarAdministrador = new Object[] {
				"Criando um administrador", "Administrando o LARbc" };
		Object[] gerenciarDemandas = new Object[] { "Gerenciando demandas",
				"Administrando o LARbc" };
		return new Object[][] { visaoGeral, consulta, loginAdministrador,
				criarDemanda, criarAdministrador, gerenciarDemandas, };
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
				PanelSwitcher.switchPanel(new ModalityPanel());				
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
