package com.endlessvegetables2.turngame;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.Achievement;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;

public class TurnGameMonster {

	public static ArrayList<TurnGameSprite> Enemy;
			
	private TurnGameMonsterMove gameMonsterMove;
	
	private TurnGameSprite wordNum[];
	
	private char wordNumChars[];
	
	private ArrayList<DamageNumberAnimation> damageNumberAnimation;
	
	private TurnGameEffect effect;
	
	private TurnGameShield gameShield;
	
	private TurnGameSlow gameSlow;
	
	private TurnGameHeadFire gameHeadFire;
	
	private int centerX;
	
	private int centerY;
	
	public static boolean centerMove;
	
	private long idNum;
	
	public static int shxSkillTime;	
	
	public static boolean xIsSkill;		
	
	private int linkNumber;
	
	private Bitmap[] wordNumBit;
	
	private int abnormalStateNumber;//累计异常状态数
			
	private int sirenCallNumber;//警报器触发次数
	
	public void init()
	{
		Enemy = new ArrayList<TurnGameSprite>();
		
		gameMonsterMove = new TurnGameMonsterMove();
		
		damageNumberAnimation = new ArrayList<DamageNumberAnimation>();
		
		effect = new TurnGameEffect();
		effect.loadImage();		
		
		wordNumChars = new char[]{'0','1','2','3','4','5','6','7','8','9'};
		
		gameShield = new TurnGameShield();
		
		gameSlow = new TurnGameSlow();
		
		gameHeadFire = new TurnGameHeadFire();
		
		idNum = 0;
		
		linkNumber = 1;
		
		xIsSkill = false;
		
		abnormalStateNumber = 0;
		
		sirenCallNumber = 0;
	}
	
//	public List<RectF> getShieldRectList()
//	{
//		return isShieldRect;
//	}
	
	public void loadImage()
	{		
		wordNumBit = GameImage.getAutoSizecutBitmap("word/num_5", 10, 1, GameImage.Sort_line);
		wordNum = new TurnGameSprite[wordNumBit.length];
		for(int i=0;i<wordNumBit.length;i++)
		{
			wordNum[i] = new TurnGameSprite(wordNumBit[i]);
		}
	}
	
	public void delImage()
	{
		effect.delImage();
		
		gameShield.delImage();
		
		gameSlow.delImage();
		
		gameHeadFire.delImage();
		
		for(int i=0;i<wordNum.length;i++)
		{
			GameImage.delImage(wordNum[i].bitmap);
		}
		
		GameImage.delImageArray(wordNumBit);
	}
	
	public void addEnemy(int kind, int x, int y,
			             int AppearWaitingTime, /*int MoveStyle,*/ int stop_y, 
			             int moveSpeed, int attackTime, int standWaitingTime)
	{
		boolean bloodIsOne = false;//将怪物生命值改为1
		
		if(kind>10000)//判断怪物类型值是否大于10000，如果是的话说明要将其生命值改为1
		{
			kind -=10000;
			
			bloodIsOne = true;
		}
			
		TurnGameSprite sprite = new TurnGameSprite();
		
		sprite.initSprite(kind, x, y, TurnGameSprite.SPRITE_STATE_NONE);
		
//		sprite.changeAction(sprite.Enemy_action0);
		
		sprite.transitionWaiting = AppearWaitingTime;
		
//		sprite.byMoveStyle = (byte)MoveStyle;
		
		sprite.orgbyMoveStop = stop_y/*-SpriteLibrary.GetH(sprite.kind)*/;
		
		sprite.byMoveStop = sprite.orgbyMoveStop;
		
		if(kind==CoolEditDefine.Enemy_DS||
		   kind==CoolEditDefine.Enemy_YGDS||
		   kind==CoolEditDefine.Enemy_X||
		   kind==CoolEditDefine.Enemy_SHX||
		   kind==CoolEditDefine.Enemy_YGX||
		   kind==CoolEditDefine.Enemy_Z||
		   kind==CoolEditDefine.Enemy_SHZ||
		   kind==CoolEditDefine.Enemy_YGZ)
		{
			
			int l = (int)(sprite.y+ExternalMethods.throwDice(100, 200));
			
			if(kind==CoolEditDefine.Enemy_Z||
			   kind==CoolEditDefine.Enemy_SHZ||
			   kind==CoolEditDefine.Enemy_YGZ)
				l = (int)(sprite.y+ExternalMethods.throwDice(50, 100));
			
			sprite.byMoveStop = l>sprite.orgbyMoveStop?sprite.orgbyMoveStop:l;
		}		
		
		sprite.byMoveSpeed = moveSpeed;
		
		sprite.orgAttackTime = attackTime;
		
		sprite.attackTime = sprite.orgAttackTime;
		
		sprite.lifeMax = bloodIsOne?1:TurnGameSpriteLibrary.GetHP(sprite.kind);
		
		sprite.life = sprite.lifeMax;	
		System.out.println("clpqy:kind="+kind+",life="+sprite.life);
		sprite.byMoveWaitingTime = standWaitingTime;
		
		sprite.idNum = idNum++;
		
		gameShield.setShield((int)sprite.x, (int)sprite.y);
		
		gameSlow.setSlow((int)sprite.x, (int)sprite.y-TurnGameSpriteLibrary.GetH(kind));
		
		gameHeadFire.setHeadFire((int)sprite.x, (int)sprite.y-TurnGameSpriteLibrary.GetH(kind));
		
		Enemy.add(sprite);				
	}
		
	public ArrayList<TurnGameSprite> getEnemyList()
	{
		return Enemy;
	}
	
	public ArrayList<TurnGameSprite> getEnemyList(int kind)
	{
		ArrayList<TurnGameSprite> list = new ArrayList<TurnGameSprite>();
		
		for(int i=0;i<Enemy.size();i++)
		{
			if(Enemy.get(i).kind==kind)
			{
				list.add(Enemy.get(i));
			}
		}
		
		return list;
	}
	
	public int isAliveMonsterNumber()
	{
		int num = 0;
		
		for(int i=0;i<Enemy.size();i++)
		{
			if(Enemy.get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
			   Enemy.get(i).kind!=CoolEditDefine.Enemy_MEGICWATER&&
			   Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
			   Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
			   Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
			   Enemy.get(i).kind!=CoolEditDefine.Enemy_PANGXIEZIDAN)
			{
				num ++;
			}
		}
		
		return num;
	}
	
	
	private int EnemySort[];
	
	/*
	 * 对怪物进行前后排序
	 * */
	private void sort()
	{
		int len = Enemy.size();
		
		boolean spriteEnemyIsLive[] = new boolean[len];
		
		EnemySort = new int[len];
		
		for(int i=0;i<spriteEnemyIsLive.length;i++)
		{
			spriteEnemyIsLive[i] = true;
			
			EnemySort[i] = 0;
		}
		
		int EnemySortId = -1;
		
		while(len>0)
		{
			len --;
			
			boolean result = false;
			
			for(int i=0;i<spriteEnemyIsLive.length;i++)
			{
				if(spriteEnemyIsLive[i])
				{
					EnemySortId ++;
					
					EnemySort[EnemySortId] = i;
					
					spriteEnemyIsLive[i] = false;
					
					result = true;
					
					break;
				}	
			}
			
			if(!result)
				break;
				
			for(int i=0;i<Enemy.size();i++)
			{
				if(spriteEnemyIsLive[i])
				{
					if(Enemy.get(i).y<Enemy.get(EnemySort[EnemySortId]).y)
					{
						spriteEnemyIsLive[EnemySort[EnemySortId]] = true;
						
						EnemySort[EnemySortId] = i;
						
						spriteEnemyIsLive[EnemySort[EnemySortId]] = false;
					}
				}	
			}
			
			for(int i=0;i<Enemy.size();i++)
			{
				if(spriteEnemyIsLive[i])
				{
					if(Enemy.get(i).y==Enemy.get(EnemySort[EnemySortId]).y)
					{
						spriteEnemyIsLive[i] = false;
						
						EnemySortId ++;
						
						EnemySort[EnemySortId] = i;
					}
				}	
			}
		}		
	}
	
	public void paint(Canvas canvas)
	{
		sort();
		
		for(int i=0;i<EnemySort.length;i++)
		{			
			if(Enemy.get(EnemySort[i]).getActionName()!=TurnGameSprite.Enemy_action02)
			{				
				if(Enemy.get(EnemySort[i]).getActionName()>TurnGameSprite.Enemy_action0&&
				   Enemy.get(EnemySort[i]).kind!=CoolEditDefine.Enemy_DS&&
				   Enemy.get(EnemySort[i]).kind!=CoolEditDefine.Enemy_YGDS)
				{
					if(!Enemy.get(EnemySort[i]).isFly)
					{		
						if(Enemy.get(EnemySort[i]).kind==CoolEditDefine.Enemy_MEGICWATER&&Enemy.get(EnemySort[i]).state==TurnGameSprite.SPRITE_STATE_STAND)
							Enemy.get(EnemySort[i]).paintSpriteShadow(canvas, 0, 1);
						else if(Enemy.get(EnemySort[i]).kind==CoolEditDefine.Enemy_SIREN)
							Enemy.get(EnemySort[i]).paintSpriteShadow(canvas, 40, 1);
						else if(Enemy.get(EnemySort[i]).kind!=CoolEditDefine.Enemy_MEGICWATER&&
								Enemy.get(EnemySort[i]).kind!=CoolEditDefine.Enemy_SIREN)
							Enemy.get(EnemySort[i]).paintSpriteShadow(canvas, 0, 1);
						
					}
				}
			}
		}
		
		for(int i=0;i<EnemySort.length;i++)
		{
			if(Enemy.get(EnemySort[i]).kind != CoolEditDefine.Enemy_MMJS&&
			   Enemy.get(EnemySort[i]).kind != CoolEditDefine.Enemy_SHZYXZ&&
			   Enemy.get(EnemySort[i]).kind != CoolEditDefine.Enemy_SIREN)
			{
				Enemy.get(EnemySort[i]).paintSprite(canvas, 0, 0);
				
				if(Enemy.get(EnemySort[i]).shieldTime>0)
				gameShield.paint(canvas, EnemySort[i], (int)Enemy.get(EnemySort[i]).x, (int)Enemy.get(EnemySort[i]).y);
				
				if(Enemy.get(EnemySort[i]).speedDownTime>0)
				gameSlow.paint(canvas, EnemySort[i], (int)Enemy.get(EnemySort[i]).x, (int)Enemy.get(EnemySort[i]).y-TurnGameSpriteLibrary.GetH(Enemy.get(EnemySort[i]).kind));
				
				if(Enemy.get(EnemySort[i]).isHeadFire)
//				if(Enemy.get(EnemySort[i]).lostBloodTime>0)
				gameHeadFire.paint(canvas, EnemySort[i], (int)Enemy.get(EnemySort[i]).x, (int)Enemy.get(EnemySort[i]).y-TurnGameSpriteLibrary.GetH(Enemy.get(EnemySort[i]).kind));
			}
		}
		
		for(int i=0;i<EnemySort.length;i++)
		{
			if(Enemy.get(EnemySort[i]).kind == CoolEditDefine.Enemy_MMJS||
			   Enemy.get(EnemySort[i]).kind == CoolEditDefine.Enemy_SHZYXZ||
			   Enemy.get(EnemySort[i]).kind == CoolEditDefine.Enemy_SIREN)
			Enemy.get(EnemySort[i]).paintSprite(canvas, 0, 0);
		}
		
		if(effect!=null)
		effect.paint(canvas);
		
		for(int i=0;i<damageNumberAnimation.size();i++)
		{
			damageNumberAnimation.get(i).drawDamageNumberAnimation(canvas);
		}
	}
	
//	RectF r = new RectF();
	
