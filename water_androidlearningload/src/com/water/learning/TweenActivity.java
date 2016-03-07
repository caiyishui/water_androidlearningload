package com.water.learning;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;



public class TweenActivity extends Activity {
	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweenactivity);
		
		iv = (ImageView) findViewById(R.id.iv);
	}

	
	public void play(View btn){
		//鍔犺浇鍔ㄧ敾
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.set);
		
		//寮�濮嬬殑瑙掑害銆佺粨鏉熺殑瑙掑害
		//鏃嬭浆鏃朵腑蹇冪偣X鍧愭爣鐨勭被鍨嬨�佷腑蹇冪偣X鍧愭爣鐨勫��
		//鏃嬭浆鏃朵腑蹇冪偣Y鍧愭爣鐨勭被鍨嬨�佷腑蹇冪偣Y鍧愭爣鐨勫��
		//RotateAnimation animation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		//璁剧疆鏃堕暱
		animation.setDuration(2000);
		iv.startAnimation(animation);
		
		
	}
}
