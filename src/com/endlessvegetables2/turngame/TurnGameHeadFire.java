package com.endlessvegetables2.turngame;

import java.util.List;
import java.util.Vector;

import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class TurnGameHeadFire {
	private List<TurnGameSprite> fire;
	
	public TurnGameHeadFire()
	{	
		fire = new Vector<TurnGameSprite>();
		
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_HEADFIRE);
	}
		
	public void delImage()
	{
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_HEADFIRE);
	}
	
	public void setHeadFire(int x, int y)
	{						
		TurnGameSprite sprite = new TurnGameSprite();
		sprite.initSprite(CoolEditDefine.Effect_HEADFIRE, x, y, TurnGameSprite.SPRITE_STATE_NORMAL);
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