	private void bearSkill(TurnGameSprite sprite)
	{
		if(sprite.kind == CoolEditDefine.Enemy_X)//石器熊技能
		{		
			if(sprite.y<sprite.orgbyMoveStop)
			{										
				if(xIsSkill)
				{					
					RectF r = new RectF();
					
					r.top 	 = sprite.getCollisionRect().top-50;
					r.left 	 = sprite.getCollisionRect().left-50;
					r.right  = sprite.getCollisionRect().right+50;
					r.bottom = sprite.getCollisionRect().bottom+50;
					
					for(int j=0;j<Enemy.size();j++)
					{
						if(Enemy.get(j).kind!=CoolEditDefine.Enemy_HZXJ&&
						   Enemy.get(j).kind!=CoolEditDefine.Enemy_MMJS&&
						   Enemy.get(j).kind!=CoolEditDefine.Enemy_SHZYXZ&&
						   Enemy.get(j).kind!=CoolEditDefine.Enemy_YGYYM&&
						   Enemy.get(j).kind!=CoolEditDefine.Enemy_YGYKS&&
						   Enemy.get(j).kind!=CoolEditDefine.Enemy_MM&&
						   Enemy.get(j).kind!=CoolEditDefine.Enemy_PANGXIEZIDAN)
						{
							if(Enemy.get(j).state!=TurnGameSprite.SPRITE_STATE_NONE&&
							   Enemy.get(j).state!=TurnGameSprite.SPRITE_STATE_NORMAL&&
							   Enemy.get(j).state!=TurnGameSprite.SPRITE_STATE_DEAD&&
							   Enemy.get(j).state!=TurnGameSprite.SPRITE_STATE_REVIVAL)
							{
								if(ExternalMethods.RectCollision(r, Enemy.get(j).getCollisionRect()))
								{
									Enemy.get(j).shieldTime = 50;
								}
							}
						}
					}	
					
					xIsSkill = false;
				}
			}
		}
	}		
	
	public void updata(TurnGameMain gameMain)
	{		
		if(gameMain.doubleGameNumberTime>0)
		gameMain.doubleGameNumberTime --;
		
		if(shxSkillTime>0)
		shxSkillTime--;
		
		if(effect!=null)
		effect.updata();
		
		for(int i=0;i<Enemy.size();i++)
		{
			//------------------------  持续损伤HP效果 -----------------------
			if(Enemy.get(i).life>1)
			{
				if(Enemy.get(i).lostBloodTime>0)
				{
					Enemy.get(i).lostBloodTime --;
					
					if(Enemy.get(i).lostBloodTime%Enemy.get(i).lostBloodTimeOffset==0)
					{
						int lostBloodNum = ExternalMethods.throwDice(1, Enemy.get(i).lostBloodAmount);
						
						Enemy.get(i).life -= lostBloodNum;
						
						addDamageNumberAnimation((int)Enemy.get(i).x, (int)Enemy.get(i).y, lostBloodNum);
						
						if(Enemy.get(i).life<=1)
						{
							Enemy.get(i).life = 1;
							
							Enemy.get(i).lostBloodTime = 0;
							
							Enemy.get(i).lostBloodTimeOffset = 0;
							
							Enemy.get(i).lostBloodAmount = 0;
							
							Enemy.get(i).isHeadFire = false;
						}
					}
				}
				else
				{
					Enemy.get(i).lostBloodTime = 0;
					
					Enemy.get(i).lostBloodTimeOffset = 0;
					
					Enemy.get(i).lostBloodAmount = 0;
					
					Enemy.get(i).isHeadFire = false;
				}
			}
			else
			{
				Enemy.get(i).lostBloodTime = 0;
				
				Enemy.get(i).lostBloodTimeOffset = 0;
				
				Enemy.get(i).lostBloodAmount = 0;
				
				Enemy.get(i).isHeadFire = false;
			}
			
			if(Enemy.get(i).transitionWaiting>0)
			{
				Enemy.get(i).transitionWaiting -- ;
								
				if(Enemy.get(i).kind != CoolEditDefine.Enemy_MMB1&&
				   Enemy.get(i).kind != CoolEditDefine.Enemy_MMB2&&
				   Enemy.get(i).kind != CoolEditDefine.Enemy_MMB3&&
				   Enemy.get(i).kind != CoolEditDefine.Enemy_MMB4&&
				   Enemy.get(i).kind != CoolEditDefine.Enemy_CHG)
				{
					if(Enemy.get(i).transitionWaiting == 0)
					{
						Enemy.get(i).changeAction(TurnGameSprite.Enemy_action0);
						
						Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_NORMAL);	
						
						if(Enemy.get(i).kind == CoolEditDefine.Enemy_MEGICWATER)
						{
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.water1s, 0);
						}
						else if(Enemy.get(i).kind == CoolEditDefine.Enemy_MM||
								Enemy.get(i).kind == CoolEditDefine.Enemy_YGY||
								Enemy.get(i).kind == CoolEditDefine.Enemy_SHZY)
						{
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.boss2s, 0);
						}
					}
				}
			}
			else
			{						
				if(Enemy.get(i).freezeState)
				{					
					if(Enemy.get(i).freezeTime>0)
					{		
						Enemy.get(i).freezeTime --;
						
						if(Enemy.get(i).freezeTime==0)
						{
							Enemy.get(i).freezeState = false;
							
//							if(Enemy.get(i).life<=0)
//							{						
//								Enemy.get(i).changeAction(Sprite.Enemy_action02);
//								
//								Enemy.get(i).setState(Sprite.SPRITE_STATE_DEAD);
//								
//								gameMain.addGoldenAnimation(Enemy.get(i));
//								
////								gameMain.gameNumber += SpriteLibrary.GetNumber(Enemy.get(i).kind);
////								
////								if(gameMain.doubleGameNumberTime>0)
////								gameMain.gameNumber += SpriteLibrary.GetNumber(Enemy.get(i).kind);									
//							}
						}
					}
				}
				else
				{		
					if(Enemy.get(i).dizzinessTime>0){
						//眩晕
//						Enemy.get(i).dizzinessTime --;	
					}
										
					
					if(Enemy.get(i).kind != CoolEditDefine.Enemy_MMB1&&
					   Enemy.get(i).kind != CoolEditDefine.Enemy_MMB2&&
					   Enemy.get(i).kind != CoolEditDefine.Enemy_MMB3&&
					   Enemy.get(i).kind != CoolEditDefine.Enemy_MMB4&&
					   Enemy.get(i).kind != CoolEditDefine.Enemy_CHG)
					{						
						if(Enemy.get(i).shieldTime>0)
						Enemy.get(i).shieldTime --;
						
						Enemy.get(i).updataSprite();
						
						gameShield.updata(i);
						
						gameSlow.updata(i);
												
						gameHeadFire.updata(i);
												
						if(Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_MOVE||
						   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_STAND||
						   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_ATTACK||
						   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_REVIVAL)
						{
							if(Enemy.get(i).dizzinessTime<=0)
							moveOrAttack(Enemy.get(i), gameMain);

							bearSkill(Enemy.get(i));
						}
						
						spriteFlyMove(Enemy.get(i));
					}
					else 
					{
						if(Enemy.get(i).dizzinessTime<=0)
						moveOrAttack(Enemy.get(i), gameMain);
						
						if(Enemy.get(i).state != TurnGameSprite.SPRITE_STATE_NONE)
						Enemy.get(i).updataSprite();												
					}
				}	
			}
		}
		
		for(int i=0;i<Enemy.size();i++)
		{
			if(Enemy.get(i).state==TurnGameSprite.SPRITE_STATE_NONE && Enemy.get(i).life<=0)
			{
//				if(Enemy.get(i).kind == CoolEditDefine.Enemy_CHG&&Enemy.get(i).isTouch)
//				{
//					byte rmd = (byte)ExternalMethods.throwDice(GameCenterEffect.CARD1_EFFECT, GameCenterEffect.CARD5_EFFECT);
//						
//					gameMain.gameCenterEffect.show(gameMain, rmd);			
//					
//					if(rmd == GameCenterEffect.CARD1_EFFECT)
//						gameMain.doubleGameNumberTime = 250;
//				}	
					
				Enemy.remove(i);
				
				gameShield.remove(i);
				
				gameSlow.remove(i);
				
				gameHeadFire.remove(i);
			}
		}
		
		for(int i=0;i<damageNumberAnimation.size();i++)
		{
			if(!damageNumberAnimation.get(i).getDamageNumberState())
				damageNumberAnimation.remove(i);
		}				
	}
	
	private void moveOrAttack(TurnGameSprite sprite, TurnGameMain gameMain)
	{
//		//大家快看飞艇来了
//		if(gameMain.gameAirship.getState())
//		{
//			return;
//		}
		if(TurnGameMain.turn!=4)
		{
			if(sprite.state==TurnGameSprite.SPRITE_STATE_ATTACK
					||sprite.state==TurnGameSprite.SPRITE_STATE_MOVE
					){
				sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);
				sprite.changeAction(TurnGameSprite.Enemy_action03);
			}
			return;
		}
		
		if(sprite.jumpState>0)//跳起
		{
			return;
		}
		
