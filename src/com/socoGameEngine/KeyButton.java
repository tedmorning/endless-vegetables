package com.socoGameEngine;

import android.view.MotionEvent;


public class KeyButton{//按键类保存按键的坐标 序列 状态
	public float i_x;
	public float i_y;
	public int i_pointindex;
	public int i_state;
	
	public KeyButton(int _pointindex,float x,float y, int state){
		
		reset(_pointindex, x, y,  state);
		
	}
	public void reset(int _pointindex,float x,float y, int state){//重置按键
		i_x = x;
		i_y = y;
		i_pointindex = _pointindex;
		i_state = state;
//		Log.dln("i_state="+state);
	}
	
	public boolean isPressedUp(){//判断按键是否被抬起
	
//		Log.dln("i_pointindex="+i_pointindex+" i_state="+i_state);
		if((i_state== MotionEvent.ACTION_POINTER_1_UP&&i_pointindex == 0)
				||i_state == MotionEvent.ACTION_UP
				||i_state == MotionEvent.ACTION_POINTER_UP
				||(i_state == MotionEvent.ACTION_POINTER_2_UP&&i_pointindex == 1)
				||i_pointindex>=2
				){
			return true;
		}
		else{
			return false;
		}
	}
}