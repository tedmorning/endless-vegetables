package com.endlessvegetables2.turngame;

import com.socoGameEngine.GameConfig;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class TurnGameSirenLight {

	private Paint paint;
	
	private boolean isShow;
	
	private int x;
	
	private int y;
	
	private int Agree;
	
	public TurnGameSirenLight()
	{
		paint = new Paint();
		
		isShow = false;
		
		Agree = 0;
	}
	
	public void setXY(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	public void setShow(boolean _isShow)
	{
		isShow = _isShow;
		
		Agree = 0;
	}
	
	public void run()
	{
		if(!isShow)
		  return;
		
		Agree += 2;
		
		if(Agree>=24)
		{
			Agree = 24;
		}
	}
	
	public void draw(Canvas canvas)
	{
		if(!isShow)
		  return;
		
		paint.setColor(Color.YELLOW);
		
		paint.setAlpha(80);
		
		RectF oval = new RectF();
		
		oval.left =  x-120*GameConfig.f_zoomx;
		oval.top = y-120*GameConfig.f_zoomy;
		oval.right = x+120*GameConfig.f_zoomx;	
		oval.bottom = y+120*GameConfig.f_zoomy;
		
		canvas.drawArc(oval, 90-Agree, Agree*2, true, paint);
	}	
}