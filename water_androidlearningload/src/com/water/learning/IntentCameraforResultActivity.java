package com.water.learning;

import java.io.ByteArrayOutputStream;
import java.io.File;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class IntentCameraforResultActivity extends Activity implements OnClickListener {
	private ImageView iv;
	LinearLayout lacal_button;
	LinearLayout camera_button;
	public static final String IMAGE_ALL="image/*";
	public static final int LOCAL_IMAGE=0;
	public static final int TAKE_PHOTO=1;
	public static final int  PHOTO_RESULT=2;
	public static final String IMAGE_UNSPECIFIED="image/*";
	public static  String imageDir;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentcameraforresult);
        /**初始化控件及绑定监听*/
        iv=(ImageView) findViewById(R.id.avatar);
       lacal_button =(LinearLayout) findViewById(R.id.local_select_button);
       camera_button=(LinearLayout) findViewById(R.id.take_photo_button);
        //设置本地文件跳转
        lacal_button.setOnClickListener(this);
        //设置camera跳转
        camera_button.setOnClickListener(this);
    }
    /**监听按钮判定
     * @author water
     * 
     */
	public void onClick(View v) {
		if(v==lacal_button){
			//按本地按钮，实现本地的图片调用
			Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType(IMAGE_ALL);
			startActivityForResult(intent, LOCAL_IMAGE);		
		}
		if(v==camera_button){
			//完成相机调用
			imageDir="temp.jpg";
			Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg")));
			startActivityForResult(intent, TAKE_PHOTO);
		}	
	}
	/**onActivityResult回调
	 * 完成本地回传取值，和相机取值，给空间设定截面
	 * @author water
	 * 
	 * 
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==RESULT_OK){
			if(requestCode==LOCAL_IMAGE){
				photoZoom(data.getData());
			}
			if(requestCode==TAKE_PHOTO){
				File picture = new File(Environment.getExternalStorageDirectory() + "/" + imageDir);
                Log.i("INFO",picture.toString());
                photoZoom(Uri.fromFile(picture));
			}
			if(requestCode==PHOTO_RESULT){
				
				Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);
                    iv.setImageBitmap(photo);			
                					}
											}			
								}
		super.onActivityResult(requestCode, resultCode, data);
	}
	/**利用回传的uir给图片裁剪，并给主截面set值
	 * @author water
	 * @param uri
	 */
	private void photoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESULT);
		
	}
	
}
