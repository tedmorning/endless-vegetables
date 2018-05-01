package com.game.data;

/**
 * 农场的数据
 * */
public class FarmData1 {
	//钻石购买的土地是否解锁
    public static boolean isunLOCK = false;
    //当前土地种植的类型
    public static byte TYPE[] = new byte[4];
    //当前土地种植的状态
    public static byte STATE[] = new byte[4];
    //当前土地种植的当前时间
    public static long DQ_TIME[] = new long[4];
    //当前土地种植的总的时间
    public static long SIZE_TIME[] = new long[4];
    
	//需要100钻石解锁
	public static final int DIALOGBOX = 100; 
    //是砖石购买道具1点数
	public static final int BUYPROP = 10; 
	//铲除一个道具需要用的铲子数
	public static final int DELETEPROP = 1;
	//打开宝箱需要的砖石
	public static final int BOXPROP = 100;
	//一件成熟价格
	public static final int ONE_EY = 100;
	
	//铲子的个数
	private static int deleteNumber = 1000;
	//肥料
	public static int manureNumber = 90000;
	//药水
	public static int liquidMedicineNuber = 999;
	

	//爱心
	public static final byte FARM_PLANTHEART = 1;
	//卡牌
	public static final byte FARM_PLANTCARD = 2;
	//金币
	public static final byte FARM_PLANTGOLD = 3;
	//宝箱
	public static final byte FARM_PLANTBox = 4;
	/**
	 * 四块土地的配置接口
	 * */
	public final static long Random_Plan[][][] = {
			 {  {1,2,3,4}/**能被种植的id*/ , {50, 20, 10, 20}/**对应种植id的机率*/, {(1000*60)*30/**对应种植的cd时间 (1000*60) 为一分钟 */} },
			 {  {1,2,3,4}/**能被种植的id*/ , {50, 20, 10, 20}/**对应种植id的机率*/, {(1000*60)*60/**对应种植的cd时间 (1000*60) 为一分钟 */} },
			 {  {1,2,3,4}/**能被种植的id*/ , {50, 20, 10, 20}/**对应种植id的机率*/, {(1000*60)*160/**对应种植的cd时间 (1000*60) 为一分钟 */} },
			 {  {1,2,3,4}/**能被种植的id*/ , {50, 20, 10, 20}/**对应种植id的机率*/, {(1000*60)*260/**对应种植的cd时间 (1000*60) 为一分钟 */} },
	};
 
	/**
	 * 宝箱的机率
	 * */
	public final static int Random_BOX[][][] = {
			{  {1,2,3,4}/**卡牌的id*/ , {50, 200}/**金币的机率*/, {100, 500}/**金币的机率砖石*/ },
			{  {1,2,3,4}/**卡牌的id*/ , {50, 200}/**金币的机率*/, {100, 500}/**金币的机率砖石*/ },
			{  {1,2,3,4}/**卡牌的id*/ , {50, 200}/**金币的机率*/, {100, 500}/**金币的机率砖石*/ },
			{  {1,2,3,4}/**卡牌的id*/ , {50, 200}/**金币的机率*/, {100, 500}/**金币的机率砖石*/ },
	};
	/**
	 * 每块地宝箱随机的卡牌张数
	 * */
	public final int Random_CardNumber[][] = {
			{  1, 2  },
			{  1, 2  },
			{  1, 2  },
			{  1, 2  }
	};
	/**
	 * 卡牌的随机
	 * */
	public final static int Random_CardId[][] = {
			{1,50},
			{20,50},
			{23,50},
			{44,50},
	};
	/**
	 * 金币的随机
	 * */
	public final static int Random_Gold[][] = {
			{1,50},
			{20,50},
			{23,50},
			{44,50},
	};
	/**
	 * 爱心的随机
	 * */
	public final static int Random_LoveStar[][] = {
			{1,50},
			{20,50},
			{23,50},
			{44,50},
	};
	/**
	 * 每块地随机的卡牌张数
	 * */
	public final static int Random_PU_CardNumber[][] = {
			{  1, 3  },
			{  1, 3  },
			{  1, 3  },
			{  1, 3  }
	};
	
