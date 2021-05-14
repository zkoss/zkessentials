/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter2;

import org.zkoss.essentials.services.SidebarPage;
import org.zkoss.essentials.services.SidebarPageConfig;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
//SelectorComposer 实现ZK的Composer 在MVC中扮演Controller,需要继承org.zkoss.zk.ui.select.SelectorComposer
public class SidebarChapter2Controller extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	//wire components
	/**
	 * @wire 在字段或setter方法上指定注释时，SelectorComposer将自动找到该组件将其分配给该字段或将其传递到setter方法中
	 * 默认情况下SelectorComposer在zul中找到ID和类型都与变量名称和类型匹配的组件
	 * wire还支持连接选择器语法
	 * */
	@Wire
	private Grid sidebar;//SelectorComposer查找Grid其ID为"sidebar"的ID，并将其分配给变量sidebar
	
	//services
	private SidebarPageConfig pageConfig = new SidebarPageConfigChapter2Impl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//initialize view after view construction.
		Rows rows = sidebar.getRows();
		
		for(SidebarPage page:pageConfig.getPages()){
			Row row = constructSidebarRow(page.getLabel(),page.getIconUri(),page.getUri());
			rows.appendChild(row);
		}
	}

	private Row constructSidebarRow(String label, String imageSrc, final String locationUri) {
		
		//construct component and hierarchy
		Row row = new Row();
		Image image = new Image(imageSrc);
		Label lab = new Label(label);
		
		row.appendChild(image);
		row.appendChild(lab);
		
		//set style attribute
		row.setSclass("sidebar-fn");
		
		//create and register event listener
		EventListener<Event> actionListener = new SerializableEventListener<Event>() {
			private static final long serialVersionUID = 1L;

			public void onEvent(Event event) throws Exception {
				//redirect current url to new location
				Executions.getCurrent().sendRedirect(locationUri);
			}
		};
		
		row.addEventListener(Events.ON_CLICK, actionListener);

		return row;
	}
}
