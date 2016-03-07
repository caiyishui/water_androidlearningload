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
/**���Զ���ؼ���ͨ������*/
public class SoundView extends View {
	private Paint paint;
	private Bitmap gray;
	private Bitmap green;
	private int maxVolumn;
	public int currentVolumn;
	public AudioManager am;
	/**xml ������Ҫ��������*/
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		paint=new Paint();
		
		//ͨ��ͼƬid����ͼƬ
		gray=BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		green=BitmapFactory.decodeResource(getResources(), R.drawable.green);
		//ͨ��ϵͳ��ý���������ȡ��ý�������
		am=(AudioManager) context.getSystemService(context.AUDIO_SERVICE);
		maxVolumn =am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		//��ȡ��ǰ����ֵ
		currentVolumn =am.getStreamVolume(AudioManager.STREAM_MUSIC);		
	}
	//ͨ���˻����ؼ� canvasΪ����
	@Override
		protected void onDraw(Canvas canvas) {
		//�½�һ��top����ͼƬ��Y����
		int top=0;
		//����ɫ��Ŀ
		for(int i=0;i<maxVolumn-currentVolumn;i++){
			top=i*2*gray.getHeight();
			canvas.drawBitmap(gray,20,top, paint);
		}
		//��ɫ��Ŀ
		for(int i=maxVolumn-currentVolumn;i<maxVolumn;i++ ){
			top=i*2*green.getHeight();
			canvas.drawBitmap(green,20,top, paint);
		}
			
		
		super.onDraw(canvas);
		}

}
