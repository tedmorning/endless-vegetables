package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditData;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;

public class Sprite{
	
	private ArrayList<Long> touchArray;	
	
	public float	size			= 1;
	public int		Alpha			= 255;
	public float	jiaodu			= 0;
	
	public int		r				= 0;
	public int		g				= 0;
	public int		b				= 0;
	
	public int		kind			= -1;
	public byte		state			= 0;
	public float	x				= -1;
	public float	y				= -1;
	public short	frames			= 0;
	public short	framesjiange	= 0;
	
	public int		actionName		= 0;
	
	public int		life			= -1;
	public int		lifeMax			= -1;
	
	public boolean  isOver;
	
	public long		idNum;
	
//-----------------------	ORIGIN	-------------------------------//	
	public float	org_x			= -1;
	public float	org_y			= -1;
	public int		org_actionName	= -1;
	public byte		org_byMoveStyle	= 0;
	public float	org_byMoveSpeed	= 0;			
	
	//--------------------------- �����ƶ� ----------------------------------//
	public int	byMoveAngle;
	public int	byMoveSpeed;
	public byte	byMoveStyle;
	
	public float byMoveDegree;
	
	public boolean isMove;
	
	public byte byMoveStyleStep;
	
	public float byMoveX;
	public float byMoveY;
	public float byMoveK;
	
	public byte byMoveDirect;
	
	public int	byMoveChangeLen;	
	
	public int byMoveStop;
	
	public int orgbyMoveStop;
	
	public int byMoveWaitingTime;
	
	//--------------------------- ���鹥�� ----------------------------------//
	public boolean isAttack;
	public int attackTime;
	public int orgAttackTime;
	public byte attackType;
	
	//--------------------------- ���鵯�� ----------------------------------//
	public boolean isFly;
	public int isFlyMoveDegree;
    public int isFlyMoveSpeed;
	
    //---------------------------- ����װ���ȴ� -----------------------------------//
    public int  reloadTime;  
    public int  reloadJumpStep;      
    public int  reloadJumpWaiting;
    public int  reloadJumpPoint;  
    
    //---------------------------- �����Զ�����Ĺ���  -----------------------------------//
    public boolean  isTransition;  
    public int  transition_x;  
    public int  transition_y;      
    public int  transitionStep;
    public int  transitionWaiting;
    
    //--------------------------- ������� ----------------------------------//
    public int  speedUpTime;
    
    //--------------------------- ������� ----------------------------------//
    public int  speedDownTime;
//    public boolean isSpeedDown;
    
    //--------------------------- ����ѣ�� ----------------------------------//
    public int  dizzinessTime;
    public boolean isDizziness;
    
    //--------------------------- �������� ----------------------------------//   
    public boolean isUpState;    
    public boolean isUpAndLRState;
    
    //--------------------------- ����ظ�ʱ�� ----------------------------------//
    public int recoverTime;
    
    //--------------------------- ���������˶� ----------------------------------//
    public boolean comeBack;
    
    //--------------------------- ����򵽹���  ----------------------------------//
    public boolean isTouch;
    
    //--------------------------- ������� ----------------------------------//
    public int isGroupId;
    
    //--------------------------- ����combo ----------------------------------//
    public boolean isCombo;
    
    //--------------------------- �ı䶯��ʱ��  ----------------------------------//
    public int changeActionWaiting;
    
    //--------------------------- ���鷴������ ----------------------------------//
    public int kickNumber;    
    
    //--------------------------- Ч������ʱ�� ----------------------------------//
    public int effectShowTime;
    
    //--------------------------- ��������ֵ ----------------------------------//
    public int special;
    
    //--------------------------- ǿ��ֹͣλ�� ----------------------------------//
    public int stop_x;
    public int stop_y;
    
    //--------------------------- ����״̬  ----------------------------------//
    public boolean freezeState;
    public int freezeTime;
    
    //--------------------------- �ж�ʱ��  ----------------------------------//
    public int shieldTime;
    
    //--------------------------- ����ʱ��  ----------------------------------//
    public int revivalTime;
    
    //--------------------------- ˢ��ʱ��  ----------------------------------//
    public int cdTime;
    public int cd;
    
    //--------------------------- ���ʱ��  ----------------------------------//
    public int lifeTime;
    
    //--------------------------- �����Զ�ʧѪ  ----------------------------------//
    public int lostBloodTime;//ʧѪʱ��
    public int lostBloodAmount;//ʧѪ��
    public int lostBloodTimeOffset;//ʧѪʱ����    
    
    //-------------------------- ��ʾ����Ч�� ---------------------------------------//    
    public boolean isHeadFire;
    
    //--------------------------- ����ɱ��������  ----------------------------------//
    public int killedMonsterMaxNumber;
    public int killedMonsterNumberIndex;
    
    //--------------------------- �ı��С  ----------------------------------//
    public boolean changeSize;
    
    //--------------------------- ��Ծ״̬  ----------------------------------//
    public byte jumpState;
    public byte jumpStep;
       
    //--------------------------- ����֮�������  ----------------------------------//
    public int linkNumber;
    
    //------------------------- ������Ч��֮�������  ----------------------------------//
    public int freezeLinkNumber;
    public int fireLinkNumber;
    public int dizzinessLinkNumber;
    public int speedDownLinkNumber;
    
    //--------------------------- ħ��ˮ�εı���  ----------------------------------//
    public boolean magicWaterProtect;
    
    //--------------------------- ����Ѫ��1 ----------------------------------//
    public boolean bloodIsOne;
        
    //--------------------------- ���������� ---------------------------------//
    public SirenLight sirenLight;
    
    //--------------------------- �ٻ�����  ---------------------------------//
    public int summonsNumber;
    
    //--------------------------- ���ﻬ��  ---------------------------------//
    public boolean isGlide;    
    public int glideLinkNumber;   
    
    //------------------------------ ǿ��ֹͣ ------------------------------//
    public boolean isForcedStop;
    
    //--------------------------- õ���ʱ��  ----------------------------------//
    public int roseThornTime;

	//-----------------------	 State	-------------------------------//
	public static final byte SPRITE_STATE_NONE		= 0; // ��Ч״̬
	public static final byte SPRITE_STATE_NORMAL	= 1; // ͨ��״̬
	public static final byte SPRITE_STATE_STAND		= 2; // վ��״̬
	public static final byte SPRITE_STATE_MOVE		= 3; // �ƶ�״̬
	public static final byte SPRITE_STATE_ATTACK	= 4; // ����״̬
	public static final byte SPRITE_STATE_INJURE	= 5; // ����״̬
	public static final byte SPRITE_STATE_DEAD		= 6; // ����״̬
	
	public static final byte SPRITE_STATE_BURROW	= 7; // ���״̬
	public static final byte SPRITE_STATE_GROUND	= 8; // ����״̬
	
	public static final byte SPRITE_STATE_VIOLENT	= 9; // ��״̬
	public static final byte SPRITE_STATE_SUMMON	= 10;// �ٻ�״̬	
	public static final byte SPRITE_STATE_RESTORE	= 11;// �ָ�״̬
	
