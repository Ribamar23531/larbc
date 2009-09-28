package com.googlecode.projeto1.client.panels.manage;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.googlecode.projeto1.client.panels.manage.adminTab.AdminTab;
import com.googlecode.projeto1.client.panels.manage.createTab.CreateTab;
import com.googlecode.projeto1.client.panels.manage.demandTab.DemandsTab;
import com.googlecode.projeto1.client.panels.manage.editTab.EditTab;
import com.gwtext.client.widgets.Panel;

/**
 * @author Alcione Pinheiro
 * @author Diego Rodrigues
 * @author João Felipe
 * @version LARbc 1.0
 */
public class ManagePanel extends Panel{
	
	private AbsolutePanel managePanel;	
	
	public ManagePanel(){
		super();		
		managePanel = new AbsolutePanel();		
		{
			TabPanel tabPanel = new TabPanel();
			tabPanel.setAnimationEnabled(true);
			managePanel.add(tabPanel, 5, 5);
			tabPanel.setSize("600px", "400px");
			{
				CreateTab createTab = new CreateTab();
				tabPanel.add(createTab, "Criar", false);
				createTab.setSize("620px", "260px");
				
			}
			{
				EditTab editTab = new EditTab();
				tabPanel.add(editTab, "Editar", false);
				editTab.setSize("620px", "500px");				
			}
			{
				DemandsTab demandsTab = new DemandsTab();
				tabPanel.add(demandsTab, "Moderar Demandas", false);
				demandsTab.setSize("620px", "500px");
			}
			{
				AdminTab adminsMainPanel = new AdminTab();
				tabPanel.add(adminsMainPanel, "Administradores", false);
				adminsMainPanel.setSize("620px", "400px");
				
			}
		}
		this.add(managePanel);
		this.setFrame(true);
	}

}
