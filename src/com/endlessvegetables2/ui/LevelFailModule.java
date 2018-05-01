package com.endlessvegetables2.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.audio.Music;
import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.Location.LevelSuccess;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMain;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.Module;

public class LevelFailModule extends Module{
	private Sprite lfm;
//	private Sprite backbg1;
//	private Sprite backbg2;
//	private Sprite backbg3;
//	private Sprite close;
//	private Sprite title;
//	private Sprite[] button;
//	private Sprite button_again_text;
//	private Bitmap[] tomato;
	Bitmap bitmap_title_level_2;
	Bitmap bitmap_num_09[];
	
	private boolean anjianclose;
	private boolean anjianagain;
	private boolean anjianquit;
	public void releaseResource() {
//		GameImage.delImage(backbg1.bitmap);
//		backbg1 = null;
//		GameImage.delImage(backbg2.bitmap);
//		backbg2 = null;
//		GameImage.delImage(backbg3.bitmap);
//		backbg3 = null;
//		GameImage.delImage(close.bitmap);
//		close = null;
//		GameImage.delImage(title.bitmap);
//		title = null;
//		GameImage.delImage(button[0].bitmap);
//		GameImage.delImage(button[1].bitmap);
//		button = null;
//		GameImage.delImage(button_again_text.bitmap);
//		button_again_text = null;
//		GameImage.delImageArray(tomato);
	}
	
	public LevelFailModule() {
		lfm = new Sprite();
//		backbg1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
//		backbg2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
//		backbg3 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
//		close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
//		title = new Sprite(GameImage.getImage(GameStaticImage.word_gameover));
//		button = new Sprite[2];
//		button[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
//		button[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
//		button_again_text = new Sprite(GameImage.getImage(GameStaticImage.word_again));
//		tomato = GameImage.getAutoSizecutBitmap(GameStaticImage.gameover_tomato_over_03, 4, 1, GameImage.Sort_line);
	}
	
	public boolean initialize() {
		LevelData.testData();
		int temp_len = 0;
		int temp_start = 0;
//		int temp1_len = 0;
//		int temp1_start = 0;
		for(int i=0; i<LevelData.getData().size(); i++) {
			if (i == 0) {
			} else if (i == 1) {
			} else if (i >= 2 && i <= 4) {
			} else if (i == 5) {
			} else if (i == 6) {
			} else if (i == 7) {
			} else if (i == 8) {
				VeggiesData.addGold(LevelData.getData().get(i) - VeggiesData.getGold());
			} else if (i == 9) {
				VeggiesData.addGem(LevelData.getData().get(i) - VeggiesData.getGem());
			} else if (i == 26) {
				temp_start = i+1;
				temp_len = LevelData.getData().get(i) * 3;
			} else if (i == temp_start){
				for (int j=0; j<temp_len; j+=3) {
					VeggiesData.setAllCardNum(LevelData.getData().get(temp_start+j), (LevelData.getData().get(temp_start+j+1) - LevelData.getData().get(temp_start+j+2)));
				}
				i += temp_len-1;
			} 
		}
		LevelData.getData().clear();
		//TODO 处理数据
		new VeggiesData().saveGame();
		
//		Music.play(R.raw.levelfaill, false, true);
		if(!VeggiesData.isMuteMusic()){
			GameMedia.playMusic(R.raw.levelfaill, false, true);
		}
		tempi = 0;
		bitmap_title_level_2=GameImage.getImage("gameover/title_level_2");
		bitmap_num_09=GameImage.getAutoSizecutBitmap("gameover/num_09", 10, 1, (byte)0);
		return false;
	}

