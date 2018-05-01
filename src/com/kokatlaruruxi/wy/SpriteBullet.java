package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

public class SpriteBullet {
		
	private ArrayList<Sprite> spriteBulletList;
	
	private Effect effect;
	
	public SpriteBullet()
	{
		spriteBulletList = new ArrayList<Sprite>();
		
		effect = new Effect();
	}
	
	public void init()
	{
		spriteBulletList.clear();
		
		spriteBulletList = new ArrayList<Sprite>();
	}
	
	private void setSpriteBullet(Sprite sprite, int x, int y, float degree, int speed)
	{		
		sprite.setXY(x, y);
		
		sprite.jiaodu = degree - 270;
		
		sprite.byMoveDegree = degree - 180;
				
		sprite.byMoveSpeed = speed;
		
		sprite.state = Sprite.SPRITE_STATE_NORMAL;
		
		sprite.isMove = true;		
	}
	
	public void addSpriteBullet(int x, int y, float degree, int speed, int actionName, int kind, int groupId, int special, int stop_x, int stop_y)
	{				
		for(int i=0;i<spriteBulletList.size();i++)
		{
			if(!spriteBulletList.get(i).isMove&&
			    spriteBulletList.get(i).state == Sprite.SPRITE_STATE_NONE)
			{
				spriteBulletList.remove(i);
			}
		}
		
		Sprite bullet = new Sprite();			
		bullet.initSprite(kind, 0, 0, Sprite.SPRITE_STATE_NORMAL);
		bullet.changeAction(actionName);
		
		bullet.isGroupId = groupId;
		
		bullet.special = special;
		
		if(kind==CoolEditDefine.Player_LB)
			bullet.killedMonsterMaxNumber = 6;
		else if(kind==CoolEditDefine.Player_LB_2)
			bullet.killedMonsterMaxNumber = 8;
		else if(kind==CoolEditDefine.Player_LB_3)
			bullet.killedMonsterMaxNumber = 10;
		else if(kind==CoolEditDefine.Player_ZS)
			bullet.killedMonsterMaxNumber = 6;
		else if(kind==CoolEditDefine.Player_ZS_2)
			bullet.killedMonsterMaxNumber = 8;
		else if(kind==CoolEditDefine.Player_ZS_3)
			bullet.killedMonsterMaxNumber = 10;
		
		bullet.stop_x = stop_x;
		
		bullet.stop_y = stop_y;
		
		setSpriteBullet(bullet, x, y, degree, speed);
				
		spriteBulletList.add(bullet);			
	}
	
	public void addSpriteBullet1(int x, int y, float degree, int speed, int actionName, int kind, int groupId, int special, float size, int lifeTime)
	{				
		Sprite bullet = new Sprite();			
		bullet.initSprite(kind, 0, 0, Sprite.SPRITE_STATE_NORMAL);
		bullet.changeAction(actionName);
		
		bullet.isGroupId = groupId;
		
		bullet.special = special;
		
		bullet.size = size;
		
		bullet.lifeTime = lifeTime;
		
		bullet.isCombo = false;
		
		setSpriteBullet(bullet, x, y, degree, speed);
				
		spriteBulletList.add(bullet);			
	}
	
