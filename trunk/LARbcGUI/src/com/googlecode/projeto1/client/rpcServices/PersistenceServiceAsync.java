package com.googlecode.projeto1.client.rpcServices;

import java.util.List;

import com.googlecode.projeto1.client.beans.CaseBean;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PersistenceServiceAsync {

	public void doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType, AsyncCallback<List<CaseBean>> callback);

}
