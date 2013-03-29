Goal:
	Chpater9 doesn't has new pages. 
	Instated, it provide spring bean implementation of service and use these beans in controller or view-model.  
	* Wire Spring Bean in Controller / @WireVariable
	* Wire Spring Bean in Initiator / @WireVariable with Selectors manually
	
	Following classes are removed and replaced by spring implementation
	
	org/zkoss/essentials/chapter4/SidebarPageConfigChapter4Impl.java
		>> org/zkoss/essentials/services/impl/SidebarPageConfigPagebaseImpl.java
		
	org/zkoss/essentials/chapter5/AuthenticationServiceChapter5Impl.java
		>> org/zkoss/essentials/services/impl/AuthenticationServiceImpl.java
	org/zkoss/essentials/chapter5/UserInfoServiceChapter5Impl.java
		>> org/zkoss/essentials/services/impl/UserInfoServiceImpl.java

	org/zkoss/essentials/chapter6/TodoListServiceChapter6Impl.java
		>> org/zkoss/essentials/services/impl/TodoListServiceImpl.java

	org/zkoss/essentials/chapter7/pagebase/SidebarPageConfigPagebaseImpl.java
		>> org/zkoss/essentials/services/impl/SidebarPageConfigPagebaseImpl.java
	org/zkoss/essentials/chapter7/single/SidebarPageConfigAjaxbasedImpl.java
		>> org/zkoss/essentials/services/impl/SidebarPageConfigAjaxbasedImpl.java
	
	org/zkoss/essentials/chapter8/AuthenticationServiceChapter8Impl.java
		>> org/zkoss/essentials/services/impl/AuthenticationServiceImpl.java
		
	* basic spring configuration
		* maven dependencies
		* applicationContext.xml
		* web.xml
				<listener>
					<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
				</listener>
				<!-- Enable webapp Scopes-->
				 <listener>
			    	<listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
				</listener>
		