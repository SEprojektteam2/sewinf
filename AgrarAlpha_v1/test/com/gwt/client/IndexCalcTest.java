package com.gwt.client;

import static org.junit.Assert.*;

import org.junit.Test;

public class IndexCalcTest {

	@Test
	public void test() {
		
		int test1 = VisualizationManager.calculateYearIndex("2011");	//expected result 0
		int test2 = VisualizationManager.calculateYearIndex("2009");	//expected result 2
		int test3 = VisualizationManager.calculateYearIndex("1990");	//expected result Diff-Year - 1
		int tested = VisualizationManager.DIF_YEAR;
		
		assertEquals(0, test1);
		assertEquals(2,test2);
		assertEquals(tested, test3);
		
		//chekcing for exeptions
		int test4 = VisualizationManager.calculateYearIndex("-1");	//expected result -1
		int test5 = VisualizationManager.calculateYearIndex("2012");	//expected result -1
		int test6 = VisualizationManager.calculateYearIndex("1899");	//expected result -1
		//-1 means invalid index!
		
		assertEquals(-1, test4);
		assertEquals(-1, test5);
		assertEquals(-1, test6);
		
	}

}
