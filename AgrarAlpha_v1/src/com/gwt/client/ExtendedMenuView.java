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
		openBtn.addClickHandler(new openClickHandler());


		changeBtn = new Button("Change");
		changeBtn.addClickHandler(new changeClickHandler());

		saveBtn = new Button("Save");
		saveBtn.addClickHandler(new saveClickHandler());

		exportBtn = new Button("Export");
		exportBtn.addClickHandler(new exportClickHandler());

		
		homeBtn = new Button("AgrarAlpha");
		homeBtn.addClickHandler(new homeClickHandler());
		
		this.vPanel.add(homeBtn);
		this.vPanel.add(changeBtn);
		this.vPanel.add(openBtn);
		this.vPanel.add(saveBtn);
		this.vPanel.add(exportBtn);
		
		
	}
	
	private class openClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			main.openOpenView();
			}
		
	}
	
	private class homeClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			main.openHomeView();

		}
		
	}
	private class saveClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			main.openSaveView();
			}
	}
	
		private class exportClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				main.openExportView();
				}
			
		}
		private class changeClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				main.openChangeView();
				}
			
		}
	
	
}