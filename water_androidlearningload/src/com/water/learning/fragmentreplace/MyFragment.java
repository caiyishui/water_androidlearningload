package com.water.learning.fragmentreplace;

import java.util.ArrayList;

import com.water.learning.R;
import com.water.learning.adapt.MyAdapt;
import com.water.learning.adapt.MyContactsAdapt;
import com.water.learning.bean.Heros;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MyFragment extends Fragment{
	String[] contries = new String[]{
			"中国","韩国","朝鲜","日本","美国","英国"
	};
	private ArrayList<Heros> data;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initadata();
		// TODO Auto-generated method stub
		Bundle bundle = this.getArguments();
		int position = bundle.getInt("position");
		if(contries[position].equals("日本")){
			ImageView img=new ImageView(getActivity());
			img.setImageResource(R.drawable.japan);
			return img;
		}
		
		if(contries[position].equals("韩国")){
			ImageView img=new ImageView(getActivity());
			img.setBackgroundResource(R.drawable.one);
			
			return img;
		}
		if(contries[position].equals("中国")){
			ListView lv=new ListView(this.getActivity());
			
			MyContactsAdapt adapter=new MyContactsAdapt(getActivity(), data);
			lv.setAdapter(adapter);
			return lv;
		}
		if(contries[position].equals("美国")){
			ImageView img=new ImageView(getActivity());
			img.setImageResource(R.drawable.three);
			return img;
		}
		
		TextView tv = new TextView(getActivity());
		tv.setText(contries[position]);
		return tv;
	}

	private void initadata() {
		
		/**初始化data数据*/
		int[] faces =new int[]{
			R.drawable.face1,
			R.drawable.face2,
			R.drawable.face3,
			R.drawable.face4,
			R.drawable.face5,
			R.drawable.face6,
			R.drawable.face7,
			R.drawable.face8
			};
		String[] names= new String[]{
				"张飞",
				"曹操",
				"孙权",
				"吕布",
				"诸葛亮",
				"貂蝉",
				"关羽",
				"刘备"				
		};
		String[] numbers =new String[]{
			"1111111111",
			"2222222222",
			"3333333333",
			"4444444444",
			"5555555555",
			"6666666666",
			"7777777777",
			"8888888888"
			};
		data=new ArrayList<Heros>();
		for(int i=0;i<8;i++){
			Heros heros =new Heros();
			heros.setFace(faces[i]);
			heros.setName(names[i]);
			heros.setNumber(numbers[i]);
			data.add(heros);
		}
		
	}
	
}