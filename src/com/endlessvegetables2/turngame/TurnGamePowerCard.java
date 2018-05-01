package com.endlessvegetables2.turngame;

import com.endlessvegetables2.ui.GameItem;

public class TurnGamePowerCard {

//	����ǿ��	һ��	5%��������
//	����ǿ��	����	20%��������
//	����ǿ��	����	50%��������
//	���ǿ��	һ��	5%��ҽ���
//	���ǿ��	����	10%��ҽ���
//	���ǿ��	����	20%��ҽ���
//	����ָ	һ��	COMBOʱ��������1���
//	����ָ	����	COMBOʱ��������2���
//	����ָ	����	COMBOʱ��������3���
//	������	һ��	15%���ʴ�������
//	������	����	30%���ʴ�������
//	������	����	45%���ʴ�������
//	�Ѷȿ�	һ��	����Ѫ������10%
//	�Ѷȿ�	����	����Ѫ������20%
//	�Ѷȿ�	����	����Ѫ������30%
//	�ȹ̿�	һ��	���Ѫ������25%
//	�ȹ̿�	����	���Ѫ������50%
//	�ȹ̿�	����	���Ѫ������75%
	
//	 * GameItem.Item46,GameItem.Item47,GameItem.Item48,	//�ӱ���,����Ϊ1-3��
//	 * GameItem.Item49,GameItem.Item50,GameItem.Item51,	//�����Ѫ,����Ϊ1-3��
//	 * GameItem.Item52,GameItem.Item53,GameItem.Item54,	//��ʼ�Ѫ,����Ϊ1-3��
//	 * GameItem.Item55,GameItem.Item56,GameItem.Item57,	//��ҽ���,����Ϊ1-3��
//	 * GameItem.Item58,GameItem.Item59,GameItem.Item60,	//�ӽ����,����Ϊ1-3��
//	 * GameItem.Item61,GameItem.Item62,GameItem.Item63,	//combo����,����Ϊ1-3��
	
	public static int critRate;//�ӱ�����
	public static float monsterBloodLower;//����Ѫ�����Ͱٷֱ�
	public static float latticeBlood;//��ʼ�Ѫ
	public static float addGameGolden;//��ҽ���
	public static float addGameNumber;//�ӽ����
	public static int addGameComboGolden;//combo����
	
	public static void setPowerCard(int cardType)
	{
		critRate = 0;//�ӱ�����
		monsterBloodLower = 1f;//����Ѫ�����Ͱٷֱ�
		latticeBlood = 1f;//��ʼ�Ѫ
		addGameGolden = 1f;//��ҽ���
		addGameNumber = 1f;//�ӽ����
		addGameComboGolden = 0;//combo����
		
		switch(cardType)
		{
			case GameItem.Item46://�ӱ�����1��15%
				critRate = 15;
				break;
			
			case GameItem.Item47://�ӱ�����2��30%
				critRate = 30;
				break;
				
			case GameItem.Item48://�ӱ�����3��45%
				critRate = 45;
				break;
				
			case GameItem.Item49://�Ѷȿ�1��	����Ѫ������10%
				monsterBloodLower = 0.9f;
				break;
			
			case GameItem.Item50://�Ѷȿ�2��	����Ѫ������20%
				monsterBloodLower = 0.8f;
				break;
				
			case GameItem.Item51://�Ѷȿ�3��	����Ѫ������30%
				monsterBloodLower = 0.7f;
				break;	
				
			case GameItem.Item52://�ȹ̿�1��	���Ѫ������25%
				latticeBlood = 1.25f;
				break;
			
			case GameItem.Item53://�ȹ̿�2��	���Ѫ������50%
				latticeBlood = 1.5f;
				break;
				
			case GameItem.Item54://�ȹ̿�3��	���Ѫ������75%
				latticeBlood = 1.75f;
				break;	
				
			case GameItem.Item55://���ǿ��1��5%��ҽ���
				addGameGolden = 1.05f;
				break;
			
			case GameItem.Item56://���ǿ��2��10%��ҽ���
				addGameGolden = 1.1f;
				break;
				
			case GameItem.Item57://���ǿ��3��20%��ҽ���
				addGameGolden = 1.2f;
				break;	
				
			case GameItem.Item58://����ǿ��1��5%��������
				addGameNumber = 1.05f;
				break;
			
			case GameItem.Item59://����ǿ��2��20%��������
				addGameNumber = 1.2f;
				break;
				
			case GameItem.Item60://����ǿ��3��50%��������
				addGameNumber = 1.5f;
				break;
				
			case GameItem.Item61://����ָ1��ȼ��ʱ��������1���
				addGameComboGolden = 1;
				break;
			
			case GameItem.Item62://����ָ2��ȼ��ʱ��������2���
				addGameComboGolden = 2;
				break;
				
			case GameItem.Item63://����ָ3��ȼ��ʱ��������3���
				addGameComboGolden = 3;
				break;								
		}
	}	 
}
