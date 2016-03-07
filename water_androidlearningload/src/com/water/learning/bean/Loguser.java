package com.water.learning.bean;

import java.io.Serializable;

public class Loguser implements Serializable{
	public String name;
	public String pswd;
	@Override
	public String toString() {
		return "ÓÃ»§Ãû [name=" + name + ", ÃÜÂë=" + pswd + "]";
	}
	public Loguser(String name, String pswd) {
		super();
		this.name = name;
		this.pswd = pswd;
	}
}
