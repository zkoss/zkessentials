package org.zkoss.tutorial2012.chapter8;

import java.util.Map;

import org.zkoss.tutorial2012.chapter5.AuthenticationServiceImpl;
import org.zkoss.tutorial2012.services.AuthenticationService;
import org.zkoss.tutorial2012.services.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class AuthenticationInit implements Initiator {

	AuthenticationService sercie = new AuthenticationServiceImpl();
	
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
		UserCredential userCredential = sercie.getCurrentUserCredential();
		if("true".equalsIgnoreCase((String)args.get("authentication"))){
			if(userCredential==null || userCredential.isAnonymous()){
				Executions.sendRedirect("/chapter8/login.zul");
				return;
			}
		}
	}
}