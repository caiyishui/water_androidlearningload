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
    //����֮��Ҫִ�еķ���
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	//����requestCode
    	switch (requestCode) {
		case REQUEST_CODE_lOGIN:
			//����ɹ���ȡ����
			if(resultCode==Activity.RESULT_OK){
				Loguser user= (Loguser) data.getSerializableExtra("user");
				tv_info.setText("����ɹ�\t\n"+user.toString());
			}else{
				tv_info.setText("����ʧ��");
			}
			
			//����ʧ��
			
			break;

		case REQUEST_CODE_REG:
			if(resultCode==Activity.RESULT_OK){
				Loguser user= (Loguser) data.getSerializableExtra("user");
				tv_info.setText("ע��ɹ�\t\n"+user.toString());
			}else{
				tv_info.setText("ע��ʧ��");
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
			//requestCode ����ȥ������������
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
