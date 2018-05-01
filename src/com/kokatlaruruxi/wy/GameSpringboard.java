package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class GameSpringboard {

	private ArrayList<Sprite> Springboard;
	
	private boolean isShow;
	
	private byte jumpStepLong;
	
	private int touchSpringBoardNumber;//ÀÛ¼Æ´¥ÅöÌø°åÊý
	
	public GameSpringboard()
	{
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_BANANA);
		
		Springboard = new ArrayList<Sprite>();
		
		isShow = false;
		
		touchSpringBoardNumber = 0;
	}
	
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_BANANA);
	}
	
	public void setSpringboard(int[] pls, int stepLong)
	{
		isShow = true;
		
		jumpStepLong = (byte)stepLong;
		
		for(int i=0;i<pls.length/2;i++)
		{
			Sprite sprite = new Sprite();
			
			sprite.initSprite(CoolEditDefine.Effect_BANANA, (int)(pls[i*2]*GameConfig.f_zoomx), (int)(pls[i*2+1]*GameConfig.f_zoomx), Sprite.SPRITE_STATE_NORMAL);
			sprite.changeAction(0);
			
			Springboard.add(sprite);
		}
	}
	
	public void paint(Canvas canvas)
	{
		if(!isShow)
			return;
		
		for(int i=0;i<Springboard.size();i++)
			Springboard.get(i).paintSprite(canvas, 0, 0);
	}
	
	public void updata(GameMain gameMain)
	{
		if(!isShow)
			return;
		
		for(int i=0;i<Springboard.size();i++)
		{
			Springboard.get(i).updataSprite();
		
			collision(gameMain, Springboard.get(i));
		}
	}
	
	public void collision(GameMain gameMain, Sprite springboard)
	{
		for(int i=0;i<gameMain.gameMonster.getEnemyList().size();i++)
		{		
			if(!gameMain.gameMonster.getEnemyList().get(i).isFly)
			{
				if(gameMain.gameMonster.getEnemyList().get(i).kind != CoolEditDefine.Enemy_DS&&
				   gameMain.gameMonster.getEnemyList().get(i).kind != CoolEditDefine.Enemy_YGDS&&
				   gameMain.gameMonster.getEnemyList().get(i).kind != CoolEditDefine.Enemy_HZXJ&&
				   gameMain.gameMonster.getEnemyList().get(i).kind != CoolEditDefine.Enemy_MEGICWATER)
				{
					if(gameMain.gameMonster.getEnemyList().get(i).jumpState==0&&
					   gameMain.gameMonster.getEnemyList().get(i).y<springboard.y+SpriteLibrary.GetH(springboard.kind)/2)
					{
						if(ExternalMethods.RectCollision(springboard.getCollisionRect(), gameMain.gameMonster.getEnemyList().get(i).getCollisionRect()))
						{
							gameMain.gameMonster.getEnemyList().get(i).jumpStep = jumpStepLong;
							
							gameMain.gameMonster.getEnemyList().get(i).jumpState = 8;	
							
							springboard.changeAction(1);
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.catapults, 0);
							
							touchSpringBoardNumber ++;
						}			
					}
					
					jump(gameMain.gameMonster.getEnemyList().get(i), gameMain);	
				}
			}
		}
	}
	
	private void jump(Sprite sprite, GameMain gameMain)
	{
		if(sprite.jumpState>0)
		{
			switch(sprite.jumpState)
			{
				case 8:					
					sprite.size = 1.2f;
					break;
					
				case 7:
					sprite.size = 1.4f;
					break;	
					
				case 6:
					sprite.size = 1.6f;
					break;		
					
				case 5:
					sprite.size = 1.8f;
					break;	
					
				case 4:
					sprite.size = 1.6f;
					break;	
					
				case 3:
					sprite.size = 1.4f;
					break;		
					
				case 2:
					sprite.size = 1.2f;
					break;
					
				case 1:
					sprite.size = 1f;
					break;															
			}

			sprite.y += sprite.jumpStep;
			
			sprite.jumpState --;
			
			if(sprite.dizzinessTime>0)
			{
				gameMain.gameMonster.getEffect().setEffectXY(CoolEditDefine.Effect_DIZZINESS, sprite.dizzinessLinkNumber, (int)sprite.x, (int)sprite.y-SpriteLibrary.GetH(sprite.kind));
			}
			
			if(sprite.freezeTime>0)
			{
				gameMain.gameMonster.getEffect().setEffectXY(CoolEditDefine.Effect_ICESTATELV3, sprite.freezeLinkNumber, (int)sprite.x, (int)sprite.y);
			}
			
			if(sprite.speedDownTime>0)
			{
				gameMain.gameMonster.getEffect().setEffectXY(CoolEditDefine.Effect_SLOWDOWN, sprite.speedDownLinkNumber, (int)sprite.x, (int)sprite.y-SpriteLibrary.GetH(sprite.kind));
			}
		}
	}
	
	public int getTouchSpringBoardNumber()
	{
		return touchSpringBoardNumber;
	}	
}