package com.water.learning.myview;
import com.nineoldandroids.view.ViewHelper;
import com.water.learning.QqshowActivity;
import com.water.learning.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
public class MySlidingMenu extends HorizontalScrollView  {
	private int widthPixls;
	private int mMenuWidth;

	private ViewGroup mMenu;
	private ViewGroup mContent;

	private MySlidingMenu mySlidingMenu;

     Handler handler=new Handler(){


		public void handleMessage(Message msg) {
			int scrollDeltX = (Integer) msg.obj;
			mySlidingMenu.smoothScrollTo(scrollDeltX, 0);
			
			
		};
	};
	
	public MySlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//������Ļ�� �������ؼ���
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		widthPixls = displayMetrics.widthPixels;
		mySlidingMenu=(MySlidingMenu) findViewById(R.id.menu);
		
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//get the child viewgroup
		LinearLayout ll = (LinearLayout) this.getChildAt(0);
		
		mMenu =  (ViewGroup) ll.getChildAt(0);
		mContent = (ViewGroup) ll.getChildAt(1);
		//���������Сֵ
		mMenuWidth =  (int) (0.8*widthPixls);
		mMenu.getLayoutParams().width = mMenuWidth;
		mContent.getLayoutParams().width = widthPixls;
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(mMenuWidth, 0);
			Log.i("INFO", "mMenuWidth:"+mMenuWidth);
		}
	}
	
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		//l�ı仯��0~mMenuWidth
		float scale = (float)l/mMenuWidth;
		//scale�ı仯��0~1
		float leftScale = 1-scale*0.3f;
		//leftScale�仯1~0.7
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		
		//͸���ȱ仯1~0.3
		float leftAlpha = 1-scale*0.7f;
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
		//λ�Ʊ仯
		ViewHelper.setTranslationX(mMenu, l*0.7f);
		
		//mContent�仯 0.8~1.0
		float rightScale = 0.8f+0.2f*scale;
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
		super.onScrollChanged(l, t, oldl, oldt);
	}
	/**����touch�¼�up���ж�ͣ��λ��
	 * @author water
	 * */
		@Override
		public boolean onTouchEvent(MotionEvent ev) {
			
					switch (ev.getAction()) {
					case MotionEvent.ACTION_DOWN:
						
						break;
					case MotionEvent.ACTION_UP:
						Log.i("INFO", "up");
						int scrollX = mySlidingMenu.getScrollX();
						int scrollDeltX;
						if(scrollX<=0.4*widthPixls){
							//������һҳ
							scrollDeltX = 0;
						}else{
							//��������ҳ
							scrollDeltX = (int) (0.8*widthPixls);
						}
						Message msg = handler.obtainMessage();
						msg.obj = scrollDeltX;
						msg.sendToTarget();
//						hsScrollView.smoothScrollTo(scrollDeltX, 0);
						break;
					case MotionEvent.ACTION_MOVE:
						
						break;
					default:
						break;
					}
					
			
			return super.onTouchEvent(ev);
		}
		
		
		
	
}
