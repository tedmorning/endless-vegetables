package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.R;
import com.game.item.DiamondItem;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

/**
 * 钻石充值
 * @author Administrator
 *
 */
public class RechargeDiamond extends Module{
	
	//用RMB购买钻石的数量
	public static final int buyDiamon[] = {10, 30, 60, 100, 500, 1500};
	//购买钻石需要的RMB
	public static final double buyDiamonRMB[] = {1.92, 4.99, 9.99, 19.99, 50.99, 100.99};
	//礼包的价格
	public static final double spree[] = {5.88, 8.88, 18.88, 88.88};

	public final byte GAMEDIAMOND = 0; //钻石充值
	public final byte SPREE = 1; //礼包	
	private byte state = -1; 
	
	
	boolean anjianclose;
	public Sprite share_ui_back_03;
	public Sprite shop_icon[];
	public Sprite share_ui_back_06;
	public Sprite[] word_num_03;
	public boolean[] anjianbutton = {false,false,false,false,false,false,false,false};
	public Sprite shop_reward;
	public String bonus[] = {"0%", "10%", "0%", "10%", "20%", "50%"};
	public Paint paint ;
	public String s_bonus ;
	public Sprite gs; 
	private Sprite[] arrow;
	private Sprite[] s_word_number;
	private Sprite[] s_word_honor;
	private Sprite[] s_share_ui_back_04;
	private Sprite[] shopIcon;
	private int bgxy[][] = {{76, 67},{275, 67}};
//	private int titlexy[][] = {{99, 83},{304, 83}};
	
	private float _library_x,_library_y,_library_w,_library_h,_library_w_s,_library_h_s;
	private float move_Y;
	private float oldY;
	private boolean ismove;
	boolean isCorrectCardMove;
	float correctCard_move = 5 * GameConfig.f_zoom;
	private List<DiamondItem> _library;
	private List<DiamondItem> itemLibrary;	
	int spreeNumber[][] = { //礼包的
			{30, 300, 3},
			{50, 500, 3},
			{100, 800, 6},
			{800, 1000, 9}
	};
	 
	String describe[];
	boolean spree_anjianbutton[];
	int iconid[][] = {
			{3,7,0},
			{4,8,0},
			{5,9,1},
			{6,10,2}
	};
	

	Sprite bitmap_Get_01;
	Sprite bitmap_key_add_2;
	int textsize=0;
	int textstate=0;
	int addGet = 0;
	int i_Get;
	
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		if(GameStaticImage.s_share_ui_back_01 == null)
			GameStaticImage.s_share_ui_back_01 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
		if(GameStaticImage.s_share_ui_back_02 == null)
			GameStaticImage.s_share_ui_back_02 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
		if(GameStaticImage.s_share_ui_back_02_2 == null)
			GameStaticImage.s_share_ui_back_02_2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
		if(GameStaticImage.s_share_ui_close == null)
			GameStaticImage.s_share_ui_close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
	
		if(bitmap_Get_01==null)
			bitmap_Get_01= new Sprite(GameImage.getImage("newui/Gem_01"));
		if(bitmap_key_add_2==null)
			bitmap_key_add_2= new Sprite(GameImage.getImage("Interface/key_add_2"));
		
		
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Typeface font	= Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
		paint.setTypeface(font);
		paint.setColor(Color.WHITE);
		paint.setTextSize(18 * GameConfig.f_zoomx);
		
