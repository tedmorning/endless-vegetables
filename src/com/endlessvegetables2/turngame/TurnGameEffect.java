package com.endlessvegetables2.turngame;

import java.util.ArrayList;
import java.util.HashMap;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class TurnGameEffect {
	
	public final static int FLOWER = 0;
	public final static int SMALLGOLDEN = 1;
	public final static int BOX = 2;
	
	private ArrayList<TurnGameSprite> effect;
	
	private ArrayList<Manually> manuallyEffect;
	
	public TurnGameSprite light;
	
	private TurnGameSprite smallGolden;
	
	private TurnGameSprite bigGolden;
	
	private TurnGameSprite box;
	
	private TurnGameSprite flower[];
	
	private TurnGameSprite numScore[];

	private char wordNumChars[];
	
	private Bitmap[] numScoreBit;
	
	private int effectLinkNumber;
	
	public TurnGameEffect()
	{
		effect = new ArrayList<TurnGameSprite>();		
		
		manuallyEffect = new ArrayList<Manually>();	
		
		effectLinkNumber = 1;
	}
	
	public void loadImage()
	{
		light = new TurnGameSprite(GameImage.getImage("newEffect/Effect_dust_bc"));
		
		smallGolden = new TurnGameSprite(GameImage.getImage("newEffect/glod_small_1"));
		
		bigGolden = new TurnGameSprite(GameImage.getImage("newEffect/glod_big"));
		
		box = new TurnGameSprite(GameImage.getImage("newEffect/Mini_2_bag1"));
		
		flower = new TurnGameSprite[6];
		
		for(int i=0;i<flower.length;i++)
		{
			flower[i] = new TurnGameSprite(GameImage.getImage("newEffect/s"+i));
		}		
		
		wordNumChars = new char[]{'0','1','2','3','4','5','6','7','8','9','x',':','-','.'};
		
		numScoreBit=GameImage.getAutoSizecutBitmap("word/number1", 14, 1, GameImage.Sort_line);
		
		numScore =new TurnGameSprite[numScoreBit.length];
		
		for(int i=0;i<numScoreBit.length;i++)
		{
			numScore[i]=new TurnGameSprite(numScoreBit[i]);
		}
			
//		TurnGameSpriteLibrary.loadTurnGameSpriteImage(CoolEditDefine.Effect_ICESTATELV1);
//		TurnGameSpriteLibrary.loadTurnGameSpriteImage(CoolEditDefine.Effect_ICESTATELV2);
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_ICESTATELV3);
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_DIZZINESS);
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_SLOWDOWN);
//		TurnGameSpriteLibrary.loadTurnGameSpriteImage(CoolEditDefine.Effect_EMPTYBOX);		
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_ATTACK2);
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_RESTORE);
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_GEM);
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_GOLDEN);		
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.SMALL_CARD_BOX);
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.SMALL_CARD);		
		TurnGameSpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_BOMB);		
	}
	
	public void delImage()
	{		
		GameImage.delImage(light.bitmap);
		
		GameImage.delImage(smallGolden.bitmap);
		
		GameImage.delImage(bigGolden.bitmap);
		
		GameImage.delImage(box.bitmap);
		
		for(int i=0;i<flower.length;i++)		
			GameImage.delImage(flower[i].bitmap);
		
		for(int i=0;i<numScore.length;i++)		
			GameImage.delImage(numScore[i].bitmap);
		
		light.recycleBitmap();
		
		smallGolden.recycleBitmap();
		
		bigGolden.recycleBitmap();
		
		box.recycleBitmap();
		
		for(int i=0;i<flower.length;i++)
		flower[i].recycleBitmap();
		
		for(int i=0;i<numScore.length;i++)
		numScore[i].recycleBitmap();	
		
//		TurnGameSpriteLibrary.DelTurnGameSpriteImage(CoolEditDefine.Effect_ICESTATELV1);
//		TurnGameSpriteLibrary.DelTurnGameSpriteImage(CoolEditDefine.Effect_ICESTATELV2);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_ICESTATELV3);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_DIZZINESS);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_SLOWDOWN);
//		TurnGameSpriteLibrary.DelTurnGameSpriteImage(CoolEditDefine.Effect_EMPTYBOX);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_ATTACK2);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_RESTORE);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_GEM);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_GOLDEN);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.SMALL_CARD_BOX);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.SMALL_CARD);
		TurnGameSpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_BOMB);
		GameImage.delImageArray(numScoreBit);
	}
	
	public void init()
	{
		
	}
	
	public ArrayList<TurnGameSprite> getList()
	{
		return effect;
	}
	
	public void setXY(int index, float x, float y)
	{
		effect.get(index).setXY((int)x, (int)y);
	}
	
	public void add(int kind, int center_x, int center_y, int num)
	{
		Manually manually = new Manually();
		
		manually.setKind(kind);
		
		if(kind == FLOWER)
		{
			manually.setFlower(center_x, center_y, 10, 10);					
		}
		else if(kind == SMALLGOLDEN||kind == BOX)
		{
			manually.setJump(center_x, center_y);	
			
			manually.setGoldenNum(num);
		}		
		
		manuallyEffect.add(manually);
	}
	
	public void add(TurnGameSprite enemy, int kind,int x,int y,int state,int showTime)
	{
		effectLinkNumber ++;
		
		TurnGameSprite sprite = new TurnGameSprite();
		
		sprite.initSprite(kind, x, y, state);
		
		sprite.changeAction(0);
		
		sprite.effectShowTime = showTime;
		
		if(kind == CoolEditDefine.Effect_SLOWDOWN)
		{
			enemy.speedDownLinkNumber = effectLinkNumber;
			sprite.speedDownLinkNumber = effectLinkNumber;
		}
		else if(kind == CoolEditDefine.Effect_DIZZINESS)
		{
			enemy.dizzinessLinkNumber = effectLinkNumber;
			sprite.dizzinessLinkNumber = effectLinkNumber;
		}
		else if(kind == CoolEditDefine.Effect_ICESTATELV1||
				kind == CoolEditDefine.Effect_ICESTATELV2||
				kind == CoolEditDefine.Effect_ICESTATELV3)
		{
			enemy.freezeLinkNumber = effectLinkNumber;
			sprite.freezeLinkNumber = effectLinkNumber;
		}		
		
		effect.add(sprite);				
	}
	
