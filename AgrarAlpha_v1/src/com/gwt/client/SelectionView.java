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

public class SelectionView extends Composite{

	private Label yearLabel;
	private Label countryLabel;
	private CheckBox productCB;
	private CheckBox typeCB;
	
	private FlowPanel fPanel = new FlowPanel();	
	private ListBox yearLB; //the size will adapt
	FlexTable fTable= new FlexTable();
	
	private ListBox countryLB;
	private ListBox productLB;
	private ListBox typeLB;
	
	private Button createBtn;
	private MainView main;
	
	private int CBcounter = 0;
	private final int MAX_CBcounterWorld = 2;
	private final int MAX_CBcounterCountry = 1;
/*
 This class is drawing the options, the user can choose
 The listbox will probably get replaced, cant select multiple options
 Probably will import from smartgwt http://www.smartclient.com/smartgwt/showcase/#multi_select_combobox_category
 */
	public SelectionView(MainView main) {
		

		initWidget(this.fTable);
		this.main=main;
		
		yearLabel = new Label("Year");
		countryLabel = new Label("Country");
		productCB = new CheckBox("Product");
		typeCB = new CheckBox("Product Type");
	
		yearLB = new ListBox();
		yearLB.addItem(" ");      //Adding blank option
		yearLB.addItem("2000"); 
		yearLB.addItem("2001");// Adding Options to select
		
		
		countryLB = new ListBox();
		countryLB.addItem("World");
		countryLB.addItem("Switzerland");
		countryLB.addItem("Germany");

		
		productLB = new ListBox();
		productLB.addItem(" ");
		productLB.addItem("Apple");
		productLB.addItem("Pie");
		productLB.addChangeHandler(new listBoxChangeHandler(productLB, productCB));
		
		typeLB = new ListBox();
		typeLB.addItem(" ");
		typeLB.addItem("Import");
		typeLB.addItem("Export");
		typeLB.addChangeHandler(new listBoxChangeHandler(typeLB, typeCB));


		createBtn=new Button("Create");
        createBtn.addClickHandler(new createClickHandler());
		

	/* Adding every component to the FlexTable*/
		fTable.setWidget(0, 0, yearLabel);
		fTable.setWidget(0,1,yearLB);
		
		fTable.setWidget(1,0, countryLabel);
		fTable.setWidget(1,1, countryLB);

		fTable.setWidget(2,0, productCB);
		fTable.setWidget(2,1, productLB);
		
		fTable.setWidget(3,0, typeCB);
		fTable.setWidget(3,1, typeLB);
		
		
		fTable.setWidget(4,0,createBtn);


      }
	
	/*will add get methode to get the selected items */
	
	private class createClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			main.openCreateView();
			}
		
	}
	
	private class listBoxChangeHandler implements ChangeHandler{
		
		private CheckBox box;
		private ListBox list;
		

		public listBoxChangeHandler(ListBox list, CheckBox box){
			this.list = list;
			this.box = box;
			
		}
		
		public void onChange(ChangeEvent event) {
			//if(countryLB)
			if(list.isItemSelected(0))
				{
					if(box.getValue())
					{
						CBcounter--;
					}
					
					box.setValue(false);
					
				}
			else
				{
					if(!box.getValue() && (CBcounter < MAX_CBcounterWorld || CBcounter < MAX_CBcounterCountry))
					{
						CBcounter++;
						box.setValue(true);
					}
				}
		}
	}
	
	
	private class checkBoxClickHandler implements ClickHandler{


		private CheckBox box;
		

		public checkBoxClickHandler(CheckBox box){
			
			this.box = box;
			
		}
		

		@Override
		public void onClick(ClickEvent event) {
			if(countryLB.isItemSelected(0))
			{
				if(!(CBcounter < MAX_CBcounterWorld))
				{
					box.setValue(false);
				}
				else if(box.getValue())
				{
					
					box.setValue(false);
					CBcounter--;
				}
				else if(!box.getValue())
				{
					box.setValue(true);
					CBcounter++;
				}
			}
			
		}
		


	}


}
