package com.gwt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
// only placeholder at the moment
public class ExportView extends Composite {

	 private FlowPanel fPanel= new FlowPanel();
     private Button b;	
     
     public ExportView(){
    	 initWidget(this.fPanel);

    	 b= new Button("coming soon2");
    	 fPanel.add(b);
    	 
     }


}
