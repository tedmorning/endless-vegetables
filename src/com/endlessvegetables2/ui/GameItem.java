package com.endlessvegetables2.ui;

public class GameItem {
	public static final int Item01 =  1;//1ÐÇ·¬ÇÑ
	public static final int Item02 =  2;//2ÐÇ·¬ÇÑ
	public static final int Item03 =  3;//3ÐÇ·¬ÇÑ
	public static final int Item04 =  4;//1ÐÇÂÜ²·
	public static final int Item05 =  5;//2ÐÇÂÜ²·
	public static final int Item06 =  6;//3ÐÇÂÜ²·
	public static final int Item07 =  7;//1ÐÇÑó´Ð
	public static final int Item08 =  8;//2ÐÇÑó´Ð
	public static final int Item09 =  9;//3ÐÇÑó´Ð
	public static final int Item10 = 10;//1ÐÇÍÁ¶¹
	public static final int Item11 = 11;//2ÐÇÍÁ¶¹
	public static final int Item12 = 12;//3ÐÇÍÁ¶¹
	public static final int Item13 = 13;//1ÐÇÍã¶¹
	public static final int Item14 = 14;//2ÐÇÍã¶¹
	public static final int Item15 = 15;//3ÐÇÍã¶¹
	public static final int Item16 = 16;//1ÐÇÄ¢¹½
	public static final int Item17 = 17;//2ÐÇÄ¢¹½
	public static final int Item18 = 18;//3ÐÇÄ¢¹½
	public static final int Item19 = 19;//1ÐÇÀ±½·
	public static final int Item20 = 20;//2ÐÇÀ±½·
	public static final int Item21 = 21;//3ÐÇÀ±½·
	public static final int Item22 = 22;//1ÐÇ»¨²Ë
	public static final int Item23 = 23;//2ÐÇ»¨²Ë
	public static final int Item24 = 24;//3ÐÇ»¨²Ë
	public static final int Item25 = 25;//1ÐÇÖñËñ
	public static final int Item26 = 26;//2ÐÇÖñËñ
	public static final int Item27 = 27;//3ÐÇÖñËñ
	public static final int Item28 = 28;//1ÐÇÄÏ¹Ï
	public static final int Item29 = 29;//2ÐÇÄÏ¹Ï
	public static final int Item30 = 30;//3ÐÇÄÏ¹Ï
	public static final int Item31 = 31;//1ÐÇ±ùµ¯
	public static final int Item32 = 32;//2ÐÇ±ùµ¯
	public static final int Item33 = 33;//3ÐÇ±ùµ¯
	public static final int Item34 = 34;//1ÐÇ·ÉÍ§
	public static final int Item35 = 35;//2ÐÇ·ÉÍ§
	public static final int Item36 = 36;//3ÐÇ·ÉÍ§
	public static final int Item37 = 37;//1ÐÇ±¬ÆÆµ¯
	public static final int Item38 = 38;//2ÐÇ±¬ÆÆµ¯
	public static final int Item39 = 39;//3ÐÇ±¬ÆÆµ¯
	public static final int Item40 = 40;//1ÐÇ»Ö¸´¼Á
	public static final int Item41 = 41;//2ÐÇ»Ö¸´¼Á
	public static final int Item42 = 42;//3ÐÇ»Ö¸´¼Á
	public static final int Item43 = 43;//1ÐÇÈ¼ÉÕÒ©¼Á
	public static final int Item44 = 44;//2ÐÇÈ¼ÉÕÒ©¼Á
	public static final int Item45 = 45;//3ÐÇÈ¼ÉÕÒ©¼Á
	public static final int Item46 = 46;//1ÐÇ15%±©»÷
	public static final int Item47 = 47;//2ÐÇ30%±©»÷
	public static final int Item48 = 48;//3ÐÇ45%±©»÷
	public static final int Item49 = 49;//1ÐÇ¹ÖÎïHP-10%
	public static final int Item50 = 50;//2ÐÇ¹ÖÎïHP-20%
	public static final int Item51 = 51;//3ÐÇ¹ÖÎïHP-30%
	public static final int Item52 = 52;//1ÐÇ³ÇÇ½HP+25%
	public static final int Item53 = 53;//2ÐÇ³ÇÇ½HP+50%
	public static final int Item54 = 54;//3ÐÇ³ÇÇ½HP+75%
	public static final int Item55 = 55;//1ÐÇ½ð±Ò½±Àø+5%
	public static final int Item56 = 56;//2ÐÇ½ð±Ò½±Àø+10%
	public static final int Item57 = 57;//3ÐÇ½ð±Ò½±Àø+20%
	public static final int Item58 = 58;//1ÐÇ½áËã·Ö+5%
	public static final int Item59 = 59;//2ÐÇ½áËã·Ö+20%
	public static final int Item60 = 60;//3ÐÇ½áËã·Ö+50%
	public static final int Item61 = 61;//1ÐÇcombo+1½ð±Ò
	public static final int Item62 = 62;//2ÐÇcombo+2½ð±Ò
	public static final int Item63 = 63;//3ÐÇcombo+3½ð±Ò
	
