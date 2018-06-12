/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter6.ajaxbased;

import org.zkoss.essentials.services.*;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.select.*;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.*;
import org.zkoss.zuti.zul.Apply;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SidebarAjaxbasedController extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	@Wire
	Grid sidebar;
	
	//wire service
	@WireVariable("sidebarPageConfigAjaxbased")
	SidebarPageConfig pageConfig;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//to initial view after view constructed.
		Rows rows = sidebar.getRows();
		
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
					//change the URI of shadow element, apply
					Apply apply = (Apply)Selectors.iterable(event.getPage(), "::shadow#content")
							.iterator().next();
					apply.setTemplateURI(locationUri);
					apply.recreate();
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
