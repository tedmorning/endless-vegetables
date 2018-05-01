package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import com.socogame.coolEdit.CoolEditDefine;

public class GameMission {
	
	public static final byte MISSION_1 = 1;//通关时，保持篱笆满血
	public static final byte MISSION_2 = 2;//使用XXX（蔬菜名）完成本关的最后一击
	public static final byte MISSION_3 = 3;//屏幕中不能同时存在超过XX（数量）个XXXX（怪物名）
	public static final byte MISSION_4 = 4;//完成XXX（数量）连击
	public static final byte MISSION_5 = 5;//连续击杀XX（数量）个XXXX（怪物名）
	public static final byte MISSION_6 = 6;//用贯穿攻击一次杀死N个XXX（怪物名）
	public static final byte MISSION_7 = 7;//游戏开始XX（数量）秒内不使用主蔬菜
	public static final byte MISSION_8 = 8;//XX（数量）秒内杀死XX（数量）个怪物
	public static final byte MISSION_9 = 9;//用燃烧的蔬菜杀死XX（数量）个XXXX（怪物名）
	public static final byte MISSION_10 = 10;//维持城墙血量在30%（50%，80%）以上
	public static final byte MISSION_11 = 11;//晕眩场上XX个的怪物
	
	public static final byte MISSION_12 = 12;//怪物攻击城墙的次数小于X次 
	public static final byte MISSION_13 = 13;//游戏得分达到X分  
	public static final byte MISSION_14 = 14;//游戏中使用X次燃烧(无论道具还是连击达成） 	
	public static final byte MISSION_15 = 15;//游戏开始后X秒内不使用蔬菜弹射 	
	public static final byte MISSION_16 = 16;//游戏中总共使X个怪物处于异常状态（眩晕、冰冻、缓速等）	
	public static final byte MISSION_17 = 17;//使用任意蔬菜伙伴次数达到X次 	
	public static final byte MISSION_18 = 18;//在X时间内完成游戏 
	public static final byte MISSION_19 = 19;//碰到跳板的怪物小于X个	
	public static final byte MISSION_20 = 20;//使X个以上蔬菜穿越时空门	
	public static final byte MISSION_21 = 21;//警报器触发X次以下
	public static final byte MISSION_22 = 22;//用燃烧的蔬菜击败X个怪物
	public static final byte MISSION_23 = 23;//关卡过关
	
