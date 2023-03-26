package com.taskPlanner.Beans;

public enum Status {

	NOT_START("not_start"),
	WORK_IN_PROGRESS("work_in_progress"),
	COMPLETED("completed");
	
	private final String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
