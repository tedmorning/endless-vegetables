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

	public final int STATE_0 = -1;//��Ч	
//	private final int STATE_1 = 0;//������ʾ
	public final int STATE_2 = 1;//ԲȦ��С
	public final int STATE_3 = 2;//����С��
	
	public final static int NO_HAND = -1;//û����
	public final static int HAND_MOVE_STATE_0 = 0;//����������	
	public final static int HAND_MOVE_STATE_1 = 1;//���
	public final static int HAND_MOVE_STATE_2 = 2;//�����϶�
	
	public final static int LEFT_HAND = 0;//����
	public final static int RIGHT_HAND = 1;//����	
	
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
	
	//----------------------------- ���ش���  ----------------------------	
	private boolean isTouch;

	private boolean isMaskType;
	
	public static short TEACH_VOL1 = 0;//�߲˵����ѧ
	public static short TEACH_VOL2 = 1;//COMBOģʽ��������˵����
	public static short TEACH_VOL3 = 2;//COMBOģʽ������ʹ�ã�	
	public static short TEACH_VOL4 = 3;//��һ�γ鿨��
	public static short TEACH_VOL5 = 4;//��һ�γ鿨��
	public static short TEACH_VOL6 = 5;//��һ�γ鿨��
	public static short TEACH_VOL7 = 6;//��һ��װ����ѧ
	public static short TEACH_VOL8 = 7;//��һ��װ����ѧ
	public static short TEACH_VOL9 = 8;//��һ��װ����ѧ
	public static short TEACH_VOL10 = 9;//��һ��װ����ѧ
	public static short TEACH_VOL11 = 10;//��һ��װ����ѧ
	public static short TEACH_VOL12 = 11;//��һ��ʹ�õ���
	public static short TEACH_VOL13 = 12;//��һ��ʹ�û��
	public static short TEACH_VOL14 = 13;//��һ��ʹ������
	public static short TEACH_VOL15 = 14;//��һ��ʹ������
	public static short TEACH_VOL16 = 15;//��һ��װ��Ԯ��
	public static short TEACH_VOL17 = 16;//��һ��װ��Ԯ��
	public static short TEACH_VOL18 = 17;//��һ��װ��Ԯ��
	public static short TEACH_VOL19 = 18;//��һ��ʹ��Ԯ��
	public static short TEACH_VOL20 = 19;//��һ��ʹ��ũ��
	public static short TEACH_VOL21 = 20;//��һ��ʹ��ũ����δ��½��
	public static short TEACH_VOL22 = 21;//��һ��ʹ��ũ�����ѵ�½��
	public static short TEACH_VOL23 = 22;//��һ��ʹ��ũ�����ѵ�½��
	public static short TEACH_VOL24 = 23;//��һ��ʹ��ũ�����ѵ�½��

	//���������id
	public static short TEACH_VOL25 = 24;//��һ�ν����ͼ
	public static short TEACH_VOL26 = 25;//ָ������ؿ�
	public static short TEACH_VOL27 = 26;//����˵��
	public static short TEACH_VOL28 = 27;//������Ϸ
	public static short TEACH_VOL29 = 28;//ʹ��COMBO
	public static short TEACH_VOL30 = 29;//�鿴�ؿ��еõ��Ķ���
	public static short TEACH_VOL31 = 30;//���뿨Ƭ��
	public static short TEACH_VOL32 = 31;//�����Ƭ
	public static short TEACH_VOL33 = 32;//����ڶ���
	public static short TEACH_VOL34 = 33;//������һ����
	public static short TEACH_VOL35 = 34;//װ������ѧ ������鿨���Բ鿴����	
	public static short TEACH_VOL36 = 35;//�����ѧ
	public static short TEACH_VOL37 = 36;//�ص���ѡ�ص�ͼ
	public static short TEACH_VOL38 = 37;//��������
	public static short TEACH_VOL39 = 38;//������һ����
	public static short TEACH_VOL40 = 39;//װ��ǿ����ѧ
	public static short TEACH_VOL41 = 40;//װ��ǿ����ѧ
	public static short TEACH_VOL42 = 41;//��һ�ν������رհ�ť
	public static short TEACH_VOL43 = 42;//��ʾcombo��	
	public static short TEACH_VOL44 = 43;//�ڰ˹���Ϸ��,��һ��ȥ������
	public static short TEACH_VOL45 = 44;//��ʮ�ؽ�������,С���ֽ�ѧ
	public static short TEACH_VOL46 = 45;//�ڰ˹ؽ�������,�ۿ���ѧ
	public static short TEACH_VOL47 = 46;//�ۿ���ѧ
	public static short TEACH_VOL48 = 47;//�ۿ���ѧ

	public static boolean teachingArrary[] = {
			false,//1�߲˵����ѧ
			false,//2COMBOģʽ��������˵����
			false,//3COMBOģʽ������ʹ�ã�
			false,//4��һ�γ鿨��
			false,//5��һ�γ鿨��
			false,//6��һ�γ鿨��
			false,//7��һ��װ����ѧ
			false,//8��һ��װ����ѧ
			false,//9��һ��װ����ѧ
			false,//10��һ��װ����ѧ
			false,//11��һ��װ����ѧ
			false,//12��һ��ʹ�õ���
			false,//13��һ��ʹ�û��
			false,//14��һ��ʹ������
			false,//15��һ��ʹ������
			false,//16��һ��װ��Ԯ��
			false,//17��һ��װ��Ԯ��
			false,//18��һ��װ��Ԯ��
			false,//19��һ��ʹ��Ԯ��
			false,//20��һ��ʹ��ũ��
			false,//21��һ��ʹ��ũ����δ��½��
			false,//22��һ��ʹ��ũ�����ѵ�½��
			false,//23��һ��ʹ��ũ�����ѵ�½��
			false,//24��һ��ʹ��ũ�����ѵ�½��
			//���������id
			false,//25��һ�ν����ͼ
			false,//26ָ������ؿ�
			false,//27����˵��
			false,//28������Ϸ
			false,//29ʹ��COMBO
			false,//30�鿴�ؿ��еõ��Ķ���
			false,//31���뿨Ƭ��
			false,//32�����Ƭ
			false,//33����ڶ���
			false,//34������һ����
			false,//35װ������ѧ ������鿨���Բ鿴����	
			false,//36�����ѧ
			false,//37�ص���ѡ�ص�ͼ
			false,//38��������
			false,//39������һ����
			false,//40װ��ǿ����ѧ
			false,//41װ��ǿ����ѧ
			false,//42��һ�ν������رհ�ť 
			false,//43��ʾcombo��
			false,//44�ڰ˹���Ϸ��,��һ��ȥ������
			false,//45��ʮ�ؽ�������,С���ֽ�ѧ
			false,//46�ڰ˹ؽ�������,�ۿ���ѧ
			false,//47�ۿ���ѧ
			false //48�ۿ���ѧ
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
		 
		 if(_word.equals("δʹ��"))
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
		 
		 if(_word.equals("δʹ��"))
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
			if(isMaskType)//Բ������
				drawMask(canvas);
			else//��������
				drawRectMask(canvas);
				
			drawIntroduction(canvas);
		}
		else if(state == STATE_3)
		{			
			if(isMaskType)//Բ������
				drawMask(canvas);
			else//��������
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
	
	// �Ŵ���СͼƬ
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
			
			//����
			canvas.drawRect(0, 0, x, GameConfig.GameScreen_Height, paint);
			
			//����
			canvas.drawRect(x, 0, x+w, y, paint);
			
			//����
			canvas.drawRect(x+w, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
			
			//����
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
			
			//����
			canvas.drawRect(0, 0, x, GameConfig.GameScreen_Height, paint);
			
			//����
			canvas.drawRect(x, 0, x+w, y, paint);
			
			//����
			canvas.drawRect(x+w, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
			
			//����
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
	 * ���ر��� �Ŀ��
	 * */
	public int getteachBg(){
		if(teachBg != null)
		    return teachBg.bitmap.getHeight();
		return -1;
	}
}
