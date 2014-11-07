package com.gwt.client;

import com.google.gwt.visualization.client.visualizations.*;
import com.google.gwt.visualization.client.*;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;


public class VisualizationManager {
	
	public final int DIF_YEAR = 2011-1990;
	public final int MIN_YEAR = 1990;
	public final int MAX_YEAR = 2011;
	
	private DataTable[] DATA;
	
	private DataTable TableDATA;
	private int curYearIndex;		//as index for DATA array
	private String param1;
	private String param2;
	
	Selectable[] graphs;
	//index 0 = Table --> always created
	//index 1 = GeoMap --> only if no country was chosen
	
	
	//Constructor
	public VisualizationManager(DataTable aTableDATA, String country, String product, String type, String year)
	{
		String firstParameter;
		String secondParameter;
		
		if (country.contentEquals("world"))
		{
			firstParameter = "Coutry";
			secondParameter = product + " " + type;
		}
		else if(product.contentEquals("null"))
		{
			firstParameter = "Product";
			secondParameter = country + " " + type;
		}
		else
		{
			firstParameter = "Type";
			secondParameter = country + " " + product;
		}

		
		TableDATA = aTableDATA;
		setParam1(firstParameter);
		setParam2(secondParameter);

		setCurYearIndex(year);
		prepareData();
		
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
		
	//calculating the index which will represent the appropriate year
	private int calculateYearIndex(String year)
	{
		int index = -1;
		
		int yInd = Integer.parseInt(year);
		yInd -= MAX_YEAR;
		
		index = Math.abs(yInd); 
		
		return index;
	}

	
//Get and set methods for attributes
	public void setCurYearIndex(String year)
	{
		
		curYearIndex = calculateYearIndex(year);
		
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
