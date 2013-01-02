package org.zkoss.tutorial.services;


public interface AuthenticationService {

	public boolean login(String nm, String pd);
	public void logout();
	public UserCredential getUserCredential();
	
}
