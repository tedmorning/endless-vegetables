package com.kokatlaruruxi.wy;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class GameTeleport {

//	public static final byte IN_TYPE1 = 0;//不动
//	public static final byte IN_TYPE2 = 1;//移动
//	
//	public static final byte OUT_TYPE1 = 0;//左边不动
//	public static final byte OUT_TYPE2 = 1;//左边移动
//	public static final byte OUT_TYPE3 = 2;//右边不动
//	public static final byte OUT_TYPE4 = 3;//右边移动
	
	private Sprite in;
	
	private Sprite out;
	
	private boolean isShow;
	
	private int touchSpriteNumber;
	
	public GameTeleport()
	{
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_TELEPORT);
		
		in = new Sprite();
		
		out = new Sprite();
		
		isShow = false;				
	}
	
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_TELEPORT);
		
		in.recycleBitmap();
		
		out.recycleBitmap();
	}
	
	public void setShow(GameMain gameMain/*, byte inType, byte outType*/)
	{						
		in.initSprite(CoolEditDefine.Effect_TELEPORT, 
				SpriteLibrary.GetW(CoolEditDefine.Effect_TELEPORT)/2, 
				gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-SpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT), Sprite.SPRITE_STATE_NORMAL);		
		in.changeAction(0);
		
		in.byMoveDirect = 0;
//		in.byMoveStyle = inType;
		
		out.initSprite(CoolEditDefine.Effect_TELEPORT, 
				 GameConfig.GameScreen_Width-SpriteLibrary.GetW(CoolEditDefine.Effect_TELEPORT)/2, 
				 (int)(200*GameConfig.f_zoomy), Sprite.SPRITE_STATE_NORMAL);		
		out.changeAction(0);
		
		out.byMoveDirect = 0;
//		out.byMoveStyle = outType;
		
		isShow = true;
	}
	
	public void paint(Canvas canvas)
	{
		if(!isShow)
			return;
		
		in.paintSprite(canvas, 0, 0);
		
		out.paintSprite(canvas, 0, 0);
	}
	
	public void updata(GameMain gameMain)
	{
		if(!isShow)
			return;
		
		in.updataSprite();
		
		out.updataSprite();
		
		move(gameMain);
		
		collision(gameMain);
	}
	
	private void move(GameMain gameMain)
	{
		if(in.byMoveDirect==0)
		{
			in.y -= 4;
			
			if(in.y<=350*GameConfig.f_zoomy)
			{
				in.y = 350*GameConfig.f_zoomy;
				
				in.byMoveDirect = 1;
			}
		}
		else if(in.byMoveDirect==1)
		{
			in.y += 4;
			
			if(in.y>=gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-SpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT))
			{
				in.y=gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-SpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT);
				
				in.byMoveDirect = 0;
			}
		}
		
		if(out.byMoveDirect==0)
		{
			out.y += 4;
			
			if(out.y>=gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-SpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT))
			{
				out.y = gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-SpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT);
				
				out.byMoveDirect = 1;
			}
		}
		else if(out.byMoveDirect==1)
		{
			out.y -= 4;
			
			if(out.y<=(int)(200*GameConfig.f_zoomy))
			{
				out.y=(int)(200*GameConfig.f_zoomy);
				
				out.byMoveDirect = 0;
			}
		}
		
