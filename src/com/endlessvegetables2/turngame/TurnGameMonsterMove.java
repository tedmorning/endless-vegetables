package com.endlessvegetables2.turngame;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;

public class TurnGameMonsterMove {
	
//	public static final int MOVE_1 = 1;
//	public static final int MOVE_2 = 2;
//	public static final int MOVE_3 = 3;
	
	/*
	 * 横向移动
	 * */
	public byte TransverseMove(TurnGameSprite sprite)
	{		
		if(sprite.byMoveDirect == -1)
		{
			sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 1);
		}
		else if(sprite.byMoveDirect == 0)//从左往右
		{
			sprite.x += sprite.byMoveSpeed;
			
			if(sprite.x>GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind)/2)
			{
				sprite.x = GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind)/2;
				
				sprite.byMoveDirect = 1;
			}
		}
		else if(sprite.byMoveDirect == 1)//从右往左
		{
			sprite.x -= sprite.byMoveSpeed;
			
			if(sprite.x<=TurnGameSpriteLibrary.GetW(sprite.kind)/2)
			{
				sprite.x = TurnGameSpriteLibrary.GetW(sprite.kind)/2;
				
				sprite.byMoveDirect = 0;
			}
		}
		
		return sprite.byMoveDirect;
	}
	
	/*
	 * 青蛙跳
	 * */
	public static void Jump(TurnGameSprite sprite, boolean LtoR)
	{			
		if(LtoR)
			sprite.x += sprite.byMoveSpeed;
		else 
			sprite.x -= sprite.byMoveSpeed;
		
		if(sprite.x>GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind)/2)
		{
			sprite.x = GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind)/2;						
		}
		
		if(sprite.x<=TurnGameSpriteLibrary.GetW(sprite.kind)/2)
		{
			sprite.x = TurnGameSpriteLibrary.GetW(sprite.kind)/2;						
		}
		
		if(sprite.frames==1)
			sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 1);
		
		if(sprite.frames>=1)
		{
			if(sprite.byMoveDirect==0)
				sprite.y -= sprite.byMoveSpeed;
			else 
				sprite.y += sprite.byMoveSpeed;
		}	
		
		if(sprite.y>=sprite.byMoveStop)
		{
			sprite.y = sprite.byMoveStop;						
		}
		
		if(sprite.y<=200*GameConfig.f_zoomy)
		{
			sprite.y = 200*GameConfig.f_zoomy;						
		}
	}		
	
	/*
	 * 从左往右移动
	 * */
	public boolean LToRMove(TurnGameSprite sprite)
	{
		boolean isStop = false;
		
		sprite.x += sprite.byMoveSpeed;
		
		if(sprite.x>=GameConfig.GameScreen_Width+TurnGameSpriteLibrary.GetW(sprite.kind))
		{
			sprite.x = GameConfig.GameScreen_Width+TurnGameSpriteLibrary.GetW(sprite.kind);
			
			isStop = true;
		}
		
		return isStop;
	}
	
	/*
	 * 从右往左移动
	 * */
	public boolean RToLMove(TurnGameSprite sprite)
	{
		boolean isStop = false;
		
		sprite.x -= sprite.byMoveSpeed;
		
		if(sprite.x<=-TurnGameSpriteLibrary.GetW(sprite.kind))
		{
			sprite.x = -TurnGameSpriteLibrary.GetW(sprite.kind);
			
			isStop = true;
		}
		
		return isStop;
	}
	
	/*
	 * 从上往下移动
	 * */
	public boolean LongitudinalMove(TurnGameSprite sprite)
	{
		boolean isStop = false;
		
		if(sprite.speedDownTime%2==0)
		sprite.y += sprite.byMoveSpeed;
		
		if(sprite.y>=sprite.byMoveStop)
		{
			sprite.y = sprite.byMoveStop;
			
			isStop = true;
		}			
		
		return isStop;
	}
	
	/*
	 * 发散移动
	 * */
	public boolean radiationMove(TurnGameSprite sprite, int degree)
	{
		boolean isStop = false;
		
		if(sprite.speedDownTime%2==0)
		{			
			sprite.byMoveDegree = degree;							
					
			sprite.x += (int)ExternalMethods.getAngleX(sprite.byMoveDegree, sprite.byMoveSpeed);
			sprite.y += (int)ExternalMethods.getAngleY(sprite.byMoveDegree, sprite.byMoveSpeed);
		}
		
		if(sprite.y>=sprite.byMoveStop)
		{
			sprite.y = sprite.byMoveStop;
			
			isStop = true;
		}			
		
		return isStop;
	}
	
	
	
	/*
	 * 在规定范围内移动
	 * */
	public void rangeMove(TurnGameSprite sprite, int leftSide, int rightSide, int topSide, int bottomSide)
	{
		if(sprite.byMoveDirect == -1)
		{
			sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
		}
		else if(sprite.byMoveDirect == 0)//从左往右
		{
			if(sprite.speedDownTime%2==0)
			sprite.x += sprite.byMoveSpeed;
			
			if(sprite.x>=(sprite.org_x+rightSide>GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind)/2?GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind)/2:sprite.org_x+rightSide))
			{
				sprite.x = (sprite.org_x+rightSide>GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind)/2?GameConfig.GameScreen_Width-TurnGameSpriteLibrary.GetW(sprite.kind)/2:sprite.org_x+rightSide);
				
				sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
			}
		}
		else if(sprite.byMoveDirect == 1)//从右往左
		{
			if(sprite.speedDownTime%2==0)
			sprite.x -= sprite.byMoveSpeed;
			
			if(sprite.x<=(sprite.org_x-leftSide<TurnGameSpriteLibrary.GetW(sprite.kind)/2?TurnGameSpriteLibrary.GetW(sprite.kind)/2:sprite.org_x-leftSide))
			{
				sprite.x = (sprite.org_x-leftSide<TurnGameSpriteLibrary.GetW(sprite.kind)/2?TurnGameSpriteLibrary.GetW(sprite.kind)/2:sprite.org_x-leftSide);
				
				sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
			}
		}
		else if(sprite.byMoveDirect == 2)//从上往下
		{
			if(sprite.speedDownTime%2==0)
			sprite.y += sprite.byMoveSpeed;
			
			if(sprite.y>=sprite.org_y+bottomSide)
			{
				sprite.y = sprite.org_y+bottomSide;
				
				sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
			}
		}
		else if(sprite.byMoveDirect == 3)//从下往上
		{
			if(sprite.speedDownTime%2==0)
			sprite.y -= sprite.byMoveSpeed;
			
			if(sprite.y<=sprite.org_y-topSide)
			{
				sprite.y = sprite.org_y-topSide;
				
				sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
			}
		}
	}
	
	/*
	 * 环形移动
	 * */
	public void loopMove(TurnGameSprite sprite, int center_x, int center_y)
	{
		sprite.byMoveSpeed += 5;
		
		if(sprite.byMoveSpeed>360)
			sprite.byMoveSpeed = 0;
		
		sprite.x = ExternalMethods.getAngleX(sprite.byMoveSpeed, 80*GameConfig.f_zoom);
		sprite.y = ExternalMethods.getAngleY(sprite.byMoveSpeed, 80*GameConfig.f_zoom);
		
		sprite.x += center_x;
		sprite.y += center_y;
	}
}
