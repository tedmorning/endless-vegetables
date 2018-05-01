package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameHailStone {
	
	private ArrayList<Sprite> cold;
	
	private boolean isShowTime;
	
	private int Plc[];
	
	private ArrayList<Integer> PlcId;
	
	private int level;
	
	public GameHailStone()
	{						
		cold = new ArrayList<Sprite>();
		
		isShowTime = false;
		
		Plc = new int[]{GameConfig.GameScreen_Width/6, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6,
				        GameConfig.GameScreen_Width/6, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*3,
				        GameConfig.GameScreen_Width/6, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*5,
				        GameConfig.GameScreen_Width/6*3, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6,
				        GameConfig.GameScreen_Width/6*3, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*3,
				        GameConfig.GameScreen_Width/6*3, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*5,
				        GameConfig.GameScreen_Width/6*5, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6,
				        GameConfig.GameScreen_Width/6*5, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*3,
				        GameConfig.GameScreen_Width/6*5, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*5};
		
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_COLD);
	}
	
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_COLD);
		
		for(int i=0;i<cold.size();i++)
		{						
			cold.get(i).recycleBitmap();
		}
	}
	
	public void setHailStone(int num, int _level)
	{				
		level	= _level;
		
		PlcId = new ArrayList<Integer>();
		
		for(int i=0;i<num;i++)
		{
			int tmp = ExternalMethods.throwDice(0, 8);
			
			int k = 0;
			
			while(k < PlcId.size())
			{
				if(tmp == PlcId.get(k))
				{
					tmp = ExternalMethods.throwDice(0, 8);
					
					k = 0;
				}
				else
				{
					k ++;
				}
			}
			
			PlcId.add(tmp);
		}		
		
		int t = 0;
		
		for(int i=0;i<PlcId.size();i++)
		{
			Sprite sprite = new Sprite();
			
			sprite.initSprite(CoolEditDefine.Effect_COLD, Plc[PlcId.get(i)*2], Plc[PlcId.get(i)*2+1], Sprite.SPRITE_STATE_NORMAL);
			
			t += GameLibrary.getIntRandom(3, 7);
			
			sprite.x -= t*10;
			sprite.y -= t*20;
			
			sprite.changeAction(0);
									
			cold.add(sprite);
		}		
	}
	
	private void move()
	{
		for(int i=0;i<cold.size();i++)
		{
			if(cold.get(i).getActionName()==0)
			{
				cold.get(i).x += 10;
				
				cold.get(i).y += 20;
				
				if(cold.get(i).x>=cold.get(i).org_x)
				{
					cold.get(i).x = cold.get(i).org_x;
					
					cold.get(i).changeAction(1);
					
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.ices_01, 0);
				}		
			}
		}	
	}
	
	public void setHailStone(boolean show)
	{
		isShowTime = show;
	}
	
	public boolean getHailStoneState()
	{
		return isShowTime;
	}
	
	public void paint(Canvas canvas)
	{
//		Paint paint = new Paint();
//		
//		paint.setColor(Color.RED);
//		
//		canvas.drawLine(0, (int)(200*GameConfig.f_zoomy), GameConfig.GameScreen_Width, (int)(200*GameConfig.f_zoomy), paint);
//		
//		canvas.drawLine(0, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*2, GameConfig.GameScreen_Width, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*2, paint);
//		
//		canvas.drawLine(0, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*4, GameConfig.GameScreen_Width, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*4, paint);
//		
//		canvas.drawLine(0, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*6, GameConfig.GameScreen_Width, (int)(200*GameConfig.f_zoomy)+GameConfig.GameScreen_Height/2/6*6, paint);
				
		if(!isShowTime)
			return;
		
		for(int i=0;i<cold.size();i++)
		{
			if(cold.get(i)!=null)
			{
				cold.get(i).paintSpriteShadow(canvas, 0, 1);							
			}
		}	
		
		for(int i=0;i<cold.size();i++)
		{
			if(cold.get(i)!=null)
			{				
				cold.get(i).paintSprite(canvas, 0, 0);
			}
		}				
	}
	
	public void updata()
	{
		if(!isShowTime)
			return;
		
		for(int i=0;i<cold.size();i++)
		{
			cold.get(i).updataSprite();
			
			if(cold.get(i).state == Sprite.SPRITE_STATE_NONE)
			{
				cold.remove(i);
			}
		}
		
		if(cold.size()==0)
		{
			isShowTime = false;
		}
		
		move();
	}		
	
	public void collision(Sprite sprite, GameMain gameMain)
	{	
		for(int i=0;i<cold.size();i++)
		{
			if(cold.get(i) != null && cold.get(i).state != Sprite.SPRITE_STATE_NONE && cold.get(i).getActionName() == 1)
			{
				if(sprite.kind!=CoolEditDefine.Enemy_YGYJF)
				{
					if(sprite.life>0)
					{
						if(ExternalMethods.RectCollision(sprite.getCollisionRect(), cold.get(i).getCollisionRect()))
						{
							if(!sprite.freezeState)
							{
								sprite.freezeState = true;
							
//								sprite.freezeTime = 150;
								
								if(level==1)
									sprite.freezeTime = 50;
								else if(level==2)
									sprite.freezeTime = 75;
								else if(level==3)
									sprite.freezeTime = 100;
								
								gameMain.gameMonster.getEffect().add(sprite, CoolEditDefine.Effect_ICESTATELV3, (int)sprite.x, (int)sprite.y, Sprite.SPRITE_STATE_NORMAL, sprite.freezeTime-25);
								
								gameMain.gameMonster.addAbnormalStateNumber();
							}
							
							if(sprite.kind!=CoolEditDefine.Enemy_YGY)
								GameMonster.centerMove = false;
						}
					}
				}
			}
		}
	}
}