	public void paint(Canvas canvas) {
		lfm.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(148 * GameConfig.f_zoomy), (int)(476 * GameConfig.f_zoomx), (int)(429 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(211 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(349 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(211 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(349 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
		
		GameStaticImage.s_word_gameover.drawBitmap(canvas, GameStaticImage.s_word_gameover.bitmap, Location.LevelFail.title_xy[0] * GameConfig.f_zoomx, Location.LevelFail.title_xy[1] * GameConfig.f_zoomy, null);
		
		if (anjianagain) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.LevelFail.again_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.LevelFail.again_bg_xy[1] * GameConfig.f_zoomy  + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), (int)(160 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.LevelFail.again_bg_xy[0] * GameConfig.f_zoomx), (int)(Location.LevelFail.again_bg_xy[1] * GameConfig.f_zoomy), (int)(160 * GameConfig.f_zoomx), -1);
		}
		GameStaticImage.s_word_again.drawBitmap(canvas, Location.LevelFail.again_xy[0] * GameConfig.f_zoomx - GameStaticImage.s_word_again.bitmap.getWidth()/2*(anjianagain?0.2f:0.0f), Location.LevelFail.again_xy[1] * GameConfig.f_zoomy - GameStaticImage.s_word_again.bitmap.getHeight()/2*(anjianagain?0.2f:0.0f), anjianagain?1.2f:1.0f, anjianagain?1.2f:1.0f, 255, 0, 0, 0);
		if (anjianquit) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.LevelFail.quit_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.LevelFail.quit_bg_xy[1] * GameConfig.f_zoomy  + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), (int)(160 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.LevelFail.quit_bg_xy[0] * GameConfig.f_zoomx), (int)(Location.LevelFail.quit_bg_xy[1] * GameConfig.f_zoomy), (int)(160 * GameConfig.f_zoomx), -1);
		}
		GameStaticImage.s_word_quit.drawBitmap(canvas, Location.LevelFail.quit_xy[0] * GameConfig.f_zoomx - GameStaticImage.s_word_quit.bitmap.getWidth()/2*(anjianquit?0.2f:0.0f), Location.LevelFail.quit_xy[1] * GameConfig.f_zoomy - GameStaticImage.s_word_quit.bitmap.getHeight()/2*(anjianquit?0.2f:0.0f), anjianquit?1.2f:1.0f, anjianquit?1.2f:1.0f, 255, 0, 0, 0);
	
		int tempw=bitmap_title_level_2.getWidth()+bitmap_num_09[0].getWidth()*(""+(VeggiesData.getCurrentLevel()+1)).length();
		int tempx=(GameConfig.GameScreen_Width-tempw)/2;
		int tempy=(int)(70*GameConfig.f_zoom);
		canvas.drawBitmap(bitmap_title_level_2, tempx, tempy, null);
		tempx+=bitmap_title_level_2.getWidth();
		ExternalMethods.DrawNumber1(canvas, bitmap_num_09, tempx, tempy, 0, LevelSuccessModule.Char_num1, ""+(VeggiesData.getCurrentLevel()+1), null, 0, 1f);
		
		//番茄动画
		
//		if (GameConfig.i_coke % 20 < 20) {
//			tempi = GameConfig.i_coke /2 % 10; 
//			if (tempi == 4 || tempi == 6 || tempi == 8) tempi = 2;
//			else if (tempi == 5 || tempi == 7 || tempi == 9) tempi = 3;
//		}
		if (GameConfig.i_coke % 3 < 1) {
			tempi++;
			if (tempi > 3) tempi = 2;
		}
		lfm.drawBitmap(canvas, GameStaticImage.s_gameover_tomato_over_03[tempi], Location.LevelFail.tomato_xy[0] * GameConfig.f_zoomx, Location.LevelFail.tomato_xy[1] * GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0, 0, 0, 0);
	}
	int tempi = 0;
	
	public void run() {
		
	}

	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			Configs.isGameToMenu = true;
			GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
		}
		return false;
	}

	public void Release() {
		GameImage.delImage(bitmap_title_level_2);
		bitmap_title_level_2=null;
		GameImage.delImageArray(bitmap_num_09);
		bitmap_num_09=null;
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (ExternalMethods.CollisionTest(x, y, 
				453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
				140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				140 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			} else if (ExternalMethods.CollisionTest(x, y,
				(int)(Location.LevelFail.again_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
				(int)(Location.LevelFail.again_bg_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
				(int)(Location.LevelFail.again_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 200 * GameConfig.f_zoomx * 1.2f), 
				(int)(Location.LevelFail.again_bg_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjianagain = true;
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.LevelFail.quit_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelFail.quit_bg_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelFail.quit_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 200 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelFail.quit_bg_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
					anjianquit = true;
			}  
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (ExternalMethods.CollisionTest(x, y, 
				453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
				140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				140 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
//				GameManager.ChangeModule(null);
				//TODO close
//				Configs.isGameToMenu = true;
				GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
			} else if (anjianagain && ExternalMethods.CollisionTest(x, y,
				(int)(Location.LevelFail.again_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
				(int)(Location.LevelFail.again_bg_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
				(int)(Location.LevelFail.again_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 200 * GameConfig.f_zoomx * 1.2f), 
				(int)(Location.LevelFail.again_bg_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				//TODO again
//				GameManager.ResetToRunModule(new GameMain());
				
				GameConfig.b_gameReset = true;
				
				GameManager.ChangeModule(null);
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.LevelFail.quit_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelFail.quit_bg_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelFail.quit_bg_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 200 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelFail.quit_bg_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
			}
			
			anjianagain = false;
			anjianquit = false;
			anjianclose = false;
		}
		
	}

}
