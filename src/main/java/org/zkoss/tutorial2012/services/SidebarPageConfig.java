package org.zkoss.tutorial2012.services;

import java.util.List;

public interface SidebarPageConfig {

		
	public List<SidebarPage> getPages();
	
	public SidebarPage getPage(String name);
}