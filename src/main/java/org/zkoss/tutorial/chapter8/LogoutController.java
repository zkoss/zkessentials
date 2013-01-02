package org.zkoss.tutorial.chapter8;


import org.zkoss.tutorial.services.AuthenticationService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver(DelegatingVariableResolver.class)
public class LogoutController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	//wire service
	@WireVariable
	AuthenticationService authService;
	
	@Listen("onClick=#logout")
	public void doLogout(){
		authService.logout();		
		Executions.sendRedirect("/chapter8/");
	}
}
