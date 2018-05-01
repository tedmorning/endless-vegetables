package com.endlessvegetables2.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Message;
import android.util.FloatMath;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.facebook.FacebookOperation;
import com.facebook.FriendIcon;
import com.facebook.UserRequest;
import com.game.data.FaceBookPlayer;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMain;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Sprite;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class ChooseLevelModule2 extends Module implements FriendIcon{
	private Sprite[] mapImage;
//	private Sprite[] map_octopus;
//	private Sprite[] map_smoke;
//	private Sprite[] map_star;
//	private Sprite map_door;
//	private Sprite[] map_cloud;
//	private Sprite   map_wait;
	private MySprite mySprite;
	private MySprite mySprite2;
	private MySprite mySprite3;
//	private MySprite mySprite4;
	private BottomMenu bottom; //底部菜单
	
	public int s_smallcard_card_index;
	private Thread thread_card;
	
	Bitmap bitmap_map_card;
	Bitmap bitmap_map_card_2;
	
	private float startX_1 = 0;			//记录第一个触点开始 X 坐标
	private float startY_1 = 0;			//记录第一个触点开始 Y 坐标
	private float move_X,move_Y;
	private int oldX=-1,oldY=-1;
	private float move_X_speed,move_Y_speed;
	private float move_X_old,move_Y_old;
	private float move_r;
	private float move_r_old;
	boolean isguanxing=true;

	private float r;
	float sizew=-1,sizeh=-1;
	int mode1;
	int oldDist;
//	private int farmArrowH;
//	private int doorArrowW;
	private String time_str;
	private String mail_str;
	private long CD = 1800000;//1800000
	private long heartCD;
	private int octopus_aphla = 255, octopus_R = 0,octopus_G = 0,octopus_B = 0;//argb暂用一个值来代替
	private int friend = 1;//好友数
	private int wait_ani = 50;
	
	private float bgx,bgy;
	private int bg1_1x,bg1_1y,bg1_2x,bg1_2y;
	private int bg2_1x,bg2_1y,bg2_2x,bg2_2y;
//	private int bg3_1x,bg3_1y,bg3_2x,bg3_2y;
//	private int bg4_1x,bg4_1y,bg4_2x,bg4_2y; //渐变
	private float[][][] mapLocation_xy;
	
	int storeGrayFloor_x,store_x,storeWord_x;
	int mail_x,mailRedFloor_x;
	int farm_x,farm_y,farmArrow_x,farmArrow_y;
//	int door_x,door_y;
	float wait_x,wait_y;
	float kaiQiXiaYiGuanAni_x,kaiQiXiaYiGuanAni_y;
//	float kaiQiXiaYiGuanAni_move_x,kaiQiXiaYiGuanAni_move_y;
	boolean b_switch = false; //切换
	int timing = 0;//计时
	
	private boolean anjiangold;
	private boolean anjiangem;
	private boolean anjianheart;
	private boolean anjianstore;
	private boolean anjianmail;
	private boolean anjianfarm;
	private boolean anjiandoor;
	private boolean[] anjian_level;
	private boolean anjian_boos_1;
	private boolean isStrtLoafing;
	
	private boolean isfarmArrowDown;
	private boolean isTwoTouchMoveZoomBig;
	private boolean isTwoTouchMoveZoomSmall;
	private boolean isOneTouchMove;
	
	private boolean isHideMail;
	public static boolean isKaiQiXiaYiGuanAni;
	private long _startTime;
	int smoke_1[] = {61, 92};
	int smoke_2[] = {40, 86, 63, 69, 90, 46};
	int smoke_3[] = {12, 91, 52, 41, 68, 3};
	int smoke_4[] = {1, 93, 1, 36};
	public ArrayList<levelIcon> friendLevelIcon;
	public Bitmap playerIcon;
	private boolean isShowFramPM; //显示农场跑马灯
//	新手教学
//    public static GameTeaching gt;
	final static int GAP = 59*2;//(1--60关卡59组  61--100关卡39组)+39*2
	final static int layer = 4; //地图的关卡在第几次+1
	public boolean initialize() {
    	GameImage.RemoveAllImage();
		SpriteLibrary.DelAllSpriteImage();
		GameImage.showImageHashMap();
		GameImage.showMemory();
		System.out.println("地图界面内存");
		isStrtLoafing = true;
		isShowFramPM = false;
		if(Configs.isDebug) {
			_startTime = System.currentTimeMillis();
			System.out.println(">>>>>>>>>>>>>>>>>>start load clm time");
		}
		GameStaticImage.loadMapMenu();
		GameStaticImage.loadInfoEquip();
		VeggiesData.isThreeStarPass(); //判断是否三星通关
		r = 1.0f;
		isOneTouchMove = true;
		mode1 = Configs.NONE; 
		isfarmArrowDown = true;
		time_str	= "33:00";
		mail_str	= "25";
		isHideMail = false;
		
		boolean isok = false;
		if(!VeggiesData.getBossLevel()[0] && (VeggiesData.BOOS_LEVEL1-1)<VeggiesData.getGameGuanka().length){
			VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL1-1] = -1;
			isok = true;
		}
		if(!VeggiesData.getBossLevel()[1] && (VeggiesData.BOOS_LEVEL2-1)<VeggiesData.getGameGuanka().length){
			VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL2-1] = -1;
			isok = true;
		}
		if(isok && (VeggiesData.getYiKaiQiMaxLevel()==(VeggiesData.BOOS_LEVEL1-1) || VeggiesData.getYiKaiQiMaxLevel()==(VeggiesData.BOOS_LEVEL2-1))){
			int level = VeggiesData.getYiKaiQiMaxLevel();
			if(VeggiesData.getYiKaiQiMaxLevel()==(VeggiesData.BOOS_LEVEL1-1)){
				boosLevel(VeggiesData.BOOS_LEVEL1-1);
			}else if( VeggiesData.getYiKaiQiMaxLevel()==(VeggiesData.BOOS_LEVEL2-1)){
				boosLevel(VeggiesData.BOOS_LEVEL2-1);
			}
			VeggiesData.setYiKaiQiMaxLevel(level-1);
		}
		
		initMapMenu();
		FacebookOperation.getFacebook().setFriendIcon(this);
		Configs.GameMapWidth = Configs.GameMapORGWidth * GameConfig.f_zoomx;
		Configs.GameMapHeight = Configs.GameMapORGHeight * GameConfig.f_zoomy;
		bgx = GameConfig.GameScreen_Width/2+(Configs.GameMapWidth-GameConfig.GameScreen_Width)/2;
		bgy = GameConfig.GameScreen_Height/2-(Configs.GameMapHeight-GameConfig.GameScreen_Height)/2;
		
		bg1_1x = initIsNotBitmapCoordinateX(BigMapLocation.upperHalfBackGround_x);
		bg1_1y = initIsNotBitmapCoordinateY(BigMapLocation.upperHalfBackGround_y);
		bg1_2x = initIsNotBitmapCoordinateX(BigMapLocation.upperHalfBackGround_x + BigMapLocation.upperHalfBackGround_w);
		bg1_2y = initIsNotBitmapCoordinateY(BigMapLocation.upperHalfBackGround_y + BigMapLocation.upperHalfBackGround_h);
		bg2_1x = initIsNotBitmapCoordinateX(BigMapLocation.bottomHalfBackGround_x);
		bg2_1y = initIsNotBitmapCoordinateY(BigMapLocation.bottomHalfBackGround_y);
		bg2_2x = initIsNotBitmapCoordinateX(BigMapLocation.bottomHalfBackGround_x + BigMapLocation.bottomHalfBackGround_w);
		bg2_2y = initIsNotBitmapCoordinateY(BigMapLocation.bottomHalfBackGround_y + BigMapLocation.bottomHalfBackGround_h);
//		bg3_1x = initIsNotBitmapCoordinateX(BigMapLocation.newMapHalfBackGround_x);
//		bg3_1y = initIsNotBitmapCoordinateY(BigMapLocation.newMapHalfBackGround_y);
//		bg3_2x = initIsNotBitmapCoordinateX(BigMapLocation.newMapHalfBackGround_x + BigMapLocation.newMapHalfBackGround_w);
//		bg3_2y = initIsNotBitmapCoordinateY(BigMapLocation.newMapHalfBackGround_y + BigMapLocation.newMapHalfBackGround_h);
		
//		bg4_1x = initIsNotBitmapCoordinateX(BigMapLocation.JBBackGround_x);
//		bg4_1y = initIsNotBitmapCoordinateY(BigMapLocation.JBBackGround_y);
//		bg4_2x = initIsNotBitmapCoordinateX(BigMapLocation.JBBackGround_x + BigMapLocation.JBBackGround_w);
//		bg4_2y = initIsNotBitmapCoordinateY(BigMapLocation.JBBackGround_y + BigMapLocation.JBBackGround_h);
		
		
		mapImage = new Sprite[BigMapLocation.MapImagePath.length];
		for(int i=0; i<mapImage.length; i++) {
//			if (i == BigMapLocation.MAP_OCTOPUS) {
//				map_octopus = GameImage.getAutoSizecutSprite(GameStaticImage.mapPath + BigMapLocation.MapImagePath[i], 3, 1, GameImage.Sort_line);
//			} else if (i == BigMapLocation.MAP_SMOKE) {
//				map_smoke = GameImage.getAutoSizecutSprite(GameStaticImage.mapPath + BigMapLocation.MapImagePath[i], 4, 1, GameImage.Sort_line);
//			} else {
				if (i == 0 || i == 1 || i == 35 || i == 36 || i == 37 || i == 58)
					mapImage[i] = new Sprite(GameImage.getImage(GameStaticImage.mapPath + BigMapLocation.MapImagePath[i]));				
//			}
		}
		 
//		GameStaticImage.loadHashMapImage(GameStaticImage.mapPath + "org1.json", GameStaticImage.mapPath + "org1");
//		for(int i=0; i<BigMapLocation.MapImagePath.length; i++) {
//			if (i == BigMapLocation.MAP_OCTOPUS) {
//				map_octopus = GameImage.getAutoSizecutSprite(GameStaticImage.mapPath + BigMapLocation.MapImagePath[i], 3, 1, GameImage.Sort_line);
//			} else if (i == BigMapLocation.MAP_SMOKE) {
//				map_smoke = GameImage.getAutoSizecutSprite(GameStaticImage.mapPath + BigMapLocation.MapImagePath[i], 4, 1, GameImage.Sort_line);
//			} else {
//				//mapImage[i] = new Sprite(GameImage.getImage(GameStaticImage.mapPath + BigMapLocation.MapImagePath[i]));				
//				if (i == 0 || i == 35 || i == 36 || i == 37) {
//					Bitmap bmp = GameImage.getImage(GameStaticImage.mapPath + BigMapLocation.MapImagePath[i]);
//					GameStaticImage.hBitmap.put(BigMapLocation.MapImagePath[i] + ".png", new Sprite(bmp));
//					bmp = null;
//					//System.gc();
//				}
//			}
//		}
		mySprite = new MySprite(GameStaticImage.mapPath + "org1.json", GameStaticImage.mapPath + "org1");
		mySprite2 = new MySprite(GameStaticImage.mapPath + "org2.json", GameStaticImage.mapPath + "org2");
		mySprite3 = new MySprite(GameStaticImage.mapPath + "org3.json", GameStaticImage.mapPath + "org3");
