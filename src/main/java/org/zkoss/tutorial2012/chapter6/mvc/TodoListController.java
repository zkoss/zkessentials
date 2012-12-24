package org.zkoss.tutorial2012.chapter6.mvc;

import java.util.List;

import org.zkoss.lang.Strings;
import org.zkoss.tutorial2012.chapter6.TodoListServiceImpl;
import org.zkoss.tutorial2012.entity.Priority;
import org.zkoss.tutorial2012.entity.Todo;
import org.zkoss.tutorial2012.services.TodoListService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

public class TodoListController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;

	//wire component
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
	Radiogroup selectedTodoPriority;
	@Wire
	Datebox selectedTodoDate;
	@Wire
	Textbox selectedTodoDescription;
	@Wire
	Button updateSelectedTodo;
	
	//wire service
	TodoListService todoListService = new TodoListServiceImpl();
	
	//model for the view
	ListModelList<Todo> todoListModel;
	ListModelList<Priority> priorityListModel;
	Todo selectedTodo;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//get data from service and wrap it to model for the view
		List<Todo> todoList = todoListService.getTodoList();
		todoListModel = new ListModelList<Todo>(todoList);
		todoListbox.setModel(todoListModel);
		
		priorityListModel = new ListModelList<Priority>(Priority.values());
		selectedTodoPriority.setModel(priorityListModel);
	}
	
	//when user clicks on the button or enter on the textbox
	@Listen("onClick = #addTodo; onOK = #todoSubject")
	public void doTodoAdd(){
		//get user input from view
		String subject = todoSubject.getValue();
		if(Strings.isBlank(subject)){
			Clients.showNotification("Nothing to do ?","info",todoSubject,"after_start",10,true);
		}else{
			//save data
			selectedTodo = todoListService.saveTodo(new Todo(subject));
			//update the model of listbox
			todoListModel.add(selectedTodo);
			todoListModel.addToSelection(selectedTodo);
			
			//refresh detail view
			updateDetailView();
			
			//reset value for fast typing.
			todoSubject.setValue("");
		}
	}
	
	//when user checks on the checkbox of each todo on the list
	@Listen("onTodoCheck = #todoListbox")
	public void doTodoCheck(ForwardEvent evt){
		//get data from event
		Object[] param = (Object[])evt.getData();
		Todo todo = (Todo)param[0];
		Checkbox cbox = ((Checkbox)param[1]);
		boolean checked = cbox.isChecked();
		todo.setComplete(checked);
		
		//save data
		todoListService.updateTodo(todo);
		if(todo.equals(selectedTodo)){
			//refresh detail view
			updateDetailView();
		}
		//update listitem style
		((Listitem)cbox.getParent().getParent()).setSclass(checked?"complete-todo":"");
	}
	
	//when user clicks the delete button of each todo on the list
	@Listen("onTodoDelete = #todoListbox")
	public void doTodoDelete(ForwardEvent evt){
		Todo todo = (Todo)evt.getData();
		
		//save data
		todoListService.deleteTodo(todo);
		
		//update the model of listbox
		todoListModel.remove(todo);
		
		if(todo.equals(selectedTodo)){
			//refresh selected todo view
			selectedTodo = null;
			updateDetailView();
		}
	}

	//when user selects a todo of the listbox
	@Listen("onSelect = #todoListbox")
	public void doTodoSelect() {
		if(todoListModel.isSelectionEmpty()){
			//just in case for the no selection
			selectedTodo = null;
		}else{
			selectedTodo = todoListModel.getSelection().iterator().next();
		}
		updateDetailView();
	}
	
	private void updateDetailView() {
		//update the detail view of selected todo
		if(selectedTodo==null){
			//clean
			selectedTodoBlock.setVisible(false);
			selectedTodoCheck.setChecked(false);
			selectedTodoSubject.setValue(null);
			selectedTodoDate.setValue(null);
			selectedTodoDescription.setValue(null);
			updateSelectedTodo.setDisabled(true);
			
			priorityListModel.clearSelection();
		}else{
			selectedTodoBlock.setVisible(true);
			selectedTodoCheck.setChecked(selectedTodo.isComplete());
			selectedTodoSubject.setValue(selectedTodo.getSubject());
			selectedTodoDate.setValue(selectedTodo.getDate());
			selectedTodoDescription.setValue(selectedTodo.getDescription());
			updateSelectedTodo.setDisabled(false);
			
			priorityListModel.addToSelection(selectedTodo.getPriority());
		}
	}
	
	//when user clicks the update button
	@Listen("onClick = #updateSelectedTodo")
	public void doUpdateClick(){
		if(Strings.isBlank(selectedTodoSubject.getValue())){
			Clients.showNotification("Nothing to do ?",selectedTodoSubject);
			return;
		}
		
		selectedTodo.setComplete(selectedTodoCheck.isChecked());
		selectedTodo.setSubject(selectedTodoSubject.getValue());
		selectedTodo.setDate(selectedTodoDate.getValue());
		selectedTodo.setDescription(selectedTodoDescription.getValue());
		selectedTodo.setPriority(priorityListModel.getSelection().iterator().next());
		
		//save data
		todoListService.updateTodo(selectedTodo);
		
		//refresh listmodel for only 1 item
		todoListModel.set(todoListModel.indexOf(selectedTodo), selectedTodo);
		
		//show message for user
		Clients.showNotification("Todo saved");
	}
	
	//when user clicks the update button
	@Listen("onClick = #reloadSelectedTodo")
	public void doReloadClick(){
		updateDetailView();
	}
}
