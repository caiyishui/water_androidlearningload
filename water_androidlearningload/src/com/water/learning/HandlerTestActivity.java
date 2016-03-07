package com.water.learning;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HandlerTestActivity extends Activity implements OnClickListener {
	public static  boolean flag;
	private Button start,ts,stop;
	private int i=0;
	private final int  HAPPY=1;
	
	
	private Handler handler=new Handler(){
		//����Ҿ���һ����
		@Override
		public void handleMessage(Message msg) {
			//�յ���Ϣ
			int what =msg.what;
			switch (what) {
			case HAPPY:
				String text =(String) msg.obj;
				ts.setText(text);
				
				break;

			default:
				break;
			}
			
		}
		
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handlertestactivity);
        start=(Button) findViewById(R.id.start);
        ts=(Button) findViewById(R.id.ts);
        stop= (Button) findViewById(R.id.stop);
        start.setOnClickListener(this);
        ts.setOnClickListener(this);
        stop.setOnClickListener(this);
        flag=true;
        MyRunnableThread myRunnableThread =new MyRunnableThread();
        Thread thread = new Thread(myRunnableThread);
        thread.start();
        if(i==10001){
        	Toast.makeText(this, "water keep on going", 2000).show();
        }else{
        	Toast.makeText(this, "water keep on going too", 2000).show();
        }
//        String threadName= Thread.currentThread().getName();
//        Toast.makeText(this, threadName, 5000).show();
        
        
    }

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start:
			MyThread myThread = new MyThread();
			myThread.start();
			
			break;
		case R.id.ts:
			handler.post(run_handler);//��run_handler�Ž�rannable�Ķ�����
			break;
		case R.id.stop:
			handler.removeCallbacks(run_handler);//��run_handler�Ƴ�rannable�Ķ�����
			
			break;

		default:
			break;
		}
		
	}
	private Runnable run_handler=new Runnable(){
		public void run() {
			i++;
			ts.setText("rannable ˢ��UI"+i+"��");
			handler.postDelayed(run_handler,100);//������runable���ڶ�����
		};
		
	};
	private class MyRunnableThread implements Runnable{

		public void run() {
			while(flag){
				i++;
				if(i>10000){
					flag=false;
				}
				
				
			}
			
		}		
	}
	private class MyThread extends Thread{
		@Override
		public void run() {
			try {
				Thread.sleep(5000);
//				ts.setText("water");���������߳����������߳�UI
				Message msg_ob = handler.obtainMessage();//ʹ����Ϣ�صĸ���
				msg_ob.obj="���죬��ô������";//������Ϣobj
				msg_ob.what =HAPPY;//����ʶ����Ϣ��what
				msg_ob.sendToTarget();//������Ϣ
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			
		}		
		
	}
}
