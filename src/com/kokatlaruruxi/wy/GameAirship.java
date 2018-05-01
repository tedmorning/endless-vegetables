package com.kokatlaruruxi.wy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

public class GameAirship {

	private Sprite[] bloom;
	
	private Sprite airship;
	
	private int airship_x;
	
	private int airship_y;
	
	private boolean isShow;
	
	private int times;
	
	private int offset;
	
	public GameAirship(int lev)
	{				
		isShow = false;
		
		if(lev == 1)
		{
			airship = new Sprite(GameImage.getImage("newEffect/airship"));
		}
		else if(lev == 2)
		{
			airship = new Sprite(GameImage.getImage("newEffect/airship_2"));
		}
		else if(lev == 3)
		{
			airship = new Sprite(GameImage.getImage("newEffect/airship_3"));
		}
		
		bloom = new Sprite[4];	
		
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_BOMB);
	}
	
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_BOMB);
		
		GameImage.delImage(airship.bitmap);
		
		airship.recycleBitmap();
		
		for(int i=0;i<bloom.length;i++)
		{
			if(bloom[i]!=null)
			bloom[i].recycleBitmap();
		}
	}
	
	public void setTimes(int t)
	{
		times = t;
	}
	
	public void setStart()
	{				
		offset = GameConfig.GameScreen_Width/times;
		
		airship_x = -airship.bitmap.getWidth();
		
		airship_y = (int)(240*GameConfig.f_zoom);
		
		isShow = true;	
		
		if(!VeggiesData.isMuteSound())
		GameMedia.playSound(R.raw.airships, 3);
	}
	
	public void updata()
	{
		if(!isShow)
			return;
		
//		airship_x += 5;
		
		if(airship_x>GameConfig.GameScreen_Width)
		{						
			boolean result = false;
			
			for(int i=0;i<bloom.length;i++)
			{								
				if(bloom[i].state != Sprite.SPRITE_STATE_NONE)
				{
					result = true;
				}				
			}
			
			isShow = result;
		}
		else
		{	
			for(int k=0;k<5;k++)
			{
				airship_x ++;
				
				if(airship_x>=-airship.bitmap.getWidth()/4&&airship_x<=GameConfig.GameScreen_Width&&times>0)			
				{					
					if((airship_x+airship.bitmap.getWidth()/4)%offset==0)
					{				
						times --;
						
						for(int i=0;i<bloom.length;i++)
						{
							if(bloom[i]==null||bloom[i].state == Sprite.SPRITE_STATE_NONE)
							{
								if(bloom[i]==null)
									bloom[i] = new Sprite();
								
								int x = airship_x + ExternalMethods.throwDice((int)(0*GameConfig.f_zoom), (int)(300*GameConfig.f_zoom));
									
								int y = airship_y + ExternalMethods.throwDice((int)(0*GameConfig.f_zoom), (int)(300*GameConfig.f_zoom));
								
								bloom[i].initSprite(CoolEditDefine.Effect_BOMB, x, y, Sprite.SPRITE_STATE_NORMAL);
								bloom[i].changeAction(0);
							}						
						}
					}
				}
			}
		}
		
		for(int i=0;i<bloom.length;i++)
		{
			if(bloom[i]!=null)
			bloom[i].updataSprite();
		}			
	}
	
	public void paint(Canvas canvas)
	{
		if(!isShow)
			return;
		
		for(int i=0;i<bloom.length;i++)
		{
			if(bloom[i]!=null)
			bloom[i].paintSprite(canvas, 0, 0);
		}
		
		Paint paint = new Paint();
		
		airship.drawBitmap(canvas, airship.bitmap, airship_x, airship_y, paint);		
	}
	
	public boolean getState()
	{
		return isShow;
	}
	
	public void collision(Sprite sprite, GameMain gameMain)
	{		
		for(int i=0;i<bloom.length;i++)
		{
			if(bloom[i] != null&&bloom[i].state != Sprite.SPRITE_STATE_NONE)
			{
				if(!sprite.magicWaterProtect&&
				sprite.kind!=CoolEditDefine.Enemy_SHZYCS&&
				sprite.kind!=CoolEditDefine.Enemy_SHZYXZ&&
				sprite.kind!=CoolEditDefine.Enemy_MMJS&&
				sprite.kind!=CoolEditDefine.Enemy_HZXJ)	
//				if(sprite.kind!=CoolEditDefine.Enemy_YGYJF)
				{
					if(sprite.life>0)
					{
						if(ExternalMethods.RectCollision(sprite.getCollisionRect(), bloom[i].getCollisionRect()))
						{							
							sprite.life --;
							
							if(sprite.life<=0)
							{							
								if(!sprite.freezeState)
								{
									if(sprite.kind == CoolEditDefine.Enemy_MEGICWATER)//魔法水滴，释放有保护的怪物
									{											
										for(int j=0;j<gameMain.gameMonster.getEnemyList().size();j++)
										{		
											if(gameMain.gameMonster.getEnemyList().get(j).kind != CoolEditDefine.Enemy_MEGICWATER)
											{
												if(gameMain.gameMonster.getEnemyList().get(j).linkNumber==sprite.linkNumber)
												{					
													gameMain.gameMonster.getEnemyList().get(j).linkNumber = 0;
													
													gameMain.gameMonster.getEnemyList().get(j).magicWaterProtect = false;
													
													break;						
												}
											}
										}
									}
									
									sprite.changeAction(Sprite.Enemy_action02);
									
									sprite.setState(Sprite.SPRITE_STATE_DEAD);	
									
									gameMain.addGoldenAnimation(sprite);
									
	//								gameMain.gameNumber += SpriteLibrary.GetNumber(sprite.kind);							
	//								
	//								if(gameMain.doubleGameNumberTime>0)
	//								gameMain.gameNumber += SpriteLibrary.GetNumber(sprite.kind);
									
									gameMain.gameMission.addMonsterDeadTime(gameMain);							
								}							
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
