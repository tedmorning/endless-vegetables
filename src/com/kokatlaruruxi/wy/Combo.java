package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.Achievement;
import com.endlessvegetables2.ui.GameStaticImage;
import com.endlessvegetables2.ui.Location;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditData;
import com.socogame.coolEdit.CoolEditDefine;

public class Combo {

	public static final int ComboNumberMax = 6;
	
	private Sprite combo;
	
	private Sprite cao;
	
	private Sprite cao1[];
	
	private Sprite wordNum[];
	
	private int caoShowLen;
	
	private int comboNumber;
	
	private boolean comboShow;
	
	private int timeLen;
	
	private int start;
	
	private char wordNumChars[];
	
	private int comboShowTime;//暴击持续时间
	
	private ArrayList<ComboAnimation> comboAnimation;
	
	private int comboDescendingTime;
	
	private int readSpritekind;		
	
	private int comboSpecialTime;
	
	private byte ComboAnimationFrame;
	
	private Bitmap[] wordNumBit;
	
	private int startComboNumber;
	
	private float combox;
	
	private float comboy;	
	
	private Sprite arrow;
	
	private Sprite comboPrompt;
	
	private int showArrowTime;
	
	private float ArrowH;
	
	private boolean isArrowDown;
	
	public void init(int comboShowTime)
	{
		comboShow = false;
		
		caoShowLen = 0;
		
		comboNumber = 0;
				
		comboAnimation = new ArrayList<ComboAnimation>();	
		
		wordNumChars = new char[]{'0','1','2','3','4','5','6','7','8','9'};
		
		this.comboShowTime = comboShowTime;
		
		comboDescendingTime = 50;
		
		comboSpecialTime = 0;
		
		startComboNumber = 0;
		
		showArrowTime = 0;
	}
	
	public int getComboNumber()
	{
		return comboNumber;
	}
	
	public void completeCombo()
	{
		caoShowLen = ComboNumberMax;
	}
	
	public void loadImage()
	{
		cao = new Sprite(GameImage.getImage("newui/ui_combo_01"));

		arrow = new Sprite(GameImage.getImage(GameStaticImage.map_farmArrow));

		comboPrompt = new Sprite(GameImage.getImage("newCombo/ui_hold"));
		
		isArrowDown = false;
		
		cao1 = new Sprite[6];
		
		for(int i=0;i<6;i++)
		cao1[i] = new Sprite(GameImage.getImage("newui/ui_combo_0"+(i+2)));
		
		combo = new Sprite(GameImage.getImage("word/combo"));
		
		wordNumBit = GameImage.getAutoSizecutBitmap("word/num_5", 10, 1, GameImage.Sort_line);
		wordNum = new Sprite[wordNumBit.length];
		for(int i=0;i<wordNumBit.length;i++)
		{
			wordNum[i] = new Sprite(wordNumBit[i]);
		}
	}
	
	public void delImage()
	{
		GameImage.delImage(cao.bitmap);
		
		GameImage.delImage(combo.bitmap);
		
		for(int i=0;i<cao1.length;i++)
			GameImage.delImage(cao1[i].bitmap);
						
		for(int i=0;i<wordNum.length;i++)
			GameImage.delImage(wordNum[i].bitmap);
		
		
		GameImage.delImage(arrow.bitmap);
		arrow.recycleBitmap();
		arrow = null;
		
		GameImage.delImage(comboPrompt.bitmap);		
		comboPrompt.recycleBitmap();
		comboPrompt = null;
		
		combo.recycleBitmap();
		
		cao.recycleBitmap();
		
		for(int i=0;i<cao1.length;i++)
			cao1[i].recycleBitmap();
		
		for(int i=0;i<wordNum.length;i++)
			wordNum[i].recycleBitmap();	
		
		GameImage.delImageArray(wordNumBit);
	}
	
	public void updata(GameMain gameMain)
	{				
		//教学
		if(gameMain.gameTeaching.pauseState())
			return;
		
		if(comboSpecialTime>0)
		{
			comboSpecialTime --;
			
			if(comboSpecialTime==0)
			{
				reduction(gameMain);
			}
			
			return;
		}
			
		arrowTimeUpdata();
		
		if(comboShow)
		{		
			start ++;
			
			if(timeLen>0)
			{				
				timeLen = (int)(cao.bitmap.getWidth()*(comboShowTime-/*(end-*/start/*)*/)/comboShowTime);
			}
			else 		
			{
				comboShow = false;
				
				caoShowLen = 0;
			
				comboNumber = 0;
				
				reduction(gameMain);	
				
				gameMain.slingshot.changeSlingShotPieceFrame(0);
			}
		}
		else
		{
			if(caoShowLen!=ComboNumberMax)
			{			
				comboDescendingTime --;
				
				if(comboDescendingTime<=0)
				{
					if(caoShowLen>0)
					{
						caoShowLen --;					
					}
					
					comboDescendingTime = 50;
				}
			}
		}
	}
	
