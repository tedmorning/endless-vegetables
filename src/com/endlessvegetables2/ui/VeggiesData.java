/* 一.游戏中需要使用的数据
 * 1.当前的游戏关卡	
 * VeggiesData.getCurrentLevel()
 * 返回值为当前关卡的索引值，关卡值为索引值+1.
 * 即：VeggiesData.getCurrentLevel()+1,若VeggiesData.getCurrentLevel()=0则是VeggiesData.getCurrentLevel()+1则是第一关
 * 
 * 2.主蔬菜
 * VeggiesData.getCardSlot()[0]
 * 返回值为主蔬菜的索引值。
 * 主蔬菜的索引值如下：
 * GameItem.Item01,GameItem.Item02,GameItem.Item03,	//番茄,依次为1-3星
 * GameItem.Item13,GameItem.Item14,GameItem.Item15,	//豌豆,依次为1-3星
 * GameItem.Item19,GameItem.Item20,GameItem.Item21,	//辣椒,依次为1-3星
 * 
 * 3.副蔬菜
 * 副蔬菜第一格：VeggiesData.getCardSlot()[1]
 * 副蔬菜第二格：VeggiesData.getCardSlot()[2]
 * 副蔬菜第三格：VeggiesData.getCardSlot()[3]
 * 返回值为副蔬菜的索引值。其中返回值-1为该卡槽未解锁,0为无蔬菜。
 * 副蔬菜的索引值如下：
 * GameItem.Item04,GameItem.Item05,GameItem.Item06,	//萝卜,依次为1-3星
 * GameItem.Item07,GameItem.Item08,GameItem.Item09,	//洋葱,依次为1-3星
 * GameItem.Item10,GameItem.Item11,GameItem.Item12,	//土豆,依次为1-3星
 * GameItem.Item16,GameItem.Item17,GameItem.Item18,	//蘑菇,依次为1-3星
 * GameItem.Item22,GameItem.Item23,GameItem.Item24,	//花菜,依次为1-3星
 * GameItem.Item25,GameItem.Item26,GameItem.Item27,	//竹笋,依次为1-3星
 * GameItem.Item28,GameItem.Item29,GameItem.Item30,	//南瓜,依次为1-3星
 * 
 * 4.奖励卡槽
 * VeggiesData.getCardSlot()[4]
 * 返回值为奖励卡牌的索引值。其中返回值-1为该卡槽未解锁,0为无奖励卡牌。
 * 奖励卡牌的索引值如下：
 * GameItem.Item46,GameItem.Item47,GameItem.Item48,	//加暴击,依次为1-3星
 * GameItem.Item49,GameItem.Item50,GameItem.Item51,	//怪物减血,依次为1-3星
 * GameItem.Item52,GameItem.Item53,GameItem.Item54,	//篱笆加血,依次为1-3星
 * GameItem.Item55,GameItem.Item56,GameItem.Item57,	//金币奖励,依次为1-3星
 * GameItem.Item58,GameItem.Item59,GameItem.Item60,	//加结算分,依次为1-3星
 * GameItem.Item61,GameItem.Item62,GameItem.Item63,	//combo奖励,依次为1-3星
 * 
 * 5.道具卡槽
 * 道具卡槽第一格：VeggiesData.getCardSlot()[5]
 * 道具卡槽第二格：VeggiesData.getCardSlot()[6]
 * 返回为道具卡牌的索引值。其中返回值-1为该卡槽未解锁,0为无道具卡牌。
 * 道具卡牌的索引值如下：
 * GameItem.Item31,GameItem.Item32,GameItem.Item33,	//冰弹,依次为1-3星
 * GameItem.Item34,GameItem.Item35,GameItem.Item36,	//飞艇,依次为1-3星
 * GameItem.Item37,GameItem.Item38,GameItem.Item39,	//爆破弹,依次为1-3星
 * GameItem.Item40,GameItem.Item41,GameItem.Item42,	//恢复剂,依次为1-3星
 * GameItem.Item43,GameItem.Item44,GameItem.Item45,	//燃烧药剂,依次为1-3星
 * 
 * PS:所有卡牌具体索引值可从GameItem类中查询
 * 
 * 6.增加或减少对应卡牌数量
 * VeggiesData.setAllCardNum(int cardId, int num)
 * 第一参数为卡牌的Id,第二参数为增加的数量（例VeggiesData.setAllCardNum(GameItem.Item01, -6)为番茄卡牌数量减6）
 * PS:此处id适用所用的卡牌id.
 * 
 * 7.获取卡牌仓库
 * VeggiesData.getAllCardNum()
 * 返回整个卡牌仓库的数组。其中-1为未解锁,0为数量为0,1以上为次卡牌的数量
 * 
 * 8.获取某卡牌的数量
 * VeggiesData.getAllCardNum()[VeggiesData.getCardIndex(cardId)]
 * 值类型int, 为当前卡牌id的卡牌数量.
 * 
 * 
 * 9.设置当前关卡的任务情况
 * VeggiesData.setTask_Mission(int current_Level, boolean[] taskIsSuccess)
 * 第一参数为当前关卡索引,第二参数为当前关卡的任务完成情况
 * 
 * 10. 获取金币总量
 * VeggiesData.getGold()
 * 返回值为金币总量(int)
 * 
 * 11. 增加金币
 * VeggiesData.addGold(int _gold)
 * 参数为增加的金币量，负数为减掉金币量
 * 
 * 12. 获取宝石
 * VeggiesData.getGem()
 * 返回值为宝石总量(int)
 * 
 * 13. 增加宝石
 * VeggiesData.addGem(int _gem)
 * 参数为增加的宝石量，负数为减掉宝石量
 * 
 * 14. 获取篱笆血量
 * VeggiesData.getFence_HP()
 * 返回值为当前篱笆等级的篱笆血量.
 * PS:若需要篱笆等级：VeggiesData.getFence_HP_level(),返回值为0~3；
 * 
 * 15. 获取燃烧伤害
 * VeggiesData.getCombus_damage()
 * 返回值为当前燃烧伤害等级的燃烧伤害
 * PS:若需要燃烧伤害等级：VeggiesData.getCombus_damage_level(),返回值为0~3.
 * 
 * 16. 获取燃烧时间
 * VeggiesData.getCombus_time()
 * 返回值为当前燃烧时间等级的燃烧时间
 * PS:若需要燃烧时间等级：VeggiesData.getCombus_time_levl(),返回值0~3.
 * 
 * 17. 获取弹弓暴击
 * VeggiesData.getSlingshot_crit()
 * 返回值为当前弹弓暴击等级的弹弓暴击
 * PS:若需要弹弓暴击等级：VeggiesData.getSlingshot_crit_levl(),返回值0~3
 * 
 * 18. 获取任务完成情况
 * getTask_Mission()
 * 返回的是整个任务的二位数组
 * PS:获取当前任务getTask_Mission()[currentLevel] 返回当前关卡的任务情况
 * 
 * 20. 增加成就完成度
 * addAchievementNum(int achievementId, int num)
 * 无返回值。
 * achievementId为成就的ID，ID见类Achievement.java
 * num为该成就的完成数的增加量。
 * PS:若为一次性任务，如：登录facebook， 则num为1
 * 
 * 21. 设置成就状态
 * setAchievement(int achievementId, int state)
 * 无返回值。
 * achievementId为成就的ID，ID见类Achievement.java
 * state为成就状态，默认为-1(成就未完成),0(已完成已领取奖励),1(已完成未领取奖励)
 * 
 * 22. 获取成就
 * getAchievement
 * 返回为成就数组。
 * 例：getAchievement()[0]为第一个成就的状态,状态见21.中成就状态描述
 * 
 * 23. 获取成就目前进度
 * getAchievementSchedule
 * 返回为目前成就进度数组
 * 例：getAchievementSchedule()[0]为第一个成就目前完成多少了.
 * 
 * 24. 获取成就目标进度
 * getAchievementDes
 * 返回为目前成就目标进度数组
 * 例：getAchievementDes()[0]为第一个成就目标多少.
 **/
