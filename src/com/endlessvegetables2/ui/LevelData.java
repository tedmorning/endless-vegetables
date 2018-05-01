package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.List;

import com.kokatlaruruxi.wy.GameMission;


public class LevelData {
//	public static int score;//关卡分数
//	public static long time;//关卡剩余时间
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
	 * 0:	score 	分数<br/>
	 * 1:	time	关卡剩余时间<br/>
	 * 2-4:	task	当前任务完成度(0:失败,1:成功,-1:进行中)(0,1,0)<br/>
	 * 5	gold	关卡中获得的金币<br/>
	 * 6	gem		关卡中获得的宝石<br/>
	 * 7	card	关卡中获得的卡片数<br/>
	 * 8	sgold	剩余总金币数<br/>
	 * 9	sgem	剩余总宝石数<br/>
	 * 10   cHP		当前血量<br/>
	 * 11	mHP		最大血量<br/>
	 * 12	hit_rate击中数<br/>
	 * 13	shoot	发射数<br/>
	 * 14	3 * 4	任务信息列表(任务类型,怪物/蔬菜,完成数,目标数)<br/>
	 * 26	cardlen 删减卡片的长度（card_len）<br/>
	 * card_len*3 	 删减卡片条目(cardId, +, -)<br/>
	 */
	public static void setData(List<Integer> _lData) {
		lData = _lData;
	}
	/**
	 * 0:	score 	分数<br/>
	 * 1:	time	关卡剩余时间<br/>
	 * 2-4:	task	当前任务完成度(0:失败,1:成功,-1:进行中)(0,1,0)<br/>
	 * 5	gold	关卡中获得的金币<br/>
	 * 6	gem		关卡中获得的宝石<br/>
	 * 7	card	关卡中获得的卡片数<br/>
	 * 8	sgold	剩余总金币数<br/>
	 * 9	sgem	剩余总宝石数<br/>
	 * 10   cHP		当前血量<br/>
	 * 11	mHP		最大血量<br/>
	 * 12	hit		击中数<br/>
	 * 13	shoot	发射数<br/>
	 * 14	3 * 4	任务信息列表(任务类型,怪物/蔬菜,完成数,目标数)<br/>
	 * 26	cardlen 删减卡片的长度（card_len）<br/>
	 * card_len*3 	 删减卡片条目(cardId, +, -)<br/>
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
