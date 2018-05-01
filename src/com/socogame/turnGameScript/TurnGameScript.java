package com.socogame.turnGameScript;

import com.kokatlaruruxi.wy.GameTeaching;
import com.socogame.coolEdit.CoolEditDefine;

public class TurnGameScript {

	public static final byte Refresh  = 0;//横向刷同一种怪指定数量 {Refresh，种类，y，数量，x, x....}
	public static final byte Refresh1 = 1;//纵向刷同一种怪指定位置 {Refresh1，种类，y，x，数量，间隔}
	public static final byte Wait 	  = 2;//刷怪等待  {Wait，等待秒}，负数为随机区间值
	public static final byte Fog 	  = 3;//是否有迷雾{Fog}
	public static final byte Teleport = 4;//是否有时空门{Teleport, 入口类型（0不动，1移动），出口类型（0左边不动，1左边移动，2右边不动，3右边移动）}
	public static final byte Board 	  = 5;//是否有跳板{Board，x，y，x1，y1....}
	public static final byte OpenFight1Music = 6;//打开战斗场景1音乐new short[]{OpenFight1Music}
	public static final byte OpenFight2Music = 7;//打开战斗场景2音乐new short[]{OpenFight2Music}
	public static final byte OpenFight3Music = 8;//打开战斗场景3音乐new short[]{OpenFight3Music}
	public static final byte OpenFight4Music = 9;//打开战斗场景4音乐new short[]{OpenFight4Music}
	public static final byte OpenBossMusic 	= 10;//打开boss关卡场景音乐new short[]{OpenBossMusic}
	public static final byte OpenBubble 	= 11;//打开泡泡效果new short[]{OpenBubble}
	public static final byte CloseBubble 	= 12;//关闭泡泡效果new short[]{CloseBubble}	
	public static final byte TeachState 	= 13;//教学状态new short[]{TeachState, teachid}
	public static final byte ClothTouch 	= 14;//关闭触摸new short[]{ClothTouch}
	public static final byte OpenTouch 		= 15;//打开触摸new short[]{OpenTouch}
	public static final byte EnterTeach 	= 16;//是否进入教学new short[]{EnterTeach, teachid}
	
	public static final byte GoTo 	= 17;//脚本内部循环new short[]{GoTo，脚本索引值最小值， 脚本索引值最大值，返回值};
	
	//正常模式的刷怪
	public short ScriptData[][][][] =
	{
		script_1(),	
		script_2(),	
		script_3(),	
		script_4(),	
		script_5(),	
		script_6(),	
		script_7(),	
		script_8(),	
		script_9(),	
		script_10(),	
		script_11(),	
		script_12(),	
		script_13(),	
		script_14(),	
		script_15(),	
		script_16(),	
		script_17(),	
		script_18(),	
		script_19(),	
		script_20(),	
		script_21(),	
		script_22(),	
		script_23(),	
		script_24(),	
		script_25(),	
		script_26(),	
		script_27(),	
		script_26(),	
		script_29(),	
		script_30(),
		script_31(),	
		script_32(),	
		script_33(),	
		script_34(),	
		script_35(),	
		script_36(),	
		script_37(),	
		script_38(),	
		script_39(),	
		script_40(),	
		script_41(),	
		script_42(),	
		script_43(),	
		script_44(),	
		script_45(),	
		script_46(),	
		script_47(),	
		script_48(),	
		script_49(),	
		script_50(),	
		script_51(),	
		script_52(),	
		script_53(),	
		script_54(),	
		script_55(),	
		script_56(),	
		script_57(),	
		script_58(),	
		script_59(),	
		script_60(),
		
		//隐藏关卡
		script_61(),
		
	};
	
	//关卡时间
	public short ScriptTime[] =
	{
		30,//1-1
		30,//1-2
		30,//1-3
		30,//1-4
		30,//1-5
		30,//1-6
		45,//1-7
		45,//1-8
		45,//1-9
		45,//1-10
		45,//1-11
		45,//1-12
		60,//1-13
		60,//1-14
		60,//1-15
		60,//1-16
		60,//1-17
		60,//1-18
		60,//1-19
		60,//1-20
		60,//1-21
		60,//1-22
		60,//1-23
		60,//1-24
		75,//1-25
		75,//1-26
		75,//1-27
		75,//1-26
		75,//1-29
		75,//1-30	
		45,//1-31
		45,//1-32
		45,//1-33
		45,//1-34
		45,//1-35
		45,//1-36
		60,//1-37
		60,//1-38
		60,//1-39
		60,//1-40	
		60,//1-41
		60,//1-42
		75,//1-43
		75,//1-44
		75,//1-45
		75,//1-46
		75,//1-47
		75,//1-48
		90,//1-49
		90,//1-50	
		90,//1-51
		90,//1-52
		90,//1-53
		90,//1-54
		100,//1-55
		100,//1-56
		100,//1-57
		100,//1-58
		120,//1-59
		120,//1-60	
		
		//隐藏关卡
		30,
	};
	
	
//	private short[][][] script_1()  //11
//	{
//		return
//		new short[][][]
//		{										
//			new short[][]  {//	第2波
////				new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,3,266, 266-80, 266+80},
////				
////				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,1,266},
//									
//				new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},					
//			},						
//		};
//	};
	
	/*
	 * 关卡脚本1
	 * */
	private short[][][] script_1()  //11
	{
		return
		new short[][][]
		{
			new short[][]{	//播放声音	
				new short[]{OpenFight1Music},	
			},	
				
//			new short[][]{	//	教学1：蔬菜弹射教学	
//				new short[]{EnterTeach, GameTeaching.TEACH_VOL1},//判断教学是否已经执行过
//				new short[]{ClothTouch},//关闭触摸					
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,266},//小蓝怪+弹射教程引导
//				new short[]{Wait,10},
//				new short[]{OpenTouch},//打开触摸
//				new short[]{TeachState, GameTeaching.TEACH_VOL1},				
//			},
			
//			new short[][]{	//	教学2：COMBO模式（连击数说明）
//				new short[]{EnterTeach, GameTeaching.TEACH_VOL2},//判断教学是否已经执行过	
//				new short[]{ClothTouch},//关闭触摸	
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,266},//小蓝怪+弹射教程引导
//				new short[]{Wait,10},
//				new short[]{OpenTouch},//打开触摸
//				new short[]{TeachState, GameTeaching.TEACH_VOL2},				
//			},

//			new short[][]{	//	教学3：COMBO模式（触发使用）	
//				new short[]{EnterTeach, GameTeaching.TEACH_VOL3},//判断教学是否已经执行过	
//				new short[]{ClothTouch},//关闭触摸	
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,266},//小蓝怪+弹射教程引导
//				new short[]{Wait,10},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,2,266-100,266+100},//小蓝怪+弹射教程引导
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,2,266-200,266+200},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,250,2,266-100,266+100},
//				new short[]{Wait,10},				
//				new short[]{OpenTouch},//打开触摸
//				new short[]{TeachState, GameTeaching.TEACH_VOL3},				
//			},
			
