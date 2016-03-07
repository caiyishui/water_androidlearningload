package com.water.learning.fragmentreplace;
import android.app.Fragment;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyContentFragment extends Fragment{
	private int mIndex;

	public int getMindex() {
		return mIndex;
	}
	public void setMindex(int mIndex) {
		this.mIndex = mIndex;
	}
	private String [] contents = new String[]{
			"today is Moday",
			"today is Tuesday",
			"today is Wednesday",
			"today is Thursday",
			"today is Friday",
			"today is Saturday",
			"today is Sunday"
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//�����������ڲ�ͬ����ʾ��ͬ������
		Bundle bundle = this.getArguments();
		int index = bundle.getInt("index");
		this.mIndex=index;
		String name = bundle.getString("name");
		String content = contents[index]+name;
		TextView tv = new TextView(getActivity());
		tv.setText(content);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}
	//ʵ��������frament
	public static MyContentFragment getInstance(int index){
		MyContentFragment contentFragment = new MyContentFragment();//����fragment
		Bundle bundle = new Bundle();//
		bundle.putInt("index", index);
		bundle.putString("name", "����ˮ��");
		contentFragment.setArguments(bundle);//���ò�����ȥ
		return contentFragment;
	}
	public MyContentFragment() {
		super();
	}
	
}
