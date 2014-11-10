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
	private SourceView source;
	
	private VisualizationManager VisMan;

	/* This class present the view the user has after he clicked the create button on mainView. it contains the graphics the user wants to see
	 */
	public CreateView(VisualizationManager aVisManager){
		initWidget(this.basePanel);
		
		VisMan = aVisManager;
		
		source= new SourceView(); //A Panel which will contain labels with the source of the data
		source.addSource("Source:...."); //add a source
		
		tablePanel = new VerticalPanel(); // Panel where user see's the table, is in one of the tabs from tabpanel
		graphPanel = new VerticalPanel(); // Panel where user see's the graph, is in one of the tabs from tabpanel
		mapPanel = new VerticalPanel(); //Panel where user see's the map, is in one of the tabs from tabpanel
        
		/*only placeholer until we can fill with the acutal graphics from visalisationmanager.  will be removed later*/
		String s="content from visualizationmanager"; 
		  
		tablePanel.add(VisMan.graphWidgets[0]);
		tablePanel.add(source);
		
		//adding table
		//tablePanel.add(child);
		
		graphPanel.add(new Button("to be implemented."));
		graphPanel.add(source); // adding a verticalPanel with all source to the mapPanel
		
		mapPanel.add(VisMan.graphWidgets[1]);
		mapPanel.add(source); // adding a verticalPanel with all source to the mapPanel
		
		
		basePanel.add(tablePanel,"Table");
		basePanel.add(mapPanel,"Map");
		basePanel.add(graphPanel,"Graph");

		basePanel.selectTab(0); // first tab of the tabPanel will be open
		
	
	}

}
