package com.water.learning;



import com.water.learning.bean.Loguser;
import com.water.learning.bean.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginAndRegActivity extends Activity {
	public static final int REQUEST_CODE_lOGIN=1;
	public static final int REQUEST_CODE_REG=2;
	private TextView tv_info;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginandregactivity);
        tv_info= (TextView) findViewById(R.id.tv_info);
    }
    //回来之后要执行的方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	//根据requestCode
    	switch (requestCode) {
		case REQUEST_CODE_lOGIN:
			//登入成功获取数据
			if(resultCode==Activity.RESULT_OK){
				Loguser user= (Loguser) data.getSerializableExtra("user");
				tv_info.setText("登入成功\t\n"+user.toString());
			}else{
				tv_info.setText("登入失败");
			}
			
			//登入失败
			
			break;

		case REQUEST_CODE_REG:
			if(resultCode==Activity.RESULT_OK){
				Loguser user= (Loguser) data.getSerializableExtra("user");
				tv_info.setText("注册成功\t\n"+user.toString());
			}else{
				tv_info.setText("注册失败");
			}
			
			break;	
		default:
			break;
		}
    	
    }
    public void jump(View btn){
    	Intent intent = new Intent();
    	switch (btn.getId()) {
		case R.id.btn_login:
			//requestCode 代表去哪里，从哪里回来
			intent.setClass(this, LoginActivity.class);
			startActivityForResult(intent, REQUEST_CODE_lOGIN);
			break;
		case R.id.btn_reg:
			intent.setClass(this, LoginActivity.class);
			startActivityForResult(intent, REQUEST_CODE_REG);
			break;

		default:
			break;
		}
    	
    }
}
