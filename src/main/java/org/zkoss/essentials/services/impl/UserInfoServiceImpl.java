/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.UserInfoService;

import java.io.Serializable;

@Service("userInfoService")
@Scope
public class UserInfoServiceImpl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserDao dao;
	
	public User findUser(String account){
		return dao.get(account);
	}
	
	public User updateUser(User user){
		return dao.update(user);
	}
}
