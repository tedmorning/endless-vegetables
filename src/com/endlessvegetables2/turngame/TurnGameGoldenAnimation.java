package com.endlessvegetables2.turngame;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class TurnGameGoldenAnimation
{	
	public static final int GOLDEN_TYPE = 0;	
	public static final int GEM_TYPE = 1;
	
	private boolean goldenState;
		
	private int e_x;
	private int e_y;
	
	private int gNum;
	
	private TurnGameSprite sprite;
	
	private int waitingTime;		
	
	private int type;
	
	private boolean moveXdic;
	
	private final int JumpH[] = {60,33,20,13,6,0,
								 0,4,10,18,24,18,10,4,0,3,6,12,6,3,0,3,6,3,
								 0};				
	
	public void setGoldenAnimation(int x, int y, int end_x, int end_y, int num, int _type)
	{
		type = _type;
		
		sprite = new TurnGameSprite();
		
		sprite.initSprite(type==GOLDEN_TYPE?CoolEditDefine.Effect_GOLDEN:CoolEditDefine.Effect_GEM, x, y, TurnGameSprite.SPRITE_STATE_NORMAL);						
		
		sprite.changeAction(0);
		
		e_x = end_x;
		
		e_y = end_y;
		
		goldenState = true;
		
		gNum = num;
		
		waitingTime = GameLibrary.getIntRandom(5, 10);
		
		waitingTime = JumpH.length;
		
		moveXdic = GameLibrary.getIntRandom(0, 1)==0?true:false;
		
		if(type==GOLDEN_TYPE)
		{
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.coinsdowns, 0);
		}
		else
		{
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.gemdowns, 0);
		}		
	}
	
	public void updata(TurnGameMain gameMain)
	{
		sprite.updataSprite();
		
		if(waitingTime>0)		
		{
			sprite.y = sprite.org_y;
			
			sprite.y -= JumpH[JumpH.length-waitingTime];
			
			if(JumpH[JumpH.length-waitingTime]>0)
			{
				if(moveXdic)
					sprite.x --;
				else
					sprite.x ++;
			}
			
			waitingTime --;	
			
			return;
		}				
		
		if(goldenState)
		{				
			if(sprite.x<e_x)
			{
				sprite.x+=30;
				
				if(sprite.x>=e_x)
				{
					sprite.x = e_x;
				}
			}
			else if(sprite.x>e_x)
			{
				sprite.x-=30;
				
				if(sprite.x<=e_x)
				{
					sprite.x = e_x;
				}
			}
			
			if(sprite.y>e_y)
			{
				sprite.y-=30;
				
				if(sprite.y<=e_y)
				{
					sprite.y = e_y;
				}
			}
			
//			if(sprite.size>0.5f)
//			{
//				sprite.size -= 0.01f;
//			}
//			
//			if(sprite.Alpha>120)
//			{
//				sprite.Alpha -= 10;
//			}
							
			if(sprite.x==e_x&&sprite.y<=e_y)
			{
				goldenState = false;		
				
				if(type==GOLDEN_TYPE)
				{
					gameMain.goldenNumber += gNum;
					
					gameMain.gameUI.setGoldNumber(gameMain.goldenNumber);
					
					gameMain.getGoldenNumber += gNum;					
				}
				else
				{					
					gameMain.gemNumber += gNum;	
					
					gameMain.gameUI.setGemNumber(gameMain.gemNumber);
					
					gameMain.getGemNumber += gNum;
				}
								
				if(type==GOLDEN_TYPE)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.coinspicks, 0);
				}
				else
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.gempicks, 0);
				}
			}										
		}
	}
	
	public boolean getGoldenState()
	{
		return goldenState;
	}
	
	public void drawGoldenAnimation(Canvas canvas)
	{			
		sprite.paintSprite(canvas, 0, 0);												
	}		
}
