/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.UserInfoService;

public class UserInfoServiceChapter5Impl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	
	static protected List<User> userList = new ArrayList<User>();  
	static{
		userList.add(new User("anonymous","1234","Anonymous","anonumous@your.com"));
		userList.add(new User("admin","1234","Admin","admin@your.com"));
		userList.add(new User("zkoss","1234","ZKOSS","info@zkoss.org"));
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized User findUser(String account){
		int s = userList.size();
		for(int i=0;i<s;i++){
			User u = userList.get(i);
			if(account.equals(u.getAccount())){
				return User.clone(u);
			}
		}
		return null;
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized User updateUser(User user){
		int s = userList.size();
		for(int i=0;i<s;i++){
			User u = userList.get(i);
			if(user.getAccount().equals(u.getAccount())){
				userList.set(i,u = User.clone(user));
				return u;
			}
		}
		throw new RuntimeException("user not found "+user.getAccount());
	}
}
