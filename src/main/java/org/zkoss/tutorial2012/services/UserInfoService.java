package org.zkoss.tutorial2012.services;

import org.zkoss.tutorial2012.entity.User;

public interface UserInfoService {

	public User findUser(String account);
	
	public void updateUser(User user);
}
