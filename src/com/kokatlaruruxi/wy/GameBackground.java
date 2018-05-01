package com.kokatlaruruxi.wy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;

public class GameBackground {

	private Sprite bg;
	
	private boolean ShakeState;
	
	private int Shake_x;	
	
	private int ShakeTime;
	
	public void loadImage(String url)
	{		
		bg = new Sprite(GameImage.getImage(url));
	}
	
	public void delImage()
	{
		GameImage.delImage(bg.bitmap);
		
		bg.recycleBitmap();
	}
	
	public void init()
	{
		ShakeState = false;
		
		Shake_x = 0;	
		
		ShakeTime = 0;	
	}
	
	public void paint(Canvas canvas)
	{
		Paint paint = new Paint();
		
		if(bg!=null&&bg.bitmap!=null)
		bg.drawBitmap(canvas, bg.bitmap, Shake_x, Shake_x, null, GameLibrary.TL, paint);
	}
	
	public void startShake()
	{
		if(!ShakeState)
		{
			ShakeState = true;
			
			Shake_x = 0;	
			
			ShakeTime = 4;	
		}
	}
	
	public void updata()
	{
		if(ShakeState)
		{
			ShakeTime --;
			
			if(ShakeTime%2==0)
				Shake_x = -2;
			else
				Shake_x = 2;
			
			if(ShakeTime==0)
			{
				Shake_x = 0;
				
				ShakeState = false;
			}
		}
	}
}
