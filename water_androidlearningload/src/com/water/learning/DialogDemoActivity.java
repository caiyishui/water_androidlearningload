package com.water.learning;

import java.util.Calendar;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogDemoActivity extends Activity implements OnClickListener{
	/**�����ռ䰴ťdialog*/
	private Button dialog;
	private Button progressDialog;
	private Button customDialog;
	private Dialog passworddialog;
	private Button timeDialog;
	private Button popupWindow;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogdemoactivity);
        /**��ʼ����dialog*/
        dialog=(Button) findViewById(R.id.dialog);
        progressDialog=(Button) findViewById(R.id.progressdialog);
        customDialog=(Button) findViewById(R.id.customdialog);
        timeDialog= (Button) findViewById(R.id.timedialog);
        popupWindow =(Button) findViewById(R.id.popupwindow);
        /**����ť���ü���*/
        dialog.setOnClickListener(this);
        progressDialog.setOnClickListener(this);
        customDialog.setOnClickListener(this);
        timeDialog.setOnClickListener(this);
        popupWindow.setOnClickListener(this);
        
    }
	public void onClick(View v) {
		// ��ʾ��ͨ�Ի���
		/**������������ͨ�Ի���ť*/
		if(v==dialog){
			showNormalDialog();}
		/**���������ǽ������Ի���ť*/
		if(v==progressDialog){
		showProgressDialog();}
		/**�������Զ���Ի���*/
		if(v==customDialog){
			showCustomDialog();}
		/**������ʱ��ɼ����Ի���*/
		if(v==timeDialog){
			showTimePickerDialog();}
		/**��������ťpopupWindow�ͳ��ֵ�����*/
		if(v==popupWindow){
			showPopupWindowDialog();}
	}
	/**activityҲ����������
	 * 
	 * ֻҪ��activity������andriod:theme="@android:style/theme.Dialog"
	 * �Ϳ��԰�activity��dialogʹ��
	 * */
	
	
	
	/**����������*/
	private void showPopupWindowDialog() {
		/**ʹpopupwindow��ȡ���㣬
		 * 1.���ñ���
		 * 2.���ÿɾ۽�
		 * */
		View view = LayoutInflater.from(this).inflate(R.layout.popup_window, null);
		PopupWindow window = new PopupWindow(this);//����popupwindow
		window.setContentView(view);//����window��ͼ
		//��ȡ��Ļ�Ŀ��
		WindowManager wm = this.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();   
		int height = wm.getDefaultDisplay().getHeight();
		//һ��Ҫ����window�Ŀ��
		window.setWidth(width/2);
		window.setHeight(height/6);
		//��ȡָ���İ�ť��λ��
		int [] location =new int[2];
		popupWindow.getLocationInWindow(location);
		window.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.local_popup_bg));
		window.setFocusable(true);
		/**1.popupWindowѡ��һ����������һ���ؼ��Ϳ���ȷ����Ҫ�����Ǹ�������
		 * 2.Ҫ����λ��
		 * 3.
		 * */
		window.showAtLocation(popupWindow, Gravity.LEFT|Gravity.TOP,0, (location[1]+popupWindow.getHeight()));
		
		
	}
	private void showTimePickerDialog() {
		Calendar c=Calendar.getInstance();//����������
		c.setTimeInMillis(System.currentTimeMillis());//����ϵͳʱ��
		int hourOfDay =c.get(Calendar.HOUR_OF_DAY);
		int minute =c.get(Calendar.MINUTE);
		TimePickerDialog timePickerDialog= new TimePickerDialog(this, new OnTimeSetListener() {
			
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				//�ٵ�����������������ɺ���߼�
				Toast.makeText(DialogDemoActivity.this,"hour:"+hourOfDay+"minute:"+minute , 2000).show();
			}
		}, hourOfDay, minute, true);
		timePickerDialog.show();
		
	}
	/**
	 * ��ʾ�Զ���Ի���
	 * @author water
	 */
	private void showCustomDialog() {
		AlertDialog.Builder builder =new AlertDialog.Builder(this);
		View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
		final EditText pswd =(EditText) view.findViewById(R.id.password);
		final EditText cpswd =(EditText) view.findViewById(R.id.cpassword);
		Button confirm = (Button) view.findViewById(R.id.confirm);
		confirm.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// �˴�û�м��ϸ���жϡ�ע��
				if(pswd.getText().toString().trim().equals(cpswd.getText().toString().trim())){
					Toast.makeText(DialogDemoActivity.this, "�����޸ĳɹ�", 2000).show();
					/**���������ȷ��passworddialog��ʧ*/
					passworddialog.dismiss();
				}else{
					Toast.makeText(DialogDemoActivity.this, "�����޸�ʧ��", 2000).show();
				}
				
			}
		});
		builder.setView(view);
		passworddialog= builder.create();
		passworddialog.show();
		
	}
	/**
	 * ��ʾ�������Ի���
	 * ������������һ�������������һ��ˮƽ������
	 * @author water
	 */
	private void showProgressDialog() {
		ProgressDialog pgDialog=new ProgressDialog(this);
		pgDialog.setMessage("���ڼ�����");
		//���ý�������ʽ���
		pgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//�����γ���һ�㶼�ڶ�Ӧ����
		//���ý����������ֵ
		pgDialog.setMax(100);
		//����ȡ����ť
		pgDialog.setButton("ȡ��", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogDemoActivity.this, "ȡ������", 2000).show();
				
			}
		});
		/**ע��̶�������չ�ֳ�������ȥ���¿̶ȡ�����*/
		pgDialog.show();
		pgDialog.setProgress(50);
	}
	/**
	 * ��ʾ��ͨ�Ի���
	 * @author water
	 */
	private void showNormalDialog() {
		/**ͨ��������ģʽ����*/
		AlertDialog.Builder builder =new AlertDialog.Builder(this);//�Ի��򴴽���
		
		builder.setTitle("����");//����
		builder.setMessage("�������°汾�Ƿ����");//��Ϣ�ı�
		builder.setIcon(R.drawable.ic_launcher);//����ǰ��ͼƬ
		/**OnClickListener:
		 * ������1.View.OnClickListener
		 * 2.DialogInterface.OnClickListener
		 * �˴�������DialogInterface.OnClickListener
		 * */
		builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				/**ע��˴�contextΪMainActivity.this*/
				Toast.makeText(DialogDemoActivity.this, "ȷ������",2000).show();
				
			}
		} );
		/**����ȡ������ʱ��*/
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogDemoActivity.this, "ȡ������",2000).show();
				
			}
		});
		Dialog dialog =builder.create();//ͨ�������ߴ���һ���Ի���
		dialog.show();//չ�ֳ���
	}
}
