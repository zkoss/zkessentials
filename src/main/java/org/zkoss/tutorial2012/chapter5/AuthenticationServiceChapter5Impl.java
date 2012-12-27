package org.zkoss.tutorial2012.chapter5;

import java.io.Serializable;

import org.zkoss.tutorial2012.services.AuthenticationService;
import org.zkoss.tutorial2012.services.UserCredential;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class AuthenticationServiceChapter5Impl implements AuthenticationService,Serializable{
	private static final long serialVersionUID = 1L;

	public UserCredential getUserCredential(){
		Session sess = Sessions.getCurrent();
		UserCredential cre = (UserCredential)sess.getAttribute("userCredential");
		if(cre==null){
			cre = new UserCredential();//new a anonymous user and set to session
			sess.setAttribute("userCredential",cre);
		}
		return cre;
	}
	

	public boolean login(String nm, String pd) {
		// will be implemented in chapter 8
		return false;
	}


	public void logout() {
		// will be implemented in chapter 8
	}

	public void setLastUnauthUri(String uri) {
		// will be implemented in chapter 8
	}

	public String getLastUnauthUri() {
		// will be implemented in chapter 8
		return null;
	}


}