//		mySprite4 = new MySprite(GameStaticImage.mapPath + "org4.json", GameStaticImage.mapPath + "org4");
		
		bitmap_map_card = GameImage.getImage("s_map/map_card");
		bitmap_map_card_2=GameImage.getImage("s_map/map_card_2");
		if(GameStaticImage.s_map_ren_1 == null)
			GameStaticImage.s_map_ren_1 = new Sprite(GameImage.getImage(GameStaticImage.map_ren_1));
		if(GameStaticImage.s_map_ren_2 == null)
			GameStaticImage.s_map_ren_2 = new Sprite(GameImage.getImage(GameStaticImage.map_ren_2));
		
		mapLocation_xy = new float[BigMapLocation.MapLocation.length][][];
		for(int i=0; i<mapLocation_xy.length; i++) {
			mapLocation_xy[i] = new float[BigMapLocation.MapLocation[i].length][2];
			for (int j=0; j<BigMapLocation.MapLocation[i].length; j++) {
				if ((int) BigMapLocation.MapLocation[i][j][0] == BigMapLocation.MAP_OCTOPUS) {			
					mapLocation_xy[i][j][0] = initIsBitmapCoordinateX((Float) BigMapLocation.MapLocation[i][j][1], mySprite2.getImageWidth("map_octopus_0.png"));
					mapLocation_xy[i][j][1] = initIsBitmapCoordinateY((Float) BigMapLocation.MapLocation[i][j][2], mySprite2.getImageHeight("map_octopus_0.png"));									
				} else if ((int) BigMapLocation.MapLocation[i][j][0] == BigMapLocation.MAP_SMOKE) {					
					mapLocation_xy[i][j][0] = initIsBitmapCoordinateX((Float) BigMapLocation.MapLocation[i][j][1], mySprite2.getImageWidth("map_smoke_0.png"));
					mapLocation_xy[i][j][1] = initIsBitmapCoordinateY((Float) BigMapLocation.MapLocation[i][j][2], mySprite2.getImageHeight("map_smoke_0.png"));									
				}else {
					if ((int) BigMapLocation.MapLocation[i][j][0] == 0  || (int) BigMapLocation.MapLocation[i][j][0] == 1  || (int) BigMapLocation.MapLocation[i][j][0] == 35 || (int) BigMapLocation.MapLocation[i][j][0] == 36 || (int) BigMapLocation.MapLocation[i][j][0] == 37 || (int) BigMapLocation.MapLocation[i][j][0] == 58) {
						mapLocation_xy[i][j][0] = initIsBitmapCoordinateX((Float) BigMapLocation.MapLocation[i][j][1], mapImage[(int) BigMapLocation.MapLocation[i][j][0]]);
						mapLocation_xy[i][j][1] = initIsBitmapCoordinateY((Float) BigMapLocation.MapLocation[i][j][2], mapImage[(int) BigMapLocation.MapLocation[i][j][0]]);
					} else if ((int) BigMapLocation.MapLocation[i][j][0] == 26 || (int) BigMapLocation.MapLocation[i][j][0] == 27 || (int) BigMapLocation.MapLocation[i][j][0] == 28 || (int) BigMapLocation.MapLocation[i][j][0] == 29) {
						mapLocation_xy[i][j][0] = initIsBitmapCoordinateX((Float) BigMapLocation.MapLocation[i][j][1], mySprite2.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png"));
						mapLocation_xy[i][j][1] = initIsBitmapCoordinateY((Float) BigMapLocation.MapLocation[i][j][2], mySprite2.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png"));						
					} else if ((int) BigMapLocation.MapLocation[i][j][0] == 39 || (int) BigMapLocation.MapLocation[i][j][0] == 40 || (int) BigMapLocation.MapLocation[i][j][0] == 41){
						mapLocation_xy[i][j][0] = initIsBitmapCoordinateX((Float) BigMapLocation.MapLocation[i][j][1], mySprite3.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png"));
						mapLocation_xy[i][j][1] = initIsBitmapCoordinateY((Float) BigMapLocation.MapLocation[i][j][2], mySprite3.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png"));																							
					}else if((int) BigMapLocation.MapLocation[i][j][0] >= 42 && (int) BigMapLocation.MapLocation[i][j][0] <=57){
//						mapLocation_xy[i][j][0] = initIsBitmapCoordinateX((Float) BigMapLocation.MapLocation[i][j][1], mySprite4.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png"));
//						mapLocation_xy[i][j][1] = initIsBitmapCoordinateY((Float) BigMapLocation.MapLocation[i][j][2], mySprite4.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png"));																							
					} else {
						mapLocation_xy[i][j][0] = initIsBitmapCoordinateX((Float) BigMapLocation.MapLocation[i][j][1], mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png"));
						mapLocation_xy[i][j][1] = initIsBitmapCoordinateY((Float) BigMapLocation.MapLocation[i][j][2], mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png"));																							
					}
				}
			}
		}
		
		anjian_level = new boolean[VeggiesData.getGameGuanka().length];
		for(int i=0; i<anjian_level.length; i++) {
			anjian_level[i] = false;
		}
		anjian_boos_1 = false;
		
//		map_star = new Sprite[4];
//		for(int i=0; i<map_star.length; i++) {
//			map_star[i] = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "star_" + i));
//		}
		GameStaticImage.s_map_num_1 = GameImage.getAutoSizecutSprite(GameStaticImage.map_num_1, 10, 1, GameImage.Sort_line);
//		GameStaticImage.s_map_enemy_01 = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "boss_1"));
//		GameStaticImage.s_map_enemy_02 = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "boss_2"));
//		GameStaticImage.s_map_enemy_04 = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "boss_4"));
//		GameStaticImage.s_map_ren_1 = new Sprite(GameImage.getImage(GameStaticImage.map_ren_1));
//		GameStaticImage.s_map_ren_2 = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "ren_3"));
		
//		map_cloud = new Sprite[3];
//		for(int i=0; i<map_cloud.length; i++) {
//			map_cloud[i] = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "cloud"+(i+1)));
//		}
//		map_wait = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "jingqingqidai"));
//		wait_x = initIsBitmapCoordinateX( BigMapLocation.text_please_wait_x, map_wait);
//		wait_y = initIsBitmapCoordinateY( BigMapLocation.text_please_wait_y, map_wait);									
		if(Configs.isDebug) {
			System.out.println(">>>>>>>>>>>>>>>>>>load clm time = " + (System.currentTimeMillis() - _startTime));
		}
		
//		kaiQiXiaYiGuanAni_move_x = 0;
//		kaiQiXiaYiGuanAni_move_y = 0;
//		isKaiQiXiaYiGuanAni = true;
//		if (isKaiQiXiaYiGuanAni) {
//			kaiQiXiaYiGuanAni_x = mapLocation_xy[layer][VeggiesData.getCurrentLevel()+118][0];
//			kaiQiXiaYiGuanAni_y = mapLocation_xy[layer][VeggiesData.getCurrentLevel()+118][1];
//			kaiQiXiaYiGuanAni_move_x = (mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+118][0] - mapLocation_xy[layer][VeggiesData.getCurrentLevel()+118][0]) / 40;
//			kaiQiXiaYiGuanAni_move_y = (mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+118][1] - mapLocation_xy[layer][VeggiesData.getCurrentLevel()+118][1]) / 40;
//		} else {
			kaiQiXiaYiGuanAni_x = mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+GAP][0];
			kaiQiXiaYiGuanAni_y = mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+GAP][1];			
//		}
		
		if(!FacebookOperation.isLanding){
			isHideMail = true;
		}else{
			mail_str	= MessageModule.message.size()+"";
		}

		s_smallcard_card_index = 0;
		GameEquipmentModule.s_smallcard_card = new HashMap<Integer, Sprite>();
		GameEquipmentModule.smallcard_load_sort = new ArrayList<Integer>();
		GameEquipmentModule.init_smallcardLoadList();
		GameImage.showImageHashMap();
		//每日登录
		if(getDateBool(System.currentTimeMillis()) == 1){
			GameEquipmentModule.isFree = true; //每天一次免费抽卡
			new VeggiesData().saveGame();
			GameManager.setPopUp(PopUp.EVERYDAY, "", new PopUp("") {
//				@Override
//				public byte onTouch(MotionEvent event) {
//					// TODO Auto-generated method stub
//					byte temp = super.onTouch(event);
//					if(temp == PopUp.onTouch_everyDayExit){
//						boolean isGT = initGameTeaching();
//						if(!isGT && LevelSuccessModule.isNext){
//							LevelSuccessModule.isNext = false;
//							int level = VeggiesData.getCurrentLevel();
//							VeggiesData.setCurrentLevel(level+1);
//							GameManager.forbidModule(GameMenu.gameLevelInfoModule);
//						}
//						return -1;
//					}
//					return temp;
//				}
				@Override
				public boolean run() {
					// TODO Auto-generated method stub
					boolean isOK = super.run();
					if(isOK){
						boolean isGT = initGameTeaching();
						if(bottom == null)
						    bottom = new BottomMenu(mySprite, mySprite3);
						if(!isGT && LevelSuccessModule.isNext){
							boolean is_ok = false;
							if(!VeggiesData.getBossLevel()[0] && (VeggiesData.BOOS_LEVEL1-1)<VeggiesData.getGameGuanka().length){
								VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL1-1] = -1;
								is_ok = true;
							}
							if(!VeggiesData.getBossLevel()[1] && (VeggiesData.BOOS_LEVEL2-1)<VeggiesData.getGameGuanka().length){
								VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL2-1] = -1;
								is_ok = true;
							}
							if(!is_ok){
								int level = VeggiesData.getCurrentLevel();
								VeggiesData.setCurrentLevel(level+1);
								GameManager.forbidModule(GameMenu.gameLevelInfoModule);
							}
							LevelSuccessModule.isNext = false;
						}
					}
					return isOK;
				}
			});
			if(VeggiesData.getGameGuanka()[2]>=0){
				UserRequest.getUser().setHideLevel(true, false);
				new VeggiesData().saveGame();
			}
		}else{
			boolean isGT = initGameTeaching();
			if(!isGT && LevelSuccessModule.isNext){
				
				boolean is_ok = false;
				if(!VeggiesData.getBossLevel()[0] && (VeggiesData.BOOS_LEVEL1-1)<VeggiesData.getGameGuanka().length){
					VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL1-1] = -1;
					is_ok = true;
				}
				if(!VeggiesData.getBossLevel()[1] && (VeggiesData.BOOS_LEVEL2-1)<VeggiesData.getGameGuanka().length){
					VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL2-1] = -1;
					is_ok = true;
				}
				if(!is_ok){
					int level = VeggiesData.getCurrentLevel();
					VeggiesData.setCurrentLevel(level+1);
					GameManager.forbidModule(GameMenu.gameLevelInfoModule);
				}
				
				LevelSuccessModule.isNext = false;
				run_smallcardLoadList(); //异步加载
			}
		}
		
		infoLevelIcon();
		
		
		if(GameManager.getGT()!=null && GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL45){
			UserRequest.getUser().setHideLevel(true, true);
		}
		
		boolean isEVERYDAY = false;
		if(GameManager.getpop_up()!=null){
			for(int i=0;i<GameManager.getpop_up().size();++i){
				if(GameManager.getpop_up().get(i).type == PopUp.EVERYDAY)
					isEVERYDAY = true;
			}
			
		}
		if(!isEVERYDAY)
		    bottom = new BottomMenu(mySprite, mySprite3);
		
		
	 if(LevelSuccessModule.isSurpass){
		 LevelSuccessModule.isSurpass = false;
		 int level = VeggiesData.getCurrentLevel()-1;
		 GameManager.forbidModule(new RankingChange(level, FriendScore.surpassFriendFbid));
	 }

		GameImage.showMemory();
		System.out.println("地图加载完全的界面内存");
		return false;
	}
	
	public void run_smallcardLoadList() {
		if(thread_card == null){
			s_smallcard_card_index = 0;
			thread_card  =	new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(s_smallcard_card_index < GameEquipmentModule.smallcard_load_sort.size()){
						if(GameManager.getpop_up()!=null && GameManager.getpop_up().size()==0){ 
							//有弹出框不能使用hasmap
							if (GameEquipmentModule.smallcard_load_sort.get(s_smallcard_card_index)  < 10) {
								if(!GameEquipmentModule.s_smallcard_card.containsKey(GameEquipmentModule.smallcard_load_sort.get(s_smallcard_card_index)))
									GameEquipmentModule.s_smallcard_card.put(GameEquipmentModule.smallcard_load_sort.get(s_smallcard_card_index), new Sprite(GameImage.getImage(GameStaticImage.SMALLCARD + "card_pc_0" + GameEquipmentModule.smallcard_load_sort.get(s_smallcard_card_index) + "_s")));
							} else {
								if(!GameEquipmentModule.s_smallcard_card.containsKey(GameEquipmentModule.smallcard_load_sort.get(s_smallcard_card_index)))
									GameEquipmentModule.s_smallcard_card.put(GameEquipmentModule.smallcard_load_sort.get(s_smallcard_card_index), new Sprite(GameImage.getImage(GameStaticImage.SMALLCARD + "card_pc_" + GameEquipmentModule.smallcard_load_sort.get(s_smallcard_card_index) + "_s")));
							}
							s_smallcard_card_index++;
							System.out.println("<><> 正在异步加载图片");
						}
					}
				}
			});
			thread_card.start();
		}
		
		
		
	}
	@Override
	public void onreStart() {
		// TODO Auto-generated method stub
		super.onreStart();
		FacebookOperation.getFacebook().setFriendIcon(this);
		infoLevelIcon();
	}
	
	public void infoLevelIcon(){
		friendLevelIcon = new ArrayList<levelIcon>();
		HashMap<Integer, Bitmap> leveIcon = new HashMap<Integer, Bitmap>();
		
		Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
		while (iterator.hasNext()) {
			FaceBookPlayer player = FacebookOperation.playerMap.get(iterator.next());
			if(player.getMaxLevel()>=1 && !leveIcon.containsKey(player.getMaxLevel())){
				float width = 44*GameConfig.f_zoomx;
				float height = 44*GameConfig.f_zoomy;
				// 图片缩放
				Bitmap temp = player.getIcon();//user.getIcon();
				if(temp==null){
					temp = GameStaticImage.s_share_ui_photo_01.bitmap;
				}
				temp = GameImage.zoomImage(temp, width, height);
				leveIcon.put(player.getMaxLevel(), temp);
				friendLevelIcon.add(new levelIcon(temp, player.getName(), player.getMaxLevel()-1));
			}
		}
		leveIcon.clear();
		leveIcon = null;
		
		if(FacebookOperation.player!=null && FacebookOperation.player.getIcon()!=null){
			float width = 44*GameConfig.f_zoomx;
			float height = 44*GameConfig.f_zoomy;
			playerIcon  = GameImage.zoomImage(FacebookOperation.player.getIcon(), width, height);
		}
	}
	
	private int getDateBool(long millis) {   
		long ztNyr = VeggiesData.ztNyr;//昨天的long
		int endT = VeggiesData.endT; //昨天月数的最大天数
		if(ztNyr==0){
			return 1;
		}
		//当前的年月日
		int[]  nyr = getNYR(millis);
		//昨天的年月日
		int[]  _ztnyr = getNYR(ztNyr);
		
		//同月同一天
		if(_ztnyr[1] == (nyr[1]) && _ztnyr[2] == (nyr[2])){
			return -1;
		} 
		if(_ztnyr[2] != (nyr[2]-1)){ //表示昨天可能没登陆
			//同一年 下个月的第一天 和当月的最后一天
			if(nyr[0] == _ztnyr[0] && (_ztnyr[1]+1) == nyr[1] && nyr[2]==1 && _ztnyr[2] == endT){
				return 1;
			}else if(nyr[0] == (_ztnyr[0]+1) && _ztnyr[1]==12 && _ztnyr[2] == endT ){
				return 1;
			}
		}else if(_ztnyr[2] == (nyr[2]-1) && _ztnyr[1] == nyr[1] && _ztnyr[0] == nyr[0]){
			return 1;
		}
		
		//没连续登陆
		VeggiesData.everyDay = 0;
//		new VeggiesData().saveGame();
		
        return 1;
        
        
//		return ft.format("%1$tY年%1$tm月%1$td日%1$tA，%1$tT %1$tp", cal).toString();	
	}
	
	//获取年月日
	private int[]  getNYR(long millis){
		Calendar cal = Calendar.getInstance();        
		cal.setTimeInMillis(millis);                  
		Formatter ft=new Formatter(Locale.CHINA); 
		//年
		int year = cal.get(Calendar.YEAR);
		//月
		int month = cal.get(Calendar.MONTH)+1;
		//日
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int[] nyr = {year, month, day};
		return nyr;
	}
	 
	private boolean initGameTeaching() {
		if (VeggiesData.getGameGuanka()[1] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL31]) {
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL31, (int)(GameConfig.GameScreen_Width-100*GameConfig.f_zoomx), (int)(20*GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_31), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
//			GameManager.getGT().setHandType(GameTeaching.LEFT_HAND);
			return true;
		}else if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL25]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL25, 0, 0, LangUtil.getLangString(LangDefineClient.GUIDE_25), GameTeaching.NO_HAND, GameConfig.GameScreen_Height>>1);
			return true;
		}else if(VeggiesData.getGameGuanka()[8] >= 0 &&!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL46]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			VeggiesData.setAllCardNum(GameItem.Item04, 1);
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL46, (int)(GameConfig.GameScreen_Width-100*GameConfig.f_zoomx), (int)(20*GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_46), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
			return true;
		}else if (VeggiesData.getGameGuanka()[4] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL4]) {
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL4, (int)(storeGrayFloor_x + mySprite.getImageWidth("map_back.png")/2), (int)(Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoomy + mySprite.getImageHeight("map_back.png")/2), LangUtil.getLangString(LangDefineClient.GUIDE_4), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
			return true;
		}else if(VeggiesData.getGameGuanka()[4] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL38]){
			mapRestore(); //地图还原
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			float tempX = move_X;
			float tempY = move_Y;
			int level = 4;
			float temp_x = mapLocation_xy[layer][level+GAP][0] * r + bgx + tempX;
			float temp_y = mapLocation_xy[layer][level+GAP][1] * r + bgy + tempY;
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL38, (int)temp_x, (int)temp_y, LangUtil.getLangString(LangDefineClient.GUIDE_38), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
			return true;
		}else if(VeggiesData.getGameGuanka()[1] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL33]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			float tempX = move_X;
			float tempY = move_Y;
			int level = 1;
			float temp_x = mapLocation_xy[layer][level+GAP][0] * r + bgx + tempX;
			float temp_y = mapLocation_xy[layer][level+GAP][1] * r + bgy + tempY;
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL33, (int)temp_x, (int)temp_y, LangUtil.getLangString(LangDefineClient.GUIDE_33), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
			return true;
		}else if(VeggiesData.getGameGuanka()[9] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL20]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL20, farm_x+(int)(mySprite.getImageWidth("farmbutton2.png")/2), farm_y+(int)(mySprite.getImageHeight("farmbutton2.png")/2), LangUtil.getLangString(LangDefineClient.GUIDE_20), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
			return true;
		}else if(VeggiesData.getGameGuanka()[7] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL45]){
			mapRestore(); //地图还原
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			float tempX = move_X;
			float tempY = move_Y;
			int level = 0;
			float temp_x = mapLocation_xy[layer][level+GAP][0] * r + bgx + tempX;
			float temp_y = mapLocation_xy[layer][level+GAP][1] * r + bgy + tempY;
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL45, (int)temp_x, (int)temp_y, LangUtil.getLangString(LangDefineClient.GUIDE_45), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
			return true;
		}
		return false;
	}
	
	//地图还原
	private void mapRestore(){
		 move_X = 0;
		 move_Y = 0;
		 oldX=-1;
		 oldY=-1;
		 move_X_speed = 0;
		 move_Y_speed = 0;
		 move_X_old = 0;
		 move_Y_old = 0;
		 move_r = 0;
		 move_r_old = 0;
	}
	
	//初始化地图菜单
	private void initMapMenu() {
		storeGrayFloor_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.storeGrayFloor_x)* GameConfig.f_zoomx);
		store_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.store_x)* GameConfig.f_zoomx);
		storeWord_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.storeWord_x)* GameConfig.f_zoomx);
		
		mail_x = (int) (454*GameConfig.f_zoomx);
		mailRedFloor_x = (int) (444*GameConfig.f_zoomx);
	
		farm_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.farm_x)* GameConfig.f_zoomx);
		farm_y = (int) (GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.BigMapMenu.farm_y)* GameConfig.f_zoomy);
		farmArrow_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.farmArrow_x)* GameConfig.f_zoomx);
		farmArrow_y = (int) (GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.BigMapMenu.farmArrow_y)* GameConfig.f_zoomy);
		