	private void reduction(GameMain gameMain)
	{
		gameMain.readSpriteBullet.initSpriteBullet(readSpritekind, gameMain.slingshot.slingShotPiece_x, gameMain.slingshot.slingShotPiece_y, gameMain.gameMainLeftPlayerSpecial);
		
		gameMain.readSpriteBullet.changeSpriteBulletAction(1);
		
		gameMain.slingshot.setSendSpriteBullet(false);
		
		if(readSpritekind == CoolEditDefine.Player_TD||
			readSpritekind == CoolEditDefine.Player_TD_2||
			readSpritekind == CoolEditDefine.Player_TD_3||
			readSpritekind == CoolEditDefine.Player_MG||
			readSpritekind == CoolEditDefine.Player_MG_2||
			readSpritekind == CoolEditDefine.Player_MG_3||
			readSpritekind==CoolEditDefine.Player_HC||
			readSpritekind==CoolEditDefine.Player_HC_2||
			readSpritekind==CoolEditDefine.Player_HC_3)
		gameMain.slingshot.getIndicator().setSnipeState(true);		
	}
	
	public void setCombo(GameMain gameMain, int x, int y)
	{
		if(comboSpecialTime>0)
			return;
		
		if(!comboShow)
		{
//			gameMain.hitNum ++;
			
			comboNumber ++;
			
			if(comboNumber>=2)
			{
				int num = (gameMain.doubleGameNumberTime>0?comboNumber*20:comboNumber*10);
				
				if(num>=100)
					num = 100;
				
				gameMain.gameNumber += num;
				
				for(int i=0;i<comboAnimation.size();i++)
				{
					if(!comboAnimation.get(i).getComboState())
					{
						comboAnimation.remove(i);
					}
				}
				
				ComboAnimation anim = new ComboAnimation();
				
				anim.setComboAnimation(x, y, comboNumber);
				
				comboAnimation.add(anim);
			}
		}
		
		if(!comboShow&&caoShowLen!=ComboNumberMax)
		{
//			comboNumber ++;
			
			if(comboNumber>=2)
			{							
				caoShowLen ++;
				
				comboDescendingTime = 50;
				
				if(caoShowLen>=ComboNumberMax)
				{			
					caoShowLen = ComboNumberMax;
					
					gameMain.slingshot.changeSlingShotPieceFrame(1);
					
					showArrowTime = 125;
				}
				else								
				{
					
				}
				
//				for(int i=0;i<comboAnimation.size();i++)
//				{
//					if(!comboAnimation.get(i).getComboState())
//					{
//						comboAnimation.remove(i);
//					}
//				}
//				
//				ComboAnimation anim = new ComboAnimation();
//				
//				anim.setComboAnimation(x, y, comboNumber);
//				
//				comboAnimation.add(anim);	
			}		
		}
	}
	
	public void comboNumberClaer()
	{
		comboNumber = 0;
	}
	
	public boolean isComboResult()
	{
		if(caoShowLen>=ComboNumberMax)
			return true;
		else 
			return false;
	}
			
	public int getCombox()
	{
		return (int)(combox);
	}
	
	public int getComboy()
	{
		return (int)(comboy);
	} 
	
	public int getComboWidth()
	{
		return cao.bitmap.getWidth();
	}
	
	public int getComboHeight()
	{
		return cao.bitmap.getHeight();
	}
	
