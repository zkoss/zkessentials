package org.zkoss.tutorial2012.chapter5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.tutorial2012.entity.User;
import org.zkoss.tutorial2012.services.UserInfoService;

public class UserInfoServiceChapter5Impl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	
	static protected List<User> userList = new ArrayList<User>();  
	static{
		userList.add(new User("anonymous","1234","Anonymous","anonumous@your.com"));
		userList.add(new User("admin","1234","Admin","admin@your.com"));
		userList.add(new User("zkoss","1234","ZKOSS","info@zkoss.org"));
	}
	
	
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
	
	public synchronized void updateUser(User user){
		//do nothing
		int s = userList.size();
		for(int i=0;i<s;i++){
			User u = userList.get(i);
			if(user.getAccount().equals(u.getAccount())){
				userList.set(i,User.clone(user));
				break;
			}
		}
	}
}
