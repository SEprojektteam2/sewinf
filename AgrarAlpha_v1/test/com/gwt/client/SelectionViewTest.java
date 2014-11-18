package com.gwt.client;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class SelectionViewTest extends GWTTestCase {

	@Test
	public void test() {
		
		SelectionView.productLB.setItemSelected(1, true);
        assertEquals("true",SelectionView.productCB.getValue());
	
        SelectionView.productLB.setItemSelected(0, false);
        assertEquals("false",SelectionView.productCB.getValue());
	
        SelectionView.typeLB.setItemSelected(1, true);
        assertEquals("true",SelectionView.typeCB.getValue());
	
        SelectionView.typeLB.setItemSelected(0, false);
        assertEquals("false",SelectionView.typeCB.getValue());
	}

	@Override
	public String getModuleName() {
		return "com.gwt.AgrarAlpha_v1";
	}

}

