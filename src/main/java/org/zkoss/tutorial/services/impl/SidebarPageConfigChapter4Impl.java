package org.zkoss.tutorial.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.zkoss.tutorial.services.SidebarPage;
import org.zkoss.tutorial.services.SidebarPageConfig;

@Service("sidebarPageConfigChapter4")
@Scope(value="request",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SidebarPageConfigChapter4Impl implements SidebarPageConfig{
	
	HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	public SidebarPageConfigChapter4Impl(){		
		pageMap.put("zk",new SidebarPage("zk","www.zkoss.org","/imgs/site.png","http://www.zkoss.org/"));
		pageMap.put("demo",new SidebarPage("demo","ZK Demo","/imgs/demo.png","http://www.zkoss.org/zkdemo"));
		pageMap.put("devref",new SidebarPage("devref","ZK Developer Reference","/imgs/doc.png","http://books.zkoss.org/wiki/ZK_Developer's_Reference"));
	}
	
	public List<SidebarPage> getPages(){
		return new ArrayList<SidebarPage>(pageMap.values());
	}
	
	public SidebarPage getPage(String name){
		return pageMap.get(name);
	}
	
}