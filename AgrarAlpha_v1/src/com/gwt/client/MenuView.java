package com.gwt.client;

//package guiA.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuView extends Composite {

private VerticalPanel vPanel = new VerticalPanel();
	private Button openBtn;
    private MainView main;
	private Button homeBtn;

	/*the menu on the main page to navigate through application*/
	public MenuView(MainView main) {
		initWidget(this.vPanel);
		this.main=main;
		openBtn = new Button("Open");
		
		homeBtn = new Button("AgrarAlpha");
	    homeBtn.addClickHandler(new homeClickHandler());	
		
		this.vPanel.add(homeBtn);
		this.vPanel.add(openBtn);
		}
	
	private class homeClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			main.openHomeView();
			}
		
	}
	}