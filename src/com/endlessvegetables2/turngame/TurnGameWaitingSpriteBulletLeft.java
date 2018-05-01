package com.endlessvegetables2.turngame;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameLibrary;
import com.socogame.coolEdit.CoolEditDefine;

public class TurnGameWaitingSpriteBulletLeft {

	public ArrayList<TurnGameSprite> spriteBulletWaiting;
	
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
	
	public ArrayList<TurnGameSprite> getSpriteBulletWaiting()
	{
		return spriteBulletWaiting;
	}
	
	public void init(int kind, int org_x, int org_y, int special)
	{
		TurnGameSpriteLibrary.loadSpriteImage(kind);
		
		moveIndex = 0;
		
		spriteBulletWaiting = new ArrayList<TurnGameSprite>();
				
		this.kind = kind;
		
		this.org_x = org_x;
		
		this.org_y = org_y;	
		
		this.special = special;
		
//		this.reloadTime = reloadTime;
		
		for(int i=0;i<4;i++)
		{
			TurnGameSprite sprite = new TurnGameSprite();
			sprite.initSprite(kind, (int)(org_x-spriteBulletWaitingPls[i*2]*GameConfig.f_zoom), (int)(org_y+spriteBulletWaitingPls[i*2+1]*GameConfig.f_zoom), TurnGameSprite.SPRITE_STATE_NORMAL);
			sprite.changeAction(0);
			
			spriteBulletWaiting.add(sprite);
		}
	}
	
	public void delImage(TurnGameMain gameMain)
	{
		TurnGameSpriteLibrary.DelSpriteImage(gameMain.gameMainLeftPlayerID);
		
		for(int i=0;i<spriteBulletWaiting.size();i++)
			spriteBulletWaiting.get(i).recycleBitmap();
	}
	
	public void updata(TurnGameMain gameMain)
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
				
			for(int i=0;i<spriteBulletWaiting.size();i++){
				spriteBulletWaiting.get(i).paintSprite(canvas, 0, 0);
				Sprite tempSprite=spriteBulletWaiting.get(i);
				float tempsize=0.6f;
				int tempnengliang=TurnGameSpriteLibrary.Getnengliang(tempSprite.kind);
				float tempx=tempSprite.x-TurnGameMain.sprite_ui_Action_2.bitmap.getWidth()/2-TurnGameMain.sprite_reward_num[0].bitmap.getWidth()/2*(""+tempnengliang).length();
				float tempy=tempSprite.y;
				TurnGameMain.sprite_ui_Action_2.drawBitmap(canvas, tempx, tempy,tempsize,tempsize, 255, 0, 0, 0, 0, 0, 0);
				tempx+=TurnGameMain.sprite_ui_Action_2.bitmap.getWidth()*tempsize;
				tempy+=TurnGameMain.sprite_ui_Action_2.bitmap.getHeight()*tempsize/2-TurnGameMain.sprite_reward_num[0].bitmap.getHeight()/2;
				GameLibrary.DrawNumber(canvas, TurnGameMain.sprite_reward_num, tempx, tempy, TurnGameMain.NumChars, ""+tempnengliang, null, (byte)0, 0f,0.9f,0.9f);
				if(TurnGameMain.turn!=0){
					tempx=tempSprite.x-TurnGameMain.sprite_skill_silent.bitmap.getWidth()/2;
					tempy=tempSprite.y-TurnGameMain.sprite_skill_silent.bitmap.getHeight();
					TurnGameMain.sprite_skill_silent.drawBitmap(canvas, TurnGameMain.sprite_skill_silent.bitmap, tempx, tempy, null);
				}
			}
			
		}
	}
	
	public void setSpriteBullet(TurnGameMain gameMain)
	{		
		gameMain.readSpriteBullet.initSpriteBullet(kind, gameMain.slingshot.SLINGSHOT_X , gameMain.slingshot.SLINGSHOT_Y, special);
	}
	
	public void spriteBulletMove(TurnGameMain gameMain)
	{
		if(!gameMain.slingshot.slingShotBufferState&&
			gameMain.readSpriteBullet.getSpriteBullet().state == TurnGameSprite.SPRITE_STATE_NONE)
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
	
	public void onTouchEvent(MotionEvent event, TurnGameMain gameMain)
	{
		int pointx = (int) event.getX();
		int pointy = (int) event.getY();
	
//		if(gameMain.getHideStageState())
//			return;
			
		if(gameMain.slingshot.getSendSpriteBullet())
			return;
		
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{			
			for(int i=0;i<spriteBulletWaiting.size();i++)
			{												
				if(pointx>=spriteBulletWaiting.get(i).x-TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind)/2&&
				   pointx<=spriteBulletWaiting.get(i).x+TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind)/2&&
				   pointy>=spriteBulletWaiting.get(i).y-TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)/2*3&&
				   pointy<=spriteBulletWaiting.get(i).y+TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)/2)
				{
					if(spriteBulletWaiting.get(i).cd==spriteBulletWaiting.get(i).cdTime)
					{
						spriteBulletWaiting.get(i).cd = 0;
						
						setSpriteBullet(gameMain.readSpriteBullet, spriteBulletWaiting.get(i), gameMain.slingshot.SLINGSHOT_X, gameMain.slingshot.SLINGSHOT_Y);
						
						if(spriteBulletWaiting.get(i).kind == CoolEditDefine.Player_TD||
						   spriteBulletWaiting.get(i).kind == CoolEditDefine.Player_TD_2||
						   spriteBulletWaiting.get(i).kind == CoolEditDefine.Player_TD_3||
						   spriteBulletWaiting.get(i).kind == CoolEditDefine.Player_MG||
						   spriteBulletWaiting.get(i).kind == CoolEditDefine.Player_MG_2||
						   spriteBulletWaiting.get(i).kind == CoolEditDefine.Player_MG_3||
						   spriteBulletWaiting.get(i).kind==CoolEditDefine.Player_HC||
						   spriteBulletWaiting.get(i).kind==CoolEditDefine.Player_HC_2||
						   spriteBulletWaiting.get(i).kind==CoolEditDefine.Player_HC_3)
							gameMain.slingshot.getIndicator().setSnipeState(true);
						else
							gameMain.slingshot.getIndicator().setSnipeState(false);
						
//						//教学
//						if(gameMain.gameTeaching.pauseState())
//						{								
//							if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL13)
//							   gameMain.gameTeaching.finish();
//						}
					}
				}
			}
		}
	}
	
	public void setSpriteBullet(TurnGameReadSpriteBullet readSpriteBullet, TurnGameSprite waitingSprite, int x , int y)
	{				
		readSpriteBullet.initSpriteBullet(waitingSprite.kind, x, y, waitingSprite.special);
	}
}