	public static final int taskArray[][][] = 
	{
//		{//例子
////			{MISSION_1},//关卡中的任务类型
////			{MISSION_2, CoolEditDefine.Player_FQ},
////			{MISSION_3, CoolEditDefine.Enemy_MIMI, 10},
////			{MISSION_4, 10},
////			{MISSION_5, CoolEditDefine.Enemy_MIMI, 10},
////			{MISSION_6, CoolEditDefine.Enemy_MIMI, 10},
////			{MISSION_7, 20},
////			{MISSION_8, 20, 10},//{任务类型，时间，怪物数量}
////			{MISSION_9, CoolEditDefine.Enemy_MIMI, 10},
////			{MISSION_10, 20},
////			{MISSION_11, 20},
////			
//			{MISSION_12, 10},
//			{MISSION_13, 5000},
//			{MISSION_14, 5},
////			{MISSION_15, 10},
////			{MISSION_16, 10},
////			{MISSION_17, 10},			
////			{MISSION_18, 60},
////			{MISSION_19, 10},
////			{MISSION_20, 50},
////			{MISSION_21, 5},
////			{MISSION_22, 20},
////			{MISSION_23},
//		}
		new int[][]{//关卡1
			{MISSION_14,1},
			{MISSION_13,8000},
			{MISSION_23},
			
		},
		new int[][]{//关卡2
			{MISSION_10,100},
			{MISSION_13,10000},
			{MISSION_23},
			
		},
		new int[][]{//关卡3
			{MISSION_14,1},
			{MISSION_13,12000},
			{MISSION_23},
			
		},
		new int[][]{//关卡4
			{MISSION_16,5},
			{MISSION_12,0},
			{MISSION_23},
			
		},
		new int[][]{//关卡5
			{MISSION_17,3},
			{MISSION_10,97},
			{MISSION_23},
			
		},
		new int[][]{//关卡6
			{MISSION_13,15000},
			{MISSION_18,30},
			{MISSION_23},
			
		},
		new int[][]{//关卡7
			{MISSION_12,0},
			{MISSION_14,3},
			{MISSION_23},
			
		},
		new int[][]{//关卡8
			{MISSION_10,98},
			{MISSION_16,10},
			{MISSION_23},
			
		},
		new int[][]{//关卡9
			{MISSION_17,6},
			{MISSION_13,20000},
			{MISSION_23},
			
		},
		new int[][]{//关卡10
			{MISSION_22,10},
			{MISSION_12,0},
			{MISSION_23},
			
		},
		new int[][]{//关卡11
			{MISSION_10,98},
			{MISSION_16,15},
			{MISSION_23},
			
		},
		new int[][]{//关卡12
			{MISSION_12,0},
			{MISSION_13,24000},
			{MISSION_23},
			
		},
		new int[][]{//关卡13
			{MISSION_18,45},
			{MISSION_22,12},
			{MISSION_23},
			
		},
		new int[][]{//关卡14
			{MISSION_14,5},
			{MISSION_10,95},
			{MISSION_23},
			
		},
		new int[][]{//关卡15
			{MISSION_13,30000},
			{MISSION_16,18},
			{MISSION_23},
			
		},
		new int[][]{//关卡16
			{MISSION_22,12},
			{MISSION_18,50},
			{MISSION_23},
			
		},
		new int[][]{//关卡17
			{MISSION_15,8},
			{MISSION_13,32000},
			{MISSION_23},
			
		},
		new int[][]{//关卡18
			{MISSION_22,10},
			{MISSION_12,1},
			{MISSION_23},
			
		},
		new int[][]{//关卡19
			{MISSION_18,40},
			{MISSION_13,20000},
			{MISSION_23},
			
		},
		new int[][]{//关卡20
			{MISSION_15,6},
			{MISSION_22,15},
			{MISSION_23},
			
		},
		new int[][]{//关卡21
			{MISSION_13,20000},
			{MISSION_10,96},
			{MISSION_23},
			
		},
		new int[][]{//关卡22
			{MISSION_18,42},
			{MISSION_14,6},
			{MISSION_23},
			
		},
		new int[][]{//关卡23
			{MISSION_13,30000},
			{MISSION_16,10},
			{MISSION_23},
			
		},
		new int[][]{//关卡24
			{MISSION_12,0},
			{MISSION_16,10},
			{MISSION_23},
			
		},
		new int[][]{//关卡25
			{MISSION_18,48},
			{MISSION_13,32000},
			{MISSION_23},
			
		},
		new int[][]{//关卡26
			{MISSION_21,1},
			{MISSION_12,2},
			{MISSION_23},
			
		},
		new int[][]{//关卡27
			{MISSION_21,1},
			{MISSION_10,95},
			{MISSION_23},
			
		},
		new int[][]{//关卡28
			{MISSION_21,1},
			{MISSION_13,36000},
			{MISSION_23},
			
		},
		new int[][]{//关卡29
			{MISSION_21,2},
			{MISSION_22,10},
			{MISSION_23},
			
		},
		new int[][]{//关卡30
			{MISSION_10,90},
			{MISSION_22,12},
			{MISSION_23},
			
		},
		new int[][]{//关卡31
			{MISSION_19,1},
			{MISSION_13,32000},
			{MISSION_23},
			
		},
		new int[][]{//关卡32
				{MISSION_19,1},
				{MISSION_12,1},
				{MISSION_23},
				
			},
			new int[][]{//关卡33
					{MISSION_19,1},
					{MISSION_10,96},
					{MISSION_23},
					
				},
				new int[][]{//关卡34
						{MISSION_19,1},
						{MISSION_13,33000},
						{MISSION_23},
						
					},
		new int[][]{//关卡35
							{MISSION_19,2},
							{MISSION_18,8},
							{MISSION_23},
							
						},
						new int[][]{//关卡36
								{MISSION_18,45},
								{MISSION_16,10},
								{MISSION_23},
								
							},
							new int[][]{//关卡37
									{MISSION_21,1},
									{MISSION_13,36000},
									{MISSION_23},
									
								},
								new int[][]{//关卡38
										{MISSION_21,1},
										{MISSION_13,37000},
										{MISSION_23},
										
									},
									new int[][]{//关卡39
											{MISSION_21,1},
											{MISSION_22,12},
											{MISSION_23},
											
										},
							new int[][]{//关卡40
										{MISSION_21,1},
										{MISSION_10,100},
										{MISSION_23},
												
											},
							new int[][]{//关卡41
										{MISSION_13,40000},
										{MISSION_21,2},
										{MISSION_23},
															
														},
							new int[][]{//关卡42
									    {MISSION_12,0},
										{MISSION_18,50},
										{MISSION_23},
										},
			new int[][]{//关卡43
						{MISSION_19,1},
					    {MISSION_10,90},
						{MISSION_23},
					   },
					   new int[][]{//关卡44
								{MISSION_19,1},
							    {MISSION_13,50000},
								{MISSION_23},
							   },
							   new int[][]{//关卡45
										{MISSION_19,1},
									    {MISSION_18,8},
										{MISSION_23},
									   },
									   new int[][]{//关卡46
												{MISSION_19,1},
											    {MISSION_16,10},
												{MISSION_23},
											   },
											   new int[][]{//关卡47
														{MISSION_19,1},
													    {MISSION_13,42000},
														{MISSION_23},
													   },
													   new int[][]{//关卡48
																{MISSION_13,50000},
															    {MISSION_22,10},
																{MISSION_23},
															   },
															   new int[][]{//关卡49
																		{MISSION_20,10},
																	    {MISSION_10,95},
																		{MISSION_23},
																	   },
		new int[][]
		          {//关卡50
					{MISSION_20,12},
				    {MISSION_13,60000},
					{MISSION_23},
				  },
				  new int[][]
		          {//关卡51
					{MISSION_20,16},
				    {MISSION_18,12},
					{MISSION_23},
				  },
				  new int[][]
					          {//关卡52
								{MISSION_20,20},
							    {MISSION_16,10},
								{MISSION_23},
							  },
				  new int[][]
					          {//关卡53
								{MISSION_20,24},
							    {MISSION_13,63000},
								{MISSION_23},
							  },
				  new int[][]
					          {//关卡54
								{MISSION_13,75000},
							    {MISSION_16,10},
								{MISSION_23},
							  },
				  new int[][]
					          {//关卡55
								{MISSION_21,1},
							    {MISSION_20,16},
								{MISSION_23},
							  },
				  new int[][]
					          {//关卡56
								{MISSION_21,1},
							    {MISSION_20,16},
								{MISSION_23},
							  },
				  new int[][]
					          {//关卡57
								{MISSION_21,1},
							    {MISSION_20,20},
								{MISSION_23},
							  },
				  new int[][]
					          {//关卡58
								{MISSION_21,1},
							    {MISSION_20,24},
								{MISSION_23},
							  },
				  new int[][]
					          {//关卡59
								{MISSION_21,1},
							    {MISSION_20,24},
								{MISSION_23},
							  },
				  new int[][]
					          {//关卡60
								{MISSION_10,80},
							    {MISSION_13,80000},
								{MISSION_23},
							  },
	};
	
