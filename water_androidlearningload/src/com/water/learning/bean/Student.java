package com.water.learning.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable{
	public String name ;
	public int age;
	
	
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**1.一个对象如果 选哟被写入Parcel对象，需要实现Parcelable接口；
	 * 2.Parcel对象是一个消息，可以被IBinder进行发送
	 * 3.IBinder是一个用于进程内或跨进程访问的高效机制
	 * */
	//写入Parcel中
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeInt(this.age);
		
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	//必须要有的属性，固定写法CREATOR
	 public static final Parcelable.Creator<Student> CREATOR
     = new Parcelable.Creator<Student>() {
		 //从Parcel中读取出来
		 public Student createFromParcel(Parcel in) {
			 return new Student(in.readString(),in.readInt());
 } 

 public Student[] newArray(int size) {
     return new Student[size];
 }
};


}
