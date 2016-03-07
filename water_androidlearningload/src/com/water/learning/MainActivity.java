package com.water.learning;

import java.util.ArrayList;
import java.util.List;





import com.water.learning.adapt.MyAdapt;
import com.water.learning.adapt.MyContactsAdapt;
import com.water.learning.bean.CourseList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {
	 private ListView lv;
	 private List<CourseList> data;
	 private final int MYVIEW=18;
	 private final int VIEWPAGER=17;
	 private final int QQSHOWACTIVITY=16;
	 private final int HTTPCONNECTIONACTIVITY=15;
	 private final int TWEENACTIVITY=14;
	 private final int FRAGMENTREPLACEACTIVITY=13;
	 private final int ONTOUCHACTIVITY=12;
	 private final int ASYNCTASKLOADINGBITMAPACTIVITY=11;
	 private final int HANDLERTESTACTIVITY=10;
	 private final int LOGINANDREGACTIVITY=9;
	 private final int OBJECTTRANSFERBETWEENACTIVITY=8;
	 private final int DIALOGDEMOACTIVITY=7;
	 private final int CUSTOMVIEWOFSOUNDCONTROLACTIVITY=6;
	 private final int FILESEARCHDEMOACTIVITY=5;
	 private final int MYCONTACTDEMOACTIVITY=4;	
	 private final int UIINJAVADOMEACTIVITY=3;	
	 private final int INTENTCAMERAFORRESULTACTIVITY=2;	 
	 private final int INTENTSYSTEMSTOREACTIVITY=1;	 
	 private final int MYLOG=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initdata();
        initView();
    }
    
    /**
     * 设置 退出方式的onkeydown方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
    	if(keyCode==KeyEvent.KEYCODE_BACK){
    		outofappmethod();
    		return true;
    	}
   	
    	return super.onKeyDown(keyCode, event);
    }
    
    
    
    
    
    
    
    
  
/**
 *  退出app的方式
 *  @author water
 * 
 */
	private void outofappmethod() {
		/**通过创建者模式创建*/
		AlertDialog.Builder builder =new AlertDialog.Builder(this);//对话框创建者
		
		builder.setTitle("提示");//标题
		builder.setMessage("你是否确认推出");//消息文本
		builder.setIcon(R.drawable.app);//设置前面图片
		/**OnClickListener:
		 * 有两个1.View.OnClickListener
		 * 2.DialogInterface.OnClickListener
		 * 此处给配置DialogInterface.OnClickListener
		 * */
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				/**注意此处context为MainActivity.this*/
//				确认推出
				onBackPressed();
				
			}
		} );
		/**设置取消监听时间*/
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "你小子误按了把",2000).show();
				
			}
		});
		Dialog dialog =builder.create();//通过创建者创建一个对话框
		dialog.show();//展现出来
		
		
	}

	private void initView() {
	
		/**获取lv控件，并使用自定义适配加载*/
		lv=(ListView)findViewById(R.id.lv);
		MyAdapt mdapt= new MyAdapt(this, data);
		lv.setAdapter(mdapt);
		lv.setBackgroundColor(R.color.write);
		/**设置条目点击吐死事件*/
		lv.setOnItemClickListener(this);
		
	}
	private void initdata() {
		/**初始化data数据*/
		
		int[] bitmaps =new int[]{
		R.drawable.log,
		R.drawable.phone,
		R.drawable.camera,
		R.drawable.ui,
		R.drawable.contacts,
		R.drawable.filesearch,
		R.drawable.sound,
		R.drawable.dialog,
		R.drawable.ac1,
		R.drawable.ac2,
		R.drawable.hd,
		R.drawable.task,
		R.drawable.touch,
		R.drawable.frag,
		R.drawable.movie,
		R.drawable.movie,
		R.drawable.touch,
		R.drawable.ac2,
		R.drawable.dialog,
			};
		String[] courseName= new String[]{
				"第一节课",
				"第二节课",
				"第二节课作业",
				"第三节课",
				"第四节课",
				"第五节课",
				"第六节课",
				"第七节课",
				"第八节课",
				"第九节课",
				"第十节课",
				"第十节课作业",
				"第十一节课",
				"第十二节课",
				"第十三节课",
				"第十四节课",
				"第十五节课",
				"第十六节课",
				"第十七节课"
							
		};
		String[] courseContent =new String[]{
			"打印收集日志",
			"打电话发短信调用系统相机",
			"系统相机的调用应用",			
			"UI的in java demo",
			"英雄联系人电话dome",
			"文件浏览器的简单制作",
			"自定义控件声音控制",
			"各种对话框的实例",
			"Activity间的对象的传递",
			"Activity的注册demo",
			"Handler 的demo实例",
			"AsyncTaskLoadingbitmap",
			"Ontouch的实例",
			"Fragment Replace",
			"补间动画demo",
			"Http服务连接",
			"qq侧拉效果",
			"类似微信效果",
			"自定义高级控件"
			};
		data=new ArrayList<CourseList>();
		for(int i=0;i<courseName.length;i++){
			CourseList courselists =new CourseList();
			courselists.setBitmap(bitmaps[i]);
			courselists.setCourseName(courseName[i]);
			courselists.setCourseContent(courseContent[i]);
			data.add(courselists);
		}
		
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		/**跳转对应的activity*/
	switch (position) {
	case MYLOG:
		startActivity(new Intent(this,MyLogActivity.class));
		break;
	
	case INTENTSYSTEMSTOREACTIVITY:
		startActivity(new Intent(this,IntentSystemStoreActivity.class));
		break;
	case INTENTCAMERAFORRESULTACTIVITY:
	startActivity(new Intent(this,IntentCameraforResultActivity.class));
			break;
			
	case UIINJAVADOMEACTIVITY:
	startActivity(new Intent(this,UiInJavadomeActivity.class));
			break;
	case MYCONTACTDEMOACTIVITY:
		startActivity(new Intent(this,MyContactdemoActivity.class));
				break;
	case FILESEARCHDEMOACTIVITY:
		startActivity(new Intent(this,FileSearchdemoActivity.class));
				break;
	case CUSTOMVIEWOFSOUNDCONTROLACTIVITY:
		startActivity(new Intent(this,CustomViewOfSoundControlActivity.class));
				break;
	case DIALOGDEMOACTIVITY:
		startActivity(new Intent(this,DialogDemoActivity.class));
				break;
	case OBJECTTRANSFERBETWEENACTIVITY:
		startActivity(new Intent(this,ObjectTransferBetweenActivity.class));
				break;
	case LOGINANDREGACTIVITY:
		startActivity(new Intent(this,LoginAndRegActivity.class));
				break;
	case HANDLERTESTACTIVITY:
		startActivity(new Intent(this,HandlerTestActivity.class));
				break;
	case ASYNCTASKLOADINGBITMAPACTIVITY:
		startActivity(new Intent(this,AsyncTaskLoadingbitmapActivity.class));
				break;
	case ONTOUCHACTIVITY:
		startActivity(new Intent(this,OnTouchActivity.class));
				break;
	case FRAGMENTREPLACEACTIVITY:
		startActivity(new Intent(this,FragmentReplaceActivity.class));
				break;
	case TWEENACTIVITY:
		startActivity(new Intent(this,TweenActivity.class));
				break;
	case HTTPCONNECTIONACTIVITY:
		startActivity(new Intent(this,HttpConnectionActivity.class));
				break;
	case QQSHOWACTIVITY:
		startActivity(new Intent(this,QqshowActivity.class));
				break;
	case VIEWPAGER:
		startActivity(new Intent(this,ViewPagerActivity.class));
				break;
	case MYVIEW:
		startActivity(new Intent(this,MyViewActivity.class));
				break;
	default:
		break;
	}
	
		
	}
}