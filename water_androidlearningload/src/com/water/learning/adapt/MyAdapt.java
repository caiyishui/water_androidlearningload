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
/**δ�Ż���*/
public class MyAdapt extends BaseAdapter{
	private List<CourseList> data;
	/**��������Դ������*/
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
//	/**����inflater����list��xml��ȡһ��linearlayout��view*/
//	View v =inflater.inflate(R.layout.list, null);
//	/**����fandviewbyid�ҵ�v�����Ӧ��face����������set�������ö�Ӧpisition�е�heros������*/
//	ImageView image=(ImageView) v.findViewById(R.id.face);
//	TextView coursename =(TextView) v.findViewById(R.id.name);
//	TextView coursecontent =(TextView) v.findViewById(R.id.number);	
//	image.setImageResource(courseLists.getBitmap());
//	coursename.setText(courseLists .getCourseName());
//	coursecontent.setText(courseLists .getCourseContent());
//	
//	/**������Ŀ����ͼ*/
//		return v;
		/**һ���Ż�*/
	ViewHolder holder=null;
	if(convertView==null){
		convertView=inflater.inflate(R.layout.list, null);
		holder=new ViewHolder();
		holder.image=(ImageView) convertView.findViewById(R.id.face);
		holder.coursename=(TextView) convertView.findViewById(R.id.name);
		holder.coursecontent=(TextView) convertView.findViewById(R.id.number);
		convertView.setTag(holder);				
	}else{
		//���´�convertView�������ó��鲼��
		holder=(ViewHolder) convertView.getTag();
	}	
	CourseList courseList=data.get(position);
	

	
		//����һ���첽����ʼ����ͼƬ
	holder.image.setImageResource(courseList.getBitmap());
	holder.coursename.setText(courseList.getCourseName());
	holder.coursecontent.setText(courseList.getCourseContent());
	return convertView;	
	}
	//�����Ż�
//	ViewHolder holder=null;
//	if(convertView==null){
//		convertView=inflater.inflate(R.layout.list, null);
//		holder=new ViewHolder();
//		holder.image=(ImageView) convertView.findViewById(R.id.face);
//		holder.coursename=(TextView) convertView.findViewById(R.id.name);
//		holder.coursecontent=(TextView) convertView.findViewById(R.id.number);
//		convertView.setTag(holder);				
//	}else{
//		//���´�convertView�������ó��鲼��
//		holder=(ViewHolder) convertView.getTag();
//	}	
//	CourseList courseList=data.get(position);
//	

	
		//����һ���첽����ʼ����ͼƬ

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