//		if(sprite.dizzinessTime>0)//眩晕
//		{
//			sprite.dizzinessTime --;
//			
//			return;
//		}
		
		if(sprite.speedDownTime>0)//缓慢
		{
			sprite.speedDownTime --;
			
			if(sprite.speedDownTime%5!=0)
			{
				return;
			}					
		}
		else
		{
			sprite.setSlowDown(false);
		}
			
		if(sprite.kind == CoolEditDefine.Enemy_MEGICWATER)//魔法水滴
		{
			if(sprite.state == TurnGameSprite.SPRITE_STATE_STAND)
			{
				for(int i=0;i<getEnemyList().size();i++)
				{		
					if(!getEnemyList().get(i).isFly)
					{
						if(getEnemyList().get(i).linkNumber==0&&
						   getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MEGICWATER&&
						   getEnemyList().get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
						   getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MMJS&&
						   getEnemyList().get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
						   getEnemyList().get(i).kind!=CoolEditDefine.Enemy_YGYYM&&
						   getEnemyList().get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
						   getEnemyList().get(i).kind!=CoolEditDefine.Enemy_PANGXIEZIDAN)
						{									
							if(ExternalMethods.RectCollision(sprite.getCollisionRect(), getEnemyList().get(i).getCollisionRect()))
							{
								getEnemyList().get(i).linkNumber = linkNumber;
								
								getEnemyList().get(i).magicWaterProtect = true;
								
								sprite.linkNumber = linkNumber;		
								
								sprite.changeAction(TurnGameSprite.Enemy_action27);
								
								sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
								
								linkNumber ++;
								
								break;
							}								
						}
					}
				}
			}			
			
			if(sprite.linkNumber>0)
			{
				boolean isMove = false;
				
				for(int i=0;i<getEnemyList().size();i++)
				{		
					if(getEnemyList().get(i).kind != CoolEditDefine.Enemy_MEGICWATER)
					{
						if(sprite.linkNumber==getEnemyList().get(i).linkNumber)
						{			
							float offset = ((sprite.getCollisionRect().bottom - sprite.getCollisionRect().top)-
							               (getEnemyList().get(i).getCollisionRect().bottom - getEnemyList().get(i).getCollisionRect().top))/2;
							
							sprite.setXY((int)getEnemyList().get(i).x, (int)(getEnemyList().get(i).y+offset));
							
							isMove = true;
							
							break;											
						}
					}
				}
				
				if(!isMove)
				{
					sprite.life = 0;
					
					sprite.linkNumber = 0;
					
//					sprite.changeAction(Sprite.Enemy_action03);
//					
//					sprite.setState(Sprite.SPRITE_STATE_STAND);	
					
					sprite.changeAction(TurnGameSprite.Enemy_action02);
					
					sprite.setState(TurnGameSprite.SPRITE_STATE_DEAD);					
				}
			}
		}		 	
		else if(sprite.kind == CoolEditDefine.Enemy_SIREN)//警报器行动方式
		{					
			if(sprite.byMoveWaitingTime==-100)
			{
				sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
				
				sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 1);
				
				if(sprite.byMoveDirect == 0)//从右向左
				{			
					sprite.changeAction(TurnGameSprite.Enemy_action30);
					
					sprite.setXY(GameConfig.GameScreen_Width, sprite.orgbyMoveStop);
				}
				else//从左向右
				{
					sprite.changeAction(TurnGameSprite.Enemy_action04);
															
					sprite.setXY(0, sprite.orgbyMoveStop);										
				}	
				
				sprite.byMoveWaitingTime = 0;
			}	
			else if(sprite.byMoveWaitingTime==0)
			{								
				if(sprite.state == TurnGameSprite.SPRITE_STATE_MOVE)
				{
					sprite.attackTime -- ;
					
					if(sprite.byMoveDirect == 0)//从右向左
					{
						if(gameMonsterMove.RToLMove(sprite))
						{							
							sprite.changeAction(TurnGameSprite.Enemy_action04);
							
							sprite.setXY(0, (int)(GameLibrary.getIntRandom(200, 350)*GameConfig.f_zoomy));	
							
							sprite.byMoveDirect = 1;
						}
					}
					else
					{
						if(gameMonsterMove.LToRMove(sprite))
						{										
							sprite.changeAction(TurnGameSprite.Enemy_action30);
							
							sprite.setXY(GameConfig.GameScreen_Width, (int)(GameLibrary.getIntRandom(200, 350)*GameConfig.f_zoomy));	
							
							sprite.byMoveDirect = 0;
						}
					}
					
					if(ExternalMethods.throwDice(0, 99) == 50)
					{
						sprite.byMoveWaitingTime = 25;
						
						sprite.changeAction(TurnGameSprite.Enemy_action03);	
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);
					}
					
					if(sprite.attackTime<=0)
					{						
						if(sprite.x<TurnGameSpriteLibrary.GetW(sprite.kind)||
						   sprite.x>GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind))
						{
							sprite.attackTime = 4;
						}
						else
						{
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
							
							sirenCallNumber ++;
							
							sprite.attackTime = -50;
							
							sprite.summonsNumber = ExternalMethods.throwDice(1, 2);
							
							sprite.sirenLight.setXY((int)sprite.x, (int)(sprite.y-16*GameConfig.f_zoomy));
							sprite.sirenLight.setShow(true);
						}
					}
				}
				else if(sprite.state == TurnGameSprite.SPRITE_STATE_ATTACK)
				{
					sprite.attackTime ++;
										
					if(sprite.attackTime>=0)
					{					
						if(sprite.byMoveDirect == 0)//从右向左
							sprite.changeAction(TurnGameSprite.Enemy_action30);
						else
							sprite.changeAction(TurnGameSprite.Enemy_action04);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);
						
						sprite.attackTime = sprite.orgAttackTime;
						
						sprite.sirenLight.setShow(false);
					}
					else if(sprite.attackTime>=-20)
					{																	
						if(sprite.attackTime%10==0&&sprite.summonsNumber>0)
						{
							sprite.summonsNumber --;
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.summons, 0);
						
							gameMain.gameScriptRun.summonsMonster(1, gameMain, (int)(sprite.x)+ExternalMethods.throwDice(-20, 20), (int)(sprite.y+60*GameConfig.f_zoomy));
						}
					}
				}						
			}
			else if(sprite.byMoveWaitingTime>0)
			{				
				sprite.byMoveWaitingTime --;	
				
				if(sprite.byMoveWaitingTime<=0)
				{
					if(sprite.byMoveDirect == 0)//从右向左
						sprite.changeAction(TurnGameSprite.Enemy_action30);
					else
						sprite.changeAction(TurnGameSprite.Enemy_action04);
					
					sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);
					
					sprite.attackTime = sprite.orgAttackTime;
				}
			}
			
