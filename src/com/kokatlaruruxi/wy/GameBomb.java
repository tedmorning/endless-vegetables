package com.kokatlaruruxi.wy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

public class GameBomb {

	private Sprite Bomb;
	
	private Sprite bloom;
	
	private int pointx;
	
	private int pointy;
	
	private boolean show;
	
	private boolean isBomb;
	
	public int itemId;
	
	private int level;
	
	private String imgId[] = {"newui/ui_item_07", "newui/ui_item_08", "newui/ui_item_09"};
	
	public GameBomb(int lev)
	{												
		show = false;
		
		isBomb = false;
		
		itemId = -1;
		
		level = lev;
		
		if(lev<=0)
			return;
		
		Bomb = new Sprite(GameImage.getImage(imgId[lev-1]));
		
		bloom = new Sprite();
		
		if(level==1)
		{
			bloom.initSprite(CoolEditDefine.Effect_BOMBLV1, pointx, pointy, Sprite.SPRITE_STATE_NORMAL);
						
			SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_BOMBLV1);
		}
		else if(level==2)
		{
			bloom.initSprite(CoolEditDefine.Effect_BOMBLV2, pointx, pointy, Sprite.SPRITE_STATE_NORMAL);			
			
			SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_BOMBLV2);
		}
		else if(level==3)
		{
			bloom.initSprite(CoolEditDefine.Effect_BOMBLV3, pointx, pointy, Sprite.SPRITE_STATE_NORMAL);						
			
			SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_BOMBLV3);
		}
	}
	
	public void delImage()
	{
		if(Bomb!=null)
		{
			GameImage.delImage(Bomb.bitmap);
			
			Bomb.recycleBitmap();
		}
		
		if(bloom!=null)
		bloom.recycleBitmap();
		
		if(level==1)
			SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_BOMBLV1);
		else if(level==2)
			SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_BOMBLV2);
		else if(level==3)
			SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_BOMBLV3);					
	}
	
	public void setShow(boolean isShow)
	{
		show = isShow;				
	}
	
	public boolean getShowState()
	{
		return show;
	}
	
	public void setLevel(int islevel)
	{
		level = islevel;
	}
	
	public boolean getBombState()
	{
		return isBomb;
	}
	
	public void setXY(int x, int y)
	{
		pointx = x;
		
		pointy = y;
	}
	
	public void updata()
	{		
		if(isBomb)	
		{
			bloom.updataSprite();
			
			if(bloom.state==Sprite.SPRITE_STATE_NONE)
			{
				isBomb = false;
			}
		}
	}
	
	public void paint(Canvas canvas)
	{				
		if(show)
		{		
			Paint paint = new Paint();
			
			Bomb.drawBitmap(canvas, Bomb.bitmap, pointx-Bomb.bitmap.getWidth()/2, pointy-Bomb.bitmap.getHeight()/2, paint);
		}
		
		if(isBomb)			
		{
			if(bloom!=null)
			bloom.paintSprite(canvas, 0, 0);						
		}
	}
	
	public boolean onTouchEvent(MotionEvent event, GameMain gameMain)
	{				
//		if(event.getAction() == MotionEvent.ACTION_DOWN)
//		{
//			setShow(true);
//			
//			pointx = (int) event.getX();
//			pointy = (int) event.getY();
//			
//			return true;
//		}
//		else 
		
		if(!getShowState())
			return false;
		
		if(event.getAction() == MotionEvent.ACTION_MOVE)
		{
			pointx = (int) event.getX();
			pointy = (int) event.getY();	
			
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_UP)
		{
			setShow(false);	
			
			pointx = (int) event.getX();
			pointy = (int) event.getY();	
			
			if(pointy<gameMain.slingshot.SLINGSHOT_Y)
			{
				isBomb = true;
				
				if(level==1)
					bloom.initSprite(CoolEditDefine.Effect_BOMBLV1, pointx, pointy, Sprite.SPRITE_STATE_NORMAL);
				else if(level==2)
					bloom.initSprite(CoolEditDefine.Effect_BOMBLV2, pointx, pointy, Sprite.SPRITE_STATE_NORMAL);
				else if(level==3)
					bloom.initSprite(CoolEditDefine.Effect_BOMBLV3, pointx, pointy, Sprite.SPRITE_STATE_NORMAL);
					
				bloom.changeAction(0);
				
				if(level==1||level==2)
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.littlebombs, 0);
				}
				else	
				{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.bombs, 0);
				}
			}						
			
			return false;
		}		
		
		return false;
	}	
	
	public void collision(Sprite sprite, GameMain gameMain)
	{	
		if(bloom!=null && bloom.state!=Sprite.SPRITE_STATE_NONE)
		{
			if(sprite.kind!=CoolEditDefine.Enemy_SHZYCS&&
			sprite.kind!=CoolEditDefine.Enemy_SHZYXZ&&
			sprite.kind!=CoolEditDefine.Enemy_MMJS&&
			sprite.kind!=CoolEditDefine.Enemy_HZXJ)	
//			if(sprite.kind!=CoolEditDefine.Enemy_YGYJF)
			{
				if(sprite.life>0)
				{
					if(ExternalMethods.RectCollision(sprite.getCollisionRect(), bloom.getCollisionRect()))
					{
						//动画帧数为9，连续伤害9次						
						sprite.life -= 15;
						
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
								
		//							gameMain.gameNumber += SpriteLibrary.GetNumber(sprite.kind);							
		//							
		//							if(gameMain.doubleGameNumberTime>0)
		//							gameMain.gameNumber += SpriteLibrary.GetNumber(sprite.kind);
								
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