	public static final byte SPRITE_STATE_REVIVAL	= 12;// ����״̬	
		
	//-----------------------	Enemy actionName	-------------------------------//
	public static final byte Enemy_action0=0;//����
	public static final byte Enemy_action01=1;//������
	public static final byte Enemy_action02=2;//����
	public static final byte Enemy_action03=3;//����
	public static final byte Enemy_action04=4;//�ƶ�
	public static final byte Enemy_action05=5;//����
	
	public static final byte Enemy_action06=6;//���	
	public static final byte Enemy_action07=7;//����
	
	public static final byte Enemy_action08=6;//��	
	public static final byte Enemy_action09=7;//�ٻ�
	public static final byte Enemy_action10=8;//��Ѫ
	
	public static final byte Enemy_action11=0;//С���ִ��������ƶ�	
	public static final byte Enemy_action12=1;//С���ִ�����������
	public static final byte Enemy_action13=2;//С���ִ��������ƶ�
	public static final byte Enemy_action14=3;//С���ִ�����������
	
	public static final byte Enemy_action15=6;//����౻��������
	public static final byte Enemy_action16=7;//������ƶ�����
	public static final byte Enemy_action17=8;//����๥������
	
	public static final byte Enemy_action18=6;//��������Ծ
	public static final byte Enemy_action19=7;//��������Ծ
	
	public static final byte Enemy_action20=6;//�¹����ٻ�����
	
	public static final byte Enemy_action21=6;//�¹���Ĺ��
	
	public static final byte Enemy_action22=6;//������ٻ�
	public static final byte Enemy_action23=7;//�������ī
		
	public static final byte Enemy_action24=6;//�¹�ӥ쫷�
	public static final byte Enemy_action25=7;//�¹�ӥ��ë����
	public static final byte Enemy_action26=8;//�¹�ӥ�ٻ�
	
	public static final byte Enemy_action27=6;//ħ��ˮ�α��
	
//	public static final byte Enemy_action28=6;//�������������ұ�����
//	public static final byte Enemy_action29=8;//�������������Ҵ���
	public static final byte Enemy_action30=6;//���������������ƶ�
//	public static final byte Enemy_action31=10;//�������������Ҽ���
	

//	public static final byte Enemy_action32=6;//�㽶Ƥ����
	
//-----------------------	ATTACK	-------------------------------//
//	public byte		byAttackStyle	= -1;
//	public short	shAttackPower	= 0;
//	public byte		byAttackRate	= 0;
//	public byte		byAttackjiange	= 0;
//	public byte		byAttackzhen	= 0;
//	
////-----------------------	MOVE	-------------------------------//	
//	public byte		byMoveStyle		= 0;
//	public float	byMoveSpeed		= 0;
//	
//	public static final byte MoveStyle0=0;//�����ƶ�״̬
//	public static final byte MoveStyle01=1;//�ƶ���Ŀ���
//	
////-----------------------	Enemy actionName	-------------------------------//
//	public static final byte Enemy_action0=0;//����
//	public static final byte Enemy_action01=1;//������
//	public static final byte Enemy_action02=2;//����
//	public static final byte Enemy_action03=3;//�ƶ�
//	public static final byte Enemy_action04=4;//����
//	
//	//-----------------------	Player actionName	-------------------------------//
//	public static final byte Player_action0=0;//����
//	public static final byte Player_action01=1;//����
//	public static final byte Player_action02=2;//����
//	public static final byte Player_action03=3;//�϶�
//	public static final byte Player_action04=4;//�ƶ�
//	public static final byte Player_action05=5;//վ��
//	public static final byte Player_action06=6;//����
	
	public static final int PLAYER_SPECIAL_0 = 0;//�����ƶ�
	public static final int PLAYER_SPECIAL_1 = 1;//����
	public static final int PLAYER_SPECIAL_2 = 2;//ѣ��
	public static final int PLAYER_SPECIAL_3 = 3;//����
	public static final int PLAYER_SPECIAL_4 = 4;//��ȼ
	
	public Bitmap bitmap;
	
	public Sprite()
	{
		
	}
	
	public Sprite(Bitmap bitmap)
	{
		  
		
		this.bitmap = bitmap;
	}
	
	public void recycleBitmap()
	{
		if(bitmap!=null)
		{
			if(!bitmap.isRecycled())
				bitmap.recycle();
			
			bitmap = null;
		}
	}
	
	
	public void setSize(float siez)
	{
		size = siez;
		
	}
	
	
	public void initSprite(int kind,int x,int y,int state){
		org_x = x;
		org_y = y;
		this.x = x;
		this.y = y;
		this.kind=kind;
		this.state=(byte)state;
//		state	= 1;
		frames	= 0;
		size = 1f;
		Alpha = 255;
		jiaodu = 0;
		
		byMoveDirect = -1;
		
		byMoveStyle = -1;
		
		byMoveWaitingTime = 0;
		
		isMove = false;	
		
		isFly = false;
		
		isOver = false;
		
		isAttack = false;
		
		byMoveStop = 0;
		
		isTransition = true;
		
		transitionWaiting = 0;
		
		transitionStep = 0;
		
		speedUpTime = GameLibrary.getIntRandom(50, 75);
		
		speedDownTime = 0;
		
//		isSpeedDown = false;
		
		dizzinessTime = 0;
		
	    isDizziness = false;
	    
	    isUpState = false;
	    
	    isUpAndLRState = true;
		
	    recoverTime = 50;
	    
	    comeBack = false;
	    
	    isTouch = false;
	    
	    isGroupId = -1;
	    
	    changeActionWaiting = 0;
	    
	    special = 0;//0����ͨ	1������	  2��ѣ��
	    
	    freezeState = false;
	    
	    shieldTime = 0;
	    
	    revivalTime = 0;
	    
	    attackType = 0;
	    
	    lifeTime = 0;
	    
	    lostBloodTime = 0;
	    
	    lostBloodAmount = 0;
	    
	    lostBloodTimeOffset = 0;
	    
	    killedMonsterMaxNumber = 0;
	    
	    killedMonsterNumberIndex = 0;
	    
	    touchArray = new ArrayList<Long>();
	    
	    isHeadFire = false;
	    
	    changeSize = false;	 
	    
	    isCombo = true;
	    
	    kickNumber = 0;
	    
	    jumpState = 0;
	    
	    jumpStep = 0;
	    
	    linkNumber = 0;
	    
	    freezeLinkNumber = 0;
	    fireLinkNumber = 0;
	    dizzinessLinkNumber = 0;
	    speedDownLinkNumber = 0;
	    
	    magicWaterProtect = false;
	    
	    bloodIsOne = false;
	    
	    sirenLight = new SirenLight();
	    
	    summonsNumber = 0;	
	    
	    isGlide = false;
	    
	    glideLinkNumber = 0;	
	    
	    isForcedStop = false;
	    
	    roseThornTime = 0;
	}
	
	public void setState(byte state)
	{
		this.state = state;
	}
	
