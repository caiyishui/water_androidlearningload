package com.water.learning.myview;

import com.water.learning.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyView extends View {
	private Bitmap gray_bf;//灰色背景
	private Bitmap btn;//  大饼
	private Bitmap bg_number;
	private Bitmap green_af;//蓝色选择区域
	private float bf_height;//背景的高度
	float scale_h;//画布缩放比例，x，y方向缩放比例一制
	Paint paint;
	private float span;
	private float CIRCLE_SCALE=(float)1/20;
	private int price_u=2000;
	private int price_d=0;
	private float CIRCLE_HALF;
	private Rect rect_u;
	private Rect rect_d;
	private float y_byprice_u;//通过价格获取的点中心位置坐标up
	private float y_byprice_d;
	private int btn_x;
	private int PRICEPADDING=15;//价格的与款的边距
	private boolean isUpClick=false;
	private boolean isDownClick=false;
	//车价格的区间价
		private final int FIRST_STAGE = 0;
		private final int SECOND_STAGE = 500;
		private final int THIRD_STAGE = 800;
		private final int FOUTH_STAGE = 900;
		private final int FIFTH_STAGE = 10000;
		/**要设置自定义控件的xml属性
		 * 第一步就是要让属性有get和set方法
		 * 第二步在values文件夹下新建一个attrs的xml在里面写需要手动设置的属性名和格式
		 * 第三步在main的xml中新建一个myandroid的一个地址前面复制上面的地址后面改成自己主包名
		 * 第四部在构造函数中解析sttrs的属性并设置给属性值
		 * */
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gray_bf=BitmapFactory.decodeResource(getResources(), R.drawable.axis_before);
		bg_number=BitmapFactory.decodeResource(getResources(), R.drawable.bg_number);
		green_af=BitmapFactory.decodeResource(getResources(),R.drawable.axis_after);
		btn=BitmapFactory.decodeResource(getResources(), R.drawable.btn);
		paint=new Paint();
		paint.setColor(Color.GRAY);
		
		//解析自定义属性
		TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.slidingbutton);		
		price_u=array.getInt(R.styleable.slidingbutton_price_u, 1000);
		price_d=array.getInt(R.styleable.slidingbutton_price_d, 500);
		
	}
		public int getPrice_u() {
			return price_u;
		}
		public void setPrice_u(int price_u) {
			this.price_u = price_u;
		}
		public int getPrice_d() {
			return price_d;
		}
		public void setPrice_d(int price_d) {
			this.price_d = price_d;
		}
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
	        bf_height=gray_bf.getHeight();//滑竿高度
	        
	        int measuredHeight=(int) ((heightMode==MeasureSpec.EXACTLY)?heightSize:bf_height);//判断是否为warpcontent
	        scale_h=(float)measuredHeight/bf_height;//画布缩放比例
	        int measuredWidth=2*measuredHeight/3;
	        span=(1-CIRCLE_SCALE)* bf_height/4;
	        CIRCLE_HALF=CIRCLE_SCALE/2*bf_height;
	        setMeasuredDimension(measuredWidth, measuredHeight);
	        
		}
		@Override
		protected void onDraw(Canvas canvas) {
			canvas.save();
			canvas.scale(scale_h, scale_h);
			int bf_x=  (int) ((this.getWidth()/scale_h-gray_bf.getWidth())/2);//滑竿x坐标
			Log.i("INFO","ddd"+bf_x+"------sssss"+scale_h);
			//画滑竿
			canvas.drawBitmap(gray_bf, bf_x,0, paint);
			// 画滑竿右边的数字
			String[] numbers=new String[]{
				"不限",
				String.valueOf(FOUTH_STAGE),
				String.valueOf(THIRD_STAGE),
				String.valueOf(SECOND_STAGE),
				"0",
			};
			paint.setTextSize(40/scale_h);
			for(int i=0;i<5;i++){
			float y =CIRCLE_SCALE* bf_height/2+i*span+paint.descent();//第i个坐标的y值
			canvas.drawText(numbers[i], 5*bf_x/4, y, paint);
			}
			//画大饼
			btn_x=(int)((this.getWidth()/scale_h-btn.getWidth())/2);//大饼的x坐标
			y_byprice_u=getbtnLocationByPrice(price_u);
			y_byprice_d=getbtnLocationByPrice(price_d);
			float btn_y_u=y_byprice_u-(float)btn.getHeight()/2;
			
			float btn_y_d=y_byprice_d-(float)btn.getHeight()/2;
			//画上大饼
			canvas.drawBitmap(btn, btn_x, btn_y_u, paint);
			//画下大饼
			canvas.drawBitmap(btn, btn_x, btn_y_d, paint);
			
			//画选择的蓝色价格区间
			Rect src=new Rect();
			src.left=0;
			src.top=(int) (y_byprice_u);
			src.right=green_af.getWidth();
			src.bottom=(int) (y_byprice_d);
			Rect dst = new Rect(bf_x,(int)(y_byprice_u),bf_x+green_af.getWidth(),(int)(y_byprice_d));
			
			
			canvas.drawBitmap(green_af, src, dst, paint);
			
			
			
			
			//画数字框
			rect_u=rectForNumberFromY(y_byprice_u);//上
			canvas.drawBitmap(bg_number, null, rect_u, paint);//画上数字框
			
			rect_d=rectForNumberFromY(y_byprice_d);//下
			canvas.drawBitmap(bg_number, null, rect_d, paint);
			
			//画数字框里面的文字
			int number_u_x=(int) ((3*rect_u.width()/4 - paint.measureText(String.valueOf(price_u)))/2);
			int number_u_y=(int)(rect_u.top-paint.ascent()+PRICEPADDING);
			int number_d_x=(int)(3*rect_d.width()/4-paint.measureText(String.valueOf(price_d)))/2;
			int number_d_y=(int)(rect_d.top-paint.ascent()+PRICEPADDING);
			canvas.drawText(String.valueOf(price_u),number_u_x, number_u_y, paint);
			canvas.drawText(String.valueOf(price_d),number_d_x, number_d_y, paint);
			
			
			canvas.restore();
			super.onDraw(canvas);
		}
		/**
		 * 通过价格确定的y的值，确定数字框的位置rect
		 * @param btn_y_u_1
		 * @return
		 */
		private Rect rectForNumberFromY(float btn_y_u_1) {
			Rect rect_up=new Rect();
			rect_up.left=0;
			rect_up.top=(int) (btn_y_u_1-(paint.descent()-paint.ascent())/2-PRICEPADDING);
			rect_up.right=(int) ((this.getWidth()/scale_h-btn.getWidth())/2);
			rect_up.bottom=(int) (btn_y_u_1+(paint.descent()-paint.ascent())/2+PRICEPADDING);
			
			return rect_up;
		}
		/**
		 *通过价格或许大饼的y坐标
		 * @param price_u2
		 * @author water
		 * @return
		 */
		private float getbtnLocationByPrice(float price_u2) {
			float y=0;
			if(price_u2>FIFTH_STAGE){				
				price_u2=FIFTH_STAGE;
				
				
			}
			if(price_u2<0){
				price_u2=0;
			}
			if(price_u2>=0&&price_u2<SECOND_STAGE){
				//价格区间在0-200元之间的时候y的值
				y=bf_height-(price_u2/(SECOND_STAGE-0)*span+CIRCLE_HALF);							
			}else if(price_u2>=SECOND_STAGE&&price_u2<THIRD_STAGE){
				y=bf_height-((price_u2-SECOND_STAGE)/(THIRD_STAGE-SECOND_STAGE)*span+CIRCLE_HALF+span);
			}else if(price_u2>=THIRD_STAGE&&price_u2<FOUTH_STAGE){
				y=bf_height-((price_u2-THIRD_STAGE)/(FOUTH_STAGE-THIRD_STAGE)*span+CIRCLE_HALF+2*span);
			}else{
				
				y=bf_height-((price_u2-FOUTH_STAGE)/(FIFTH_STAGE-FOUTH_STAGE)*span+CIRCLE_HALF+3*span);
				
			}
			
			
			
			
			
			
			return y;
		}
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			int action=event.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				float touch_x=event.getX()/scale_h;
				float touch_y=event.getY()/scale_h;
				if(touch_x>btn_x&&touch_x<btn_x+btn.getWidth()){
					if(touch_y>y_byprice_u-btn.getHeight()/2&&touch_y<y_byprice_u+btn.getHeight()/2){
						//说明上大饼被按住了
//						Toast.makeText(getContext(), "上大饼点到了", 2000).show();
						isUpClick=true;
						isDownClick=false;
					}
					if(touch_y>y_byprice_d-btn.getHeight()/2&&touch_y<y_byprice_d+btn.getHeight()/2){
						//说明下大饼被按住了
//						Toast.makeText(getContext(), "下大饼点到了", 2000).show();
						isDownClick=true;
						isUpClick=false;
					}
					
					
				}
				
				break;
			case MotionEvent.ACTION_MOVE:
				float price_y=event.getY()/scale_h;
				if(isUpClick){
					price_u=getPriceByLocation(price_y);
					//如果是上面价格小于下面价格就把下面价格给上面
					if(price_u<price_d){
						price_u = price_d;
					}
				}
				if(isDownClick){
					price_d=getPriceByLocation(price_y);
					if(price_d>price_u){
						price_d = price_u;
					}
				}
				this.invalidate();
				
				break;
			case MotionEvent.ACTION_UP:
				isUpClick=false;
				isDownClick=false;
				break;

			default:
				break;
			}
			return true;
		}
		/**
		 * 利用位置确定价格
		 * @param price_y
		 * @return
		 */
		private int getPriceByLocation(float price_y) {
			int pricebyy = 0;
		if(price_y<CIRCLE_HALF){
			pricebyy=FIFTH_STAGE;
		}
		
		if(price_y>CIRCLE_HALF&&price_y<CIRCLE_HALF+span){
			pricebyy=(int) ((1-(price_y-CIRCLE_HALF)/span)*(FIFTH_STAGE-FOUTH_STAGE)+FOUTH_STAGE);
		}
		if(price_y>CIRCLE_HALF+span&&price_y<CIRCLE_HALF+2*span){
			pricebyy=(int) ((1-(price_y-CIRCLE_HALF-span)/span)*(FOUTH_STAGE-THIRD_STAGE)+THIRD_STAGE);
		}
		if(price_y>CIRCLE_HALF+2*span&&price_y<CIRCLE_HALF+3*span){
			pricebyy=(int) ((1-(price_y-CIRCLE_HALF-2*span)/span)*(THIRD_STAGE-SECOND_STAGE)+SECOND_STAGE);
		}
		if(price_y>CIRCLE_HALF+3*span&&price_y<CIRCLE_HALF+4*span){
			pricebyy=(int) ((1-(price_y-CIRCLE_HALF-3*span)/span)*(SECOND_STAGE-0)+0);
		}
		if(price_y>CIRCLE_HALF+4*span){
			pricebyy=0;
		}
		//设置精度
		if(pricebyy<1000){
			int mol =pricebyy%20;
			if(mol>=10){
				
				pricebyy=pricebyy-mol+20;
			}else{
				pricebyy=pricebyy-mol;
			}
			
		}
		if(pricebyy>1000){
			int mol =pricebyy%1000;
			if(mol>=500){
				
				pricebyy=pricebyy-mol+1000;
			}else{
				pricebyy=pricebyy-mol;
			}
			
		}
		
		
		
		
			return pricebyy;
		}
}