	public static void addDelete(int number){
		deleteNumber+=number;
	}
	
	public static int getDelete(){
		return deleteNumber;
	}
	
//	/**
//	 * 宝箱的机率
//	 * */
//	public final int Random_BOX[][][][] = {
//			//第一块地
//			{ {  {1,2/**宝箱1卡牌id*/} , {1,2/**宝箱2卡牌id*/} ,{1,2/**宝箱3卡牌id*/}, {1,2/**宝箱4卡牌id*/},   },
//			  {  {10, 1000/**宝箱1的金币随机范围*/},{10, 1000/**宝箱2的金币随机范围*/},{10, 1000/**宝箱3的金币随机范围*/},{10, 1000/**宝箱4的金币随机范围*/},   }, 
//			  {  {10, 1000/**宝箱1的砖石随机范围*/},{10, 1000/**宝箱2的砖石随机范围*/},{10, 1000/**宝箱3的砖石随机范围*/},{10, 1000/**宝箱4的砖石随机范围*/},   }
//			},
//			//第二块地
//			{ {  {1,2/**宝箱1卡牌id*/} , {1,2/**宝箱2卡牌id*/} ,{1,2/**宝箱3卡牌id*/}, {1,2/**宝箱4卡牌id*/},   },
//			  {  {10, 1000/**宝箱1的金币随机范围*/},{10, 1000/**宝箱2的金币随机范围*/},{10, 1000/**宝箱3的金币随机范围*/},{10, 1000/**宝箱4的金币随机范围*/},   }, 
//			  {  {10, 1000/**宝箱1的砖石随机范围*/},{10, 1000/**宝箱2的砖石随机范围*/},{10, 1000/**宝箱3的砖石随机范围*/},{10, 1000/**宝箱4的砖石随机范围*/},   }
//			},
//			//第三块地
//			{ {  {1,2/**宝箱1卡牌id*/} , {1,2/**宝箱2卡牌id*/} ,{1,2/**宝箱3卡牌id*/}, {1,2/**宝箱4卡牌id*/},   },
//			  {  {10, 1000/**宝箱1的金币随机范围*/},{10, 1000/**宝箱2的金币随机范围*/},{10, 1000/**宝箱3的金币随机范围*/},{10, 1000/**宝箱4的金币随机范围*/},   }, 
//			  {  {10, 1000/**宝箱1的砖石随机范围*/},{10, 1000/**宝箱2的砖石随机范围*/},{10, 1000/**宝箱3的砖石随机范围*/},{10, 1000/**宝箱4的砖石随机范围*/},   }
//			},
//			//第四块地
//			{ {  {1,2/**宝箱1卡牌id*/} , {1,2/**宝箱2卡牌id*/} ,{1,2/**宝箱3卡牌id*/}, {1,2/**宝箱4卡牌id*/},   },
//			  {  {10, 1000/**宝箱1的金币随机范围*/},{10, 1000/**宝箱2的金币随机范围*/},{10, 1000/**宝箱3的金币随机范围*/},{10, 1000/**宝箱4的金币随机范围*/},   }, 
//			  {  {10, 1000/**宝箱1的砖石随机范围*/},{10, 1000/**宝箱2的砖石随机范围*/},{10, 1000/**宝箱3的砖石随机范围*/},{10, 1000/**宝箱4的砖石随机范围*/},   }
//			},
//			
//	};
	
	
	/**
	 * 水壶减少时间
	 * */
	public final static long MANURE = 60*1000*30; //暂定减少半小时
	/**
	 * 每块土地使用是需要的水壶点数
	 * */
	public final static long MANURE_POIT[] = {5, 10, 15, 20};
	
 
	/**
	 * 未种植  
	 * */
	public static final byte unplanted = 0;

	/**
	 * 生长状态
	 * */
	public static final byte grow = 1;

	/**
	 * 收获
	 * */
	public static final byte get = 2;
	
	
//	public final static byte white = 0; 	    //白色
//	public final static byte blue = 1;  	    //蓝色
//	public final static byte orange = 2;	    //橙色
//	public final static byte golden = 3;		//金色
	public final static byte notUnlock = -1;   //未解锁
	
}//end class
