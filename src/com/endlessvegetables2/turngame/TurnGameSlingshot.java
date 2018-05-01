package com.endlessvegetables2.turngame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socogame.coolEdit.CoolEditDefine;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class TurnGameSlingshot {
	
	private TurnGameIndicator indicator;
	
	public int SLINGSHOT_X;
	public int SLINGSHOT_Y;		
		
	public int slingShotPiece_x;
	public int slingShotPiece_y;
	public float slingShotPieceDegree;	
	
	private boolean selectSlingShotPiece;
	
	public boolean slingShotBufferState;
	private int slingShotBufferIndex;
	private final int slingShotBuffer[] = {4,8,9,5,-2,1};
	
	private int slingShotRopeInterval;
	private int slingShotRope_x;
	private int slingShotRope_y;
	
	public int slingShotLen;
	public int slingShotMaxLen;
	
	private boolean sendSpriteBullet;
	
	private TurnGameSprite slingShotPiece;
	
	private TurnGameSprite slingShotPieceFrameLeft;
	
//	private Sprite slingShotPieceFrameRight;
	
	private int comboTime;
	
	private boolean isCombo;
	
	private int slingShotLevel;
	
	private int slingShotRopeYArray[] = {6,-16,-12,-8};
	
	private String slingShotPieceImageArray[] = {
												"GameBg/dg0_0","GameBg/dg0",
												"GameBg/dg1_0","GameBg/dg1",
												"GameBg/dg2_0","GameBg/dg2",
												"GameBg/dg3_0","GameBg/dg3"
			                                    };	
	
	private int leftSideSendNumber;
	
	private int rightSideSendNumber;
	
	//--------------------- �㶹����������Ҫʹ�õĲ��� --------------------------
	public int sendWaitingTime;
	
	public int slingShotSpeed;
	
	public float slingShotDegree;
	
	public boolean isSend;
		
	public void loadImage()
	{
		//����ƤͼƬ
		slingShotPiece = new TurnGameSprite(GameImage.getImage(slingShotPieceImageArray[slingShotLevel*2]));
		
		//��൯��֧��
		slingShotPieceFrameLeft = new TurnGameSprite(GameImage.getImage(slingShotPieceImageArray[slingShotLevel*2+1]));
		
//		//�Ҳ൯��֧��
//		slingShotPieceFrameRight = new Sprite(reversionBitmap(slingShotPieceFrameLeft.bitmap, -1, 1));
	}
	
	public void delImage()
	{	
		indicator.delImage();
		
		GameImage.delImage(slingShotPiece.bitmap);
		
		GameImage.delImage(slingShotPieceFrameLeft.bitmap);
		
//		GameImage.delImage(slingShotPieceFrameRight.bitmap);
		
		slingShotPiece.recycleBitmap();
		
		slingShotPieceFrameLeft.recycleBitmap();
		
//		slingShotPieceFrameRight.recycleBitmap();		
	}
	
	public void init()
	{
//		slingShotLevel = 3;
		
		SLINGSHOT_X = GameConfig.GameScreen_Width/2;
		SLINGSHOT_Y = GameConfig.GameScreen_Height-GameConfig.GameScreen_Height/4 /*+ (int)(40*GameConfig.f_zoom)*/;				
		
		//����Ƥλ��
		slingShotPiece_x = SLINGSHOT_X;
		slingShotPiece_y = SLINGSHOT_Y+(int)(40*GameConfig.f_zoom);
		
		//����Ƥ��ʼ�Ƕ�
		slingShotPieceDegree = 270;
				
		//���е���Ƥ
		selectSlingShotPiece = false;
		
		//����Ƥ���ջ���
		slingShotBufferState = false;
		
		//������֮��ľ���		
		slingShotRopeInterval = (int)(110*GameConfig.f_zoom);
		
		//��������ʮ���ǵľ���
		slingShotRope_x = slingShotRopeInterval/2/*+(int)(18*GameConfig.f_zoom)*/;
		slingShotRope_y = (int)(slingShotRopeYArray[slingShotLevel]*GameConfig.f_zoom);
		
		//�������������೤��
		slingShotMaxLen = (int)(120*GameConfig.f_zoom);
		
		//���Է����߲��ӵ�
		sendSpriteBullet = false;
		
		indicator = new TurnGameIndicator();	
		
		leftSideSendNumber = 0;
		
		rightSideSendNumber = 0;
		
		isSend = false;
	}
	
	public TurnGameIndicator getIndicator()
	{
		return indicator;
	}	
	
//	// ��תͼƬ
//	private Bitmap reversionBitmap(Bitmap bitmap, float LR, float UD) 
//	{	
//		Matrix matrix = new Matrix();
//		
//		matrix.postScale(LR, UD);
//		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
//				matrix, true);
//		return newbmp;
//	}
	
	Paint paint = new Paint();
	
	public void paint(Canvas canvas, TurnGameMain gameMain)
	{			
		
		//----------------------------------- �ȴ��е��߲� --------------------------------------
		if(TurnGameMain.turn!=0){
			gameMain.waitingSpriteBulletLeft.paint(canvas);

//			if(!gameMain.getHideStageState())
			gameMain.waitingSpriteBulletRight.paint(canvas, TurnGameMonster.shxSkillTime>0?true:false);
			
		}

		//---------------------------------- ����֧�� ---------------------------------------
		
//		slingShotPieceFrameLeft.drawBitmap(canvas, slingShotPieceFrameLeft.bitmap, SLINGSHOT_X - slingShotRope_x + 4*GameConfig.f_zoom, SLINGSHOT_Y + 14*GameConfig.f_zoom + slingShotRope_y, null, GameLibrary.VH, paint);
//				
//		slingShotPieceFrameRight.drawBitmap(canvas, slingShotPieceFrameRight.bitmap, SLINGSHOT_X + slingShotRope_x - 4*GameConfig.f_zoom, SLINGSHOT_Y + 14*GameConfig.f_zoom + slingShotRope_y, null, GameLibrary.VH, paint);				
		
//		slingShotPieceFrameLeft.drawBitmap(canvas, slingShotPieceFrameLeft.bitmap, SLINGSHOT_X - slingShotRope_x - 12*GameConfig.f_zoom, SLINGSHOT_Y - 30*GameConfig.f_zoom + slingShotRope_y, paint);
//		
//		slingShotPieceFrameRight.drawBitmap(canvas, slingShotPieceFrameRight.bitmap, SLINGSHOT_X + slingShotRope_x - 20*GameConfig.f_zoom, SLINGSHOT_Y - 30*GameConfig.f_zoom + slingShotRope_y, paint);
		int temp_maxtime=TurnGameMain.turn13time_max*2+TurnGameMain.turn2time_max*1+TurnGameMain.turn_guaitime_max;
		int temp_time=temp_maxtime;
		canvas.save();
		if(TurnGameMain.turn!=0&&TurnGameMain.turn<5){
			temp_time=TurnGameMain.turntime;
			switch (TurnGameMain.turn) {
//			case 7:
//				temp_time+=TurnGameMain.turn2time_max;
//			case 6:
//				temp_time+=TurnGameMain.turn13time_max;
//			case 5:
//				temp_time+=TurnGameMain.turn_guaitime_max;
			case 4:
				temp_time+=TurnGameMain.turn13time_max;
			case 3:
				temp_time+=TurnGameMain.turn2time_max;
			case 2:
				temp_time+=TurnGameMain.turn13time_max;
			}
			canvas.clipRect(0, SLINGSHOT_Y - 30*GameConfig.f_zoom+slingShotPieceFrameLeft.bitmap.getHeight()*temp_time/temp_maxtime, GameConfig.GameScreen_Width, SLINGSHOT_Y - 30*GameConfig.f_zoom+slingShotPieceFrameLeft.bitmap.getHeight());
			paint.setAlpha(100);
			slingShotPieceFrameLeft.drawBitmap(canvas, slingShotPieceFrameLeft.bitmap, SLINGSHOT_X - slingShotPieceFrameLeft.bitmap.getWidth()/2, SLINGSHOT_Y - 30*GameConfig.f_zoom, paint);
			
			ExternalMethods.drawImage(canvas, slingShotPiece.bitmap, slingShotPiece_x-slingShotPiece.bitmap.getWidth()/2, slingShotPiece_y-slingShotPiece.bitmap.getHeight()/2, 1f, 1f, 100, -slingShotPieceDegree, slingShotPiece.bitmap.getWidth()/2, slingShotPiece.bitmap.getHeight()/2);

			paint.reset();
			paint.setStrokeWidth(5*GameConfig.f_zoom);
			paint.setColor(Color.BLACK);
		
			int tempR=slingShotPiece.bitmap.getHeight()/2-(int)(5*GameConfig.f_zoom);
			int tempX=(int)ExternalMethods.getAngleX(-slingShotPieceDegree+90,tempR);
			int tempY=(int)ExternalMethods.getAngleY(-slingShotPieceDegree+90,tempR);
			
			canvas.drawLine(SLINGSHOT_X - slingShotRope_x, SLINGSHOT_Y + slingShotRope_y, slingShotPiece_x + tempX, slingShotPiece_y - tempY, paint);
			canvas.drawLine(SLINGSHOT_X + slingShotRope_x, SLINGSHOT_Y + slingShotRope_y, slingShotPiece_x - tempX, slingShotPiece_y + tempY, paint);				
			canvas.restore();
			
			canvas.clipRect(0, SLINGSHOT_Y - 30*GameConfig.f_zoom, GameConfig.GameScreen_Width, SLINGSHOT_Y - 30*GameConfig.f_zoom+slingShotPieceFrameLeft.bitmap.getHeight()*temp_time/temp_maxtime);
			
			paint.setAlpha(255);
		}
		
		slingShotPieceFrameLeft.drawBitmap(canvas, slingShotPieceFrameLeft.bitmap, SLINGSHOT_X - slingShotPieceFrameLeft.bitmap.getWidth()/2, SLINGSHOT_Y - 30*GameConfig.f_zoom, paint);
		
		//----------------------------------- �ȴ��е��߲� --------------------------------------
		if(TurnGameMain.turn==0){
			gameMain.waitingSpriteBulletLeft.paint(canvas);

//			if(!gameMain.getHideStageState())
			gameMain.waitingSpriteBulletRight.paint(canvas, TurnGameMonster.shxSkillTime>0?true:false);
		
		
			//----------------------------------- ׼���е��߲� --------------------------------------
			gameMain.readSpriteBullet.paint(canvas);
		}
		//------------------------------------- ����Ƥ -----------------------------------
		
		ExternalMethods.drawImage(canvas, slingShotPiece.bitmap, slingShotPiece_x-slingShotPiece.bitmap.getWidth()/2, slingShotPiece_y-slingShotPiece.bitmap.getHeight()/2, 1f, 1f, 255, -slingShotPieceDegree, slingShotPiece.bitmap.getWidth()/2, slingShotPiece.bitmap.getHeight()/2);

		//------------------------------------- ������ -----------------------------------				
		
		paint.reset();
		paint.setStrokeWidth(5*GameConfig.f_zoom);
		paint.setColor(Color.BLACK);
	
		int tempR=slingShotPiece.bitmap.getHeight()/2-(int)(5*GameConfig.f_zoom);
		int tempX=(int)ExternalMethods.getAngleX(-slingShotPieceDegree+90,tempR);
		int tempY=(int)ExternalMethods.getAngleY(-slingShotPieceDegree+90,tempR);
		
		canvas.drawLine(SLINGSHOT_X - slingShotRope_x, SLINGSHOT_Y + slingShotRope_y, slingShotPiece_x + tempX, slingShotPiece_y - tempY, paint);
		canvas.drawLine(SLINGSHOT_X + slingShotRope_x, SLINGSHOT_Y + slingShotRope_y, slingShotPiece_x - tempX, slingShotPiece_y + tempY, paint);				
		
		canvas.restore();
		//��ʾ����Ƥ��ײ��
//		paint.setColor(Color.GREEN);
//		paint.setStyle(Style.STROKE);
//		
//		canvas.drawRect(slingShotPiece_x-slingShotPiece.bitmap.getHeight()/2, slingShotPiece_y-slingShotPiece.bitmap.getWidth()*2,
//				        slingShotPiece_x+slingShotPiece.bitmap.getHeight()/2, slingShotPiece_y+slingShotPiece.bitmap.getWidth()/2, paint);
		
//		//------------------------------------- ����ָʾ���� ---------------------------------
//		
//		indicator.paint(canvas, SLINGSHOT_X, SLINGSHOT_Y, slingShotPiece_x, slingShotPiece_y, slingShotLen, slingShotPieceDegree);				
	}
	
	public void paintIndicator(Canvas canvas)
	{
		//------------------------------------- ����ָʾ���� ---------------------------------
		
		indicator.paint(canvas, SLINGSHOT_X, SLINGSHOT_Y, slingShotPiece_x, slingShotPiece_y, slingShotLen, slingShotPieceDegree);				
	}
	
	public void updata(TurnGameMain gameMain)
	{
//		if(selectSlingShotPiece)
//		{					
//			if(slingShotLen == slingShotMaxLen)
//			{
//				if(!isCombo)
//				{					
//					comboTime ++;
//					
//					if(comboTime>25)
//					{					
//						comboTime = 0;
//						
//						if(gameMain.combo.isComboResult()&&!gameMain.combo.getComboState())
//						{
////							gameMain.gameUI.setComboType(GameUI.COMBO_TYPE_1);
//							
//							gameMain.comboShowLevel = 1;
//							
//							gameMain.gameCenterEffect.show(gameMain, GameCenterEffect.COMBO_EFFECT);
//							
//							isCombo = true;
//							
//							//��ѧ
//							if(gameMain.gameTeaching.pauseState())
//							{								
//								if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL3)
//								{
////								   gameMain.gameTeaching.setGameTeaching(GameTeaching.TEACH_VOL29, slingShotPiece_x, slingShotPiece_y, LangUtil.getLangString(LangDefineClient.GUIDE_29), GameTeaching.HAND_MOVE_STATE_2, (int)(500*GameConfig.f_zoomy));
//									
//								   gameMain.gameTeaching.finish();
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		
		if(slingShotBufferState)
		{
			slingShotBufferIndex++;
	
			if(slingShotBufferIndex>=slingShotBuffer.length)
			{
				slingShotBufferIndex = 0;
				
				slingShotBufferState = false;		
				
				slingShotPieceDegree = 270;		
				
				slingShotLen = 0;								
			}
			else
			{
				if(slingShotBufferIndex==1)
				{											
					slingShotPiece_x = SLINGSHOT_X;
					slingShotPiece_y = SLINGSHOT_Y+(int)(40*GameConfig.f_zoom);		
					
					if(slingShotLen>60)
					{
//						//��ѧ
//						if(gameMain.gameTeaching.pauseState())
//						{
//							if(gameMain.gameTeaching.getTeachId()!=GameTeaching.TEACH_VOL3)
//							{
//								setSendSpriteBullet(true);
//								
//								gameMain.gameTeaching.setIsTouch(false);
//							}
//						}
//						//�ǽ�ѧ
//						else 
//						{
							setSendSpriteBullet(true);
//						}
						
						setValue(gameMain.gameMainLeftPlayerID);
					}
				}
				else
				{
					if(slingShotBufferIndex>=2)
					{
						slingShotPieceDegree = (270+slingShotPieceDegree)/2;							
						
						slingShotPiece_y = SLINGSHOT_Y+(int)(40*GameConfig.f_zoom);	
						
						slingShotPiece_y += (int)(slingShotBuffer[slingShotBufferIndex-2]*GameConfig.f_zoom);						
					}
				}
			}
		}
	}
	
	public void setSendSpriteBullet(boolean state)
	{
		sendSpriteBullet = state;
	}
	
	public boolean getSendSpriteBullet()
	{
		return sendSpriteBullet;
	}
	
//	private boolean isDown = false;
	
	public boolean onTouchEvent(MotionEvent event, TurnGameMain gameMain) {
		// TODO Auto-generated method stub
		int x = (int) event.getX();
		int y = (int) event.getY();
		if(TurnGameMain.turn!=0){
			return false;
		}
//		//��ѧ
//		if(gameMain.gameTeaching.pauseState())
//		{
//			if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL43)
//			{
//				gameMain.gameTeaching.finish();
//				
//				gameMain.gameTeaching.setGameTeaching(GameTeaching.TEACH_VOL3, gameMain.slingshot.slingShotPiece_x, gameMain.slingshot.slingShotPiece_y, LangUtil.getLangString(LangDefineClient.GUIDE_3), GameTeaching.HAND_MOVE_STATE_0, (int)(500*GameConfig.f_zoomy));
//				
//				return false;
//			}
//		}
		
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{				
			if(ExternalMethods.CollisionTest(x, y, slingShotPiece_x-slingShotPiece.bitmap.getHeight()/2, slingShotPiece_y-slingShotPiece.bitmap.getWidth()*2,
					   							   slingShotPiece_x+slingShotPiece.bitmap.getHeight()/2, slingShotPiece_y+slingShotPiece.bitmap.getWidth()/2))
			{
				isCombo = false;
				
				comboTime = 0;								
				
//				//��ѧ
//				if(gameMain.gameTeaching.pauseState())
//				{
//					if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL29)
//					{						
//						gameMain.gameTeaching.finish();
//					}
//				}	
				
//				isDown = true;
			}						
		}		
		else if(event.getAction() == MotionEvent.ACTION_MOVE)
		{		
//			if(!isDown)
//				return false;
			
//			 comboTime = 0;
			
			if(selectSlingShotPiece)
			{		
				indicator.setIndicatorState(true);
				
				slingShotPieceDegree = ExternalMethods.getAngle(x-SLINGSHOT_X,y-SLINGSHOT_Y);
								
				if((slingShotPieceDegree<90&&slingShotPieceDegree>=0)||
				   (slingShotPieceDegree<360&&slingShotPieceDegree>=340)){
					slingShotPieceDegree=340;
				}
				else if(slingShotPieceDegree>=90&&slingShotPieceDegree<=200){
					slingShotPieceDegree=200;
				}
					
//				//��ѧ
//				if(gameMain.gameTeaching.pauseState())
//				{
//					if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL1)
//						slingShotPieceDegree=270;
//					else if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL2)
//						slingShotPieceDegree=270;
//					else if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL3)
//						slingShotPieceDegree=270;											
//				}	
				
				slingShotLen = ExternalMethods.sqrtValue(SLINGSHOT_X, SLINGSHOT_Y, x, y);
										
				if(slingShotLen > slingShotMaxLen)
				{
					slingShotLen = slingShotMaxLen;					
				}
				else
					comboTime = 0;
					
				
				slingShotPiece_x=SLINGSHOT_X+(int)ExternalMethods.getAngleX(slingShotPieceDegree, slingShotLen);
				slingShotPiece_y=SLINGSHOT_Y+(int)ExternalMethods.getAngleY(slingShotPieceDegree, slingShotLen);
				
				return true;
			}
			else
			{
				if(ExternalMethods.CollisionTest(x, y, slingShotPiece_x-slingShotPiece.bitmap.getHeight()/2, slingShotPiece_y-slingShotPiece.bitmap.getWidth()*2,
													   slingShotPiece_x+slingShotPiece.bitmap.getHeight()/2, slingShotPiece_y+slingShotPiece.bitmap.getWidth()/2))
				{
					//����Ƥλ��
					slingShotPiece_x = SLINGSHOT_X;
					slingShotPiece_y = SLINGSHOT_Y+(int)(40*GameConfig.f_zoom);
					
					//����Ƥ��ʼ�Ƕ�
					slingShotPieceDegree = 270;
							
					//���е���Ƥ
					selectSlingShotPiece = true;
					
					//����Ƥ���ջ���
					slingShotBufferState = false;
					
					slingShotBufferIndex = 0;
					
//					if(gameMain.waitingSpriteBullet.getSpriteBulletWaiting().size()>0&&
//					   gameMain.waitingSpriteBullet.getSpriteBulletWaiting().get(0).reloadTime == 0)
//					{
//						if(gameMain.readSpriteBullet.getSpriteBullet().state == Sprite.SPRITE_STATE_NONE)
//						{
//							gameMain.waitingSpriteBullet.setSpriteBullet(gameMain.readSpriteBullet, slingShotPiece_x, slingShotPiece_y);
//						}
//					}
//					else 
					if(gameMain.readSpriteBullet.getSpriteBullet().state == TurnGameSprite.SPRITE_STATE_NONE)
					{
						gameMain.readSpriteBullet.openReloadState();
						
						selectSlingShotPiece = false;						
					}
					
					return true;
				}	
			}			
		}
		else if(event.getAction() == MotionEvent.ACTION_UP)
		{			
//			if(!isDown)
//				return false;
//			else 
//				isDown = false;
			
			if(selectSlingShotPiece)
			{
//				selectSlingShotPiece = false;
//					
//				slingShotBufferState = true;			
//					
//				indicator.setIndicatorState(false);	
//				
//				indicator.setSnipeState(false);
				
				isSend = true;
				
				TurnGameMain.turn_nengliang-=TurnGameSpriteLibrary.Getnengliang(gameMain.readSpriteBullet.getSpriteBullet().kind);
				TurnGameMain.isShow_nengliang=15;
				if(TurnGameMain.turn_nengliang<TurnGameSpriteLibrary.Getnengliang(gameMain.waitingSpriteBulletLeft.spriteBulletWaiting.get(0).kind)){
					TurnGameMain.turn=8;
					TurnGameMain.turntime=0;
				}
				
				countSendBulletNumber(gameMain);
				
				recover(gameMain);
				
				return true;
			}
		}
		
		return false;
	}
	
	public void recover(TurnGameMain gameMain)//������ԭ
	{
//		//��ѧ
//		if(gameMain.gameTeaching.pauseState())
//		{												
//			if(gameMain.gameTeaching.getTeachId()==GameTeaching.TEACH_VOL29)
//			{
//				return;
//			}
//		}	
		
		if(selectSlingShotPiece)
		{
			selectSlingShotPiece = false;
			
			slingShotBufferState = true;			
				
			indicator.setIndicatorState(false);	
			
			indicator.setSnipeState(false);
		}
	}
	
	public boolean getIsSend()
	{
		return isSend;
	}
	
	private void setValue(int kind)
	{
		if(kind==CoolEditDefine.Player_WD)
			sendWaitingTime = 4;
		else if(kind==CoolEditDefine.Player_WD_2)
			sendWaitingTime = 6;
		else if(kind==CoolEditDefine.Player_WD_3)
			sendWaitingTime = 8;
		
		slingShotSpeed = slingShotLen/2<40?20:slingShotLen/4;
		
		slingShotDegree = slingShotPieceDegree;
	}
	
	public void setSlingShotLevel(int _level)
	{
		slingShotLevel = _level;
	}	
	
	private void countSendBulletNumber(TurnGameMain gameMain)
	{
		if(gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_WD||
		   gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_WD_2||
		   gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_WD_3||
		   gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_FQ||
		   gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_FQ_2||
		   gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_FQ_3||
		   gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LJ||
		   gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LJ_2||
		   gameMain.readSpriteBullet.getSpriteBullet().kind==CoolEditDefine.Player_LJ_3)
		{
			leftSideSendNumber ++;			
		}
		else
		{
			rightSideSendNumber ++;
		}	
	}
	
	public int getLeftSideSendNumber()
	{
		return leftSideSendNumber;
	}
	
	public int getRightSideSendNumber()
	{
		return rightSideSendNumber;
	}	
}
