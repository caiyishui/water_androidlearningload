package com.water.learning;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskLoadingbitmapActivity extends Activity implements OnClickListener {
	private Button bt;
	private ProgressBar pb;
	private TextView tv;
	private ImageView img;
	private  final String URL_PATH="http://d.hiphotos.baidu.com/image/pic/item/730e0cf3d7ca7bcb997158e9bb096b63f724a8ea.jpg";
	private final String SD_PATH =Environment.getExternalStorageDirectory().getAbsolutePath().toString();
    private int contentLength;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctaskloadingbitmapactivity);
        initView();
    }

	private void initView() {
		bt=(Button) findViewById(R.id.download);
		pb=(ProgressBar) findViewById(R.id.pb);
		tv=(TextView) findViewById(R.id.tv);
		img=(ImageView) findViewById(R.id.img);
		bt.setOnClickListener(this);	
	}
	//���ذ�ť����
	public void onClick(View v) {
			MyTask myTask =new MyTask();
			myTask.execute(URL_PATH);	
	}
	private class MyTask extends AsyncTask<String, Integer, Bitmap>{
		@Override
		protected Bitmap doInBackground(String... params) {
			String path =params[0];
			Bitmap bitmap=null;
			try {
				bitmap = loadImage(path);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
						
			return bitmap;
		}

		private Bitmap loadImage(String path) throws Exception {
		
			String name =path.substring(path.lastIndexOf("/")+1);
			URL url =new URL(path);
			HttpURLConnection conn =(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			if(conn.getResponseCode()==200){
				contentLength=conn.getContentLength();
				pb.setMax(contentLength);
				InputStream is =conn.getInputStream();
				FileOutputStream os =new FileOutputStream(SD_PATH+"/"+name);
				int len=0;
				byte[] buffer =new byte[1024];
				while((len=is.read(buffer))!=-1){
					//	�����ȴ��ݸ�progress
					this.publishProgress(len);
					os.write(buffer,0,len);
				}
				is.close();
				os.close();
				
				Bitmap bitmap =BitmapFactory.decodeFile(SD_PATH+"/"+name);
				Log.i("INFO","���ܵ�������ͼƬ��");
				return bitmap;
				
			}
			
			
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		//������Ͽ���ˢ�£�UI
		@Override
		protected void onPostExecute(Bitmap result) {
			img.setImageBitmap(result);
			if(result!=null){
			tv.setText("�������");}
			else{tv.setText("û�м��سɹ�");}
			super.onPostExecute(result);
		}
//		�����ͬ�����½�����
		@Override
		protected void onProgressUpdate(Integer... values) {
			pb.setProgress(pb.getProgress()+values[0]);
			String per =100*pb.getProgress()/pb.getMax()+"%";
			tv.setText("���������"+per);			
			super.onProgressUpdate(values);
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	
}
}
