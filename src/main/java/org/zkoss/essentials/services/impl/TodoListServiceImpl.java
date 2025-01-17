/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zkoss.essentials.entity.Todo;
import org.zkoss.essentials.services.TodoListService;

import java.util.List;

@Service("todoListService")
@Scope
public class TodoListServiceImpl implements TodoListService {

	@Autowired
	TodoDao dao;
	
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
