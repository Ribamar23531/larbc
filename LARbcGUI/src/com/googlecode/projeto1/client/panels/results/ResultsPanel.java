package com.googlecode.projeto1.client.panels.results;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.widgets.Panel;

public class ResultsPanel extends Panel{
	
	private VerticalPanel vp;
	
	public ResultsPanel(){
		super();
		vp = new VerticalPanel();
		this.setFrame(true);
		vp.add(Util.createImage(Util.LARBC_IMAGE_PATH));
		vp.add(Util.createImage(Util.IMMOBILE_IMAGE_PATH));
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		vp.add(new CasePanel());
		this.add(vp);
		this.setAutoScroll(true);
		
	}

}
