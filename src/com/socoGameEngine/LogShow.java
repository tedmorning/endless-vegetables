package com.socoGameEngine;


public class LogShow {
	public  static String LOGTAG = "SocoGameJumpGame";
	public  static boolean SHOWLOG = true;
	
	public static void setLOGTAG(String msg) {
		LOGTAG = msg;
	}
	public static void d(String msg) {
		if(SHOWLOG)
			android.util.Log.d(LOGTAG, msg);
	}
}