package com.endlessvegetables2.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.facebook.FacebookOperation;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMission;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.Module;
import com.socoGameEngine.TextBox;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class GameLevelInfoModule extends Module{
	private Sprite glim;
//	private Sprite backbg1;
//	private Sprite backbg2;
//	private Sprite backbg3;
//	private Sprite close;
//	private Sprite ribbon;
//	private Sprite shine;
//	private Sprite buttontext;
//	private Sprite button1;
//	private Sprite button2;
//	private Sprite buttonbg;
//	private Sprite star1;
//	private Sprite star0;
//	private Sprite[] star_big;
//	private Sprite[] star_big_hid;
//	private Sprite highscore;
//	private Sprite[] arrow;
	
	boolean anjianbutton;
//	boolean anjianarrow_left;
//	boolean anjianarrow_right;
	boolean anjianclose;
	
	Paint paint;
	Typeface fontFace ;
	
	FriendScore friendScore;
	private TextBox[] textBoxs; 
	
	boolean isMove;
	int oldX;
	int move_X;
	int friend_space = 157;
	float friend_leftmost;
	float friend_rightmost;
	
	int ani_frame = 4;
	float ani_y1,ani_y2;
	float ani_up,ani_down;
	static boolean isAni;
	boolean isCancel;
	
	int isGo = 0;
	int isGo_Equip = 1;
	
	private int[] star=
	{
			-1,-1,-1
	};
	private String[] starjob=
	{
		"Score: 100000",
		"Combo: 100000",
		"Life:More than 40%HP:",
	};
	public GameLevelInfoModule() {
	}
	
	private void initFriend() {
		if(FacebookOperation.isLanding && !FacebookOperation.getFacebook().getLoadingFriend()){
			FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_USER_VIEW);
		}
		float _friend_w = (493 - 28) * GameConfig.f_zoomx - GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth();
		float _friend_h = 100 * GameConfig.f_zoomy;
		float _friend_sc = 90 * GameConfig.f_zoomx;
		float _friend_s = (_friend_w - _friend_sc * 4) / 4;
		float _friend_x = 28 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth() + _friend_s / 2;
		float _friend_y = GameConfig.GameScreen_Height - _friend_h - 32 * GameConfig.f_zoomy;
		friendScore = new FriendScore(_friend_x, _friend_y, _friend_w, _friend_h, _friend_sc, _friend_s);
	}
	
	public void ReleaseRecoure () {
//		GameImage.delImage(backbg1.bitmap);
//		backbg1 = null;
//		GameImage.delImage(backbg1.bitmap);
//		backbg1 = null;
//		GameImage.delImage(backbg2.bitmap);
//		backbg2 = null;
//		GameImage.delImage(backbg3.bitmap);
//		backbg3 = null;
//		GameImage.delImage(close.bitmap);
//		close = null;
//		GameImage.delImage(ribbon.bitmap);
//		ribbon = null;
//		GameImage.delImage(shine.bitmap);
//		shine = null;
//		GameImage.delImage(button1.bitmap);
//		button1 = null;
//		GameImage.delImage(button2.bitmap);
//		button2 = null;
//		GameImage.delImage(buttonbg.bitmap);
//		buttonbg = null;
//		GameImage.delImage(buttontext.bitmap);
//		buttontext = null;
//		GameImage.delImage(star0.bitmap);
//		star0 = null;
//		GameImage.delImage(star1.bitmap);
//		star1 = null;
//		for (int i=0; i<star_big.length; i++) {
//			GameImage.delImage(star_big[i].bitmap);
//			star_big[i] = null;
//		}
//		star_big = null;
//		for (int i=0; i<star_big_hid.length; i++) {
//			GameImage.delImage(star_big_hid[i].bitmap);
//			star_big_hid[i] = null;
//		}
//		star_big_hid = null;
//		GameImage.delImage(highscore.bitmap);
//		highscore = null;
//		for (int i=0; i<arrow.length; i++) {
//			GameImage.delImage(arrow[i].bitmap);
//			arrow[i] = null;
//		}
//		arrow = null;
//		friendScore.release();
	}
	
	public void Release() {
		friendScore.release();
		GameManager.setGT(null);
	}
	public boolean initialize() {
		glim = new Sprite();
//		backbg1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
//		backbg2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
//		backbg3 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
//		close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
//		ribbon = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_ribbon_01));
//		shine = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_shine));
//		button1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05_1));
//		button2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05_2));
//		buttonbg = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05));
//		buttontext = new Sprite(GameImage.getImage(GameStaticImage.word_continue));
//		star0 = new Sprite(GameImage.getImage(GameStaticImage.interface_star_08));
//		star1 = new Sprite(GameImage.getImage(GameStaticImage.interface_star_05));
//		star_big = new Sprite[3];
//		star_big[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_09));
//		star_big[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_10));
//		star_big[2] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_11));
//		star_big_hid = new Sprite[GameStaticImage.s_interface_star_09.length];
//		star_big_hid[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_12));
//		star_big_hid[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_13));
//		star_big_hid[2] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_14));
//		for(int i=0; i<star_big.length; i++) {
//			star_big[i] = new Sprite(GameImage.getImage("Interface/Star_" + (9+i)));
//			star_big_hid[i] = new Sprite(GameImage.getImage("Interface/Star_" + (12+i)));
//		}
		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
		paint =new Paint();
		paint.setTextSize(26*GameConfig.f_zoom);
		paint.setTypeface(fontFace);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
//		highscore = new Sprite(GameImage.getImage(GameStaticImage.word_highscore));
//		arrow = new Sprite[2];
//		for(int i=0; i<arrow.length; i++) {
//			arrow[i] = new Sprite(GameImage.getImage("share/ui_arrows_0" + (i+1)));
//		}
		
		initFriend();
		
		//-----------------------------------------
		ani_y1 = 150 * GameConfig.f_zoomy + 374 * GameConfig.f_zoomy + GameStaticImage.s_interface_star_12[0].bitmap.getHeight(); 
		ani_y2 = 260 * GameConfig.f_zoomy;
		ani_up = -ani_y1;
		ani_down = ani_y2;
		isAni = true;
		friendScore.init();
		
		for (int i=0; i<star.length; i++) {
			star[i] = VeggiesData.getTask_Mission()[VeggiesData.getCurrentLevel()][i];
		}
		
		textBoxs = new TextBox[3];
		for (int i=0; i<textBoxs.length; i++) {
			textBoxs[i] = new TextBox();
			if (star[i] == 1) {
				textBoxs[i].setColor(0,0xff824d22);
			} else {
				textBoxs[i].setColor(0,0xff7f7f7f);				
			}
			textBoxs[i].setTextAlign(TextBox.LEFT);
			textBoxs[i].setBoxSize((int)(323 * GameConfig.f_zoomx), (int)(190 * GameConfig.f_zoomy));
			textBoxs[i].setTextSize((int)(24*GameConfig.f_zoom));

			int currentLevel = VeggiesData.getCurrentLevel();
			switch(GameMission.taskArray[currentLevel][i][0]) {
			case GameMission.MISSION_10:
				textBoxs[i].setKeyWord(14, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_12:
				textBoxs[i].setKeyWord(16, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_13:
				textBoxs[i].setKeyWord(18, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_14:
				textBoxs[i].setKeyWord(20, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_15:
				textBoxs[i].setKeyWord(22, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_16:
				textBoxs[i].setKeyWord(24, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_17:
				textBoxs[i].setKeyWord(26, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_18:
				textBoxs[i].setKeyWord(28, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_19:
				textBoxs[i].setKeyWord(30, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_20:
				textBoxs[i].setKeyWord(32, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_21:
				textBoxs[i].setKeyWord(34, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_22:
				textBoxs[i].setKeyWord(36, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			case GameMission.MISSION_23:
				//textBoxs[i].setKeyWord(20, ""+GameMission.taskArray[currentLevel][i][1]);				
				break;
			}
			textBoxs[i].setString(GameWord.missionText[GameWord.useLanguage][GameMission.taskArray[currentLevel][i][0]][0]+GameWord.missionState[GameWord.useLanguage][0] + GameWord.missionText[GameWord.useLanguage][GameMission.taskArray[currentLevel][i][0]][1]);
			textBoxs[i].setBoxSize((int)(323 * GameConfig.f_zoomx), (int)textBoxs[i].height());
		}
		
		if(VeggiesData.getGameGuanka()[1] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL27]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL27, (int)((263) * GameConfig.f_zoomx), (int)(500 * GameConfig.f_zoomy)-(int)(288 * GameConfig.f_zoomy)/2, (int)((422) * GameConfig.f_zoomx), (int)(288 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_27), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height-(GameManager.getGT().getteachBg()*2)+GameManager.getGT().getteachBg()/2+(int)(20*GameConfig.f_zoomy));
//			GameManager.getGT().setMaskPoint((int)((54) * GameConfig.f_zoomx), (int)((219) * GameConfig.f_zoomy));
			GameManager.getGT().setFinalPoint((int)((263) * GameConfig.f_zoomx), (int)(470 * GameConfig.f_zoomy));
		}else if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL34]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL34, (int)((263) * GameConfig.f_zoomx), (int)(474 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_34), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height-(GameManager.getGT().getteachBg()*2)+GameManager.getGT().getteachBg()/2+(int)(20*GameConfig.f_zoomy));
		}else if(VeggiesData.getGameGuanka()[4] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL39]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL39, (int)((263) * GameConfig.f_zoomx), (int)(474 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_39), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height-(GameManager.getGT().getteachBg()*2)+GameManager.getGT().getteachBg()/2+(int)(20*GameConfig.f_zoomy));
		}
		
		return false;
	}

	public void paint(Canvas canvas) {
		glim.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		paintLevelInfo(canvas, ani_up);
		
		//绘制好友排行榜
		paintFriendScore(canvas, ani_down);
	}
	
	private void paintLevelInfo(Canvas canvas, float y) {
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(146 * GameConfig.f_zoomy + y), (int)(476 * GameConfig.f_zoomx), (int)(374 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(206 * GameConfig.f_zoomy + y), (int)(442 * GameConfig.f_zoomx), (int)(222 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(206 * GameConfig.f_zoomy + y), (int)(442 * GameConfig.f_zoomx), (int)(222 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 449 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 141 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f) + y, anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
		
		GameStaticImage.s_interface_ui_ribbon_01.drawBitmap(canvas, GameStaticImage.s_interface_ui_ribbon_01.bitmap, (int)(67 * GameConfig.f_zoomx), (int)(120 * GameConfig.f_zoomy + y), null);
		//中间画星星
		switch (VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()]) {
		case 0:
			GameStaticImage.s_interface_star_12[0].drawBitmap(canvas, GameStaticImage.s_interface_star_12[0].bitmap, (int)(150 * GameConfig.f_zoomx), (int)(112 * GameConfig.f_zoomy + y), null);
			GameStaticImage.s_interface_star_12[1].drawBitmap(canvas, GameStaticImage.s_interface_star_12[1].bitmap, (int)(228 * GameConfig.f_zoomx), (int)(116 * GameConfig.f_zoomy + y), null);
			GameStaticImage.s_interface_star_12[2].drawBitmap(canvas, GameStaticImage.s_interface_star_12[2].bitmap, (int)(307 * GameConfig.f_zoomx), (int)(111 * GameConfig.f_zoomy + y), null);
			break;
		case 1:
			GameStaticImage.s_interface_star_09[0].drawBitmap(canvas, GameStaticImage.s_interface_star_09[0].bitmap, (int)(150 * GameConfig.f_zoomx), (int)(112 * GameConfig.f_zoomy + y), null);
			GameStaticImage.s_interface_star_12[1].drawBitmap(canvas, GameStaticImage.s_interface_star_12[1].bitmap, (int)(228 * GameConfig.f_zoomx), (int)(116 * GameConfig.f_zoomy + y), null);
			GameStaticImage.s_interface_star_12[2].drawBitmap(canvas, GameStaticImage.s_interface_star_12[2].bitmap, (int)(307 * GameConfig.f_zoomx), (int)(111 * GameConfig.f_zoomy + y), null);
			break;
		case 2:
			GameStaticImage.s_interface_star_09[0].drawBitmap(canvas, GameStaticImage.s_interface_star_09[0].bitmap, (int)(150 * GameConfig.f_zoomx), (int)(112 * GameConfig.f_zoomy + y), null);
			GameStaticImage.s_interface_star_09[1].drawBitmap(canvas, GameStaticImage.s_interface_star_09[1].bitmap, (int)(228 * GameConfig.f_zoomx), (int)(116 * GameConfig.f_zoomy + y), null);
			GameStaticImage.s_interface_star_12[2].drawBitmap(canvas, GameStaticImage.s_interface_star_12[2].bitmap, (int)(307 * GameConfig.f_zoomx), (int)(111 * GameConfig.f_zoomy + y), null);
			break;
		case 3:
			GameStaticImage.s_interface_star_09[0].drawBitmap(canvas, GameStaticImage.s_interface_star_09[0].bitmap, (int)(150 * GameConfig.f_zoomx), (int)(112 * GameConfig.f_zoomy + y), null);
			GameStaticImage.s_interface_star_09[1].drawBitmap(canvas, GameStaticImage.s_interface_star_09[1].bitmap, (int)(228 * GameConfig.f_zoomx), (int)(116 * GameConfig.f_zoomy + y), null);
			GameStaticImage.s_interface_star_09[2].drawBitmap(canvas, GameStaticImage.s_interface_star_09[2].bitmap, (int)(307 * GameConfig.f_zoomx), (int)(111 * GameConfig.f_zoomy + y), null);
			break;
		}
		GameStaticImage.s_interface_ui_shine.drawBitmap(canvas, GameStaticImage.s_interface_ui_shine.bitmap, (int)(145 * GameConfig.f_zoomx), (int)(155 * GameConfig.f_zoomy + y), null);
		GameStaticImage.s_share_ui_button_05.drawBitmap(canvas, 55 * GameConfig.f_zoomx , (438 + y)* GameConfig.f_zoomy , 1f, 1f, 255, 0, 0, 0);
		if (anjianbutton) {
			GameStaticImage.s_share_ui_button_05_2.drawBitmap(canvas, GameStaticImage.s_share_ui_button_05_2.bitmap, 55 * GameConfig.f_zoomx , (439 + y) * GameConfig.f_zoomy, null);			
		} else {
			GameStaticImage.s_share_ui_button_05_1.drawBitmap(canvas, GameStaticImage.s_share_ui_button_05_1.bitmap, 55 * GameConfig.f_zoomx , (438 + y) * GameConfig.f_zoomy, null);			
		}
		GameStaticImage.s_word_continue.drawBitmap(canvas, GameStaticImage.s_word_continue.bitmap, 200 * GameConfig.f_zoomx, (463 + y) * GameConfig.f_zoomy, null);
		for (int i=0; i<star.length; i++) {
			if (star[i] == 1) {
				GameStaticImage.s_interface_star_05.drawBitmap(canvas, GameStaticImage.s_interface_star_05.bitmap, (int)(79 * GameConfig.f_zoomx), (int)((227 + 60 * i + y) * GameConfig.f_zoomy), null);				
//				paint.setColor(0xff824d22);
//				paint.setTextSize(24*GameConfig.f_zoom);
//				canvas.drawText(starjob[i], 146 * GameConfig.f_zoomx,(259 + 60 * i + y) * GameConfig.f_zoomy, paint);
			} else {				
				GameStaticImage.s_interface_star_08.drawBitmap(canvas, GameStaticImage.s_interface_star_08.bitmap, (int)(79 * GameConfig.f_zoomx), (int)((227 + 60 * i + y) * GameConfig.f_zoomy), null);				
				//给一下不亮的颜色
//				paint.setColor(0xff7f7f7f);
//				paint.setTextSize(24*GameConfig.f_zoom);
//				canvas.drawText(starjob[i], 146 * GameConfig.f_zoomx,(259 + 60 * i + y) * GameConfig.f_zoomy, paint);
			}
			textBoxs[i].paintText(canvas, (int)(146 * GameConfig.f_zoomx),(int)((227 + 60 * i + y) * GameConfig.f_zoomy + GameStaticImage.s_interface_star_08.bitmap.getHeight()/2 - textBoxs[i].height()/2));
		}
	}

	private void paintFriendScore(Canvas canvas, float y) {
		//绘制底部好友排行榜
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, 0 - GameStaticImage.s_share_ui_back_01.bitmap.getWidth()/3, (int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy + y * GameConfig.f_zoomy), GameConfig.GameScreen_Width + GameStaticImage.s_share_ui_back_01.bitmap.getWidth()*2/3 , (int)(240 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(9 * GameConfig.f_zoomx), (int)(GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoomy + y * GameConfig.f_zoomy), (int)(517 * GameConfig.f_zoomx), (int)(154 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(9 * GameConfig.f_zoomx), (int)(GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoomy + y * GameConfig.f_zoomy), (int)(517 * GameConfig.f_zoomx), (int)(154 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_word_highscore.drawBitmap(canvas, GameStaticImage.s_word_highscore.bitmap, (int)(162 * GameConfig.f_zoomx), (int)(GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 648 )* GameConfig.f_zoomy + y * GameConfig.f_zoomy), null);
	
		
		//绘制好友
		friendScore.paint(canvas, paint, y);
//		canvas.save();
//		canvas.clipRect(friend_leftmost, 
//				GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoom, 
//				friend_rightmost, 
//				GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoom + 154 * GameConfig.f_zoom);
//		for (int i=0; i<friendScore.length; i++) {			
//			friendScore[i].paint(canvas, (75 + friend_space * i) * GameConfig.f_zoom + move_X, GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 711) * GameConfig.f_zoom, paint);
//		}
//		canvas.restore();
		
		//绘制左右方向键
//		arrow[0].drawBitmap(canvas, (int)(28 * GameConfig.f_zoom + arrow[0].bitmap.getWidth()/2 - arrow[0].bitmap.getWidth()/2*(anjianarrow_left?1.2f:1.0f)), (int)(GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 756) * GameConfig.f_zoom + arrow[0].bitmap.getHeight()/2 - arrow[0].bitmap.getHeight()/2*(anjianarrow_left?1.2f:1.0f)), anjianarrow_left?1.2f:1.0f, anjianarrow_left?1.2f:1.0f,255,0,0,0);			
//		arrow[1].drawBitmap(canvas, (int)(493 * GameConfig.f_zoom+ arrow[1].bitmap.getWidth()/2 - arrow[1].bitmap.getWidth()/2*(anjianarrow_right?1.2f:1.0f)), (int)(GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 756) * GameConfig.f_zoom + arrow[1].bitmap.getHeight()/2 - arrow[1].bitmap.getHeight()/2*(anjianarrow_right?1.2f:1.0f)), anjianarrow_right?1.2f:1.0f, anjianarrow_right?1.2f:1.0f,255,0,0,0);			
//		if (75 * GameConfig.f_zoom + move_X < friend_leftmost) {
//			arrow[0].drawBitmap(canvas, (int)(28 * GameConfig.f_zoom + arrow[0].bitmap.getWidth()/2 - arrow[0].bitmap.getWidth()/2*(anjianarrow_left?1.2f:1.0f)), (int)(GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 756) * GameConfig.f_zoom + arrow[0].bitmap.getHeight()/2 - arrow[0].bitmap.getHeight()/2*(anjianarrow_left?1.2f:1.0f)), anjianarrow_left?1.2f:1.0f, anjianarrow_left?1.2f:1.0f,255,0,0,0);			
//		}
//		if ((75 + friend_space * friendScore.length) * GameConfig.f_zoom + move_X > friend_rightmost) {
//			arrow[1].drawBitmap(canvas, (int)(493 * GameConfig.f_zoom+ arrow[1].bitmap.getWidth()/2 - arrow[1].bitmap.getWidth()/2*(anjianarrow_right?1.2f:1.0f)), (int)(GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 756) * GameConfig.f_zoom + arrow[1].bitmap.getHeight()/2 - arrow[1].bitmap.getHeight()/2*(anjianarrow_right?1.2f:1.0f)), anjianarrow_right?1.2f:1.0f, anjianarrow_right?1.2f:1.0f,255,0,0,0);			
//		}
	}
	@Override
	public void onreStart() {
		// TODO Auto-generated method stub
		super.onreStart();
		if(friendScore!=null)
			friendScore.testFriend();
	}
	public void run() {
		//纠正好友排行边界
		if (isAni) {
			ani_up += ani_y1 / ani_frame;
			ani_down -= ani_y2 / ani_frame;
			if (ani_up >= 0 || ani_down <= 0) {
				ani_up = ani_down = 0;
				isAni = false;
			}
		} else if (isCancel) {
			ani_up -= ani_y1 / ani_frame;
			ani_down += ani_y2 / ani_frame;
			if (ani_up <= -ani_y1 || ani_down >= ani_y2) {
				ani_up = -ani_y1;
				ani_down = ani_y2;
				isCancel = false;
				if (isGo == isGo_Equip) {
					//GameManager.ChangeModule(null);
					GameManager.forbidModule(GameMenu.gameEquipmentModule);
				} else if (isGo == 0) {
					GameManager.ChangeModule(null);	
//					if (GameStaticImage.s_map_ship_1 == null) new ImageThread().start();
				}
			}
		}else {			
			friendScore.run();
		}
		
//		GameEquipmentModule.run_smallcardLoadList();
	}

	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			isCancel = true;
			isGo = 0;
		}
		return false;
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
		if (isAni) return;
		if (isCancel) return;
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){  //新手教学
			if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL27
				|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL34
				|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL39){
					if (ExternalMethods.CollisionTest(x, y, 55 * GameConfig.f_zoomx, 438 * GameConfig.f_zoomy,
							55 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_button_05_1.bitmap.getWidth(), 438 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_button_05_1.bitmap.getHeight())) {
						anjianbutton = true;
					}
				}
				
			}else if(event.getActionMasked() == MotionEvent.ACTION_UP){
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL27
				|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL34
				|| GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL39){
					if (anjianbutton && ExternalMethods.CollisionTest(x, y, 55 * GameConfig.f_zoomx, 438 * GameConfig.f_zoomy,
							55 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_button_05_1.bitmap.getWidth(), 438 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_button_05_1.bitmap.getHeight())) {
						isCancel = true;
						isGo = isGo_Equip;
						GameManager.getGT().finish();
						new VeggiesData().saveGame(); 
					} 
				}
				
				anjianbutton = false;
			}
			return;
		}
		
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (ExternalMethods.CollisionTest(x, y, 55 * GameConfig.f_zoomx, 438 * GameConfig.f_zoomy,
					55 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_button_05_1.bitmap.getWidth(), 438 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_button_05_1.bitmap.getHeight())) {
				anjianbutton = true;
			} 
			else if (ExternalMethods.CollisionTest(x, y, 
					449 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					141 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				449 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				141 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			} 
			else if (ExternalMethods.CollisionTest(x, y, 
					9 * GameConfig.f_zoomx, 
					GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoomy, 
					9 * GameConfig.f_zoomx + 517 * GameConfig.f_zoomy, 
					GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoomy + 154 * GameConfig.f_zoomy)) {
				friendScore.oldX = x;
				isMove = true;
				friendScore.onTouchEvent(event);
			}
//			else if (ExternalMethods.CollisionTest(x, y, 
//					28 * GameConfig.f_zoom - arrow[0].bitmap.getWidth()/2,
//					GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 756) * GameConfig.f_zoom - arrow[0].bitmap.getHeight()/2, 
//					28 * GameConfig.f_zoom + arrow[0].bitmap.getWidth(), 
//					GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 756) * GameConfig.f_zoom + arrow[0].bitmap.getHeight())) {
//				//anjianarrow_left = true;
//			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (anjianbutton && ExternalMethods.CollisionTest(x, y, 55 * GameConfig.f_zoomx, 438 * GameConfig.f_zoomy,
					55 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_button_05_1.bitmap.getWidth(), 438 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_button_05_1.bitmap.getHeight())) {
				isCancel = true;
				isGo = isGo_Equip;
			} 
			else if (anjianclose && ExternalMethods.CollisionTest(x, y, 
					449 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					141 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				449 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				141 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				isCancel = true;
				isGo = 0;
			} 
//			else if (anjianarrow_left && ExternalMethods.CollisionTest(x, y, 
//					28 * GameConfig.f_zoom - arrow[0].bitmap.getWidth()/2,
//					GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 756) * GameConfig.f_zoom - arrow[0].bitmap.getHeight()/2, 
//					28 * GameConfig.f_zoom + arrow[0].bitmap.getWidth(), 
//					GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 756) * GameConfig.f_zoom + arrow[0].bitmap.getHeight())) {
//			}
//			anjianarrow_left = false;
			if (isMove) {
				friendScore.onTouchEvent(event);
			}
			
			anjianclose = false;
			isMove = false;
			anjianbutton = false;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (isMove) {
//				if (friendScore.length>3) {
//					int tempX = (int) event.getX();
//					
//					//预判移动
//					move_X+=tempX-oldX;						
////					int ttt = (int)(30*GameConfig.f_zoom);
////					if (75 * GameConfig.f_zoom + move_X < ttt + friend_leftmost) {
////						move_X+=tempX-oldX;						
////					} else if ((75 + friend_space * friendScore.length) * GameConfig.f_zoom + move_X > friend_rightmost + ttt ) {
////						move_X+=tempX-oldX;						
////					}
//					
//					oldX = (int) event.getX();					
//				}
				friendScore.onTouchEvent(event);
			}
		}
	}
	
}