//			
//			if(sprite.byMoveWaitingTime<=0)
//			{	
//				if(sprite.state == Sprite.SPRITE_STATE_MOVE||
//				   sprite.state == Sprite.SPRITE_STATE_ATTACK)
//				{
//					if(ExternalMethods.throwDice(0, 99) == 50)
//					{
//						sprite.byMoveWaitingTime = 25;
//					
//						sprite.changeAction(Sprite.Enemy_action03);					
//					}
//					
//					sprite.attackTime -- ;
//					
//					if(sprite.attackTime==125)
//					{						
//						sprite.changeAction(Sprite.Enemy_action05);
//						
//						sprite.setState(Sprite.SPRITE_STATE_ATTACK);	
//						
//						sirenCallNumber ++;
//					}
//					
//					if(sprite.attackTime<=0)
//					{
//						sprite.attackTime = 375;
//						
////						for(int i=0;i<5;i++)
////						{
////							addEnemy(SpriteLibrary.Enemy_HZ, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
////									 (int)(ExternalMethods.throwDice(200, 350)*GameConfig.f_zoomy), 5, 
////									 gameMain.slingshot.SLINGSHOT_Y- gameMain.spriteLattice.getSpriteLatticeHeight(), 1, ExternalMethods.throwDice(100, 200), 10);
////						}
//						
//						if(!VeggiesData.isMuteMusic())
//						GameMedia.playSound(R.raw.summons, 0);
//						
//						gameMain.gameScriptRun.summonsMonster(5, gameMain);
//						
//						if(sprite.byMoveDirect == 0)//从右向左
//							sprite.changeAction(Sprite.Enemy_action04);
//						else
//							sprite.changeAction(Sprite.Enemy_action30);
//						
//						sprite.setState(Sprite.SPRITE_STATE_MOVE);
//					}
//					
//					if(sprite.byMoveDirect == 0)//从右向左
//					{
//						if(gameMonsterMove.RToLMove(sprite))
//						{
//							if(sprite.state == Sprite.SPRITE_STATE_MOVE)
//								sprite.changeAction(Sprite.Enemy_action30);
//							else if(sprite.state == Sprite.SPRITE_STATE_ATTACK)									
//								sprite.changeAction(Sprite.Enemy_action05);
//							
//							sprite.setXY(0, (int)(GameLibrary.getIntRandom(200, 350)*GameConfig.f_zoomy));	
//							
//							sprite.byMoveDirect = 1;
//						}
//					}
//					else
//					{
//						if(gameMonsterMove.LToRMove(sprite))
//						{														
//							if(sprite.state == Sprite.SPRITE_STATE_MOVE)
//								sprite.changeAction(Sprite.Enemy_action04);
//							else if(sprite.state == Sprite.SPRITE_STATE_ATTACK)									
//								sprite.changeAction(Sprite.Enemy_action05);
//							
//							sprite.setXY(GameConfig.GameScreen_Width, (int)(GameLibrary.getIntRandom(200, 350)*GameConfig.f_zoomy));	
//							
//							sprite.byMoveDirect = 0;
//						}
//					}
//				}
//			}
//			else
//			{
//				sprite.byMoveWaitingTime--;								
//				
//				if(sprite.byMoveWaitingTime==0)
//				{							
//					if(sprite.state == Sprite.SPRITE_STATE_MOVE)
//					{
//						if(sprite.byMoveDirect == 0)//从右向左
//							sprite.changeAction(Sprite.Enemy_action04);
//						else
//							sprite.changeAction(Sprite.Enemy_action30);
//					}
//					else if(sprite.state == Sprite.SPRITE_STATE_ATTACK)
//					{
////						if(sprite.byMoveDirect == 0)//从右向左
//							sprite.changeAction(Sprite.Enemy_action05);
////						else
////							sprite.changeAction(Sprite.Enemy_action31);
//					}
//				}
//			}		
		}
		else if(sprite.kind == CoolEditDefine.Enemy_SHHM)//深海蛤蟆行动方式
		{
			if(sprite.changeSize)
				sprite.size = 1.2f;
			else 
				sprite.size = 1f;
				
			if(sprite.byMoveWaitingTime<=0)
			{
				if(sprite.attackTime>0)
				{
					sprite.attackTime --;
					
					if(sprite.attackTime==0)
					{						
						sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 1);
						
						if(sprite.byMoveDirect==0)
						{
							gameMonsterMove.LToRMove(sprite);
							
							sprite.changeAction(TurnGameSprite.Enemy_action19);
						}
						else
						{														
							gameMonsterMove.RToLMove(sprite);
							
							sprite.changeAction(TurnGameSprite.Enemy_action18);
						}
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
					
						sprite.attackTime = sprite.orgAttackTime;
					}
				}
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
//					if(sprite.state!=Sprite.SPRITE_STATE_MOVE)
//					{
//						sprite.changeAction(Sprite.Enemy_action04);
//						
//						sprite.setState(Sprite.SPRITE_STATE_MOVE);	
//					}
				}
			}					
		}
		else if(sprite.kind == CoolEditDefine.Enemy_SHHT)//深海河豚行动方式
		{
			if(sprite.byMoveWaitingTime<=0)
			{
				if(gameMonsterMove.LongitudinalMove(sprite))
				{
					if(sprite.attackTime>0)
					{
						sprite.attackTime --;
						
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_STAND)
						{
//							sprite.changeAction(Sprite.Enemy_action03);
					
							sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);
						}
					}
					else
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							if(sprite.byMoveDirect==0)
							{
								sprite.changeAction(TurnGameSprite.Enemy_action05);
							}
							else
							{
								sprite.changeAction(TurnGameSprite.Enemy_action17);
							}
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);															
						}
					}
				}
				else 
				{
					if(gameMonsterMove.TransverseMove(sprite)==0)
					{
						if(sprite.getActionName()!=TurnGameSprite.Enemy_action04)
							sprite.changeAction(TurnGameSprite.Enemy_action04);
					}
					else
					{
						if(sprite.getActionName()!=TurnGameSprite.Enemy_action16)
							sprite.changeAction(TurnGameSprite.Enemy_action16);
						
					}
				}
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
					}
				}
			}					
		}
		else if(sprite.kind == CoolEditDefine.Enemy_PANGXIE1||
				sprite.kind == CoolEditDefine.Enemy_PANGXIE2){
			
			if(gameMonsterMove.LongitudinalMove(sprite))
			{
				if(sprite.attackTime>0)
				{
					sprite.attackTime --;
					
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_STAND)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action03);
				
						sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);
					}
				}
				else
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action05);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);
						
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.attacks, 0);
					}
				}
				if(TurnGameMain.turn_guaitime==50){
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_SUMMON)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action06);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_SUMMON);	

						gameMain.gameScriptRun.summonsMonster(2, gameMain);
						
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.npcattacks, 0);
					}
				}
			}else{
				if(sprite.byMoveWaitingTime<=0)
				{
					if(sprite.attackTime>0)
					{
						sprite.attackTime --;
					}
					else if(sprite.attackTime!=-100)
					{
						sprite.attackTime = -100;	
						
						int rmd = ExternalMethods.throwDice(0, 1);
						
						if(rmd==0)//普通攻击
						{
							if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
							{
								sprite.changeAction(TurnGameSprite.Enemy_action06);
								
								sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
														
							    addEnemy(CoolEditDefine.Enemy_PANGXIEZIDAN, (int)sprite.x, (int)(sprite.y+20*GameConfig.f_zoomy), 12, 
							    		 sprite.orgbyMoveStop, 32, 0, 0);      						    
							}
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.npcattacks, 0);
						}
						else if(rmd==1)//召唤
						{
							if(sprite.state!=TurnGameSprite.SPRITE_STATE_SUMMON)
							{
								sprite.changeAction(TurnGameSprite.Enemy_action06);
								
								sprite.setState(TurnGameSprite.SPRITE_STATE_SUMMON);	

								gameMain.gameScriptRun.summonsMonster(2, gameMain);
								
								if(!VeggiesData.isMuteSound())
								GameMedia.playSound(R.raw.npcattacks, 0);
							}
						} 				
					}				
				}
				else
				{
					sprite.byMoveWaitingTime--;
					
					if(sprite.byMoveWaitingTime==0)
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action04);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);												
						}
					}
				}			
			}
		}
		else if(sprite.kind == CoolEditDefine.Enemy_MIMI||
				sprite.kind == CoolEditDefine.Enemy_SHMM||
				sprite.kind == CoolEditDefine.Enemy_YGMM)//咪咪系列行动方式
		{
			if(sprite.byMoveWaitingTime<=0)
			{
				if(gameMonsterMove.LongitudinalMove(sprite))
				{
					if(sprite.attackTime>0)
					{
						sprite.attackTime --;
						
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_STAND)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action03);
					
							sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);
						}
					}
					else
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.attacks, 0);
						}
					}
				}
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
					}
				}
			}					
		}
		else if(sprite.kind == CoolEditDefine.Enemy_DS||
				sprite.kind == CoolEditDefine.Enemy_YGDS)//地鼠系列行动方式
		{
			if(sprite.state==TurnGameSprite.SPRITE_STATE_MOVE)
			{	
				if(!VeggiesData.isMuteSound())
				GameMedia.playSound(R.raw.moves, 0);
				
				if(gameMonsterMove.LongitudinalMove(sprite))
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_GROUND)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action07);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_GROUND);	
						
						sprite.byMoveWaitingTime = ExternalMethods.throwDice(25, 75);
						
						int l = (int)(sprite.y+ExternalMethods.throwDice(100, 200));
						
						sprite.byMoveStop = l>sprite.orgbyMoveStop?sprite.orgbyMoveStop:l;												
					}
				}						
			}
			else
			{		
				if(sprite.y!=sprite.orgbyMoveStop)
				{
					if(sprite.byMoveWaitingTime>0)
					{
						sprite.byMoveWaitingTime--;
						
						if(sprite.byMoveWaitingTime==0)
						{
							if(sprite.state!=TurnGameSprite.SPRITE_STATE_BURROW)
							{
								sprite.changeAction(TurnGameSprite.Enemy_action06);
								
								sprite.setState(TurnGameSprite.SPRITE_STATE_BURROW);
								
								if(!VeggiesData.isMuteSound())
								GameMedia.playSound(R.raw.intogrounds, 0);
							}
						}
					}	
				}
				else 
				{					
					if(sprite.attackTime>0)
					{
						sprite.attackTime --;
						
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_STAND)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action03);
					
							sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);
						}
					}
					else
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.attacks, 0);
							
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);							
						}
					}					
				}		
			}						
		}
		else if(sprite.kind == CoolEditDefine.Enemy_X||
			    sprite.kind == CoolEditDefine.Enemy_SHX||
			    sprite.kind == CoolEditDefine.Enemy_YGX)//熊系列行动方式
		{			
			if(sprite.state==TurnGameSprite.SPRITE_STATE_MOVE)
			{				
				if(gameMonsterMove.LongitudinalMove(sprite))
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
					{
						if(sprite.kind == CoolEditDefine.Enemy_YGX)
						{
//							sprite.changeAction(Sprite.Enemy_action20);
//							
//							sprite.setState(Sprite.SPRITE_STATE_ATTACK);	
							
							if(sprite.y != sprite.orgbyMoveStop)
							{
								int rmd = ExternalMethods.throwDice(2, 4);
								
								while(rmd>0)
								{
									rmd --;
									
									addEnemy(CoolEditDefine.Enemy_YGXTM, ExternalMethods.throwDice(TurnGameSpriteLibrary.GetW(CoolEditDefine.Enemy_YGXTM)/2, GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(CoolEditDefine.Enemy_YGXTM)/2), 
											 sprite.orgbyMoveStop+(int)(20*GameConfig.f_zoomy), 30, 0, 0, 0, 0);
								}
							}
						}
//						else
//						{
//							sprite.changeAction(Sprite.Enemy_action05);
//							
//							sprite.setState(Sprite.SPRITE_STATE_ATTACK);													
//							
//							GameMedia.playSound(R.raw.yx038, 0);
//						}
						
						sprite.changeAction(TurnGameSprite.Enemy_action20);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
						
						sprite.byMoveWaitingTime = /*ExternalMethods.throwDice(75, 150)*/75;
						
						int l = (int)(sprite.y+ExternalMethods.throwDice(100, 200));
						
						sprite.byMoveStop = l>sprite.orgbyMoveStop?sprite.orgbyMoveStop:l;	
						
						if(sprite.kind == CoolEditDefine.Enemy_YGX||
						   sprite.kind == CoolEditDefine.Enemy_SHX)
						{
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.bear2s, 0);
						}
					}
				}						
			}
			else
			{										
				if(sprite.y!=sprite.orgbyMoveStop)
				{
					if(sprite.byMoveWaitingTime>0)
					{
						sprite.byMoveWaitingTime--;
						
						if(sprite.byMoveWaitingTime==0)
						{
							if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
							{
								sprite.changeAction(TurnGameSprite.Enemy_action04);
								
								sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
							}
						}
					}										
				}
				else 
				{					
					if(sprite.attackTime>0)
					{
						sprite.attackTime --;
						
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_STAND)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action03);
					
							sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);
						}
					}
					else
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.attacks, 0);
						}
					}					
				}		
			}					
		}
		else if(sprite.kind == CoolEditDefine.Enemy_Z||
				sprite.kind == CoolEditDefine.Enemy_SHZ||
				sprite.kind == CoolEditDefine.Enemy_YGZ)//猪系列行动方式
		{		
			if(sprite.state==TurnGameSprite.SPRITE_STATE_REVIVAL)
			{
				sprite.revivalTime --;
				
				if(sprite.revivalTime<=0)
				{
					sprite.changeAction(TurnGameSprite.Enemy_action04);
					
					sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);
					
					sprite.life = sprite.lifeMax;
				}
			}
			else if(sprite.state==TurnGameSprite.SPRITE_STATE_MOVE)
			{				
				if(gameMonsterMove.LongitudinalMove(sprite))
				{
					if(sprite.y!=sprite.orgbyMoveStop)
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
							
							sprite.byMoveWaitingTime = 25;												
						}
					}
					else
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_STAND)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action03);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);												
						}
					}
				}						
			}
			else
			{										
				if(sprite.y!=sprite.orgbyMoveStop)
				{
					if(sprite.byMoveWaitingTime>0)
					{
						sprite.byMoveWaitingTime--;
						
						if(sprite.byMoveWaitingTime==0)
						{
							if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
							{
								sprite.changeAction(TurnGameSprite.Enemy_action04);
								
								sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
							}
						}
					}	
				}
				else 
				{					
					if(sprite.attackTime>0)
					{
						sprite.attackTime --;
						
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_STAND)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action03);
					
							sprite.setState(TurnGameSprite.SPRITE_STATE_STAND);
						}
					}
					else
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.attacks, 0);
						}
					}					
				}		
			}					
		}
		else if(sprite.kind == CoolEditDefine.Enemy_HZ||
				sprite.kind == CoolEditDefine.Enemy_YGHZ)//猴子系列行动方式
		{						
			if(sprite.byMoveWaitingTime<=0)
			{											
				if(sprite.attackTime>0)
				{
					sprite.attackTime --;
					
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
				
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);												
					}
					
					gameMonsterMove.rangeMove(sprite, 50, 50, 50, 50);
				}
				else
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action05);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
																		
						if(sprite.kind == CoolEditDefine.Enemy_HZ)
						{
							addEnemy(CoolEditDefine.Enemy_HZXJ, (int)sprite.x, (int)sprite.y, 7, 
						    		 sprite.orgbyMoveStop, 4, 0, 1); 							
						}
						else if(sprite.kind == CoolEditDefine.Enemy_YGHZ)
						{
							if(sprite.attackType == 0)
								sprite.attackType = 1;
							else sprite.attackType = 0;
							
							if(sprite.attackType==0||
							   sprite.attackType==1)
							{
								addEnemy(CoolEditDefine.Enemy_HZXJ, (int)sprite.x, (int)sprite.y, 7, 
							    		 sprite.orgbyMoveStop, 4, 0, 1);
							}
						    
							if(sprite.attackType==0)
							{
							    addEnemy(CoolEditDefine.Enemy_HZXJ, (int)sprite.x, (int)sprite.y, 7, 
							    		 sprite.orgbyMoveStop, 5, 0, 2);
							    
							    addEnemy(CoolEditDefine.Enemy_HZXJ, (int)sprite.x, (int)sprite.y, 7, 
							    		 sprite.orgbyMoveStop, 5, 0, 3);
							}
						}	
						
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.npcattacks, 0);
					}
				}				
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
					}
				}
			}		
		}
		else if(sprite.kind == CoolEditDefine.Enemy_HZXJ)//石器猴子香蕉行动方式
		{			
			if(sprite.state==TurnGameSprite.SPRITE_STATE_ATTACK)
			{
				if((sprite.byMoveWaitingTime==1&&gameMonsterMove.LongitudinalMove(sprite))||
				   (sprite.byMoveWaitingTime==2&&gameMonsterMove.radiationMove(sprite, 240))||
				   (sprite.byMoveWaitingTime==3&&gameMonsterMove.radiationMove(sprite, 300)))
				{
					if(sprite.x>=0&&sprite.x<=GameConfig.GameScreen_Width)
					{
						sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
						
						sprite.life = 0;
						
						TurnGameMain.spriteLattice.spriteLatticeLostBlood(sprite);
					}
				}
			}
		}
		else if(sprite.kind == CoolEditDefine.Enemy_MM)//石器猛犸行动方式
		{						
			if(sprite.byMoveWaitingTime<=0)
			{											
				if(sprite.attackTime>0)
				{
					sprite.attackTime --;
					
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
				
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);												
					}
					
					gameMonsterMove.rangeMove(sprite, 50, 50, 50, 50);
				}
				else if(sprite.attackTime!=-100)
				{
					sprite.attackTime = -100;	
					
					int rmd = ExternalMethods.throwDice(0, 3);
					
					if(rmd==0)//普通攻击
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
													
						    addEnemy(CoolEditDefine.Enemy_MMJS, (int)sprite.x, (int)sprite.y, 12, 
						    		 sprite.orgbyMoveStop, 16, 0, 0);      						    
						}
						
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.npcattacks, 0);
					}
					else if(rmd==1)//特殊攻击
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_VIOLENT)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action08);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_VIOLENT);
							
							addEnemy(CoolEditDefine.Enemy_MMJS, (int)sprite.x, (int)sprite.y, 10, 
						    		 sprite.orgbyMoveStop, 16, 0, 1);    
							
							addEnemy(CoolEditDefine.Enemy_MMJS, (int)sprite.x, (int)sprite.y, 15, 
						    		 sprite.orgbyMoveStop, 16, 0, 2);    
							
							addEnemy(CoolEditDefine.Enemy_MMJS, (int)sprite.x, (int)sprite.y, 20, 
						    		 sprite.orgbyMoveStop, 16, 0, 3);    
							
							addEnemy(CoolEditDefine.Enemy_MMJS, (int)sprite.x, (int)sprite.y, 25, 
						    		 sprite.orgbyMoveStop, 16, 0, 4);   
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.npcattacks, 0);
						}
					} 
					else if(rmd==2)//召唤
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_SUMMON)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action09);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_SUMMON);	
								
//							addEnemy(CoolEditDefine.Enemy_HZ, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//									 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 5, 
//									 sprite.orgbyMoveStop, 1, ExternalMethods.throwDice(100, 200), 10);
//							
//							addEnemy(CoolEditDefine.Enemy_HZ, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//									 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 5, 
//									 sprite.orgbyMoveStop, 1, ExternalMethods.throwDice(100, 200), 10);
							
