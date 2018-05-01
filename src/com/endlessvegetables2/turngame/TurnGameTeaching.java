package com.endlessvegetables2.turngame;

import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.TextBox;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.MotionEvent;

public class TurnGameTeaching {

	public final int STATE_0 = -1;//无效	
//	private final int STATE_1 = 0;//文字提示
	public final int STATE_2 = 1;//圆圈缩小
	public final int STATE_3 = 2;//出现小手
	
	public final static int NO_HAND = -1;//没有手
	public final static int HAND_MOVE_STATE_0 = 0;//从上往下拖	
	public final static int HAND_MOVE_STATE_1 = 1;//点击
	public final static int HAND_MOVE_STATE_2 = 2;//左右拖动
	
	public final static int LEFT_HAND = 0;//左手
	public final static int RIGHT_HAND = 1;//右手	
	
	private int handType;
	
	public boolean isIntroduction;
	
	private TurnGameSprite teachIcon;
	
	private TurnGameSprite teachBg;
	
	private TurnGameSprite mask;
	
	private TurnGameSprite hand;
	
//	private Matrix matrix;
	
	private Paint paint;
	
	private int pointx;
	
	private int pointy;
	
	private int nextPointx;
	
	private int nextPointy;
	
	private int maskWidth;
	
	private int maskHeight;
	
	private float maskSize;
	
	private byte state;
	
	private TextBox introduction;
	
	private int currentPage;
	
	private int handMoveType;
	
	private int handMovex;
	
	private int handMovey;
	
	private boolean handMoveDirect;
	
	private int dailogBottomY;
		
	private int teachId;
	
	//----------------------------- 开关触摸  ----------------------------	
	private boolean isTouch;

	private boolean isMaskType;
	
	public static short TEACH_VOL1 = 0;//蔬菜弹射教学
	public static short TEACH_VOL2 = 1;//COMBO模式（连击数说明）
	public static short TEACH_VOL3 = 2;//COMBO模式（触发使用）	
	public static short TEACH_VOL4 = 3;//第一次抽卡包
	public static short TEACH_VOL5 = 4;//第一次抽卡包
	public static short TEACH_VOL6 = 5;//第一次抽卡包
	public static short TEACH_VOL7 = 6;//第一次装备教学
	public static short TEACH_VOL8 = 7;//第一次装备教学
	public static short TEACH_VOL9 = 8;//第一次装备教学
	public static short TEACH_VOL10 = 9;//第一次装备教学
	public static short TEACH_VOL11 = 10;//第一次装备教学
	public static short TEACH_VOL12 = 11;//第一次使用道具
	public static short TEACH_VOL13 = 12;//第一次使用伙伴
	public static short TEACH_VOL14 = 13;//第一次使用乱舞
	public static short TEACH_VOL15 = 14;//第一次使用乱舞
	public static short TEACH_VOL16 = 15;//第一次装备援助
	public static short TEACH_VOL17 = 16;//第一次装备援助
	public static short TEACH_VOL18 = 17;//第一次装备援助
	public static short TEACH_VOL19 = 18;//第一次使用援助
	public static short TEACH_VOL20 = 19;//第一次使用农场
	public static short TEACH_VOL21 = 20;//第一次使用农场（未登陆）
	public static short TEACH_VOL22 = 21;//第一次使用农场（已登陆）
	public static short TEACH_VOL23 = 22;//第一次使用农场（已登陆）
	public static short TEACH_VOL24 = 23;//第一次使用农场（已登陆）

