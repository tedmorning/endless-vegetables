//package com.endlessvegetables2.ui;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;
//import android.graphics.Typeface;
//import android.os.Message;
//import android.util.DisplayMetrics;
//import android.util.FloatMath;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.widget.Toast;
//
//import com.endlessvegetables2.android.ExternalMethods;
//import com.endlessvegetables2.android.Main;
//import com.endlessvegetables2.android.Sprite;
//import com.socoGameEngine.GameConfig;
//import com.socoGameEngine.GameImage;
//import com.socoGameEngine.GameManager;
//import com.socoGameEngine.MainActivity;
//import com.socoGameEngine.Module;
//
//public class ChooseLevelModule extends Module{
////	private Bitmap bgbmp;
//	private Sprite coast1,coast2;
//	private Sprite boat;
//	private Sprite gold;
//	private Sprite gem;
//	private Sprite heart;
//	private Sprite statebg;
//	private Sprite rechange;
//	private Sprite storeWord;
//	private Sprite store;
//	private Sprite mail;
//	private Sprite mailred;
//	private Sprite farm;
//	private Sprite farmArrow;
//	private Sprite hill;
//	private Bitmap[] smoke;
//	private Sprite[] spot;
//	private Sprite[] tree;
//	private Sprite[] grass;
//	private Bitmap[] vortex1;
//	private Bitmap[] vortex2;
//	private Bitmap[] fish;
//	private Sprite[] stone;
//	private Sprite box;
//	private Bitmap[] octopus;
//	private Sprite[] cloud;
//	private Sprite[] select;
//	private Sprite[] star;
//	private Sprite[] enemy_level;
//	private Sprite[] island;
////	private Sprite[] bigmapelements;
//	private Bitmap[] maplevel_number;
//	private Bitmap[] mapmenu_number;
//	private Bitmap[] mapmenu_heart_number;
//	private Sprite[] photo;
//	private Bitmap[] currentLevel;
//	private float bgx,bgy;
//	private float coast1x,coast1y,coast2x,coast2y;
//	private int boatx,boaty;
////	private int island_2_x,island_2_y;
//	private int hill_x,hill_y;
//	private int smoke_x,smoke_y;
//	private int bg1_1x,bg1_1y,bg1_2x,bg1_2y;
//	private int bg2_1x,bg2_1y,bg2_2x,bg2_2y;
//	private int shade_1x,shade_1y,shade_2x,shade_2y;
//	private int box_x,box_y;
//	private int octopus_x,octopus_y;
//	private int hill_aphla = 255, hill_R = 0,hill_G = 0,hill_B = 0;//argb暂用一个值来代替
//	private int octopus_aphla = 255, octopus_R = 0,octopus_G = 0,octopus_B = 0;//argb暂用一个值来代替
////	private int grassland1_1x,grassland1_1y,grassland1_2x,grassland1_2y;
////	private int grassland2_1x,grassland2_1y,grassland2_2x,grassland2_2y;
//	private int[][] grassland_s = 
//	{
//			{Location.BigMap.grassland1_1_x,Location.BigMap.grassland1_1_y,Location.BigMap.grassland1_1_w,Location.BigMap.grassland1_1_h,0xffA8DF6A},
//			{Location.BigMap.grassland1_2_x,Location.BigMap.grassland1_2_y,Location.BigMap.grassland1_2_w,Location.BigMap.grassland1_2_h,0xffA8DF6A},
//			{Location.BigMap.grassland1_3_x,Location.BigMap.grassland1_3_y,Location.BigMap.grassland1_3_w,Location.BigMap.grassland1_3_h,0xffA8DF6A},
//			{Location.BigMap.grassland1_4_x,Location.BigMap.grassland1_4_y,Location.BigMap.grassland1_4_w,Location.BigMap.grassland1_4_h,0xffA8DF6A},
//			{Location.BigMap.grassland2_1_x,Location.BigMap.grassland2_1_y,Location.BigMap.grassland2_1_w,Location.BigMap.grassland2_1_h,0xff65CC78},
//			{Location.BigMap.grassland2_2_x,Location.BigMap.grassland2_2_y,Location.BigMap.grassland2_2_w,Location.BigMap.grassland2_2_h,0xff65CC78},
//			{Location.BigMap.grassland2_3_x,Location.BigMap.grassland2_3_y,Location.BigMap.grassland2_3_w,Location.BigMap.grassland2_3_h,0xff65CC78},
//			{Location.BigMap.grassland2_4_x,Location.BigMap.grassland2_4_y,Location.BigMap.grassland2_4_w,Location.BigMap.grassland2_4_h,0xff65CC78},
//			{Location.BigMap.grassland3_1_x,Location.BigMap.grassland3_1_y,Location.BigMap.grassland3_1_w,Location.BigMap.grassland3_1_h,0xff349B87},
//			{Location.BigMap.grassland3_2_x,Location.BigMap.grassland3_2_y,Location.BigMap.grassland3_2_w,Location.BigMap.grassland3_2_h,0xff349B87},
//			{Location.BigMap.grassland3_3_x,Location.BigMap.grassland3_3_y,Location.BigMap.grassland3_3_w,Location.BigMap.grassland3_3_h,0xff349B87},
//			{Location.BigMap.grassland3_4_x,Location.BigMap.grassland3_4_y,Location.BigMap.grassland3_4_w,Location.BigMap.grassland3_4_h,0xff349B87},
//			{Location.BigMap.grassland3_5_x,Location.BigMap.grassland3_5_y,Location.BigMap.grassland3_5_w,Location.BigMap.grassland3_5_h,0xff349B87},
//			{Location.BigMap.grassland3_6_x,Location.BigMap.grassland3_6_y,Location.BigMap.grassland3_6_w,Location.BigMap.grassland3_6_h,0xff349B87},
//			{Location.BigMap.grassland3_7_x,Location.BigMap.grassland3_7_y,Location.BigMap.grassland3_7_w,Location.BigMap.grassland3_7_h,0xff349B87},
//			{Location.BigMap.grassland4_1_x,Location.BigMap.grassland4_1_y,Location.BigMap.grassland4_1_w,Location.BigMap.grassland4_1_h,0xff3DB9A1},
//			{Location.BigMap.grassland4_2_x,Location.BigMap.grassland4_2_y,Location.BigMap.grassland4_2_w,Location.BigMap.grassland4_2_h,0xff3DB9A1},
//			{Location.BigMap.grassland4_3_x,Location.BigMap.grassland4_3_y,Location.BigMap.grassland4_3_w,Location.BigMap.grassland4_3_h,0xff3DB9A1},
//			{Location.BigMap.grassland4_4_x,Location.BigMap.grassland4_4_y,Location.BigMap.grassland4_4_w,Location.BigMap.grassland4_4_h,0xff3DB9A1},
//			{Location.BigMap.grassland4_5_x,Location.BigMap.grassland4_5_y,Location.BigMap.grassland4_5_w,Location.BigMap.grassland4_5_h,0xff3DB9A1},
//			{Location.BigMap.grassland4_6_x,Location.BigMap.grassland4_6_y,Location.BigMap.grassland4_6_w,Location.BigMap.grassland4_6_h,0xff3DB9A1},
//			{Location.BigMap.grassland4_7_x,Location.BigMap.grassland4_7_y,Location.BigMap.grassland4_7_w,Location.BigMap.grassland4_7_h,0xff3DB9A1},
//	};
//	private int[][] grassland_d;
//	
//	private int[][] spot_s = 
//	{
//			{Location.BigMap.spot4_1_x, Location.BigMap.spot4_1_y, 4},
//			{Location.BigMap.spot4_2_x, Location.BigMap.spot4_2_y, 4},
//			{Location.BigMap.spot4_3_x, Location.BigMap.spot4_3_y, 4},
//			{Location.BigMap.spot4_4_x, Location.BigMap.spot4_4_y, 4},
//			{Location.BigMap.spot4_5_x, Location.BigMap.spot4_5_y, 4},
//			{Location.BigMap.spot4_6_x, Location.BigMap.spot4_6_y, 4},
//			{Location.BigMap.spot2_1_x, Location.BigMap.spot2_1_y, 2},
//			{Location.BigMap.spot2_2_x, Location.BigMap.spot2_2_y, 2},
//			{Location.BigMap.spot2_3_x, Location.BigMap.spot2_3_y, 2},
//			{Location.BigMap.spot2_4_x, Location.BigMap.spot2_4_y, 2},
//			{Location.BigMap.spot2_5_x, Location.BigMap.spot2_5_y, 2},
//			{Location.BigMap.spot3_1_x, Location.BigMap.spot3_1_y, 3},
//			{Location.BigMap.spot3_2_x, Location.BigMap.spot3_2_y, 3},
//			{Location.BigMap.spot3_3_x, Location.BigMap.spot3_3_y, 3},
//			{Location.BigMap.spot3_4_x, Location.BigMap.spot3_4_y, 3},
//			{Location.BigMap.spot3_5_x, Location.BigMap.spot3_5_y, 3},
//			{Location.BigMap.spot3_6_x, Location.BigMap.spot3_6_y, 3},
//			{Location.BigMap.spot3_7_x, Location.BigMap.spot3_7_y, 3},
//			{Location.BigMap.spot3_8_x, Location.BigMap.spot3_8_y, 3},
//			{Location.BigMap.spot3_9_x, Location.BigMap.spot3_9_y, 3},
//			{Location.BigMap.spot3_10_x, Location.BigMap.spot3_10_y, 3},
//			{Location.BigMap.spot1_1_x, Location.BigMap.spot1_1_y, 1},
//			{Location.BigMap.spot1_2_x, Location.BigMap.spot1_2_y, 1},
//			{Location.BigMap.spot1_3_x, Location.BigMap.spot1_3_y, 1},
//			{Location.BigMap.spot1_4_x, Location.BigMap.spot1_4_y, 1},
//			{Location.BigMap.spot1_5_x, Location.BigMap.spot1_5_y, 1},
//			{Location.BigMap.spot1_6_x, Location.BigMap.spot1_6_y, 1},
//			{Location.BigMap.spot1_7_x, Location.BigMap.spot1_7_y, 1},
//			{Location.BigMap.spot1_8_x, Location.BigMap.spot1_8_y, 1},
//			{Location.BigMap.spot1_9_x, Location.BigMap.spot1_9_y, 1},
//			{Location.BigMap.spot1_10_x, Location.BigMap.spot1_10_y, 1},
//			{Location.BigMap.spot5_1_x, Location.BigMap.spot5_1_y, 5},
//			{Location.BigMap.spot5_2_x, Location.BigMap.spot5_2_y, 5},
//			{Location.BigMap.spot5_3_x, Location.BigMap.spot5_3_y, 5},
//			{Location.BigMap.spot5_4_x, Location.BigMap.spot5_4_y, 5},
//			{Location.BigMap.spot5_5_x, Location.BigMap.spot5_5_y, 5},
//			{Location.BigMap.spot5_6_x, Location.BigMap.spot5_6_y, 5},
//			{Location.BigMap.spot5_7_x, Location.BigMap.spot5_7_y, 5},
//	};
//	private int[][] spot_d; 
//	
//	private int[][] tree_s = 
//	{
//			{Location.BigMap.tree1_1_x, Location.BigMap.tree1_1_y, 1},
//			{Location.BigMap.tree1_2_x, Location.BigMap.tree1_2_y, 1},
//			{Location.BigMap.tree1_3_x, Location.BigMap.tree1_3_y, 1},
//			{Location.BigMap.tree2_1_x, Location.BigMap.tree2_1_y, 2},
//			{Location.BigMap.tree2_2_x, Location.BigMap.tree2_2_y, 2},
//			{Location.BigMap.tree2_3_x, Location.BigMap.tree2_3_y, 2},
//			{Location.BigMap.tree2_4_x, Location.BigMap.tree2_4_y, 2},
//			{Location.BigMap.tree2_5_x, Location.BigMap.tree2_5_y, 2},
//			{Location.BigMap.tree2_6_x, Location.BigMap.tree2_6_y, 2},
//			{Location.BigMap.tree4_1_x, Location.BigMap.tree4_1_y, 4},
//			{Location.BigMap.tree4_2_x, Location.BigMap.tree4_2_y, 4},
//			{Location.BigMap.tree4_3_x, Location.BigMap.tree4_3_y, 4},
//			{Location.BigMap.tree4_4_x, Location.BigMap.tree4_4_y, 4},
//			{Location.BigMap.tree4_5_x, Location.BigMap.tree4_5_y, 4},
//			{Location.BigMap.tree4_6_x, Location.BigMap.tree4_6_y, 4},
//			{Location.BigMap.tree4_7_x, Location.BigMap.tree4_7_y, 4},
//			{Location.BigMap.tree4_8_x, Location.BigMap.tree4_8_y, 4},
//			{Location.BigMap.tree4_9_x, Location.BigMap.tree4_9_y, 4},
//			{Location.BigMap.tree4_10_x, Location.BigMap.tree4_10_y, 4},
//			{Location.BigMap.tree4_11_x, Location.BigMap.tree4_11_y, 4},
//			{Location.BigMap.tree4_12_x, Location.BigMap.tree4_12_y, 4},
//			{Location.BigMap.tree4_13_x, Location.BigMap.tree4_13_y, 4},
//			{Location.BigMap.tree3_1_x, Location.BigMap.tree3_1_y, 3},
//			{Location.BigMap.tree3_2_x, Location.BigMap.tree3_2_y, 3},
//			{Location.BigMap.tree3_3_x, Location.BigMap.tree3_3_y, 3},
//	};
//	private int[][] tree_d;
//	
//	private int[][] grass_s = 
//	{
//			{Location.BigMap.grass1_1_x, Location.BigMap.grass1_1_y, 1},
//			{Location.BigMap.grass1_2_x, Location.BigMap.grass1_2_y, 1},
//			{Location.BigMap.grass1_3_x, Location.BigMap.grass1_3_y, 1},
//			{Location.BigMap.grass1_4_x, Location.BigMap.grass1_4_y, 1},
//			{Location.BigMap.grass2_1_x, Location.BigMap.grass2_1_y, 2},
//			{Location.BigMap.grass2_2_x, Location.BigMap.grass2_2_y, 2},
//			{Location.BigMap.grass3_1_x, Location.BigMap.grass3_1_y, 3},
//			{Location.BigMap.grass3_2_x, Location.BigMap.grass3_2_y, 3},
//			{Location.BigMap.grass3_3_x, Location.BigMap.grass3_3_y, 3},
//			{Location.BigMap.grass3_4_x, Location.BigMap.grass3_4_y, 3},
//			{Location.BigMap.grass3_5_x, Location.BigMap.grass3_5_y, 3},
//	};
//	private int[][] grass_d;
//	private int[][] fish_s = 
//	{//最后一位1为正向，2为反向
//			{Location.BigMap.fish_1_x, Location.BigMap.fish_1_y, 2},
//			{Location.BigMap.fish_2_x, Location.BigMap.fish_2_y, 2},
//			{Location.BigMap.fish_3_x, Location.BigMap.fish_3_y, 2},
//			{Location.BigMap.fish_4_x, Location.BigMap.fish_4_y, 1},
//	};
//	private int[][] fish_d;
//	private int[][] vortex_s = 
//	{
//			{Location.BigMap.vortex1_1_x, Location.BigMap.vortex1_1_y, 1},
//			{Location.BigMap.vortex1_2_x, Location.BigMap.vortex1_2_y, 1},
//			{Location.BigMap.vortex1_3_x, Location.BigMap.vortex1_3_y, 1},
//			{Location.BigMap.vortex2_1_x, Location.BigMap.vortex2_1_y, 2},
//			{Location.BigMap.vortex2_2_x, Location.BigMap.vortex2_2_y, 2},
//	};
//	private int[][] vortex_d;
//	private int[][] stone_s = 
//	{
//			{Location.BigMap.stone2_1_x, Location.BigMap.stone2_1_y, 2},
//			{Location.BigMap.stone2_2_x, Location.BigMap.stone2_2_y, 2},
//			{Location.BigMap.stone2_3_x, Location.BigMap.stone2_3_y, 2},
//			{Location.BigMap.stone1_1_x, Location.BigMap.stone1_1_y, 1},
//			{Location.BigMap.stone1_2_x, Location.BigMap.stone1_2_y, 1},
//			{Location.BigMap.stone1_3_x, Location.BigMap.stone1_3_y, 1},
//	};
//	private int[][] stone_d;
//	private int[][] island_s = 
//	{
//			{Location.BigMap.island1_1_x, Location.BigMap.island1_1_y, 1},
//			{Location.BigMap.island1_2_x, Location.BigMap.island1_2_y, 1},
//			{Location.BigMap.island1_3_x, Location.BigMap.island1_3_y, 1},
//			{Location.BigMap.island1_4_x, Location.BigMap.island1_4_y, 1},
//			{Location.BigMap.island2_1_x, Location.BigMap.island2_1_y, 2},
//	};
//	private int[][] island_d;
//	private int[][] cloud_s = 
//	{
//			{Location.BigMap.cloud1_1_x, Location.BigMap.cloud1_1_y, 1},
//			{Location.BigMap.cloud1_3_x, Location.BigMap.cloud1_3_y, 1},
//			{Location.BigMap.cloud1_4_x, Location.BigMap.cloud1_4_y, 1},
//			{Location.BigMap.cloud1_2_x, Location.BigMap.cloud1_2_y, 1},
//			{Location.BigMap.cloud2_1_x, Location.BigMap.cloud2_1_y, 2},
//			{Location.BigMap.cloud1_5_x, Location.BigMap.cloud1_5_y, 1},
//			{Location.BigMap.cloud1_7_x, Location.BigMap.cloud1_7_y, 1},
//			{Location.BigMap.cloud1_6_x, Location.BigMap.cloud1_6_y, 1},
//			{Location.BigMap.cloud1_9_x, Location.BigMap.cloud1_9_y, 1},
//			{Location.BigMap.cloud1_8_x, Location.BigMap.cloud1_8_y, 1},
//			{Location.BigMap.cloud2_3_x, Location.BigMap.cloud2_3_y, 2},
//			{Location.BigMap.cloud2_2_x, Location.BigMap.cloud2_2_y, 2},
//			{Location.BigMap.cloud2_4_x, Location.BigMap.cloud2_4_y, 2},
//			{Location.BigMap.cloud1_10_x, Location.BigMap.cloud1_10_y, 1},
//	};
//	private int[][] cloud_d;
//	private int[][][] line_s = 
//	{
//			{{Location.BigMapLevel.select7_1_1_x, Location.BigMapLevel.select7_1_1_y, 7},{Location.BigMapLevel.select7_1_2_x, Location.BigMapLevel.select7_1_2_y, 7}},
//			{{Location.BigMapLevel.select7_2_1_x, Location.BigMapLevel.select7_2_1_y, 7},{Location.BigMapLevel.select7_2_2_x, Location.BigMapLevel.select7_2_2_y, 7}},
//			{{Location.BigMapLevel.select7_3_1_x, Location.BigMapLevel.select7_3_1_y, 7},{Location.BigMapLevel.select7_3_2_x, Location.BigMapLevel.select7_3_2_y, 7}},
//			{{Location.BigMapLevel.select7_4_1_x, Location.BigMapLevel.select7_4_1_y, 7},{Location.BigMapLevel.select7_4_2_x, Location.BigMapLevel.select7_4_2_y, 7}},
//			{{Location.BigMapLevel.select7_5_1_x, Location.BigMapLevel.select7_5_1_y, 7},{Location.BigMapLevel.select7_5_2_x, Location.BigMapLevel.select7_5_2_y, 7}},
//			{{Location.BigMapLevel.select7_6_1_x, Location.BigMapLevel.select7_6_1_y, 7},{Location.BigMapLevel.select7_6_2_x, Location.BigMapLevel.select7_6_2_y, 7}},
//			{{Location.BigMapLevel.select7_7_1_x, Location.BigMapLevel.select7_7_1_y, 7},{Location.BigMapLevel.select7_7_2_x, Location.BigMapLevel.select7_7_2_y, 7}},
//			{{Location.BigMapLevel.select7_8_1_x, Location.BigMapLevel.select7_8_1_y, 7},{Location.BigMapLevel.select7_8_2_x, Location.BigMapLevel.select7_8_2_y, 7}},
//			{{Location.BigMapLevel.select7_9_1_x, Location.BigMapLevel.select7_9_1_y, 7},{Location.BigMapLevel.select7_9_2_x, Location.BigMapLevel.select7_9_2_y, 7}},
//			{{Location.BigMapLevel.select7_10_1_x, Location.BigMapLevel.select7_10_1_y, 7}, {Location.BigMapLevel.select7_10_2_x, Location.BigMapLevel.select7_10_2_y, 7}},
//			{{Location.BigMapLevel.select7_11_1_x, Location.BigMapLevel.select7_11_1_y, 7},	{Location.BigMapLevel.select7_11_2_x, Location.BigMapLevel.select7_11_2_y, 7}},
//			{{Location.BigMapLevel.select7_12_1_x, Location.BigMapLevel.select7_12_1_y, 7}, {Location.BigMapLevel.select7_12_2_x, Location.BigMapLevel.select7_12_2_y, 7}},
//			{{Location.BigMapLevel.select7_13_1_x, Location.BigMapLevel.select7_13_1_y, 7}},
//			{{Location.BigMapLevel.select7_14_1_x, Location.BigMapLevel.select7_14_1_y, 7}},
//			{{Location.BigMapLevel.select8_15_1_x, Location.BigMapLevel.select8_15_1_y, 8},	{Location.BigMapLevel.select8_15_2_x, Location.BigMapLevel.select8_15_2_y, 8}},
//			{{Location.BigMapLevel.select8_16_1_x, Location.BigMapLevel.select8_16_1_y, 8},	{Location.BigMapLevel.select8_16_2_x, Location.BigMapLevel.select8_16_2_y, 8}},
//			{{Location.BigMapLevel.select8_17_1_x, Location.BigMapLevel.select8_17_1_y, 8},	{Location.BigMapLevel.select8_17_2_x, Location.BigMapLevel.select8_17_2_y, 8}},
//			{{Location.BigMapLevel.select8_18_1_x, Location.BigMapLevel.select8_18_1_y, 8},	{Location.BigMapLevel.select8_18_2_x, Location.BigMapLevel.select8_18_2_y, 8}},
//			{{Location.BigMapLevel.select8_19_1_x, Location.BigMapLevel.select8_19_1_y, 8},	{Location.BigMapLevel.select8_19_2_x, Location.BigMapLevel.select8_19_2_y, 8}},
//			{{Location.BigMapLevel.select8_20_1_x, Location.BigMapLevel.select8_20_1_y, 8},	{Location.BigMapLevel.select8_20_2_x, Location.BigMapLevel.select8_20_2_y, 8}},
//			{{Location.BigMapLevel.select8_21_1_x, Location.BigMapLevel.select8_21_1_y, 8},	{Location.BigMapLevel.select8_21_2_x, Location.BigMapLevel.select8_21_2_y, 8}},
//			{{Location.BigMapLevel.select8_22_1_x, Location.BigMapLevel.select8_22_1_y, 8},	{Location.BigMapLevel.select8_22_2_x, Location.BigMapLevel.select8_22_2_y, 8}},
//			{{Location.BigMapLevel.select8_23_1_x, Location.BigMapLevel.select8_23_1_y, 8},	{Location.BigMapLevel.select8_23_2_x, Location.BigMapLevel.select8_23_2_y, 8}},
//			{{Location.BigMapLevel.select8_24_1_x, Location.BigMapLevel.select8_24_1_y, 8},	{Location.BigMapLevel.select8_24_2_x, Location.BigMapLevel.select8_24_2_y, 8}},
//			{{Location.BigMapLevel.select8_25_1_x, Location.BigMapLevel.select8_25_1_y, 8},	{Location.BigMapLevel.select8_25_2_x, Location.BigMapLevel.select8_25_2_y, 8}},
//			{{Location.BigMapLevel.select8_26_1_x, Location.BigMapLevel.select8_26_1_y, 8},	{Location.BigMapLevel.select8_26_2_x, Location.BigMapLevel.select8_26_2_y, 8}},
//			{{Location.BigMapLevel.select8_27_1_x, Location.BigMapLevel.select8_27_1_y, 8},	{Location.BigMapLevel.select8_27_2_x, Location.BigMapLevel.select8_27_2_y, 8}},
//			{{Location.BigMapLevel.select8_28_1_x, Location.BigMapLevel.select8_28_1_y, 8},	{Location.BigMapLevel.select8_28_2_x, Location.BigMapLevel.select8_28_2_y, 8}},
//			{{Location.BigMapLevel.select8_29_1_x, Location.BigMapLevel.select8_29_1_y, 8},	{Location.BigMapLevel.select8_29_2_x, Location.BigMapLevel.select8_29_2_y, 8},{Location.BigMapLevel.select8_29_3_x, Location.BigMapLevel.select8_29_3_y, 8}},
//			{{Location.BigMapLevel.select9_30_1_x, Location.BigMapLevel.select9_30_1_y, 9},	{Location.BigMapLevel.select9_30_2_x, Location.BigMapLevel.select9_30_2_y, 9}},
//			{{Location.BigMapLevel.select9_31_1_x, Location.BigMapLevel.select9_31_1_y, 9},	{Location.BigMapLevel.select9_31_2_x, Location.BigMapLevel.select9_31_2_y, 9}},
//			{{Location.BigMapLevel.select9_32_1_x, Location.BigMapLevel.select9_32_1_y, 9},	{Location.BigMapLevel.select9_32_2_x, Location.BigMapLevel.select9_32_2_y, 9}},
//			{{Location.BigMapLevel.select9_33_1_x, Location.BigMapLevel.select9_33_1_y, 9},	{Location.BigMapLevel.select9_33_2_x, Location.BigMapLevel.select9_33_2_y, 9}},
//			{{Location.BigMapLevel.select9_34_1_x, Location.BigMapLevel.select9_34_1_y, 9},	{Location.BigMapLevel.select9_34_2_x, Location.BigMapLevel.select9_34_2_y, 9}},
//			{{Location.BigMapLevel.select9_35_1_x, Location.BigMapLevel.select9_35_1_y, 9},	{Location.BigMapLevel.select9_35_2_x, Location.BigMapLevel.select9_35_2_y, 9}},
//			{{Location.BigMapLevel.select9_36_1_x, Location.BigMapLevel.select9_36_1_y, 9},	{Location.BigMapLevel.select9_36_2_x, Location.BigMapLevel.select9_36_2_y, 9}},
//			{{Location.BigMapLevel.select9_37_1_x, Location.BigMapLevel.select9_37_1_y, 9},	{Location.BigMapLevel.select9_37_2_x, Location.BigMapLevel.select9_37_2_y, 9}},
//			{{Location.BigMapLevel.select9_38_1_x, Location.BigMapLevel.select9_38_1_y, 9},	{Location.BigMapLevel.select9_38_2_x, Location.BigMapLevel.select9_38_2_y, 9}},
//			{{Location.BigMapLevel.select9_39_1_x, Location.BigMapLevel.select9_39_1_y, 9},	{Location.BigMapLevel.select9_39_2_x, Location.BigMapLevel.select9_39_2_y, 9}},
//			{{Location.BigMapLevel.select9_40_1_x, Location.BigMapLevel.select9_40_1_y, 9},	{Location.BigMapLevel.select9_40_2_x, Location.BigMapLevel.select9_40_2_y, 9}},
//			{{Location.BigMapLevel.select9_41_1_x, Location.BigMapLevel.select9_41_1_y, 9},	{Location.BigMapLevel.select9_41_2_x, Location.BigMapLevel.select9_41_2_y, 9}},
//			{{Location.BigMapLevel.select9_42_1_x, Location.BigMapLevel.select9_42_1_y, 9},	{Location.BigMapLevel.select9_42_2_x, Location.BigMapLevel.select9_42_2_y, 9}},
//			{{Location.BigMapLevel.select9_43_1_x, Location.BigMapLevel.select9_43_1_y, 9},	{Location.BigMapLevel.select9_43_2_x, Location.BigMapLevel.select9_43_2_y, 9}},
//			{{Location.BigMapLevel.select9_44_1_x, Location.BigMapLevel.select9_44_1_y, 9},	{Location.BigMapLevel.select9_44_2_x, Location.BigMapLevel.select9_44_2_y, 9}},
//			{{Location.BigMapLevel.select9_45_1_x, Location.BigMapLevel.select9_45_1_y, 9},	{Location.BigMapLevel.select9_45_2_x, Location.BigMapLevel.select9_45_2_y, 9}},
//			{{Location.BigMapLevel.select9_46_1_x, Location.BigMapLevel.select9_46_1_y, 9},	{Location.BigMapLevel.select9_46_2_x, Location.BigMapLevel.select9_46_2_y, 9}},
//			{{Location.BigMapLevel.select9_47_1_x, Location.BigMapLevel.select9_47_1_y, 9},	{Location.BigMapLevel.select9_47_2_x, Location.BigMapLevel.select9_47_2_y, 9}},
//			{{Location.BigMapLevel.select9_48_1_x, Location.BigMapLevel.select9_48_1_y, 9},	{Location.BigMapLevel.select9_48_2_x, Location.BigMapLevel.select9_48_2_y, 9}},
//			{{Location.BigMapLevel.select9_49_1_x, Location.BigMapLevel.select9_49_1_y, 9},	{Location.BigMapLevel.select9_49_2_x, Location.BigMapLevel.select9_49_2_y, 9}},
//	};
//	private int[][][] line_d;
////	private int[] line_jianju = 
////	{
////			2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
////			2, 2, 1, 1, 2, 2, 2, 2, 2, 2,
////			2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
////			2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
////			2, 2, 2, 2, 2, 2, 2, 2, 2, 
////	};
//	private int[][] level_s = 
//	{
//			{Location.BigMapLevel.select1_1_x, Location.BigMapLevel.select1_1_y, 1},
//			{Location.BigMapLevel.select1_2_x, Location.BigMapLevel.select1_2_y, 1},
//			{Location.BigMapLevel.select1_3_x, Location.BigMapLevel.select1_3_y, 1},
//			{Location.BigMapLevel.select1_4_x, Location.BigMapLevel.select1_4_y, 1},
//			{Location.BigMapLevel.select1_5_x, Location.BigMapLevel.select1_5_y, 1},
//			{Location.BigMapLevel.select1_6_x, Location.BigMapLevel.select1_6_y, 1},
//			{Location.BigMapLevel.select1_7_x, Location.BigMapLevel.select1_7_y, 1},
//			{Location.BigMapLevel.select1_8_x, Location.BigMapLevel.select1_8_y, 1},
//			{Location.BigMapLevel.select1_9_x, Location.BigMapLevel.select1_9_y, 1},
//			{Location.BigMapLevel.select1_10_x, Location.BigMapLevel.select1_10_y, 1},
//			{Location.BigMapLevel.select1_11_x, Location.BigMapLevel.select1_11_y, 1},
//			{Location.BigMapLevel.select1_12_x, Location.BigMapLevel.select1_12_y, 1},
//			{Location.BigMapLevel.select1_13_x, Location.BigMapLevel.select1_13_y, 1},
//			{Location.BigMapLevel.select1_14_x, Location.BigMapLevel.select1_14_y, 1},
//			{Location.BigMapLevel.select2_15_x, Location.BigMapLevel.select2_15_y, 2},
//			{Location.BigMapLevel.select3_16_x, Location.BigMapLevel.select3_16_y, 3},
//			{Location.BigMapLevel.select3_17_x, Location.BigMapLevel.select3_17_y, 3},
//			{Location.BigMapLevel.select3_18_x, Location.BigMapLevel.select3_18_y, 3},
//			{Location.BigMapLevel.select3_19_x, Location.BigMapLevel.select3_19_y, 3},
//			{Location.BigMapLevel.select3_20_x, Location.BigMapLevel.select3_20_y, 3},
//			{Location.BigMapLevel.select3_21_x, Location.BigMapLevel.select3_21_y, 3},
//			{Location.BigMapLevel.select3_22_x, Location.BigMapLevel.select3_22_y, 3},
//			{Location.BigMapLevel.select3_23_x, Location.BigMapLevel.select3_23_y, 3},
//			{Location.BigMapLevel.select3_24_x, Location.BigMapLevel.select3_24_y, 3},
//			{Location.BigMapLevel.select3_25_x, Location.BigMapLevel.select3_25_y, 3},
//			{Location.BigMapLevel.select3_26_x, Location.BigMapLevel.select3_26_y, 3},
//			{Location.BigMapLevel.select3_27_x, Location.BigMapLevel.select3_27_y, 3},
//			{Location.BigMapLevel.select3_28_x, Location.BigMapLevel.select3_28_y, 3},
//			{Location.BigMapLevel.select3_29_x, Location.BigMapLevel.select3_29_y, 3},
//			{Location.BigMapLevel.select4_30_x, Location.BigMapLevel.select4_30_y, 4},
//			{Location.BigMapLevel.select5_31_x, Location.BigMapLevel.select5_31_y, 5},
//			{Location.BigMapLevel.select5_32_x, Location.BigMapLevel.select5_32_y, 5},
//			{Location.BigMapLevel.select5_33_x, Location.BigMapLevel.select5_33_y, 5},
//			{Location.BigMapLevel.select5_34_x, Location.BigMapLevel.select5_34_y, 5},
//			{Location.BigMapLevel.select5_35_x, Location.BigMapLevel.select5_35_y, 5},
//			{Location.BigMapLevel.select5_36_x, Location.BigMapLevel.select5_36_y, 5},
//			{Location.BigMapLevel.select5_37_x, Location.BigMapLevel.select5_37_y, 5},
//			{Location.BigMapLevel.select5_38_x, Location.BigMapLevel.select5_38_y, 5},
//			{Location.BigMapLevel.select5_39_x, Location.BigMapLevel.select5_39_y, 5},
//			{Location.BigMapLevel.select5_40_x, Location.BigMapLevel.select5_40_y, 5},
//			{Location.BigMapLevel.select5_41_x, Location.BigMapLevel.select5_41_y, 5},
//			{Location.BigMapLevel.select5_42_x, Location.BigMapLevel.select5_42_y, 5},
//			{Location.BigMapLevel.select5_43_x, Location.BigMapLevel.select5_43_y, 5},
//			{Location.BigMapLevel.select5_44_x, Location.BigMapLevel.select5_44_y, 5},
//			{Location.BigMapLevel.select5_45_x, Location.BigMapLevel.select5_45_y, 5},
//			{Location.BigMapLevel.select5_46_x, Location.BigMapLevel.select5_46_y, 5},
//			{Location.BigMapLevel.select5_47_x, Location.BigMapLevel.select5_47_y, 5},
//			{Location.BigMapLevel.select5_48_x, Location.BigMapLevel.select5_48_y, 5},
//			{Location.BigMapLevel.select5_49_x, Location.BigMapLevel.select5_49_y, 5},
//			{Location.BigMapLevel.select6_50_x, Location.BigMapLevel.select6_50_y, 6},
//	};
//	private int[][] level_d;
//	
//	float startX_1 = 0;			//记录第一个触点开始 X 坐标
//	float startY_1 = 0;			//记录第一个触点开始 Y 坐标
//	float startX_2 = 0;			//记录第二个触点开始 X 坐标
//	float startY_2 = 0;			//记录第二个触点开始 Y 坐标
//	int oldX,oldY;
//	
//	int mode1,mode2;
//	float r;
//	float min_r = 0.7f;
//	float max_r = 1.5f;
//	float move_X,move_Y;
//	
//	private String gold_str;
//	private String gem_str;
//	private String heart_str;
//	private String time_str;
//	private String mail_str;
//	
//	boolean anjiangold;
//	boolean anjiangem;
//	boolean anjianheart;
//	boolean anjianfarm;
//	boolean anjianstore;
//	boolean anjianmail;
//	private boolean[] anjian_level;
//	
//	boolean isfarmArrowDown;
//	boolean isboatDown;
//	
//	boolean isTwoTouchMoveZoomBig;
//	boolean isTwoTouchMoveZoomSmall;
//	boolean isOneTouchMove;
//	//暂定跟宝箱使用同一变量做动画
//	int farmArrowH;
//	int boatH;
//	
//	int reward_level = -1;
//	int current_level = 13;
//	int friend = 4;
//	
//	GameLevelInfoModule levelInfoModule;
////	public static GetCardModule getCardModule;
//	public static TakeCardModule takeCardModule;
////	private int[][] bigmap_elements_xy;
////	private String[] bigmap_elements_strs=
////	{
////			
////	};
//	public void Release() {
////		GameImage.delImage(bgbmp);
////		bgbmp = null;
//		GameImage.delImage(coast1.bitmap);
//		coast1 = null;
//		GameImage.delImage(coast2.bitmap);
//		coast2 = null;
//		GameImage.delImage(boat.bitmap);
//		boat = null;
//		GameImage.delImage(gold.bitmap);
//		gold = null;
//		GameImage.delImage(gem.bitmap);
//		gem = null;
//		GameImage.delImage(heart.bitmap);
//		heart = null;
//		GameImage.delImage(statebg.bitmap);
//		statebg = null;
//		GameImage.delImage(rechange.bitmap);
//		rechange = null;
//		GameImage.delImage(storeWord.bitmap);
//		storeWord = null;
//		GameImage.delImage(store.bitmap);
//		store = null;
//		GameImage.delImage(mail.bitmap);
//		mail = null;
//		GameImage.delImage(mailred.bitmap);
//		mailred = null;
//		GameImage.delImage(farm.bitmap);
//		farm = null;
//		GameImage.delImage(farmArrow.bitmap);
//		farmArrow = null;
////		for (int i=0; i<bigmapelements.length; i++){
////			GameImage.delImage(bigmapelements[i]);
////			bigmapelements[i] = null;
////		}
////		bigmapelements = null;
//		GameImage.delImageArray(maplevel_number);
//		maplevel_number = null;
//		GameImage.delImageArray(mapmenu_number);
//		mapmenu_number = null;
//		GameImage.delImageArray(mapmenu_heart_number);
//		mapmenu_heart_number = null;
//		GameImage.delImage(hill.bitmap);
//		hill = null;
//		GameImage.delImageArray(smoke);
//		smoke = null;
//		for (int i=0; i<spot.length; i++){
//			GameImage.delImage(spot[i].bitmap);
//			spot[i] = null;
//		}
//		spot = null;
//		for (int i=0; i<tree.length; i++){
//			GameImage.delImage(tree[i].bitmap);
//			tree[i] = null;
//		}
//		tree = null;
//		for (int i=0; i<grass.length; i++){
//			GameImage.delImage(grass[i].bitmap);
//			grass[i] = null;
//		}
//		grass = null;
//		GameImage.delImageArray(vortex1);
//		vortex1 = null;
//		GameImage.delImageArray(vortex2);
//		vortex2 = null;
//		GameImage.delImageArray(fish);
//		fish = null;
//		for (int i=0; i<stone.length; i++){
//			GameImage.delImage(stone[i].bitmap);
//			stone[i] = null;
//		}
//		stone = null;
//		GameImage.delImage(box.bitmap);
//		box = null;
//		for (int i=0; i<island.length; i++){
//			GameImage.delImage(island[i].bitmap);
//			island[i] = null;
//		}
//		island = null;
//		GameImage.delImageArray(octopus);
//		octopus = null;
//		for (int i=0; i<cloud.length; i++){
//			GameImage.delImage(cloud[i].bitmap);
//			cloud[i] = null;
//		}
//		cloud = null;
//		for (int i=0; i<select.length; i++){
//			GameImage.delImage(select[i].bitmap);
//			select[i] = null;
//		}
//		select = null;
//		for (int i=0; i<star.length; i++){
//			GameImage.delImage(star[i].bitmap);
//			star[i] = null;
//		}
//		star = null;
//		for (int i=0; i<enemy_level.length; i++){
//			GameImage.delImage(enemy_level[i].bitmap);
//			enemy_level[i] = null;
//		}
//		enemy_level = null;
//		for (int i=0; i<photo.length; i++){
//			GameImage.delImage(photo[i].bitmap);
//			photo[i] = null;
//		}
//		photo = null;
//		GameImage.delImageArray(currentLevel);
//		currentLevel = null;
//		
////		levelInfoModule.ReleaseRecoure();
//	}
//	
//	public ChooseLevelModule() {
//
////		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(),
////	    "font/ARLRDBD.TTF");
////		paint =new Paint();
////		paint.setColor(Color.RED);
////		paint.setTextSize(24*GameConfig.f_zoom);
////		paint.setTypeface(fontFace);
//		r = 1f;
//		Configs.GameMapWidth = Configs.GameMapORGWidth * GameConfig.f_zoom;
//		Configs.GameMapHeight = Configs.GameMapORGHeight * GameConfig.f_zoom;
//		
////		bgbmp = GameImage.getImage("s_map/guankamap");
//		//所有初始坐标需要加上初次的便宜缩放的量
////		bgx = (int) (0 + GameConfig.GameScreen_Width/2);
////		bgy = (int) (0 - (Configs.GameMapHeight - GameConfig.GameScreen_Height) + (Configs.GameMapHeight - GameConfig.GameScreen_Height/2)*r);
//		bgx = GameConfig.GameScreen_Width/2+(Configs.GameMapWidth-GameConfig.GameScreen_Width)/2;
//		bgy = GameConfig.GameScreen_Height/2-(Configs.GameMapHeight-GameConfig.GameScreen_Height)/2;
//		
//		bg1_1x = initIsNotBitmapCoordinate(Location.BigMap.upperHalfBackGround_x);
//		bg1_1y = initIsNotBitmapCoordinate(Location.BigMap.upperHalfBackGround_y);
//		bg1_2x = initIsNotBitmapCoordinate(Location.BigMap.upperHalfBackGround_x + Location.BigMap.upperHalfBackGround_w);
//		bg1_2y = initIsNotBitmapCoordinate(Location.BigMap.upperHalfBackGround_y + Location.BigMap.upperHalfBackGround_h);
//		bg2_1x = initIsNotBitmapCoordinate(Location.BigMap.bottomHalfBackGround_x);
//		bg2_1y = initIsNotBitmapCoordinate(Location.BigMap.bottomHalfBackGround_y);
//		bg2_2x = initIsNotBitmapCoordinate(Location.BigMap.bottomHalfBackGround_x + Location.BigMap.bottomHalfBackGround_w);
//		bg2_2y = initIsNotBitmapCoordinate(Location.BigMap.bottomHalfBackGround_y + Location.BigMap.bottomHalfBackGround_h);
//		
//		coast1 = new Sprite(GameImage.getImage("s_map/map_coast_1"));
//		coast2 = new Sprite(GameImage.getImage("s_map/map_coast_2"));
//		coast1x = initIsBitmapCoordinateX(Location.BigMap.coast1_x, coast1);
//		coast1y = initIsBitmapCoordinateY(Location.BigMap.coast1_y, coast1);
//		coast2x = initIsBitmapCoordinateX(Location.BigMap.coast2_x, coast2);
//		coast2y = initIsBitmapCoordinateY(Location.BigMap.coast2_y, coast2);
//		
//		shade_1x = initIsNotBitmapCoordinate(Location.BigMap.shade_1_x);
//		shade_1y = initIsNotBitmapCoordinate(Location.BigMap.shade_1_y);
//		shade_2x = initIsNotBitmapCoordinate(Location.BigMap.shade_1_x + Location.BigMap.shade_1_w);
//		shade_2y = initIsNotBitmapCoordinate(Location.BigMap.shade_1_y + Location.BigMap.shade_1_h);
//		
////		grassland1_1x = initIsNotBitmapCoordinate(Location.BigMap.grassland_1_x);
////		grassland1_1y = initIsNotBitmapCoordinate(Location.BigMap.grassland_1_y);
////		grassland1_2x = initIsNotBitmapCoordinate(Location.BigMap.grassland_1_x + Location.BigMap.grassland_1_w);
////		grassland1_2y = initIsNotBitmapCoordinate(Location.BigMap.grassland_1_y + Location.BigMap.grassland_1_h);
////		grassland2_1x = initIsNotBitmapCoordinate(Location.BigMap.grassland_2_x);
////		grassland2_1y = initIsNotBitmapCoordinate(Location.BigMap.grassland_2_y);
////		grassland2_2x = initIsNotBitmapCoordinate(Location.BigMap.grassland_2_x + Location.BigMap.grassland_2_w);
////		grassland2_2y = initIsNotBitmapCoordinate(Location.BigMap.grassland_2_y + Location.BigMap.grassland_2_h);
//		grassland_d = new int[grassland_s.length][grassland_s[0].length];
//		for(int i=0; i<grassland_d.length; i++) {
//			grassland_d[i][0] = initIsNotBitmapCoordinate(grassland_s[i][0]);
//			grassland_d[i][1] = initIsNotBitmapCoordinate(grassland_s[i][1]);
//			grassland_d[i][2] = initIsNotBitmapCoordinate(grassland_s[i][0]+grassland_s[i][2]);
//			grassland_d[i][3] = initIsNotBitmapCoordinate(grassland_s[i][1]+grassland_s[i][3]);
//			grassland_d[i][4] = grassland_s[i][4];
//		}
//		
//		spot = new Sprite[5];
//		for(int i=0; i<spot.length; i++) {
//			spot[i] = new Sprite(GameImage.getImage("s_map/map_spot_" + (i+1)));
//		}
//		spot_d = new int[spot_s.length][spot_s[0].length];
//		for(int i=0; i<spot_s.length; i++) {
//			spot_d[i][0] = (int)initIsBitmapCoordinateX(spot_s[i][0], spot[spot_s[i][2]-1]);
//			spot_d[i][1] = (int)initIsBitmapCoordinateY(spot_s[i][1], spot[spot_s[i][2]-1]);
//			spot_d[i][2] = spot_s[i][2];
//		}
//		
//		tree = new Sprite[4];
//		for(int i=0; i<tree.length; i++) {
//			tree[i] = new Sprite(GameImage.getImage("s_map/map_tree_" + (i+1)));
//		}
//		tree_d = new int[tree_s.length][tree_s[0].length];
//		for(int i=0; i<tree_s.length; i++) {
//			tree_d[i][0] = (int)initIsBitmapCoordinateX(tree_s[i][0], tree[tree_s[i][2]-1]);
//			tree_d[i][1] = (int)initIsBitmapCoordinateY(tree_s[i][1], tree[tree_s[i][2]-1]);
//			tree_d[i][2] = tree_s[i][2];
//		}
//		grass = new Sprite[3];
//		for(int i=0; i<grass.length; i++) {
//			grass[i] = new Sprite(GameImage.getImage("s_map/map_grass_" + (i+1)));
//		}
//		grass_d = new int[grass_s.length][grass_s[0].length];
//		for(int i=0; i<grass_s.length; i++) {
//			grass_d[i][0] = (int)initIsBitmapCoordinateX(grass_s[i][0], grass[grass_s[i][2]-1]);
//			grass_d[i][1] = (int)initIsBitmapCoordinateY(grass_s[i][1], grass[grass_s[i][2]-1]);
//			grass_d[i][2] = grass_s[i][2];
//		}
//		
//		//******初始化海景*******
//		fish = GameImage.getAutoSizecutBitmap("s_map/map_fish", 3, 1,
//				GameImage.Sort_line);
//		fish_d = new int[fish_s.length][fish_s[0].length];
//		for(int i=0; i<fish_s.length; i++) {
//			fish_d[i][0] = (int)initIsBitmapCoordinateX(fish_s[i][0], fish[0]);
//			fish_d[i][1] = (int)initIsBitmapCoordinateY(fish_s[i][1], fish[0]);
//			fish_d[i][2] = fish_s[i][2];
//		}
////		vortex = new Bitmap[2];
////		for(int i=0; i<vortex.length; i++) {
////			vortex[i] = GameImage.getImage("s_map/map_vortex_" + (i+1));
////		}
//		vortex1 = GameImage.getAutoSizecutBitmap("s_map/map_vortex_1", 3, 1,
//				GameImage.Sort_line);
//		vortex2 = GameImage.getAutoSizecutBitmap("s_map/map_vortex_2", 3, 1,
//				GameImage.Sort_line);
//		vortex_d = new int[vortex_s.length][vortex_s[0].length];
//		for(int i=0; i<vortex_s.length; i++) {
//			switch (vortex_s[i][2]) {
//			case 1:				
//				vortex_d[i][0] = (int)initIsBitmapCoordinateX(vortex_s[i][0], vortex1[0]);
//				vortex_d[i][1] = (int)initIsBitmapCoordinateY(vortex_s[i][1], vortex1[0]);
//				break;
//			case 2:
//				vortex_d[i][0] = (int)initIsBitmapCoordinateX(vortex_s[i][0], vortex2[0]);
//				vortex_d[i][1] = (int)initIsBitmapCoordinateY(vortex_s[i][1], vortex2[0]);				
//				break;
//			}
//			vortex_d[i][2] = vortex_s[i][2];
//		}
//		stone = new Sprite[2];
//		for(int i=0; i<stone.length; i++) {
//			stone[i] = new Sprite(GameImage.getImage("s_map/map_stone_" + (i+1)));
//		}
//		stone_d = new int[stone_s.length][stone_s[0].length];
//		for(int i=0; i<stone_s.length; i++) {
//			stone_d[i][0] = (int)initIsBitmapCoordinateX(stone_s[i][0], stone[stone_s[i][2]-1]);
//			stone_d[i][1] = (int)initIsBitmapCoordinateY(stone_s[i][1], stone[stone_s[i][2]-1]);
//			stone_d[i][2] = stone_s[i][2];
//		}
//		
//		//100,200,w,h
//		boat = new Sprite(GameImage.getImage("s_map/map_boat"));
//		boatH = 0;
//		isboatDown = true;
//		boatx = (int)initIsBitmapCoordinateX(Location.BigMap.boat_x, boat);
//		boaty = (int)initIsBitmapCoordinateY(Location.BigMap.boat_y, boat);
//		if (Configs.isDebug)
//		System.out.println("sunlf init boatx = " +boatx + ",boaty = " + boaty);
//		
//		island = new Sprite[2];
//		for(int i=0; i<island.length; i++) {
//			island[i] = new Sprite(GameImage.getImage("s_map/map_island_" + (i+1)));
//		}
//		island_d = new int[island_s.length][island_s[0].length];
//		for(int i=0; i<island_s.length; i++) {
//			island_d[i][0] = (int)initIsBitmapCoordinateX(island_s[i][0], island[island_s[i][2]-1]);
//			island_d[i][1] = (int)initIsBitmapCoordinateY(island_s[i][1], island[island_s[i][2]-1]);
//			island_d[i][2] = island_s[i][2];
//		}
////		island_2 = GameImage.getImage("s_map/map_island_2");
////		island_2_x = (int)initIsBitmapCoordinateX(Location.BigMap.island2_1_x, island_2);
////		island_2_y = (int)initIsBitmapCoordinateY(Location.BigMap.island2_1_y, island_2);
//		
//		hill	= new Sprite(GameImage.getImage("s_map/map_hill"));
//		hill_x = (int)initIsBitmapCoordinateX(Location.BigMap.hill_x, hill);
//		hill_y = (int)initIsBitmapCoordinateY(Location.BigMap.hill_y, hill);
//		hill_aphla = 133;
//		hill_R = hill_G = hill_B = -255;		
//		
//		smoke = GameImage.getAutoSizecutBitmap("s_map/map_smoke", 4, 1,
//				GameImage.Sort_line);
//		smoke_x = (int)initIsBitmapCoordinateX(Location.BigMap.smoke_x, smoke[0]);
//		smoke_y = (int)initIsBitmapCoordinateY(Location.BigMap.smoke_y, smoke[0]);
//		
//		box = new Sprite(GameImage.getImage("s_map/map_box"));
//		box_x = (int)initIsBitmapCoordinateX(Location.BigMap.box_x, box);
//		box_y = (int)initIsBitmapCoordinateY(Location.BigMap.box_y, box);
//		
//		octopus = GameImage.getAutoSizecutBitmap("s_map/map_octopus", 3, 1,
//				GameImage.Sort_line);
//		octopus_x = (int)initIsBitmapCoordinateX(Location.BigMap.octopus_x, octopus[0]);
//		octopus_y = (int)initIsBitmapCoordinateY(Location.BigMap.octopus_y, octopus[0]);
//		octopus_aphla = 133;
//		octopus_R = octopus_G = octopus_B = -255;		
//		
//		cloud = new Sprite[2];
//		for(int i=0; i<cloud.length; i++) {
//			cloud[i] = new Sprite(GameImage.getImage("s_map/map_cloud_0" + (i+1)));
//		}
//		cloud_d = new int[cloud_s.length][cloud_s[0].length];
//		for(int i=0; i<cloud_s.length; i++) {
//			cloud_d[i][0] = (int)initIsBitmapCoordinateX(cloud_s[i][0], cloud[cloud_s[i][2]-1]);
//			cloud_d[i][1] = (int)initIsBitmapCoordinateY(cloud_s[i][1], cloud[cloud_s[i][2]-1]);
//			cloud_d[i][2] = cloud_s[i][2];
//		}
////		bigmapelements = new Bitmap[bigmap_elements_strs.length];
////		for (int i=0; i<bigmap_elements_strs.length; i++) {
////			bigmapelements[i] = GameImage.getImage("s_map/"+bigmap_elements_strs[i]);
////		}
////		bigmap_elements_xy = new int[bigmap_elements_strs.length][2];
////		for(int i=0; i<bigmap_elements_xy.length; i++) {
////			bigmap_elements_xy[i][0] = (int) ((Location.BigMap.elements[i][0] + bigmapelements[i].getWidth()/2 - Configs.GameMapWidth/2));
////			bigmap_elements_xy[i][1] = (int) ((Location.BigMap.elements[i][1] + bigmapelements[i].getWidth()/2 - Configs.GameMapWidth/2));
////		}
//		//初始化地图
//		initMapLevel();
//		
//		//初始化地图菜单
//		initMapMenu();
//		
//		mode1 = Configs.NONE; 
//		isOneTouchMove = true;
//		
//		levelInfoModule = new GameLevelInfoModule();
////		getCardModule = new GetCardModule();
//		takeCardModule = new TakeCardModule();
//	}
//	
////	Typeface fontFace ;
////	Paint paint;
//	public boolean initialize() {
//		return false;
//	}
//	
//	private void initMapLevel() {
//		select = new Sprite[9];
//		for(int i=0; i<select.length; i++) {
//			select[i] = new Sprite(GameImage.getImage("s_map/map_select_0" + (i+1)));
//		}
//		//初始化路线
//		line_d = line_s.clone();
//		for (int i=0; i<line_s.length; i++) {
//			for (int j=0; j<line_s[i].length; j++) {
//				line_d[i][j][0] = (int)initIsBitmapCoordinateX(line_s[i][j][0], select[line_s[i][j][2]-1]);
//				line_d[i][j][1] = (int)initIsBitmapCoordinateY(line_s[i][j][1], select[line_s[i][j][2]-1]);
//				line_d[i][j][2] = line_s[i][j][2];
//			}
//		}
//		
//		anjian_level = new boolean[level_s.length];
//		level_d = new int[level_s.length][level_s[0].length];
//		for(int i=0; i<level_s.length; i++) {
//			level_d[i][0] = (int)initIsBitmapCoordinateX(level_s[i][0], select[level_s[i][2]-1]);
//			level_d[i][1] = (int)initIsBitmapCoordinateY(level_s[i][1], select[level_s[i][2]-1]);
//			level_d[i][2] = level_s[i][2];
//			anjian_level[i] = false;
//		}
//		maplevel_number = GameImage.getAutoSizecutBitmap("s_map/map_num_1", 10, 1, GameImage.Sort_line);
//		
//		star = new Sprite[4];
//		for(int i=0; i<star.length; i++) {
//			star[i] = new Sprite(GameImage.getImage("s_map/map_star_0" + (i+1)));
//		}
//		enemy_level = new Sprite[4];
//		for(int i=0; i<enemy_level.length; i++) {
//			enemy_level[i] = new Sprite(GameImage.getImage("s_map/map_enemy_0" + (i+1)));
//		}
//		reward_level = 1;
//		photo = new Sprite[2];
//		for(int i=0; i<photo.length; i++) {
//			photo[i] = new Sprite(GameImage.getImage("s_map/map_photo_" + (i+1)));
//		}
//		currentLevel = GameImage.getAutoSizecutBitmap("s_map/map_current", 8, 1,
//				GameImage.Sort_line);
//	}
//
//	private int initIsNotBitmapCoordinate(int original_x) {
//		return (int) ((original_x * GameConfig.f_zoom));
//		//return (int) ((original_x * GameConfig.f_zoom + Configs.GameMapWidth/2 - Configs.GameMapWidth/2));
//	}
//	//图片坐标初始化
//	private float initIsBitmapCoordinateX(int s_x, Sprite bitmap) {
//		return s_x * GameConfig.f_zoom + bitmap.bitmap.getWidth()/2 - Configs.GameMapWidth/2;
//	}
//	private float initIsBitmapCoordinateY(int s_y, Sprite bitmap) {
//		return s_y * GameConfig.f_zoom + bitmap.bitmap.getHeight()/2 - Configs.GameMapHeight/2;
//	}
//	private float initIsBitmapCoordinateX(int s_x, Bitmap bitmap) {
//		return s_x * GameConfig.f_zoom + bitmap.getWidth()/2 - Configs.GameMapWidth/2;
//	}
//	private float initIsBitmapCoordinateY(int s_y, Bitmap bitmap) {
//		return s_y * GameConfig.f_zoom + bitmap.getHeight()/2 - Configs.GameMapHeight/2;
//	}
//	
//	//初始化地图菜单
//	private void initMapMenu() {
//		gold 	= new Sprite(GameImage.getImage("s_map/map_gold"));
//		gem		= new Sprite(GameImage.getImage("s_map/map_gem"));
//		heart	= new Sprite(GameImage.getImage("s_map/map_heart"));
//		statebg = new Sprite(GameImage.getImage("s_map/map_back"));
//		rechange= new Sprite(GameImage.getImage("s_map/map_add"));
//		
//		storeWord= new Sprite(GameImage.getImage("s_map/map_store_2"));
//		store	= new Sprite(GameImage.getImage("s_map/map_store"));
//		Location.BigMapMenu.storeGrayFloor_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.storeGrayFloor_x)* GameConfig.f_zoom);
//		Location.BigMapMenu.store_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.store_x)* GameConfig.f_zoom);
//		Location.BigMapMenu.storeWord_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.storeWord_x)* GameConfig.f_zoom);
//		
//		mail	= new Sprite(GameImage.getImage("s_map/map_mail"));
//		mailred	= new Sprite(GameImage.getImage("s_map/map_mail_2"));
//		Location.BigMapMenu.mail_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.mail_x)* GameConfig.f_zoom);
//		Location.BigMapMenu.mailRedFloor_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.mailRedFloor_x)* GameConfig.f_zoom);
//		Location.BigMapMenu.mail_num_x = (int) (Location.BigMapMenu.mailRedFloor_x + 5 * GameConfig.f_zoom);
//		
//		farm	= new Sprite(GameImage.getImage("s_map/map_farm_1"));
//		farmArrow= new Sprite(GameImage.getImage("s_map/map_farm_2"));
//		Location.BigMapMenu.farm_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.farm_x)* GameConfig.f_zoom);
//		Location.BigMapMenu.farm_y = (int) (GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.BigMapMenu.farm_y)* GameConfig.f_zoom);
//		Location.BigMapMenu.farmArrow_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.farmArrow_x)* GameConfig.f_zoom);
//		Location.BigMapMenu.farmArrow_y = (int) (GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.BigMapMenu.farmArrow_y)* GameConfig.f_zoom);
//		
//		mapmenu_number = GameImage.getAutoSizecutBitmap("s_map/map_num_2", 11, 1, GameImage.Sort_line);
//		mapmenu_heart_number = GameImage.getAutoSizecutBitmap("s_map/map_num_3", 6, 1, GameImage.Sort_line);
//		gold_str 	= "12345";
//		gem_str 	= "12345";
//		heart_str	= "3";
//		time_str	= "01:30";
//		mail_str	= "25";
//		
//		isfarmArrowDown = true;
//		farmArrowH = 0;
//		if (Configs.isDebug)
//			System.out.println(">>>>>>>>>>>ScreenWidth = " + GameConfig.GameScreen_Width + ",ORGScreenWidth = " + GameConfig.ORGScreen_Width);
//	}
//
//	public void paint(Canvas canvas) {
////		canvas.drawBitmap(bgbmp, 0,0, null);
//		ExternalMethods.paintzhao(canvas, new Paint(), 0xff25d3f2, 255, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
//		float tempX = move_X;
//		float tempY = move_Y;
//		//chenliang
//		//画背景
//		paintBackGround(canvas, tempX, tempY);
//		
////		Library2.drawImage(canvas, bgbmp, bgx + tempX - Configs.GameMapWidth/2 * r, bgy + tempY - Configs.GameMapHeight/2 * r, 1f * r, 1f * r, 255, 0, 0, 0);
////		//synge
////		Library2.drawImage(canvas, island_2, island_2_x * r + bgx + tempX - island_2.getWidth()/2*r, island_2_y*r + bgy+tempY-island_2.getHeight()/2*r, r, r, 255, 0, 0, 0,0,0,0);
//		
////		for (int i=0; i<bigmapelements.length; i++) {
////			
////		}
//		paintMapLevel(canvas, tempX, tempY);
//		
//		paintMapMenu(canvas);
//		
////		canvas.drawText("Android Map Menffffffddd", 100,100, paint);
//	}
//	//绘制关卡地图
//	private void paintMapLevel(Canvas canvas, float tempX, float tempY) {
//		//绘制路线点
//		for(int i=0; i<line_d.length; i++) {
//			int aphla = 255, R = 0,G = 0,B = 0;//argb暂用一个值来代替
//			if (VeggiesData.getGameGuanka()[i+1] <0) {
//				aphla = 133;
//				R = G = B = -255;
//			}
//			for (int j=0; j<line_d[i].length; j++) {	
//				//Library2.drawImage(canvas, select[line_d[i][j][2]-1], line_d[i][j][0] * r + bgx + tempX - select[line_d[i][j][2]-1].getWidth()/2*r, line_d[i][j][1]*r + bgy+tempY-select[line_d[i][j][2]-1].getHeight()/2*r, r, r, aphla,0,0,0, R,G,B);
//				select[line_d[i][j][2]-1].drawBitmap(canvas, line_d[i][j][0] * r + bgx + tempX - select[line_d[i][j][2]-1].bitmap.getWidth()/2*r, line_d[i][j][1]*r + bgy+tempY-select[line_d[i][j][2]-1].bitmap.getHeight()/2*r, r, r, aphla,0,0,0, R,G,B);
//			}			
//		}
//		//绘制关卡
//		for(int i=0; i<level_d.length; i++) {
//			float temp_x = level_d[i][0] * r + bgx + tempX - select[level_d[i][2]-1].bitmap.getWidth()/2*r * (anjian_level[i] ? 1.2f : 1.0f);//* (anjian_level[i] ? 1.2f : 1.0f)
//			float temp_y = level_d[i][1] * r + bgy + tempY - select[level_d[i][2]-1].bitmap.getHeight()/2*r * (anjian_level[i] ? 1.2f : 1.0f);
//			int aphla = 255, R = 0,G = 0,B = 0;//argb暂用一个值来代替
//			if (VeggiesData.getGameGuanka()[i] <0) {
//				aphla = 133;
//				R = G = B = -255;
//			}
//			//Library2.drawImage(canvas, select[level_d[i][2]-1], temp_x, temp_y, r * (anjian_level[i] ? 1.2f : 1.0f), r * (anjian_level[i] ? 1.2f : 1.0f), aphla,0,0,0, R,G,B);//133,-255,-255,-255
//			select[level_d[i][2]-1].drawBitmap(canvas, temp_x, temp_y, r * (anjian_level[i] ? 1.2f : 1.0f), r * (anjian_level[i] ? 1.2f : 1.0f), aphla,0,0,0, R,G,B);
//			if (i+1 < 10 ) {
//				temp_x = level_d[i][0] * r + bgx + tempX - select[level_d[i][2]-1].bitmap.getWidth()/2*r + select[level_d[i][2]-1].bitmap.getWidth()/2*r - maplevel_number[0].getWidth()/2*r * (anjian_level[i] ? 1.2f : 1.0f);					
//			} else {
//				temp_x = level_d[i][0] * r + bgx + tempX - select[level_d[i][2]-1].bitmap.getWidth()/2*r + select[level_d[i][2]-1].bitmap.getWidth()/2*r - maplevel_number[0].getWidth()*r * (anjian_level[i] ? 1.2f : 1.0f);
//			}
//			temp_y = level_d[i][1] * r + bgy + tempY - select[level_d[i][2]-1].bitmap.getHeight()/2*r + select[level_d[i][2]-1].bitmap.getHeight()/2*r - maplevel_number[0].getHeight()/2*r * (anjian_level[i] ? 1.2f : 1.0f);				
//			if (VeggiesData.getGameGuanka()[i] >= 0) {				
//				ExternalMethods.DrawNumber1(canvas, maplevel_number, (int)(temp_x), (int)(temp_y) , GameConfig.Char_num1, Integer.toString(i+1), null, 0 , r * (anjian_level[i] ? 1.2f : 1.0f));
//			}
//		}
//		//绘制当前关卡特效
//		int tempi = 0;
//		float tx = level_d[current_level][0] * r + bgx + tempX - currentLevel[tempi].getWidth()/2*r;
//		float ty = level_d[current_level][1] * r + bgy + tempY - currentLevel[tempi].getHeight()/2*r;
//		if (VeggiesData.getGameGuanka()[29] == 0) {
//			if (GameConfig.i_coke % 24 < 24) {
//				tempi = GameConfig.i_coke / 3 % 8; 
//				ExternalMethods.drawImage(canvas, currentLevel[tempi], tx, ty, r, r, 255, 0, 0, 0);
//			}			
//		}
//		//绘制星数
//		for (int i=0; i<level_d.length; i++) {
//			float temp_x = level_d[i][0] * r + bgx + tempX - select[level_d[i][2]-1].bitmap.getWidth()/2 * r + select[level_d[i][2]-1].bitmap.getWidth()/2 * r;
//			float temp_y = level_d[i][1] * r + bgy + tempY - select[level_d[i][2]-1].bitmap.getHeight()/2 * r + select[level_d[i][2]-1].bitmap.getHeight() * r - 10 * GameConfig.f_zoom * r;
//			switch(VeggiesData.getGameGuanka()[i]) {
//			case 0:
//				star[3].drawBitmap(canvas, temp_x - star[3].bitmap.getWidth()*1.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				star[3].drawBitmap(canvas, temp_x - star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				star[3].drawBitmap(canvas, temp_x + star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				break;
//			case 1:
//				star[0].drawBitmap(canvas, temp_x - star[3].bitmap.getWidth()*1.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				star[3].drawBitmap(canvas, temp_x - star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				star[3].drawBitmap(canvas, temp_x + star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);				
//				break;
//			case 2:
//				star[0].drawBitmap(canvas, temp_x - star[3].bitmap.getWidth()*1.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				star[1].drawBitmap(canvas, temp_x - star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				star[3].drawBitmap(canvas, temp_x + star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);				
//				break;
//			case 3:
//				star[0].drawBitmap(canvas, temp_x - star[3].bitmap.getWidth()*1.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				star[1].drawBitmap(canvas, temp_x - star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);
//				star[2].drawBitmap(canvas, temp_x + star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);				
//				break;
//			}
//		}
//		//绘制关键boss关卡和奖励关卡
//		if(VeggiesData.getGameGuanka()[14] >= 0) {
//			float temp_x = level_d[14][0] * r + bgx + tempX - select[level_d[14][2]-1].bitmap.getWidth()/2 * r + select[level_d[14][2]-1].bitmap.getWidth() * r - 10 * GameConfig.f_zoom * r;
//			float temp_y = level_d[14][1] * r + bgy + tempY - select[level_d[14][2]-1].bitmap.getHeight()/2 * r + select[level_d[14][2]-1].bitmap.getHeight()/2 * r - enemy_level[0].bitmap.getHeight() / 2 * r;
//			enemy_level[0].drawBitmap(canvas, temp_x, temp_y, r , r , 255,0,0,0);
//			
//		}
//		if(VeggiesData.getGameGuanka()[29] >= 0) {
//			float temp_x = level_d[29][0] * r + bgx + tempX - select[level_d[29][2]-1].bitmap.getWidth()/2 * r + select[level_d[29][2]-1].bitmap.getWidth() * r - 10 * GameConfig.f_zoom * r;
//			float temp_y = level_d[29][1] * r + bgy + tempY - select[level_d[29][2]-1].bitmap.getHeight()/2 * r + select[level_d[29][2]-1].bitmap.getHeight()/2 * r - enemy_level[1].bitmap.getHeight() / 2 * r;
//			enemy_level[1].drawBitmap(canvas, temp_x, temp_y, r , r , 255,0,0,0);
//		}
//		if(VeggiesData.getGameGuanka()[49] >= 0) {
//			float temp_x = level_d[49][0] * r + bgx + tempX - select[level_d[49][2]-1].bitmap.getWidth()/2 * r + select[level_d[49][2]-1].bitmap.getWidth() * r - 10 * GameConfig.f_zoom * r;
//			float temp_y = level_d[49][1] * r + bgy + tempY - select[level_d[49][2]-1].bitmap.getHeight()/2 * r + select[level_d[49][2]-1].bitmap.getHeight()/2 * r - enemy_level[2].bitmap.getHeight() / 2 * r;
//			enemy_level[2].drawBitmap(canvas, temp_x, temp_y, r , r , 255,0,0,0);
//		}
//		if(reward_level >= 0) {
//			float temp_x = level_d[reward_level][0] * r + bgx + tempX - select[level_d[reward_level][2]-1].bitmap.getWidth()/2 * r + select[level_d[reward_level][2]-1].bitmap.getWidth() * r - 10 * GameConfig.f_zoom * r;
//			float temp_y = level_d[reward_level][1] * r + bgy + tempY - select[level_d[reward_level][2]-1].bitmap.getHeight()/2 * r + select[level_d[reward_level][2]-1].bitmap.getHeight()/2 * r - enemy_level[3].bitmap.getHeight() / 2 * r;
//			enemy_level[3].drawBitmap(canvas, temp_x, temp_y, r , r , 255,0,0,0);
//		}
//		//绘制头像
//		if (current_level >= 0) {
//			float temp_x = level_d[current_level][0] * r + bgx + tempX - photo[0].bitmap.getWidth()/2*r;
//			float temp_y = level_d[current_level][1] * r + bgy + tempY - select[level_d[current_level][2]-1].bitmap.getHeight()/2 * r - photo[0].bitmap.getHeight()*r + 5*GameConfig.f_zoom*r;
//			if (friend>0) {
//				for (int i=1; i<4&&i<=friend; i++) {
//					photo[0].drawBitmap(canvas, temp_x + (i-1) * photo[0].bitmap.getWidth()/2*r, temp_y, r , r , 255,0,0,0);									
//				}
//			}
//			temp_x = level_d[current_level][0] * r + bgx + tempX - select[level_d[current_level][2]-1].bitmap.getWidth()/2 * r - photo[1].bitmap.getWidth()*r;
//			temp_y = level_d[current_level][1] * r + bgy + tempY - photo[1].bitmap.getHeight()/2*r;
//			photo[1].drawBitmap(canvas, temp_x, temp_y, r , r , 255,0,0,0);
//		}
//	}
//
//	private void paintBackGround(Canvas canvas, float tempX, float tempY) {
//		paintBackgroundColor(canvas, 
//				bg1_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				bg1_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//				bg1_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				bg1_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,0xffFAE375,false);
//		
//		paintBackgroundColor(canvas, 
//				bg2_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				bg2_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//				bg2_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				bg2_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,0xff25AEF2,false);
//		
//		coast1.drawBitmap(canvas, coast1x*r +bgx+ tempX - coast1.bitmap.getWidth()/2*r, coast1y*r +bgy+ tempY - coast1.bitmap.getHeight()/2*r, r, r, 255, 0, 0, 0);
//		coast2.drawBitmap(canvas, coast2x*r +bgx+ tempX - coast2.bitmap.getWidth()/2*r, coast2y*r +bgy+ tempY - coast2.bitmap.getHeight()/2*r, r, r, 255, 0, 0, 0);
//		canvas.save();
//		//先遮罩
//		canvas.clipRect(shade_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				shade_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//				shade_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				shade_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r);
//		
////		paintBackgroundColor(canvas, 
////				grassland1_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
////				grassland1_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
////				grassland1_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
////				grassland1_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,0xff65CC78,true);
////		paintBackgroundColor(canvas, 
////				grassland2_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
////				grassland2_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
////				grassland2_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
////				grassland2_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,0xff65CC78,true);
//		
//		for(int i=0; i<grassland_d.length;i++) {
//			paintBackgroundColor(canvas, 
//					grassland_d[i][0] * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//					grassland_d[i][1] * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//					grassland_d[i][2] * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//					grassland_d[i][3] * r + bgy + tempY - Configs.GameMapHeight/2 * r,grassland_d[i][4],true);
//		}
//		
//		if (VeggiesData.getGameGuanka()[29] == 0) {
//			if (hill_aphla != 255) {
//				hill_aphla = 255; 
//				hill_R = hill_G = hill_B = 0;
//			}
//		}
//		//火山
//		hill.drawBitmap(canvas, hill_x * r + bgx + tempX - hill.bitmap.getWidth()/2*r, hill_y*r + bgy+tempY-hill.bitmap.getHeight()/2*r, r, r, hill_aphla,0,0,0, hill_R,hill_G,hill_B);
//		
//		//火山烟
//		int tempi = 0;
//		if (VeggiesData.getGameGuanka()[29] == 0) {
//			if (GameConfig.i_coke % 50 < 32) {
//				tempi = GameConfig.i_coke % 50 / 4 % 4; 
//				ExternalMethods.drawImage(canvas, smoke[tempi],smoke_x * r + bgx + tempX - smoke[0].getWidth()/2*r, 
//						smoke_y*r + bgy+tempY-smoke[0].getHeight()/2*r, r, r, 255, 0, 0, 0);
//			}			
//		}
//		
//		//草地
//		for(int i=0; i<grass_d.length; i++) {
//			grass[grass_d[i][2]-1].drawBitmap(canvas, grass_d[i][0] * r + bgx + tempX - grass[grass_d[i][2]-1].bitmap.getWidth()/2*r, grass_d[i][1]*r + bgy+tempY-grass[grass_d[i][2]-1].bitmap.getHeight()/2*r, r, r, 255,0,0,0);
//		}
//		
//		//斑点
//		for(int i=0; i<spot_d.length; i++) {
//			spot[spot_d[i][2]-1].drawBitmap(canvas, spot_d[i][0] * r + bgx + tempX - spot[spot_d[i][2]-1].bitmap.getWidth()/2*r, spot_d[i][1]*r + bgy+tempY-spot[spot_d[i][2]-1].bitmap.getHeight()/2*r, r, r, 255,0,0,0);
//		}
//		//树
//		for(int i=0; i<tree_d.length; i++) {
//			tree[tree_d[i][2]-1].drawBitmap(canvas, tree_d[i][0] * r + bgx + tempX - tree[tree_d[i][2]-1].bitmap.getWidth()/2*r, tree_d[i][1]*r + bgy+tempY-tree[tree_d[i][2]-1].bitmap.getHeight()/2*r, r, r, 255,0,0,0);
//		}
//		
//		
//		canvas.restore();
//		//****************以下绘制海面背景****************
//		//鱼群
//		for(int i=0; i<fish_d.length; i++) {
//			switch(fish_d[i][2]) {
//			case 1:					
//				ExternalMethods.drawImage(canvas, fish[0], fish_d[i][0] * r + bgx + tempX - fish[0].getWidth()/2*r, fish_d[i][1]*r + bgy+tempY-fish[0].getHeight()/2*r, r, r, 255,0,0,0);
//				break;
//			case 2:
//				ExternalMethods.drawImage(canvas, fish[0], fish_d[i][0] * r + bgx + tempX - fish[0].getWidth()/2*r, fish_d[i][1]*r + bgy+tempY-fish[0].getHeight()/2*r, -r, -r, 255,0,0,0);
//				break;
//			}
//		}
//		//漩涡
//		tempi = 0;
//		if (GameConfig.i_coke % 24 < 24) {
//			tempi = GameConfig.i_coke % 24 / 8 % 3;
//			for (int i=0; i<vortex_d.length; i++) {
//				switch(vortex_d[i][2]) {
//				case 1:					
//					ExternalMethods.drawImage(canvas, vortex1[tempi],vortex_d[i][0] * r + bgx + tempX - vortex1[0].getWidth()/2*r, 
//							vortex_d[i][1]*r + bgy+tempY-vortex1[0].getHeight()/2*r, r, r, 255, 0, 0, 0);
//					break;
//				case 2:
//					ExternalMethods.drawImage(canvas, vortex2[tempi],vortex_d[i][0] * r + bgx + tempX - vortex2[0].getWidth()/2*r, 
//							vortex_d[i][1]*r + bgy+tempY-vortex2[0].getHeight()/2*r, r, r, 255, 0, 0, 0);					
//					break;
//				}
//				tempi = ++tempi%3;
//			}
//		}
//		//石头
//		for(int i=0; i<stone_d.length; i++) {
//			stone[stone_d[i][2]-1].drawBitmap(canvas, stone_d[i][0] * r + bgx + tempX - stone[stone_d[i][2]-1].bitmap.getWidth()/2*r, stone_d[i][1]*r + bgy+tempY-stone[stone_d[i][2]-1].bitmap.getHeight()/2*r, r, r, 255,0,0,0);
//		}
//		
//		//帆船
//		if (isboatDown) {
//			
//			if (GameConfig.i_coke % 8 < 1) {
//				boatH -= 2;
//				if (boatH <= 0) {
//					boatH = 0;
//					isboatDown = false;
//				}
//			}
//		} else {
//			if (GameConfig.i_coke % 8 < 1) {
//				boatH += 2;
//				if (boatH >= 2) {
//					boatH = 2;
//					isboatDown = true;
//				}				
//			}
//		}
//		boat.drawBitmap(canvas, boatx*r +bgx+ tempX - boat.bitmap.getWidth()/2*r, boaty*r +bgy+ tempY - boat.bitmap.getHeight()/2*r - boatH * GameConfig.f_zoom, r, r, 255, 0, 0, 0);
//		//宝箱
//		box.drawBitmap(canvas, box_x*r +bgx+ tempX - box.bitmap.getWidth()/2*r, box_y*r +bgy+ tempY - box.bitmap.getHeight()/2*r - boatH * GameConfig.f_zoom, r, r, 255, 0, 0, 0);
//		//小岛
//		for(int i=0; i<island_d.length; i++) {
//			island[island_d[i][2]-1].drawBitmap(canvas, island_d[i][0] * r + bgx + tempX - island[island_d[i][2]-1].bitmap.getWidth()/2*r, island_d[i][1]*r + bgy+tempY-island[island_d[i][2]-1].bitmap.getHeight()/2*r, r, r, 255,0,0,0);
//		}
//		//章鱼
//		tempi = 0;
//		if (GameConfig.i_coke % 32 < 32) {
//			tempi = GameConfig.i_coke % 32 / 4 % 4; 
//			if (tempi == 3) tempi = 1;
//			if (VeggiesData.getGameGuanka()[14] < 0) {
//				tempi = 0;
//			} else {
//				if (octopus_aphla != 255) {
//					octopus_aphla = 255; 
//					octopus_R = octopus_G = octopus_B = 0;
//				}
//			}
//			ExternalMethods.drawImage(canvas, octopus[tempi],octopus_x * r + bgx + tempX - octopus[0].getWidth()/2*r, 
//					octopus_y*r + bgy+tempY-octopus[0].getHeight()/2*r, r, r, octopus_aphla, 0, 0, 0, octopus_R, octopus_G, octopus_B);
//		}
//		//云层
//		canvas.save();
//		//先遮罩
//		canvas.clipRect(0 * 			r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				cloud_d[0][1]* 			r + bgy + tempY - Configs.GameMapHeight/2 * r,
//				Configs.GameMapWidth * 	r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				Configs.GameMapHeight* 	r + bgy + tempY - Configs.GameMapHeight/2 * r);
//		for(int i=0; i<cloud_d.length; i++) {
//			cloud[cloud_d[i][2]-1].drawBitmap(canvas, cloud_d[i][0] * r + bgx + tempX - cloud[cloud_d[i][2]-1].bitmap.getWidth()/2*r, cloud_d[i][1]*r + bgy+tempY-cloud[cloud_d[i][2]-1].bitmap.getHeight()/2*r, r, r, 255,0,0,0);
//		}
//		canvas.restore();
//	}
//	
//	//画椭圆草地、背景色
//	private void paintBackgroundColor(Canvas canvas, float x1, float y1, float x2, float y2, int argb, boolean isOval) {
//		Paint p = new Paint();
//		RectF oval2 = new RectF(x1, y1, x2, y2);
//        p.setColor(argb);
//        if (isOval)
//        	canvas.drawOval(oval2, p);
//        else
//        	canvas.drawRect(oval2, p);
//	}
//	
//	//大选关菜单界面
//	private void paintMapMenu(Canvas canvas) {
//		statebg.drawBitmap(canvas, Location.BigMapMenu.goldGrayFloor_x * GameConfig.f_zoom, Location.BigMapMenu.goldGrayFloor_y *GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		statebg.drawBitmap(canvas, Location.BigMapMenu.gemGrayFloor_x * GameConfig.f_zoom, Location.BigMapMenu.gemGrayFloor_y *GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		statebg.drawBitmap(canvas, Location.BigMapMenu.heartGrayFloor_x * GameConfig.f_zoom, Location.BigMapMenu.heartGrayFloor_y *GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		
//		gold.drawBitmap(canvas, Location.BigMapMenu.gold_x * GameConfig.f_zoom, Location.BigMapMenu.gold_y * GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		gem.drawBitmap(canvas, Location.BigMapMenu.gem_x * GameConfig.f_zoom, Location.BigMapMenu.gem_y * GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		heart.drawBitmap(canvas, Location.BigMapMenu.heart_x * GameConfig.f_zoom, Location.BigMapMenu.heart_y * GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		
////		Library2.drawImage(canvas, rechange, (Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom + rechange.getWidth()/2) - (rechange.getWidth()/2 * (anjiangold ? 1.2f:1.0f)), (Location.BigMapMenu.goldRechange_y * GameConfig.f_zoom + rechange.getHeight()/2) - (rechange.getHeight()/2 * (anjiangold?1.2f:1.0f)), anjiangold?1.2f:1f, anjiangold?1.2f:1f, 255, 0, 0, 0);
//		rechange.drawBitmap(canvas, Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * (anjiangold ? 0.2f:0f)), Location.BigMapMenu.goldRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * (anjiangold?0.2f:0f)), anjiangold?1.2f:1f, anjiangold?1.2f:1f, 255, 0, 0, 0);
//		rechange.drawBitmap(canvas, Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * (anjiangem ? 0.2f:0f)), Location.BigMapMenu.gemRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * (anjiangem?0.2f:0f)), anjiangem?1.2f:1f, anjiangem?1.2f:1f, 255, 0, 0, 0);
//		rechange.drawBitmap(canvas, Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * (anjianheart ? 0.2f:0f)), Location.BigMapMenu.heartRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * (anjianheart?0.2f:0f)), anjianheart?1.2f:1f, anjianheart?1.2f:1f, 255, 0, 0, 0);
//		
//		statebg.drawBitmap(canvas, Location.BigMapMenu.storeGrayFloor_x, Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		storeWord.drawBitmap(canvas, Location.BigMapMenu.storeWord_x, Location.BigMapMenu.storeWord_y * GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		store.drawBitmap(canvas, Location.BigMapMenu.store_x, Location.BigMapMenu.store_y * GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		
//		mail.drawBitmap(canvas, Location.BigMapMenu.mail_x, Location.BigMapMenu.mail_y * GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		mailred.drawBitmap(canvas, Location.BigMapMenu.mailRedFloor_x, Location.BigMapMenu.mailRedFloor_y * GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);
//		
//		//农场上箭头动画
//		if (isfarmArrowDown) {
//			farmArrowH -= Math.max(2, 0);
//			if (farmArrowH <= 0) {
//				farmArrowH = 0;
//				isfarmArrowDown = false;
//			}
//		} else {
//			farmArrowH += Math.min(2, 15);
//			if (farmArrowH >= 15) {
//				farmArrowH = 15;
//				isfarmArrowDown = true;
//			}
//		}
//		farm.drawBitmap(canvas, Location.BigMapMenu.farm_x, Location.BigMapMenu.farm_y, 1f, 1f, 255, 0, 0, 0);
//		farmArrow.drawBitmap(canvas, Location.BigMapMenu.farmArrow_x, Location.BigMapMenu.farmArrow_y-farmArrowH*GameConfig.f_zoom, 1f, 1f, 255, 0, 0, 0);		
//	
//		ExternalMethods.DrawNumber1(canvas, mapmenu_number,	(int)(Location.BigMapMenu.gold_num_x * GameConfig.f_zoom + mapmenu_number[0].getWidth() * (Configs.GoldMaxLength-gold_str.length())), (int)(Location.BigMapMenu.gold_num_y * GameConfig.f_zoom), GameConfig.Char_num1, gold_str, null, 1,1);
//		ExternalMethods.DrawNumber1(canvas, mapmenu_number,	(int)(Location.BigMapMenu.gem_num_x * GameConfig.f_zoom + mapmenu_number[0].getWidth() * (Configs.GoldMaxLength-gem_str.length())), (int)(Location.BigMapMenu.gem_num_y * GameConfig.f_zoom), GameConfig.Char_num1, gem_str, null, 1,1);
//		ExternalMethods.DrawNumber1(canvas, mapmenu_heart_number, (int)(Location.BigMapMenu.heart_num_x * GameConfig.f_zoom ), (int)(Location.BigMapMenu.heart_num_y * GameConfig.f_zoom), GameConfig.Char_num1, heart_str, null, 1,1);
//		ExternalMethods.DrawNumber1(canvas, mapmenu_number, (int)(Location.BigMapMenu.heart_time_x * GameConfig.f_zoom ), (int)(Location.BigMapMenu.heart_time_y * GameConfig.f_zoom), GameConfig.Char_num2, time_str, null, 0,1);
//		ExternalMethods.DrawNumber1(canvas, mapmenu_number, (int)(Location.BigMapMenu.mail_num_x), (int)(Location.BigMapMenu.mail_num_y * GameConfig.f_zoom), GameConfig.Char_num1, mail_str, null, -2,1);
//	}
//
//	public void run() {
//		
//		if (isTwoTouchMoveZoomBig) {
//			if (r < max_r){
//        		r += 0.05;
//        		getrrr(0.05f*Configs.GameMapWidth,0.05f*Configs.GameMapHeight,sizew,sizeh);
//        	}
//		}
//		if (isTwoTouchMoveZoomSmall) {
//			if ( (r > min_r) ){
//        		r -= 0.05;
//        		getrrr(-0.05f*Configs.GameMapWidth,-0.05f*Configs.GameMapHeight,sizew,sizeh);
//            	
//        	}
//		}
//		
//		int ttt=(int)(50*GameConfig.f_zoom);
//		int tempx=(int)(bgx+move_X - Configs.GameMapWidth/2 * r);
//		int tempy=(int)(bgy+move_Y - Configs.GameMapHeight/2 * r);
//		//纠正靠边缩放地图的位置
//		correctMapZoom(tempx, tempy, ttt);
//		
//		tempx=(int)(bgx + move_X- Configs.GameMapWidth/2 * r);
//		tempy=(int)(bgy + move_Y- Configs.GameMapHeight/2 * r);
//		ttt=(int)(15*GameConfig.f_zoom);
//		//纠正靠边移动地图的位置
//		if (isOneTouchMove) {
//			correctMapMove(tempx, tempy, ttt);			
//		}
//	}
//
//	public boolean onKeyDown(int keyCode, KeyEvent msg) {
//		
//		return false;
//	}
//
//	public boolean onKeyUp(int keyCode, KeyEvent msg) {
//		if(keyCode == KeyEvent.KEYCODE_BACK) {
//			MainActivity.getActivity().closeGame();
//		}
//		return false;
//	}
//
//	public void initwordpic() {
//		
//	}
//	
//	public void getrrr(float addW,float addH,float sizew,float sizeh) {
////		System.out.println("**************");
////		System.out.println("clpqy:addW="+addW);
////		System.out.println("clpqy:addH="+addH);
////		System.out.println("clpqy:sizew="+sizew);
////		System.out.println("clpqy:sizeh="+sizeh);
//		
//		bgx += addW*(0.5f-sizew);
//		bgy += addH*(0.5f-sizeh);
//	}
//	
//	int oldDist;
//	float sizew=-1,sizeh=-1;
//	public void onTouchEvent(MotionEvent event) {
//		switch(event.getActionMasked())									//me.getActionMasked()  获取当前动作类型	
//		{
//			case MotionEvent.ACTION_DOWN:								//如果是第一个触点被按下是执行
//				startX_1 = event.getX(event.getActionIndex());			//获取第一个触点的 X 坐标
//				startY_1 = event.getY(event.getActionIndex());		//获取第一个触点的 Y 坐标
//				if (Configs.isDebug)
//				System.out.println("第一点被按下" + event.getActionIndex());	//这个是我定义的方法，用于设置文本框显示信息
//				
//				mode1 = Configs.ONETOUCH;
//				
//				oldX = (int) startX_1;
//				oldY = (int) startY_1;
//				if (ExternalMethods.CollisionTest(startX_1, startY_1, 
//						Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//						Location.BigMapMenu.goldRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f), 
//						Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f) + rechange.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.goldRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f) + rechange.bitmap.getHeight()/2 * 1.2f)) {
//					anjiangold = true;
//				} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
//						Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//						Location.BigMapMenu.gemRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f), 
//						Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f) + rechange.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.gemRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f) + rechange.bitmap.getHeight()/2 * 1.2f)) {
//					anjiangem = true;
//				} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
//						Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f), 
//						Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f) + rechange.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f) + rechange.bitmap.getHeight()/2 * 1.2f)) {
//					anjianheart = true;
//				} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
//						Location.BigMapMenu.farmArrow_x, 
//						Location.BigMapMenu.farmArrow_y-15*GameConfig.f_zoom, 
//						GameConfig.GameScreen_Width, 
//						GameConfig.GameScreen_Height)) {
//					anjianfarm = true;
//				} else if (ExternalMethods.CollisionTest(startX_1, startY_1,
//						Location.BigMapMenu.storeGrayFloor_x, 
//						Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
//						GameConfig.GameScreen_Width, 
//						Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom + statebg.bitmap.getHeight())) {
//					anjianstore = true;
//				} else if (ExternalMethods.CollisionTest(startX_1, startY_1,
//						Location.BigMapMenu.mail_x, 
//						Location.BigMapMenu.mail_y * GameConfig.f_zoom, 
//						GameConfig.GameScreen_Width,  
//						Location.BigMapMenu.mail_y * GameConfig.f_zoom + mail.bitmap.getHeight())) {
//					anjianmail = true;
//				} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 0, 0,
//						Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f) + rechange.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f) + rechange.bitmap.getHeight()/2 * 1.2f)) {
//					//修正左上角触碰无响应
//					//sendMessage("无效触碰区域");
//					//GameManager.forbidModule(new GameLevelInfoModule());
//					return;
//				} else {
//					for(int i=0; i<level_d.length; i++) {
//						float temp_x = level_d[i][0] * r + bgx + move_X - select[level_d[i][2]-1].bitmap.getWidth()/2*r * 1.2f;
//						float temp_y = level_d[i][1] * r + bgy + move_Y - select[level_d[i][2]-1].bitmap.getHeight()/2*r * 1.2f;
//						if (ExternalMethods.CollisionTest(startX_1, startY_1, 
//							temp_x, temp_y,
//							temp_x + select[level_d[i][2]-1].bitmap.getWidth() * r * 1.2f,
//							temp_y + select[level_d[i][2]-1].bitmap.getHeight() * r * 1.2f)) {
//							if (VeggiesData.getGameGuanka()[i] >= 0) {
//								anjian_level[i] = true;								
//							}
//						}
//					}
//				}
//				break;
//			case MotionEvent.ACTION_UP:				//如果第一个触点弹起时被触发(手指离开屏幕时)
//				startX_1 = event.getX(event.getActionIndex());
//				startY_1 = event.getY(event.getActionIndex());
////				bgx+=move_X;
////				bgy+=move_Y;
////				move_X=0;
////				move_Y=0;
//				if (Configs.isDebug)
//				System.out.println("第一点被抬起" + event.getActionIndex());
//				mode1 = Configs.NONE;
//				if (Configs.isDebug) {
//					System.out.println(">>>>>>>x="+startX_1+",y="+startY_1);
//					System.out.println(">>>>>>>x1="+(Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f))+",y1="+(Location.BigMapMenu.goldRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f)));
//					System.out.println(">>>>>>>x2="+(Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f)+ rechange.bitmap.getWidth()/2 * 1.2f)+",y2="+(Location.BigMapMenu.goldRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f)+ rechange.bitmap.getHeight()/2 * 1.2f));					
//				}
//				if (anjiangold && ExternalMethods.CollisionTest(startX_1, startY_1, 
//						Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//						Location.BigMapMenu.goldRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f), 
//						Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f) + rechange.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.goldRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f) + rechange.bitmap.getHeight()/2 * 1.2f)) {
//					sendMessage("金币充值被点击了");
//				} else if (anjiangem && ExternalMethods.CollisionTest(startX_1, startY_1, 
//						Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//						Location.BigMapMenu.gemRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f), 
//						Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f) + rechange.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.gemRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f) + rechange.bitmap.getHeight()/2 * 1.2f)) {					
//					sendMessage("宝石充值被点击了");
//				} else if (anjianheart && ExternalMethods.CollisionTest(startX_1, startY_1, 
//						Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f), 
//						Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f) + rechange.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f) + rechange.bitmap.getHeight()/2 * 1.2f)) {
//					sendMessage("心充值被点击了");
//					GameManager.forbidModule(new HeartRechargeModule());
//				} else if (anjianfarm && ExternalMethods.CollisionTest(startX_1, startY_1, 
//						Location.BigMapMenu.farmArrow_x, 
//						Location.BigMapMenu.farmArrow_y-15*GameConfig.f_zoom, 
//						GameConfig.GameScreen_Width, 
//						GameConfig.GameScreen_Height)) {
//					sendMessage("农场被点击了");
//					GameManager.forbidModule(new FarmModule());
//				} else if (anjianstore && ExternalMethods.CollisionTest(startX_1, startY_1,
//						Location.BigMapMenu.storeGrayFloor_x, 
//						Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
//						GameConfig.GameScreen_Width, 
//						Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom + statebg.bitmap.getHeight())) {
//					sendMessage("商店被点击了");
//					GameManager.forbidModule(new GameShop());
//				} else if (anjianmail && ExternalMethods.CollisionTest(startX_1, startY_1,
//						Location.BigMapMenu.mail_x, 
//						Location.BigMapMenu.mail_y * GameConfig.f_zoom, 
//						GameConfig.GameScreen_Width,  
//						Location.BigMapMenu.mail_y * GameConfig.f_zoom + mail.bitmap.getHeight())) {
//					sendMessage("邮件被点击了");
//					GameManager.forbidModule(new MessageModule());
//				} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 0, 0,
//						Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f) + rechange.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoom - (rechange.bitmap.getHeight()/2 * 0.2f) + rechange.bitmap.getHeight()/2 * 1.2f)) {
//					return;
//				} else {
//					for(int i=0; i<level_d.length; i++) {
//						float temp_x = level_d[i][0] * r + bgx + move_X - select[level_d[i][2]-1].bitmap.getWidth()/2*r * 1.2f;
//						float temp_y = level_d[i][1] * r + bgy + move_Y - select[level_d[i][2]-1].bitmap.getHeight()/2*r * 1.2f;
//						if (anjian_level[i] && ExternalMethods.CollisionTest(startX_1, startY_1, 
//							temp_x, temp_y,
//							temp_x + select[level_d[i][2]-1].bitmap.getWidth() * r * 1.2f,
//							temp_y + select[level_d[i][2]-1].bitmap.getHeight() * r * 1.2f)) {
//							sendMessage("第 " + (i+1) + "关被点击~");
//							VeggiesData.setCurrentLevel(i+1);
////							if (VeggiesData.getCurrentLevel() == 1) {
////								FriendScore.isFaceBookLoginSuccess = true;
////							} else {								
////								FriendScore.isFaceBookLoginSuccess = false;
////							}
//							VeggiesData.setCurrentLevel(i);
//							GameManager.forbidModule(levelInfoModule);
//						}
//					}
//				}
//				isOneTouchMove = true;
//				cleanAnJianState();
//				for (int i=0; i<anjian_level.length; i++) {
//					anjian_level[i] = false;
//				}
//				break;
//			case MotionEvent.ACTION_POINTER_DOWN:   		//除第一个触点外，其他触点被按下时该方法被触发。
//				startX_2 = event.getX(event.getActionIndex());    //获取第二个触点的坐标
//				startY_2 = event.getY(event.getActionIndex()); 	//获取第二个触点的坐标
//				if (Configs.isDebug)
//				System.out.println("第二个点被按下~~~~~");
//
//				setInfo(event,"ACTION_POINTER_DOWN  ", event.getActionIndex());
//				
//				oldDist = (int) spacing(event);  
//                if (oldDist > 10f)  
//                {  
//                    mode1 = Configs.TWOTOUCH;
//                    int tempx=(int)(Math.min(event.getX(0), event.getX(1))+(Math.max(event.getX(0), event.getX(1))-Math.min(event.getX(0), event.getX(1)))/2);
//                    int tempy=(int)(Math.min(event.getY(0), event.getY(1))+(Math.max(event.getY(0), event.getY(1))-Math.min(event.getY(0), event.getY(1)))/2);
//                    sizew=(tempx-(bgx+move_X - Configs.GameMapWidth*r/2))/(float)(Configs.GameMapWidth*r);
//                    sizeh=(tempy-(bgy +move_Y- Configs.GameMapHeight*r/2))/(float)(Configs.GameMapHeight*r);
//                }
//                
//                cleanAnJianState();
//                for (int i=0; i<anjian_level.length; i++) {
//        			anjian_level[i] = false;
//        		}
//				break;
//			case MotionEvent.ACTION_POINTER_UP:  			//除第一个触点外，其他触点弹起时该方法被触发。
//				startX_2 = event.getX(event.getActionIndex());
//				startY_2 = event.getY(event.getActionIndex());
//				if (Configs.isDebug)
//				System.out.println("第二点被抬起~~~~~");
//				
//				mode1 = Configs.NONE;
//				isTwoTouchMoveZoomBig = false;
//            	isTwoTouchMoveZoomSmall = false;
//				cleanAnJianState();
//				for (int i=0; i<anjian_level.length; i++) {
//					anjian_level[i] = false;
//				}
//				break;
//			case MotionEvent.ACTION_MOVE:  					//当任意一个触点在屏幕移动时被触发
//				if (Configs.isDebug) {
//					System.out.println("第一点x="+event.getX(0)+",第一点y="+event.getY(0));
//					System.out.println("第二点x="+event.getX(1)+",第二点y="+event.getY(1));
//					System.out.println("move~~~~~~~~~");					
//				}
//				/**
//				 * 获取触控点无论是单触控还是2点触控。event.getX(0)和event.getX(1)都会有数据。
//				 * 需要通过点击的时候判断到底是1点触控还是2点触控
//				 */
//				//if (1和2都按下的时候)
//				if (mode1 == Configs.TWOTOUCH) {
//					// 正在移动的点距初始点的距离   
//                    float newDist = spacing(event);
////                    int tempx=(int)(Math.min(event.getX(0), event.getX(1))+(Math.max(event.getX(0), event.getX(1))-Math.min(event.getX(0), event.getX(1)))/2);
////                    int tempy=(int)(Math.min(event.getY(0), event.getY(1))+(Math.max(event.getY(0), event.getY(1))-Math.min(event.getY(0), event.getY(1)))/2);
//                    if (newDist > oldDist)  
//                    { 
////                    		System.out.println("clpqy:tempx="+tempx+",bgx="+bgx+",Configs.GameMapWidth="+Configs.GameMapWidth+",r+"+r);
////                    		System.out.println("clpqy:tempy="+tempy+",bgy="+bgy+",Configs.GameMapHeight="+Configs.GameMapHeight+",r+"+r);
//                    	isTwoTouchMoveZoomBig = true;
//                    	isTwoTouchMoveZoomSmall = false;
//                    	
//                    } 
//                    if (newDist < oldDist)  
//                    {  
//                    	isTwoTouchMoveZoomBig = false;
//                    	isTwoTouchMoveZoomSmall = true;      
//                    	
//                    }
//                    
//				}
//				//if (1按下的时候)
//				if (mode1 == Configs.ONETOUCH) {
//					isOneTouchMove = false;
//					int tempX = (int) event.getX();
//					int tempY = (int) event.getY();
//					
//					move_X+=tempX-oldX;
//					move_Y+=tempY-oldY;
//					
//					oldX = (int) event.getX();
//					oldY = (int) event.getY();
//					
//					if (tempX - startX_1 > 5 * GameConfig.f_zoom || tempY - startY_1 > 5 * GameConfig.f_zoom) {
//						//三星s3机型点击的问题	(按键事件down->move->up)
//						for (int i = 0; i < anjian_level.length; i++) {
//							anjian_level[i] = false;
//						}
//					}
//					
//					int ttt=(int)(50*GameConfig.f_zoom);
//					int tempx=(int)(bgx+move_X - Configs.GameMapWidth/2 * r);
//					int tempy=(int)(bgy+move_Y - Configs.GameMapHeight/2 * r);
//					if(tempx>ttt){
////						move_X-=tempx-ttt;
//						move_X=ttt-bgx+Configs.GameMapWidth/2*r;
//					}else if(tempx<GameConfig.GameScreen_Width-Configs.GameMapWidth*r-ttt){
////						move_X+=(GameConfig.GameScreen_Width-Configs.GameMapWidth*r)-tempx-ttt;
//						move_X = GameConfig.GameScreen_Width-Configs.GameMapWidth*r-ttt-bgx+ Configs.GameMapWidth/2*r;
//					}
//					
//					if(tempy>ttt){
////						move_Y-=tempy-ttt;
//						move_Y=ttt+Configs.GameMapHeight/2 * r-bgy;
//					}else if(tempy<GameConfig.GameScreen_Height-Configs.GameMapHeight*r-ttt){
////						move_Y+=(GameConfig.GameScreen_Height-Configs.GameMapHeight*r)-tempy-ttt;
//						move_Y =GameConfig.GameScreen_Height-Configs.GameMapHeight*r-ttt-bgy+ Configs.GameMapHeight/2 * r;
//					}
//				}
//				
//				cleanAnJianState();
//				break;
//		}
//	}
//	public void setInfo(MotionEvent m,String Identify, int index)
//	{
//		//System.out.println(Identify + index + " ******* " + m.getPointerCount() + "    Time:" + a++ + "\n\n" + "StartX:" + startX_1    + "      CurrentX:" + m.getX(0) + "\n" + "StartY:" + startY_1 + "     CurrentY" + m.getY(0));
//		//if(m.getPointerCount() == 2)  							//当有两个触点时才设置第二个触点信息框的信息
//		//System.out.println(Identify + index + " ******* " + m.getPointerCount() + "    Time:" + a++ + "\n\n" + "StartX:" + startX_2    + "      CurrentX:" + m.getX(1) + "\n" + "StartY:" + startY_2 + "     CurrentY" + m.getY(1));
//	}
//	
//	/** 
//     * 求出2个触点间的 距离 
//     *  
//     * @param event 
//     * @return 
//     */  
//    private float spacing(MotionEvent event)  
//    {  
//        float x = event.getX(0) - event.getX(1);  
//        float y = event.getY(0) - event.getY(1);  
//        return FloatMath.sqrt(x * x + y * y);  
//    }  
//    
//    /**
//     * 纠正大题图缩放
//     * @param tempx
//     * @param tempy
//     * @param ttt
//     */
//    private void correctMapZoom(int tempx, int tempy, int ttt) {
//    	if(tempx>ttt){
//			move_X-=tempx-ttt;
//		}else if(tempx<GameConfig.GameScreen_Width-Configs.GameMapWidth*r-ttt){
//			move_X+=(GameConfig.GameScreen_Width-Configs.GameMapWidth*r)-tempx-ttt;
//		}
//		
//		if(tempy>ttt){
//			move_Y-=tempy-ttt;
//		}else if(tempy<GameConfig.GameScreen_Height-Configs.GameMapHeight*r-ttt){
//			move_Y+=(GameConfig.GameScreen_Height-Configs.GameMapHeight*r)-tempy-ttt;
//		}
//    }
//    /**
//     * 纠正地图靠边移动
//     * @param tempx
//     * @param tempy
//     * @param ttt
//     */
//    private void correctMapMove(int tempx, int tempy, int ttt) {
//    	if(tempx>0){
//			move_X-=Math.min(tempx, ttt);
//		}else if(tempx<GameConfig.GameScreen_Width-Configs.GameMapWidth*r){
//			move_X+=Math.min(ttt,(GameConfig.GameScreen_Width-Configs.GameMapWidth*r)-tempx);
//		}
//		
//		if(tempy>0){
//			move_Y-=Math.min(tempy, ttt);
//		}else if(tempy<GameConfig.GameScreen_Height-Configs.GameMapHeight*r){
//			move_Y+=Math.min(ttt,(GameConfig.GameScreen_Height-Configs.GameMapHeight*r)-tempy);
//		}
//    }
//    
//    //清理按键状态
//    private void cleanAnJianState() {
//    	anjiangold = false;
//		anjiangem = false;
//		anjianheart = false;
//		anjianfarm = false;
//		anjianstore = false;
//		anjianmail = false;
////		for (int i=0; i<anjian_level.length; i++) {
////			anjian_level[i] = false;
////		}
//    }
//    
//    public static void sendMessage(String str) {
//    	Message msg = new Message();
//		msg.what = 1;
//		msg.obj = str;
//		Main.dialogHandler.sendMessage(msg);
//    }
//}
