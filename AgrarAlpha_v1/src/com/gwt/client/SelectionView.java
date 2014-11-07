package com.gwt.client;

//package guiA.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class SelectionView extends Composite{

	private CheckBox yearCB;
	private CheckBox countryCB;
	private CheckBox productCB;
	private FlowPanel fPanel = new FlowPanel();	private ListBox yearFromLB; //the size will adapt
	FlexTable fTable= new FlexTable();
	private ListBox yearToLB;
	private ListBox countryLB;
	private ListBox productLB;
	private Button createBtn;
	private MainView main;
/*
 This class is drawing the options, the user can choose
 The listbox will probably get replaced, cant select multiple options
 Probably will import from smartgwt http://www.smartclient.com/smartgwt/showcase/#multi_select_combobox_category
 */
	public SelectionView(MainView main) {
		

		initWidget(this.fTable);
		this.main=main;
		yearCB = new CheckBox("Year");
		countryCB = new CheckBox("Country");
		productCB = new CheckBox("Product");
	
		yearFromLB = new ListBox();
		yearFromLB.addItem(" ");      //Adding blank option
		yearFromLB.addItem("2000"); 
		yearFromLB.addItem("2001");// Adding Options to select
		
		/*
		yearToLB = new ListBox();
		yearToLB.addItem(" ");
		yearToLB.addItem("2001");
*/
		
		countryLB = new ListBox();
		countryLB.addItem(" ");
		countryLB.addItem("Switzerland");
		countryLB.addItem("Germany");

		
		productLB = new ListBox();
		productLB.addItem(" ");
		productLB.addItem("Apple");
		productLB.addItem("Pie");


		 createBtn=new Button("Create");
         createBtn.addClickHandler(new createClickHandler());
		

	/* Adding every component to the FlexTable*/
		fTable.setWidget(0,0,yearCB);
		fTable.setWidget(0,1,yearFromLB);
		fTable.setWidget(0,2,yearToLB);
		
		fTable.setWidget(1,0, countryCB);
		fTable.setWidget(1,1, countryLB);

		fTable.setWidget(2,0, productCB);
		fTable.setWidget(2,1, productLB);
		
		fTable.setWidget(3,0,createBtn);


      }
	
	/*will add get methode to get the selected items */
	
	private class createClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			main.openCreateView();
			}
		
	}






}
