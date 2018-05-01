package com.endlessvegetables2.turngame;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.endlessvegetables2.ui.Achievement;
import com.endlessvegetables2.ui.GameItem;
import com.endlessvegetables2.ui.PauseModule;
import com.endlessvegetables2.ui.VeggiesData;
import com.facebook.FacebookOperation;
import com.facebook.FriendIcon;
import com.facebook.UserRequest;
import com.game.data.FaceBookPlayer;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class TurnGameUI{

	public static final byte ITEM_TYPE_0 = 0; //无道具
	public static final byte ITEM_TYPE_1 = 1; //轰炸飞艇
	public static final byte ITEM_TYPE_2 = 2; //冰雹
	public static final byte ITEM_TYPE_3 = 3; //爆破弹
	public static final byte ITEM_TYPE_5 = 4; //乱舞
	public static final byte ITEM_TYPE_6 = 5; //觉醒药
	public static final byte ITEM_TYPE_7 = 6; //恢复药
	
	private Matrix matrix;
	
	private Paint paint;
	
	private int goldNumber;
	
	private int gemNumber;
	
	private TurnGameSprite uiProgress1;
	
	private TurnGameSprite uiProgress2;
	
	private TurnGameSprite uiProgress3;
	
	private TurnGameSprite butPasue;
	
	public TurnGameSprite userHelp;
	
	public TurnGameSprite icon1;
	
	private TurnGameSprite icon2;
	
	private TurnGameSprite icon11;
	
	private TurnGameSprite icon21;
	
	private TurnGameSprite goldGame;
	
	private TurnGameSprite starGame;
	
	private TurnGameSprite stoneGame;
	
	private TurnGameSprite numScore[];
	
	private TurnGameSprite itemNum[];
	
	private TurnGameSprite itemNum1[];
	
	private TurnGameSprite userIcon;
	
	private char wordNumChars[];		
	
	private char itemNumChars[];
	
	private ArrayList<Item> items;
	
	private int gameTimeMax;
	
	private int gameTime;
	
	private String minute;
	
	private String second;
		
	private int StageLengthMax;
	
	private int StageLength;
	
	private boolean pauseKeyDown;
	
	private Bitmap[] itemNumBit;
	
	private Bitmap[] itemNum1Bit;
	
	private Bitmap[] numScoreBit;
	
	public float[] uiPls;
	
	private FaceBookPlayer player;
	
	private int userIconAlpha;
	
	public TurnGameUI()
	{
		pauseKeyDown = false;
		
		player = null;
		
		paint = new Paint();
	}
	
	public void setStageLength(int len)
	{
		StageLengthMax = len;
		
		StageLength = StageLengthMax;
	}
	
	private void changeStageLength(int len)
	{		
		StageLength = len;
	}
	
	public void setGameTime(int t)
	{
		gameTimeMax = 25*t;
		
		gameTime = gameTimeMax;
		
		minute = getMinute(gameTime);
		
		second = getSecond(gameTime);
	}
	
	/*
	 * 获得游戏已经进行的时间
	 * */
	public int getGameLostTime()
	{
		return (gameTimeMax-gameTime)/25;
	}
	
	public int getCurrentGameTime()
	{
		if(gameTime<=0)
			gameTime = 0;
		
		return gameTime;
	}
	
	public void loadImage()
	{
		uiProgress1 = new TurnGameSprite(GameImage.getImage("newui/ui_progress_01"));
		
		uiProgress2 = new TurnGameSprite(GameImage.getImage("newui/ui_progress_02"));
		
		uiProgress3 = new TurnGameSprite(GameImage.getImage("newui/progress_3"));
		
		butPasue = new TurnGameSprite(GameImage.getImage("newui/ui_pause_01"));
		
//		userHelp = new Sprite(GameImage.getImage("newui/icon_photo_01"));
				
		userIcon = new TurnGameSprite();							
		
		userIconAlpha = -1;
		
		float imageWidth = 62*GameConfig.f_zoomx;		
		float imageHeight = 63*GameConfig.f_zoomy;
		
		if(UserRequest.getUser().getFriendIconID()!=null)
		{
			if(UserRequest.getUser().getFriendIconID().equals(""))
			{
				//没有选择,没有头像
				userIcon.bitmap = null;
				
				userHelp = new TurnGameSprite(GameImage.getImage("newui/icon_photo_01"));
			}
			else
			{
				//选择facebook的头像		
				player = FacebookOperation.playerMap.get(UserRequest.getUser().getFriendIconID());
				
				if(player.getIcon()==null)
				{
					//没有从网络拿到用户头像
					userIcon.bitmap = GameImage.zoomImage(GameImage.getImage("share/ui_photo_02"), imageWidth, imageHeight);
					
					userIconAlpha = 0;
					
					userHelp = new TurnGameSprite(GameImage.getImage("newui/icon_photo_02"));
				}
				else
				{
					//已经从网络拿到用户头像
					userIcon.bitmap = GameImage.zoomImage(player.getIcon(), imageWidth, imageHeight);
					
					userIconAlpha = 0;
					
					userHelp = new TurnGameSprite(GameImage.getImage("newui/icon_photo_02"));
				}
			}
		}
		else
		{
			//系统头像
			userIcon.bitmap = GameImage.zoomImage(GameImage.getImage("share/ui_photo_02"), imageWidth, imageHeight);
			
			userIconAlpha = 0;
			
			userHelp = new TurnGameSprite(GameImage.getImage("newui/icon_photo_02"));
		}				
		
		icon1 = new TurnGameSprite(GameImage.getImage("newui/icon_01"));
		
		icon2 = new TurnGameSprite(GameImage.getImage("newui/icon_02"));
		
		icon11 = new TurnGameSprite(GameImage.getImage("newui/icon_01_1"));
		
		icon21 = new TurnGameSprite(GameImage.getImage("newui/icon_02_1"));
		
		goldGame = new TurnGameSprite(GameImage.getImage("newui/Gold_01"));
		
		starGame = new TurnGameSprite(GameImage.getImage("newui/Star_01"));
		
		stoneGame = new TurnGameSprite(GameImage.getImage("newui/Gem_01"));
		
		numScoreBit = GameImage.getAutoSizecutBitmap("newui/num_01", 11, 1, GameImage.Sort_line);
		
		numScore =new TurnGameSprite[numScoreBit.length];
		
		for(int i=0;i<numScoreBit.length;i++)
		{
			numScore[i]=new TurnGameSprite(numScoreBit[i]);
		}
		
		itemNumBit = GameImage.getAutoSizecutBitmap("newui/icon_01_num", 10, 1, GameImage.Sort_line);
		
		itemNum =new TurnGameSprite[itemNumBit.length];
		
		for(int i=0;i<itemNumBit.length;i++)
		{			
			itemNum[i]=new TurnGameSprite(itemNumBit[i]);
		}
		
		itemNum1Bit = GameImage.getAutoSizecutBitmap("newui/icon_02_num", 10, 1, GameImage.Sort_line);
		
		itemNum1 =new TurnGameSprite[itemNum1Bit.length];
		
		for(int i=0;i<itemNum1Bit.length;i++)
		{
			itemNum1[i]=new TurnGameSprite(itemNum1Bit[i]);
		}		
		
		wordNumChars = new char[]{'0','1','2','3','4','5','6','7','8','9',':'};
		
		itemNumChars = new char[]{'0','1','2','3','4','5','6','7','8','9'};		
		
	    uiPls = new float[]{
				10*GameConfig.f_zoomx,
				GameConfig.GameScreen_Height-icon1.bitmap.getHeight()-10*GameConfig.f_zoomy,
				10*GameConfig.f_zoomx+icon1.bitmap.getWidth()+5*GameConfig.f_zoomx,
				GameConfig.GameScreen_Height-icon1.bitmap.getHeight()-10*GameConfig.f_zoomy,
				
				GameConfig.GameScreen_Width-icon1.bitmap.getWidth()-10*GameConfig.f_zoomx, 
				GameConfig.GameScreen_Height-icon1.bitmap.getHeight()-10*GameConfig.f_zoomy,	
				GameConfig.GameScreen_Width-icon1.bitmap.getWidth()*2-15*GameConfig.f_zoomx, 
				GameConfig.GameScreen_Height-icon1.bitmap.getHeight()-10*GameConfig.f_zoomy,	
		};
	}
	
	public void delImage()
	{
		GameImage.delImage(uiProgress1.bitmap);
		
		uiProgress1.recycleBitmap();
		
		GameImage.delImage(uiProgress2.bitmap);
		
		uiProgress2.recycleBitmap();
		
		GameImage.delImage(uiProgress3.bitmap);
		
		uiProgress3.recycleBitmap();
		
		GameImage.delImage(butPasue.bitmap);
		
		butPasue.recycleBitmap();
		
		GameImage.delImage(userHelp.bitmap);
		
		userHelp.recycleBitmap();
		
		GameImage.delImage(userIcon.bitmap);
		
		userIcon.recycleBitmap();
		
		GameImage.delImage(icon1.bitmap);
		
		icon1.recycleBitmap();
		
		GameImage.delImage(icon2.bitmap);
		
		icon2.recycleBitmap();
		
		GameImage.delImage(icon11.bitmap);
		
		icon11.recycleBitmap();
		
		GameImage.delImage(icon21.bitmap);
		
		icon21.recycleBitmap();
		
		GameImage.delImage(goldGame.bitmap);
		
		goldGame.recycleBitmap();
		
		GameImage.delImage(starGame.bitmap);
		
		starGame.recycleBitmap();
		
		GameImage.delImage(stoneGame.bitmap);
		
		stoneGame.recycleBitmap();
		
		for(int i=0;i<numScore.length;i++)
		{
			GameImage.delImage(numScore[i].bitmap);
			
			numScore[i].recycleBitmap();
		}
		
		for(int i=0;i<itemNum.length;i++)
		{
			GameImage.delImage(itemNum[i].bitmap);
			
			itemNum[i].recycleBitmap();
		}
		
		for(int i=0;i<itemNum1.length;i++)
		{
			GameImage.delImage(itemNum1[i].bitmap);
			
			itemNum1[i].recycleBitmap();
		}
		
		for(int i=0;i<items.size();i++)
		{
			GameImage.delImage(items.get(i).tool.bitmap);
			
			items.get(i).tool.recycleBitmap();
		}
		
		GameImage.delImageArray(itemNumBit);
		
		GameImage.delImageArray(itemNum1Bit);
		
		GameImage.delImageArray(numScoreBit);		
	}
	
	public void initGameUITool(byte itemType[], int itemLevel[], int itemNum[])
	{
		String imgId[][] = {
							{"newui/icon_01_no", "newui/icon_01_no", "newui/icon_01_no"},
							{"newui/ui_item_04", "newui/ui_item_05", "newui/ui_item_06"},
							{"newui/ui_item_01", "newui/ui_item_02", "newui/ui_item_03"},
							{"newui/ui_item_07", "newui/ui_item_08", "newui/ui_item_09"},
							{"newui/icon_02", "newui/icon_02", "newui/icon_02"},
							{"newui/ui_item_13", "newui/ui_item_14", "newui/ui_item_15"},
							{"newui/ui_item_10", "newui/ui_item_11", "newui/ui_item_12"}
						 };
		
		items = new ArrayList<Item>();
		
		for(int i=0;i<3;i++)
		{
			Item item = new Item();
						
			item.type = itemType[i];
			
			item.level = itemLevel[i];
			
			if(item.type==ITEM_TYPE_1)
			{
				if(item.level==1)
					item.times = 5;
				else if(item.level==2)
					item.times = 7;
				else if(item.level==3)
					item.times = 9;
			}
			else if(item.type==ITEM_TYPE_2)
			{
//				if(item.level==1)
//					item.times = 4;
//				else if(item.level==2)
//					item.times = 5;
//				else if(item.level==3)
					item.times = 6;
			}
			
			item.tool = new TurnGameSprite(GameImage.getImage(imgId[item.type][item.level-1]));
			
			item.cdTime = 75;
			
			item.cd = item.cdTime;
			
//			if(i<=1)
//				item.number = 999;
//			else
//				item.number = 5;
			
			item.number = itemNum[i];
			
			item.reduction = 0;
			
			items.add(item);			
		}
		
		goldNumber = 0;
		
		gemNumber = 0;
		
//		setGameTime(90);//设置游戏时间
	}
		
	public void setGoldNumber(int num)
	{
		goldNumber = num;
	}
	
	public void setGemNumber(int num)
	{
		gemNumber = num;
	}		
	
	public void paint(TurnGameMain gameMain, Canvas canvas, int gameNumber)
	{
		//------------------------------------- 上中  ------------------------------------------------			
		uiProgress1.drawBitmap(canvas, uiProgress1.bitmap, (GameConfig.GameScreen_Width-uiProgress1.bitmap.getWidth())/2, 10*GameConfig.f_zoomy, paint);
			
		int t = uiProgress2.bitmap.getWidth()*StageLength/StageLengthMax;
		
		canvas.save();
		
		canvas.clipRect((GameConfig.GameScreen_Width-uiProgress2.bitmap.getWidth())/2, 10*GameConfig.f_zoomy,
						(GameConfig.GameScreen_Width-uiProgress2.bitmap.getWidth())/2+t, 10*GameConfig.f_zoomy+uiProgress2.bitmap.getHeight());

		uiProgress2.drawBitmap(canvas, uiProgress2.bitmap, (GameConfig.GameScreen_Width-uiProgress2.bitmap.getWidth())/2, 10*GameConfig.f_zoomy, paint);
		
		canvas.restore();
		
		uiProgress3.drawBitmap(canvas, uiProgress3.bitmap, (GameConfig.GameScreen_Width-uiProgress2.bitmap.getWidth())/2+t-uiProgress3.bitmap.getWidth()/2, 10*GameConfig.f_zoomy, paint);
		
//		star2.drawBitmap(canvas, star2.bitmap, (GameConfig.GameScreen_Width-uiProgress1.bitmap.getWidth())/2+uiProgress1.bitmap.getWidth()/3-star2.bitmap.getWidth()/2, 10*GameConfig.f_zoomy, paint);
//		
//		star3.drawBitmap(canvas, star3.bitmap, (GameConfig.GameScreen_Width-uiProgress1.bitmap.getWidth())/2+uiProgress1.bitmap.getWidth()/3*2-star3.bitmap.getWidth()/2, 10*GameConfig.f_zoomy, paint);
//		
//		star4.drawBitmap(canvas, star4.bitmap, (GameConfig.GameScreen_Width-uiProgress1.bitmap.getWidth())/2+uiProgress1.bitmap.getWidth()-star4.bitmap.getWidth(), 10*GameConfig.f_zoomy, paint);
		
		
		//---------------------------------------- 右上角 ------------------------------------------
		
		butPasue.drawBitmap(canvas, butPasue.bitmap, GameConfig.GameScreen_Width-butPasue.bitmap.getWidth()-8*GameConfig.f_zoomx, 8*GameConfig.f_zoomy, paint);
				
		//---------------------------------------- 右下角 ------------------------------------------
//		if(!gameMain.getHideStageState())
//		{
			if(gameMain.stageIndex>=6)
			{				
				if(userIcon.bitmap!=null)	
				{
					userIcon.drawBitmap(canvas, userIcon.bitmap, uiPls[6]+(userHelp.bitmap.getWidth()-userIcon.bitmap.getWidth())/2,
							uiPls[7]+(userHelp.bitmap.getHeight()-userIcon.bitmap.getHeight())/2, paint);
													
					paint.setColor(Color.BLACK);
					
					paint.setAlpha(userIconAlpha);
					
					RectF rect = new RectF();
					
					rect.left = uiPls[6]+(userHelp.bitmap.getWidth()-userIcon.bitmap.getWidth())/2;
					rect.top = uiPls[7]+(userHelp.bitmap.getHeight()-userIcon.bitmap.getHeight())/2;
					rect.right = uiPls[6]+(userHelp.bitmap.getWidth()-userIcon.bitmap.getWidth())/2+userIcon.bitmap.getWidth();
					rect.bottom = uiPls[7]+(userHelp.bitmap.getHeight()-userIcon.bitmap.getHeight())/2+userIcon.bitmap.getHeight();
					
	//				canvas.drawRoundRect(rect, 6, 6, paint);
					
					canvas.drawRect(rect, paint);
					
					paint.reset();
				}	
				
				userHelp.drawBitmap(canvas, userHelp.bitmap, uiPls[6], uiPls[7], paint);
			}
//		}
		
		//---------------------------------------- 左下角 ------------------------------------------		
//		if(!gameMain.getHideStageState())
			drawTools(canvas, paint);
		
		//---------------------------------------- 左 上角 ------------------------------------------
		
		starGame.drawBitmap(canvas,starGame.bitmap, 5*GameConfig.f_zoomx, 5*GameConfig.f_zoomy, paint);	
		
		GameLibrary.DrawNumber(canvas, numScore, starGame.bitmap.getWidth() + 4*GameConfig.f_zoomx, 9*GameConfig.f_zoomy, wordNumChars, ""+gameNumber, paint, GameLibrary.TL, 0);
		
		goldGame.drawBitmap(canvas,goldGame.bitmap, 5*GameConfig.f_zoomx, starGame.bitmap.getHeight() + 10*GameConfig.f_zoomy, paint);	
		
		GameLibrary.DrawNumber(canvas, numScore, goldGame.bitmap.getWidth() + 4*GameConfig.f_zoomx, starGame.bitmap.getHeight() + 14*GameConfig.f_zoomy, wordNumChars, ""+goldNumber, paint, GameLibrary.TL, 0);		
		
		stoneGame.drawBitmap(canvas,stoneGame.bitmap, 5*GameConfig.f_zoomx, starGame.bitmap.getHeight() + goldGame.bitmap.getHeight() + 15*GameConfig.f_zoomy, paint);	
		
		GameLibrary.DrawNumber(canvas, numScore, stoneGame.bitmap.getWidth() + 4*GameConfig.f_zoomx, starGame.bitmap.getHeight() + goldGame.bitmap.getHeight() + 19*GameConfig.f_zoomy, wordNumChars, ""+gemNumber, paint, GameLibrary.TL, 0);		
		
		//---------------------------------------- 时间 ------------------------------------------
		
//		GameLibrary.DrawNumber(canvas, numScore, (GameConfig.GameScreen_Width - GameConfig.GameScreen_Width/4)+22*GameConfig.f_zoomx, 10*GameConfig.f_zoomy, wordNumChars, minute+":"+second, paint, GameLibrary.TH, 0);								
	}		
	
	private void drawTools(Canvas canvas, Paint paint)
	{		
//		if(items==null)
//			return;
//		
//		for(int i=0;i<items.size();i++)
//		{
//			if(items.get(i).tool!=null&&items.get(i).type!=ITEM_TYPE_0)
//			{		
//				float x = uiPls[i*2];
//				
//				float y = uiPls[i*2+1];
//				
//				paint.setAlpha(120);
//				
//				if(i<=1)
//					icon1.drawBitmap(canvas,icon1.bitmap, x, y, paint);
//				else
//					icon2.drawBitmap(canvas,icon2.bitmap, x, y, paint);
//				
//				items.get(i).tool.drawBitmap(canvas,items.get(i).tool.bitmap, x+(icon1.bitmap.getWidth()-items.get(i).tool.bitmap.getWidth())/2, y+(icon1.bitmap.getHeight()-items.get(i).tool.bitmap.getHeight())/2, paint);
//				
//				paint.reset();
//				
//				canvas.save();
//				
//				int t = icon1.bitmap.getHeight()*items.get(i).cd/items.get(i).cdTime;
//				
//				canvas.clipRect(x, y, x+icon1.bitmap.getWidth(), y+t);
//				
//				if(i<=1)
//					icon1.drawBitmap(canvas,icon1.bitmap, x, y, paint);
//				else
//					icon2.drawBitmap(canvas,icon2.bitmap, x, y, paint);
//				
//				items.get(i).tool.drawBitmap(canvas,items.get(i).tool.bitmap, x+(icon1.bitmap.getWidth()-items.get(i).tool.bitmap.getWidth())/2, y+(icon1.bitmap.getHeight()-items.get(i).tool.bitmap.getHeight())/2, paint);
//				
//				canvas.restore();
//				
//				if(items.get(i).type!=ITEM_TYPE_0)
//				{
//					if(i<=1)
//					{
//						icon11.drawBitmap(canvas,icon11.bitmap, x+(icon1.bitmap.getWidth()-icon11.bitmap.getWidth()), y+(icon1.bitmap.getHeight()-icon11.bitmap.getHeight()), paint);
//						
//						GameLibrary.DrawNumber(canvas, itemNum, x+(icon1.bitmap.getWidth()-icon11.bitmap.getWidth())+icon11.bitmap.getWidth()/2, y+(icon1.bitmap.getHeight()-icon11.bitmap.getHeight())+icon11.bitmap.getHeight()/2, itemNumChars, items.get(i).number+"", paint, GameLibrary.VH, 0);				
//					}
//					else 
//					{
//						icon21.drawBitmap(canvas,icon21.bitmap, x+(icon1.bitmap.getWidth()-icon21.bitmap.getWidth()), y+(icon1.bitmap.getHeight()-icon21.bitmap.getHeight()), paint);
//						
//						GameLibrary.DrawNumber(canvas, itemNum1, x+(icon1.bitmap.getWidth()-icon21.bitmap.getWidth())+icon21.bitmap.getWidth()/4+2*GameConfig.f_zoomx, y+(icon1.bitmap.getHeight()-icon21.bitmap.getHeight())+icon21.bitmap.getHeight()-10*GameConfig.f_zoomy, itemNumChars, items.get(i).number+"", paint, GameLibrary.VH, 0);
//					}
//				}			
//			}
//		}
	}
	
	public void updata(TurnGameMain gameMain)
	{
		changeStageLength(gameMain.gameScriptRun.getStageLength());
		
		toolsUpdata();
		
		timeUpdata();
	}
	
	private void toolsUpdata()
	{
		if(items==null)
			return;
		
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).tool!=null)
			{				
				if(items.get(i).cd<items.get(i).cdTime&&items.get(i).number>0)					
					items.get(i).cd ++;							
			}
		}
	}
	
	public void onTouchEvent(MotionEvent event, TurnGameMain gameMain)
	{
		int pointx = (int) event.getX();
		int pointy = (int) event.getY();
				
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
//			//教学
//			if(!gameMain.gameTeaching.pauseState())
//			{	
//				if(gameMain.gameBomb.getBombState())
//				{
//					items.get(gameMain.gameBomb.itemId).open();
//					
//					VeggiesData.addAchievementNum(Achievement.USE_ONEHUNDRED_ITEMS, 1);
//				}				
				
				//暂停界面
				if(pauseKeyDown)
				{
					gameMain.gamePauseMissionProcess();
					
					gameMain.setLevelData();
					
					GameManager.forbidModule(new PauseModule());
				}	
//			}
		}
		
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{	
//			if(!gameMain.getHideStageState())
//			{
				if(gameMain.stageIndex>=6)		
				{
//					if((gameMain.gameTeaching.pauseState()&&gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL19)||					  
//					   !gameMain.gameTeaching.pauseState())
//					{
						//好友援助
						if(pointx>=uiPls[6]&&
						   pointx<=uiPls[6]+userHelp.bitmap.getWidth()&&
						   pointy>=uiPls[7]&&
						   pointy<=uiPls[7]+userHelp.bitmap.getHeight())
						{
//							if(!gameMain.gameFirendlyHammer.getShowState())
//							{
//								if(userIconAlpha==0)
//								{
//									gameMain.gameFirendlyHammer.setShow();
//									
//									userIconAlpha = 120;
//									
//									//教学
//									if(gameMain.gameTeaching.pauseState())
//									{								
//										if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL19)
//										   gameMain.gameTeaching.finish();
//									}
//								}
//							}
						}
//					}
				}
//			}
			
//			//教学
//			if(!gameMain.gameTeaching.pauseState())
//			{	
				//暂停界面
				if(pointx>GameConfig.GameScreen_Width-butPasue.bitmap.getWidth()-8*GameConfig.f_zoomx&&
				   pointx<GameConfig.GameScreen_Width-8*GameConfig.f_zoomx&&
				   pointy>8*GameConfig.f_zoomy&&
				   pointy<8*GameConfig.f_zoomy+butPasue.bitmap.getHeight())
				{
					pauseKeyDown = true;
				}
				else
				{
					pauseKeyDown = false;
				}
//			}
		}
		
		if(event.getAction() == MotionEvent.ACTION_MOVE)
		{
//			//教学
//			if(!gameMain.gameTeaching.pauseState())
//			{
				//暂停界面
				if(pointx>GameConfig.GameScreen_Width-butPasue.bitmap.getWidth()-8*GameConfig.f_zoomx&&
				   pointx<GameConfig.GameScreen_Width-8*GameConfig.f_zoomx&&
				   pointy>8*GameConfig.f_zoomy&&
				   pointy<8*GameConfig.f_zoomy+butPasue.bitmap.getHeight())
				{
					pauseKeyDown = true;
				}
				else
				{
					pauseKeyDown = false;
				}
//			}
		}
		