//							addEnemy(SpriteLibrary.Enemy_HZ, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//									 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 5, 
//									 sprite.orgbyMoveStop, 1, ExternalMethods.throwDice(100, 200), 10);
							
							gameMain.gameScriptRun.summonsMonster(2, gameMain);
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.mammoths, 0);
						}
					} 
					else if(rmd==3)//恢复
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_RESTORE)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action10);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_RESTORE);	
							
							effect.add(CoolEditDefine.Effect_RESTORE, (int)sprite.x, (int)sprite.y, TurnGameSprite.SPRITE_STATE_NORMAL, 0, 1f);
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.hprs, 0);
						}
					} 						
				}				
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);												
					}
				}
			}		
		}
		else if(sprite.kind == CoolEditDefine.Enemy_MMJS||//石器猛犸巨石行动方式
				sprite.kind == CoolEditDefine.Enemy_SHZYXZ)//深海章鱼鞋子行动方式
		{						
			if(sprite.state==TurnGameSprite.SPRITE_STATE_ATTACK)
			{
				if(gameMonsterMove.LongitudinalMove(sprite))
				{
					sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
					
					sprite.life = 0;
					
					TurnGameMain.spriteLattice.spriteLatticeLostBlood(sprite);					
				}
				else 
				{
					if(sprite.byMoveWaitingTime==1)
					{
						sprite.x -= 4;
					}
					else if(sprite.byMoveWaitingTime==2)
					{
						sprite.x += 4;
					}
					else if(sprite.byMoveWaitingTime==3)
					{
						sprite.x -= 8;
					}
					else if(sprite.byMoveWaitingTime==4)
					{
						sprite.x += 8;
					}
				}				
			}		
		}
		else if(sprite.kind == CoolEditDefine.Enemy_MMB1||
				sprite.kind == CoolEditDefine.Enemy_MMB2||
				sprite.kind == CoolEditDefine.Enemy_MMB3||
				sprite.kind == CoolEditDefine.Enemy_MMB4)//小兰怪行动方式
		{			
			if(sprite.byMoveWaitingTime<=0)
			{											
				if(sprite.byMoveDirect == 0)//从右向左
				{
					if(gameMonsterMove.RToLMove(sprite))
					{
						sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
						
						sprite.life = 0;
					}
				}
				else
				{
					if(gameMonsterMove.LToRMove(sprite))
					{
						sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
						
						sprite.life = 0;
					}
				}
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
					sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 1);
					
					if(sprite.byMoveDirect == 0)//从右向左
					{
						sprite.changeAction(TurnGameSprite.Enemy_action11);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
						
						sprite.setXY(GameConfig.GameScreen_Width, sprite.orgbyMoveStop);
					}
					else//从左向右
					{
						sprite.changeAction(TurnGameSprite.Enemy_action13);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
						
						sprite.setXY(0, sprite.orgbyMoveStop);
					}																	
				}
			}		
		}
		else if(sprite.kind == CoolEditDefine.Enemy_CHG)//彩虹怪行动方式
		{			
			if(sprite.byMoveWaitingTime<=0)
			{	
				if(sprite.state == TurnGameSprite.SPRITE_STATE_MOVE)
				{
					if(sprite.byMoveDirect == 0)//从右向左
					{
						if(gameMonsterMove.RToLMove(sprite))
						{
							sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
							
							sprite.life = 0;
						}
					}
					else
					{
						if(gameMonsterMove.LToRMove(sprite))
						{
							sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
							
							sprite.life = 0;
						}
					}
				}
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
					sprite.changeAction(TurnGameSprite.Enemy_action04);
					
					sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);	
					
					sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 1);
					
					if(sprite.byMoveDirect == 0)//从右向左
					{												
						sprite.setXY(GameConfig.GameScreen_Width, sprite.orgbyMoveStop);
					}
					else//从左向右
					{
						sprite.setXY(0, sprite.orgbyMoveStop);										
					}																	
				}
			}		
		}
		else if(sprite.kind == CoolEditDefine.Enemy_YGXTM)//月光熊藤蔓
		{			
		}
		else if(sprite.kind == CoolEditDefine.Enemy_SHZY)//深海章鱼行动方式
		{						
			if(sprite.byMoveWaitingTime<=0)
			{											
				if(sprite.attackTime>0)
				{					
					sprite.attackTime --;
					
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
				
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);												
					}
					
					gameMonsterMove.rangeMove(sprite, 50, 50, 50, 50);
				}
				else if(sprite.attackTime!=-100)
				{					
					sprite.attackTime = -100;
					
					int rmd = ExternalMethods.throwDice(0, 2);
					
					if(rmd==0)//普通攻击
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
								
						    addEnemy(CoolEditDefine.Enemy_SHZYXZ, (int)sprite.x, (int)sprite.y, 5, 
						    		 sprite.orgbyMoveStop, 16, 0, ExternalMethods.throwDice(0, 4));    						    						    
						}
						
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.npcattacks, 0);
					}
					else if(rmd==1)//召唤第一场景怪物
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_SUMMON)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action22);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_SUMMON);	
								
							int num = ExternalMethods.throwDice(2, 3);
							
							gameMain.gameScriptRun.summonsMonster(num, gameMain);
							
//							while(num>0)
//							{
//								num --;
//								
//								int type = ExternalMethods.throwDice(0, 4);
								
