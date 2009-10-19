package com.googlecode.projeto1.client.panels.help;

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
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GroupingView;
import com.gwtext.client.widgets.grid.event.GridCellListener;
import com.gwtext.client.widgets.grid.event.GridColumnListener;
import com.gwtext.client.widgets.grid.event.GridHeaderListener;
import com.gwtext.client.widgets.grid.event.GridListener;
import com.gwtext.client.widgets.grid.event.GridRowListener;
import com.gwtext.client.widgets.layout.FitLayout;

public class Help extends Panel{

	public Help() {  
		Panel panel = new Panel();  
		panel.setBorder(false);  
		panel.setPaddings(15);  
	  
		MemoryProxy proxy = new MemoryProxy(getCompanyData());  
		RecordDef recordDef = new RecordDef(  
			new FieldDef[]{  
					new StringFieldDef("larbc"),
					new StringFieldDef("categoria")
			}  
		);  
	
		ArrayReader reader = new ArrayReader(recordDef);  

		GroupingStore store = new GroupingStore();  
		store.setReader(reader);  
		store.setDataProxy(proxy);  
		store.setSortInfo(new SortState("categoria", SortDir.DESC));  
		store.setGroupField("categoria");  
		store.load();  
	  
		ColumnConfig columnConfigLARbc = new ColumnConfig("LARbc", "larbc", 160, true, null, "larbc");
		ColumnConfig columnConfigCategoria = new ColumnConfig("Categoria", "categoria", 1, true);
		ColumnConfig[] columns = new ColumnConfig[]{  
				columnConfigLARbc,  
				columnConfigCategoria  
		};  
	    
		ColumnModel columnModel = new ColumnModel(columns);  

		GridPanel grid = new GridPanel();  
		grid.setStore(store);  
		grid.setColumnModel(columnModel);
		grid.setFrame(true);  
		grid.setStripeRows(true);  
		grid.setAutoExpandColumn("larbc");  
		grid.setTitle("Grid Events");  
		grid.setHeight(350);  
		grid.setWidth(600);  
	    
		GroupingView gridView = new GroupingView();  
		gridView.setForceFit(true); 
		gridView.setGroupTextTpl("{text}");

		grid.setView(gridView);  
		grid.setFrame(true);  
		grid.setWidth(580);  
		grid.setHeight(500);  
		grid.setCollapsible(true);  
		grid.setAnimCollapse(false);  
		grid.setTitle("Ajuda");  
		grid.setIconCls("grid-icon");  
	    
		//TESTANDO
		 grid.addGridCellListener(new GridCellListener() {  
			 public void onCellClick(GridPanel grid, int rowIndex, int colindex,  
					 EventObject e) {  
				 System.out.println("GridCellListener.onCellClick::row(" + rowIndex +  
						 "), col(" + colindex + ");");  
			 }  

			 public void onCellContextMenu(GridPanel grid, int rowIndex,  
					 int colIndex, EventObject e) {  
				 System.out.println("GridCellListener.onCellContextMenu::row(" + rowIndex +  
						 "), col(" + colIndex + ");");  
			 }  

			 public void onCellDblClick(GridPanel grid, int rowIndex,  
					 int colIndex, EventObject e) {  
				 System.out.println("GridCellListener.onCellDblClick::row(" + rowIndex +  
						 "), col(" + colIndex + ");");  
			 }  
		 });  

		 grid.addGridColumnListener(new GridColumnListener() {  
			 public void onColumnMove(GridPanel grid, int oldIndex, int newIndex) {  
				 System.out.println(Format.format("GridCellListener.onColumnMove::oldIndex({0}),newIndex({1})", oldIndex, newIndex));  
			 }  

			 public void onColumnResize(GridPanel grid, int colIndex, int newSize) {  
				 System.out.println(Format.format("GridCellListener.onColumnResize::oldIndex({0}), newSize({1})", colIndex, newSize));  
			 }  
		 });  

		 grid.addGridHeaderListener(new GridHeaderListener() {  
			 public void onHeaderClick(GridPanel grid, int colIndex, EventObject e) {  
				 System.out.println(Format.format("GridHeaderListener.onHeaderClick::colIndex({0}))", colIndex));  
			 }  

			 public void onHeaderContextMenu(GridPanel grid, int colIndex, EventObject e) {  
				 System.out.println(Format.format("GridHeaderListener.onHeaderContextMenu::colIndex({0}))", colIndex));  
			 }  

			 public void onHeaderDblClick(GridPanel grid, int colIndex, EventObject e) {  
				 System.out.println(Format.format("GridHeaderListener.onHeaderDblClick::colIndex({0}))",colIndex));  
			 }  
		 });  

		 grid.addGridListener(new GridListener() {  
			 public void onBodyScroll(int scrollLeft, int scrollTop) {  
				 System.out.println(Format.format("GridListener.onBodyScroll::scrollLeft({0},scrollTop({1})))", scrollLeft, scrollTop));  
			 }  

			 public void onClick(EventObject e) {  
				 System.out.println("GridListener.onClick");  
			 }  

			 public void onContextMenu(EventObject e) {  
				 System.out.println("GridListener.onContextMenu");  
			 }  

			 public void onDblClick(EventObject e) {  
				 System.out.println("GridListener.onDblClick");  
			 }  

			 public void onKeyDown(EventObject e) {  
				 System.out.println("GridListener.onKeyDown");  
			 }  

			 public void onKeyPress(EventObject e) {  
				 System.out.println("GridListener.onKeyPress");  
			 }  
		 });  


		 grid.addGridRowListener(new GridRowListener() {  
			 public void onRowClick(GridPanel grid, int rowIndex, EventObject e) {  
				 System.out.println(Format.format("GridRowListener.onRowClick::rowIndex({0})",  
						 rowIndex));  
			 }  

			 public void onRowDblClick(GridPanel grid, int rowIndex, EventObject e) {  
				 System.out.println(Format.format("GridRowListener.onRowDblClick::rowIndex({0})",  
						 rowIndex));  
			 }  

			 public void onRowContextMenu(GridPanel grid, int rowIndex, EventObject e) {  
				 System.out.println(Format.format("GridRowListener.onRowContextMenu::rowIndex({0}),({1}), y({2})", rowIndex, e.getPageX(), e.getPageY()));  
			 }  
		 });		
		 //TESTANDO
		
		panel.add(grid);  
		this.add(panel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}  
	  
	private Object[][] getCompanyData() { 
		String newLine = "\n";
		Object[] visaoGeral = new Object[]{"LARbc é uma aplicação que sugere imóveis aos usuários, levando em consideração as suas preferências como um apartamento ou casa com certo número de quartos, área útil, dentre outros fatores.", "Visão geral sobre o LARbc"};
		Object[] consulta = new Object[]{"Para realizar uma consulta o usuário deve seguir os seguintes passos: " + newLine + " 1. Ao iniciar a plicação clicar no botão ENTRAR \n 2. Na próxima tela aparecerá a pergunta 'O que você deseja?', então o usuário deve clicar no botão COMPRAR/ALUGAR \n" +
				"							3. Aparecerá um formulário para que o usuário preencha, com os seguintes itens (opcionais): Nome da rua, Bairro, Cidade, UF, Nome do imóvel, Área construída," +
				"							Área total, Vagas na garagem, Quartos, Suítes, Banheiros Sociais, tipo do Imóvel, Preço e Tipo de Negócio ", "Como fazer uma consulta no LARbc"};
		Object[] loginAdministrador = new Object[]{"Como entrar como administrador", "Administrando o LARbc"};
		Object[] criarDemanda = new Object[]{"Como criar uma demanda","Administrando o LARbc"};
		Object[] criarAdministrador = new Object[]{"Como criar um administrador", "Administrando o LARbc"};
		Object[] gerenciarDemandas = new Object[]{"Como gerenciar demandas", "Administrando o LARbc"};
		return new Object[][]{  
				visaoGeral, 
				consulta,  
				loginAdministrador,  
				criarDemanda,  
				criarAdministrador,  
				gerenciarDemandas,  
		};  
	}

}  


