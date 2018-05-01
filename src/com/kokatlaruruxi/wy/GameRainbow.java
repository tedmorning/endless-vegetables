package com.kokatlaruruxi.wy;

import com.socoGameEngine.GameConfig;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;

public class GameRainbow {
	
	private boolean isShow;
	
	private Paint paint;
	
	private int agree;
	
	private final int[] color = {255,  0,  0,
							255,255,  0,
							153,255,  0,
							  0,153,255,
							102, 51,255};
	
	private int[] colorChange;
	
	private int colorIndex;
	
	private final int[] rainbowWidth = {16,6,14,10,10};
	
	public GameRainbow()
	{
		paint = new Paint();
		
		agree = 360;
		
		colorIndex = 0;
		
		colorChange = new int[color.length];
		
		for(int i=0;i<color.length;i++)		
		colorChange[i] = color[i];		
	}
	
	public void paint(Canvas canvas)
	{	
//		红 255 0 0
//		黄 255 255 0
//		绿 153 255 0
//		蓝 0 153 255
//		紫 102 51 255
		
		drawRainbow(canvas, rainbowWidth, colorChange, (int)(200*GameConfig.f_zoomy), (int)(200*GameConfig.f_zoomy), (int)(100*GameConfig.f_zoomy), agree);
	}
	
	/*
	 * 彩虹颜色为从外到里
	 * */
	private void drawRainbow(Canvas canvas, int[] rainbowWidth, int[] color, int x, int y, int size, int _agree)
	{
		canvas.save();	
		
		int alpha = 180;
		
		RectF oval = new RectF();
		
		int widthSum = 0;
		
		for(int i=0;i<rainbowWidth.length;i++)		
			widthSum += rainbowWidth[i];		
		
		oval.left = x - widthSum*2;
		oval.top = y - size/2 - widthSum/2;
		oval.right = x + size + widthSum*2;
		oval.bottom = y - size/2 + size + widthSum/2;				
		
		Path path = new Path();
		
		path.addArc(oval, 90, _agree);			
		
		canvas.clipPath(path);
		
		paint.setStyle(Style.STROKE);
					
		for(int i=1;i<rainbowWidth.length;i++)	
		{
			widthSum = 0;
			
			for(int j=i;j<rainbowWidth.length;j++)		
			widthSum += rainbowWidth[j];
			
			oval.left = x - widthSum/2;
			oval.top = y - widthSum/2;
			oval.right = x + size + widthSum/2;
			oval.bottom = y + size + widthSum/2;
								
			paint.setStrokeWidth(rainbowWidth[i-1]);
			
			paint.setColor(Color.rgb(color[(i-1)*3],color[(i-1)*3+1],color[(i-1)*3+2]));
			
			paint.setAlpha(alpha);
			
			canvas.drawArc(oval, 0, 360, true, paint);
		}
		
		canvas.restore();
	}
	
	public void changeColor()
	{				
		for(int i=0;i<color.length;i++)
		{
			colorChange[i] = color[i];
		}
		
		colorChange[colorIndex*3] = 255;
		
		colorChange[colorIndex*3+1] = 255;
		
		colorChange[colorIndex*3+2] = 255;
		
		colorIndex ++;
		
		if(colorIndex>=colorChange.length/3)
			colorIndex = 0;
	}
	
	public void updata()
	{
		changeColor();
		
//		agree += 15;
//		
//		if(agree>=360)
//			agree = 0;
	}
	
	public void collision()
	{
		
	}
}
