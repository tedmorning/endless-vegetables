//package com.endlessvegetables2.ui;
//
//import java.math.BigDecimal;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;
//import android.os.Message;
//import android.util.FloatMath;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
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
//public class ChooseLevelModule1 extends Module{
//	private Sprite clmSprite;	//场景用背景色
//	
//	//--------------地图菜单--------------
////	private Sprite bg;
////	private Sprite kuang3;
////	private Sprite kuang4;
////	private Sprite statebg;
////	private Sprite gold;
////	private Sprite gem;
////	private Sprite heart;
////	private Sprite rechange;
////	private Sprite storeWord;
////	private Sprite store;
////	private Sprite mail;
////	private Sprite mailred;
////	private Sprite farm;
////	private Sprite farmArrow;
////	private Bitmap[] mapmenu_number;
////	private Bitmap[] mapmenu_heart_number;
////	private Bitmap[] octopus;
////	private Bitmap[] smoke;
////	private Sprite[] dot;
////	private Sprite[] button;
////	private Bitmap[] maplevel_number;
////	private Sprite[] star;
////	private Sprite ren_1;
////	private Sprite ren_2;
////	private Sprite enemy_01;
////	private Sprite enemy_02;
////	private Sprite enemy_04;
//	
////	private Sprite coast;
////	private Sprite green_2;
////	private Sprite green_1;
////	private Sprite yellow_2;
////	private Sprite dune;
////	private Sprite castle;
////	private Sprite stump;
////	private Sprite shell;
////	private Sprite hill_1;
////	private Sprite hill_2;
////	private Sprite stone_2;
////	private Sprite tree_1;
////	private Sprite tree_2;
////	private Sprite grass;
////	private Sprite flower;
////	private Sprite seastar;
////	private Sprite fish;
////	private Sprite bubble;
////	private Sprite water_wave;
////	private Sprite vortex;
////	private Sprite stone_1;
////	private Sprite island_1;
////	private Sprite island_2;
////	private Sprite ship_2;
////	private Sprite ship_1;
////	private Sprite cloud;
//	
//	private boolean anjiangold;
//	private boolean anjiangem;
//	private boolean anjianheart;
//	private boolean anjianstore;
//	private boolean anjianmail;
//	private boolean anjianfarm;
//	private boolean[] anjian_level;
//	
//	private boolean isfarmArrowDown;
//	private boolean isTwoTouchMoveZoomBig;
//	private boolean isTwoTouchMoveZoomSmall;
//	private boolean isOneTouchMove;
//	
//	private boolean isboatDown;
//	
//	private float startX_1 = 0;			//记录第一个触点开始 X 坐标
//	private float startY_1 = 0;			//记录第一个触点开始 Y 坐标
//	private float startX_2 = 0;			//记录第二个触点开始 X 坐标
//	private float startY_2 = 0;			//记录第二个触点开始 Y 坐标
//	private float move_X,move_Y;
//	private float bgx,bgy;
//	private float coast_x,coast_y;
//	private float stump_x,stump_y;
//	private float shell_x,shell_y;
//	private float hill_1_x,hill_1_y;
//	private float hill_2_x,hill_2_y;
//	private float sea_tree_2_x,sea_tree_2_y;
//	private float sea_grass_x,sea_grass_y;
//	private float ship_1_x,ship_1_y;
//	private float octopus_x,octopus_y;
//	private float smoke_x,smoke_y;
//	private int bg1_1x,bg1_1y,bg1_2x,bg1_2y;
//	private int bg2_1x,bg2_1y,bg2_2x,bg2_2y;
//	private int oldX,oldY;
//	private float r;
//	private float boatH;
//	private int reward_level;
//	
//	private int octopus_aphla = 255, octopus_R = 0,octopus_G = 0,octopus_B = 0;//argb暂用一个值来代替
//	private float octopus_r;
//	private float smoke_r;
//	
//	int friend = 3;
//	private int farmArrowH;
//	int mode1,mode2;
//	
////	private String gold_str;
////	private String gem_str;
////	private String heart_str;
//	private String time_str;
//	private String mail_str;
//	
//	
//	
//	private float[][] green_2_s = 
//	{
//			{Location.BigMap1.green_2_1_x, Location.BigMap1.green_2_1_y, 1.5f},
//			{Location.BigMap1.green_2_2_x, Location.BigMap1.green_2_2_y, 1.0f},
//			{Location.BigMap1.green_2_3_x, Location.BigMap1.green_2_3_y, 1.0f},
//			{Location.BigMap1.green_2_4_x, Location.BigMap1.green_2_4_y, 1.0f},
//	};
//	private float[][] green_2_d;
//	private float[][] green_1_s = 
//	{
//			{Location.BigMap1.green_1_1_x, Location.BigMap1.green_1_1_y},
//			{Location.BigMap1.green_1_2_x, Location.BigMap1.green_1_2_y},
//			{Location.BigMap1.green_1_3_x, Location.BigMap1.green_1_3_y},
//			{Location.BigMap1.green_1_4_x, Location.BigMap1.green_1_4_y},
//			{Location.BigMap1.green_1_5_x, Location.BigMap1.green_1_5_y},
//	};
//	private float[][] green_1_d;
//	private float[][] yellow_2_s = 
//	{
//			{Location.BigMap1.yellow_2_1_x, Location.BigMap1.yellow_2_1_y, 1.0f},
//			{Location.BigMap1.yellow_2_2_x, Location.BigMap1.yellow_2_2_y, 1.0f},
//			{Location.BigMap1.yellow_2_3_x, Location.BigMap1.yellow_2_3_y, 1.5f},
//	};
//	private float[][] yellow_2_d;
//	private float[][] dune_s = 
//	{
//			{Location.BigMap1.dune_1_x, Location.BigMap1.dune_1_y},
//			{Location.BigMap1.dune_2_x, Location.BigMap1.dune_2_y},
//			{Location.BigMap1.dune_3_x, Location.BigMap1.dune_3_y},
//			{Location.BigMap1.dune_4_x, Location.BigMap1.dune_4_y},
//			{Location.BigMap1.dune_5_x, Location.BigMap1.dune_5_y},
//			{Location.BigMap1.dune_6_x, Location.BigMap1.dune_6_y},
//			{Location.BigMap1.dune_7_x, Location.BigMap1.dune_7_y},
//			{Location.BigMap1.dune_8_x, Location.BigMap1.dune_8_y},
//			{Location.BigMap1.dune_9_x, Location.BigMap1.dune_9_y},
//			{Location.BigMap1.dune_10_x, Location.BigMap1.dune_10_y},
//			{Location.BigMap1.dune_11_x, Location.BigMap1.dune_11_y},
//			{Location.BigMap1.dune_12_x, Location.BigMap1.dune_12_y},
//			{Location.BigMap1.dune_13_x, Location.BigMap1.dune_13_y},
//			{Location.BigMap1.dune_14_x, Location.BigMap1.dune_14_y},
//			{Location.BigMap1.dune_15_x, Location.BigMap1.dune_15_y},
//	};
//	private float[][] dune_d;
//	private float[][] castle_s = 
//	{
//			{Location.BigMap1.castle_1_x, Location.BigMap1.castle_1_y},
//			{Location.BigMap1.castle_2_x, Location.BigMap1.castle_2_y},
//			{Location.BigMap1.castle_3_x, Location.BigMap1.castle_3_y},
//	};
//	private float[][] castle_d;
//	private float[][] hill_2_s = 
//	{
//			{Location.BigMap1.hill_2_1_x, Location.BigMap1.hill_2_1_y, 1.0f},		
//			{Location.BigMap1.hill_2_2_x, Location.BigMap1.hill_2_2_y, 1.0f},		
//			{Location.BigMap1.hill_2_3_x, Location.BigMap1.hill_2_3_y, 116/144f},		
//			{Location.BigMap1.hill_2_4_x, Location.BigMap1.hill_2_4_y, 116/144f},		
//	};
//	private float[][] hill_2_d;
//	private float[][] stone_2_s = 
//	{
//			{Location.BigMap1.stone_2_1_x, Location.BigMap1.stone_2_1_y},
//			{Location.BigMap1.stone_2_2_x, Location.BigMap1.stone_2_2_y},
//			{Location.BigMap1.stone_2_3_x, Location.BigMap1.stone_2_3_y},
//			{Location.BigMap1.stone_2_4_x, Location.BigMap1.stone_2_4_y},
//			{Location.BigMap1.stone_2_5_x, Location.BigMap1.stone_2_5_y},
//	};
//	private float[][] stone_2_d;
//	private float[][] tree_1_s = 
//	{
//			{Location.BigMap1.tree_1_1_x, Location.BigMap1.tree_1_1_y},
//			{Location.BigMap1.tree_1_2_x, Location.BigMap1.tree_1_2_y},
//			{Location.BigMap1.tree_1_3_x, Location.BigMap1.tree_1_3_y},
//			{Location.BigMap1.tree_1_4_x, Location.BigMap1.tree_1_4_y},
//			{Location.BigMap1.tree_1_5_x, Location.BigMap1.tree_1_5_y},
//			{Location.BigMap1.tree_1_6_x, Location.BigMap1.tree_1_6_y},
//			{Location.BigMap1.tree_1_7_x, Location.BigMap1.tree_1_7_y},
//			{Location.BigMap1.tree_1_8_x, Location.BigMap1.tree_1_8_y},
//	};
//	private float[][] tree_1_d;
//	private float[][] tree_2_s = 
//	{
//			{Location.BigMap1.tree_2_1_x, Location.BigMap1.tree_2_1_y},
//			{Location.BigMap1.tree_2_2_x, Location.BigMap1.tree_2_2_y},
//			{Location.BigMap1.tree_2_3_x, Location.BigMap1.tree_2_3_y},
//			{Location.BigMap1.tree_2_4_x, Location.BigMap1.tree_2_4_y},
//			{Location.BigMap1.tree_2_5_x, Location.BigMap1.tree_2_5_y},
//	};
//	private float[][] tree_2_d;
//	private float[][] grass_s = 
//	{
//			{Location.BigMap1.grass_1_x, Location.BigMap1.grass_1_y},
//			{Location.BigMap1.grass_2_x, Location.BigMap1.grass_2_y},
//			{Location.BigMap1.grass_3_x, Location.BigMap1.grass_3_y},
//			{Location.BigMap1.grass_4_x, Location.BigMap1.grass_4_y},
//			{Location.BigMap1.grass_5_x, Location.BigMap1.grass_5_y},
//			{Location.BigMap1.grass_6_x, Location.BigMap1.grass_6_y},
//			{Location.BigMap1.grass_7_x, Location.BigMap1.grass_7_y},
//			{Location.BigMap1.grass_8_x, Location.BigMap1.grass_8_y},
//	};
//	private float[][] grass_d;
//	private float[][] flower_s = 
//	{
//			{Location.BigMap1.flower_1_x, Location.BigMap1.flower_1_y},
//			{Location.BigMap1.flower_2_x, Location.BigMap1.flower_2_y},
//			{Location.BigMap1.flower_3_x, Location.BigMap1.flower_3_y},
//			{Location.BigMap1.flower_4_x, Location.BigMap1.flower_4_y},
//			{Location.BigMap1.flower_5_x, Location.BigMap1.flower_5_y},
//			{Location.BigMap1.flower_6_x, Location.BigMap1.flower_6_y},
//			{Location.BigMap1.flower_7_x, Location.BigMap1.flower_7_y},
//			{Location.BigMap1.flower_8_x, Location.BigMap1.flower_8_y},
//			{Location.BigMap1.flower_9_x, Location.BigMap1.flower_9_y},
//	};
//	private float[][] flower_d;
//	private float[][] seastar_s = 
//	{
//			{Location.BigMap1.seastar_1_x, Location.BigMap1.seastar_1_y},
//			{Location.BigMap1.seastar_2_x, Location.BigMap1.seastar_2_y},
//			{Location.BigMap1.seastar_3_x, Location.BigMap1.seastar_3_y},
//			{Location.BigMap1.seastar_4_x, Location.BigMap1.seastar_4_y},
//	};
//	private float[][] seastar_d;
//	private float[][] fish_s = 
//	{
//			{Location.BigMap1.fish_1_x, Location.BigMap1.fish_1_y, -1},
//			{Location.BigMap1.fish_2_x, Location.BigMap1.fish_2_y, 1},
//			{Location.BigMap1.fish_3_x, Location.BigMap1.fish_3_y, 1},
//			{Location.BigMap1.fish_4_x, Location.BigMap1.fish_4_y, -1},
//			{Location.BigMap1.fish_5_x, Location.BigMap1.fish_5_y, 1},
//			{Location.BigMap1.fish_6_x, Location.BigMap1.fish_6_y, 1},
//	};
//	private float[][] fish_d;
//	private float[][] bubble_s = 
//	{
//			{Location.BigMap1.bubble_1_x, Location.BigMap1.bubble_1_y},
//			{Location.BigMap1.bubble_2_x, Location.BigMap1.bubble_2_y},
//			{Location.BigMap1.bubble_3_x, Location.BigMap1.bubble_3_y},
//			{Location.BigMap1.bubble_4_x, Location.BigMap1.bubble_4_y},
//	};
//	private float[][] bubble_d;
//	private float[][] water_wave_s = 
//	{
//			{Location.BigMap1.water_wave_1_x, Location.BigMap1.water_wave_1_y},
//			{Location.BigMap1.water_wave_2_x, Location.BigMap1.water_wave_2_y},
//			{Location.BigMap1.water_wave_3_x, Location.BigMap1.water_wave_3_y},
//			{Location.BigMap1.water_wave_4_x, Location.BigMap1.water_wave_4_y},
//			{Location.BigMap1.water_wave_5_x, Location.BigMap1.water_wave_5_y},
//			{Location.BigMap1.water_wave_6_x, Location.BigMap1.water_wave_6_y},
//			{Location.BigMap1.water_wave_7_x, Location.BigMap1.water_wave_7_y},
//			{Location.BigMap1.water_wave_8_x, Location.BigMap1.water_wave_8_y},
//			{Location.BigMap1.water_wave_9_x, Location.BigMap1.water_wave_9_y},
//			{Location.BigMap1.water_wave_10_x, Location.BigMap1.water_wave_10_y},
//			{Location.BigMap1.water_wave_11_x, Location.BigMap1.water_wave_11_y},
//	};
//	private float[][] water_wave_d;
//	private float[][] vortex_s = 
//	{
//			{Location.BigMap1.vortex_1_x, Location.BigMap1.vortex_1_y, 1f},
//			{Location.BigMap1.vortex_2_x, Location.BigMap1.vortex_2_y, 1.5f},
//			{Location.BigMap1.vortex_3_x, Location.BigMap1.vortex_3_y, 1f},
//			{Location.BigMap1.vortex_4_x, Location.BigMap1.vortex_4_y, 1.5f},
//			{Location.BigMap1.vortex_5_x, Location.BigMap1.vortex_5_y, 1f},
//	};
//	private float[][] vortex_d;
//	private float[][] stone_1_s = 
//	{
//			{Location.BigMap1.stone_1_1_x, Location.BigMap1.stone_1_1_y, 1f},
//			{Location.BigMap1.stone_1_2_x, Location.BigMap1.stone_1_2_y, 112/104f},
//			{Location.BigMap1.stone_1_3_x, Location.BigMap1.stone_1_3_y, 1f},
//			{Location.BigMap1.stone_1_4_x, Location.BigMap1.stone_1_4_y, 112/104f},
//			{Location.BigMap1.stone_1_5_x, Location.BigMap1.stone_1_5_y, 1f},
//			{Location.BigMap1.stone_1_6_x, Location.BigMap1.stone_1_6_y, 112/104f},
//	};
//	private float[][] stone_1_d;
//	private float[][] island_1_s = 
//	{
//			{Location.BigMap1.island_1_1_x, Location.BigMap1.island_1_1_y},
//			{Location.BigMap1.island_1_2_x, Location.BigMap1.island_1_2_y},
//	};
//	private float[][] island_1_d;
//	private float[][] island_2_s = 
//	{
//			{Location.BigMap1.island_2_x, Location.BigMap1.island_2_y},
//	};
//	private float[][] island_2_d;
//	private float[][] sea_tree_1_s = 
//	{
//			{Location.BigMap1.sea_tree_1_1_x, Location.BigMap1.sea_tree_1_1_y},
//			{Location.BigMap1.sea_tree_1_2_x, Location.BigMap1.sea_tree_1_2_y},
//			{Location.BigMap1.sea_tree_1_3_x, Location.BigMap1.sea_tree_1_3_y},
//	};
//	private float[][] sea_tree_1_d;
//	private float[][] ship_2_s = 
//	{
//			{Location.BigMap1.ship_2_1_x, Location.BigMap1.ship_2_1_y},
//			{Location.BigMap1.ship_2_2_x, Location.BigMap1.ship_2_2_y},
//	};
//	private float[][] ship_2_d;
//	private float[][] cloud_s = 
//	{
//			{Location.BigMap1.cloud_1_x, Location.BigMap1.cloud_1_y},
//			{Location.BigMap1.cloud_2_x, Location.BigMap1.cloud_2_y},
//			{Location.BigMap1.cloud_3_x, Location.BigMap1.cloud_3_y},
//			{Location.BigMap1.cloud_4_x, Location.BigMap1.cloud_4_y},
//	};
//	private float[][] cloud_d;
//	private float[][][] dot_s = 
//	{
//			{{Location.BigMapLevel1.dot_1_1_1_x, Location.BigMapLevel1.dot_1_1_1_y, 2}, {Location.BigMapLevel1.dot_1_1_2_x, Location.BigMapLevel1.dot_1_1_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_2_1_x, Location.BigMapLevel1.dot_1_2_1_y, 2}, {Location.BigMapLevel1.dot_1_2_2_x, Location.BigMapLevel1.dot_1_2_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_3_1_x, Location.BigMapLevel1.dot_1_3_1_y, 2}, {Location.BigMapLevel1.dot_1_3_2_x, Location.BigMapLevel1.dot_1_3_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_4_1_x, Location.BigMapLevel1.dot_1_4_1_y, 2}, {Location.BigMapLevel1.dot_1_4_2_x, Location.BigMapLevel1.dot_1_4_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_5_1_x, Location.BigMapLevel1.dot_1_5_1_y, 2}, {Location.BigMapLevel1.dot_1_5_2_x, Location.BigMapLevel1.dot_1_5_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_6_1_x, Location.BigMapLevel1.dot_1_6_1_y, 2}, {Location.BigMapLevel1.dot_1_6_2_x, Location.BigMapLevel1.dot_1_6_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_7_1_x, Location.BigMapLevel1.dot_1_7_1_y, 2}, {Location.BigMapLevel1.dot_1_7_2_x, Location.BigMapLevel1.dot_1_7_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_8_1_x, Location.BigMapLevel1.dot_1_8_1_y, 2}, {Location.BigMapLevel1.dot_1_8_2_x, Location.BigMapLevel1.dot_1_8_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_9_1_x, Location.BigMapLevel1.dot_1_9_1_y, 2}, {Location.BigMapLevel1.dot_1_9_2_x, Location.BigMapLevel1.dot_1_9_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_10_1_x, Location.BigMapLevel1.dot_1_10_1_y, 2}, {Location.BigMapLevel1.dot_1_10_2_x, Location.BigMapLevel1.dot_1_10_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_11_1_x, Location.BigMapLevel1.dot_1_11_1_y, 2}, {Location.BigMapLevel1.dot_1_11_2_x, Location.BigMapLevel1.dot_1_11_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_12_1_x, Location.BigMapLevel1.dot_1_12_1_y, 2}, {Location.BigMapLevel1.dot_1_12_2_x, Location.BigMapLevel1.dot_1_12_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_13_1_x, Location.BigMapLevel1.dot_1_13_1_y, 2}, {Location.BigMapLevel1.dot_1_13_2_x, Location.BigMapLevel1.dot_1_13_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_14_1_x, Location.BigMapLevel1.dot_1_14_1_y, 2}, {Location.BigMapLevel1.dot_1_14_2_x, Location.BigMapLevel1.dot_1_14_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_15_1_x, Location.BigMapLevel1.dot_1_15_1_y, 2}, {Location.BigMapLevel1.dot_1_15_2_x, Location.BigMapLevel1.dot_1_15_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_16_1_x, Location.BigMapLevel1.dot_1_16_1_y, 2}, {Location.BigMapLevel1.dot_1_16_2_x, Location.BigMapLevel1.dot_1_16_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_17_1_x, Location.BigMapLevel1.dot_1_17_1_y, 2}, {Location.BigMapLevel1.dot_1_17_2_x, Location.BigMapLevel1.dot_1_17_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_18_1_x, Location.BigMapLevel1.dot_1_18_1_y, 2}, {Location.BigMapLevel1.dot_1_18_2_x, Location.BigMapLevel1.dot_1_18_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_19_1_x, Location.BigMapLevel1.dot_1_19_1_y, 2}, {Location.BigMapLevel1.dot_1_19_2_x, Location.BigMapLevel1.dot_1_19_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_20_1_x, Location.BigMapLevel1.dot_1_20_1_y, 2}, {Location.BigMapLevel1.dot_1_20_2_x, Location.BigMapLevel1.dot_1_20_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_21_1_x, Location.BigMapLevel1.dot_1_21_1_y, 2}, {Location.BigMapLevel1.dot_1_21_2_x, Location.BigMapLevel1.dot_1_21_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_22_1_x, Location.BigMapLevel1.dot_1_22_1_y, 2}, {Location.BigMapLevel1.dot_1_22_2_x, Location.BigMapLevel1.dot_1_22_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_23_1_x, Location.BigMapLevel1.dot_1_23_1_y, 2}, {Location.BigMapLevel1.dot_1_23_2_x, Location.BigMapLevel1.dot_1_23_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_24_1_x, Location.BigMapLevel1.dot_1_24_1_y, 2}, {Location.BigMapLevel1.dot_1_24_2_x, Location.BigMapLevel1.dot_1_24_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_25_1_x, Location.BigMapLevel1.dot_1_25_1_y, 2}, {Location.BigMapLevel1.dot_1_25_2_x, Location.BigMapLevel1.dot_1_25_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_26_1_x, Location.BigMapLevel1.dot_1_26_1_y, 2}, {Location.BigMapLevel1.dot_1_26_2_x, Location.BigMapLevel1.dot_1_26_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_27_1_x, Location.BigMapLevel1.dot_1_27_1_y, 2}, {Location.BigMapLevel1.dot_1_27_2_x, Location.BigMapLevel1.dot_1_27_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_28_1_x, Location.BigMapLevel1.dot_1_28_1_y, 2}, {Location.BigMapLevel1.dot_1_28_2_x, Location.BigMapLevel1.dot_1_28_2_y, 2}},
//			{{Location.BigMapLevel1.dot_1_29_1_x, Location.BigMapLevel1.dot_1_29_1_y, 2}, {Location.BigMapLevel1.dot_1_29_2_x, Location.BigMapLevel1.dot_1_29_2_y, 2}},
//			{{Location.BigMapLevel1.dot_2_30_1_x, Location.BigMapLevel1.dot_2_30_1_y, 1}, {Location.BigMapLevel1.dot_2_30_2_x, Location.BigMapLevel1.dot_2_30_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_31_1_x, Location.BigMapLevel1.dot_2_31_1_y, 1}, {Location.BigMapLevel1.dot_2_31_2_x, Location.BigMapLevel1.dot_2_31_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_32_1_x, Location.BigMapLevel1.dot_2_32_1_y, 1}, {Location.BigMapLevel1.dot_2_32_2_x, Location.BigMapLevel1.dot_2_32_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_33_1_x, Location.BigMapLevel1.dot_2_33_1_y, 1}, {Location.BigMapLevel1.dot_2_33_2_x, Location.BigMapLevel1.dot_2_33_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_34_1_x, Location.BigMapLevel1.dot_2_34_1_y, 1}, {Location.BigMapLevel1.dot_2_34_2_x, Location.BigMapLevel1.dot_2_34_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_35_1_x, Location.BigMapLevel1.dot_2_35_1_y, 1}, {Location.BigMapLevel1.dot_2_35_2_x, Location.BigMapLevel1.dot_2_35_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_36_1_x, Location.BigMapLevel1.dot_2_36_1_y, 1}, {Location.BigMapLevel1.dot_2_36_2_x, Location.BigMapLevel1.dot_2_36_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_37_1_x, Location.BigMapLevel1.dot_2_37_1_y, 1}, {Location.BigMapLevel1.dot_2_37_2_x, Location.BigMapLevel1.dot_2_37_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_38_1_x, Location.BigMapLevel1.dot_2_38_1_y, 1}, {Location.BigMapLevel1.dot_2_38_2_x, Location.BigMapLevel1.dot_2_38_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_39_1_x, Location.BigMapLevel1.dot_2_39_1_y, 1}, {Location.BigMapLevel1.dot_2_39_2_x, Location.BigMapLevel1.dot_2_39_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_40_1_x, Location.BigMapLevel1.dot_2_40_1_y, 1}, {Location.BigMapLevel1.dot_2_40_2_x, Location.BigMapLevel1.dot_2_40_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_41_1_x, Location.BigMapLevel1.dot_2_41_1_y, 1}, {Location.BigMapLevel1.dot_2_41_2_x, Location.BigMapLevel1.dot_2_41_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_42_1_x, Location.BigMapLevel1.dot_2_42_1_y, 1}, {Location.BigMapLevel1.dot_2_42_2_x, Location.BigMapLevel1.dot_2_42_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_43_1_x, Location.BigMapLevel1.dot_2_43_1_y, 1}, {Location.BigMapLevel1.dot_2_43_2_x, Location.BigMapLevel1.dot_2_43_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_44_1_x, Location.BigMapLevel1.dot_2_44_1_y, 1}, {Location.BigMapLevel1.dot_2_44_2_x, Location.BigMapLevel1.dot_2_44_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_45_1_x, Location.BigMapLevel1.dot_2_45_1_y, 1}, {Location.BigMapLevel1.dot_2_45_2_x, Location.BigMapLevel1.dot_2_45_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_46_1_x, Location.BigMapLevel1.dot_2_46_1_y, 1}, {Location.BigMapLevel1.dot_2_46_2_x, Location.BigMapLevel1.dot_2_46_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_47_1_x, Location.BigMapLevel1.dot_2_47_1_y, 1}, {Location.BigMapLevel1.dot_2_47_2_x, Location.BigMapLevel1.dot_2_47_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_48_1_x, Location.BigMapLevel1.dot_2_48_1_y, 1}, {Location.BigMapLevel1.dot_2_48_2_x, Location.BigMapLevel1.dot_2_48_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_49_1_x, Location.BigMapLevel1.dot_2_49_1_y, 1}, {Location.BigMapLevel1.dot_2_49_2_x, Location.BigMapLevel1.dot_2_49_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_50_1_x, Location.BigMapLevel1.dot_2_50_1_y, 1}, {Location.BigMapLevel1.dot_2_50_2_x, Location.BigMapLevel1.dot_2_50_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_51_1_x, Location.BigMapLevel1.dot_2_51_1_y, 1}, {Location.BigMapLevel1.dot_2_51_2_x, Location.BigMapLevel1.dot_2_51_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_52_1_x, Location.BigMapLevel1.dot_2_52_1_y, 1}, {Location.BigMapLevel1.dot_2_52_2_x, Location.BigMapLevel1.dot_2_52_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_53_1_x, Location.BigMapLevel1.dot_2_53_1_y, 1}, {Location.BigMapLevel1.dot_2_53_2_x, Location.BigMapLevel1.dot_2_53_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_54_1_x, Location.BigMapLevel1.dot_2_54_1_y, 1}, {Location.BigMapLevel1.dot_2_54_2_x, Location.BigMapLevel1.dot_2_54_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_55_1_x, Location.BigMapLevel1.dot_2_55_1_y, 1}, {Location.BigMapLevel1.dot_2_55_2_x, Location.BigMapLevel1.dot_2_55_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_56_1_x, Location.BigMapLevel1.dot_2_56_1_y, 1}, {Location.BigMapLevel1.dot_2_56_2_x, Location.BigMapLevel1.dot_2_56_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_57_1_x, Location.BigMapLevel1.dot_2_57_1_y, 1}, {Location.BigMapLevel1.dot_2_57_2_x, Location.BigMapLevel1.dot_2_57_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_58_1_x, Location.BigMapLevel1.dot_2_58_1_y, 1}, {Location.BigMapLevel1.dot_2_58_2_x, Location.BigMapLevel1.dot_2_58_2_y, 1}},
//			{{Location.BigMapLevel1.dot_2_59_1_x, Location.BigMapLevel1.dot_2_59_1_y, 1}, {Location.BigMapLevel1.dot_2_59_2_x, Location.BigMapLevel1.dot_2_59_2_y, 1}},
//	};
//	private float[][][] dot_d;
//	private float[][] button_s = 
//	{
//			{Location.BigMapLevel1.button_1_x, Location.BigMapLevel1.button_1_y},
//			{Location.BigMapLevel1.button_2_x, Location.BigMapLevel1.button_2_y},
//			{Location.BigMapLevel1.button_3_x, Location.BigMapLevel1.button_3_y},
//			{Location.BigMapLevel1.button_4_x, Location.BigMapLevel1.button_4_y},
//			{Location.BigMapLevel1.button_5_x, Location.BigMapLevel1.button_5_y},
//			{Location.BigMapLevel1.button_6_x, Location.BigMapLevel1.button_6_y},
//			{Location.BigMapLevel1.button_7_x, Location.BigMapLevel1.button_7_y},
//			{Location.BigMapLevel1.button_8_x, Location.BigMapLevel1.button_8_y},
//			{Location.BigMapLevel1.button_9_x, Location.BigMapLevel1.button_9_y},
//			{Location.BigMapLevel1.button_10_x, Location.BigMapLevel1.button_10_y},
//			{Location.BigMapLevel1.button_11_x, Location.BigMapLevel1.button_11_y},
//			{Location.BigMapLevel1.button_12_x, Location.BigMapLevel1.button_12_y},
//			{Location.BigMapLevel1.button_13_x, Location.BigMapLevel1.button_13_y},
//			{Location.BigMapLevel1.button_14_x, Location.BigMapLevel1.button_14_y},
//			{Location.BigMapLevel1.button_15_x, Location.BigMapLevel1.button_15_y},
//			{Location.BigMapLevel1.button_16_x, Location.BigMapLevel1.button_16_y},
//			{Location.BigMapLevel1.button_17_x, Location.BigMapLevel1.button_17_y},
//			{Location.BigMapLevel1.button_18_x, Location.BigMapLevel1.button_18_y},
//			{Location.BigMapLevel1.button_19_x, Location.BigMapLevel1.button_19_y},
//			{Location.BigMapLevel1.button_20_x, Location.BigMapLevel1.button_20_y},
//			{Location.BigMapLevel1.button_21_x, Location.BigMapLevel1.button_21_y},
//			{Location.BigMapLevel1.button_22_x, Location.BigMapLevel1.button_22_y},
//			{Location.BigMapLevel1.button_23_x, Location.BigMapLevel1.button_23_y},
//			{Location.BigMapLevel1.button_24_x, Location.BigMapLevel1.button_24_y},
//			{Location.BigMapLevel1.button_25_x, Location.BigMapLevel1.button_25_y},
//			{Location.BigMapLevel1.button_26_x, Location.BigMapLevel1.button_26_y},
//			{Location.BigMapLevel1.button_27_x, Location.BigMapLevel1.button_27_y},
//			{Location.BigMapLevel1.button_28_x, Location.BigMapLevel1.button_28_y},
//			{Location.BigMapLevel1.button_29_x, Location.BigMapLevel1.button_29_y},
//			{Location.BigMapLevel1.button_30_x, Location.BigMapLevel1.button_30_y},
//			{Location.BigMapLevel1.button_31_x, Location.BigMapLevel1.button_31_y},
//			{Location.BigMapLevel1.button_32_x, Location.BigMapLevel1.button_32_y},
//			{Location.BigMapLevel1.button_33_x, Location.BigMapLevel1.button_33_y},
//			{Location.BigMapLevel1.button_34_x, Location.BigMapLevel1.button_34_y},
//			{Location.BigMapLevel1.button_35_x, Location.BigMapLevel1.button_35_y},
//			{Location.BigMapLevel1.button_36_x, Location.BigMapLevel1.button_36_y},
//			{Location.BigMapLevel1.button_37_x, Location.BigMapLevel1.button_37_y},
//			{Location.BigMapLevel1.button_38_x, Location.BigMapLevel1.button_38_y},
//			{Location.BigMapLevel1.button_39_x, Location.BigMapLevel1.button_39_y},
//			{Location.BigMapLevel1.button_40_x, Location.BigMapLevel1.button_40_y},
//			{Location.BigMapLevel1.button_41_x, Location.BigMapLevel1.button_41_y},
//			{Location.BigMapLevel1.button_42_x, Location.BigMapLevel1.button_42_y},
//			{Location.BigMapLevel1.button_43_x, Location.BigMapLevel1.button_43_y},
//			{Location.BigMapLevel1.button_44_x, Location.BigMapLevel1.button_44_y},
//			{Location.BigMapLevel1.button_45_x, Location.BigMapLevel1.button_45_y},
//			{Location.BigMapLevel1.button_46_x, Location.BigMapLevel1.button_46_y},
//			{Location.BigMapLevel1.button_47_x, Location.BigMapLevel1.button_47_y},
//			{Location.BigMapLevel1.button_48_x, Location.BigMapLevel1.button_48_y},
//			{Location.BigMapLevel1.button_49_x, Location.BigMapLevel1.button_49_y},
//			{Location.BigMapLevel1.button_50_x, Location.BigMapLevel1.button_50_y},
//			{Location.BigMapLevel1.button_51_x, Location.BigMapLevel1.button_51_y},
//			{Location.BigMapLevel1.button_52_x, Location.BigMapLevel1.button_52_y},
//			{Location.BigMapLevel1.button_53_x, Location.BigMapLevel1.button_53_y},
//			{Location.BigMapLevel1.button_54_x, Location.BigMapLevel1.button_54_y},
//			{Location.BigMapLevel1.button_55_x, Location.BigMapLevel1.button_55_y},
//			{Location.BigMapLevel1.button_56_x, Location.BigMapLevel1.button_56_y},
//			{Location.BigMapLevel1.button_57_x, Location.BigMapLevel1.button_57_y},
//			{Location.BigMapLevel1.button_58_x, Location.BigMapLevel1.button_58_y},
//			{Location.BigMapLevel1.button_59_x, Location.BigMapLevel1.button_59_y},
//			{Location.BigMapLevel1.button_60_x, Location.BigMapLevel1.button_60_y},
//	};
//	private float[][] button_d;
//	
//	public void Release() {
////		bg = null;
////		kuang3 = null;
////		kuang4 = null;
////		coast = null;
////		statebg = null;
////		gold = null;
////		gem = null;
////		heart = null;
////		rechange = null;
////		storeWord = null;
////		store = null;
////		mail = null;
////		mailred = null;
////		farm = null;
////		farmArrow = null;
////		mapmenu_number = null;
////		mapmenu_heart_number = null;
////		green_2 = null;
////		green_1 = null;
////		yellow_2 = null;
////		dune = null;
////		castle = null;
////		stump = null;
////		shell = null;
////		hill_1 = null;
////		hill_2 = null;
////		stone_2 = null;
////		tree_1 = null;
////		tree_2 = null;
////		grass = null;
////		flower = null;
////		seastar = null;
////		fish = null;
////		bubble = null;
////		water_wave = null;
////		vortex = null;
////		stone_1 = null;
////		island_1 = null;
////		island_2 = null;
////		ship_2 = null;
////		ship_1 = null;
////		octopus = null;
////		cloud = null;
////		smoke = null;
////		for (int i=0; i<dot.length; i++) {
////			dot[i] = null;
////		}
////		dot = null;
////		for (int i=0; i<button.length; i++) {
////			button[i] = null;
////		}
////		button = null;
////		for (int i=0; i<star.length; i++) {
////			star[i] = null;
////		}
////		star = null;
////		maplevel_number = null;
////		ren_1 = null;
////		ren_2 = null;
////		enemy_01 = null;
////		enemy_02 = null;
////		enemy_04 = null;
//		GameStaticImage.delImage();
//		GameStaticImage.releaseStaticImage();
//		GameStaticImage.shareReleaseStaticImage();
//		GameStaticImage.delSmallCard();
//	}
//	
//	public ChooseLevelModule1() {
//		
//	}
//	
//	@Override
//	public void onreStart() {
//		// TODO Auto-generated method stub
//		super.onreStart();
//		 System.out.println("<><>");
//	}
//	BigDecimal   b ;
//	public boolean initialize() {
//		GameStaticImage.loadImage();
//		GameStaticImage.loadStaticImage();
//		
//		clmSprite = new Sprite();
//		
//		initMapLevel();
//		
//		initMapMenu();
//		
//		Configs.GameMapWidth = Configs.GameMapORGWidth * GameConfig.f_zoomx;
//		Configs.GameMapHeight = Configs.GameMapORGHeight * GameConfig.f_zoomy;
//		bgx = GameConfig.GameScreen_Width/2+(Configs.GameMapWidth-GameConfig.GameScreen_Width)/2;
//		bgy = GameConfig.GameScreen_Height/2-(Configs.GameMapHeight-GameConfig.GameScreen_Height)/2;
//		
//		bg1_1x = initIsNotBitmapCoordinate(Location.BigMap1.upperHalfBackGround_x);
//		bg1_1y = initIsNotBitmapCoordinate(Location.BigMap1.upperHalfBackGround_y);
//		bg1_2x = initIsNotBitmapCoordinate(Location.BigMap1.upperHalfBackGround_x + Location.BigMap1.upperHalfBackGround_w);
//		bg1_2y = initIsNotBitmapCoordinate(Location.BigMap1.upperHalfBackGround_y + Location.BigMap1.upperHalfBackGround_h);
//		bg2_1x = initIsNotBitmapCoordinate(Location.BigMap1.bottomHalfBackGround_x);
//		bg2_1y = initIsNotBitmapCoordinate(Location.BigMap1.bottomHalfBackGround_y);
//		bg2_2x = initIsNotBitmapCoordinate(Location.BigMap1.bottomHalfBackGround_x + Location.BigMap1.bottomHalfBackGround_w);
//		bg2_2y = initIsNotBitmapCoordinate(Location.BigMap1.bottomHalfBackGround_y + Location.BigMap1.bottomHalfBackGround_h);
//		
//		coast_x = initIsBitmapCoordinateX(Location.BigMap1.coast_x, GameStaticImage.s_map_seabeach);
//		coast_y = initIsBitmapCoordinateY(Location.BigMap1.coast_y, GameStaticImage.s_map_seabeach);
//		
//		green_2_d = new float[green_2_s.length][green_2_s[0].length];
//		for(int i=0; i<green_2_s.length; i++) {
//			green_2_d[i][0] = initIsBitmapCoordinateX(green_2_s[i][0], GameStaticImage.s_map_green_2);
//			green_2_d[i][1] = initIsBitmapCoordinateY(green_2_s[i][1], GameStaticImage.s_map_green_2);
//			green_2_d[i][2] = green_2_s[i][2];
//		}
//		green_1_d = new float[green_1_s.length][green_1_s[0].length];
//		for(int i=0; i<green_1_s.length; i++) {
//			green_1_d[i][0] = initIsBitmapCoordinateX(green_1_s[i][0], GameStaticImage.s_map_green_1);
//			green_1_d[i][1] = initIsBitmapCoordinateY(green_1_s[i][1], GameStaticImage.s_map_green_1);
//		}
//		yellow_2_d = new float[yellow_2_s.length][yellow_2_s[0].length];
//		for(int i=0; i<yellow_2_s.length; i++) {
//			yellow_2_d[i][0] = initIsBitmapCoordinateX(yellow_2_s[i][0], GameStaticImage.s_map_yellow_2);
//			yellow_2_d[i][1] = initIsBitmapCoordinateY(yellow_2_s[i][1], GameStaticImage.s_map_yellow_2);
//			yellow_2_d[i][2] = yellow_2_s[i][2];
//		}
//		dune_d = new float[dune_s.length][dune_s[0].length];
//		for(int i=0; i<dune_s.length; i++) {
//			dune_d[i][0] = initIsBitmapCoordinateX(dune_s[i][0], GameStaticImage.s_map_dune);
//			dune_d[i][1] = initIsBitmapCoordinateY(dune_s[i][1], GameStaticImage.s_map_dune);
//		}
//		castle_d = new float[castle_s.length][castle_s[0].length];
//		for(int i=0; i<castle_s.length; i++) {
//			castle_d[i][0] = initIsBitmapCoordinateX(castle_s[i][0], GameStaticImage.s_map_castle);
//			castle_d[i][1] = initIsBitmapCoordinateY(castle_s[i][1], GameStaticImage.s_map_castle);
//		}
//		
//		hill_2_d = new float[hill_2_s.length][hill_2_s[0].length];
//		for(int i=0; i<hill_2_s.length; i++) {
//			hill_2_d[i][0] = initIsBitmapCoordinateX(hill_2_s[i][0], GameStaticImage.s_map_hill_2);
//			hill_2_d[i][1] = initIsBitmapCoordinateY(hill_2_s[i][1], GameStaticImage.s_map_hill_2);
//			hill_2_d[i][2] = hill_2_s[i][2];
//		}
//		stone_2_d = new float[stone_2_s.length][stone_2_s[0].length];
//		for(int i=0; i<stone_2_s.length; i++) {
//			stone_2_d[i][0] = initIsBitmapCoordinateX(stone_2_s[i][0], GameStaticImage.s_map_stone_2);
//			stone_2_d[i][1] = initIsBitmapCoordinateY(stone_2_s[i][1], GameStaticImage.s_map_stone_2);
//		}
//		tree_1_d = new float[tree_1_s.length][tree_1_s[0].length];
//		for(int i=0; i<tree_1_s.length; i++) {
//			tree_1_d[i][0] = initIsBitmapCoordinateX(tree_1_s[i][0], GameStaticImage.s_map_tree_1);
//			tree_1_d[i][1] = initIsBitmapCoordinateY(tree_1_s[i][1], GameStaticImage.s_map_tree_1);
//		}
//		tree_2_d = new float[tree_2_s.length][tree_2_s[0].length];
//		for(int i=0; i<tree_2_s.length; i++) {
//			tree_2_d[i][0] = initIsBitmapCoordinateX(tree_2_s[i][0], GameStaticImage.s_map_tree_2);
//			tree_2_d[i][1] = initIsBitmapCoordinateY(tree_2_s[i][1], GameStaticImage.s_map_tree_2);
//		}
//		grass_d = new float[grass_s.length][grass_s[0].length];
//		for(int i=0; i<grass_s.length; i++) {
//			grass_d[i][0] = initIsBitmapCoordinateX(grass_s[i][0], GameStaticImage.s_map_grass);
//			grass_d[i][1] = initIsBitmapCoordinateY(grass_s[i][1], GameStaticImage.s_map_grass);
//		}
//		flower_d = new float[flower_s.length][flower_s[0].length];
//		for(int i=0; i<flower_s.length; i++) {
//			flower_d[i][0] = initIsBitmapCoordinateX(flower_s[i][0], GameStaticImage.s_map_flower);
//			flower_d[i][1] = initIsBitmapCoordinateY(flower_s[i][1], GameStaticImage.s_map_flower);
//		}
//		seastar_d = new float[seastar_s.length][seastar_s[0].length];
//		for(int i=0; i<seastar_s.length; i++) {
//			seastar_d[i][0] = initIsBitmapCoordinateX(seastar_s[i][0], GameStaticImage.s_map_seastar);
//			seastar_d[i][1] = initIsBitmapCoordinateY(seastar_s[i][1], GameStaticImage.s_map_seastar);
//		}
//		fish_d = new float[fish_s.length][fish_s[0].length];
//		for(int i=0; i<fish_s.length; i++) {
//			fish_d[i][0] = initIsBitmapCoordinateX(fish_s[i][0], GameStaticImage.s_map_fish);
//			fish_d[i][1] = initIsBitmapCoordinateY(fish_s[i][1], GameStaticImage.s_map_fish);
//			fish_d[i][2] = fish_s[i][2];
//		}
//		bubble_d = new float[bubble_s.length][bubble_s[0].length];
//		for(int i=0; i<bubble_s.length; i++) {
//			bubble_d[i][0] = initIsBitmapCoordinateX(bubble_s[i][0], GameStaticImage.s_map_bubble);
//			bubble_d[i][1] = initIsBitmapCoordinateY(bubble_s[i][1], GameStaticImage.s_map_bubble);
//		}
//		water_wave_d = new float[water_wave_s.length][water_wave_s[0].length];
//		for(int i=0; i<water_wave_s.length; i++) {
//			water_wave_d[i][0] = initIsBitmapCoordinateX(water_wave_s[i][0], GameStaticImage.s_map_water_wave);
//			water_wave_d[i][1] = initIsBitmapCoordinateY(water_wave_s[i][1], GameStaticImage.s_map_water_wave);
//		}
//		vortex_d = new float[vortex_s.length][vortex_s[0].length];
//		for(int i=0; i<vortex_s.length; i++) {
//			vortex_d[i][0] = initIsBitmapCoordinateX(vortex_s[i][0], GameStaticImage.s_map_vortex);
//			vortex_d[i][1] = initIsBitmapCoordinateY(vortex_s[i][1], GameStaticImage.s_map_vortex);
//			vortex_d[i][2] = vortex_s[i][2];
//		}
//		stone_1_d = new float[stone_1_s.length][stone_1_s[0].length];
//		for(int i=0; i<stone_1_s.length; i++) {
//			stone_1_d[i][0] = initIsBitmapCoordinateX(stone_1_s[i][0], GameStaticImage.s_map_stone_1);
//			stone_1_d[i][1] = initIsBitmapCoordinateY(stone_1_s[i][1], GameStaticImage.s_map_stone_1);
//			stone_1_d[i][2] = stone_1_s[i][2];
//		}
//		island_1_d = new float[island_1_s.length][island_1_s[0].length];
//		for(int i=0; i<island_1_s.length; i++) {
//			island_1_d[i][0] = initIsBitmapCoordinateX(island_1_s[i][0], GameStaticImage.s_map_island_1);
//			island_1_d[i][1] = initIsBitmapCoordinateY(island_1_s[i][1], GameStaticImage.s_map_island_1);
//		}
//		island_2_d = new float[island_2_s.length][island_2_s[0].length];
//		island_2_d[0][0] = initIsBitmapCoordinateX(island_2_s[0][0], GameStaticImage.s_map_island_2);
//		island_2_d[0][1] = initIsBitmapCoordinateY(island_2_s[0][1], GameStaticImage.s_map_island_2);
//		
//		sea_tree_1_d = new float[sea_tree_1_s.length][sea_tree_1_s[0].length];
//		for(int i=0; i<sea_tree_1_s.length; i++) {
//			sea_tree_1_d[i][0] = initIsBitmapCoordinateX(sea_tree_1_s[i][0], GameStaticImage.s_map_tree_1);
//			sea_tree_1_d[i][1] = initIsBitmapCoordinateY(sea_tree_1_s[i][1], GameStaticImage.s_map_tree_1);
//		}
//		sea_tree_2_x = initIsBitmapCoordinateX(Location.BigMap1.sea_tree_2_x, GameStaticImage.s_map_tree_2);
//		sea_tree_2_y = initIsBitmapCoordinateY(Location.BigMap1.sea_tree_2_y, GameStaticImage.s_map_tree_2);
//		sea_grass_x = initIsBitmapCoordinateX(Location.BigMap1.sea_grass_x, GameStaticImage.s_map_grass);
//		sea_grass_y = initIsBitmapCoordinateY(Location.BigMap1.sea_grass_y, GameStaticImage.s_map_grass);
//		ship_2_d = new float[ship_2_s.length][ship_2_s[0].length];
//		for(int i=0; i<ship_2_s.length; i++) {
//			ship_2_d[i][0] = initIsBitmapCoordinateX(ship_2_s[i][0], GameStaticImage.s_map_ship_2);
//			ship_2_d[i][1] = initIsBitmapCoordinateY(ship_2_s[i][1], GameStaticImage.s_map_ship_2);
//		}
//		try {
//			ship_1_x = initIsBitmapCoordinateX(Location.BigMap1.ship_1_x, GameStaticImage.s_map_ship_1);
//			ship_1_y = initIsBitmapCoordinateY(Location.BigMap1.ship_1_y, GameStaticImage.s_map_ship_1);
//			octopus_x = initIsBitmapCoordinateX(Location.BigMap1.octopus_x, GameStaticImage.s_map_octopus[0]);
//			octopus_y = initIsBitmapCoordinateY(Location.BigMap1.octopus_y, GameStaticImage.s_map_octopus[0]);
//			octopus_r = 298.0f * 3.0f / 711.0f;
//			stump_x = initIsBitmapCoordinateX(Location.BigMap1.stump_x, GameStaticImage.s_map_stump);
//			stump_y = initIsBitmapCoordinateY(Location.BigMap1.stump_y, GameStaticImage.s_map_stump);
//			shell_x = initIsBitmapCoordinateX(Location.BigMap1.shell_x, GameStaticImage.s_map_shell);
//			shell_y = initIsBitmapCoordinateY(Location.BigMap1.shell_y, GameStaticImage.s_map_shell);
//			hill_1_x = initIsBitmapCoordinateX(Location.BigMap1.hill_1_x, GameStaticImage.s_map_hill_1);
//			hill_1_y = initIsBitmapCoordinateY(Location.BigMap1.hill_1_y, GameStaticImage.s_map_hill_1);
//			cloud_d = new float[cloud_s.length][cloud_s[0].length];
//		}catch(Exception e){
//		}
//		for(int i=0; i<cloud_s.length; i++) {
//			cloud_d[i][0] = initIsBitmapCoordinateX(cloud_s[i][0], GameStaticImage.s_map_cloud);
//			cloud_d[i][1] = initIsBitmapCoordinateY(cloud_s[i][1], GameStaticImage.s_map_cloud);
//		}
//		smoke_x = initIsBitmapCoordinateX(Location.BigMap1.smoke_x, GameStaticImage.s_map_smoke[0]);
//		smoke_y = initIsBitmapCoordinateY(Location.BigMap1.smoke_y, GameStaticImage.s_map_smoke[0]);
//		smoke_r = 69.0f*4.0f/608.0f;
//		//初始化路线
////		dot_d = dot_s.clone();
//		dot_d = new float[dot_s.length][dot_s[0].length][dot_s[0][0].length];
//		for (int i=0; i<dot_s.length; i++) {
//			for (int j=0; j<dot_s[i].length; j++) {
//				b   =   new   BigDecimal(dot_s[i][j][2]-1);
//				dot_d[i][j][0] = (int)initIsBitmapCoordinateX(dot_s[i][j][0], GameStaticImage.s_map_dot[b.setScale(0).intValue()]);
//				dot_d[i][j][1] = (int)initIsBitmapCoordinateY(dot_s[i][j][1], GameStaticImage.s_map_dot[b.setScale(0).intValue()]);
//				dot_d[i][j][2] = dot_s[i][j][2];
//			}
//		}
//		anjian_level = new boolean[button_s.length];
//		button_d = new float[button_s.length][button_s[0].length];
//		for(int i=0; i<button_s.length; i++) {
//			int j = 0;
//			if (i == 59 || i == 30 || i == 24) j = 3;
//			button_d[i][0] = initIsBitmapCoordinateX(button_s[i][0], GameStaticImage.s_map_button[j]);
//			button_d[i][1] = initIsBitmapCoordinateY(button_s[i][1], GameStaticImage.s_map_button[j]);
//			anjian_level[i] = false;
//		}
//		//-----------------------------------------------
//		r = 1.0f;
//		
//		boatH = 0;
//		isboatDown = true;
//		
//		octopus_aphla = 133;
//		octopus_R = octopus_G = octopus_B = -255;
//		
////		gold_str 	= "12345";
////		gem_str 	= "12345";
////		heart_str	= "3";
//		time_str	= "01:30";
//		mail_str	= "25";
//		isfarmArrowDown = true;
//		farmArrowH = 0;
//		
//		mode1 = Configs.NONE; 
//		isOneTouchMove = true;
//		
//		reward_level = 1;
//		
//		GameStaticImage.loadSmallCard();
////		GameStaticImage.delMenuImage();
//		GameImage.showImageHashMap();
//		return false;
//	}
//	
//	private void initMapLevel() {
////		bg = new Sprite(GameImage.getImage(GameStaticImage.map_bg));
////		coast = new Sprite(GameImage.getImage(GameStaticImage.map_seabeach));
////		green_2 = new Sprite(GameImage.getImage(GameStaticImage.map_green_2));
////		green_1 = new Sprite(GameImage.getImage(GameStaticImage.map_green_1));
////		yellow_2 = new Sprite(GameImage.getImage(GameStaticImage.map_yellow_2));
////		dune = new Sprite(GameImage.getImage(GameStaticImage.map_dune));
////		castle = new Sprite(GameImage.getImage(GameStaticImage.map_castle));
////		stump = new Sprite(GameImage.getImage(GameStaticImage.map_stump));
////		shell = new Sprite(GameImage.getImage(GameStaticImage.map_shell));
////		hill_1 = new Sprite(GameImage.getImage(GameStaticImage.map_hill_1));
////		hill_2 = new Sprite(GameImage.getImage(GameStaticImage.map_hill_2));
////		stone_2 = new Sprite(GameImage.getImage(GameStaticImage.map_stone_2));
////		tree_1 = new Sprite(GameImage.getImage(GameStaticImage.map_tree_1));
////		tree_2 = new Sprite(GameImage.getImage(GameStaticImage.map_tree_2));
////		grass = new Sprite(GameImage.getImage(GameStaticImage.map_grass));
////		flower = new Sprite(GameImage.getImage(GameStaticImage.map_flower));
////		seastar = new Sprite(GameImage.getImage(GameStaticImage.map_seastar));
////		fish = new Sprite(GameImage.getImage(GameStaticImage.map_fish));
////		bubble = new Sprite(GameImage.getImage(GameStaticImage.map_bubble));
////		water_wave = new Sprite(GameImage.getImage(GameStaticImage.map_water_wave));
////		vortex = new Sprite(GameImage.getImage(GameStaticImage.map_vortex));
////		stone_1 = new Sprite(GameImage.getImage(GameStaticImage.map_stone_1));
////		island_1 = new Sprite(GameImage.getImage(GameStaticImage.map_island_1));
////		island_2 = new Sprite(GameImage.getImage(GameStaticImage.map_island_2));
////		ship_2 = new Sprite(GameImage.getImage(GameStaticImage.map_ship_2));
////		ship_1 = new Sprite(GameImage.getImage(GameStaticImage.map_ship_1));
////		octopus = GameImage.getAutoSizecutBitmap(GameStaticImage.map_octopus, 3, 1, GameImage.Sort_line);
////		cloud = new Sprite(GameImage.getImage(GameStaticImage.map_cloud));
////		smoke = GameImage.getAutoSizecutBitmap(GameStaticImage.map_smoke, 4, 1, GameImage.Sort_line);
////		dot = new Sprite[2];
////		dot[0] = new Sprite(GameImage.getImage(GameStaticImage.map_dot_1));
////		dot[1] = new Sprite(GameImage.getImage(GameStaticImage.map_dot_2));
////		button = new Sprite[4];
////		button[0] = new Sprite(GameImage.getImage(GameStaticImage.map_button1));
////		button[1] = new Sprite(GameImage.getImage(GameStaticImage.map_button2));
////		button[2] = new Sprite(GameImage.getImage(GameStaticImage.map_button3));
////		button[3] = new Sprite(GameImage.getImage(GameStaticImage.map_button4));
////		maplevel_number = GameImage.getAutoSizecutBitmap(GameStaticImage.map_num_1, 10, 1, GameImage.Sort_line);
////		star = new Sprite[4];
////		star[0] = new Sprite(GameImage.getImage(GameStaticImage.map_star_1));
////		star[1] = new Sprite(GameImage.getImage(GameStaticImage.map_star_2));
////		star[2] = new Sprite(GameImage.getImage(GameStaticImage.map_star_3));
////		star[3] = new Sprite(GameImage.getImage(GameStaticImage.map_star_4));
////		ren_1 = new Sprite(GameImage.getImage(GameStaticImage.map_ren_1));
////		ren_2 = new Sprite(GameImage.getImage(GameStaticImage.map_ren_2));
////		kuang3 = new Sprite(GameImage.getImage(GameStaticImage.map_kuang3));
////		kuang4 = new Sprite(GameImage.getImage(GameStaticImage.map_kuang4));
////		enemy_01 = new Sprite(GameImage.getImage(GameStaticImage.map_enemy_01));
////		enemy_02 = new Sprite(GameImage.getImage(GameStaticImage.map_enemy_02));
////		enemy_04 = new Sprite(GameImage.getImage(GameStaticImage.map_enemy_04));
//	}
//
//	private int initIsNotBitmapCoordinate(int original_x) {
//		return (int) ((original_x * GameConfig.f_zoomx));
//	}
//	//图片坐标初始化
//	private float initIsBitmapCoordinateX(int s_x, Sprite bitmap) {
//		return s_x * GameConfig.f_zoomx + bitmap.bitmap.getWidth()/2 - Configs.GameMapWidth/2;
//	}
//	private float initIsBitmapCoordinateY(int s_y, Sprite bitmap) {
//		return s_y * GameConfig.f_zoomy + bitmap.bitmap.getHeight()/2 - Configs.GameMapHeight/2;
//	}
//	private float initIsBitmapCoordinateX(float s_x, Sprite bitmap) {
//		return s_x * GameConfig.f_zoomx + bitmap.bitmap.getWidth()/2 - Configs.GameMapWidth/2;
//	}
//	private float initIsBitmapCoordinateY(float s_y, Sprite bitmap) {
//		return s_y * GameConfig.f_zoomy + bitmap.bitmap.getHeight()/2 - Configs.GameMapHeight/2;
//	}
//	private float initIsBitmapCoordinateX(float s_x, Bitmap bitmap) {
//		return s_x * GameConfig.f_zoomx + bitmap.getWidth()/2 - Configs.GameMapWidth/2;
//	}
//	private float initIsBitmapCoordinateY(float s_y, Bitmap bitmap) {
//		return s_y * GameConfig.f_zoomy + bitmap.getHeight()/2 - Configs.GameMapHeight/2;
//	}
//	int storeGrayFloor_x,store_x,storeWord_x;
//	int mail_x,mailRedFloor_x,mail_num_x;
//	int farm_x,farm_y,farmArrow_x,farmArrow_y;
//	//初始化地图菜单
//	private void initMapMenu() {
////		statebg = new Sprite(GameImage.getImage(GameStaticImage.map_back));
////		gold 	= new Sprite(GameImage.getImage(GameStaticImage.map_gold));
////		gem		= new Sprite(GameImage.getImage(GameStaticImage.map_gem));
////		heart	= new Sprite(GameImage.getImage(GameStaticImage.map_heart));
////		rechange= new Sprite(GameImage.getImage(GameStaticImage.map_add));
//		
////		storeWord= new Sprite(GameImage.getImage(GameStaticImage.map_store_2));
////		store	= new Sprite(GameImage.getImage(GameStaticImage.map_store));
//		storeGrayFloor_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.storeGrayFloor_x)* GameConfig.f_zoomx);
//		store_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.store_x)* GameConfig.f_zoomx);
//		storeWord_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.storeWord_x)* GameConfig.f_zoomx);
//		
////		mail	= new Sprite(GameImage.getImage(GameStaticImage.map_mail));
////		mailred	= new Sprite(GameImage.getImage(GameStaticImage.map_mail_2));
//		mail_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.mail_x)* GameConfig.f_zoomx);
//		mailRedFloor_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.mailRedFloor_x)* GameConfig.f_zoomx);
//		mail_num_x = (int) (mailRedFloor_x + 5 * GameConfig.f_zoomx);
//	
////		farm	= new Sprite(GameImage.getImage(GameStaticImage.map_farmbutton2));
////		farmArrow= new Sprite(GameImage.getImage(GameStaticImage.map_farmArrow));
//		farm_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.farm_x)* GameConfig.f_zoomx);
//		farm_y = (int) (GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.BigMapMenu.farm_y)* GameConfig.f_zoomy);
//		farmArrow_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.farmArrow_x)* GameConfig.f_zoomx);
//		farmArrow_y = (int) (GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.BigMapMenu.farmArrow_y)* GameConfig.f_zoomy);
//		
////		mapmenu_number = GameImage.getAutoSizecutBitmap(GameStaticImage.map_num_2, 11, 1, GameImage.Sort_line);
////		mapmenu_heart_number = GameImage.getAutoSizecutBitmap(GameStaticImage.map_num_3, 6, 1, GameImage.Sort_line);
//		
//	}
//
//	public void paint(Canvas canvas) {
////		clmSprite.drawBitmap(canvas, GameStaticImage.paint, 0xff25d3f2, 255, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
//		GameStaticImage.s_map_bg.drawBitmap(canvas, 0, 0, (float)GameConfig.GameScreen_Height / GameStaticImage.s_map_bg.bitmap.getWidth() , (float)GameConfig.GameScreen_Height / GameStaticImage.s_map_bg.bitmap.getHeight(), 255, 0, 0, 0);
//		float tempX = move_X;
//		float tempY = move_Y;
//		paintBackGround(canvas, tempX, tempY);
//		paintMapLevel(canvas, tempX, tempY);
//		paintKuang(canvas, tempX, tempY);
//		paintMapMenu(canvas);
//	}
//	//画地图框
//	private void paintKuang(Canvas canvas, float tempX, float tempY) {
//		GameStaticImage.s_map_kuang3.drawBitmap(canvas, 0 + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				0 + bgy + tempY - Configs.GameMapHeight/2 * r, r, r, 255, 0, 0, 0);
//		
//		canvas.save();
//		canvas.clipRect(0 + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				0 + bgy + tempY - Configs.GameMapHeight/2 * r, 
//				16 * GameConfig.f_zoomx * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				Configs.GameMapHeight * r + bgy + tempY - Configs.GameMapHeight/2 * r);
//		GameStaticImage.s_map_kuang4.drawBitmap(canvas, 0 + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				0 + bgy + tempY - Configs.GameMapHeight/2 * r, r, r, 255, 0, 0, 0);
//		canvas.restore();
//		
//		GameStaticImage.s_map_kuang3.drawBitmap(canvas, 0 + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				Configs.GameMapHeight * r - GameStaticImage.s_map_kuang3.bitmap.getHeight() * r + bgy + tempY - Configs.GameMapHeight/2 * r, r, r, 255, 0, 0, 0);
//		canvas.save();
//		canvas.clipRect(Configs.GameMapWidth * r - GameStaticImage.s_map_kuang4.bitmap.getWidth() * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				0 + bgy + tempY - Configs.GameMapHeight/2 * r, 
//				Configs.GameMapWidth * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				Configs.GameMapHeight * r + bgy + tempY - Configs.GameMapHeight/2 * r);
//		GameStaticImage.s_map_kuang4.drawBitmap(canvas, Configs.GameMapWidth * r - GameStaticImage.s_map_kuang4.bitmap.getWidth() * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				0 + bgy + tempY - Configs.GameMapHeight/2 * r, r, r, 255, 0, 0, 0);
//		canvas.restore();
//	}
//	
//	//绘制关卡地图
//	private void paintMapLevel(Canvas canvas, float tempX, float tempY) {
//		//绘制路线点
//		for(int i=0; i<dot_d.length; i++) {
//			int aphla = 255, R = 0,G = 0,B = 0;//argb暂用一个值来代替
//			if (VeggiesData.getGameGuanka()[i+1] <0) {
//				aphla = 133;
//				R = G = B = -255;
//			}
//			for (int j=0; j<dot_d[i].length; j++) {	
//				b = new BigDecimal(dot_d[i][j][2]-1);
//				if (isInScreen(getRectF(dot_d[i][j][0] * r + bgx + tempX - GameStaticImage.s_map_dot[b.setScale(0).intValue()].bitmap.getWidth()/2*r, dot_d[i][j][1]*r + bgy+tempY-GameStaticImage.s_map_dot[b.setScale(0).intValue()].bitmap.getHeight()/2*r, GameStaticImage.s_map_dot[b.setScale(0).intValue()].bitmap.getWidth()*r, GameStaticImage.s_map_dot[b.setScale(0).intValue()].bitmap.getHeight()*r))) {
//					GameStaticImage.s_map_dot[b.setScale(0).intValue()].drawBitmap(canvas, dot_d[i][j][0] * r + bgx + tempX - GameStaticImage.s_map_dot[b.setScale(0).intValue()].bitmap.getWidth()/2*r, dot_d[i][j][1]*r + bgy+tempY-GameStaticImage.s_map_dot[b.setScale(0).intValue()].bitmap.getHeight()/2*r, r, r, aphla,0,0,0, R,G,B);					
//				}
//			}			
//		}
//		//绘制关卡
//		for(int i=0; i<button_d.length; i++) {
//			int j = 0;
//			if (i == 59 || i == 29) j = 3;
//			float temp_x = button_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_button[j].bitmap.getWidth()/2*r * (anjian_level[i] ? 1.2f : 1.0f);
//			float temp_y = button_d[i][1] * r + bgy + tempY - GameStaticImage.s_map_button[j].bitmap.getHeight()/2*r * (anjian_level[i] ? 1.2f : 1.0f);
//			int aphla = 255, R = 0,G = 0,B = 0;//argb暂用一个值来代替
//			if (VeggiesData.getGameGuanka()[i] <0) {
//				aphla = 133;
//				R = G = B = -255;
//			}
////			if (VeggiesData.GameGuanka[i] == -1) {
////				if (i == 59 || i == 29) j = 2;
////				else j = 1;
////			} else {
//				if (i == 59 || i == 29) j = 3;
//				else j = 0;
////			}
//				if (isInScreen(getRectF(temp_x, temp_y, GameStaticImage.s_map_button[j].bitmap.getWidth(), GameStaticImage.s_map_button[j].bitmap.getHeight()))) {
//					GameStaticImage.s_map_button[j].drawBitmap(canvas, temp_x, temp_y, r * (anjian_level[i] ? 1.2f : 1.0f), r * (anjian_level[i] ? 1.2f : 1.0f), aphla,0,0,0, R,G,B);					
//				}
//			if (i+1 < 10 ) {
//				temp_x = button_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_button[j].bitmap.getWidth()/2*r + GameStaticImage.s_map_button[j].bitmap.getWidth()/2*r - GameStaticImage.s_map_num_1[0].bitmap.getWidth()/2*r * (anjian_level[i] ? 1.2f : 1.0f);					
//			} else {
//				temp_x = button_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_button[j].bitmap.getWidth()/2*r + GameStaticImage.s_map_button[j].bitmap.getWidth()/2*r - GameStaticImage.s_map_num_1[0].bitmap.getWidth()*r * (anjian_level[i] ? 1.2f : 1.0f);
//			}
//			temp_y = button_d[i][1] * r + bgy + tempY - GameStaticImage.s_map_button[j].bitmap.getHeight()/2*r + GameStaticImage.s_map_button[j].bitmap.getHeight()/2*r - GameStaticImage.s_map_num_1[0].bitmap.getHeight()/2*r * (anjian_level[i] ? 1.2f : 1.0f);				
//			if (VeggiesData.getGameGuanka()[i] >= 0) {
//				if (isInScreen(getRectF(temp_x, temp_y, GameStaticImage.s_map_button[j].bitmap.getWidth(), GameStaticImage.s_map_button[j].bitmap.getHeight()))) {//数字的宽高用关卡按钮宽高代替
//					clmSprite.drawBitmap(canvas, GameStaticImage.s_map_num_1, (int)(temp_x), (int)(temp_y) , GameConfig.Char_num1, Integer.toString(i+1), null, 0 , r * (anjian_level[i] ? 1.2f : 1.0f));					
//				}
//			}
//		}
//		//绘制星数
//		for (int i=0; i<button_d.length; i++) {
//			int j = 0;
//			if (i == 59 || i == 29) j = 3;
//			float temp_x = button_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_button[j].bitmap.getWidth()/2 * r + GameStaticImage.s_map_button[j].bitmap.getWidth()/2 * r;
//			float temp_y = button_d[i][1] * r + bgy + tempY - GameStaticImage.s_map_button[j].bitmap.getHeight()/2 * r + GameStaticImage.s_map_button[j].bitmap.getHeight() * r - 10 * GameConfig.f_zoomy * r;
//			switch(VeggiesData.getGameGuanka()[i]) {
//			case 0:
//				if (isInScreen(getRectF(temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*1.5f*r, temp_y, GameStaticImage.s_map_star[0].bitmap.getWidth(), GameStaticImage.s_map_star[0].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[0].drawBitmap(canvas, temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*1.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				if (isInScreen(getRectF(temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, GameStaticImage.s_map_star[0].bitmap.getWidth(), GameStaticImage.s_map_star[0].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[0].drawBitmap(canvas, temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				if (isInScreen(getRectF(temp_x + GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, GameStaticImage.s_map_star[0].bitmap.getWidth(), GameStaticImage.s_map_star[0].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[0].drawBitmap(canvas, temp_x + GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				break;
//			case 1:
//				if (isInScreen(getRectF(temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*1.5f*r, temp_y, GameStaticImage.s_map_star[1].bitmap.getWidth(), GameStaticImage.s_map_star[1].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[1].drawBitmap(canvas, temp_x - GameStaticImage.s_map_star[1].bitmap.getWidth()*1.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				if (isInScreen(getRectF(temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, GameStaticImage.s_map_star[0].bitmap.getWidth(), GameStaticImage.s_map_star[0].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[0].drawBitmap(canvas, temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				if (isInScreen(getRectF(temp_x + GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, GameStaticImage.s_map_star[0].bitmap.getWidth(), GameStaticImage.s_map_star[0].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[0].drawBitmap(canvas, temp_x + GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);									
//				}
//				break;
//			case 2:
//				if (isInScreen(getRectF(temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*1.5f*r, temp_y, GameStaticImage.s_map_star[1].bitmap.getWidth(), GameStaticImage.s_map_star[1].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[1].drawBitmap(canvas, temp_x - GameStaticImage.s_map_star[1].bitmap.getWidth()*1.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				if (isInScreen(getRectF(temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, GameStaticImage.s_map_star[2].bitmap.getWidth(), GameStaticImage.s_map_star[2].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[2].drawBitmap(canvas, temp_x - GameStaticImage.s_map_star[2].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				if (isInScreen(getRectF(temp_x + GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, GameStaticImage.s_map_star[0].bitmap.getWidth(), GameStaticImage.s_map_star[0].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[0].drawBitmap(canvas, temp_x + GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);									
//				}
//				break;
//			case 3:
//				if (isInScreen(getRectF(temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*1.5f*r, temp_y, GameStaticImage.s_map_star[1].bitmap.getWidth(), GameStaticImage.s_map_star[1].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[1].drawBitmap(canvas, temp_x - GameStaticImage.s_map_star[1].bitmap.getWidth()*1.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				if (isInScreen(getRectF(temp_x - GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, GameStaticImage.s_map_star[2].bitmap.getWidth(), GameStaticImage.s_map_star[2].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[2].drawBitmap(canvas, temp_x - GameStaticImage.s_map_star[2].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);					
//				}
//				if (isInScreen(getRectF(temp_x + GameStaticImage.s_map_star[0].bitmap.getWidth()*0.5f*r, temp_y, GameStaticImage.s_map_star[3].bitmap.getWidth(), GameStaticImage.s_map_star[3].bitmap.getHeight()))) {
//					GameStaticImage.s_map_star[3].drawBitmap(canvas, temp_x + GameStaticImage.s_map_star[3].bitmap.getWidth()*0.5f*r, temp_y, r, r, 255, 0, 0, 0);									
//				}
//				break;
//			}
//		}
//		//绘制关键boss关卡和奖励关卡
////		if(VeggiesData.getGameGuanka()[29] >= 0) {
////			float temp_x = button_d[29][0] * r + bgx + tempX - GameStaticImage.s_map_button[3].bitmap.getWidth()/2 * r + GameStaticImage.s_map_button[3].bitmap.getWidth() * r - 10 * GameConfig.f_zoomx * r;
////			float temp_y = button_d[29][1] * r + bgy + tempY - GameStaticImage.s_map_button[3].bitmap.getHeight()/2 * r + GameStaticImage.s_map_button[3].bitmap.getHeight()/2 * r - GameStaticImage.s_map_enemy_01.bitmap.getHeight() / 2 * r;
//////			if (isInScreen(getRectF(temp_x, temp_y, GameStaticImage.s_map_enemy_01.bitmap.getWidth() * r, GameStaticImage.s_map_enemy_01.bitmap.getHeight() * r))) {
//////				GameStaticImage.s_map_enemy_01.drawBitmap(canvas, temp_x, temp_y, r , r , 255,0,0,0);				
//////			}
////		}
////		if(VeggiesData.getGameGuanka()[59] >= 0) {
////			float temp_x = button_d[59][0] * r + bgx + tempX - GameStaticImage.s_map_button[3].bitmap.getWidth()/2 * r + GameStaticImage.s_map_button[3].bitmap.getWidth() * r - 10 * GameConfig.f_zoomx * r;
////			float temp_y = button_d[59][1] * r + bgy + tempY - GameStaticImage.s_map_button[3].bitmap.getHeight()/2 * r + GameStaticImage.s_map_button[3].bitmap.getHeight()/2 * r - GameStaticImage.s_map_enemy_02.bitmap.getHeight() / 2 * r;
//////			if (isInScreen(getRectF(temp_x, temp_y, GameStaticImage.s_map_enemy_02.bitmap.getWidth() * r, GameStaticImage.s_map_enemy_02.bitmap.getHeight() * r))) {
//////				GameStaticImage.s_map_enemy_02.drawBitmap(canvas, temp_x, temp_y, r , r , 255,0,0,0);				
//////			}
////		}
////		if(reward_level >= 0) {//奖励关卡
////			float temp_x = button_d[reward_level][0] * r + bgx + tempX - GameStaticImage.s_map_button[0].bitmap.getWidth()/2 * r + GameStaticImage.s_map_button[0].bitmap.getWidth() * r - 10 * GameConfig.f_zoomx * r;
////			float temp_y = button_d[reward_level][1] * r + bgy + tempY - GameStaticImage.s_map_button[0].bitmap.getHeight()/2 * r + GameStaticImage.s_map_button[0].bitmap.getHeight()/2 * r - GameStaticImage.s_map_enemy_04.bitmap.getHeight() / 2 * r;
//////			if (isInScreen(getRectF(temp_x, temp_y, GameStaticImage.s_map_enemy_04.bitmap.getWidth() * r, GameStaticImage.s_map_enemy_04.bitmap.getHeight() * r))) {
//////				GameStaticImage.s_map_enemy_04.drawBitmap(canvas, temp_x, temp_y, r , r , 255,0,0,0);				
//////			}
////		}
//		
//		//绘制头像
////		if (VeggiesData.getCurrentLevel() >= 0) {
////			int j = 0;
////			if (VeggiesData.getCurrentLevel() == 59 || VeggiesData.getCurrentLevel() == 29) j = 3;
////			float temp_x = button_d[VeggiesData.getCurrentLevel()][0] * r + bgx + tempX - GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r;
////			float temp_y = button_d[VeggiesData.getCurrentLevel()][1] * r + bgy + tempY - GameStaticImage.s_map_button[j].bitmap.getHeight()/2 * r - GameStaticImage.s_map_ren_2.bitmap.getHeight()*r + 5*GameConfig.f_zoomy*r;
////			if (friend>0) {
////				if (friend == 1) {
////					if (isInScreen(getRectF(temp_x + (0) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, GameStaticImage.s_map_ren_2.bitmap.getWidth()*r, GameStaticImage.s_map_ren_2.bitmap.getHeight()*r))) {
////						GameStaticImage.s_map_ren_2.drawBitmap(canvas, temp_x + (0) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, r , r , 255,0,0,0);															
////					}
////				} else if (friend == 2) {
////					if (isInScreen(getRectF(temp_x + (-0.5f) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, GameStaticImage.s_map_ren_2.bitmap.getWidth()*r, GameStaticImage.s_map_ren_2.bitmap.getHeight()*r))) {
////						GameStaticImage.s_map_ren_2.drawBitmap(canvas, temp_x + (-0.5f) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, r , r , 255,0,0,0);																				
////					}
////					if (isInScreen(getRectF(temp_x + (0.5f) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, GameStaticImage.s_map_ren_2.bitmap.getWidth()*r, GameStaticImage.s_map_ren_2.bitmap.getHeight()*r))) {
////						GameStaticImage.s_map_ren_2.drawBitmap(canvas, temp_x + (0.5f) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, r , r , 255,0,0,0);																				
////					}
////				} else if (friend >= 3) {
////					if (isInScreen(getRectF(temp_x + (-1) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, GameStaticImage.s_map_ren_2.bitmap.getWidth()*r, GameStaticImage.s_map_ren_2.bitmap.getHeight()*r))) {
////						GameStaticImage.s_map_ren_2.drawBitmap(canvas, temp_x + (-1) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, r , r , 255,0,0,0);																				
////					}
////					if (isInScreen(getRectF(temp_x + (0) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, GameStaticImage.s_map_ren_2.bitmap.getWidth()*r, GameStaticImage.s_map_ren_2.bitmap.getHeight()*r))) {
////						GameStaticImage.s_map_ren_2.drawBitmap(canvas, temp_x + (0) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, r , r , 255,0,0,0);						
////					}
////					if (isInScreen(getRectF(temp_x + (1) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, GameStaticImage.s_map_ren_2.bitmap.getWidth()*r, GameStaticImage.s_map_ren_2.bitmap.getHeight()*r))) {
////						GameStaticImage.s_map_ren_2.drawBitmap(canvas, temp_x + (1) * GameStaticImage.s_map_ren_2.bitmap.getWidth()/2*r, temp_y + 10 * GameConfig.f_zoomy * r, r , r , 255,0,0,0);						
////					}
////				}
////				
////			}
////			temp_x = button_d[VeggiesData.getCurrentLevel()][0] * r + bgx + tempX - GameStaticImage.s_map_button[j].bitmap.getWidth()/2 * r - GameStaticImage.s_map_ren_1.bitmap.getWidth()*r;
////			temp_y = button_d[VeggiesData.getCurrentLevel()][1] * r + bgy + tempY - GameStaticImage.s_map_ren_1.bitmap.getHeight()/2*r;
////			if (isInScreen(getRectF(temp_x + 10 * GameConfig.f_zoomx * r, temp_y, GameStaticImage.s_map_ren_1.bitmap.getWidth() * r, GameStaticImage.s_map_ren_1.bitmap.getHeight() * r))) {
////				GameStaticImage.s_map_ren_1.drawBitmap(canvas, temp_x + 10 * GameConfig.f_zoomx * r, temp_y, r , r , 255,0,0,0);				
////			}
////		}
//	}
//
//	private void paintBackGround(Canvas canvas, float tempX, float tempY) {
//		if (isInScreen(new RectF(bg1_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				bg1_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//				bg1_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				bg1_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r))) {
//			paintBackgroundColor(canvas, 
//					bg1_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//					bg1_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//					bg1_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//					bg1_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,Color.rgb(250, 227, 117),false);
//		}
//		if (isInScreen(new RectF(bg2_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				bg2_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//				bg2_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				bg2_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r))) {			
//			paintBackgroundColor(canvas, 
//					bg2_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//					bg2_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//					bg2_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//					bg2_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,Color.rgb(37, 174, 242),false);
//		}
//		if (isInScreen(getRectF(coast_x*r +bgx+ tempX - GameStaticImage.s_map_seabeach.bitmap.getWidth()/2*r, coast_y*r +bgy+ tempY - GameStaticImage.s_map_seabeach.bitmap.getHeight()/2*r, GameStaticImage.s_map_seabeach.bitmap.getWidth(), GameStaticImage.s_map_seabeach.bitmap.getHeight())))
//			GameStaticImage.s_map_seabeach.drawBitmap(canvas, coast_x*r +bgx+ tempX - GameStaticImage.s_map_seabeach.bitmap.getWidth()/2*r, coast_y*r +bgy+ tempY - GameStaticImage.s_map_seabeach.bitmap.getHeight()/2*r, r, r, 255, 0, 0, 0);
//		
//		canvas.save();
//		//先遮罩
//		canvas.clipRect(0 + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				0 + bgy + tempY - Configs.GameMapHeight/2 * r, 
//				Configs.GameMapWidth * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				600 * GameConfig.f_zoomy * r + bgy + tempY - Configs.GameMapHeight/2 * r);
//		for(int i=0; i<green_2_d.length;i++) {
//			if (i == 0) {
//				if (isInScreen(getRectF(green_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_green_2.bitmap.getWidth()/2*r, green_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_green_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_green_2.bitmap.getWidth()*r, GameStaticImage.s_map_green_2.bitmap.getHeight()*r))) {					
//					GameStaticImage.s_map_green_2.drawBitmap(canvas, green_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_green_2.bitmap.getWidth()/2*r, green_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_green_2.bitmap.getHeight()/2*r, -r*green_2_d[i][2], r*green_2_d[i][2], 255,0,0,0);				
//				}
//			} else {
//				if (isInScreen(getRectF(green_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_green_2.bitmap.getWidth()/2*r, green_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_green_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_green_2.bitmap.getWidth()*r, GameStaticImage.s_map_green_2.bitmap.getHeight()*r))) {
//					GameStaticImage.s_map_green_2.drawBitmap(canvas, green_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_green_2.bitmap.getWidth()/2*r, green_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_green_2.bitmap.getHeight()/2*r, r*green_2_d[i][2], r*green_2_d[i][2], 255,0,0,0);									
//				}
//			}
//			
//		}
//		canvas.restore();
//		for(int i=0; i<green_1_d.length;i++) {
//			if (isInScreen(getRectF(green_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_green_1.bitmap.getWidth()/2*r, green_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_green_1.bitmap.getHeight()/2*r, GameStaticImage.s_map_green_1.bitmap.getWidth()*r, GameStaticImage.s_map_green_1.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_green_1.drawBitmap(canvas, green_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_green_1.bitmap.getWidth()/2*r, green_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_green_1.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<yellow_2_d.length;i++) {
//			if (isInScreen(getRectF(yellow_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_yellow_2.bitmap.getWidth()/2*r, yellow_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_yellow_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_yellow_2.bitmap.getWidth()*r, GameStaticImage.s_map_yellow_2.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_yellow_2.drawBitmap(canvas, yellow_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_yellow_2.bitmap.getWidth()/2*r, yellow_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_yellow_2.bitmap.getHeight()/2*r, r*yellow_2_d[i][2], r*yellow_2_d[i][2], 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<dune_d.length;i++) {
//			if (isInScreen(getRectF(dune_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_dune.bitmap.getWidth()/2*r, dune_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_dune.bitmap.getHeight()/2*r, GameStaticImage.s_map_dune.bitmap.getWidth()*r, GameStaticImage.s_map_dune.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_dune.drawBitmap(canvas, dune_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_dune.bitmap.getWidth()/2*r, dune_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_dune.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<castle_d.length;i++) {
//			if (isInScreen(getRectF(castle_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_castle.bitmap.getWidth()/2*r, castle_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_castle.bitmap.getHeight()/2*r, GameStaticImage.s_map_castle.bitmap.getWidth()*r, GameStaticImage.s_map_castle.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_castle.drawBitmap(canvas, castle_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_castle.bitmap.getWidth()/2*r, castle_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_castle.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		if (GameStaticImage.s_map_stump != null) {
//			if (isInScreen(getRectF(stump_x*r +bgx+ tempX - GameStaticImage.s_map_stump.bitmap.getWidth()/2*r, stump_y*r +bgy+ tempY - GameStaticImage.s_map_stump.bitmap.getHeight()/2*r, GameStaticImage.s_map_stump.bitmap.getWidth()*r, GameStaticImage.s_map_stump.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_stump.drawBitmap(canvas, stump_x*r +bgx+ tempX - GameStaticImage.s_map_stump.bitmap.getWidth()/2*r, stump_y*r +bgy+ tempY - GameStaticImage.s_map_stump.bitmap.getHeight()/2*r, r, r, 255, 0, 0, 0);				
//			}
//		}
//		if (GameStaticImage.s_map_shell != null) {
//			if (isInScreen(getRectF(shell_x*r +bgx+ tempX - GameStaticImage.s_map_shell.bitmap.getWidth()/2*r, shell_y*r +bgy+ tempY - GameStaticImage.s_map_shell.bitmap.getHeight()/2*r, GameStaticImage.s_map_shell.bitmap.getWidth()*r, GameStaticImage.s_map_shell.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_shell.drawBitmap(canvas, shell_x*r +bgx+ tempX - GameStaticImage.s_map_shell.bitmap.getWidth()/2*r, shell_y*r +bgy+ tempY - GameStaticImage.s_map_shell.bitmap.getHeight()/2*r, r, r, 255, 0, 0, 0);				
//			}
//		}
//		if (GameStaticImage.s_map_hill_1 != null) {
//			if (isInScreen(getRectF(hill_1_x*r +bgx+ tempX - GameStaticImage.s_map_hill_1.bitmap.getWidth()/2*r, hill_1_y*r +bgy+ tempY - GameStaticImage.s_map_hill_1.bitmap.getHeight()/2*r, GameStaticImage.s_map_hill_1.bitmap.getWidth()*r, GameStaticImage.s_map_hill_1.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_hill_1.drawBitmap(canvas, hill_1_x*r +bgx+ tempX - GameStaticImage.s_map_hill_1.bitmap.getWidth()/2*r, hill_1_y*r +bgy+ tempY - GameStaticImage.s_map_hill_1.bitmap.getHeight()/2*r, r, r, 255, 0, 0, 0);				
//			}
//		}
//		for(int i=0; i<hill_2_d.length;i++) {
//			if (isInScreen(getRectF(hill_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_hill_2.bitmap.getWidth()/2*r, hill_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_hill_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_hill_2.bitmap.getWidth()*r, GameStaticImage.s_map_hill_2.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_hill_2.drawBitmap(canvas, hill_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_hill_2.bitmap.getWidth()/2*r, hill_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_hill_2.bitmap.getHeight()/2*r, r*hill_2_d[i][2], r*hill_2_d[i][2], 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<stone_2_d.length;i++) {
//			if (isInScreen(getRectF(stone_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_stone_2.bitmap.getWidth()/2*r, stone_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_stone_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_stone_2.bitmap.getWidth()*r, GameStaticImage.s_map_stone_2.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_stone_2.drawBitmap(canvas, stone_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_stone_2.bitmap.getWidth()/2*r, stone_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_stone_2.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<tree_1_d.length;i++) {
//			if (isInScreen(getRectF(tree_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_tree_1.bitmap.getWidth()/2*r, tree_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_tree_1.bitmap.getHeight()/2*r, GameStaticImage.s_map_tree_1.bitmap.getWidth()*r, GameStaticImage.s_map_tree_1.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_tree_1.drawBitmap(canvas, tree_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_tree_1.bitmap.getWidth()/2*r, tree_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_tree_1.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<tree_2_d.length;i++) {
//			if (isInScreen(getRectF(tree_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_tree_2.bitmap.getWidth()/2*r, tree_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_tree_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_tree_2.bitmap.getWidth()*r, GameStaticImage.s_map_tree_2.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_tree_2.drawBitmap(canvas, tree_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_tree_2.bitmap.getWidth()/2*r, tree_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_tree_2.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<grass_d.length;i++) {
//			if (isInScreen(getRectF(grass_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_grass.bitmap.getWidth()/2*r, grass_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_grass.bitmap.getHeight()/2*r, GameStaticImage.s_map_grass.bitmap.getWidth()*r, GameStaticImage.s_map_grass.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_grass.drawBitmap(canvas, grass_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_grass.bitmap.getWidth()/2*r, grass_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_grass.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<flower_d.length;i++) {
//			if (isInScreen(getRectF(flower_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_flower.bitmap.getWidth()/2*r, flower_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_flower.bitmap.getHeight()/2*r, GameStaticImage.s_map_flower.bitmap.getWidth()*r, GameStaticImage.s_map_flower.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_flower.drawBitmap(canvas, flower_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_flower.bitmap.getWidth()/2*r, flower_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_flower.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<seastar_d.length;i++) {
//			if (isInScreen(getRectF(seastar_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_seastar.bitmap.getWidth()/2*r, seastar_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_seastar.bitmap.getHeight()/2*r, GameStaticImage.s_map_seastar.bitmap.getWidth()*r, GameStaticImage.s_map_seastar.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_seastar.drawBitmap(canvas, seastar_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_seastar.bitmap.getWidth()/2*r, seastar_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_seastar.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<fish_d.length;i++) {
//			if (isInScreen(getRectF(fish_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_fish.bitmap.getWidth()/2*r, fish_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_fish.bitmap.getHeight()/2*r, GameStaticImage.s_map_fish.bitmap.getWidth()*r, GameStaticImage.s_map_fish.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_fish.drawBitmap(canvas, fish_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_fish.bitmap.getWidth()/2*r, fish_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_fish.bitmap.getHeight()/2*r, r*fish_d[i][2], r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<bubble_d.length;i++) {
//			if (isInScreen(getRectF(bubble_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_bubble.bitmap.getWidth()/2*r, bubble_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_bubble.bitmap.getHeight()/2*r, GameStaticImage.s_map_bubble.bitmap.getWidth()*r, GameStaticImage.s_map_bubble.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_bubble.drawBitmap(canvas, bubble_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_bubble.bitmap.getWidth()/2*r, bubble_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_bubble.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<water_wave_d.length;i++) {
//			if (isInScreen(getRectF(water_wave_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_water_wave.bitmap.getWidth()/2*r, water_wave_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_water_wave.bitmap.getHeight()/2*r, GameStaticImage.s_map_water_wave.bitmap.getWidth()*r, GameStaticImage.s_map_water_wave.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_water_wave.drawBitmap(canvas, water_wave_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_water_wave.bitmap.getWidth()/2*r, water_wave_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_water_wave.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<vortex_d.length;i++) {
//			if (isInScreen(getRectF(vortex_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_vortex.bitmap.getWidth()/2*r, vortex_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_vortex.bitmap.getHeight()/2*r, GameStaticImage.s_map_vortex.bitmap.getWidth()*r, GameStaticImage.s_map_vortex.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_vortex.drawBitmap(canvas, vortex_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_vortex.bitmap.getWidth()/2*r, vortex_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_vortex.bitmap.getHeight()/2*r, r*vortex_d[i][2], r*vortex_d[i][2], 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<stone_1_d.length;i++) {
//			if (isInScreen(getRectF(stone_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_stone_1.bitmap.getWidth()/2*r, stone_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_stone_1.bitmap.getHeight()/2*r, GameStaticImage.s_map_stone_1.bitmap.getWidth()*r, GameStaticImage.s_map_stone_1.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_stone_1.drawBitmap(canvas, stone_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_stone_1.bitmap.getWidth()/2*r, stone_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_stone_1.bitmap.getHeight()/2*r, r*stone_1_d[i][2], r*stone_1_d[i][2], 255,0,0,0);				
//			}
//		}
//		for(int i=0; i<island_1_d.length;i++) {
//			if (isInScreen(getRectF(island_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_island_1.bitmap.getWidth()/2*r, island_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_island_1.bitmap.getHeight()/2*r, GameStaticImage.s_map_island_1.bitmap.getWidth()*r, GameStaticImage.s_map_island_1.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_island_1.drawBitmap(canvas, island_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_island_1.bitmap.getWidth()/2*r, island_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_island_1.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		if (isInScreen(getRectF(island_2_d[0][0] * r + bgx + tempX - GameStaticImage.s_map_island_2.bitmap.getWidth()/2*r, island_2_d[0][1]*r + bgy+tempY-GameStaticImage.s_map_island_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_island_2.bitmap.getWidth()*r, GameStaticImage.s_map_island_2.bitmap.getHeight()*r))) {
//			GameStaticImage.s_map_island_2.drawBitmap(canvas, island_2_d[0][0] * r + bgx + tempX - GameStaticImage.s_map_island_2.bitmap.getWidth()/2*r, island_2_d[0][1]*r + bgy+tempY-GameStaticImage.s_map_island_2.bitmap.getHeight()/2*r, r, r, 255,0,0,0);			
//		}
//		for(int i=0; i<sea_tree_1_d.length;i++) {
//			if (isInScreen(getRectF(sea_tree_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_tree_1.bitmap.getWidth()/2*r, sea_tree_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_tree_1.bitmap.getHeight()/2*r, GameStaticImage.s_map_tree_1.bitmap.getWidth()*r, GameStaticImage.s_map_tree_1.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_tree_1.drawBitmap(canvas, sea_tree_1_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_tree_1.bitmap.getWidth()/2*r, sea_tree_1_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_tree_1.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
//		}
//		if (isInScreen(getRectF(sea_tree_2_x * r + bgx + tempX - GameStaticImage.s_map_tree_2.bitmap.getWidth()/2*r, sea_tree_2_y*r + bgy+tempY-GameStaticImage.s_map_tree_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_tree_2.bitmap.getWidth()*r, GameStaticImage.s_map_tree_2.bitmap.getHeight()*r))) {
//			GameStaticImage.s_map_tree_2.drawBitmap(canvas, sea_tree_2_x * r + bgx + tempX - GameStaticImage.s_map_tree_2.bitmap.getWidth()/2*r, sea_tree_2_y*r + bgy+tempY-GameStaticImage.s_map_tree_2.bitmap.getHeight()/2*r, r, r, 255,0,0,0);			
//		}
//		if (isInScreen(getRectF(sea_grass_x * r + bgx + tempX - GameStaticImage.s_map_grass.bitmap.getWidth()/2*r, sea_grass_y*r + bgy+tempY-GameStaticImage.s_map_grass.bitmap.getHeight()/2*r, GameStaticImage.s_map_grass.bitmap.getWidth()*r, GameStaticImage.s_map_grass.bitmap.getHeight()*r))) {
//			GameStaticImage.s_map_grass.drawBitmap(canvas, sea_grass_x * r + bgx + tempX - GameStaticImage.s_map_grass.bitmap.getWidth()/2*r, sea_grass_y*r + bgy+tempY-GameStaticImage.s_map_grass.bitmap.getHeight()/2*r, r, r, 255,0,0,0);			
//		}
//		for(int i=0; i<ship_2_d.length;i++) {
//			if (isInScreen(getRectF(ship_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_ship_2.bitmap.getWidth()/2*r, ship_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_ship_2.bitmap.getHeight()/2*r, GameStaticImage.s_map_ship_2.bitmap.getWidth()*r, GameStaticImage.s_map_ship_2.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_ship_2.drawBitmap(canvas, ship_2_d[i][0] * r + bgx + tempX - GameStaticImage.s_map_ship_2.bitmap.getWidth()/2*r, ship_2_d[i][1]*r + bgy+tempY-GameStaticImage.s_map_ship_2.bitmap.getHeight()/2*r, r, r, 255,0,0,0);				
//			}
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
//		if (GameStaticImage.s_map_ship_1 != null)
//			if (isInScreen(getRectF(ship_1_x * r + bgx + tempX - GameStaticImage.s_map_ship_1.bitmap.getWidth()/2*r, ship_1_y*r + bgy+tempY-GameStaticImage.s_map_ship_1.bitmap.getHeight()/2*r - boatH * GameConfig.f_zoomy, GameStaticImage.s_map_ship_1.bitmap.getWidth()*r, GameStaticImage.s_map_ship_1.bitmap.getHeight()*r))) {
//				GameStaticImage.s_map_ship_1.drawBitmap(canvas, ship_1_x * r + bgx + tempX - GameStaticImage.s_map_ship_1.bitmap.getWidth()/2*r, ship_1_y*r + bgy+tempY-GameStaticImage.s_map_ship_1.bitmap.getHeight()/2*r - boatH * GameConfig.f_zoomy, r, r, 255,0,0,0);				
//			}
//		
//		//章鱼
//		int tempi = 0;
//		if (GameConfig.i_coke % 32 < 32) {
//			tempi = GameConfig.i_coke % 32 / 4 % 4; 
//			if (tempi == 3) tempi = 1;
//			//先不场景
////			if (VeggiesData.GameGuanka[14] < 0) {
////				tempi = 0;
////			} else {
//				if (octopus_aphla != 255) {
//					octopus_aphla = 255; 
//					octopus_R = octopus_G = octopus_B = 0;
//				}
////			}
//			if (GameStaticImage.s_map_octopus != null) {
//				if (isInScreen(getRectF(octopus_x * r + bgx + tempX - GameStaticImage.s_map_octopus[0].getWidth()/2*r, 
//						octopus_y*r + bgy+tempY-GameStaticImage.s_map_octopus[0].getHeight()/2*r, GameStaticImage.s_map_octopus[0].getWidth()*r, GameStaticImage.s_map_octopus[0].getHeight()*r))) {
//					clmSprite.drawBitmap(canvas, GameStaticImage.s_map_octopus[tempi], octopus_x * r + bgx + tempX - GameStaticImage.s_map_octopus[0].getWidth()/2*r, 
//							octopus_y*r + bgy+tempY-GameStaticImage.s_map_octopus[0].getHeight()/2*r, r*octopus_r, r*octopus_r, octopus_aphla, 0f, 0f, 0f, octopus_R, octopus_G, octopus_B);									
//				}
//			}
//		}
//		
//		//云
//		//左上角
//		canvas.save();
//		canvas.clipRect(cloud_d[0][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, 
//				0 + bgy + tempY - Configs.GameMapHeight/2 * r, 
//				Configs.GameMapWidth * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				0 + bgy + tempY - Configs.GameMapHeight/2 * r + GameStaticImage.s_map_cloud.bitmap.getHeight()*r);
//		if (isInScreen(getRectF(cloud_d[0][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, cloud_d[0][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, GameStaticImage.s_map_cloud.bitmap.getWidth()*r, GameStaticImage.s_map_cloud.bitmap.getHeight()*r))) {
//			GameStaticImage.s_map_cloud.drawBitmap(canvas, cloud_d[0][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, cloud_d[0][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, r, r, 255,0,0,0);			
//		}
//		canvas.restore();
//		//中间
//		canvas.save();
//		canvas.clipRect(cloud_d[1][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, 
//				cloud_d[1][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, 
//				Configs.GameMapWidth * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				cloud_d[1][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r + GameStaticImage.s_map_cloud.bitmap.getHeight()*r);
//		if (isInScreen(getRectF(cloud_d[1][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, cloud_d[1][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, GameStaticImage.s_map_cloud.bitmap.getWidth()*r, GameStaticImage.s_map_cloud.bitmap.getHeight()*r))) {
//			GameStaticImage.s_map_cloud.drawBitmap(canvas, cloud_d[1][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, cloud_d[1][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, r, r, 255,0,0,0);			
//		}
//		canvas.restore();
//		//右下角
//		canvas.save();
//		canvas.clipRect(cloud_d[3][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, 
//				cloud_d[2][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, 
//				Configs.GameMapWidth * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				Configs.GameMapHeight * r + bgy + tempY - Configs.GameMapHeight/2 * r);
//		if (isInScreen(getRectF(cloud_d[2][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, cloud_d[2][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, GameStaticImage.s_map_cloud.bitmap.getWidth()*r, GameStaticImage.s_map_cloud.bitmap.getHeight()*r))) {
//			GameStaticImage.s_map_cloud.drawBitmap(canvas, cloud_d[2][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, cloud_d[2][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, r, r, 255,0,0,0);			
//		}
//		if (isInScreen(getRectF(cloud_d[3][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, cloud_d[3][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, GameStaticImage.s_map_cloud.bitmap.getWidth()*r, GameStaticImage.s_map_cloud.bitmap.getHeight()*r))) {
//			GameStaticImage.s_map_cloud.drawBitmap(canvas, cloud_d[3][0] * r + bgx + tempX - GameStaticImage.s_map_cloud.bitmap.getWidth()/2*r, cloud_d[3][1]*r + bgy+tempY-GameStaticImage.s_map_cloud.bitmap.getHeight()/2*r, r, r, 255,0,0,0);			
//		}
//		canvas.restore();
//		
//		//火山烟
//		tempi = 0;
////		if (VeggiesData.GameGuanka[29] == 0) {
//			if (GameConfig.i_coke % 50 < 32 && GameStaticImage.s_map_smoke != null) {
//				tempi = GameConfig.i_coke % 50 / 4 % 4; 
//				if (isInScreen(getRectF(smoke_x * r + bgx + tempX - GameStaticImage.s_map_smoke[0].getWidth()/2*r, 
//						smoke_y*r + bgy+tempY-GameStaticImage.s_map_smoke[0].getHeight()/2*r, GameStaticImage.s_map_smoke[0].getWidth()*r, GameStaticImage.s_map_smoke[0].getHeight()*r))) {
//					clmSprite.drawBitmap(canvas, GameStaticImage.s_map_smoke[tempi],smoke_x * r + bgx + tempX - GameStaticImage.s_map_smoke[0].getWidth()/2*r, 
//							smoke_y*r + bgy+tempY-GameStaticImage.s_map_smoke[0].getHeight()/2*r, r, r, 255, 0, 0, 0,0,0,0);					
//				}
//			}
////		}
//	}
//	
//	//大选关菜单界面
//	private void paintMapMenu(Canvas canvas) {
////		GameStaticImage.s_map_back.drawBitmap(canvas, Location.BigMapMenu.goldGrayFloor_x * GameConfig.f_zoomx, Location.BigMapMenu.goldGrayFloor_y *GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_back.drawBitmap(canvas, Location.BigMapMenu.gemGrayFloor_x * GameConfig.f_zoomx, Location.BigMapMenu.gemGrayFloor_y *GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_back.drawBitmap(canvas, Location.BigMapMenu.heartGrayFloor_x * GameConfig.f_zoomx, Location.BigMapMenu.heartGrayFloor_y *GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
//	
////		GameStaticImage.s_map_gold.drawBitmap(canvas, Location.BigMapMenu.gold_x * GameConfig.f_zoomx, Location.BigMapMenu.gold_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_gem.drawBitmap(canvas, Location.BigMapMenu.gem_x * GameConfig.f_zoomx, Location.BigMapMenu.gem_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_heart.drawBitmap(canvas, Location.BigMapMenu.heart_x * GameConfig.f_zoomx, Location.BigMapMenu.heart_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
//	
////		GameStaticImage.s_map_add.drawBitmap(canvas, Location.BigMapMenu.goldRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * (anjiangold ? 0.2f:0f)), Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * (anjiangold?0.2f:0f)), anjiangold?1.2f:1f, anjiangold?1.2f:1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_add.drawBitmap(canvas, Location.BigMapMenu.gemRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * (anjiangem ? 0.2f:0f)), Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * (anjiangem?0.2f:0f)), anjiangem?1.2f:1f, anjiangem?1.2f:1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_add.drawBitmap(canvas, Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * (anjianheart ? 0.2f:0f)), Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * (anjianheart?0.2f:0f)), anjianheart?1.2f:1f, anjianheart?1.2f:1f, 255, 0, 0, 0);
//		
////		if (Configs.isDebug) {
////			GameStaticImage.s_map_add.paintCollisionRect(canvas, 0, 
////					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.goldRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth() * 1.2f,
////					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight() * 1.2f);
////			GameStaticImage.s_map_add.paintCollisionRect(canvas, 0, 
////					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.gemRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth() * 1.2f,
////					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight() * 1.2f);
////			GameStaticImage.s_map_add.paintCollisionRect(canvas, 0, 
////					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth() * 1.2f,
////					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight() * 1.2f);
////			GameStaticImage.s_map_add.paintCollisionRect(canvas, storeGrayFloor_x - 15 * GameConfig.f_zoomx, //商店
////					0,//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
////					GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx, 
////					Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoomy + GameStaticImage.s_map_back.bitmap.getHeight() + 15 * GameConfig.f_zoomy);
////			GameStaticImage.s_map_add.paintCollisionRect(canvas, mail_x - 15 * GameConfig.f_zoomx, //邮件
////					Location.BigMapMenu.mail_y * GameConfig.f_zoomy - 15 * GameConfig.f_zoomy, 
////					GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx,  
////					Location.BigMapMenu.mail_y * GameConfig.f_zoomy + GameStaticImage.s_map_mail.bitmap.getHeight() + 15 * GameConfig.f_zoomy);
////			GameStaticImage.s_map_add.paintCollisionRect(canvas, farmArrow_x - 30 * GameConfig.f_zoomx, //农场
////					farmArrow_y-15*GameConfig.f_zoomy, 
////					GameConfig.GameScreen_Width, 
////					GameConfig.GameScreen_Height);
////		}
//		
////		GameStaticImage.s_map_back.drawBitmap(canvas, storeGrayFloor_x, Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_store_2.drawBitmap(canvas, storeWord_x, Location.BigMapMenu.storeWord_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_store.drawBitmap(canvas, store_x, Location.BigMapMenu.store_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
//		
////		GameStaticImage.s_map_mail.drawBitmap(canvas, mail_x, Location.BigMapMenu.mail_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_mail_2.drawBitmap(canvas, mailRedFloor_x, Location.BigMapMenu.mailRedFloor_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
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
////		GameStaticImage.s_map_farmbutton2.drawBitmap(canvas, farm_x, farm_y, 1f, 1f, 255, 0, 0, 0);
////		GameStaticImage.s_map_farmArrow.drawBitmap(canvas, farmArrow_x, farmArrow_y-farmArrowH*GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
//		
//		clmSprite.drawBitmap(canvas, GameStaticImage.s_map_num_2, (int)(Location.BigMapMenu.gold_num_x * GameConfig.f_zoomx + GameStaticImage.s_map_num_2[0].bitmap.getWidth() * (Configs.GoldMaxLength-Integer.toString(VeggiesData.getGold()).length())), (int)(Location.BigMapMenu.gold_num_y * GameConfig.f_zoomy), GameConfig.Char_num1, Integer.toString(VeggiesData.getGold()), null, 1,1);
//		clmSprite.drawBitmap(canvas, GameStaticImage.s_map_num_2, (int)(Location.BigMapMenu.gem_num_x * GameConfig.f_zoomx + GameStaticImage.s_map_num_2[0].bitmap.getWidth() * (Configs.GoldMaxLength-Integer.toString(VeggiesData.getGem()).length())), (int)(Location.BigMapMenu.gem_num_y * GameConfig.f_zoomy), GameConfig.Char_num1, Integer.toString(VeggiesData.getGem()), null, 1,1);
//		clmSprite.drawBitmap(canvas, GameStaticImage.s_map_num_3, (int)(Location.BigMapMenu.heart_num_x * GameConfig.f_zoomx ), (int)(Location.BigMapMenu.heart_num_y * GameConfig.f_zoomy), GameConfig.Char_num1, Integer.toString(VeggiesData.getHeart()), null, 1,1);
//		clmSprite.drawBitmap(canvas, GameStaticImage.s_map_num_2, (int)(Location.BigMapMenu.heart_time_x * GameConfig.f_zoomx ), (int)(Location.BigMapMenu.heart_time_y * GameConfig.f_zoomy), GameConfig.Char_num2, time_str, null, 0,1);
//		clmSprite.drawBitmap(canvas, GameStaticImage.s_map_num_2, (int)(mail_num_x), (int)(Location.BigMapMenu.mail_num_y * GameConfig.f_zoomy), GameConfig.Char_num1, mail_str, null, -2,1);
//	}
//	static boolean isGotoGLifo = false;
//	static boolean isGotoShop = false;
//	static boolean isGotoHeart = false; //充值爱心
//	static boolean isGotoMessage = false; //信息界面
//	public void run() {
//		if (isGotoGLifo) {
//			GameStaticImage.delImage();
//			GameManager.forbidModule(GameMenu.gameLevelInfoModule);
//			isGotoGLifo = false;
//			return;
//		}
//		if(isGotoHeart){
//			GameStaticImage.delImage();
//			GameManager.forbidModule(new HeartRechargeModule());
//			isGotoHeart = false;
//			return;
//		}
//		if(isGotoMessage){
//			GameStaticImage.delImage();
//			GameManager.forbidModule(new MessageModule());
//			isGotoMessage = false;
//			return;
//		}
//		
//		if (isGotoShop) {
//			GameStaticImage.delImage();
//			GameManager.forbidModule(new GameShop());
//			isGotoShop = false;
//			return;
//		}
//		
//		if (GameMenu.isBack) {
//			GameMenu.isBack = false;
//			GameStaticImage.loadMenuImage();
//			GameManager.ChangeModule(null);
//			GameStaticImage.delImage();//删除章鱼等图片
//			GameStaticImage.delSmallCard();
//			return;
//		}
//		
//		if (isTwoTouchMoveZoomBig) {
//			if (r < Configs.max_r){
//        		r += 0.05;
//        		getrrr(0.05f*Configs.GameMapWidth,0.05f*Configs.GameMapHeight,sizew,sizeh);
//        	}
//		}
//		if (isTwoTouchMoveZoomSmall) {
//			if ( (r > Configs.min_r) ){
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
////			MainActivity.getActivity().closeGame();
////			GameMenu.isBack = true;
//			GameManager.ResetToRunModule(GameMenu.gameMenu);
//		}
//		return false;
//	}
//
//	public void initwordpic() {
//		
//	}
//	
//	public void getrrr(float addW,float addH,float sizew,float sizeh) {
//		bgx += addW*(0.5f-sizew);
//		bgy += addH*(0.5f-sizeh);
//	}
//	
//	int oldDist;
//	float sizew=-1,sizeh=-1;
//	public void onTouchEvent(MotionEvent event) 
//	{
//		switch(event.getActionMasked())									//me.getActionMasked()  获取当前动作类型	
//		{
//		case MotionEvent.ACTION_DOWN:
//			startX_1 = event.getX(event.getActionIndex());			//获取第一个触点的 X 坐标
//			startY_1 = event.getY(event.getActionIndex());	
//			
//			mode1 = Configs.ONETOUCH;
//			
//			oldX = (int) startX_1;
//			oldY = (int) startY_1;
////			if (ExternalMethods.CollisionTest(startX_1, startY_1, 
////					0,//Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
////					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.goldRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth() * 1.2f,
////					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight() * 1.2f)) {
////				anjiangold = true;
////			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
////					0,//Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
////					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.gemRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth() * 1.2f,
////					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight() * 1.2f)) {
////				anjiangem = true;
////			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
////					0,//Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
////					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth() * 1.2f,
////					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight() * 1.2f)) {
////				anjianheart = true;
////			} else if (ExternalMethods.CollisionTest(startX_1, startY_1,
////					storeGrayFloor_x - 15 * GameConfig.f_zoomx, //商店
////					0,//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
////					GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx, 
////					Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoomy + GameStaticImage.s_map_back.bitmap.getHeight() + 15 * GameConfig.f_zoomy)) {
////				anjianstore = true;
////			} else if (ExternalMethods.CollisionTest(startX_1, startY_1,
////					mail_x - 15 * GameConfig.f_zoomx, //邮件
////					Location.BigMapMenu.mail_y * GameConfig.f_zoomy - 15 * GameConfig.f_zoomy, 
////					GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx,  
////					Location.BigMapMenu.mail_y * GameConfig.f_zoomy + GameStaticImage.s_map_mail.bitmap.getHeight() + 15 * GameConfig.f_zoomy)) {
////				anjianmail = true;
////			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
////					farmArrow_x - 30 * GameConfig.f_zoomx, 
////					farmArrow_y-15*GameConfig.f_zoomy, 
////					GameConfig.GameScreen_Width, 
////					GameConfig.GameScreen_Height)) {
////				anjianfarm = true;
////			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 0, 0,
////					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth() * 1.2f,
////					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight() * 1.2f)) {
////				//修正左上角触碰无响应
////				return;
////			} else {
////				for(int i=0; i<button_d.length; i++) {
////					int j = 0;
////					if (i == 59 || i == 29) j = 3;
////					float temp_x = button_d[i][0] * r + bgx + move_X - GameStaticImage.s_map_button[j].bitmap.getWidth()/2*r * 1.2f;
////					float temp_y = button_d[i][1] * r + bgy + move_Y - GameStaticImage.s_map_button[j].bitmap.getHeight()/2*r * 1.2f;
////					if (ExternalMethods.CollisionTest(startX_1, startY_1, 
////						temp_x, temp_y,
////						temp_x + GameStaticImage.s_map_button[j].bitmap.getWidth() * r * 1.2f,
////						temp_y + GameStaticImage.s_map_button[j].bitmap.getHeight() * r * 1.2f)) {
////						if (VeggiesData.getGameGuanka()[i] >= 0) {
////							anjian_level[i] = true;								
////						}
////					}
////				}
////			}
//			break;
//		case MotionEvent.ACTION_UP:	
//			startX_1 = event.getX(event.getActionIndex());
//			startY_1 = event.getY(event.getActionIndex());
//			
//			mode1 = Configs.NONE;
//			isTwoTouchMoveZoomBig = false;
//        	isTwoTouchMoveZoomSmall = false;
//        	
////			if (anjiangold && ExternalMethods.CollisionTest(startX_1, startY_1, 
////					0,//Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
////					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.goldRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth()/2 * 1.2f,
////					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight()/2 * 1.2f)) {
////				sendMessage("金币充值被点击了");
////			} else if (anjiangem && ExternalMethods.CollisionTest(startX_1, startY_1, 
////					0,//Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
////					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.gemRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth()/2 * 1.2f,
////					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight()/2 * 1.2f)) {					
////				sendMessage("宝石充值被点击了");
////			} else if (anjianheart && ExternalMethods.CollisionTest(startX_1, startY_1, 
////					0,//Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
////					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth() * 1.2f,
////					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight() * 1.2f)) {
////				//图片的宽高/2 去掉了
////				sendMessage("心充值被点击了");
////				isGotoHeart = true;
////			} else if (anjianstore && ExternalMethods.CollisionTest(startX_1, startY_1,
////					storeGrayFloor_x - 15 * GameConfig.f_zoomx, //商店
////					0,//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
////					GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx, 
////					Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoomy + GameStaticImage.s_map_back.bitmap.getHeight() + 15 * GameConfig.f_zoomy)) {
////				sendMessage("商店被点击了");
////				isGotoShop = true;
////			} else if (anjianmail && ExternalMethods.CollisionTest(startX_1, startY_1,
////					mail_x - 15 * GameConfig.f_zoomx, //邮件
////					Location.BigMapMenu.mail_y * GameConfig.f_zoomy - 15 * GameConfig.f_zoomy, 
////					GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx,  
////					Location.BigMapMenu.mail_y * GameConfig.f_zoomy + GameStaticImage.s_map_mail.bitmap.getHeight() + 15 * GameConfig.f_zoomy)) {
////				sendMessage("邮件被点击了");
////				isGotoMessage = true;
////			} else if (anjianfarm && ExternalMethods.CollisionTest(startX_1, startY_1, 
////					farmArrow_x - 30 * GameConfig.f_zoomx, 
////					farmArrow_y-15*GameConfig.f_zoomy, 
////					GameConfig.GameScreen_Width, 
////					GameConfig.GameScreen_Height)) {
////				sendMessage("农场被点击了");
////				GameManager.forbidModule(new FarmModule());
////				
////			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 0, 0,
////					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth()/2 * 1.2f,
////					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight()/2 * 1.2f)) {
////				//return;
////			} else {
////				for(int i=0; i<button_d.length; i++) {
////					int j = 0;
////					if (i == 59 || i == 29) j = 3;
////					float temp_x = button_d[i][0] * r + bgx + move_X - GameStaticImage.s_map_button[j].bitmap.getWidth()/2*r * 1.2f;
////					float temp_y = button_d[i][1] * r + bgy + move_Y - GameStaticImage.s_map_button[j].bitmap.getHeight()/2*r * 1.2f;
////					if (anjian_level[i] && ExternalMethods.CollisionTest(startX_1, startY_1, 
////						temp_x, temp_y,
////						temp_x + GameStaticImage.s_map_button[j].bitmap.getWidth() * r * 1.2f,
////						temp_y + GameStaticImage.s_map_button[j].bitmap.getHeight() * r * 1.2f)) {
////						sendMessage("第 " + (i+1) + "关被点击~");
////						VeggiesData.setCurrentLevel(i+1);
//////						if (VeggiesData.getCurrentLevel() == 1) {
//////							FriendScore.isFaceBookLoginSuccess = true;
//////						} else {								
//////							FriendScore.isFaceBookLoginSuccess = false;
//////						}
////						VeggiesData.setCurrentLevel(i);
//////						GameManager.forbidModule(GameMenu.gameLevelInfoModule);
////						isGotoGLifo = true;
////					}
////				}
////			}
//			for (int i=0; i<anjian_level.length; i++) {
//				anjian_level[i] = false;
//			}
//			isOneTouchMove = true;
//			cleanAnJianState();
//			break;
//		case MotionEvent.ACTION_POINTER_DOWN:
//			startX_2 = event.getX(event.getActionIndex());    //获取第二个触点的坐标
//			startY_2 = event.getY(event.getActionIndex()); 	//获取第二个触点的坐标
//			oldDist = (int) spacing(event);  
//            if (oldDist > 10f)  
//            {  
//                mode1 = Configs.TWOTOUCH;
//                int tempx=(int)(Math.min(event.getX(0), event.getX(1))+(Math.max(event.getX(0), event.getX(1))-Math.min(event.getX(0), event.getX(1)))/2);
//                int tempy=(int)(Math.min(event.getY(0), event.getY(1))+(Math.max(event.getY(0), event.getY(1))-Math.min(event.getY(0), event.getY(1)))/2);
//                sizew=(tempx-(bgx+move_X - Configs.GameMapWidth*r/2))/(float)(Configs.GameMapWidth*r);
//                sizeh=(tempy-(bgy+move_Y- Configs.GameMapHeight*r/2))/(float)(Configs.GameMapHeight*r);
//            }
//            cleanAnJianState();
//			break;
//		case MotionEvent.ACTION_POINTER_UP: 
//			startX_2 = event.getX(event.getActionIndex());
//			startY_2 = event.getY(event.getActionIndex());
//			mode1 = Configs.NONE;
//			isTwoTouchMoveZoomBig = false;
//        	isTwoTouchMoveZoomSmall = false;
//        	cleanAnJianState();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			if (mode1 == Configs.TWOTOUCH) {
//				// 正在移动的点距初始点的距离   
//                float newDist = spacing(event);
//                if (newDist > oldDist)  
//                { 
//                	isTwoTouchMoveZoomBig = true;
//                	isTwoTouchMoveZoomSmall = false;
//                	
//                } 
//                if (newDist < oldDist)  
//                {  
//                	isTwoTouchMoveZoomBig = false;
//                	isTwoTouchMoveZoomSmall = true;      
//                	
//                }
//				
//			}
//			
//			//if (1按下的时候)
//			if (mode1 == Configs.ONETOUCH) {
//				isOneTouchMove = false;
//				
//				int tempX = (int) event.getX();
//				int tempY = (int) event.getY();
//				
//				move_X+=tempX-oldX;
//				move_Y+=tempY-oldY;
//				
//				oldX = (int) event.getX();
//				oldY = (int) event.getY();
//				
//				if (tempX - startX_1 > 5 * GameConfig.f_zoomx || tempY - startY_1 > 5 * GameConfig.f_zoomy) {
//					//三星s3机型点击的问题	(按键事件down->move->up)
//					for (int i = 0; i < anjian_level.length; i++) {
//						anjian_level[i] = false;
//					}
//					cleanAnJianState();
//				}
//				
//				int ttt=(int)(50*GameConfig.f_zoom);
//				int tempx=(int)(bgx+move_X - Configs.GameMapWidth/2 * r);
//				int tempy=(int)(bgy+move_Y - Configs.GameMapHeight/2 * r);
//				if(tempx>ttt){
//					move_X=ttt-bgx+Configs.GameMapWidth/2*r;
//				}else if(tempx<GameConfig.GameScreen_Width-Configs.GameMapWidth*r-ttt){
//					move_X = GameConfig.GameScreen_Width-Configs.GameMapWidth*r-ttt-bgx+ Configs.GameMapWidth/2*r;
//				}
//				
//				if(tempy>ttt){
//					move_Y=ttt+Configs.GameMapHeight/2 * r-bgy;
//				}else if(tempy<GameConfig.GameScreen_Height-Configs.GameMapHeight*r-ttt){
//					move_Y =GameConfig.GameScreen_Height-Configs.GameMapHeight*r-ttt-bgy+ Configs.GameMapHeight/2 * r;
//				}
//			}
//			break;
//		}
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
//    }
//    
//  //画椭圆草地、背景色
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
//    public static void sendMessage(String str) {
//    	Message msg = new Message();
//		msg.what = 1;
//		msg.obj = str;
//		Main.dialogHandler.sendMessage(msg);
//    }
//    
//    private boolean isInScreen(RectF rectF) {
//    	return ExternalMethods.RectCollision(Configs.ScreenRectF, rectF);
//    }
//    
//    private RectF getRectF(float x, float y, float w, float h) {
//		return new RectF(x, y, x + w, y + h);
//	}
//}
