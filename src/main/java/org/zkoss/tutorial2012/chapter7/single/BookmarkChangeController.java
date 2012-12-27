package org.zkoss.tutorial2012.chapter7.single;

import org.zkoss.tutorial2012.chapter7.single.BookmarkableFunctions.BookmarkableFunction;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;

public class BookmarkChangeController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		if (comp.getParent() != null) {
			throw new RuntimeException("A bookmark change listener can only apply on root comp");
		}

		comp.addEventListener("onBookmarkChange", new EventListener<BookmarkEvent>() {
			public void onEvent(BookmarkEvent event) throws Exception {
				String bookmark = event.getBookmark();
				if(bookmark.startsWith("fn_")){
					String fn = bookmark.substring(3);
					BookmarkableFunction sf = BookmarkableFunctions.getFunction(fn);
					
					if(sf!=null){
						Include include = (Include)Selectors.iterable(getPage(), "#mainContent include").iterator().next();
						include.setSrc(sf.getUri());
					}
				}
			}
		});
	}
}
