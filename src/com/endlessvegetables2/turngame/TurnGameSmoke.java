package com.endlessvegetables2.turngame;

import java.util.ArrayList;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class TurnGameSmoke {

	private TurnGameSprite smokeBm;
	
	private int Plc[];
	
	private ArrayList<Smoke> smokeList;
	
	private int showTime;
	
	private final int smokeNumber = 9;
	
	private boolean isShow;
	
	public TurnGameSmoke()
	{
		isShow = false;
		
		smokeList = new ArrayList<Smoke>();
		
		smokeBm = new TurnGameSprite(GameImage.getImage("newEffect/skill_smoke"));
		
		showTime = 0;
		
		Plc = new int[]{(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2, (int)(80*GameConfig.f_zoomy),
						(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2+GameConfig.GameScreen_Width/3, (int)(80*GameConfig.f_zoomy),
						(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2+GameConfig.GameScreen_Width/3*2, (int)(80*GameConfig.f_zoomy),
						        
						(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2, (int)(80*GameConfig.f_zoomy)+smokeBm.bitmap.getHeight()/2,
						(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2+GameConfig.GameScreen_Width/3, (int)(80*GameConfig.f_zoomy)+smokeBm.bitmap.getHeight()/2,
						(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2+GameConfig.GameScreen_Width/3*2, (int)(80*GameConfig.f_zoomy)+smokeBm.bitmap.getHeight()/2,
						
						(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2, (int)(80*GameConfig.f_zoomy)+smokeBm.bitmap.getHeight(),
						(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2+GameConfig.GameScreen_Width/3, (int)(80*GameConfig.f_zoomy)+smokeBm.bitmap.getHeight(),
						(GameConfig.GameScreen_Width/3-smokeBm.bitmap.getWidth())/2+GameConfig.GameScreen_Width/3*2, (int)(80*GameConfig.f_zoomy)+smokeBm.bitmap.getHeight(),										
						};
		
	}
	
	public void delImage()
	{
		GameImage.delImage(smokeBm.bitmap);
		
		smokeBm.recycleBitmap();
	}
	
	public void setShow(boolean show)
	{
		isShow = show;
	}	
	
	Paint paint = new Paint();
	
	public void paint(Canvas canvas)
	{		
		if(!isShow)
			return;
		
		for(int i=0;i<smokeList.size();i++)
		{			
			paint.setAlpha(smokeList.get(i).alpha);
			
			smokeBm.drawBitmap(canvas, smokeBm.bitmap, Plc[smokeList.get(i).PlcId*2]+smokeList.get(i).moveOffset, Plc[smokeList.get(i).PlcId*2+1], paint);
		}
//		
//		ExternalMethods.drawImage(canvas, smokeBm.bitmap, Plc[i*2], Plc[i*2+1], ExternalMethods.getFloatRandom(1, 2), ExternalMethods.getFloatRandom(1, 2), GameLibrary.getIntRandom(150, 255), 0, 0, 0);
		
//		for(int i=0;i<smokeList.size();i++)
//		{		
//			if(smokeList.get(i).state <= 2)
//			ExternalMethods.drawImage(canvas, smokeBm.bitmap, smokeList.get(i).x+smokeList.get(i).moveOffset, smokeList.get(i).y, smokeList.get(i).size, smokeList.get(i).size, smokeList.get(i).alpha, 0, 0, 0);
//		}
		
//		for(int i=0;i<smokeList.size();i++)
//		{
//			paint.setAlpha(smokeList.get(i).alpha);
//			
//			smokeBm.drawBitmap(canvas, smokeBm.bitmap, smokeList.get(i).x+smokeList.get(i).moveOffset, smokeList.get(i).y, paint);
//			
//			paint.reset();
//		}		
	}
	
	public void updata(TurnGameMain gameMain)
	{		
		if(!isShow)
			return;
		
		//教学
		if(gameMain.gameTeaching.pauseState())		
			return;		
		
		showTime ++;
		
		if(showTime>=75)
		{
			add(gameMain);
			
			showTime = 0;
		}
		
		somkeMove(gameMain);
	}
	
	private void somkeMove(TurnGameMain gameMain)
	{
		for(int i=0;i<smokeList.size();i++)
		{					
			if(smokeList.get(i).waitTime<=0)
			{
				smokeList.get(i).waitTime = 2;
								
				if(smokeList.get(i).move)
				{
					smokeList.get(i).moveOffset ++;	
	
					if(smokeList.get(i).moveOffset>=20)
					{
						smokeList.get(i).move = false;										
					}
				}
				else if(!smokeList.get(i).move)
				{
					smokeList.get(i).moveOffset --;
	
					if(smokeList.get(i).moveOffset<=-20)
					{
						smokeList.get(i).move = true;				
					}
				}									
			}
			else
				smokeList.get(i).waitTime --;
			
			if(smokeList.get(i).state == 0)
			{
				smokeList.get(i).alpha += 20;
				
				if(smokeList.get(i).alpha>=255)
				{
					smokeList.get(i).alpha = 255;
					
					smokeList.get(i).state = 1;
					
//					//教学
//					if(!gameMain.gameTeaching.teachingArrary[GameTeaching.TEACH_VOL44])
//					{							
//						int x = Plc[smokeList.get(i).PlcId*2]+smokeBm.bitmap.getWidth()/2;
//						
//						int y = Plc[smokeList.get(i).PlcId*2+1]+smokeBm.bitmap.getHeight()/2;
//						
//						gameMain.gameTeaching.setGameTeaching(GameTeaching.TEACH_VOL44, x, y, LangUtil.getLangString(LangDefineClient.GUIDE_44), GameTeaching.HAND_MOVE_STATE_2, (int)(700*GameConfig.f_zoomy));
//					}
				}
			}
			else if(smokeList.get(i).state == 2)
			{
				smokeList.get(i).alpha -= 20;
				
				if(smokeList.get(i).alpha<=0)
				{
					smokeList.get(i).alpha = 0;
					
					smokeList.get(i).state = 3;
				}
			}
			else if(smokeList.get(i).state == 3)
			{
				smokeList.remove(i);			
			}
		}
	}
	
	public void add(TurnGameMain gameMain)
	{
		if(smokeList.size()>=smokeNumber)
			return;
		
		Smoke smoke = new Smoke();
		
//		smoke.size = ExternalMethods.getFloatRandom(1, 2);
//		
		smoke.w = smokeBm.bitmap.getWidth();/*(int)(smokeBm.bitmap.getWidth()*smoke.size)*/
		
		smoke.h = smokeBm.bitmap.getHeight();/*(int)(smokeBm.bitmap.getHeight()*smoke.size)*/
//		
//		smoke.x = GameLibrary.getIntRandom(0, GameConfig.GameScreen_Width-smoke.w);
//		
//		smoke.y = GameLibrary.getIntRandom((int)(160*GameConfig.f_zoomy), gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-smoke.h);
			
		smoke.PlcId = GameLibrary.getIntRandom(0,8);
				
		int k = 0;
		
		while(k < smokeList.size())
		{
			if(smoke.PlcId == smokeList.get(k).PlcId)
			{				
				smoke.PlcId = ExternalMethods.throwDice(0, 8);
				
				k = 0;
			}
			else
			{
				k ++;
			}
		}
		
//		if(!gameMain.gameTeaching.teachingArrary[GameTeaching.TEACH_VOL44])
//		{
//			smoke.PlcId = 4;					
//		}
		
		smoke.alpha = 0;
		
		smoke.state = 0;
		
		smoke.move = false;
		
		smoke.moveOffset = 0;
		
		smoke.waitTime = 2;
		
		smokeList.add(smoke);
		
		if(!VeggiesData.isMuteSound())
		GameMedia.playSound(R.raw.fogs, 0);
	}
	
	public void onTouchEvent(MotionEvent event, TurnGameMain gameMain)
	{
		int pointx = (int) event.getX();
		int pointy = (int) event.getY();
		
		if(event.getAction() == MotionEvent.ACTION_MOVE)
		{
			for(int i=0;i<smokeList.size();i++)
			{
				if(smokeList.get(i).state<2)
				{
					if(pointx>=Plc[smokeList.get(i).PlcId*2]&&
					   pointx<=Plc[smokeList.get(i).PlcId*2]+smokeList.get(i).w&&
					   pointy>=Plc[smokeList.get(i).PlcId*2+1]&&
					   pointy<=Plc[smokeList.get(i).PlcId*2+1]+smokeList.get(i).h)
					{
						smokeList.get(i).state = 2;
						
//						//教学
//						if(!gameMain.gameTeaching.teachingArrary[GameTeaching.TEACH_VOL44])
//						{						
//							gameMain.gameTeaching.finish();							
//						}
						
						break;
					}
				}
			}
		}		
	}
	
	private class Smoke
	{
//		public int x;
//		
//		public int y;
//		
		public int w;
		
		public int h;
		
		public int PlcId;
		
		public int alpha;
		
//		public float size;
		
		public byte state;
		
		public boolean move;
		
		public int moveOffset;
		
		public int waitTime;
	}
}
