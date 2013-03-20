package org.zkoss.essentials.services.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.essentials.entity.User;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
    public User get(String account){
        return em.find(User.class, account);
    }
 
    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }
    
    @Transactional
    public User update(User user){
    	user = em.merge(user);
        return user;
    }
}
