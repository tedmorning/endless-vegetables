package com.endlessvegetables2.ui;

public class GameItem {
	public static final int Item01 =  1;//1�Ƿ���
	public static final int Item02 =  2;//2�Ƿ���
	public static final int Item03 =  3;//3�Ƿ���
	public static final int Item04 =  4;//1���ܲ�
	public static final int Item05 =  5;//2���ܲ�
	public static final int Item06 =  6;//3���ܲ�
	public static final int Item07 =  7;//1�����
	public static final int Item08 =  8;//2�����
	public static final int Item09 =  9;//3�����
	public static final int Item10 = 10;//1������
	public static final int Item11 = 11;//2������
	public static final int Item12 = 12;//3������
	public static final int Item13 = 13;//1���㶹
	public static final int Item14 = 14;//2���㶹
	public static final int Item15 = 15;//3���㶹
	public static final int Item16 = 16;//1��Ģ��
	public static final int Item17 = 17;//2��Ģ��
	public static final int Item18 = 18;//3��Ģ��
	public static final int Item19 = 19;//1������
	public static final int Item20 = 20;//2������
	public static final int Item21 = 21;//3������
	public static final int Item22 = 22;//1�ǻ���
	public static final int Item23 = 23;//2�ǻ���
	public static final int Item24 = 24;//3�ǻ���
	public static final int Item25 = 25;//1������
	public static final int Item26 = 26;//2������
	public static final int Item27 = 27;//3������
	public static final int Item28 = 28;//1���Ϲ�
	public static final int Item29 = 29;//2���Ϲ�
	public static final int Item30 = 30;//3���Ϲ�
	public static final int Item31 = 31;//1�Ǳ���
	public static final int Item32 = 32;//2�Ǳ���
	public static final int Item33 = 33;//3�Ǳ���
	public static final int Item34 = 34;//1�Ƿ�ͧ
	public static final int Item35 = 35;//2�Ƿ�ͧ
	public static final int Item36 = 36;//3�Ƿ�ͧ
	public static final int Item37 = 37;//1�Ǳ��Ƶ�
	public static final int Item38 = 38;//2�Ǳ��Ƶ�
	public static final int Item39 = 39;//3�Ǳ��Ƶ�
	public static final int Item40 = 40;//1�ǻָ���
	public static final int Item41 = 41;//2�ǻָ���
	public static final int Item42 = 42;//3�ǻָ���
	public static final int Item43 = 43;//1��ȼ��ҩ��
	public static final int Item44 = 44;//2��ȼ��ҩ��
	public static final int Item45 = 45;//3��ȼ��ҩ��
	public static final int Item46 = 46;//1��15%����
	public static final int Item47 = 47;//2��30%����
	public static final int Item48 = 48;//3��45%����
	public static final int Item49 = 49;//1�ǹ���HP-10%
	public static final int Item50 = 50;//2�ǹ���HP-20%
	public static final int Item51 = 51;//3�ǹ���HP-30%
	public static final int Item52 = 52;//1�ǳ�ǽHP+25%
	public static final int Item53 = 53;//2�ǳ�ǽHP+50%
	public static final int Item54 = 54;//3�ǳ�ǽHP+75%
	public static final int Item55 = 55;//1�ǽ�ҽ���+5%
	public static final int Item56 = 56;//2�ǽ�ҽ���+10%
	public static final int Item57 = 57;//3�ǽ�ҽ���+20%
	public static final int Item58 = 58;//1�ǽ����+5%
	public static final int Item59 = 59;//2�ǽ����+20%
	public static final int Item60 = 60;//3�ǽ����+50%
	public static final int Item61 = 61;//1��combo+1���
	public static final int Item62 = 62;//2��combo+2���
	public static final int Item63 = 63;//3��combo+3���
	
	public static final int[] equipPrice = //װ�������ۼ۸�
	{
		-1, -1, -1, 100, -1, -1, 100 
	};
	
	public static final int[][] cardSort =
	{
		{//��װ���� ����
			Item01,Item02,Item03,	//����
			Item13,Item14,Item15,	//�㶹
			Item19,Item20,Item21,	//����
		},
		{//��װ��������
			Item04,Item05,Item06,	//�ܲ�
			Item07,Item08,Item09,	//���
			Item10,Item11,Item12,	//����
			Item16,Item17,Item18,	//Ģ��
			Item22,Item23,Item24,	//����
			Item25,Item26,Item27,	//����
			Item28,Item29,Item30,	//�Ϲ�
		},
		{//����װ��������
			Item46,Item47,Item48,	//�ӱ���
			Item49,Item50,Item51,	//�����Ѫ
			Item52,Item53,Item54,	//��ʼ�Ѫ
			Item55,Item56,Item57,	//��ҽ���
			Item58,Item59,Item60,	//�ӽ����
			Item61,Item62,Item63,	//combo����
		},
		{//����װ��������
			Item31,Item32,Item33,	//����
			Item34,Item35,Item36,	//��ͧ
			Item37,Item38,Item39,	//���Ƶ�
			Item40,Item41,Item42,	//�ָ���
			Item43,Item44,Item45,	//ȼ��ҩ��
		},
	};
	
	public static final int[][] cardLibrary = 
	{
		{
			Item01,Item02,Item03,	//����
			Item04,Item05,Item06,	//�ܲ�
			Item07,Item08,Item09,	//���
			Item10,Item11,Item12,	//����
			Item13,Item14,Item15,	//�㶹
			Item16,Item17,Item18,	//Ģ��
			Item19,Item20,Item21,	//����
			Item22,Item23,Item24,	//����
			Item25,Item26,Item27,	//����
			Item28,Item29,Item30,	//�Ϲ�
		},
		{
			Item31,Item32,Item33,	//����
			Item34,Item35,Item36,	//��ͧ
			Item37,Item38,Item39,	//���Ƶ�
			Item40,Item41,Item42,	//�ָ���
			Item43,Item44,Item45,	//ȼ��ҩ��
			Item46,Item47,Item48,	//�ӱ���
			Item49,Item50,Item51,	//�����Ѫ
			Item52,Item53,Item54,	//��ʼ�Ѫ
			Item55,Item56,Item57,	//��ҽ���
			Item58,Item59,Item60,	//�ӽ����
			Item61,Item62,Item63,	//combo����
		},
	};
}
