package org.zkoss.tutorial.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.tutorial.entity.Todo;

@Repository
public class TodoDao {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly=true)
    public List<Todo> queryAll() {
        Query query = em.createQuery("SELECT t FROM Todo t");
        List<Todo> result = query.getResultList();
        return result;
    }
	
	@Transactional(readOnly=true)
    public Todo reload(Todo todo){
        return em.find(Todo.class, todo.getId());
    }
	
	@Transactional(readOnly=true)
    public Todo get(Integer id){
        return em.find(Todo.class, id);
    }
 
    @Transactional
    public Todo save(Todo todo){
        em.persist(todo);
        return todo;
    }
    
    @Transactional
    public Todo update(Todo todo){
        todo = em.merge(todo);
        return todo;
    }
    
    @Transactional
    public void delete(Todo todo){
    	Todo r = get(todo.getId());
    	if(r!=null){
    		em.remove(r);
    	}
    }
}
