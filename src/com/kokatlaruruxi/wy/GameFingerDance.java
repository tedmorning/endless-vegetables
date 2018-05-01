package com.kokatlaruruxi.wy;

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
import android.graphics.RectF;
import android.view.MotionEvent;

public class GameFingerDance {
	
	private boolean isShow;

	private int showTime;
	
	private int showTimeMax;
	
	private boolean start;
	
	public GameFingerDance()
	{
		isShow = false;	
		
		start = false;
	}
	
	public void setShow(int isTime)
	{
		isShow = true;
		
		showTime = isTime;
		
		showTimeMax = showTime;
		
		start = false;
		
		if(!VeggiesData.isMuteSound())
		GameMedia.playSound(R.raw.goldfingers, 0);
	}
	
	public boolean getShowState()
	{
		return isShow;
	}
	
	public void updata()
	{
		if(!isShow)
			return;
		
		if(!start)
			return;
		
		showTime -- ;		
		
		if(showTime<=0)
		{
			showTime = 0;
			
			isShow = false;
		}
	}
	
	public void paint(Canvas canvas)
	{
		if(!isShow)
			return;
		
		canvas.save();
		
		Paint paint = new Paint();
		
		paint.setColor(Color.argb(125, 255, 150, 222));

		canvas.clipRect(0, 0,  GameConfig.GameScreen_Width, (int)(580*GameConfig.f_zoomy));
		
		RectF oval = new RectF();
		
		oval.set(-100*GameConfig.f_zoomx, -160*GameConfig.f_zoomx, GameConfig.GameScreen_Width+100*GameConfig.f_zoomx, 740*GameConfig.f_zoomy);
		
		canvas.drawArc(oval, -90+(360-360*showTime/showTimeMax), 360*showTime/showTimeMax, true, paint);
		
		canvas.restore();
	}
	
