package com.endlessvegetables2.ui;

import android.graphics.RectF;

public class Configs {
	public static String filePath = "language/en";
	public static boolean isDebug = false;
	/**
	 * ¿Õ×´Ì¬
	 */
	public static final int NONE = 0;
//	/**
//	 * °´ÏÂ×´Ì¬
//	 */
//	public static final int DOWN = 1;
//	/**
//	 * Ì§Æð×´Ì¬
//	 */
//	public static final int UP = 2;
	/**
	 * 1µã´¥¿Ø×´Ì¬
	 */
	public static final int ONETOUCH = 1;
	/**
	 * 2µã´¥¿Ø×´Ì¬
	 */
	public static final int TWOTOUCH = 2;
	
	public static final int GoldMaxLength = 5;
	
	public static final int GameMapORGWidth = 1149;
	public static final int GameMapORGHeight = 2493;//+2047
	public static float GameMapWidth;
	public static float GameMapHeight;
	
	public static final float min_r = 0.7f;
	public static final float max_r = 1.8f;
	
	public static final float r_zoom_length = 400;
	public static float r_zoom_unit = (max_r - min_r) / 200;
	
	public static boolean isGameToMenu = false;
	
	public static boolean isFirst = true;
	
	public static RectF ScreenRectF;
	
	public static final int HEART_MAX = 999;
	public static final int HEART_CDMAX = 5;
}