	private void spriteBulletMove(Sprite sprite, GameMain gameMain)
	{				
		if(sprite.isMove)
		{		
//			System.out.println(sprite.byMoveDegree);
			
			sprite.x += (int)ExternalMethods.getAngleX(sprite.byMoveDegree, sprite.byMoveSpeed);
			sprite.y += (int)ExternalMethods.getAngleY(sprite.byMoveDegree, sprite.byMoveSpeed);
			
//			if(sprite.getActionName()!=4)
//			{
			if(sprite.size!=1f||
			   (sprite.kind!=CoolEditDefine.Player_TD&&
			   sprite.kind!=CoolEditDefine.Player_TD_2&&
			   sprite.kind!=CoolEditDefine.Player_TD_3&&
			   sprite.kind!=CoolEditDefine.Player_MG&&
			   sprite.kind!=CoolEditDefine.Player_MG_2&&
			   sprite.kind!=CoolEditDefine.Player_MG_3&&
			   sprite.kind!=CoolEditDefine.Player_HC&&
			   sprite.kind!=CoolEditDefine.Player_HC_2&&
			   sprite.kind!=CoolEditDefine.Player_LB&&
			   sprite.kind!=CoolEditDefine.Player_LB_2&&
			   sprite.kind!=CoolEditDefine.Player_LB_3&&
			   sprite.kind!=CoolEditDefine.Player_ZS&&
			   sprite.kind!=CoolEditDefine.Player_ZS_2&&
			   sprite.kind!=CoolEditDefine.Player_ZS_3&&
			   sprite.kind!=CoolEditDefine.Player_LJ&&
			   sprite.kind!=CoolEditDefine.Player_LJ_2&&
			   sprite.kind!=CoolEditDefine.Player_LJ_3&&
			   sprite.kind!=CoolEditDefine.Player_NG&&
			   sprite.kind!=CoolEditDefine.Player_NG_2&&
			   sprite.kind!=CoolEditDefine.Player_NG_3&&
			   sprite.kind!=CoolEditDefine.Player_WD&&
			   sprite.kind!=CoolEditDefine.Player_WD_2&&
			   sprite.kind!=CoolEditDefine.Player_WD_3))		
			{
				if(sprite.getActionName()!=5)
				{
					//·´µ¯
					if(sprite.x-SpriteLibrary.GetW(sprite.kind)/2<0)
					{
						sprite.byMoveDegree = 90 - (sprite.byMoveDegree-90);
						
						sprite.jiaodu = -sprite.jiaodu;	
						
//						if(sprite.y > 0 && sprite.state != Sprite.SPRITE_STATE_NONE)
//						GameMedia.playSound(R.raw.yx014, 0);
						
						sprite.kickNumber ++;
					}
					else if(sprite.x+SpriteLibrary.GetW(sprite.kind)/2>GameConfig.GameScreen_Width)
					{
						sprite.byMoveDegree = 90 + (90-sprite.byMoveDegree);
						
						sprite.jiaodu = -sprite.jiaodu;		
						
//						if(sprite.y > 0 && sprite.state != Sprite.SPRITE_STATE_NONE)
//						GameMedia.playSound(R.raw.yx014, 0);
						
						sprite.kickNumber ++;
					}	
					
					if(sprite.kickNumber>3)
					{
						sprite.isMove = false;
					
						sprite.setState(Sprite.SPRITE_STATE_NONE);
					}
				}
//				else 
//				{
//					if(gameMain.gameUI.getComboType()==GameUI.COMBO_TYPE_2)
//					{
//						//-------------------------------- ÂÒÎè ---------------------------------------
//						//·´µ¯
//						if(sprite.x-SpriteLibrary.GetW(sprite.kind)/2<0)
//						{
//							sprite.byMoveDegree = 90 - (sprite.byMoveDegree-90);
//							
//							sprite.jiaodu = -sprite.jiaodu;			
//							
//							sprite.kickNumber ++;
//						}
//						
//						if(sprite.y-SpriteLibrary.GetH(sprite.kind)/2<100*GameConfig.f_zoomy)
//						{								
//							sprite.byMoveDegree = -sprite.byMoveDegree;
//							
//							sprite.jiaodu = sprite.byMoveDegree+270;
//							
//							sprite.kickNumber ++;
//						}					
//						
//						if(sprite.x+SpriteLibrary.GetW(sprite.kind)/2>GameConfig.GameScreen_Width)
//						{
//							sprite.byMoveDegree = 90 + (90-sprite.byMoveDegree);
//							
//							sprite.jiaodu = -sprite.jiaodu;
//							
//							sprite.kickNumber ++;
//						}	
//						
//						if(sprite.y+SpriteLibrary.GetH(sprite.kind)/2>(gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight())&&
//						   sprite.kickNumber>0)
//						{								
//							sprite.byMoveDegree = -sprite.byMoveDegree;
//							
//							sprite.jiaodu = sprite.byMoveDegree+270;	
//						}
//						
//						if(sprite.kickNumber>4)
//						{
//							sprite.isMove = false;
//						
//							sprite.setState(Sprite.SPRITE_STATE_NONE);
//						}
//					}
//				}
			}
//			}
			
//			if(sprite.special==3)
			if(sprite.size==1f&&
			   (sprite.kind==CoolEditDefine.Player_TD||
			   sprite.kind==CoolEditDefine.Player_TD_2||
			   sprite.kind==CoolEditDefine.Player_TD_3||
			   sprite.kind==CoolEditDefine.Player_MG||
			   sprite.kind==CoolEditDefine.Player_MG_2||
			   sprite.kind==CoolEditDefine.Player_MG_3||
			   sprite.kind==CoolEditDefine.Player_HC||
			   sprite.kind==CoolEditDefine.Player_HC_2||
			   sprite.kind==CoolEditDefine.Player_HC_3))					
			{				
				if(sprite.y<=sprite.stop_y)
				{
					sprite.x = sprite.stop_x;
					sprite.y = sprite.stop_y;
					
					sprite.jiaodu = 0;
					
					sprite.isMove = false;
					
					if(sprite.kind==CoolEditDefine.Player_TD||
					   sprite.kind==CoolEditDefine.Player_TD_2||
					   sprite.kind==CoolEditDefine.Player_TD_3)
					{
						sprite.changeAction(7);
					}
					else if(sprite.kind==CoolEditDefine.Player_MG||
							sprite.kind==CoolEditDefine.Player_MG_2||
							sprite.kind==CoolEditDefine.Player_MG_3)	
					{
						sprite.changeAction(6);
						
						sprite.lifeTime = 251;
					}
					else if(sprite.kind==CoolEditDefine.Player_HC||
							sprite.kind==CoolEditDefine.Player_HC_2||
							sprite.kind==CoolEditDefine.Player_HC_3)
					{			
						int num = 3;
						
						if(sprite.kind==CoolEditDefine.Player_HC_2)
							num = 4;
						else if(sprite.kind==CoolEditDefine.Player_HC_3)
							num = 5;
							
						while(num>0)
						{
							num --;
							
							int x = (int)(sprite.x);
							
							if(sprite.x<=SpriteLibrary.GetW(sprite.kind)/2)
								x = (int)(SpriteLibrary.GetW(sprite.kind)/2);
							else if(sprite.x>=GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2)
								x = (int)(GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2);
								
							addSpriteBullet1(x, (int)sprite.y, ExternalMethods.throwDice(0, 360), 6, sprite.getActionName(), sprite.kind, 0, 0, 0.5f, 25);
						}
						
//						sprite.setState(Sprite.SPRITE_STATE_NONE);
						
						sprite.changeAction(7);
						
						effect.add(CoolEditDefine.Effect_BOMB, (int)sprite.x, (int)sprite.y-SpriteLibrary.GetH(sprite.kind)/2, Sprite.SPRITE_STATE_NORMAL, 0, 1f);
						
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.bombs, 0);
					}
				}								
			}
			else 
			if(sprite.size!=1f&&
			   (sprite.kind==CoolEditDefine.Player_HC||
				sprite.kind==CoolEditDefine.Player_HC_2||
				sprite.kind==CoolEditDefine.Player_HC_3))					
			{		
				sprite.lifeTime --;
				
				if(sprite.lifeTime<=0)
				{
					sprite.isMove = false;
					
//					sprite.setState(Sprite.SPRITE_STATE_NONE);
					
					sprite.changeAction(7);
					
					effect.add(CoolEditDefine.Effect_BOMB, (int)sprite.x, (int)sprite.y-SpriteLibrary.GetH(sprite.kind)/2, Sprite.SPRITE_STATE_NORMAL, 0, 0.5f);
					
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.littlebombs, 0);
				}
			}
			else
			{								
				if(sprite.y<-100||sprite.x<-100||sprite.x>GameConfig.GameScreen_Width+100)
				{		
					if(sprite.kind==CoolEditDefine.Player_WD||
					   sprite.kind==CoolEditDefine.Player_WD_2||
					   sprite.kind==CoolEditDefine.Player_WD_3)
					{
						sprite.isMove = false;
						
						sprite.setState(Sprite.SPRITE_STATE_NONE);
						
						changeSameGroupIDClearCombo(gameMain);
					}
					else
					{
						sprite.isMove = false;
							
						sprite.setState(Sprite.SPRITE_STATE_NONE);	
						
						if(sprite.kind==CoolEditDefine.Player_LB||
						   sprite.kind==CoolEditDefine.Player_LB_2||
						   sprite.kind==CoolEditDefine.Player_LB_3||
						   sprite.kind==CoolEditDefine.Player_ZS||
						   sprite.kind==CoolEditDefine.Player_ZS_2||
						   sprite.kind==CoolEditDefine.Player_ZS_3)
						{
							if(sprite.killedMonsterNumberIndex==0)
								gameMain.combo.comboNumberClaer();
						}				
						else 
							gameMain.combo.comboNumberClaer();
					}
				}
			}
		}
	}
	
	public void updata(GameMain gameMain)
	{				
		effect.updata();
		
		for(int i=0;i<spriteBulletList.size();i++)
		{
			if(spriteBulletList.get(i).state!=Sprite.SPRITE_STATE_NONE)
			{
				spriteBulletList.get(i).updataSprite();
				
				if(spriteBulletList.get(i).isMove)							
					spriteBulletMove(spriteBulletList.get(i), gameMain);	
				
				//---------------------- Ä¢¹½ -----------------------------------
				if(spriteBulletList.get(i).lifeTime>0&&
				   spriteBulletList.get(i).getActionName()==6)
				{
					spriteBulletList.get(i).lifeTime --;
					
					if(spriteBulletList.get(i).lifeTime%50==0)
					{
						spriteBulletList.get(i).changeAction(7);												
					}
				}
			}
			else
			{
				if(spriteBulletList.get(i).isMove)
					spriteBulletMove(spriteBulletList.get(i), gameMain);
				else	
					spriteBulletList.remove(i);				
			}					
		}
	}
	
	public void paintShadow(Canvas canvas)
	{
		for(int i=0;i<spriteBulletList.size();i++)
		{		
			if(spriteBulletList.get(i).kind==CoolEditDefine.Player_MG||
			   spriteBulletList.get(i).kind==CoolEditDefine.Player_MG_2||
			   spriteBulletList.get(i).kind==CoolEditDefine.Player_MG_3)
			{
				if(spriteBulletList.get(i).getActionName()==6||
				   spriteBulletList.get(i).getActionName()==7)
				spriteBulletList.get(i).paintSpriteShadow(canvas, 0, 1);
			}
		}
	}
	
	public void paint(Canvas canvas)
	{		
		for(int i=0;i<spriteBulletList.size();i++)
		{					
			spriteBulletList.get(i).paintSprite(canvas, 0, 0);			
		}
		
		effect.paint(canvas);
	}		
	
	public ArrayList<Sprite> getSpriteBulletList()
	{
		return spriteBulletList;
	}
	
	public Sprite getSpriteBullet(int id)
	{
		return spriteBulletList.get(id);
	}		
	
	public void changeSameGroupIDisCombo()
	{		
		for(int i=0;i<spriteBulletList.size();i++)
		{
			if(spriteBulletList.get(i).kind==CoolEditDefine.Player_WD||
			   spriteBulletList.get(i).kind==CoolEditDefine.Player_WD_2||
			   spriteBulletList.get(i).kind==CoolEditDefine.Player_WD_3)
			{
				if(!spriteBulletList.get(i).isMove && spriteBulletList.get(i).isTouch)
				{
					for(int j=0;j<spriteBulletList.size();j++)
					{
						if(spriteBulletList.get(i).isGroupId==spriteBulletList.get(j).isGroupId)
						{
							spriteBulletList.get(j).isCombo = false;
						}
					}
				}
			}
		}
	}
	
	public void changeSameGroupIDClearCombo(GameMain gameMain)
	{			
		for(int i=0;i<spriteBulletList.size();i++)
		{			
			if(spriteBulletList.get(i).kind==CoolEditDefine.Player_WD||
			   spriteBulletList.get(i).kind==CoolEditDefine.Player_WD_2||
			   spriteBulletList.get(i).kind==CoolEditDefine.Player_WD_3)
			{	
				boolean result = false;
								
				for(int j=0;j<spriteBulletList.size();j++)
				{
					if(spriteBulletList.get(i).isGroupId==spriteBulletList.get(j).isGroupId)
					{
						if(spriteBulletList.get(j).isMove)
						{
							result = true;
							
							break;
						}
					}
				}
				
				if(!result)
				{					
					if(spriteBulletList.get(i).isCombo)
					{
						gameMain.combo.comboNumberClaer();
					}
				}
			}
		}
	}
	
//	public boolean special(Sprite sprite)
//	{		
//		boolean result = false;
//		
////		if(sprite.special==0)
////		{
//			sprite.isMove = false;
//			
//			sprite.setState(Sprite.SPRITE_STATE_NONE);
//			
//			result = true;
////		}
////		else if(sprite.special==2)
////		{
////			sprite.isMove = false;
////			
////			if(sprite.getActionName()!=5)
////			sprite.changeAction(5);
////		}
////		else if(sprite.special==3||
////				sprite.special==4)
////		{
////			sprite.jiaodu = 0;
////			
////			sprite.isMove = false;
////			
////			if(sprite.getActionName()!=4)
////			sprite.changeAction(4);
////		}
//		
//		return result;
//	}	
}
