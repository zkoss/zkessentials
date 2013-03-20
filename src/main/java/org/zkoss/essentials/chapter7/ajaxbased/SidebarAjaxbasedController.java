/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter7.ajaxbased;

import org.zkoss.essentials.services.SidebarPage;
import org.zkoss.essentials.services.SidebarPageConfig;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class SidebarAjaxbasedController extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	@Wire
	Grid fnList;
	
	//wire service
	SidebarPageConfig pageConfig = new SidebarPageConfigAjaxBasedImpl();
	
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

	private Row constructSidebarRow(final String name,String label, String imageSrc, final String locationUri) {
		
		//construct component and hierarchy
		Row row = new Row();
		Image image = new Image(imageSrc);
		Label lab = new Label(label);
		
		row.appendChild(image);
		row.appendChild(lab);
		
		//set style attribute
		row.setSclass("sidebar-fn");
		
		//new and register listener for events
		EventListener<Event> onActionListener = new SerializableEventListener<Event>(){
			private static final long serialVersionUID = 1L;

			public void onEvent(Event event) throws Exception {
				//redirect current url to new location
				if(locationUri.startsWith("http")){
					//open a new browser tab
					Executions.getCurrent().sendRedirect(locationUri);
				}else{
					//use iterable to find the first include only
					Include include = (Include)Selectors.iterable(fnList.getPage(), "#mainInclude")
							.iterator().next();
					include.setSrc(locationUri);
					
					//advance bookmark control, 
					//bookmark with a prefix
					if(name!=null){
						getPage().getDesktop().setBookmark("p_"+name);
					}
				}
			}
		};		
		row.addEventListener(Events.ON_CLICK, onActionListener);

		return row;
	}
	
}
