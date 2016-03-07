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
    //����ʱ��callme
    public void callme(View v){
    	//phone call method
    	Intent intent =new Intent();//��ͼ
    	intent.setAction(Intent.ACTION_CALL);//���ò�����ͼ
    	intent.setData(Uri.parse("tel:"+10086));//���ò��ŵ���Ϣ
    	startActivity(intent);//������ͼ	
    }
    public void sendmessage(View v){
    	//phone send message method
    	Intent intent =new Intent();//��ͼ
    	intent.setAction(Intent.ACTION_SENDTO);//���÷�������ͼ
    	intent.setData(Uri.parse("smsto:"+1358752546));//���ò��ŵ���Ϣ
    	//���⣺��ô������11λ�绰����
    	String message="����ˮ�磬������ô";
    	intent.putExtra("sms_body", message);
    	startActivity(intent);//������ͼ	 
    }
    public void phonesister(View v){
    	//phone phone sister method
    	Intent intent =new Intent();//��ͼ
    	intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//����������ͼ
    	startActivity(intent);//������ͼ		
    }

}