	//新添加任务id
	public static short TEACH_VOL25 = 24;//第一次进入地图
	public static short TEACH_VOL26 = 25;//指引进入关卡
	public static short TEACH_VOL27 = 26;//任务说明
	public static short TEACH_VOL28 = 27;//进入游戏
	public static short TEACH_VOL29 = 28;//使用COMBO
	public static short TEACH_VOL30 = 29;//查看关卡中得到的东西
	public static short TEACH_VOL31 = 30;//进入卡片库
	public static short TEACH_VOL32 = 31;//点击卡片
	public static short TEACH_VOL33 = 32;//点击第二关
	public static short TEACH_VOL34 = 33;//进入下一界面
	public static short TEACH_VOL35 = 34;//装备伙伴教学 长按伙伴卡可以查看属性	
	public static short TEACH_VOL36 = 35;//分享教学
	public static short TEACH_VOL37 = 36;//回到大选关地图
	public static short TEACH_VOL38 = 37;//进入第五关
	public static short TEACH_VOL39 = 38;//进入下一界面
	public static short TEACH_VOL40 = 39;//装备强化教学
	public static short TEACH_VOL41 = 40;//装备强化教学
	public static short TEACH_VOL42 = 41;//第一次结算界面关闭按钮
	public static short TEACH_VOL43 = 42;//提示combo条	
	public static short TEACH_VOL44 = 43;//第八关游戏中,第一次去除迷雾
	public static short TEACH_VOL45 = 44;//第十关结算界面后,小蓝怪教学
	public static short TEACH_VOL46 = 45;//第八关结算界面后,售卡教学
	public static short TEACH_VOL47 = 46;//售卡教学
	public static short TEACH_VOL48 = 47;//售卡教学

	public static boolean teachingArrary[] = {
			false,//1蔬菜弹射教学
			false,//2COMBO模式（连击数说明）
			false,//3COMBO模式（触发使用）
			false,//4第一次抽卡包
			false,//5第一次抽卡包
			false,//6第一次抽卡包
			false,//7第一次装备教学
			false,//8第一次装备教学
			false,//9第一次装备教学
			false,//10第一次装备教学
			false,//11第一次装备教学
			false,//12第一次使用道具
			false,//13第一次使用伙伴
			false,//14第一次使用乱舞
			false,//15第一次使用乱舞
			false,//16第一次装备援助
			false,//17第一次装备援助
			false,//18第一次装备援助
			false,//19第一次使用援助
			false,//20第一次使用农场
			false,//21第一次使用农场（未登陆）
			false,//22第一次使用农场（已登陆）
			false,//23第一次使用农场（已登陆）
			false,//24第一次使用农场（已登陆）
			//新添加任务id
			false,//25第一次进入地图
			false,//26指引进入关卡
			false,//27任务说明
			false,//28进入游戏
			false,//29使用COMBO
			false,//30查看关卡中得到的东西
			false,//31进入卡片库
			false,//32点击卡片
			false,//33点击第二关
			false,//34进入下一界面
			false,//35装备伙伴教学 长按伙伴卡可以查看属性	
			false,//36分享教学
			false,//37回到大选关地图
			false,//38进入第五关
			false,//39进入下一界面
			false,//40装备强化教学
			false,//41装备强化教学
			false,//42第一次结算界面关闭按钮 
			false,//43提示combo条
			false,//44第八关游戏中,第一次去除迷雾
			false,//45第十关结算界面后,小蓝怪教学
			false,//46第八关结算界面后,售卡教学
			false,//47售卡教学
			false //48售卡教学
	};		
	
	public TurnGameTeaching()
	{
		paint = new Paint();
		
//	    matrix = new Matrix();
		
	    state = STATE_0;
	    
	    teachId = -1;
	    
	    isTouch = true;
	    
	    maskWidth = (int)(100*GameConfig.f_zoomx);
		
		maskHeight = (int)(100*GameConfig.f_zoomy);
		
		handType = RIGHT_HAND;
	}
	
	public void init()
	{
		
	}
	
	public boolean getOnTouchState()
	{
		if(state==STATE_2
//		 ||state==STATE_1
		 )
			return true;
		else
			return false;
	}
	
	public boolean pauseState()
	{
		if(state!=STATE_0)
			return true;
		else
			return false;
	}
	
	public byte getState()
	{
		return state;
	}
	
	public void setIsTouch(boolean _isTouch)
	{
		isTouch = _isTouch;
	}
	
	public boolean getIsTouch()
	{
		return isTouch;
	}
	