	private ArrayList<Mission> stageMissionList;
	
	public int lastEndPlayId;//完成最后一击的蔬菜id
	
//	public boolean isSendPlayId;//是否发射过蔬菜
	
	private ArrayList<Integer> monsterDeadTimeList;//怪物的死亡时间
	
	public GameMission(int stage)
	{
		init(stage);
	}
	
	public ArrayList<Mission> getStageMissionList()
	{
		return stageMissionList;
	}
	
//	public void setSendPlayId(boolean _isSend)
//	{
//		isSendPlayId = _isSend;
//	}
	
	public void addMonsterDeadTime(GameMain gameMain)
	{
		int t = gameMain.gameUI.getCurrentGameTime();
		
		monsterDeadTimeList.add(t);
	}
	
	public ArrayList<Integer> getMonsterDeadTimeList()
	{
		return monsterDeadTimeList;
	}
	
	public void init(int stage)
	{
		stageMissionList = new ArrayList<Mission>();
		
		for(int i=0;i<taskArray[stage].length;i++)
		{
			Mission mission = new Mission();
			
			mission.missionId = (byte)taskArray[stage][i][0];
			
			setMission(mission, taskArray[stage][i]);
			
			stageMissionList.add(mission);
		}
		
		lastEndPlayId = 0;
		
//		isSendPlayId = false;
		
		monsterDeadTimeList = new ArrayList<Integer>();
	}
		
