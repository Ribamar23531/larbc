package com.googlecode.projeto1.client.panels.maps.point;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtext.client.widgets.Panel;

public class PointMapSubPanel extends Panel{
	
	private AbsolutePanel absolutePanel;
	private TextBox nameTextBox;
	private TextArea obsTextArea;
	private Button saveButton;
	private Button removeButton;
	
	public PointMapSubPanel(Button saveButton, Button removeButton){
		absolutePanel = new AbsolutePanel();
		absolutePanel.setSize("613px", "167px");
		
		loadComponents();
		
		this.saveButton = saveButton;
		this.removeButton = removeButton;	

		absolutePanel.add(saveButton, 415, 127);
		absolutePanel.add(removeButton, 499, 127);		
	}
	
	public PointMapSubPanel(Button removeButton){
		absolutePanel = new AbsolutePanel();
		absolutePanel.setSize("613px", "167px");
		
		loadComponents();
		
		this.saveButton = new Button();//apenas para evitar null pointer exception
		this.removeButton = removeButton;
		
		absolutePanel.add(removeButton, 499, 127);

		
	}
	
	private void loadComponents(){
		Label nameLabel = new Label("Nome:");
		absolutePanel.add(nameLabel, 5, 5);

		nameTextBox = new TextBox();
		nameTextBox.setSize("552px", "22px");
		absolutePanel.add(nameTextBox, 56, 5);

		Label obsLabel = new Label("Obs.:");
		absolutePanel.add(obsLabel, 5, 32);

		obsTextArea = new TextArea();
		absolutePanel.add(obsTextArea, 56, 28);
		obsTextArea.setSize("553px", "94px");
		
		this.add(absolutePanel);
		this.setFrame(true);
	}
	
	public String getName(){
		return this.nameTextBox.getText();
	}
	
	public String getObs(){
		return this.obsTextArea.getText();
	}
	
	public void clearName(){
		this.nameTextBox.setText("");
	}
	
	public void setName(String name){
		this.nameTextBox.setText(name);
	}
	
	public void setObs(String obs){
		this.obsTextArea.setText(obs);
	}
	
	public void clearObs(){
		this.obsTextArea.setText("");
	}
	
	public void setSaveButtonEnabled(boolean enabled){
		this.saveButton.setEnabled(enabled);
	}
	
	public void setRemoveButtonEnabled(boolean enabled){
		this.removeButton.setEnabled(enabled);
	} 

}
