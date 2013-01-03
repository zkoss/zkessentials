/* 
	Description:
		ZK Tutorial
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.tutorial.chapter8;

import org.zkoss.tutorial.services.AuthenticationService;
import org.zkoss.xel.XelException;
import org.zkoss.zkplus.spring.SpringUtil;

public class AuthenticationVariableResolver implements org.zkoss.xel.VariableResolver {

	public Object resolveVariable(String name) throws XelException {
		if ("userCredential".equals(name)) {
			//for a global scope variable resolver, it is not suggest to keep a bean as a member (except the bean is singleton)
			AuthenticationService authService = (AuthenticationService)SpringUtil.getBean("authService");
			return authService.getUserCredential();
		}
		return null;
	}

}