//		float y = GameConfig.GameScreen_Height-icon1.bitmap.getHeight()-10*GameConfig.f_zoomy;
		
//		if(gameMain.getHideStageState())
//			return;
		
		if(pointx>=uiPls[0]&&
		   pointx<=uiPls[0]+icon1.bitmap.getWidth()&&
		   pointy>=uiPls[1]&&
		   pointy<=uiPls[1]+icon1.bitmap.getHeight())
		{		
//			if((gameMain.gameTeaching.pauseState()&&gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL12)||					  
//			   !gameMain.gameTeaching.pauseState())
//			{
				itemSkill(gameMain, items.get(0), event);
				
				if(items.get(0).type == ITEM_TYPE_3)
				{
//					gameMain.gameBomb.itemId = 0;
				}				
				
//				//教学
//				if(gameMain.gameTeaching.pauseState())
//				{								
//					if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL12)
//					   gameMain.gameTeaching.finish();
//				}
//			}
		}
		else
		if(pointx>=uiPls[2]&&
		   pointx<=uiPls[2]+icon1.bitmap.getWidth()&&
		   pointy>=uiPls[3]&&
		   pointy<=uiPls[3]+icon1.bitmap.getHeight())			
		{							
			itemSkill(gameMain, items.get(1), event);
			
//			if(items.get(1).type == ITEM_TYPE_3)
//			{
//				gameMain.gameBomb.itemId = 1;
//			}
		}
		else
		if(pointx>=uiPls[4]&&
		   pointx<=uiPls[4]+icon1.bitmap.getWidth()&&
		   pointy>=uiPls[5]&&
		   pointy<=uiPls[5]+icon1.bitmap.getHeight())	
		{		
//			if((gameMain.gameTeaching.pauseState()&&gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL14)||					  
//			   !gameMain.gameTeaching.pauseState())
//			{
				itemSkill(gameMain, items.get(2), event);
				
//				//教学
//				if(gameMain.gameTeaching.pauseState())
//				{								
//					if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL14)
//					{
//					   gameMain.gameTeaching.finish();
//					   
//					   if(!gameMain.gameTeaching.teachingArrary[GameTeaching.TEACH_VOL15])
//					   {
//							int _x = (int)(266*GameConfig.f_zoomx);
//							
//							int _y = (int)(300*GameConfig.f_zoomy);
//							
//							gameMain.gameTeaching.setGameTeaching(GameTeaching.TEACH_VOL15, _x, _y, LangUtil.getLangString(LangDefineClient.GUIDE_15), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
//							
//							gameMain.gameFingerDance.setShow(100);
//						}
//					}
//				}
//			}
		}
		
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			for(int i=0;i<items.size();i++)
				items.get(i).setKeyDown(false);
		}
	}
			
	private void itemSkill(TurnGameMain gameMain, Item item, MotionEvent event)
	{
		switch(item.type)
		{
			case ITEM_TYPE_1:				
				if(event.getAction() == MotionEvent.ACTION_UP)
				{					
//					if(!gameMain.gameAirship.getState())
//					{	
//						if(item.open())
//						{
//							gameMain.gameAirship.setTimes(item.times);
//							
//							gameMain.gameCenterEffect.show(gameMain, GameCenterEffect.AIRSHIP_EFFECT);
//							
//							VeggiesData.addAchievementNum(Achievement.USE_ONEHUNDRED_ITEMS, 1);
//						}																
//					}
				}
				break;	
			
			case ITEM_TYPE_2:
				if(event.getAction() == MotionEvent.ACTION_UP)
				{
//					if(!gameMain.gameHailStone.getHailStoneState())
//					{	
//						if(item.open())
//						{
//							gameMain.gameHailStone.setHailStone(true);
//							
//							gameMain.gameHailStone.setHailStone(item.times, item.level);
//							
//							VeggiesData.addAchievementNum(Achievement.USE_ONEHUNDRED_ITEMS, 1);
//						}																
//					}
				}
				break;	
				
			case ITEM_TYPE_3:
				if(event.getAction() == MotionEvent.ACTION_DOWN)
				{
//					if(!gameMain.gameBomb.getShowState())
//					{
//						if(item.isReady())
//						{
//							gameMain.gameBomb.setShow(true);
//							
//							gameMain.gameBomb.setLevel(item.level);
//							
//							gameMain.gameBomb.setXY((int) event.getX(), (int) event.getY());							
//						}	
//					}
				}				
				break;	
				
			case ITEM_TYPE_5:	
				if(event.getAction() == MotionEvent.ACTION_UP)
				{
//					if(!gameMain.gameFingerDance.getShowState())
//					{												
//						if(item.openItemType5(gameMain))
//						{
//							gameMain.gameFingerDance.setShow(100);
//						}						
//					}					
				}
				break;	
				
			case ITEM_TYPE_6:	
//				if(event.getAction() == MotionEvent.ACTION_UP)
//				{												
//					if(!gameMain.combo.getComboState()&&!gameMain.combo.getComboSpecialTimeState())
//					{
//						if(item.open())
//						{
//							gameMain.comboShowLevel = item.level;
//							
//							gameMain.gameCenterEffect.show(gameMain, GameCenterEffect.COMBO_EFFECT);
//							
//							VeggiesData.addAchievementNum(Achievement.USE_ONEHUNDRED_ITEMS, 1);
//						}
//					}					
//				}
				break;
				
			case ITEM_TYPE_7:
//				if(event.getAction() == MotionEvent.ACTION_DOWN)
//				{
//					item.setKeyDown(true);
//				}
//				else if(event.getAction() == MotionEvent.ACTION_UP)
//				{					
//					if(item.getKeyDownState())
//					{
//						if(!gameMain.gameLatticeRestore.getShowTimeState())
//						{
//							if(item.open())
//							{
//								if(item.level==1)							
//									gameMain.gameLatticeRestore.showTime(gameMain, 50);
//								else if(item.level==2)							
//									gameMain.gameLatticeRestore.showTime(gameMain, 100);
//								else if(item.level==3)							
//									gameMain.gameLatticeRestore.showTime(gameMain, 150);
//								
//								VeggiesData.addAchievementNum(Achievement.USE_ONEHUNDRED_ITEMS, 1);
//							} 
//						}	
//						
//						item.setKeyDown(false);
//					}
//				}
				break;	
		}
	}
	
	private String getMinute(int gameTime)
	{
		int m = gameTime/25/60;
		
		String str="";
		
		if(m>9)
			str = ""+m;
		else 
			str = "0"+m;
			
		return str;
	}
	
	private String getSecond(int gameTime)
	{
		int s = (gameTime/25)%60;
		
		String str="";
		
		if(s>9)
			str = ""+s;
		else 
			str = "0"+s;
		
		return str;
	}
	
	private void timeUpdata()
	{
		if(gameTime>0)
		{
			gameTime --;
			
			minute = getMinute(gameTime);
			
			second = getSecond(gameTime);
		}
	}
	
	public ArrayList<Integer> getItemCardInfo()
	{
		ArrayList<Integer> info = new ArrayList<Integer>();
		
		int cardNumber = 0;
		
		for(int i=0;i<items.size();i++)
		{
			if(items.get(i).type!= ITEM_TYPE_0&&
			   items.get(i).type!= ITEM_TYPE_5	)
			{		
				cardNumber ++;
				
				switch(items.get(i).type)
				{
					case ITEM_TYPE_1:
						if(items.get(i).level==1)
							info.add(GameItem.Item34);
						else if(items.get(i).level==2)
							info.add(GameItem.Item35);
						else if(items.get(i).level==3)
							info.add(GameItem.Item36);
						break;
						
					case ITEM_TYPE_2:
						if(items.get(i).level==1)
							info.add(GameItem.Item31);
						else if(items.get(i).level==2)
							info.add(GameItem.Item32);
						else if(items.get(i).level==3)
							info.add(GameItem.Item33);
						break;	
						
					case ITEM_TYPE_3:
						if(items.get(i).level==1)
							info.add(GameItem.Item37);
						else if(items.get(i).level==2)
							info.add(GameItem.Item38);
						else if(items.get(i).level==3)
							info.add(GameItem.Item39);
						break;	
						
					case ITEM_TYPE_6:
						if(items.get(i).level==1)
							info.add(GameItem.Item43);
						else if(items.get(i).level==2)
							info.add(GameItem.Item44);
						else if(items.get(i).level==3)
							info.add(GameItem.Item45);
						break;		
						
					case ITEM_TYPE_7:
						if(items.get(i).level==1)
							info.add(GameItem.Item40);
						else if(items.get(i).level==2)
							info.add(GameItem.Item41);
						else if(items.get(i).level==3)
							info.add(GameItem.Item42);
						break;	
				}
				
				info.add(items.get(i).reduction);
			}
		}
		
		info.add(cardNumber);
		
		return info;
	}		
	
