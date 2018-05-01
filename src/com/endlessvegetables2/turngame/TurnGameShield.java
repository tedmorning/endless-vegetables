package com.endlessvegetables2.turngame;

import java.util.List;
import java.util.Vector;

import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class TurnGameShield {
	private List<TurnGameSprite> shield;
	
	public TurnGameShield()
	{	
		shield = new Vector<TurnGameSprite>();
		
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_SHIELD);
	}
		
	public void setShield(int x, int y)
	{						
		TurnGameSprite sprite = new TurnGameSprite();
		sprite.initSprite(CoolEditDefine.Effect_SHIELD, x, y, TurnGameSprite.SPRITE_STATE_NORMAL);
		sprite.changeAction(0);
		
		shield.add(sprite);
	}
		
	public void delImage()
	{
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_SHIELD);
	}
	
	public void updata(int id)
	{		
		if(id<shield.size())
		shield.get(id).updataSprite();
	}
	
	public void paint(Canvas canvas, int id, int x, int y)
	{			
		if(id<shield.size())	
		{
			shield.get(id).setXY(x, y);
			shield.get(id).paintSprite(canvas, 0, 0);
		}
	}
	
	public void remove(int id)
	{		
		if(id<shield.size())
		shield.remove(id);
	}
}
