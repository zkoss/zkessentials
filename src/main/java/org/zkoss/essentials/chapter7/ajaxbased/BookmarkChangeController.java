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
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Include;

public class BookmarkChangeController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	
	SidebarPageConfig pageConfig = new SidebarPageConfigAjaxBasedImpl();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		if (comp.getParent() != null) {
			throw new RuntimeException("A bookmark change listener can only apply on the root comp");
		}

		comp.addEventListener("onBookmarkChange", new SerializableEventListener<BookmarkEvent>() {
			private static final long serialVersionUID = 1L;

			public void onEvent(BookmarkEvent event) throws Exception {
				String bookmark = event.getBookmark();
				if(bookmark.startsWith("p_")){
					String p = bookmark.substring("p_".length());
					SidebarPage page = pageConfig.getPage(p);
					
					if(page!=null){
						//use iterable to find the first include only
						Include include = (Include)Selectors.iterable(getPage(), "#mainInclude").iterator().next();
						include.setSrc(page.getUri());
					}
				}
			}
		});
	}
}
