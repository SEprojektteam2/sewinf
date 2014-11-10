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
	public CreateView(){
		initWidget(this.basePanel);
		
		//VisMan = aVisManager;
		
		source= new SourceView();
		source.addSource("Source:...."); //add a source
		
		tablePanel = new VerticalPanel();
		graphPanel = new VerticalPanel();
		mapPanel = new VerticalPanel();
        
		/*only placeholer until we can fill with the acutal graphics from visalisationmanager.  will be removed later*/
		Button message = new Button("To be implemented in a future sprint.");
		message.setStyleName("message");
		
		
		  
		//tablePanel.add(VisMan.graphWidgets[0]);
		tablePanel.add(message.asWidget());
		tablePanel.add(source);
		
		//adding table
		//tablePanel.add(child);
		
		
	
		graphPanel.add(message.asWidget());
		graphPanel.add(source); // adding a verticalPanel with all source to the mapPanel
		
		//mapPanel.add(VisMan.graphWidgets[1]);
		mapPanel.add(message.asWidget());
		mapPanel.add(source); // adding a verticalPanel with all source to the mapPanel
		
		
		basePanel.add(tablePanel,"Table");
		basePanel.add(mapPanel,"Map");
		basePanel.add(graphPanel,"Graph");

		basePanel.selectTab(0); // first tab of the tabPanel will be open
		
	
	}

}
