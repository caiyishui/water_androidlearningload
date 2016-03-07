package com.water.learning;

import com.water.learning.bean.Loguser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegActivity extends Activity {
	private EditText et_name;
	private EditText et_pswd;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        et_name=(EditText) findViewById(R.id.et_usnm);
        et_pswd= (EditText) findViewById(R.id.et_pswd);
    }
    public void login(View btn){
    	String name = et_name.getText().toString();
    	String pswd = et_pswd.getText().toString();
    	if(name.trim().length()>0&&pswd.trim().length()>0)
    	//注册成功
    	{Loguser user =new Loguser(name,pswd);
    	Intent intent =new Intent();
    	intent.putExtra("user", user);
    	//将用户回传给主界面
    	setResult(Activity.RESULT_OK, intent);}
    	else{
    		//注册失败
    		setResult(Activity.RESULT_CANCELED);
    	}
    	//干掉自己回到主界面
    	finish();
    	
    }
}