	public void setMission(Mission mission, int[] taskValue)
	{
		switch(mission.missionId)
		{
			case MISSION_2:
				mission.playId = (byte)taskValue[1];
				break;
				
			case MISSION_3:
				mission.enemyId = (byte)taskValue[1];
				mission.enemyNumber = taskValue[2];
				mission.enemySum = 0;
				break;	
				
			case MISSION_4:
				mission.comboNumber = taskValue[1];
				mission.comboSum = 0;
				break;
				
			case MISSION_5:
				mission.enemyId = (byte)taskValue[1];
				mission.enemyNumber = taskValue[2];
				mission.enemySum = 0;
				break;	
				
			case MISSION_6:
				mission.enemyId = (byte)taskValue[1];
				mission.enemyNumber = taskValue[2];
				mission.enemySum = 0;
				break;		
				
			case MISSION_7:
				mission.waitingTime = taskValue[1];
				mission.gameTime = 0;
				break;	
			
			case MISSION_8:
				mission.waitingTime = taskValue[1];
				mission.gameTime = 0;
				mission.enemyNumber = taskValue[2];
				mission.enemySum = 0;
				break;	
				
			case MISSION_9:
				mission.enemyId = (byte)taskValue[1];
				mission.enemyNumber = taskValue[2];
				mission.enemySum = 0;
				break;		
				
			case MISSION_10:
				mission.latticeLifePercent = taskValue[1];
//				mission.isLatticeLifePercentSuccess = true;
				break;	
				
			case MISSION_11:
				mission.enemyNumber = taskValue[1];
				mission.enemySum = 0;
				break;	
				
			case MISSION_12:
				mission.monsterAttackLatticeNumber = taskValue[1];
				mission.monsterAttackLatticeSum = 0;
				break;		
				
			case MISSION_13:
				mission.gameNumber = taskValue[1];
				mission.gameNumberSum = 0;
				break;	
				
			case MISSION_14:
				mission.completeComboNumber = taskValue[1];
				mission.completeComboSum = 0;
				break;	
				
			case MISSION_15:
				mission.waitingTime = taskValue[1];
				mission.gameTime = 0;
				break;	
				
			case MISSION_16:
				mission.enemyNumber = taskValue[1];
				mission.enemySum = 0;
				break;		
				
			case MISSION_17:
				mission.rightSideNumber = taskValue[1];
				mission.rightSideSum = 0;
				break;		
				
			case MISSION_18:
				mission.waitingTime = taskValue[1];
				mission.gameTime = 0;
				break;
				
			case MISSION_19:
				mission.enemyNumber = taskValue[1];
				mission.enemySum = 0;
				break;	
				
			case MISSION_20:
				mission.enemyNumber = taskValue[1];
				mission.enemySum = 0;
				break;
								
			case MISSION_21:
				mission.sirenCallNumber = taskValue[1];
				mission.sirenCallSum = 0;
				break;
				
			case MISSION_22:			
				mission.enemyNumber = taskValue[1];
				mission.enemySum = 0;
				break;							
		}			
	}
	
