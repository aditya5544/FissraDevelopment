package com.v2stech.fissara.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorData {
	String name;
	String message;
	
	public String getName() {
		return name;
	}
	public ErrorData(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	
}