//								if(type==0)
//								{
//									addEnemy(CoolEditDefine.Enemy_SHMM, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//											 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 50, 
//											 sprite.orgbyMoveStop, 2, 50, 100);
//								}
//								else if(type==1)
//								{
//									addEnemy(CoolEditDefine.Enemy_SHHT, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//											 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 50, 
//											 sprite.orgbyMoveStop, 1, 50, 100);
//								}
//								else if(type==2)
//								{
//									addEnemy(CoolEditDefine.Enemy_SHHM, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//											 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 50, 
//											 sprite.orgbyMoveStop, 5, 50, 100);
//								}
//								else if(type==3)
//								{
//									addEnemy(CoolEditDefine.Enemy_SHX, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//											 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 50, 
//											 sprite.orgbyMoveStop, 1, 50, 50);
//								}
//								else if(type==4)
//								{
//									addEnemy(CoolEditDefine.Enemy_SHZ, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//											 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 50, 
//											 sprite.orgbyMoveStop, 1, 50, 10);
//								}
//							}
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.bosss, 0);
						}
					} 
					else if(rmd==2)//召唤触手
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_SUMMON)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action22);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_SUMMON);	
								
							int num = ExternalMethods.throwDice(3, 4);
							
							while(num>0)
							{
								num --;
								
								addEnemy(CoolEditDefine.Enemy_SHZYCS, ExternalMethods.throwDice(TurnGameSpriteLibrary.GetW(CoolEditDefine.Enemy_SHZYCS)/2, GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(CoolEditDefine.Enemy_SHZYCS)/2), 
										 sprite.orgbyMoveStop, 30, 0, 0, 50, 0);
							}	
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.bosss, 0);
						}
					}					
				}				
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);												
					}
				}
			}		
		}
		else if(sprite.kind == CoolEditDefine.Enemy_SHZYCS)//深海章鱼触手
		{
			if(sprite.attackTime>0)
			{
				sprite.attackTime --;
				
				if(sprite.attackTime==0)
				{											
					sprite.changeAction(TurnGameSprite.Enemy_action05);
					
					sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);									
				}
			}
		}
		else if(sprite.kind == CoolEditDefine.Enemy_YGY)//月光鹰行动方式
		{					
			centerX = (int)sprite.x;
			centerY = (int)sprite.y;
			
			if(sprite.byMoveWaitingTime<=0)
			{											
				if(sprite.attackTime>0)
				{
					sprite.attackTime --;
					
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
				
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);												
					}
					
					gameMonsterMove.rangeMove(sprite, 50, 50, 50, 50);
				}
				else if(sprite.attackTime!=-100)
				{
					sprite.attackTime = -100;
					
					int rmd = ExternalMethods.throwDice(0, 3);
					
					if(rmd==0)//口水攻击
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_ATTACK)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action05);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_ATTACK);	
								
							addEnemy(CoolEditDefine.Enemy_YGYKS, (int)sprite.x, (int)sprite.y, 14, 
						    		 sprite.orgbyMoveStop, 8, 0, 0); 
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.npcattacks, 0);
						}
					}
					else if(rmd==1)//飓风防御
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_SUMMON && !centerMove)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action24);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_SUMMON);	
							
							addEnemy(CoolEditDefine.Enemy_YGYJF, (int)sprite.x, (int)sprite.y, 12, 
						    		 0, 0, 0, 400);
							
							addEnemy(CoolEditDefine.Enemy_YGYJF, (int)sprite.x, (int)sprite.y, 12, 
						    		 0, 180, 0, 400);
							
							centerMove = true;
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.bosss, 0);
						}
					} 
					else if(rmd==2)//鬼魅尖叫
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_SUMMON)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action25);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_SUMMON);
							
							int num = 5;
							
							while(num>0)
							{
								addEnemy(CoolEditDefine.Enemy_YGYYM, (int)sprite.x, (int)sprite.y, 20, 
							    		 sprite.orgbyMoveStop, 8, 0, num);
								
								num --;
							}
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.npcattacks, 0);
						}
					} 
					else if(rmd==3)//召唤
					{
						if(sprite.state!=TurnGameSprite.SPRITE_STATE_SUMMON)
						{
							sprite.changeAction(TurnGameSprite.Enemy_action26);
							
							sprite.setState(TurnGameSprite.SPRITE_STATE_SUMMON);	
														
							gameMain.gameScriptRun.summonsMonster(2, gameMain);
							
//							addEnemy(CoolEditDefine.Enemy_YGDS, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//									 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 50,
//									 sprite.orgbyMoveStop, 2, 50, ExternalMethods.throwDice(25, 75));
//							
//							addEnemy(CoolEditDefine.Enemy_YGDS, ExternalMethods.throwDice(50, GameConfig.GameScreen_Width-50), 
//									 ExternalMethods.throwDice(200, sprite.orgbyMoveStop-100), 50,
//									 sprite.orgbyMoveStop, 2, 50, ExternalMethods.throwDice(25, 75));
				
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.bosss, 0);
						}
					} 						
				}				
			}
			else
			{
				sprite.byMoveWaitingTime--;
				
				if(sprite.byMoveWaitingTime==0)
				{
					if(sprite.state!=TurnGameSprite.SPRITE_STATE_MOVE)
					{
						sprite.changeAction(TurnGameSprite.Enemy_action04);
						
						sprite.setState(TurnGameSprite.SPRITE_STATE_MOVE);												
					}
					
					centerMove = false;
				}
			}		
		}
		else if(sprite.kind == CoolEditDefine.Enemy_YGYYM)//月光鹰羽毛行动方式
		{			
			if(sprite.state==TurnGameSprite.SPRITE_STATE_ATTACK)
			{
				if((sprite.byMoveWaitingTime==1&&gameMonsterMove.LongitudinalMove(sprite))||
				   (sprite.byMoveWaitingTime==2&&gameMonsterMove.radiationMove(sprite, 255))||
				   (sprite.byMoveWaitingTime==3&&gameMonsterMove.radiationMove(sprite, 240))||
				   (sprite.byMoveWaitingTime==4&&gameMonsterMove.radiationMove(sprite, 285))||
				   (sprite.byMoveWaitingTime==5&&gameMonsterMove.radiationMove(sprite, 300)))
				{
					if(sprite.x>=0&&sprite.x<=GameConfig.GameScreen_Width)
					{
						sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
						
						sprite.life = 0;
						
						TurnGameMain.spriteLattice.spriteLatticeLostBlood(sprite);
					}
				}
			}
		}
		else if(sprite.kind == CoolEditDefine.Enemy_YGYKS||
				sprite.kind == CoolEditDefine.Enemy_PANGXIEZIDAN)//月影鹰口水行动方式
		{			
			if(sprite.state==TurnGameSprite.SPRITE_STATE_ATTACK)
			{
				if(gameMonsterMove.LongitudinalMove(sprite))
				{
					sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
						
					sprite.life = 0;
						
					TurnGameMain.spriteLattice.spriteLatticeLostBlood(sprite);
				}
			}
		}
		else if(sprite.kind == CoolEditDefine.Enemy_YGYJF)//月影鹰飓风行动方式
		{								
			if(sprite.byMoveWaitingTime>0)
			{
				gameMonsterMove.loopMove(sprite, centerX, centerY);
				
				sprite.byMoveWaitingTime --;
				
				if(!centerMove)
				sprite.byMoveWaitingTime = 0;
			}
			else
			{
				sprite.setState(TurnGameSprite.SPRITE_STATE_NONE);
						
				sprite.life = 0;
				
				centerMove = false;
			}
		}
	}
	
	public void collision(TurnGameMain gameMain, TurnGameSprite bullet)	
	{
		int i = 0;
		
		for(;i<Enemy.size();i++)
		{
			if(ExternalMethods.RectCollision(bullet.getCollisionRect(), Enemy.get(i).getCollisionRect())&&
					bullet.isTouchSprite(Enemy.get(i).idNum)&&!Enemy.get(i).magicWaterProtect)
			{
				bullet.setTouchSpriteId(Enemy.get(i).idNum);

				if(Enemy.get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
				   Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
				   Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
				   Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
				   Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYYM&&
				   Enemy.get(i).kind!=CoolEditDefine.Enemy_PANGXIEZIDAN)
				{
					if(Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_NORMAL||
					   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_STAND||				   
					   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_ATTACK||
					   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_SUMMON||
					   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_REVIVAL||
					   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_INJURE||
					   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_GROUND||
					   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_VIOLENT||
					   Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_RESTORE||
					   (Enemy.get(i).kind!=CoolEditDefine.Enemy_DS&&
					    Enemy.get(i).kind!=CoolEditDefine.Enemy_YGDS&&
					    Enemy.get(i).state == TurnGameSprite.SPRITE_STATE_MOVE)||
					    (Enemy.get(i).dizzinessTime>0&&Enemy.get(i).life>0))
					{
						if(Enemy.get(i).shieldTime<=0)	
						{
							if(!bullet.isTouch)
							{
								if(Enemy.get(i).kind==CoolEditDefine.Enemy_SHHM)
								{
									if(Enemy.get(i).size>1||
									   bullet.getActionName()==5||
									   bullet.getActionName()==9||
									   bullet.getActionName()==11)
									{
										Enemy.get(i).life -= TurnGameSpriteLibrary.GetAttack(bullet.kind);
										
										Enemy.get(i).changeSize = false;
									}
								}
								else	
								{
									Enemy.get(i).life -= TurnGameSpriteLibrary.GetAttack(bullet.kind);
									
									if(Enemy.get(i).kind == CoolEditDefine.Enemy_MM)
									{
										if(!VeggiesData.isMuteSound())
										GameMedia.playSound(R.raw.bosshurts, 0);
									}
									else
									{
										int rmd = ExternalMethods.throwDice(0, 1);
										
										if(rmd==0)
										{
											if(!VeggiesData.isMuteSound())
											GameMedia.playSound(R.raw.npchurts, 0);
										}
										else
										{
											if(!VeggiesData.isMuteSound())
											GameMedia.playSound(R.raw.npchurt1s, 0);
										}
									}
								}
							}
						}
						else
						{
							bullet.isMove = false;
							
							bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
							
							break;
						}
			
						if(Enemy.get(i).kind == CoolEditDefine.Enemy_CHG)
						{							
							Enemy.get(i).changeAction(TurnGameSprite.Enemy_action01);
							
							Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_INJURE);	
							
							Enemy.get(i).isTouch = true;	
							
//							int rmd = ExternalMethods.throwDice(0, 1);
//							
//							if(rmd==0)
//							{
//								if(!VeggiesData.isMuteSound())
//								GameMedia.playSound(R.raw.npchurts, 0);
//							}
//							else
//							{
//								if(!VeggiesData.isMuteSound())
//								GameMedia.playSound(R.raw.npchurt1s, 0);
//							}
						}
						else if(Enemy.get(i).kind == CoolEditDefine.Enemy_MMB1||
							   Enemy.get(i).kind == CoolEditDefine.Enemy_MMB2||
							   Enemy.get(i).kind == CoolEditDefine.Enemy_MMB3||
							   Enemy.get(i).kind == CoolEditDefine.Enemy_MMB4)
						{
							bullet.isMove = false;
							
							bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
							
							if(Enemy.get(i).life<=0)
							{								
								Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);	
								
//								int rmd = ExternalMethods.throwDice(0, 1);
//																						
//								if(rmd==0)
//								{			
									int num = 0;
									
									if(Enemy.get(i).kind == CoolEditDefine.Enemy_MMB1)
										num = ExternalMethods.throwDice(5, 10);
									else if(Enemy.get(i).kind == CoolEditDefine.Enemy_MMB2)
										num = ExternalMethods.throwDice(2, 8);
									else if(Enemy.get(i).kind == CoolEditDefine.Enemy_MMB3)
										num = ExternalMethods.throwDice(1, 5);
									else if(Enemy.get(i).kind == CoolEditDefine.Enemy_MMB4)
										num = ExternalMethods.throwDice(1, 1);
									
									gameMain.goldenNumber +=  num;
									
									gameMain.gameUI.setGoldNumber(gameMain.goldenNumber);
									
									gameMain.getGoldenNumber += num;
									
									effect.add(TurnGameEffect.SMALLGOLDEN, (int)Enemy.get(i).x, (int)Enemy.get(i).y-TurnGameSpriteLibrary.GetH(Enemy.get(i).kind)/2, num);																																			
//								}
//								else
//									effect.add(Effect.BOX, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind)/2, 0);
								
//								gameMain.gameMission.lastEndPlayId = Enemy.get(i).kind;
//								
//								gameMain.gameMission.addMonsterDeadTime(gameMain);
								
								if(!VeggiesData.isMuteSound())
								GameMedia.playSound(R.raw.npcdies, 0);
							}				
							else
							{
								if(Enemy.get(i).byMoveDirect==0)
								{
									Enemy.get(i).changeAction(TurnGameSprite.Enemy_action12);
									
									Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_INJURE);
								}
								else
								{
									Enemy.get(i).changeAction(TurnGameSprite.Enemy_action14);
									
									Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_INJURE);
								}		
								
//								int rmd = ExternalMethods.throwDice(0, 1);
//								
//								if(rmd==0)
//								{
//									if(!VeggiesData.isMuteSound())
//									GameMedia.playSound(R.raw.npchurts, 0);
//								}
//								else
//								{
//									if(!VeggiesData.isMuteSound())
//									GameMedia.playSound(R.raw.npchurt1s, 0);
//								}
							}
							
							effect.add(TurnGameEffect.FLOWER, (int)Enemy.get(i).x, (int)Enemy.get(i).y-TurnGameSpriteLibrary.GetH(Enemy.get(i).kind)/2, 0);
						}
						else if(Enemy.get(i).kind==CoolEditDefine.Enemy_SHHM&&
								Enemy.get(i).size==1&&								
								Enemy.get(i).dizzinessTime<=0&&
								bullet.getActionName()!=5&&
								bullet.getActionName()!=9&&
								bullet.getActionName()!=11
								/*&&!gameMain.gameCold.getState()*/)
						{							
							bullet.isMove = false;
							
							bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
								
							Enemy.get(i).changeSize = true;
							
							Enemy.get(i).changeAction(TurnGameSprite.Enemy_action05);
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.eats, 0);
							
							break;
						}
						else if(Enemy.get(i).kind==CoolEditDefine.Enemy_YGYJF)
						{
							bullet.isMove = false;
							
							bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
							
							Enemy.get(i).life = Enemy.get(i).lifeMax;
							
							Enemy.get(i).changeAction(TurnGameSprite.Enemy_action03);
							
							Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_STAND);
							
							break;
						}
						else 
						{
							if(Enemy.get(i).shieldTime<=0)	
							{
//								if(!Enemy.get(i).freezeState)
//								{
									if(Enemy.get(i).life<=0)
									{
										if(Enemy.get(i).freezeState)
										{
											effect.closeEffect(CoolEditDefine.Effect_ICESTATELV3, Enemy.get(i).freezeLinkNumber);
											
											Enemy.get(i).freezeState = false;
										}
										
										if(Enemy.get(i).kind==CoolEditDefine.Enemy_MM||
										   Enemy.get(i).kind==CoolEditDefine.Enemy_SHZY||
										   Enemy.get(i).kind==CoolEditDefine.Enemy_YGY)
										VeggiesData.addAchievementNum(Achievement.DEFEAT_ONCE_BOSS, 1);
										
										if(Enemy.get(i).kind==CoolEditDefine.Enemy_YGZ)
										{											
											if(Enemy.get(i).state==TurnGameSprite.SPRITE_STATE_REVIVAL)
											{												
												Enemy.get(i).changeAction(TurnGameSprite.Enemy_action02);
												
												Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_DEAD);	
												
												gameMain.addGoldenAnimation(Enemy.get(i));
												
//												gameMain.gameNumber += SpriteLibrary.GetNumber(Enemy.get(i).kind);
//												
//												if(gameMain.doubleGameNumberTime>0)
//												gameMain.gameNumber += SpriteLibrary.GetNumber(Enemy.get(i).kind);
												
												gameMain.gameMission.lastEndPlayId = Enemy.get(i).kind;
												
												gameMain.gameMission.addMonsterDeadTime(gameMain);
												
												if(!VeggiesData.isMuteSound())
												GameMedia.playSound(R.raw.npcdies, 0);
											}
											else
											{												
												Enemy.get(i).changeAction(TurnGameSprite.Enemy_action21);
											
												Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_REVIVAL);
												
												Enemy.get(i).life = 10;
												
												Enemy.get(i).revivalTime = 50;
												
												Enemy.get(i).dizzinessTime = 0;
												
												Enemy.get(i).setSlowDown(false);
												
												Enemy.get(i).speedDownTime = 0;
												
//												effect.closeEffect(CoolEditDefine.Effect_DIZZINESS, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind));
												
												effect.closeEffect(CoolEditDefine.Effect_DIZZINESS, Enemy.get(i).dizzinessLinkNumber);
											}
										}
										else if(Enemy.get(i).kind == CoolEditDefine.Enemy_MEGICWATER)//魔法水滴，释放有保护的怪物
										{											
											for(int j=0;j<getEnemyList().size();j++)
											{		
												if(getEnemyList().get(j).kind != CoolEditDefine.Enemy_MEGICWATER)
												{
													if(getEnemyList().get(j).linkNumber==Enemy.get(i).linkNumber)
													{					
														getEnemyList().get(j).linkNumber = 0;
														
														getEnemyList().get(j).magicWaterProtect = false;
														
														break;						
													}
												}
											}
											
											Enemy.get(i).changeAction(TurnGameSprite.Enemy_action02);
											
											Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_DEAD);	
											
											gameMain.addGoldenAnimation(Enemy.get(i));
											
//											gameMain.gameNumber += SpriteLibrary.GetNumber(Enemy.get(i).kind);
//											
//											if(gameMain.doubleGameNumberTime>0)
//											gameMain.gameNumber += SpriteLibrary.GetNumber(Enemy.get(i).kind);
											
//											effect.closeEffect(CoolEditDefine.Effect_DIZZINESS, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind));
											
											effect.closeEffect(CoolEditDefine.Effect_DIZZINESS, Enemy.get(i).dizzinessLinkNumber);
											
											if(!VeggiesData.isMuteSound())
											GameMedia.playSound(R.raw.water2s, 0);
											
//											gameMain.gameMission.lastEndPlayId = Enemy.get(i).kind;
//											
//											gameMain.gameMission.addMonsterDeadTime(gameMain);
										}										
										else
										{													
											Enemy.get(i).dizzinessTime = 0;
											
											Enemy.get(i).setSlowDown(false);
											
											Enemy.get(i).speedDownTime = 0;
											
											if(bullet.special==TurnGameSprite.PLAYER_SPECIAL_3)//弹飞
											{
												if(Enemy.get(i).kind!=CoolEditDefine.Enemy_MM&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZY&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYJF&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYYM)
												{
													if(!Enemy.get(i).isFly)
													{
														Enemy.get(i).isFly = true;
														Enemy.get(i).isFlyMoveDegree = ExternalMethods.throwDice(30, 150);
														Enemy.get(i).isFlyMoveSpeed  = 40;	
														
														if(!VeggiesData.isMuteSound())
														GameMedia.playSound(R.raw.npcflys, 0);
													}
												}
											}
											else
											{
												if(Enemy.get(i).kind==CoolEditDefine.Enemy_PANGXIE1){
//													addEnemy(CoolEditDefine.Enemy_PANGXIE2, (int)Enemy.get(i).x, (int)Enemy.get(i).y, 1, 
//															gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 2, 500, 1);
													Enemy.get(i).changeAction(TurnGameSprite.Enemy_action07);
													TurnGameMain.pangxie2=9;
													TurnGameMain.pangxie2X=(int)Enemy.get(i).x;
													TurnGameMain.pangxie2Y=(int)Enemy.get(i).y;
												}else{
													Enemy.get(i).changeAction(TurnGameSprite.Enemy_action02);
												}
												Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_DEAD);
												
											}
											
											gameMain.addGoldenAnimation(Enemy.get(i));
											
