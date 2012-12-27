package org.zkoss.tutorial2012.chapter7.single;

import org.zkoss.tutorial2012.chapter7.single.BookmarkableFunctions.BookmarkableFunction;
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

public class SingleDesktopNavController extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	@Wire
	Grid fnList;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//to initial view after view constructed.
		Rows rows = fnList.getRows();
		
		Row row = constructFunctionRow(null,"www.zkoss.org","/imgs/site.png","http://www.zkoss.org/");
		rows.appendChild(row);
		
		row = constructFunctionRow(null,"ZK Demo","/imgs/demo.png","http://www.zkoss.org/zkdemo");
		rows.appendChild(row);
		
		row = constructFunctionRow(null,"ZK Developer Reference","/imgs/doc.png","http://books.zkoss.org/wiki/ZK_Developer's_Reference");
		rows.appendChild(row);
		
		for(BookmarkableFunction fn:BookmarkableFunctions.getFunctions()){
			row = constructFunctionRow(fn.getBookmark(),fn.getLabel(),fn.getIconUri(),fn.getUri());
			rows.appendChild(row);
		}		
	}

	private Row constructFunctionRow(final String bookmark,String label, String imageSrc, final String locationUri) {
		
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
				if(locationUri.startsWith("http")){
					//open a new browser tab
					Executions.getCurrent().sendRedirect(locationUri);
				}else{
					Include include = (Include)Selectors.iterable(fnList.getPage(), "#mainContent include").iterator().next();
					include.setSrc(locationUri);
					
					//bookmark with a prefix
					if(bookmark!=null){
						getPage().getDesktop().setBookmark("fn_"+bookmark);
					}
				}
			}
		};		
		row.addEventListener(Events.ON_CLICK, onActionListener);
		a.addEventListener(Events.ON_CLICK, onActionListener);

		return row;
	}
	
}
