/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.entity;

/**
 * Enum for Todo
 */
public enum Priority {
	HIGH(0, "High"), MEDIUM(1, "Medium"), LOW(2, "Low");

	private int priority;
	private String label;

	private Priority(int priority, String label) {
		this.priority = priority;
		this.label = label;
	}

	public int getPriority() {
		return priority;
	}

	public String getLabel() {
		return label;
	}
}