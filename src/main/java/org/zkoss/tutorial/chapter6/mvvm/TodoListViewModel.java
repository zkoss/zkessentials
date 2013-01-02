package org.zkoss.tutorial.chapter6.mvvm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.lang.Strings;
import org.zkoss.tutorial.entity.Priority;
import org.zkoss.tutorial.entity.Todo;
import org.zkoss.tutorial.services.TodoListService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;

@VariableResolver(DelegatingVariableResolver.class)
public class TodoListViewModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//wire service
	@WireVariable
	TodoListService todoListService;
	
	//model for the view
	String subject;
	ListModelList<Todo> todoListModel;
	Todo selectedTodo;
	
	//getter & setter for the binding of the view
	public ListModelList<Todo> getTodoListModel() {
		return todoListModel;
	}
	
	public List<Priority> getPriorityList(){
		return Arrays.asList(Priority.values());
	}

	public Todo getSelectedTodo() {
		return selectedTodo;
	}

	public void setSelectedTodo(Todo selectedTodo) {
		this.selectedTodo = selectedTodo;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
	@Init // @Init marks a initial method
	public void init(){
		//get data from service and wrap it to model for the view
		List<Todo> todoList = todoListService.getTodoList();
		//you can use List directly, however use ListModelList provide efficient control in MVVM 
		todoListModel = new ListModelList<Todo>(todoList);
	}

	@Command //@Command marks a command method 
	@NotifyChange({"selectedTodo","subject"})//@NotifyChange marks data changed notification after calling this method 
	public void addTodo(){
		if(Strings.isBlank(subject)){
			Clients.showNotification("Todo subject is blank, nothing to do ?");
		}else{
			//save data
			selectedTodo = todoListService.saveTodo(new Todo(subject));
			//update the model, by using ListModelList, you don't need to notify todoListModel change
			//it is efficient that only update one item of the listbox
			todoListModel.add(selectedTodo);
			todoListModel.addToSelection(selectedTodo);
			
			//reset value for fast typing.
			subject = null;
		}
	}
	

	@Command 
	//@NotifyChange("selectedTodo")
	public void completeTodo(@BindingParam("todo") Todo todo){
		//save data
		todoListService.updateTodo(todo);
		if(todo.equals(selectedTodo)){
			//for the case that notification is decided dynamically
			//you can use BindUtils.postNotifyChange to notify a value changed
			BindUtils.postNotifyChange(null, null, this, "selectedTodo");
		}
	}
	
	@Command 
	//@NotifyChange("selectedTodo")
	public void deleteTodo(@BindingParam("todo") Todo todo){
		//save data
		todoListService.deleteTodo(todo);
		
		//update the model, by using ListModelList, you don't need to notify todoListModel change
		todoListModel.remove(todo);
		
		if(todo.equals(selectedTodo)){
			//refresh selected todo view
			selectedTodo = null;
			//for the case that notification is decided dynamically
			BindUtils.postNotifyChange(null, null, this, "selectedTodo");
		}
	}
	
	@Command 
	@NotifyChange("selectedTodo")
	public void updateTodo(){
		//save data
		todoListService.updateTodo(selectedTodo);
		
		//update the model, by using ListModelList, you don't need to notify todoListModel change
		//by reseting an item , it make listbox only refresh one item
		todoListModel.set(todoListModel.indexOf(selectedTodo), selectedTodo);
	}
	
	//when user clicks the update button
	@Command @NotifyChange("selectedTodo")
	public void reloadTodo(){
		//do nothing, the selectedTodo will reload by notify change
	}
	
	//the validator to validate data before save to todo
	public Validator getTodoValidator(){
		return new AbstractValidator() {
			
			public void validate(ValidationContext ctx) {
				//the base to save to.
				Todo todo = (Todo)ctx.getProperty().getBase();
				//the properties to apply to base
				Map<String,Property> properties = ctx.getProperties(todo);
				//the value to apply to base.subject
				String subject = (String)properties.get("subject").getValue();
				if(Strings.isBlank(subject)){
					Clients.showNotification("Todo subject is blank, nothing to do ?");
					//mark the validation is invalid, so the data will not update to bean
					//and the further command will be skipped.
					ctx.setInvalid();
				}
			}
		};
	}
	
}
