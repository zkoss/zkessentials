/* 
	Description:
		ZK Tutorial
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.tutorial.chapter5.mvvm;

import java.io.Serializable;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.tutorial.chapter5.AuthenticationServiceChapter5Impl;
import org.zkoss.tutorial.chapter5.UserInfoServiceChapter5Impl;
import org.zkoss.tutorial.entity.User;
import org.zkoss.tutorial.services.AuthenticationService;
import org.zkoss.tutorial.services.CommonInfoService;
import org.zkoss.tutorial.services.UserCredential;
import org.zkoss.tutorial.services.UserInfoService;
import org.zkoss.zk.ui.util.Clients;

public class ProfileViewModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//services
	AuthenticationService authService = new AuthenticationServiceChapter5Impl();
	UserInfoService userInfoService = new UserInfoServiceChapter5Impl();
	
	//data for the view
	User currentUser;
	
	public User getCurrentUser(){
		return currentUser;
	}
	
	public List<String> getCountryList(){
		return CommonInfoService.getCountryList();
	}
	
	@Init // @Init annotates a initial method
	public void init(){
		UserCredential cre = authService.getUserCredential();
		currentUser = userInfoService.findUser(cre.getAccount());
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
}