//			new short[][]{	//	教学3：提示combo条	
//				new short[]{EnterTeach, GameTeaching.TEACH_VOL43},//判断教学是否已经执行过	
//				new short[]{ClothTouch},//关闭触摸	
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,266},//小蓝怪+弹射教程引导
//				new short[]{Wait,10},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,2,266-100,266+100},//小蓝怪+弹射教程引导
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,2,266-200,266+200},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,250,2,266-100,266+100},
//				new short[]{Wait,10},				
//				new short[]{OpenTouch},//打开触摸
//				new short[]{TeachState, GameTeaching.TEACH_VOL43},				
//			},
			
			new short[][]  {//	第2波	
				new short[]{Refresh,CoolEditDefine.Enemy_PANGXIE1,150,1,200},//小蓝怪+弹射教程引导	
				
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,26},//小蓝怪+弹射教程引导
//				new short[]{Wait,40},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,460},//小蓝怪+弹射教程引导
//				new short[]{Wait,40},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,266},//小蓝怪+弹射教程引导
//				new short[]{Wait,40},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,86},//小蓝怪+弹射教程引导
//				new short[]{Wait,60},			
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,2,206,326},
//				new short[]{Wait,60},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,3,146,206,266},
			},						
		};
	};
	private short[][][] script_2()//17
	{
		return
		new short[][][]
		{
			new short[][]{	//播放声音	
				new short[]{OpenFight1Music},	
			},	
				

			
//			new short[][]{	//	教学2：第一次使用伙伴		
//				new short[]{EnterTeach, GameTeaching.TEACH_VOL13},//判断教学是否已经执行过		
//				new short[]{ClothTouch},//关闭触摸					
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,266},//萝卜弹射教程引导
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,225,1,266},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,266},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,375,1,266},
//				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,450,1,266},
//				new short[]{Wait,10},
//				new short[]{OpenTouch},//打开触摸
//				new short[]{TeachState, GameTeaching.TEACH_VOL13},				
//			},
			new short[][]{	//	第2波
					//{DIAOLUO,26},
					////{DENGJI,26},
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,375,1,386},
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,446},
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,225,1,506},
					new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,446},
				},
				new short[][]{	//	第3波
					////{DENGJI,26},
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,375,1,146},
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,86},
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,225,1,26},
					new short[]{Wait,30},
					////{DENGJI,26},
					new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,86},
	                                ////{DENGJI,26},
	                                new short[]{Wait,30},
					new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,186},
	                                new short[]{Wait,30},
					new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,386},
	                           
				},
				new short[][]{	//	第4波
					////{DENGJI,26},				
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,266},
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,250,2,206,326},
					new short[]{Refresh,CoolEditDefine.Enemy_SHZ,250,1,266},
					new short[]{Refresh,CoolEditDefine.Enemy_SHZ,200,1,266},
					new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
				},
			
		};
	};
	private short[][][] script_3()//22
	{
		
			return
			new short[][][]
			{
				new short[][]{	//播放声音	
					new short[]{OpenFight1Music},	
				},	
					

				
//				new short[][]{	//	教学2：第一次使用伙伴		
//						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,266},//小蓝怪
//						new short[]{Wait,60},
//						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
//						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,2,206,326},
//						new short[]{Wait,100},
//						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,86},
//						new short[]{Wait,60},
//						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,506},
//						new short[]{Wait,60},
//						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},
//						new short[]{Wait,60},
//						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,5,26,146,266,386,506},
//					},
			
			
			
		};
	};
	private short[][][] script_4()//26 任务关，在规定的射击次数内通过才算通过(6次内)
	{
		return
				new short[][][]
				{
			
				
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,180,146,5,40},//纵向连续刷怪
                        new short[]{Wait,40},
                        new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,180,386,5,40},//纵向连续刷怪
						
						
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,335,1,266},//右翼斜向
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,265,1,386},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,230,1,446},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,235,1,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,200,1,386},	
                        new short[]{Wait,40},
                        //{MOZHI,8,200,100,380,300},
                        new short[]{Wait,40},
                        //{MOZHI,8,200,200,380,500},
					},
					new short[][]{	//	第3波
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,335,1,266},//左翼斜向
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,265,1,146},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,206},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,230,1,86},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,235,1,206},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,200,1,146},	
                        new short[]{Wait,50},
						//{MOZHI,7,150,250,330,500},
						new short[]{Wait,90},
						//{MOZHI,7,150,250,330,500},						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,206,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,266},
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,3,206,266,326},
                        new short[]{Wait,40},
					},
					
					{
						//{ShowPic,9998,23}
					},
				};
	};
	private short[][][] script_5()//解锁洋葱
	{
		return
		new short[][][]
		{
			new short[][]{	//播放声音	
				new short[]{OpenFight1Music},	
			},	
				
			
			
			
			
			new short[][]{	//	第1波
				////{DENGJI,1},
				new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,300,146,5,40},
				//{MOZHI,8,200,260,380,420},
			},
			
			new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
				//{MOZHI,8,200,160,380,320},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,266},//箭头形
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,225,2,206,326},
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
				//{MOZHI,8,200,160,380,320},
			},
			
			new short[][]{	//	第3波
				new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,150,206,3,40},
				new short[]{Wait,100},
				//{MOZHI,8,200,160,380,320},
				new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,150,506,5,40},
			},
			
			new short[][]{	//	第4波
				//{MOZHI,10,200,160,380,400},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,26},
				new short[]{Wait,80},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,3,146,266,386},
				new short[]{Wait,80},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,206},
				//{MOZHI,8,200,160,380,320},
			},
			
			{
			    //{Player,CoolEditDefine.Player_YC},
			},
		};
	};
	private short[][][] script_6()//洋葱教学，熟悉使用洋葱对付血牛怪物
	{
		return
				new short[][][]
				{
				
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,300,1,266},//洋葱教学
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,3,146,266,386},
						
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,4,146,206,326,386},//箭头形
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},

						new short[]{Wait,60},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,300,2,206,326},
						
						


					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,206,266,326},

						new short[]{Wait,60},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,165,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,205,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,245,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,265,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,325,1,266},
					},
					new short[][]{	//	第4波
						
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
						new short[]{Wait,60},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,206,266,326},
						new short[]{Wait,100},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,5,26,146,266,246,506},
					},
				};
	};
	private short[][][] script_7()
	{
		return
		new short[][][]
		{
			new short[][]{	//播放声音	
				new short[]{OpenFight1Music},	
			},	
					
				
			new short[][]{	//	第1波
				
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,260,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,220,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,180,1,266},				
			},
			
			new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)   正三角形
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,2,146,386},
				new short[]{Wait,60},
				////{DENGJI,2},
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,260,2,206,326},
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,220,2,206,386},				
			},
			
			new short[][]{	//	第3波
				////{DENGJI,2},
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,4,146,206,326,386},
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,260,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,340,1,266},
				new short[]{Wait,40},
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,266},				
			},
			
			new short[][]{	//	第4波
				new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
				new short[]{Wait,80},
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,26},
				new short[]{Wait,80},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,206,266,326},
				new short[]{Wait,80},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,506},			
			},
		};
	};
	private short[][][] script_8()
	{
		return
				new short[][][]
				{
				
				new short[][]{	//	第1波
						////{DENGJI,26},
						////{SHUIDI,1,180,500,1,1},
						new short[]{OpenFight1Music},
						new short[]{Fog},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
						new short[]{Wait,60},
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,266,386},
						new short[]{Wait,40},
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},	
						new short[]{Wait,60},
						
                                                new short[]{Wait,50},
                                                ////{DENGJI,2},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,266},	
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,260,1,266},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,340,1,266},	
                        						new short[]{Wait,60},
                        						


					},
					new short[][]{	//	第3波
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,266,386},
                                                new short[]{Wait,50},
                                                ////{DENGJI,26},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,2,146,386},	
                        						new short[]{Wait,60},
                        						
						
					},
					new short[][]{	//	第4波
						
						
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,300,2,206,326},
                                                new short[]{Wait,40},
                                                ////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,5,146,206,266,326,386},
                                                new short[]{Wait,80},
                                                ////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,3,206,266,326},
						new short[]{Wait,60},
						////{SHUIDI,26,100,150,1,2},
					},
				};
	};
	private short[][][] script_9()//墨汁关3
	{
		return
				new short[][][]
				{
				
				new short[][]{	//	第1波
						////{SHUIDI,1,180,500,1,1},
						////{DENGJI,2},
						new short[]{OpenFight1Music},
						new short[]{Fog},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,266,2,26,86},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,290,2,86,146},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,340,1,146},
                    
                                                
						
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,146},
						new short[]{Wait,40},

						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,5,26,146,266,386,506},
						new short[]{Wait,90},
						
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,446},
						new short[]{Wait,100},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,4,86,206,326,446},
						////{SHUIDI,26,100,160,1,2},

					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},
						
					},
					new short[][]{	//	第4波
						////{SHUIDI,1,100,500,1,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,146},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,386},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,3,206,266,326},
						new short[]{Wait,80},
						////{SHUIDI,26,180,500,1,2},
					},
					
				};
	};
	private short[][][] script_11()
	{
		return
				new short[][][]
				{
				
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
						////{SHUIDI,1,180,360,1,2},
						new short[]{Fog},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,266},//正三角形			
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,260,3,206,266,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,220,5,146,206,266,326,386},
						new short[]{Wait,60},
						//{MOZHI,8,200,200,420,360}, 
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,266,386},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,2,206,326},
						new short[]{Wait,90},
						//{MOZHI,8,70,200,270,360},
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,425,1,386},
						new short[]{Wait,30},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,266},
						new short[]{Wait,40},
						//{MOZHI,10,200,160,380,400},


					},
					new short[][]{	//	第3波
						////{DENGJI,1},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,150,26,3,30},
						new short[]{Wait,90},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,150,446,3,30},
						new short[]{Wait,90},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,400,3,206,266,326},
						new short[]{Wait,60},
						////{SHUIDI,26,100,160,1,2},
						
					},
					new short[][]{	//	第4波
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,1,26},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,386},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,1,446},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,146},
						new short[]{Wait,30},
						//{MOZHI,8,100,300,300,500},
						new short[]{Wait,30},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
                        new short[]{Wait,60},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,1,26},
                        new short[]{Wait,60},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,386},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,2,206,326},
                        new short[]{Wait,60},
                        //{MOZHI,10,100,200,400,360},
                        new short[]{Wait,60},
                        ////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,400,3,206,266,326},
					},
				};
	};
	private short[][][] script_10()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						////{SHUIDI,1,180,500,1,1},
						////{DENGJI,1},
						new short[]{OpenFight1Music},
						new short[]{Fog},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,150,146,3,60},//双管齐下	
						new short[]{Wait,180},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,150,386,3,60},
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,2,206,326},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,460,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,266},
					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,86},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,506},	
						
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},	
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,146},	
					},
					new short[][]{	//	第4波
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
						new short[]{Wait,30},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,3,146,266,386},
						new short[]{Wait,30},
						
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,206,266,326},
					},
					new short[][]{	//	第5波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,86,206,326},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,206,326,446},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,26},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,206,266,326},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,506},
						////{SHUIDI,26,100,160,1,2},
					},
					{
					    //{Player,CoolEditDefine.Player_TD},
					},
				};
	};
	private short[][][] script_12()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
