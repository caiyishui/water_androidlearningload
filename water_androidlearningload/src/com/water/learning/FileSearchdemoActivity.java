package com.water.learning;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.water.learning.adapt.FileAdapter;
import com.water.learning.bean.MyFile;
import com.water.learning.constant.MyConstant;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FileSearchdemoActivity extends Activity implements OnItemClickListener{
	private ListView lv;
	private List<MyFile> data;
	private FileAdapter adapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv=(ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        //������������             �����ľ����ҵ���ǰĿ¼�µ������ļ���
        //SD���ĸ�Ŀ¼��һ��������Щ��Ŀ��ʱ��һ��д������������
        initData(MyConstant.ROOT );
    }
    /**һ�������ã���bug����ָ�� �����*/
    /**��ȡָ��Ŀ¼������������ļ��б�
     * @author water
     * 
     * */
	private void initData(String root) {
		List<MyFile> files = new ArrayList<MyFile>();
		File file_root = new File(root);//��ȡ��Ŀ¼���� 
		/**��һ���Ƿ��ص���*/
		MyFile file_back=new MyFile();
		//���ص�ǰĿ¼�ĸ�Ŀ¼ע����Ҫ+1�������շ���"/"
		String back_path=root.substring(0, root.lastIndexOf("/")+1);
		/**�ֱ�����back������*/
		file_back.setFilePath(back_path);
		file_back.setName("back");
		file_back.setFile(new File(back_path));
		//��׿�м���ͼƬ�ķ�ʽBitmapFactory.decodeResource����
		file_back.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.dirs)));
		/**�����úõ�file_back��ӵ�file��list��*/
		files.add(file_back);
		/**�ļ���listFiles����������ȡ��Ŀ¼�������ļ������飬��for����*/
		Log.i("INFo","for");
		for(File f:file_root.listFiles()){
			String path = f.getAbsolutePath();
			MyFile myFile =new MyFile();
			myFile.setFilePath(path);
			myFile.setName(path.substring(path.lastIndexOf("/")+1));
			myFile.setFile(f);
			Log.i("INFo","�ж�Ǯ1");
			if(f.isDirectory()){
				myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.dirs)));
				Log.i("INFo","�ж�Ǯ2");
			}else if(path.toUpperCase().endsWith("JPG")||path.toUpperCase().endsWith("PNG")
					||path.toUpperCase().endsWith("JPEG")||path.toUpperCase().endsWith("GIF")
					||path.toUpperCase().endsWith("BMP")){
				/**�ж�����ļ���ͼƬ�Ļ�,ֱ�Ӽ���ͼƬ*/
//				Log.i("INFo","�ж�Ǯ3");//ͬ������
//				myFile.setBitmap(BitmapFactory.decodeFile(path));
				//�첽����
//				myFile.setBitmap(null);
				/**����ΪͼƬСbug*/
				myFile.setPic(true);
				myFile.setBitmap(new SoftReference<Bitmap>(null));//ע��˴�ӦΪΪ������Ϊ�գ���Ȼ���ܵ���get����
				Log.i("INFo","�ж�Ǯ3");
			}else{
				/**��������ļ��к�ͼƬ���Ͳ����ã�ֱ����������������image*/
			}
			
			files.add(myFile);
		}
			data=files;
			//����������
			adapter =new FileAdapter(this,data);
			lv.setAdapter(adapter);
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		File file_back = data.get(position).getFile();
		Log.i("1INFO+1", file_back.getPath());
		if(file_back.isDirectory()){
			initData(file_back.getAbsolutePath());
		}
		
//		/**��ȡ������ļ���·��*/
//		String path =data.get(position).getFilePath();
//		File file =new File(path);
//		if(file.isDirectory()){
//			initData(path);
//		}
		
		
	}

}
