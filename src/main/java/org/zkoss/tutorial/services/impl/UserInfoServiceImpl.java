package org.zkoss.tutorial.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.zkoss.tutorial.entity.User;
import org.zkoss.tutorial.services.UserInfoService;

@Service("userInfoService")
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserInfoServiceImpl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserDao dao;
	
	public UserInfoServiceImpl(){
		System.out.println(">>>> "+this);
	}
	
	public User findUser(String account){
		return dao.get(account);
	}
	
	public void updateUser(User user){
		dao.update(user);
	}
}