	public int[] getMissionInfo(Mission mission)
	{
		int info[] = new int[3];
		
		switch(mission.missionId)
		{					
			case MISSION_2:
				info[0] = mission.playId;
				info[1] = 0;
				info[2] = 0;				
				return info;
				
			case MISSION_3:				
				info[0] = mission.enemyId;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;	
				return info;
				
			case MISSION_4:				
				info[0] = 0;
				info[1] = mission.comboSum;
				info[2] = mission.comboNumber;					
				return info;
				
			case MISSION_5:				
				info[0] = mission.enemyId;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;		
				return info;	
				
			case MISSION_6:
				info[0] = mission.enemyId;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;				
				return info;		
				
			case MISSION_7:				
				info[0] = 0;
				info[1] = mission.gameTime;
				info[2] = mission.waitingTime;		
				return info;	
			
			case MISSION_8:				
				info[0] = 0;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;	
				return info;	
				
			case MISSION_9:				
				info[0] = mission.enemyId;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;	
				return info;		
				
			case MISSION_10:				
				info[0] = 0;
				info[1] = 0;
				info[2] = mission.latticeLifePercent;	
				return info;	
				
			case MISSION_11:			
				info[0] = 0;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;	
				return info;	
				
			case MISSION_12:				
				info[0] = 0;
				info[1] = mission.monsterAttackLatticeSum;
				info[2] = mission.monsterAttackLatticeNumber;
				return info;		
				
			case MISSION_13:				
				info[0] = 0;
				info[1] = mission.gameNumberSum;
				info[2] = mission.gameNumber;
				return info;	
				
			case MISSION_14:
				info[0] = 0;
				info[1] = mission.completeComboSum;
				info[2] = mission.completeComboNumber;
				return info;	
				
			case MISSION_15:				
				info[0] = 0;
				info[1] = mission.gameTime;
				info[2] = mission.waitingTime;				
				return info;	
				
			case MISSION_16:				
				info[0] = 0;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;						
				return info;		
				
			case MISSION_17:
				info[0] = 0;
				info[1] = mission.rightSideSum;
				info[2] = mission.rightSideNumber;	
				return info;		
				
			case MISSION_18:
				info[0] = 0;
				info[1] = mission.gameTime;
				info[2] = mission.waitingTime;	
				return info;
				
			case MISSION_19:
				info[0] = 0;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;	
				return info;	
				
			case MISSION_20:
				info[0] = 0;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;	
				return info;
								
			case MISSION_21:
				info[0] = 0;
				info[1] = mission.sirenCallSum;
				info[2] = mission.sirenCallNumber;	
				return info;
				
			case MISSION_22:	
				info[0] = 0;
				info[1] = mission.enemySum;
				info[2] = mission.enemyNumber;	
				return info;	
				
			default:
				info[0] = 0;
				info[1] = 0;
				info[2] = 0;
				return info;	
		}			
	}
	
	
	public ArrayList<Integer> getGameMissionIndex(byte missionId)
	{
		ArrayList<Integer> index = new ArrayList<Integer>();
		
		for(int i=0;i<stageMissionList.size();i++)
		{
			if(stageMissionList.get(i).missionId==missionId)
			{
				index.add(i);
			}
		}
		
		return index;
	}
	
	public class Mission
	{		
		public byte missionId;
		
		public byte playId;//蔬菜id
		
		public byte enemyId;//怪物id
		
		public int enemyNumber;//怪物数量
		
		public int enemySum;//计算怪物数量
		
		public int comboNumber;//连击数
		
		public int comboSum;//计算连击数量
		
		public int waitingTime;//需要等待的时间
		
		public int gameTime;//游戏时间
		
		public int latticeLifePercent;//栅栏生命百分比
		
//		public boolean isLatticeLifePercentSuccess;//判断栅栏生命百分比是否低于给出的标准
		
		public int monsterAttackLatticeNumber;//怪物攻击城墙的次数
		
		public int monsterAttackLatticeSum;//计算怪物攻击城墙的次数
		
		public int gameNumber;//游戏得分
		
		public int gameNumberSum;//计算游戏得分
		
		public int completeComboNumber;//达成燃烧数
		
		public int completeComboSum;//累计燃烧数
		
		public int rightSideNumber;//右侧伙伴数
		
		public int rightSideSum;//累计右侧伙伴数
		
		public int sirenCallNumber;//警报器触发次数
		
		public int sirenCallSum;//累计警报器触发次数
		
