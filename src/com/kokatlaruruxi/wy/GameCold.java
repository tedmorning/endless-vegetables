package com.kokatlaruruxi.wy;
//package com.endlessvegetables2.android;
//
//import android.graphics.Canvas;
//
//import com.socoGameEngine.GameConfig;
//
//public class GameCold {
//	private Sprite cold;
//	
//	private int showTime;
//	
//	private boolean isShow;
//	
//	public GameCold()
//	{
//		showTime = 0;
//		
//		isShow = false;
//	}
//	
//	public boolean getState()
//	{
//		return isShow;
//	}
//	
//	public void setCold()
//	{		
//		cold = new Sprite();
//		cold.initSprite(SpriteLibrary.Effect_COLD, GameConfig.GameScreen_Width/2, 0, Sprite.SPRITE_STATE_NORMAL);
//		cold.changeAction(0);
//		
//		showTime = 50;
//		
//		isShow = true;
//	}
//	
//	public int getShowTime()
//	{
//		return showTime-10;
//	}
//	
//	public void updata()
//	{
//		if(cold!=null)
//		cold.updataSprite();
//		
//		if(!isShow)
//			return;
//			
//		showTime --;
//		
//		if(showTime<=0)
//		{
//			isShow = false;
//			
//			cold.changeAction(1);
//		}				
//	}
//	
//	public void paint(Canvas canvas)
//	{
//		if(cold!=null)
//		cold.paintSprite(canvas, 0, 0);
//	}
//}
