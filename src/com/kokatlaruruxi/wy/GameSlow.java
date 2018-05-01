package com.kokatlaruruxi.wy;

import java.util.List;
import java.util.Vector;

import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class GameSlow {
	private List<Sprite> slow;
	
	public GameSlow()
	{	
		slow = new Vector<Sprite>();
		
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_SLOWDOWN);
	}
		
	public void setSlow(int x, int y)
	{						
		Sprite sprite = new Sprite();
		sprite.initSprite(CoolEditDefine.Effect_SLOWDOWN, x, y, Sprite.SPRITE_STATE_NORMAL);
		sprite.changeAction(0);
		
		slow.add(sprite);
	}
		
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_SLOWDOWN);
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