//											gameMain.gameNumber += SpriteLibrary.GetNumber(Enemy.get(i).kind);
//											
//											if(gameMain.doubleGameNumberTime>0)
//											gameMain.gameNumber += SpriteLibrary.GetNumber(Enemy.get(i).kind);
											
//											effect.closeEffect(CoolEditDefine.Effect_DIZZINESS, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind));
											
											effect.closeEffect(CoolEditDefine.Effect_DIZZINESS, Enemy.get(i).dizzinessLinkNumber);
											
											gameMain.gameMission.lastEndPlayId = Enemy.get(i).kind;
											
											gameMain.gameMission.addMonsterDeadTime(gameMain);
										}
									}				
									else
									{																						
										if(Enemy.get(i).dizzinessTime<=0)
										{
											if(Enemy.get(i).kind == CoolEditDefine.Enemy_SHHT)
											{
												if(Enemy.get(i).byMoveDirect==0)
												{
													Enemy.get(i).changeAction(TurnGameSprite.Enemy_action01);																								
												}
												else
												{
													Enemy.get(i).changeAction(TurnGameSprite.Enemy_action15);																								
												}
												
												Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_INJURE);
											}
											else if(Enemy.get(i).kind == CoolEditDefine.Enemy_SIREN)
											{
//												if(Enemy.get(i).byMoveDirect==0)
//												{
													Enemy.get(i).changeAction(TurnGameSprite.Enemy_action01);																								
//												}
//												else
//												{
//													Enemy.get(i).changeAction(Sprite.Enemy_action28);																								
//												}
												
												Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_INJURE);
											}
											else if(Enemy.get(i).kind == CoolEditDefine.Enemy_MEGICWATER)
											{
												
											}
											else
											{
												Enemy.get(i).changeAction(TurnGameSprite.Enemy_action01);
												
												Enemy.get(i).setState(TurnGameSprite.SPRITE_STATE_INJURE);											
											}
										}
										
//										if(Enemy.get(i).kind == CoolEditDefine.Enemy_MM)
//										{
//											if(!VeggiesData.isMuteSound())
//											GameMedia.playSound(R.raw.bosshurts, 0);
//										}
//										else
//										{
//											int rmd = ExternalMethods.throwDice(0, 1);
//											
//											if(rmd==0)
//											{
//												if(!VeggiesData.isMuteSound())
//												GameMedia.playSound(R.raw.npchurts, 0);
//											}
//											else
//											{
//												if(!VeggiesData.isMuteSound())
//												GameMedia.playSound(R.raw.npchurt1s, 0);
//											}
//										}
										
										if(bullet.special==TurnGameSprite.PLAYER_SPECIAL_2)//眩晕
										{
											if(Enemy.get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MEGICWATER&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MM&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZY&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYJF&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYYM)
											{
												Enemy.get(i).dizzinessTime = 1;
												
												if(bullet.kind==CoolEditDefine.Player_YC)
													Enemy.get(i).dizzinessTime = 1;
												else if(bullet.kind==CoolEditDefine.Player_YC_2)
													Enemy.get(i).dizzinessTime = 2;
												else if(bullet.kind==CoolEditDefine.Player_YC_3)
													Enemy.get(i).dizzinessTime = 3;
												
												Enemy.get(i).speedDownTime = 0;
												
												Enemy.get(i).setSlowDown(false);																						
												
												if(Enemy.get(i).life>0)
												{
													effect.add(Enemy.get(i), CoolEditDefine.Effect_DIZZINESS, (int)Enemy.get(i).x, (int)Enemy.get(i).y-TurnGameSpriteLibrary.GetH(Enemy.get(i).kind), TurnGameSprite.SPRITE_STATE_NORMAL, Enemy.get(i).dizzinessTime);
													
													if(!VeggiesData.isMuteSound())
													GameMedia.playSound(R.raw.vertigos, 0);
												}
												
												addAbnormalStateNumber();
											}
										}
										else if(bullet.special==TurnGameSprite.PLAYER_SPECIAL_4)//点燃
										{
											if(Enemy.get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MEGICWATER&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MM&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZY&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYJF&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYYM)
											{
												Enemy.get(i).lostBloodTime = 75;
												
												Enemy.get(i).lostBloodTimeOffset = 25;
												
												Enemy.get(i).lostBloodAmount = 10;
												
												Enemy.get(i).isHeadFire = true;
												
												addAbnormalStateNumber();
											}
										}
										else if(bullet.special==TurnGameSprite.PLAYER_SPECIAL_1)//减速
										{	
											if(Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZY&&
											   Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
											   Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
											   Enemy.get(i).kind!=CoolEditDefine.Enemy_MM&&
											   Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
											   Enemy.get(i).kind!=CoolEditDefine.Enemy_MEGICWATER)
											{
												if(Enemy.get(i).dizzinessTime<=0)
												{	
													if(Enemy.get(i).speedDownTime==0)
													{
														if(bullet.kind==CoolEditDefine.Player_FQ_2)
															Enemy.get(i).speedDownTime = 50;		
														else if(bullet.kind==CoolEditDefine.Player_FQ_3)
															Enemy.get(i).speedDownTime = 100;
														else if(bullet.kind==CoolEditDefine.Player_TD_2)
															Enemy.get(i).speedDownTime = 75;
														
														addAbnormalStateNumber();
													}
												}
											}
										}
									}	
//								}
								
								effect.add(CoolEditDefine.Effect_ATTACK2, (int)Enemy.get(i).x, (int)Enemy.get(i).y-TurnGameSpriteLibrary.GetH(Enemy.get(i).kind)/2, TurnGameSprite.SPRITE_STATE_NORMAL, 50, 1f);																		
							
								addDamageNumberAnimation((int)Enemy.get(i).x, (int)Enemy.get(i).y, TurnGameSpriteLibrary.GetAttack(bullet.kind));														

								if(bullet.getActionName()==7)
								{
//									if(bullet.isCombo)		
//									{
//										gameMain.combo.setCombo(gameMain, (int)Enemy.get(i).x, (int)Enemy.get(i).y);
//										
//										bullet.isCombo = false;
//									}										
								}
								else if(bullet.getActionName()==5||
										bullet.getActionName()==9||
										bullet.getActionName()==11)//燃烧状态
								{
//									if(gameMain.gameUI.getComboType()==GameUI.COMBO_TYPE_1)
//									{
										bullet.isMove = false;
										
										bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
										
										if(Enemy.get(i).state==TurnGameSprite.SPRITE_STATE_DEAD||
										   Enemy.get(i).state==TurnGameSprite.SPRITE_STATE_NONE)
										{
//											gameMain.gameMission9Process((byte)Enemy.get(i).kind);
											
											gameMain.gameMission22Process();
										}
										
										break;
//									}
								}
								else if(bullet.getActionName()==3)
								{
									if(!bullet.isTouch)
									{										
										bullet.isTouch = true;
										
//										if(bullet.killedMonsterNumberIndex==0&&bullet.isCombo)												
//										gameMain.combo.setCombo(gameMain, (int)Enemy.get(i).x, (int)Enemy.get(i).y);																				
																						
										if(bullet.kind==CoolEditDefine.Player_YC||
										   bullet.kind==CoolEditDefine.Player_YC_2||
										   bullet.kind==CoolEditDefine.Player_YC_3)
										{
											if(bullet.kind==CoolEditDefine.Player_YC)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*2,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*2,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*2, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*2);														
											}
											else if(bullet.kind==CoolEditDefine.Player_YC_2)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*3,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*3,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*3, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*3);														
											}
											else if(bullet.kind==CoolEditDefine.Player_YC_3)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*5,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*5,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*5, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*5);														
											}
											
											bullet.special = TurnGameSprite.PLAYER_SPECIAL_2;
											
											if(Enemy.get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MEGICWATER&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MM&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZY&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYJF&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYYM)
											{
												Enemy.get(i).dizzinessTime = 1;
												
												if(bullet.kind==CoolEditDefine.Player_YC)
													Enemy.get(i).dizzinessTime = 1;
												else if(bullet.kind==CoolEditDefine.Player_YC_2)
													Enemy.get(i).dizzinessTime = 2;
												else if(bullet.kind==CoolEditDefine.Player_YC_3)
													Enemy.get(i).dizzinessTime = 3;
												
												Enemy.get(i).speedDownTime = 0;
												
												Enemy.get(i).setSlowDown(false);
												
												if(Enemy.get(i).life>0)
												{
													effect.add(Enemy.get(i), CoolEditDefine.Effect_DIZZINESS, (int)Enemy.get(i).x, (int)Enemy.get(i).y-TurnGameSpriteLibrary.GetH(Enemy.get(i).kind), TurnGameSprite.SPRITE_STATE_NORMAL, Enemy.get(i).dizzinessTime);
													
													if(!VeggiesData.isMuteSound())
													GameMedia.playSound(R.raw.vertigos, 0);
												}
												
												addAbnormalStateNumber();
											}
											
											i = -1;
										}
										else if(bullet.kind==CoolEditDefine.Player_LJ||
										   bullet.kind==CoolEditDefine.Player_LJ_2||
										   bullet.kind==CoolEditDefine.Player_LJ_3)
										{
											if(bullet.kind==CoolEditDefine.Player_LJ)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*2,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*2,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*2, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*2);														
											}
											else if(bullet.kind==CoolEditDefine.Player_LJ_2)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*3,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*3,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*3, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*3);														
											}
											else if(bullet.kind==CoolEditDefine.Player_LJ_3)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*4,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*4,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*4, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*4);														
											}
											
											bullet.special = TurnGameSprite.PLAYER_SPECIAL_4;
											
											if(Enemy.get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MEGICWATER&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYJF&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
												Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYYM)
											{
												Enemy.get(i).lostBloodTime = 75;
												
												Enemy.get(i).lostBloodTimeOffset = 25;
												
												Enemy.get(i).lostBloodAmount = 10;
												
												Enemy.get(i).isHeadFire = true;
												
												addAbnormalStateNumber();
											}
											
											i = -1;																					
										}
										else if(bullet.kind==CoolEditDefine.Player_NG||
										   bullet.kind==CoolEditDefine.Player_NG_2||
										   bullet.kind==CoolEditDefine.Player_NG_3)
										{
											if(bullet.kind==CoolEditDefine.Player_NG)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*2,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*2,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*2, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*2);														
											}
											else if(bullet.kind==CoolEditDefine.Player_NG_2)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*3,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*3,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*3, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*3);														
											}
											else if(bullet.kind==CoolEditDefine.Player_NG_3)
											{
												bullet.setCollisionRect(
														bullet.x-TurnGameSpriteLibrary.GetW(bullet.kind)/2*4,
														bullet.y-TurnGameSpriteLibrary.GetH(bullet.kind)/2*4,
														bullet.x+TurnGameSpriteLibrary.GetW(bullet.kind)/2*4, 
														bullet.y+TurnGameSpriteLibrary.GetH(bullet.kind)/2*4);														
											}
											
											if(bullet.state!=TurnGameSprite.SPRITE_STATE_NONE)
											{
												i = -1;
												
												bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
											}
										}
									}
								}
							}
						}
						
