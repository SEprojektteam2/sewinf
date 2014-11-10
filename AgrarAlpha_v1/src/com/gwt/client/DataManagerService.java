package com.gwt.client;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface DataManagerService extends RemoteService {
	ArrayList<String> getCountries();
}
