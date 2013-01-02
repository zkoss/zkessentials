package org.zkoss.tutorial.services;

import org.zkoss.tutorial.entity.User;

public interface UserInfoService {

	public User findUser(String account);
	
	public void updateUser(User user);
}
