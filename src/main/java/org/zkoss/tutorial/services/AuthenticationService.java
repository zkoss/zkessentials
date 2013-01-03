/* 
	Description:
		ZK Tutorial
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.tutorial.services;

public interface AuthenticationService {

	public boolean login(String nm, String pd);
	public void logout();
	public UserCredential getUserCredential();
	
}