	private void drawComboAnimation(Canvas canvas, GameMain gameMain)
	{
		Paint paint = new Paint();
		
		combox = (GameConfig.GameScreen_Width-cao.bitmap.getWidth())/2;
		
		comboy = gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight();
		
//		cao.drawBitmap(canvas, cao.bitmap, (GameConfig.GameScreen_Width-cao.bitmap.getWidth())/2, gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), paint);
		
		cao.drawBitmap(canvas, cao.bitmap, combox, comboy, paint);
		
		canvas.save();
		
		if(comboShow)
		{					
			canvas.clipRect((GameConfig.GameScreen_Width-cao.bitmap.getWidth())/2, gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 
							(GameConfig.GameScreen_Width-cao.bitmap.getWidth())/2+timeLen, gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()+cao.bitmap.getHeight());						
		}
		else
		{
			int t = cao.bitmap.getWidth()*caoShowLen/ComboNumberMax;
			
			canvas.clipRect((GameConfig.GameScreen_Width-cao.bitmap.getWidth())/2, gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 
							(GameConfig.GameScreen_Width-cao.bitmap.getWidth())/2+t, gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight()+cao.bitmap.getHeight());						
		}
		
		if(isComboResult()||comboShow)
		{
			ComboAnimationFrame ++;
			 
			if(ComboAnimationFrame>=cao1.length)
				ComboAnimationFrame = 0;
			
			cao1[ComboAnimationFrame].drawBitmap(canvas, cao1[ComboAnimationFrame].bitmap, (GameConfig.GameScreen_Width-cao.bitmap.getWidth())/2, gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), paint);
		}
		else
		{
			cao1[0].drawBitmap(canvas, cao1[0].bitmap, (GameConfig.GameScreen_Width-cao.bitmap.getWidth())/2, gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), paint);
		}
		
		canvas.restore();
		
		//提示使用燃烧槽
		paintArrow(canvas, gameMain);
		
	}
	
	public void arrowTimeUpdata()
	{
		if(!isComboResult())return;
		
		showArrowTime --;
		
		if(showArrowTime<=0)
		showArrowTime = 0;					
	}
	
	/*
	 * 气积满状态提示
	 * */
	public void paintArrow(Canvas canvas, GameMain gameMain){
		//教学
		if(gameMain.gameTeaching.pauseState())
			return;
		
		if(!isComboResult())return;
		
		if(getComboState())return;
		
		if(showArrowTime==0)
		{
			//指引箭头
			if (isArrowDown) {
				ArrowH -= Math.max(2, 0);
				if (ArrowH <= 0) {
					ArrowH = 0;
					isArrowDown = false;
				}
			} else {
				ArrowH += Math.min(2, 15);
				if (ArrowH >= 15) {
					ArrowH = 15;
					isArrowDown = true;
				}
			}
			
			comboPrompt.drawBitmap(canvas, gameMain.slingshot.SLINGSHOT_X - comboPrompt.bitmap.getWidth()/2, gameMain.slingshot.SLINGSHOT_Y+130*GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0, 0, 0, 0);		
			arrow.drawBitmap(canvas, gameMain.slingshot.SLINGSHOT_X - arrow.bitmap.getWidth()/2, gameMain.slingshot.SLINGSHOT_Y+100*GameConfig.f_zoomy-ArrowH*GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
		}
	}
	
	public void paint(Canvas canvas, GameMain gameMain)
	{		
		drawComboAnimation(canvas, gameMain);		
		
		for(int i=0;i<comboAnimation.size();i++)
		{		
			comboAnimation.get(i).drawComboAnimation(canvas);
		}
	}
	
	public void start(GameMain gameMain)
	{
		timeLen = cao.bitmap.getWidth();
		
		comboShow = true;
		
		start = 0;
		
		readSpritekind = gameMain.readSpriteBullet.getSpriteBullet().kind;
		
		gameMain.readSpriteBullet.initSpriteBullet(gameMain.gameMainLeftPlayerID, gameMain.slingshot.slingShotPiece_x, gameMain.slingshot.slingShotPiece_y, gameMain.gameMainLeftPlayerSpecial);
		
		int actionName = 4;
		
		if(gameMain.comboShowLevel==1)
			actionName = 4;
		else if(gameMain.comboShowLevel==2)
			actionName = 8;
		else if(gameMain.comboShowLevel==3)
			actionName = 10;
		
		gameMain.readSpriteBullet.changeSpriteBulletAction(actionName);		
		
		if(!VeggiesData.isMuteSound())
		GameMedia.playSound(R.raw.combos, 0);
		
		startComboNumber ++;
		
		VeggiesData.addAchievementNum(Achievement.USE_ONEHUNDRED_BURN, 1);
	}
	
	public boolean getComboState()
	{
		return comboShow;
	}
	
	public boolean getComboSpecialTimeState()
	{
		if(comboSpecialTime<=0)
			return false;
		else
			return true;
	}
	
	public int getStartComboNumber()
	{
		return startComboNumber;
	}
	
	private class ComboAnimation
	{	
		private boolean comboState;
		
		private int comboMove;		
		private int combo_x;
		private int combo_y;
		
		private int comboNumber;

		public void setComboAnimation(int x, int y, int num)
		{
			combo_x = x;
			
			combo_y = y;
			
			comboState = true;
			
			comboMove = 0;
			
			comboNumber = num;
		}
		
		public boolean getComboState()
		{
			return comboState;
		}
		
		public void drawComboAnimation(Canvas canvas)
		{			
			if(comboState)
			{
				Paint paint=new Paint();
					
				comboMove +=2;
				
				if(comboMove<30)	
				{
					paint.setAlpha(255-(comboMove*9));
					
					if(comboMove==2)
					{
						int n = 1;
						
						int t = comboNumber;
						
						while(t/10>0)
						{
							n ++;
							
							t/=10;
						}
						
						int w = combo.bitmap.getWidth();
						
						w += wordNum[0].bitmap.getWidth()*n;
						
						combo_x -= w/2;
					}
					
					combo.drawBitmap(canvas, combo.bitmap, combo_x, combo_y+combo.bitmap.getHeight()-comboMove, null, GameLibrary.BL, paint);
					
					GameLibrary.DrawNumber(canvas, wordNum, combo_x+combo.bitmap.getWidth(), combo_y+combo.bitmap.getHeight()+8*GameConfig.f_zoom-comboMove, wordNumChars, comboNumber+"", paint, GameLibrary.BL, -10);					
				}
				else
				{
					comboState = false;
				}
			}				
		}		
	}		
}
