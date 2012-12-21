package org.zkoss.tutorial2012.services;

import java.util.List;

import org.zkoss.tutorial2012.entity.Todo;

public interface TodoListService {

	List<Todo> getTodoList(boolean all);
	public Todo getTodo(Integer id);
	public void updateTodo(Todo todo);
}
