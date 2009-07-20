package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.gwtext.client.widgets.Panel;

public class ManagePanel extends Panel{
	
	private AbsolutePanel managePanel;	
	
	public ManagePanel(){
		super();		
		managePanel = new AbsolutePanel();		
		{
			TabPanel tabPanel = new TabPanel();
			tabPanel.setAnimationEnabled(true);
			managePanel.add(tabPanel, 5, 5);
			tabPanel.setSize("469px", "313px");
			{
				CreateTab createTab = new CreateTab();
				tabPanel.add(createTab, "Criar", false);
				createTab.setSize("428px", "257px");
				
			}
			{
				EditTab editTab = new EditTab();
				tabPanel.add(editTab, "Editar", false);
				editTab.setSize("433px", "260px");				
			}
			{
				DemandsTab demandsTab = new DemandsTab();
				tabPanel.add(demandsTab, "Moderar Demandas", false);
				demandsTab.setSize("453px", "263px");
			}
			{
				AdminTab adminsMainPanel = new AdminTab();
				tabPanel.add(adminsMainPanel, "Administradores", false);
				adminsMainPanel.setSize("437px", "259px");
				
			}
		}
		this.add(managePanel);
		this.setFrame(true);
	}

}
