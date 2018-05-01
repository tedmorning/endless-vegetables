package com.kokatlaruruxi.wy;

import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class GameFirendlyHammer {

	private Sprite hammer;
	
	private boolean isShow;
	
	public GameFirendlyHammer()
	{
		init();
	}
	
	public void init()
	{
		isShow = false;
		
		hammer = new Sprite();
		
		hammer.initSprite(CoolEditDefine.Effect_HAMMER, GameConfig.GameScreen_Width/2, (int)(GameConfig.GameScreen_Height/2-100*GameConfig.f_zoomy), Sprite.SPRITE_STATE_NORMAL);
		hammer.changeAction(0);
		
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_HAMMER);
	}
	
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_HAMMER);
		
		hammer.recycleBitmap();
	}
	
	public void paint(Canvas canvas)
	{
		if(!isShow)
			return;
		
		hammer.paintSprite(canvas, 0, 0);
	}
	
	public void updata(GameMain gameMain)
	{
		if(!isShow)
			return;
		
		hammer.updataSprite();
		
		if(hammer.state==Sprite.SPRITE_STATE_NONE)
		{
			isShow = false;
			
			for(int i=0;i<gameMain.gameMonster.getEnemyList().size();i++)
			{		
				if(gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_HZXJ||
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MEGICWATER||
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MMJS||
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_SHZYCS||
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_SHZYXZ||
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_YGYJF||
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_YGYKS||
				   gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_YGYYM)
				gameMain.gameMonster.getEnemyList().get(i).dizzinessTime = 50;
				
				gameMain.gameMonster.getEnemyList().get(i).speedDownTime = 0;
				
				gameMain.gameMonster.getEnemyList().get(i).setSlowDown(false);
				
				gameMain.gameMonster.getEffect().add(gameMain.gameMonster.getEnemyList().get(i), CoolEditDefine.Effect_DIZZINESS, 
						(int)gameMain.gameMonster.getEnemyList().get(i).x, 
						(int)gameMain.gameMonster.getEnemyList().get(i).y-SpriteLibrary.GetH(gameMain.gameMonster.getEnemyList().get(i).kind), 
						Sprite.SPRITE_STATE_NORMAL, gameMain.gameMonster.getEnemyList().get(i).dizzinessTime);
				
				gameMain.gameMonster.addAbnormalStateNumber();
			}
		}
	}
	
	public void setShow()
	{
		isShow = true;
		
		hammer.initSprite(CoolEditDefine.Effect_HAMMER, GameConfig.GameScreen_Width/2, (int)(GameConfig.GameScreen_Height/2-100*GameConfig.f_zoomy), Sprite.SPRITE_STATE_NORMAL);
		hammer.changeAction(0);
		
		hammer.size = 1;
	}
	
	public void onTouchEvent(MotionEvent event)
	{
		setShow();
	}
	
	public boolean getShowState()
	{
		return isShow;
	}
}
