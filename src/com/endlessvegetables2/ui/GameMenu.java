package com.endlessvegetables2.ui;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.SocoNetCallBack;
import com.audio.Music;
import com.kokatlaruruxi.wy.R;
import com.facebook.sdk.FBInterface;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameStory;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.net.Http;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.socogame.coolEdit.CoolEditData;
import com.test.ResponseHandler;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class GameMenu extends Module{
//	private Sprite bg;
//	private Sprite bg1;
//	private Sprite title;
//	private Sprite play;
//	private Sprite menubg;
//	private Sprite[] menu;
//	private Sprite[] smallmenu;
	
	private boolean anjian_play;
	private boolean anjian_menu[] = {false,false,false};
	private boolean anjian_smallmenu[] = {false,false};
	
	private float r = 0f;
	private float[] ani_r = {0.0f,0.2f,0.6f,1.2f,1.0f};
	private int index=0;
	private static boolean isAni = true;
	public static boolean isBack = false;
	public static boolean isNextModule = false;
	
	public static GameMenu gameMenu;
	public static ChooseLevelModule2 chooseLevelModule;
	public static GameEquipmentModule gameEquipmentModule;
	public static GameLevelInfoModule gameLevelInfoModule;
	public static TakeCardModule takeCardModule;
	 
	public boolean initialize() {
		 
	 
		if (!Configs.isFirst) {
//			Configs.isGameToMenu = false;
//			GameStaticImage.loadStaticImage();
//			GameStaticImage.loadImage();
//			new LoadSmallThread().start();
//			GameManager.forbidModule(GameMenu.chooseLevelModule);
//			initMapLevel();
			
			r = 0.0f;
			index = 0;
			isAni = true;
			GameStaticImage.loadMenuImage();
			 
		} else {			
			CoolEditData.init();
			
			
			Main.initsound();
			if(!VeggiesData.isMuteMusic()){
				GameMedia.playMusic(R.raw.loginl, true, true);
			}
//			GameStaticImage.loadStaticImage();
			
			GameStaticImage.loadMenuImage();
			Configs.isFirst = false;
			
			if(VeggiesData.isTestMode) {
				//VeggiesData.intiTestMode();
			}
			if(VeggiesData.isTestCardOpen){
				VeggiesData.initCardOpen();
			}
		}
		//FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_INIT);
//		bg = new Sprite(GameImage.getImage(GameStaticImage.interface_BG_01));
//		bg1 = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_back_10));
//		title = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_logo));
//		play = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_play));
//		menubg = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_back_09));
//		menu = new Sprite[3];
//		menu[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_success));
//		menu[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_facebook));
//		menu[2] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_card));
//		smallmenu = new Sprite[2];
//		smallmenu[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_about));
//		smallmenu[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_set));
		
		if (chooseLevelModule == null) {			
			gameMenu = new GameMenu();
			chooseLevelModule = new ChooseLevelModule2();
			gameEquipmentModule = new GameEquipmentModule();
			gameLevelInfoModule = new GameLevelInfoModule();
			takeCardModule = new TakeCardModule();
		}
		
		GameImage.showImageHashMap();
		
		return false;
	}
	@Override
	public void onreStart() {
		// TODO Auto-generated method stub
		super.onreStart();
	}
	public void paint(Canvas canvas) {
		float tempy = 0;
		if (GameStaticImage.s_interface_smallmenu == null || GameStaticImage.s_interface_smallmenu[1] == null) return;
		GameStaticImage.s_loading_bg.drawBitmap(canvas, GameStaticImage.s_loading_bg.bitmap, GameConfig.GameScreen_Width/2-GameStaticImage.s_loading_bg.bitmap.getWidth()/2,GameConfig.GameScreen_Height/2-GameStaticImage.s_loading_bg.bitmap.getHeight()/2, null);
		if (r != 0) {
//			bg1.drawBitmap(canvas, bg1.bitmap, 0, 148 * GameConfig.f_zoom, null);
			GameStaticImage.s_interface_ui_back_10.drawBitmap(canvas, 0 + GameStaticImage.s_interface_ui_back_10.bitmap.getWidth()/2*(1-r), 148 * GameConfig.f_zoomy + GameStaticImage.s_interface_ui_back_10.bitmap.getHeight()/2*(1-r), r, r, 255, 0, 0, 0);
//			title.drawBitmap(canvas, title.bitmap, 37 * GameConfig.f_zoom, 82 * GameConfig.f_zoom, null);
			GameStaticImage.s_interface_ui_logo.drawBitmap(canvas, 37 * GameConfig.f_zoomx + GameStaticImage.s_interface_ui_logo.bitmap.getWidth()/2*(1-r), 82 * GameConfig.f_zoomy + GameStaticImage.s_interface_ui_logo.bitmap.getHeight()/2*(1-r), r, r, 255, 0, 0, 0);
			
			tempy = GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 603) * GameConfig.f_zoomy;
			GameStaticImage.s_interface_ui_play.drawBitmap(canvas, 155 * GameConfig.f_zoomx - (anjian_play?0.2f:0f) * GameStaticImage.s_interface_ui_play.bitmap.getWidth()/2 * r,  tempy - (anjian_play?0.2f:0f) * GameStaticImage.s_interface_ui_play.bitmap.getHeight()/2 * r, (anjian_play?1.2f:1.0f) * r, (anjian_play?1.2f:1.0f) * r, 255, 0, 0, 0);
			//menubg.drawBitmap(canvas, menubg.bitmap, 31 * GameConfig.f_zoom, 700 * GameConfig.f_zoom, null);
			tempy = GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 700 - 10) * GameConfig.f_zoomy;
			GameStaticImage.s_interface_ui_back_09.drawBitmap(canvas, 31 * GameConfig.f_zoomx + GameStaticImage.s_interface_ui_back_09.bitmap.getWidth()/2*(1-r), tempy + GameStaticImage.s_interface_ui_back_09.bitmap.getHeight()/2*(1-r), r, r, 255, 0, 0, 0);
			
//			for (int i=0; i<GameStaticImage.s_interface_menu.length; i++) {
//				tempy = GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.GameMenu.menu[i][1] - 10) * GameConfig.f_zoomy;
//				GameStaticImage.s_interface_menu[i].drawBitmap(canvas, Location.GameMenu.menu[i][0] * GameConfig.f_zoomx - (anjian_menu[i]?0.2f:0f) * GameStaticImage.s_interface_menu[i].bitmap.getWidth()/2 * r,  tempy - (anjian_menu[i]?0.2f:0f) * GameStaticImage.s_interface_menu[i].bitmap.getHeight()/2 * r, (anjian_menu[i]?1.2f:1.0f) * r, (anjian_menu[i]?1.2f:1.0f) * r, 255, 0, 0, 0);
//				if(i==0 && VeggiesData.getShow_success()){
//					GameStaticImage.s_interface_ui_new_1.drawBitmap(canvas, (Location.GameMenu.menu[i][0]-10) * GameConfig.f_zoomx - (anjian_menu[i]?0.2f:0f) * GameStaticImage.s_interface_menu[i].bitmap.getWidth()/2 * r,  tempy-5*GameConfig.f_zoomy - (anjian_menu[i]?0.2f:0f) * GameStaticImage.s_interface_menu[i].bitmap.getHeight()/2 * r, (anjian_menu[i]?1.2f:1.0f) * r, (anjian_menu[i]?1.2f:1.0f) * r, 255, 0, 0, 0);
//				}
//			}
			
			for (int i=0; i<GameStaticImage.s_interface_smallmenu.length; i++) {
				GameStaticImage.s_interface_smallmenu[i].drawBitmap(canvas, Location.GameMenu.smallmenu[i][0] * GameConfig.f_zoomx - (anjian_smallmenu[i]?0.2f:0f) * GameStaticImage.s_interface_smallmenu[i].bitmap.getWidth()/2 * r,  Location.GameMenu.smallmenu[i][1] * GameConfig.f_zoomy - (anjian_smallmenu[i]?0.2f:0f) * GameStaticImage.s_interface_smallmenu[i].bitmap.getHeight()/2 * r, (anjian_smallmenu[i]?1.2f:1.0f) * r, (anjian_smallmenu[i]?1.2f:1.0f) * r, 255, 0, 0, 0);
			}
		}
		
