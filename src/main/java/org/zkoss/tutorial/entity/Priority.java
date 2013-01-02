package org.zkoss.tutorial.entity;

/**
 * Enum for Todo
 */
public enum Priority {
	HIGH(0, "High"), MEDIUM(1, "Medium"), LOW(3, "Low");

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