//						effect.add(SpriteLibrary.Effect_ATTACK, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind)/2, Sprite.SPRITE_STATE_NORMAL, 50);
						
//						effect.add(SpriteLibrary.Effect_ICEATTACK, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind)/2, Sprite.SPRITE_STATE_NORMAL, 50);												
						
//						effect.add(SpriteLibrary.Effect_SUMMON, (int)Enemy.get(i).x, (int)Enemy.get(i).y, Sprite.SPRITE_STATE_NORMAL, 50);												
						
//						effect.add(SpriteLibrary.Effect_BOMB, (int)Enemy.get(i).x, (int)Enemy.get(i).y, Sprite.SPRITE_STATE_NORMAL, 50);												
						
//						effect.add(SpriteLibrary.Effect_SHIELD, (int)Enemy.get(i).x, (int)Enemy.get(i).y, Sprite.SPRITE_STATE_NORMAL, 50);												
						
//						effect.add(SpriteLibrary.Effect_SLOWDOWN, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind)/2, Sprite.SPRITE_STATE_NORMAL, 50);												
						
//						effect.add(SpriteLibrary.Effect_RESTORE, (int)Enemy.get(i).x, (int)Enemy.get(i).y, Sprite.SPRITE_STATE_NORMAL, 0);																		
						
//						effect.add(SpriteLibrary.Effect_EMPTYBOX, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind)/2, Sprite.SPRITE_STATE_NORMAL, 0);
						
//						effect.add(Effect.FLOWER, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind)/2);
						
//						addDamageNumberAnimation((int)Enemy.get(i).x, (int)Enemy.get(i).y, SpriteLibrary.GetAttack(spriteBullet.getSpriteBullet(spriteBulletId).kind));
						
//						if(gameMain.spriteBullet.special(bullet))
						if(bullet.kind!=CoolEditDefine.Player_YC&&
						   bullet.kind!=CoolEditDefine.Player_YC_2&&
						   bullet.kind!=CoolEditDefine.Player_YC_3&&
						   bullet.kind!=CoolEditDefine.Player_LJ&&
						   bullet.kind!=CoolEditDefine.Player_LJ_2&&
						   bullet.kind!=CoolEditDefine.Player_LJ_3)
						{
							if(bullet.isTouch)
							{								
								if(bullet.kind==CoolEditDefine.Player_LB||
								   bullet.kind==CoolEditDefine.Player_LB_2||
								   bullet.kind==CoolEditDefine.Player_LB_3)
								{
									if(Enemy.get(i).state==TurnGameSprite.SPRITE_STATE_DEAD||
									   Enemy.get(i).state==TurnGameSprite.SPRITE_STATE_NONE)
									{
//										gameMain.gameMission6Process((byte)Enemy.get(i).kind);
									}
									
									bullet.killedMonsterNumberIndex ++;
									
									if(bullet.killedMonsterNumberIndex>=bullet.killedMonsterMaxNumber)
									{
										bullet.isMove = false;
										
										bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
																														
										break;
									}
									else
									{
										bullet.isTouch = false;
									}
									
									if(bullet.kind==CoolEditDefine.Player_LB_2||
									   bullet.kind==CoolEditDefine.Player_LB_3)
									{
										if(bullet.kind==CoolEditDefine.Player_LB_2)
											Enemy.get(i).lostBloodTime = 50;
										else if(bullet.kind==CoolEditDefine.Player_LB_3)
											Enemy.get(i).lostBloodTime = 75;
										
										Enemy.get(i).lostBloodTimeOffset = 25;
										
										Enemy.get(i).lostBloodAmount = 10;
									}
								}	
								else if(bullet.kind==CoolEditDefine.Player_ZS||
										bullet.kind==CoolEditDefine.Player_ZS_2||
										bullet.kind==CoolEditDefine.Player_ZS_3)
								{
									if(Enemy.get(i).state==TurnGameSprite.SPRITE_STATE_DEAD||
									   Enemy.get(i).state==TurnGameSprite.SPRITE_STATE_NONE)
									{
//										gameMain.gameMission6Process((byte)Enemy.get(i).kind);
									}
									
									bullet.killedMonsterNumberIndex ++;
									
									if(bullet.killedMonsterNumberIndex>=bullet.killedMonsterMaxNumber)
									{
										bullet.isMove = false;
										
										bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
																														
										break;
									}
									else
									{
										bullet.isTouch = false;
									}
									
									if(Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZY&&
									   Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
									   Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
									   Enemy.get(i).kind!=CoolEditDefine.Enemy_MM&&
									   Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
									   Enemy.get(i).kind!=CoolEditDefine.Enemy_MEGICWATER)
									{										
										if(Enemy.get(i).dizzinessTime<=0)
										{	
											if(Enemy.get(i).speedDownTime==0)
											{
												if(Enemy.get(i).life>0)
												{
													if(bullet.kind==CoolEditDefine.Player_ZS)
														Enemy.get(i).speedDownTime = 25;
													else if(bullet.kind==CoolEditDefine.Player_ZS_2)
														Enemy.get(i).speedDownTime = 50;
													else if(bullet.kind==CoolEditDefine.Player_ZS_3)
														Enemy.get(i).speedDownTime = 75;	
													
													addAbnormalStateNumber();
												}
											}
										}
									}
									
//									if(Enemy.get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_MEGICWATER&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_MM&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_MMJS&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZY&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYJF&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
//										Enemy.get(i).kind!=CoolEditDefine.Enemy_YGYYM)
//									{
//										if(bullet.kind==CoolEditDefine.Player_ZS)
//											Enemy.get(i).dizzinessTime = 25;
//										else if(bullet.kind==CoolEditDefine.Player_ZS_2)
//											Enemy.get(i).dizzinessTime = 50;
//										else if(bullet.kind==CoolEditDefine.Player_ZS_3)
//											Enemy.get(i).dizzinessTime = 75;
//										
//										Enemy.get(i).speedDownTime = 0;
//										
//										Enemy.get(i).setSlowDown(false);
//										
//										if(Enemy.get(i).life>0)
//										{
//											effect.add(CoolEditDefine.Effect_DIZZINESS, (int)Enemy.get(i).x, (int)Enemy.get(i).y-SpriteLibrary.GetH(Enemy.get(i).kind), Sprite.SPRITE_STATE_NORMAL, Enemy.get(i).dizzinessTime);
//											
//											GameMedia.playSound(R.raw.vertigos, 0);
//										}
//										
//										gameMain.gameMonster.addAbnormalStateNumber();
//									}
								}	
//								else if(bullet.kind==CoolEditDefine.Player_LJ||
//										bullet.kind==CoolEditDefine.Player_LJ_2||
//										bullet.kind==CoolEditDefine.Player_LJ_3)
//								{							
//									Enemy.get(i).lostBloodTime = 75;
//									
//									Enemy.get(i).lostBloodTimeOffset = 25;
//									
//									Enemy.get(i).lostBloodAmount = 10;
//									
//									Enemy.get(i).isHeadFire = true;
//								}	
								else if(bullet.kind==CoolEditDefine.Player_NG||
										bullet.kind==CoolEditDefine.Player_NG_2||
										bullet.kind==CoolEditDefine.Player_NG_3)
								{							
									bullet.isTouch = false;
									
									bullet.isCombo = false;
								}	
								else if(bullet.kind==CoolEditDefine.Player_WD||
										bullet.kind==CoolEditDefine.Player_WD_2||
										bullet.kind==CoolEditDefine.Player_WD_3)
								{																
									bullet.isCombo = false;
									
									bullet.isMove = false;
									
									bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
									
									break;
								}	
								else 
								{									
									bullet.isMove = false;
									
									bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);
									
									break;
								}
							}
						}
					}
				}
			}
		}
		
		if(bullet.kind==CoolEditDefine.Player_YC||
			bullet.kind==CoolEditDefine.Player_YC_2||
			bullet.kind==CoolEditDefine.Player_YC_3||
			bullet.kind==CoolEditDefine.Player_LJ||
			bullet.kind==CoolEditDefine.Player_LJ_2||
			bullet.kind==CoolEditDefine.Player_LJ_3||
			bullet.kind==CoolEditDefine.Player_NG||
			bullet.kind==CoolEditDefine.Player_NG_2||
			bullet.kind==CoolEditDefine.Player_NG_3)
		{
			if(bullet.isTouch)
			{
				bullet.isMove = false;
				
				bullet.setState(TurnGameSprite.SPRITE_STATE_NONE);				
			}
		}
		
		if(bullet.getActionName()==7)
		{
//			if(bullet.isCombo)
//			{				
//				gameMain.combo.comboNumberClaer();
//				
//				bullet.isCombo = false;
//			}
		}
	}
	
	private void addDamageNumberAnimation(int x, int y, int num)
	{
		DamageNumberAnimation dna = new DamageNumberAnimation();
		
		dna.setDamageNumberAnimation(x, y, num);
		
		damageNumberAnimation.add(dna);
	}
	
	private class DamageNumberAnimation
	{	
		private boolean damageNumberState;
		
		private int damageNumberMove;		
		private int damageNumber_x;
		private int damageNumber_y;
		
		private int damageNumber;

		public void setDamageNumberAnimation(int x, int y, int num)
		{
			damageNumber_x = x;
			
			damageNumber_y = y;
			
			damageNumberState = true;
			
			damageNumberMove = 0;
			
			damageNumber = num;
		}
		
		public boolean getDamageNumberState()
		{
			return damageNumberState;
		}
		
		public void drawDamageNumberAnimation(Canvas canvas)
		{			
			if(damageNumberState)
			{
				Paint paint=new Paint();
					
				damageNumberMove +=2;
				
				if(damageNumberMove<30)	
				{
					paint.setAlpha(255-(damageNumberMove*9));
					
					int n = 1;
					
					int t = damageNumber;
					
					while(t/10>0)
					{
						n ++;
						
						t/=10;
					}
					
					if(damageNumber != 0)
					GameLibrary.DrawNumber(canvas, wordNum, damageNumber_x, damageNumber_y-16*GameConfig.f_zoom-damageNumberMove, wordNumChars, damageNumber+"", paint, GameLibrary.VH, 0);					
				}
				else
				{
					damageNumberState = false;
				}
			}				
		}		
	}		
	
	private void spriteFlyMove(TurnGameSprite enemy)
	{	
		if(enemy.isFly)
		{
			enemy.jiaodu += 30;
											
			enemy.x += (int)ExternalMethods.getAngleX(enemy.isFlyMoveDegree, enemy.isFlyMoveSpeed);
			enemy.y += (int)ExternalMethods.getAngleY(enemy.isFlyMoveDegree, enemy.isFlyMoveSpeed);				
						
			//反弹
			if(enemy.x-TurnGameSpriteLibrary.GetW(enemy.kind)/2<0)
			{
				enemy.isFlyMoveDegree = 90 - (enemy.isFlyMoveDegree-90);
				
				enemy.jiaodu = -enemy.jiaodu;							
			}
			else if(enemy.x+TurnGameSpriteLibrary.GetW(enemy.kind)/2>GameConfig.GameScreen_Width)
			{
				enemy.isFlyMoveDegree = 90 + (90-enemy.isFlyMoveDegree);
				
				enemy.jiaodu = -enemy.jiaodu;								
			}	
						
			if(enemy.y<0||enemy.x<0||enemy.x>GameConfig.GameScreen_Width)
			{
				enemy.life = 0;
				
				enemy.isFly = false;
				
				enemy.isOver = true;	
				
				enemy.setState(TurnGameSprite.SPRITE_STATE_NONE); 
			}
		}
	}
	
	public TurnGameEffect getEffect()
	{
		return effect;
	}
	
	public void addAbnormalStateNumber()
	{
		abnormalStateNumber ++;
	}
	
	public int getAbnormalStateNumber()
	{
		return abnormalStateNumber;
	}
	
	public int getSirenCallNumber()
	{
		return sirenCallNumber;
	}		
}