		share_ui_back_03 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_03));
		
		shop_icon = new Sprite[6];
		String icon[] = {GameStaticImage.shop_gem_05, GameStaticImage.shop_gem_06, GameStaticImage.shop_gem_07, GameStaticImage.shop_gem_08, GameStaticImage.shop_gem_09, GameStaticImage.shop_gem_10};
		for(int i=0;i<icon.length;++i){
			shop_icon[i] = new Sprite(GameImage.getImage(icon[i]));
		}
			
		share_ui_back_06 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_06));
		word_num_03  = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_03, 11, 1, GameImage.Sort_line);
		
		if(GameStaticImage.s_share_ui_button_01 == null){
			GameStaticImage.s_share_ui_button_01 = new Sprite[2];
			GameStaticImage.s_share_ui_button_01[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
			GameStaticImage.s_share_ui_button_01[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
		}
//		shop_gem_12 = new Sprite(GameImage.getImage(GameStaticImage.shop_gem_12));
		shop_reward = new Sprite(GameImage.getImage(GameStaticImage.shop_reward));
		if(GameStaticImage.s_word_num_04 == null)
			GameStaticImage.s_word_num_04 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_04, 12, 1, GameImage.Sort_line);
	
		s_bonus = LangUtil.getLangString(LangDefineClient.BONUS);
		gs = new Sprite();
		
		
		s_word_number = new Sprite[2];
		s_word_honor = new Sprite[2];
		s_word_number[0] = new Sprite(GameImage.getImage(GameStaticImage.word_title_gems));
		s_word_number[1] = new Sprite(GameImage.getImage(GameStaticImage.word_title_gems_2));
		s_word_honor[0] = new Sprite(GameImage.getImage(GameStaticImage.word_title_spree));
		s_word_honor[1] = new Sprite(GameImage.getImage(GameStaticImage.word_title_spree_2));
		s_share_ui_back_04 = new Sprite[3];
		s_share_ui_back_04[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_04));
		s_share_ui_back_04[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05));
		s_share_ui_back_04[2] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05_1));
		
		spree_anjianbutton = new boolean[4];
		
		describe =new String[3];
		describe[0] = LangUtil.getLangString(LangDefineClient.GEM);
		describe[1] = LangUtil.getLangString(LangDefineClient.GOLDS);
		describe[2] = LangUtil.getLangString(LangDefineClient.CARDS);
		String shopname[] = {GameStaticImage.shop_card_01, GameStaticImage.shop_card_02 , GameStaticImage.shop_card_03
				, GameStaticImage.shop_gem_01, GameStaticImage.shop_gem_02, GameStaticImage.shop_gem_03, GameStaticImage.shop_gem_04
				, GameStaticImage.shop_gold_01, GameStaticImage.shop_gold_02, GameStaticImage.shop_gold_03, GameStaticImage.shop_gold_04
		};
		
		shopIcon = new Sprite[shopname.length];
		for(int i=0;i<shopname.length;++i){
			shopIcon[i] = new Sprite(GameImage.getImage(shopname[i]));
		}
		
		arrow = new Sprite[2];
		arrow[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_03));
		arrow[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_04));
		
		_library = new ArrayList<DiamondItem>();
		_library_w_s = 202 * GameConfig.f_zoomx - share_ui_back_03.bitmap.getWidth();
		_library_h_s = 268 * GameConfig.f_zoomy -share_ui_back_03.bitmap.getHeight();
		_library_w = _library_w_s * 2 +share_ui_back_03.bitmap.getWidth() * 2;
		_library_x = (int)(79 * GameConfig.f_zoomx);
		_library_y = (int)(139 * GameConfig.f_zoomy);
		_library_h = (int)(650 * GameConfig.f_zoomy);

		i_Get = VeggiesData.getGem();
		
		itemLibrary = new ArrayList<DiamondItem>();
		for (int i=0; i<6; i++) {
			itemLibrary.add(new DiamondItem(this, i+1, i+1));
			boolean _isbonus = false;
			if(i ==1 || i ==3 || i ==4 || i ==5)
				_isbonus = true;
			itemLibrary.get(i).setBonus(_isbonus, bonus[i], s_bonus );
			itemLibrary.get(i).setBuyDiamon(buyDiamon[i], buyDiamonRMB[i]);
		}
		
		replaceItem();
		
		state = GAMEDIAMOND;
		
		return false;
	}
	private void replaceItem() {
		_library.clear();
		_library.add(new DiamondItem(null, -1, -1));
		_library.add(new DiamondItem(null, -1, -1));
		for (int i=0; i<itemLibrary.size()&& i<6; i++) {
			_library.add(itemLibrary.get(i));			
		}
	}
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		gs.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0,GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		int y = 0;
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(29 * GameConfig.f_zoomx), (int)(50 * GameConfig.f_zoomy), (int)(472 * GameConfig.f_zoomx), (int)(757 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(46 * GameConfig.f_zoomx), (int)(135 * GameConfig.f_zoomy), (int)(438 * GameConfig.f_zoomx), (int)(650 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(135 * GameConfig.f_zoomy), (int)(438 * GameConfig.f_zoomx), (int)(650 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 450 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 41 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
	
		for (int i=0; i<bgxy.length; i++) {
			if (i == state) {
				s_share_ui_back_04[1].drawBitmap(canvas, null, (int)(bgxy[i][0] * GameConfig.f_zoomx), (int)((bgxy[i][1]) * GameConfig.f_zoomy), (int)(168 * GameConfig.f_zoomx), (int)(54 * GameConfig.f_zoomy), -1);
				s_share_ui_back_04[2].drawBitmap(canvas, s_share_ui_back_04[2].bitmap, (bgxy[i][0] + 73)* GameConfig.f_zoomx, (bgxy[i][1] + 55)* GameConfig.f_zoomy, null);
			} else {
				s_share_ui_back_04[0].drawBitmap(canvas, null, (int)(bgxy[i][0] * GameConfig.f_zoomx), (int)((bgxy[i][1]) * GameConfig.f_zoomy), (int)(168 * GameConfig.f_zoomx), (int)(54 * GameConfig.f_zoomy), -1);
				
			}
			if (state == GAMEDIAMOND) {
				s_word_number[0].drawBitmap(canvas, s_word_number[0].bitmap, (int)(bgxy[0][0] * GameConfig.f_zoomx)+((int)(168 * GameConfig.f_zoomx) -  s_word_number[0].bitmap.getWidth() >>1), (int)(bgxy[0][1] * GameConfig.f_zoomy)+(s_share_ui_back_04[0].bitmap.getHeight() -  s_word_number[0].bitmap.getHeight() >>1), null);					
				s_word_honor[1].drawBitmap(canvas, s_word_honor[1].bitmap, (int)(bgxy[1][0] * GameConfig.f_zoomx)+((int)(168 * GameConfig.f_zoomx) -  s_word_honor[1].bitmap.getWidth() >>1), (int)(bgxy[1][1] * GameConfig.f_zoomy)+(s_share_ui_back_04[0].bitmap.getHeight() -  s_word_honor[1].bitmap.getHeight() >>1), null);					
			} else {
				s_word_number[1].drawBitmap(canvas, s_word_number[1].bitmap,(int)(bgxy[0][0] * GameConfig.f_zoomx)+((int)(168 * GameConfig.f_zoomx) -  s_word_number[0].bitmap.getWidth() >>1), (int)(bgxy[0][1] * GameConfig.f_zoomy)+(s_share_ui_back_04[0].bitmap.getHeight() -  s_word_number[0].bitmap.getHeight() >>1), null);										
				s_word_honor[0].drawBitmap(canvas, s_word_honor[0].bitmap, (int)(bgxy[1][0] * GameConfig.f_zoomx)+((int)(168 * GameConfig.f_zoomx) -  s_word_honor[0].bitmap.getWidth() >>1), (int)(bgxy[1][1] * GameConfig.f_zoomy)+(s_share_ui_back_04[0].bitmap.getHeight() -  s_word_honor[0].bitmap.getHeight() >>1), null);					
			}
		}
		
		
		switch(state){
		case GAMEDIAMOND: //充值钻石
			paintDiamond(canvas, paint);
			break;
		case SPREE: //礼包
			paintSpree(canvas, paint);
			break;
		}
		
		
		
		
	}
	
	
	/**
	 * 礼包
	 */
	private void paintSpree(Canvas canvas, Paint paint){
		int w = share_ui_back_03.bitmap.getWidth();
		int h = (int)(20*GameConfig.f_zoomy);

		int y = 0;
		 for(int i=0;i<4;++i){
			int x  = (int)(_library_x + _library_w_s / 2 + (i % 2) * (_library_w_s + share_ui_back_03.bitmap.getWidth())-15 * GameConfig.f_zoomx);
			int y1 = (int)(_library_y + _library_h_s / 2 + (i/2) * (share_ui_back_03.bitmap.getHeight() + _library_h_s)+10 * GameConfig.f_zoomy);
			 //五星背景
			share_ui_back_03.drawBitmap(canvas, share_ui_back_03.bitmap, x, y+y1, null);
			
			//价格背景
			int goldbg_x = x + (w-share_ui_back_06.bitmap.getWidth()>>1);
			int goldbg_y = y+y1+(h);
			for(int j=0;j<3;++j){
				int gols_y = (int)(goldbg_y + j*(26*GameConfig.f_zoomy+share_ui_back_06.bitmap.getHeight()));
				share_ui_back_06.drawBitmap(canvas, share_ui_back_06.bitmap, goldbg_x, gols_y, null);
				shopIcon[iconid[i][j]].drawBitmap(canvas, shopIcon[iconid[i][j]].bitmap, goldbg_x, gols_y+share_ui_back_06.bitmap.getHeight()-shopIcon[j].bitmap.getHeight(), null);
			    canvas.drawText(spreeNumber[i][j]+describe[j], goldbg_x+share_ui_back_06.bitmap.getWidth()-paint.measureText(spreeNumber[i][j]+describe[j]+"  "), gols_y+(int)(share_ui_back_06.bitmap.getHeight()/2 + paint.getTextSize()/2), paint);
			}
			
			//button
			int button_x = x+(int)(20 * GameConfig.f_zoomx);
			int button_y = y+y1+(int)(197 * GameConfig.f_zoomy);
			if (spree_anjianbutton[i]) {
				GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2), 
						button_y+(GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2), (int)(134 * GameConfig.f_zoomx * 1.2f), -1);
			} else {				
				GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, button_x, button_y, (int)(134 * GameConfig.f_zoomx), -1);
			}
			//button上面的砖石图标
			int button_icon_x = x+(int)(40 * GameConfig.f_zoomx);
			int button_icon_y = y+y1+(int)(210 * GameConfig.f_zoomy);
//			diamond.shop_gem_12.drawBitmap(canvas, button_icon_x - (anjianbutton?0.2f:0f) *diamond.shop_gem_12.bitmap.getWidth()/2, button_icon_y - (anjianbutton?0.2f:0f) * diamond.shop_gem_12.bitmap.getHeight()/2, anjianbutton?1.2f:1.0f, anjianbutton?1.2f:1.0f, 255, 0, 0, 0);
			GameStaticImage.s_word_num_04[0].drawBitmap(canvas, GameStaticImage.s_word_num_04, button_icon_x, button_icon_y, GameConfig.Char_num0, "$"+Double.toString(spree[i]), null, 0,spree_anjianbutton[i]?1.2f:1.0f);
	 
		 }
		
	}
	
	/**
	 * 绘制钻石充值
	 * */
	private void paintDiamond(Canvas canvas, Paint paint){
		
		Rect rect = new Rect();     
		//当前金币数显示
		int tempX=(int)(10*GameConfig.f_zoom);
		int tempY=(int)(10*GameConfig.f_zoom)+bitmap_Get_01.bitmap.getHeight()/2;
		canvas.drawBitmap(bitmap_Get_01.bitmap, tempX, tempY-bitmap_Get_01.bitmap.getHeight()/2, null);
		tempX+=bitmap_Get_01.bitmap.getWidth()+5;
		
		float size = paint.getTextSize();
		paint.setTextSize(30*GameConfig.f_zoom);
		paint.setColor(0xffffffff);
		canvas.drawText(""+i_Get, tempX, tempY+15*GameConfig.f_zoom, paint);
		
		paint.getTextBounds(""+i_Get, 0, (""+i_Get).length(), rect);
		tempX+=rect.width()+10*GameConfig.f_zoom;
		canvas.drawBitmap(bitmap_key_add_2.bitmap, tempX, tempY-bitmap_key_add_2.bitmap.getHeight()/2, null);
		
		//可增加金币数
		paint.setColor(0xffffe552);
		paint.setTextSize(42*GameConfig.f_zoom+textsize*4f*GameConfig.f_zoom);
		tempX+=bitmap_key_add_2.bitmap.getWidth()+10*GameConfig.f_zoom;;
		canvas.drawText(""+addGet, tempX, tempY+15*GameConfig.f_zoom, paint);
		paint.setTextSize(size);
		
		if (_library.get(2).index == 1 && move_Y >= 0) {
		} else {
			arrow[0].drawBitmap(canvas, arrow[0].bitmap, 253 * GameConfig.f_zoomx, 153 * GameConfig.f_zoomy,null);	
		}
		
		if (_library.size() <= 4 || (_library.get(_library.size() - 2-1).index == itemLibrary.size() && move_Y <= 0)) {
		} else {
			arrow[1].drawBitmap(canvas, arrow[1].bitmap, 253 * GameConfig.f_zoomx, 754 * GameConfig.f_zoomy,null);
		}
		
		canvas.save();
		canvas.clipRect(_library_x, _library_y + _library_h_s/2, _library_x + _library_w, _library_y + _library_h-_library_h_s/2);
		for (int i=0; i<_library.size(); i++) {
			int x  = (int)(_library_x + _library_w_s / 2 + (i % 2) * (_library_w_s + share_ui_back_03.bitmap.getWidth())-15 * GameConfig.f_zoomx);
			int y1 = (int)(_library_y + _library_h_s / 2 + (i/2 - 1) * (share_ui_back_03.bitmap.getHeight() + _library_h_s)+10 * GameConfig.f_zoomy);
			if (_library.get(i).index != -1) {
				_library.get(i).paint(canvas, shop_icon[_library.get(i).id-1],  x, (int)(move_Y + y1), paint);
			}
		}
		canvas.restore();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch(state){
		case GAMEDIAMOND: //充值钻石
			if(textstate>0){
				textstate--;
				textsize++;
				if(textstate<=0){
					textstate=-5;
					textsize=0;
					i_Get+=addGet;
					addGet = 0;
				}
			}else if(textstate<0){
				textstate++;
				textsize--;
				if(textstate>=0){
					textstate=0;
					textsize=0;
					i_Get+=addGet;
					addGet = 0;
				}
			}
			
			if (isCorrectCardMove) {
				move_Y += correctCard_move;
				if (move_Y <= -(share_ui_back_03.bitmap.getHeight() + _library_h_s)) {
						if (_library.get(_library.size()-3).index != itemLibrary.size())
							addDownCard();
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				} else if (move_Y > -Math.abs(correctCard_move) && move_Y < Math.abs(correctCard_move)) {
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				} else if (move_Y >= share_ui_back_03.bitmap.getWidth() + _library_h_s) {
					if (_library.get(2).index != 1)
						addUpCard();
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				}
			}
			break;
		case SPREE: //礼包
			break;
		}
		
	}

	private void addDownCard() {
		for (int j=0; j<_library.size(); j+=2) {
			if (j + 2 < _library.size()) {			
				_library.set(j+0, _library.get(j+2));
				_library.set(j+1, _library.get(j+3));
			} else {
				if (j + 2 - _library.size() == 0) {
					if (_library.get(j+1).index < itemLibrary.size()) {
						_library.set(j+0, itemLibrary.get(_library.get(j).index + 1));
						_library.set(j+1, itemLibrary.get(_library.get(j+1).index + 1));
					} else {
						_library.set(j, new DiamondItem(null, -1, -1));
						_library.set(j+1, new DiamondItem(null, -1, -1));
					}
				}
			}
		}
	}
	private void addUpCard() {
		for (int j=_library.size(); j>0; j-=2) {
			if (j > 2) {
				_library.set(j-1, _library.get(j-3));
				_library.set(j-2, _library.get(j-4));
			} else {
				if (_library.get(j-1).index == 2) {
					_library.set(j-1, new DiamondItem(null, -1, -1));
					_library.set(j-2, new DiamondItem(null, -1, -1));
				} else {
					_library.set(j-1, itemLibrary.get(_library.get(j-1).index - 3));
					_library.set(j-2, itemLibrary.get(_library.get(j-2).index - 3));
					
				}
			}
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Release() {
		// TODO Auto-generated method stub
		if(gs!=null){
			GameImage.delImage(gs.bitmap);
			gs.bitmap = null;
		}
		gs = null;
		
		if(share_ui_back_03!=null)
			GameImage.delImage(share_ui_back_03.bitmap);
		share_ui_back_03 = null;
		if(shop_icon!=null){
			for(int i=0;i<shop_icon.length;++i){
				GameImage.delImage(shop_icon[i].bitmap);
			}
		}
		shop_icon = null;
		
		if(bitmap_Get_01!=null)
			GameImage.delImage(bitmap_Get_01.bitmap);
		bitmap_Get_01 = null;
		
		if(bitmap_key_add_2!=null)
			GameImage.delImage(bitmap_key_add_2.bitmap);
		bitmap_key_add_2 = null;
		
		if(share_ui_back_06!=null)
			GameImage.delImage(share_ui_back_06.bitmap);
		share_ui_back_06 = null;
		
		if(word_num_03!=null)
			GameImage.delImageArray(word_num_03);
		word_num_03 = null;
		
//		if(shop_gem_12!=null)
//			GameImage.delImage(shop_gem_12.bitmap);
//		shop_gem_12 = null;
		
		if(shop_reward!=null)
			GameImage.delImage(shop_reward.bitmap);
		shop_reward = null;
		
		if(arrow!=null){
			for (int i=0; i<arrow.length; i++) {
				GameImage.delImage(arrow[i].bitmap);
				arrow[i].bitmap = null;
			}
		}
		arrow = null;
		paint = null;
	}

	@Override
	public void initwordpic() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			
			if (ExternalMethods.CollisionTest(x, y, 
				453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
				76 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				76 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			}
			
			switch(state){
			case GAMEDIAMOND: //充值钻石
				if (ExternalMethods.CollisionTest(x, y, _library_x, _library_y, _library_x + _library_w, _library_y + _library_h)) {
					oldY = y;
					ismove = true;
				}
				for (int i=0;i<_library.size(); i++) {
					if(_library.get(i).index != -1 ){
						_library.get(i).onTouch(event);
					}
				}
				break;
			case SPREE: //礼包
				 for(int i=0;i<4;++i){
					int x1  = (int)(_library_x + _library_w_s / 2 + (i % 2) * (_library_w_s + share_ui_back_03.bitmap.getWidth())-15 * GameConfig.f_zoomx);
					int y1 = (int)(_library_y + _library_h_s / 2 + (i/2) * (share_ui_back_03.bitmap.getHeight() + _library_h_s)+10 * GameConfig.f_zoomy);
					//button
					int button_x = x1+(int)(20 * GameConfig.f_zoomx);
					int button_y = y1+(int)(197 * GameConfig.f_zoomy);
					if (ExternalMethods.CollisionTest(x, y, 
							(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
							(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
							(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
							(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
						spree_anjianbutton[i] = true;
					}
				 }	
				break;
			}
			
			
			
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			
			if (anjianclose && ExternalMethods.CollisionTest(x, y, 
				453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
				76 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				76 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
//				if (GameStaticImage.s_map_ship_1 == null) new ImageThread().start();
				GameManager.ChangeModule(null);
			}
			for (int i=0; i<bgxy.length; i++) {
				if (ExternalMethods.CollisionTest(x, y, (int)(bgxy[i][0] * GameConfig.f_zoom + (s_share_ui_back_04[0].bitmap.getWidth()/2 - s_share_ui_back_04[1].bitmap.getWidth()/2)), 
						(int)(bgxy[i][1] * GameConfig.f_zoom  + (s_share_ui_back_04[0].bitmap.getHeight()/2 - s_share_ui_back_04[1].bitmap.getHeight()/2)), 
						(int)(bgxy[i][0] * GameConfig.f_zoom + (s_share_ui_back_04[0].bitmap.getWidth()/2 - s_share_ui_back_04[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoom * 1.2f), 
						(int)(bgxy[i][1] * GameConfig.f_zoom  + (s_share_ui_back_04[0].bitmap.getHeight()/2 - s_share_ui_back_04[1].bitmap.getHeight()/2) + s_share_ui_back_04[1].bitmap.getHeight()))) {
						if(i == 0){
							state = GAMEDIAMOND;
						}else if(i == 1){
							state = SPREE;
						}
				}
			}
			
			
			switch(state){
			case GAMEDIAMOND: //充值钻石
				for(int i=0;i<_library.size();++i){
					if(_library.get(i).index != -1){
						int id = _library.get(i).onTouch(event)-1;
						if(id>=0){
							if(true){
								int buyGet = buyDiamon[id];
								if(id == 1){
									buyGet+=buyGet*0.1;
								}else if(id == 3){
									buyGet+=buyGet*0.1;
								}else if(id == 4){
									buyGet+=buyGet*0.2;
								}else if(id == 5){
									buyGet+=buyGet*0.5;
								}
								String temp = LangUtil.getLangString(LangDefineClient.REWARD_GEM);
								temp = temp.replace("X", ""+buyGet);
								addGet = buyGet;
								VeggiesData.addGem(buyGet);
								GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
									@Override
									public byte onTouch(MotionEvent event) {
										// TODO Auto-generated method stub
										byte temp = super.onTouch(event);
										if(temp == PopUp.onTouch_googsExit || temp == PopUp.onTouch_close){
											textstate=-5;
											textsize=5;
											return -1;
										}
										return temp;
									}
								});
							}else{
								String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_RMB);
								GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
								});
							}
						}
					}
				}
				
				if (ismove) {
					if (move_Y < -(share_ui_back_03.bitmap.getHeight() + _library_h_s)/2 || (move_Y > 0 && move_Y < (share_ui_back_03.bitmap.getHeight() + _library_h_s)/2)) {
						//向_friend[0]靠近 -
						correctCard_move = - correctCard_move;
						isCorrectCardMove = true;
					} else if ((move_Y < 0 && move_Y >= -(share_ui_back_03.bitmap.getHeight() + _library_h_s)/2 || move_Y >= (share_ui_back_03.bitmap.getHeight() + _library_h_s)/2)){
						//向_friend[1]靠近 +
						isCorrectCardMove = true;
					}
				}
				
				break;
			case SPREE: //礼包
				 for(int i=0;i<4;++i){
						int x1  = (int)(_library_x + _library_w_s / 2 + (i % 2) * (_library_w_s + share_ui_back_03.bitmap.getWidth())-15 * GameConfig.f_zoomx);
						int y1 = (int)(_library_y + _library_h_s / 2 + (i/2) * (share_ui_back_03.bitmap.getHeight() + _library_h_s)+10 * GameConfig.f_zoomy);
						//button
						int button_x = x1+(int)(20 * GameConfig.f_zoomx);
						int button_y = y1+(int)(197 * GameConfig.f_zoomy);
						if (spree_anjianbutton[i] && ExternalMethods.CollisionTest(x, y, 
								(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
								(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
								(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
								(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
							System.out.println("<><> "+i);
//							if(礼包购买成功){
//							 for(int j= 0;j<spreeNumber[i].length;++j)
//							 {
//								 int temp = spreeNumber[i][j];
//								 switch(j){
//								 case 0:
//									 VeggiesData.addGem(temp);
//									 break;
//								 case 1:
//									 VeggiesData.addGold(temp);
//									 break;
//								 case 2:
//									 break;
//								 }
//							 }
//							String temp = MainActivity.getActivity().getString(R.string.buyok);
//							GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
//							});
//							}else{
								String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_RMB);
								GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
								});
//							}
						}
					 }
				break;
			}
			
			

 
			

			ismove = false;
			anjianclose = false;
			for (int i=0; i<anjianbutton.length; i++) {
				anjianbutton[i] = false;
			}
			for (int i=0; i<spree_anjianbutton.length; i++) {
				spree_anjianbutton[i] = false;
			}
			
		}else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			
			if(state != GAMEDIAMOND)return;
			 
			 
			
			for(int i=0;i<_library.size();++i){
				if(_library.get(i).index != -1){
					int id = _library.get(i).onTouch(event);
				}
			}
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
				} else if ((move_Y < -_library_h_s && _library.size() < 6 ) || (move_Y < -_library_h_s && _library.get(6).index == -1)) {
					move_Y = -_library_h_s;
				}
				if (move_Y >= _library_h_s + share_ui_back_03.bitmap.getHeight()) {
					move_Y = 0;
					addUpCard();
				} else if (move_Y <= -(_library_h_s + share_ui_back_03.bitmap.getHeight())) {
					move_Y = 0;
					addDownCard();
				}
			}
		}
		
	}
}