	public void finish()
	{
		state = STATE_0;
		 
		teachingArrary[teachId] = true;
		
		teachId = -1;
	}
	
	public int getTeachId()
	{
		return teachId;
	}		
	
	public void setFinalPoint(int _nextPointx, int _nextPointy)
	{
		nextPointx = _nextPointx;
		
		nextPointy = _nextPointy;
	}
	
	public void setGameTeaching(int _teachId, int _pointx, int _pointy, int _maskWidth, int _maskHeight, String _word, int _handMoveType, int _dailogBottomy)
	{
		 teachId = _teachId;
		
		 pointx = _pointx;
			
		 pointy = _pointy;
		 
		 nextPointx = -1;
			
		 nextPointy = -1;
		 
		 maskWidth = _maskWidth;
		 
		 maskHeight = _maskHeight;
		
		 maskSize = 4f;
		 
		 state = STATE_2;
		 
		 if(_word.equals("未使用"))
			 isIntroduction = false;
		 else
			 isIntroduction = true;
		 
		 introduction = new TextBox();
		 introduction.setTextAlign(TextBox.HCENTER);
		 introduction.setTextSize((int)(26*GameConfig.f_zoomx));
		 introduction.setBoxSize(GameConfig.GameScreen_Width-teachIcon.bitmap.getWidth()-(int)(40*GameConfig.f_zoomx), teachBg.bitmap.getHeight());	
		 introduction.setDefaultColor(Color.argb(255, 255, 255, 255));
		 introduction.setString(_word);
		 
		 currentPage = 0;
		 
		 handMoveType = _handMoveType;
		 
		 if(handMoveType == HAND_MOVE_STATE_0)
		 {
			 handMovex = 0;
				
			 handMovey = 0;
		 }
		 else if(handMoveType == HAND_MOVE_STATE_1)
		 {
			 handMovex = 10;
				
			 handMovey = 10;
		 }
		 else if(handMoveType == HAND_MOVE_STATE_2)
		 {
			 handMovex = -20;
			 
			 handMoveDirect = true;
		 }
		 
		 dailogBottomY = _dailogBottomy;
		 
		 isMaskType = false;
	}		
	
	public void setGameTeaching(int _teachId, int _pointx, int _pointy, String _word, int _handMoveType, int _dailogBottomy)
	{
		 teachId = _teachId;
		
		 pointx = _pointx;
			
		 pointy = _pointy;
		 
		 nextPointx = -1;
			
		 nextPointy = -1;
		
		 maskSize = 4f;
		 
		 state = STATE_2;
		 
		 if(_word.equals("未使用"))
			 isIntroduction = false;
		 else
			 isIntroduction = true;
		 
		 introduction = new TextBox();
		 introduction.setTextSize((int)(26*GameConfig.f_zoomx));
		 introduction.setTextAlign(TextBox.HCENTER);		
		 introduction.setBoxSize(GameConfig.GameScreen_Width-teachIcon.bitmap.getWidth()-(int)(40*GameConfig.f_zoomx), teachBg.bitmap.getHeight());	
		 introduction.setDefaultColor(Color.argb(255, 255, 255, 255));
		 introduction.setString(_word);	
		 
		 currentPage = 0;
		 
		 handMoveType = _handMoveType;
		 
		 if(handMoveType == HAND_MOVE_STATE_0)
		 {
			 handMovex = 0;
				
			 handMovey = 0;
		 }
		 else if(handMoveType == HAND_MOVE_STATE_1)
		 {
			 handMovex = 10;
				
			 handMovey = 10;
		 }
		 else if(handMoveType == HAND_MOVE_STATE_2)
		 {
			 handMovex = -20;
			 
			 handMoveDirect = true;
		 }
		 
		 dailogBottomY = _dailogBottomy;
		 
		 isMaskType = true;
	}
	
	public void loadImage()
	{
		if(teachIcon==null)
			teachIcon = new TurnGameSprite(GameImage.getImage("teach/ui_guidance_01"));

		if(teachBg==null)
			teachBg = new TurnGameSprite(GameImage.getImage("teach/ui_guidance_02"));

		if(mask==null)
			mask = new TurnGameSprite(GameImage.getImage("teach/mask"));		

		if(hand==null)
			hand = new TurnGameSprite(GameImage.getImage("teach/hand"));		
	}
	
