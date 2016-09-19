/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter6.ajaxbased.mvvm;

import java.util.List;

import org.zkoss.essentials.chapter6.ajaxbased.SidebarPageConfigAjaxBasedImpl;
import org.zkoss.essentials.services.SidebarPage;
import org.zkoss.essentials.services.SidebarPageConfig;

public class SidebarAjaxbasedViewModel {

	//todo: wire service
	private SidebarPageConfig pageConfig = new SidebarPageConfigAjaxBasedImpl();
	
	public List<SidebarPage> getSidebarPages() {
		return pageConfig.getPages();
	}
}
