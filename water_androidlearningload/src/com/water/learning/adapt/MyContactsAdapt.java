package com.water.learning.adapt;

import java.util.List;

import com.water.learning.R;
import com.water.learning.bean.Heros;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyContactsAdapt extends BaseAdapter{
	private List<Heros> data;
	/**��������Դ������*/
	private Context context;
	private LayoutInflater inflater ;
	public MyContactsAdapt(Context context,List<Heros> data){
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
	Heros heros =data.get(position);
	/**����inflater����list��xml��ȡһ��linearlayout��view*/
	View v =inflater.inflate(R.layout.contactlist, null);
	/**����fandviewbyid�ҵ�v�����Ӧ��face����������set�������ö�Ӧpisition�е�heros������*/
	ImageView face =(ImageView) v.findViewById(R.id.face);
	TextView name =(TextView) v.findViewById(R.id.name);
	TextView number =(TextView) v.findViewById(R.id.number);	
	face.setImageResource(heros.getFace());
	name.setText(heros.getName());
	number.setText(heros.getNumber());
	/**������Ŀ����ͼ*/
		return v;
	}

}
