package com.water.learning;



import com.water.learning.bean.Student;
import com.water.learning.bean.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ObjectTransferBetweenActivityTwo extends Activity{
	private Bundle bundle;
	private User user;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.objecttransferbetweenactivitytwo);
        	getData();
 
    }
	private void getData() {
		
		//��ҽ;�л�ȡ���ݵĲ���
        Intent intent = getIntent();
        //1.������������
//        String name =intent.getStringExtra("name");
//        int age =intent.getIntExtra("age", 16);
//        Log.i("INFO","name"+name+"age"+age);

        // 2.ʹ��Bundle��������
//        bundle=intent.getExtras();
//        String name= bundle.getString("name");
//        int age =bundle.getInt("age");
//        Log.i("INFO","name:"+name+"age:"+age+"****************");

        //3.Serializable����        
//        user =(User) intent.getSerializableExtra("user");
      
//        Log.i("INFO",user.toString());
        //Parcelable ���󣨣�
        Student student =intent.getParcelableExtra("student");
        Log.i("INFO",student.toString()+"Parcel");
        Toast.makeText(this, student.toString()+"Parcel", 2000).show();
        
        
	}

}
