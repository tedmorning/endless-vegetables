package com.tools;

import android.graphics.Bitmap;

/**
 * һЩ���÷���ֵ
 * */
public class GameTools {

	
	/**
	 * ����λ�ÿ�
	 * */
	public static int getLengthWidth(Bitmap bg, Bitmap bitmap, String size){
		
		if(bg ==null || bitmap==null || size.length()==0)return 0;
		
		int leng = 0;
		leng = (bg.getWidth() - (size.length() * bitmap.getWidth() + (size.length()-1)))>>1;
		
		return leng;
	}
	
	/**
	 * ����λ�ÿ�
	 * */
	public static int getLengthWidth(int width,  Bitmap bitmap, String size){
		
		if(width ==0 || bitmap==null || size.length()==0)return 0;
		
		int leng = 0;
		leng = (width - (size.length() * bitmap.getWidth() + (size.length()-1)))>>1;
		
		return leng;
	}
	
	
	/**
	 * ����λ�ø�
	 * */
	public static int getLengthHight(Bitmap bg, Bitmap bitmap){
		
		if(bg ==null || bitmap==null )return 0;
		
		int leng = 0;
		leng = (bg.getHeight() - bitmap.getHeight())>>1;
		
		return leng;
	}
	
	
}//end class
