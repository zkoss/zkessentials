package org.zkoss.tutorial2012.chapter7.single;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class BookmarkableFunctions {

	static HashMap<String,BookmarkableFunction> functionMap = new LinkedHashMap<String,BookmarkableFunction>();
	static{
		functionMap.put("fn1",new BookmarkableFunction("fn1","Profile (MVC)","/imgs/fn.png","/chapter5/profile-mvc.zul"));
		functionMap.put("fn2",new BookmarkableFunction("fn2","Profile (MVVM)","/imgs/fn.png","/chapter5/profile-mvvm.zul"));
		functionMap.put("fn3",new BookmarkableFunction("fn3","Todo List (MVC)","/imgs/fn.png","/chapter6/todolist-mvc.zul"));
		functionMap.put("fn4",new BookmarkableFunction("fn4","Todo List (MVVM)","/imgs/fn.png","/chapter6/todolist-mvvm.zul"));
	}
	
	public static List<BookmarkableFunction> getFunctions(){
		return new ArrayList<BookmarkableFunction>(functionMap.values());
	}
	
	public static BookmarkableFunction getFunction(String bookmark){
		return functionMap.get(bookmark);
	}
	
	static public class BookmarkableFunction{
		String bookmark;
		String label;
		String iconUri;
		String uri;

		public BookmarkableFunction(String bookmark,String label, String iconUri, String uri) {
			this.bookmark = bookmark;
			this.label = label;
			this.iconUri = iconUri;
			this.uri = uri;
		}
		public String getBookmark(){
			return bookmark;
		}
		public String getLabel() {
			return label;
		}
		public String getIconUri() {
			return iconUri;
		}
		public String getUri() {
			return uri;
		}
	}
}
