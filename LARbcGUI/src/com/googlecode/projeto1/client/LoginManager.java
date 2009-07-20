package com.googlecode.projeto1.client;

import com.googlecode.projeto1.client.beans.AdminBean;

public class LoginManager {
	
	private static AdminBean logedAdministrator;
	
	public static void setLogged(AdminBean admin){
		logedAdministrator = admin;
	}
	
	public static AdminBean getLogedAdministrator(){
		return logedAdministrator;
	}

}
