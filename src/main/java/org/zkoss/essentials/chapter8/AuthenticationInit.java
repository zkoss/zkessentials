/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter8;

import java.util.Map;

import org.zkoss.essentials.services.AuthenticationService;
import org.zkoss.essentials.services.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Initiator;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AuthenticationInit implements Initiator {

	@WireVariable
	AuthenticationService authService;
	
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		//wire service manually by calling Selectors API
		Selectors.wireVariables(page, this, Selectors.newVariableResolvers(getClass(), null));
		
		UserCredential cre = authService.getUserCredential();
		if(cre==null || cre.isAnonymous()){
			Executions.sendRedirect("/chapter8/login.zul");
			return;
		}
	}
}
