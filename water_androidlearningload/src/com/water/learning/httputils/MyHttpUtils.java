package com.water.learning.httputils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Map;

import com.water.learning.listener.MyHttpCallback;



import android.os.AsyncTask;
import android.util.Log;

public class MyHttpUtils {
	//连接互联网地址，数据，请求方式
	private String path;
	private Map<String,String> data;
	private String method;
	private MyHttpCallback callback;
	public MyHttpUtils(String path,Map<String,String> data,String method,MyHttpCallback callback){
		this.path = path;
		this.data = data;
		this.method = method;
		this.callback = callback;
	}
	
	
	public void doRequestByHttpUrlConnection(){
		MyHttpTask task = new MyHttpTask();
		task.execute(null,null);
	}


	private String doPostByHttpUrlConnection() {
		// TODO Auto-generated method stub
		String result = null;
		String errorResult = null;
		StringBuffer sb = new StringBuffer();
		//不需要问好
		for(Map.Entry<String, String> entry : data.entrySet()){
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		if(data.size()!= 0){
			sb.deleteCharAt(sb.length() - 1);
		}
		//写数据到服务端是通过流,需要把字符串转成字节数组
		byte[] entity = sb.toString().getBytes();
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorResult = "MalformedURLException";
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setConnectTimeout(5000);
			//设置文本长度
			conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
			conn.setDoOutput(true);//设置可以向外写数据
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorResult = "Connection Error";
		}
		OutputStream outputStream = null;
		try {
			outputStream = conn.getOutputStream();
			outputStream.write(entity);//写数据过去
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int statuCode;
		try {
			statuCode = conn.getResponseCode();
			if(statuCode == 200){
				//成功
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				result = reader.readLine();
				if(result!= null){
//					Log.i("INFO", result);
				}
				callback.onSuccess(result);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			callback.onFailure("IO Exception");
		}
		return result;
	}

	/**
	 * @author Danny
	 * 
	 */
	private String doGetByHttpUrlConnection() {
		//get请求
		String content = null;
		StringBuffer sb = new StringBuffer();
		
		sb.append("?");
		for(Map.Entry<String, String> entry : data.entrySet()){
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		path = path+sb.toString();
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();//开启连接
			conn.setRequestMethod(method);
			conn.setConnectTimeout(5000);
			int statuCode = conn.getResponseCode();//获取返回码
			if(statuCode == 200){
				//请求成功了
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				content = reader.readLine();
				if(content!= null){
//					Log.i("INFO", content);
				}
				callback.onSuccess(content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			callback.onFailure(content);
		}
		return content;
	}
	
	private class MyHttpTask extends AsyncTask<String,Void,String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(method.equals("GET")){
				return doGetByHttpUrlConnection();
			}else if(method.equals("POST")){
				return doPostByHttpUrlConnection();
			}else{
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}
}