		public int result;//任务结果 -1：进行中，0：未达成，1：已达成	
		
		public Mission()
		{
			result = -1;			
		}
		
		public void setMissionResult(int _result)
		{
			result = _result;
		}
		
		public int getMissionResult()
		{
			return result;
		}
		
//		public boolean getMission1(int lifeMax, int currentLife)
//		{
//			if(!result)
//			{
//				if(currentLife>=lifeMax)
//					result = true;	
//			}
//			
//			return result;
//		}
//		
//		public boolean getMission2(byte _playId)
//		{
//			if(!result)
//			{
//				if(playId==_playId)
//					result = true;
//			}
//							
//			return result;
//		}
//		
//		public void setMission3(byte _enemyId)
//		{
//			if(enemyId==_enemyId)
//			{
//				enemySum ++;
//			}
//		}
//		
//		public void setMission3()
//		{
//			if(!result)
//			{
//				if(enemySum<=enemyNumber)
//				{
//					enemySum = 0;										
//				}
//			}
//		}
//		
//		public boolean getMission3()
//		{			
//			if(!result)
//			{
//				if(enemySum<=enemyNumber)
//				{
////					enemySum = 0;
////					
//					result = true;
//				}
//			}
//			
//			return result;
//		}
//		
//		public void setMission4(int _comboSum)
//		{
//			comboSum = _comboSum;
//		}
//		
//		public boolean getMission4()
//		{		
//			if(!result)
//			{
//				if(comboSum>=comboNumber)			
//					result = true;				
//			}
//			
//			return result;
//		}
//		
//		public void setMission5(byte _enemyId)
//		{
//			if(enemyId==_enemyId)
//			{
//				enemySum ++;
//			}
//			else enemySum = 0;
//		}
//		
//		public boolean getMission5()
//		{			
//			if(!result)
//			{
//				if(enemySum>=enemyNumber)			
//					result = true;
//				else			
//					enemySum = 0;									
//			}
//			
//			return result;
//		}
//		
//		public void setMission6(byte _enemyId)
//		{
//			if(enemyId==_enemyId)
//			{
//				enemySum ++;
//			}
//			else enemySum = 0;
//		}
//		
//		public boolean getMission6()
//		{			
//			if(!result)
//			{
//				if(enemySum>=enemyNumber)			
//					result = true;
//				else 
//					enemySum = 0;
//			}
//			
//			return result;
//		}
//		
//		public void setMission7(int _gameTime)
//		{
//			gameTime = _gameTime;
//		}
//		
//		public boolean getMission7()
//		{		
//			if(!result)
//			{
////				if(!isSendPlayId)
////				{
//					if(gameTime>=waitingTime)			
//						result = true;
////				}
//			}
//			
//			return result;
//		}
		
//		public void setMission8(byte _gameTime)
//		{
//			gameTime = _gameTime;
//			
//			enemySum ++;
//		}
		
//		public boolean getMission8()
//		{		
//			if(!result)
//			{
//				if(monsterDeadTimeList.size()>=enemyNumber)
//				{
//					int t = (monsterDeadTimeList.get(monsterDeadTimeList.size()-enemyNumber)-monsterDeadTimeList.get(monsterDeadTimeList.size()-1))/25;
//					
//					if(t<=waitingTime)
//					{
//						result = true;							
//					}
//				}					
//			}
//			
//			return result;
//		}
		
//		public void setMission9(byte _enemyId)
//		{
//			if(enemyId==_enemyId)
//			{
//				enemySum ++;
//			}			
//		}
//		
//		public boolean getMission9()
//		{		
//			if(!result)
//			{
//				if(enemySum>=enemyNumber)			
//					result = true;				
//			}
//			
//			return result;
//		}
		
		public void setMission10(int lifeMax, int currentLife)
		{
			if(result==-1)
			{
//				if(isLatticeLifePercentSuccess)
//				{
					float tmp = latticeLifePercent/100;
					
					if(currentLife<lifeMax*tmp)					
//						isLatticeLifePercentSuccess = false;
						result = 0;											
//				}
			}			
		}
		
