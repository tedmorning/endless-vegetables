package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.socoGameEngine.GameConfig;

public class WaitingSpriteBulletLeft {

	private ArrayList<Sprite> spriteBulletWaiting;
	
	private final int spriteBulletWaitingPls[] = {480, 40, 210, 70, 150, 40, 90, 70};
		
	private final int spriteBulletWaitingMovePls[][] = 
	{
		{270-10, 70, 210-10, 70, 150-10, 70, 90-10, 70},
		{270-20, 70, 210-20, 70, 150-20, 70, 90-20, 70},
		{270-20, 70, 210-20, 70, 150-20, 70, 90-25, 50},
		{270-20, 70, 210-20, 70, 150-20, 70, 90-30, 30},		
	};
	
	//------------------------------ 蔬菜子弹类型 -----------------------
	private int kind;
	
	//------------------------------ 等待中蔬菜的起始位置 ----------------------
	private int org_x;
	private int org_y;
	
	//------------------------------ 技能类型  ----------------------
	private int special;
	
	//------------------------------ 下次上膛等待时间  ----------------------
//	private int reloadTime;
	
	//------------------------------ 移动索引  ----------------------
	private int moveIndex;
	
	public ArrayList<Sprite> getSpriteBulletWaiting()
	{
		return spriteBulletWaiting;
	}
	
	public void init(int kind, int org_x, int org_y, int special)
	{
		SpriteLibrary.loadSpriteImage(kind);
		
		moveIndex = 0;
		
		spriteBulletWaiting = new ArrayList<Sprite>();
				
		this.kind = kind;
		
		this.org_x = org_x;
		
		this.org_y = org_y;	
		
		this.special = special;
		
//		this.reloadTime = reloadTime;
		
		for(int i=0;i<4;i++)
		{
			Sprite sprite = new Sprite();
			sprite.initSprite(kind, (int)(org_x-spriteBulletWaitingPls[i*2]*GameConfig.f_zoom), (int)(org_y+spriteBulletWaitingPls[i*2+1]*GameConfig.f_zoom), Sprite.SPRITE_STATE_NORMAL);
			sprite.changeAction(0);
			
			spriteBulletWaiting.add(sprite);
		}
	}
	
	public void delImage(GameMain gameMain)
	{
		SpriteLibrary.DelSpriteImage(gameMain.gameMainLeftPlayerID);
		
		for(int i=0;i<spriteBulletWaiting.size();i++)
			spriteBulletWaiting.get(i).recycleBitmap();
	}
	
	public void updata(GameMain gameMain)
	{		
		if(spriteBulletWaiting!=null)
		{
			for(int i=0;i<spriteBulletWaiting.size();i++)
				spriteBulletWaiting.get(i).updataSprite();
			
			spriteBulletMove(gameMain);
		}
	}
	
	public void paint(Canvas canvas)
	{
		if(spriteBulletWaiting!=null)
		{
			for(int i=0;i<spriteBulletWaiting.size();i++)
			{
				if(moveIndex==spriteBulletWaitingMovePls.length-2&&i==3)
					spriteBulletWaiting.get(i).paintSpriteShadow(canvas, 10, 1);
				else if(moveIndex==spriteBulletWaitingMovePls.length-1&&i==3)
					spriteBulletWaiting.get(i).paintSpriteShadow(canvas, 20, 1);
				else
					spriteBulletWaiting.get(i).paintSpriteShadow(canvas, 0, 1);
			}
				
			for(int i=0;i<spriteBulletWaiting.size();i++)
				spriteBulletWaiting.get(i).paintSprite(canvas, 0, 0);
		}
	}
	
	public void setSpriteBullet(GameMain gameMain)
	{		
		gameMain.readSpriteBullet.initSpriteBullet(kind, gameMain.slingshot.SLINGSHOT_X , gameMain.slingshot.SLINGSHOT_Y, special);
	}
	
	public void spriteBulletMove(GameMain gameMain)
	{
		if(!gameMain.slingshot.slingShotBufferState&&
			gameMain.readSpriteBullet.getSpriteBullet().state == Sprite.SPRITE_STATE_NONE)
		{
			if(moveIndex<spriteBulletWaitingMovePls.length)
			{
				for(int i=0;i<spriteBulletWaiting.size();i++)
				{
					spriteBulletWaiting.get(i).setXY((int)(org_x-spriteBulletWaitingMovePls[moveIndex][i*2]*GameConfig.f_zoom), (int)(org_y+spriteBulletWaitingMovePls[moveIndex][i*2+1]*GameConfig.f_zoom));
				}	
				
				moveIndex ++;
			}
			else
			{
				for(int i=0;i<spriteBulletWaiting.size();i++)
				{
					spriteBulletWaiting.get(i).x = spriteBulletWaiting.get(i).org_x;
					spriteBulletWaiting.get(i).y = spriteBulletWaiting.get(i).org_y;
				}	
				
				gameMain.readSpriteBullet.initSpriteBullet(kind, gameMain.slingshot.SLINGSHOT_X , gameMain.slingshot.SLINGSHOT_Y, special);
				
				moveIndex = 0;
			}
		}
	}	
}