	public void setXY( int x,int y )
	{
		this.x=x;
		this.y=y;
	}
	
	public void changeAction( int action )
	{		
		actionName		= action;
		frames			= 0;
		framesjiange	= 2;
		
//		if(isSpeedDown)
//		{
//			framesjiange	= 10;
//		}
	}
	
	public void setSlowDown(boolean _isSlowDown)
	{
//		if(isSlowDown)
//			framesjiange	= 10;
//		else
//			framesjiange	= 2;
		
//		isSpeedDown = _isSlowDown;
	}
	
	public int getActionName()
	{
		return actionName;
	}
	
	public int getframes()
	{
		return frames;
	}
	
	public int getMaxFrames()
	{
		return CoolEditData.npcItem0[kind][actionName].length*framesjiange;
		
	}				
	public void updataSprite(){						
		if(state>0&&kind>-1){			
//			frames++;						
					
			sirenLight.run();
	
//			if(speedDownTime>0)
//			{
//				if(speedDownTime%5!=0)
//				{
//					return;
//				}	
//			}
			
			if(kind == CoolEditDefine.Enemy_Z||
			   kind == CoolEditDefine.Enemy_SHZ||
			   kind == CoolEditDefine.Enemy_YGZ)
			{							
				if(actionName==Enemy_action05)//���
				{		
					if(!isForcedStop)
					{					
						if(dizzinessTime<=0)//û��ѣ��
						{
							frames++;
							
							if(y!=orgbyMoveStop)
							{
								frames++;
								
								if(frames>5)
								{
									y += 10;
									
									if(y>=orgbyMoveStop)
									{
										y=orgbyMoveStop;
									}
								}
							}
						}
						else
						{					
							int l = (int)(y+ExternalMethods.throwDice(150, 200));
							
							byMoveStop = l>orgbyMoveStop?orgbyMoveStop:l;
							
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					}
					else
					{
						frames++;
					}
				}
				else
				{					
					if(dizzinessTime<=0||//û��ѣ��
					   actionName==Enemy_action0||
					   actionName==Enemy_action02)
					{
						frames++;						
					}		
				}
			}
			else if(kind == CoolEditDefine.Enemy_SHHM)
			{				
				if(actionName==Enemy_action18)
				{
					if(dizzinessTime<=0)//û��ѣ��
					{
						frames++;	
						
						GameMonsterMove.Jump(this, false);
					}
					else
					{
						changeAction(Enemy_action03);
						
						setState(SPRITE_STATE_STAND);
					}
				}
				else if(actionName==Enemy_action19)
				{
					if(dizzinessTime<=0)//û��ѣ��
					{
						frames++;	
						
						GameMonsterMove.Jump(this, true);
					}
					else
					{
						changeAction(Enemy_action03);
						
						setState(SPRITE_STATE_STAND);
					}
				}
				else
				{
					if(dizzinessTime<=0||//û��ѣ��
					   actionName==Enemy_action0||
					   actionName==Enemy_action02)
					{
						frames++;						
					}	
				}								
			}
			else
			{								
				if(dizzinessTime<=0||//û��ѣ��
				   actionName==Enemy_action0||
				   actionName==Enemy_action02)
				{
					frames++;						
				}										
			}
			
			if(frames>=CoolEditData.npcItem0[kind][actionName].length*framesjiange){
				frames=0;
				
				if(SpriteLibrary.isEnemy(kind) == SpriteLibrary.isPlayer)
				{
					if(kind == CoolEditDefine.Player_FQ||
					   kind == CoolEditDefine.Player_FQ_2||
					   kind == CoolEditDefine.Player_FQ_3||
					   kind == CoolEditDefine.Player_WD||
					   kind == CoolEditDefine.Player_WD_2||
					   kind == CoolEditDefine.Player_WD_3||
					   kind == CoolEditDefine.Player_LJ||
					   kind == CoolEditDefine.Player_LJ_2||
					   kind == CoolEditDefine.Player_LJ_3||
					   kind == CoolEditDefine.Player_YC||
					   kind == CoolEditDefine.Player_YC_2||
					   kind == CoolEditDefine.Player_YC_3||
					   kind == CoolEditDefine.Player_LB||
					   kind == CoolEditDefine.Player_LB_2||
					   kind == CoolEditDefine.Player_LB_3||
					   kind == CoolEditDefine.Player_TD||
					   kind == CoolEditDefine.Player_TD_2||
					   kind == CoolEditDefine.Player_TD_3||
					   kind == CoolEditDefine.Player_MG||
					   kind == CoolEditDefine.Player_MG_2||
					   kind == CoolEditDefine.Player_MG_3||
					   kind == CoolEditDefine.Player_HC||
					   kind == CoolEditDefine.Player_HC_2||
					   kind == CoolEditDefine.Player_HC_3||
					   kind == CoolEditDefine.Player_ZS||
					   kind == CoolEditDefine.Player_ZS_2||
					   kind == CoolEditDefine.Player_ZS_3||
					   kind == CoolEditDefine.Player_NG||
					   kind == CoolEditDefine.Player_NG_2||
					   kind == CoolEditDefine.Player_NG_3)
					{
//						if(actionName==5||actionName==4)
//						{
//							setState(SPRITE_STATE_NONE);
//						}
					}
					
					if(kind == CoolEditDefine.Player_TD||
					   kind == CoolEditDefine.Player_TD_2||
					   kind == CoolEditDefine.Player_TD_3)
					{
						if(actionName==7)
						{
							setState(SPRITE_STATE_NONE);
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.presss, 0);
						}
					}
					else 
					if(kind==CoolEditDefine.Player_MG||
					   kind==CoolEditDefine.Player_MG_2||
					   kind==CoolEditDefine.Player_MG_3)	
					{												
						if(actionName==7)
						{
							if(lifeTime>0)
							{
								changeAction(6);
								
								clearTouchSprite();
							}
							else	
								setState(SPRITE_STATE_NONE);
						}
					}
					else 
					if(kind==CoolEditDefine.Player_HC||
					   kind==CoolEditDefine.Player_HC_2||
					   kind==CoolEditDefine.Player_HC_3)	
					{												
						if(actionName==7)
						{								
							setState(SPRITE_STATE_NONE);
						}
					}					
				}
				
				if(SpriteLibrary.isEnemy(kind) == SpriteLibrary.isEnemy)
				{
//					if(isGlide)
//					return;
					
					if(kind == CoolEditDefine.Enemy_SHHM)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01)
						{					    						    						    	
					    	changeAction(Enemy_action03);
								
							setState(SPRITE_STATE_STAND);	
									
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
							
//							size = 1f;
						}
						else if(actionName==Enemy_action05)
						{																	
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
							
//							size = 1.2f;
						}
						else if(actionName==Enemy_action18||
								actionName==Enemy_action19)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}						
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_SHHT)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01||
					    		actionName==Enemy_action15)
						{					    						    	
					    	if(byMoveWaitingTime>0)
					    	{
					    		changeAction(Enemy_action03);
					    		
								setState(SPRITE_STATE_STAND);
					    	}
					    	else
					    	{
					    		if(actionName==Enemy_action01)
					    			changeAction(Enemy_action04);
					    		else 
					    			changeAction(Enemy_action16);
								
								setState(SPRITE_STATE_MOVE);
					    	}
//					    	
//					    	if(attackTime<=0)
//							{
//								attackTime = orgAttackTime;
//							}
					    	
					    	if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05||actionName==Enemy_action17)
						{			
							if(!isUpState)
							GameMain.spriteLattice.spriteLatticeLostBlood(this);
							
							setState(SPRITE_STATE_NONE);	
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.littlebombs, 0);
							
