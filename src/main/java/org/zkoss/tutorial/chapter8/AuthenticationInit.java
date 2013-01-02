package org.zkoss.tutorial.chapter8;

import java.util.Map;

import org.zkoss.tutorial.services.AuthenticationService;
import org.zkoss.tutorial.services.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class AuthenticationInit implements Initiator {

	AuthenticationService authService = new AuthenticationServiceChapter8Impl();
	
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
		UserCredential cre = authService.getUserCredential();
		if("true".equalsIgnoreCase((String)args.get("authentication"))){
			if(cre==null || cre.isAnonymous()){
				Executions.sendRedirect("/chapter8/login.zul");
				return;
			}
		}
	}
}