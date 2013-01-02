package org.zkoss.tutorial.chapter8;

import org.zkoss.tutorial.services.AuthenticationService;
import org.zkoss.xel.VariableResolver;
import org.zkoss.xel.XelException;

public class AuthenticationVariableResolver implements VariableResolver {

	AuthenticationService authService = new AuthenticationServiceChapter8Impl();

	public Object resolveVariable(String name) throws XelException {
		if ("userCredential".equals(name)) {
			return authService.getUserCredential();
		}
		return null;
	}

}
