/* 
	Description:
		ZK Tutorial
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.tutorial.services;

import java.util.List;

import org.zkoss.tutorial.entity.Todo;

public interface TodoListService {

	List<Todo> getTodoList();
	Todo getTodo(Integer id);
	Todo saveTodo(Todo todo);
	Todo updateTodo(Todo todo);
	void deleteTodo(Todo todo);
	
}
