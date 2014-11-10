package com.gwt.client;
import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DataManager {
	private DataManagerServiceAsync dataManagerSvc = GWT.create(DataManagerService.class);
	private ArrayList<String> result;
	public ArrayList<String> getCountries() {
		
		// Initialize the service proxy.
		if (dataManagerSvc == null) {
			dataManagerSvc = GWT.create(DataManagerService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<ArrayList<String>> callback = new AsyncCallback<ArrayList<String>>() {
	      public void onFailure(Throwable caught) {
	        // TODO: Do something with errors.
	      }

	      public void onSuccess(ArrayList<String> resultTemp) {
	        result=resultTemp;
	      }
	    };

	    // Make the call to the stock price service.
	    dataManagerSvc.getCountries(callback);
	    return result;
	  }
	
}
