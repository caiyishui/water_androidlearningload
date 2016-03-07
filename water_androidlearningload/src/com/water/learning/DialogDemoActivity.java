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
	/**声明空间按钮dialog*/
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
        /**初始化绑定dialog*/
        dialog=(Button) findViewById(R.id.dialog);
        progressDialog=(Button) findViewById(R.id.progressdialog);
        customDialog=(Button) findViewById(R.id.customdialog);
        timeDialog= (Button) findViewById(R.id.timedialog);
        popupWindow =(Button) findViewById(R.id.popupwindow);
        /**给按钮设置监听*/
        dialog.setOnClickListener(this);
        progressDialog.setOnClickListener(this);
        customDialog.setOnClickListener(this);
        timeDialog.setOnClickListener(this);
        popupWindow.setOnClickListener(this);
        
    }
	public void onClick(View v) {
		// 显示普通对话框
		/**如果点击的是普通对话框按钮*/
		if(v==dialog){
			showNormalDialog();}
		/**如果点击的是进度条对话框按钮*/
		if(v==progressDialog){
		showProgressDialog();}
		/**如果点击自定义对话框*/
		if(v==customDialog){
			showCustomDialog();}
		/**如果点击时间采集器对话框*/
		if(v==timeDialog){
			showTimePickerDialog();}
		/**如果点击按钮popupWindow就出现弹出框*/
		if(v==popupWindow){
			showPopupWindowDialog();}
	}
	/**activity也尅当弹出框
	 * 
	 * 只要在activity中设置andriod:theme="@android:style/theme.Dialog"
	 * 就可以把activity当dialog使用
	 * */
	
	
	
	/**弹出框设置*/
	private void showPopupWindowDialog() {
		/**使popupwindow获取焦点，
		 * 1.设置背景
		 * 2.设置可聚焦
		 * */
		View view = LayoutInflater.from(this).inflate(R.layout.popup_window, null);
		PopupWindow window = new PopupWindow(this);//创建popupwindow
		window.setContentView(view);//设置window视图
		//获取屏幕的宽高
		WindowManager wm = this.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();   
		int height = wm.getDefaultDisplay().getHeight();
		//一定要设置window的宽高
		window.setWidth(width/2);
		window.setHeight(height/6);
		//获取指定的按钮的位置
		int [] location =new int[2];
		popupWindow.getLocationInWindow(location);
		window.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.local_popup_bg));
		window.setFocusable(true);
		/**1.popupWindow选择一个界面的里的一个控件就可以确定，要弹的那个界面了
		 * 2.要弹的位置
		 * 3.
		 * */
		window.showAtLocation(popupWindow, Gravity.LEFT|Gravity.TOP,0, (location[1]+popupWindow.getHeight()));
		
		
	}
	private void showTimePickerDialog() {
		Calendar c=Calendar.getInstance();//创建日历类
		c.setTimeInMillis(System.currentTimeMillis());//设置系统时间
		int hourOfDay =c.get(Calendar.HOUR_OF_DAY);
		int minute =c.get(Calendar.MINUTE);
		TimePickerDialog timePickerDialog= new TimePickerDialog(this, new OnTimeSetListener() {
			
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				//毁掉函数，可以设置完成后的逻辑
				Toast.makeText(DialogDemoActivity.this,"hour:"+hourOfDay+"minute:"+minute , 2000).show();
			}
		}, hourOfDay, minute, true);
		timePickerDialog.show();
		
	}
	/**
	 * 显示自定义对话框
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
				// 此处没有加严格的判断。注意
				if(pswd.getText().toString().trim().equals(cpswd.getText().toString().trim())){
					Toast.makeText(DialogDemoActivity.this, "密码修改成功", 2000).show();
					/**如果密码正确，passworddialog消失*/
					passworddialog.dismiss();
				}else{
					Toast.makeText(DialogDemoActivity.this, "密码修改失败", 2000).show();
				}
				
			}
		});
		builder.setView(view);
		passworddialog= builder.create();
		passworddialog.show();
		
	}
	/**
	 * 显示进度条对话框
	 * 进度条分两种一种任意进度条，一种水平进度条
	 * @author water
	 */
	private void showProgressDialog() {
		ProgressDialog pgDialog=new ProgressDialog(this);
		pgDialog.setMessage("正在加载中");
		//设置进度条样式风格
		pgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//放整形常量一般都在对应类下
		//设置进度条的最大值
		pgDialog.setMax(100);
		//设置取消按钮
		pgDialog.setButton("取消", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogDemoActivity.this, "取消更新", 2000).show();
				
			}
		});
		/**注意刻度条是先展现出来，再去更新刻度。。瞄*/
		pgDialog.show();
		pgDialog.setProgress(50);
	}
	/**
	 * 显示普通对话框
	 * @author water
	 */
	private void showNormalDialog() {
		/**通过创建者模式创建*/
		AlertDialog.Builder builder =new AlertDialog.Builder(this);//对话框创建者
		
		builder.setTitle("更新");//标题
		builder.setMessage("发现最新版本是否更新");//消息文本
		builder.setIcon(R.drawable.ic_launcher);//设置前面图片
		/**OnClickListener:
		 * 有两个1.View.OnClickListener
		 * 2.DialogInterface.OnClickListener
		 * 此处给配置DialogInterface.OnClickListener
		 * */
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				/**注意此处context为MainActivity.this*/
				Toast.makeText(DialogDemoActivity.this, "确定更新",2000).show();
				
			}
		} );
		/**设置取消监听时间*/
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogDemoActivity.this, "取消更新",2000).show();
				
			}
		});
		Dialog dialog =builder.create();//通过创建者创建一个对话框
		dialog.show();//展现出来
	}
}
