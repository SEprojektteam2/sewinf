package com.gwt.client;

import com.google.gwt.visualization.client.visualizations.*;
import com.google.gwt.visualization.client.*;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;


public class VisualizationManager {
	
	private DataTable[] DATA;
	private DataTable TableDATA;
	private int curYearIndex;		//as index for DATA array
	private String param1;
	private String param2;
	
	Selectable[] graphs;
	//index 0 = Table --> always created
	//index 1 = GeoMap --> only if no country was chosen
	
	
	//Constructor
	VisualizationManager(String firstParameter, String secondParameter)
	{
		this(firstParameter, secondParameter, 2014);
	}
	VisualizationManager(String firstParameter, String secondParameter, int year)
	{
		setParam1(firstParameter);
		setParam2(secondParameter);
		
		//has to be the index for the DATA array, not the actual year!
		setCurYearIndex(year);
		
		prepareDataTable();
		prepareGraphs();
				
	}
	
	private void prepareDataTable()
	{
		//In Table there will be all the years displayed
		TableDATA = DataTable.create();
		TableDATA.addColumn(ColumnType.NUMBER, "Year");
		
		//For all other charts it's only possible to display 1 year with a few exceptions - those will have a separat DATAset when we implement them
		DATA = new DataTable[20];
		for(int i = 0; i < DATA.length; i++)
		{
			DATA[i] = DataTable.create();
		}
		
		//DATA.addColumn(ColumnType.STRING, param1);
		
		/*
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Name');
        data.addColumn('number', 'Salary');
        data.addColumn('boolean', 'Full Time Employee');
        data.addRows([
          ['Mike',  {v: 10000, f: '$10,000'}, true],
          ['Jim',   {v:8000,   f: '$8,000'},  false],
          ['Alice', {v: 12500, f: '$12,500'}, true],
          ['Bob',   {v: 7000,  f: '$7,000'},  true]
        ]);

        
        table.draw(data, {showRowNumber: true});
		*/

	}
	
	public void prepareGraphs()
	{
		graphs = new Selectable[2];
		
		//table
		Table table = new Table(TableDATA, null);
		graphs[0] = table;
		
		//GeoMap
		GeoMap map = new GeoMap(DATA[curYearIndex], null);
		graphs[1] = map;
	}
	
	/*		
	gwt.Graph donut
			gwt.Graph table
			gwt.Graph ..
			gwt.Graph.Slider
			..
			
			+selectData(Information)
			+prepareDataMap( )
			// Nimmt globales int curYear und zeichnet Grafik vom Array DATA[curYear][ ][ ]
			+prepareDataTable( )
			+prepareDataGraphics( )
			+generateInterpolationBar( )
	
	*/
			
//Get and set methods for attributes
	public void setCurYearIndex(int year)
	{
		//DOTO Berechnung des Indexes fuer DATA array
		curYearIndex = year;
	}
	
	public int getCurYearIndex()
	{
		return curYearIndex;
	}
	
	public void setParam1(String Parameter)
	{
		param1 = Parameter;
	}
	
	public String getParam1()
	{
		return param1;
	}
	
	public void setParam2(String Parameter)
	{
		param2 = Parameter;
	}
	
	public String getParam2()
	{
		return param2;
	}
}
