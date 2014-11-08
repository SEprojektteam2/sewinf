package com.gwt.client;

//package guiA.client;

import com.google.gwt.dev.asm.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateView extends Composite{
	
	private TabPanel basePanel = new TabPanel();
	private VerticalPanel tablePanel = new VerticalPanel();
	private VerticalPanel graphPanel = new VerticalPanel();
	private VerticalPanel mapPanel = new VerticalPanel();
	
	private VisualizationManager VisMan;

	/* This class present the view the user has after he clicked the create button on mainView. it contains the graphics the user wants to see
	 */
	public CreateView(VisualizationManager aVisMan){
		initWidget(this.basePanel);
		
		VisMan = aVisMan;
		
		tablePanel = new VerticalPanel();
		graphPanel = new VerticalPanel();
		mapPanel = new VerticalPanel();
        
		/*only placeholer until we can fill with the acutal graphics from visalisationmanager.  will be removed later*/
		String s="content from visualizationmanager"; 
		Button b= new Button(s);  
		tablePanel.add(new Button("Placeholder"));
		
		//adding table
		//tablePanel.add(child);
		
		graphPanel.add(new Button("Apple"));
		mapPanel.add(b);
		
		
		basePanel.add(tablePanel,"Table");
		basePanel.add(mapPanel,"Map");
		basePanel.add(graphPanel,"Graph");

		basePanel.selectTab(0); // first tab of the tabPanel will be open
		
	
	}

}
