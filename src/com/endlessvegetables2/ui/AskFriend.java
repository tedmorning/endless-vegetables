package com.endlessvegetables2.ui;

import com.kokatlaruruxi.wy.Sprite;

import android.graphics.Bitmap;

public class AskFriend {
	public int index;
	public String name;
	public String url;
	public Bitmap bmp;
	public long endTime;
	public long cd;
	public long uid;
	public String facebookId;
	
	private long currenttime;
	public String s_time;
	public AskFriend(long _uid, int index,String name, Bitmap bmp) {
		super();
		this.index = index;
		this.name = name;
		this.bmp = bmp;
		uid = _uid;
	}
	public AskFriend(long _uid, String facebookid, int index,String name, Bitmap bmp) {
		super();
		this.index = index;
		this.name = name;
		this.bmp = bmp;
		uid = _uid;
		facebookId = facebookid;
	}
	public AskFriend(long _uid, int index, String name, long endTime) {
		super();
		this.index = index;
		this.name = name;
		this.endTime = endTime;
		uid = _uid;
	}
	public AskFriend(long _uid, int index, String name, String url, Bitmap bmp) {
		this.index = index;
		this.name = name;
		this.url = url;
		this.bmp = bmp;
		uid = _uid;
	}
	public void setIcon(Bitmap icon){
		this.bmp =  icon;
	}
	public void setendTime(long endTime){
		this.endTime = endTime;
	}
	public void runTime(){
		currenttime = System.currentTimeMillis();
		String _time = "";
		long timeee = endTime;
		 
//		long cd; 
		if (timeee>currenttime && timeee - currenttime  > 0) {
			_time = "";
			cd = (timeee - currenttime);
			long tempCD = cd / 1000;
			if (tempCD % 60 == 0)
				_time = "00";
			else if (tempCD % 60 < 10)
				_time = "0" + tempCD % 60;
			else
				_time = _time + tempCD % 60;
			tempCD = tempCD / 60;
			if (tempCD % 60 == 0)
				_time = "00:" + _time;
			else if (tempCD % 60 < 10)
				_time = "0" + tempCD % 60 + ":" + _time;
			else
				_time = tempCD % 60 + ":" + _time;
		} else { 
			_time = "00:00";
			cd = 0;
		//ÊÕ³É
		}
		s_time = _time;
		
	}
}