//		if (Configs.isDebug) {
//			for (int i=0; i<1; i++) {
//				tempy = GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.GameMenu.menu[i][1] - 10) * GameConfig.f_zoom;
//					GameStaticImage.s_loading_bg.paintCollisionRect(canvas, 
//							Location.GameMenu.menu[i][0] * GameConfig.f_zoom - 0.2f * GameStaticImage.s_interface_menu[i].bitmap.getWidth()/2, 
//							tempy - 0.2f * GameStaticImage.s_interface_menu[i].bitmap.getHeight()/2,
//							Location.GameMenu.menu[i][0] * GameConfig.f_zoom + 1.2f * GameStaticImage.s_interface_menu[i].bitmap.getWidth(), 
//							tempy + 1.2f * GameStaticImage.s_interface_menu[i].bitmap.getHeight());
//			}
//		}
		 
//		if (loading > 0) {
////			GameStaticImage.s_loading_bg.drawBitmap(canvas, GameStaticImage.s_loading_bg.bitmap, GameConfig.GameScreen_Width/2-GameStaticImage.s_loading_bg.bitmap.getWidth()/2,GameConfig.GameScreen_Height/2-GameStaticImage.s_loading_bg.bitmap.getHeight()/2,null);
////			GameStaticImage.load1.drawBitmap(canvas, GameStaticImage.load1.bitmap, GameConfig.GameScreen_Width/2 - GameStaticImage.load1.bitmap.getWidth()/2, GameConfig.GameScreen_Height/2 + GameStaticImage.load1.bitmap.getHeight() + 5 * GameConfig.f_zoom, null);
////			int tempi = 0;
////			if (GameConfig.i_coke % 8 < 8) {
////				tempi = GameConfig.i_coke % 8 / 4 % 4; 
////				if (tempi == 3) tempi = 1;
////				GameStaticImage.load2[tempi].drawBitmap(canvas, GameStaticImage.load2[tempi].bitmap, GameConfig.GameScreen_Width/2 - GameStaticImage.load2[tempi].bitmap.getWidth()/2, GameConfig.GameScreen_Height/2 - GameStaticImage.load2[tempi].bitmap.getHeight() - 5 * GameConfig.f_zoom, null);
////				System.out.println(">>>>>>>>tempi = " + tempi);
////			}
//			if (loading == 32) {
//				loading = -1;
//				GameManager.forbidModule(new CardLibraryModule());
//			} else if (loading == 42) {
//				loading = -1;
////				GameStaticImage.loadImage();
////				GameStaticImage.loadSmallCard();
//				GameManager.ChangeModule(chooseLevelModule);
////				GameStaticImage.delMenuImage();
//			}
//			loading++;
//		}
	}

	public void run() {
		if(isAni) {
			if (GameConfig.i_coke % 2 < 1) {
				if (r != ani_r[ani_r.length-1]) {
					r = ani_r[index++];
				} else if (r == ani_r[ani_r.length-1]) {
					isAni = false;
				}
			}
		}
		
//		if (isNextModule) {
//			isNextModule = false;
//			GameStaticImage.loadImage();
//			GameManager.forbidModule(chooseLevelModule);
//			new LoadSmallThread().start();
//			GameStaticImage.delMenuImage();
//			isNextModule = false;
//		}
		//音效缓存清理
		GameMedia.clearBuffer();
	}

	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			String temp = LangUtil.getLangString(LangDefineClient.EXIT_GAME);
			GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
				@Override
				public byte onTouch(MotionEvent event) {
					// TODO Auto-generated method stub
					byte temp = super.onTouch(event);
					if(temp == PopUp.onTouch_googsExit){
						MainActivity.getActivity().closeGame();
						return -1;
					}
					return temp;
				}
			});
			
			 
			
		}
		return false;
	}

	public void Release() {
//		GameImage.delImage(bg.bitmap);
//		bg = null;
//		GameImage.delImage(bg1.bitmap);
//		bg1 = null;
//		GameImage.delImage(title.bitmap);
//		title = null;
//		GameImage.delImage(play.bitmap);
//		play = null;
//		GameImage.delImage(menubg.bitmap);
//		menubg = null;
//		for (int i=0; i<menu.length;i++) {
//			GameImage.delImage(menu[i].bitmap);
//			menu[i] = null;
//		}
//		menu = null;
//		for (int i=0; i<smallmenu.length;i++) {
//			GameImage.delImage(smallmenu[i].bitmap);
//			smallmenu[i] = null;
//		}
//		smallmenu = null;
		
		GameStaticImage.delMenuImage();
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
//		if (loading > 0) return;
		if (isAni) return;
		float x = event.getX();
		float y = event.getY();
		float tempy = 0;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			tempy = GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 603) * GameConfig.f_zoomy;
			if (ExternalMethods.CollisionTest(x, y, 
					155 * GameConfig.f_zoomx - 0.2f * GameStaticImage.s_interface_ui_play.bitmap.getWidth()/2,  
					tempy - 0.2f * GameStaticImage.s_interface_ui_play.bitmap.getHeight()/2,
					155 * GameConfig.f_zoomx + 1.2f * GameStaticImage.s_interface_ui_play.bitmap.getWidth(),  
					tempy + 1.2f * GameStaticImage.s_interface_ui_play.bitmap.getHeight())) {
				anjian_play = true;
			}