	public void delImage()
	{
		if(teachIcon!=null)
			GameImage.delImage(teachIcon.bitmap);		
		if(teachBg!=null)
			GameImage.delImage(teachBg.bitmap);	
		if(mask!=null)
			GameImage.delImage(mask.bitmap);
		if(hand!=null)
			GameImage.delImage(hand.bitmap);

		if(teachIcon!=null)
			teachIcon.recycleBitmap();

		if(teachBg!=null)
			teachBg.recycleBitmap();

		if(mask!=null)
			mask.recycleBitmap();

		if(hand!=null)
			hand.recycleBitmap();
	}
	
	public void updata()
	{
		if(state == STATE_2)
		{
			maskSize -= 0.2f;
			
			if(maskSize<=1f)
			{
			   maskSize = 1f;
			   
			   state = STATE_3;		
			   
			   isTouch = true;
			}
		}
		else if(state == STATE_3)
		{
			handMove();
		}
	}

	public void paint(Canvas canvas)
	{						
//		if(state == STATE_1)
//			drawIntroduction(canvas);
//		else 
		if(state == STATE_2)
		{			
			if(isMaskType)//圆形遮罩
				drawMask(canvas);
			else//方形遮罩
				drawRectMask(canvas);
				
			drawIntroduction(canvas);
		}
		else if(state == STATE_3)
		{			
			if(isMaskType)//圆形遮罩
				drawMask(canvas);
			else//方形遮罩
				drawRectMask(canvas);
				
			drawHand(canvas);
			drawIntroduction(canvas);
		}				
	}	
	
	public void onTouchEvent(MotionEvent event) 
	{		
//		if(event.getAction()==MotionEvent.ACTION_UP)
//		{
//			if(state == STATE_1)
//			{
//				currentPage ++;
//				
//				if(currentPage>=introduction.page())
//				state = STATE_2;
//			}		
//		}
	}		
	
	// 放大缩小图片
//	private Bitmap zoomBitmap(Bitmap bitmap, float scaleWidht, float scaleHeight) {
//		matrix.reset();		
//		matrix.postScale(scaleWidht, scaleHeight);
//		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
//				matrix, true);
//		return newbmp;
//	}
		
