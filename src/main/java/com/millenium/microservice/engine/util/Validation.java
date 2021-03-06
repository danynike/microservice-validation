package com.millenium.microservice.engine.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Validation implements Serializable{

	private String msg;
	private String id;
	private String name;
	private String path;
	
	public Validation(){
		
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
