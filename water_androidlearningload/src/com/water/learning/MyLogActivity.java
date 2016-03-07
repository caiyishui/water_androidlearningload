package com.water.learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MyLogActivity extends Activity implements OnClickListener{
	 /** Called when the activity is first created. */
		/**button for print log*/
		private Button log;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mylog);
	        Log.i("INFO", "今天真的是愚人节" );
	        initView();
	    }
	    /**
	     * method for init the View and on listener
	     * @author water
	     *
	     * 
	     * */
		private void initView() {
			//init button by id
		log=(Button) findViewById(R.id.log);
		//set listener
		log.setOnClickListener(this);
			
		}
		/**
		 * onClick
		 */
		public void onClick(View v) {
			//connect Logs
			try {
				readLog();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/**
		 * readLog by Phone
		 * @author water
		 * @throws IOException 
		 */
		private void readLog() throws IOException {
			Log.i("INFO","今天是开始进入安卓第一天");
			//存储读出来的log
			StringBuffer sb =new StringBuffer();
			ArrayList<String> cmdLine =new ArrayList<String>();
			cmdLine.add("logcat");
			cmdLine.add("-d");
			cmdLine.add("-s");
			cmdLine.add("INFO");
			//运行cmd代码
			
			InputStream inputStream = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()])).getInputStream();
			BufferedReader reader =new BufferedReader( new InputStreamReader(inputStream));//缓存reader
			String str =null;
			while((str=reader.readLine())!=null){
				sb.append(str);
				sb.append("\n");	
			}
			Toast.makeText(this, sb.toString(), 3000).show();
			
		}

}
