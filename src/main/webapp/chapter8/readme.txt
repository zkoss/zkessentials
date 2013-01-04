Goal:
	* Session concept
	* The basic authentication in ZK
		* <?init class="org.zkoss.tutorial.chapter8.AuthenticationInit"?>
		* sessionScope.userCredential object in session
		* index.zul(template.zul) v.s. login.zul(template-anonymous.zul)
			* template.zul / if="${not sessionScope.userCredential.anonymous}"/if="${sessionScope.userCredential.anonymous}"
			* template-anonymous.zul without sidebar
		 	
	* login / banner / logout 
	
	* [TODO] the basic authorization in ZK