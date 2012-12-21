package org.zkoss.tutorial2012.chapter6.mvc;

import java.util.List;

import org.zkoss.tutorial2012.chapter6.TodoListServiceImpl;
import org.zkoss.tutorial2012.entity.Todo;
import org.zkoss.tutorial2012.services.TodoListService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

public class TodoListController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;

	//wire component
	@Wire
	Checkbox showAll;
	@Wire
	Textbox todoSubject;
	@Wire
	Button addTodo;
	@Wire
	Listbox todoListbox;
	
	@Wire
	Component selectedTodoBlock;
	@Wire
	Checkbox selectedTodoCheck;
	@Wire
	Textbox selectedTodoSubject;
	@Wire
	Datebox selectedTodoDate;
	@Wire
	Textbox selectedTodoDescription;
	@Wire
	Button updateSelectedTodo;
	
	//model
	ListModelList<Todo> todoListModel;
	
	
	//wire service
	TodoListService todoListService = new TodoListServiceImpl();
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		List<Todo> todoList = todoListService.getTodoList(showAll.isChecked());
		todoListModel = new ListModelList<Todo>(todoList);
		todoListbox.setModel(todoListModel);
	}
	
	@Listen("onTodoDelete = #todoListbox")
	public void doTodoDelete(ForwardEvent evt){
		Todo todo = (Todo)evt.getData();
		
	}
	@Listen("onTodoCheck = #todoListbox")
	public void doTodoCheck(ForwardEvent evt){
		Object[] param = (Object[])evt.getData();
		Todo todo = (Todo)param[0];
		boolean checked = ((Checkbox)param[1]).isChecked();
		
		todo.setComplete(checked);
		todoListService.updateTodo(todo);
		
		if(checked && !showAll.isChecked()){
			todoListModel.remove(todo);
		}
	}
	
	@Listen("onCheck = #showAll")
	public void doShowAllCheck(){
		List<Todo> todoList = todoListService.getTodoList(showAll.isChecked());
		todoListModel = new ListModelList<Todo>(todoList);
		todoListbox.setModel(todoListModel);
	}
	
}
