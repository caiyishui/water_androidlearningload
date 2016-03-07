package com.water.learning.myview;
import com.water.learning.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
/**简单自定义控件，通过绘制*/
public class SoundView extends View {
	private Paint paint;
	private Bitmap gray;
	private Bitmap green;
	private int maxVolumn;
	public int currentVolumn;
	public AudioManager am;
	/**xml 解析需要两个参数*/
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		paint=new Paint();
		
		//通过图片id加载图片
		gray=BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		green=BitmapFactory.decodeResource(getResources(), R.drawable.green);
		//通过系统多媒体管理器获取多媒体的音量
		am=(AudioManager) context.getSystemService(context.AUDIO_SERVICE);
		maxVolumn =am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//获取当前音量值
		currentVolumn =am.getStreamVolume(AudioManager.STREAM_MUSIC);		
	}
	//通过此画出控件 canvas为画布
	@Override
		protected void onDraw(Canvas canvas) {
		//新建一个top绘制图片的Y坐标
		int top=0;
		//画灰色条目
		for(int i=0;i<maxVolumn-currentVolumn;i++){
			top=i*2*gray.getHeight();
			canvas.drawBitmap(gray,20,top, paint);
		}
		//绿色条目
		for(int i=maxVolumn-currentVolumn;i<maxVolumn;i++ ){
			top=i*2*green.getHeight();
			canvas.drawBitmap(green,20,top, paint);
		}
			
		
		super.onDraw(canvas);
		}

}
