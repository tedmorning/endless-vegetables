//package com.endlessvegetables2.ui;
//
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//
//import com.endlessvegetables2.android.ExternalMethods;
//import com.endlessvegetables2.android.Sprite;
//import com.socoGameEngine.GameConfig;
//import com.socoGameEngine.GameImage;
//import com.socoGameEngine.GameManager;
//import com.socoGameEngine.Module;
//
//public class GetCardModule extends Module{
//	private Sprite backbg1;
//	private Sprite backbg2;
//	private Sprite backbg3;
//	private Sprite close;
//	private Sprite title_get;
//	private Sprite[] button;
//	private Sprite button_share_text;
//	private Sprite button_ok_text;
//	private Sprite[] card;
//	
//	private boolean anjianclose;
//	private boolean anjianshare,anjianok;
//	
//	private int[] getCard={GameItem.Item03, GameItem.Item09};
//	
//	public void ReleaseResource() {
//		GameImage.delImage(backbg1.bitmap);
//		backbg1 = null;
//		GameImage.delImage(backbg2.bitmap);
//		backbg2 = null;
//		GameImage.delImage(backbg3.bitmap);
//		backbg3 = null;
//		GameImage.delImage(close.bitmap);
//		close = null;
//		GameImage.delImage(title_get.bitmap);
//		title_get = null;
//		GameImage.delImage(button[0].bitmap);
//		GameImage.delImage(button[1].bitmap);
//		button = null;
//		GameImage.delImage(button_share_text.bitmap);
//		button_share_text = null;
//		GameImage.delImage(button_ok_text.bitmap);
//		button_ok_text = null;
//		for (int i=0; i<card.length; i++) {
//			GameImage.delImage(card[i].bitmap);
//			card[i].bitmap = null;
//		}
//		card = null;
//	}
//	
//	public GetCardModule() {
//		backbg1 = new Sprite(GameImage.getImage("share/ui_back_01"));
//		backbg2 = new Sprite(GameImage.getImage("share/ui_back_02"));
//		backbg3 = new Sprite(GameImage.getImage("share/ui_back_02_2"));
//		close = new Sprite(GameImage.getImage("share/ui_close"));
//		title_get = new Sprite(GameImage.getImage(Configs.filePath + "/word/title_get"));
//		button = new Sprite[2];
//		button[0] = new Sprite(GameImage.getImage("share/ui_button_01"));
//		button[1] = new Sprite(GameImage.getImage("share/ui_button_01_2"));
//		button_share_text = new Sprite(GameImage.getImage(Configs.filePath + "/word/key_share"));
//		button_ok_text = new Sprite(GameImage.getImage(Configs.filePath + "/word/key_ok"));
//		card = new Sprite[63];
//		for (int i=0; i<card.length; i++) {
//			if (i  < 9) {
//				card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_0" + (i+1) + "_s"));				
//			} else {				
//				card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_" + (i+1) + "_s"));				
//			}
//		}
//	}
//	
//	public boolean initialize() {
//		return false;
//	}
//
//	public void paint(Canvas canvas) {
//		ExternalMethods.paintzhao(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
//		ExternalMethods.paintUI(canvas, null, backbg1, (int)(28 * GameConfig.f_zoom), (int)(85 * GameConfig.f_zoom), (int)(476 * GameConfig.f_zoom), (int)(671 * GameConfig.f_zoom), -1);
//		ExternalMethods.paintUI(canvas, null, backbg2, (int)(45 * GameConfig.f_zoom), (int)(148 * GameConfig.f_zoom), (int)(443 * GameConfig.f_zoom), (int)(591 * GameConfig.f_zoom), -1);
//		ExternalMethods.paintUI(canvas, null, backbg3, (int)(45 * GameConfig.f_zoom), (int)(148 * GameConfig.f_zoom), (int)(443 * GameConfig.f_zoom), (int)(591 * GameConfig.f_zoom), -1);
//		close.drawBitmap(canvas, 453 * GameConfig.f_zoom - close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 76 * GameConfig.f_zoom - close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
//		title_get.drawBitmap(canvas, title_get.bitmap, Location.GetCard.title_xy[0] * GameConfig.f_zoom, Location.GetCard.title_xy[1] * GameConfig.f_zoom, null);
//		
//		if (anjianshare) {
//			ExternalMethods.paintUI1(canvas, null, button[1], (int)(Location.GetCard.share_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), (int)(Location.GetCard.share_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), (int)(152 * GameConfig.f_zoom * 1.2f), button[1].bitmap.getHeight(), -1);
//		} else {				
//			ExternalMethods.paintUI1(canvas, null, button[0], (int)(Location.GetCard.share_xy[0] * GameConfig.f_zoom), (int)(Location.GetCard.share_xy[1] * GameConfig.f_zoom), (int)(152 * GameConfig.f_zoom), button[0].bitmap.getHeight(), -1);
//		}
//		button_share_text.drawBitmap(canvas, Location.GetCard.share_word_xy[0] * GameConfig.f_zoom - button_share_text.bitmap.getWidth()/2*(anjianshare?0.2f:0.0f), Location.GetCard.share_word_xy[1] * GameConfig.f_zoom - button_share_text.bitmap.getHeight()/2*(anjianshare?0.2f:0.0f), anjianshare?1.2f:1.0f, anjianshare?1.2f:1.0f, 255, 0, 0, 0);
//		if (anjianok) {
//			ExternalMethods.paintUI1(canvas, null, button[1], (int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), (int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), (int)(152 * GameConfig.f_zoom * 1.2f), button[1].bitmap.getHeight(), -1);
//		} else {				
//			ExternalMethods.paintUI1(canvas, null, button[0], (int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoom), (int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoom), (int)(152 * GameConfig.f_zoom), button[0].bitmap.getHeight(), -1);
//		}
//		button_ok_text.drawBitmap(canvas, Location.GetCard.ok_word_xy[0] * GameConfig.f_zoom - button_ok_text.bitmap.getWidth()/2*(anjianok?0.2f:0.0f), Location.GetCard.ok_word_xy[1] * GameConfig.f_zoom - button_ok_text.bitmap.getHeight()/2*(anjianok?0.2f:0.0f), anjianok?1.2f:1.0f, anjianok?1.2f:1.0f, 255, 0, 0, 0);
//	
//		for (int i=0; i<getCard.length; i++) {
//			card[getCard[i]-1].drawBitmap(canvas, card[getCard[i]-1].bitmap, Location.GetCard.card_xy[i][0] * GameConfig.f_zoom, Location.GetCard.card_xy[i][1] * GameConfig.f_zoom, null);
//		}
//	}
//
//	public void run() {
//		
//	}
//
//	public boolean onKeyDown(int keyCode, KeyEvent msg) {
//		return false;
//	}
//
//	public boolean onKeyUp(int keyCode, KeyEvent msg) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			GameManager.ChangeModule(null);
////			GameShop.isHideShop = false;
//		}
//		return false;
//	}
//
//	public void Release() {
//		
//	}
//
//	public void initwordpic() {
//		
//	}
//
//	public void onTouchEvent(MotionEvent event) {
//		float x = event.getX();
//		float y = event.getY();
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			if (ExternalMethods.CollisionTest(x, y, 
//					453 * GameConfig.f_zoom - close.bitmap.getWidth()/2*0.2f, 
//					76 * GameConfig.f_zoom - close.bitmap.getHeight()/2*0.2f,
//				453 * GameConfig.f_zoom + close.bitmap.getWidth()*1.2f, 
//				4476 * GameConfig.f_zoom + close.bitmap.getHeight()*1.2f)) {
//				anjianclose = true;
//			} else if (ExternalMethods.CollisionTest(x, y, 
//					(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
//					(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
//					(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoom * 1.2f), 
//					(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + button[1].bitmap.getHeight()))) {
//				anjianshare = true;
//			} else if (ExternalMethods.CollisionTest(x, y, 
//					(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
//					(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
//					(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoom * 1.2f), 
//					(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + button[1].bitmap.getHeight()))) {
//				anjianok = true;
//			}
//		} else if (event.getAction() == MotionEvent.ACTION_UP) {
//			if (ExternalMethods.CollisionTest(x, y, 
//					453 * GameConfig.f_zoom - close.bitmap.getWidth()/2*0.2f, 
//					44 * GameConfig.f_zoom - close.bitmap.getHeight()/2*0.2f,
//				453 * GameConfig.f_zoom + close.bitmap.getWidth()*1.2f, 
//				44 * GameConfig.f_zoom + close.bitmap.getHeight()*1.2f)) {
//				GameManager.ChangeModule(null);
////				GameShop.isHideShop = false;
//			} else if (anjianshare && ExternalMethods.CollisionTest(x, y, 
//					(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
//					(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
//					(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoom * 1.2f), 
//					(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + button[1].bitmap.getHeight()))) {
//				//TODO share
//			} else if (anjianok && ExternalMethods.CollisionTest(x, y, 
//					(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
//					(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
//					(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoom + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoom * 1.2f), 
//					(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoom  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + button[1].bitmap.getHeight()))) {
//				//TODO ok
////				GameManager.ChangeModule(null);
////				GameManager.ChangeModule(null);
////				GameShop.isHideShop = false;
//				
////				GameManager.ChangeModule();
//			}
//			
//			anjianshare = false;
//			anjianok = false;
//		}
//	}
//	
//}
