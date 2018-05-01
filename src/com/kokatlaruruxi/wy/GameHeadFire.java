package com.kokatlaruruxi.wy;

import java.util.List;
import java.util.Vector;

import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class GameHeadFire {
	private List<Sprite> fire;
	
	public GameHeadFire()
	{	
		fire = new Vector<Sprite>();
		
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_HEADFIRE);
	}
		
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_HEADFIRE);
	}
	
	public void setHeadFire(int x, int y)
	{						
		Sprite sprite = new Sprite();
		sprite.initSprite(CoolEditDefine.Effect_HEADFIRE, x, y, Sprite.SPRITE_STATE_NORMAL);
		sprite.changeAction(0);
		
		fire.add(sprite);			
	}
		
	public void updata(int id)
	{		
		if(id<fire.size())
			fire.get(id).updataSprite();
	}
	
	public void paint(Canvas canvas, int id, int x, int y)
	{			
		if(id<fire.size())	
		{
			fire.get(id).setXY(x, y);
			fire.get(id).paintSprite(canvas, 0, 0);
		}
	}
	
	public void remove(int id)
	{		
		if(id<fire.size())
			fire.remove(id);
	}
}
