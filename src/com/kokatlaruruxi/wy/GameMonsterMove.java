package com.kokatlaruruxi.wy;

import com.socoGameEngine.GameConfig;

public class GameMonsterMove {
	
//	public static final int MOVE_1 = 1;
//	public static final int MOVE_2 = 2;
//	public static final int MOVE_3 = 3;
	
	/*
	 * �����ƶ�
	 * */
	public byte TransverseMove(Sprite sprite)
	{		
		if(sprite.byMoveDirect == -1)
		{
			sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 1);
		}
		else if(sprite.byMoveDirect == 0)//��������
		{
			sprite.x += sprite.byMoveSpeed;
			
			if(sprite.x>GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2)
			{
				sprite.x = GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2;
				
				sprite.byMoveDirect = 1;
			}
		}
		else if(sprite.byMoveDirect == 1)//��������
		{
			sprite.x -= sprite.byMoveSpeed;
			
			if(sprite.x<=SpriteLibrary.GetW(sprite.kind)/2)
			{
				sprite.x = SpriteLibrary.GetW(sprite.kind)/2;
				
				sprite.byMoveDirect = 0;
			}
		}
		
		return sprite.byMoveDirect;
	}
	
	/*
	 * ������
	 * */
	public static void Jump(Sprite sprite, boolean LtoR)
	{			
		if(LtoR)
			sprite.x += sprite.byMoveSpeed;
		else 
			sprite.x -= sprite.byMoveSpeed;
		
		if(sprite.x>GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2)
		{
			sprite.x = GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2;						
		}
		
		if(sprite.x<=SpriteLibrary.GetW(sprite.kind)/2)
		{
			sprite.x = SpriteLibrary.GetW(sprite.kind)/2;						
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
	 * ���������ƶ�
	 * */
	public boolean LToRMove(Sprite sprite)
	{
		boolean isStop = false;
		
		sprite.x += sprite.byMoveSpeed;
		
		if(sprite.x>=GameConfig.GameScreen_Width+SpriteLibrary.GetW(sprite.kind))
		{
			sprite.x = GameConfig.GameScreen_Width+SpriteLibrary.GetW(sprite.kind);
			
			isStop = true;
		}
		
		return isStop;
	}
	
	/*
	 * ���������ƶ�
	 * */
	public boolean RToLMove(Sprite sprite)
	{
		boolean isStop = false;
		
		sprite.x -= sprite.byMoveSpeed;
		
		if(sprite.x<=-SpriteLibrary.GetW(sprite.kind))
		{
			sprite.x = -SpriteLibrary.GetW(sprite.kind);
			
			isStop = true;
		}
		
		return isStop;
	}
	
	/*
	 * ���������ƶ�
	 * */
	public boolean LongitudinalMove(Sprite sprite)
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
	 * ��ɢ�ƶ�
	 * */
	public boolean radiationMove(Sprite sprite, int degree)
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
	 * �ڹ涨��Χ���ƶ�
	 * */
	public void rangeMove(Sprite sprite, int leftSide, int rightSide, int topSide, int bottomSide)
	{
		if(sprite.byMoveDirect == -1)
		{
			sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
		}
		else if(sprite.byMoveDirect == 0)//��������
		{
			if(sprite.speedDownTime%2==0)
			sprite.x += sprite.byMoveSpeed;
			
			if(sprite.x>=(sprite.org_x+rightSide>GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2?GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2:sprite.org_x+rightSide))
			{
				sprite.x = (sprite.org_x+rightSide>GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2?GameConfig.GameScreen_Width-SpriteLibrary.GetW(sprite.kind)/2:sprite.org_x+rightSide);
				
				sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
			}
		}
		else if(sprite.byMoveDirect == 1)//��������
		{
			if(sprite.speedDownTime%2==0)
			sprite.x -= sprite.byMoveSpeed;
			
			if(sprite.x<=(sprite.org_x-leftSide<SpriteLibrary.GetW(sprite.kind)/2?SpriteLibrary.GetW(sprite.kind)/2:sprite.org_x-leftSide))
			{
				sprite.x = (sprite.org_x-leftSide<SpriteLibrary.GetW(sprite.kind)/2?SpriteLibrary.GetW(sprite.kind)/2:sprite.org_x-leftSide);
				
				sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
			}
		}
		else if(sprite.byMoveDirect == 2)//��������
		{
			if(sprite.speedDownTime%2==0)
			sprite.y += sprite.byMoveSpeed;
			
			if(sprite.y>=sprite.org_y+bottomSide)
			{
				sprite.y = sprite.org_y+bottomSide;
				
				sprite.byMoveDirect = (byte)ExternalMethods.throwDice(0, 3);
			}
		}
		else if(sprite.byMoveDirect == 3)//��������
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
	 * �����ƶ�
	 * */
	public void loopMove(Sprite sprite, int center_x, int center_y)
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
