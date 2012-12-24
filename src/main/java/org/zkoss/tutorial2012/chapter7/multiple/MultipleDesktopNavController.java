package org.zkoss.tutorial2012.chapter7.multiple;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class MultipleDesktopNavController extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	@Wire
	Grid fnList;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//to initial view after view constructed.
		Rows rows = fnList.getRows();
		
		Row row = constructFunctionRow("www.zkoss.org","/imgs/site.png","http://www.zkoss.org/");
		rows.appendChild(row);
		
		row = constructFunctionRow("ZK Demo","/imgs/demo.png","http://www.zkoss.org/zkdemo");
		rows.appendChild(row);
		
		row = constructFunctionRow("ZK Developer Reference","/imgs/doc.png","http://books.zkoss.org/wiki/ZK_Developer's_Reference");
		rows.appendChild(row);
		
		row = constructFunctionRow("Profile (MVC)","/imgs/fn.png","/chapter7/multiple/profile-mvc.zul");
		rows.appendChild(row);
		
		row = constructFunctionRow("Profile (MVVM)","/imgs/fn.png","/chapter7/multiple/profile-mvvm.zul");
		rows.appendChild(row);
		
		row = constructFunctionRow("Todo List (MVC)","/imgs/fn.png","/chapter7/multiple/todolist-mvc.zul");
		rows.appendChild(row);
		
		row = constructFunctionRow("Todo List (MVVM)","/imgs/fn.png","/chapter7/multiple/todolist-mvvm.zul");
		rows.appendChild(row);
				
	}

	private Row constructFunctionRow(String label, String imageSrc, final String locationUri) {
		
		//construct component and hierarchy
		Row row = new Row();
		Image image = new Image(imageSrc);
		A a = new A(label);
		
		row.appendChild(image);
		row.appendChild(a);
		
		//set style attribute
		row.setSclass("sidebar-fn");
		
		//new and register listener for events
		EventListener<Event> onActionListener = new EventListener<Event>(){
			public void onEvent(Event event) throws Exception {
				//redirect current url to new location
				Executions.getCurrent().sendRedirect(locationUri);
			}
		};		
		row.addEventListener(Events.ON_CLICK, onActionListener);
		a.addEventListener(Events.ON_CLICK, onActionListener);

		return row;
	}
	
}
