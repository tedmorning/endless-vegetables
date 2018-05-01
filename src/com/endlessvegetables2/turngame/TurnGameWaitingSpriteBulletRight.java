package com.endlessvegetables2.turngame;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socogame.coolEdit.CoolEditDefine;

public class TurnGameWaitingSpriteBulletRight {

	private ArrayList<TurnGameSprite> spriteBulletWaiting;
	
	public final int spriteBulletWaitingPls[] = { 90, 70,													
			                                      150, 40,
			                                      210, 70 };
	
	//------------------------------ 等待中蔬菜的起始位置 ----------------------
	private int org_x;
	private int org_y;

	private TurnGameSprite skillSilent;
	
	public ArrayList<TurnGameSprite> getSpriteBulletWaiting()
	{
		return spriteBulletWaiting;
	}
	
	public void init(int org_x, int org_y)
	{
		spriteBulletWaiting = new ArrayList<TurnGameSprite>();
		
		this.org_x = org_x;
		
		this.org_y = org_y;
		
		skillSilent = new TurnGameSprite(GameImage.getImage("newEffect/skill_silent"));
	}
	
	public void delImage()
	{
		GameImage.delImage(skillSilent.bitmap);
		
		skillSilent.recycleBitmap();
		
		for(int i=0;i<spriteBulletWaiting.size();i++)
		{
			TurnGameSpriteLibrary.DelSpriteImage(spriteBulletWaiting.get(i).kind);
			
			spriteBulletWaiting.get(i).recycleBitmap();
		}
	}	
	
	public void addWaitingSpriteBullet(int kind, int cdTime, int special, int pls_id)
	{		
		TurnGameSpriteLibrary.loadSpriteImage(kind);
		
		TurnGameSprite sprite = new TurnGameSprite();
		sprite.initSprite(kind, (int)(org_x+spriteBulletWaitingPls[pls_id*2]*GameConfig.f_zoomx), (int)(org_y+spriteBulletWaitingPls[pls_id*2+1]*GameConfig.f_zoomy), TurnGameSprite.SPRITE_STATE_NORMAL);
		sprite.changeAction(0);
		
		sprite.cdTime = cdTime;
		
		sprite.cd = sprite.cdTime;
					
		sprite.special = special;				
			
		spriteBulletWaiting.add(sprite);
	}	
	
	public void updata(boolean showSilent)
	{
		if(showSilent)
			return;
		
		for(int i=0;i<spriteBulletWaiting.size();i++)
		{
			spriteBulletWaiting.get(i).updataSprite();
			
			cdChange(spriteBulletWaiting.get(i));
		}
	}
	
	private void cdChange(TurnGameSprite sprite)
	{		
		if(sprite.cd<sprite.cdTime)
		{
			sprite.cd ++;			
		}
	}
	
