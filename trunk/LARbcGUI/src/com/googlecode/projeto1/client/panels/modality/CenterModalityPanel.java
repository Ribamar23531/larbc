package com.googlecode.projeto1.client.panels.modality;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class CenterModalityPanel extends Panel{
	
	private VerticalPanel vp;
	private Panel p;
	private HorizontalPanel hp;

	public CenterModalityPanel(){
		super();
		this.vp = new VerticalPanel();
		this.p = new Panel();
		this.hp = new HorizontalPanel();
		p.setLayout(new ColumnLayout());
		Panel panel1 = new Panel();		
		panel1.add(Util.createImage(Util.PREENCHIMENTO_PATH));
		Panel panel2 = new Panel();
		panel2.add(Util.createImage(Util.QUESTION));
		p.add(panel1, new ColumnLayoutData(.10));
		p.add(panel2, new ColumnLayoutData(.80));
		p.add(panel1, new ColumnLayoutData(.10));
		vp.add(p);
		this.add(vp);
	}
	
}
