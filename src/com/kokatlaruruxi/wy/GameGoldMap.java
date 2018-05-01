package com.kokatlaruruxi.wy;
//package com.endlessvegetables2.android;
//
//import com.socoGameEngine.GameConfig;
//import com.socoGameEngine.GameImage;
//import com.socoGameEngine.GameLibrary;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//
//public class GameGoldMap{
//	
////	private GameMonster gameMonster; 
////	
////	private int playTime;
////	
////	private int kind[] = {SpriteLibrary.Enemy_MMB1,
////						   SpriteLibrary.Enemy_MMB2,
////						   SpriteLibrary.Enemy_MMB3,
////						   SpriteLibrary.Enemy_MMB4};
////	
////	private int height[] = {(int)(200*GameConfig.f_zoomy), 
////							(int)(300*GameConfig.f_zoomy),  
////							(int)(400*GameConfig.f_zoomy),  
////							(int)(500*GameConfig.f_zoomy)};
////	
//////	private Bitmap numScore[];
//////
//////	private char wordNumChars[];
////	
////	private Sprite effectNumber[];
////	
////	private float size;
////	
////	private byte effectNumberId;
////		
////	private Matrix matrix;
////	
////	public GameGoldMap()
////	{
////		matrix = new Matrix();
////		
//////		wordNumChars = new char[]{'0','1','2','3','4','5','6','7','8','9',':'};
//////		
//////		numScore = GameImage.getAutoSizecutBitmap("ui/num_1", 11, 1, GameImage.Sort_line);
////		
////		effectNumber = new Sprite[3];
////		
////		effectNumber[0] = new Sprite(GameImage.getImage("newEffect/effect_number3"));
////		effectNumber[1] = new Sprite(GameImage.getImage("newEffect/effect_number2"));		
////		effectNumber[2] = new Sprite(GameImage.getImage("newEffect/effect_number1"));
////	}
////	
////	public void initGoldMap()
////	{		
////		gameMonster = new GameMonster();
////		
////		gameMonster.init();
////		
////		gameMonster.loadImage();
////				
////		for(int i=0;i<kind.length;i++)
////		{
////			gameMonster.addEnemy(kind[i], 0, 0, 0, 
////								 height[i], 20-4*i, 0, ExternalMethods.throwDice(0, 10));
////		}
////		
////		playTime = 250;
////		
////		size = 1;
////		
////		effectNumberId = -1;
////	}
////	
////	private void monsterMove()
////	{
////		if(gameMonster.getEnemyList().size()==0)
////		{
////			for(int i=0;i<kind.length;i++)
////			{
////				gameMonster.addEnemy(kind[i], 0, 0, 0, 
////									 height[i], 20-4*i, 0, ExternalMethods.throwDice(0, 10));
////			}
////		}
////		else if(gameMonster.getEnemyList().size()<kind.length)
////		{			
////			for(int i=0;i<kind.length;i++)
////			{
////				boolean result = true;
////				
////				for(int j=0;j<gameMonster.getEnemyList().size();j++)
////				{
////					if(kind[i]==gameMonster.getEnemyList().get(j).kind)
////					{
////						result = false;
////						
////						break;
////					}
////				}
////				
////				if(result)
////				{
////					gameMonster.addEnemy(kind[i], 0, 0, 0, 
////							 height[i], 20-4*i, 0, ExternalMethods.throwDice(0, 10));
////					
////					break;
////				}
////			}			
////		}
////	}
////	
////	public void goldMapUpdata(GameMain gameMain)
////	{					
////		playTime --;
////		
////		if(playTime<=0)
////			playTime = 0;
////		else
////		{
////			int num = playTime/25;
////			
////			if(playTime%25>0)
////			{
////				num ++;
////			}
////			
////			if(num==3)
////			{
////				if(effectNumberId==-1)
////				{
////					effectNumberId = 0;
////					
////					size = 2;
////				}
////				
////				size -= 0.1;	
////				
////				if(size<=0)
////					size = 0;
////			}
////			else if(num==2)
////			{
////				if(effectNumberId==0)
////				{
////					effectNumberId = 1;
////					
////					size = 2;
////				}
////				
////				size -= 0.1;	
////				
////				if(size<=0)
////					size = 0;
////			}
////			else if(num==1)
////			{
////				if(effectNumberId==1)
////				{
////					effectNumberId = 2;
////					
////					size = 2;
////				}
////				
////				size -= 0.1;
////				
////				if(size<=0)
////					size = 0;
////			}
////			
//////			if(playTime<=30)
//////			{
//////				size -= 0.1;
//////				
//////				if(size<=0)
//////				{
//////					if(effectNumberId<3)
//////					{
//////						effectNumberId ++;
//////						
//////						size = 1;
//////					}					
//////				}
//////			}
////		}
////						
////		if(gameMonster==null)
////			return;
////		
////		monsterMove();
////		
////		gameMonster.updata(gameMain);
////	}
////	
////	public boolean isEnd()
////	{
////		if(playTime==0)
////			return true;
////		else
////			return false;
////	}
////	
////	public void drawGoldMap(Canvas canvas)
////	{
////		if(gameMonster==null)
////			return;
////		
////		Paint paint = new Paint();
////		
//////		int num = playTime/25;
//////		
//////		if(playTime%25>0)
//////		{
//////			num ++;
//////		}
//////		
//////		GameLibrary.DrawNumber(canvas, numScore, GameConfig.GameScreen_Width-10*GameConfig.f_zoom, 14*GameConfig.f_zoom, wordNumChars, (num>9?"00:":"00:0")+num, paint, GameLibrary.TR, 0);			
//////		
////		gameMonster.paint(canvas);
////		
////		if(effectNumberId!=-1)
////		{
////			matrix.reset();
////			
////			matrix.postScale(size, size, effectNumber[effectNumberId].bitmap.getWidth()/2, effectNumber[effectNumberId].bitmap.getHeight()/2);
////			
////			matrix.postTranslate(GameConfig.GameScreen_Width/2-effectNumber[effectNumberId].bitmap.getWidth()/2, GameConfig.GameScreen_Height/2-effectNumber[effectNumberId].bitmap.getHeight()/2);
////			
////			paint.setAlpha((int)(size*127));
////			
////			effectNumber[effectNumberId].drawBitmap(canvas, effectNumber[effectNumberId].bitmap, matrix, paint);
////		}
////	}
////
////	public void collisionGoldenMap(GameMain gameMain)
////	{
////		gameMain.slingshot.getIndicator().setSnipeState(false);
////		
////		for(int i=0;i<gameMain.spriteBullet.getSpriteBulletList().size();i++)
////		{
////			if(gameMain.spriteBullet.getSpriteBulletList().get(i).isMove)
////			gameMonster.Collision(gameMain, gameMain.spriteBullet.getSpriteBulletList().get(i));
////		}
////	}
////	
////	public void clear()
////	{		
////	}
//}
