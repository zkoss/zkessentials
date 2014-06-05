/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter7.ajaxbased.mvvm;

import java.util.Collections;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.essentials.services.SidebarPage;
import org.zkoss.essentials.services.SidebarPageConfig;

public class BookmarkChangeViewModel  {

	//todo: wire service
	private SidebarPageConfig pageConfig = new SidebarPageConfigAjaxBasedImpl();
	
	@Command("onBookmarkNavigate")
	public void onBookmarkNavigate(@BindingParam("bookmark") String bookmark) {
		if(bookmark.startsWith("p_")){
			//retrieve page from config
			String p = bookmark.substring("p_".length());
			SidebarPage page = pageConfig.getPage(p);
			if(page!=null) {
				//and post command to NavigationViewModel
				BindUtils.postGlobalCommand(null,  null, "onNavigate", Collections.<String, Object>singletonMap("page", page));
			}
		}
	}
}
