package com.gwt.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SourceView extends Composite {

	private VerticalPanel vPanel= new VerticalPanel();
	private Label label;
	private ArrayList <Label>sources;
	public SourceView(){
		initWidget(this.vPanel);
		sources= new ArrayList();
		
	}
	
	public void addSource(String s){
		label=new Label(s);
		vPanel.add(label);
		sources.add(label);
	}
	
	public void removeSource(String s){
		label=new Label(s);
		if(sources.contains(label)){
			vPanel.remove(label);
			sources.remove(label);
		}
		
	}
}