//		map_door = new Sprite[2];
//		map_door = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "back_3"));
//		map_door[1] = new Sprite(GameImage.getImage(GameStaticImage.mapPath + "door_4"));
//		door_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.door_x)* GameConfig.f_zoomx);
//		door_y = (int) (GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.BigMapMenu.door_y)* GameConfig.f_zoomy);
//		doorArrow_x = (int) (GameConfig.GameScreen_Width - (GameConfig.ORGScreen_Width - Location.BigMapMenu.doorArrow_x)* GameConfig.f_zoomx);
//		doorArrow_y = (int) (GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.BigMapMenu.doorArrow_y)* GameConfig.f_zoomy);
	}
	Paint p = new Paint();
	public void paint(Canvas canvas) {
		float tempX = move_X;
		float tempY = move_Y;
		canvas.save();
		canvas.clipRect(0 * r + bgx + tempX - Configs.GameMapWidth/2 * r, 0 * r + bgy + tempY - Configs.GameMapHeight/2 * r,
				Configs.GameMapWidth * r + bgx + tempX - Configs.GameMapWidth/2 * r, Configs.GameMapHeight * r + bgy + tempY - Configs.GameMapHeight/2 * r);
		paintBackgroundColor(canvas, 
				bg1_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
				bg1_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
				bg1_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
				bg1_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,Color.rgb(246, 214, 114),false);
		paintBackgroundColor(canvas, 
				bg2_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
				bg2_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
				bg2_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
				bg2_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,Color.rgb(18, 132, 209),false);
//		paintBackgroundColor(canvas, 
//				bg3_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r, 
//				bg3_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r,
//				bg3_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r,
//				bg3_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r,Color.rgb(35, 113, 119),false);
		
//		DrawList(canvas, p, 
//				(int)(bg4_1x * r + bgx + tempX - Configs.GameMapWidth/2 * r), 
//				(int)(bg4_1y * r + bgy + tempY - Configs.GameMapHeight/2 * r),
//				(int)(bg4_2x * r + bgx + tempX - Configs.GameMapWidth/2 * r),
//				(int)(bg4_2y * r + bgy + tempY - Configs.GameMapHeight/2 * r), 
//				Color.argb(255, 35, 113, 119), Color.argb(255, 246, 214, 114));
		
		for(int i=0; i<BigMapLocation.MapLocation.length; i++) {
			for (int j=0; j<BigMapLocation.MapLocation[i].length; j++) {				
				switch((int)BigMapLocation.MapLocation[i][j][0]) {
				case BigMapLocation.MAP_OCTOPUS:
					//章鱼
					int tempi = 0;
					if (GameConfig.i_coke % 32 < 32) {
						tempi = GameConfig.i_coke % 32 / 4 % 4; 
						if (tempi == 3) tempi = 1;
						//先不场景
//						if (VeggiesData.GameGuanka[14] < 0) {
//							tempi = 0;
//						} else {
							if (octopus_aphla != 255) {
								octopus_aphla = 255; 
								octopus_R = octopus_G = octopus_B = 0;
							}
//						}
//						if (map_octopus[tempi] != null) {
								mySprite2.drawBitmap(canvas, "map_octopus_"+tempi+".png", mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite2.getImageWidth("map_octopus_0.png")/2*r, 
										mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite2.getImageHeight("map_octopus_0.png")/2*r, r*(Float)BigMapLocation.MapLocation[i][j][4], r*(Float)BigMapLocation.MapLocation[i][j][4], octopus_aphla, 0f, 0, 0, octopus_R, octopus_G, octopus_B);									
//						}
					}
					break;
				case BigMapLocation.MAP_SMOKE:
					int temp1 = 0;
//					if (VeggiesData.GameGuanka[29] == 0) {
						if (GameConfig.i_coke % 50 < 32) {
							temp1 = GameConfig.i_coke % 50 / 4 % 4; 
//							mySprite2.drawBitmap(canvas, "map_smoke_"+temp1+".png",mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite2.getImageWidth("map_smoke_0.png")/2*r, 
//										mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite2.getImageHeight("map_smoke_0.png")/2*r, r, r, 255, 0, 0, 0,0,0,0);					
							
							float smokeOff_x = mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite2.getImageWidth("map_smoke_0.png")/2*r;
							float smokeOff_y = mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite2.getImageHeight("map_smoke_0.png")/2*r;
							switch(temp1){
							case 0:
								//第一组
								mySprite2.drawBitmap(canvas, "map_smoke_"+0+".png",smoke_1[0]*GameConfig.f_zoomx * r+smokeOff_x, 
										smoke_1[1]*GameConfig.f_zoomy * r+smokeOff_y, r, r, 255, 0, 0, 0,0,0,0);		
								break;
							case 1:
								 //第二组
								for(int k=0;k<smoke_2.length/2-1;++k){
									mySprite2.drawBitmap(canvas, "map_smoke_"+0+".png",smoke_2[k]*GameConfig.f_zoomx * r+smokeOff_x, 
											smoke_2[k+2]*GameConfig.f_zoomy * r+smokeOff_y, r, r, 255, 0, 0, 0,0,0,0);	
								}
								mySprite2.drawBitmap(canvas, "map_smoke_"+1+".png",smoke_2[smoke_2.length/2]*GameConfig.f_zoomx * r+smokeOff_x, 
										smoke_2[smoke_2.length-1]*GameConfig.f_zoomy * r+smokeOff_y, r, r, 255, 0, 0, 0,0,0,0);		
								break;
							case 2:
								//第三组
								for(int k=0;k<smoke_3.length/2-1;++k){
									mySprite2.drawBitmap(canvas, "map_smoke_"+1+".png",smoke_3[k]*GameConfig.f_zoomx * r+smokeOff_x, 
											smoke_3[k+2]*GameConfig.f_zoomy * r+smokeOff_y, r, r, 255, 0, 0, 0,0,0,0);	
								}
								mySprite2.drawBitmap(canvas, "map_smoke_"+2+".png",smoke_3[smoke_2.length/2]*GameConfig.f_zoomx * r+smokeOff_x, 
										smoke_3[smoke_3.length-1]*GameConfig.f_zoomy * r+smokeOff_y, r, r, 255, 0, 0, 0,0,0,0);		
								break;
							case 3:
								//第四组
								for(int k=0;k<smoke_4.length/2;++k){
									mySprite2.drawBitmap(canvas, "map_smoke_"+2+".png",smoke_4[k]*GameConfig.f_zoomx * r+smokeOff_x, 
											smoke_4[k+2]*GameConfig.f_zoomy * r+smokeOff_y, r, r, 255, 0, 0, 0,0,0,0);	
								}
								break;
							}
						}
//					}
					break;
				case BigMapLocation.MAP_LEVEL:
					if(VeggiesData.getGameGuanka()[j-GAP] < 0) {
						mySprite.drawBitmap(canvas, BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]+1]+".png", mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4], r * (Float)BigMapLocation.MapLocation[i][j][4], 255, 0, 0, 0, 0, 0, 0);
					} else {
						mySprite.drawBitmap(canvas, BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]]+".png", mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r *(anjian_level[j-GAP] ? 0.2f : 0f), mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r *(anjian_level[j-GAP] ? 0.2f : 0f), (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4]*(anjian_level[j-GAP] ? 1.2f : 1.0f), r * (Float)BigMapLocation.MapLocation[i][j][4]*(anjian_level[j-GAP] ? 1.2f : 1.0f), 255, 0, 0, 0, 0, 0, 0);						
						mySprite.drawBitmap(canvas, "star_"+VeggiesData.getGameGuanka()[j-GAP]+".png", mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth("star_"+VeggiesData.getGameGuanka()[j-GAP]+".png")/2*r , (mapLocation_xy[i][j][1]-25*GameConfig.f_zoomy)*r + bgy+tempY-mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4], r * (Float)BigMapLocation.MapLocation[i][j][4], 255, 0, 0, 0, 0, 0, 0);
						float temp_x;
						if (j-GAP+1 < 10 ) {
							temp_x = mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r - GameStaticImage.s_map_num_1[0].bitmap.getWidth()/2*r * (anjian_level[j-GAP] ? 1.2f : 1.0f);					
						} else {
							temp_x = mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r - GameStaticImage.s_map_num_1[0].bitmap.getWidth()*r * (anjian_level[j-GAP] ? 1.2f : 1.0f);
						}
						float temp_y = mapLocation_xy[i][j][1] * r + bgy + tempY - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r + mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r - GameStaticImage.s_map_num_1[0].bitmap.getHeight()/2*r * (anjian_level[j-GAP] ? 1.2f : 1.0f);
						GameStaticImage.s_map_num_1[0].drawBitmap(canvas, GameStaticImage.s_map_num_1, (int)(temp_x), (int)(temp_y) , GameConfig.Char_num1, Integer.toString(j-GAP+1), null, 0 , r * (anjian_level[j-GAP] ? 1.2f : 1.0f));	
					
						//绘制关键boss关卡和奖励关卡
						if(j-GAP == 29 && VeggiesData.getGameGuanka()[j-GAP] >= 0) {
							temp_x = mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png") * r - 5 * GameConfig.f_zoomx * r;
							temp_y = mapLocation_xy[i][j][1] * r + bgy + tempY - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r + mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r - mySprite.getImageHeight("boss_1.png") / 2 * r;
							mySprite.drawBitmap(canvas, "boss_1.png", temp_x, temp_y, r , r , 255,0,0,0);				
						}
						if(j-GAP == 59 && VeggiesData.getGameGuanka()[j-GAP] >= 0) {
							temp_x = mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png") * r - 5 * GameConfig.f_zoomx * r;
							temp_y = mapLocation_xy[i][j][1] * r + bgy + tempY - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r + mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r - mySprite.getImageHeight("boss_2.png") / 2 * r;
							mySprite.drawBitmap(canvas,"boss_2.png", temp_x, temp_y, r , r , 255,0,0,0);				
						}
						if(UserRequest.getUser().getHideLevel() && j-GAP == UserRequest.getUser().gethideLevel() && VeggiesData.getGameGuanka()[j-GAP] >= 0) {//奖励关卡
							temp_x = mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png") * r - 5 * GameConfig.f_zoomx * r;
							temp_y = mapLocation_xy[i][j][1] * r + bgy + tempY - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r + mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2 * r - mySprite.getImageHeight("boss_4.png") / 2 * r;
							mySprite.drawBitmap(canvas,"boss_4.png", temp_x, temp_y, r , r , 255,0,0,0);				
						}
					}
					break; 
				default:
					if ((int) BigMapLocation.MapLocation[i][j][0] == 0 || (int) BigMapLocation.MapLocation[i][j][0] == 1 || (int) BigMapLocation.MapLocation[i][j][0] == 35 || (int) BigMapLocation.MapLocation[i][j][0] == 36 || (int) BigMapLocation.MapLocation[i][j][0] == 37 || (int) BigMapLocation.MapLocation[i][j][0] == 58) {
						
						if((int) BigMapLocation.MapLocation[i][j][0] == 35){
							float mapx =  mapLocation_xy[i][j][0] * r + bgx + tempX - mapImage[(int) BigMapLocation.MapLocation[i][j][0]].bitmap.getWidth()/2*r;
							float mapy = mapLocation_xy[i][j][1]*r + bgy+tempY-mapImage[(int) BigMapLocation.MapLocation[i][j][0]].bitmap.getHeight()/2*r;
							float mapw = (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4];
							float maph = r * (Float)BigMapLocation.MapLocation[i][j][4];
							for(int k=0;k<8;++k){
								mapImage[(int) BigMapLocation.MapLocation[i][j][0]].drawBitmap(canvas, mapx, mapy+(k * mapImage[(int) BigMapLocation.MapLocation[i][j][0]].bitmap.getHeight()*r),  mapw, maph, 255, 0, 0, 0, 0, 0, 0);	
							}
						}else
							mapImage[(int) BigMapLocation.MapLocation[i][j][0]].drawBitmap(canvas, mapLocation_xy[i][j][0] * r + bgx + tempX - mapImage[(int) BigMapLocation.MapLocation[i][j][0]].bitmap.getWidth()/2*r, mapLocation_xy[i][j][1]*r + bgy+tempY-mapImage[(int) BigMapLocation.MapLocation[i][j][0]].bitmap.getHeight()/2*r, (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4], r * (Float)BigMapLocation.MapLocation[i][j][4], 255, 0, 0, 0, 0, 0, 0);						
					
					} else if ((int) BigMapLocation.MapLocation[i][j][0] == 26 || (int) BigMapLocation.MapLocation[i][j][0] == 27 || (int) BigMapLocation.MapLocation[i][j][0] == 28 || (int) BigMapLocation.MapLocation[i][j][0] == 29) {
						mySprite2.drawBitmap(canvas, BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png",mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite2.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite2.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4], r * (Float)BigMapLocation.MapLocation[i][j][4], 255, 0, 0, 0, 0, 0, 0);												
					}else if((int) BigMapLocation.MapLocation[i][j][0] == 30 && VeggiesData.getYiKaiQiMaxLevel()>=(j/2)+1 ){
						mySprite.drawBitmap(canvas, BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + "_2.png",mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4], r * (Float)BigMapLocation.MapLocation[i][j][4], 255, 0, 0, 0, 0, 0, 0);						
					}else if((int) BigMapLocation.MapLocation[i][j][0] == 39 || (int) BigMapLocation.MapLocation[i][j][0] == 40 || (int) BigMapLocation.MapLocation[i][j][0] == 41){
						 //栈道锁   栈道栅栏  栈道桥
						if(VeggiesData.getBossLevel()[0] && (int) BigMapLocation.MapLocation[i][j][0] == 39){
							
						}else
							mySprite3.drawBitmap(canvas,BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png", mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite3.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r  - mySprite3.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r *(anjian_boos_1 ? 0.2f : 0f), mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite3.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r - mySprite3.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r *(anjian_boos_1 ? 0.2f : 0f), (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4]*(anjian_boos_1 ? 1.2f : 1.0f), r * (Float)BigMapLocation.MapLocation[i][j][4]*(anjian_boos_1 ? 1.2f : 1.0f), 255, 0, 0, 0, 0, 0, 0);						
					}else if((int) BigMapLocation.MapLocation[i][j][0] >= 42 && (int) BigMapLocation.MapLocation[i][j][0] <=57){
//							mySprite4.drawBitmap(canvas,BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png", mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite4.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r  - mySprite4.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r *(anjian_boos_1 ? 0.2f : 0f), mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite4.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r - mySprite4.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r *(anjian_boos_1 ? 0.2f : 0f), (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4]*(anjian_boos_1 ? 1.2f : 1.0f), r * (Float)BigMapLocation.MapLocation[i][j][4]*(anjian_boos_1 ? 1.2f : 1.0f), 255, 0, 0, 0, 0, 0, 0);						
					} 
					else {
						mySprite.drawBitmap(canvas, BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png",mapLocation_xy[i][j][0] * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, mapLocation_xy[i][j][1]*r + bgy+tempY-mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r, (((int)BigMapLocation.MapLocation[i][j][3]) == 0?1:-1) * r * (Float)BigMapLocation.MapLocation[i][j][4], r * (Float)BigMapLocation.MapLocation[i][j][4], 255, 0, 0, 0, 0, 0, 0);						
					}
					break;
				} 
			}
		}
		
