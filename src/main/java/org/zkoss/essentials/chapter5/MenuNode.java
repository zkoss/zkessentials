package org.zkoss.essentials.chapter5;

import java.util.List;

public class MenuNode {

	private String label;
	private String iconSclass;
	private List<MenuNode> subMenus;
	
	
	public MenuNode(String label, String iconSclass){
		this.label = label;
		this.iconSclass = iconSclass;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getIconSclass() {
		return iconSclass;
	}
	public void setIconSclass(String iconSclass) {
		this.iconSclass = iconSclass;
	}
	public List<MenuNode> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<MenuNode> subMenus) {
		this.subMenus = subMenus;
	}
	
}
