package com.gwt.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.*;
import com.google.gwt.visualization.client.visualizations.Table.Options;
import com.google.gwt.visualization.client.*;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;


public class VisualizationManager {
	
	public final int DIF_YEAR = 2011-1990;
	public final int MIN_YEAR = 1990;
	public final int MAX_YEAR = 2011;
	
	private ArrayList<DataTable> DATA;
	
	private DataTable TableDATA;
	private int curYearIndex;		//as index for DATA array
	private String param1;
	private String param2;
	
	private boolean mapAvailable = false;
	
	public ArrayList<Widget> graphs;
	//index 0 = Table --> always created
	//index 1 = GeoMap --> only if no country was chosen
	
	
	//Constructor
	public VisualizationManager(DataTable aTableDATA, String country, String product, String type, String year)
	{
		setParameters(country, product, type);
		
		TableDATA = aTableDATA;
		
		prepareData();
		setCurYearIndex(year);
	}
	
	
	void setParameters(String country, String product, String type)
	{
		String firstParameter;
		String secondParameter;
		
		if (country.contentEquals("world"))
		{
			firstParameter = "Coutry";
			secondParameter = product + " " + type;
			mapAvailable = true;
		}
		else if(product.contentEquals("null"))
		{
			firstParameter = "Product";
			secondParameter = country + " " + type;
			mapAvailable = false;
		}
		else
		{
			firstParameter = "Type";
			secondParameter = country + " " + product;
			mapAvailable = false;
		}

		setParam1(firstParameter);
		setParam2(secondParameter);
	}

	
	
	private void prepareData()
	{
		
		//For all other charts it's only possible to display 1 year with a few exceptions - those will have a separat DATAset when we implement them
		DATA = new ArrayList<DataTable>();
		for(int i = 0; i < TableDATA.getNumberOfColumns()-1; i++)
		{
			DATA.add(DataTable.create());
			DATA.get(i).addColumn(ColumnType.STRING, param1);
			DATA.get(i).addColumn(ColumnType.NUMBER, param2);
			
			DATA.get(i).addRows(TableDATA.getNumberOfRows());
			
			//copy first column of TableDATA to DATA and the i+1 column to the second column of DATA
			for(int c = 0; c < DATA.get(i).getNumberOfRows(); c++ )
			{
				DATA.get(i).setCell(c, 0, TableDATA.getValueString(c, 0), TableDATA.getFormattedValue(c, 0), null);
				DATA.get(i).setCell(c, 1, TableDATA.getValueDouble(c, i+1), TableDATA.getFormattedValue(c, i+1), null);
			}
		}
		
	}
	
	
	private Table.Options createTableOptions() 
	{
	    Table.Options options = Options.create();
	    options.setWidth("400");
	    options.setHeight("240");
	    return options;
	}

	private GeoMap.Options createMapOptions()
	{
		GeoMap.Options options = GeoMap.Options.create();
		options.setWidth("400");
		options.setHeight("240");
		options.setColors(959595);
		return options;
	}
	
	private void prepareGraphs()
	{
		graphs = new ArrayList<Widget>(2);
		
		
		//table
		Table table = new Table();
		table.draw(TableDATA, createTableOptions());
		graphs.add(0, table);
		
	
		
		if(mapAvailable)
		{
			//GeoMap
			GeoMap map = new GeoMap();
			map.draw(DATA.get(curYearIndex), createMapOptions());
			graphs.add(1, map);
		}
		else
		{
			graphs.add(1, null);
			notavailableMessage(1);
		}
	}

	private void notavailableMessage(int index) {
		Label message = new Label("Your chosen criteria doesn't allow this representation.");
		graphs.add(index, message);
		message.setStyleName("message");
	}
		
