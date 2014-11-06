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
	public VisualizationManager(DataTable aTableDATA, String firstParameter, String secondParameter)
	{
		this(aTableDATA, firstParameter, secondParameter, 2014);
	}
	
	public VisualizationManager(DataTable aTableDATA, String firstParameter, String secondParameter, int year)
	{
		TableDATA = aTableDATA;
		setParam1(firstParameter);
		setParam2(secondParameter);
		
		prepareData();
		
		//has to be the index for the DATA array, not the actual year!
		setCurYearIndex(year);
				
	}
	
	private void prepareData()
	{
		
		//For all other charts it's only possible to display 1 year with a few exceptions - those will have a separat DATAset when we implement them
		DATA = new DataTable[20];
		for(int i = 0; i < DATA.length; i++)
		{
			DATA[i] = DataTable.create();
			DATA[i].addColumn(ColumnType.STRING, param1);
			DATA[i].addColumn(ColumnType.NUMBER, param2);
			
			DATA[i].addRows(TableDATA.getNumberOfColumns()-1);
			//
			for(int c = 0; c < DATA[i].getNumberOfRows(); c++ )
			{
				DATA[i].setCell(c, 0, TableDATA.getColumnLabel(c+1), TableDATA.getColumnLabel(c+1), null);
				DATA[i].setCell(c, 1, TableDATA.getValueDouble(i, c+1), TableDATA.getFormattedValue(i, c+1), null);
			}
		}
		
	}
	
	private void prepareGraphs()
	{
		graphs = new Selectable[2];
		
		//table
		Table table = new Table(TableDATA, null);
		graphs[0] = table;
		
		//GeoMap
		GeoMap map = new GeoMap(DATA[curYearIndex], null);
		graphs[1] = map;
	}
		
//Get and set methods for attributes
	public void setCurYearIndex(int year)
	{
		//DOTO Berechnung des Indexes fuer DATA array
		curYearIndex = year;
		
		//graphs need to be updated because a different Year is to be shown
		prepareGraphs();
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