	public static final int[] equipPrice = //×°±¸À¸¿¨²Û¼Û¸ñ
	{
		-1, -1, -1, 100, -1, -1, 100 
	};
	
	public static final int[][] cardSort =
	{
		{//Ö÷×°±¸À¸ Ë÷Òý
			Item01,Item02,Item03,	//·¬ÇÑ
			Item13,Item14,Item15,	//Íã¶¹
			Item19,Item20,Item21,	//À±½·
		},
		{//¸±×°±¸À¸Ë÷Òý
			Item04,Item05,Item06,	//ÂÜ²·
			Item07,Item08,Item09,	//Ñó´Ð
			Item10,Item11,Item12,	//ÍÁ¶¹
			Item16,Item17,Item18,	//Ä¢¹½
			Item22,Item23,Item24,	//»¨²Ë
			Item25,Item26,Item27,	//ÖñËñ
			Item28,Item29,Item30,	//ÄÏ¹Ï
		},
		{//½±Àø×°±¸À¸Ë÷Òý
			Item46,Item47,Item48,	//¼Ó±©»÷
			Item49,Item50,Item51,	//¹ÖÎï¼õÑª
			Item52,Item53,Item54,	//Àé°Ê¼ÓÑª
			Item55,Item56,Item57,	//½ð±Ò½±Àø
			Item58,Item59,Item60,	//¼Ó½áËã·Ö
			Item61,Item62,Item63,	//combo½±Àø
		},
		{//µÀ¾ß×°±¸À¸Ë÷Òý
			Item31,Item32,Item33,	//±ùµ¯
			Item34,Item35,Item36,	//·ÉÍ§
			Item37,Item38,Item39,	//±¬ÆÆµ¯
			Item40,Item41,Item42,	//»Ö¸´¼Á
			Item43,Item44,Item45,	//È¼ÉÕÒ©¼Á
		},
	};
	
	public static final int[][] cardLibrary = 
	{
		{
			Item01,Item02,Item03,	//·¬ÇÑ
			Item04,Item05,Item06,	//ÂÜ²·
			Item07,Item08,Item09,	//Ñó´Ð
			Item10,Item11,Item12,	//ÍÁ¶¹
			Item13,Item14,Item15,	//Íã¶¹
			Item16,Item17,Item18,	//Ä¢¹½
			Item19,Item20,Item21,	//À±½·
			Item22,Item23,Item24,	//»¨²Ë
			Item25,Item26,Item27,	//ÖñËñ
			Item28,Item29,Item30,	//ÄÏ¹Ï
		},
		{
			Item31,Item32,Item33,	//±ùµ¯
			Item34,Item35,Item36,	//·ÉÍ§
			Item37,Item38,Item39,	//±¬ÆÆµ¯
			Item40,Item41,Item42,	//»Ö¸´¼Á
			Item43,Item44,Item45,	//È¼ÉÕÒ©¼Á
			Item46,Item47,Item48,	//¼Ó±©»÷
			Item49,Item50,Item51,	//¹ÖÎï¼õÑª
			Item52,Item53,Item54,	//Àé°Ê¼ÓÑª
			Item55,Item56,Item57,	//½ð±Ò½±Àø
			Item58,Item59,Item60,	//¼Ó½áËã·Ö
			Item61,Item62,Item63,	//combo½±Àø
		},
	};
}
