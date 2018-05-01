package com.kokatlaruruxi.wy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

import com.socoGameEngine.GameConfig;

public class ReadSpriteBullet {

	private Sprite spriteBullet;
	
	private boolean isReloadState;
	
	private int isReloadShowTime;
	
//	private Effect effect;
	
	public ReadSpriteBullet()
	{
//		effect = new Effect();
//		
//		effect.add(SpriteLibrary.Effect_HALO, 0, 0, Sprite.SPRITE_STATE_NORMAL, 0);		
	}
	
	public void initSpriteBullet(int playerId, int x, int y, int special)
	{
		spriteBullet = new Sprite();
				
		spriteBullet.initSprite(playerId, x, y, Sprite.SPRITE_STATE_NORMAL);
				
//		if(enterGoldWorld == 0)
//		{			
//			if(isAutomaticFire)
//				spriteBullet.changeAction(2);
//			else	
//				spriteBullet.changeAction(1);
//		}
//		else
//		{			
		spriteBullet.changeAction(1);
//		}
		
		spriteBullet.setXY(x, y);
		
		spriteBullet.special = special;
				
		initReloadState();
		
//		GameMedia.playSound(R.raw.yx006, 0);
	}
	
	public void showReLoadMessage(Canvas canvas, int x, int y)
	{
		if(isReloadState)
		{
			isReloadShowTime ++;
			
			if(isReloadShowTime<10)
			{
				Paint paint = new Paint();
				
				paint.setColor(Color.RED);
				paint.setTextAlign(Align.CENTER);			
				paint.setTextSize(30);
				
				canvas.drawText("Reload", x, y-60*GameConfig.f_zoom, paint);
			}
			else
			{
				initReloadState();
			}
		}
	}
	
	public void initReloadState()
	{
		isReloadShowTime = 0;
		
		isReloadState = false;
	}
	
	public void openReloadState()
	{
		isReloadState = true;
	}
	
	public void delReadSpriteBullet()
	{
		spriteBullet.state = Sprite.SPRITE_STATE_NONE;
	}
	
	public void paint(Canvas canvas)
	{
		//---------------------------------- 准备就绪的蔬菜  ------------------------------
						
//		if(spriteBullet.special==0)
//			spriteBullet.paintSpriteShadow(canvas, 46, 1);	
//		spriteBullet.paintSpriteShadow(canvas, 64, 1);	
		
//		else
//		{
//			effect.setXY(0, spriteBullet.x, spriteBullet.y+30*GameConfig.f_zoom);
//		
//			effect.paint(canvas, 0);
//		}
		
		spriteBullet.paintSprite(canvas, 0, 0);
	}
	
	public void updata(int x, int y, float degree)
	{
//		effect.updata();
		
		setSpriteBullet(x, y, degree);
		
		spriteBullet.updataSprite();
	}
	
	public void setSpriteBullet(int x, int y, float degree)
	{
		if(spriteBullet!=null && spriteBullet.state!=Sprite.SPRITE_STATE_NONE)
		{
			spriteBullet.jiaodu = degree-270;
			spriteBullet.setXY(x, y);
		}
	}
	
	public Sprite getSpriteBullet()
	{
		return spriteBullet;
	}
	
	public void changeSpriteBulletAction(int action_id)
	{
		spriteBullet.changeAction(action_id);
	}
}
