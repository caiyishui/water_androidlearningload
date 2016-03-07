package com.water.learning.bean;

import java.io.Serializable;

public class User implements Serializable{
	public int age;
	public String name;
	public boolean gender;
	public User(String name,int age,boolean gender){
		super();
		this.name =name;
		this.age=age;
		this.gender =gender;
	}
	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + ", gender=" + gender
				+ "]";
	}

}