//			for (int i=0; i<GameStaticImage.s_interface_menu.length; i++) {
//				tempy = GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.GameMenu.menu[i][1] - 10) * GameConfig.f_zoomy;
//				if (ExternalMethods.CollisionTest(x, y, 
//						Location.GameMenu.menu[i][0] * GameConfig.f_zoomx - 0.2f * GameStaticImage.s_interface_menu[i].bitmap.getWidth()/2, 
//						tempy - 0.2f * GameStaticImage.s_interface_menu[i].bitmap.getHeight()/2,
//						Location.GameMenu.menu[i][0] * GameConfig.f_zoomx + 1.2f * GameStaticImage.s_interface_menu[i].bitmap.getWidth(), 
//						tempy + 1.2f * GameStaticImage.s_interface_menu[i].bitmap.getHeight())) {
//					anjian_menu[i] = true;
//				}
//			}
			for (int i=0; i<GameStaticImage.s_interface_smallmenu.length; i++) {
				if (ExternalMethods.CollisionTest(x, y, 
						Location.GameMenu.smallmenu[i][0] * GameConfig.f_zoomx - 0.2f * GameStaticImage.s_interface_smallmenu[i].bitmap.getWidth()/2, 
						Location.GameMenu.smallmenu[i][1] * GameConfig.f_zoomy - 0.2f * GameStaticImage.s_interface_smallmenu[i].bitmap.getHeight()/2,
						Location.GameMenu.smallmenu[i][0] * GameConfig.f_zoomx + 1.2f * GameStaticImage.s_interface_smallmenu[i].bitmap.getWidth(), 
						Location.GameMenu.smallmenu[i][1] * GameConfig.f_zoomy + 1.2f * GameStaticImage.s_interface_smallmenu[i].bitmap.getHeight())) {
					anjian_smallmenu[i] = true;
				}
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			tempy = GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 603) * GameConfig.f_zoomy;
			if (anjian_play && ExternalMethods.CollisionTest(x, y, 
					155 * GameConfig.f_zoomx - 0.2f * GameStaticImage.s_interface_ui_play.bitmap.getWidth()/2,  
					tempy - 0.2f * GameStaticImage.s_interface_ui_play.bitmap.getHeight()/2,
					155 * GameConfig.f_zoomx + 1.2f * GameStaticImage.s_interface_ui_play.bitmap.getWidth(),  
					tempy + 1.2f * GameStaticImage.s_interface_ui_play.bitmap.getHeight())) {
				//进入选关界面
//				loading = 42;
//				isNextModule = true;
//				GameManager.forbidModule(chooseLevelModule);
				
				if(!VeggiesData.isStory[0]){
					GameManager.ResetToRunModule(new GameStory());
				}else{
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.clicks, 0);
					GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
				}
