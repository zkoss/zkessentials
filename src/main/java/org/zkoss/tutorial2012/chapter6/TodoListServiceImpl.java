package org.zkoss.tutorial2012.chapter6;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.tutorial2012.entity.Priority;
import org.zkoss.tutorial2012.entity.Todo;
import org.zkoss.tutorial2012.services.TodoListService;

public class TodoListServiceImpl implements TodoListService {

	static int todoId = 0;
	static List<Todo> todoList = new ArrayList<Todo>();  
	static{
		todoList.add(new Todo(todoId++,"Buy some milk",Priority.LOW,null,null));
		todoList.add(new Todo(todoId++,"Dennis' birthday gift",Priority.MEDIUM,dayAfter(10),null));
		todoList.add(new Todo(todoId++,"Pay credit-card bill",Priority.HIGH,dayAfter(5),"$1,000"));
		for(int i=0;i<500;i++){
			todoList.add(new Todo(todoId++,"Todo "+i,Priority.HIGH,dayAfter(5),"This is todo "+i));
		}
	}
	
	
	private static Date dayAfter(int d){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, d);
		return c.getTime();
	}
	
	public synchronized List<Todo>getTodoList() {
		List<Todo> list = new ArrayList<Todo>();
		for(Todo todo:todoList){
			list.add(Todo.clone(todo));
		}
		return list;
	}
	
	public synchronized Todo getTodo(Integer id){
		int size = todoList.size();
		for(int i=0;i<size;i++){
			Todo t = todoList.get(i);
			if(t.getId().equals(id)){
				return Todo.clone(t);
			}
		}
		return null;
	}
	
	public synchronized Todo saveTodo(Todo todo){
		todo = Todo.clone(todo);
		todo.setId(todoId++);
		todoList.add(todo);
		return todo;
	}
	
	public synchronized void updateTodo(Todo todo){
		if(todo.getId()==null){
			throw new IllegalArgumentException("cann't save a null-id todo, save it first");
		}else{
			todo = Todo.clone(todo);
			int size = todoList.size();
			for(int i=0;i<size;i++){
				Todo t = todoList.get(i);
				if(t.getId().equals(todo.getId())){
					todoList.set(i, todo);
					break;
				}
			}
		}
	}
	
	public synchronized void deleteTodo(Todo todo){
		if(todo.getId()!=null){
			int size = todoList.size();
			for(int i=0;i<size;i++){
				Todo t = todoList.get(i);
				if(t.getId().equals(todo.getId())){
					todoList.remove(i);
					return;
				}
			}
		}
	}

}
