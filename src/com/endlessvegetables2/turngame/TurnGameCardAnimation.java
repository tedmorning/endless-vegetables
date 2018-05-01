package com.endlessvegetables2.turngame;

import com.endlessvegetables2.ui.GameItem;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Canvas;

public class TurnGameCardAnimation {
	
	private TurnGameSprite box;
	
	private TurnGameSprite card;
	
	private TurnGameSprite light;
	
	private boolean boxMoveState;
	
	private boolean state;
	
	private int type;		
	
	private int jumpIndex;
	
	private byte jumpDirect;

	private int lightAgreeWaiting;
	
	private int lightAgree;
	
	private float size;
	
	private final int JumpH[] = {60,50,40,30,20,10,0,
								8,16,24,32,40,
								48,38,28,18,8,
								0,6,12,18,24,30,
								36,24,12,4,
								 0,3,6,12,6,3,0,3,6,3,
								 0};
	
	public TurnGameCardAnimation()
	{
	}
	
	public void init()
	{
	}
	
	public void setGameCardAnimation(TurnGameMain gameMain, TurnGameSprite sprite, TurnGameSprite _light)
	{	
//		if(gameMain.getMogo)
//		{
//			type = GameItem.Item04;
//			
//			gameMain.getMogo = false;
//		}
//		else
//		{
			//type以GameItem类中的卡片ID相对应
			int rmd = ExternalMethods.throwDice(0, 20);
							
			if(TurnGameSpriteLibrary.GetCardsPercent(sprite.kind)==2)
				type = rmd*3+3;
			else if(TurnGameSpriteLibrary.GetCardsPercent(sprite.kind)==1)
				type = rmd*3+2;
			else 
				type = rmd*3+1;
			
			if(type==GameItem.Item01)//游戏中不掉落一星番茄卡
			type = GameItem.Item02;
//		}
		
		gameMain.getCard.add(type);
		
		VeggiesData.isVegetablesRepeat(type);
		
		int cardx = (int)sprite.x;
		int cardy = (int)sprite.y;
		
		card = new TurnGameSprite();
		
		card.initSprite(CoolEditDefine.SMALL_CARD, cardx, cardy, TurnGameSprite.SPRITE_STATE_NORMAL);						
		
		card.changeAction(0);
						
		int boxx = GameConfig.GameScreen_Width+TurnGameSpriteLibrary.GetW(CoolEditDefine.SMALL_CARD_BOX)/2;
		int boxy = (int)(160*GameConfig.f_zoomy);
		
		box = new TurnGameSprite();
		
		box.initSprite(CoolEditDefine.SMALL_CARD_BOX, boxx, boxy, TurnGameSprite.SPRITE_STATE_NORMAL);						
		
		box.changeAction(0);
		
		light = new TurnGameSprite(_light.bitmap);
		
		state = true;
			
		boxMoveState = true;
		
		gameMain.getCardNumber ++;	
		
		size = 0.5f;
		
		if(sprite.x>=GameConfig.GameScreen_Width/2)
			jumpDirect = 0;
		else
			jumpDirect = 1;
		
		lightAgree = 0;
		
		lightAgreeWaiting = 0;
		
		jumpIndex = 0;
	}
	
	public void paint(Canvas canvas)
	{		
		if(state)
		{		
			if(boxMoveState)
			{
				for(int i=0;i<6;i++)
				{			
					light.drawBitmap(canvas, card.x-light.bitmap.getWidth()*size/2, card.y-light.bitmap.getHeight()*size, size, size, 255, 60*i+lightAgree, light.bitmap.getWidth()*size/2, light.bitmap.getHeight()*size, 0, 0, 0);
				}	
			}
			
			card.paintSprite(canvas, 0, 0);
			
			box.paintSprite(canvas, 0, 0);
		}
	}
	
	public void updata()
	{
		if(state)
		{	
			box.updataSprite();
			
			card.updataSprite();
			
			jumpIndex ++;
			
			if(jumpDirect==0)
				card.x --;
			else
				card.x ++;
									
			lightAgreeWaiting ++;
			
			if(lightAgreeWaiting>4)
			{
				lightAgree += 30;								
				
				lightAgreeWaiting = 0;
			}
					
			if(jumpIndex>=JumpH.length-1)
			{
				jumpIndex = JumpH.length-1;							
			}
			else 
			{
				card.y = card.org_y-JumpH[jumpIndex];
				
				return;
			}
							
			if(boxMoveState)
			{
				box.x -= 16;
				
				if(box.x<=GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(CoolEditDefine.SMALL_CARD_BOX)/2)
				{
					box.x = GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(CoolEditDefine.SMALL_CARD_BOX)/2;
					
					card.x += 24;
					card.y -= 24;
					
					card.Alpha -= 8;
					card.size -= 0.02f;
					
					if(card.Alpha <= 40)
					card.Alpha = 40;
					
					if(card.size <= 0.4f)
					card.size = 0.4f;
					
					if(card.x>=box.x)
					{
						card.x = box.x;
					}
					
					if(card.y<=box.y)
					{
						card.y = box.y;
					}
					
					if(card.x == box.x&&card.y == box.y)
					{						
						card.setState(TurnGameSprite.SPRITE_STATE_NONE);
						
						box.changeAction(1);
						
						boxMoveState = false;
					}
				}								
			}
			else
			{
				if(box.getActionName()==0)
				{
					box.x += 16;
					
					if(box.x>=GameConfig.GameScreen_Width+TurnGameSpriteLibrary.GetW(CoolEditDefine.SMALL_CARD_BOX)/2)
					{
						box.x = GameConfig.GameScreen_Width+TurnGameSpriteLibrary.GetW(CoolEditDefine.SMALL_CARD_BOX)/2;
						
						state = false;
					}
				}
			}						
		}
	}
	
	public boolean getState()
	{
		return state;
	}
}