//						////{SHUIDI,1,100,500,1,1},
//						////{DENGJI,26},
						new short[]{OpenFight1Music},
						new short[]{Fog},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,400,1,266},//箭头
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,360,2,206,326},
						new short[]{Wait,40},
						//{MOZHI,8,70,200,270,360}, 
						new short[]{Wait,40},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHHT,320,2,146,386},
                       

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,2},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,300,86,5,40},
                        new short[]{Wait,50},
                        //{MOZHI,10,100,200,400,360}, 
                        new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,266,3,326,386,446},

                        new short[]{Wait,50},
                        //{MOZHI,8,100,200,400,450},
                        new short[]{Wait,50},
                        ////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,2,206,326},
						new short[]{Wait,50},
						////{SHUIDI,26,100,160,1,2},


					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},
						
					},
					new short[][]{	//	第4波
						////{SHUIDI,1,100,160,1,2},
						////{DENGJI,26},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,146,266,386},
                        new short[]{Wait,50},
                        //{MOZHI,8,100,150,300,450},
                        ////{SHUIDI,26,100,160,1,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,360,1,266},
                        new short[]{Wait,50},
                        //{MOZHI,8,100,300,300,500},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,225,1,266},
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,2,206,326},
					},
				};
	};
	private short[][][] script_13()//任务关3 命中率为100%
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						////{DENGJI,1},
						new short[]{OpenFight1Music},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,3,146,266,386},//双管齐下
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,1,26},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,506},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,146,266,386},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,190,2,146,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,26},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,2,206,326},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,3,206,266,326},
					},
					new short[][]{	//	第5波
						////{DENGJI,2},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,300,146,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,300,206,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,300,266,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,300,326,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHMM,300,386,3,40},
					},
					
				};
	};
	private short[][][] script_14()//猪，熊，小兰怪
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
//						////{DENGJI,2},
						new short[]{OpenFight1Music},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,190,1,266},//心形			
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,190,2,146,386},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,230,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,270,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,210,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,266},




					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,2,137,386},//箭头形
                                                new short[]{Wait,60},
                                                ////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,340,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,380,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,500,1,206},
			                        


					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,300,1,266},
                                                new short[]{Wait,90},
                                                ////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,26},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,190,1,86},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,230,1,146},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,270,1,206},
                                              
                                                new short[]{Wait,120},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,1,506},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,190,1,446},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,230,1,386},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHMM,270,1,326},
                                                new short[]{Wait,60},
                        						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,506}, 
                        						new short[]{Wait,60},
                        						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,500,1,326},

					},
					new short[][]{	//	第4波
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,206,266},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,266,386},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,206,266,326},
                                                new short[]{Wait,60},
                                                ////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,200,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,266},
					},
					new short[][]{   
						////{DENGJI,2},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,300,206,3,60},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,300,326,3,60},
						new short[]{Wait,120},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,200,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,360,2,206,326},
					},
				};
	};
	private short[][][] script_15()//鱼、蛙、小兰怪
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,86,266,446},	
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,6,146,206,266,326,386,446},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,400,1,348},
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,1,266},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,225,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,266},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,2,26,8},
						new short[]{Wait,120},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,146,266,386},
						new short[]{Wait,120},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,86,206,326},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,86},


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
						new short[]{Wait,60},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,266,386},
						new short[]{Wait,60},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,2,26,482},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,500,2,266,386},
					},
					new short[][]{	//	第4波
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,3,206,266,326},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,360,1,206},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,266},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,26,266,506},
						new short[]{Wait,60},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,500,1,386},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
					},
				
				};
	};
	private short[][][] script_16()  //11
	{
		return
		new short[][][]
		{
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,3,206,266,326},
                                new short[]{Wait,30},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,3,206,266,326},

				new short[]{Wait,30},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,326},
			},
			new short[][]  {	//	第2波
				
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,5,26,146,266,386,506},
				new short[]{Wait,40},
				new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,4,86,206,326,446},
				new short[]{Wait,30},
                                new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,4,86,206,326,446},
                                new short[]{Wait,30},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,446},
				
			},
			new short[][]{	//	第3波
				new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,2,206,326},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,2,206,326},
				new short[]{Wait,60},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,146,206,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,3,146,206,266},
			},
		};
	};
	private short[][][] script_17()//17
	{
		return
				new short[][][]
				{				
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,206,266,326},//小蓝怪
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,266},
					},
					new short[][]{	//	第2波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,200,2,206,326},


					},
					new short[][]{	//	第3波
					
					new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,5,146,206,266,326,386},
                                            				new short[]{Wait,40},
                                           new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,5,146,206,266,326,386},
                                           new short[]{Wait,40},
                                            new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,5,146,206,266,326,386},
                                            new short[]{Wait,40},
                                            new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,5,146,206,266,326,386},
                                            new short[]{Wait,40},
                                            new short[]{Refresh,CoolEditDefine.Enemy_SHMM,300,5,146,206,266,326,386},
                                           new short[]{Wait,40},
                                            new short[]{Refresh,CoolEditDefine.Enemy_SHMM,150,5,146,206,266,326,386},
					},
					new short[][]{	//	第4波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,86},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,206,266,326},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,5,146,206,266,326,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,400,1,266},
					},
					{
					    //{Player,CoolEditDefine.Player_LB},
					},
				};
	};
	private short[][][] script_18()//22
	{
		return
		new short[][][]
		{
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
				//{ShowPic,9999,29},
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,375,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,450,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,450,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,1,266},
			},
			
			
			new short[][]{	//	第2波
				//{DIAOLUO,26},
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHM,475,1,386},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,1,446},
				new short[]{Refresh,CoolEditDefine.Enemy_SHX,225,1,506},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,446},
				new short[]{Wait,60},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,360,1,446},
			},
			new short[][]{	//	第3波
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHM,475,1,146},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,1,86},
				new short[]{Refresh,CoolEditDefine.Enemy_SHX,225,1,26},
				new short[]{Wait,30},
				////{DENGJI,26},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,86},
				new short[]{Wait,60},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,380,1,86},
			},
			new short[][]{	//	第4波
				////{DENGJI,26},				
				new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHHT,250,2,206,326},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,250,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,200,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
				new short[]{Wait,60},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,266},
			},
		};
	};
	private short[][][] script_19()
	{
		return
				new short[][][]
				{
				{
					//{DIAOLUO,1,3,1,35},
				},
				
				new short[][]{	//	第1波
						//{DIAOLUO,26},
						////{DENGJI,1},
						new short[]{OpenFight1Music},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHHT,180,146,5,40},//纵向连续刷怪
						new short[]{Wait,90},
						//{MOZHI,7,70,300,270,500},
						//{ShowPic,9995,-1},
						new short[]{Wait,40},
						//{MOZHI,7,200,300,380,500},
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,335,1,266},//右翼斜向
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,265,1,386},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,230,1,446},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,235,1,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,200,1,386},	
                        new short[]{Wait,40},
                        //{MOZHI,8,200,100,380,300},
                        new short[]{Wait,40},
                        //{MOZHI,8,200,200,380,500},
					},
					new short[][]{	//	第3波
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,335,1,266},//左翼斜向
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,265,1,146},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,206},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,230,1,86},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,235,1,206},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,200,1,146},	
                        new short[]{Wait,50},