							life = 0;
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_MIMI||
					   kind == CoolEditDefine.Enemy_SHMM||
					   kind == CoolEditDefine.Enemy_YGMM)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01)
						{					    						    	
					    	if(byMoveWaitingTime>0)
					    	{
					    		changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
					    	}
					    	else
					    	{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
					    	}
					    	
					    	if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
					    	
					    	if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05)
						{			
							if(!isForcedStop)
							{
								if(!isUpState)
								GameMain.spriteLattice.spriteLatticeLostBlood(this);
								
								attackTime = orgAttackTime;	
							}
							else
							{
								changeAction(Enemy_action04);							
							}
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_DS||
					   kind == CoolEditDefine.Enemy_YGDS)
					{
						if(actionName==Enemy_action0||
						   actionName==Enemy_action01||
						   actionName==Enemy_action07)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(actionName==Enemy_action01)
							{
								if(speedDownTime>0)
						    	{
						    		setSlowDown(true);
						    	}
							}
						}
						else if(actionName==Enemy_action05)
						{										
							if(!isUpState)
							GameMain.spriteLattice.spriteLatticeLostBlood(this);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
						else if(actionName==Enemy_action06)
						{
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
						}
					}
					else 
					if(kind == CoolEditDefine.Enemy_X||
					   kind == CoolEditDefine.Enemy_SHX||
					   kind == CoolEditDefine.Enemy_YGX)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01)
						{
					    	if(byMoveWaitingTime>0)
					    	{
					    		changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
					    	}
					    	else
					    	{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
					    	}
					    	
					    	if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
					    	
					    	if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05)
						{			
							if(!isForcedStop)
							{
								if(!isUpState)								
								GameMain.spriteLattice.spriteLatticeLostBlood(this);
								
								attackTime = orgAttackTime;
							}
							else
							{
								changeAction(Enemy_action04);
							}
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}
						else if(actionName==Enemy_action20)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
							
							if(kind == CoolEditDefine.Enemy_SHX)
								GameMonster.shxSkillTime = 75;
							
							if(kind == CoolEditDefine.Enemy_X)
							{
								GameMonster.xIsSkill = true;
								
								if(!VeggiesData.isMuteSound())
								GameMedia.playSound(R.raw.bear1s, 0);
							}
						}
					}
					else
					if(kind == CoolEditDefine.Enemy_Z||
					   kind == CoolEditDefine.Enemy_SHZ||
					   kind == CoolEditDefine.Enemy_YGZ)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01)
						{
					    	if(byMoveWaitingTime>0)
					    	{
					    		int l = (int)(y+ExternalMethods.throwDice(150, 200));
								
								byMoveStop = l>orgbyMoveStop?orgbyMoveStop:l;
					    		
					    		changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
					    	}
					    	else
					    	{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
					    	}
					    	
					    	if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
					    	
					    	if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05)
						{			
							if(y!=orgbyMoveStop)
							{
								int l = (int)(y+ExternalMethods.throwDice(150, 200));
								
								byMoveStop = l>orgbyMoveStop?orgbyMoveStop:l;
								
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);								
							}
							else
							{
								if(!isUpState)
								GameMain.spriteLattice.spriteLatticeLostBlood(this);
								
								attackTime = orgAttackTime;
							}
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}
						else if(actionName==Enemy_action21)
						{
							
						}						
					}
					else
					if(kind == CoolEditDefine.Enemy_HZ||
					   kind == CoolEditDefine.Enemy_YGHZ)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{
							if(attackTime>0)
							{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
							}
							else
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}	
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05)
						{				
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_HZXJ)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action05);
							
							setState(SPRITE_STATE_ATTACK);							
						}					
					}
					else
					if(kind == CoolEditDefine.Enemy_MM)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{							
							if(attackTime>0)
							{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
							}
							else
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}	
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05||
								actionName==Enemy_action08||
								actionName==Enemy_action09||
								actionName==Enemy_action10)
						{				
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_MMJS||
					   kind == CoolEditDefine.Enemy_SHZYXZ)
					{
						
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action05);
							
							setState(SPRITE_STATE_ATTACK);							
						}
					}
					else
					if(kind == CoolEditDefine.Enemy_MMB1||
					   kind == CoolEditDefine.Enemy_MMB2||
					   kind == CoolEditDefine.Enemy_MMB3||
				       kind == CoolEditDefine.Enemy_MMB4)
					{
						if(actionName==Enemy_action12)
						{
							changeAction(Enemy_action11);
							
							setState(SPRITE_STATE_MOVE);							
						}
						else if(actionName==Enemy_action14)
						{
							changeAction(Enemy_action13);
							
							setState(SPRITE_STATE_MOVE);							
						}
					}
					else
					if(kind == CoolEditDefine.Enemy_CHG)
					{
						if(actionName==Enemy_action01)
						{
							life = 0;
							
							setState(SPRITE_STATE_NONE);													
						}
					}
					else 
					if(kind == CoolEditDefine.Enemy_YGXTM)//�¹�������
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{							
							changeAction(Enemy_action03);
								
							setState(SPRITE_STATE_STAND);
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}
					}	
					else
					if(kind == CoolEditDefine.Enemy_SHZY)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{
							if(attackTime>0)
							{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
							}
							else
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}	
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05||
								actionName==Enemy_action22||
								actionName==Enemy_action23)
						{				
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else 
					if(kind == CoolEditDefine.Enemy_SHZYCS)//����㴥��
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{							
							changeAction(Enemy_action03);
								
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action05)
						{				
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);			
							
							if(!isUpState)
							GameMain.spriteLattice.spriteLatticeLostBlood(this);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else
					if(kind == CoolEditDefine.Enemy_YGY)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{							
							if(attackTime>0)
							{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
							}
							else
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}	
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05||
								actionName==Enemy_action24||
								actionName==Enemy_action25||
								actionName==Enemy_action26)
						{				
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_YGYYM)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action05);
							
							setState(SPRITE_STATE_ATTACK);							
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}								
					}
					else
					if(kind == CoolEditDefine.Enemy_YGYKS)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action05);
							
							setState(SPRITE_STATE_ATTACK);							
						}											
					}	
					else
					if(kind == CoolEditDefine.Enemy_YGYJF)
					{
						if(actionName==Enemy_action0||
						   actionName==Enemy_action01)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}											
					}
					else
					if(kind == CoolEditDefine.Enemy_SIREN)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}						
						else if(actionName==Enemy_action02)
						{							
							setState(SPRITE_STATE_NONE);													
						}
						else if(actionName==Enemy_action01
//								||actionName==Enemy_action28
								)
						{
//							if(actionName==Enemy_action01)
								changeAction(Enemy_action03);
//							else
//								changeAction(Enemy_action29);
																										
//							if(attackTime>125)
//								setState(SPRITE_STATE_MOVE);
//							else
//								setState(SPRITE_STATE_ATTACK);
//							
//							byMoveWaitingTime = 25;
								
							setState(SPRITE_STATE_STAND);
							
							byMoveWaitingTime = 25;
							
							sirenLight.setShow(false);
						}						
					}
					else
					if(kind == CoolEditDefine.Enemy_MEGICWATER)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