	public void paint(Canvas canvas, boolean showSilent)
	{
		for(int i=spriteBulletWaiting.size()-1;i>=0;i--)
		{					
			if(spriteBulletWaiting.get(i)!=null)
			{
				spriteBulletWaiting.get(i).paintSpriteShadow(canvas, 0, 1);
			}
		}
		
		for(int i=spriteBulletWaiting.size()-1;i>=0;i--)
		{			
			if(spriteBulletWaiting.get(i)!=null&&i%2==1)
			{
				canvas.save();
				
				spriteBulletWaiting.get(i).Alpha = 120;
				
				spriteBulletWaiting.get(i).paintSprite(canvas, 0, 0);
				
				int t = TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)*spriteBulletWaiting.get(i).cd/spriteBulletWaiting.get(i).cdTime;
				
				canvas.clipRect(spriteBulletWaiting.get(i).x-TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind)/2, 
						        spriteBulletWaiting.get(i).y-TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind), 
						        spriteBulletWaiting.get(i).x+TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind),
						        spriteBulletWaiting.get(i).y-(TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)-t));
				
				spriteBulletWaiting.get(i).Alpha = 255;
				
				spriteBulletWaiting.get(i).paintSprite(canvas, 0, 0);
				
				canvas.restore();
				
				if(showSilent)
				{
					Paint paint = new Paint();
					
					skillSilent.drawBitmap(canvas, skillSilent.bitmap, 
							(spriteBulletWaiting.get(i).x-TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind)/2)+(TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind)-skillSilent.bitmap.getWidth()>>1), 
					        spriteBulletWaiting.get(i).y-TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)+(TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)-skillSilent.bitmap.getHeight()>>1),  paint);
				}
				Sprite tempSprite=spriteBulletWaiting.get(i);
				float tempsize=0.6f;
				int tempnengliang=TurnGameSpriteLibrary.Getnengliang(tempSprite.kind);
				float tempx=tempSprite.x-TurnGameMain.sprite_ui_Action_2.bitmap.getWidth()/2-TurnGameMain.sprite_reward_num[0].bitmap.getWidth()/2*(""+tempnengliang).length();
				float tempy=tempSprite.y;
				TurnGameMain.sprite_ui_Action_2.drawBitmap(canvas, tempx, tempy,tempsize,tempsize, 255, 0, 0, 0, 0, 0, 0);
				tempx+=TurnGameMain.sprite_ui_Action_2.bitmap.getWidth()*tempsize;
				tempy+=TurnGameMain.sprite_ui_Action_2.bitmap.getHeight()*tempsize/2-TurnGameMain.sprite_reward_num[0].bitmap.getHeight()/2;
				GameLibrary.DrawNumber(canvas, TurnGameMain.sprite_reward_num, tempx, tempy, TurnGameMain.NumChars, ""+tempnengliang, null, (byte)0, 0f,0.9f,0.9f);
				if(TurnGameMain.turn!=0||tempnengliang>TurnGameMain.turn_nengliang){
					tempx=tempSprite.x-TurnGameMain.sprite_skill_silent.bitmap.getWidth()/2;
					tempy=tempSprite.y-TurnGameMain.sprite_skill_silent.bitmap.getHeight();
					TurnGameMain.sprite_skill_silent.drawBitmap(canvas, TurnGameMain.sprite_skill_silent.bitmap, tempx, tempy, null);
				}
			}
		}
		
		for(int i=spriteBulletWaiting.size()-1;i>=0;i--)
		{			
			if(spriteBulletWaiting.get(i)!=null&&i%2==0)
			{
				canvas.save();
				
				spriteBulletWaiting.get(i).Alpha = 120;
				
				spriteBulletWaiting.get(i).paintSprite(canvas, 0, 0);
				
				int t = TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)*spriteBulletWaiting.get(i).cd/spriteBulletWaiting.get(i).cdTime;
				
				canvas.clipRect(spriteBulletWaiting.get(i).x-TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind)/2, 
						        spriteBulletWaiting.get(i).y-TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind), 
						        spriteBulletWaiting.get(i).x+TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind),
						        spriteBulletWaiting.get(i).y-(TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)-t));
				
				spriteBulletWaiting.get(i).Alpha = 255;
				
				spriteBulletWaiting.get(i).paintSprite(canvas, 0, 0);
				
				canvas.restore();
				
				if(showSilent)
				{
					Paint paint = new Paint();
					
					skillSilent.drawBitmap(canvas, skillSilent.bitmap, 
							(spriteBulletWaiting.get(i).x-TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind)/2)+(TurnGameSpriteLibrary.GetW(spriteBulletWaiting.get(i).kind)-skillSilent.bitmap.getWidth()>>1), 
					        spriteBulletWaiting.get(i).y-TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)+(TurnGameSpriteLibrary.GetH(spriteBulletWaiting.get(i).kind)-skillSilent.bitmap.getHeight()>>1),  paint);
				}
				Sprite tempSprite=spriteBulletWaiting.get(i);
				float tempsize=0.6f;
				int tempnengliang=TurnGameSpriteLibrary.Getnengliang(tempSprite.kind);
				float tempx=tempSprite.x-TurnGameMain.sprite_ui_Action_2.bitmap.getWidth()/2-TurnGameMain.sprite_reward_num[0].bitmap.getWidth()/2*(""+tempnengliang).length();
				float tempy=tempSprite.y;
				TurnGameMain.sprite_ui_Action_2.drawBitmap(canvas, tempx, tempy,tempsize,tempsize, 255, 0, 0, 0, 0, 0, 0);
				tempx+=TurnGameMain.sprite_ui_Action_2.bitmap.getWidth()*tempsize;
				tempy+=TurnGameMain.sprite_ui_Action_2.bitmap.getHeight()*tempsize/2-TurnGameMain.sprite_reward_num[0].bitmap.getHeight()/2;
				GameLibrary.DrawNumber(canvas, TurnGameMain.sprite_reward_num, tempx, tempy, TurnGameMain.NumChars, ""+tempnengliang, null, (byte)0, 0f,0.9f,0.9f);
				if(TurnGameMain.turn!=0||tempnengliang>TurnGameMain.turn_nengliang){
					tempx=tempSprite.x-TurnGameMain.sprite_skill_silent.bitmap.getWidth()/2;
					tempy=tempSprite.y-TurnGameMain.sprite_skill_silent.bitmap.getHeight();
					TurnGameMain.sprite_skill_silent.drawBitmap(canvas, TurnGameMain.sprite_skill_silent.bitmap, tempx, tempy, null);
				}
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
					int tempnengliang=TurnGameSpriteLibrary.Getnengliang(spriteBulletWaiting.get(i).kind);
					if(TurnGameMain.turn_nengliang>=tempnengliang){
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
							
//							//教学
//							if(gameMain.gameTeaching.pauseState())
//							{								
//								if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL13)
//								   gameMain.gameTeaching.finish();
//							}
						}
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
