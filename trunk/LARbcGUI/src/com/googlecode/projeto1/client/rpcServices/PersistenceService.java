package com.googlecode.projeto1.client.rpcServices;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.googlecode.projeto1.client.beans.CaseBean;

@RemoteServiceRelativePath("persistence")
public interface PersistenceService extends RemoteService {

	public List<CaseBean> doQuery(int resultNumber, String state, String city, String neighborhood, String street, String name,
			 float builtArea, float totalArea, int garageSpace, int bedroom, int suite,
			 int bathroom, String type, float price, int businessType);

}
