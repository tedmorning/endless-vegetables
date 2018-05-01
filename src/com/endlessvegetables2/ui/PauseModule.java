package com.endlessvegetables2.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.audio.Music;
import com.kokatlaruruxi.wy.R;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMission;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.Module;
import com.socoGameEngine.TextBox;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class PauseModule extends Module{
	private Sprite pm;
//	private Sprite backbg1;
//	private Sprite backbg2;
//	private Sprite backbg3;
//	private Sprite close;
//	private Sprite[] button;
//	private Sprite button_quit_text;
//	private Sprite button_restart_text;
//	private Sprite button_resume_text;
//	private Sprite[] sound;
//	private Sprite[] music;
//	private Sprite star1;
//	private Sprite star0;
	
	private boolean anjianclose;
	private boolean anjianquit;
	private boolean anjianrestart;
	private boolean anjianresume;
	private boolean anjianmusic;
	private boolean anjiansound;
	
	private TextBox[] textBoxs; 
	
	private int[] col = {Color.argb(255, 130, 77, 34), Color.RED};
	
	private int[] star = {-1,-1,-1};
	private String[] miss_text = 
	{
//			"%c0%Score:%c1%5000%c0%/100000",
			"%c0%Score:%c1%%k0%%c0%/%k1%",
			"%c0%Time:%c1%1:00%c0%/2:00",
			"%c0%Combo:%c1%5%c0%/10"
	};
	private int[] mission;
	private int gold;
	private int gem;
	private int[] card;
	
	public void releaseResource() {
//		GameImage.delImage(backbg1.bitmap);
//		backbg1 = null;
//		GameImage.delImage(backbg2.bitmap);
//		backbg2 = null;
//		GameImage.delImage(backbg3.bitmap);
//		backbg3 = null;
//		GameImage.delImage(close.bitmap);
//		close = null;
//		GameImage.delImage(button[0].bitmap);
//		GameImage.delImage(button[1].bitmap);
//		button = null;
//		GameImage.delImage(button_quit_text.bitmap);
//		button_quit_text = null;
//		GameImage.delImage(button_restart_text.bitmap);
//		button_restart_text = null;
//		GameImage.delImage(button_resume_text.bitmap);
//		button_resume_text = null;
//		GameImage.delImage(sound[0].bitmap);
//		GameImage.delImage(sound[1].bitmap);
//		sound = null;
//		GameImage.delImage(music[0].bitmap);
//		GameImage.delImage(music[1].bitmap);
//		music = null;
//		GameImage.delImage(star0.bitmap);
//		star0 = null;
//		GameImage.delImage(star1.bitmap);
//		star1 = null;
	}
	public PauseModule(){
		pm = new Sprite();
//		backbg1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
//		backbg2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
//		backbg3 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
//		close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
//		button = new Sprite[2];
//		button[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
//		button[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
//		button_quit_text = new Sprite(GameImage.getImage(GameStaticImage.word_quit));
//		button_restart_text = new Sprite(GameImage.getImage(GameStaticImage.word_restart));
//		button_resume_text = new Sprite(GameImage.getImage(GameStaticImage.word_resume));
//		sound = new Sprite[2];
//		sound[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_sound));
//		sound[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_sound_2));
//		music = new Sprite[2];
//		music[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_music));
//		music[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_music_2));
//		star0 = new Sprite(GameImage.getImage(GameStaticImage.interface_star_08));
//		star1 = new Sprite(GameImage.getImage(GameStaticImage.interface_star_05));
	}
	public boolean initialize() {	
		
		GameConfig.b_gamePause = true;
		
		if(!VeggiesData.isMuteMusic())
			GameMedia.pauseMusic();
		
		textBoxs = new TextBox[3];
		for (int i=0; i<textBoxs.length; i++) {
			textBoxs[i] = new TextBox();
			textBoxs[i].setColor(col);
			textBoxs[i].setTextAlign(TextBox.LEFT);
			textBoxs[i].setKeyWord(0, ""+5000);
			textBoxs[i].setKeyWord(1, ""+10000);
//			textBoxs[i].setString(miss_text[i]);
			textBoxs[i].setBoxSize((int)(303 * GameConfig.f_zoomx), (int)(190 * GameConfig.f_zoomy));
			textBoxs[i].setTextSize((int)(22*GameConfig.f_zoom));
		}
		
		int temp_star = 0;
		int temp_len = 0;
		int temp_start = 0;
		LevelData.testData();
		for(int i=0; i<LevelData.getData().size(); i++) {
			if (i == 0) {
			} else if (i == 1) {
			} else if (i >= 2 && i <= 4) {
				if (LevelData.getData().get(i) == 1) {
					star[temp_star] = 1;
					//VeggiesData.getTask_Mission()[VeggiesData.getCurrentLevel()][temp_star] = true;
					temp_star++;
				} else if (LevelData.getData().get(i) == 0){					
					star[temp_star] = 0;
					//VeggiesData.getTask_Mission()[VeggiesData.getCurrentLevel()][temp_star] = false;
					temp_star++;
				} else {					
					star[temp_star] = -1;
					temp_star++;
				}
//				VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()] = temp_star;
//				if (i == 4) VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()+1] = 0;
			} else if (i == 5) {
			} else if (i == 6) {
			} else if (i == 7) {
			} else if (i == 8) {
				gold = LevelData.getData().get(i);
//				VeggiesData.addGold(LevelData.getData()[i]);
			} else if (i == 9) {
				gem = LevelData.getData().get(i);
//				VeggiesData.addGem(LevelData.getData()[i]);
			}else if (i >= 14 && i < 26) {
				mission = new int[12];
				for (int j=0; j<12; j+=4) {
					mission[j] = LevelData.getData().get(i+j);
					mission[j+1] = LevelData.getData().get(i+j+1);
					mission[j+2] = LevelData.getData().get(i+j+2);
					mission[j+3] = LevelData.getData().get(i+j+3);
				}
				i += 12-1;
			} 
			else if (i == 26) {
				temp_start = i+1;
				temp_len = LevelData.getData().get(i) * 3;
				card = new int[temp_len];
			} else if (i == temp_start){
				for (int j=0; j<temp_len; j+=3) {
					card[j] = LevelData.getData().get(temp_start+j);
					card[j+1] = LevelData.getData().get(temp_start+j+1);
					card[j+2] = LevelData.getData().get(temp_start+j+2);
//					VeggiesData.setAllCardNum(LevelData.getData()[temp_start+j], (LevelData.getData()[temp_start+j+1] - LevelData.getData()[temp_start+j+2]));
				}
				i += temp_len-1;
			}
		}
		LevelData.getData().clear();
		
		for (int i=0; i<textBoxs.length; i++) {
			switch(mission[i*4]){
			case GameMission.MISSION_10:				
				textBoxs[i].setKeyWord(13, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(14, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_12:
				textBoxs[i].setKeyWord(15, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(16, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_13:
				textBoxs[i].setKeyWord(17, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(18, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_14:
				textBoxs[i].setKeyWord(19, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(20, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_15:
				textBoxs[i].setKeyWord(21, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(22, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_16:
				textBoxs[i].setKeyWord(23, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(24, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_17:
				textBoxs[i].setKeyWord(25, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(26, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_18:
				textBoxs[i].setKeyWord(27, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(28, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_19:
				textBoxs[i].setKeyWord(29, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(30, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_20:
				textBoxs[i].setKeyWord(31, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(32, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_21:
				textBoxs[i].setKeyWord(33, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(34, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_22:
				textBoxs[i].setKeyWord(35, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				textBoxs[i].setKeyWord(36, ""+mission[i*4+3]);
				break;
			case GameMission.MISSION_23:
				//textBoxs[i].setKeyWord(35, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
				//textBoxs[i].setKeyWord(36, ""+mission[i*4+3]);
				break;
				
			}
			if (star[i] == 1) {
				textBoxs[i].setString(GameWord.missionText[GameWord.useLanguage][mission[i*4]][0]);				
			} else if (star[i] == 0){				
				textBoxs[i].setString(GameWord.missionText[GameWord.useLanguage][mission[i*4]][0] + GameWord.missionState[GameWord.useLanguage][0]+GameWord.missionState[GameWord.useLanguage][1]);				
			} else if (star[i] == -1) {				
				textBoxs[i].setString(GameWord.missionText[GameWord.useLanguage][mission[i*4]][0] + GameWord.missionState[GameWord.useLanguage][0]+GameWord.missionText[GameWord.useLanguage][mission[i*4]][2]);				
			}
			if(i==2){
				if (star[i] == 1) {
					String title = textBoxs[i].text;
					title = title+"1/1";
					textBoxs[i].setString(title);
				} else {
					String title = textBoxs[i].text;
					title = title+"%c1%0%c0%/1"; //%c1% col 数组1的颜色
					textBoxs[i].setString(title);
				}
			}
			textBoxs[i].height();
			textBoxs[i].setBoxSize((int)(303 * GameConfig.f_zoomx), (int)textBoxs[i].height());
		}
		
//		star = LevelData.getTask();
		return false;
	}

	public void paint(Canvas canvas) {
		pm.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(155 * GameConfig.f_zoomy), (int)(476 * GameConfig.f_zoomx), (int)(392 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(172 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(360 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(172 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(360 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 150 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
	
		if (anjianquit) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.Pause.button_xy[0][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.Pause.button_xy[0][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), (int)(134 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.Pause.button_xy[0][0] * GameConfig.f_zoomx), (int)(Location.Pause.button_xy[0][1] * GameConfig.f_zoomy), (int)(134 * GameConfig.f_zoomx), -1);
		}
		GameStaticImage.s_word_quit.drawBitmap(canvas, Location.Pause.quit_xy[0] * GameConfig.f_zoomx - GameStaticImage.s_word_quit.bitmap.getWidth()/2*(anjianquit?0.2f:0.0f), Location.Pause.quit_xy[1] * GameConfig.f_zoomy - GameStaticImage.s_word_quit.bitmap.getHeight()/2*(anjianquit?0.2f:0.0f), anjianquit?1.2f:1.0f, anjianquit?1.2f:1.0f, 255, 0, 0, 0);
		if (anjianrestart) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.Pause.button_xy[1][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.Pause.button_xy[1][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), (int)(134 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.Pause.button_xy[1][0] * GameConfig.f_zoomx), (int)(Location.Pause.button_xy[1][1] * GameConfig.f_zoomy), (int)(134 * GameConfig.f_zoomx), -1);
		}
		GameStaticImage.s_word_restart.drawBitmap(canvas, Location.Pause.restart_xy[0] * GameConfig.f_zoomx - GameStaticImage.s_word_restart.bitmap.getWidth()/2*(anjianrestart?0.2f:0.0f), Location.Pause.restart_xy[1] * GameConfig.f_zoomy - GameStaticImage.s_word_restart.bitmap.getHeight()/2*(anjianrestart?0.2f:0.0f), anjianrestart?1.2f:1.0f, anjianrestart?1.2f:1.0f, 255, 0, 0, 0);
		if (anjianresume) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.Pause.button_xy[2][0] * GameConfig.f_zoomx+ (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.Pause.button_xy[2][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), (int)(134 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.Pause.button_xy[2][0] * GameConfig.f_zoomx), (int)(Location.Pause.button_xy[2][1] * GameConfig.f_zoomy), (int)(134 * GameConfig.f_zoomx), -1);
		}
		GameStaticImage.s_word_resume.drawBitmap(canvas, Location.Pause.resume_xy[0] * GameConfig.f_zoomx - GameStaticImage.s_word_resume.bitmap.getWidth()/2*(anjianresume?0.2f:0.0f), Location.Pause.resume_xy[1] * GameConfig.f_zoomy - GameStaticImage.s_word_resume.bitmap.getHeight()/2*(anjianresume?0.2f:0.0f), anjianresume?1.2f:1.0f, anjianresume?1.2f:1.0f, 255, 0, 0, 0);
	
		GameStaticImage.s_interface_icon_music[0].drawBitmap(canvas, GameStaticImage.s_interface_icon_music[VeggiesData.isMuteMusic()?1:0].bitmap, Location.Pause.music_xy[0] * GameConfig.f_zoomx, Location.Pause.music_xy[1] * GameConfig.f_zoomy, null);
		GameStaticImage.s_interface_icon_sound[0].drawBitmap(canvas, GameStaticImage.s_interface_icon_sound[VeggiesData.isMuteSound()?1:0].bitmap, Location.Pause.sound_xy[0] * GameConfig.f_zoomx, Location.Pause.sound_xy[1] * GameConfig.f_zoomy, null);
	
		for (int i=0; i<star.length; i++) {
			if (star[i] == 1) {
				GameStaticImage.s_interface_star_05.drawBitmap(canvas, GameStaticImage.s_interface_star_05.bitmap, (int)(Location.Pause.PauseStar_xy[i][0] * GameConfig.f_zoomx), (int)(Location.Pause.PauseStar_xy[i][1] * GameConfig.f_zoomy), null);
			} else {
				GameStaticImage.s_interface_star_08.drawBitmap(canvas, GameStaticImage.s_interface_star_08.bitmap, (int)(Location.Pause.PauseStar_xy[i][0] * GameConfig.f_zoomx), (int)(Location.Pause.PauseStar_xy[i][1] * GameConfig.f_zoomy), null);
			}
			textBoxs[i].paintText(canvas, (int)((Location.Pause.PauseStar_xy[i][0] + 10) * GameConfig.f_zoomx + GameStaticImage.s_interface_star_08.bitmap.getWidth()), (int)((Location.Pause.PauseStar_xy[i][1] + (GameStaticImage.s_interface_star_08.bitmap.getHeight()/2 - textBoxs[i].height()/2)) * GameConfig.f_zoomy ));
		}
	}

	public void run() {
		
	}

	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			GameManager.ChangeModule(null);
			
			GameConfig.b_gamePause = false;
			
			if(!VeggiesData.isMuteMusic())
				GameMedia.resumeMusic();
		}
		return false;
	}

	public void Release() {
		
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (ExternalMethods.CollisionTest(x, y, 
				453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
				150 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				150 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.Pause.button_xy[0][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.Pause.button_xy[0][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.Pause.button_xy[0][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.Pause.button_xy[0][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjianquit = true;
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.Pause.button_xy[1][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.Pause.button_xy[1][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.Pause.button_xy[1][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.Pause.button_xy[1][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjianrestart = true;
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.Pause.button_xy[2][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.Pause.button_xy[2][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.Pause.button_xy[2][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.Pause.button_xy[2][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjianresume = true;
			} else if (ExternalMethods.CollisionTest(x, y, 
					Location.Pause.music_xy[0] * GameConfig.f_zoomx, 
					Location.Pause.music_xy[1] * GameConfig.f_zoomy, 
					Location.Pause.music_xy[0] * GameConfig.f_zoomx + GameStaticImage.s_interface_icon_music[0].bitmap.getWidth(),
					Location.Pause.music_xy[1] * GameConfig.f_zoomy + GameStaticImage.s_interface_icon_music[0].bitmap.getHeight())) {
				anjianmusic = true;
			} else if (ExternalMethods.CollisionTest(x, y, 
					Location.Pause.sound_xy[0] * GameConfig.f_zoomx, 
					Location.Pause.sound_xy[1] * GameConfig.f_zoomy, 
					Location.Pause.sound_xy[0] * GameConfig.f_zoomx + GameStaticImage.s_interface_icon_sound[0].bitmap.getWidth(),
					Location.Pause.sound_xy[1] * GameConfig.f_zoomy + GameStaticImage.s_interface_icon_sound[0].bitmap.getHeight())) {
				anjiansound = true;
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (anjianclose && ExternalMethods.CollisionTest(x, y, 
				453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
				140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				140 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				GameManager.ChangeModule(null);
				
				GameConfig.b_gamePause = false;
				
				if(!VeggiesData.isMuteMusic())
					GameMedia.resumeMusic();
				
//				if(!VeggiesData.isMuteMusic()){
//					GameMedia.playMusic(R.raw.loginl, true, true);
//				}
			} else if (anjianquit && ExternalMethods.CollisionTest(x, y,
					(int)(Location.Pause.button_xy[0][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.Pause.button_xy[0][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.Pause.button_xy[0][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.Pause.button_xy[0][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				//TODO quit
				countToSave();
				new VeggiesData().saveGame();
				
				Configs.isGameToMenu = true;
				GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
				
				GameConfig.b_gamePause = false;
				
				if(!VeggiesData.isMuteMusic()){
					GameMedia.playMusic(R.raw.loginl, true, true);
				}
			} else if (anjianrestart && ExternalMethods.CollisionTest(x, y,
					(int)(Location.Pause.button_xy[1][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.Pause.button_xy[1][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.Pause.button_xy[1][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.Pause.button_xy[1][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {				
				//TODO restart
//				GameManager.ResetToRunModule(new GameMain());
				// play
				if (VeggiesData.getHeart() == Configs.HEART_MAX) {
					VeggiesData.heart_time = System.currentTimeMillis();
					VeggiesData.addHeart(-1);
				} else if (VeggiesData.getHeart() > 0
						&& VeggiesData.getHeart() < Configs.HEART_MAX) {
					VeggiesData.addHeart(-1);
				} else {
					// TODO 心数不足
					String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_HEART);
					GameManager.setPopUp(PopUp.GOOGS, GameStaticImage.shop_heart_03, new PopUp(temp) {
						@Override
						public byte onTouch(MotionEvent event) {
							// TODO Auto-generated method stub
							byte temp = super.onTouch(event);
							if(temp == PopUp.onTouch_googsExit){
								GameManager.forbidModule(new HeartRechargeModule());
								return -1;
							}
							anjianrestart = false;
							return temp;
						}
					});
					anjianrestart = false;
					return;
				}
				GameConfig.b_gameReset = true;
				countToSave();
				new VeggiesData().saveGame();
				GameManager.ChangeModule(null);
				
				GameConfig.b_gamePause = false;
				 
				
//				if(!VeggiesData.isMuteMusic()){
//					GameMedia.playMusic(R.raw.loginl, true, true);
//				}
			} else if (anjianresume && ExternalMethods.CollisionTest(x, y,
					(int)(Location.Pause.button_xy[2][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.Pause.button_xy[2][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.Pause.button_xy[2][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.Pause.button_xy[2][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				GameManager.ChangeModule(null);
				
				GameConfig.b_gamePause = false;
				
				if(!VeggiesData.isMuteMusic())
					GameMedia.resumeMusic();
				
			} else if (anjianmusic && ExternalMethods.CollisionTest(x, y, 
					Location.Pause.music_xy[0] * GameConfig.f_zoomx, 
					Location.Pause.music_xy[1] * GameConfig.f_zoomy, 
					Location.Pause.music_xy[0] * GameConfig.f_zoomx + GameStaticImage.s_interface_icon_music[0].bitmap.getWidth(),
					Location.Pause.music_xy[1] * GameConfig.f_zoomy + GameStaticImage.s_interface_icon_music[0].bitmap.getHeight())) {
				VeggiesData.setMuteMusic(!VeggiesData.isMuteMusic());
				
//				if(!VeggiesData.isMuteMusic())
//					GameMedia.resumeMusic();
//				else
//					GameMedia.pauseMusic();
				
				new VeggiesData().saveGame();
			} else if (anjiansound && ExternalMethods.CollisionTest(x, y, 
					Location.Pause.sound_xy[0] * GameConfig.f_zoomx, 
					Location.Pause.sound_xy[1] * GameConfig.f_zoomy, 
					Location.Pause.sound_xy[0] * GameConfig.f_zoomx + GameStaticImage.s_interface_icon_sound[0].bitmap.getWidth(),
					Location.Pause.sound_xy[1] * GameConfig.f_zoomy + GameStaticImage.s_interface_icon_sound[0].bitmap.getHeight())) {
				VeggiesData.setMuteSound(!VeggiesData.isMuteSound());
				new VeggiesData().saveGame();
			}
			
			anjiansound = false;
			anjianmusic = false;
			anjianresume = false;
			anjianrestart = false;
			anjianquit = false;
			anjianclose = false;
		}
	}
	
	private void countToSave() {
		VeggiesData.addGold(gold - VeggiesData.getGold());
		VeggiesData.addGem(gem - VeggiesData.getGem());
		for(int i=0; i<card.length; i+=3) {
			VeggiesData.setAllCardNum(card[i], card[i+1] - card[i+2]);			
		}
	}
}
