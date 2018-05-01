package com.facebook.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class FBUser {
	private String id;
	private String name;
	private IconCallback iconCallback;
	private Boolean isRequestingIcon = false;
	
	protected FBUser(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void getIcon (IconCallback callback){
		// ����������������
		if(FBInterface.allIconMap.containsKey(id)){
			callback.onComplete(this, FBInterface.allIconMap.get(id), null);
			return;
		} else if(!isRequestingIcon){
			this.iconCallback = callback;
			queryIconUrl();
		} else {
			callback.onComplete(this, null, null);
		}
	}
	
	private void queryIconUrl (){
		isRequestingIcon = true;
		String queryString = "SELECT pic_square FROM user WHERE uid = "+id;
		Bundle params = new Bundle();
		params.putString("q", queryString);
//		Request queryRequest = new Request(Session.getActiveSession(), "/fql", params, HttpMethod.GET, new Request.Callback() {
//			
//			@Override
//			public void onCompleted(Response response) {
//				GraphObject graphObject = response.getGraphObject();
//				if(graphObject != null && graphObject.getProperty("data") != null){
//					try{
//						JSONArray dataArray = new JSONArray(graphObject.getProperty("data").toString());
//						JSONObject dataObject = dataArray.getJSONObject(0);
////						requestIcon(dataObject.getString("pic_square"));
//						Thread netThread = new Thread(
//								new RequestIconThread(dataObject.getString("pic_square"), iconCallback));
//						netThread.start();
//						
//					}
//					catch(JSONException e) {
//						e.printStackTrace();
//					}
//				} else if(response.getError() != null){
//					
//				}
//			}
//		});
		//queryRequest.executeAsync();
	}
	
	public interface IconCallback
	{
		public void onComplete(FBUser user, Bitmap icon, Exception exception);
	}
	
	public class RequestIconThread implements Runnable
	{
		String url;
		IconCallback callback;
		public RequestIconThread(String url, IconCallback callback){
			this.url = url;
			this.callback = callback;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			URL imageUrl;
			Bitmap icon = null;
			try {
				imageUrl = new URL(url);
				HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream inputStream = conn.getInputStream();
				icon = BitmapFactory.decodeStream(inputStream);
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(icon !=null){
				FBInterface.allIconMap.put(id, icon);
			}
			callback.onComplete(FBUser.this, icon, null);
			FBUser.this.isRequestingIcon = false;
		}
	}
}