	//calculating the index which will represent the appropriate year
	private int calculateYearIndex(String year)
	{
		
		int index = -1;
		
		int yInd = Integer.parseInt(year);
		if(yInd < 1990 || yInd > 2011)
			return -1;
		
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



/*package com.gwt.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.*;
import com.google.gwt.visualization.client.*;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;


public class VisualizationManager {
	
	public static final int DIF_YEAR = 2011-1990;
	public static final int MIN_YEAR = 1990;
	public static final int MAX_YEAR = 2011;
	
	static DataTable[] DATA;
	
	static DataTable TableDATA;
	public static int curYearIndex;		//as index for DATA array
	public static String param1;
	public static String param2;
	
	static Selectable[] graphs;
	static Widget[] graphWidgets;
	//index 0 = Table --> always created
	//index 1 = GeoMap --> only if no country was chosen
	
	
	//Constructor
	public VisualizationManager(DataTable aTableDATA, String country, String product, String type, String year)
	{
		
		setParameters(country, product, type);
		TableDATA = aTableDATA;

		setCurYearIndex(year);
		prepareData();
		
	}

	public static void setParameters(String country, String product, String type) {
		String firstParameter;
		String secondParameter;
		
		if (country.equalsIgnoreCase("world"))
		{
			firstParameter = "Country";
			secondParameter = product.substring(0, 1).toUpperCase().concat(product.substring(1, product.length())) + " " + type.substring(0, 1).toUpperCase().concat(type.substring(1, type.length()));
		}
		else if(product.equalsIgnoreCase("null"))
		{
			firstParameter = "Product";
			secondParameter = country.substring(0, 1).toUpperCase().concat(country.substring(1, country.length())) + " " + type.substring(0, 1).toUpperCase().concat(type.substring(1, type.length()));
		}
		else if(type.equalsIgnoreCase("null"))
		{
			firstParameter = "Product Type";
			secondParameter = country.substring(0, 1).toUpperCase().concat(country.substring(1, country.length())) + " " + product.substring(0, 1).toUpperCase().concat(product.substring(1, product.length()));
		}
		else
		{
			firstParameter = "invalid";
			secondParameter = "invalid";
		}

		setParam1(firstParameter);
		setParam2(secondParameter);
	}
	
	static void prepareData()
	{
		
		//For all other charts it's only possible to display 1 year with a few exceptions - those will have a separat DATAset when we implement them
		DATA = new DataTable[TableDATA.getNumberOfColumns()];
		for(int i = 0; i < DATA.length; i++)
		{
			DATA[i] = DataTable.create();
			DATA[i].addColumn(ColumnType.STRING, param1);
			DATA[i].addColumn(ColumnType.NUMBER, param2);
			
			DATA[i].addRows(TableDATA.getNumberOfRows());
			
			//copy first column of TableDATA to DATA and the i+1 column to the second column of DATA
			for(int c = 0; c < DATA[i].getNumberOfRows(); c++ )
			{
				DATA[i].setCell(c, 0, TableDATA.getValueString(c, 0), TableDATA.getFormattedValue(c, 0), null);
				DATA[i].setCell(c, 1, TableDATA.getValueDouble(c, i+1), TableDATA.getFormattedValue(c, i+1), null);
			}
		}
		
	}
	
	static void prepareGraphs()
	{
		graphs = new Selectable[2];
		graphWidgets = new Widget[2];
		
		//table
		Table table = new Table(TableDATA, null);
		graphs[0] = table;
		graphWidgets[0] = table.asWidget();
		
		if(param1.equalsIgnoreCase("country"))
		{
			//GeoMap
			GeoMap map = new GeoMap(DATA[curYearIndex], null);
			graphs[1] = map;
			graphWidgets[1] = map.asWidget();
		}
		else
		{
			graphs[1] = null;
			notavailableMessage(1);
		}
	}

	static void notavailableMessage(int index) {
		Label message = new Label("Your chosen criteria doesn't allow this representation.");
		graphWidgets[index] = message.asWidget();
		message.setStyleName("message");
	}
		
	//calculating the index which will represent the appropriate year
	static int calculateYearIndex(String year)
	{
		int index = -1;
		
		int yInd = Integer.parseInt(year);
		
		if(yInd > MAX_YEAR)
			return index;
		else if(yInd < MIN_YEAR)
			return index;
		
		yInd -= MAX_YEAR;
		
		index = Math.abs(yInd); 
		
		return index;
	}

	
//Get and set methods for attributes
	public static void setCurYearIndex(String year)
	{
		
		curYearIndex = calculateYearIndex(year);
		
		//graphs need to be updated because a different Year is to be shown
		prepareGraphs();
	}
	
	public static int getCurYearIndex()
	{
		return curYearIndex;
	}
	
	public static void setParam1(String Parameter)
	{
		param1 = Parameter;
	}
	
	public String getParam1()
	{
		return param1;
	}
	
	public static void setParam2(String Parameter)
	{
		param2 = Parameter;
	}
	
	public static String getParam2()
	{
		return param2;
	}
}
*/