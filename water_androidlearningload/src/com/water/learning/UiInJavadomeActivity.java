package com.water.learning;



import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UiInJavadomeActivity extends Activity {
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.main);
	        /**��LayoutParams*/
	        LinearLayout.LayoutParams fw=(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	        LinearLayout.LayoutParams fw1=(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT,1));
	        LinearLayout.LayoutParams ff=(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	        LinearLayout.LayoutParams ww=(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	        LinearLayout.LayoutParams wf=(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
	        LinearLayout.LayoutParams fwc=(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	        fwc.gravity=Gravity.CENTER;
	        /**����ll-all*/
	        LinearLayout ll_all = new LinearLayout(this);
	        ll_all.setLayoutParams(ff);
	        ll_all.setOrientation(LinearLayout.VERTICAL);
	        /**����ll-top*/
	        LinearLayout ll_top = new LinearLayout(this);
	        ll_top.setLayoutParams(fw);
	        /**����editview*/
	        EditText et=new EditText(this);
	        et.setLayoutParams(fw1);
	      
	        /**����button*/
	        Button bt=new Button(this);
	        bt.setLayoutParams(ww);
	        bt.setText(this.getString(R.string.bt1));
	        /**����ll_under*/
	        LinearLayout ll_under = new  LinearLayout(this);
	        ll_under.setLayoutParams(ff);
	       
	        /**����imageview*/
	        ImageView img=new ImageView(this); 
	        img.setLayoutParams(fwc);
	        img.setImageResource(R.drawable.ic_launcher);
	       
	       
	        /**��ӿؼ�*/
	        ll_top.addView(et);
	        ll_top.addView(bt);
	        ll_under.addView(img);
	        ll_all.addView(ll_top);
	        ll_all.addView(ll_under);
	        setContentView(ll_all);
	    }
}
