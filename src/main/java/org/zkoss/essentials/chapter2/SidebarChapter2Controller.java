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
	//SidebarPageConfig存储了超链接的配置(URL和标签)使用此配置在边栏中创建和设置组件
	private SidebarPageConfig pageConfig = new SidebarPageConfigChapter2Impl();

	/**
	 * 初始化视图/组件，重写composer生命周期方法
	 * Component comp为控制器所使用的组件
	 * */
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);//实现接线组件的初始化
		
		//initialize view after view construction.
		//获取Grid的子组件---row
		Rows rows = sidebar.getRows();
		
		for(SidebarPage page:pageConfig.getPages()){
			Row row = constructSidebarRow(page.getLabel(),page.getIconUri(),page.getUri());
			rows.appendChild(row);
		}
	}

	/**
	 * 创建组件并添加事件侦听器以响应用户的单击的三个步骤：
	 * 1.创建一个组件对象
	 * 2.设置组件的属性
	 * 3.附加到目标父组件
	 * */

	private Row constructSidebarRow(String label, String imageSrc, final String locationUri) {

		/**下面代码等效于
		 * <row sclass="sidebar-fn">
		 *     <image/><label/>
		 * </row>
		 * */
		//construct component and hierarchy
		//创建Row，并添加事件侦听器
		Row row = new Row();
		Image image = new Image(imageSrc);
		Label lab = new Label(label);

		//附加组件，建立父子关系
		row.appendChild(image);
		row.appendChild(lab);
		/**上面代码等效于
		 * <row sclass="sidebar-fn">
		 *     <image/><label/>
		 * </row>
		 * */


		
		//set style attribute
		//通过setter方法更改组件的属性，方法名称与标记的属性名称相对应
		row.setSclass("sidebar-fn");
		
		//create and register event listener
		//事件侦听器类，适用于集群
		EventListener<Event> actionListener = new SerializableEventListener<Event>() {
			private static final long serialVersionUID = 1L;

			//实现业务逻辑，当侦听事件发送到服务器时，ZK调用此方法
			public void onEvent(Event event) throws Exception {
				//redirect current url to new location
				Executions.getCurrent().sendRedirect(locationUri);
			}
		};
		//将事件侦听器添加到由鼠标单击操作触发的row，侦听的onEvent方法事件
		row.addEventListener(Events.ON_CLICK, actionListener);

		return row;
	}
}
