package org.zkoss.tutorial2012.chapter8;

import java.util.Map;

import org.zkoss.tutorial2012.services.AuthenticationService;
import org.zkoss.tutorial2012.services.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver(DelegatingVariableResolver.class)
public class AuthenticationInit implements Initiator {

	@WireVariable
	AuthenticationService authService;
	
	
	public AuthenticationInit(){
		
	}
	
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		Selectors.wireVariables(page, this, Selectors.newVariableResolvers(getClass(), null));
		
		UserCredential cre = authService.getUserCredential();
		if("true".equalsIgnoreCase((String)args.get("authentication"))){
			if(cre==null || cre.isAnonymous()){
				Executions.sendRedirect("/chapter8/login.zul");
				return;
			}
		}
	}
}