package com.endlessvegetables2.turngame;

import com.endlessvegetables2.ui.GameItem;

public class TurnGamePowerCard {

//	分数强化	一星	5%分数奖励
//	分数强化	二星	20%分数奖励
//	分数强化	三星	50%分数奖励
//	金币强化	一星	5%金币奖励
//	金币强化	二星	10%金币奖励
//	金币强化	三星	20%金币奖励
//	金手指	一星	COMBO时攻击掉落1金币
//	金手指	二星	COMBO时攻击掉落2金币
//	金手指	三星	COMBO时攻击掉落3金币
//	暴击卡	一星	15%几率触发暴击
//	暴击卡	二星	30%几率触发暴击
//	暴击卡	三星	45%几率触发暴击
//	难度卡	一星	怪物血量降低10%
//	难度卡	二星	怪物血量降低20%
//	难度卡	三星	怪物血量降低30%
//	稳固卡	一星	篱笆血量增加25%
//	稳固卡	二星	篱笆血量增加50%
//	稳固卡	三星	篱笆血量增加75%
	
//	 * GameItem.Item46,GameItem.Item47,GameItem.Item48,	//加暴击,依次为1-3星
//	 * GameItem.Item49,GameItem.Item50,GameItem.Item51,	//怪物减血,依次为1-3星
//	 * GameItem.Item52,GameItem.Item53,GameItem.Item54,	//篱笆加血,依次为1-3星
//	 * GameItem.Item55,GameItem.Item56,GameItem.Item57,	//金币奖励,依次为1-3星
//	 * GameItem.Item58,GameItem.Item59,GameItem.Item60,	//加结算分,依次为1-3星
//	 * GameItem.Item61,GameItem.Item62,GameItem.Item63,	//combo奖励,依次为1-3星
	
	public static int critRate;//加暴击率
	public static float monsterBloodLower;//怪物血量降低百分比
	public static float latticeBlood;//篱笆加血
	public static float addGameGolden;//金币奖励
	public static float addGameNumber;//加结算分
	public static int addGameComboGolden;//combo奖励
	
	public static void setPowerCard(int cardType)
	{
		critRate = 0;//加暴击率
		monsterBloodLower = 1f;//怪物血量降低百分比
		latticeBlood = 1f;//篱笆加血
		addGameGolden = 1f;//金币奖励
		addGameNumber = 1f;//加结算分
		addGameComboGolden = 0;//combo奖励
		
		switch(cardType)
		{
			case GameItem.Item46://加暴击率1星15%
				critRate = 15;
				break;
			
			case GameItem.Item47://加暴击率2星30%
				critRate = 30;
				break;
				
			case GameItem.Item48://加暴击率3星45%
				critRate = 45;
				break;
				
			case GameItem.Item49://难度卡1星	怪物血量降低10%
				monsterBloodLower = 0.9f;
				break;
			
			case GameItem.Item50://难度卡2星	怪物血量降低20%
				monsterBloodLower = 0.8f;
				break;
				
			case GameItem.Item51://难度卡3星	怪物血量降低30%
				monsterBloodLower = 0.7f;
				break;	
				
			case GameItem.Item52://稳固卡1星	篱笆血量增加25%
				latticeBlood = 1.25f;
				break;
			
			case GameItem.Item53://稳固卡2星	篱笆血量增加50%
				latticeBlood = 1.5f;
				break;
				
			case GameItem.Item54://稳固卡3星	篱笆血量增加75%
				latticeBlood = 1.75f;
				break;	
				
			case GameItem.Item55://金币强化1星5%金币奖励
				addGameGolden = 1.05f;
				break;
			
			case GameItem.Item56://金币强化2星10%金币奖励
				addGameGolden = 1.1f;
				break;
				
			case GameItem.Item57://金币强化3星20%金币奖励
				addGameGolden = 1.2f;
				break;	
				
			case GameItem.Item58://分数强化1星5%分数奖励
				addGameNumber = 1.05f;
				break;
			
			case GameItem.Item59://分数强化2星20%分数奖励
				addGameNumber = 1.2f;
				break;
				
			case GameItem.Item60://分数强化3星50%分数奖励
				addGameNumber = 1.5f;
				break;
				
			case GameItem.Item61://金手指1星燃烧时攻击掉落1金币
				addGameComboGolden = 1;
				break;
			
			case GameItem.Item62://金手指2星燃烧时攻击掉落2金币
				addGameComboGolden = 2;
				break;
				
			case GameItem.Item63://金手指3星燃烧时攻击掉落3金币
				addGameComboGolden = 3;
				break;								
		}
	}	 
}
