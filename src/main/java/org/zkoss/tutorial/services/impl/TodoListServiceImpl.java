package org.zkoss.tutorial.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.zkoss.tutorial.entity.Todo;
import org.zkoss.tutorial.services.TodoListService;

@Service("todoListService")
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class TodoListServiceImpl implements TodoListService {

	@Autowired
	TodoDao dao;
	
	public TodoListServiceImpl(){
		System.out.println(">>>> "+this);
	}
	
	public List<Todo>getTodoList() {
		return dao.queryAll();
	}
	
	public Todo getTodo(Integer id){
		return dao.get(id);
	}
	
	public Todo saveTodo(Todo todo){
		dao.save(todo);
		return todo;
	}
	
	public Todo updateTodo(Todo todo){
		return dao.update(todo);
	}
	
	public void deleteTodo(Todo todo){
		dao.delete(todo);
	}

}
