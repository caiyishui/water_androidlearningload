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
    		/**����AudioManager�µ�adjustStreamVolume�������ķ����ֱ�������������
    		 * 1.������������
    		 * 2.������������
    		 * 3.����������ʽ
    		 * 
    		 * */
    		am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
    		int currentVolumn =am.getStreamVolume(AudioManager.STREAM_MUSIC);
        	//���ˢ��������
        	sv.currentVolumn =currentVolumn;
        	//ˢ��UI�������µ���ondraw�����ػ�
        	sv.invalidate();
        	return true;
    	}else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
    		am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
    		int currentVolumn =am.getStreamVolume(AudioManager.STREAM_MUSIC);
        	//���ˢ��������
        	sv.currentVolumn =currentVolumn;
        	//ˢ��UI�������µ���ondraw�����ػ�
        	sv.invalidate();
        	return true;//���Բ�����ϵͳ������ʾ
    	}
    	
    	return super.onKeyDown(keyCode, event);
    }
}
