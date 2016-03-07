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
		/**��ȡ���ּ�����*/
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
	/**ÿ����Ŀ���峤ʲô��*/
	/**�����Ż�
	 * 1.�첽���� �������ݣ�ͼƬ���ڼ�������ʱ����������Ϊnull���������������жϣ����û�м��أ��ٲ����첽����
	 * 2.�����ã����ڴ����ʱ��ֱ���ͷţ�SoftReference<T>
	 * ��3�����ò��ܱ����٣�1.ֱ��new ���� �Ķ����ǿ���á�
	 * �����ã���ʱ���ܶϣ��˴����ʺ�
	 * */
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		/**��һ������*/
		ViewHolder holder = null; 
		if(convertView==null){
			//˵����û�л���
			convertView =inflater.inflate(R.layout.filelistitem, null);//�˴�nullΪ�Ƿ�Ϊ��󶨸�����
			holder =new ViewHolder(); //�����鲼��
			holder.img=(ImageView) convertView.findViewById(R.id.img);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			/**���鲼�����뻺����convertViewһ�𻺴�*/
			convertView.setTag(holder);	
		}else{
			/**���鲼���ӻ�����ȡ����*/
			holder=(ViewHolder) convertView.getTag();
			
		}
		MyFile file =data.get(position);
		//���ذ�ͼƬ
		/**�ж��Ƿ�Ϊsd���ڵ�ͼƬ*/
		if(file.isPic()){
			/**ע������ʹ��������ʱ�������get���������жϵ��Ƿ�ͼƬһ����*/
			
			if(file.getBitmap().get()==null){
				//˵�����ļ���ͼƬ������û�м���ͼƬ����,�Ǿ��ȸ������ذ�ɫͼƬ
				holder.img.setImageResource(R.color.write);
				/**��Ҫ����һ�����̣߳�ĬĬ�ļ��أ�����첽����ͼƬ��andriod��������Thread��װ��AsyncTask*/
				MyTask task = new MyTask();//����һ���첽����
				/**ʹ��executeִ���첽������м��أ���Ҫ��������������1.ΪͼƬ·����2.ΪͼƬ���ص��δ��˴�����һ��position����ҪString����*/
				task.execute(file.getFilePath(),String.valueOf(position));
				
			}else{
				holder.img.setImageBitmap(file.getBitmap().get());//�͸�������
				
			}
			
		}
		/**ֱ����������������*/
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
	/**1.��һ��������Ҫ����Ĳ������˴���Ҫ����ͼƬ��ַΪString��
	 * 2.�ڶ�������Ϊ���������˴������������ʹ��Void
	 * 3.������Ϊ��Ҫ����ʲô�������˴���Ҫ����ͼƬʹ��Bitmap
	  */
	private class MyTask extends AsyncTask<String, Void, Bitmap>{
		/**do in back*/
		@Override
		protected Bitmap doInBackground(String... params) {
			String path =params[0];//ͼƬ·��
			int position =Integer.valueOf(params[1]);//��ȡָ���ļ��±�
			Bitmap bitmap =BitmapFactory.decodeFile(path);//
			/**ע���Ҳ����������bitmap*/
			data.get(position).setBitmap(new SoftReference<Bitmap>(bitmap));//����ָ���±������ļ�
			/**������Ҫˢ��������UI��ע�ⲻ����*/
			
			return null;
		}
		/**���������Ժ�ͻ�ִ�и÷������������߳���ִ��*/
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			FileAdapter.this.notifyDataSetChanged();//ˢ��������ע�ⲻ�������߳�ִ�У�ˢ�����̵߳�UI
			
		}
		
	}
	private class ViewHolder{
		ImageView img;
		TextView name;
		
	}
}
