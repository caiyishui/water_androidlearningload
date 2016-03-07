package com.water.learning;

import java.util.HashMap;
import java.util.Map;

import com.water.learning.httputils.MyHttpUtils;
import com.water.learning.listener.MyHttpCallback;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HttpConnectionActivity extends Activity implements MyHttpCallback{
	 final String VITUAL_IP = "10.0.2.2:8080/";
	 final String REAL_IP = "180.97.130.37:8080/";
	 final String HOST = "http://"+VITUAL_IP;
	 final String LOGIN_PATH = HOST+"web_tz_server/MyAndroidServlet";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}
	public void login(View v){
    	Map<String,String> data = new HashMap<String, String>();
    	data.put("userName", "Danny");
    	data.put("password", "123");
    	MyHttpUtils httpUtils = new MyHttpUtils(LOGIN_PATH, data, "POST",this);
    	httpUtils.doRequestByHttpUrlConnection();
    }

	public void onSuccess(String result) {
		// TODO Auto-generated method stub
		Log.i("INFO", "callback success,result:"+result);
		//”√handler
	}

	public void onFailure(String result) {
		// TODO Auto-generated method stub
		Log.i("INFO", "callback fail,result:"+result);
		//”√handler
	}
    
    

}
