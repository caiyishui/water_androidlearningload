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
     * ���� �˳���ʽ��onkeydown����
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
 *  �˳�app�ķ�ʽ
 *  @author water
 * 
 */
	private void outofappmethod() {
		/**ͨ��������ģʽ����*/
		AlertDialog.Builder builder =new AlertDialog.Builder(this);//�Ի��򴴽���
		
		builder.setTitle("��ʾ");//����
		builder.setMessage("���Ƿ�ȷ���Ƴ�");//��Ϣ�ı�
		builder.setIcon(R.drawable.app);//����ǰ��ͼƬ
		/**OnClickListener:
		 * ������1.View.OnClickListener
		 * 2.DialogInterface.OnClickListener
		 * �˴�������DialogInterface.OnClickListener
		 * */
		builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				/**ע��˴�contextΪMainActivity.this*/
//				ȷ���Ƴ�
				onBackPressed();
				
			}
		} );
		/**����ȡ������ʱ��*/
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "��С�����˰�",2000).show();
				
			}
		});
		Dialog dialog =builder.create();//ͨ�������ߴ���һ���Ի���
		dialog.show();//չ�ֳ���
		
		
	}

	private void initView() {
	
		/**��ȡlv�ؼ�����ʹ���Զ����������*/
		lv=(ListView)findViewById(R.id.lv);
		MyAdapt mdapt= new MyAdapt(this, data);
		lv.setAdapter(mdapt);
		lv.setBackgroundColor(R.color.write);
		/**������Ŀ��������¼�*/
		lv.setOnItemClickListener(this);
		
	}
	private void initdata() {
		/**��ʼ��data����*/
		
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
				"��һ�ڿ�",
				"�ڶ��ڿ�",
				"�ڶ��ڿ���ҵ",
				"�����ڿ�",
				"���Ľڿ�",
				"����ڿ�",
				"�����ڿ�",
				"���߽ڿ�",
				"�ڰ˽ڿ�",
				"�ھŽڿ�",
				"��ʮ�ڿ�",
				"��ʮ�ڿ���ҵ",
				"��ʮһ�ڿ�",
				"��ʮ���ڿ�",
				"��ʮ���ڿ�",
				"��ʮ�Ľڿ�",
				"��ʮ��ڿ�",
				"��ʮ���ڿ�",
				"��ʮ�߽ڿ�"
							
		};
		String[] courseContent =new String[]{
			"��ӡ�ռ���־",
			"��绰�����ŵ���ϵͳ���",
			"ϵͳ����ĵ���Ӧ��",			
			"UI��in java demo",
			"Ӣ����ϵ�˵绰dome",
			"�ļ�������ļ�����",
			"�Զ���ؼ���������",
			"���ֶԻ����ʵ��",
			"Activity��Ķ���Ĵ���",
			"Activity��ע��demo",
			"Handler ��demoʵ��",
			"AsyncTaskLoadingbitmap",
			"Ontouch��ʵ��",
			"Fragment Replace",
			"���䶯��demo",
			"Http��������",
			"qq����Ч��",
			"����΢��Ч��",
			"�Զ���߼��ؼ�"
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
		
		/**��ת��Ӧ��activity*/
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