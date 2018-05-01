package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.socoGameEngine.GameConfig;
import com.socogame.coolEdit.CoolEditDefine;

public class GameBanana {

	private ArrayList<Sprite> banana;
	
	private int linkNum;
	
	public GameBanana()
	{
		banana = new ArrayList<Sprite>();
		
		linkNum = 0;
	}
	
	public void loadImage()
	{
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_BANANA);
	}
	
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_BANANA);
	}
	
	public ArrayList<Sprite> getBananaList()
	{
		return banana;
	}
	
	public void setBanana(int x, int y)
	{
		Sprite _banana = new Sprite();
		
		_banana.initSprite(CoolEditDefine.Effect_BANANA, x, y, Sprite.SPRITE_STATE_NORMAL);		
		_banana.changeAction(2);
		
		_banana.byMoveDirect = (byte)ExternalMethods.throwDice(0, 1);//0£º×ó£¬1£ºÓÒ
		
		banana.add(_banana);
	}
	
	private void bananaMove(GameMain gameMain, Sprite _banana)
	{
		for(int i=0;i<gameMain.gameMonster.getEnemyList().size();i++)
		{		
			if(gameMain.gameMonster.getEnemyList().get(i).glideLinkNumber == _banana.glideLinkNumber)
			{
				_banana.y += 16;
				
				if(_banana.byMoveDirect == 0)
				{
					_banana.x -= 2;
					
					if(_banana.x<=SpriteLibrary.GetW(_banana.kind)/2)
					{
						_banana.byMoveDirect = 1;
					}
				}
				else if(_banana.byMoveDirect == 1)
				{
					_banana.x += 2;
					
					if(_banana.x>=GameConfig.GameScreen_Width-SpriteLibrary.GetW(_banana.kind)/2)
					{
						_banana.byMoveDirect = 0;
					}
				}
				
				if(!gameMain.gameMonster.getEnemyList().get(i).isGlide)
				{
					_banana.setState(Sprite.SPRITE_STATE_NONE);
					
					gameMain.gameMonster.getEnemyList().get(i).glideLinkNumber = 0;	
					
					if(gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHX||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_X||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGX||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHZ||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_Z||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGZ)
					{
//						gameMain.gameMonster.getEnemyList().get(i).byMoveStop = gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop;
						
						int l = (int)(gameMain.gameMonster.getEnemyList().get(i).y+ExternalMethods.throwDice(100, 200));
						
						gameMain.gameMonster.getEnemyList().get(i).byMoveStop = l>gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop?gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop:l;	
					}
				}
				else 
				if(gameMain.gameMonster.getEnemyList().get(i).freezeState||
				   gameMain.gameMonster.getEnemyList().get(i).dizzinessTime>0)
				{
					_banana.setState(Sprite.SPRITE_STATE_NONE);
					
					gameMain.gameMonster.getEnemyList().get(i).isGlide = false;
					
					gameMain.gameMonster.getEnemyList().get(i).glideLinkNumber = 0;	
					
					gameMain.gameMonster.getEnemyList().get(i).changeAction(Sprite.Enemy_action04);
					
					if(gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHX||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_X||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGX||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHZ||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_Z||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGZ)
					{
//						gameMain.gameMonster.getEnemyList().get(i).byMoveStop = gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop;
						
						int l = (int)(gameMain.gameMonster.getEnemyList().get(i).y+ExternalMethods.throwDice(100, 200));
						
						gameMain.gameMonster.getEnemyList().get(i).byMoveStop = l>gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop?gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop:l;	
					}
				}
				else
				if(gameMain.gameMonster.getEnemyList().get(i).isFly||
				   gameMain.gameMonster.getEnemyList().get(i).life<=0)
				{
					_banana.setState(Sprite.SPRITE_STATE_NONE);
					
					gameMain.gameMonster.getEnemyList().get(i).isGlide = false;
					
					gameMain.gameMonster.getEnemyList().get(i).glideLinkNumber = 0;	
				}
				else
				if(_banana.y >= gameMain.slingshot.SLINGSHOT_Y - SpriteLibrary.GetH(gameMain.spriteLattice.getSpriteLattice().kind))
				{
					_banana.setState(Sprite.SPRITE_STATE_NONE);
															
					gameMain.gameMonster.getEnemyList().get(i).isGlide = false;
					
					gameMain.gameMonster.getEnemyList().get(i).glideLinkNumber = 0;
					
					gameMain.gameMonster.getEnemyList().get(i).changeAction(Sprite.Enemy_action04);
					
					gameMain.gameMonster.getEffect().add(Effect.FLOWER, (int)_banana.x, (int)_banana.y, 0);
					
					_banana.y -= SpriteLibrary.GetH(gameMain.gameMonster.getEnemyList().get(i).kind)*2;
					
					if(gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHX||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_X||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGX||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHZ||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_Z||
					   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGZ)
					{
//						gameMain.gameMonster.getEnemyList().get(i).byMoveStop = gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop;
						
						int l = (int)(gameMain.gameMonster.getEnemyList().get(i).y+ExternalMethods.throwDice(100, 200));
						
						gameMain.gameMonster.getEnemyList().get(i).byMoveStop = l>gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop?gameMain.gameMonster.getEnemyList().get(i).orgbyMoveStop:l;	
					}
				}								
				
				gameMain.gameMonster.getEnemyList().get(i).setXY((int)_banana.x, (int)_banana.y);
				
				break;
			}
		}
	}
	
	public void paint(Canvas canvas)
	{
		for(int i=0;i<banana.size();i++)
		{
			if(!banana.get(i).isGlide)
			{
				if(banana.get(i).getActionName()==2)
				banana.get(i).paintSpriteShadow(canvas, -SpriteLibrary.GetH(banana.get(i).kind)/2, 1f);
				
				banana.get(i).paintSprite(canvas, 0, 0);
			}
		}
	}
	
	public void updata(GameMain gameMain)
	{
		for(int i=0;i<banana.size();i++)
		{			
			if(banana.get(i).state == Sprite.SPRITE_STATE_NONE)
			{
				banana.remove(i);
			}
			else
			{			
				banana.get(i).updataSprite();
				
//				bulletCollision(gameMain, banana.get(i));
				
				if(banana.get(i).isGlide)
					bananaMove(gameMain, banana.get(i));
				else
					monsterCollision(gameMain, banana.get(i));		
			}
		}
	}
	
	public void bulletCollision(GameMain gameMain, Sprite bullet)
	{
		for(int i=0;i<banana.size();i++)
		{
			if(banana.get(i).getActionName() != 1)
			{
				if(ExternalMethods.RectCollision(bullet.getCollisionRect(), banana.get(i).getCollisionRect()))				
				{
					bullet.isMove = false;
					
					bullet.setState(Sprite.SPRITE_STATE_NONE);
					
					banana.get(i).changeAction(1);
					
	//				banana.get(i).setState(Sprite.SPRITE_STATE_NONE);
	//				
					gameMain.gameMonster.getEffect().add(CoolEditDefine.Effect_ATTACK2, (int)banana.get(i).x, (int)banana.get(i).y-SpriteLibrary.GetH(banana.get(i).kind)/2, Sprite.SPRITE_STATE_NORMAL, 50, 1f);													
					
					break;
				}
			}
		}
	}
	
	private void addLinkNum()
	{
		linkNum ++;
	}
	
	private int getLinkNum()
	{
		return linkNum;
	}
	
	private void monsterCollision(GameMain gameMain, Sprite _banana)
	{
		for(int i=0;i<gameMain.gameMonster.getEnemyList().size();i++)
		{		
			if(!gameMain.gameMonster.getEnemyList().get(i).isFly&&
			   !gameMain.gameMonster.getEnemyList().get(i).isGlide)
			{
				if(gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHMM||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_MIMI||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGMM||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHX||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_X||
				   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGX||
				   (gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHZ&&gameMain.gameMonster.getEnemyList().get(i).actionName!=Sprite.Enemy_action05)||
				   (gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_Z&&gameMain.gameMonster.getEnemyList().get(i).actionName!=Sprite.Enemy_action05)||
				   (gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGZ&&gameMain.gameMonster.getEnemyList().get(i).actionName!=Sprite.Enemy_action05))
				{											
					if(ExternalMethods.RectCollision(_banana.getCollisionRect(), gameMain.gameMonster.getEnemyList().get(i).getCollisionRect()))
					{
						addLinkNum();
						
						_banana.glideLinkNumber = getLinkNum();
						
						_banana.isGlide = true;
						
						gameMain.gameMonster.getEnemyList().get(i).glideLinkNumber = getLinkNum();
						
						gameMain.gameMonster.getEnemyList().get(i).isGlide = true;
													
						gameMain.gameMonster.getEnemyList().get(i).setXY((int)_banana.x, (int)_banana.y);
												
						if(gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_SHX||
						   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_X||
						   gameMain.gameMonster.getEnemyList().get(i).kind == CoolEditDefine.Enemy_YGX)
							gameMain.gameMonster.getEnemyList().get(i).changeAction(7);
						else
							gameMain.gameMonster.getEnemyList().get(i).changeAction(6);
						
						break;
					}						
				}
			}
		}
	}
}
