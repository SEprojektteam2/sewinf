package com.gwt.client;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.visualization.client.DataTable;

public interface DataManagerService extends RemoteService {
	ArrayList<String> getCountries();
	DataTable getDataTable(String country, String product, String type);
}
