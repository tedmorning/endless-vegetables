package com.socoGameEngine;
public class GameConfig {
	public static int i_coke = 0;
	public static char Char_num0[]	 = {'0','1','2','3','4','5','6','7','8','9','$','.'};
	public static char Char_num1[]	 = {'0','1','2','3','4','5','6','7','8','9'};
	public static char Char_num2[]	 = {'0','1','2','3','4','5','6','7','8','9',':'};
	public static char Char_num3[]	 = {'0','1','2','3','4','5','6','7','8','9','x',':','-','.'};
	public static char Char_num4[]	 = {'0','1','2','3','4','5','6','7','8','9',':','x'};
	public static char Char_num5[]	 = {'0','1','2','3','4','5','6','7','8','9',','};
	public static char Char_num6[]	 = {'0','1','2','3','4','5','6','7','8','9','%'};
	public static char Char_num7[]	 = {'0','1','2','3','4','5','6','7','8','9','x'};
	private static int Sleep_time = 1000/20;
	public static int ORGScreen_Width = 533;
	public static int ORGScreen_Height = 854;
	public static int GameScreen_Width;
	public static int GameScreen_Height;
	public static float f_zoomx;
	public static float f_zoomy;
	public static float f_transx;
	public static float f_transy;
	public static boolean ShowFps = false;//��ʾfps
	public static boolean b_showMemory = true;//ͼƬ��ȡ��ʾ�ڴ����
	public static float f_zoom;//����
	public static boolean  b_PngRead = false;//��ȡͼƬ
	public static int getSleepTime(){
		return Sleep_time;
	}
	
	public static void setSleepTime(int time){
		Sleep_time = time;
	}
	public static void setORGScreen(int width,int height){
		ORGScreen_Width = width;
		ORGScreen_Height = height;
	}
	
	public static boolean  b_gameReset = false;//���¿�ʼ��Ϸ

	public static boolean  b_gamePause = false;//��ͣ��Ϸ
	
	public static boolean  isFacebook = false;//�Ƿ���facebooke״̬
}
