package com.water.learning;

import com.water.learning.bean.Loguser;
import com.water.learning.bean.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private EditText et_name;
	private EditText et_pswd;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        et_name=(EditText) findViewById(R.id.et_usnm);
        et_pswd= (EditText) findViewById(R.id.et_pswd);
    }
    public void login(View btn){
    	String name = et_name.getText().toString();
    	String pswd = et_pswd.getText().toString();
    	if(name.trim().length()>0&&pswd.trim().length()>0)
    	//����ɹ�
    	{Loguser user =new Loguser(name,pswd);
    	Intent intent =new Intent();
    	intent.putExtra("user", user);
    	//���û��ش���������
    	setResult(Activity.RESULT_OK, intent);}
    	else{
    		//����ʧ��
    		setResult(Activity.RESULT_CANCELED);
    	}
    	//�ɵ��Լ��ص�������
    	finish();
    }
}