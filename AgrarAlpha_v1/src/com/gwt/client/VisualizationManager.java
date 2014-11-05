package com.gwt.client;

import com.googlecode.gwt.charts.*;


public class VisualizationManager {
	
	private var DATA;
	private int curYear;
	private String param1;
	private String param2;
	Table table;
	
	//Constructor
	VisualizationManager(String firstParameter, String secondParameter)
	{
		this(firstParameter, secondParameter, 2014);
	}
	VisualizationManager(String firstParameter, String secondParameter, int year)
	{
		setParam1(firstParameter);
		setParam2(secondParameter);
		setCurYear(year);
		
		
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

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: true});
		*/
		
	}
	/*		
	gwt.Graph donut
			gwt.Graph table
			gwt.Graph ..
			gwt.Graph.Slider
			..
			+VisualizationManager(int, String)
			+VisualizationManager(int, int)
			+VisualizationManager(int, char)
			+VisualizationManager(int, double)
			+setYear(int Year)
			//int curYear = Year - eventuell auch noch Jahr der einzelnen gwt.Graph Objekte neu aufbereiten.
			//prepareDataMap(); prepareDataTable.
			+selectData(Information)
			+prepareDataMap( )
			// Nimmt globales int curYear und zeichnet Grafik vom Array DATA[curYear][ ][ ]
			+prepareDataTable( )
			+prepareDataGraphics( )
			+generateInterpolationBar( )
	
	*/
			
//Get and set methods for attributes
	public void setCurYear(int year)
	{
		curYear = year;
	}
	
	public int getCurYear()
	{
		return curYear;
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
