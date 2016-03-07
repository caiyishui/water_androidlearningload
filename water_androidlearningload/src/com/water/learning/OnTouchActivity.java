package com.water.learning;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class OnTouchActivity extends Activity {
	private ImageView img;
    private Bitmap bitmap;
    private Matrix matrix;//图片矩阵
    private float startX,startY;
    private PointF pointMid;
    private float oldDis,newDis;
    private float scale;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ontouchactivity);
        matrix = new Matrix();
        img = (ImageView) findViewById(R.id.img);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.liutao);
        img.setImageBitmap(bitmap);
        img.setImageMatrix(matrix);//设置imagevie的矩阵
     
    }
    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	int pointerCount = event.getPointerCount();
    	if(pointerCount == 1){
    		//处理移动逻辑
    		switch (event.getAction()) {
    		case MotionEvent.ACTION_DOWN:
    			startX = event.getX();
    			startY = event.getY();//当手指刚按下去的时候的坐标
    			break;
    		case MotionEvent.ACTION_MOVE:
				//移动
    			float x = event.getX();
    			float y = event.getY();
				matrix.postTranslate(x-startX, y -startY);
				startX = x;
				startY = y;
				img.setImageMatrix(matrix);//设置新的矩阵
				break;
    		case MotionEvent.ACTION_UP:
    			break;
			default:
				break;
			}
    	}else if(pointerCount ==2){
    		//缩放
    		switch (event.getAction()&MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_POINTER_DOWN:
				//第二根手指按下去
				oldDis = getDisByXY(event);
				//缩放的中心点
				pointMid = getMidPointByEvent(event);
				break;
			case MotionEvent.ACTION_MOVE:
				//两根手指移动
				newDis = getDisByXY(event);
				scale = newDis/oldDis;//缩放比例
				//矩阵进行缩放设置
				matrix.postScale(scale, scale, pointMid.x, pointMid.y);
				img.setImageMatrix(matrix);
				oldDis=newDis;
				break;
			default:
				break;
			}
    	}
    	return super.onTouchEvent(event);
    }

    private PointF getMidPointByEvent(MotionEvent event) {
    	PointF point = new PointF();
    	point.x = (event.getX()+event.getX(1))/2;
    	point.y = (event.getY()+event.getY(1))/2;
    	return point;
	}


	/**
     * 计算两点之间的距离
     * @param event
     */
	private float getDisByXY(MotionEvent event) {
		//第一个点
		float x = event.getX();
		float y = event.getY();
		
		//第二个点
		float x2 = event.getX(1);
		float y2 = event.getY(1);
		return FloatMath.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
		
	}
    
	



	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			Log.i("INFO", "move");
			break;

		default:
			break;
		}
		return false;
	}
}
