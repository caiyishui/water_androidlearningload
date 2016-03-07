package com.water.learning.constant;

import android.os.Environment;

public class MyConstant {
	/**手机根目录常量类,英文是拿到环境外部存储目录,获取绝对路径.toString()*/
	public static final String ROOT=Environment.getExternalStorageDirectory().getAbsolutePath().toString();
}
