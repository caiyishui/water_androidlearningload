package com.water.learning;

import java.util.ArrayList;
import java.util.List;


import com.water.learning.adapt.MyContactsAdapt;
import com.water.learning.bean.Heros;



import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyContactdemoActivity extends Activity implements OnItemClickListener{
	   private ListView lv;
	    private List<Heros> data;
		/** Called when the activity is first created. */
	    
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        initdata();
	        initView();
	    }

		private void initdata() {
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

		private void initView() {
			/**获取lv控件，并使用自定义适配加载*/
			lv=(ListView)findViewById(R.id.lv);
			MyContactsAdapt mdapt= new MyContactsAdapt(this, data);
			lv.setAdapter(mdapt);
			/**设置条目点击吐死事件*/
			lv.setOnItemClickListener(this);
		}
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			/**通过TextView的getText（）方法获取名字*/
			TextView name = (TextView) view.findViewById(R.id.name);
			Toast.makeText(this, name.getText(), 1000).show();
		}
}
