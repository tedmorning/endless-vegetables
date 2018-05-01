package com.kokatlaruruxi.wy;

import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class GameCenterEffect {
	
	public static final byte WHITE_EFFECT = 0;//白屏效果
	public static final byte COMBO_EFFECT = 1;//暴击效果
	public static final byte SMALLGAME_EFFECT = 2;//进入小游戏效果
	
	public static final byte CARD1_EFFECT = 3;//金币加倍
	public static final byte CARD2_EFFECT = 4;//上膛加速
	public static final byte CARD3_EFFECT = 5;//冰冻
	public static final byte CARD4_EFFECT = 6;//龙卷
	public static final byte CARD5_EFFECT = 7;//暴击
	
	public static final byte AIRSHIP_EFFECT = 8;//召唤飞艇的效果
	
	private byte kind;
	
	private int x;
	
	private int y;
	
	private int showTime;
	
	private int move;
	
	private int move1;
	
	private float size;
	
	private boolean state;
	
	private Sprite combo[];	
	
	private Sprite effectMimi[];
	
	private Sprite effectAirship[];
	
	private Sprite card[];
	
	private Sprite effectRainbow[];
		
	private Matrix matrix;
	
//	private int fastReloadTime;
	
	private int doubleGoldenTime;
	
	public GameCenterEffect()
	{
		matrix = new Matrix();
		
//		fastReloadTime = 0;
		
		doubleGoldenTime = 0;
		
		size = 0;
		
		x = GameConfig.GameScreen_Width/2;
		
		y = GameConfig.GameScreen_Height/2;
		
		combo = new Sprite[2];
		
		combo[0] = new Sprite(GameImage.getImage("newEffect/effect_combo1"));
		combo[1] = new Sprite(GameImage.getImage("newEffect/effect_combo0"));
		
		effectMimi = new Sprite[2];
		
		effectMimi[0] = new Sprite(GameImage.getImage("newEffect/effect_mimi1"));
		effectMimi[1] = new Sprite(GameImage.getImage("newEffect/effect_mimi0"));
		
		effectAirship = new Sprite[2];
		
		effectAirship[0] = new Sprite(GameImage.getImage("newEffect/effect_airship1"));
		effectAirship[1] = new Sprite(GameImage.getImage("newEffect/effect_airship0"));
		
		card = new Sprite[5];
		
		for(int i=0;i<card.length;i++)
		{
			card[i] = new Sprite(GameImage.getImage("ui/card"+(6+i)));
		}
		
		effectRainbow = new Sprite[2];
		
		effectRainbow[0] = new Sprite(GameImage.getImage("newEffect/effect_rainbow1"));
		effectRainbow[1] = new Sprite(GameImage.getImage("newEffect/effect_rainbow0"));
		
//		effectNumber = new Bitmap[3];
//		
//		effectNumber[0] = GameImage.getImage("newEffect/effect_number3");
//		effectNumber[1] = GameImage.getImage("newEffect/effect_number2");		
//		effectNumber[2] = GameImage.getImage("newEffect/effect_number1");
		
		state = false;
	}
	
	public byte getKind()
	{
		return kind;
	}
	
	public void delImage()
	{		
		for(int i=0;i<combo.length;i++)
		{
			GameImage.delImage(combo[i].bitmap);
			
			combo[i].recycleBitmap();
		}
		
		for(int i=0;i<effectMimi.length;i++)
		{
			GameImage.delImage(effectMimi[i].bitmap);
			
			effectMimi[i].recycleBitmap();
		}
		
		for(int i=0;i<effectAirship.length;i++)
		{
			GameImage.delImage(effectAirship[i].bitmap);
			
			effectAirship[i].recycleBitmap();
		}
		
		for(int i=0;i<card.length;i++)
		{
			GameImage.delImage(card[i].bitmap);
			
			card[i].recycleBitmap();
		}
		
		for(int i=0;i<effectRainbow.length;i++)
		{
			GameImage.delImage(effectRainbow[i].bitmap);
			
			effectRainbow[i].recycleBitmap();
		}
	}
	
//	public void setXY(int x, int y)
//	{
//		this.x = x;
//		
//		this.y = y;
//	}
	
	public void show(GameMain gameMain, byte kind)
	{			
		this.kind = kind;
		
//		gameMain.slingshot.recover();
		
		showTime = 0;
		
		state = true;
		
		size = 0;
		
		if(kind == COMBO_EFFECT)
		{
			move = -combo[0].bitmap.getWidth();
			
			move1 = -combo[1].bitmap.getWidth();
			
			x = 0;
			
			y = (int)(120*GameConfig.f_zoom);	
		}				
		else if(kind == SMALLGAME_EFFECT )
		{
			move = 0;
			
			x = GameConfig.GameScreen_Width/2;
			
			y = GameConfig.GameScreen_Height/2;		
		}
		else if(kind == AIRSHIP_EFFECT)
		{
			move = -effectAirship[1].bitmap.getWidth();
			
			x = GameConfig.GameScreen_Width/2;
			
			y = GameConfig.GameScreen_Height/2;	
		}
		else if(kind == WHITE_EFFECT)
		{			
			x = GameConfig.GameScreen_Width/2;
			
			y = GameConfig.GameScreen_Height/2;	
		}
		else
		{			
			move = -effectRainbow[1].bitmap.getWidth();
			
			x = GameConfig.GameScreen_Width/2;
			
			y = GameConfig.GameScreen_Height/2;			
		}		
	}
	
	public boolean getState()
	{
		return state;
	}
	
	public void updata(GameMain gameMain)
	{
//		if(fastReloadTime>0)
//		{
//			fastReloadTime --;
//			
//			if(fastReloadTime==0)
//			{
//				gameMain.reloadTime = 40;
//				
//				gameMain.waitingSpriteBullet.setReloadTime(gameMain.reloadTime);
//			}
//		}
		
		if(doubleGoldenTime>0)
		{
			doubleGoldenTime --;
		}
		
		if(state)
		{
			showTime ++;
			
			if(kind == COMBO_EFFECT)
			{					
				if(showTime==1)
					gameMain.combo.start(gameMain);
				
				if(showTime<14&&showTime>=4)
				{
					move += 70;
					
					if(move>=0)
					{
						move = 0;
					}
				}
				else if(showTime>=14)
				{
					move1 += 40;
					
					if(move1>=0)
					{
						move1 = 0;
					}
				}
				
				if(showTime>=50)
				{
					state = false;
					
//					gameMain.combo.start(gameMain);
				}
			}
			else if(kind == WHITE_EFFECT)
			{
				if(showTime>=7)
				{
					state = false;										
				}
			}
			else if(kind == SMALLGAME_EFFECT)
			{
				if(showTime>=14)
				{
					move += 30;
					
					if(move>=GameConfig.GameScreen_Width)
					{
						move = GameConfig.GameScreen_Width;
						
						state = false;	
						
//						gameMain.enterGoldenMap();
					}
				}				
			}
			else if(kind == AIRSHIP_EFFECT)
			{
				if(showTime>=14)
				{
					move += 40;
					
					if(move>=GameConfig.GameScreen_Width/4-effectAirship[1].bitmap.getWidth()/2)
					{
						move = GameConfig.GameScreen_Width/4-effectAirship[1].bitmap.getWidth()/2;												
					}
				}		
				
				if(showTime>=30)
				{
					state = false;	
					
					gameMain.gameAirship.setStart();
				}
			}
			else
			{
				if(showTime>=14&&showTime<60)
				{
					move += 40;
					
					if(move>=GameConfig.GameScreen_Width/4-effectRainbow[1].bitmap.getWidth()/2)
					{
						move = GameConfig.GameScreen_Width/4-effectRainbow[1].bitmap.getWidth()/2;												
					}
				}		
				else if(showTime>60)
				{
					size += 0.2f;
					
					if(size>=1)
					{
						size = 1;
					}
					
					if(showTime>=80)
					{
						state = false;
						
						if(kind == CARD1_EFFECT)
						{
							doubleGoldenTime = 250;
						}
						else if(kind == CARD2_EFFECT)
						{
//							fastReloadTime = 200;
//							
//							gameMain.reloadTime = 10;
//							
//							gameMain.waitingSpriteBullet.setReloadTime(gameMain.reloadTime);
						}
						else if(kind == CARD3_EFFECT)
						{
//							gameMain.gameCold.setCold();
//							
//							GameMedia.playSound(R.raw.yx018, 0);
						}
						else if(kind == CARD4_EFFECT)
						{
//							gameMain.gameWind.setWind();
//							
//							GameMedia.playSound(R.raw.yx019, 0);
						}
						else if(kind == CARD5_EFFECT)
						{
//							gameMain.combo.start(gameMain);
						}
					}
				}				
			}
		}
	}		
	
	public void drawCombo(Canvas canvas)
	{
		if(state)
		{
			Paint paint = new Paint();
			
			if(kind == COMBO_EFFECT)
			{
				if(showTime<4)
				{
//					paint.setColor(Color.WHITE);		
//					canvas.drawRect(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
				}
				else if(showTime<14)
				{
					combo[0].drawBitmap(canvas,combo[0].bitmap, move, y, paint);
				}
				else if(showTime<60)
				{			
					combo[0].drawBitmap(canvas,combo[0].bitmap, move, y, paint);
					
					combo[1].drawBitmap(canvas,combo[1].bitmap, move1, y-40*GameConfig.f_zoom, paint);
				}
			}
			else if(kind == WHITE_EFFECT)
			{
				paint.setColor(Color.WHITE);		
				canvas.drawRect(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);		
			}
			else if(kind == SMALLGAME_EFFECT)
			{
				if(showTime<4)
				{
					paint.setColor(Color.WHITE);		
					canvas.drawRect(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
				}
				else if(showTime<14)
				{
					effectMimi[0].drawBitmap(canvas, effectMimi[0].bitmap, x-effectMimi[0].bitmap.getWidth()/2, y-effectMimi[0].bitmap.getHeight()/2, paint);
				}
				else if(showTime<60)
				{			
					effectMimi[0].drawBitmap(canvas, effectMimi[0].bitmap, x-effectMimi[0].bitmap.getWidth()/2, y-effectMimi[0].bitmap.getHeight()/2, paint);
					
					effectMimi[1].drawBitmap(canvas, effectMimi[1].bitmap, move, y-effectMimi[1].bitmap.getHeight()/2, paint);
				}
			}
			else if(kind == AIRSHIP_EFFECT)
			{
				if(showTime<4)
				{
//					paint.setColor(Color.WHITE);		
//					canvas.drawRect(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
				}
				else if(showTime<14)
				{
					effectAirship[0].drawBitmap(canvas, effectAirship[0].bitmap, x-effectAirship[0].bitmap.getWidth()/2, y-effectAirship[0].bitmap.getHeight()/2, paint);
				}
				else if(showTime<60)
				{			
					effectAirship[0].drawBitmap(canvas, effectAirship[0].bitmap, x-effectAirship[0].bitmap.getWidth()/2, y-effectAirship[0].bitmap.getHeight()/2, paint);
					
					effectAirship[1].drawBitmap(canvas, effectAirship[1].bitmap, move, y-effectAirship[1].bitmap.getHeight()/2, paint);
				}
			}
			else
			{
				if(showTime<4)
				{
					paint.setColor(Color.WHITE);		
					canvas.drawRect(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
				}
				else if(showTime<14)
				{
					effectRainbow[0].drawBitmap(canvas, effectRainbow[0].bitmap, x-effectRainbow[0].bitmap.getWidth()/2, y-effectRainbow[0].bitmap.getHeight()/2, paint);
				}
				else if(showTime<60)
				{			
					effectRainbow[0].drawBitmap(canvas, effectRainbow[0].bitmap, x-effectRainbow[0].bitmap.getWidth()/2, y-effectRainbow[0].bitmap.getHeight()/2, paint);
					
					effectRainbow[1].drawBitmap(canvas, effectRainbow[1].bitmap, move, y-effectRainbow[1].bitmap.getHeight()/2, paint);
				}
				else
				{				
					matrix.reset();
					
					matrix.postScale(size, size, card[kind-3].bitmap.getWidth()/2, card[kind-3].bitmap.getHeight()/2);
					
					matrix.postTranslate(x-card[kind-3].bitmap.getWidth()/2, y-card[kind-3].bitmap.getHeight()/2);
					
					card[kind-3].drawBitmap(canvas, card[kind-3].bitmap, matrix, paint);
				}
			}
		}
	}		
	
}
