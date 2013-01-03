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
