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
//		"�߲�����",
//		"С��С��",
//		"���Ǵ���",
//		"����ն��",
//		"��������",
//		"��ҹ�ǳ�",
//		"���˵�ͷ",
//		"�߲˴���",
//		"�����߲�",
//		"��֮�߲�"
//	}; 
//	
//	public final static int GAMEAWARD_0 = 0;  //�߲�����	�����������ָ��
//	public final static int GAMEAWARD_1 = 1;  //С��С��	���һ��ǿ������
//	public final static int GAMEAWARD_2 = 2;  //���Ǵ���	��ʹ���߲�ͨ�أ���������
//	public final static int GAMEAWARD_3 = 3;  //����ն��	��һ�λ�ɱBOSS
//	public final static int GAMEAWARD_4 = 4;  //��������	��һ����Ϸ��ʹ�ó���3������
//	public final static int GAMEAWARD_5 = 5;  //��ҹ�ǳ�	�ۼ����۳���30����
//	public final static int GAMEAWARD_6 = 6;  //���˵�ͷ	���һ�����ǿ�
//	public final static int GAMEAWARD_7 = 7;  //�߲˴���	ʹ��4�������߲˿�ͨ��
//	public final static int GAMEAWARD_8 = 8;  //�����߲�	COMBO����50
//	public final static int GAMEAWARD_9 = 9;  //��֮�߲�	�������������߲�
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
//					canvas.drawText("��óɾͣ�"+AWARDS_TIPS[i], GameConfig.GameScreen_Width/2, rect.top+36*GameConfig.f_zoom, paint);
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
//		private boolean result;//�ɾ��Ƿ��
//		
//		private boolean isNew;//�Ƿ������µõ��ĳɾ�
//		
//		private boolean isTake;//�Ƿ�ȡ�߸óɾ͵���Ӧ����
//		
//		private int golden;//����Ϊ��ң�����Ϊˮ��
//		
//		private byte showTime;//��ʾʱ��
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
