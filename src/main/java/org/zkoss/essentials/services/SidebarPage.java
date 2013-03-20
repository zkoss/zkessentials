/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.services;

import java.io.Serializable;

public class SidebarPage implements Serializable{
	private static final long serialVersionUID = 1L;
	String name;
	String label;
	String iconUri;
	String uri;

	public SidebarPage(String name, String label, String iconUri, String uri) {
		this.name = name;
		this.label = label;
		this.iconUri = iconUri;
		this.uri = uri;
	}

	public String getName() {
		return name;
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