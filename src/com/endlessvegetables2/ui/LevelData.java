package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.List;

import com.kokatlaruruxi.wy.GameMission;


public class LevelData {
//	public static int score;//�ؿ�����
//	public static long time;//�ؿ�ʣ��ʱ��
//	private static int[] data;
//	private static boolean[] task;
//	private static int[] task_info;
//	public static int[] getData() {
//		return data;
//	}
//	public static void setData(int[] _data) {
//		data = _data;
//	}
//	public static boolean[] getTask() {
//		return task;
//	}
//	public static void setTask(boolean[] _task) {
//		task = _task;
//	}
//	public static void setTaskInfo(int[] _task_info){
//		task_info = _task_info;
//	}
//	public static int[] getTaskInfo() {
//		return task_info;
//	}
	private static List<Integer> lData;
	/**
	 * 0:	score 	����<br/>
	 * 1:	time	�ؿ�ʣ��ʱ��<br/>
	 * 2-4:	task	��ǰ������ɶ�(0:ʧ��,1:�ɹ�,-1:������)(0,1,0)<br/>
	 * 5	gold	�ؿ��л�õĽ��<br/>
	 * 6	gem		�ؿ��л�õı�ʯ<br/>
	 * 7	card	�ؿ��л�õĿ�Ƭ��<br/>
	 * 8	sgold	ʣ���ܽ����<br/>
	 * 9	sgem	ʣ���ܱ�ʯ��<br/>
	 * 10   cHP		��ǰѪ��<br/>
	 * 11	mHP		���Ѫ��<br/>
	 * 12	hit_rate������<br/>
	 * 13	shoot	������<br/>
	 * 14	3 * 4	������Ϣ�б�(��������,����/�߲�,�����,Ŀ����)<br/>
	 * 26	cardlen ɾ����Ƭ�ĳ��ȣ�card_len��<br/>
	 * card_len*3 	 ɾ����Ƭ��Ŀ(cardId, +, -)<br/>
	 */
	public static void setData(List<Integer> _lData) {
		lData = _lData;
	}
	/**
	 * 0:	score 	����<br/>
	 * 1:	time	�ؿ�ʣ��ʱ��<br/>
	 * 2-4:	task	��ǰ������ɶ�(0:ʧ��,1:�ɹ�,-1:������)(0,1,0)<br/>
	 * 5	gold	�ؿ��л�õĽ��<br/>
	 * 6	gem		�ؿ��л�õı�ʯ<br/>
	 * 7	card	�ؿ��л�õĿ�Ƭ��<br/>
	 * 8	sgold	ʣ���ܽ����<br/>
	 * 9	sgem	ʣ���ܱ�ʯ��<br/>
	 * 10   cHP		��ǰѪ��<br/>
	 * 11	mHP		���Ѫ��<br/>
	 * 12	hit		������<br/>
	 * 13	shoot	������<br/>
	 * 14	3 * 4	������Ϣ�б�(��������,����/�߲�,�����,Ŀ����)<br/>
	 * 26	cardlen ɾ����Ƭ�ĳ��ȣ�card_len��<br/>
	 * card_len*3 	 ɾ����Ƭ��Ŀ(cardId, +, -)<br/>
	 * */
	public static List<Integer> getData() {
		return lData;
	}
	
	public static void testData() {
//		lData = new ArrayList<Integer>();
//		lData.add(222222);
//		lData.add(180000);
//		lData.add(1);
//		lData.add(-1);
//		lData.add(0);
//		lData.add(1000);
//		lData.add(2000);
//		lData.add(2);
//		lData.add(20000);
//		lData.add(30000);
//		lData.add(200);
//		lData.add(200);
//		lData.add(10);
//		lData.add(15);
//		lData.add((int) GameMission.MISSION_12);
//		lData.add(-1);
//		lData.add(8);
//		lData.add(10);
//		lData.add((int) GameMission.MISSION_13);
//		lData.add(-1);
//		lData.add(3000);
//		lData.add(5000);
//		lData.add((int) GameMission.MISSION_15);
//		lData.add(-1);
//		lData.add(3);
//		lData.add(5);
	}
}
