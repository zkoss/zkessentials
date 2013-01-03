/* 
	Description:
		ZK Tutorial
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.tutorial.services;

import java.util.List;

public interface SidebarPageConfig {
		
	public List<SidebarPage> getPages();	
	public SidebarPage getPage(String name);
}