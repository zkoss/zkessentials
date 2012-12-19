package org.zkoss.tutorial2012.services;

import java.io.Serializable;

public class UserCredential implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String account;
	String name;

	public UserCredential(String account, String name) {
		this.account = account;
		this.name = name;
	}

	public UserCredential() {
		this.account = "anonymous";
		this.name = "Anonymous";
	}

	public boolean isAnonymous() {
		return "anonymous".equals(account);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
