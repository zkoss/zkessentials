Goal:
	Chpater 9 doesn't has its own pages. 
	Instated, it demonstrates JPA/DAO integration by rewriting previous chapters' code:    

	Following services are refined to use the new DAO
	
	org/zkoss/essentials/services/impl/TodoListServiceImpl.java
		>> org/zkoss/essentials/services/impl/TodoDao.java
		
	org/zkoss/essentials/services/impl/UserInfoServiceImpl.java
		>> org/zkoss/essentials/services/impl/UserDao.java
	
	Basic JPA(Hibernate) confugration
		* maven dependencies
		* persistence.xml
		* applicationContext.xml
		* Entity annotation for Todo & User
		* Dao implementation
	