//{MOZHI,7,150,250,330,500},
new short[]{Wait,90},
//{MOZHI,7,150,250,330,500},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,206,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,266},
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,300,3,206,266,326},
                        new short[]{Wait,40},

					},
					
					{
						//{ShowPic,9998,23}
					},
				};
	};
	private short[][][] script_20()//解锁洋葱
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						////{DENGJI,1},
						new short[]{OpenFight1Music},
						new short[]{Fog},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHHT,300,146,5,40},
						//{MOZHI,8,200,260,380,420},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,260,1,130},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{MOZHI,8,200,160,380,320},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,400,3,146,266,386},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,2,206,326},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},
						//{MOZHI,8,200,160,380,320},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,330},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,500,1,266},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,450,1,146},


					},
					new short[][]{	//	第3波
						new short[]{Refresh1,CoolEditDefine.Enemy_SHX,150,206,3,40},
						new short[]{Wait,100},
						//{MOZHI,8,200,160,380,320},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHHT,150,506,5,40},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,206},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,450},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,200,1,206},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,200,1,450},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,266,1,206},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,266,1,450},
					},
					new short[][]{	//	第4波
						//{MOZHI,10,200,160,380,400},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,26},

                        new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,3,146,266,386},
                                                new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,300,1,206},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,32},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,206},
					},
					{
					    //{Player,CoolEditDefine.Player_YC},
					},
				};
	};
	private short[][][] script_21()//洋葱教学，熟悉使用洋葱对付血牛怪物
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
						new short[]{Fog},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,300,1,266},//洋葱教学
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,146,266,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,227},
						
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,4,146,206,326,386},//箭头形
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},

						new short[]{Wait,60},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,2,206,326},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,206},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,267},
						
						


					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,3,206,266,326},

						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,192},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,165,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,205,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,245,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,265,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,325,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,265,1,236},
					},
					new short[][]{	//	第4波
						
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
						new short[]{Wait,60},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,206,266,326},
						new short[]{Wait,100},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,5,26,146,266,246,506},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,146},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,200,1,266},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,220,1,456},
					},
				};
	};
	private short[][][] script_22()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
						new short[]{Fog},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,260,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,220,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,180,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,245},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)   正三角形
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,2,146,386},
						new short[]{Wait,60},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,260,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,220,2,206,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,320,1,243},
						


					},
					new short[][]{	//	第3波
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,4,146,206,326,386},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,260,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,340,1,266},
						new short[]{Wait,40},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,266},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,320,1,206},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,516,1,241},
						
					},
					new short[][]{	//	第4波
						
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,440,1,132},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,30},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,26},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,206,266,326},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,506},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,206},
					
					},
				};
	};
	private short[][][] script_23()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						////{DENGJI,26},
						////{SHUIDI,1,180,500,1,1},
						new short[]{OpenFight1Music},
						new short[]{Fog},
						
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,450,1,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,266,386},
						
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,450,1,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,146,266,386},	
						new short[]{Wait,60},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},	
                        new short[]{Refresh,CoolEditDefine.Enemy_SHZ,260,1,266},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHZ,340,1,266},	
                                        						


					},
					new short[][]{	//	第3波
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,146,266,386},
                                                new short[]{Wait,50},
                                                ////{DENGJI,26},
                       new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,2,146,386},	
                        						               						
						
					},
					new short[][]{	//	第4波
						
						
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,206},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,300,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,2,206,386},
						new short[]{Wait,40},
                                                ////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,5,146,206,266,326,386},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,500,1,326},
						new short[]{Wait,80},
                                                ////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,3,206,266,326},
						
					},
				};
	};
	private short[][][] script_24()//墨汁关3
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						////{SHUIDI,1,180,500,1,1},
						////{DENGJI,2},
						new short[]{OpenFight1Music},
						new short[]{Fog},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,266,1,30},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,266,2,26,86},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,290,2,86,146},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,340,1,146},
                    
                                                
						
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,500,1,146},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,146},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,506},
						new short[]{Wait,40},

						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,5,26,146,266,386,506},
						new short[]{Wait,90},
						
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,500,1,446},
						new short[]{Wait,100},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,4,86,206,326,446},
						////{SHUIDI,26,100,160,1,2},

					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,320,1,238},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,266},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,1,136},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,137},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,1,241},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,1,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,386},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,500,3,206,266,326},
						
					},
					
				};
	};
	private short[][][] script_25()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						////{SHUIDI,1,180,360,1,2},
						new short[]{OpenFight1Music},
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},//正三角形			
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,260,3,206,266,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,220,5,146,206,266,326,386},
						new short[]{Wait,60},
						//{MOZHI,8,200,200,420,360}, 
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,266,386},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,225,2,206,326},
						new short[]{Wait,90},
						//{MOZHI,8,70,200,270,360},
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,425,1,386},
						new short[]{Wait,30},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,266},
					


					},
					new short[][]{	//	第3波
						////{DENGJI,1},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHX,150,26,3,30},
						new short[]{Wait,90},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,150,446,3,30},
						new short[]{Wait,90},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,400,3,206,266,326},
						new short[]{Wait,60},
						////{SHUIDI,26,100,160,1,2},
						
					},
					new short[][]{	//	第4波
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,225,1,26},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,386},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,225,1,446},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,146},
						new short[]{Wait,30},
						//{MOZHI,8,100,300,300,500},
						new short[]{Wait,30},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
                        new short[]{Wait,60},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,1,26},
                        new short[]{Wait,60},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,386},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,2,206,326},
                        new short[]{Wait,60},
                        //{MOZHI,10,100,200,400,360},
                        new short[]{Wait,60},
                        ////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,400,3,206,266,326},
					},
				};
	};
	private short[][][] script_26()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						////{SHUIDI,1,180,500,1,1},
						////{DENGJI,1},
						new short[]{OpenFight1Music},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHHT,150,146,3,60},//双管齐下	
						new short[]{Wait,180},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHHT,150,386,3,60},
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,206},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,2,206,326},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,460,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,1,266},
					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,86},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,506},	
						
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},	
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,146},	
					},
					new short[][]{	//	第4波
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},
						new short[]{Wait,30},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,3,146,266,386},
						new short[]{Wait,30},
						
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,400,3,206,266,326},
					},
					new short[][]{	//	第5波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,86,206,326},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,206,326,446},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,26},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,225,3,206,266,326},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,300,1,506},
						////{SHUIDI,26,100,160,1,2},
					},
					{
					    //{Player,CoolEditDefine.Player_TD},
					},
				};
	};
	private short[][][] script_27()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
