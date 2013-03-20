/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter5.mvc;

import java.util.Set;

import org.zkoss.essentials.chapter5.AuthenticationServiceChapter5Impl;
import org.zkoss.essentials.chapter5.UserInfoServiceChapter5Impl;
import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.AuthenticationService;
import org.zkoss.essentials.services.CommonInfoService;
import org.zkoss.essentials.services.UserCredential;
import org.zkoss.essentials.services.UserInfoService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class ProfileViewController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;

	//wire components
	@Wire
	Label account;
	@Wire
	Textbox fullName;
	@Wire
	Textbox email;
	@Wire
	Datebox birthday;
	@Wire
	Listbox country;
	@Wire
	Textbox bio;
	@Wire
	Label nameLabel;
	
	//services
	AuthenticationService authService = new AuthenticationServiceChapter5Impl();
	UserInfoService userInfoService = new UserInfoServiceChapter5Impl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		ListModelList<String> countryModel = new ListModelList<String>(CommonInfoService.getCountryList());
		country.setModel(countryModel);
		
		refreshProfileView();
	}
	
	
	@Listen("onClick=#saveProfile")
	public void doSaveProfile(){
		UserCredential cre = authService.getUserCredential();
		User user = userInfoService.findUser(cre.getAccount());
		if(user==null){
			//TODO handle un-authenticated access 
			return;
		}
		
		//apply component value to bean
		user.setFullName(fullName.getValue());
		user.setEmail(email.getValue());
		user.setBirthday(birthday.getValue());
		user.setBio(bio.getValue());
		
		Set<String> selection = ((ListModelList)country.getModel()).getSelection();
		if(!selection.isEmpty()){
			user.setCountry(selection.iterator().next());
		}else{
			user.setCountry(null);
		}
		
		nameLabel.setValue(fullName.getValue());
		
		userInfoService.updateUser(user);
		
		Clients.showNotification("Your profile is updated");
	}
	
	@Listen("onClick=#reloadProfile")
	public void doReloadProfile(){
		refreshProfileView();
	}

	private void refreshProfileView() {
		UserCredential cre = authService.getUserCredential();
		User user = userInfoService.findUser(cre.getAccount());
		if(user==null){
			//TODO handle un-authenticated access 
			return;
		}
		
		//apply bean value to UI components
		account.setValue(user.getAccount());
		fullName.setValue(user.getFullName());
		email.setValue(user.getEmail());
		birthday.setValue(user.getBirthday());
		bio.setValue(user.getBio());
		
		((ListModelList)country.getModel()).addToSelection(user.getCountry());

		nameLabel.setValue(user.getFullName());
	}
}
