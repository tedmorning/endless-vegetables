package com.kokatlaruruxi.wy;
//package com.endlessvegetables2.android;
//
//import java.util.ArrayList;
//
//import com.socoGameEngine.GameConfig;
//
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Paint.Align;
//import android.graphics.RectF;
//
//public class GameAwards {
//
//	public final static String AWARDS_TIPS[] = {
//		"蔬菜新人",
//		"小打小闹",
//		"我是传奇",
//		"过关斩将",
//		"富甲天下",
//		"今夜星辰",
//		"鸿运当头",
//		"蔬菜达人",
//		"动感蔬菜",
//		"梦之蔬菜"
//	}; 
//	
//	public final static int GAMEAWARD_0 = 0;  //蔬菜新人	完成所有新人指引
//	public final static int GAMEAWARD_1 = 1;  //小打小闹	完成一次强化操作
//	public final static int GAMEAWARD_2 = 2;  //我是传奇	不使用蔬菜通关，纯道具流
//	public final static int GAMEAWARD_3 = 3;  //过关斩将	第一次击杀BOSS
//	public final static int GAMEAWARD_4 = 4;  //富甲天下	在一局游戏中使用超过3个道具
//	public final static int GAMEAWARD_5 = 5;  //今夜星辰	累计评价超过30颗星
//	public final static int GAMEAWARD_6 = 6;  //鸿运当头	获得一张三星卡
//	public final static int GAMEAWARD_7 = 7;  //蔬菜达人	使用4种三星蔬菜卡通关
//	public final static int GAMEAWARD_8 = 8;  //动感蔬菜	COMBO超过50
//	public final static int GAMEAWARD_9 = 9;  //梦之蔬菜	集齐所有三星蔬菜
//	
//	private static int golden[] = {10, 100, -1, 30, 30, 100, -1, 10, 10, 10};
//	
//	private static ArrayList<Award> isAwards;	
//	
//	public static void showAwards(Canvas canvas)
//	{		
//		Paint paint = new Paint();
//						
//		RectF rect = new RectF();
//		
//		paint.setAlpha(0);
//		
//		for(int i=0;i<AWARDS_TIPS.length;i++)
//		{			
//			if(isAwards.get(i).result)
//			{
//				if(isAwards.get(i).showTime>0)
//				{
//					isAwards.get(i).showTime --;
//								
//					paint.setColor(Color.WHITE);	
//										
//					if(isAwards.get(i).showTime>=75)						
//						paint.setAlpha(10*(100-isAwards.get(i).showTime));
//					else if(isAwards.get(i).showTime<=25)						
//						paint.setAlpha(10*isAwards.get(i).showTime);	
//					else 
//						paint.setAlpha(255);	
//					
//					rect.left = (GameConfig.GameScreen_Width - 240*GameConfig.f_zoom)/2;
//					rect.right = rect.left + 240*GameConfig.f_zoom;
//					rect.top = GameConfig.GameScreen_Height - 120*GameConfig.f_zoom;
//					rect.bottom = rect.top + 60*GameConfig.f_zoom;
//								
//					canvas.drawRoundRect(rect, 6, 6, paint);
//					
//					paint.setColor(Color.BLACK);	
//					
//					paint.setTextSize(20*GameConfig.f_zoom);
//					
//					paint.setTextAlign(Align.CENTER);
//										
//					if(isAwards.get(i).showTime>=75)						
//						paint.setAlpha(10*(100-isAwards.get(i).showTime));
//					else if(isAwards.get(i).showTime<=25)						
//						paint.setAlpha(10*isAwards.get(i).showTime);	
//					else 
//						paint.setAlpha(255);	
//					
//					canvas.drawText("获得成就："+AWARDS_TIPS[i], GameConfig.GameScreen_Width/2, rect.top+36*GameConfig.f_zoom, paint);
//					
//					if(isAwards.get(i).showTime==0)
//					{
//						GameData data = new GameData();
//					
////						data.saveGame();
//					}
//					
//					break;
//				}
//			}
//		}				
//	}
//	
//	public static void initAwardsList()
//	{		
//		isAwards = new ArrayList<Award>();
//		
//		for(int i=0;i<AWARDS_TIPS.length;i++)
//		{
//			Award award = new Award();
//			
//			award.init(golden[i]);
//			
//			isAwards.add(award);
//		}		
//	}
//	
//	public static void setAwardsList(ArrayList<Award> _awardList)
//	{
//		isAwards = new ArrayList<Award>();
//		
//		isAwards = (ArrayList<Award>) _awardList.clone();
//	}
//	
//	public static ArrayList getAwardsList()
//	{
//		return isAwards;
//	}
//	
//	public static boolean getAwardResult(int gameAwardId)
//	{
//		return isAwards.get(gameAwardId).result;
//	}
//	
//	public static int getAwardGolden(int gameAwardId)
//	{
//		return isAwards.get(gameAwardId).golden;
//	}
//	
//	public static boolean isNewAward()
//	{
//		boolean result = false;
//		
//		for(int i=0;i<isAwards.size();i++)
//		{
//			if(isAwards.get(i).isNew)
//			{
////				isAwards.get(i).isNew = false;
//				
//				result = true;						
//			}
//		}
//		
//		return result;
//	}
//	
//	public static void setAwards(int id)
//	{		
//		if(getAwardResult(id))
//			return;
//		
//		switch(id)
//		{
//			case GAMEAWARD_0:
//				isAwards.get(GAMEAWARD_0).success();
//				break;
//		}
//	}
//	
//	static class Award
//	{		
//		private boolean result;//成就是否打开
//		
//		private boolean isNew;//是否是最新得到的成就
//		
//		private boolean isTake;//是否取走该成就的相应奖励
//		
//		private int golden;//正数为金币，负数为水晶
//		
//		private byte showTime;//显示时间
//		
//		private int num;
//		
//		private void init(int golden)
//		{
//			isNew = false;
//			
//			result = false;
//			
//			isTake = false;
//			
//			num = 0;
//			
//			showTime = 0;
//			
//			this.golden = golden;
//		}
//		
//		private void success()
//		{
//			isNew = true;
//			
//			result = true;
//			
//			isTake = true;		
//			
//			showTime = 100;
//			
//			GameData data = new GameData();
//			
////			data.saveGame();
//		}
//	}
//}
