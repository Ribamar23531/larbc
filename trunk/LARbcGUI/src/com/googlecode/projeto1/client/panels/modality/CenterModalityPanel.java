package com.googlecode.projeto1.client.panels.modality;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.projeto1.client.panels.Util;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class CenterModalityPanel extends Panel{
	
	private VerticalPanel vp;
	private Panel p1;
	private Panel p2;
//	private Image venderButton;
//	private Image selectedVenderButton;
//	private boolean isSelectedVenderButton;
	private Panel buttonsPanel;

	public CenterModalityPanel(){
		super();
		this.vp = new VerticalPanel();
		this.p1 = new Panel();
		this.p2 = new Panel();
//		this.isSelectedVenderButton = false;
//		createVenderButton();
		p1.setLayout(new ColumnLayout());
		Panel panel1 = new Panel();		
		panel1.add(Util.createImage(Util.PREENCHIMENTO_PATH));
		Panel panel2 = new Panel();
		panel2.add(Util.createImage(Util.QUESTION));
		Panel panel3 = new Panel();
		panel3.add(Util.createImage(Util.PREENCHIMENTO_PATH));
		p1.add(panel1, new ColumnLayoutData(.40));
		p1.add(panel2, new ColumnLayoutData(.40));
		p1.add(panel3, new ColumnLayoutData(.20));
		vp.add(p1);
		p2.setLayout(new ColumnLayout());
		buttonsPanel = new Panel();
		vp.add(buttonsPanel);
		Panel panel4 = getButtonsPanel();
		this.add(vp);
		this.setAutoScroll(true);
	}

	private Panel getButtonsPanel() {
//		Panel
		return null;
	}

//	private void createVenderButton() {
//		venderButton = Util.createImage(Util.VENDER_BUTTON_IMAGE);
//		venderButton.addMouseListener(new MouseListenerAdapter(){
//
//			
//
//			public void onMouseEnter(Widget arg0) {
//				isSelectedVenderButton = true;
//				rebuildPanel();
//				
//			}
//
//			
//			
//		});
//		selectedVenderButton = Util.createImage(Util.SELECTED_VENDER_BUTTON_IMAGE);
//		selectedVenderButton.addMouseListener(new MouseListenerAdapter(){			
//
//			public void onMouseLeave(Widget arg0) {
//				isSelectedVenderButton = false;
//				rebuildPanel();				
//			}			
//			
//		});
//		
//		buildButtonsPanel();
//		
//	}
//	
//	private void buildButtonsPanel() {
//		if(!isSelectedVenderButton){
//			buttonsPanel.add(venderButton);
//		}else{
//			buttonsPanel.add(selectedVenderButton);
//		}
//		buttonsPanel.doLayout();
//		
//	}
//
//	private void rebuildPanel() {
//		buildButtonsPanel();		
//	}
}
