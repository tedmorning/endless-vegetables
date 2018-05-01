package com.kokatlaruruxi.wy;
//package com.endlessvegetables2.android;
//
//import java.util.ArrayList;
//
//import android.graphics.Canvas;
//
//import com.socoGameEngine.GameConfig;
//
//public class WaitingSpriteBullet {
//
//	private ArrayList<Sprite> spriteBulletWaiting;
//	
//	private final int spriteBulletWaitingPls[] = {270, 40, 210, 70, 150, 40, 90, 70};
//	
//	//----------------------------- 装弹时间 --------------------------
//	private int reloadTime;
//	
//	//------------------------------ 蔬菜子弹类型 -----------------------
//	private int kind;
//	
//	//------------------------------ 等待中蔬菜的起始位置 ----------------------
//	private int org_x;
//	private int org_y;
//
//	public ArrayList<Sprite> getSpriteBulletWaiting()
//	{
//		return spriteBulletWaiting;
//	}
//	
//	public void init(int kind, int reloadTime, int org_x, int org_y)
//	{
//		spriteBulletWaiting = new ArrayList<Sprite>();
//		
//		this.reloadTime = reloadTime;
//		
//		this.kind = kind;
//		
//		this.org_x = org_x;
//		
//		this.org_y = org_y;	
//	}
//	
//	public void setReloadTime(int reloadTime)
//	{
//		this.reloadTime = reloadTime;
//	}
//	
//	public void addWaitingSpriteBullet(int reloadTime, int special)
//	{		
//		Sprite sprite = new Sprite();
//		sprite.initSprite(kind, (int)(org_x-spriteBulletWaitingPls[0]*GameConfig.f_zoom), (int)(org_y+spriteBulletWaitingPls[1]*GameConfig.f_zoom), Sprite.SPRITE_STATE_NONE);
//		sprite.changeAction(0);
//		
//		sprite.reloadTime = reloadTime;
//								
//		sprite.reloadJumpWaiting = 0;
//		
//		sprite.reloadJumpPoint = 0;
//		
//		sprite.special = special;
//						
//		spriteBulletWaiting.add(sprite);
//	}	
//	
//	public void updata(GameMain gameMain)
//	{
//		if(spriteBulletWaiting.size()>0&&
//			!gameMain.slingshot.slingShotBufferState&&
//		    spriteBulletWaiting.get(0).reloadTime == 0&&
//		    spriteBulletWaiting.get(0).reloadJumpStep == 0&&
//		    gameMain.readSpriteBullet.getSpriteBullet().state == Sprite.SPRITE_STATE_NONE)
//		{
//			setSpriteBullet(gameMain.readSpriteBullet, gameMain.slingshot.SLINGSHOT_X , gameMain.slingshot.SLINGSHOT_Y);
//		}
//		
//		for(int i=0;i<spriteBulletWaiting.size();i++)
//		{			
//			spriteBulletWaiting.get(i).updataSprite();		
//								
//			if(spriteBulletWaiting.get(i).reloadTime==0)
//			{										
//				if(spriteBulletWaiting.get(i).reloadJumpWaiting>2&&spriteBulletWaiting.get(i).reloadJumpStep>0)
//				{
//					spriteBulletWaiting.get(i).reloadJumpWaiting = 0;
//					
//					spriteBulletWaiting.get(i).reloadJumpStep --;
//					
//					spriteBulletWaiting.get(i).reloadJumpPoint ++;
//					
//					spriteBulletWaiting.get(i).setXY((int)(org_x-spriteBulletWaitingPls[2*spriteBulletWaiting.get(i).reloadJumpPoint]*GameConfig.f_zoom), 
//							                         (int)(org_y+spriteBulletWaitingPls[2*spriteBulletWaiting.get(i).reloadJumpPoint+1]*GameConfig.f_zoom));
//				}
//				else
//				{
//					spriteBulletWaiting.get(i).reloadJumpWaiting ++;
//				}
//			}
//			else
//			{
//				spriteBulletWaiting.get(i).reloadTime -- ;
//	
//				if(spriteBulletWaiting.get(i).reloadTime==0)
//				{
//					if(i==0)
//					{
//						spriteBulletWaiting.get(i).reloadJumpStep =  3;
//					}
//					else if(i==1)
//					{
//						spriteBulletWaiting.get(i).reloadJumpStep =  2;
//					}
//					else if(i==2)
//					{
//						spriteBulletWaiting.get(i).reloadJumpStep =  1;
//					}
//					else if(i>=3)
//					{
//						spriteBulletWaiting.get(i).reloadJumpStep =  0;
//					}
//					
//					spriteBulletWaiting.get(i).setState(Sprite.SPRITE_STATE_NORMAL);
//				}
//			}		
//		}
//		
//		if(spriteBulletWaiting.size()<3)
//		{
//			addWaitingSpriteBullet(reloadTime, gameMain.gameMainLeftPlayerSpecial);
//		}				
//	}
//	
//	public void setSpriteBullet(ReadSpriteBullet readSpriteBullet, int x , int y)
//	{				
//		int id = -1;
//		
//		int special = spriteBulletWaiting.get(0).special;
//		
//		if(spriteBulletWaiting.size()>0)
//		{
//			id = spriteBulletWaiting.get(0).kind;
//			
//			spriteBulletWaiting.remove(0);
//		}
//		
//		for(int i=0;i<spriteBulletWaiting.size();i++)
//		{														
//			spriteBulletWaiting.get(i).reloadJumpStep ++;
//			spriteBulletWaiting.get(i).reloadJumpWaiting = 0;												
//		}
//		
//		if(id!=-1)
//		{
//			readSpriteBullet.initSpriteBullet(id, x, y, special);
//		}
//	}
//	
//	public void paint(Canvas canvas)
//	{				
//		for(int i=spriteBulletWaiting.size()-1;i>=0;i--)
//		{					
//			if(spriteBulletWaiting.get(i)!=null&&
//			   spriteBulletWaiting.get(i).x>0)
//			{
//				spriteBulletWaiting.get(i).paintSpriteShadow(canvas, 0, 1);
//			}
//		}
//		
//		for(int i=spriteBulletWaiting.size()-1;i>=0;i--)
//		{			
//			if(spriteBulletWaiting.get(i)!=null&&i%2==1)
//			spriteBulletWaiting.get(i).paintSprite(canvas, 0, 0);						
//		}
//		
//		for(int i=spriteBulletWaiting.size()-1;i>=0;i--)
//		{			
//			if(spriteBulletWaiting.get(i)!=null&&i%2==0)
//			spriteBulletWaiting.get(i).paintSprite(canvas, 0, 0);						
//		}
//	}
//}
