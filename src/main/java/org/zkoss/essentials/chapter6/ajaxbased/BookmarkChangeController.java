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
import org.zkoss.zuti.zul.*;

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
						//change the URI of shadow element, apply
						Apply apply = (Apply)Selectors.iterable(getPage(), "::shadow#content")
								.iterator().next();
						apply.setTemplateURI(page.getUri());
						apply.recreate();
					}
				}
			}
		});
	}
}
