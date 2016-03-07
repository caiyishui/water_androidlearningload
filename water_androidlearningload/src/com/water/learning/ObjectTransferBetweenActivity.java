package com.water.learning;



import com.water.learning.bean.Student;
import com.water.learning.bean.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ObjectTransferBetweenActivity extends Activity{
	 private Button jump;
	   
		/** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.objecttransferbetweenactivity);
	       
	    }
	    public void jump(View jump){
	    	
	    	//基本参数传递
	    	/**一个即将执行的抽象操作的描述，可以用来*/
	    	Intent intent = new Intent(this,ObjectTransferBetweenActivityTwo.class);
//	    	//1.传递参数
//	    	intent.putExtra("name","jason");
//	    	intent.putExtra("age","16");
	    	
	    	//2.bundle
//	    	/**如果参数很多可以打包成一个Bundle对象之后再进行传递*/
//			Bundle bundle =new Bundle();
//	    	//将参数装进Bundle，一起传递
//	    	bundle.putString("name","water");
//	    	bundle.putInt("age", 24);
//	    	intent.putExtras(bundle);
	    	
	    	//3.Serializable对象
	    	User user =new User("water",24,true);
	    	intent.putExtra("user",user);
	    	//Parcelable 对象（）
	    	 //4.Parcelable 对象（Android系统效率更高的解决方案）
	    	//推荐使用Parcelable接口
	    	//进程间访问
	    	/**总结：
	    	 * 如果A进程要给B进程传递一个学生对象，发快递一样
	    	 * 1.学生类要实现Parcelable接口
	    	 * 2.将学生对象包装成一个Parcel对象
	    	 * 3.通过Ibinder将Parcel对象场地给B进程
	    	 * 4.在B进程中，拿到Parcel对象，从Parcel对象中读取到学生对象中
	    	*/
	    	Student s =new Student("water",24);
	    	intent.putExtra("student",s);
	    	
	    
	    	
	    	 startActivity(intent);
	    	

	        	

	    }
}