//	public void add(int kind,int x,int y,int state,int showTime)
//	{
//		
//		TurnGameSprite sprite = new TurnGameSprite();
//		
//		sprite.initTurnGameSprite(kind, x, y, state);
//		
//		sprite.changeAction(0);
//		
//		sprite.effectShowTime = showTime;
//		
//		effect.add(sprite);				
//	}
	
	public void add(int kind,int x,int y,int state,int showTime,float size)
	{
		TurnGameSprite sprite = new TurnGameSprite();
		
		sprite.initSprite(kind, x, y, state);
		
		sprite.changeAction(0);
		
		sprite.effectShowTime = showTime;
		
		sprite.size = size;
		
		effect.add(sprite);
	}
	
	public void updata()
	{
		for(int i=0;i<effect.size();i++)
		{
			effect.get(i).updataSprite();
			
			iceEffect(i);
			
//			summonEffect(i);
			
//			shieldEffect(i);
			
			slowDownEffect(i);
			
			dizzinessTimeEffect(i);
		}
		
		for(int i=0;i<effect.size();i++)
		{
			if(effect.get(i).state == TurnGameSprite.SPRITE_STATE_NONE)
				effect.remove(i);
		}	
		
		for(int i=0;i<manuallyEffect.size();i++)
		{
			if(manuallyEffect.get(i).getKind() == SMALLGOLDEN||
			   manuallyEffect.get(i).getKind() == BOX)
				manuallyEffect.get(i).updata();	
			
			if(manuallyEffect.get(i).getKind() == FLOWER)					
				manuallyEffect.get(i).flowerUpdata();		
		}
		
		for(int i=0;i<manuallyEffect.size();i++)
		{
			if(manuallyEffect.get(i).getKind() == SMALLGOLDEN||
			   manuallyEffect.get(i).getKind() == BOX)
			{
				if(manuallyEffect.get(i).end())
					manuallyEffect.remove(i);	
			}			
			else if(manuallyEffect.get(i).getKind() == FLOWER)
			{
				if(manuallyEffect.get(i).flowerEnd())
					manuallyEffect.remove(i);
			}
		}
	}
	
	public void paint(Canvas canvas, int id)
	{
		effect.get(id).paintSprite(canvas, 0, 0);
	}
	
	public void paint(Canvas canvas)
	{
		for(int i=0;i<effect.size();i++)
		{
			effect.get(i).paintSprite(canvas, 0, 0);
		}	
		
		for(int i=0;i<manuallyEffect.size();i++)
		{
			if(manuallyEffect.get(i).getKind() == SMALLGOLDEN)
			{				
				manuallyEffect.get(i).drawSmallGolden(canvas);
			}	
			else if(manuallyEffect.get(i).getKind() == BOX)
			{				
				manuallyEffect.get(i).drawBox(canvas);
			}	
			else if(manuallyEffect.get(i).getKind() == FLOWER)
			{
				manuallyEffect.get(i).drawFlower(canvas);
			}			
		}
	}
	
	private void iceEffect(int i)
	{
		if(effect.get(i).kind==CoolEditDefine.Effect_ICESTATELV1||
		   effect.get(i).kind==CoolEditDefine.Effect_ICESTATELV2||
		   effect.get(i).kind==CoolEditDefine.Effect_ICESTATELV3)
		{
			if(effect.get(i).effectShowTime>0)
			{
				effect.get(i).effectShowTime--;
				
				if(effect.get(i).effectShowTime==0)
				   effect.get(i).changeAction(1);
			}
		}
		
//		if(effect.get(i).kind==TurnGameSpriteLibrary.Effect_ICEATTACK)
//		{
//			if(effect.get(i).state == TurnGameSprite.SPRITE_STATE_NONE)
//			{
//				add(TurnGameSpriteLibrary.Effect_ICESTATE, (int)effect.get(i).x, (int)effect.get(i).y, TurnGameSprite.SPRITE_STATE_NORMAL, effect.get(i).effectShowTime);
//			}				
//		}
//		else if(effect.get(i).kind==TurnGameSpriteLibrary.Effect_ICESTATE)
//		{				
//			if(effect.get(i).effectShowTime>0)
//			{
//				effect.get(i).effectShowTime--;
//				
//				if(effect.get(i).effectShowTime==0)
//				   effect.get(i).changeAction(1);
//			}				
//		}
	}
	
