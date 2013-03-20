Goal:
	Chpater10 doesn't has new pages 
	Instated, it provide JPA/DAO implementation, and also refine the spring beans in chapter 9 to access these DAO   

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
	