//	// 放大缩小图片
//	private Bitmap zoomBitmap(Bitmap bitmap, float scaleWidht, float scaleHeight) {		
//		matrix = new Matrix();		
//		matrix.reset();		
//		matrix.postScale(scaleWidht, scaleHeight);
//		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
//				matrix, true);
//		return newbmp;
//	}
	
	private class Item
	{		
		private int level;
		
		private int times;
		
		private byte type;
		
		private TurnGameSprite tool;
		
		private int cdTime;
		
		private int number;
		
		private int reduction;
		
		private int cd;
		
		boolean keyDown;
		
		public Item()
		{
			keyDown = false;
		}
		
		public void setKeyDown(boolean keyDownState)
		{
			keyDown = keyDownState;
		}
		
		public boolean getKeyDownState()
		{
			return keyDown;
		}
		
		public boolean open()
		{
			if(number>0&&cd==cdTime)
			{
//				if(type!=ITEM_TYPE_5)
				number --;
				
				cd = 0;
				
				reduction ++;
				
				return true;
			}
			
			return false;
		}
		
		public boolean openItemType5(TurnGameMain gameMain)
		{
			if(gemNumber/number>0&&cd==cdTime)
			{
				gemNumber -= number;
				
				gameMain.gemNumber = gemNumber;
				
				cd = 0;
				
				return true;
			}
			
			return false;
		}
		
		public boolean isReady()
		{
			if(number>0&&cd==cdTime)
			{								
				return true;
			}
			
			return false;
		}
	}
}
