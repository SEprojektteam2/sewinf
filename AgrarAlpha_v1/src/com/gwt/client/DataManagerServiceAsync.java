package com.gwt.client;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataManagerServiceAsync {
	void getCountries(AsyncCallback<ArrayList<String>> callback);
}
