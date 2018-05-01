package com.endlessvegetables2.turngame;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class TurnGameLatticeRestore {
	
	private TurnGameSprite latticeRestore;
	
	private boolean showTime;
	
	public TurnGameLatticeRestore(TurnGameMain gameMain)
	{
		init(gameMain);
	}
	
	public void init(TurnGameMain gameMain)
	{
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_RESTORE);
		
		latticeRestore = new TurnGameSprite();
		latticeRestore.initSprite(CoolEditDefine.Effect_RESTORE, gameMain.slingshot.SLINGSHOT_X, gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), TurnGameSprite.SPRITE_STATE_NORMAL);		
		latticeRestore.changeAction(0);
		
		showTime = false;
	}
	
	public void delImage()
	{
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_RESTORE);
		
		latticeRestore.recycleBitmap();
	}
	
	public boolean getShowTimeState()
	{
		return showTime;
	}
	
	public void loadImage()
	{
		
	}
	
	public void showTime(TurnGameMain gameMain, int num)
	{
		showTime = true;
		
		latticeRestore.initSprite(CoolEditDefine.Effect_RESTORE, gameMain.slingshot.SLINGSHOT_X, gameMain.slingshot.SLINGSHOT_Y+gameMain.spriteLattice.getSpriteLatticeHeight(), TurnGameSprite.SPRITE_STATE_NORMAL);		
		latticeRestore.changeAction(0);
		
		gameMain.spriteLattice.spriteLatticeAddBlood(num);
		
		if(!VeggiesData.isMuteSound())
		GameMedia.playSound(R.raw.slingshots, 0);
	}
	
	public void updata()
	{
		if(!showTime)
			return;
		
		latticeRestore.updataSprite();
		
		if(latticeRestore.state == TurnGameSprite.SPRITE_STATE_NONE)
		{
			showTime = false;
		}
	}
	
	public void paint(Canvas canvas)
	{
		if(!showTime)
			return;
		
		latticeRestore.paintSprite(canvas, 0, 0);
	}
}
