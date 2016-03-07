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
	/**1.һ��������� ѡӴ��д��Parcel������Ҫʵ��Parcelable�ӿڣ�
	 * 2.Parcel������һ����Ϣ�����Ա�IBinder���з���
	 * 3.IBinder��һ�����ڽ����ڻ����̷��ʵĸ�Ч����
	 * */
	//д��Parcel��
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeInt(this.age);
		
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	//����Ҫ�е����ԣ��̶�д��CREATOR
	 public static final Parcelable.Creator<Student> CREATOR
     = new Parcelable.Creator<Student>() {
		 //��Parcel�ж�ȡ����
		 public Student createFromParcel(Parcel in) {
			 return new Student(in.readString(),in.readInt());
 } 

 public Student[] newArray(int size) {
     return new Student[size];
 }
};


}
