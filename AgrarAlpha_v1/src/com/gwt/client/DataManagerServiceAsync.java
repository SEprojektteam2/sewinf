package com.gwt.client;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.visualization.client.DataTable;

public interface DataManagerServiceAsync {
	void getCountries(AsyncCallback<ArrayList<String>> callback);
	void getDataTable(String country, String product, String type, AsyncCallback<DataTable> callback);
}
