package com.kokatlaruruxi.wy;

import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class SpriteLattice {

	private Sprite spriteLattice;
	
	private byte spriteLatticeStep;
	
	private boolean spriteLatticeChangeColor;
		
	private boolean gameOver;
	
	private int lostBloodNum;
	
	private int spriteLatticeKind;
	
	public SpriteLattice()
	{
		gameOver = false;
		
		lostBloodNum = 0;
	}
	
	public int getLostBloodNum()
	{
		return lostBloodNum;
	}
	
	public Sprite getSpriteLattice()
	{
		return spriteLattice;
	}
	
	public int getSpriteLatticeHeight()
	{
		return SpriteLibrary.GetH(spriteLattice.kind);
	}
	
	public void initSpriteLattice(int lifeValue, int x, int y, int level)
	{
		if(level==0)
			spriteLatticeKind = CoolEditDefine.Lattice;
		else if(level==1)
			spriteLatticeKind = CoolEditDefine.Lattice_2;
		else if(level==2)
			spriteLatticeKind = CoolEditDefine.Lattice_3;
		else if(level==3)
			spriteLatticeKind = CoolEditDefine.Lattice_4;		
		
		SpriteLibrary.loadSpriteImage(spriteLatticeKind);
		
		spriteLattice = new Sprite();
				
		spriteLattice.initSprite(spriteLatticeKind, x, y, Sprite.SPRITE_STATE_NORMAL);
		
		spriteLattice.setXY(x, y-SpriteLibrary.GetH(spriteLattice.kind)/2);
		
		spriteLattice.changeAction(0);				
		
		spriteLattice.lifeMax = lifeValue;
		
		spriteLattice.life = spriteLattice.lifeMax;
		
		spriteLatticeStep = 0;
		
		spriteLatticeChangeColor = false;
	}
	
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(spriteLatticeKind);
		
		spriteLattice.recycleBitmap();
	}
	
	public void paint(Canvas canvas)
	{		
		spriteLattice.paintSprite(canvas, 0, 0);
	}
	
	public void spriteLatticeAddBlood(int bloodNumber)
	{
		spriteLattice.life += bloodNumber;
		
		if(spriteLattice.life>=spriteLattice.lifeMax)		
		   spriteLattice.life=spriteLattice.lifeMax;
		
		if(spriteLattice.life>spriteLattice.lifeMax/3*2)
		{			
			if(spriteLattice.getActionName()!=0)
			spriteLattice.changeAction(0);
		}
		else if(spriteLattice.life>spriteLattice.lifeMax/3)
		{			
			if(spriteLattice.getActionName()!=1)
			spriteLattice.changeAction(1);
		}
		else if(spriteLattice.life>0)
		{			
			if(spriteLattice.getActionName()!=2)
			spriteLattice.changeAction(2);
		}				
	}
	
	public void spriteLatticeLostBlood(Sprite sprite)
	{
		if(spriteLattice.life>0)
		{
			spriteLattice.life -= SpriteLibrary.GetAttack(sprite.kind);							
			
			lostBloodNum ++;
			
			if(spriteLattice.life<=0)
			{			
				if(spriteLattice.getActionName()!=3)
				spriteLattice.changeAction(3);
			}
			else if(spriteLattice.life<spriteLattice.lifeMax/3)
			{			
				if(spriteLattice.getActionName()!=2)
				spriteLattice.changeAction(2);
			}
			else if(spriteLattice.life<spriteLattice.lifeMax/3*2)
			{			
				if(spriteLattice.getActionName()!=1)
				spriteLattice.changeAction(1);
			}
		}
				
		if(!spriteLatticeChangeColor)
		{
			spriteLatticeChangeColor = true;						
		}				
	}
	
	public void updata()
	{
		spriteLattice.updataSprite();
		
		if(spriteLatticeChangeColor)
		{
			spriteLatticeStep ++;
			
			if(spriteLatticeStep<5)
			{
				spriteLattice.r = 255;
				spriteLattice.g = 255;
				spriteLattice.b = 255;
			}
			else if(spriteLatticeStep<10)
			{
				spriteLattice.r = 255;
				spriteLattice.g = 0;
				spriteLattice.b = 0;
			}
			else 
			{
				spriteLattice.r = 0;
				spriteLattice.g = 0;
				spriteLattice.b = 0;		
				
				spriteLatticeStep = 0;								
												
				spriteLatticeChangeColor = false;
				
				if(spriteLattice.life<=0)
				{
					spriteLattice.life=0;
					
					gameOver = true;
					
//					GameMedia.playMusic(R.raw.music09, false, true);	
				}
			}
		}
	}
	
	public boolean getGameOver()
	{
		return gameOver;
	}
}
