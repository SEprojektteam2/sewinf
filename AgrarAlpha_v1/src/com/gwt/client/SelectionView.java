package com.gwt.client;

//package guiA.client;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class SelectionView extends Composite {

	private Label yearLabel;
	private Label countryLabel;
	private Label test1; // only to test if the getter functions works
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
	private Label informationL; // will appear if world is selected and inform the user that he can only choose a product or a producttype but not both
	
	private MainView main;

	
	private int lastyear = 2010; // last year we got data
	private int CBcounter = 0; //counter how many checkboxes are checked
	private DataManager data;

	/*
	 * This class is drawing the options, the user can choose from. The
	 * RootPanel is a FlexTable (Table with flexible size)
	 */
	public SelectionView(MainView main){

		initWidget(this.fTable);
		this.main = main;
		
        
		informationL=new Label("placeholder"); // informs the user that he can only select a product or a producttyp but not both
		yearLabel = new Label("Year");
		countryLabel = new Label("Country");
		
		productCB = new CheckBox("Product");
		productCB.addClickHandler(new checkBoxClickHandler(productCB));
		typeCB = new CheckBox("Product Type");
		typeCB.addClickHandler(new checkBoxClickHandler(typeCB));

		yearLB = new ListBox();
		yearLB.addItem(" "); // Adding blank option

		/* fills listbox with years */
		String year = null;
		for (int i = 1990; i <= lastyear; i++) // i is the first year user can
												// select
		{
			year = year.valueOf(i);
			yearLB.addItem(year);
		}
		
		// String country=null;
		/* fills listbox with countries */
		countryLB = new ListBox();
		countryLB.addChangeHandler(new countryLBChangeHandler());
		ArrayList cArray= new ArrayList();
		cArray = data.getCountries(); 
		for(int j=0;j<cArray.size();j++) {
			String country=(String) cArray.get(j); 
			countryLB.addItem(country); 
		}
		
		/*countryLB = new ListBox();
		countryLB.addChangeHandler(new countryLBChangeHandler());
		countryLB.addItem("World");
		countryLB.addItem("Switzerland");
		countryLB.addItem("Germany");*/

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
  	  fTable.setWidget(1,2,informationL);


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
		if (productCB.getValue()) // checks if checkbox is selected
			s = productLB.getValue(productLB.getSelectedIndex());
		else
			s = "null";
		return s;
	}

	public String getType() {
		String s;
		if (typeCB.getValue()) // checks if checkbox is selected
			s = typeLB.getValue(typeLB.getSelectedIndex());
		else
			s = "null";
		return s;
	}

	private class createClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			 // dialog appears when user wants to create an invalid selection 
			if(yearLB.isItemSelected(0) || CBcounter==0){
				new DialogBoxCreate().show();
			}
			
			// main.openCreateView(); // for testing checkbox behavior its in a comment

			/*
			 * only for testing get methodes test1=new Label(getYear());
			 * test2=new Label(getCountry()); test3=new Label(getProduct());
			 * test4=new Label(getType());
			 * 
			 * fTable.setWidget(5, 0, test1); fTable.setWidget(5, 1, test2);
			 * fTable.setWidget(6, 0, test3); fTable.setWidget(6, 1, test4);
			 */
		}

	}
//this changehandler will let a label appear when world is selected and dissapear when a country is selected
	private class countryLBChangeHandler implements ChangeHandler{

		@Override
		public void onChange(ChangeEvent event) {
              if(countryLB.isItemSelected(0)){
            	  fTable.setWidget(1,2,informationL);
              }		
              else{
            	  fTable.remove(informationL);
              }
		}
		
	}
	
	private class listBoxChangeHandler implements ChangeHandler {

		private CheckBox box;
		private ListBox list;

		public listBoxChangeHandler(ListBox list, CheckBox box) {
			this.list = list;
			this.box = box;

		}
/* this method handles the change of the selection in a listbox
 * if the user select an option in a listbox the checkbox will adapt*/
		public void onChange(ChangeEvent event) {
			if (countryLB.isItemSelected(0)) { //checks if world is chosen

				if (list.isItemSelected(0)) {//checks if the user has picked an option
					if (box.getValue()) { // look if the checkbox is checked
						CBcounter--;
					}

					box.setValue(false);

				} else {
					if (!box.getValue() && CBcounter < 1) { // checks if there already a checkbox checked and if the current checkbox is checked
						CBcounter++;
						box.setValue(true);
					}

				}

			} else {
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

	}

	private class checkBoxClickHandler implements ClickHandler {

		private CheckBox box;

		public checkBoxClickHandler(CheckBox box) {

			this.box = box;

		}

		@Override
		public void onClick(ClickEvent event) {
			if (countryLB.isItemSelected(0)) { //if world is selected the user is only allowed to choose one more parameter
				if (!(CBcounter < 1)) {  //checks if there is already one parameter chosen
					box.setValue(false);
				} else if (box.getValue()) {

					box.setValue(false);
					CBcounter--;
				} else if (!box.getValue()) {
					box.setValue(true);
					CBcounter++;
				}
			} else { //for the case world is not chosen the user can set two parameters
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

}