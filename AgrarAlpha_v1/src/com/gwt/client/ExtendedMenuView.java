package com.gwt.client;

//package guiA.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ExtendedMenuView extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private Button openBtn;
	private Button changeBtn;
	private Button saveBtn;
	private Button exportBtn;
	private Button homeBtn;
	private MainView main;

	public ExtendedMenuView(MainView main) {
		initWidget(this.vPanel);
		
		this.main=main;
		
		openBtn = new Button("Open");
		changeBtn = new Button("Change");
		saveBtn = new Button("Save");
		exportBtn = new Button("Export");
		homeBtn = new Button("AgrarAlpha");
		homeBtn.addClickHandler(new homeClickHandler());
		
		this.vPanel.add(homeBtn);
		this.vPanel.add(changeBtn);
		this.vPanel.add(openBtn);
		this.vPanel.add(saveBtn);
		this.vPanel.add(exportBtn);
		
		
	}
	
	private class homeClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			main.openHomeView();

		}
		
	}
}