//	private void summonEffect(int i)
//	{
//		if(effect.get(i).kind==TurnGameSpriteLibrary.Effect_SUMMON)
//		{				
//			if(effect.get(i).effectShowTime>0)
//			{
//				effect.get(i).effectShowTime--;
//				
//				if(effect.get(i).effectShowTime==0)
//				   effect.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);
//			}				
//		}
//	}
	
//	private void shieldEffect(int i)
//	{
//		if(effect.get(i).kind==TurnGameSpriteLibrary.Effect_SHIELD)
//		{				
//			if(effect.get(i).effectShowTime>0)
//			{
//				effect.get(i).effectShowTime --;
//				
//				if(effect.get(i).effectShowTime==0)
//				   effect.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);
//			}				
//		}
//	}		
	
	private void slowDownEffect(int i)
	{
		if(effect.get(i).kind==CoolEditDefine.Effect_SLOWDOWN)
		{				
			if(effect.get(i).effectShowTime>0)
			{
				effect.get(i).effectShowTime--;
				
				if(effect.get(i).effectShowTime==0)
				   effect.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);
			}
		}
	}
	
	private void dizzinessTimeEffect(int i)
	{
		if(effect.get(i).kind==CoolEditDefine.Effect_DIZZINESS)
		{
			if(TurnGameMain.turn==5&&TurnGameMain.turntime==1){
				if(effect.get(i).effectShowTime>0)
				{
					effect.get(i).effectShowTime--;
					
					if(effect.get(i).effectShowTime==0)
					   effect.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);
				}	
			}	
		}
	}
	
