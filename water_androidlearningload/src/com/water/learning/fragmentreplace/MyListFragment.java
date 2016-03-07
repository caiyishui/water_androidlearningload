package com.water.learning.fragmentreplace;


import com.water.learning.R;

import android.app.FragmentManager;
import android.app.FragmentManager.BackStackEntry;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MyListFragment extends ListFragment implements OnItemClickListener{
	private FragmentManager fm;
	String [] days = new String[]{
			"Monday",
			"Tuesday",
			"Wednesday",
			"Thursday",
			"Friday",
			"Saturday",
			"Sunday"
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				days
				));
		fm = getFragmentManager();//fragment的管理器
		fm.addOnBackStackChangedListener(new OnBackStackChangedListener() {
			
			public void onBackStackChanged() {
				int count=fm.getBackStackEntryCount();
				for(int i=0;i<count;i++){
					BackStackEntry backstackEntry =fm.getBackStackEntryAt(i);
					String name=backstackEntry.getName();
					Log.i("INFO",name);
					
					
				}
				
			}
		});
		this.getListView().setOnItemClickListener(this);
		super.onActivityCreated(savedInstanceState);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//some item been clieked
		showContent(position);
	}

	private void showContent(int position) {
		//新的fragment
		MyContentFragment contentFragment = MyContentFragment.getInstance(position);
		//旧的fragment
		MyContentFragment mf = (MyContentFragment) fm.findFragmentById(R.id.content1);
		
		if(mf == null||mf.getMindex()!=position){
			//需要将新的fragment替换成旧的
			//开启事务
			FragmentTransaction ft = fm.beginTransaction();//
			ft.replace(R.id.content1, contentFragment);
			ft.addToBackStack("fragment"+position);
			//提交
			ft.commit();
		}
	}
	
}
