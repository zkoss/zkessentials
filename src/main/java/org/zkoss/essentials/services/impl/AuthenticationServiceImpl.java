package org.zkoss.essentials.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.*;
import org.zkoss.zk.ui.*;

import java.io.Serializable;

@Service("authService")
@Scope
public class AuthenticationServiceImpl implements AuthenticationService,Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserInfoService userInfoService;

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
		User user = userInfoService.findUser(nm);
		//a simple plan text password verification
		if(user==null || !user.getPassword().equals(pd)){
			return false;
		}
		
		Session sess = Sessions.getCurrent();
		UserCredential cre = new UserCredential(user.getAccount(),user.getFullName());
		sess.setAttribute("userCredential",cre);
		
		//TODO handle the role here for authorization
		return true;
	}
	
	public void logout() {
		Session sess = Sessions.getCurrent();
		sess.removeAttribute("userCredential");
	}
}
