package org.zkoss.tutorial2012.chapter7.pagebase;

import org.zkoss.tutorial2012.services.SidebarPage;
import org.zkoss.tutorial2012.services.SidebarPageConfig;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class SidebarPagebaseController extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	@Wire
	Grid fnList;
	
	//wire service
	SidebarPageConfig pageConfig = new SidebarPageConfigPagebaseImpl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//to initial view after view constructed.
		Rows rows = fnList.getRows();
		
		for(SidebarPage page:pageConfig.getPages()){
			Row row = constructSidebarRow(page.getName(),page.getLabel(),page.getIconUri(),page.getUri());
			rows.appendChild(row);
		}
	}

	private Row constructSidebarRow(String name,String label, String imageSrc, final String locationUri) {
		
		//construct component and hierarchy
		Row row = new Row();
		Image image = new Image(imageSrc);
		Label lab = new Label(label);
		
		row.appendChild(image);
		row.appendChild(lab);
		
		//set style attribute
		row.setSclass("sidebar-fn");
			
		EventListener<Event> actionListener = new EventListener<Event>() {
			public void onEvent(Event event) throws Exception {
				//redirect current url to new location
				Executions.getCurrent().sendRedirect(locationUri);
			}
		};
		
		row.addEventListener(Events.ON_CLICK, actionListener);

		return row;
	}
}
