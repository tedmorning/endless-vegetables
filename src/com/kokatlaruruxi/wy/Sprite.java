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
	
	//--------------------------- 精灵移动 ----------------------------------//
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
	
	//--------------------------- 精灵攻击 ----------------------------------//
	public boolean isAttack;
	public int attackTime;
	public int orgAttackTime;
	public byte attackType;
	
	//--------------------------- 精灵弹飞 ----------------------------------//
	public boolean isFly;
	public int isFlyMoveDegree;
    public int isFlyMoveSpeed;
	
    //---------------------------- 精灵装弹等待 -----------------------------------//
    public int  reloadTime;  
    public int  reloadJumpStep;      
    public int  reloadJumpWaiting;
    public int  reloadJumpPoint;  
    
    //---------------------------- 精灵从远到近的过程  -----------------------------------//
    public boolean  isTransition;  
    public int  transition_x;  
    public int  transition_y;      
    public int  transitionStep;
    public int  transitionWaiting;
    
    //--------------------------- 精灵加速 ----------------------------------//
    public int  speedUpTime;
    
    //--------------------------- 精灵减速 ----------------------------------//
    public int  speedDownTime;
//    public boolean isSpeedDown;
    
    //--------------------------- 精灵眩晕 ----------------------------------//
    public int  dizzinessTime;
    public boolean isDizziness;
    
    //--------------------------- 精灵上升 ----------------------------------//   
    public boolean isUpState;    
    public boolean isUpAndLRState;
    
    //--------------------------- 精灵回复时间 ----------------------------------//
    public int recoverTime;
    
    //--------------------------- 精灵来回运动 ----------------------------------//
    public boolean comeBack;
    
    //--------------------------- 精灵打到怪物  ----------------------------------//
    public boolean isTouch;
    
    //--------------------------- 精灵组号 ----------------------------------//
    public int isGroupId;
    
    //--------------------------- 设置combo ----------------------------------//
    public boolean isCombo;
    
    //--------------------------- 改变动作时间  ----------------------------------//
    public int changeActionWaiting;
    
    //--------------------------- 精灵反弹次数 ----------------------------------//
    public int kickNumber;    
    
    //--------------------------- 效果持续时间 ----------------------------------//
    public int effectShowTime;
    
    //--------------------------- 特殊能力值 ----------------------------------//
    public int special;
    
    //--------------------------- 强制停止位置 ----------------------------------//
    public int stop_x;
    public int stop_y;
    
    //--------------------------- 冰冻状态  ----------------------------------//
    public boolean freezeState;
    public int freezeTime;
    
    //--------------------------- 有盾时间  ----------------------------------//
    public int shieldTime;
    
    //--------------------------- 复活时间  ----------------------------------//
    public int revivalTime;
    
    //--------------------------- 刷新时间  ----------------------------------//
    public int cdTime;
    public int cd;
    
    //--------------------------- 存活时间  ----------------------------------//
    public int lifeTime;
    
    //--------------------------- 怪物自动失血  ----------------------------------//
    public int lostBloodTime;//失血时间
    public int lostBloodAmount;//失血量
    public int lostBloodTimeOffset;//失血时间间隔    
    
    //-------------------------- 显示灼烧效果 ---------------------------------------//    
    public boolean isHeadFire;
    
    //--------------------------- 最大可杀死怪物数  ----------------------------------//
    public int killedMonsterMaxNumber;
    public int killedMonsterNumberIndex;
    
    //--------------------------- 改变大小  ----------------------------------//
    public boolean changeSize;
    
    //--------------------------- 跳跃状态  ----------------------------------//
    public byte jumpState;
    public byte jumpStep;
       
    //--------------------------- 怪物之间的链接  ----------------------------------//
    public int linkNumber;
    
    //------------------------- 怪物与效果之间的链接  ----------------------------------//
    public int freezeLinkNumber;
    public int fireLinkNumber;
    public int dizzinessLinkNumber;
    public int speedDownLinkNumber;
    
    //--------------------------- 魔法水滴的保护  ----------------------------------//
    public boolean magicWaterProtect;
    
    //--------------------------- 怪物血变1 ----------------------------------//
    public boolean bloodIsOne;
        
    //--------------------------- 警报器光速 ---------------------------------//
    public SirenLight sirenLight;
    
    //--------------------------- 召唤数量  ---------------------------------//
    public int summonsNumber;
    
    //--------------------------- 怪物滑行  ---------------------------------//
    public boolean isGlide;    
    public int glideLinkNumber;   
    
    //------------------------------ 强制停止 ------------------------------//
    public boolean isForcedStop;
    
    //--------------------------- 玫瑰刺时间  ----------------------------------//
    public int roseThornTime;

	//-----------------------	 State	-------------------------------//
	public static final byte SPRITE_STATE_NONE		= 0; // 无效状态
	public static final byte SPRITE_STATE_NORMAL	= 1; // 通常状态
	public static final byte SPRITE_STATE_STAND		= 2; // 站立状态
	public static final byte SPRITE_STATE_MOVE		= 3; // 移动状态
	public static final byte SPRITE_STATE_ATTACK	= 4; // 攻击状态
	public static final byte SPRITE_STATE_INJURE	= 5; // 受伤状态
	public static final byte SPRITE_STATE_DEAD		= 6; // 死亡状态
	
	public static final byte SPRITE_STATE_BURROW	= 7; // 钻地状态
	public static final byte SPRITE_STATE_GROUND	= 8; // 出地状态
	
	public static final byte SPRITE_STATE_VIOLENT	= 9; // 狂暴状态
	public static final byte SPRITE_STATE_SUMMON	= 10;// 召唤状态	
	public static final byte SPRITE_STATE_RESTORE	= 11;// 恢复状态
	
	public static final byte SPRITE_STATE_REVIVAL	= 12;// 复活状态	
		
	//-----------------------	Enemy actionName	-------------------------------//
	public static final byte Enemy_action0=0;//出现
	public static final byte Enemy_action01=1;//被攻击
	public static final byte Enemy_action02=2;//死亡
	public static final byte Enemy_action03=3;//待机
	public static final byte Enemy_action04=4;//移动
	public static final byte Enemy_action05=5;//攻击
	
	public static final byte Enemy_action06=6;//钻地	
	public static final byte Enemy_action07=7;//出地
	
	public static final byte Enemy_action08=6;//狂暴	
	public static final byte Enemy_action09=7;//召唤
	public static final byte Enemy_action10=8;//回血
	
	public static final byte Enemy_action11=0;//小兰怪从右向左移动	
	public static final byte Enemy_action12=1;//小兰怪从右向左受伤
	public static final byte Enemy_action13=2;//小兰怪从左向右移动
	public static final byte Enemy_action14=3;//小兰怪从左向右受伤
	
	public static final byte Enemy_action15=6;//深海河豚被攻击朝左
	public static final byte Enemy_action16=7;//深海河豚移动朝左
	public static final byte Enemy_action17=8;//深海河豚攻击朝左
	
	public static final byte Enemy_action18=6;//深海蛤蟆左跳跃
	public static final byte Enemy_action19=7;//深海蛤蟆右跳跃
	
	public static final byte Enemy_action20=6;//月光熊召唤藤蔓
	
	public static final byte Enemy_action21=6;//月光猪墓碑
	
	public static final byte Enemy_action22=6;//深海章鱼召唤
	public static final byte Enemy_action23=7;//深海章鱼喷墨
		
	public static final byte Enemy_action24=6;//月光鹰飓风
	public static final byte Enemy_action25=7;//月光鹰羽毛攻击
	public static final byte Enemy_action26=8;//月光鹰召唤
	
	public static final byte Enemy_action27=6;//魔法水滴变大
	
