package com.endlessvegetables2.turngame;

import java.util.List;
import java.util.Vector;

import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class TurnGameSlow {
	private List<TurnGameSprite> slow;
	
	public TurnGameSlow()
	{	
		slow = new Vector<TurnGameSprite>();
		
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_SLOWDOWN);
	}
		
	public void setSlow(int x, int y)
	{						
		TurnGameSprite sprite = new TurnGameSprite();
		sprite.initSprite(CoolEditDefine.Effect_SLOWDOWN, x, y, TurnGameSprite.SPRITE_STATE_NORMAL);
		sprite.changeAction(0);
		
		slow.add(sprite);
	}
		
	public void delImage()
	{
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_SLOWDOWN);
	}
	
	public void updata(int id)
	{		
		if(id<slow.size())
			slow.get(id).updataSprite();
	}
	
	public void paint(Canvas canvas, int id, int x, int y)
	{			
		if(id<slow.size())	
		{
			slow.get(id).setXY(x, y);
			slow.get(id).paintSprite(canvas, 0, 0);
		}
	}
	
	public void remove(int id)
	{		
		if(id<slow.size())
			slow.remove(id);
	}
}