//				GameManager.forbidModule(new PauseModule());
			}
			for (int i=0; i<GameStaticImage.s_interface_menu.length; i++) {
				tempy = GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - Location.GameMenu.menu[i][1] - 10) * GameConfig.f_zoomy;
				if (anjian_menu[i] && ExternalMethods.CollisionTest(x, y, 
						Location.GameMenu.menu[i][0] * GameConfig.f_zoomx - 0.2f * GameStaticImage.s_interface_menu[i].bitmap.getWidth()/2, 
						tempy - 0.2f * GameStaticImage.s_interface_menu[i].bitmap.getHeight()/2,
						Location.GameMenu.menu[i][0] * GameConfig.f_zoomx + 1.2f * GameStaticImage.s_interface_menu[i].bitmap.getWidth(), 
						tempy + 1.2f * GameStaticImage.s_interface_menu[i].bitmap.getHeight())) {
					switch(i) {
					case 0://排行榜
						GameManager.forbidModule(new RankingSuccessModule(RankingSuccessModule.SUCCESS));
						break;
					case 1://facebook

//						if(FBInterface.sessionTracker!=null
//						&& FBInterface.sessionTracker.getOpenSession()!=null){
//							FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_FACEBOOK_FRIENDS);
//						}else{
//							if(FacebookOperation.gameState!= FacebookOperation.GAME_STATE_INIT_LOADING)
//								FacebookOperation.gameState= FacebookOperation.GAME_STATE_INIT_LOADING;
//							FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.invitation); //fb的登陆以及邀请
//						}
							break;
					case 2://图鉴
//						loading = 32;
//						GameManager.ResetToRunModule(new CardLibraryModule());
						GameManager.forbidModule(new RankingSuccessModule(RankingSuccessModule.NUMBER));
						break;
					}
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.clicks, 0);
				}
			}
			for (int i=0; i<GameStaticImage.s_interface_smallmenu.length; i++) {
				if (anjian_smallmenu[i] && ExternalMethods.CollisionTest(x, y, 
						Location.GameMenu.smallmenu[i][0] * GameConfig.f_zoomx - 0.2f * GameStaticImage.s_interface_smallmenu[i].bitmap.getWidth()/2, 
						Location.GameMenu.smallmenu[i][1] * GameConfig.f_zoomy - 0.2f * GameStaticImage.s_interface_smallmenu[i].bitmap.getHeight()/2,
						Location.GameMenu.smallmenu[i][0] * GameConfig.f_zoomx + 1.2f * GameStaticImage.s_interface_smallmenu[i].bitmap.getWidth(), 
						Location.GameMenu.smallmenu[i][1] * GameConfig.f_zoomy + 1.2f * GameStaticImage.s_interface_smallmenu[i].bitmap.getHeight())) {
					switch(i) {
					case 0:	//关于
						String temp = LangUtil.getLangString(LangDefineClient.ABOUT_VER);
						GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp) {
						 
						});
						break;
					case 1:	//设置
						GameManager.forbidModule(new GameSetModule());
						break;
					}
					if(!VeggiesData.isMuteSound())
					GameMedia.playSound(R.raw.clicks, 0);
				}
			}
			anjian_play = false;
			for (int i=0; i<anjian_menu.length; i++) {
				anjian_menu[i] = false;
			}
			for (int i=0; i<anjian_smallmenu.length; i++) {
				anjian_smallmenu[i] = false;
			}
		}
	}
}
