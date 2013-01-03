/* 
	Description:
		ZK Tutorial
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.tutorial.services;

import org.zkoss.tutorial.entity.User;

public interface UserInfoService {

	public User findUser(String account);
	
	public User updateUser(User user);
}
