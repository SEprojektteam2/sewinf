package com.gwt.client;

//package guiA.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class SelectionView extends Composite {

	private Label yearLabel;
	private Label countryLabel;
	private Label test1; //only to test if the getter functions works
	private Label test2;
	private Label test3;
	private Label test4;

	private CheckBox productCB;
	private CheckBox typeCB;

	private ListBox yearLB;
	private FlexTable fTable = new FlexTable(); // size is flexible

	private ListBox countryLB;
	private ListBox productLB;
	private ListBox typeLB;

	private Button createBtn;
	private MainView main;

	private int lastyear;
	private int CBcounter = 0;

	/*
	 * This class is drawing the options, the user can choose from. The
	 * RootPanel is a FlexTable (Table with flexible size)
	 */
	public SelectionView(MainView main) {

		initWidget(this.fTable);
		this.main = main;

		yearLabel = new Label("Year");
		countryLabel = new Label("Country");
		productCB = new CheckBox("Product");
		typeCB = new CheckBox("Product Type");

		yearLB = new ListBox();
		yearLB.addItem(" "); // Adding blank option
		
		for(int i=1990;i<lastyear;i++) //i is the first year user can select
		{
		
//			yearLB.addItem(year);
		}
		yearLB.addItem("2000"); // Adding Options to select


		countryLB = new ListBox();
		countryLB.addItem("World");
		countryLB.addItem("Switzerland");
		countryLB.addItem("Germany");

		productLB = new ListBox();
		productLB.addItem(" ");
		productLB.addItem("Apple");
		productLB.addItem("Pie");
		productLB.addChangeHandler(new listBoxChangeHandler(productLB,
				productCB));

		typeLB = new ListBox();
		typeLB.addItem(" ");
		typeLB.addItem("Import");
		typeLB.addItem("Export");
		typeLB.addChangeHandler(new listBoxChangeHandler(typeLB, typeCB));

		createBtn = new Button("Create");
		createBtn.addClickHandler(new createClickHandler());

		/* Adding every component to the FlexTable */
		fTable.setWidget(0, 0, yearLabel);
		fTable.setWidget(0, 1, yearLB);

		fTable.setWidget(1, 0, countryLabel);
		fTable.setWidget(1, 1, countryLB);

		fTable.setWidget(2, 0, productCB);
		fTable.setWidget(2, 1, productLB);

		fTable.setWidget(3, 0, typeCB);
		fTable.setWidget(3, 1, typeLB);

		fTable.setWidget(4, 0, createBtn);

	}

	public String getYear() {
		
		String s = yearLB.getValue(yearLB.getSelectedIndex());
		return s;
	}

	public String getCountry() {
		String s = countryLB.getValue(countryLB.getSelectedIndex());
		return s;
	}

	public String getProduct() {
		String s;
		if(productCB.getValue()) //checks if checkbox is selected
		 s = productLB.getValue(productLB.getSelectedIndex());
		else
			s="null";
		return s;
	}

	public String getType() {
		String s;
		if(typeCB.getValue()) //checks if checkbox is selected
		 s = typeLB.getValue(typeLB.getSelectedIndex());
		else
			s="null";
		return s;
	}
	private class createClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			//main.openCreateView();
		test1=new Label(getYear());	
		test2=new Label(getCountry());	
		test3=new Label(getProduct());	
		test4=new Label(getType());	
			
			fTable.setWidget(5, 0, test1);
			fTable.setWidget(5, 1, test2);
			fTable.setWidget(6, 0, test3);
			fTable.setWidget(6, 1, test4);

		}

	}

	private class listBoxChangeHandler implements ChangeHandler {

		private CheckBox box;
		private ListBox list;

		public listBoxChangeHandler(ListBox list, CheckBox box) {
			this.list = list;
			this.box = box;

		}

		public void onChange(ChangeEvent event) {
			// if(countryLB)
			if (list.isItemSelected(0)) {
				if (box.getValue()) {
					CBcounter--;
				}

				box.setValue(false);

			} else {
				if (!box.getValue() && CBcounter < 2) {
					CBcounter++;
					box.setValue(true);
				}

			}

		}

	}

	private class checkBoxClickHandler implements ClickHandler {

		private CheckBox box;

		public checkBoxClickHandler(CheckBox box) {

			this.box = box;

		}

		@Override
		public void onClick(ClickEvent event) {
			if (!(CBcounter < 2)) {
				box.setValue(false);
			} else if (box.getValue()) {

				box.setValue(false);
				CBcounter--;
			} else if (!box.getValue()) {
				box.setValue(true);
				CBcounter++;
			}

		}

	}



}