	private void drawMask(Canvas canvas)
	{		
		if(handMoveType!=NO_HAND)
		{
			paint.setAlpha(120);
			
			int w = (int)(mask.bitmap.getWidth()*maskSize);
			
			int h = (int)(mask.bitmap.getHeight()*maskSize);
			
			int x = pointx-w/2;
			
			int y = pointy-h/2;
			
			mask.drawBitmap(canvas, x, y, (float)w/mask.bitmap.getWidth(), (float)h/mask.bitmap.getHeight(), 120, 0, 0, 0, 0, 0, 0);
			
			paint.reset();
			
			paint.setAlpha(120);
			
			//上左
			canvas.drawRect(0, 0, x, GameConfig.GameScreen_Height, paint);
			
			//上中
			canvas.drawRect(x, 0, x+w, y, paint);
			
			//上右
			canvas.drawRect(x+w, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
			
			//下中
			canvas.drawRect(x, y+h, x+w, GameConfig.GameScreen_Height, paint);
		}
	}
	
	private void drawRectMask(Canvas canvas)
	{
		if(handMoveType!=NO_HAND)
		{
			paint.setAlpha(120);
			
			int x = 0;
			int y = 0;
						
			int w = (int)(maskWidth*maskSize);
			
			int h = (int)(maskHeight*maskSize);
		
			x = pointx-w/2;
				
			y = pointy-h/2;
			
			paint.setStyle(Style.STROKE);
			
			RectF rect = new RectF();
			
			rect.left = x;			
			rect.top = y;			
			rect.right = x+w;			
			rect.bottom = y+h;
			
			canvas.drawRect(rect, paint);
			
			paint.reset();
			
			paint.setAlpha(120);
			
			//上左
			canvas.drawRect(0, 0, x, GameConfig.GameScreen_Height, paint);
			
			//上中
			canvas.drawRect(x, 0, x+w, y, paint);
			
			//上右
			canvas.drawRect(x+w, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
			
			//下中
			canvas.drawRect(x, y+h, x+w, GameConfig.GameScreen_Height, paint);
			
//			canvas.save();
//			
//			paint.setColor(Color.BLACK);
//
//			canvas.drawRect(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
//			
//			int w = (int)(maskWidth*maskSize);
//			
//			int h = (int)(maskHeight*maskSize);
//			
//			int x = pointx-w/2;
//			
//			int y = pointy-h/2;
//			
//			RectF rect = new RectF();
//			
//			rect.left = x;			
//			rect.top = y;			
//			rect.right = x+w;			
//			rect.bottom = y+h;
//			
//			Path mPath=new Path();
//		    mPath.addRoundRect(rect, 6*GameConfig.f_zoomx, 6*GameConfig.f_zoomy, Path.Direction.CW);		  
//		    mPath.close(); 			
//			
//			canvas.clipPath(mPath);
//			
//			canvas.restore();
		}
	}
	
	private void drawIntroduction(Canvas canvas)
	{	
		if(!isIntroduction)
			return;			
		
		paint.reset();
		
		teachBg.drawBitmap(canvas, teachBg.bitmap, 0, dailogBottomY-teachBg.bitmap.getHeight(), paint);
		
		teachIcon.drawBitmap(canvas, teachIcon.bitmap, (int)(10*GameConfig.f_zoomy), dailogBottomY-teachIcon.bitmap.getHeight()-(int)(10*GameConfig.f_zoomy), paint);
		
		introduction.paintText(canvas, teachIcon.bitmap.getWidth(), (dailogBottomY-teachBg.bitmap.getHeight())+(int)(6*GameConfig.f_zoomy), currentPage);
	}
	
	private void handMove()
	{
		if(handMoveType==HAND_MOVE_STATE_0)
		{
			handMovey += 2;
			
			if(handMovey>30)
			handMovey = 0;
		}
		else if(handMoveType==HAND_MOVE_STATE_1)
		{
			handMovex --;
			handMovey --;
			
			if(handMovey<=0)
			{
				handMovex = 10;
				handMovey = 10;
			}
		}	
		else if(handMoveType == HAND_MOVE_STATE_2)
		{		   
			if(handMoveDirect)
			{
				handMovex += 2;
				
				if(handMovex>=20)
				{
					handMoveDirect = false;
				}
			}
			else
			{
				handMovex -= 2;
				
				if(handMovex<=-20)
				{
					handMoveDirect = true;
				}
			}				
		}
	}
	
	public void setHandType(int _type)
	{
		handType = _type;
	}
	
	private void drawHand(Canvas canvas)
	{		
		if(handMoveType!=NO_HAND)
		{
			paint.reset();
			
			if(nextPointx<0)
			{
				if(handType == RIGHT_HAND)
					hand.drawBitmap(canvas, hand.bitmap, pointx+handMovex, pointy+handMovey, paint);	
				else
					hand.drawBitmap(canvas, pointx+handMovex-hand.bitmap.getWidth(), pointy+handMovey, -1f, 1f, 255, 0, 0, 0, 0, 0, 0);
			}
			else
			{
				if(handType == RIGHT_HAND)
					hand.drawBitmap(canvas, hand.bitmap, nextPointx+handMovex, nextPointy+handMovey, paint);	
				else
					hand.drawBitmap(canvas, nextPointx+handMovex-hand.bitmap.getWidth(), nextPointy+handMovey, -1f, 1f, 255, 0, 0, 0, 0, 0, 0);
			}
		}
	}
	
	/**
	 * 返回背景 的宽高
	 * */
	public int getteachBg(){
		if(teachBg != null)
		    return teachBg.bitmap.getHeight();
		return -1;
	}
}