//	public void closeEffect(int kind, int x, int y)
//	{
//		for(int i=0;i<effect.size();i++)
//		{
//			if(effect.get(i).kind==kind&&
//			   effect.get(i).x==x&&effect.get(i).y==y)
//			{
//				effect.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);
//			}						
//		}					
//	}	
	
	public void closeEffect(int kind, int _linkNumber)
	{				
		for(int i=0;i<effect.size();i++)
		{
			if(effect.get(i).kind == kind)
			{
				if(kind == CoolEditDefine.Effect_SLOWDOWN)
				{					
					if(effect.get(i).speedDownLinkNumber == _linkNumber)
					{
						effect.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);
						
						break;
					}
				}
				else if(kind == CoolEditDefine.Effect_DIZZINESS)
				{					
					if(effect.get(i).dizzinessLinkNumber == _linkNumber)
					{
						effect.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);
						
						break;
					}
				}
				else if(kind == CoolEditDefine.Effect_ICESTATELV1||
						kind == CoolEditDefine.Effect_ICESTATELV2||
						kind == CoolEditDefine.Effect_ICESTATELV3)
				{					
					if(effect.get(i).freezeLinkNumber == _linkNumber)
					{
						effect.get(i).setState(TurnGameSprite.SPRITE_STATE_NONE);
						
						break;
					}
				}		
			}			
		}					
	}	
	
	public void setEffectXY(int kind, int _linkNumber, int x, int y)
	{
		for(int i=0;i<effect.size();i++)
		{
			if(kind == CoolEditDefine.Effect_SLOWDOWN)
			{					
				if(effect.get(i).speedDownLinkNumber == _linkNumber)
				{
					effect.get(i).setXY(x, y);
					
					break;
				}
			}
			else if(kind == CoolEditDefine.Effect_DIZZINESS)
			{					
				if(effect.get(i).dizzinessLinkNumber == _linkNumber)
				{
					effect.get(i).setXY(x, y);
					
					break;
				}
			}
			else if(kind == CoolEditDefine.Effect_ICESTATELV1||
					kind == CoolEditDefine.Effect_ICESTATELV2||
					kind == CoolEditDefine.Effect_ICESTATELV3)
			{					
				if(effect.get(i).freezeLinkNumber == _linkNumber)
				{
					effect.get(i).setXY(x, y);
					
					break;
				}
			}	
		}					
	}	
	
	class Manually
	{
		private int kind;
		
		private int lightCenterX;
		
		private int lightCenterY;
		
		private int lightAgree;
		
		private int lightUp;
		
		private int jumpId;
		
		private int jumpX;
		
		private int jumpY;
		
		private byte jumpXOffsetDirect;
		
		private int jumpXOffset;
		
		private byte step;
		
		private byte showTime;				
		
		private final int jumpPoint[] = {6,9,12,15,12,9,6,
				                   0,4,6,8,12,8,6,4,
				                   0,2,4,6,4,2,0,1,2,1,0};
					

		private int goldenNum;				
		
		public void setGoldenNum(int num)
		{
			goldenNum = num;
		}
		
		public void setKind(int id)
		{
			kind = id;
		}
		
		public int getKind()
		{
			return kind;
		}
		
		public void updata()
		{						
			if(step==0)
			{
				if(jumpId<jumpPoint.length-1)
				{
					jumpId ++;
					
					if(jumpXOffsetDirect==0)
						jumpXOffset ++;
					else 
						jumpXOffset --;
				}
				else
					step = 1;
			}
			else if(step==1)
			{	
				if(kind == SMALLGOLDEN)
				{
					setLightCenterPoint(jumpX+jumpXOffset, jumpY);
					
					step = 2;
				}
				else if(kind == BOX)
				{
//					add(CoolEditDefine.Effect_EMPTYBOX, jumpX+jumpXOffset, jumpY+box.bitmap.getHeight()/2, TurnGameSprite.SPRITE_STATE_NORMAL, 0, 1f);
					
					step = 3;
				}														
			}			
			else if(step==2)
			{
				showTime --;
				
				lightUp += 2;
				
				if(showTime==0)				
					step = 3;
			
				lightAgree += 6;
			}
		}
		
		public void setLightCenterPoint(int center_x, int center_y)
		{
			lightCenterX = center_x;
			
			lightCenterY = center_y;
			
			lightAgree = 0;
			
			showTime = 15;
			
			lightUp = 0;
		}
		
		private Paint paint = new Paint();
		
		private Matrix matrix = new Matrix();
		
		private void drawLight(Canvas canvas)
		{				
//			for(int i=0;i<6;i++)
//			ExternalMethods.drawImage(canvas, light, lightCenterX-light.getWidth()/2, lightCenterY-light.getHeight()-lightUp, 1f, 1f, 255, 60*i+lightAgree, light.getWidth()/2, light.getHeight());
			
//			Paint paint = new Paint();
//			
//			Matrix matrix = new Matrix();
			
			for(int i=0;i<6;i++)
			{			
				matrix.reset();
				
				matrix.postTranslate(lightCenterX-light.bitmap.getWidth()/2, lightCenterY-light.bitmap.getHeight()-lightUp);
				
				matrix.postRotate(60*i+lightAgree, lightCenterX,  lightCenterY-lightUp);
				
				light.drawBitmap(canvas, light.bitmap, matrix, paint);
			}			
		}
		
		public void drawGetSmallGolden(Canvas canvas)
		{
			Paint paint = new Paint();
			
			drawLight(canvas);
			
			smallGolden.drawBitmap(canvas, smallGolden.bitmap, lightCenterX-smallGolden.bitmap.getWidth()/2, lightCenterY-smallGolden.bitmap.getHeight()/2-lightUp, paint);
			
			GameLibrary.DrawNumber(canvas, numScore, lightCenterX, lightCenterY+50*GameConfig.f_zoom-lightUp, wordNumChars, goldenNum+"", paint, GameLibrary.VH, 0);
		}
		
		public void drawGetBigGolden(Canvas canvas)
		{
			Paint paint = new Paint();
			
			drawLight(canvas);
			
			bigGolden.drawBitmap(canvas, bigGolden.bitmap, lightCenterX-bigGolden.bitmap.getWidth()/2, lightCenterY-bigGolden.bitmap.getHeight()/2-lightUp, paint);					
		}
		
		public void drawGetBox(Canvas canvas)
		{
			Paint paint = new Paint();
			
			drawLight(canvas);
			
			box.drawBitmap(canvas, box.bitmap, lightCenterX-box.bitmap.getWidth()/2, lightCenterY-box.bitmap.getHeight()/2-lightUp, paint);					
		}
				
		public void setJump(int jump_x, int jump_y)
		{
			jumpId = 0;
			
			jumpXOffset = 0;
			
			jumpX =	jump_x;
			
			jumpY =	jump_y;
			
			jumpXOffsetDirect = (byte)ExternalMethods.throwDice(0, 1);
			
			step = 0;
		}
		
		private void jump(Canvas canvas, TurnGameSprite bm)
		{
			Paint paint = new Paint();
			
			bm.drawBitmap(canvas, bm.bitmap, jumpX-bm.bitmap.getWidth()/2+jumpXOffset, jumpY-bm.bitmap.getHeight()/2-jumpPoint[jumpId], paint);		
		}
		
		public void drawSmallGoldenJump(Canvas canvas)
		{
			jump(canvas, smallGolden);
		}		
		
		public void drawBoxJump(Canvas canvas)
		{
			jump(canvas, box);
		}	
		
		public void drawSmallGolden(Canvas canvas)
		{
			if(step==0||step==1)			
				drawSmallGoldenJump(canvas);			
			else if(step==2)			
				drawGetSmallGolden(canvas);			
		}
		
		public void drawBox(Canvas canvas)
		{
			if(step==0||step==1)			
				drawBoxJump(canvas);						
		}
		
		public boolean end()
		{
			if(step==3)
				return true;
			else
				return false;
		}
	
		private ArrayList<HashMap<String, String>> parabola[];
		
		private Bitmap[] bm; 
		
		private int[] rotateAngle; 
		
		private int flowerCenterX;
		
		private int flowerCenterY;
		
		private int[] flowerStep;
		
		private float[] flowerSize;
		
		public void setFlower(int center_x , int center_y, int num, int len)
		{			
			parabola = new ArrayList[num];
			
			flowerCenterX = center_x;
			
			flowerCenterY = center_y;
			
			bm = new Bitmap[num];
			
			rotateAngle = new int[num];
			
			flowerStep = new int[num];
			
			flowerSize = new float[num];
			
			for(int i=0;i<num;i++)
			{
				int angle = ExternalMethods.throwDice(0, 360);
				
				int move = 0; 
				
				bm[i] = flower[ExternalMethods.throwDice(0, flower.length-1)].bitmap;
				
				rotateAngle[i] = ExternalMethods.throwDice(10, 360);
				
				parabola[i] = new ArrayList<HashMap<String, String>>();
				
				flowerStep[i] = 0;
				
				flowerSize[i] = GameLibrary.getFloatRandom(1.0f, 2.5f);
				
				for(int j=0;j<len;j++)
				{				
					HashMap<String, String> temp = new HashMap<String, String>();
					
					temp.put("x", (int)ExternalMethods.getAngleX(angle, move)+"");
					temp.put("y", (int)ExternalMethods.getAngleY(angle, move)+"");	
					
					parabola[i].add(temp);
					
					move += 10;
				}
			}
		}
		
		public void flowerUpdata()
		{
			for(int i=0;i<parabola.length;i++)
			{						
				if(flowerStep[i]<parabola[i].size())
					flowerStep[i] ++;
				
				rotateAngle[i] += 20;				
			}	
		}
		
		public void drawFlower(Canvas canvas)
		{			
			for(int i=0;i<parabola.length;i++)
			{						
//				if(flowerStep[i]<parabola[i].size())
//					flowerStep[i] ++;
//				
//				rotateAngle[i] += 20;
				
				if(flowerStep[i]<parabola[i].size())
				ExternalMethods.drawImage(canvas, bm[i], flowerCenterX-bm[i].getWidth()/2+Integer.parseInt(parabola[i].get(flowerStep[i]).get("x")), flowerCenterY-bm[i].getHeight()+Integer.parseInt(parabola[i].get(flowerStep[i]).get("y")), flowerSize[i], flowerSize[i], 255, rotateAngle[i], bm[i].getWidth()/2, bm[i].getHeight()/2);				
			}			
		}	
		
		public boolean flowerEnd()
		{
			for(int i=0;i<parabola.length;i++)
			{		
				for(int j=0;j<parabola[i].size();j++)
				{		
					if(flowerStep[i]==parabola[i].size())
						return true;
				}
			}
			
			return false;
		}					
	}
}