//						else if(actionName==Enemy_action01)
//						{		
//							if(linkNumber==0)
//							{
//								changeAction(Enemy_action03);
//								
//								setState(SPRITE_STATE_STAND);	
//							}
//							else
//							{
//								changeAction(Enemy_action27);
//								
//								setState(SPRITE_STATE_MOVE);	
//							}
//						}
						else if(actionName==Enemy_action02)
						{							
							setState(SPRITE_STATE_NONE);													
						}
					}					
				}
				
				if(SpriteLibrary.isEnemy(kind)==SpriteLibrary.isEffect)
				{
//					if(kind==SpriteLibrary.Effect_ICESTATE)
//					{
//						if(actionName==1)
//						{
//							setState(SPRITE_STATE_NONE);
//						}
//					}
//					else if(kind==SpriteLibrary.Effect_COLD)
//					{
//						if(actionName==0)
//						{
//							changeAction(2);
//						}
//						else if(actionName==1)
//						{
//							setState(SPRITE_STATE_NONE);
//						}						
//					}
					if(kind==CoolEditDefine.SMALL_CARD_BOX)
					{
						if(actionName==1)
						{
							changeAction(0);
						}
					}					
					else if(kind==CoolEditDefine.Effect_ICESTATELV1||
					   kind==CoolEditDefine.Effect_ICESTATELV2||
					   kind==CoolEditDefine.Effect_ICESTATELV3)
					{
						if(actionName==1)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else if(/*kind==SpriteLibrary.Effect_SUMMON||*/
							kind==CoolEditDefine.Effect_SHIELD||
							kind==CoolEditDefine.Effect_SLOWDOWN||
							kind==CoolEditDefine.Effect_DIZZINESS||
							kind==CoolEditDefine.Effect_HEADFIRE||
							kind==CoolEditDefine.SMALL_CARD||
							kind==CoolEditDefine.SLINGSHOT1||
							kind==CoolEditDefine.SLINGSHOT2||
							kind==CoolEditDefine.SLINGSHOT3||
							kind==CoolEditDefine.SLINGSHOT4)
					{
						
					}					
					else
					{
						if(actionName==0)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
				}
				
				if(SpriteLibrary.isEnemy(kind)==SpriteLibrary.isObstruct)
				{
					if(/*kind==SpriteLibrary.Effect_COLDBOMB||*/
					   kind==CoolEditDefine.Effect_BOMB||					  
					   kind==CoolEditDefine.Effect_BOMBLV1||
					   kind==CoolEditDefine.Effect_BOMBLV2||
					   kind==CoolEditDefine.Effect_BOMBLV3)
					{
						if(actionName == 0)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else 
					if(kind==CoolEditDefine.Effect_SPRINGBOARD)
					{
						if(actionName == 1)
						{
							changeAction(0);
						}
					}
					else 
					if(/*kind==SpriteLibrary.Effect_COLDBOMB||*/
					   kind==CoolEditDefine.Effect_COLD)
					{
						if(actionName == 1)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else 
					if(kind==CoolEditDefine.Effect_BANANA)
					{
						if(actionName == 0)
						{
							changeAction(2);
						}
						else if(actionName == 1)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else 
					if(kind==CoolEditDefine.Effect_ROSE||
					   kind==CoolEditDefine.Effect_ROSETHIRDATTACK)
					{
						if(actionName == 0)
						{
							changeAction(1);
						}
					}
					else if(kind==CoolEditDefine.Effect_ROSESECOND||
							kind==CoolEditDefine.Effect_ROSETHIRD)
					{
						if(actionName == 0)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else 
					if(kind==CoolEditDefine.Effect_MAGICDOOR)
					{
						if(actionName == 0)
						{
							changeAction(1);
						}
						else if(actionName == 2)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
				}
			}
		}
	}
	
	public void paintSprite(Canvas canvas,int yi_x,int yi_y){
//		SpriteLibrary.paintSprite(canvas, kind, yi_x+x, yi_y+y, actionName, frames,size,Alpha,jiaodu);
		if(state>0&&kind>-1){
			canvas.save();
			SpriteLibrary.paintSprite(canvas, kind, (float)(yi_x+x), 
					(float)(yi_y+y), actionName, frames/framesjiange,size*GameConfig.f_zoom,Alpha,jiaodu,r,g,b,null);
						
			canvas.rotate(-jiaodu, x, y);
			
//			if(SpriteLibrary.isEnemy(kind)==SpriteLibrary.isObstruct)
//			{				
//				setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
//								(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size/2, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y)+SpriteLibrary.GetH(kind)*size/2);
//			}
//			else
//			{		
//				if(kind == SpriteLibrary.Player_FQ||
//				   kind == SpriteLibrary.Player_FQ_2||
//				   kind == SpriteLibrary.Player_FQ_3||
//				   kind == SpriteLibrary.Player_WD||
//				   kind == SpriteLibrary.Player_WD_2||
//				   kind == SpriteLibrary.Player_WD_3||
//				   kind == SpriteLibrary.Player_LJ||
//				   kind == SpriteLibrary.Player_LJ_2||
//				   kind == SpriteLibrary.Player_LJ_3||
//				   kind == SpriteLibrary.Player_YC||
//				   kind == SpriteLibrary.Player_YC_2||
//				   kind == SpriteLibrary.Player_YC_3||
//				   kind == SpriteLibrary.Player_LB||
//				   kind == SpriteLibrary.Player_LB_2||
//				   kind == SpriteLibrary.Player_LB_3||
//				   kind == SpriteLibrary.Player_TD||
//				   kind == SpriteLibrary.Player_TD_2||
//				   kind == SpriteLibrary.Player_TD_3||
//				   kind == SpriteLibrary.Player_MG||
//				   kind == SpriteLibrary.Player_MG_2||
//				   kind == SpriteLibrary.Player_MG_3||
//				   kind == SpriteLibrary.Player_HC||
//				   kind == SpriteLibrary.Player_HC_2||
//				   kind == SpriteLibrary.Player_HC_3||
//				   kind == SpriteLibrary.Player_ZS||
//				   kind == SpriteLibrary.Player_ZS_2||
//				   kind == SpriteLibrary.Player_ZS_3||
//				   kind == SpriteLibrary.Player_NG||
//				   kind == SpriteLibrary.Player_NG_2||
//				   kind == SpriteLibrary.Player_NG_3)
//				{
//					setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
//									(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
//				}
//				else
//				{
//					if(kind == SpriteLibrary.Enemy_MEGICWATER)
//					{
//						if(getActionName()==Enemy_action27)
//						{
//							setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*3*size/2, 
//									(float)(yi_y+y)-SpriteLibrary.GetH(kind)*4*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*3*size/2, (float)(yi_y+y));
//						}
//						else
//						{
//							setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
//									(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
//						}
//					}
//					else
//					{
//						setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
//										(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
//					}
//				}
//			}
			
			canvas.restore();
			
//			if(SpriteLibrary.isEnemy(kind)==SpriteLibrary.isObstruct)
//			{				
//				setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
//								(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size/2, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y)+SpriteLibrary.GetH(kind)*size/2);
//			}
//			else
//			{		
//				if(kind == SpriteLibrary.Player_FQ||
//				   kind == SpriteLibrary.Player_FQ_2||
//				   kind == SpriteLibrary.Player_FQ_3||
//				   kind == SpriteLibrary.Player_WD||
//				   kind == SpriteLibrary.Player_WD_2||
//				   kind == SpriteLibrary.Player_WD_3||
//				   kind == SpriteLibrary.Player_LJ||
//				   kind == SpriteLibrary.Player_LJ_2||
//				   kind == SpriteLibrary.Player_LJ_3||
//				   kind == SpriteLibrary.Player_YC||
//				   kind == SpriteLibrary.Player_YC_2||
//				   kind == SpriteLibrary.Player_YC_3||
//				   kind == SpriteLibrary.Player_LB||
//				   kind == SpriteLibrary.Player_LB_2||
//				   kind == SpriteLibrary.Player_LB_3||
//				   kind == SpriteLibrary.Player_TD||
//				   kind == SpriteLibrary.Player_TD_2||
//				   kind == SpriteLibrary.Player_TD_3||
//				   kind == SpriteLibrary.Player_MG||
//				   kind == SpriteLibrary.Player_MG_2||
//				   kind == SpriteLibrary.Player_MG_3||
//				   kind == SpriteLibrary.Player_HC||
//				   kind == SpriteLibrary.Player_HC_2||
//				   kind == SpriteLibrary.Player_HC_3||
//				   kind == SpriteLibrary.Player_ZS||
//				   kind == SpriteLibrary.Player_ZS_2||
//				   kind == SpriteLibrary.Player_ZS_3||
//				   kind == SpriteLibrary.Player_NG||
//				   kind == SpriteLibrary.Player_NG_2||
//				   kind == SpriteLibrary.Player_NG_3)
//				{
//					setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
//									(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
//				}
//				else
//				{
//					if(kind == SpriteLibrary.Enemy_MEGICWATER)
//					{
//						if(getActionName()==Enemy_action27)
//						{
//							setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*3*size/2, 
//									(float)(yi_y+y)-SpriteLibrary.GetH(kind)*4*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*3*size/2, (float)(yi_y+y));
//						}
//						else
//						{
//							setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
//									(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
//						}
//					}
//					else
//					{
//						setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
//										(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
//					}
//				}
//			}
			
			setSpriteCollisionRect(yi_x, yi_y);
			
			//----------------- ��ʾ��ײ�� --------------------------------
//			paint.setColor(Color.GREEN);
//			paint.setStyle(Style.STROKE);			
//			
//			RectF f = getCollisionRect();						
//			
//			canvas.drawRect(f.left, f.top, f.right, f.bottom, paint);	
			//------------------------------------------------------------
			
//			canvas.restore();
			
			sirenLight.draw(canvas);
			
			paintSpriteBlood(canvas);
		}
	}
	
	public void drawBitmap(Canvas canvas, Bitmap image, float x, float y, Paint paint)
    {
        canvas.drawBitmap(image, x, y, paint);
    }
	
    public void drawBitmap(Canvas canvas, Bitmap image, Matrix matrix, Paint paint)
    {
        canvas.drawBitmap(image, matrix, paint);
    }
    
    public void drawBitmap(Canvas canvas, Bitmap bitmap, float i_x, float i_y, Matrix matrix, int anchor,Paint paint)
    {
        GameLibrary.DrawBitmap(canvas, bitmap, i_x, i_y, matrix, anchor, paint);
    }
    /**
     * ���ƺ���������float
     * @param canvas
     * @param x	
     * @param y
     * @param sizeW
     * @param sizeH
     * @param Alpha
     * @param Rotate
     * @param RotateX
     * @param RotateY
     */
    public void drawBitmap(Canvas canvas, float x, Float y, float sizeW, Float sizeH, int Alpha, float Rotate, int RotateX, int RotateY){
    	ExternalMethods.drawImage(canvas, bitmap, x, y, sizeW, sizeH, Alpha, Rotate, RotateX, RotateY);
    }
    /**
     * ���ƺ���������int
     * @param canvas
     * @param x
     * @param y
     * @param sizeW
     * @param sizeH
     * @param Alpha
     * @param Rotate
     * @param RotateX
     * @param RotateY
     */
    public void drawBitmap(Canvas canvas, int x, int y, float sizeW, Float sizeH, int Alpha, float Rotate, int RotateX, int RotateY){
    	ExternalMethods.drawImage(canvas, bitmap, x, y, sizeW, sizeH, Alpha, Rotate, RotateX, RotateY);
    }
    /**
     * ���ƺ����ţ���ɫ����float
     * @param canvas
     * @param x	
     * @param y
     * @param sizeW
     * @param sizeH
     * @param Alpha
     * @param Rotate
     * @param RotateX
     * @param RotateY
     */
    public void drawBitmap(Canvas canvas, float x, float y, float sizeW, float sizeH, int Alpha, float Rotate, float RotateX, float RotateY, int r, int g, int b) {
    	ExternalMethods.drawImage(canvas, bitmap, x, y, sizeW, sizeH, Alpha, Rotate, RotateX, RotateY, r, g, b);
    }
    /**
     * ���ƴ��ͼ����֡
     */
    public void drawBitmap(Canvas canvas, Bitmap bmp, float x, float y, float sizeW, float sizeH, int Alpha, float Rotate, float RotateX, float RotateY, int r, int g, int b) {
    	ExternalMethods.drawImage(canvas, bmp, x, y, sizeW, sizeH, Alpha, Rotate, RotateX, RotateY, r, g, b);
    }
    /**
     * ���ƴ��ͼ����ɫ
     */
    public void drawBitmap(Canvas canvas,Paint paint,int Col,int i_alpha,int x,int y,int x2,int y2){
    	paint.setColor(Col);
		paint.setAlpha(i_alpha);
		Rect rect = new Rect(x,y,x2,y2);
		canvas.drawRect(rect, paint);
    };
    /**
     * ��������(����)(������)
     */
    public void drawBitmap(Canvas canvas, Sprite img_Number[],int x, int y,char Chars[],String n,Paint paint,int jianju,float size){
    	drawBitmap(canvas, img_Number,x, y, img_Number[0].bitmap.getHeight(),Chars,n,paint,jianju,size);
    };
    /**
     * ��������
     */
    private void drawBitmap(Canvas canvas, Sprite img_Number[],int x, int y, int h,char Chars[],String n,Paint paint,int jianju,float size) {
    	float w = (float) img_Number[0].bitmap.getWidth() * size;
		for (int i = 0; i < n.length(); i++) {
			for (int j = 0; j < Chars.length; j++) {
				if (n.charAt(i) == Chars[j]) {
					img_Number[j].drawBitmap(canvas,
							(int) (x + i * (w + jianju * size)), y, size, size,
							255, 0, 0, 0);
					break;
				}
			}
		}
    }
	//synge	�ݶ�ʹ�ã��������ŵ�������
	public void DrawNumber1(Canvas canvas, Bitmap img_Number[],int x, int y, int h,char Chars[],String n,Paint paint,int jianju,float size) {
		float w = (float) img_Number[0].getWidth() * size;
		for (int i = 0; i < n.length(); i++) {
			for (int j = 0; j < Chars.length; j++) {
				if (n.charAt(i) == Chars[j]) {
					ExternalMethods.drawImage(canvas, img_Number[j],
							(int) (x + i * (w + jianju * size)), y, size, size,
							255, 0, 0, 0);
					break;
				}
			}
		}
	}
	
	//���Ź���
	/**
	 * �Ź���
	 */
	public void drawBitmap(Canvas canvas,Paint paint,int x,int y,int w,int h ,int bgcol)
	{
		int unit_w	= bitmap.getWidth()/3;
		int unit_h	= bitmap.getHeight()/3;

		if(bgcol>=0){
			paint.setColor(bgcol);
			canvas.drawRect(x+4,y+4,x+4+w-8,y+4+h-8, paint);
		}else if(bgcol!=-100){
			// �м�
			for( int i=unit_w;i<w-unit_w;i+=unit_w )
			for( int j=unit_h;j<h-unit_h;j+=unit_h )
			{
				canvas.save();
				ExternalMethods.setClip(canvas, x+i,y+j,unit_w,unit_h, x+unit_w, y+unit_h,w-unit_w*2,h-unit_h*2);
//	        	canvas.clipRect( x+i,y+j,unit_w+x+i,unit_h+y+j);
				this.drawBitmap(canvas, this.bitmap, x+i-unit_w,y+j-unit_h,paint);
	        	canvas.restore();
//				g.setClip( x+i,y+j,unit_w,unit_h );
//				g.drawImage( Resource.img[imageID],x+i-unit_w,y+j-unit_h,Graphics.TOP|Graphics.LEFT );
			}
		}
		
		// ������
		for( int i=unit_w;i<w-unit_w;i+=unit_w )
		{
			canvas.save();
//        	canvas.clipRect(  x+i,y,unit_w+x+i,unit_h+y ); 
			ExternalMethods.setClip(canvas, x+i,y,unit_w,unit_h, x+unit_w, y ,w-unit_w*2, unit_h);
			this.drawBitmap(canvas, this.bitmap, x+i-unit_w,y,paint);
        	canvas.restore();
			canvas.save();
			ExternalMethods.setClip(canvas,x+i,y+h-unit_h,unit_w,unit_h, x+unit_w, y+h-unit_h, w-unit_w*2,unit_h);
//        	canvas.clipRect(x+i,y+h-unit_h,unit_w+x+i,unit_h+y+h-unit_h); 
			this.drawBitmap(canvas, this.bitmap,x+i-unit_w,y+h-this.bitmap.getHeight(),paint);
        	canvas.restore();
		}
		for (int j=unit_h;j<h-unit_h;j+=unit_h )
		{
			canvas.save();
//        	canvas.clipRect( x,y+j,unit_w+x,unit_h +y+j ); 
			ExternalMethods.setClip(canvas,x,y+j,unit_w,unit_h , x, y+unit_h, unit_w,h-unit_h*2);
			this.drawBitmap(canvas, this.bitmap,x,y+j-unit_h,paint);
        	canvas.restore();
			canvas.save();
			ExternalMethods.setClip(canvas,x+w-unit_w,y+j,unit_w,unit_h, x+w-unit_w, y+unit_h, unit_w,h-unit_h*2);
//        	canvas.clipRect(x+w-unit_w,y+j,unit_w+x+w-unit_w,unit_h+y+j ); 
			this.drawBitmap(canvas, this.bitmap,x+w-this.bitmap.getWidth(),y+j-unit_h,paint);
        	canvas.restore();
		}

		// �ĸ���
		canvas.save();
    	canvas.clipRect(x,y,unit_w+x,unit_h +y);
    	this.drawBitmap(canvas, this.bitmap,x,y,paint);
    	canvas.restore();
    	
		canvas.save();
    	canvas.clipRect(x+w-unit_w,y,unit_w+x+w-unit_w,unit_h +y); 
    	this.drawBitmap(canvas, this.bitmap,x+w-this.bitmap.getWidth(),y,paint);
    	canvas.restore();

		canvas.save();
    	canvas.clipRect( x,y+h-unit_h,x+unit_w,unit_h+y+h-unit_h ); 
    	this.drawBitmap(canvas, this.bitmap,x,y+h-this.bitmap.getHeight(),paint);
    	canvas.restore();
    	
		canvas.save();
    	canvas.clipRect(x+w-unit_w,y+h-unit_h,unit_w+x+w-unit_w,unit_h+y+h-unit_h); 
    	this.drawBitmap(canvas, this.bitmap,x+w-this.bitmap.getWidth(),y+h-this.bitmap.getHeight(),paint);
    	canvas.restore();
	}
	/**
	 * �������žŹ������
	 */
	public void drawBitmap(Canvas canvas,Paint paint,int x,int y,int w ,int bgcol) {
		int unit_w	= bitmap.getWidth()/3;
		if(bgcol>=0){
			paint.setColor(bgcol);
			canvas.drawRect(x+4,y+4,x+4+w-8,y+4+bitmap.getHeight()-8, paint);
		}else if(bgcol!=-100){
			for( int i=unit_w;i<w-unit_w;i+=unit_w ) {
				canvas.save();
				ExternalMethods.setClip(canvas, x+i,y,unit_w,bitmap.getHeight(), x+unit_w, y,w-unit_w*2,bitmap.getHeight());
				drawBitmap(canvas, bitmap, x+i-unit_w,y,paint);
	        	canvas.restore();
			}
			
			// �ĸ���
			canvas.save();
	    	canvas.clipRect(x,y,unit_w+x,bitmap.getHeight()+y);
	    	drawBitmap(canvas, bitmap,x,y,paint);
	    	canvas.restore();
	    	
	    	canvas.save();
	    	canvas.clipRect(x+w-unit_w,y,x+w,y+bitmap.getHeight()); 
	    	drawBitmap(canvas, bitmap,x+w-bitmap.getWidth(),y,paint);
	    	canvas.restore();
		}
	}
    
	//��Ӱ��
	public Paint paint=new Paint();
	
	public RectF oval=new RectF();
	
	public void paintSpriteShadow(Canvas canvas,float yiy, float showsize) {
		if(kind<0||state==Sprite.SPRITE_STATE_NONE){
			return;
		}
		
		paint.reset();

		paint.setColor(Color.BLACK);
		paint.setAlpha(100*Alpha/255);

		if(kind==CoolEditDefine.Effect_COLD)
		{
			oval.top=org_y-10*GameConfig.f_zoom*size*showsize+yiy;
			oval.bottom=org_y+10*GameConfig.f_zoom*size*showsize+yiy;
			oval.left=org_x-((SpriteLibrary.GetW(kind)/2-5*GameConfig.f_zoom))*size*showsize;
			oval.right=org_x+((SpriteLibrary.GetW(kind)/2-5*GameConfig.f_zoom))*size*showsize;	
		}
		else
		{		
			oval.top=y-10*GameConfig.f_zoom*(jumpState>0?1f:size)*showsize+yiy;
			oval.bottom=y+10*GameConfig.f_zoom*(jumpState>0?1f:size)*showsize+yiy;
			oval.left=x-((SpriteLibrary.GetW(kind)/2-5*GameConfig.f_zoom))*(jumpState>0?1f:size)*showsize;
			oval.right=x+((SpriteLibrary.GetW(kind)/2-5*GameConfig.f_zoom))*(jumpState>0?1f:size)*showsize;	
		}
		
		canvas.drawOval(oval, paint);			
	}
	
	private RectF collision = new RectF();
	
	public RectF getCollisionRect()
	{
		return collision;
	}
	
	public void paintCollisionRect(Canvas canvas, float left, float top, float right, float bottom) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		canvas.drawRect(left, top, right, bottom, p);
	}
	
	public void setCollisionRect(float left, float top, float right, float bottom)
	{
		collision.left   = left;
		collision.top    = top;
		collision.right  = right;
		collision.bottom = bottom;
	}
	
	private void setSpriteCollisionRect(int yi_x,int yi_y)
	{
		if(SpriteLibrary.isEnemy(kind)==SpriteLibrary.isObstruct)
		{				
			setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
							(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size/2, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y)+SpriteLibrary.GetH(kind)*size/2);
		}
		else
		{		
			if(kind == CoolEditDefine.Player_FQ||
			   kind == CoolEditDefine.Player_FQ_2||
			   kind == CoolEditDefine.Player_FQ_3||
			   kind == CoolEditDefine.Player_WD||
			   kind == CoolEditDefine.Player_WD_2||
			   kind == CoolEditDefine.Player_WD_3||
			   kind == CoolEditDefine.Player_LJ||
			   kind == CoolEditDefine.Player_LJ_2||
			   kind == CoolEditDefine.Player_LJ_3||
			   kind == CoolEditDefine.Player_YC||
			   kind == CoolEditDefine.Player_YC_2||
			   kind == CoolEditDefine.Player_YC_3||
			   kind == CoolEditDefine.Player_LB||
			   kind == CoolEditDefine.Player_LB_2||
			   kind == CoolEditDefine.Player_LB_3||
			   kind == CoolEditDefine.Player_TD||
			   kind == CoolEditDefine.Player_TD_2||
			   kind == CoolEditDefine.Player_TD_3||
			   kind == CoolEditDefine.Player_MG||
			   kind == CoolEditDefine.Player_MG_2||
			   kind == CoolEditDefine.Player_MG_3||
			   kind == CoolEditDefine.Player_HC||
			   kind == CoolEditDefine.Player_HC_2||
			   kind == CoolEditDefine.Player_HC_3||
			   kind == CoolEditDefine.Player_ZS||
			   kind == CoolEditDefine.Player_ZS_2||
			   kind == CoolEditDefine.Player_ZS_3||
			   kind == CoolEditDefine.Player_NG||
			   kind == CoolEditDefine.Player_NG_2||
			   kind == CoolEditDefine.Player_NG_3)
			{
				setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
								(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
			}
			else
			{
				if(kind == CoolEditDefine.Enemy_MEGICWATER)
				{
					if(getActionName()==Enemy_action27)
					{
						setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*3*size/2, 
								(float)(yi_y+y)-SpriteLibrary.GetH(kind)*4*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*3*size/2, (float)(yi_y+y));
					}
					else
					{
						setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
								(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
					}
				}
				else
				{
					setCollisionRect((float)(yi_x+x)-SpriteLibrary.GetW(kind)*size/2, 
									(float)(yi_y+y)-SpriteLibrary.GetH(kind)*size, (float)(yi_x+x)+SpriteLibrary.GetW(kind)*size/2, (float)(yi_y+y));
				}
			}
		}
		
		if(jiaodu==90||jiaodu==-90)
		{
			RectF f = getCollisionRect();
			
			int h = (int)(f.bottom - f.top);
			
			setCollisionRect(f.left, f.top+h/2, f.right, f.bottom+h/2);
		}
	}
	
	//------------------------- Ѫ�� --------------------------------
	public void paintSpriteBlood(Canvas canvas) {				
//		if(kind<0||state==Sprite.SPRITE_STATE_NONE){
//			return;
//		}
		
		if(kind!=CoolEditDefine.Enemy_MM&&
		   kind!=CoolEditDefine.Enemy_SHZY)
			return;
		
		if(life<=0||actionName==Enemy_action0)
			return;
		
		paint.reset();
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.STROKE);		
		paint.setStrokeWidth(1);
		
		RectF r = new RectF();
		
		r.left = x - SpriteLibrary.GetW(kind)/2;
		r.right = x + SpriteLibrary.GetW(kind)/2;
		r.top = y - SpriteLibrary.GetH(kind) - 10*GameConfig.f_zoomy;
		r.bottom = y - SpriteLibrary.GetH(kind) - 4*GameConfig.f_zoomy;
		
		canvas.drawRect(r, paint);
		
		paint.reset();
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);		
		
		r = new RectF();
		
		r.left = x - SpriteLibrary.GetW(kind)/2 + 1;
		r.right = x - SpriteLibrary.GetW(kind)/2 + 1 + (SpriteLibrary.GetW(kind)-2)*life/lifeMax;
		r.top = y - SpriteLibrary.GetH(kind) - 9*GameConfig.f_zoomy;
		r.bottom = y - SpriteLibrary.GetH(kind) - 5*GameConfig.f_zoomy;
		
		canvas.drawRect(r, paint);
	}
	
	public void setTouchSpriteId(long idNum)
	{
		touchArray.add(idNum);
	}
	
	public boolean isTouchSprite(long idNum)
	{
		boolean result = true;
		
		for(int i=0;i<touchArray.size();i++)
		{
			if(touchArray.get(i)==idNum)
			{
				result = false;
			}
		}
		
		return result;
	}
	
	public void clearTouchSprite()
	{
		touchArray.clear();
		
		touchArray = null;
		
		touchArray = new ArrayList<Long>();
		
//		for(int i=0;i<touchArray.size();i++)
//		{
//			touchArray.remove(i);
//		}
	}
}
