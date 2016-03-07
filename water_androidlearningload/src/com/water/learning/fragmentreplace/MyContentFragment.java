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
		//根据星期日期不同，显示不同的内容
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
	//实例化内容frament
	public static MyContentFragment getInstance(int index){
		MyContentFragment contentFragment = new MyContentFragment();//创建fragment
		Bundle bundle = new Bundle();//
		bundle.putInt("index", index);
		bundle.putString("name", "我是水哥");
		contentFragment.setArguments(bundle);//设置参数进去
		return contentFragment;
	}
	public MyContentFragment() {
		super();
	}
	
}
