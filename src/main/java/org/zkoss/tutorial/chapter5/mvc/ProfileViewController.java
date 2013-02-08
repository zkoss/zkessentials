/* 
	Description:
		ZK Tutorial
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.tutorial.chapter5.mvc;

import java.util.Set;

import org.zkoss.tutorial.entity.User;
import org.zkoss.tutorial.services.AuthenticationService;
import org.zkoss.tutorial.services.CommonInfoService;
import org.zkoss.tutorial.services.UserCredential;
import org.zkoss.tutorial.services.UserInfoService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

@VariableResolver(DelegatingVariableResolver.class)
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
	
	//wire services
	@WireVariable
	AuthenticationService authService;
	@WireVariable
	UserInfoService userInfoService;
	
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
			Clients.showNotification("check chapter 8 for authenticated access");
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
