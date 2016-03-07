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
        //适配器和数据             （核心就是找到当前目录下的所有文件）
        //SD卡的根目录是一个常量，些项目的时候，一般写到常量类里面
        initData(MyConstant.ROOT );
    }
    /**一级软引用，有bug，空指针 求大神*/
    /**获取指定目录这的所有所有文件列表
     * @author water
     * 
     * */
	private void initData(String root) {
		List<MyFile> files = new ArrayList<MyFile>();
		File file_root = new File(root);//获取根目录对象 
		/**第一行是返回单独*/
		MyFile file_back=new MyFile();
		//返回当前目录的父目录注意需要+1才能最终返回"/"
		String back_path=root.substring(0, root.lastIndexOf("/")+1);
		/**分别设置back的属性*/
		file_back.setFilePath(back_path);
		file_back.setName("back");
		file_back.setFile(new File(back_path));
		//安卓中加载图片的方式BitmapFactory.decodeResource（）
		file_back.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.dirs)));
		/**将设置好的file_back添加到file的list中*/
		files.add(file_back);
		/**文件的listFiles（）方法获取其目录下所有文件的数组，用for遍历*/
		Log.i("INFo","for");
		for(File f:file_root.listFiles()){
			String path = f.getAbsolutePath();
			MyFile myFile =new MyFile();
			myFile.setFilePath(path);
			myFile.setName(path.substring(path.lastIndexOf("/")+1));
			myFile.setFile(f);
			Log.i("INFo","判断钱1");
			if(f.isDirectory()){
				myFile.setBitmap(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.dirs)));
				Log.i("INFo","判断钱2");
			}else if(path.toUpperCase().endsWith("JPG")||path.toUpperCase().endsWith("PNG")
					||path.toUpperCase().endsWith("JPEG")||path.toUpperCase().endsWith("GIF")
					||path.toUpperCase().endsWith("BMP")){
				/**判断如果文件是图片的话,直接加载图片*/
//				Log.i("INFo","判断钱3");//同步加载
//				myFile.setBitmap(BitmapFactory.decodeFile(path));
				//异步加载
//				myFile.setBitmap(null);
				/**设置为图片小bug*/
				myFile.setPic(true);
				myFile.setBitmap(new SoftReference<Bitmap>(null));//注意此处应为为软引用为空，不然不能调用get方法
				Log.i("INFo","判断钱3");
			}else{
				/**如果不是文件夹和图片，就不设置，直接在适配器中设置image*/
			}
			
			files.add(myFile);
		}
			data=files;
			//创建适配器
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
		
//		/**获取被点击文件的路径*/
//		String path =data.get(position).getFilePath();
//		File file =new File(path);
//		if(file.isDirectory()){
//			initData(path);
//		}
		
		
	}

}