//		if(in.byMoveStyle==IN_TYPE2)
//		{
//			if(in.byMoveDirect==0)
//			{
//				in.x += 5;
//				
//				if(in.x>=GameConfig.GameScreen_Width-SpriteLibrary.GetW(in.kind))
//				{
//					in.x=GameConfig.GameScreen_Width-SpriteLibrary.GetW(in.kind);
//					
//					in.byMoveDirect = 1;
//				}
//			}
//			else
//			{
//				in.x -= 5;
//				
//				if(in.x<=SpriteLibrary.GetW(in.kind))
//				{
//					in.x=SpriteLibrary.GetW(in.kind);
//					
//					in.byMoveDirect = 0;
//				}
//			}
//		}
//		
//		if(out.byMoveStyle==OUT_TYPE2||out.byMoveStyle==OUT_TYPE4)
//		{
//			if(out.byMoveDirect==0)
//			{
//				out.y += 5;
//				
//				if(out.y>=400*GameConfig.f_zoomy)
//				{
//					out.y=400*GameConfig.f_zoomy;
//					
//					out.byMoveDirect = 1;
//				}
//			}
//			else
//			{
//				out.y -= 5;
//				
//				if(out.y<=200*GameConfig.f_zoomy)
//				{
//					out.y=200*GameConfig.f_zoomy;
//					
//					out.byMoveDirect = 0;
//				}
//			}
//		}
	}
	
	public void collision(GameMain gameMain)
	{
//		for(int i=0;i<cold.size();i++)
//		{
//			if(cold.get(i)!=null && cold.get(i).state!=Sprite.SPRITE_STATE_NONE)
//			{
//				if(sprite.kind!=SpriteLibrary.Enemy_YGYJF)
//				{
//					if(sprite.life>0)
//					{
//						if(ExternalMethods.RectCollision(sprite.getCollisionRect(), cold.get(i).getCollisionRect()))
//						{
//							if(!sprite.freezeState)
//							{
//								sprite.freezeState = true;
//							
//								sprite.freezeTime = 100;
//								
//								gameMain.gameMonster.getEffect().add(SpriteLibrary.Effect_ICEATTACK, (int)sprite.x, (int)sprite.y-SpriteLibrary.GetH(sprite.kind)/2, Sprite.SPRITE_STATE_NORMAL, sprite.freezeTime-25);
//							}
//							
//							if(sprite.kind!=SpriteLibrary.Enemy_YGY)
//								GameMonster.centerMove = false;
//						}
//					}
//				}
//			}
//		}
		
		for(int i=0;i<gameMain.spriteBullet.getSpriteBulletList().size();i++)
		{
			if(gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_TD&&
				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_TD_2&&
				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_TD_3&&
				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_MG&&
				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_MG_2&&
				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_MG_3&&
				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_HC&&
				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_HC_2&&
				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_HC_3)
			{
				if(gameMain.spriteBullet.getSpriteBulletList().get(i).getActionName()!=5)
				{
					if(ExternalMethods.RectCollision(in.getCollisionRect(), gameMain.spriteBullet.getSpriteBulletList().get(i).getCollisionRect()))
					{
						gameMain.spriteBullet.getSpriteBulletList().get(i).setXY((int)out.x, (int)out.y);
						
//						if(out.byMoveStyle == OUT_TYPE1||
//						   out.byMoveStyle == OUT_TYPE2)
//						{
//							gameMain.spriteBullet.getSpriteBulletList().get(i).jiaodu = 270;
//							
//							gameMain.spriteBullet.getSpriteBulletList().get(i).byMoveDegree = 0;
//												
//						}
//						else 
//						{
							gameMain.spriteBullet.getSpriteBulletList().get(i).jiaodu = -270;
							
							gameMain.spriteBullet.getSpriteBulletList().get(i).byMoveDegree = 180;
//												
//						}
						
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.doors, 0);
						
						touchSpriteNumber ++;
					}
				}
			}
		}
	}
	
	public int getTouchSpriteNumber()
	{
		return touchSpriteNumber;
	}
}

