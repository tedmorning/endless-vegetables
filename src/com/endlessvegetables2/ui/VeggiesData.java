/* һ.��Ϸ����Ҫʹ�õ�����
 * 1.��ǰ����Ϸ�ؿ�	
 * VeggiesData.getCurrentLevel()
 * ����ֵΪ��ǰ�ؿ�������ֵ���ؿ�ֵΪ����ֵ+1.
 * ����VeggiesData.getCurrentLevel()+1,��VeggiesData.getCurrentLevel()=0����VeggiesData.getCurrentLevel()+1���ǵ�һ��
 * 
 * 2.���߲�
 * VeggiesData.getCardSlot()[0]
 * ����ֵΪ���߲˵�����ֵ��
 * ���߲˵�����ֵ���£�
 * GameItem.Item01,GameItem.Item02,GameItem.Item03,	//����,����Ϊ1-3��
 * GameItem.Item13,GameItem.Item14,GameItem.Item15,	//�㶹,����Ϊ1-3��
 * GameItem.Item19,GameItem.Item20,GameItem.Item21,	//����,����Ϊ1-3��
 * 
 * 3.���߲�
 * ���߲˵�һ��VeggiesData.getCardSlot()[1]
 * ���߲˵ڶ���VeggiesData.getCardSlot()[2]
 * ���߲˵�����VeggiesData.getCardSlot()[3]
 * ����ֵΪ���߲˵�����ֵ�����з���ֵ-1Ϊ�ÿ���δ����,0Ϊ���߲ˡ�
 * ���߲˵�����ֵ���£�
 * GameItem.Item04,GameItem.Item05,GameItem.Item06,	//�ܲ�,����Ϊ1-3��
 * GameItem.Item07,GameItem.Item08,GameItem.Item09,	//���,����Ϊ1-3��
 * GameItem.Item10,GameItem.Item11,GameItem.Item12,	//����,����Ϊ1-3��
 * GameItem.Item16,GameItem.Item17,GameItem.Item18,	//Ģ��,����Ϊ1-3��
 * GameItem.Item22,GameItem.Item23,GameItem.Item24,	//����,����Ϊ1-3��
 * GameItem.Item25,GameItem.Item26,GameItem.Item27,	//����,����Ϊ1-3��
 * GameItem.Item28,GameItem.Item29,GameItem.Item30,	//�Ϲ�,����Ϊ1-3��
 * 
 * 4.��������
 * VeggiesData.getCardSlot()[4]
 * ����ֵΪ�������Ƶ�����ֵ�����з���ֵ-1Ϊ�ÿ���δ����,0Ϊ�޽������ơ�
 * �������Ƶ�����ֵ���£�
 * GameItem.Item46,GameItem.Item47,GameItem.Item48,	//�ӱ���,����Ϊ1-3��
 * GameItem.Item49,GameItem.Item50,GameItem.Item51,	//�����Ѫ,����Ϊ1-3��
 * GameItem.Item52,GameItem.Item53,GameItem.Item54,	//��ʼ�Ѫ,����Ϊ1-3��
 * GameItem.Item55,GameItem.Item56,GameItem.Item57,	//��ҽ���,����Ϊ1-3��
 * GameItem.Item58,GameItem.Item59,GameItem.Item60,	//�ӽ����,����Ϊ1-3��
 * GameItem.Item61,GameItem.Item62,GameItem.Item63,	//combo����,����Ϊ1-3��
 * 
 * 5.���߿���
 * ���߿��۵�һ��VeggiesData.getCardSlot()[5]
 * ���߿��۵ڶ���VeggiesData.getCardSlot()[6]
 * ����Ϊ���߿��Ƶ�����ֵ�����з���ֵ-1Ϊ�ÿ���δ����,0Ϊ�޵��߿��ơ�
 * ���߿��Ƶ�����ֵ���£�
 * GameItem.Item31,GameItem.Item32,GameItem.Item33,	//����,����Ϊ1-3��
 * GameItem.Item34,GameItem.Item35,GameItem.Item36,	//��ͧ,����Ϊ1-3��
 * GameItem.Item37,GameItem.Item38,GameItem.Item39,	//���Ƶ�,����Ϊ1-3��
 * GameItem.Item40,GameItem.Item41,GameItem.Item42,	//�ָ���,����Ϊ1-3��
 * GameItem.Item43,GameItem.Item44,GameItem.Item45,	//ȼ��ҩ��,����Ϊ1-3��
 * 
 * PS:���п��ƾ�������ֵ�ɴ�GameItem���в�ѯ
 * 
 * 6.���ӻ���ٶ�Ӧ��������
 * VeggiesData.setAllCardNum(int cardId, int num)
 * ��һ����Ϊ���Ƶ�Id,�ڶ�����Ϊ���ӵ���������VeggiesData.setAllCardNum(GameItem.Item01, -6)Ϊ���ѿ���������6��
 * PS:�˴�id�������õĿ���id.
 * 
 * 7.��ȡ���Ʋֿ�
 * VeggiesData.getAllCardNum()
 * �����������Ʋֿ�����顣����-1Ϊδ����,0Ϊ����Ϊ0,1����Ϊ�ο��Ƶ�����
 * 
 * 8.��ȡĳ���Ƶ�����
 * VeggiesData.getAllCardNum()[VeggiesData.getCardIndex(cardId)]
 * ֵ����int, Ϊ��ǰ����id�Ŀ�������.
 * 
 * 
 * 9.���õ�ǰ�ؿ����������
 * VeggiesData.setTask_Mission(int current_Level, boolean[] taskIsSuccess)
 * ��һ����Ϊ��ǰ�ؿ�����,�ڶ�����Ϊ��ǰ�ؿ�������������
 * 
 * 10. ��ȡ�������
 * VeggiesData.getGold()
 * ����ֵΪ�������(int)
 * 
 * 11. ���ӽ��
 * VeggiesData.addGold(int _gold)
 * ����Ϊ���ӵĽ����������Ϊ���������
 * 
 * 12. ��ȡ��ʯ
 * VeggiesData.getGem()
 * ����ֵΪ��ʯ����(int)
 * 
 * 13. ���ӱ�ʯ
 * VeggiesData.addGem(int _gem)
 * ����Ϊ���ӵı�ʯ��������Ϊ������ʯ��
 * 
 * 14. ��ȡ���Ѫ��
 * VeggiesData.getFence_HP()
 * ����ֵΪ��ǰ��ʵȼ������Ѫ��.
 * PS:����Ҫ��ʵȼ���VeggiesData.getFence_HP_level(),����ֵΪ0~3��
 * 
 * 15. ��ȡȼ���˺�
 * VeggiesData.getCombus_damage()
 * ����ֵΪ��ǰȼ���˺��ȼ���ȼ���˺�
 * PS:����Ҫȼ���˺��ȼ���VeggiesData.getCombus_damage_level(),����ֵΪ0~3.
 * 
 * 16. ��ȡȼ��ʱ��
 * VeggiesData.getCombus_time()
 * ����ֵΪ��ǰȼ��ʱ��ȼ���ȼ��ʱ��
 * PS:����Ҫȼ��ʱ��ȼ���VeggiesData.getCombus_time_levl(),����ֵ0~3.
 * 
 * 17. ��ȡ��������
 * VeggiesData.getSlingshot_crit()
 * ����ֵΪ��ǰ���������ȼ��ĵ�������
 * PS:����Ҫ���������ȼ���VeggiesData.getSlingshot_crit_levl(),����ֵ0~3
 * 
 * 18. ��ȡ����������
 * getTask_Mission()
 * ���ص�����������Ķ�λ����
 * PS:��ȡ��ǰ����getTask_Mission()[currentLevel] ���ص�ǰ�ؿ����������
 * 
 * 20. ���ӳɾ���ɶ�
 * addAchievementNum(int achievementId, int num)
 * �޷���ֵ��
 * achievementIdΪ�ɾ͵�ID��ID����Achievement.java
 * numΪ�óɾ͵����������������
 * PS:��Ϊһ���������磺��¼facebook�� ��numΪ1
 * 
 * 21. ���óɾ�״̬
 * setAchievement(int achievementId, int state)
 * �޷���ֵ��
 * achievementIdΪ�ɾ͵�ID��ID����Achievement.java
 * stateΪ�ɾ�״̬��Ĭ��Ϊ-1(�ɾ�δ���),0(���������ȡ����),1(�����δ��ȡ����)
 * 
 * 22. ��ȡ�ɾ�
 * getAchievement
 * ����Ϊ�ɾ����顣
 * ����getAchievement()[0]Ϊ��һ���ɾ͵�״̬,״̬��21.�гɾ�״̬����
 * 
 * 23. ��ȡ�ɾ�Ŀǰ����
 * getAchievementSchedule
 * ����ΪĿǰ�ɾͽ�������
 * ����getAchievementSchedule()[0]Ϊ��һ���ɾ�Ŀǰ��ɶ�����.
 * 
 * 24. ��ȡ�ɾ�Ŀ�����
 * getAchievementDes
 * ����ΪĿǰ�ɾ�Ŀ���������
 * ����getAchievementDes()[0]Ϊ��һ���ɾ�Ŀ�����.
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
	
	// �ؿ�����
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
	private static boolean SHOW_SUCCESS = false; //�ɾͽ����Ƿ���ʾnew_icon
	public static boolean isStory[] = new boolean[3];  // 
	/**
	 * �µĿ�Ƭ����Ժ� ������ʾnew��Сͼ��
	 * */
	private static Vector<Integer> cardnewIcon = new Vector<Integer>();
	
	private static int[] cardSlot = 
	{
		GameItem.Item01,//������
		-1,-1,-1,//������
		-1,//��������
		-1,-1//���߿���
	};
	
	private static int[] allCardNum = 
	{//�����п�Ƭ����ϲ�һ�����
		 1, -1, -1,		//����
		 1, -1, -1,		//�ܲ�
		-1, -1, -1,		//���
		-1, -1, -1,		//����
		-1, -1, -1,		//�㶹
		-1, -1, -1,		//Ģ��
		-1, -1, -1,		//����
		-1, -1, -1,		//����
		-1, -1, -1,		//����
		-1, -1, -1,		//�Ϲ�
		-1, -1, -1,		//����
		-1, -1, -1,		//��ͧ
		-1, -1, -1,		//��ɫը��
		-1, -1, -1,		//�ָ���
		-1, -1, -1,		//ȼ��ҩ��
		-1, -1, -1,		//-15%~45%����
		-1, -1, -1,		//�����Ѫ
		-1, -1, -1,		//��ǽ��Ѫ
		-1, -1, -1,		//��ҽ���
		-1, -1, -1,		//���������
		-1, -1, -1,		//combo����
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
	{//�ؿ���߷���
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
	
	private static int fence_HP = 100;//���Ѫ��
	private static int combus_damage = 50;//ȼ���˺�
	private static float combus_time = 3;//ȼ��ʱ��
	private static int slingshot_crit = 0;//��������
	
	private static int fence_HP_level = 0;//��ʵȼ�
	private static int combus_damage_level = 0;//ȼ���˺��ȼ�
	private static int combus_time_level = 0;//ȼ��ʱ��ȼ�
	private static int slingshot_crit_level = 0;//���������ȼ�
	
	private static boolean muteMusic = false;
	private static boolean muteSound = false;
	
	public static long systemtime;
	
	public static long heart_time;
	
	public static long  ztNyr = 0 ;//�����long
	public static int endT = 0; //�����������������
	public static int everyDay = 0; //��ȡ�˼���
	
	/**
	 * ����boss�ؿ��Ƿ����
	 * */
	public static boolean[] getBossLevel(){
		return boosLevel;
	}
	
	/**
	 * ����boss����
	 * */
	public static void setBossLevel(int index, boolean isPass){
		boosLevel[index] = isPass;
		if(index == 0) 
			VeggiesData.getGameGuanka()[BOOS_LEVEL1-1] = 0;
		else if(index == 1)
			VeggiesData.getGameGuanka()[BOOS_LEVEL2 - 1] = 0;
	}
	//ʮ���߲�
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
	 *���õ�ǰ�ؿ� ����
	 */
	public static void setCurrentLevel(int level) {
		currentLevel = level;
	}
	/**
	 *���õ�ǰ�ؿ� ����
	 */
	public static void setYiKaiQiMaxLevel(int level) {
		yiKaiQiMaxLevel = level;
	}
	/**
	 *��õ�ǰ�ؿ� ����
	 */
	public static int getCurrentLevel() {
		return currentLevel;
	}
	/**
	 *��õ�ǰ�ؿ� ����
	 */
	public static int getYiKaiQiMaxLevel() {
		return yiKaiQiMaxLevel;
	}
	/**
	 * ���ùؿ�
	 * @param cLevel �ؿ�����
	 * @param star	-1δ����,0������1-3Ϊͨ������
	 */
	public static void setGameGuanka(int cLevel, int star) {
		GameGuanka[cLevel] = star;
	}
	
	public static int[] getGameGuanka() {
		return GameGuanka;
	}
	
	/**
	 * ���ÿ���
	 */
	public static void setCardSlot(int index, int cardId) {
		cardSlot[index] = cardId;
	}
	
	/**
	 * ����˳��<br/>
	 * 0������	1������	2������	3������<br/>
	 * 4��������	5���߿���	6���߿���
	 */
	public static int[] getCardSlot() {
		return cardSlot;
	}
	/**
	 * ���ӿ��Ʋֿ�Ŀ���
	 * @param cardId ����id
	 * @param num	ʹ�õ�������,��Ϊʹ�õ�����������Ϊ���ӵ�����
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
	 * ���ؿ��Ʋֿ�
	 * @return
	 */
	public static int[] getAllCardNum() {
		return allCardNum;
	}
	
	public static int getCardNum(int cardId) {
		return allCardNum[getCardIndex(cardId)];
	}
	
	/**
	 * ���õ�ǰ�ؿ��������Ƿ����
	 * @param current_Level	��ǰ�ؿ�����
	 * @param taskIsSuccess	��ǰ�ؿ�������������
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
	 * ��ý������ 
	 */
	public static int getGold() {
		return gold;
	}
	/**
	 * ���ӽ��
	 */
	public static void addGold(int _gold) {
		gold += _gold;
		if(gold<=0)
			gold = 0;
		gold = gold>999999?999999:gold;
	}
	/**
	 * ��ñ�ʯ����
	 */
	public static int getGem() {
		return gem;
	}
	/**
	 * ���ӱ�ʯ
	 */
	public static void addGem(int _gem) {
		gem += _gem;
		gem = gem>999999?999999:gem;
	}
	/**
	 * �������
	 */
	public static int getHeart() {
		return heart;
	}
	/**
	 * �������� 
	 */
	public static void addHeart(int _heart) {
		heart += _heart;
		heart = heart>Configs.HEART_MAX?Configs.HEART_MAX:heart;
	}
	/**
	 * ������ʵȼ�
	 */
	public static void setFence_HP_level(int _fence_HP_level) {
		fence_HP_level = _fence_HP_level;
	}
	/**
	 * ������ʵȼ�
	 */
	public static int getFence_HP_level() {
		return fence_HP_level;
	}
	/**
	 *��ȡ��ǰ��ʵȼ������Ѫ�� 
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
	 * ����ȼ���˺��ȼ�
	 */
	public static void setCombus_damage_level(int _combus_damage_level) {
		combus_damage_level = _combus_damage_level;
	}
	/**
	 * ��ȡȼ���˺��ȼ�
	 */
	public static int getCombus_damage_level(){
		return combus_damage_level;
	}
	/**
	 * ��ȡ��ǰȼ�յȼ���ȼ���˺� 
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
	 * ����ȼ���˺��ȼ�
	 */
	public static void setCombus_time_level(int _combus_time_level) {
		combus_time_level = _combus_time_level;
	}
	/**
	 * ��ȡȼ���˺��ȼ�
	 */
	public static int getCombus_time_level() {
		return combus_time_level;
	}
	/**
	 * ��ȡ��ǰȼ��ʱ��ȼ���ȼ��ʱ��
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
	 * ���õ��������ȼ� 
	 */
	public static void setSlingshot_crit_level(int _slingshot_crit_level) {
		slingshot_crit_level = _slingshot_crit_level;
	}
	/**
	 * ��ȡ���������ȼ�
	 */
	public static int getSlingshot_crit_level() {
		return slingshot_crit_level;
	}
	/**
	 * ��ȡ��ǰ���������ȼ��ĵ�������
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
	 * �Ƿ�����
	 */
	public static boolean isMuteMusic() {
		return muteMusic;
	}
	/**
	 * ���þ�����
	 */
	public static void setMuteMusic(boolean muteMusic) {
		VeggiesData.muteMusic = muteMusic;
	}
	/**
	 * �Ƿ���Ч
	 */
	public static boolean isMuteSound() {
		return muteSound;
	}
	/**
	 * ���þ���Ч
	 */
	public static void setMuteSound(boolean muteSound) {
		VeggiesData.muteSound = muteSound;
	}
	/*
	 * ��������ͨ������Id�ڿ��ƿ��ж�Ӧ���Ƶ�����
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
	 * ���ӳɾͼ�¼ֵ
	 * @param achievementId	�ɾ�ID
	 * @param num ��ͨһ������������Ϊ1�����ఴ��������������
	 */
	public static void addAchievementNum(int achievementId, int num) {
		if (achievement[achievementId] < 0) {
			achievementSchedule[achievementId] += num;
			if (achievementSchedule[achievementId] == achievementDes[achievementId]) {
				achievement[achievementId] = 1;
				VeggiesData.setShow_success(true); //��ʾnew icon
			}
		}
	}
	
	//�Ƿ�����ͨ����ʮ�ؿ�
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
	 * �ж��ռ���ʮ�������߲��Ƿ��ظ�
	 * */
	public static void isVegetablesRepeat(int id){
		if (achievement[Achievement.GET_TEN_THREESTAR_CARDS] < 0) {
			boolean isok = false;
			//3�ǵ�
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
	 * ���óɾ�
	 * @param achievementId �ɾ�ID
	 * @param state -1Ϊ����δ���</br>
	 * 				0Ϊ�����ɽ�������ȡ</br>
	 * 				1Ϊ�����ɽ���δ��ȡ
	 */
	public static void setAchievement(int achievementId, int state) {
		achievement[achievementId] = state;
	}
	/**
	 * ��ȡ�ɾ�״̬
	 * @return
	 */
	public static int[] getAchievement() {
		return achievement;
	}
	/**
	 * ��ȡ�ɾ���ɽ���
	 * @return
	 */
	public static int[] getAchievementSchedule(){
		return achievementSchedule;
	}
	/**
	 * ��ȡ�ɾ����Ŀ��
	 * @return
	 */
	public static int[] getAchievementDes() {
		return achievementDes;
	}
	
	private void testData() {
		int current_Level = getCurrentLevel();	//��ȡ��ǰ�ؿ�����
		setCurrentLevel(10);				//���õ�ǰ�ؿ�Ϊ��ʮ��
		
		int mainVeggies = getCardSlot()[0];		//���߲�
		int minorVeggies_1 = getCardSlot()[1];	//���߲�1
		int minorVeggies_2 = getCardSlot()[2];	//���߲�2
		int minorVeggies_3 = getCardSlot()[3];	//���߲�3
		int reward_card	= getCardSlot()[4];		//��������
		int item_card_1 = getCardSlot()[5];		//����1
		int item_card_2 = getCardSlot()[6];		//����2
		
		//��ȡ1�Ǳ�������
		int num = getAllCardNum()[getCardIndex(GameItem.Item31)];
		//��������-6
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
	 * ���óɾͽ���new  icon
	 * */
	public static void setShow_success(boolean isshow){
		SHOW_SUCCESS = isshow;
	}
	 
	/**
	 * ���سɾͽ���new  icon
	 * */
	public static boolean getShow_success(){
		return SHOW_SUCCESS ;
	}
	/**
	 * ���ؿ����Ƿ������new  icon
	 * */
	public static Vector<Integer> getCardnewIcon(){
		return cardnewIcon ;
	}
	/**
	 * ���id
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
