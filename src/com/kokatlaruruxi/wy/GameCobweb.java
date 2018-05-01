package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class GameCobweb {

	private ArrayList<Sprite> cobweb;
	
	public GameCobweb()
	{
		cobweb = new ArrayList<Sprite>();
	}
	
	public void loadImage()
	{
		SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_COBWEB);
	}
	
	public void delImage()
	{
		SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_COBWEB);
	}
	
	public void paint(Canvas canvas)
	{
		for(int i=0;i<cobweb.size();i++)
			cobweb.get(i).paintSprite(canvas, 0, 0);	
	}
	
	public ArrayList<Sprite> getCobwebList()
	{
		return cobweb;
	}
	
	public void setCobweb(int x, int y)
	{
		Sprite _cobweb = new Sprite();
		
		_cobweb.initSprite(CoolEditDefine.Effect_COBWEB, x, y, Sprite.SPRITE_STATE_NORMAL);		
		_cobweb.changeAction(0);
		
		cobweb.add(_cobweb);
	}
	
	public void updata()
	{
		for(int i=0;i<cobweb.size();i++)
		{
			if(cobweb.get(i).state == Sprite.SPRITE_STATE_NONE)
			{
				cobweb.remove(i);
			}
			else
			{
				cobweb.get(i).updataSprite();
			}
		}
	}
	
	public void bulletCollision(GameMain gameMain, Sprite bullet)
	{
		for(int i=0;i<cobweb.size();i++)
		{
			if(ExternalMethods.RectCollision(bullet.getCollisionRect(), cobweb.get(i).getCollisionRect()))				
			{
				bullet.isMove = false;
				
				bullet.setState(Sprite.SPRITE_STATE_NONE);
				
				cobweb.get(i).setState(Sprite.SPRITE_STATE_NONE);
				
				gameMain.gameMonster.getEffect().add(CoolEditDefine.Effect_ATTACK2, (int)cobweb.get(i).x, (int)cobweb.get(i).y-SpriteLibrary.GetH(cobweb.get(i).kind)/2, Sprite.SPRITE_STATE_NORMAL, 50, 1f);													
				
				break;
			}
		}
	}
}
