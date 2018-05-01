package com.kokatlaruruxi.wy;

import java.util.List;
import java.util.Vector;

import android.graphics.Canvas;

import com.socogame.coolEdit.CoolEditDefine;

public class GameRoseThorn {
	private List<Sprite> thorn;
	
	public GameRoseThorn()
	{	
		thorn = new Vector<Sprite>();
		
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_ROSETHIRDATTACK);
	}
		
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_ROSETHIRDATTACK);
	}
	
	public void setRoseThorn(int x, int y)
	{						
		Sprite sprite = new Sprite();
		sprite.initSprite(CoolEditDefine.Effect_ROSETHIRDATTACK, x, y, Sprite.SPRITE_STATE_NORMAL);
		sprite.changeAction(0);
		
		thorn.add(sprite);			
	}
	
	public void updata(int id)
	{		
		if(id<thorn.size())
			thorn.get(id).updataSprite();
	}
	
	public void paint(Canvas canvas, int id, int x, int y)
	{			
		if(id<thorn.size())	
		{
			thorn.get(id).setXY(x, y);
			thorn.get(id).paintSprite(canvas, 0, 0);
		}
	}
	
	public void remove(int id)
	{		
		if(id<thorn.size())
			thorn.remove(id);
	}
	
	public void changeState(int id, int actionName)
	{
		if(id<thorn.size())
			thorn.get(id).changeAction(actionName);
	}
}