//						////{SHUIDI,1,100,500,1,1},
//						////{DENGJI,26},
						new short[]{OpenFight1Music},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,400,1,266},//箭头
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,360,2,206,326},
						new short[]{Wait,40},
						//{MOZHI,8,70,200,270,360}, 
						new short[]{Wait,40},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHHT,320,2,146,386},
                       

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,2},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,300,86,5,40},
                        new short[]{Wait,50},
                        
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,266,3,326,386,446},

                        new short[]{Wait,50},
                        
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,2,206,326},
						


					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,300,1,266},
						
					},
					new short[][]{	//	第4波
						////{SHUIDI,1,100,160,1,2},
						////{DENGJI,26},
                        new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,146,266,386},
                        new short[]{Wait,50},
                        //{MOZHI,8,100,150,300,450},
                        ////{SHUIDI,26,100,160,1,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,360,1,266},
                        new short[]{Wait,50},
                        //{MOZHI,8,100,300,300,500},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,225,1,266},
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,2,206,326},
					},
				};
	};
	private short[][][] script_28()//任务关3 命中率为100%
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
						////{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,300,3,146,266,386},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,225,1,26},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,1,506},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,266},
						


					},
					new short[][]{	//	第2波
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,400},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,3,146,266,386},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,190,2,146,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},
					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,26},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,2,206,386},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHM,500,3,206,266,326},
					},
					new short[][]{	//	第4波
						////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,300,146,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,300,206,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,300,266,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,300,326,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHZ,300,386,3,40},
					},
					
				};
	};
	private short[][][] script_29()//猪，熊，小兰怪
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight1Music},
//						////{DENGJI,2},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,190,1,266},//心形			
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,190,2,146,386},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,230,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,270,1,266},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,210,1,266},
                                                new short[]{Wait,40},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						





					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,150,2,146,386},//箭头形
                                                new short[]{Wait,60},
                                                ////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,340,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,380,1,266},
			                        


					},
					new short[][]{	//	第3波
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,300,1,266},
                                                new short[]{Wait,90},
                                                ////{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,150,1,26},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHHT,190,1,86},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHHT,230,1,146},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHHT,270,1,206},
                                              
                                                new short[]{Wait,120},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,1,506},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHZ,190,1,446},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHZ,230,1,386},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SHZ,270,1,326},
                                             

					},
					new short[][]{	//	第4波
						
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,206,266},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,146,266,386},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZ,150,3,206,266,326},
                                                new short[]{Wait,60},
                                                ////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,200,1,266},
					},
					new short[][]{   
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHHT,300,206,3,60},
						new short[]{Refresh1,CoolEditDefine.Enemy_SHHT,300,326,3,60},
						new short[]{Wait,120},
						////{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_SHX,200,1,266},
					},
				};
	};
	private short[][][] script_30()//BOSS 章鱼
	{
		return
				new short[][][]
				{
				new short[][]{ 
						new short[]{OpenBossMusic},
						new short[]{Refresh,CoolEditDefine.Enemy_SHHT,300,2,206,326},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SHZY,450,1,266},
				        new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,30},
				   }
				};
	};
	
	private short[][][] script_31()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board, 266,360},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,266},//熟悉豌豆			
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,260,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,220,1,266},
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,185,1,266},//扇形
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,225,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,265,2,146,386},
						
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,266,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,206,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,326,3,40},
					},
					
				};
	};
	private short[][][] script_32()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board, 266,180},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,3,146,266,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,146,206,266,326,386},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,2,26,506},//
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,500,3,206,266,326},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,190,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,230,1,266},


					},
					new short[][]{	//	第3波 野猪正三角形
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,260,3,206,266,326},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,220,5,146,206,266,326,386},
					},
					
				};
	};
	private short[][][] script_33()//22
	{
		return
		new short[][][]
		{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
				new short[]{Board, 326,360,386,400},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,225,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,375,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,450,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,4,146,206,326,386},
				
			},
			new short[][]{	//	第2波
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,1,266},
				new short[]{Wait,20},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,86},
				new short[]{Wait,60},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,1,326},
				new short[]{Wait,20},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,206},
				new short[]{Wait,60},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,326},
			},
			new short[][]{	//	第3波
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,375,1,386},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,446},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,225,1,506},
				new short[]{Wait,70},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,150,2,146,206},
			},
			new short[][]{	//	第4波
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,375,1,146},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,86},
				new short[]{Refresh,CoolEditDefine.Enemy_MIMI,225,1,26},
				new short[]{Wait,30},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,150,2,386,446},
			},
			{
			   // {Player,CoolEditDefine.Player_MG},
			},
		};
	};
	private short[][][] script_34()//26
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board,386,360,146,300},
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,180,146,5,20},//纵向连续刷怪			
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,335,1,266},//右翼斜向
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,265,1,386},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,230,1,446},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,235,1,506},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,200,1,386},						
					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,335,1,266},//左翼斜向
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,265,1,146},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,206},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,230,1,86},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,235,1,206},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,200,1,146},	
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,206,2,26,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,3,266,386,506},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,5,146,206,266,326,386},
					},
					
					
				};
	};
	private short[][][] script_35()//墨汁关,解锁洋葱
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board, 266,360,206,320,326,320},
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,146,5,60},//双管齐下
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,386,5,60},
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,266},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,225,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,86,146,206,266,326},
						new short[]{Wait,40},
					
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,206,266,326,386,446},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,146,206,266,326,386},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,300,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,375,3,206,266,326},
						
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,146,206,266,326,386},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,225,5,146,206,266,326,386},
					},
					
										
				};
	};
	private short[][][] script_36()//洋葱教学，熟悉使用洋葱对付血牛怪物
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						//{WU,1},
						//{DENGJI,26},
						new short[]{OpenFight2Music},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,1,266},//洋葱教学

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,4,146,206,326,386},//箭头形
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						new short[]{Wait,60},
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,2,206,326},
						
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,206,266,326},
						new short[]{Wait,120},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,165,3,26,146,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,205,3,266,386,506},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,245,3,146,206,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,265,3,266,326,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,325,3,26,266,506},
					},
					new short[][]{	//	第4波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,400,3,146,266,386},
						new short[]{Wait,60},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,1,266},
						new short[]{Wait,60},
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,2,206,326},
						new short[]{Wait,60},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,26,146,266,386,506},
						new short[]{Wait,60},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,26,146,266,386,506},
					},
									
				};
	};
	private short[][][] script_37()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Fog},
						//{DENGJI,1},
						
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,260,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,220,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,180,1,266},
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)   正三角形
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,2,146,386},
						new short[]{Wait,60},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,260,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,220,2,146,386},
						


					},
					new short[][]{	
						//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,4,146,206,326,386},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,260,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,340,1,266},
						new short[]{Wait,40},//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,1,266},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,3,146,266,386},
						new short[]{Wait,40},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,26},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,506},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,206},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,446},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,400,3,146,266,386},
					
					},
					{
					   // {Player,CoolEditDefine.Player_LJ},
					},
				};
	};
	private short[][][] script_38()//任务关1
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						//{WU2,1},
						//{DENGJI,1},
						new short[]{OpenFight2Music},
						new short[]{Fog},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,146,5,60},//双管齐下			
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,150,386,5,60},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,3,206,266,326},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,500,3,206,266,326},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,506},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,26},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,386},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,425,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,350,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,275,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,200,1,266},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,146,206,266,326,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,146,206,266,326,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,146,206,266,326,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,400,5,146,206,266,326,386},
					},
					
				};
	};
	private short[][][] script_39()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						//{WU2,1},
						//{DENGJI,1},
						new short[]{OpenFight2Music},
						new short[]{Fog},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,266,2,26,86},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,290,2,86,146},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,340,1,86},
						new short[]{Wait,100},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,266,2,446,506},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,290,2,386,446},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,340,1,386},
						
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,2,146,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,5,26,146,266,386,506},
						new short[]{Wait,90},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,1,446},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,4,86,206,326,446},
						

					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,2,206,266},
						
					},
					new short[][]{	//	第4波
						
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,146},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,386,506},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,3,206,266,326},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,206,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,386,506},
						new short[]{Wait,40},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,5,146,206,266,326,386},
						
					},
									
				};
	};
	private short[][][] script_40()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						//{WU2,1},
						//{SHUIDI,1,100,500,1,1},
						//{DENGJI,2},
						new short[]{OpenFight2Music},
						new short[]{Fog},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,266},//正三角形			
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,260,3,206,266,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,260,5,146,206,266,326,386},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,146,266,386},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,2,206,326},
						new short[]{Wait,90},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,325,1,386},
						new short[]{Wait,30},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,1,266},
						
						//{SHUIDI,26,100,160,1,2},

					},
					new short[][]{	//	第3波
						new short[]{Refresh1,CoolEditDefine.Enemy_DS,150,26,3,30},
						new short[]{Wait,90},
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,150,446,3,30},
						new short[]{Wait,90},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,400,3,206,266,326},
						
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						//{SHUIDI,1,150,500,1,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,266},
                                                new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,1,26},
                                                new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,386},
                                                new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,1,446},
                                                new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,146},
                                                new short[]{Wait,90},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,400,3,206,266,326},
						new short[]{Wait,40},
						//{SHUIDI,26,150,200,2,3},
					},
										
				};				
	};
	private short[][][] script_41()//墨汁关2
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Fog},
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,146,5,60},//双管齐下			
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,386,5,60},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,266},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,225,2,206,326},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,3,206,266,326},
						
					},
					new short[][]{	//	第4波
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,206,326},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,2,146,266},
						new short[]{Wait,40},
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,5,146,206,266,326,386},
						new short[]{Wait,40},
						
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,206,326},
					},
					
				};
	};
	private short[][][] script_42()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						//{WU2,1},
						//{DENGJI,1},
						new short[]{OpenFight2Music},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,400,1,266},//箭头	
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,360,2,206,326},
						new short[]{Wait,80},
                        new short[]{Refresh,CoolEditDefine.Enemy_HZ,320,2,146,386},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{SHUIDI,1,150,500,1,1},
						//{DENGJI,2},
						new short[]{Refresh1,CoolEditDefine.Enemy_MIMI,300,86,5,40},
                                                new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,266,3,326,386,446},
						
                                                new short[]{Wait,50},
                                                //{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,2,206,326},
						


					},
					new short[][]{	//	第3波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},
						
					},
					new short[][]{	//	第4波
						//{DENGJI,1},
                        new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,3,146,266,386},
                        new short[]{Wait,50},
                        //{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,360,1,266},
						new short[]{Wait,50},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,2,206,326},
                        new short[]{Wait,50},
                        //{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,225,1,266},
						new short[]{Wait,50},
						//{DENGJI,1},
                        new short[]{Refresh,CoolEditDefine.Enemy_DS,300,3,146,266,386},
						new short[]{Wait,50},
						
                        //{DENGJI,26},
                        new short[]{Refresh,CoolEditDefine.Enemy_X,225,1,266},
						//{SHUIDI,26,150,200,2,3},
					},
									
				};
	};
	private short[][][] script_43()//任务关2
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board, 266,400,206,400,398,400},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,206},
						new short[]{Wait,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,300,206,3,60},//3管齐下			
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,300,266,3,60},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,300,326,3,60},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,300},
						
					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,136},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,3,146,266,386},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,225,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,350},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,400,1,506},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,380,1,190},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,350,3,86,146,206},
						new short[]{Wait,100},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,146,386},


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,136},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,3,146,266,386},
						new short[]{Wait,40},
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,206},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,5,146,206,266,326,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,5,146,206,266,326,386},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,206},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,3,206,266,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,136},
						new short[]{Wait,40},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,3,26,146,266,},
						new short[]{Wait,200},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,3,266,386,506},
					},
				};
	};
	private short[][][] script_44()//猪，熊，小兰怪
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board, 206,300,266,400,386,250},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,190,1,206},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,190,1,266},//心形			
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,190,2,146,386},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,230,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,270,1,266},
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,210,1,266},





					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},//箭头形
                                                new short[]{Wait,60},
                                                //{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,340,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,380,1,266},
						new short[]{Wait,40}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,242},
						           


					},
					new short[][]{	//	第3波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,300,1,266},
                                                new short[]{Wait,90},
                                                //{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,26},
						new short[]{Wait,60}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,24},
                        new short[]{Refresh,CoolEditDefine.Enemy_HZ,190,1,86},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_HZ,230,1,146},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_HZ,270,1,206},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_HZ,310,1,266},
                                                new short[]{Wait,60}, 
                        						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,310,1,242},
                                                //{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,1,8},
                                                new short[]{Refresh,CoolEditDefine.Enemy_MIMI,190,1,446},
                                                new short[]{Refresh,CoolEditDefine.Enemy_MIMI,230,1,386},
                                                new short[]{Refresh,CoolEditDefine.Enemy_MIMI,270,1,326},
                                                new short[]{Refresh,CoolEditDefine.Enemy_MIMI,310,1,266},

					},
					new short[][]{	//	第4波
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,146,206,266},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,146,266,386},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,206,266,326},
						new short[]{Wait,60}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,180,1,296},
                                                //{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,450,1,266},
						new short[]{Wait,120},
						//{DENGJI,1},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,190,266,3,40},
						new short[]{Wait,40}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,210,1,242},
					},
				};
	};
	private short[][][] script_45()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board, 266,360,206,360,326,360},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,300,1,266},//熟悉豌豆			
						new short[]{Refresh,CoolEditDefine.Enemy_DS,260,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,220,1,266},
						new short[]{Wait,40}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,242},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_X,185,1,266},//扇形
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,265,2,146,326},
						new short[]{Wait,40}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,210,1,242},
						new short[]{Wait,20}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,260,1,142},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,300,266,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,300,206,3,40},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,300,326,3,40},
						new short[]{Wait,40}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,330,1,242},
						new short[]{Wait,20}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,500,1,190},
						new short[]{Wait,20}, 
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,300},
					},
					
				};
	};
	private short[][][] script_46()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board, 266,360,326,266},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,170,1,136},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,3,146,266,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,5,146,206,266,326,386},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,1,86},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,2,26,86},//
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,500,3,206,266,326},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,230,1,238},
						new short[]{Wait,50},
						new short[]{Refresh,CoolEditDefine.Enemy_X,190,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_X,230,1,266},


					},
					new short[][]{	//	第3波 野猪正三角形
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,266},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,260,1,326},
						new short[]{Wait,20},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,260,3,206,266,326},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,220,5,146,206,266,326,386},
					},
					
				};
	};
	private short[][][] script_47()//22
	{
		return
		new short[][][]
		{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Board, 326,360},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,236},
				new short[]{Wait,20},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,300,1,300},
				new short[]{Wait,20},
				new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,300,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,375,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,450,1,266},
				new short[]{Refresh,CoolEditDefine.Enemy_Z,300,4,146,206,326,386},
				
			},
			new short[][]{	//	第2波
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,1,266},
				new short[]{Wait,40},
				new short[]{Refresh,CoolEditDefine.Enemy_Z,150,1,266},
				new short[]{Wait,20},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,86},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,150,1,300},
				new short[]{Wait,60},
				new short[]{Refresh,CoolEditDefine.Enemy_Z,150,1,326},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,225,1,206},
				new short[]{Wait,20},
				new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,206},
				new short[]{Wait,60},
				new short[]{Refresh,CoolEditDefine.Enemy_X,300,1,326},
			},
			new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,375,1,446},
						new short[]{Wait,40},
				new short[]{Refresh,CoolEditDefine.Enemy_HZ,375,1,386},
				new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,446},
				new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,1,506},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,170,1,136},
				new short[]{Wait,70},
				new short[]{Refresh,CoolEditDefine.Enemy_X,150,2,146,206},
			},
			new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,250,1,30},
						new short[]{Wait,40},
				new short[]{Refresh,CoolEditDefine.Enemy_Z,375,1,146},
				new short[]{Refresh,CoolEditDefine.Enemy_Z,300,1,86},
				new short[]{Refresh,CoolEditDefine.Enemy_Z,225,1,26},
				new short[]{Refresh,CoolEditDefine.Enemy_MEGICWATER,170,1,400},
				new short[]{Wait,40},
				new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,386,446},
			},
			{
			   // {Player,CoolEditDefine.Player_MG},
			},
		};
	};
	private short[][][] script_48()//26
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						//{WU,1},
						new short[]{OpenFight2Music},
						//{DENGJI,26},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,180,146,5,20},//纵向连续刷怪			
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_Z,335,1,266},//右翼斜向
						new short[]{Refresh,CoolEditDefine.Enemy_Z,265,1,386},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,1,326},
						new short[]{Refresh,CoolEditDefine.Enemy_X,230,1,446},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,235,1,326},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,200,1,386},						
					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,335,1,266},//左翼斜向
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,265,1,146},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,1,206},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,230,1,86},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,235,1,206},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,200,1,146},	
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,206,2,26,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,4,206,266,386,506},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,5,146,206,266,326,386},
					},
					
					
				};
	};
	private short[][][] script_49()//墨汁关,解锁洋葱
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						//{WU,1},
						new short[]{OpenFight2Music},
						//{DENGJI,26},
						new short[]{Teleport, 0, 3},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,300,146,5,60},//双管齐下
						new short[]{Refresh1,CoolEditDefine.Enemy_Z,300,386,5,60},
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_X,300,1,266},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_Z,225,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,5,86,146,206,266,326},
						new short[]{Wait,40},
					
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,5,206,266,326,386,446},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,5,146,206,266,326,386},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,300,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,375,3,206,266,326},
						
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,5,146,206,266,326,386},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,5,146,206,266,326,386},
					},
					
										
				};
	};
	private short[][][] script_50()//洋葱教学，熟悉使用洋葱对付血牛怪物
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 0, 1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,4,146,206,326,386},//洋葱教学

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,4,146,206,326,386},//箭头形
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},
						new short[]{Wait,60},
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,2,206,326},
						
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,206,266,326},
						new short[]{Wait,120},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,165,3,26,146,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,205,3,266,386,506},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,245,3,146,206,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,265,3,266,326,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,325,3,26,266,506},
					},
					new short[][]{	//	第4波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,400,3,146,266,386},
						new short[]{Wait,60},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},
						new short[]{Wait,60},
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,2,206,326},
						new short[]{Wait,60},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,5,26,146,266,386,506},
						new short[]{Wait,60},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,5,26,146,266,386,506},
					},
									
				};
	};
	private short[][][] script_51()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 0, 3},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,260,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,220,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,180,1,266},
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)   正三角形
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,2,146,386},
						new short[]{Wait,60},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_X,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,260,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,220,2,146,386},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,4,146,206,326,386},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,260,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,340,1,266},
						new short[]{Wait,40},//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,300,1,266},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,3,146,266,386},
						new short[]{Wait,40},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,26},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,506},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,206},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,446},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,400,3,146,266,386},
					
					},
					
				};
	};
	private short[][][] script_52()//任务关1
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 0, 1},
						new short[]{Refresh1,CoolEditDefine.Enemy_Z,300,146,5,60},//双管齐下			
						new short[]{Refresh1,CoolEditDefine.Enemy_Z,150,386,5,60},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,206,266,326},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,3,206,266,326},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,1,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,506},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,26},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,1,386},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_X,500,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,425,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,350,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,275,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,200,1,266},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,5,146,206,266,326,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,5,146,206,266,326,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,5,146,206,266,326,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,400,5,146,206,266,326,386},
					},
					
				};
	};
	private short[][][] script_53()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 0, 3},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,266,2,26,1},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,290,2,86,146},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,340,1,86},
						new short[]{Wait,100},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,266,2,446,506},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,290,2,386,446},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,340,1,386},
						
						

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						
						new short[]{Refresh,CoolEditDefine.Enemy_X,300,2,146,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,5,26,146,266,386,506},
						new short[]{Wait,90},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,1,446},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,4,86,206,326,446},
						

					},
					new short[][]{	//	第3波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,2,206,266},
						
					},
					new short[][]{	//	第4波
						
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,146},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,386,506},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,3,206,266,326},
						
					},
					new short[][]{	//	第4波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,206,266},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,386,506},
						new short[]{Wait,40},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,5,146,206,266,326,386},
						
					},
									
				};
	};
	private short[][][] script_54()
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						//{WU2,1},
						//{SHUIDI,1,100,500,1,1},
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,1,266},//正三角形			
						new short[]{Refresh,CoolEditDefine.Enemy_Z,260,3,206,266,326},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,260,5,146,206,266,326,386},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,146,266,386},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,2,206,326},
						new short[]{Wait,90},
						new short[]{Refresh,CoolEditDefine.Enemy_X,325,1,386},
						new short[]{Wait,30},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,500,1,266},
						
						//{SHUIDI,26,100,160,1,2},

					},
					new short[][]{	//	第3波
						new short[]{Refresh1,CoolEditDefine.Enemy_DS,150,26,3,30},
						new short[]{Wait,90},
						new short[]{Refresh1,CoolEditDefine.Enemy_X,150,446,3,30},
						new short[]{Wait,90},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,400,3,206,266,326},
						
						
					},
					new short[][]{	//	第4波
						//{SHUIDI,1,150,500,1,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,266},
                                                new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,1,26},
                                                new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,386},
                                                new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,1,446},
                                                new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,146},
                                                new short[]{Wait,90},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,400,3,206,266,326},
						new short[]{Wait,40},
						//{SHUIDI,26,150,200,2,3},
					},
										
				};				
	};
	private short[][][] script_55()//墨汁关2
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 0, 3},
						
						
						new short[]{Refresh1,CoolEditDefine.Enemy_DS,300,146,5,60},//双管齐下			
						new short[]{Refresh1,CoolEditDefine.Enemy_Z,300,386,5,60},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,266},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,225,2,206,326},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},
						


					},
					new short[][]{	//	第3波
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,266},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,3,206,266,326},
						
					},
					new short[][]{	//	第4波
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,206,326},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,2,146,266},
						new short[]{Wait,40},
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,300,5,146,206,266,326,386},
						new short[]{Wait,40},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,206,266,326},
					},
					
				};
	};
	private short[][][] script_56()
	{
		return
				new short[][][]
				{
				
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 0, 1},
						
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,400,1,266},//箭头	
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,360,2,206,326},
						new short[]{Wait,80},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_HZ,320,2,146,386},

					},
					new short[][]{	
						
						new short[]{Refresh1,CoolEditDefine.Enemy_Z,300,86,5,40},
                                                new short[]{Wait,50},
                                            	new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
                        						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,266,3,326,386,446},
						
                                                new short[]{Wait,50},
                                                //{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,500,2,206,326},
						


					},
					new short[][]{	//	第3波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,266},
						
					},
					new short[][]{	//	第4波
						//{DENGJI,1},
                        new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,3,146,266,386},
                        new short[]{Wait,50},
                    	new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_X,360,1,266},
						new short[]{Wait,50},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,2,206,326},
                        new short[]{Wait,50},
                        //{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,225,1,266},
						new short[]{Wait,50},
						//{DENGJI,1},
                        new short[]{Refresh,CoolEditDefine.Enemy_DS,300,3,146,266,386},
						new short[]{Wait,50},
						
                        //{DENGJI,26},
                        new short[]{Refresh,CoolEditDefine.Enemy_X,225,1,266},
						//{SHUIDI,26,150,200,2,3},
					},
								
				};
	};
	private short[][][] script_57()//任务关2
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 1, 3},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh1,CoolEditDefine.Enemy_Z,300,206,3,60},//3管齐下			
						new short[]{Refresh1,CoolEditDefine.Enemy_DS,300,266,3,60},
						new short[]{Refresh1,CoolEditDefine.Enemy_Z,300,326,3,60},

					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,3,146,266,386},//箭头形
						new short[]{Refresh,CoolEditDefine.Enemy_Z,225,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,1,266},
						new short[]{Wait,80},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_X,400,1,506},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,350,3,86,146,206},
						new short[]{Wait,100},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,2,146,386},


					},
					new short[][]{	//	第3波
						
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,3,146,266,386},
						new short[]{Wait,40},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,300,5,146,206,266,326,386},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,5,146,206,266,326,386},
						
					},
					new short[][]{	//	第4波
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,225,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,3,206,266,326},
						new short[]{Wait,40},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,3,26,146,266},
						new short[]{Wait,200},
						//{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,300,3,266,386,506},
					},
				};
	};
	private short[][][] script_58()//猪，熊，小兰怪
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 1, 1},
						
						new short[]{Refresh,CoolEditDefine.Enemy_Z,190,1,266},//心形			
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,190,2,146,386},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,230,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,270,1,266},
						new short[]{Wait,30},
                                                new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,30},
						new short[]{Refresh,CoolEditDefine.Enemy_X,210,1,266},





					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},//箭头形
                                                new short[]{Wait,60},
                                                //{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,340,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,380,1,266},
			                        


					},
					new short[][]{	//	第3波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,300,1,266},
                                                new short[]{Wait,40},
                                            	new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
                        						new short[]{Wait,60},
                                                //{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,26},
						new short[]{Wait,60},
                        new short[]{Refresh,CoolEditDefine.Enemy_HZ,190,1,86},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_HZ,230,1,146},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_HZ,270,1,206},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_HZ,310,1,266},
                                                new short[]{Wait,60},
                                                //{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,1,8},
                                                new short[]{Refresh,CoolEditDefine.Enemy_DS,190,1,446},
                                                new short[]{Refresh,CoolEditDefine.Enemy_DS,230,1,386},
                                                new short[]{Refresh,CoolEditDefine.Enemy_DS,270,1,326},
                                                new short[]{Refresh,CoolEditDefine.Enemy_DS,310,1,266},

					},
					new short[][]{	//	第4波
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,146,206,266},
                                                new short[]{Wait,60},
                                            	new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
                        						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,3,146,266,386},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,3,206,266,326},
                                                new short[]{Wait,60},
                                                //{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,450,1,266},
						new short[]{Wait,120},
						//{DENGJI,1},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,190,266,3,40},
					},
				};
	};
	private short[][][] script_59()//猪，熊，小兰怪
	{
		return
				new short[][][]
				{
				new short[][]{	//	第1波
						new short[]{OpenFight2Music},
						new short[]{Teleport, 1, 3},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_X,190,1,266},//心形			
						new short[]{Refresh,CoolEditDefine.Enemy_Z,150,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,190,2,146,386},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,230,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_Z,270,1,266},
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,210,1,266},





					},
					new short[][]{	//	第2波(Refresh，种类，Y,数量，X1,X2....)
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,150,1,266},//箭头形
                                                new short[]{Wait,60},
                                            	new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
                        						new short[]{Wait,60},
                                                //{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,1,266},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,340,2,206,326},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,380,1,266},
			                        


					},
					new short[][]{	//	第3波
						//{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,300,1,266},
                                                new short[]{Wait,90},
                                                //{DENGJI,1},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,150,1,26},
						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						new short[]{Wait,60},
                        new short[]{Refresh,CoolEditDefine.Enemy_HZ,190,1,86},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_DS,230,1,146},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_DS,270,1,206},
                                                new short[]{Wait,60},
                                                new short[]{Refresh,CoolEditDefine.Enemy_DS,310,1,266},
                                                new short[]{Wait,60},
                                                //{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_MIMI,150,1,8},
                                                new short[]{Refresh,CoolEditDefine.Enemy_MIMI,190,1,446},
                                                new short[]{Refresh,CoolEditDefine.Enemy_MIMI,230,1,386},
                                                new short[]{Refresh,CoolEditDefine.Enemy_MIMI,270,1,326},
                                                new short[]{Refresh,CoolEditDefine.Enemy_MIMI,310,1,266},

					},
					new short[][]{	//	第4波
						//{DENGJI,2},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,146,206,266},
                                                new short[]{Wait,60},
            new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
                        						new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,146,266,386},
                                                new short[]{Wait,60},
						new short[]{Refresh,CoolEditDefine.Enemy_DS,150,3,206,266,326},
                                                new short[]{Wait,60},
                                                //{DENGJI,26},
						new short[]{Refresh,CoolEditDefine.Enemy_X,450,1,266},
						new short[]{Wait,120},
						//{DENGJI,1},
						new short[]{Refresh1,CoolEditDefine.Enemy_HZ,190,266,3,40},
					},
				};
	};
	
	private short[][][] script_60()//BOSS 大象
	{
		return
				new short[][][]
				{
				new short[][]{  
						new short[]{OpenBossMusic},
						new short[]{Refresh,CoolEditDefine.Enemy_HZ,300,2,26,506},
						new short[]{Wait,60},
				        new short[]{Refresh,CoolEditDefine.Enemy_MM,450,1,266},
				        new short[]{Wait,60},
				    	new short[]{Refresh,CoolEditDefine.Enemy_SIREN,150,1,266},
						
				   }
				};
	};
	
	private short[][][] script_61()//隐藏关卡
	{
		return
			new short[][][]
			{
				new short[][]{  
					new short[]{OpenBossMusic},
					
					new short[]{Refresh1,CoolEditDefine.Enemy_MMB1,220,0,1,0},				
					new short[]{Wait, 10},					
					new short[]{Refresh1,CoolEditDefine.Enemy_MMB2,330,0,1,0},
					new short[]{Wait, 10},
					new short[]{Refresh1,CoolEditDefine.Enemy_MMB3,440,0,1,0},
					new short[]{Wait, 10},
					new short[]{Refresh1,CoolEditDefine.Enemy_MMB4,550,0,1,0},	
					
					new short[]{Wait, -10},
					
					new short[]{GoTo, 1, 7, 8},	
				}
			};
	};
	
	
