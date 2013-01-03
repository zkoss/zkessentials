Goal:
	Chpater9 doesn't has new pages. 
	Instated, it provide spring bean implementation of service and use these beans in controller or view-model.  
	* Wire Spring Bean in Controller / @WireVariable
	* Wire Spring Bean in Initiator / @WireVariable with Selectors manually
	* Use SpringUtil in VariableResolver / SpringUtil.getBean(name)
	
	Following classes are removed and replaced by spring implementation
	
	org/zkoss/tutorial/chapter4/SidebarPageConfigChapter4Impl.java
		>> org/zkoss/tutorial/services/impl/SidebarPageConfigChapter4Impl.java
		
	org/zkoss/tutorial/chapter5/AuthenticationServiceChapter5Impl.java
		>> org/zkoss/tutorial/services/impl/AuthenticationServiceImpl.java
	org/zkoss/tutorial/chapter5/UserInfoServiceChapter5Impl.java
		>> org/zkoss/tutorial/services/impl/UserInfoServiceImpl.java

	org/zkoss/tutorial/chapter6/TodoListServiceChapter6Impl.java
		>> org/zkoss/tutorial/services/impl/TodoListServiceImpl.java

	org/zkoss/tutorial/chapter7/pagebase/SidebarPageConfigPagebaseImpl.java
		>> org/zkoss/tutorial/services/impl/SidebarPageConfigPagebaseImpl.java
	org/zkoss/tutorial/chapter7/single/SidebarPageConfigSingleDesktopImpl.java
		>> org/zkoss/tutorial/services/impl/SidebarPageConfigSingleDesktopImpl.java
	
	org/zkoss/tutorial/chapter8/AuthenticationServiceChapter8Impl.java
		>> org/zkoss/tutorial/services/impl/AuthenticationServiceImpl.java