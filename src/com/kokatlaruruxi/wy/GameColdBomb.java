package com.kokatlaruruxi.wy;

import com.socoGameEngine.GameImage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class GameColdBomb {

//	private Sprite Bomb;
//	
//	private Sprite bloom;
//	
//	private int pointx;
//	
//	private int pointy;
//	
//	private boolean show;
//	
//	private boolean isBomb;
//	
//	public int itemId;
//	
//	private int level;
//	
//	public GameColdBomb()
//	{
//		Bomb = new Sprite(GameImage.getImage("ui/item3"));
//		
//		bloom = new Sprite();
//		
//		show = false;
//		
//		isBomb = false;
//		
//		itemId = -1;
//		
//		level = 1;
//	}
//
//	public void setShow(boolean isShow)
//	{
//		show = isShow;
//	}
//	
//	public boolean getShowState()
//	{
//		return show;
//	}
//	
//	public void setLevel(int isLevel)
//	{
//		level = isLevel;
//	}
//	
//	public boolean getBombState()
//	{
//		return isBomb;
//	}
//	
//	public void setXY(int x, int y)
//	{
//		pointx = x;
//		
//		pointy = y;
//	}
//	
//	public void updata()
//	{
//		if(isBomb)	
//		{
//			bloom.updataSprite();
//			
//			if(bloom.state==Sprite.SPRITE_STATE_NONE)
//			{
//				isBomb = false;
//			}
//		}
//	}
//	
//	public void paint(Canvas canvas)
//	{		
//		if(show)
//		{		
//			Paint paint = new Paint();
//			
//			Bomb.drawBitmap(canvas, Bomb.bitmap, pointx-Bomb.bitmap.getWidth()/2, pointy-Bomb.bitmap.getHeight()/2, paint);
//		}
//		
//		if(isBomb)			
//		{
//			bloom.paintSprite(canvas, 0, 0);						
//		}
//	}
//	
//	public boolean onTouchEvent(MotionEvent event, GameMain gameMain)
//	{				
//		if(!getShowState())
//			return false;
//		
//		if(event.getAction() == MotionEvent.ACTION_MOVE)
//		{
//			pointx = (int) event.getX();
//			pointy = (int) event.getY();	
//			
//			return true;
//		}
//		else if(event.getAction() == MotionEvent.ACTION_UP)
//		{
//			setShow(false);	
//			
//			pointx = (int) event.getX();
//			pointy = (int) event.getY();	
//			
//			if(pointy<gameMain.slingshot.SLINGSHOT_Y)
//			{
//				isBomb = true;
//									
//				bloom.initSprite(SpriteLibrary.Effect_COLD, pointx, pointy, Sprite.SPRITE_STATE_NORMAL);
//				bloom.changeAction(1);
//			}
//			
//			return false;
//		}		
//		
//		return false;
//	}	
//	
//	public void collision(Sprite sprite, GameMain gameMain)
//	{	
//		if(bloom!=null && bloom.state!=Sprite.SPRITE_STATE_NONE)
//		{
//			if(sprite.kind!=SpriteLibrary.Enemy_YGYJF)
//			{
//				if(sprite.life>0)
//				{
//					if(ExternalMethods.RectCollision(sprite.getCollisionRect(), bloom.getCollisionRect()))
//					{						
//						if(!sprite.freezeState)
//						{
//							sprite.freezeState = true;
//						
//							int kind = 0;
//							
//							if(level==1)
//							{
//								sprite.freezeTime = 75;
//								
//								kind = SpriteLibrary.Effect_ICESTATELV1;
//							}
//							else if(level==2)
//							{
//								sprite.freezeTime = 100;
//								
//								kind = SpriteLibrary.Effect_ICESTATELV2;
//							}
//							else if(level==3)
//							{
//								sprite.freezeTime = 125;
//								
//								kind = SpriteLibrary.Effect_ICESTATELV3;
//							}
//							
//							gameMain.gameMonster.getEffect().add(kind, (int)sprite.x, (int)sprite.y, Sprite.SPRITE_STATE_NORMAL, sprite.freezeTime-25);
//						}
//						
//						if(sprite.kind!=SpriteLibrary.Enemy_YGY)
//							GameMonster.centerMove = false;
//					}
//				}
//			}
//		}
//	}
}