//	/* 
//	 * 关卡脚本1
//	 * */
//    private static short[][][] script_1()//
//	{
//		return new short[][][]
//		{
//			new short[][]
//			{	//	第1波	
////				new short[]{Fog},
////					
////				new short[]{Teleport, 0, 0},
////				
////				new short[]{Board, 150,300,200,480},	
////					
//				new short[]{OpenFight1Music},		
//					
////				new short[]{OpenBubble},
//				
////				new short[]{Refresh, CoolEditDefine.Enemy_MM, 200, 1, 266},	
//					
//				new short[]{Refresh, CoolEditDefine.Enemy_MIMI, 200, 1, 266},
////				new short[]{Refresh, CoolEditDefine.Enemy_MEGICWATER, 400, 1, 100},
//				new short[]new short[]{Wait,60},	
//				
////				new short[]{Refresh, CoolEditDefine.Enemy_MIMI, 200, 1, 266},
////				new short[]new short[]{Wait,60},
////				new short[]{Refresh, CoolEditDefine.Enemy_MIMI, 200, 1, 266},
////				new short[]new short[]{Wait,60},
////				new short[]{Refresh, CoolEditDefine.Enemy_MIMI, 200, 1, 266},
////				new short[]new short[]{Wait,60},
////				new short[]{Refresh, CoolEditDefine.Enemy_MIMI, 200, 1, 266},
////				new short[]new short[]{Wait,60},
////				new short[]{Refresh, CoolEditDefine.Enemy_MIMI, 200, 1, 266},
////				new short[]new short[]{Wait,60},
////				new short[]{Refresh, CoolEditDefine.Enemy_MIMI, 200, 1, 266},
////				new short[]new short[]{Wait,60},
//				
////				new short[]{Refresh, SpriteLibrary.Enemy_MEGICWATER, 150, 1, 266},
//				
////				new short[]{Refresh1,SpriteLibrary.Enemy_YGMM, 100, 266, 5, 30},
//			},		
//			
////			new short[][]
////			{	//	第2波
////				new short[]{OpenBossMusic},	
////				new short[]{Refresh, CoolEditDefine.Enemy_MM, 200, 1, 266},
////			},		
//		};
//	}
}