//	public static final byte Enemy_action28=6;//警报器从左向右被攻击
//	public static final byte Enemy_action29=8;//警报器从左向右待机
	public static final byte Enemy_action30=6;//警报器从左向右移动
//	public static final byte Enemy_action31=10;//警报器从左向右技能
	

//	public static final byte Enemy_action32=6;//香蕉皮滑动
	
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
//	public static final byte MoveStyle0=0;//正常移动状态
//	public static final byte MoveStyle01=1;//移动到目标点
//	
////-----------------------	Enemy actionName	-------------------------------//
//	public static final byte Enemy_action0=0;//出现
//	public static final byte Enemy_action01=1;//被攻击
//	public static final byte Enemy_action02=2;//待机
//	public static final byte Enemy_action03=3;//移动
//	public static final byte Enemy_action04=4;//攻击
//	
//	//-----------------------	Player actionName	-------------------------------//
//	public static final byte Player_action0=0;//待机
//	public static final byte Player_action01=1;//待机
//	public static final byte Player_action02=2;//上膛
//	public static final byte Player_action03=3;//拖动
//	public static final byte Player_action04=4;//移动
//	public static final byte Player_action05=5;//站立
//	public static final byte Player_action06=6;//攻击
	
	public static final int PLAYER_SPECIAL_0 = 0;//正常移动
	public static final int PLAYER_SPECIAL_1 = 1;//减速
	public static final int PLAYER_SPECIAL_2 = 2;//眩晕
	public static final int PLAYER_SPECIAL_3 = 3;//弹飞
	public static final int PLAYER_SPECIAL_4 = 4;//点燃
	
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
	    
	    special = 0;//0：普通	1：减速	  2：眩晕
	    
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
				if(actionName==Enemy_action05)//冲刺
				{		
					if(!isForcedStop)
					{					
						if(dizzinessTime<=0)//没有眩晕
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
					if(dizzinessTime<=0||//没有眩晕
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
					if(dizzinessTime<=0)//没有眩晕
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
					if(dizzinessTime<=0)//没有眩晕
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
					if(dizzinessTime<=0||//没有眩晕
					   actionName==Enemy_action0||
					   actionName==Enemy_action02)
					{
						frames++;						
					}	
				}								
			}
			else
			{								
				if(dizzinessTime<=0||//没有眩晕
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
					if(kind == CoolEditDefine.Enemy_YGXTM)//月光熊藤蔓
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
					if(kind == CoolEditDefine.Enemy_SHZYCS)//深海章鱼触手
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
			
			//----------------- 显示碰撞框 --------------------------------
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
     * 绘制含缩放坐标float
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
     * 绘制含缩放坐标int
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
     * 绘制含缩放，颜色坐标float
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
     * 绘制大地图动画帧
     */
    public void drawBitmap(Canvas canvas, Bitmap bmp, float x, float y, float sizeW, float sizeH, int Alpha, float Rotate, float RotateX, float RotateY, int r, int g, int b) {
    	ExternalMethods.drawImage(canvas, bmp, x, y, sizeW, sizeH, Alpha, Rotate, RotateX, RotateY, r, g, b);
    }
    /**
     * 绘制大地图背景色
     */
    public void drawBitmap(Canvas canvas,Paint paint,int Col,int i_alpha,int x,int y,int x2,int y2){
    	paint.setColor(Col);
		paint.setAlpha(i_alpha);
		Rect rect = new Rect(x,y,x2,y2);
		canvas.drawRect(rect, paint);
    };
    /**
     * 绘制数字(单行)(可缩放)
     */
    public void drawBitmap(Canvas canvas, Sprite img_Number[],int x, int y,char Chars[],String n,Paint paint,int jianju,float size){
    	drawBitmap(canvas, img_Number,x, y, img_Number[0].bitmap.getHeight(),Chars,n,paint,jianju,size);
    };
    /**
     * 绘制数字
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
	//synge	暂定使用，可以缩放单行数字
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
	
	//画九宫格
	/**
	 * 九宫格
	 */
	public void drawBitmap(Canvas canvas,Paint paint,int x,int y,int w,int h ,int bgcol)
	{
		int unit_w	= bitmap.getWidth()/3;
		int unit_h	= bitmap.getHeight()/3;

		if(bgcol>=0){
			paint.setColor(bgcol);
			canvas.drawRect(x+4,y+4,x+4+w-8,y+4+h-8, paint);
		}else if(bgcol!=-100){
			// 中间
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
		
		// 四条边
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

		// 四个角
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
	 * 横向缩放九宫格简易
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
			
			// 四个角
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
    
	//画影子
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
	
	//------------------------- 血条 --------------------------------
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
