package com.water.learning.adapt;

import java.lang.ref.SoftReference;
import java.util.List;


import com.water.learning.R;
import com.water.learning.bean.MyFile;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter{
	private List<MyFile> data;
	private Context context;
	private LayoutInflater inflater;
	public FileAdapter(Context context,List<MyFile> data){
		this.context=context;
		this.data = data;
		/**获取布局加载器*/
		this.inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public int getCount() {	
		
		return data.size();
}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	/**每个条目具体长什么样*/
	/**二层优化
	 * 1.异步加载 大型数据（图片）在加载数据时，首先设置为null，再在适配器里判断，如果没有加载，再采用异步加载
	 * 2.软引用（当内存过高时，直接释放）SoftReference<T>
	 * 有3中引用不能被销毁，1.直接new 出来 的对象叫强引用。
	 * 虚引用，随时可能断，此处不适合
	 * */
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		/**第一级缓存*/
		ViewHolder holder = null; 
		if(convertView==null){
			//说明还没有缓存
			convertView =inflater.inflate(R.layout.filelistitem, null);//此处null为是否为其绑定父容器
			holder =new ViewHolder(); //创建麻布袋
			holder.img=(ImageView) convertView.findViewById(R.id.img);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			/**将麻布袋放入缓存中convertView一起缓存*/
			convertView.setTag(holder);	
		}else{
			/**将麻布袋从缓存中取出来*/
			holder=(ViewHolder) convertView.getTag();
			
		}
		MyFile file =data.get(position);
		//加载绑定图片
		/**判断是否为sd卡内的图片*/
		if(file.isPic()){
			/**注意这里使用软引用时，必须加get方法才是判断的是否图片一加载*/
			
			if(file.getBitmap().get()==null){
				//说明该文件是图片，而且没有加载图片进来,那就先给他加载白色图片
				holder.img.setImageResource(R.color.write);
				/**需要启动一个子线程，默默的加载，完成异步加载图片，andriod有类似与Thread封装的AsyncTask*/
				MyTask task = new MyTask();//创建一个异步任务
				/**使用execute执行异步任务进行加载，需要传递两个参数，1.为图片路径，2.为图片加载到何处此处返回一个position，需要String类型*/
				task.execute(file.getFilePath(),String.valueOf(position));
				
			}else{
				holder.img.setImageBitmap(file.getBitmap().get());//就给他加载
				
			}
			
		}
		/**直接在适配器中设置*/
		else if(file.getFilePath().toUpperCase().endsWith("TXT")){			
			holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.file));		
		}else if(file.getFilePath().toUpperCase().endsWith("MP3")){			
			holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.mp3));			
		}else{
			holder.img.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.dirs));
			
		}
		
		holder.name.setText(file.getName());											
		return convertView;
	}
	/**1.第一个参数需要传入的参数，此处需要传入图片地址为String；
	 * 2.第二个参数为进度条，此处无需进度条，使用Void
	 * 3.第三个为我要返回什么东西，此处需要返回图片使用Bitmap
	  */
	private class MyTask extends AsyncTask<String, Void, Bitmap>{
		/**do in back*/
		@Override
		protected Bitmap doInBackground(String... params) {
			String path =params[0];//图片路径
			int position =Integer.valueOf(params[1]);//获取指定文件下标
			Bitmap bitmap =BitmapFactory.decodeFile(path);//
			/**注意放也得用软引用bitmap*/
			data.get(position).setBitmap(new SoftReference<Bitmap>(bitmap));//利用指定下标设置文件
			/**下面需要刷新适配器UI，注意不能在*/
			
			return null;
		}
		/**加载完了以后就会执行该方法，是在主线程里执行*/
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			FileAdapter.this.notifyDataSetChanged();//刷新适配器注意不能在子线程执行，刷行主线程的UI
			
		}
		
	}
	private class ViewHolder{
		ImageView img;
		TextView name;
		
	}
}
