package com.gwt.client;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParameterSetTest {

	@Test
	public void test() {
		
		VisualizationManager.setParameters("World", "Apple", "Export");
		assertEquals("Country", VisualizationManager.param1);
		assertEquals("Apple Export", VisualizationManager.param2);
		
		VisualizationManager.setParameters("Switzerland", "null", "Export");
		assertEquals("Product", VisualizationManager.param1);
		assertEquals("Switzerland Export", VisualizationManager.param2);
		
		VisualizationManager.setParameters("Switzerland", "Pie", "null");
		assertEquals("Product Type", VisualizationManager.param1);
		assertEquals("Switzerland Pie", VisualizationManager.param2);
		
		//Exception are being dealt with in a different class. It means that the Visualization Manager will only get valid inputs.
		
		VisualizationManager.setParameters("world", "Pie", "null");
		assertEquals("Country", VisualizationManager.param1);
		assertEquals("Pie Null", VisualizationManager.param2);
		
		VisualizationManager.setParameters("world", "Null", "Import");
		assertEquals("Country", VisualizationManager.param1);
		assertEquals("Null Import", VisualizationManager.param2);
		
		VisualizationManager.setParameters("Germany", "Pie", "Production");
		assertEquals("invalid", VisualizationManager.param1);
		assertEquals("invalid", VisualizationManager.param2);
		
	}

}
