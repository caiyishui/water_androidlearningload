package com.water.learning;
import com.water.learning.myview.SoundView;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
public class CustomViewOfSoundControlActivity extends Activity{
	private SoundView sv;
    private AudioManager am;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customviewofsoundcontrolactivity);
        sv=(SoundView) findViewById(R.id.sv);
        am=(AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
    		/**利用AudioManager下的adjustStreamVolume调节流的方法分别设置三个参数
    		 * 1.调节音量类型
    		 * 2.调节音量方向
    		 * 3.调节音量方式
    		 * 
    		 * */
    		am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
    		int currentVolumn =am.getStreamVolume(AudioManager.STREAM_MUSIC);
        	//最后刷新适配器
        	sv.currentVolumn =currentVolumn;
        	//刷新UI，会重新调用ondraw方法重绘
        	sv.invalidate();
        	return true;
    	}else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
    		am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
    		int currentVolumn =am.getStreamVolume(AudioManager.STREAM_MUSIC);
        	//最后刷新适配器
        	sv.currentVolumn =currentVolumn;
        	//刷新UI，会重新调用ondraw方法重绘
        	sv.invalidate();
        	return true;//可以不弹出系统音量提示
    	}
    	
    	return super.onKeyDown(keyCode, event);
    }
}