//package com.endlessvegetables2.android;
//
//import com.socoGameEngine.GameConfig;
//import com.socoGameEngine.GameLibrary;
//import com.socoGameEngine.GameMedia;
//import com.socogame.coolEdit.CoolEditDefine;
//
//import android.graphics.Canvas;
//
//public class GameTeleport {
//
//	public static final byte IN_TYPE1 = 0;//不动
//	public static final byte IN_TYPE2 = 1;//移动
//	
//	public static final byte OUT_TYPE1 = 0;//左边不动
//	public static final byte OUT_TYPE2 = 1;//左边移动
//	public static final byte OUT_TYPE3 = 2;//右边不动
//	public static final byte OUT_TYPE4 = 3;//右边移动
//	
//	private Sprite in;
//	
//	private Sprite out;
//	
//	private boolean isShow;
//	
//	private int touchSpriteNumber;
//	
//	public GameTeleport()
//	{
//		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_TELEPORT);
//		
//		in = new Sprite();
//		
//		out = new Sprite();
//		
//		isShow = false;				
//	}
//	
//	public void delImage()
//	{
//		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_TELEPORT);
//		
//		in.recycleBitmap();
//		
//		out.recycleBitmap();
//	}
//	
//	public void setShow(GameMain gameMain, byte inType, byte outType)
//	{						
//		in.initSprite(CoolEditDefine.Effect_TELEPORT, GameLibrary.getIntRandom(0, GameConfig.GameScreen_Width), gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-SpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT), Sprite.SPRITE_STATE_NORMAL);		
//		in.changeAction(0);
//		
//		in.byMoveDirect = 0;
//		in.byMoveStyle = inType;
//		
//		out.initSprite(CoolEditDefine.Effect_TELEPORT, 
//				  outType<OUT_TYPE3?SpriteLibrary.GetW(CoolEditDefine.Effect_TELEPORT)/2:GameConfig.GameScreen_Width-SpriteLibrary.GetW(CoolEditDefine.Effect_TELEPORT)/2, 
//				  (int)(200*GameConfig.f_zoomy), Sprite.SPRITE_STATE_NORMAL);		
//		out.changeAction(0);
//		
//		out.byMoveDirect = 0;
//		out.byMoveStyle = outType;
//		
//		isShow = true;
//	}
//	
//	public void paint(Canvas canvas)
//	{
//		if(!isShow)
//			return;
//		
//		in.paintSprite(canvas, 0, 0);
//		
//		out.paintSprite(canvas, 0, 0);
//	}
//	
//	public void updata(GameMain gameMain)
//	{
//		if(!isShow)
//			return;
//		
//		in.updataSprite();
//		
//		out.updataSprite();
//		
//		move();
//		
//		collision(gameMain);
//	}
//	
//	private void move()
//	{
//		if(in.byMoveStyle==IN_TYPE2)
//		{
//			if(in.byMoveDirect==0)
//			{
//				in.x += 5;
//				
//				if(in.x>=GameConfig.GameScreen_Width-SpriteLibrary.GetW(in.kind))
//				{
//					in.x=GameConfig.GameScreen_Width-SpriteLibrary.GetW(in.kind);
//					
//					in.byMoveDirect = 1;
//				}
//			}
//			else
//			{
//				in.x -= 5;
//				
//				if(in.x<=SpriteLibrary.GetW(in.kind))
//				{
//					in.x=SpriteLibrary.GetW(in.kind);
//					
//					in.byMoveDirect = 0;
//				}
//			}
//		}
//		
//		if(out.byMoveStyle==OUT_TYPE2||out.byMoveStyle==OUT_TYPE4)
//		{
//			if(out.byMoveDirect==0)
//			{
//				out.y += 5;
//				
//				if(out.y>=400*GameConfig.f_zoomy)
//				{
//					out.y=400*GameConfig.f_zoomy;
//					
//					out.byMoveDirect = 1;
//				}
//			}
//			else
//			{
//				out.y -= 5;
//				
//				if(out.y<=200*GameConfig.f_zoomy)
//				{
//					out.y=200*GameConfig.f_zoomy;
//					
//					out.byMoveDirect = 0;
//				}
//			}
//		}
//	}
//	
//	public void collision(GameMain gameMain)
//	{
////		for(int i=0;i<cold.size();i++)
////		{
////			if(cold.get(i)!=null && cold.get(i).state!=Sprite.SPRITE_STATE_NONE)
////			{
////				if(sprite.kind!=SpriteLibrary.Enemy_YGYJF)
////				{
////					if(sprite.life>0)
////					{
////						if(ExternalMethods.RectCollision(sprite.getCollisionRect(), cold.get(i).getCollisionRect()))
////						{
////							if(!sprite.freezeState)
////							{
////								sprite.freezeState = true;
////							
////								sprite.freezeTime = 100;
////								
////								gameMain.gameMonster.getEffect().add(SpriteLibrary.Effect_ICEATTACK, (int)sprite.x, (int)sprite.y-SpriteLibrary.GetH(sprite.kind)/2, Sprite.SPRITE_STATE_NORMAL, sprite.freezeTime-25);
////							}
////							
////							if(sprite.kind!=SpriteLibrary.Enemy_YGY)
////								GameMonster.centerMove = false;
////						}
////					}
////				}
////			}
////		}
//		
//		for(int i=0;i<gameMain.spriteBullet.getSpriteBulletList().size();i++)
//		{
//			if(gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_TD&&
//				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_TD_2&&
//				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_TD_3&&
//				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_MG&&
//				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_MG_2&&
//				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_MG_3&&
//				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_HC&&
//				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_HC_2&&
//				gameMain.spriteBullet.getSpriteBulletList().get(i).kind!=CoolEditDefine.Player_HC_3)
//			{
//				if(gameMain.spriteBullet.getSpriteBulletList().get(i).getActionName()!=5)
//				{
//					if(ExternalMethods.RectCollision(in.getCollisionRect(), gameMain.spriteBullet.getSpriteBulletList().get(i).getCollisionRect()))
//					{
//						gameMain.spriteBullet.getSpriteBulletList().get(i).setXY((int)out.x, (int)out.y);
//						
//						if(out.byMoveStyle == OUT_TYPE1||
//						   out.byMoveStyle == OUT_TYPE2)
//						{
//							gameMain.spriteBullet.getSpriteBulletList().get(i).jiaodu = 270;
//							
//							gameMain.spriteBullet.getSpriteBulletList().get(i).byMoveDegree = 0;
//												
//						}
//						else 
//						{
//							gameMain.spriteBullet.getSpriteBulletList().get(i).jiaodu = -270;
//							
//							gameMain.spriteBullet.getSpriteBulletList().get(i).byMoveDegree = 180;
//												
//						}
//						
//						GameMedia.playSound(R.raw.doors, 0);
//						
//						touchSpriteNumber ++;
//					}
//				}
//			}
//		}
//	}
//	
//	public int getTouchSpriteNumber()
//	{
//		return touchSpriteNumber;
//	}
//}
