package com.gwt.client;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.Table;
import com.google.gwt.visualization.client.VisualizationUtils;

//This test has to be in the com.gwt.client package to run, and has to be run with GWJ Junit Test..
//this test doesn't run beyond the point of crashing at line 22... I don't know why...
public class VisManagerTest extends GWTTestCase {

	 DataTable data;
	
	public void gwtSetUp()
	{
		Runnable callBack = new Runnable()
		{
			public void run()
			{
				data =  DataTable.create();
				data.addColumn(ColumnType.STRING, "Country");
				data.addColumn(ColumnType.NUMBER, "2011");
				data.addColumn(ColumnType.NUMBER, "2010");
				data.addColumn(ColumnType.NUMBER, "2009");
				
				data.addRows(5);
				data.setCell(0, 0, "Switzerland", "Switzerland", null);
				data.setCell(1, 0, "Germany", "Germany", null);
				data.setCell(2, 0, "Austria", "Austria", null);
				data.setCell(3, 0, "Slovakia", "Slovakia", null);
				data.setCell(4, 0, "Czech Republic", "Czech Republic", null);
				
				
				Random random = new Random();
				for(int i=1; i<4; i++)
				{
					for(int j=0; j<5; j++)
					{
						double number = random.nextDouble() % 1000.0;
						data.setCell(j, i, number, Double.toString(number), null);
					}
				}
			}
		};
		 
		VisualizationUtils.loadVisualizationApi(callBack, Table.PACKAGE);
	}
	
	
	@Test
	public void testConstructor()
	{
		Runnable callBack7 = new Runnable()
		{
			public void run()
			{
				VisualizationManager_v2 test = new VisualizationManager_v2(data, "world", "apple", "export", "2011");
				
				assertEquals("Country", VisualizationManager_v2.param1);
				assertEquals("apple export", VisualizationManager_v2.param2);
				assertEquals(0, VisualizationManager_v2.curYearIndex);
				
			}
		};
		
		VisualizationUtils.loadVisualizationApi(callBack7, Table.PACKAGE);
	}
	
	@Test
	public void testSetYearIndex()
	{
		Runnable callBack6 = new Runnable()
		{
			public void run()
			{
				VisualizationManager_v2.setCurYearIndex("2");
				
				//check all Cells for equality
				
				for(int j = 0; j < data.getNumberOfRows(); j++)
				{
					String test1 = VisualizationManager_v2.DATA.get(VisualizationManager_v2.curYearIndex).getFormattedValue(j, 0);
					String tested1 = data.getFormattedValue(j, 2+1);
					assertEquals(tested1, test1);
				}
				
				VisualizationManager_v2.setCurYearIndex("1");
				for(int j = 0; j < data.getNumberOfRows(); j++)
				{
					String test1 = VisualizationManager_v2.DATA.get(VisualizationManager_v2.curYearIndex).getFormattedValue(j, 0);
					String tested1 = data.getFormattedValue(j, 1+1);
					assertEquals(tested1, test1);
				}
				
				VisualizationManager_v2.setCurYearIndex("0");
				
				for(int j = 0; j < data.getNumberOfRows(); j++)
				{
					String test1 = VisualizationManager_v2.DATA.get(VisualizationManager_v2.curYearIndex).getFormattedValue(j, 0);
					String tested1 = data.getFormattedValue(j, 0+1);
					assertEquals(tested1, test1);
				}
			}
		};
		
		VisualizationUtils.loadVisualizationApi(callBack6, Table.PACKAGE);
	}
	
	@Test
	public void testPrepareGraphs()
	{
		Runnable callBack5 = new Runnable()
		{
			public void run()
			{
				VisualizationManager_v2.prepareGraphs();
				
				assertEquals(2, VisualizationManager_v2.graphs.size());
				assertEquals(Table.class, VisualizationManager_v2.graphs.get(0).getClass());
				assertEquals(GeoMap.class, VisualizationManager_v2.graphs.get(1).getClass());
				
			}
		};
		
		VisualizationUtils.loadVisualizationApi(callBack5, Table.PACKAGE);
	}

