package org.zkoss.essentials.chapter7.template;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.SmartNotifyChange;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkmax.zul.Navitem;

public class MenuViewModel {

	private List<MenuNode> menuHierarchy = null;
	private List<MenuNode> menuHierarchy1 = new LinkedList<MenuNode>();
	private List<MenuNode> menuHierarchy2 = new LinkedList<MenuNode>();
	private String currentPage;
	
	@Init
	public void init(){
		//initialize menu
		initMenuHierachy1();
		initMenuHierachy2();
		menuHierarchy = menuHierarchy1;
	}


	private void initMenuHierachy1() {
		List<MenuNode> menuitems1 = new LinkedList<MenuNode>();
		menuitems1.add(new MenuNode("About Us", null));
		menuitems1.add(new MenuNode("Menu", null));
		menuitems1.add(new MenuNode("FAQ",null));
		menuitems1.add(new MenuNode("Press", null));
		MenuNode menuNode1 = new MenuNode("ZK Pizza", "z-icon-home");
		menuNode1.setSubMenus(menuitems1);
		menuHierarchy1.add(menuNode1);
		
		List<MenuNode> menuitems2 = new LinkedList<MenuNode>();
		menuitems2.add(new MenuNode("Active Members", null));
		menuitems2.add(new MenuNode("Inactive Members", null));
		MenuNode menuNode2 = new MenuNode("Customers", "z-icon-group");
		menuNode2.setSubMenus(menuitems2);
		menuHierarchy1.add(menuNode2);
		
		List<MenuNode> menuitems3 = new LinkedList<MenuNode>();
		menuitems3.add(new MenuNode("In Preparation", null));
		menuitems3.add(new MenuNode("Ready for Shipping", null));
		menuitems3.add(new MenuNode("Shipping", null));
		menuitems3.add(new MenuNode("Specified for Later", null));
		MenuNode menuNode3 = new MenuNode("Orders", "z-icon-truck");
		menuNode3.setSubMenus(menuitems3);
		menuHierarchy1.add(menuNode3);
		
		List<MenuNode> menuitems4 = new LinkedList<MenuNode>();
		menuitems4.add(new MenuNode("Events",null));
		menuitems4.add(new MenuNode("Promotion", null));
		MenuNode menuNode4 = new MenuNode("Fan Service", "z-icon-star");
		menuNode4.setSubMenus(menuitems3);
		menuHierarchy1.add(menuNode4);
		
		MenuNode menuNode5 = new MenuNode("Misc.", "z-icon-star");
		menuHierarchy1.add(menuNode5);
	}
	
	private void initMenuHierachy2() {
		List<MenuNode> menuitems1 = new LinkedList<MenuNode>();
		menuitems1.add(new MenuNode("Cloud", null));
		menuitems1.add(new MenuNode("Mobile", null));
		menuitems1.add(new MenuNode("Application",null));
		MenuNode menuNode1 = new MenuNode("Products", "z-icon-leaf");
		menuNode1.setSubMenus(menuitems1);
		menuHierarchy2.add(menuNode1);
		
		List<MenuNode> menuitems2 = new LinkedList<MenuNode>();
		menuitems2.add(new MenuNode("Business", null));
		menuitems2.add(new MenuNode("Industry", null));
		menuitems2.add(new MenuNode("Customer Experience", null));
		menuitems2.add(new MenuNode("Parnter", null));
		MenuNode menuNode2 = new MenuNode("Solutions", "z-icon-star");
		menuNode2.setSubMenus(menuitems2);
		menuHierarchy2.add(menuNode2);
		
		List<MenuNode> menuitems3 = new LinkedList<MenuNode>();
		menuitems3.add(new MenuNode("Database", null));
		menuitems3.add(new MenuNode("Middleware", null));
		menuitems3.add(new MenuNode("Enterprise", null));
		menuitems3.add(new MenuNode("Developer Tools", null));
		MenuNode menuNode3 = new MenuNode("Downloads", "z-icon-download");
		menuNode3.setSubMenus(menuitems3);
		menuHierarchy2.add(menuNode3);
		
		List<MenuNode> menuitems4 = new LinkedList<MenuNode>();
		menuitems4.add(new MenuNode("Server",null));
		menuitems4.add(new MenuNode("Desktop", null));
		MenuNode menuNode4 = new MenuNode("Sotre", "z-icon-shopping-cart");
		menuNode4.setSubMenus(menuitems3);
		menuHierarchy2.add(menuNode4);

		List<MenuNode> menuitems5 = new LinkedList<MenuNode>();
		menuitems5.add(new MenuNode("Support Service",null));
		menuitems5.add(new MenuNode("Partner",null));
		MenuNode menuNode5 = new MenuNode("Support", "z-icon-group");
		menuNode5.setSubMenus(menuitems5);
		menuHierarchy2.add(menuNode5);
		
	}
	
	@Command @SmartNotifyChange("currentPage")
	public void navigate(@ContextParam(ContextType.TRIGGER_EVENT) SelectEvent<Navitem, Object> event){
		currentPage = event.getSelectedItems().iterator().next().getLabel();
	}
	
	@Command @SmartNotifyChange("menuHierarchy")
	public void switchMenu(@BindingParam("option")int option){
		if (option == 1){
			menuHierarchy = menuHierarchy1;
		}else{
			menuHierarchy = menuHierarchy2;
		}
	}
	
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}


	public String getCurrentPage() {
		return currentPage;
	}


	public List<MenuNode> getMenuHierarchy() {
		return menuHierarchy;
	}


	public void setMenuHierarchy(List<MenuNode> menuHierarchy) {
		this.menuHierarchy = menuHierarchy;
	}
	
}
