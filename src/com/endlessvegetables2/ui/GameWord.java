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
		"Best��",
	};
	public static final String[] successLevel_Time = 
	{
		"Time��",
	};
	public static final String[][][] missionText = 
	{
//		{},
		{
			{""},
			/*1*/{"ͨ��ʱ�����������Ѫ","",""},
			/*2*/{"ʹ��%k1%��ɱ��ص����һ��","",""},
			/*3*/{"��Ļ�в���ͬʱ���ڳ���%k2%��%k3%","",""},//������ ������
			/*4*/{"���%k4%����","",""},//����
			/*5*/{"������ɱ%k5%��%k6%","",""},//����, ������
			/*6*/{"�ùᴩ����һ��ɱ��N��%k7%","",""},//������
			/*7*/{"��Ϸ��ʼ%k8%���ڲ�ʹ�����߲�","",""},//����
			/*8*/{"%k9%����ɱ��%k10%������","",""},//����,����
			/*9*/{"��ȼ�յ��߲�ɱ��%k11%��%k12%","",""},//����,������
			
			/*10*/{LangUtil.getLangString(LangDefineClient.MISSION_12A),  LangUtil.getLangString(LangDefineClient.MISSION_12B),	LangUtil.getLangString(LangDefineClient.MISSION_12C)},//�ٷֱ�
			
			/*11*/{"��ѣ����%k0%���Ĺ���","",""},//����
			
//			/*12*/{"%c0%������ǽ","%c0%���﹥����ǽ�Ĵ���С��%k16% ��",	"%c1%%k15%%c0%/%k16%"},//����
//			/*13*/{"%c0%��Ϸ�÷�","%c0%��Ϸ�÷ִﵽ%k18%��",				"%c1%%k17%%c0%/%k18%"},//����
//			/*14*/{"%c0%ʹ��ȼ��","%c0%��Ϸ��ʹ��%k20%��ȼ��",			"%c1%%k19%%c0%/%k20%"},//����
//			/*15*/{"%c0%���ƴ���","%c0%��Ϸ��ʼ��%k22%���ڲ�ʹ���߲˵���","%c1%%k21%%c0%/%k22%"},//����
//			/*16*/{"%c0%����Ч��","%c0%��Ϸ���ܹ�ʹ%k24%�����ﴦ���쳣״̬","%c1%%k23%%c0%/%k24%"},//����
//			/*17*/{"%c0%�߲˻��","%c0%ʹ�������߲˻������ﵽ%k26%��",	"%c1%%k25%%c0%/%k26%"},//����
//			/*18*/{"%c0%����ͨ��","%c0%��%k28%ʱ���������Ϸ ",			"%c1%%k27%%c0%/%k28%"},//ʱ��
//			/*19*/{"%c0%�Ͻ�����","%c0%��������Ĺ���С��%k30%��",		"%c1%%k29%%c0%/%k30%"},//����
//			/*20*/{"%c0%ʱ�մ���","%c0%ʹ%k32%�������߲˴�Խʱ����",		"%c1%%k31%%c0%/%k32%"},//����
//			/*21*/{"%c0%��ֹ����","%c0%����������%k34%������",			"%c1%%k33%%c0%/%k34%"},//����
//			/*22*/{"%c0%ȼ�ջ�ɱ","%c0%��ȼ�յ��߲˻���%c36%������ ",		"%c1%%k35%%c0%/%k36%"}, //����
//			/*23*/{"%c0%�ؿ�����","%c0%ͨ����ɹؿ� ",					""}, 
			/*12*/{LangUtil.getLangString(LangDefineClient.MISSION_1A), LangUtil.getLangString(LangDefineClient.MISSION_1B),	LangUtil.getLangString(LangDefineClient.MISSION_1C)},//����
			/*13*/{LangUtil.getLangString(LangDefineClient.MISSION_2A),  LangUtil.getLangString(LangDefineClient.MISSION_2B),	LangUtil.getLangString(LangDefineClient.MISSION_2B)},//����
			/*14*/{LangUtil.getLangString(LangDefineClient.MISSION_3A),  LangUtil.getLangString(LangDefineClient.MISSION_3B),	LangUtil.getLangString(LangDefineClient.MISSION_3C)},//����
			/*15*/{LangUtil.getLangString(LangDefineClient.MISSION_4A),  LangUtil.getLangString(LangDefineClient.MISSION_4B),	LangUtil.getLangString(LangDefineClient.MISSION_4C)},//����
			/*16*/{LangUtil.getLangString(LangDefineClient.MISSION_5A),  LangUtil.getLangString(LangDefineClient.MISSION_5B),	LangUtil.getLangString(LangDefineClient.MISSION_5C)},//����
			/*17*/{LangUtil.getLangString(LangDefineClient.MISSION_6A),  LangUtil.getLangString(LangDefineClient.MISSION_6B),	LangUtil.getLangString(LangDefineClient.MISSION_6C)},//����
			/*18*/{LangUtil.getLangString(LangDefineClient.MISSION_7A),  LangUtil.getLangString(LangDefineClient.MISSION_7B),	LangUtil.getLangString(LangDefineClient.MISSION_7C)},//ʱ��
			/*19*/{LangUtil.getLangString(LangDefineClient.MISSION_8A),  LangUtil.getLangString(LangDefineClient.MISSION_8B),	LangUtil.getLangString(LangDefineClient.MISSION_8C)},//����
			/*20*/{LangUtil.getLangString(LangDefineClient.MISSION_9A),  LangUtil.getLangString(LangDefineClient.MISSION_9B),	LangUtil.getLangString(LangDefineClient.MISSION_9C)},//����
			/*21*/{LangUtil.getLangString(LangDefineClient.MISSION_10A),  LangUtil.getLangString(LangDefineClient.MISSION_10B),	LangUtil.getLangString(LangDefineClient.MISSION_10C)},//����
			/*22*/{LangUtil.getLangString(LangDefineClient.MISSION_11A),  LangUtil.getLangString(LangDefineClient.MISSION_11B),	LangUtil.getLangString(LangDefineClient.MISSION_11C)}, //����
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
