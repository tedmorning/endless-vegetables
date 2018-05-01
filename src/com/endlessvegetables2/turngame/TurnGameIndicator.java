package com.endlessvegetables2.turngame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;

public class TurnGameIndicator {
	
	private boolean isIndicator;
	
	private TurnGameSprite snipe;
	
	private int snipe_x;
	
	private int snipe_y;
	
	private boolean snipeState;
	
	public TurnGameIndicator()
	{
		isIndicator = false;
		
		snipe = new TurnGameSprite(GameImage.getImage("newEffect/bullet_aim"));
		
		snipeState = false;
	}
		
	public void delImage()
	{
		GameImage.delImage(snipe.bitmap);
		
		snipe.recycleBitmap();				
	}
	
	public void paint(Canvas canvas, int slingShot_x, int slingShot_y, int slingShotPiece_x, int slingShotPiece_y, int slingShotLen, float slingShotPieceDegree)
	{		
		if(isIndicator)
		{
			canvas.save();
			
			Paint paint = new Paint();
			
			//参数一为渐变起初点坐标x位置，参数二为y轴位置，参数三和四分辨对应渐变终点，最后参数为平铺方式，这里设置为镜像			
			LinearGradient lg=new LinearGradient(slingShot_x, slingShotPiece_y, 
					slingShot_x, slingShotPiece_y - 40*GameConfig.f_zoom - slingShotLen*2,Color.TRANSPARENT,Color.GRAY,TileMode.REPEAT);  
	
			paint.setShader(lg);
	
			paint.setStyle(Style.FILL);
			
			paint.setAlpha(120);
			
		    Path mPath=new Path();
		    mPath.moveTo(slingShot_x - ((130*GameConfig.f_zoom - slingShotLen)>20*GameConfig.f_zoom?20*GameConfig.f_zoom:(130*GameConfig.f_zoom - slingShotLen)), slingShotPiece_y); 
		    mPath.lineTo(slingShot_x - ((125*GameConfig.f_zoom - slingShotLen)>15*GameConfig.f_zoom?15*GameConfig.f_zoom:(125*GameConfig.f_zoom - slingShotLen)), slingShotPiece_y - slingShotLen*2); 
		    mPath.lineTo(slingShot_x - ((135*GameConfig.f_zoom - slingShotLen)>30*GameConfig.f_zoom?30*GameConfig.f_zoom:(135*GameConfig.f_zoom - slingShotLen)), slingShotPiece_y - slingShotLen*2); 
		    mPath.lineTo(slingShot_x, slingShotPiece_y - 40*GameConfig.f_zoom - slingShotLen*2);  
		    mPath.lineTo(slingShot_x + ((135*GameConfig.f_zoom - slingShotLen)>30*GameConfig.f_zoom?30*GameConfig.f_zoom:(135*GameConfig.f_zoom - slingShotLen)), slingShotPiece_y - slingShotLen*2); 
		    mPath.lineTo(slingShot_x + ((125*GameConfig.f_zoom - slingShotLen)>15*GameConfig.f_zoom?15*GameConfig.f_zoom:(125*GameConfig.f_zoom - slingShotLen)), slingShotPiece_y - slingShotLen*2); 
		    mPath.lineTo(slingShot_x + ((130*GameConfig.f_zoom - slingShotLen)>20*GameConfig.f_zoom?20*GameConfig.f_zoom:(130*GameConfig.f_zoom - slingShotLen)), slingShotPiece_y); 
		    mPath.close(); 
		    
		    canvas.rotate(-(slingShotPieceDegree-270), slingShot_x, slingShot_y);
		    
		    canvas.drawPath(mPath, paint);
		    
		    canvas.restore();		    
		}				
		
		 drawSnipe(canvas, slingShot_x, slingShot_y, slingShotPiece_x, slingShotPiece_y, slingShotLen, slingShotPieceDegree);
	}
	
	private void drawSnipe(Canvas canvas, int slingShot_x, int slingShot_y, int slingShotPiece_x, int slingShotPiece_y, int slingShotLen, float slingShotPieceDegree)
	{
		if(snipeState)
		{		
			Paint paint = new Paint();
			
			int x = slingShot_x+(int)ExternalMethods.getAngleX(slingShotPieceDegree, slingShotLen*4.5f);
			int y = slingShot_y+(int)ExternalMethods.getAngleY(slingShotPieceDegree, slingShotLen*4.5f);						
			
			snipe_x = GameConfig.GameScreen_Width - x - snipe.bitmap.getWidth()/2;
			
			if(snipe_x<=-snipe.bitmap.getWidth()/2)
			{
				snipe_x=-snipe.bitmap.getWidth()/2;
			}
			else if(snipe_x>=GameConfig.GameScreen_Width-snipe.bitmap.getWidth()/2)
			{
				snipe_x=GameConfig.GameScreen_Width-snipe.bitmap.getWidth()/2;
			}
			
			snipe_y = slingShot_y - (y - slingShot_y);						
			
			if(snipe_y>slingShot_y-120*GameConfig.f_zoom)
			{
				snipe_y=(int)(slingShot_y-120*GameConfig.f_zoom);
			}
			
			snipe.drawBitmap(canvas, snipe.bitmap, snipe_x, snipe_y, paint);							
		}
	}
	
	
	public int getSnipe_x()
	{
		return snipe_x+snipe.bitmap.getWidth()/2;
	}
	
	public int getSnipe_y()
	{
		return snipe_y+snipe.bitmap.getHeight()/2;
	}
	
	public void setIndicatorState(boolean state)
	{
		isIndicator = state;
	}
	
	public void setSnipeState(boolean state)
	{
		snipeState = state;
	}
}
