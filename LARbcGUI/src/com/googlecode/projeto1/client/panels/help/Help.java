package com.googlecode.projeto1.client.panels.help;

import com.google.gwt.user.client.ui.VerticalPanel;
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
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

public class Help extends Panel{

	private VerticalPanel verticalPanel;	

	public Help() {  
		verticalPanel = new VerticalPanel();
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
	    
		panel.add(verticalPanel, new ColumnLayoutData(60));
		panel.add(grid);  
		this.add(panel);
		this.setLayout(new FitLayout());
		this.setFrame(true);
	}  
	  
	private Object[][] getCompanyData() { 
		Object[] visaoGeral = new Object[]{"O LARbc ...", "Visão geral sobre o LARbc"};
		Object[] consulta = new Object[]{"Para realizar uma consulta ...", "Como fazer uma consulta no LARbc"};
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