//		if (VeggiesData.getCurrentLevel() == j-118) {
//			if(playerIcon!=null){//自己
			if(playerIcon!=null){
				ExternalMethods.drawImage(canvas, playerIcon, kaiQiXiaYiGuanAni_x * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][VeggiesData.getCurrentLevel()+GAP][0]] + ".png")/2 * r - mySprite.getImageWidth("ren_1.png")*r+(10*GameConfig.f_zoomx*r), (10*GameConfig.f_zoomy*r) +kaiQiXiaYiGuanAni_y * r + bgy + tempY - mySprite.getImageHeight("ren_1.png")/2*r, r , r , 255,0,0,0);
				GameStaticImage.s_map_ren_1.drawBitmap(canvas, kaiQiXiaYiGuanAni_x * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][VeggiesData.getCurrentLevel()+GAP][0]] + ".png")/2 * r - mySprite.getImageWidth("ren_1.png")*r, kaiQiXiaYiGuanAni_y * r + bgy + tempY - mySprite.getImageHeight("ren_1.png")/2*r, r , r , 255,0,0,0);											
			}else
				mySprite.drawBitmap(canvas, "ren_1.png", kaiQiXiaYiGuanAni_x * r + bgx + tempX - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][VeggiesData.getCurrentLevel()+GAP][0]] + ".png")/2 * r - mySprite.getImageWidth("ren_1.png")*r, kaiQiXiaYiGuanAni_y * r + bgy + tempY - mySprite.getImageHeight("ren_1.png")/2*r, r , r , 255,0,0,0);											
			for(int i=0;i<friendLevelIcon.size();++i){
				levelIcon icon = friendLevelIcon.get(i);
				float temp_x = mapLocation_xy[layer][icon.level+GAP][0] * r + bgx + tempX - mySprite.getImageWidth("ren_3.png")/2*r;
				float temp_y = mapLocation_xy[layer][icon.level+GAP][1] * r + bgy + tempY - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][VeggiesData.getCurrentLevel()+GAP][0]] + ".png")/2 * r - mySprite.getImageHeight("ren_3.png")*r - 25*GameConfig.f_zoomy*r;
				ExternalMethods.drawImage(canvas,icon.icon, (9*GameConfig.f_zoomx*r) + temp_x + (0) * mySprite.getImageWidth("ren_3.png")/2*r, temp_y + 10 * GameConfig.f_zoomy * r+(10*GameConfig.f_zoomy*r), r , r , 255,0,0,0);
				GameStaticImage.s_map_ren_2.drawBitmap(canvas, temp_x + (0) * mySprite.getImageWidth("ren_3.png")/2*r, temp_y + 10 * GameConfig.f_zoomy * r, r , r , 255,0,0,0);											
			}
		//画边框
		paintMapKuang(canvas,tempX,tempY);
		canvas.restore();
		//画菜单
		paintMapMenu(canvas);
		
