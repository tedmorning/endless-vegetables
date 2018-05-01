package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class GameRose {
	
	private ArrayList<RoseSprite> rose;
	
	private ArrayList<RoseSprite> effect;
	
//	private ArrayList<Boolean> isPaint;
	
	public GameRose()
	{		
		rose = new ArrayList<RoseSprite>();		
		
		effect = new ArrayList<RoseSprite>();
		
//		isPaint = new ArrayList<Boolean>();
	}
	
	public void loadImage()
	{
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_ROSE);
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_ROSEFIRST);
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_ROSESECOND);
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_ROSETHIRD);
//		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_ROSETHIRDATTACK);
	}
	
	public void delImage()
	{		
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_ROSE);
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_ROSEFIRST);
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_ROSESECOND);
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_ROSETHIRD);
//		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_ROSETHIRDATTACK);
	}
	
	public void setRose(int x, int y)
	{		
		RoseSprite _rose = new RoseSprite();
		
		_rose.initSprite(CoolEditDefine.Effect_ROSE, x, y, Sprite.SPRITE_STATE_NORMAL);		
		_rose.changeAction(0);	
		
		rose.add(_rose);
		
//		boolean _isPaint = false;
//		
//		isPaint.add(_isPaint);
	}
	
	public ArrayList<RoseSprite> getRose()
	{
		return rose;
	}
	
	public ArrayList<RoseSprite> getEffect()
	{
		return effect;
	}
	
	public void clearPaint()
	{
//		for(int i=0;i<isPaint.size();i++)
//			isPaint.set(i, false);
	}
	
	public void paint(Canvas canvas, int y)
	{
//		for(int i=0;i<rose.size();i++)
//		{			
////			rose.get(i).paintSpriteShadow(canvas, -SpriteLibrary.GetH(rose.get(i).kind)/4, 1f);
//			
//			if(!isPaint.get(i)&&rose.get(i).y>y)
//			{
//				rose.get(i).paintSprite(canvas, 0, 0);
//				
//				isPaint.set(i, true);
//			}
//		}
//		
//		for(int i=0;i<effect.size();i++)
//		{
////			effect.get(i).paintSpriteShadow(canvas, -SpriteLibrary.GetH(effect.get(i).kind)/4, 1f);
//			
//			if(!isPaint.get(i)&&effect.get(i).y>y)
//			{
//				effect.get(i).paintSprite(canvas, 0, 0);
//				
//				isPaint.set(i, true);
//			}
//		}
	}
	
	public void paint(Canvas canvas)
	{
		for(int i=0;i<rose.size();i++)
		{			
//			rose.get(i).paintSpriteShadow(canvas, -SpriteLibrary.GetH(rose.get(i).kind)/4, 1f);
						
			rose.get(i).paintSprite(canvas, 0, 0);		
		}
		
		for(int i=0;i<effect.size();i++)
		{
//			effect.get(i).paintSpriteShadow(canvas, -SpriteLibrary.GetH(effect.get(i).kind)/4, 1f);
			
			effect.get(i).paintSprite(canvas, 0, 0);							
		}
	}
	
	public void updata(GameMain gameMain)
	{
		for(int i=0;i<rose.size();i++)
		{	
			if(rose.get(i).state == Sprite.SPRITE_STATE_NONE)
			{
//				rose.remove(i);
				
				rose.get(i).roseRevivalTime --;
				
				if(rose.get(i).roseRevivalTime==0)
				{
					rose.get(i).roseState = 0;
					
					rose.get(i).roseRevivalTime = 0;
					
					rose.get(i).roseNextLevelTime = 0;
					
					rose.get(i).changeAction(0);
															
					rose.get(i).setState(Sprite.SPRITE_STATE_NORMAL);
				}
			}
			else
			{
				rose.get(i).updataSprite();
				
				collision(gameMain, rose.get(i));							
				
				changeState(rose.get(i));
			}
		}
		
		for(int i=0;i<effect.size();i++)
		{
			if(effect.get(i).state == Sprite.SPRITE_STATE_NONE)
			{
				effect.remove(i);
				
//				isPaint.remove(i);
			}
			else
			{
				effect.get(i).updataSprite();
				
				attackCollision(gameMain, effect.get(i));
			}
		}
	}
	
	public void collision(GameMain gameMain, RoseSprite _rose)
	{
		for(int i=0;i<gameMain.gameMonster.getEnemyList().size();i++)
		{
			if(!gameMain.gameMonster.getEnemyList().get(i).isFly&&
			   !gameMain.gameMonster.getEnemyList().get(i).isGlide)
			{
				if(gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHMM||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_MIMI||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGMM||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHX||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_X||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGX||
				   (gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHZ&&gameMain.gameMonster.getEnemyList().get(i).actionName!=Sprite.Enemy_action05)||
				   (gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_Z&&gameMain.gameMonster.getEnemyList().get(i).actionName!=Sprite.Enemy_action05)||
				   (gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGZ&&gameMain.gameMonster.getEnemyList().get(i).actionName!=Sprite.Enemy_action05))
				{		
					if(_rose.state != Sprite.SPRITE_STATE_NONE)
					{
						if(gameMain.gameMonster.getEnemyList().get(i).y<_rose.y)
						{
							if(ExternalMethods.RectCollision(_rose.getCollisionRect(), gameMain.gameMonster.getEnemyList().get(i).getCollisionRect()))				
							{					
								if(!gameMain.gameMonster.getEnemyList().get(i).isForcedStop)
								{
									gameMain.gameMonster.getEnemyList().get(i).isForcedStop = true;
									
									_rose.isForcedStop = true;
									
									gameMain.gameMonster.getEnemyList().get(i).changeAction(Sprite.Enemy_action05);										
								}
								else
								{						
									if(gameMain.gameMonster.getEnemyList().get(i).getActionName()!=Sprite.Enemy_action05)
									{
										gameMain.gameMonster.getEnemyList().get(i).isForcedStop = false;
										
										_rose.setState(Sprite.SPRITE_STATE_NONE);
										
										_rose.roseRevivalTime = 250;
										
										_rose.isForcedStop = false;
										
	//									isPaint.remove(i);
										
										gameMain.gameMonster.getEffect().add(Effect.FLOWER, (int)_rose.x, (int)_rose.y, 0);
										
										if(gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHZ||
										   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_Z||
										   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGZ)
										gameMain.gameMonster.getEnemyList().get(i).byMoveWaitingTime = 25;
									}
								}				
							}
						}
					}
				}
			}
		}
	}
	
	public void attackCollision(GameMain gameMain, RoseSprite _effect)
	{		
		for(int i=0;i<gameMain.gameMonster.getEnemyList().size();i++)
		{			
			if(ExternalMethods.RectCollision(_effect.getCollisionRect(), gameMain.gameMonster.getEnemyList().get(i).getCollisionRect()))
			{
				if(_effect.kind==CoolEditDefine.Effect_ROSEFIRST)
				{
					if(_effect.getframes()>=_effect.getMaxFrames()-4)
					{
						gameMain.gameMonster.collision(gameMain, _effect);
					}
				}
				else if(_effect.kind==CoolEditDefine.Effect_ROSESECOND)
				{
					if(gameMain.gameMonster.getEnemyList().get(i).lostBloodTime==0)
					{
						gameMain.gameMonster.getEnemyList().get(i).lostBloodTime = 75;
						
						gameMain.gameMonster.getEnemyList().get(i).lostBloodTimeOffset = 25;
						
						gameMain.gameMonster.getEnemyList().get(i).lostBloodAmount = 10;
					}
				}
				else if(_effect.kind==CoolEditDefine.Effect_ROSETHIRD)
				{				
					if(gameMain.gameMonster.getEnemyList().get(i).roseThornTime==0)
					{
						gameMain.gameMonster.getEnemyList().get(i).roseThornTime = 75;
						
						gameMain.gameMonster.changeGameRoseThorn(i, 0);
					}
				}
			}
		}
	}
	
	public void onTouchEvent(MotionEvent event)
	{
		int pointx = (int) event.getX();
		int pointy = (int) event.getY();
		
		for(int i=0;i<rose.size();i++)
		{			
			if(!rose.get(i).isForcedStop)
			{
				if(rose.get(i).roseRevivalTime==0)
				{			
					if(pointx>rose.get(i).x-SpriteLibrary.GetW(rose.get(i).kind)/2&&
					   pointx<rose.get(i).x+SpriteLibrary.GetW(rose.get(i).kind)/2&&
					   pointy>rose.get(i).y-SpriteLibrary.GetH(rose.get(i).kind)/2&&
					   pointy<rose.get(i).y+SpriteLibrary.GetH(rose.get(i).kind)/2)
					{								
						RoseSprite _effect = new RoseSprite();
						
						if(rose.get(i).roseState==0)				
							_effect.initSprite(CoolEditDefine.Effect_ROSEFIRST, (int)rose.get(i).x, (int)rose.get(i).y, Sprite.SPRITE_STATE_NORMAL);														
						else if(rose.get(i).roseState==1)
							_effect.initSprite(CoolEditDefine.Effect_ROSESECOND, (int)rose.get(i).x, (int)rose.get(i).y, Sprite.SPRITE_STATE_NORMAL);
						else if(rose.get(i).roseState==2)
							_effect.initSprite(CoolEditDefine.Effect_ROSETHIRD, (int)rose.get(i).x, (int)rose.get(i).y, Sprite.SPRITE_STATE_NORMAL);
						
										
						_effect.changeAction(0);	
						
						effect.add(_effect);
						
						rose.get(i).setState(Sprite.SPRITE_STATE_NONE);
						
						rose.get(i).roseRevivalTime = 250;
					}
				}
			}		
		}
	}
	
	private void changeState(RoseSprite _rose)
	{
		_rose.roseNextLevelTime ++;
		
		if(_rose.roseNextLevelTime>200)
		{
			if(_rose.getActionName()!=3)
			_rose.changeAction(3);
			
			_rose.roseState = 2;
		}
		else if(_rose.roseNextLevelTime>100)
		{
			if(_rose.getActionName()!=2)
			_rose.changeAction(2);
			
			_rose.roseState = 1;
		}
	}
	
	class RoseSprite extends Sprite
	{
		public int roseState = 0;//玫瑰状态值
		
		public int roseNextLevelTime = 0;//到下一个状态的等待时间
		
		public int roseRevivalTime = 0;//玫瑰恢复时间
	}
}