	public void onTouchEvent(MotionEvent event, GameMain gameMain)
	{
		if(!isShow)
			return;

		int pointx = (int) event.getX();
		int pointy = (int) event.getY();
		
		if(event.getAction() == MotionEvent.ACTION_UP)
		{

		}
		else if(event.getAction() == MotionEvent.ACTION_DOWN||
				event.getAction() == MotionEvent.ACTION_MOVE)
		{
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				if(pointx>=0&&pointx<=GameConfig.GameScreen_Width&&
				   pointy>=0&&pointy<=(int)(580*GameConfig.f_zoomy))
				{
					start = true;
				}
			}
			
			if(!start)
				return;
			
			for(int i=gameMain.gameMonster.getEnemyList().size()-1;i>=0;i--)
			{
				if(!gameMain.gameMonster.getEnemyList().get(i).magicWaterProtect&&
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_SHZY&&
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MM&&
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MMJS)
				{
					if(gameMain.gameMonster.getEnemyList().get(i).transitionWaiting<=0)
					{
						RectF collision = gameMain.gameMonster.getEnemyList().get(i).getCollisionRect();
						
						if(pointx>=collision.left&&
						   pointx<=collision.right&&
						   pointy>=collision.top&&
						   pointy<=collision.bottom)
						{
							if(gameMain.gameMonster.getEnemyList().get(i).kind==CoolEditDefine.Enemy_MEGICWATER)
							{								
								if(gameMain.gameMonster.getEnemyList().get(i).life>0)
								{
									for(int j=0;j<gameMain.gameMonster.getEnemyList().size();j++)
									{		
										if(gameMain.gameMonster.getEnemyList().get(j).kind != CoolEditDefine.Enemy_MEGICWATER)
										{
											if(gameMain.gameMonster.getEnemyList().get(j).linkNumber==gameMain.gameMonster.getEnemyList().get(i).linkNumber)
											{					
												gameMain.gameMonster.getEnemyList().get(j).linkNumber = 0;
												
												gameMain.gameMonster.getEnemyList().get(j).magicWaterProtect = false;
												
												break;						
											}
										}
									}
									
									gameMain.gameMonster.getEnemyList().get(i).life = 0;
									
									gameMain.gameMonster.getEnemyList().get(i).isFly = false;
									
									gameMain.gameMonster.getEnemyList().get(i).isOver = true;	
									
									gameMain.gameMonster.getEnemyList().get(i).setState(Sprite.SPRITE_STATE_NONE);
									
									gameMain.addGoldenAnimation(gameMain.gameMonster.getEnemyList().get(i));
									
									gameMain.gameMission.addMonsterDeadTime(gameMain);
									
									break;
								}
							}
							else
							{
								fly(gameMain, i);
								
								gameMain.addGoldenAnimation(gameMain.gameMonster.getEnemyList().get(i));
								
								gameMain.gameMission.addMonsterDeadTime(gameMain);
								
								//ฝฬัง
								if(gameMain.gameTeaching.pauseState())
								{								
									if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL15)
									{
									   gameMain.gameTeaching.finish();							   							  
									}
								}
								
								break;
							}															
						}
					}
				}
			}
		}		
	}
	
	private void fly(GameMain gameMain, int id)
	{
		if(gameMain.gameMonster.getEnemyList().get(id).freezeState)
		{
//			gameMain.gameMonster.getEffect().closeEffect(CoolEditDefine.Effect_ICESTATELV3, (int)gameMain.gameMonster.getEnemyList().get(id).x, (int)gameMain.gameMonster.getEnemyList().get(id).y);
			
			gameMain.gameMonster.getEffect().closeEffect(CoolEditDefine.Effect_ICESTATELV3, gameMain.gameMonster.getEnemyList().get(id).freezeLinkNumber);
			
			gameMain.gameMonster.getEnemyList().get(id).freezeState = false;
			
			gameMain.gameMonster.getEnemyList().get(id).freezeTime = 0;
		}
		
		gameMain.gameMonster.getEnemyList().get(id).dizzinessTime = 0;
		
		gameMain.gameMonster.getEnemyList().get(id).setSlowDown(false);
		
		gameMain.gameMonster.getEnemyList().get(id).speedDownTime = 0;
							
		if(!gameMain.gameMonster.getEnemyList().get(id).isFly)
		{
			gameMain.gameMonster.getEnemyList().get(id).isFly = true;
			gameMain.gameMonster.getEnemyList().get(id).isFlyMoveDegree = ExternalMethods.throwDice(30, 150);
			gameMain.gameMonster.getEnemyList().get(id).isFlyMoveSpeed  = 40;
			
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.npcflys, 0);
		}
		
		if(gameMain.gameMonster.getEnemyList().get(id).kind == CoolEditDefine.Enemy_SIREN)
		{
//			if(gameMain.gameMonster.getEnemyList().get(id).byMoveDirect==0)
//			{
				gameMain.gameMonster.getEnemyList().get(id).changeAction(Sprite.Enemy_action01);																								
//			}
//			else
//			{
//				gameMain.gameMonster.getEnemyList().get(id).changeAction(Sprite.Enemy_action28);																								
//			}
			
			gameMain.gameMonster.getEnemyList().get(id).setState(Sprite.SPRITE_STATE_INJURE);
		}
		else if(gameMain.gameMonster.getEnemyList().get(id).kind == CoolEditDefine.Enemy_SHHT)
		{
			if(gameMain.gameMonster.getEnemyList().get(id).byMoveDirect==0)
			{
				gameMain.gameMonster.getEnemyList().get(id).changeAction(Sprite.Enemy_action01);																								
			}
			else
			{
				gameMain.gameMonster.getEnemyList().get(id).changeAction(Sprite.Enemy_action15);																								
			}
			
			gameMain.gameMonster.getEnemyList().get(id).setState(Sprite.SPRITE_STATE_INJURE);
		}
		else
		{
			gameMain.gameMonster.getEnemyList().get(id).changeAction(Sprite.Enemy_action01);
			
			gameMain.gameMonster.getEnemyList().get(id).setState(Sprite.SPRITE_STATE_INJURE);
		}
												
//		gameMain.gameMonster.getEffect().closeEffect(CoolEditDefine.Effect_DIZZINESS, (int)gameMain.gameMonster.getEnemyList().get(id).x, (int)gameMain.gameMonster.getEnemyList().get(id).y-SpriteLibrary.GetH(gameMain.gameMonster.getEnemyList().get(id).kind));
		
		gameMain.gameMonster.getEffect().closeEffect(CoolEditDefine.Effect_DIZZINESS, gameMain.gameMonster.getEnemyList().get(id).dizzinessLinkNumber);
	}
}