package com.endlessvegetables2.ui;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import com.facebook.FacebookOperation;
import com.facebook.UserRequest;
import com.game.data.FarmData1;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameRecord;
import com.socoGameEngine.GameSave;
import com.socogame.coolEdit.CoolEditDefine;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class VeggiesData implements GameSave{
	public static final boolean isTestMode = false;
	public static final boolean isTestCardOpen = false;
	
	// 关卡开关
	private static int GameGuanka[] = { 
			0, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//			0, 0, 0, 0, 0, 0, 0, 0, 0,-1,

//			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,

	};
	
	private static int currentLevel = 0;
	private static int yiKaiQiMaxLevel = 0;
	
	public static final int BOOS_LEVEL1 = 31;
	public static final int BOOS_LEVEL2 = 151;
	private static boolean boosLevel[] = {false, false};
	private static boolean SHOW_SUCCESS = false; //成就界面是否显示new_icon
	public static boolean isStory[] = new boolean[3];  // 
	/**
	 * 新的卡片获得以后 卡库显示new的小图标
	 * */
	private static Vector<Integer> cardnewIcon = new Vector<Integer>();
	
	private static int[] cardSlot = 
	{
		GameItem.Item01,//主卡槽
		-1,-1,-1,//副卡槽
		-1,//奖励卡槽
		-1,-1//道具卡槽
	};
	
	private static int[] allCardNum = 
	{//有所有卡片种类合并一起计算
		 1, -1, -1,		//番茄
		 1, -1, -1,		//萝卜
		-1, -1, -1,		//洋葱
		-1, -1, -1,		//土豆
		-1, -1, -1,		//豌豆
		-1, -1, -1,		//蘑菇
		-1, -1, -1,		//辣椒
		-1, -1, -1,		//花菜
		-1, -1, -1,		//玉米
		-1, -1, -1,		//南瓜
		-1, -1, -1,		//冰弹
		-1, -1, -1,		//飞艇
		-1, -1, -1,		//黑色炸弹
		-1, -1, -1,		//恢复剂
		-1, -1, -1,		//燃烧药剂
		-1, -1, -1,		//-15%~45%暴击
		-1, -1, -1,		//怪物减血
		-1, -1, -1,		//城墙加血
		-1, -1, -1,		//金币奖励
		-1, -1, -1,		//结算分增加
		-1, -1, -1,		//combo奖励
	};
	
	private static int[][] task_Mission = 
	{
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
		{-1, -1, -1},
	};
	
	private static int[] levelScore = 
	{//关卡最高分数
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
	};
	
//	private static int gold = 0;
//	private static int gem = 0;
//	private static int heart = 5;
	
	private static int gold = 99990;
	private static int gem = 99999990;
	private static int heart = 5;
	
	private static int fence_HP = 100;//篱笆血量
	private static int combus_damage = 50;//燃烧伤害
	private static float combus_time = 3;//燃烧时间
	private static int slingshot_crit = 0;//弹弓暴击
	
	private static int fence_HP_level = 0;//篱笆等级
	private static int combus_damage_level = 0;//燃烧伤害等级
	private static int combus_time_level = 0;//燃烧时间等级
	private static int slingshot_crit_level = 0;//弹弓暴击等级
	
	private static boolean muteMusic = false;
	private static boolean muteSound = false;
	
	public static long systemtime;
	
	public static long heart_time;
	
	public static long  ztNyr = 0 ;//昨天的long
	public static int endT = 0; //昨天月数的最大天数
	public static int everyDay = 0; //领取了几天
	
	/**
	 * 返回boss关卡是否解锁
	 * */
	public static boolean[] getBossLevel(){
		return boosLevel;
	}
	
	/**
	 * 设置boss解锁
	 * */
	public static void setBossLevel(int index, boolean isPass){
		boosLevel[index] = isPass;
		if(index == 0) 
			VeggiesData.getGameGuanka()[BOOS_LEVEL1-1] = 0;
		else if(index == 1)
			VeggiesData.getGameGuanka()[BOOS_LEVEL2 - 1] = 0;
	}
	//十种蔬菜
	private static int[] tenKindsOfVegetables = 
	{
		-1,-1,-1,-1,-1,
		-1,-1,-1,-1,-1,
	};
	private static int[] achievement = 
	{
		-1,-1,-1,-1,-1,
		-1,-1,-1,-1,-1,
	};
	private static int[] achievementSchedule = 
	{
		0,0,0,0,0,
		0,0,0,0,0,
	};
	private static final int[] achievementDes = 
	{
		1,1,GameStaticImage.shop_item_2.length-1,1,100,
		100,1,60,100,10,
	};
	/**
	 *设置当前关卡 索引
	 */
	public static void setCurrentLevel(int level) {
		currentLevel = level;
	}
	/**
	 *设置当前关卡 索引
	 */
	public static void setYiKaiQiMaxLevel(int level) {
		yiKaiQiMaxLevel = level;
	}
	/**
	 *获得当前关卡 索引
	 */
	public static int getCurrentLevel() {
		return currentLevel;
	}
	/**
	 *获得当前关卡 索引
	 */
	public static int getYiKaiQiMaxLevel() {
		return yiKaiQiMaxLevel;
	}
	/**
	 * 设置关卡
	 * @param cLevel 关卡索引
	 * @param star	-1未解锁,0解锁，1-3为通关星数
	 */
	public static void setGameGuanka(int cLevel, int star) {
		GameGuanka[cLevel] = star;
	}
	
	public static int[] getGameGuanka() {
		return GameGuanka;
	}
	
	/**
	 * 设置卡槽
	 */
	public static void setCardSlot(int index, int cardId) {
		cardSlot[index] = cardId;
	}
	
	/**
	 * 卡槽顺序：<br/>
	 * 0主卡槽	1副卡槽	2副卡槽	3副卡槽<br/>
	 * 4奖励卡槽	5道具卡槽	6道具卡槽
	 */
	public static int[] getCardSlot() {
		return cardSlot;
	}
	/**
	 * 增加卡牌仓库的卡牌
	 * @param cardId 卡牌id
	 * @param num	使用掉的数量,负为使用掉的数量，正为增加的数量
	 */
	public static void setAllCardNum(int cardId, int num) {
		if (allCardNum[getCardIndex(cardId)] == -1){
			allCardNum[getCardIndex(cardId)] = 0;
			if(!cardnewIcon.contains(getCardIndex(cardId)))
				cardnewIcon.add(new Integer(getCardIndex(cardId)));
		}
		allCardNum[getCardIndex(cardId)] += num;
		if (allCardNum[getCardIndex(cardId)] > 999) allCardNum[getCardIndex(cardId)] = 999;
	}
	 
	 
	/**
	 * 返回卡牌仓库
	 * @return
	 */
	public static int[] getAllCardNum() {
		return allCardNum;
	}
	
	public static int getCardNum(int cardId) {
		return allCardNum[getCardIndex(cardId)];
	}
	
	/**
	 * 设置当前关卡的任务是否完成
	 * @param current_Level	当前关卡索引
	 * @param taskIsSuccess	当前关卡的任务完成情况
	 */
	public static void setTask_Mission(int current_Level, int[] taskIsSuccess) {
		for (int i=0; i<taskIsSuccess.length; i++) {
			if(task_Mission[current_Level][i]<1){
				task_Mission[current_Level][i] = taskIsSuccess[i];
			}
		}
	}
	
	public static int[][] getTask_Mission() {
		return task_Mission;
	}
	
	/**
	 * 获得金币总数 
	 */
	public static int getGold() {
		return gold;
	}
	/**
	 * 增加金币
	 */
	public static void addGold(int _gold) {
		gold += _gold;
		if(gold<=0)
			gold = 0;
		gold = gold>999999?999999:gold;
	}
	/**
	 * 获得宝石总数
	 */
	public static int getGem() {
		return gem;
	}
	/**
	 * 增加宝石
	 */
	public static void addGem(int _gem) {
		gem += _gem;
		gem = gem>999999?999999:gem;
	}
	/**
	 * 获得心数
	 */
	public static int getHeart() {
		return heart;
	}
	/**
	 * 增加心数 
	 */
	public static void addHeart(int _heart) {
		heart += _heart;
		heart = heart>Configs.HEART_MAX?Configs.HEART_MAX:heart;
	}
	/**
	 * 设置篱笆等级
	 */
	public static void setFence_HP_level(int _fence_HP_level) {
		fence_HP_level = _fence_HP_level;
	}
	/**
	 * 返回篱笆等级
	 */
	public static int getFence_HP_level() {
		return fence_HP_level;
	}
	/**
	 *获取当前篱笆等级的篱笆血量 
	 */
	public static int getFence_HP() {
		int tempHP = 0;
		switch(fence_HP_level) { 
		case 0:
			tempHP = fence_HP;
			break;
		case 1:
			tempHP = fence_HP+20;
			break;
		case 2:
			tempHP = fence_HP+30;
			break;
		case 3:
			tempHP = fence_HP+50;
			break;
		}
		return tempHP;
	}
	
	/**
	 * 设置燃烧伤害等级
	 */
	public static void setCombus_damage_level(int _combus_damage_level) {
		combus_damage_level = _combus_damage_level;
	}
	/**
	 * 获取燃烧伤害等级
	 */
	public static int getCombus_damage_level(){
		return combus_damage_level;
	}
	/**
	 * 获取当前燃烧等级的燃烧伤害 
	 */
	public static int getCombus_damage() {
		int temp_damage = 0;
//		switch(combus_damage_level){
//		case 0:
//			temp_damage = combus_damage;
//			break;
//		case 1:
//			temp_damage = combus_damage + 50;
//			break;
//		case 2:
//			temp_damage = combus_damage + 100;			
//			break;
//		case 3:			
//			temp_damage = combus_damage + 150;			
//			break;
//		}
		switch(cardSlot[0]) {
		case GameItem.Item01:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_FQ) * (1 + 0.2 + combus_damage_level * 0.3));
			break;
		case GameItem.Item02:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_FQ_2) * (1 + 0.2 + combus_damage_level * 0.3));
			break;
		case GameItem.Item03:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_FQ_3) * (1 + 0.2 + combus_damage_level * 0.3));
			break;
		case GameItem.Item13:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_WD) * (1 + 0.2 + combus_damage_level * 0.3));			
			break;
		case GameItem.Item14:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_WD_2) * (1 + 0.2 + combus_damage_level * 0.3));			
			break;
		case GameItem.Item15:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_WD_3) * (1 + 0.2 + combus_damage_level * 0.3));						
			break;
		case GameItem.Item19:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_LJ) * (1 + 0.2 + combus_damage_level * 0.3));			
			break;
		case GameItem.Item20:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_LJ_2) * (1 + 0.2 + combus_damage_level * 0.3));						
			break;
		case GameItem.Item21:
			temp_damage = (int) (SpriteLibrary.GetAttack(CoolEditDefine.Player_LJ_3) * (1 + 0.2 + combus_damage_level * 0.3));			
			break;
		}
		if(Configs.isDebug) {
			System.out.println(">>>>>>>>> Combus_damage = " + temp_damage);
		}
		return temp_damage;
	}
	
	/**
	 * 设置燃烧伤害等级
	 */
	public static void setCombus_time_level(int _combus_time_level) {
		combus_time_level = _combus_time_level;
	}
	/**
	 * 获取燃烧伤害等级
	 */
	public static int getCombus_time_level() {
		return combus_time_level;
	}
	/**
	 * 获取当前燃烧时间等级的燃烧时间
	 */
	public static float getCombus_time() {
		float temp_time = 0;
		switch(combus_time_level) {
		case 0:
			temp_time = combus_time;
			break;
		case 1:
			temp_time = combus_time + 0.5f;
			break;
		case 2:
			temp_time = combus_time + 1;
			break;
		case 3:
			temp_time = combus_time + 2;
			break;
		}
		return temp_time;
	}
	
	/**
	 * 设置弹弓暴击等级 
	 */
	public static void setSlingshot_crit_level(int _slingshot_crit_level) {
		slingshot_crit_level = _slingshot_crit_level;
	}
	/**
	 * 获取弹弓暴击等级
	 */
	public static int getSlingshot_crit_level() {
		return slingshot_crit_level;
	}
	/**
	 * 获取当前弹弓暴击等级的弹弓暴击
	 */
	public static int getSlingshot_crit() {
		int temp_crit = 0;
		switch(slingshot_crit_level) {
		case 0:
			temp_crit = slingshot_crit;
			break;
		case 1:
			temp_crit = slingshot_crit + 5;
			break;
		case 2:
			temp_crit = slingshot_crit + 8;
			break;
		case 3:
			temp_crit = slingshot_crit + 10;
			break;
		}
		return temp_crit;
	}
	/**
	 * 是否静音乐
	 */
	public static boolean isMuteMusic() {
		return muteMusic;
	}
	/**
	 * 设置静音乐
	 */
	public static void setMuteMusic(boolean muteMusic) {
		VeggiesData.muteMusic = muteMusic;
	}
	/**
	 * 是否静音效
	 */
	public static boolean isMuteSound() {
		return muteSound;
	}
	/**
	 * 设置静音效
	 */
	public static void setMuteSound(boolean muteSound) {
		VeggiesData.muteSound = muteSound;
	}
	/*
	 * 用于修正通过卡牌Id在卡牌库中对应卡牌的数量
	 * */
	public static int getCardIndex(int cardId) {
		return cardId-1;
	}
	
	public static void setCurrentLevelScore(int _levelScore) {
		levelScore[getCurrentLevel()] = _levelScore;
	}
	
	public static int[] getLevelScore() {
		return levelScore;
	}
	/**
	 * 增加成就记录值
	 * @param achievementId	成就ID
	 * @param num 普通一次性任务数量为1，其余按照正常增加数量
	 */
	public static void addAchievementNum(int achievementId, int num) {
		if (achievement[achievementId] < 0) {
			achievementSchedule[achievementId] += num;
			if (achievementSchedule[achievementId] == achievementDes[achievementId]) {
				achievement[achievementId] = 1;
				VeggiesData.setShow_success(true); //显示new icon
			}
		}
	}
	
	//是否三星通过六十关卡
	public static void isThreeStarPass(){
		int allstar = 0;
		 if(achievement[Achievement.GET_ALL_THREE_STAR_LEVLES] < 0){
			 achievementSchedule[Achievement.GET_ALL_THREE_STAR_LEVLES] = 0;
			for(int i=0; i<GameGuanka.length; i++) {
				  if(GameGuanka[i]>=1){
					 allstar+=GameGuanka[i];
				  }
				  if(GameGuanka[i] != 3){
					  break;
				  }else{
					  addAchievementNum(Achievement.GET_ALL_THREE_STAR_LEVLES, 1);
				  }
			}
	     }
		 if(achievement[Achievement.GET_ONEHUNDRED_STARS] < 0){
			  achievementSchedule[Achievement.GET_ONEHUNDRED_STARS] = 0;
			  addAchievementNum(Achievement.GET_ONEHUNDRED_STARS, allstar);
		 }
	}
	
	/**
	 * 判断收集的十种三星蔬菜是否重复
	 * */
	public static void isVegetablesRepeat(int id){
		if (achievement[Achievement.GET_TEN_THREESTAR_CARDS] < 0) {
			boolean isok = false;
			//3星的
			for(int i=0;i<63;++i){
				int a = 3+i*3;
				if(a>63)
					break;
				if( a == id){
					isok = true;
					break;
				}
			}
			if(isok){
				 if(achievement[Achievement.GET_THREE_STAR_CARD] < 0){
					 VeggiesData.addAchievementNum(Achievement.GET_THREE_STAR_CARD, 1);
				 }
				 for(int i=0;i<tenKindsOfVegetables.length;++i){
					 if(tenKindsOfVegetables[i]==id)
						 break;
					 if(tenKindsOfVegetables[i] == -1 ){
						 tenKindsOfVegetables[i] = id;
						 VeggiesData.addAchievementNum(Achievement.GET_TEN_THREESTAR_CARDS, 1);
						 break;
					 }
				 } 
			}
		} 
	}
	
	/**
	 * 设置成就
	 * @param achievementId 成就ID
	 * @param state -1为任务未达成</br>
	 * 				0为任务达成奖励已领取</br>
	 * 				1为任务达成奖励未领取
	 */
	public static void setAchievement(int achievementId, int state) {
		achievement[achievementId] = state;
	}
	/**
	 * 获取成就状态
	 * @return
	 */
	public static int[] getAchievement() {
		return achievement;
	}
	/**
	 * 获取成就完成进度
	 * @return
	 */
	public static int[] getAchievementSchedule(){
		return achievementSchedule;
	}
	/**
	 * 获取成就完成目标
	 * @return
	 */
	public static int[] getAchievementDes() {
		return achievementDes;
	}
	
	private void testData() {
		int current_Level = getCurrentLevel();	//获取当前关卡索引
		setCurrentLevel(10);				//设置当前关卡为第十关
		
		int mainVeggies = getCardSlot()[0];		//主蔬菜
		int minorVeggies_1 = getCardSlot()[1];	//副蔬菜1
		int minorVeggies_2 = getCardSlot()[2];	//副蔬菜2
		int minorVeggies_3 = getCardSlot()[3];	//副蔬菜3
		int reward_card	= getCardSlot()[4];		//奖励卡牌
		int item_card_1 = getCardSlot()[5];		//道具1
		int item_card_2 = getCardSlot()[6];		//道具2
		
		//获取1星冰弹数量
		int num = getAllCardNum()[getCardIndex(GameItem.Item31)];
		//冰弹数量-6
		setAllCardNum(getCardIndex(GameItem.Item31), -6);
	}
	
	public static void intiTestMode() {
		for(int i=0; i<GameGuanka.length; i++) {
			if(GameGuanka[i] == -1) GameGuanka[i] = 0;
		}
		gem = 999999;
		gold = 999999;
		for(int i=0; i<allCardNum.length; i++) {
			if(allCardNum[i] == -1) allCardNum[i] = 999;
		}
		for(int i=0; i<cardSlot.length; i++) {
			if(cardSlot[i] == -1) cardSlot[i] = 0;
		}
		
		yiKaiQiMaxLevel = GameGuanka.length-1;
		for(int i=0; i<GameTeaching.teachingArrary.length; i++) {
			 
			GameTeaching.teachingArrary[i] = true;
	}
	}
	public static void initCardOpen(){
		gem = 999999;
		gold = 999999;
		for(int i=0; i<allCardNum.length; i++) {
			if(allCardNum[i] == -1) allCardNum[i] = 999;
		}
	}
	
	/**
	 * 设置成就界面new  icon
	 * */
	public static void setShow_success(boolean isshow){
		SHOW_SUCCESS = isshow;
	}
	 
	/**
	 * 返回成就界面new  icon
	 * */
	public static boolean getShow_success(){
		return SHOW_SUCCESS ;
	}
	/**
	 * 返回卡牌是否新添加new  icon
	 * */
	public static Vector<Integer> getCardnewIcon(){
		return cardnewIcon ;
	}
	/**
	 * 清空id
	 * */
	public static void setDeleteCardNewId(int id){
		for(int i=0;i<cardnewIcon.size();++i){
			if(id == cardnewIcon.get(i).intValue()){
				cardnewIcon.remove(i);
//				break;
			}
		}
	}
	
	public void saveGame() {
		GameRecord.saveGame("Veggies201311", this);
	}
	
	public void loadGame() {
		GameRecord.loadGame("Veggies201311", this);
	}
	public void writefile(ObjectOutputStream oos) throws Exception {
		oos.writeBoolean(muteMusic);
		oos.writeBoolean(muteSound);
		
		oos.writeInt(yiKaiQiMaxLevel);
		
		oos.writeInt(gold);
		oos.writeInt(gem);
		oos.writeInt(heart);
		
		oos.writeInt(fence_HP);
		oos.writeInt(combus_damage);
		oos.writeFloat(combus_time);
		oos.writeInt(slingshot_crit);
		oos.writeInt(fence_HP_level);
		oos.writeInt(combus_damage_level);
		oos.writeInt(combus_time_level);
		oos.writeInt(slingshot_crit_level);
		
		oos.writeLong(systemtime);
		oos.writeLong(heart_time);
		
		oos.writeObject(GameGuanka);
		oos.writeObject(cardSlot);
		oos.writeObject(allCardNum);
		oos.writeObject(task_Mission);
		oos.writeObject(levelScore);
		
		oos.writeObject(achievement);
		oos.writeObject(achievementSchedule);
		
		oos.writeObject(GameTeaching.teachingArrary);
		oos.writeLong(ztNyr);
		oos.writeInt(endT);
		oos.writeInt(everyDay);
		
		oos.writeBoolean(UserRequest.getUser().getHideLevel());
		oos.writeObject(boosLevel);
		oos.writeObject(tenKindsOfVegetables);
		oos.writeBoolean(FacebookOperation.isLanding);
		oos.writeBoolean(getShow_success());
		int size = cardnewIcon.size();
		int newid[] = new int[size];
		for(int i=0;i<size;++i){
			newid[i] = cardnewIcon.get(i).intValue();
		}
		oos.writeObject(newid);
		oos.writeBoolean(FarmData1.isunLOCK);
		oos.writeObject(FarmData1.TYPE);
		oos.writeObject(FarmData1.STATE);
		oos.writeObject(FarmData1.DQ_TIME);
		oos.writeObject(FarmData1.SIZE_TIME);
		oos.writeObject(isStory);
		oos.writeBoolean(GameEquipmentModule.isFree);
		
	}
	public void loadfile(ObjectInputStream ois) throws Exception {
		muteMusic = ois.readBoolean();
		muteSound = ois.readBoolean();
		
		yiKaiQiMaxLevel = ois.readInt();
		
		gold = ois.readInt();
		gem = ois.readInt();
		heart = ois.readInt();
		fence_HP = ois.readInt();
		combus_damage = ois.readInt();
		combus_time = ois.readFloat();
		slingshot_crit = ois.readInt();
		fence_HP_level = ois.readInt();
		combus_damage_level = ois.readInt();
		combus_time_level = ois.readInt();
		slingshot_crit_level = ois.readInt();
		
		systemtime = ois.readLong();
		heart_time = ois.readLong();
		
		GameGuanka = (int[]) ois.readObject();
		cardSlot = (int[]) ois.readObject();
		allCardNum = (int[]) ois.readObject();
		task_Mission = (int[][]) ois.readObject();
		levelScore = (int[]) ois.readObject();
		
		achievement = (int[]) ois.readObject();
		achievementSchedule = (int[]) ois.readObject();

		GameTeaching.teachingArrary = (boolean[]) ois.readObject();
		
		ztNyr = ois.readLong();
		endT = ois.readInt();
		everyDay = ois.readInt();
		
		UserRequest.getUser().setHideLevel(ois.readBoolean(), false);
		boosLevel = (boolean[]) ois.readObject();
		tenKindsOfVegetables = (int[]) ois.readObject();
		FacebookOperation.isLanding = ois.readBoolean();
		setShow_success(ois.readBoolean());
		int newid[] = (int[]) ois.readObject();
		for(int i=0;i<newid.length;++i){
			cardnewIcon.add(newid[i]);
		}
		FarmData1.isunLOCK = ois.readBoolean();
		FarmData1.TYPE = (byte[]) ois.readObject();
		FarmData1.STATE = (byte[]) ois.readObject();
		FarmData1.DQ_TIME = (long[]) ois.readObject();
		FarmData1.SIZE_TIME = (long[]) ois.readObject();
		isStory = (boolean[]) ois.readObject();
		GameEquipmentModule.isFree = ois.readBoolean();
		
		
		
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL31] = false;
		for(int i=0; i<GameTeaching.teachingArrary.length; i++) {
			GameTeaching.teachingArrary[i] = true;
		}
//		for(int i=0; i<GameGuanka.length; i++) {
//			  GameGuanka[i] = -1;
//		}
//		for(int i=0; i<GameGuanka.length; i++) {
//			  GameGuanka[i] = 3;
//		}
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL16] = false;
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL16] = false;
//		 for(int i=0;i<FarmData1.STATE.length;++i){
//			 FarmData1.STATE[i] =  FarmData1.unplanted;
//		 }
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL49] = false;
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL50] = false;
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL51] = false;
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL52] = false;
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL53] = false;
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL54] = false;
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL55] = false;
//		GameTeaching.teachingArrary[GameTeaching.TEACH_VOL56] = false;
	}
}
