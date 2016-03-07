package com.water.learning;

import com.water.learning.fragmentreplace.MyFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
public class ViewPagerActivity extends FragmentActivity implements OnCheckedChangeListener, OnPageChangeListener {
    HorizontalScrollView hsv;
    RadioGroup rg;
    RadioButton china,korea,nKorea,japan,usa,uk;
	View v ;
	ViewPager vp;
	private int fromX;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpageractivity);
        initView();
    }
    /**
     * 
     */
	private void initView() {
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		rg = (RadioGroup) findViewById(R.id.rg);
		rg.setOnCheckedChangeListener(this);
		v = findViewById(R.id.v);
		china = (RadioButton) findViewById(R.id.china);
		korea = (RadioButton) findViewById(R.id.korea);
		nKorea = (RadioButton) findViewById(R.id.nkorea);
		japan = (RadioButton) findViewById(R.id.japan);
		usa = (RadioButton) findViewById(R.id.usa);
		uk = (RadioButton) findViewById(R.id.uk);
		vp = (ViewPager) findViewById(R.id.vp);
		MyAdapter adapter = new MyAdapter(this.getSupportFragmentManager());
		vp.setAdapter(adapter);//设置适配器
		//给vp设置翻页监听事件
		vp.setOnPageChangeListener(this);
	}
	
	private class MyAdapter extends FragmentPagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		//每一页长什么样
		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("position", position);//传递数据
			fragment.setArguments(bundle);//将数据包放进来
			return fragment;
		}

		//有多少页
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return rg.getChildCount();
		}
		
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int currentItem = 0;
		switch (checkedId) {
		case R.id.china:
			//表示中国被选中了
			currentItem = 0;
			break;
		case R.id.korea:
			currentItem = 1;
			break;
		case R.id.nkorea:
			currentItem = 2;
			break;
		case R.id.japan:
			currentItem = 3;
			break;
		case R.id.usa:
			currentItem = 4;
			break;
		case R.id.uk:
			currentItem = 5;
			break;
		default:
			break;
		}
		vp.setCurrentItem(currentItem);
	}
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		//需要监听的主要事件
		//1计算对应选项居中时，hsv滑动的位置
		int total = (int) ((position+positionOffset)*china.getWidth());
		Log.i("INFO", "rb_position:"+position);
		int green = (vp.getWidth()-china.getWidth())/2;
		int dx = total - green;//计算出要滑出去的距离
		hsv.scrollTo(dx, 0);
		lineScroll(position, positionOffset);
		
	}
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		
	}
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}
	
	public void lineScroll(int position,float positionOffSet){
		//拿到被选中按钮在屏幕中的位置
//		Toast.makeText(this, "position:"+position, 1000).show();
		Log.i("INFO", "line_position:"+position);
		RadioButton button = (RadioButton) rg.getChildAt(position);
		int [] location = new int[2];
		button.getLocationInWindow(location);
		//开始做位移滑动
		TranslateAnimation animation = new TranslateAnimation(
				fromX,
				location[0]+positionOffSet*china.getWidth(), 
				0, 
				0);
		animation.setDuration(50);//动画持续事件
		animation.setFillAfter(true);
		fromX = (int) (location[0]+positionOffSet*china.getWidth());
		v.startAnimation(animation);//线开始动画
	}
}
