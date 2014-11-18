package com.gwt.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class SelectionViewTest extends GWTTestCase {

	SelectionView_v2 sview;
	
	@Before
	public void gwtSetUp()
	{
		sview = new SelectionView_v2(new MainView());
	}
	
	@Test
	public void test() {
		
		SelectionView_v2.productLB.setItemSelected(1, true);
		//Problem: setting this programmaticly doesn't fire the changeEvent!!!
		//which would be essential for this check!!
        assertEquals("true",SelectionView_v2.productCB.getValue());
	
        SelectionView_v2.productLB.setItemSelected(0, false);
        assertEquals("false",SelectionView_v2.productCB.getValue());
	
        SelectionView_v2.typeLB.setItemSelected(1, true);
        assertEquals("true",SelectionView_v2.typeCB.getValue());
	
        SelectionView_v2.typeLB.setItemSelected(0, false);
        assertEquals("false",SelectionView_v2.typeCB.getValue());
	}

	@Override
	public String getModuleName() {
		return "com.gwt.AgrarAlpha_v1";
	}

}

