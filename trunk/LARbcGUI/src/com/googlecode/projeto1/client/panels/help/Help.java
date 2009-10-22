package com.googlecode.projeto1.client.panels.help;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
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
import com.gwtext.client.widgets.layout.FitLayout;

public class Help extends Panel {
	private VerticalPanel verticalPanel;
	private String visaoGeralString = "<br><p>LARbc é uma aplicação que sugere imóveis aos usuários, levando em consideração as suas preferências como um apartamento ou casa com certo número de quartos, área útil, dentre outros fatores. </p></br>"
			+ "	<br> <p>No LARbc o cliente pode consultar imóveis à venda ou disponíveis para locação e pode cadastrar um imóvel para vender através da imobiliária.</p> </br>" +
					" <br><p> O dono ou funcionário da imobiliária pode administrar a ferramenta gerenciando demandas, que são cadastros de vendas feitos por clientes e consultas realizadas pelos clientes que não retornaram para eles um resultado satisfatório. </p></br>"
			;
	private String consultaString = "<br>Para realizar uma consulta o usuário deve seguir os seguintes passos:</br> <br>1. Ao iniciar a aplicação clicar no botão ENTRAR </br> <br>2. Na próxima tela aparecerá a pergunta 'O que você deseja?', então o usuário deve clicar no botão COMPRAR/ALUGAR </br>"
			+ "							<br> 3. Aparecerá um formulário para que o usuário preencha, com os seguintes itens, todos eles opcionais: </br> <ul> <br>Nome da rua - nome da rua que o cliente deseja morar</br> <br>Bairro - nome do bairro que o cliente deseja morar </br> <br>Cidade - nome da cidade que o cliente deseja morar</br><br> UF: Estado (Unidade Federativa onde está localizado o imóvel procurado)</br> <br>Nome do imóvel - Nome do edifício no caso de apartamentos </br> <br> Área construída - Tamanho da área construída do imóvel (metros)</br>"
			+ "							<br>Área total - Tamanho da área completa ocupada pelo imóvel (incluindo quintal por exemplo)</br> <br>Vagas na garagem - quantidades de vagas disponíveis na garagem</br> <br>Quartos - quantidade de quartos no imóvel, incluindo suítes</br> <br>Suítes - quantidade de suítes no imóvel</br> <br>Banheiros Sociais - quantidade de banheiros no imóvel, exceto as suítes</br> <br>tipo do Imóvel - Tipo do imóvel procurado: casa, apartamento, terreno, área comercial...</br> <br>Preço - Valor aproximado do que o cliente deseja pagar pelo imóvel</br> <br>Tipo de Negócio - Aluguel ou Compra </ul></br>";
	private String loginAdministradorString = "<br> <p>Para administrar o LARbc, o usuário deve entrar na aplicação. Aparecerá a tela com a pergunta 'O que você deseja?', nessa tela, na parte superior direita está o botão Administrar, onde o usuário deve clicar. </p></br>" +
			"<br> <p>Ao clicar no botão administrar aparecerá a tela de login, pedindo login e senha. os valores defaul são login e senha como root. Após entrar na tela de administração o usuário pode criar uma administrador com login e senha de sua preferência.</p></br>";
	private String criarDemandaString = "<br>O cliente pode criar uma demanda de duas formas:</br>" +
			"<br>1. Cadastrando um imóvel para venda :</br>" +
			"     <br>- Para cadastrar um imóvel para venda o usuário deve fazer o seguinte: ao iniciar a aplicação clicar no botão ENTRAR, na tela seguinte clicar no botão VENDER. Aparecerá um formulário para ser preenchido pelo cliente com as informações de seu imóvel. Ao preencher o formulário o cliente deve clicar em CADASTRAR.</br>" +
			"<br>2. Armazenando uma consulta</br>";
	private String criarAdministradorString = "Como criar um administrador";
	private String gerenciarDemandasString = "Como gerenciar demandas";

	public Help() {
		AbsolutePanel panel = new AbsolutePanel();
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
		image.setSize("50%", "50%");
		verticalPanel = new VerticalPanel();
		verticalPanel.add(ajudaPanel);
		verticalPanel.setVisible(true);
		verticalPanel.setPixelSize(500, 500);

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
		panel.add(verticalPanel, 410, 0);
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
}
