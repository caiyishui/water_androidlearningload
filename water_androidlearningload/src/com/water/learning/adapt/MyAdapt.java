package com.water.learning.adapt;

import java.util.List;

import com.water.learning.R;
import com.water.learning.bean.CourseList;
import com.water.learning.bean.Heros;


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
/**未优化的*/
public class MyAdapt extends BaseAdapter{
	private List<CourseList> data;
	/**上下文资源加载器*/
	private Context context;
	private LayoutInflater inflater ;
	public MyAdapt(Context context,List<CourseList> data){
		this.context= context;
		this.data = data;
		inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
	/**number of item*/
	public int getCount() {
		// TODO Auto-generated method stub
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
	/**the outside of item view*/
	public View getView(int position, View convertView, ViewGroup parent) {		
//	CourseList courseLists =data.get(position);
//	/**利用inflater解析list。xml获取一个linearlayout的view*/
//	View v =inflater.inflate(R.layout.list, null);
//	/**利用fandviewbyid找到v里面对应的face等属性利用set方法设置对应pisition中的heros的属性*/
//	ImageView image=(ImageView) v.findViewById(R.id.face);
//	TextView coursename =(TextView) v.findViewById(R.id.name);
//	TextView coursecontent =(TextView) v.findViewById(R.id.number);	
//	image.setImageResource(courseLists.getBitmap());
//	coursename.setText(courseLists .getCourseName());
//	coursecontent.setText(courseLists .getCourseContent());
//	
//	/**单个条目的视图*/
//		return v;
		/**一级优化*/
	ViewHolder holder=null;
	if(convertView==null){
		convertView=inflater.inflate(R.layout.list, null);
		holder=new ViewHolder();
		holder.image=(ImageView) convertView.findViewById(R.id.face);
		holder.coursename=(TextView) convertView.findViewById(R.id.name);
		holder.coursecontent=(TextView) convertView.findViewById(R.id.number);
		convertView.setTag(holder);				
	}else{
		//重新从convertView缓存中拿出麻布袋
		holder=(ViewHolder) convertView.getTag();
	}	
	CourseList courseList=data.get(position);
	

	
		//开启一个异步任务开始加载图片
	holder.image.setImageResource(courseList.getBitmap());
	holder.coursename.setText(courseList.getCourseName());
	holder.coursecontent.setText(courseList.getCourseContent());
	return convertView;	
	}
	//二级优化
//	ViewHolder holder=null;
//	if(convertView==null){
//		convertView=inflater.inflate(R.layout.list, null);
//		holder=new ViewHolder();
//		holder.image=(ImageView) convertView.findViewById(R.id.face);
//		holder.coursename=(TextView) convertView.findViewById(R.id.name);
//		holder.coursecontent=(TextView) convertView.findViewById(R.id.number);
//		convertView.setTag(holder);				
//	}else{
//		//重新从convertView缓存中拿出麻布袋
//		holder=(ViewHolder) convertView.getTag();
//	}	
//	CourseList courseList=data.get(position);
//	

	
		//开启一个异步任务开始加载图片

//	holder.image.setImageResource(R.color.write);
//	MyTask task=new MyTask();
//	task.execute(String.valueOf(data.get(position).getBitmap()),String.valueOf(position));
//	holder.coursename.setText(courseList.getCourseName());
//	holder.coursecontent.setText(courseList.getCourseContent());
//	return convertView;	
//	}
	private class ViewHolder{
		ImageView image;
		TextView coursename;
		TextView coursecontent;
	}
//	private class MyTask extends AsyncTask<String, Void, Bitmap>{
//
//		@Override
//		protected Bitmap doInBackground(String... params) {
//			int id=Integer.valueOf(params[0]);
//			int position =Integer.valueOf(params[1]);
//			data.get(position).setBitmap(id);
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Bitmap result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			MyAdapt.this.notifyDataSetChanged();
//		}
//		
//	}
	
}
