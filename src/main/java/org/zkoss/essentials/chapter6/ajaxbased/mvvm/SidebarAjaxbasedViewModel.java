/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter6.ajaxbased.mvvm;

import java.util.List;

import org.zkoss.essentials.services.SidebarPage;
import org.zkoss.essentials.services.SidebarPageConfig;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SidebarAjaxbasedViewModel {

	@WireVariable("sidebarPageConfigAjaxbased")
	private SidebarPageConfig pageConfig;
	
	public List<SidebarPage> getSidebarPages() {
		return pageConfig.getPages();
	}
}
