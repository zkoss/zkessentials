package org.zkoss.tutorial2012.chapter4;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

public class SidebarRendererController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	@Wire
	Grid fnList;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//to initial view after view constructed.
		
		ListModelList<Site> model = new ListModelList<Site>();
		
		model.add(new Site("www.zkoss.org","/imgs/site.png","http://www.zkoss.org/"));
		model.add(new Site("ZK Demo","/imgs/demo.png","http://www.zkoss.org/zkdemo"));
		model.add(new Site("ZK Developer Reference","/imgs/doc.png","http://books.zkoss.org/wiki/ZK_Developer's_Reference"));
		model.add(new Site("Sidebar","/imgs/fn.png","index.zul"));
		model.add(new Site("Sidebar Manipulator","/imgs/fn.png","index-manipulator.zul"));
		
		
		//set model
		fnList.setModel(model);
		//set the renderer to render Site
		fnList.setRowRenderer(new SiteRowRenderer());
				
	}
	
	public static class SiteRowRenderer implements RowRenderer<Site>{
		public void render(Row row, final Site site, int index) throws Exception {
			Image image = new Image(site.getImageSrc());
			A a = new A(site.getLabel());
			
			row.appendChild(image);
			row.appendChild(a);
			
			//set style attribute
			row.setSclass("sidebar-fn");
			
			//new and register event listener
			EventListener<Event> siteActionListener = new SiteActionListener(site);		
			row.addEventListener(Events.ON_CLICK, siteActionListener);
			a.addEventListener(Events.ON_CLICK, siteActionListener);
		}
	}
	
	//Event Listener to handle action on row
	public static class SiteActionListener implements EventListener<Event>{
		Site site;
		public SiteActionListener(Site site){
			this.site = site;
		}
		public void onEvent(Event event) throws Exception {
			//redirect current url to new location
			Executions.getCurrent().sendRedirect(site.getLocationUri());
		}
	}
	
	//A POJO hold UI information
	public static class Site {
		String label;
		String imageSrc;
		String locationUri;
		
		public Site(String label, String imageSrc, String locationUri) {
			this.label = label;
			this.imageSrc = imageSrc;
			this.locationUri = locationUri;
		}
		public String getLabel() {
			return label;
		}
		public String getImageSrc() {
			return imageSrc;
		}
		public String getLocationUri() {
			return locationUri;
		}
	}
	
}