		public void getMission10(int lifeMax, int currentLife)
		{									
			if(result==-1)
			{
				float tmp = latticeLifePercent/100;
					
				if(currentLife<lifeMax*tmp)			
					result = 0;	
				else
					result = 1;	
			}
		}
		
//		public void setMission11()
//		{			
//			enemySum ++;		
//		}
//		
//		public boolean getMission11()
//		{		
//			if(!result)
//			{
//				if(enemySum>=enemyNumber)	
//				{					
//					result = true;
//				}	
//				else enemySum = 0;
//			}
//			
//			return result;
//		}	
		
		public void setMission12(int _monsterAttackLatticeNumber)
		{			
			monsterAttackLatticeSum = _monsterAttackLatticeNumber;	
			
			if(result==-1)
			{
				if(monsterAttackLatticeSum>monsterAttackLatticeNumber)							
					result = 0;	
			}	
		}
		
		public void getMission12()
		{		
			if(result==-1)
			{
				if(monsterAttackLatticeSum<=monsterAttackLatticeNumber)							
					result = 1;	
				else 
					result = 0;	
			}			
		}	
		
		public void setMission13(int _gameNumber)
		{			
			gameNumberSum = _gameNumber;		
		}
		
		public void getMission13()
		{		
			if(result==-1)
			{
				if(gameNumberSum>=gameNumber)							
					result = 1;
			}			
		}	
				
		public void setMission14(int _completeComboNumber)
		{			
			completeComboSum = _completeComboNumber;		
		}
		
		public void getMission14()
		{		
			if(result==-1)
			{
				if(completeComboSum>=completeComboNumber)							
					result = 1;						
			}			
		}		
		
		public void setMission15(int _gameTime)
		{
			gameTime = _gameTime;
		}
		
		public void getMission15(boolean _IsSend)
		{		
			if(result==-1)
			{
				if(_IsSend)
				{
					result = 0;
				}
				else 
				{
					if(gameTime>=waitingTime)
						result = 1;					
				}								
			}			
		}
		
		public void setMission16(int _enemyNumber)
		{			
			enemySum = _enemyNumber;		
		}
		
		public void getMission16()
		{		
			if(result==-1)
			{
				if(enemySum>=enemyNumber)							
					result = 1;				
			}			
		}	
		
		public void setMission17(int _rightSideNumber)
		{			
			rightSideSum = _rightSideNumber;		
		}
		
		public void getMission17()
		{		
			if(result==-1)
			{
				if(rightSideSum>=rightSideNumber)							
					result = 1;				
			}			
		}	
		
		public void setMission18(int _gameTime)
		{
			gameTime = _gameTime;
		}
		
		public void getMission18()
		{		
			if(result==-1)
			{
				if(gameTime<=waitingTime)			
					result = 1;
				else
					result = 0;
			}			
		}
		
		public void setMission19(int _enemyNumber)
		{			
			enemySum = _enemyNumber;		
		}
		
		public void getMission19()
		{		
			if(result==-1)
			{
				if(enemySum<enemyNumber)							
					result = 1;	
				else
					result = 0;	
			}			
		}	
		
		public void setMission20(int _enemyNumber)
		{			
			enemySum = _enemyNumber;		
		}
		
		public void getMission20()
		{		
			if(result==-1)
			{
				if(enemySum>=enemyNumber)							
					result = 1;		
				else 
					result = 0;		
			}			
		}	
		
		public void setMission21(int _sirenCallNumber)
		{			
			sirenCallSum = _sirenCallNumber;	
			
			if(result==-1)
			{
				if(sirenCallSum>sirenCallNumber)							
					result = 0;
			}
		}
		
		public void getMission21()
		{		
			if(result==-1)
			{
				if(sirenCallSum<=sirenCallNumber)							
					result = 1;
				else
					result = 0;
			}			
		}	
		
		public void setMission22()
		{			
			enemySum ++;	
		}
		
		public void getMission22()
		{		
			if(result==-1)
			{
				if(enemySum>=enemyNumber)			
					result = 1;				
			}		
		}
		
		public void getMission23()
		{
			if(result==-1)
			{				
				result = 1;	
			}			
		}
	}	
}
