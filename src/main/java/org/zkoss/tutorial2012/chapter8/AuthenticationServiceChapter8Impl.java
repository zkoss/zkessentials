package org.zkoss.tutorial2012.chapter8;

import org.zkoss.tutorial2012.chapter5.AuthenticationServiceChapter5Impl;
import org.zkoss.tutorial2012.chapter5.UserInfoServiceChapter5Impl;
import org.zkoss.tutorial2012.entity.User;
import org.zkoss.tutorial2012.services.UserCredential;
import org.zkoss.tutorial2012.services.UserInfoService;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class AuthenticationServiceChapter8Impl extends AuthenticationServiceChapter5Impl{
	private static final long serialVersionUID = 1L;
	
	UserInfoService userInfoService = new UserInfoServiceChapter5Impl();
	
	@Override
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
	
	@Override
	public void logout() {
		Session sess = Sessions.getCurrent();
		sess.removeAttribute("userCredential");
	}
}
