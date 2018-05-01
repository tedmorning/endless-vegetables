package com.endlessvegetables2.ui;

import android.graphics.Bitmap;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Sprite;

public class Friend {
	public int index;
	public String name;
	public long score;
	public int rank;
	public Sprite icon;
	public String fbID;
	public Friend(int index, String name, long score, int rank, Bitmap icon) {
		super();
		this.index = index;
		this.name = name;
		this.score = score;//ExternalMethods.LongToStr_En(score);
		this.rank = rank;
		this.icon = new Sprite(icon);
	}
	public void setIcon(Bitmap icon){
		this.icon = new Sprite(icon);
	}
	public void setfbID(String fbID){
		this.fbID = fbID;
	}
}
