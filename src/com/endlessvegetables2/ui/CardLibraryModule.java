package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.transform.Templates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class CardLibraryModule extends Module{
	private Sprite backbg1;
	private Sprite backbg2;
	private Sprite backbg3;
	private Sprite close;
	private Sprite[] titlebg;
	private Sprite[] title_vegetable;
	private Sprite[] title_item;
	private Sprite[] arrow;
	private Sprite[] card;
	private Sprite[] noCard;
	private Sprite unLock;
	private Sprite num_bg;
	private Sprite gray;
	private Sprite interface_ui_new_2;
	
	private Sprite bg;
	
	private Paint paint;
	private Typeface fontFace;
	private List<CardItem> _library;
	private List<CardItem> vegetableLibrary;
	private List<CardItem> itemLibrary;
	
	private float _library_x,_library_y,_library_w,_library_h,_library_w_s,_library_h_s;
	private float move_Y;
	private float oldY;
	private float _x,_y;
	private int _index;
	
	private boolean anjianclose;
	private boolean anjiantitle;
	private boolean anjiancard;
	private boolean ismove;
	boolean isCorrectCardMove;
	
	private int cardLibrary_index;//0蔬菜1道具
	float correctCard_move = 5 * GameConfig.f_zoom;
	
	private static int[] CardID;
	
	public void ReleaseResource() {
//		GameImage.delImage(backbg1.bitmap);
//		backbg1 = null;
//		GameImage.delImage(backbg2.bitmap);
//		backbg2 = null;
//		GameImage.delImage(backbg3.bitmap);
//		backbg3 = null;
//		GameImage.delImage(close.bitmap);
//		close = null;
//		for (int i=0; i<titlebg.length; i++) {
//			GameImage.delImage(titlebg[i].bitmap);
//			titlebg[i].bitmap = null;
//		}
//		titlebg = null;
//		for (int i=0; i<title_vegetable.length; i++) {
//			GameImage.delImage(title_vegetable[i].bitmap);
//			title_vegetable[i].bitmap = null;
//		}
//		title_vegetable = null;
//		for (int i=0; i<title_item.length; i++) {
//			GameImage.delImage(title_item[i].bitmap);
//			title_item[i].bitmap = null;
//		}
//		title_item = null;
//		for (int i=0; i<arrow.length; i++) {
//			GameImage.delImage(arrow[i].bitmap);
//			arrow[i].bitmap = null;
//		}
//		arrow = null;
//		for (int i=0; i<card.length; i++) {
//			GameImage.delImage(card[i].bitmap);
//			card[i].bitmap = null;
//		}
//		card = null;
//		GameImage.delImage(noCard.bitmap);
//		noCard = null;
//		GameImage.delImage(unLock.bitmap);
//		unLock = null;
//		GameImage.delImage(num_bg.bitmap);
//		num_bg = null;
//		GameImage.delImage(gray.bitmap);
//		gray = null;
	}
	
	public CardLibraryModule() {
//		backbg1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
//		backbg2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
//		backbg3 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
//		close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
//		titlebg = new Sprite[3];
//		titlebg[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_04));
//		titlebg[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05));
//		titlebg[2] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05_1));
//		
//		title_vegetable = new Sprite[2];
//		title_vegetable[0] = new Sprite(GameImage.getImage(GameStaticImage.word_vegetable));
//		title_vegetable[1] = new Sprite(GameImage.getImage(GameStaticImage.word_vegetable_2));
//		title_item = new Sprite[2];
//		title_item[0] = new Sprite(GameImage.getImage(GameStaticImage.word_item));
//		title_item[1] = new Sprite(GameImage.getImage(GameStaticImage.word_item_2));
//		
//		arrow = new Sprite[2];
//		arrow[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_03));
//		arrow[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_04));
//		
////		card = new Sprite[63];
////		for (int i=0; i<card.length; i++) {
////			if (i  < 9) {
////				card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_0" + (i+1) + "_s"));				
////			} else {				
////				card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_" + (i+1) + "_s"));				
////			}
////		}
//		noCard = new Sprite[10];
//		for (int i=0; i<noCard.length; i++) {
//			if (i >= 9)
//				noCard[i] = new Sprite(GameImage.getImage(GameStaticImage.SMALLCARD + "card_s_" + (i+1)));
//			else
//				noCard[i] = new Sprite(GameImage.getImage(GameStaticImage.SMALLCARD + "card_s_0" + (i+1)));
//		}
//		unLock = new Sprite(GameImage.getImage(GameStaticImage.interface_card_unlock));
//		num_bg = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_07));
//		gray = new Sprite(GameImage.getImage(GameStaticImage.smallcard_card_gray));
//		
//		paint = new Paint();
//		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
//		paint =new Paint();
//		paint.setTextSize(14*GameConfig.f_zoom);
//		paint.setColor(Color.WHITE);
//		paint.setTypeface(fontFace);
//		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
	}
	
	public boolean initialize() {
		int temp[]=new int[3];
		for(int j=0;j<GameItem.cardLibrary.length;j++){
			for(int i=0;i<GameItem.cardLibrary[j].length-3;i+=3){
				if(VeggiesData.getCardNum(GameItem.cardLibrary[j][i])==-1&&VeggiesData.getCardNum(GameItem.cardLibrary[j][i+1])==-1&&VeggiesData.getCardNum(GameItem.cardLibrary[j][i+2])==-1){
					temp[0]=GameItem.cardLibrary[j][i];
					temp[1]=GameItem.cardLibrary[j][i+1];
					temp[2]=GameItem.cardLibrary[j][i+2];
					for(int z=i;z<GameItem.cardLibrary[j].length-3;z+=3){
						GameItem.cardLibrary[j][z]=GameItem.cardLibrary[j][z+3];
						GameItem.cardLibrary[j][z+1]=GameItem.cardLibrary[j][z+4];
						GameItem.cardLibrary[j][z+2]=GameItem.cardLibrary[j][z+5];
					}
					GameItem.cardLibrary[j][GameItem.cardLibrary[j].length-3]=temp[0];
					GameItem.cardLibrary[j][GameItem.cardLibrary[j].length-2]=temp[1];
					GameItem.cardLibrary[j][GameItem.cardLibrary[j].length-1]=temp[2];
				}
			}
		}
		
		bg = new Sprite(GameImage.getImage(GameStaticImage.interface_BG_01));
		card = new Sprite[63];
		for (int i=0; i<63; i++) {
			if (i  < 9) {
				card[i] = new Sprite(GameImage.getImage("smallcard1/card_pc_0" + (i+1) + "_s"));				
			} else {				
				card[i] = new Sprite(GameImage.getImage("smallcard1/card_pc_" + (i+1) + "_s"));				
			}
		}
		
		backbg1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
		backbg2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
		backbg3 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
		close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
		titlebg = new Sprite[3];
		titlebg[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_04));
		titlebg[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05));
		titlebg[2] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05_1));
		
		title_vegetable = new Sprite[2];
		title_vegetable[0] = new Sprite(GameImage.getImage(GameStaticImage.word_vegetable));
		title_vegetable[1] = new Sprite(GameImage.getImage(GameStaticImage.word_vegetable_2));
		title_item = new Sprite[2];
		title_item[0] = new Sprite(GameImage.getImage(GameStaticImage.word_item));
		title_item[1] = new Sprite(GameImage.getImage(GameStaticImage.word_item_2));
		
		arrow = new Sprite[2];
		arrow[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_03));
		arrow[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_04));
		
		noCard = new Sprite[10];
		for (int i=0; i<noCard.length; i++) {
			if (i >= 9)
				noCard[i] = new Sprite(GameImage.getImage(GameStaticImage.SMALLCARD + "card_s_" + (i+1)));
			else
				noCard[i] = new Sprite(GameImage.getImage(GameStaticImage.SMALLCARD + "card_s_0" + (i+1)));
		}
		unLock = new Sprite(GameImage.getImage(GameStaticImage.interface_card_unlock));
		num_bg = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_07));
		gray = new Sprite(GameImage.getImage(GameStaticImage.smallcard_card_gray));
		interface_ui_new_2 = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_new_2));
		
		paint = new Paint();
		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
		paint =new Paint();
		paint.setTextSize(14*GameConfig.f_zoom);
		paint.setColor(Color.WHITE);
		paint.setTypeface(fontFace);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		
		
		_library = new ArrayList<CardItem>();
		testinit();
		
		_library_w_s = (226 - 104) * GameConfig.f_zoomx - noCard[0].bitmap.getWidth();
		_library_h_s = ((714 - 183) * GameConfig.f_zoomy - 4 * noCard[0].bitmap.getHeight() - arrow[0].bitmap.getHeight()) / 4;
		_library_w = _library_w_s * 3 + noCard[0].bitmap.getWidth() * 3;
		_library_h = _library_h_s * 4 + noCard[0].bitmap.getHeight() * 4;
		_library_x = 104 * GameConfig.f_zoomx - _library_w_s / 2;
		_library_y = 208 * GameConfig.f_zoomy - _library_h_s / 2;
		
		vegetableLibrary = new ArrayList<CardItem>();
		for (int i=0; i<GameItem.cardLibrary[0].length; i++) {
			vegetableLibrary.add(new CardItem(i+1, GameItem.cardLibrary[0][i]));
		}
		itemLibrary = new ArrayList<CardItem>();
		for (int i=0; i<GameItem.cardLibrary[1].length; i++) {
			itemLibrary.add(new CardItem(i+1, GameItem.cardLibrary[1][i]));
		}
		
		replaceCard(cardLibrary_index);
		isCorrectCardMove = false;
		
		if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL32] && GameTeaching.teachingArrary[GameTeaching.TEACH_VOL31]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL32, (int)((145) * GameConfig.f_zoomx), (int)(255 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_32), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
			return true;
		}
		if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL47] && GameTeaching.teachingArrary[GameTeaching.TEACH_VOL46]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL47, (int)((145) * GameConfig.f_zoomx), (int)(400 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_47), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
			return true;
		}
		return false;
	}
	private void testinit() {
		for (int i=1; i<19; i++) {
			_library.add(new CardItem(i, i));
		}
	}

	public void paint(Canvas canvas) {
		bg.drawBitmap(canvas, bg.bitmap, GameConfig.GameScreen_Width/2-bg.bitmap.getWidth()/2,GameConfig.GameScreen_Height/2-bg.bitmap.getHeight()/2,null);
		bg.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		backbg1.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(85 * GameConfig.f_zoomy), (int)(476 * GameConfig.f_zoomx), (int)(671 * GameConfig.f_zoomy), -1);
		backbg2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(170 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(569 * GameConfig.f_zoomy), -1);
		backbg3.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(170 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(569 * GameConfig.f_zoomy), -1);
		close.drawBitmap(canvas, 453 * GameConfig.f_zoomx - close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 76 * GameConfig.f_zoomy - close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
		
		for (int i=0; i<Location.CardLibrary.title_bg_xy.length; i++) {
			if (i == cardLibrary_index) {
				titlebg[1].drawBitmap(canvas, null, (int)(Location.CardLibrary.title_bg_xy[i][0] * GameConfig.f_zoomx), (int)((Location.CardLibrary.title_bg_xy[i][1]) * GameConfig.f_zoomy), (int)(168 * GameConfig.f_zoomx), (int)(54 * GameConfig.f_zoomy), -1);
				titlebg[2].drawBitmap(canvas, titlebg[2].bitmap, (Location.CardLibrary.title_bg_xy[i][0] + 74)* GameConfig.f_zoomx, (Location.CardLibrary.title_bg_xy[i][1] + 55)* GameConfig.f_zoomy, null);
			} else {
				titlebg[0].drawBitmap(canvas, null, (int)(Location.CardLibrary.title_bg_xy[i][0] * GameConfig.f_zoomx), (int)((Location.CardLibrary.title_bg_xy[i][1]) * GameConfig.f_zoomy), (int)(168 * GameConfig.f_zoomx), (int)(54 * GameConfig.f_zoomy), -1);
			}
			if (cardLibrary_index == 0) {
				title_vegetable[0].drawBitmap(canvas, title_vegetable[0].bitmap, Location.CardLibrary.title_words_xy[0][0] * GameConfig.f_zoomx, (Location.CardLibrary.title_words_xy[0][1]) * GameConfig.f_zoomy, null);					
				title_item[1].drawBitmap(canvas, title_item[1].bitmap, Location.CardLibrary.title_words_xy[1][0] * GameConfig.f_zoomx, (Location.CardLibrary.title_words_xy[1][1]) * GameConfig.f_zoomy, null);
			} else {
				title_vegetable[1].drawBitmap(canvas, title_vegetable[1].bitmap, Location.CardLibrary.title_words_xy[0][0] * GameConfig.f_zoomx, (Location.CardLibrary.title_words_xy[0][1]) * GameConfig.f_zoomy, null);					
				title_item[0].drawBitmap(canvas, title_item[0].bitmap, Location.CardLibrary.title_words_xy[1][0] * GameConfig.f_zoomx, (Location.CardLibrary.title_words_xy[1][1]) * GameConfig.f_zoomy, null);
			}
		}
		
		if (_library.get(3).index == 1 && move_Y >= 0) {
		} else {
			arrow[0].drawBitmap(canvas, arrow[0].bitmap, 253 * GameConfig.f_zoomx, 183 * GameConfig.f_zoomy,null);	
		}
		switch(cardLibrary_index) {
		case 0:
			if (_library.size() <= 15 || (_library.get(_library.size() - 3 - 1).index == vegetableLibrary.size() && move_Y <= 0)) {
			} else {
				arrow[1].drawBitmap(canvas, arrow[1].bitmap, 253 * GameConfig.f_zoomx, 714 * GameConfig.f_zoomy,null);
			}						
			break;
		case 1:
			if (_library.size() <= 15 || (_library.get(_library.size() - 3 - 1).index == itemLibrary.size() && move_Y <= 0)) {
			} else {
				arrow[1].drawBitmap(canvas, arrow[1].bitmap, 253 * GameConfig.f_zoomx, 714 * GameConfig.f_zoomy,null);
			}						
			break;
		}
		canvas.save();
		canvas.clipRect(_library_x, _library_y + _library_h_s/2, _library_x + _library_w, _library_y + _library_h-_library_h_s/2);
		for (int i=0; i<_library.size(); i++) {
			float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCard[0].bitmap.getWidth());
			float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCard[0].bitmap.getHeight() + _library_h_s);
			
			if (_library.get(i).index != -1) {
				int cardID = _library.get(i).cardId-1;
				if (VeggiesData.getAllCardNum()[cardID] == -1) {
//					noCard[0].drawBitmap(canvas, noCard[_index == i ? 2:0].bitmap, tempx, move_Y + tempy, null);				
					noCard[0].drawBitmap(canvas, noCard[0].bitmap, tempx, move_Y + tempy, null);				
					unLock.drawBitmap(canvas, unLock.bitmap, tempx + 32 * GameConfig.f_zoomx, move_Y + tempy + 46 * GameConfig.f_zoomy, null);
				} else {
//					noCard[0].drawBitmap(canvas, noCard[(_index == _library.get(i).cardId ? 7 : 4) + (_library.get(i).cardId-1)%3].bitmap, tempx, move_Y + tempy, null);
					noCard[0].drawBitmap(canvas, noCard[(4) + (_library.get(i).cardId-1)%3].bitmap, tempx, move_Y + tempy, null);
					card[_library.get(i).cardId-1].drawBitmap(canvas, card[_library.get(i).cardId-1].bitmap, tempx, move_Y + tempy, null);
					if(_library.get(i).cardId-1!=0){
						num_bg.drawBitmap(canvas, num_bg.bitmap, tempx + 43* GameConfig.f_zoomx, move_Y + tempy + 86* GameConfig.f_zoomy, null);
						if (VeggiesData.getAllCardNum()[_library.get(i).cardId-1] > 99) {
							canvas.drawText(Integer.toString(VeggiesData.getAllCardNum()[_library.get(i).cardId-1]), tempx + (55 - 3)* GameConfig.f_zoomx, move_Y + tempy + (106 - 3)* GameConfig.f_zoomy, paint);						
						} else if (VeggiesData.getAllCardNum()[_library.get(i).cardId-1] > 9) {						
							canvas.drawText(Integer.toString(VeggiesData.getAllCardNum()[_library.get(i).cardId-1]), tempx + (59 - 3)* GameConfig.f_zoomx, move_Y + tempy + (106 - 3)* GameConfig.f_zoomy, paint);						
						} else if (VeggiesData.getAllCardNum()[_library.get(i).cardId-1] >= 0) {						
							canvas.drawText(Integer.toString(VeggiesData.getAllCardNum()[_library.get(i).cardId-1]), tempx + (63 - 3)* GameConfig.f_zoomx, move_Y + tempy + (106 - 3)* GameConfig.f_zoomy, paint);						
						}
					}
					Vector<Integer> card = VeggiesData.getCardnewIcon();
					for(int k=0;k<card.size();++k){
						if(cardID == card.get(k).intValue()){
							interface_ui_new_2.drawBitmap(canvas, interface_ui_new_2.bitmap, tempx, move_Y + tempy+interface_ui_new_2.bitmap.getHeight()/2, null);
						}
					}
					//灰色遮盖
					if (VeggiesData.getAllCardNum()[_library.get(i).cardId-1] == 0) {
						gray.drawBitmap(canvas, gray.bitmap, tempx, move_Y + tempy, null);
					}
				}
			}
		}
		canvas.restore();
		
	}

	public void run() {
		if (isCorrectCardMove) {
			move_Y += correctCard_move;
			if (move_Y <= -(noCard[0].bitmap.getHeight() + _library_h_s)) {
				switch(cardLibrary_index) {
				case 0:
					if (_library.get(_library.size()-4).index != vegetableLibrary.size())
						addDownCard(cardLibrary_index);
					break;
				case 1:
					if (_library.get(_library.size()-4).index != itemLibrary.size())
						addDownCard(cardLibrary_index);
					break;
				}
				move_Y = 0;
				correctCard_move = Math.abs(correctCard_move);
				isCorrectCardMove = false;
			} else if (move_Y > -Math.abs(correctCard_move) && move_Y < Math.abs(correctCard_move)) {
				move_Y = 0;
				correctCard_move = Math.abs(correctCard_move);
				isCorrectCardMove = false;
			} else if (move_Y >= noCard[0].bitmap.getWidth() + _library_h_s) {
				if (_library.get(3).index != 1)
					addUpCard(cardLibrary_index);
				move_Y = 0;
				correctCard_move = Math.abs(correctCard_move);
				isCorrectCardMove = false;
			}
		}
		
		
	}

	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			GameManager.ChangeModule(null);
			GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
		}
		return false;
	}

	public void Release() {
		//TODO
		for (int i=0; i<63; i++) {
			if (card[i] != null)
				GameImage.delImage(card[i].bitmap);	
			card[i] = null;
		}
		card = null;
		
		GameImage.delImage(bg.bitmap);
		bg = null;
		GameImage.delImage(backbg1.bitmap);
		backbg1 = null;
		GameImage.delImage(backbg2.bitmap);
		backbg2 = null;
		GameImage.delImage(backbg3.bitmap);
		backbg3 = null;
		GameImage.delImage(close.bitmap);
		close = null;
		for (int i=0; i<titlebg.length; i++) {
			GameImage.delImage(titlebg[i].bitmap);
			titlebg[i].bitmap = null;
		}
		titlebg = null;
		for (int i=0; i<title_vegetable.length; i++) {
			GameImage.delImage(title_vegetable[i].bitmap);
			title_vegetable[i].bitmap = null;
		}
		title_vegetable = null;
		for (int i=0; i<title_item.length; i++) {
			GameImage.delImage(title_item[i].bitmap);
			title_item[i].bitmap = null;
		}
		title_item = null;
		for (int i=0; i<arrow.length; i++) {
			GameImage.delImage(arrow[i].bitmap);
			arrow[i].bitmap = null;
		}
		arrow = null;
		for (int i=0; i<noCard.length; i++) {
			GameImage.delImage(noCard[i].bitmap);
			noCard[i].bitmap = null;
		}
		noCard = null;
//		GameImage.delImage(noCard[0].bitmap);
//		noCard = null;
		GameImage.delImage(unLock.bitmap);
		unLock = null;
		GameImage.delImage(num_bg.bitmap);
		num_bg = null;
		GameImage.delImage(gray.bitmap);
		gray = null;
		GameImage.delImage(interface_ui_new_2.bitmap);
		interface_ui_new_2 = null;
		GameManager.setGT(null);
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){  //新手教学
			_x = x;
			_y = y;
			if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL32){
				if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
					for (int i=3; i<_library.size()-3; i++) {
						float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCard[0].bitmap.getWidth());
						float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCard[0].bitmap.getHeight() + _library_h_s);
						if (ExternalMethods.CollisionTest(x, y, tempx, tempy, tempx + noCard[0].bitmap.getWidth(), tempy + noCard[0].bitmap.getHeight())) {
							anjiancard = true;
						}
					}
				}else if(event.getActionMasked() == MotionEvent.ACTION_UP){
					for (int i=3; i<_library.size()-3; i++) {
						float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCard[0].bitmap.getWidth());
						float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCard[0].bitmap.getHeight() + _library_h_s);
						if (anjiancard && i==3 &&  ExternalMethods.CollisionTest(x, y, tempx, tempy, tempx + noCard[0].bitmap.getWidth(), tempy + noCard[0].bitmap.getHeight())) {
//							ChooseLevelModule.sendMessage("卡片 : " + _library.get(i).cardId);
							_index = _library.get(i).cardId;
							GameManager.getGT().finish();
							new VeggiesData().saveGame();
							GameManager.forbidModule(new BigCardModule(_index,false));
						}
					}
				}
			}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL47){
				if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
					for (int i=3; i<_library.size()-3; i++) {
						float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCard[0].bitmap.getWidth());
						float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCard[0].bitmap.getHeight() + _library_h_s);
						if (ExternalMethods.CollisionTest(x, y, tempx, tempy, tempx + noCard[0].bitmap.getWidth(), tempy + noCard[0].bitmap.getHeight())) {
							anjiancard = true;
						}
					}
				}else if(event.getActionMasked() == MotionEvent.ACTION_UP){
					for (int i=3; i<_library.size()-3; i++) {
						float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCard[0].bitmap.getWidth());
						float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCard[0].bitmap.getHeight() + _library_h_s);
						if (anjiancard && i==6 &&  ExternalMethods.CollisionTest(x, y, tempx, tempy, tempx + noCard[0].bitmap.getWidth(), tempy + noCard[0].bitmap.getHeight())) {
//							ChooseLevelModule.sendMessage("卡片 : " + _library.get(i).cardId);
							_index = _library.get(i).cardId;
							GameManager.getGT().finish();
							new VeggiesData().saveGame();
							GameManager.forbidModule(new BigCardModule(_library.get(i).cardId,true));
							if(VeggiesData.getGameGuanka()[8] >= 0){
								GameManager.forbidModule(new BigCardModule(_library.get(i).cardId,true));
							}else{
								GameManager.forbidModule(new BigCardModule(_library.get(i).cardId,false));
							}
						}
					}
				}
			}
			return;
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_x = x;
			_y = y;
			if (ExternalMethods.CollisionTest(x, y, 
					453 * GameConfig.f_zoomx - close.bitmap.getWidth()/2*0.2f, 
					76 * GameConfig.f_zoomy - close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + close.bitmap.getWidth()*1.2f, 
				76 * GameConfig.f_zoomy + close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			}
			for (int i=0; i<Location.CardLibrary.title_bg_xy.length; i++) {
				if (ExternalMethods.CollisionTest(x, y, 
						Location.CardLibrary.title_bg_xy[i][0] * GameConfig.f_zoomx, Location.CardLibrary.title_bg_xy[i][1] * GameConfig.f_zoomy, 
						(Location.CardLibrary.title_bg_xy[i][0] + 168) * GameConfig.f_zoomx, (Location.CardLibrary.title_bg_xy[i][1] + 54) * GameConfig.f_zoomy)) {
					anjiantitle = true;
				} 				
			}
			for (int i=3; i<_library.size()-3; i++) {
				float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCard[0].bitmap.getWidth());
				float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCard[0].bitmap.getHeight() + _library_h_s);
				if (ExternalMethods.CollisionTest(x, y, tempx, tempy, tempx + noCard[0].bitmap.getWidth(), tempy + noCard[0].bitmap.getHeight())) {
					anjiancard = true;
				}
			}
			if (ExternalMethods.CollisionTest(x, y, 
					_library_x, _library_y,
					_library_x + _library_w, _library_y + _library_h)) {
				oldY = y;
				ismove = true;
			}
			
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (anjianclose && ExternalMethods.CollisionTest(x, y, 
					453 * GameConfig.f_zoomx - close.bitmap.getWidth()/2*0.2f, 
					76 * GameConfig.f_zoomy - close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + close.bitmap.getWidth()*1.2f, 
				76 * GameConfig.f_zoomy + close.bitmap.getHeight()*1.2f)) {
//				GameManager.ChangeModule(null);
				GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
			} 
			for (int i=0; i<Location.CardLibrary.title_bg_xy.length; i++) {
				if (anjiantitle && ExternalMethods.CollisionTest(x, y, 
						Location.CardLibrary.title_bg_xy[i][0] * GameConfig.f_zoomx, Location.CardLibrary.title_bg_xy[i][1] * GameConfig.f_zoomy, 
						(Location.CardLibrary.title_bg_xy[i][0] + 168) * GameConfig.f_zoomx, (Location.CardLibrary.title_bg_xy[i][1] + 54) * GameConfig.f_zoomy)) {
					if (cardLibrary_index != i) {
						move_Y = 0;
						cardLibrary_index = i;
						replaceCard(cardLibrary_index);
					}
					
				} 				
			}
			for (int i=3; i<_library.size()-3; i++) {
				float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCard[0].bitmap.getWidth());
				float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCard[0].bitmap.getHeight() + _library_h_s);
				if (anjiancard && ExternalMethods.CollisionTest(x, y, tempx, tempy, tempx + noCard[0].bitmap.getWidth(), tempy + noCard[0].bitmap.getHeight())) {
					if(VeggiesData.getCardNum(_library.get(i).cardId)>-1){
//						ChooseLevelModule.sendMessage("卡片 : " + _library.get(i).cardId);
						_index = _library.get(i).cardId;
						if(VeggiesData.getGameGuanka()[8] >= 0){
							GameManager.forbidModule(new BigCardModule(_library.get(i).cardId,_index==1?false:true));
						}else{
							GameManager.forbidModule(new BigCardModule(_library.get(i).cardId,false));
						}
					}
				}
			}
			
			if (ismove) {
				if (move_Y < -(noCard[0].bitmap.getHeight() + _library_h_s)/2 || (move_Y > 0 && move_Y < (noCard[0].bitmap.getHeight() + _library_h_s)/2)) {
					//向_friend[0]靠近 -
					correctCard_move = - correctCard_move;
					isCorrectCardMove = true;
				} else if ((move_Y < 0 && move_Y >= -(noCard[0].bitmap.getHeight() + _library_h_s)/2 || move_Y >= (noCard[0].bitmap.getHeight() + _library_h_s)/2)){
					//向_friend[1]靠近 +
					isCorrectCardMove = true;
				}
			}
			anjiancard = false;
			ismove = false;
			anjianclose = false;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (ismove) {
				if (isCorrectCardMove) {
					isCorrectCardMove = false;	//纠正移动关闭等移动释放后继续纠正
					correctCard_move = Math.abs(correctCard_move);
				}
				int tempY = (int) event.getY();
				
				move_Y+=tempY-oldY;
				
				oldY = (int) event.getY();	
				
				if (move_Y > _library_h_s && _library.get(0).index == -1) {
					move_Y = _library_h_s;
				} else if ((move_Y < -_library_h_s && _library.size() < 15 ) || (move_Y < -_library_h_s && _library.get(15).index == -1)) {
					move_Y = -_library_h_s;
				}
				if (move_Y >= _library_h_s + noCard[0].bitmap.getHeight()) {
					move_Y = 0;
					addUpCard(cardLibrary_index);
				} else if (move_Y <= -(_library_h_s + noCard[0].bitmap.getHeight())) {
					move_Y = 0;
					addDownCard(cardLibrary_index);
				}
				
				if (event.getX() - _x > 5 * GameConfig.f_zoom || event.getY() - _y > 5 * GameConfig.f_zoom) {
					anjiancard = false;
				}
			}
		}
	}
	
	private void replaceCard(int _cardLibrary_index) {
		_library.clear();
		_library.add(new CardItem(-1, -1));
		_library.add(new CardItem(-1, -1));
		_library.add(new CardItem(-1, -1));
		switch(_cardLibrary_index) {
		case 0:
			for (int i=0; i<vegetableLibrary.size()&& i<15; i++) {
				_library.add(vegetableLibrary.get(i));			
			}
			break;
		case 1:
			for (int i=0; i<itemLibrary.size()&& i<15; i++) {
				_library.add(itemLibrary.get(i));			
			}
			break;
		}
	}
	
	private void addUpCard(int _cardLibrary_index) {
		for (int j=_library.size(); j>0; j-=3) {
			if (j > 3) {
				_library.set(j-1, _library.get(j-4));
				_library.set(j-2, _library.get(j-5));
				_library.set(j-3, _library.get(j-6));
			} else {
				if (_library.get(j-1).index == 3) {
					_library.set(j-1, new CardItem(-1, -1));
					_library.set(j-2, new CardItem(-1, -1));
					_library.set(j-3, new CardItem(-1, -1));				
				} else {
					switch(_cardLibrary_index) {
					case 0:
						_library.set(j-1, vegetableLibrary.get(_library.get(j-1).index - 4));
						_library.set(j-2, vegetableLibrary.get(_library.get(j-2).index - 4));
						_library.set(j-3, vegetableLibrary.get(_library.get(j-3).index - 4));				
						break;
					case 1:
						_library.set(j-1, itemLibrary.get(_library.get(j-1).index - 4));
						_library.set(j-2, itemLibrary.get(_library.get(j-2).index - 4));
						_library.set(j-3, itemLibrary.get(_library.get(j-3).index - 4));				
						break;
					}
				}
			}
		}
	}
	private void addDownCard(int _cardLibrary_index) {
		for (int j=0; j<_library.size(); j+=3) {
			if (j + 3 < _library.size()) {			
				_library.set(j+0, _library.get(j+3));
				_library.set(j+1, _library.get(j+4));
				_library.set(j+2, _library.get(j+5));
			} else {
				if (j + 3 - _library.size() == 0) {
					switch(_cardLibrary_index) {
					case 0:
						if (_library.get(j+2).index < vegetableLibrary.size()) {
							_library.set(j+0, vegetableLibrary.get(_library.get(j).index + 2));
							_library.set(j+1, vegetableLibrary.get(_library.get(j+1).index + 2));
							_library.set(j+2, vegetableLibrary.get(_library.get(j+2).index + 2));
						} else {
							_library.set(j, new CardItem(-1, -1));
							_library.set(j+1, new CardItem(-1, -1));
							_library.set(j+2, new CardItem(-1, -1));						
						}
						break;
					case 1:
						if (_library.get(j+2).index < itemLibrary.size()) {
							_library.set(j+0, itemLibrary.get(_library.get(j).index + 2));
							_library.set(j+1, itemLibrary.get(_library.get(j+1).index + 2));
							_library.set(j+2, itemLibrary.get(_library.get(j+2).index + 2));
						} else {
							_library.set(j, new CardItem(-1, -1));
							_library.set(j+1, new CardItem(-1, -1));
							_library.set(j+2, new CardItem(-1, -1));						
						}
						break;
					}
				}
//				if (j + 3 - _library.size() == 1) {
//					if (_library.get(j+2).index < vegetableLibrary.size()) {
//						_library.set(j+0, vegetableLibrary.get(_library.get(j).index + 2));
//						_library.set(j+1, vegetableLibrary.get(_library.get(j+1).index + 2));
//						_library.set(j+2, new CardItem(-1, -1));						
//					} else {
//						_library.set(j, new CardItem(-1, -1));
//						_library.set(j+1, new CardItem(-1, -1));
//						_library.set(j+2, new CardItem(-1, -1));						
//					}
//				}
//				if (j + 3 - _library.size() == 2) {
//					if (_library.get(j+2).index < vegetableLibrary.size()) {
//						_library.set(j+0, vegetableLibrary.get(_library.get(j).index + 2));
//						_library.set(j+1, new CardItem(-1, -1));						
//						_library.set(j+2, new CardItem(-1, -1));						
//					} else {
//						_library.set(j, new CardItem(-1, -1));
//						_library.set(j+1, new CardItem(-1, -1));
//						_library.set(j+2, new CardItem(-1, -1));						
//					}
//				}
			}
		}
	}
}
