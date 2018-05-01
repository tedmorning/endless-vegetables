package com.socoGameEngine;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class Module {
	 int i_curID;
	 int i_preID;
	 int i_nextID = -1;
	private byte B_type;
	private byte B_state = -1;
	
	public byte getType(){
		return B_type;
	}
	public byte getState(){
		return B_state;
	}
	public void setState(byte _s){
		 B_state =_s;
	}
	public void setType(byte _s){
		B_type =_s;
	}
	
	public abstract boolean initialize() ;
	public abstract void paint(Canvas canvas) ;
	public abstract void run();
	public abstract boolean onKeyDown(int keyCode, KeyEvent msg);
	public abstract boolean onKeyUp(int keyCode, KeyEvent msg);
	public abstract void Release();
	public abstract void initwordpic();
	public void onreStart(){}; //重新开始
		/**
	      * 处理触屏事件
	      */
	public abstract void onTouchEvent(MotionEvent event) ;
}
