package org.zkoss.tutorial2012.services;


public interface AuthenticationService {

	public boolean login(String nm, String pd);
	public void logout();
	public UserCredential getUserCredential();
	public void setLastUnauthUri(String uri);
	public String getLastUnauthUri();
	
}