	@Test
	public void testSetParameter() {
		Runnable callBack4 = new Runnable()
		{
			public void run()
			{
				VisualizationManager_v2.setParameters("World", "Apple", "Export");
				assertEquals("Country", VisualizationManager_v2.param1);
				assertEquals("Apple Export", VisualizationManager_v2.param2);
				
				VisualizationManager_v2.setParameters("Switzerland", "null", "Export");
				assertEquals("Product", VisualizationManager_v2.param1);
				assertEquals("Switzerland Export", VisualizationManager_v2.param2);
				
				VisualizationManager_v2.setParameters("Switzerland", "Pie", "null");
				assertEquals("Product Type", VisualizationManager_v2.param1);
				assertEquals("Switzerland Pie", VisualizationManager_v2.param2);
				
				//Exception are being dealt with in a different class. It means that the Visualization Manager will only get valid inputs.
				
				VisualizationManager_v2.setParameters("world", "Pie", "null");
				assertEquals("Country", VisualizationManager_v2.param1);
				assertEquals("Pie Null", VisualizationManager_v2.param2);
				
				VisualizationManager_v2.setParameters("world", "Null", "Import");
				assertEquals("Country", VisualizationManager_v2.param1);
				assertEquals("Null Import", VisualizationManager_v2.param2);
				
				VisualizationManager_v2.setParameters("Germany", "Pie", "Production");
				assertEquals("invalid", VisualizationManager_v2.param1);
				assertEquals("invalid", VisualizationManager_v2.param2);
				
			}
		};
		
		VisualizationUtils.loadVisualizationApi(callBack4, Table.PACKAGE);
	}


	@Test
	public void testMessage() {
		
	//TODO implement the test for the method motavailableMessage(int index);
		final Label tested = new Label("message");
		tested.setStyleName("message");
		Runnable callBack3 = new Runnable()
		{
			public void run()
			{
				VisualizationManager_v2.notavailableMessage(0);
				assertEquals(tested.getStyleName(), VisualizationManager_v2.graphs.get(0).getStyleName());
				assertEquals(tested.getClass(), VisualizationManager_v2.graphs.get(0).getClass());
				assertEquals(tested.getTitle(), VisualizationManager_v2.graphs.get(0).getTitle());
				
				VisualizationManager_v2.notavailableMessage(1);
				assertEquals(tested.getStyleName(), VisualizationManager_v2.graphs.get(0).getStyleName());
				assertEquals(tested.getClass(), VisualizationManager_v2.graphs.get(0).getClass());
				assertEquals(tested.getTitle(), VisualizationManager_v2.graphs.get(0).getTitle());
				
			}
		};
		VisualizationUtils.loadVisualizationApi(callBack3, Table.PACKAGE);
	}

	@Test
	public void testIndexCalc() {
		
		int test1 = VisualizationManager_v2.calculateYearIndex("2011");	//expected result 0
		int test2 = VisualizationManager_v2.calculateYearIndex("2009");	//expected result 2
		int test3 = VisualizationManager_v2.calculateYearIndex("1990");	//expected result Diff-Year - 1
		int tested = VisualizationManager_v2.DIF_YEAR;
		
		assertEquals(0, test1);
		assertEquals(2,test2);
		assertEquals(tested, test3);
		
		//chekcing for exeptions
		int test4 = VisualizationManager_v2.calculateYearIndex("-1");	//expected result -1
		int test5 = VisualizationManager_v2.calculateYearIndex("2012");	//expected result -1
		int test6 = VisualizationManager_v2.calculateYearIndex("1899");	//expected result -1
		//-1 means invalid index!
		
		assertEquals(-1, test4);
		assertEquals(-1, test5);
		assertEquals(-1, test6);
		
	}

	
	@Test
	public void testPrepareData() {
		
		Runnable callBack2 = new Runnable()
		{
			public void run()
			{
				VisualizationManager_v2.TableDATA = data;
				VisualizationManager_v2.prepareData();
				
				assertEquals(data.getNumberOfColumns(), VisualizationManager_v2.DATA.size());
				assertEquals(data.getNumberOfRows(), VisualizationManager_v2.DATA.get(VisualizationManager_v2.DATA.size()-1).getNumberOfRows());
				
				//check all Cells for equality
				for(int i = 0; i < data.getNumberOfColumns()-1; i++)
				{
					for(int j = 0; j < data.getNumberOfRows(); j++)
					{
						String test1 = VisualizationManager_v2.DATA.get(i).getFormattedValue(j, 0);
						String tested1 = data.getFormattedValue(j, i+1);
						assertEquals(tested1, test1);
					}
				}
			}
		};
		
		VisualizationUtils.loadVisualizationApi(callBack2, Table.PACKAGE);
		
		
	}

	@Override
	public String getModuleName() {
		return "com.gwt.AgrarAlpha_v1";
		//return null;
	}

}
