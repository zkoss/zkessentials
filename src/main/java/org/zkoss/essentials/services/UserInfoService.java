/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.services;

import org.zkoss.essentials.entity.User;

public interface UserInfoService {

	/** find user by account **/
	public User findUser(String account);
	
	/** update user **/
	public User updateUser(User user);
}
