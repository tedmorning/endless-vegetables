package com.endlessvegetables2.turngame;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class TurnGameTeleport {

//	public static final byte IN_TYPE1 = 0;//不动
//	public static final byte IN_TYPE2 = 1;//移动
//	
//	public static final byte OUT_TYPE1 = 0;//左边不动
//	public static final byte OUT_TYPE2 = 1;//左边移动
//	public static final byte OUT_TYPE3 = 2;//右边不动
//	public static final byte OUT_TYPE4 = 3;//右边移动
	
	private TurnGameSprite in;
	
	private TurnGameSprite out;
	
	private boolean isShow;
	
	private int touchSpriteNumber;
	
	public TurnGameTeleport()
	{
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_TELEPORT);
		
		in = new TurnGameSprite();
		
		out = new TurnGameSprite();
		
		isShow = false;				
	}
	
	public void delImage()
	{
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_TELEPORT);
		
		in.recycleBitmap();
		
		out.recycleBitmap();
	}
	
	public void setShow(TurnGameMain gameMain/*, byte inType, byte outType*/)
	{						
		in.initSprite(CoolEditDefine.Effect_TELEPORT, 
				TurnGameSpriteLibrary.GetW(CoolEditDefine.Effect_TELEPORT)/2, 
				gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-TurnGameSpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT), TurnGameSprite.SPRITE_STATE_NORMAL);		
		in.changeAction(0);
		
		in.byMoveDirect = 0;
//		in.byMoveStyle = inType;
		
		out.initSprite(CoolEditDefine.Effect_TELEPORT, 
				 GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(CoolEditDefine.Effect_TELEPORT)/2, 
				 (int)(200*GameConfig.f_zoomy), TurnGameSprite.SPRITE_STATE_NORMAL);		
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
	
	public void updata(TurnGameMain gameMain)
	{
		if(!isShow)
			return;
		
		in.updataSprite();
		
		out.updataSprite();
		
		move(gameMain);
		
		collision(gameMain);
	}
	
	private void move(TurnGameMain gameMain)
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
			
			if(in.y>=gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-TurnGameSpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT))
			{
				in.y=gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-TurnGameSpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT);
				
				in.byMoveDirect = 0;
			}
		}
		
		if(out.byMoveDirect==0)
		{
			out.y += 4;
			
			if(out.y>=gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-TurnGameSpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT))
			{
				out.y = gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()-TurnGameSpriteLibrary.GetH(CoolEditDefine.Effect_TELEPORT);
				
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
	}
	
	public void collision(TurnGameMain gameMain)
	{
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
