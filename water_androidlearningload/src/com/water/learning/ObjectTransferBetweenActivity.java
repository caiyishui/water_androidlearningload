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
	    	
	    	//������������
	    	/**һ������ִ�еĳ����������������������*/
	    	Intent intent = new Intent(this,ObjectTransferBetweenActivityTwo.class);
//	    	//1.���ݲ���
//	    	intent.putExtra("name","jason");
//	    	intent.putExtra("age","16");
	    	
	    	//2.bundle
//	    	/**��������ܶ���Դ����һ��Bundle����֮���ٽ��д���*/
//			Bundle bundle =new Bundle();
//	    	//������װ��Bundle��һ�𴫵�
//	    	bundle.putString("name","water");
//	    	bundle.putInt("age", 24);
//	    	intent.putExtras(bundle);
	    	
	    	//3.Serializable����
	    	User user =new User("water",24,true);
	    	intent.putExtra("user",user);
	    	//Parcelable ���󣨣�
	    	 //4.Parcelable ����AndroidϵͳЧ�ʸ��ߵĽ��������
	    	//�Ƽ�ʹ��Parcelable�ӿ�
	    	//���̼����
	    	/**�ܽ᣺
	    	 * ���A����Ҫ��B���̴���һ��ѧ�����󣬷����һ��
	    	 * 1.ѧ����Ҫʵ��Parcelable�ӿ�
	    	 * 2.��ѧ�������װ��һ��Parcel����
	    	 * 3.ͨ��Ibinder��Parcel���󳡵ظ�B����
	    	 * 4.��B�����У��õ�Parcel���󣬴�Parcel�����ж�ȡ��ѧ��������
	    	*/
	    	Student s =new Student("water",24);
	    	intent.putExtra("student",s);
	    	
	    
	    	
	    	 startActivity(intent);
	    	

	        	

	    }
}
