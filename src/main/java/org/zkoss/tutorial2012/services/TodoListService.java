package org.zkoss.tutorial2012.services;

import java.util.List;

import org.zkoss.tutorial2012.entity.Todo;

public interface TodoListService {

	List<Todo> getTodoList();
	Todo getTodo(Integer id);
	Todo saveTodo(Todo todo);
	void updateTodo(Todo todo);
	void deleteTodo(Todo todo);
	
}
