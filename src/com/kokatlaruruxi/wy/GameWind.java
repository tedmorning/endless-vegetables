package com.kokatlaruruxi.wy;
//package com.endlessvegetables2.android;
//
//import java.util.ArrayList;
//
//import android.graphics.Canvas;
//
//import com.socoGameEngine.GameConfig;
//
//public class GameWind {
//	private Sprite wind;
//	
//	private ArrayList<Sprite> enemyInWind;
//	
//	private boolean windCome;	
//	
//	public GameWind()
//	{
//		windCome = false;					
//	}
//	
//	public void setWind()
//	{		
//		windCome = true;	
//		
//		initWindSprite();	
//	}
//	
//	public void initWindSprite()
//	{
//		wind = new Sprite();
//		wind.initSprite(SpriteLibrary.Effect_WIND, (int)(GameConfig.GameScreen_Width+113*GameConfig.f_zoom), GameConfig.GameScreen_Height/2, Sprite.SPRITE_STATE_NORMAL);
//		wind.changeAction(0);
//		
//		enemyInWind = new ArrayList<Sprite>();
//		
//		wind.size = 2.5f;
//		
//		wind.Alpha = 180;				
//	}
//	
//	public void addInWindEnemy(Sprite enemy)
//	{
//		Sprite sprite = new Sprite();
//		sprite.initSprite(enemy.kind, (int)enemy.x, (int)enemy.y, Sprite.SPRITE_STATE_NORMAL);
//		sprite.changeAction(enemy.getActionName());
//		
//		sprite.size = enemy.size;
//		
//		sprite.isUpState = enemy.isUpState;
//		
//		enemyInWind.add(sprite);
//	}
//	
//	public void windMove()
//	{		
//		if(windCome)
//		{							
//			wind.updataSprite();
//			
//			wind.x -= 10;
//			
//			if(wind.x<-113*GameConfig.f_zoom)
//			{
//				wind.x = (int)(GameConfig.GameScreen_Width+113*GameConfig.f_zoom);
//				
//				enemyInWind.clear();
//				
//				windCome = false;								
//			}
//			
//			for(int i=0;i<enemyInWind.size();i++)
//			{
//				enemyInWind.get(i).updataSprite();
//				
//				enemyInWind.get(i).x -= 10;
//				
//				if(enemyInWind.get(i).x>=wind.x-50*GameConfig.f_zoom&&enemyInWind.get(i).isUpAndLRState)
//				{				
//					enemyInWind.get(i).x -= 10;
//					
//					if(enemyInWind.get(i).x<wind.x-50*GameConfig.f_zoom)
//					{
//						enemyInWind.get(i).isUpAndLRState = false;
//					}
//				}
//				else if(enemyInWind.get(i).x<=wind.x+50*GameConfig.f_zoom&&!enemyInWind.get(i).isUpAndLRState)
//				{				
//					enemyInWind.get(i).x += 10;
//					
//					if(enemyInWind.get(i).x>wind.x+50*GameConfig.f_zoom)
//					{
//						enemyInWind.get(i).isUpAndLRState = true;
//					}
//				}
//				
//				enemyInWind.get(i).y -= 5;
//				
//				enemyInWind.get(i).jiaodu -= 20;
//				
//				if(enemyInWind.get(i).y<wind.y - 300*GameConfig.f_zoom)
//				{
//					enemyInWind.get(i).y = wind.y - 300*GameConfig.f_zoom;
//				}
//			}
//		}
//	}
//	
//	public void paint(Canvas canvas)
//	{
//		if(windCome)
//		{					
//			for(int i=0;i<enemyInWind.size();i++)
//			{
//				if(enemyInWind.get(i).isUpAndLRState)			
//				enemyInWind.get(i).paintSprite(canvas, 0, 0);		
//			}
//			
//			wind.paintSpriteShadow(canvas, 0, 1);
//			
//			wind.paintSprite(canvas, 0, 0);
//			
//			for(int i=0;i<enemyInWind.size();i++)
//			{
//				if(!enemyInWind.get(i).isUpAndLRState)			
//				enemyInWind.get(i).paintSprite(canvas, 0, 0);		
//			}
//		}	
//	}	
//	
//	public boolean getWindCome()
//	{
//		return windCome;
//	}
//	
////	public void windSuction(Sprite sprite)
////	{		
////		if(!windCome)
////			return;
////		
////		if(ExternalMethods.RectCollision(sprite.getCollisionRect(), wind.getCollisionRect()))
////		{
////			sprite.isUpState = true;	
////			
////			if(sprite.x < wind.x)
////			{
////				sprite.x += 20;
////					
////				if(sprite.x>=wind.x)
////				{
////					sprite.x = wind.x;
////				}
////			}
////			else if(sprite.x > wind.x)
////			{
////				sprite.x -= 20;
////					
////				if(sprite.x<=wind.x)
////				{
////					sprite.x = wind.x;
////				}
////			}		
////				
////			if(sprite.y < wind.y)
////			{
////				sprite.y += 20;
////					
////				if(sprite.y>=wind.y)
////				{
////					sprite.y = wind.y;
////				}
////			}
////			else if(sprite.y > wind.y)
////			{
////				sprite.y -= 20;
////					
////				if(sprite.y<=wind.y)
////				{
////					sprite.y = wind.y;
////				}
////			}
////				
////			if(sprite.x == wind.x&&sprite.y == wind.y)
////			{																
//////				sprite.isOver = true;
//////					
//////				sprite.isFly = true;
//////				
////				sprite.life = 0;
////					
////				sprite.setState(Sprite.SPRITE_STATE_NONE);
////					
////				addInWindEnemy(sprite);
////			}
////		}
////	}
//	
//	public void windSuction(GameMonster gameMonster)
//	{		
//		if(!windCome)
//			return;
//		
//		for(int i=0;i<gameMonster.getEnemyList().size();i++)
//		{
//			Sprite sprite = gameMonster.getEnemyList().get(i);
//			
//			if(sprite.kind!=SpriteLibrary.Enemy_YGY&&
//			   sprite.kind!=SpriteLibrary.Enemy_YGYJF&&
//			   sprite.kind!=SpriteLibrary.Enemy_MM&&
//			   sprite.kind!=SpriteLibrary.Enemy_MMJS&&
//			   sprite.kind!=SpriteLibrary.Enemy_SHZY&&
//			   sprite.kind!=SpriteLibrary.Enemy_SHZYCS&&
//			   sprite.kind!=SpriteLibrary.Enemy_YGXTM)
//			if(ExternalMethods.RectCollision(sprite.getCollisionRect(), wind.getCollisionRect()))
//			{
//				sprite.isUpState = true;	
//				
//				if(sprite.x < wind.x)
//				{
//					sprite.x += 20;
//						
//					if(sprite.x>=wind.x)
//					{
//						sprite.x = wind.x;
//					}
//				}
//				else if(sprite.x > wind.x)
//				{
//					sprite.x -= 20;
//						
//					if(sprite.x<=wind.x)
//					{
//						sprite.x = wind.x;
//					}
//				}		
//					
//				if(sprite.y < wind.y)
//				{
//					sprite.y += 20;
//						
//					if(sprite.y>=wind.y)
//					{
//						sprite.y = wind.y;
//					}
//				}
//				else if(sprite.y > wind.y)
//				{
//					sprite.y -= 20;
//						
//					if(sprite.y<=wind.y)
//					{
//						sprite.y = wind.y;
//					}
//				}
//					
//				if(sprite.x == wind.x&&sprite.y == wind.y)
//				{																
////					sprite.isOver = true;
////						
////					sprite.isFly = true;
////					
//					sprite.life = 0;
//						
//					sprite.setState(Sprite.SPRITE_STATE_NONE);
//					
////					if(sprite.freezeState)
////					{
////						for(int j=0;j<gameMonster.effect.getList().size();j++)
////						{
////							if(gameMonster.effect.getList().get(j).kind==SpriteLibrary.Effect_ICESTATE)
////							{
////								if(sprite.x==gameMonster.effect.getList().get(j).x&&
////								   sprite.y-SpriteLibrary.GetH(sprite.kind)/2==gameMonster.effect.getList().get(j).y)
////								{
////									gameMonster.effect.getList().get(j).effectShowTime = 1;
////									
////									break;
////								}
////							}
////						}
////					}
//					
//					addInWindEnemy(sprite);
//				}
//			}
//		}		
//	}
//}
