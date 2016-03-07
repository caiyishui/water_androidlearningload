package com.water.learning;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class IntentSystemStoreActivity extends Activity{
	private static final int CAMREA_RESQUSET = 0;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentsystemstore);
    }
    //监听时间callme
    public void callme(View v){
    	//phone call method
    	Intent intent =new Intent();//意图
    	intent.setAction(Intent.ACTION_CALL);//设置拨号意图
    	intent.setData(Uri.parse("tel:"+10086));//设置拨号的信息
    	startActivity(intent);//开启意图	
    }
    public void sendmessage(View v){
    	//phone send message method
    	Intent intent =new Intent();//意图
    	intent.setAction(Intent.ACTION_SENDTO);//设置发短信意图
    	intent.setData(Uri.parse("smsto:"+1358752546));//设置拨号的信息
    	//问题：怎么不能用11位电话号码
    	String message="我是水哥，有妹子么";
    	intent.putExtra("sms_body", message);
    	startActivity(intent);//开启意图	 
    }
    public void phonesister(View v){
    	//phone phone sister method
    	Intent intent =new Intent();//意图
    	intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置拍照意图
    	startActivity(intent);//开启意图		
    }

}
