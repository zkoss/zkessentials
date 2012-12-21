package org.zkoss.tutorial2012.chapter5.mvc;

import java.util.Set;

import org.zkoss.tutorial2012.chapter5.AuthenticationServiceImpl;
import org.zkoss.tutorial2012.chapter5.UserInfoServiceImpl;
import org.zkoss.tutorial2012.entity.User;
import org.zkoss.tutorial2012.services.AuthenticationService;
import org.zkoss.tutorial2012.services.CommonInfoService;
import org.zkoss.tutorial2012.services.UserCredential;
import org.zkoss.tutorial2012.services.UserInfoService;
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
import org.zkoss.zul.ext.Selectable;

public class ProfileViewController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;

	//wire component
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
	
	//wire service
	AuthenticationService authService = new AuthenticationServiceImpl();
	UserInfoService userInfoService = new UserInfoServiceImpl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		ListModelList<String> countryModel = new ListModelList<String>(CommonInfoService.getCountryList());
		country.setModel(countryModel);
		
		reloadViewData();
	}
	
	
	@Listen("onClick=#saveProfile")
	public void doSaveProfile(){
		UserCredential cer = authService.getCurrentUserCredential();
		User user = userInfoService.findUser(cer.getAccount());
		
		
		//apply component value to bean
		user.setFullName(fullName.getValue());
		user.setEmail(email.getValue());
		user.setBirthday(birthday.getValue());
		user.setBio(bio.getValue());
		
		Set<String> selection = ((Selectable)country.getModel()).getSelection();
		if(!selection.isEmpty()){
			user.setCountry(selection.iterator().next());
		}else{
			user.setCountry(null);
		}
		
		userInfoService.updateUser(user);
		
		Clients.showNotification("Your profile is updated");
	}
	
	@Listen("onClick=#reloadProfile")
	public void doReloadProfile(){
		reloadViewData();
	}

	private void reloadViewData() {
		UserCredential cer = authService.getCurrentUserCredential();
		User user = userInfoService.findUser(cer.getAccount());
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
		
		((Selectable)country.getModel()).addToSelection(user.getCountry());
	}
}
