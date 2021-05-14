/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter3.mvvm;

import org.zkoss.bind.annotation.*;
import org.zkoss.essentials.chapter3.*;
import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.*;
import org.zkoss.zk.ui.util.Clients;

import java.io.Serializable;
import java.util.List;

public class ProfileViewModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//services
	private AuthenticationService authService = new AuthenticationServiceChapter3Impl();
	private UserInfoService userInfoService = new UserInfoServiceChapter3Impl();
	
	//data for the view
	private User currentUser;
	
	public User getCurrentUser(){
		return currentUser;
	}
	
	/**
	 * Retrieve all known country names. 
	 * @return a list of country name
	 */
	public List<String> getCountryList(){
		return CommonInfoService.getCountryList();
	}
	
	public ProfileViewModel(){
		/**
		 * 初始化服务，初始化其成员字段
		 * */
		UserCredential userCredential = authService.getUserCredential();
		currentUser = userInfoService.findUser(userCredential.getAccount());
		if(currentUser==null){
			//TODO handle un-authenticated access
			return;
		}
	}

	@Command //@Command annotates a command method 
	@NotifyChange("currentUser") //@NotifyChange annotates data changed notification after calling this method 
	public void save(){
		currentUser = userInfoService.updateUser(currentUser);
		Clients.showNotification("Your profile is updated");
	}

	@Command 
	@NotifyChange("currentUser")
	public void reload(){
		UserCredential cre = authService.getUserCredential();
		currentUser = userInfoService.findUser(cre.getAccount());
	}

	/**通知属性的更改
	 *一个属性：@NotifyChange("oneProperty")
	 * 多种属性：@NotifyChange(“propertyOne”,"propertyTwo")
	 * ViewModel中的所有属性：@NotifyChange("*")
	 * */


}
