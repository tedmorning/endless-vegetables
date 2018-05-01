package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.socogame.coolEdit.CoolEditDefine;

public class GameMagicDoor {
	
	private Sprite door;
	
	private ArrayList<Sprite> halo;
	
	private int magicDoorShowTime = 0;//ħ���ų���ʱ��
	
	private int magicDoorWaitingTime = 0;//ħ���� ����ʱ��
	
	public GameMagicDoor()
	{		
		door = new Sprite();		
		
		halo = new ArrayList<Sprite>();
	}
	
	public void setMagicDoor(int _x, int _y, int showTime, int waitingTime)
	{
		door.initSprite(CoolEditDefine.Effect_MAGICDOOR, _x, _y, Sprite.SPRITE_STATE_NONE);
		door.changeAction(0);
		
		magicDoorShowTime = showTime;
		
		magicDoorWaitingTime = waitingTime;
	}
	
	public void loadImage()
	{
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_MAGICDOOR);
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_MAGICHALO);
	}
	
	public void delImage()
	{		
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_MAGICDOOR);
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_MAGICHALO);
	}
	
	public void paintDoor(Canvas canvas)
	{
		door.paintSprite(canvas, 0, 0);
	}
	
	public void paintHalo(Canvas canvas)
	{
		for(int i=0;i<halo.size();i++)
		{
			halo.get(i).paintSprite(canvas, 0, 0);
		}
	}
	
	public void update()
	{
		if(magicDoorWaitingTime>0)
		{
			magicDoorShowTime --;
			
			if(magicDoorShowTime==0)
			{
				door.changeAction(0);
				
				door.setState(Sprite.SPRITE_STATE_NORMAL);
			}
			else if(magicDoorShowTime<0)
			{
				magicDoorWaitingTime --;
				
				if(magicDoorWaitingTime==0)
				{
					door.changeAction(2);
				}
			}
		}
		
		door.updataSprite();
		
		for(int i=0;i<halo.size();i++)
		{
			halo.get(i).updataSprite();
		}
	}
	
	public void collision(GameMain gameMain, Sprite _rose)
	{
		
	}
	
//	class MagicDoorSprite extends Sprite
//	{
//		public int magicDoorState = 0;//ħ����״ֵ̬
//		
//		public int magicDoorTime = 0;//ħ���Ŵ���ʱ��
//		
//		public int magicDoorShowTime = 0;//ħ���ų���ʱ��
//	}
}