//		Shader mShader = new LinearGradient(0, 0, 50, 50,  
//	                new int[] {  Color.argb(255, 35, 113, 119), Color.argb(255, 246, 214, 114),  
//	                        Color.LTGRAY }, null, Shader.TileMode.REPEAT); 
//	
//		p.setShader(mShader);  
// 
//        RectF oval2 = new RectF(10, 200, 100, 300);
//        canvas.drawRect(oval2, p);

       
	}
	public void DrawList(Canvas canvas,Paint paint, int x,int y, int w,int h, int colorBegin,int colorEnd) {
		if(colorBegin == colorEnd){
			paint.setColor(colorBegin);
//			paint.fillRect(x, y, w, h);
		}else{
			int r0 = (colorBegin >> 16) & 0xff;
			int r1 = (colorEnd >> 16) & 0xff;
			int g0 = (colorBegin >> 8)& 0xff;
			int g1 = (colorEnd >> 8) & 0xff;
			int b0 = (colorBegin) & 0xff;
			int b1 = (colorEnd) & 0xff;
			int F,r,g,b;
			for(int i =0;i<h;++i){
				F = (i << 16)/h;
				r = r0 +((F * (r1-r0)) >> 16);
				g = g0 +((F * (g1-g0)) >> 16);
				b = b0 +((F * (b1-b0)) >> 16);
			    paint.setARGB(0xff, r, g, b);
				RectF oval2 = new RectF(x, y+i, w, i+1);
			    canvas.drawRect(oval2, paint);
			}
		}
	}
	
	private void paintMapKuang(Canvas canvas,float tempX,float tempY) {
//		map_cloud[0].drawBitmap(canvas, 0 * r + bgx + tempX - Configs.GameMapWidth/2 * r, (Configs.GameMapHeight - map_cloud[0].bitmap.getHeight()) * r + bgy + tempY - Configs.GameMapHeight/2 * r, r, r, 255, 0, 0, 0);
//		map_cloud[0].drawBitmap(canvas, Configs.GameMapWidth * r - map_cloud[0].bitmap.getWidth() * r + bgx + tempX - Configs.GameMapWidth/2 * r, (Configs.GameMapHeight - map_cloud[0].bitmap.getHeight()) * r * r + bgy + tempY - Configs.GameMapHeight/2 * r, -r, r, 255, 0, 0, 0);
//		map_cloud[1].drawBitmap(canvas, 0 * r + bgx + tempX - Configs.GameMapWidth/2 * r, Configs.GameMapHeight * r - map_cloud[1].bitmap.getHeight() * r + bgy + tempY - Configs.GameMapHeight/2 * r, r, r, 255, 0, 0, 0);
//		map_cloud[2].drawBitmap(canvas, 0 * r + bgx + tempX - Configs.GameMapWidth/2 * r, 0 * r + bgy + tempY - Configs.GameMapHeight/2 * r, r, r, 255, 0, 0, 0);
//		map_wait.drawBitmap(canvas, wait_x * r + bgx + tempX - map_wait.bitmap.getWidth()/2 * r, wait_y * r + bgy + tempY - map_wait.bitmap.getHeight()/2 * r, r, r, 255, 0, 0, 0);
	}
	
	//大选关菜单界面
	private void paintMapMenu(Canvas canvas) {
		//顶部的金币砖石爱心
		paintTop(canvas);
		
		if (!isHideMail) {
			mySprite.drawBitmap(canvas, "map_mail.png", mail_x, Location.BigMapMenu.mail_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
			mySprite.drawBitmap(canvas, "map_mail_2.png", mailRedFloor_x, Location.BigMapMenu.mailRedFloor_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);			
			 
			GameStaticImage.s_map_num_2[0].drawBitmap(canvas, GameStaticImage.s_map_num_2, (int)(mailRedFloor_x+((mySprite.getImageWidth("map_mail_2.png") - GameStaticImage.s_map_num_2[0].bitmap.getWidth())/2)),  (int)(Location.BigMapMenu.mailRedFloor_y * GameConfig.f_zoomy+((mySprite.getImageHeight("map_mail_2.png") - GameStaticImage.s_map_num_2[0].bitmap.getHeight())/2)), 
					GameConfig.Char_num1, mail_str, null, -2,1);
		}
//		if (VeggiesData.getGameGuanka()[1] >= 0) {
//			if(VeggiesData.getGameGuanka()[4] >= 0 ){
//				mySprite.drawBitmap(canvas, "map_back.png", storeGrayFloor_x, Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
//				mySprite.drawBitmap(canvas, "map_store_2.png", storeWord_x, Location.BigMapMenu.storeWord_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
//				mySprite.drawBitmap(canvas, "map_store.png", store_x, Location.BigMapMenu.store_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);			
//			}
//			
//			mySprite.drawBitmap(canvas, "map_back.png", storeGrayFloor_x, Location.BigMapMenu.CardGrayFloor_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
//			ExternalMethods.drawImage(canvas, bitmap_map_card_2, storeWord_x, Location.BigMapMenu.CardWord_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
//			ExternalMethods.drawImage(canvas, bitmap_map_card, store_x, Location.BigMapMenu.Card_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);	
////			mySprite.drawBitmap(canvas, "map_card_2.png", storeWord_x, Location.BigMapMenu.CardWord_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
////			mySprite.drawBitmap(canvas, "map_card.png", store_x, Location.BigMapMenu.Card_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);			
//		}
		if(bottom!=null)
			bottom.draw(canvas); //底部菜单
		
//		//农场上箭头动画
//		if (isfarmArrowDown) {
//			farmArrowH -= Math.max(2, 0);
//			doorArrowW -= Math.max(2, 0);
//			if (farmArrowH <= 0) {
//				farmArrowH = 0;
//				doorArrowW = 0;
//				isfarmArrowDown = false;
//			}
//		} else {
//			farmArrowH += Math.min(2, 15);
//			doorArrowW += Math.min(2, 15);
//			if (farmArrowH >= 15) {
//				farmArrowH = 15;
//				doorArrowW = 15;
//				isfarmArrowDown = true;
//			}
//		}
//		if(VeggiesData.getGameGuanka()[9] >= 0){
//			float farm1_x =  farm_x-mySprite.getImageWidth("farmbutton2.png")/2*(anjianfarm?0.2f:0f);
//			float farm1_y =  farm_y-mySprite.getImageHeight("farmbutton2.png")/2*(anjianfarm?0.2f:0f)-5*GameConfig.f_zoomy;
//			mySprite.drawBitmap(canvas, "farmbutton2.png", farm1_x,  farm1_y, anjianfarm?1.2f:1f, anjianfarm?1.2f:1f, 255, 0, 0, 0);
//			if(isShowFramPM){
//				timing++;
//				if(!b_switch){
//					if(timing>=3){
//						b_switch = true;
//						timing = 0;
//					}
//				}else{
//					if(timing>=3){
//						b_switch = false;
//						timing = 0;
//					}
//				}
//				farm1_x =  farm_x-mySprite.getImageWidth("tishi.png")/2*((b_switch)?0.2f:0f);
//				farm1_y =  farm_y-mySprite.getImageHeight("tishi.png")/2*((b_switch)?0.2f:0f)-5*GameConfig.f_zoomy;
//				mySprite.drawBitmap(canvas, "tishi.png", farm1_x-mySprite.getImageWidth("tishi.png"),  farm1_y, (b_switch)?1.2f:1f, (b_switch)?1.2f:1f, 255, 0, 0, 0);
//			}
//			
//		}
//		mySprite.drawBitmap(canvas, "back_3.png", door_x-mySprite.getImageWidth("back_3.png")/2*(anjiandoor?0.2f:0f), door_y-mySprite.getImageHeight("back_3.png")/2*(anjiandoor?0.2f:0f), anjiandoor?1.2f:1f, anjiandoor?1.2f:1f, 255, 0, 0, 0);
		
		
	}
	
	/**
	 * 顶部的金币砖石爱心
	 * */
	public void paintTop(Canvas canvas){
		mySprite.drawBitmap(canvas, "map_back.png", Location.BigMapMenu.goldGrayFloor_x * GameConfig.f_zoomx, Location.BigMapMenu.goldGrayFloor_y *GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
		mySprite.drawBitmap(canvas, "map_back.png", Location.BigMapMenu.gemGrayFloor_x * GameConfig.f_zoomx, Location.BigMapMenu.gemGrayFloor_y *GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
		mySprite.drawBitmap(canvas, "map_back.png", Location.BigMapMenu.heartGrayFloor_x * GameConfig.f_zoomx, Location.BigMapMenu.heartGrayFloor_y *GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
	
		mySprite.drawBitmap(canvas, "map_gold.png", Location.BigMapMenu.gold_x * GameConfig.f_zoomx, Location.BigMapMenu.gold_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
		mySprite.drawBitmap(canvas, "map_gem.png", Location.BigMapMenu.gem_x * GameConfig.f_zoomx, Location.BigMapMenu.gem_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
		mySprite.drawBitmap(canvas, "map_heart.png", Location.BigMapMenu.heart_x * GameConfig.f_zoomx, Location.BigMapMenu.heart_y * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
		
		mySprite.drawBitmap(canvas, "map_add.png", Location.BigMapMenu.goldRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * (anjiangold ? 0.2f:0f)), Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * (anjiangold?0.2f:0f)), anjiangold?1.2f:1f, anjiangold?1.2f:1f, 255, 0, 0, 0);
		mySprite.drawBitmap(canvas, "map_add.png", Location.BigMapMenu.gemRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * (anjiangem ? 0.2f:0f)), Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * (anjiangem?0.2f:0f)), anjiangem?1.2f:1f, anjiangem?1.2f:1f, 255, 0, 0, 0);
		mySprite.drawBitmap(canvas, "map_add.png", Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * (anjianheart ? 0.2f:0f)), Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * (anjianheart?0.2f:0f)), anjianheart?1.2f:1f, anjianheart?1.2f:1f, 255, 0, 0, 0);
		
		int leng =  Integer.toString(VeggiesData.getGold()).length();
		int width = GameStaticImage.s_map_num_2[0].bitmap.getWidth();
		int start_x = (int)(Location.BigMapMenu.gold_x* GameConfig.f_zoomx+mySprite.getImageWidth("map_gold.png"));
		int end_x = (int)(Location.BigMapMenu.goldRechange_x* GameConfig.f_zoomx);
		int x =  start_x+( ((end_x - start_x) - (leng*width+(leng-1)))/2);
		GameStaticImage.s_map_num_2[0].drawBitmap(canvas, GameStaticImage.s_map_num_2,    x, (int)(Location.BigMapMenu.gold_num_y * GameConfig.f_zoomy), GameConfig.Char_num1, Integer.toString(VeggiesData.getGold()), null, 1,1);
		
		leng =  Integer.toString(VeggiesData.getGem()).length();
		start_x = (int)(Location.BigMapMenu.gem_x* GameConfig.f_zoomx+mySprite.getImageWidth("map_gem.png"));
		end_x = (int)(Location.BigMapMenu.gemRechange_x* GameConfig.f_zoomx);
		x =  start_x+( ((end_x - start_x) - (leng*width+(leng-1)))/2);
		GameStaticImage.s_map_num_2[0].drawBitmap(canvas, GameStaticImage.s_map_num_2,  x, (int)(Location.BigMapMenu.gem_num_y * GameConfig.f_zoomy), GameConfig.Char_num1, Integer.toString(VeggiesData.getGem()), null, 1,1);
		
		int heart_x = (int)(Location.BigMapMenu.heart_x * GameConfig.f_zoomx);
		int	heart_y = (int)(Location.BigMapMenu.heart_y * GameConfig.f_zoomy);
	 
		String number = Integer.toString(VeggiesData.getHeart());
		int size = number.length();
		int w = GameStaticImage.s_map_num_3[0].bitmap.getWidth();
		heart_x = (int)(heart_x+(((mySprite.getImageWidth("map_heart.png") - (w * size + 1 * (size-1)))/2)));
		heart_y = (int)(heart_y+(mySprite.getImageHeight("map_heart.png") - GameStaticImage.s_map_num_3[0].bitmap.getHeight())/2);
		GameStaticImage.s_map_num_3[0].drawBitmap(canvas, GameStaticImage.s_map_num_3, 
				heart_x, 
				heart_y,
				GameConfig.Char_num1, number, null, 1,1);
		
		
		leng =  time_str.length();
		start_x = (int)(Location.BigMapMenu.heart_x* GameConfig.f_zoomx+mySprite.getImageWidth("map_heart.png"));
		end_x = (int)(Location.BigMapMenu.heartRechange_x* GameConfig.f_zoomx);
		x =  start_x+( ((end_x - start_x) - (leng*width+(leng-1)))/2);
		if (VeggiesData.getHeart() >= Configs.HEART_CDMAX) {
			x =  start_x+( ((end_x - start_x) - (int)(mySprite.getImageWidth("map_full.png")))/2);
			mySprite.drawBitmap(canvas, "map_full.png", x, (int)(Location.BigMapMenu.heartGrayFloor_y * GameConfig.f_zoomy)+(((int)(mySprite.getImageHeight("map_back.png")) -  (int)(mySprite.getImageHeight("map_full.png")))/2 ), null);
		} else {
			GameStaticImage.s_map_num_2[0].drawBitmap(canvas, GameStaticImage.s_map_num_2,  x, (int)(Location.BigMapMenu.heart_time_y * GameConfig.f_zoomy), GameConfig.Char_num2, time_str, null, 0,1);			
		}
	}
	
	int time=0;

	public void run() {
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
//        	}
//		}

			run_smallcardLoadList(); //异步加载
		 if(bottom!=null)
			 bottom.run();
		isShowFramPM = false;
//		if(FacebookOperation.player!=null){
//			FarmData data = FarmModule.farmdata.get(FacebookOperation.player.getid_server());
//			if(data!=null && data.status!=null){
//				if(data.timeNumber()){
//					isShowFramPM = true;
//				}
////				else{	
////					for(int i=0;i<data.status.length;++i){
////						if((byte)data.status[i] == FarmPlant.SURFACE_HARVEST){
////							isShowFramPM = true;
////							break;
////						}
////					}
////				}
//			}
//		}
		
		time++;
		float bei=1.25f;
		float jian=0.2f;
		if(move_r>0){
			move_r--;
			if(move_r<0){
				move_r=0;
			}
		}
		else if(move_r<0){
			move_r++;
			if(move_r>0){
				move_r=0;
			}
		}
		if(move_X_speed>0){
			move_X_speed/=bei;
			move_X_speed-=jian;
			if(move_X_speed<0){
				move_X_speed=0;
			}
		}else if(move_X_speed<0){
			move_X_speed/=bei;
			move_X_speed+=jian;
			if(move_X_speed>0){
				move_X_speed=0;
			}
		}
		
		if(move_Y_speed>0){
			move_Y_speed/=bei;
			move_Y_speed-=jian;
			if(move_Y_speed<0){
				move_Y_speed=0;
			}
		}else if(move_Y_speed<0){
			move_Y_speed/=bei;
			move_Y_speed+=jian;
			if(move_Y_speed>0){
				move_Y_speed=0;
			}
		}
		if(isguanxing){
			if(move_r!=0){
				r+=0.015f*move_r;
				if(r > Configs.max_r){
					r = Configs.max_r;
				}else if(r < Configs.min_r){
					r = Configs.min_r;
				}
			}
			if(move_X_speed!=0){
				move_X+=move_X_speed;
			}
			if(move_Y_speed!=0){
				move_Y+=move_Y_speed;
			}
		}else{
			float temp_move_X_speed=move_X-move_X_old;
			float temp_move_Y_speed=move_Y-move_Y_old;
			if(move_r==0){
				float temp_move_r=r-move_r_old;
				if(Math.abs(temp_move_r)>Math.abs(move_r)){
					if(temp_move_r>0){
						move_r=6;
					}else{
						move_r=-6;
					}
//					move_r=temp_move_r;
				}
			}
			if(Math.abs(temp_move_X_speed)>Math.abs(move_X_speed)){
				move_X_speed=temp_move_X_speed;
			}
			if(Math.abs(temp_move_Y_speed)>Math.abs(move_Y_speed)){
				move_Y_speed=temp_move_Y_speed;
			}
		}

		
		int ttt=(int)(50*GameConfig.f_zoom);
		int tempx=(int)(bgx+move_X - Configs.GameMapWidth/2 * r);
		int tempy=(int)(bgy+move_Y - Configs.GameMapHeight/2 * r);
		//纠正靠边缩放地图的位置
		correctMapZoom(tempx, tempy, ttt);
		
		tempx=(int)(bgx + move_X- Configs.GameMapWidth/2 * r);
		tempy=(int)(bgy + move_Y- Configs.GameMapHeight/2 * r);
		ttt=(int)(15*GameConfig.f_zoom);
		//纠正靠边移动地图的位置
		if (isOneTouchMove) {
			correctMapMove(tempx, tempy, ttt);			
		}
		
		
		long currenttime = System.currentTimeMillis();
		if (VeggiesData.getHeart() < Configs.HEART_CDMAX) {
			time_str = "";
			heartCD = currenttime - VeggiesData.heart_time;
			if(heartCD >= CD) {
				int temp_heart_num = (int) (heartCD / CD);
				if (VeggiesData.getHeart() + temp_heart_num > Configs.HEART_CDMAX) {
					VeggiesData.addHeart(Configs.HEART_CDMAX - VeggiesData.getHeart());
					heartCD = 0;
				} else {						
					VeggiesData.addHeart(temp_heart_num);
					heartCD = heartCD % CD;
				}
				VeggiesData.heart_time = System.currentTimeMillis();
			}
			if (VeggiesData.getHeart() < Configs.HEART_CDMAX) {
				heartCD = CD - heartCD;
			} else {
				heartCD = 0;
			}
			long tempCD = heartCD/1000;
			if (tempCD%60 == 0) time_str = "00";
			else if (tempCD%60 < 10) time_str = "0" + tempCD%60;
			else time_str = time_str + tempCD%60;
			tempCD = tempCD/60;
			if (tempCD%60 == 0) time_str = "00:" + time_str;
			else if (tempCD%60 < 10) time_str = "0" + tempCD%60 + ":" + time_str;
			else time_str = tempCD%60 + ":" + time_str;
			
//				heartCD = CD - (currenttime - VeggiesData.systemtime);
//				long tempCD = heartCD/1000;
//				if (tempCD%60 == 0) systemTime = "00";
//				else if (tempCD%60 < 10) systemTime = "0" + tempCD%60;
//				else systemTime = systemTime + tempCD%60;
//				tempCD = tempCD/60;
//				if (tempCD%60 == 0) systemTime = "00:" + systemTime;
//				else if (tempCD%60 < 10) systemTime = "0" + tempCD%60 + ":" + systemTime;
//				else systemTime = tempCD%60 + ":" + systemTime;
		}
		
//		if (isKaiQiXiaYiGuanAni && wait_ani-- < 0) {
//			kaiQiXiaYiGuanAni_x += kaiQiXiaYiGuanAni_move_x;
//			kaiQiXiaYiGuanAni_y += kaiQiXiaYiGuanAni_move_y;
//			if ((kaiQiXiaYiGuanAni_move_x > 0 && kaiQiXiaYiGuanAni_x >= mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+118][0])
//					|| (kaiQiXiaYiGuanAni_move_y > 0 && kaiQiXiaYiGuanAni_y >= mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+118][1])
//					|| (kaiQiXiaYiGuanAni_move_x < 0 && kaiQiXiaYiGuanAni_x <= mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+118][0])
//					|| (kaiQiXiaYiGuanAni_move_y < 0 && kaiQiXiaYiGuanAni_y <= mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+118][1])) {
//				kaiQiXiaYiGuanAni_x = mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+118][0];
//				kaiQiXiaYiGuanAni_y = mapLocation_xy[layer][VeggiesData.getYiKaiQiMaxLevel()+118][1];
//				isKaiQiXiaYiGuanAni = false;
//				wait_ani = 50;
//			} 
//		}
		
		//再加一个装备界面排序
//		for(int i=0; i<VeggiesData.getCardSlot().length; i++) {
//			if (VeggiesData.getCardSlot()[i])
//		}
//		GameEquipmentModule.run_smallcardLoadList();
		
		move_X_old=move_X;
		move_Y_old=move_Y;
		if(move_r==0){
			move_r_old=r;
		}

	}
	
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
//			GameManager.ResetToRunModule(GameMenu.gameMenu);
//			MainActivity.getActivity().closeGame();
			GameManager.ResetToRunModule(GameMenu.gameMenu);
		}
		return false;
	}

	public void Release() {
		s_smallcard_card_index = GameEquipmentModule.smallcard_load_sort.size();
		thread_card = null;
		FacebookOperation.getFacebook().setFriendIcon(null);
		GameStaticImage.delMapMenu();
		GameStaticImage.delInfoEquip();
//		GameImage.delImage(map_door.bitmap);
//		GameImage.delImage(map_door[1].bitmap);
//		map_door = null;
		GameImage.delImage(bitmap_map_card);
		bitmap_map_card=null;
		GameImage.delImage(bitmap_map_card_2);
		bitmap_map_card_2=null;
		
		for(int i=0; i<mapImage.length; i++) {
			if (mapImage[i] != null) {
				GameImage.delImage(mapImage[i].bitmap);	
				mapImage[i] = null;
			}
		}
		mapImage = null;
//		for(int i=0; i<map_star.length; i++) {
//			GameImage.delImage(map_star[i].bitmap);	
//			map_star[i] = null;
//		}
//		map_star = null;
		GameImage.delImageArray(GameStaticImage.s_map_num_1);
		GameStaticImage.s_map_num_1 = null;
		
		if(friendLevelIcon!=null){
			for(int i=0;i<friendLevelIcon.size();++i){
				friendLevelIcon.remove(i);
			}
			friendLevelIcon.clear();
		}
		friendLevelIcon = null;
		
		playerIcon = null;
		
		GameManager.setGT(null);
//		GameImage.delImage(GameStaticImage.s_map_enemy_01.bitmap);
//		GameStaticImage.s_map_enemy_01 = null;
//		GameImage.delImage(GameStaticImage.s_map_enemy_02.bitmap);
//		GameStaticImage.s_map_enemy_02 = null;
//		GameImage.delImage(GameStaticImage.s_map_enemy_04.bitmap);
//		GameStaticImage.s_map_enemy_04 = null;
		GameImage.delImage(GameStaticImage.s_map_ren_1.bitmap);
		GameStaticImage.s_map_ren_1 = null;
		GameImage.delImage(GameStaticImage.s_map_ren_2.bitmap);
		GameStaticImage.s_map_ren_2 = null;
		
		
		if (!GameEquipmentModule.s_smallcard_card.isEmpty()) {
			Iterator<Integer> iterator = GameEquipmentModule.s_smallcard_card.keySet().iterator();
			 while(iterator.hasNext()){
				 int key = iterator.next();
				 Sprite sp = GameEquipmentModule.s_smallcard_card.get(key);
				 GameImage.delImage(sp.bitmap);
//				 iterator.remove();//删除key				
//				 GameEquipmentModule.s_smallcard_card.remove(key); //删除值
			 }
			 GameEquipmentModule.s_smallcard_card.clear();
		}
		mySprite.release();
		mySprite2.release();
		mySprite3.release();
		mySprite = null;
		mySprite2 = null;
		mySprite3 = null;
		if(bottom!=null)
		bottom.delete();
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){  //新手教学
			if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
				startX_1 = event.getX(event.getActionIndex());			//获取第一个触点的 X 坐标
				startY_1 = event.getY(event.getActionIndex());	
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL4){
					int poit[] = bottom.getXYWH(BottomMenu.SHOP);
					 if ( ExternalMethods.CollisionTest(startX_1, startY_1,
							poit[0], //商店
							poit[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
							poit[0]+poit[2], 
							poit[1]+poit[3])) {
						anjianstore = true;
					} 
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL31||GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL46){ //卡库
					if (VeggiesData.getGameGuanka()[1] >= 0 && ExternalMethods.CollisionTest(startX_1, startY_1,
							 bottom.getXYWH(BottomMenu.CARDDEPOT)[0], //商店
							   bottom.getXYWH(BottomMenu.CARDDEPOT)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
							   bottom.getXYWH(BottomMenu.CARDDEPOT)[0]+bottom.getXYWH(BottomMenu.CARDDEPOT)[2], 
							   bottom.getXYWH(BottomMenu.CARDDEPOT)[1]+bottom.getXYWH(BottomMenu.CARDDEPOT)[3])) {
//						GameManager.getGT().setHandType(GameTeaching.RIGHT_HAND);
						GameManager.getGT().finish();
						new VeggiesData().saveGame();
						//图鉴
						GameManager.ResetToRunModule(new CardLibraryModule());
					}
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL26
				|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL33
				|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL38
				|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL45){
					for(int i=0; i<VeggiesData.getGameGuanka().length; i++) {
						float temp_x = mapLocation_xy[layer][GAP+i][0] * r + bgx + move_X - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png")/2*r * 1.2f;
						float temp_y = mapLocation_xy[layer][GAP+i][1] * r + bgy + move_Y - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png")/2*r * 1.2f;
						if (ExternalMethods.CollisionTest(startX_1, startY_1, 
							temp_x, temp_y,
							temp_x + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png") * r * 1.2f,
							temp_y + mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png") * r * 1.2f)) {
							if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL26 && i ==0 && VeggiesData.getGameGuanka()[i] >= 0) {
								anjian_level[i] = true;								
							}else if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL33 && i ==1 && VeggiesData.getGameGuanka()[i] >= 0) {
								anjian_level[i] = true;								
							}else if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL38 && i ==4 && VeggiesData.getGameGuanka()[i] >= 0) {
								anjian_level[i] = true;								
							}else if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL45 &&i ==0 && VeggiesData.getGameGuanka()[i] >= 0) {
								anjian_level[i] = true;								
							}
						}
					}
				}else if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL20 && ExternalMethods.CollisionTest(startX_1, startY_1, 
						 bottom.getXYWH(BottomMenu.FARM)[0], 
						   bottom.getXYWH(BottomMenu.FARM)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
						   bottom.getXYWH(BottomMenu.FARM)[0]+bottom.getXYWH(BottomMenu.FARM)[2], 
						   bottom.getXYWH(BottomMenu.FARM)[1]+bottom.getXYWH(BottomMenu.FARM)[3])) {
					anjianfarm = true;
				}
			}
			if(event.getActionMasked() == MotionEvent.ACTION_UP){
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL4){
					 int poit[] = bottom.getXYWH(BottomMenu.SHOP);
					 if ( ExternalMethods.CollisionTest(startX_1, startY_1,
								poit[0], //商店
								poit[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
								poit[0]+poit[2], 
								poit[1]+poit[3])) {
						anjianstore = false;
						GameManager.getGT().finish();
						new VeggiesData().saveGame();
						GameManager.forbidModule(new GameShop1());
					} 
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL25){
					GameManager.getGT().finish();
					new VeggiesData().saveGame();
				 
					float tempX = move_X;
					float tempY = move_Y;
					int level = 0;
					float temp_x = mapLocation_xy[layer][level+GAP][0] * r + bgx + tempX;
					float temp_y = mapLocation_xy[layer][level+GAP][1] * r + bgy + tempY;
					GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL26, (int)temp_x, (int)temp_y, LangUtil.getLangString(LangDefineClient.GUIDE_26), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
				
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL26
						|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL33
						|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL38
						|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL45){
					for(int i=0; i<VeggiesData.getGameGuanka().length; i++) {
						float temp_x = mapLocation_xy[layer][GAP+i][0] * r + bgx + move_X - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png")/2*r * 1.2f;
						float temp_y = mapLocation_xy[layer][GAP+i][1] * r + bgy + move_Y - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png")/2*r * 1.2f;
						if (anjian_level[i] && ExternalMethods.CollisionTest(startX_1, startY_1, 
							temp_x, temp_y,
							temp_x + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png") * r * 1.2f,
							temp_y + mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png") * r * 1.2f)) {

							 
							anjian_level[i] = false;
							VeggiesData.setCurrentLevel(i);
							if((UserRequest.getUser().getHideLevel() && i == UserRequest.getUser().gethideLevel()) 
									|| (GameManager.getGT()!=null && GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL45)){
								Log.e("new GameMain()", "new GameMain()=2");    
								GameManager.ResetToRunModule(new GameMain());
							}
							else
								GameManager.forbidModule(GameMenu.gameLevelInfoModule);
							
							GameManager.getGT().finish();
							new VeggiesData().saveGame();
						 }
					}
				}else if (VeggiesData.getGameGuanka()[4] >= 0 && GameManager.getGT().getTeachId()!=GameTeaching.TEACH_VOL46&&VeggiesData.getGameGuanka()[1] >= 0 && ExternalMethods.CollisionTest(startX_1, startY_1,
						storeGrayFloor_x - 15 * GameConfig.f_zoomx, //商店
						Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom-2, 
						GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx,
						Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoomy + mySprite.getImageHeight("map_back.png") + 15 * GameConfig.f_zoomy)) {
					sendMessage("商店被点击了");
					if (!GameTeaching.teachingArrary[GameManager.getGT().getTeachId()]) {
						GameManager.getGT().finish();
						new VeggiesData().saveGame();
					}
					GameManager.forbidModule(new GameShop1());
				}else if (GameManager.getGT().getTeachId()!=GameTeaching.TEACH_VOL46&&VeggiesData.getGameGuanka()[1] >= 0 && ExternalMethods.CollisionTest(startX_1, startY_1,
						 bottom.getXYWH(BottomMenu.CARDDEPOT)[0], //商店
						   bottom.getXYWH(BottomMenu.CARDDEPOT)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
						   bottom.getXYWH(BottomMenu.CARDDEPOT)[0]+bottom.getXYWH(BottomMenu.CARDDEPOT)[2], 
						   bottom.getXYWH(BottomMenu.CARDDEPOT)[1]+bottom.getXYWH(BottomMenu.CARDDEPOT)[3])) {
//					if (!GameTeaching.teachingArrary[GameManager.getGT().getTeachId()]) {
//						GameManager.getGT().finish();
//						new VeggiesData().saveGame();
//					}
					//图鉴
					GameManager.ResetToRunModule(new CardLibraryModule());
				}
				else if(anjianfarm && ExternalMethods.CollisionTest(startX_1, startY_1, 
						bottom.getXYWH(BottomMenu.FARM)[0], 
						   bottom.getXYWH(BottomMenu.FARM)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
						   bottom.getXYWH(BottomMenu.FARM)[0]+bottom.getXYWH(BottomMenu.FARM)[2], 
						   bottom.getXYWH(BottomMenu.FARM)[1]+bottom.getXYWH(BottomMenu.FARM)[3])){
//						GameManager.forbidModule(new FarmModule());
						GameManager.forbidModule(new FarmModule1());
						
						GameManager.getGT().finish();
						new VeggiesData().saveGame();
				}
				 
			}
			return;
		}
		
		if(bottom!=null)
			if(bottom.onThou(event))return;
		
		onTouchTop(event);
		
		switch(event.getActionMasked())									//me.getActionMasked()  获取当前动作类型	
		{
		case MotionEvent.ACTION_DOWN:
			isguanxing=false;

			startX_1 = event.getX(event.getActionIndex());			//获取第一个触点的 X 坐标
			startY_1 = event.getY(event.getActionIndex());	
			
			mode1 = Configs.ONETOUCH;
			
			oldX = (int) startX_1;
			oldY = (int) startY_1;
			 if (
					   VeggiesData.getGameGuanka()[4] >= 0 && ExternalMethods.CollisionTest(startX_1, startY_1,
							   bottom.getXYWH(BottomMenu.SHOP)[0], //商店
							   bottom.getXYWH(BottomMenu.SHOP)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
							   bottom.getXYWH(BottomMenu.SHOP)[0]+bottom.getXYWH(BottomMenu.SHOP)[2], 
							   bottom.getXYWH(BottomMenu.SHOP)[1]+bottom.getXYWH(BottomMenu.SHOP)[3])) {
				anjianstore = true;
			} else if (!isHideMail && ExternalMethods.CollisionTest(startX_1, startY_1,
					mail_x - 15 * GameConfig.f_zoomx, //邮件
					Location.BigMapMenu.mail_y * GameConfig.f_zoomy - 15 * GameConfig.f_zoomy, 
					GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx,  
					Location.BigMapMenu.mail_y * GameConfig.f_zoomy + mySprite.getImageHeight("map_mail.png") + 15 * GameConfig.f_zoomy)) {
				anjianmail = true;
			} else if (VeggiesData.getGameGuanka()[9] >= 0 && ExternalMethods.CollisionTest(startX_1, startY_1, 
					bottom.getXYWH(BottomMenu.FARM)[0], 
					   bottom.getXYWH(BottomMenu.FARM)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
					   bottom.getXYWH(BottomMenu.FARM)[0]+bottom.getXYWH(BottomMenu.FARM)[2], 
					   bottom.getXYWH(BottomMenu.FARM)[1]+bottom.getXYWH(BottomMenu.FARM)[3])) {
				anjianfarm = true;
			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
					   bottom.getXYWH(BottomMenu.EXIT)[0], 
					   bottom.getXYWH(BottomMenu.EXIT)[1], 
					   bottom.getXYWH(BottomMenu.EXIT)[0]+bottom.getXYWH(BottomMenu.EXIT)[2], 
					   bottom.getXYWH(BottomMenu.EXIT)[1]+bottom.getXYWH(BottomMenu.EXIT)[3])){
				anjiandoor = true;
			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 0, 0,
					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {
				//修正左上角触碰无响应
				return;
			} else {//关卡按键
				for(int i=0; i<VeggiesData.getGameGuanka().length; i++) {
					float temp_x = mapLocation_xy[layer][GAP+i][0] * r + bgx + move_X - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png")/2*r * 1.2f;
					float temp_y = mapLocation_xy[layer][GAP+i][1] * r + bgy + move_Y - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png")/2*r * 1.2f;
					if (ExternalMethods.CollisionTest(startX_1, startY_1, 
						temp_x, temp_y,
						temp_x + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png") * r * 1.2f,
						temp_y + mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png") * r * 1.2f)) {
						if (VeggiesData.getGameGuanka()[i] >= 0) {
							anjian_level[i] = true;								
						}
					}
				}
				
				for(int i=0; i<BigMapLocation.MapLocation.length; i++) {
					for (int j=0; j<BigMapLocation.MapLocation[i].length; j++) {				
						if(VeggiesData.getGameGuanka()[29] >= 1 && (int) BigMapLocation.MapLocation[i][j][0] == 41){
							float temp_x = mapLocation_xy[i][j][0] * r + bgx + move_X - mySprite3.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r * 1.2f;
							float temp_y = mapLocation_xy[i][j][1] * r + bgy + move_Y - mySprite3.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r * 1.2f;
							if(!VeggiesData.getBossLevel()[0] && ExternalMethods.CollisionTest(startX_1, startY_1, 
										temp_x, temp_y,
										temp_x + mySprite3.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png") * r * 1.2f,
										temp_y + mySprite3.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png") * r * 1.2f)){
								  anjian_boos_1 = true;
							  }
						}
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:	
			isguanxing=true;
			
			startX_1 = event.getX(event.getActionIndex());
			startY_1 = event.getY(event.getActionIndex());
			
			mode1 = Configs.NONE;
			isTwoTouchMoveZoomBig = false;
        	isTwoTouchMoveZoomSmall = false;
        	
//			if (anjiangold && ExternalMethods.CollisionTest(startX_1, startY_1, 
//					0,//Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
//					Location.BigMapMenu.goldRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
//					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {
//				sendMessage("金币充值被点击了");
//				GameManager.forbidModule(new RechargeGold());
//			} else if (anjiangem && ExternalMethods.CollisionTest(startX_1, startY_1, 
//					0,//Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
//					Location.BigMapMenu.gemRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
//					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {					
//				sendMessage("宝石充值被点击了");
//				GameManager.forbidModule(new RechargeDiamond());
//			} else if (anjianheart && ExternalMethods.CollisionTest(startX_1, startY_1, 
//					0,//Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
//					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
//					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {
////				ExternalMethods.CollisionTest(startX_1, startY_1, 
////						0,//Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
////						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
////						Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth()/2 * 1.2f,
////						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight()/2 * 1.2f)
//				sendMessage("心充值被点击了");
////				if (VeggiesData.getHeart() <= Configs.HEART_MAX - 1) {
////					VeggiesData.addHeart(1);
////				}
//				GameManager.forbidModule(new HeartRechargeModule());
//			}else 
        	if (VeggiesData.getGameGuanka()[1] >= 0 && anjianstore && ExternalMethods.CollisionTest(startX_1, startY_1,
					  bottom.getXYWH(BottomMenu.SHOP)[0], //商店
					   bottom.getXYWH(BottomMenu.SHOP)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
					   bottom.getXYWH(BottomMenu.SHOP)[0]+bottom.getXYWH(BottomMenu.SHOP)[2], 
					   bottom.getXYWH(BottomMenu.SHOP)[1]+bottom.getXYWH(BottomMenu.SHOP)[3])) {
				sendMessage("商店被点击了");
//				if (!GameTeaching.teachingArrary[GameManager.getGT().getTeachId()]) {
//					GameManager.getGT().finish();
//					new VeggiesData().saveGame();
//				}
				
				GameManager.forbidModule(new GameShop1());
//				isGotoShop = true;
			} else if (VeggiesData.getGameGuanka()[1] >= 0 && ExternalMethods.CollisionTest(startX_1, startY_1,
					 bottom.getXYWH(BottomMenu.CARDDEPOT)[0], //商店
					   bottom.getXYWH(BottomMenu.CARDDEPOT)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
					   bottom.getXYWH(BottomMenu.CARDDEPOT)[0]+bottom.getXYWH(BottomMenu.CARDDEPOT)[2], 
					   bottom.getXYWH(BottomMenu.CARDDEPOT)[1]+bottom.getXYWH(BottomMenu.CARDDEPOT)[3])) {
//				if (!GameTeaching.teachingArrary[GameManager.getGT().getTeachId()]) {
//					GameManager.getGT().finish();
//					new VeggiesData().saveGame();
//				}
				//图鉴
				GameManager.ResetToRunModule(new CardLibraryModule());
			}else if (!isHideMail && anjianmail && ExternalMethods.CollisionTest(startX_1, startY_1,
					mail_x - 15 * GameConfig.f_zoomx, //邮件
					Location.BigMapMenu.mail_y * GameConfig.f_zoomy - 15 * GameConfig.f_zoomy, 
					GameConfig.GameScreen_Width + 15 * GameConfig.f_zoomx,  
					Location.BigMapMenu.mail_y * GameConfig.f_zoomy + mySprite.getImageHeight("map_mail.png") + 15 * GameConfig.f_zoomy)) {
				sendMessage("邮件被点击了");
//				isHideMail = true;
				GameManager.forbidModule(new MessageModule());
			} else if (VeggiesData.getGameGuanka()[9] >= 0 && anjianfarm && ExternalMethods.CollisionTest(startX_1, startY_1, 
					bottom.getXYWH(BottomMenu.FARM)[0], 
					   bottom.getXYWH(BottomMenu.FARM)[1],//Location.BigMapMenu.storeGrayFloor_y * GameConfig.f_zoom, 
					   bottom.getXYWH(BottomMenu.FARM)[0]+bottom.getXYWH(BottomMenu.FARM)[2], 
					   bottom.getXYWH(BottomMenu.FARM)[1]+bottom.getXYWH(BottomMenu.FARM)[3])) {
				sendMessage("农场被点击了");
//				GameManager.forbidModule(new FarmModule());
				GameManager.forbidModule(new FarmModule1());
				
//				弹出框的例子
//				String temp = MainActivity.getActivity().getString(R.string.setnumber);
//				temp = temp.replace("{number}", 10+"");
//				
//				temp = MainActivity.getActivity().getString(R.string.unlock_star);
//				temp = temp.replace("{number}", PopUp.UNLOCK_NUMBER[0]+"");
//				
//				GameManager.setPopUp(PopUp.UNLOCK,GameStaticImage.shop_shop_gold_11, new PopUp(temp) {
//				 
//				});
				
				
//				if (VeggiesData.getHeart() == Configs.HEART_MAX) VeggiesData.heart_time = System.currentTimeMillis();
//				if (VeggiesData.getHeart() > 0)	VeggiesData.addHeart(-1);
			} else if (anjiandoor && ExternalMethods.CollisionTest(startX_1, startY_1, 
					bottom.getXYWH(BottomMenu.EXIT)[0], 
					   bottom.getXYWH(BottomMenu.EXIT)[1], 
					   bottom.getXYWH(BottomMenu.EXIT)[0]+bottom.getXYWH(BottomMenu.EXIT)[2], 
					   bottom.getXYWH(BottomMenu.EXIT)[1]+bottom.getXYWH(BottomMenu.EXIT)[3])){
				sendMessage("退出被点击了");
				GameManager.ResetToRunModule(GameMenu.gameMenu);
			}  else if (ExternalMethods.CollisionTest(startX_1, startY_1, 0, 0,
					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png")/2 * 1.2f,
					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png")/2 * 1.2f)) {
				//return;
			} else {
//				for(int i=0; i<button_d.length; i++) {
//					int j = 0;
//					if (i == 59 || i == 29) j = 3;
//					float temp_x = button_d[i][0] * r + bgx + move_X - GameStaticImage.s_map_button[j].bitmap.getWidth()/2*r * 1.2f;
//					float temp_y = button_d[i][1] * r + bgy + move_Y - GameStaticImage.s_map_button[j].bitmap.getHeight()/2*r * 1.2f;
//					if (anjian_level[i] && ExternalMethods.CollisionTest(startX_1, startY_1, 
//						temp_x, temp_y,
//						temp_x + GameStaticImage.s_map_button[j].bitmap.getWidth() * r * 1.2f,
//						temp_y + GameStaticImage.s_map_button[j].bitmap.getHeight() * r * 1.2f)) {
//						sendMessage("第 " + (i+1) + "关被点击~");
//						VeggiesData.setCurrentLevel(i+1);
//						if (VeggiesData.getCurrentLevel() == 1) {
//							FriendScore.isFaceBookLoginSuccess = true;
//						} else {								
//							FriendScore.isFaceBookLoginSuccess = false;
//						}
//						VeggiesData.setCurrentLevel(i);
////						GameManager.forbidModule(GameMenu.gameLevelInfoModule);
//						isGotoGLifo = true;
//					}
//				}
				for(int i=0; i<VeggiesData.getGameGuanka().length; i++) {
					float temp_x = mapLocation_xy[layer][GAP+i][0] * r + bgx + move_X - mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png")/2*r * 1.2f;
					float temp_y = mapLocation_xy[layer][GAP+i][1] * r + bgy + move_Y - mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png")/2*r * 1.2f;
					if (anjian_level[i] && ExternalMethods.CollisionTest(startX_1, startY_1, 
						temp_x, temp_y,
						temp_x + mySprite.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png") * r * 1.2f,
						temp_y + mySprite.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[layer][GAP+i][0]] + ".png") * r * 1.2f)) {
						sendMessage("第 " + (i+1) + "关被点击~");
						//VeggiesData.setCurrentLevel(i+1);
//						if (VeggiesData.getCurrentLevel() == 1) {
//							FriendScore.isFaceBookLoginSuccess = true;
//						} else {								
//							FriendScore.isFaceBookLoginSuccess = false;
//						}
//						if(!VeggiesData.getBossLevel()[0] && (i+1) == VeggiesData.BOOS_LEVEL1){
//							if(VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL1-1] >= 0){
//								boosLevel(i);
//							}
//						}else if(!VeggiesData.getBossLevel()[1] && (i+1) == VeggiesData.BOOS_LEVEL2){
//							if(VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL2-1] >= 0){
//								boosLevel(i);
//							}
//						}else{
							InfoModule(i);
//						}
						
						
//						isGotoGLifo = true;
					}
				}
				for(int i=0; i<BigMapLocation.MapLocation.length; i++) {
					for (int j=0; j<BigMapLocation.MapLocation[i].length; j++) {				
						if(anjian_boos_1 && VeggiesData.getGameGuanka()[29] >= 1 && (int) BigMapLocation.MapLocation[i][j][0] == 41){
							float temp_x = mapLocation_xy[i][j][0] * r + bgx + move_X - mySprite3.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r * 1.2f;
							float temp_y = mapLocation_xy[i][j][1] * r + bgy + move_Y - mySprite3.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png")/2*r * 1.2f;
							if(ExternalMethods.CollisionTest(startX_1, startY_1, 
										temp_x, temp_y,
										temp_x + mySprite3.getImageWidth(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png") * r * 1.2f,
										temp_y + mySprite3.getImageHeight(BigMapLocation.MapImagePath[(int) BigMapLocation.MapLocation[i][j][0]] + ".png") * r * 1.2f)){
								   if(!VeggiesData.getBossLevel()[0]){
//										if(VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL1-1] >= 0){
											boosLevel(VeggiesData.BOOS_LEVEL1-1);
//										}
									}
//								  else if(!VeggiesData.getBossLevel()[1] && (i+1) == VeggiesData.BOOS_LEVEL2){
//										if(VeggiesData.getGameGuanka()[VeggiesData.BOOS_LEVEL2-1] >= 0){
//											boosLevel(i);
//										}
//									}
							  }
						}
					}
				}
			}
			for (int i=0; i<anjian_level.length; i++) {
				anjian_level[i] = false;
			}
			isOneTouchMove = true;
			anjian_boos_1 = false;
			//纠正靠边移动地图的位置
			correctMapMove((int)(bgx + move_X- Configs.GameMapWidth/2 * r), (int)(bgy + move_Y- Configs.GameMapHeight/2 * r), (int)(15*GameConfig.f_zoom));

			cleanAnJianState();
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = (int) spacing(event);  
            if (oldDist > 10f)  
            {  
                mode1 = Configs.TWOTOUCH;
                int tempx=(int)(Math.min(event.getX(0), event.getX(1))+(Math.max(event.getX(0), event.getX(1))-Math.min(event.getX(0), event.getX(1)))/2);
                int tempy=(int)(Math.min(event.getY(0), event.getY(1))+(Math.max(event.getY(0), event.getY(1))-Math.min(event.getY(0), event.getY(1)))/2);
                sizew=(tempx-(bgx+move_X - Configs.GameMapWidth*r/2))/(float)(Configs.GameMapWidth*r);
                sizeh=(tempy-(bgy+move_Y- Configs.GameMapHeight*r/2))/(float)(Configs.GameMapHeight*r);
            }
            cleanAnJianState();
			break;
		case MotionEvent.ACTION_POINTER_UP: 
			mode1 = Configs.NONE;
			isTwoTouchMoveZoomBig = false;
        	isTwoTouchMoveZoomSmall = false;
        	cleanAnJianState();
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode1 == Configs.TWOTOUCH) {
				// 正在移动的点距初始点的距离   
                float newDist = spacing(event);
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
				
                
                if (newDist > oldDist) {
                	if (r + (newDist - oldDist) * Configs.r_zoom_unit < Configs.max_r){
                		r = r + (newDist - oldDist) * Configs.r_zoom_unit;
                		getrrr((newDist - oldDist) * Configs.r_zoom_unit*Configs.GameMapWidth,(newDist - oldDist) * Configs.r_zoom_unit*Configs.GameMapHeight,sizew,sizeh);
                	}
                	else {
                		getrrr((Configs.max_r - r)*Configs.GameMapWidth,(Configs.max_r - r)*Configs.GameMapHeight,sizew,sizeh);
                		r = Configs.max_r;
                	}
                } else if (newDist < oldDist) {                	
                	if (r - (oldDist - newDist) * Configs.r_zoom_unit > Configs.min_r) {
                		r = r - (oldDist - newDist) * Configs.r_zoom_unit; 
                		getrrr(-(oldDist - newDist) * Configs.r_zoom_unit*Configs.GameMapWidth,-(oldDist - newDist) * Configs.r_zoom_unit*Configs.GameMapHeight,sizew,sizeh);
                	}
                	else {
                		getrrr(-(r - Configs.min_r)*Configs.GameMapWidth,-(r - Configs.min_r)*Configs.GameMapHeight,sizew,sizeh);
                		r = Configs.min_r;
                	}
                }
                oldDist = (int)newDist;
			}
			
			//if (1按下的时候)
			if (mode1 == Configs.ONETOUCH) {
				isOneTouchMove = false;
				
				int tempX = (int) event.getX();
				int tempY = (int) event.getY();
				
				move_X+=tempX-oldX;
				move_Y+=tempY-oldY;
				
				oldX = (int) event.getX();
				oldY = (int) event.getY();
				
				if (tempX - startX_1 > 5 * GameConfig.f_zoomx || tempY - startY_1 > 5 * GameConfig.f_zoomy) {
					//三星s3机型点击的问题	(按键事件down->move->up)
					for (int i = 0; i < anjian_level.length; i++) {
						anjian_level[i] = false;
					}
					cleanAnJianState();
				}
				
				int ttt=(int)(50*GameConfig.f_zoom);
				int tempx=(int)(bgx+move_X - Configs.GameMapWidth/2 * r);
				int tempy=(int)(bgy+move_Y - Configs.GameMapHeight/2 * r);
				if(tempx>ttt){
					move_X=ttt-bgx+Configs.GameMapWidth/2*r;
				}else if(tempx<GameConfig.GameScreen_Width-Configs.GameMapWidth*r-ttt){
					move_X = GameConfig.GameScreen_Width-Configs.GameMapWidth*r-ttt-bgx+ Configs.GameMapWidth/2*r;
				}
				
				if(tempy>ttt){
					move_Y=ttt+Configs.GameMapHeight/2 * r-bgy;
				}else if(tempy<GameConfig.GameScreen_Height-Configs.GameMapHeight*r-ttt){
					move_Y =GameConfig.GameScreen_Height-Configs.GameMapHeight*r-ttt-bgy+ Configs.GameMapHeight/2 * r;
				}
			}
			break;
		}
	}

	public void onTouchTop(MotionEvent event){
		startX_1 = event.getX(event.getActionIndex());			//获取第一个触点的 X 坐标
		startY_1 = event.getY(event.getActionIndex());	
		switch(event.getActionMasked())									//me.getActionMasked()  获取当前动作类型	
		{
		case MotionEvent.ACTION_DOWN:
			if (ExternalMethods.CollisionTest(startX_1, startY_1, 
					0,//Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
					Location.BigMapMenu.goldRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {
				anjiangold = true;
			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
					0,//Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
					Location.BigMapMenu.gemRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {
				anjiangem = true;
			} else if (ExternalMethods.CollisionTest(startX_1, startY_1, 
					0,//Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {
				anjianheart = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (anjiangold && ExternalMethods.CollisionTest(startX_1, startY_1, 
					0,//Location.BigMapMenu.goldRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
					Location.BigMapMenu.goldRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
					Location.BigMapMenu.goldRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {
				sendMessage("金币充值被点击了");
				GameManager.forbidModule(new RechargeGold());
			} else if (anjiangem && ExternalMethods.CollisionTest(startX_1, startY_1, 
					0,//Location.BigMapMenu.gemRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
					Location.BigMapMenu.gemRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
					Location.BigMapMenu.gemRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {					
				sendMessage("宝石充值被点击了");
				GameManager.forbidModule(new RechargeDiamond());
			} else if (anjianheart && ExternalMethods.CollisionTest(startX_1, startY_1, 
					0,//Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f), 
					Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (mySprite.getImageWidth("map_add.png")/2 * 0.2f) + mySprite.getImageWidth("map_add.png") * 1.2f,
					Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (mySprite.getImageHeight("map_add.png")/2 * 0.2f) + mySprite.getImageHeight("map_add.png") * 1.2f)) {
//				ExternalMethods.CollisionTest(startX_1, startY_1, 
//						0,//Location.BigMapMenu.heartRechange_x * GameConfig.f_zoom - (rechange.bitmap.getWidth()/2 * 0.2f), 
//						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f), 
//						Location.BigMapMenu.heartRechange_x * GameConfig.f_zoomx - (GameStaticImage.s_map_add.bitmap.getWidth()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getWidth()/2 * 1.2f,
//						Location.BigMapMenu.heartRechange_y * GameConfig.f_zoomy - (GameStaticImage.s_map_add.bitmap.getHeight()/2 * 0.2f) + GameStaticImage.s_map_add.bitmap.getHeight()/2 * 1.2f)
				sendMessage("心充值被点击了");
//				if (VeggiesData.getHeart() <= Configs.HEART_MAX - 1) {
//					VeggiesData.addHeart(1);
//				}
				GameManager.forbidModule(new HeartRechargeModule());
			}
		break;
		}
	}
	
	//画椭圆草地、背景色
	private void paintBackgroundColor(Canvas canvas, float x1, float y1, float x2, float y2, int argb, boolean isOval) {
		Paint p = new Paint();
		RectF oval2 = new RectF(x1, y1, x2, y2);
        p.setColor(argb);
        if (isOval)
        	canvas.drawOval(oval2, p);
        else
        	canvas.drawRect(oval2, p);
	}
	
	/**
     * 纠正大题图缩放
     * @param tempx
     * @param tempy
     * @param ttt
     */
    private void correctMapZoom(int tempx, int tempy, int ttt) {
    	if(tempx>ttt){
			move_X-=tempx-ttt;
		}else if(tempx<GameConfig.GameScreen_Width-Configs.GameMapWidth*r-ttt){
			move_X+=(GameConfig.GameScreen_Width-Configs.GameMapWidth*r)-tempx-ttt;
		}
		
		if(tempy>ttt){
			move_Y-=tempy-ttt;
		}else if(tempy<GameConfig.GameScreen_Height-Configs.GameMapHeight*r-ttt){
			move_Y+=(GameConfig.GameScreen_Height-Configs.GameMapHeight*r)-tempy-ttt;
		}
    }
    /**
     * 纠正地图靠边移动
     * @param tempx
     * @param tempy
     * @param ttt
     */
    private void correctMapMove(int tempx, int tempy, int ttt) {
    	if(tempx>0){
			move_X-=Math.min(tempx, ttt);
		}else if(tempx<GameConfig.GameScreen_Width-Configs.GameMapWidth*r){
			move_X+=Math.min(ttt,(GameConfig.GameScreen_Width-Configs.GameMapWidth*r)-tempx);
		}
		
		if(tempy>0){
			move_Y-=Math.min(tempy, ttt);
		}else if(tempy<GameConfig.GameScreen_Height-Configs.GameMapHeight*r){
			move_Y+=Math.min(ttt,(GameConfig.GameScreen_Height-Configs.GameMapHeight*r)-tempy);
		}
    }
    
    public void getrrr(float addW,float addH,float sizew,float sizeh) {
		bgx += addW*(0.5f-sizew);
		bgy += addH*(0.5f-sizeh);
	}
    
    public static void sendMessage(String str) {
    	Message msg = new Message();
		msg.what = 1;
		msg.obj = str;
//		Main.dialogHandler.sendMessage(msg);
    }
    //清理按键状态
    private void cleanAnJianState() {
    	anjiangold = false;
		anjiangem = false;
		anjianheart = false;
		anjianfarm = false;
		anjianstore = false;
		anjianmail = false;
		anjiandoor = false;
    }
    /** 
     * 求出2个触点间的 距离 
     *  
     * @param event 
     * @return 
     */  
    private float spacing(MotionEvent event)  
    {  
        float x = event.getX(0) - event.getX(1);  
        float y = event.getY(0) - event.getY(1);  
        return FloatMath.sqrt(x * x + y * y);  
    }  
    //图片坐标初始化
	private float initIsBitmapCoordinateX(float s_x, float bitmapW) {
		return s_x * GameConfig.f_zoomx + bitmapW/2 - Configs.GameMapWidth/2;
	}
	private float initIsBitmapCoordinateY(float s_y, float bitmapH) {
		return s_y * GameConfig.f_zoomy + bitmapH/2 - Configs.GameMapHeight/2;
	}
	private float initIsBitmapCoordinateX(float s_x, Sprite bitmap) {
		return s_x * GameConfig.f_zoomx + bitmap.bitmap.getWidth()/2 - Configs.GameMapWidth/2;
	}
	private float initIsBitmapCoordinateY(float s_y, Sprite bitmap) {
		return s_y * GameConfig.f_zoomy + bitmap.bitmap.getHeight()/2 - Configs.GameMapHeight/2;
	}
	
	private int initIsNotBitmapCoordinateX(int original_x) {
		return (int) ((original_x * GameConfig.f_zoomx));
	}
	private int initIsNotBitmapCoordinateY(int original_y) {
		return (int) ((original_y * GameConfig.f_zoomy));
	}
	
	/**
	 * 进入关卡详情
	 * */
	private void InfoModule(int i){
		VeggiesData.setCurrentLevel(i);
		if(UserRequest.getUser().getHideLevel() && i == UserRequest.getUser().gethideLevel()){
			Log.e("new GameMain()", "new GameMain()=1");
			 GameManager.ResetToRunModule(new GameMain());
		}
		else
			GameManager.forbidModule(GameMenu.gameLevelInfoModule);
	}
	
	/**
	 * boss关卡的进入
	 * */
	private void boosLevel(final int level){
		String temp = LangUtil.getLangString(LangDefineClient.UNLOCK_STARS);
		temp = temp.replace("X", ""+PopUp.UNLOCK_NUMBER[0]);
		GameManager.setPopUp(PopUp.UNLOCK, null, new PopUp(temp) {
			@Override
			public byte onTouch(MotionEvent event) {
				// TODO Auto-generated method stub
				byte temp = super.onTouch(event);
				if(temp == PopUp.onTouch_unlockExit_SART){
						int star = 0;
						for(int i=0;i<VeggiesData.getGameGuanka().length;++i){
							if(VeggiesData.getGameGuanka()[i] >=0){
								star+=VeggiesData.getGameGuanka()[i];
							}else
								break;
						}
                        if(star>=PopUp.UNLOCK_NUMBER[0]){
//                        	InfoModule(level);
                        	if((level+1) == VeggiesData.BOOS_LEVEL1){
                        		VeggiesData.setBossLevel(0, true);
                        	}else if((level+1) == VeggiesData.BOOS_LEVEL2){
                        		VeggiesData.setBossLevel(1, true);
                        	}
                        	
                        }else{
                        	String temp1 = LangUtil.getLangString(LangDefineClient.UNLOCK_STARS);
                    		temp1 = temp1.replace("X", ""+PopUp.UNLOCK_NUMBER[0]);
                        	GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
                        }
					return -1;
				}else if(temp == PopUp.onTouch_unlockExit_GET){
					if(VeggiesData.getGem() >= PopUp.UNLOCK_NUMBER[1]){
//						InfoModule(level);
						VeggiesData.addGem(-PopUp.UNLOCK_NUMBER[1]);
                    	if((level+1) == VeggiesData.BOOS_LEVEL1){
                    		VeggiesData.setBossLevel(0, true);
                    	}else if((level+1) == VeggiesData.BOOS_LEVEL2){
                    		VeggiesData.setBossLevel(1, true);
                    	}
					}else{
						String temp1 = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
                    	GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
					}
					return -1;
				}
				return temp;
			}
		});
	}
	
	class levelIcon{
		Bitmap icon;
		int level;
		String name;
		public levelIcon(Bitmap _icon, String name, int _level){
			icon = _icon;
			level = _level;
		}
	}

	@Override
	public void onIcon(FaceBookPlayer playerIcon) {
		// TODO Auto-generated method stub
		if((FacebookOperation.player != null && playerIcon == null ) || (friendLevelIcon != null && friendLevelIcon.size()==0 )){
			infoLevelIcon();
		}
		if(FacebookOperation.player != null && FacebookOperation.player.getName().equals(playerIcon.getName())){
			float width = 44*GameConfig.f_zoomx;
			float height = 44*GameConfig.f_zoomy;
			this.playerIcon = GameImage.zoomImage(playerIcon.getIcon(), width, height);
		}else {
			if(friendLevelIcon != null)
				for(int i=0;i<friendLevelIcon.size();++i){
					levelIcon icon = friendLevelIcon.get(i);
					if(icon.level == playerIcon.getMaxLevel()-1){
						float width = 44*GameConfig.f_zoomx;
						float height = 44*GameConfig.f_zoomy;
						// 图片缩放
						Bitmap temp = GameImage.zoomImage(playerIcon.getIcon(), width, height);//user.getIcon();
						if(temp!=null){
							icon.icon = temp;
						}
					}
				}
		}
	}
}

