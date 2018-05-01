package com.endlessvegetables2.ui;

import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class GameWord {
	public static final int useLanguage = 0;
	public static final String[][] GameEquip={
		{
			"Leader",
			"Partner",
			"Strengthen",
			"Items",
			"Ask"
		},
	};
	public static final String[][] shopItem_word = 
	{
		{
			LangUtil.getLangString(LangDefineClient.CARD_1),
			LangUtil.getLangString(LangDefineClient.CARD_2),
			LangUtil.getLangString(LangDefineClient.CARD_3),
			LangUtil.getLangString(LangDefineClient.CARD_4),
		},
	};
	public static final String[][] shopItem_upgrade = 
	{
		{
			LangUtil.getLangString(LangDefineClient.UPGRADE_SLINGSHOT),
			LangUtil.getLangString(LangDefineClient.UPGRADE_FENCE),
			LangUtil.getLangString(LangDefineClient.UPGRADE_BURNINGTIMES),
			LangUtil.getLangString(LangDefineClient.UPGRADE_BURNINGATK),
		},
	};
	public static final String[] pleaseLoginFaceBook = 
	{
		"Please Login FaceBook",
	};
	public static final String[] skip = 
	{
		"skip",
	};
	public static final String[] successLevel_Score =
	{
		"Best：",
	};
	public static final String[] successLevel_Time = 
	{
		"Time：",
	};
	public static final String[][][] missionText = 
	{
//		{},
		{
			{""},
			/*1*/{"通关时，保持篱笆满血","",""},
			/*2*/{"使用%k1%完成本关的最后一击","",""},
			/*3*/{"屏幕中不能同时存在超过%k2%个%k3%","",""},//数量， 怪物名
			/*4*/{"完成%k4%连击","",""},//数量
			/*5*/{"连续击杀%k5%个%k6%","",""},//数量, 怪物名
			/*6*/{"用贯穿攻击一次杀死N个%k7%","",""},//怪物名
			/*7*/{"游戏开始%k8%秒内不使用主蔬菜","",""},//数量
			/*8*/{"%k9%秒内杀死%k10%个怪物","",""},//数量,数量
			/*9*/{"用燃烧的蔬菜杀死%k11%个%k12%","",""},//数量,怪物名
			
			/*10*/{LangUtil.getLangString(LangDefineClient.MISSION_12A),  LangUtil.getLangString(LangDefineClient.MISSION_12B),	LangUtil.getLangString(LangDefineClient.MISSION_12C)},//百分比
			
			/*11*/{"晕眩场上%k0%个的怪物","",""},//数量
			
//			/*12*/{"%c0%守卫城墙","%c0%怪物攻击城墙的次数小于%k16% 次",	"%c1%%k15%%c0%/%k16%"},//数量
//			/*13*/{"%c0%游戏得分","%c0%游戏得分达到%k18%分",				"%c1%%k17%%c0%/%k18%"},//数量
//			/*14*/{"%c0%使用燃烧","%c0%游戏中使用%k20%次燃烧",			"%c1%%k19%%c0%/%k20%"},//数量
//			/*15*/{"%c0%蓄势待发","%c0%游戏开始后%k22%秒内不使用蔬菜弹射","%c1%%k21%%c0%/%k22%"},//数量
//			/*16*/{"%c0%特殊效果","%c0%游戏中总共使%k24%个怪物处于异常状态","%c1%%k23%%c0%/%k24%"},//数量
//			/*17*/{"%c0%蔬菜伙伴","%c0%使用任意蔬菜伙伴次数达到%k26%次",	"%c1%%k25%%c0%/%k26%"},//数量
//			/*18*/{"%c0%急速通关","%c0%在%k28%时间内完成游戏 ",			"%c1%%k27%%c0%/%k28%"},//时间
//			/*19*/{"%c0%严禁起跳","%c0%碰到跳板的怪物小于%k30%个",		"%c1%%k29%%c0%/%k30%"},//数量
//			/*20*/{"%c0%时空穿梭","%c0%使%k32%个以上蔬菜穿越时空门",		"%c1%%k31%%c0%/%k32%"},//数量
//			/*21*/{"%c0%阻止报警","%c0%警报器触发%k34%次以下",			"%c1%%k33%%c0%/%k34%"},//数量
//			/*22*/{"%c0%燃烧击杀","%c0%用燃烧的蔬菜击败%c36%个怪物 ",		"%c1%%k35%%c0%/%k36%"}, //数量
//			/*23*/{"%c0%关卡过关","%c0%通过完成关卡 ",					""}, 
			/*12*/{LangUtil.getLangString(LangDefineClient.MISSION_1A), LangUtil.getLangString(LangDefineClient.MISSION_1B),	LangUtil.getLangString(LangDefineClient.MISSION_1C)},//数量
			/*13*/{LangUtil.getLangString(LangDefineClient.MISSION_2A),  LangUtil.getLangString(LangDefineClient.MISSION_2B),	LangUtil.getLangString(LangDefineClient.MISSION_2B)},//数量
			/*14*/{LangUtil.getLangString(LangDefineClient.MISSION_3A),  LangUtil.getLangString(LangDefineClient.MISSION_3B),	LangUtil.getLangString(LangDefineClient.MISSION_3C)},//数量
			/*15*/{LangUtil.getLangString(LangDefineClient.MISSION_4A),  LangUtil.getLangString(LangDefineClient.MISSION_4B),	LangUtil.getLangString(LangDefineClient.MISSION_4C)},//数量
			/*16*/{LangUtil.getLangString(LangDefineClient.MISSION_5A),  LangUtil.getLangString(LangDefineClient.MISSION_5B),	LangUtil.getLangString(LangDefineClient.MISSION_5C)},//数量
			/*17*/{LangUtil.getLangString(LangDefineClient.MISSION_6A),  LangUtil.getLangString(LangDefineClient.MISSION_6B),	LangUtil.getLangString(LangDefineClient.MISSION_6C)},//数量
			/*18*/{LangUtil.getLangString(LangDefineClient.MISSION_7A),  LangUtil.getLangString(LangDefineClient.MISSION_7B),	LangUtil.getLangString(LangDefineClient.MISSION_7C)},//时间
			/*19*/{LangUtil.getLangString(LangDefineClient.MISSION_8A),  LangUtil.getLangString(LangDefineClient.MISSION_8B),	LangUtil.getLangString(LangDefineClient.MISSION_8C)},//数量
			/*20*/{LangUtil.getLangString(LangDefineClient.MISSION_9A),  LangUtil.getLangString(LangDefineClient.MISSION_9B),	LangUtil.getLangString(LangDefineClient.MISSION_9C)},//数量
			/*21*/{LangUtil.getLangString(LangDefineClient.MISSION_10A),  LangUtil.getLangString(LangDefineClient.MISSION_10B),	LangUtil.getLangString(LangDefineClient.MISSION_10C)},//数量
			/*22*/{LangUtil.getLangString(LangDefineClient.MISSION_11A),  LangUtil.getLangString(LangDefineClient.MISSION_11B),	LangUtil.getLangString(LangDefineClient.MISSION_11C)}, //数量
			/*23*/{LangUtil.getLangString(LangDefineClient.MISSION_13A),  LangUtil.getLangString(LangDefineClient.MISSION_13B),					""}, 
//			
		},
	};
	
	public static final String[][] missionState = 
	{
//		{},
		{":",	LangUtil.getLangString(LangDefineClient.MISSION_FAILED),},
	};
	public static final String[][] missionItem = 
	{
		{
			""
		},
	};
}
