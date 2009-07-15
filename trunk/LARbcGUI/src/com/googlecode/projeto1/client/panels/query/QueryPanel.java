package com.googlecode.projeto1.client.panels.query;

import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class QueryPanel extends Panel {
	
	private TitlePanel titlePanel;
	private BodyPanel bodyPanel;
	private FooterPanel footerPanel;
	
	public QueryPanel(){
		super();
		setLayoutOfThis();
		this.setTitlePanel();
		this.setBodyPanel();
		this.searchFooterPanel();
		this.doLayout();
	}

	private void setLayoutOfThis() {
		BorderLayout borderLayout = new BorderLayout();
//		this.setLayout(boderLayout);
	}

	private void setTitlePanel() {
		this.titlePanel = new TitlePanel();		
		BorderLayoutData northData = new BorderLayoutData(RegionPosition.NORTH);
		this.add(this.titlePanel, northData);
	}

	private void setBodyPanel() {
		this.bodyPanel = new BodyPanel();
		BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);
		this.add(this.bodyPanel, centerData);
	}

	private void searchFooterPanel() {
		this.footerPanel = new FooterPanel();
		BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);
		this.add(this.footerPanel, southData);
	}
}
