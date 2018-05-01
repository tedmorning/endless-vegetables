//package com.endlessvegetables2.ui;
//
//import java.util.Random;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//
//import com.endlessvegetables2.android.ExternalMethods;
//import com.endlessvegetables2.android.GameTeaching;
//import com.endlessvegetables2.android.R;
//import com.endlessvegetables2.android.Sprite;
//import com.socoGameEngine.GameConfig;
//import com.socoGameEngine.GameImage;
//import com.socoGameEngine.GameManager;
//import com.socoGameEngine.MainActivity;
//import com.socoGameEngine.Module;
//import com.socoGameEngine.TextBox;
//import com.util.lang.LangDefineClient;
//import com.util.lang.LangUtil;
//
//public class GameShop extends Module{
////	public static boolean isHideShop = false;
//	private Sprite gs;
////	private Sprite backbg1;
////	private Sprite backbg2;
////	private Sprite backbg3;
////	private Sprite close;
////	private Sprite[] titlebg;
////	private Sprite[] title_card;
////	private Sprite[] title_upgrade;
////	private Sprite shopbg01;
////	private Sprite shopbg02;
////	private Sprite[] shopbutton;
////	private Sprite[] shopitem;
//	private Sprite[] shopitem_priceicon;
////	private Bitmap[] price_num;
//	TextBox[] shopitemword;
//	private Sprite free;
//	
////	private Sprite[] shop_lv; //显示质量的
//	private ShopUpgrade[] s_upgrade; //需要跟新的对象
//	private int wShop_lv;
//	private int hShop_lv;
//	
//	private boolean anjianclose;
//	private boolean[] anjianbutton = {false,false,false,false};
//	
//	private int shop_index;
//	private int[] word_move_y = {0,0,0,0};
//	private boolean[] isScrollUp = {false,false,false,false};
//	
//	public static final boolean[] shopbox = //商店抽卡金币宝石图标
//	{//true为金币,flase为宝石
//		true,true,
//		false,false,
//	};
//	//抽卡的价格
//	public static int[] shopitem_price = {100,300,6,9};
//	//升级的价格
//	private int shopitem_upLevel[][] = {
//			{100, 300, 600},
//			{120, 360, 720},
//			{10, 30, 60},
//			{15, 24, 50}};
//	
//	private int prop_upgrade = -1; //道具跟新
//	//商店跟新的类型
//	private final byte SLINGSHOT = 1; //弹弓
//	private final byte BERG = 2;   //冰山
//	private final byte TIME = 3;   //时间
//	private final byte TOMATO = 4;  //番茄
//
//	private MySprite mySprite;
//
//	public void ReleaseResource() {
////		GameImage.delImage(backbg1.bitmap);
////		backbg1 = null;
////		GameImage.delImage(backbg2.bitmap);
////		backbg2 = null;
////		GameImage.delImage(backbg3.bitmap);
////		backbg3 = null;
////		GameImage.delImage(close.bitmap);
////		close = null;
////		for (int i=0; i<titlebg.length; i++) {
////			GameImage.delImage(titlebg[i].bitmap);
////			titlebg[i].bitmap = null;
////		}
////		titlebg = null;
////		for (int i=0; i<title_card.length; i++) {
////			GameImage.delImage(title_card[i].bitmap);
////			title_card[i].bitmap = null;
////		}
////		title_card = null;
////		for (int i=0; i<title_upgrade.length; i++) {
////			GameImage.delImage(title_upgrade[i].bitmap);
////			title_upgrade[i].bitmap = null;
////		}
////		title_upgrade = null;
////		GameImage.delImage(shopbg01.bitmap);
////		shopbg01 = null;
////		GameImage.delImage(shopbg02.bitmap);
////		shopbg02 = null;
////		GameImage.delImage(shopbutton[0].bitmap);
////		GameImage.delImage(shopbutton[1].bitmap);
////		shopbutton = null;
////		for (int i=0; i<shopitem.length; i++) {
////			GameImage.delImage(shopitem[i].bitmap);
////			shopitem[i].bitmap = null;
////		}
////		shopitem = null;
//		for (int i=0; i<shopitem_priceicon.length; i++) {
//			GameImage.delImage(shopitem_priceicon[i].bitmap);
//			shopitem_priceicon[i].bitmap = null;
//		}
//		shopitem_priceicon = null;
//		
//		
//		for (int i=0; i<s_upgrade.length; i++) {
//			s_upgrade[i].ReleaseResource();
//		}
//		s_upgrade = null;
//
//		mySprite.release();
////		for (int i=0; i<shop_lv.length; i++) {
////			GameImage.delImage(shop_lv[i].bitmap);
////			shop_lv[i].bitmap = null;
////		}
////		shop_lv = null;
//		
////		GameImage.delImageArray(price_num);
//	}
//	
//	public void Release() {
//		if (free != null) {
//			GameImage.delImage(free.bitmap);
//			free = null;
//		}
//		GameManager.setGT(null);
//	}
//	
//	public boolean initialize() {
//		gs = new Sprite();
////		backbg1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
////		backbg2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
////		backbg3 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
////		close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
////		titlebg = new Sprite[3];
////		titlebg[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_04));
////		titlebg[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05));
////		titlebg[2] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05_1));
////		title_card = new Sprite[2];
////		title_card[0] = new Sprite(GameImage.getImage(GameStaticImage.word_cards));
////		title_card[1] = new Sprite(GameImage.getImage(GameStaticImage.word_cards_2));
////		title_upgrade = new Sprite[2];
////		title_upgrade[0] = new Sprite(GameImage.getImage(GameStaticImage.word_upgrade));
////		title_upgrade[1] = new Sprite(GameImage.getImage(GameStaticImage.word_upgrade_2));
//		
////		shopbg01 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_03));
////		shopbg02 = new Sprite(GameImage.getImage(GameStaticImage.success_S_back_1));
//		
////		shopbutton = new Sprite[2];
////		shopbutton[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
////		shopbutton[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
//		
////		shopitem = new Sprite[4];
////		shopitem[0] = new Sprite(GameImage.getImage(GameStaticImage.shop_box_01));
////		shopitem[1] = new Sprite(GameImage.getImage(GameStaticImage.shop_box_02));
////		shopitem[2] = new Sprite(GameImage.getImage(GameStaticImage.shop_box_03));
////		shopitem[3] = new Sprite(GameImage.getImage(GameStaticImage.shop_box_04));
////		for (int i=0; i<shopitem.length; i++) {
////			shopitem[i] = new Sprite(GameImage.getImage("shop/shop_box_0" + (i+1)));
////		}
//		shopitem_priceicon = new Sprite[4];
//		shopitem_priceicon[0] = new Sprite(GameImage.getImage(GameStaticImage.shop_gem_12));
//		shopitem_priceicon[1] = new Sprite(GameImage.getImage(GameStaticImage.shop_gold_06));
//	 
//		mySprite = new MySprite(GameStaticImage.SHOP + "shop.json", GameStaticImage.SHOP + "shop");
//		
//		
////		shop_lv = new Sprite[2];
////		for(int i=0;i<shop_lv.length;++i){
////			shop_lv[i] = new Sprite(GameImage.getImage(GameStaticImage.shop_lv[i]));
////		}
//		wShop_lv = (int)(mySprite.getImageWidth(GameStaticImage.shop_lv[0]+".png"));
//		hShop_lv = (int)(mySprite.getImageHeight(GameStaticImage.shop_lv[0]+".png"));
//		
//		int size = 4; //这是需要升级的
//		
//		
//		
//		int propType[] = {SLINGSHOT, BERG, TIME, TOMATO};
//		int propQuality[] = {VeggiesData.getSlingshot_crit_level(),
//				VeggiesData.getFence_HP_level(), 
//				VeggiesData.getCombus_damage_level(), 
//				VeggiesData.getCombus_time_level()};
//		s_upgrade = new ShopUpgrade[size];
//		for(int i=0;i<s_upgrade.length;++i){
//			String file = imagePath( propType[i],propQuality[i] );
//			s_upgrade[i] = new ShopUpgrade(mySprite, file, propType[i], propQuality[i]);
//			int width = (int)(mySprite.getImageWidth(file+".png"));
//			int height = (int)(mySprite.getImageHeight(file+".png"));
//			int width1 = GameStaticImage.s_share_ui_back_03.bitmap.getWidth();
//			int height1 =(int)(Location.GameShop.shopbg02_xy[i][1] * GameConfig.f_zoomy - Location.GameShop.shopbg01_xy[i][1] * GameConfig.f_zoomy);
//			s_upgrade[i].setPoint((int)(Location.GameShop.shopbg01_xy[i][0] * GameConfig.f_zoomx)+(width1 - width >>1), (int)(Location.GameShop.shopbg01_xy[i][1]* GameConfig.f_zoomy) +(height1 - height >>1 ));
//		
//		}
//		prop_upgrade = -1;
//		
////		price_num = GameImage.getAutoSizecutBitmap(GameStaticImage.word_num_04, 12, 1, GameImage.Sort_line);
//		
//		shopitemword = new TextBox[4];
//		for (int i=0; i<shopitemword.length; i++) {
//			shopitemword[i] = new TextBox();
//			shopitemword[i].setTextAlign(TextBox.LEFT);
//			shopitemword[i].setString(GameWord.shopItem_word[GameWord.useLanguage][i]);
//			shopitemword[i].setBoxSize((int)(153 * GameConfig.f_zoomx), (int)(1000 * GameConfig.f_zoomy));
//			shopitemword[i].setTextSize((int)(20*GameConfig.f_zoom));
//			shopitemword[i].setDefaultColor(Color.argb(255, 255, 255, 255));
//			shopitemword[i].height();
//			shopitemword[i].setBoxSize((int)(153 * GameConfig.f_zoomx), (int)shopitemword[i].height());
//			word_move_y[i] = 0;
//			if (shopitemword[i].height() > 45 * GameConfig.f_zoomy) {
//				isScrollUp[i] = true;
//			}
//		}
//		if (VeggiesData.getGameGuanka()[3] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL5]) {
//			if(GameManager.getGT()==null)
//				GameManager.setGT(new GameTeaching());
//			 
//			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL5, (int)((Location.GameShop.shopbutton_xy[1][0] + 67) * GameConfig.f_zoomx), (int)(Location.GameShop.shopbutton_xy[1][1] * GameConfig.f_zoomy + GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2), LangUtil.getLangString(LangDefineClient.GUIDE_5), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 根据类型和品质寻找图片
//	 * 
//	 * */
//	private String imagePath(final int type, int _quality){
//		String path = "";
//		switch(type){
//		case SLINGSHOT:
//			path = GameStaticImage.shop_item_1[_quality];
//			break;
//		case BERG:
//			path = GameStaticImage.shop_item_2[_quality];
//			break;
//		case TIME:
//			path = GameStaticImage.shop_item_3[_quality];
//			break;
//		case TOMATO:
//			path = GameStaticImage.shop_item_4[_quality];
//			break;
//		}
//		return path;
//	}
//	/**
//	 * 根据类型查询最大的品质
//	 * 
//	 * */
//	private byte qualityMax(int type){
//		byte quality = 0;
//		switch(type){
//		case SLINGSHOT:
//			quality  = (byte) (GameStaticImage.shop_item_1.length-1);
//			break;
//		case BERG:
//			quality  = (byte) (GameStaticImage.shop_item_2.length-1);
//			break;
//		case TIME:
//			quality  = (byte) (GameStaticImage.shop_item_3.length-1);
//			break;
//		case TOMATO:
//			quality  = (byte) (GameStaticImage.shop_item_4.length-1);
//			break;
//		}
//		return quality;
//	}
//	public void paint(Canvas canvas) {
//		gs.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
//		
////		if (isHideShop) return;
//		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(84 * GameConfig.f_zoomy), (int)(476 * GameConfig.f_zoomx), (int)(673 * GameConfig.f_zoomy), -1);
//		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(170 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(569 * GameConfig.f_zoomy), -1);
//		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(170 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(569 * GameConfig.f_zoomy), -1);
//		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 76 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
//	
//		for (int i=0; i<Location.GameShop.title_bg_xy.length; i++) {
//			if (i == shop_index) {
//				GameStaticImage.s_share_ui_back_04[1].drawBitmap(canvas, null, (int)(Location.GameShop.title_bg_xy[i][0] * GameConfig.f_zoomx), (int)((Location.GameShop.title_bg_xy[i][1]) * GameConfig.f_zoomy), (int)(168 * GameConfig.f_zoomx), (int)(54 * GameConfig.f_zoomy), -1);
//				GameStaticImage.s_share_ui_back_04[2].drawBitmap(canvas, GameStaticImage.s_share_ui_back_04[2].bitmap, (Location.GameShop.title_bg_xy[i][0] + 73)* GameConfig.f_zoomx, (Location.GameShop.title_bg_xy[i][1] + 55)* GameConfig.f_zoomy, null);
//			} else {
//				GameStaticImage.s_share_ui_back_04[0].drawBitmap(canvas, null, (int)(Location.GameShop.title_bg_xy[i][0] * GameConfig.f_zoomx), (int)((Location.GameShop.title_bg_xy[i][1]) * GameConfig.f_zoomy), (int)(168 * GameConfig.f_zoomx), (int)(54 * GameConfig.f_zoomy), -1);
//				
//			}
//			if (shop_index == 0) {
//				GameStaticImage.s_word_cards[0].drawBitmap(canvas, GameStaticImage.s_word_cards[0].bitmap, Location.GameShop.title_words_xy[0][0] * GameConfig.f_zoomx, (Location.GameShop.title_words_xy[0][1]) * GameConfig.f_zoomy, null);					
//				GameStaticImage.s_word_upgrade[1].drawBitmap(canvas, GameStaticImage.s_word_upgrade[1].bitmap, Location.GameShop.title_words_xy[1][0] * GameConfig.f_zoomx, (Location.GameShop.title_words_xy[1][1]) * GameConfig.f_zoomy, null);
//			} else {
//				GameStaticImage.s_word_cards[1].drawBitmap(canvas, GameStaticImage.s_word_cards[1].bitmap, Location.GameShop.title_words_xy[0][0] * GameConfig.f_zoomx, (Location.GameShop.title_words_xy[0][1]) * GameConfig.f_zoomy, null);					
//				GameStaticImage.s_word_upgrade[0].drawBitmap(canvas, GameStaticImage.s_word_upgrade[0].bitmap, Location.GameShop.title_words_xy[1][0] * GameConfig.f_zoomx, (Location.GameShop.title_words_xy[1][1]) * GameConfig.f_zoomy, null);
//			}
//		}
//		
//		for (int i=0; i<Location.GameShop.shopbg01_xy.length; i++) {
//			GameStaticImage.s_share_ui_back_03.drawBitmap(canvas, GameStaticImage.s_share_ui_back_03.bitmap, Location.GameShop.shopbg01_xy[i][0] * GameConfig.f_zoomx, Location.GameShop.shopbg01_xy[i][1] * GameConfig.f_zoomy, null);
//			GameStaticImage.s_success_S_back_1.drawBitmap(canvas, GameStaticImage.s_success_S_back_1.bitmap, Location.GameShop.shopbg02_xy[i][0] * GameConfig.f_zoomx, Location.GameShop.shopbg02_xy[i][1] * GameConfig.f_zoomy, null);			
//			
//		if (shop_index != 0 && s_upgrade.length>i) {
//			 
//			int length = qualityMax(s_upgrade[i].getType());//
//			int type = (int)(10* GameConfig.f_zoom); //间隔
//			int _x =  GameStaticImage.s_success_S_back_1.bitmap.getWidth() -(length*wShop_lv+(length-1)*type)  >>1;
//			
//				//绘制升级物品的等级
//				 for(int j=0;j<length;++j){
//					 
//					 mySprite.drawBitmap(canvas, GameStaticImage.shop_lv[0]+".png", ((Location.GameShop.shopbg02_xy[i][0]* GameConfig.f_zoomx+_x)+(type+wShop_lv)*j) , 
//								((Location.GameShop.shopbg02_xy[i][1] )* GameConfig.f_zoomy - hShop_lv*2+(hShop_lv>>1)), null);
//					 
////					 shop_lv[0].drawBitmap(canvas, shop_lv[0].bitmap, ((Location.GameShop.shopbg02_xy[i][0]* GameConfig.f_zoom+_x)+(type+wShop_lv)*j) , 
////							(Location.GameShop.shopbg02_xy[i][1]* GameConfig.f_zoom - hShop_lv*2+(hShop_lv>>1)) , null);
//					 if(j<s_upgrade[i].getQuality()){
////						 shop_lv[1].drawBitmap(canvas, shop_lv[1].bitmap, ((Location.GameShop.shopbg02_xy[i][0]* GameConfig.f_zoom+_x)+(type+wShop_lv)*j) , 
////								(Location.GameShop.shopbg02_xy[i][1]* GameConfig.f_zoom - hShop_lv*2+(hShop_lv>>1)) , null);
//						 mySprite.drawBitmap(canvas, GameStaticImage.shop_lv[1]+".png",((Location.GameShop.shopbg02_xy[i][0]* GameConfig.f_zoomx+_x)+(type+wShop_lv)*j) , 
//									((Location.GameShop.shopbg02_xy[i][1] )* GameConfig.f_zoomy- hShop_lv*2+(hShop_lv>>1)) , null);
//					 }
//					 
//					 
//				 }
//			} 
//			
//			//ExternalMethods.drawImage(canvas, shopbutton.bitmap, (int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoom - (anjianbutton[i]?0.2f:0f) * shopbutton.bitmap.getWidth()/2), (int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoom - (anjianbutton[i]?0.2f:0f) * shopbutton.bitmap.getHeight()/2), anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);			
////			ExternalMethods.paintUI1(canvas, null, anjianbutton[i]?shopbutton[1].bitmap:shopbutton[0].bitmap, (int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoom - (anjianbutton[i]?0.2f:0f) * shopbutton[].bitmap.getWidth()/2), (int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoom - (anjianbutton[i]?0.2f:0f) * shopbutton.bitmap.getHeight()/2), (int)(134 * GameConfig.f_zoom * (anjianbutton[i]?1.2f:1f)), (int)(48 * GameConfig.f_zoom * (anjianbutton[i]?1.2f:1f)), -1);
//			if (anjianbutton[i]) {
//				GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy  + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), (int)(134 * GameConfig.f_zoomx * 1.2f), -1);
//			} else {				
//				GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx), (int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy), (int)(134 * GameConfig.f_zoomx), -1);
//			}
//			
//			String shop[] = {"shop_box_01", "shop_box_02", "shop_box_03", "shop_box_04"};
//			if (shop_index == 0) {
//				//这是绘制箱子
////				GameStaticImage.s_shop_box[i].drawBitmap(canvas, GameStaticImage.s_shop_box[i].bitmap, Location.GameShop.shopitem_xy[i][0] * GameConfig.f_zoomx, Location.GameShop.shopitem_xy[i][1] * GameConfig.f_zoomy, null);
//			
//				mySprite.drawBitmap(canvas, shop[i]+".png", Location.GameShop.shopitem_xy[i][0] * GameConfig.f_zoomx, Location.GameShop.shopitem_xy[i][1] * GameConfig.f_zoomy, null);
//			
//			}else if(s_upgrade.length>i){ //升级的图标
//					s_upgrade[i].paint(canvas);
//			}
//
//			
//			float tempx = Location.GameShop.shopitem_priceicon_xy[i][0] * GameConfig.f_zoomx;
//			float tempy = Location.GameShop.shopitem_priceicon_xy[i][1] * GameConfig.f_zoomy;
//			
////			if (i == 1 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL5]) {
////				free.drawBitmap(canvas, (int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - free.bitmap.getWidth()/2*(anjianbutton[i]?1.2f:1.0f)), (int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - free.bitmap.getHeight()/2*(anjianbutton[i]?1.2f:1.0f)), anjianbutton[i]?1.2f:1.0f, anjianbutton[i]?1.2f:1.0f, 255, 0, 0, 0);
////			} else {
//				if (shopbox[i]) {
//					//shopitem_priceicon[1].drawBitmap(canvas, shopitem_priceicon[1].bitmap, Location.GameShop.shopitem_priceicon_xy[i][0] * GameConfig.f_zoom, Location.GameShop.shopitem_priceicon_xy[i][1] * GameConfig.f_zoom, null);
//					shopitem_priceicon[1].drawBitmap(canvas, tempx - (anjianbutton[i]?0.2f:0f) * shopitem_priceicon[1].bitmap.getWidth()/2, tempy - (anjianbutton[i]?0.2f:0f) * shopitem_priceicon[1].bitmap.getHeight()/2, anjianbutton[i]?1.2f:1.0f, anjianbutton[i]?1.2f:1.0f, 255, 0, 0, 0);
//				} else {
//					//shopitem_priceicon[0].drawBitmap(canvas, shopitem_priceicon[0].bitmap, Location.GameShop.shopitem_priceicon_xy[i][0] * GameConfig.f_zoom, Location.GameShop.shopitem_priceicon_xy[i][1] * GameConfig.f_zoom, null);
//					shopitem_priceicon[0].drawBitmap(canvas, tempx - (anjianbutton[i]?0.2f:0f) * shopitem_priceicon[0].bitmap.getWidth()/2, tempy - (anjianbutton[i]?0.2f:0f) * shopitem_priceicon[0].bitmap.getHeight()/2, anjianbutton[i]?1.2f:1.0f, anjianbutton[i]?1.2f:1.0f, 255, 0, 0, 0);
//				}
//				if (shop_index == 0) {
//					GameStaticImage.s_word_num_04[0].drawBitmap(canvas, GameStaticImage.s_word_num_04, (int)(tempx + 46 * GameConfig.f_zoomx), (int)(tempy + 7 * GameConfig.f_zoomy), GameConfig.Char_num0, Integer.toString(shopitem_price[i]), null, 0,anjianbutton[i]?1.2f:1.0f);
//				}else{
//					int index = s_upgrade[i].getQuality();
//					if(index>=shopitem_upLevel[i].length)
//						index = shopitem_upLevel[i].length-1;
//					GameStaticImage.s_word_num_04[0].drawBitmap(canvas, GameStaticImage.s_word_num_04, (int)(tempx + 46 * GameConfig.f_zoomx), (int)(tempy + 7 * GameConfig.f_zoomy), GameConfig.Char_num0, Integer.toString(shopitem_upLevel[i][index]), null, 0,anjianbutton[i]?1.2f:1.0f);
//				}
//			}
//			
////		}
//		
//		//这是卡牌的滚动字母
//		paintShopItemWord(canvas);
//		
//		if (loading > 0) {
////			load1.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
////			load1.drawBitmap(canvas, load1.bitmap, GameConfig.GameScreen_Width/2 - load1.bitmap.getWidth()/2, GameConfig.GameScreen_Height/2 + load1.bitmap.getHeight()/2, null);
//			if (loading == 42) {
//				loading = -1;
//				GameManager.forbidModule(GameMenu.takeCardModule);
//			}
//			loading++;
//		}
//		
//	}
//	int loading = -1;
//	private void paintShopItemWord(Canvas canvas) {
//		for (int i=0;i<shopitemword.length; i++) {
//			canvas.save();
//			canvas.clipRect(Location.GameShop.shopitem_word_xy[i][0] * GameConfig.f_zoomx, Location.GameShop.shopitem_word_xy[i][1] * GameConfig.f_zoomy, (Location.GameShop.shopitem_word_xy[i][0] + 153) * GameConfig.f_zoomx, (Location.GameShop.shopitem_word_xy[i][1] + 45) * GameConfig.f_zoomy);
//			shopitemword[i].paintText(canvas, (int)(Location.GameShop.shopitem_word_xy[i][0] * GameConfig.f_zoomx), (int)(Location.GameShop.shopitem_word_xy[i][1] * GameConfig.f_zoomy) + word_move_y[i]);
//			canvas.restore();			
//		}
//	}
//
//	public void run() {
//		for (int i=0;i<shopitemword.length; i++) {
//			if (isScrollUp[i]) {
//				if (word_move_y[i] < - shopitemword[i].height()) {
//					word_move_y[i] = (int) ((45 + 10) * GameConfig.f_zoomy); 
//				} else {
//					word_move_y[i] -= 2;
//				}			
//			}
//		}
//		
//		upgrade_quality();
//		prop_upgrade = -1;
//		for(int i=0;i<s_upgrade.length;++i){
//			s_upgrade[i].logic();
//			 
//		}
//	}
//
//	/**
//	 * 升级品质
//	 * */
//	private void upgrade_quality(){
//		if(prop_upgrade != -1){
//			boolean temp_lack = false; //砖石 金币是否充足
//			
//			int index = s_upgrade[prop_upgrade].getQuality();
//			if(index>=shopitem_upLevel[prop_upgrade].length)
//				index = shopitem_upLevel[prop_upgrade].length-1;
//			int price = shopitem_upLevel[prop_upgrade][index];
//			
//			if (shopbox[prop_upgrade]) { //true为金币 false位砖石、
//				if(VeggiesData.getGold()>=price){
//					temp_lack = true;
//				}else{
//					String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GOLD);
//					GameManager.setPopUp(PopUp.GOOGS,GameStaticImage.shop_shop_gold_11, new PopUp(temp) {
//					});
//				}
//			}else {
//				if(VeggiesData.getGem()>=price){
//					temp_lack = true;
//				}else{
//					String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
//					GameManager.setPopUp(PopUp.GOOGS,GameStaticImage.shop_gem_13, new PopUp(temp) {
//					});
//				}
//			}
//			
//			if(temp_lack){//金币砖石足够
//				String temp = LangUtil.getLangString(LangDefineClient.QUALITY_MAX);
//				int _quality = s_upgrade[prop_upgrade].getQuality();
//				
//				if(_quality>=qualityMax(s_upgrade[prop_upgrade].getType())){
////					ChooseLevelModule1.sendMessage(temp);
//					GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
//					});
//					return;
//				}
//				
//				switch(s_upgrade[prop_upgrade].getType()){
//				case SLINGSHOT:
//					VeggiesData.setSlingshot_crit_level(_quality+1);
//					//成就
//					VeggiesData.addAchievementNum(Achievement.INTENSIFY_ONCE_Slingshot, 1);
//					break;
//				case BERG:
//					VeggiesData.setFence_HP_level(_quality+1);
//					VeggiesData.addAchievementNum(Achievement.INTENSIFY_MAX_Fence, 1);
//					break;
//				case TIME:
//					VeggiesData.setCombus_time_level(_quality+1);
//					break;
//				case TOMATO:
//					VeggiesData.setCombus_damage_level(_quality+1);
//					break;
//				}
//				s_upgrade[prop_upgrade].setStatr_animation();
//				String path = imagePath( s_upgrade[prop_upgrade].getType(), s_upgrade[prop_upgrade].getQuality()+1);
//				s_upgrade[prop_upgrade].setNextName(path);
//				
//				//扣除
//				if (shopbox[prop_upgrade]) 
//					VeggiesData.addGold(-price);  
//				else 
//					VeggiesData.addGem(-price);  
//				
//				
//			}
//		}
//	}
//	
//	public boolean onKeyDown(int keyCode, KeyEvent msg) {
//		return false;
//	}
//
//	public boolean onKeyUp(int keyCode, KeyEvent msg) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			GameManager.ChangeModule(null);
////			if (GameStaticImage.s_map_ship_1 == null) new ImageThread().start();
//		}
//		return false;
//	}
//
//	public void initwordpic() {
//		
//	}
//
//	public void onTouchEvent(MotionEvent event) {
//		float x = event.getX();
//		float y = event.getY();
//		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){ //新手教学
//			if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
//				for (int i=0; i<anjianbutton.length; i++) {
//					if (ExternalMethods.CollisionTest(x, y, 
//							(int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
//							(int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
//							(int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
//							(int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
//							if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL5 && i == 1) {
//								anjianbutton[i] = true;													
//						} 
//					}
//				}
//			}
//			if(event.getActionMasked() == MotionEvent.ACTION_UP){
//				for (int i=0; i<anjianbutton.length; i++) {
//					if (anjianbutton[i] && ExternalMethods.CollisionTest(x, y, 
//							(int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
//							(int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
//							(int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
//							(int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
//						if(shop_index==0){ //卡片
//							if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL5 && i == 1) {
//								GameManager.getGT().finish();
//								new VeggiesData().saveGame();
//								
//								GameMenu.takeCardModule.box_index = i;
////								GameMenu.takeCardModule.index = 1;
////								GameMenu.takeCardModule.getcard = new int[3];
////								GameMenu.takeCardModule.getcard[0] = GameItem.Item31/*Math.abs(new Random().nextInt()%20) + 1*/;
////								GameMenu.takeCardModule.getcard[1] = GameItem.Item46/*Math.abs(new Random().nextInt()%20) + 1*/;
//////								GameMenu.takeCardModule.getcard[2] = Math.abs(new Random().nextInt()%20) + 1;
////								int temp[] = {GameItem.Item63, GameItem.Item60, GameItem.Item57, GameItem.Item54, GameItem.Item51, GameItem.Item48, GameItem.Item45, GameItem.Item42, GameItem.Item39, GameItem.Item36, GameItem.Item33, GameItem.Item30, GameItem.Item03, GameItem.Item06, GameItem.Item09, GameItem.Item12, GameItem.Item15, GameItem.Item18, GameItem.Item21, GameItem.Item24, GameItem.Item27};
////								int tt = Math.abs(new Random().nextInt()%temp.length);
////								GameMenu.takeCardModule.getcard[2] = temp[tt];
//								GameManager.ResetToRunModule(GameMenu.takeCardModule);
//							}
//						}
//					}
//				}
//			}
//			return;
//		}
//		
//		
//		if (loading > 0) return;
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			if (ExternalMethods.CollisionTest(x, y, 
//					453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
//					76 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
//				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
//				76 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
//				anjianclose = true;
//			}
//			for (int i=0; i<anjianbutton.length; i++) {
//				if (ExternalMethods.CollisionTest(x, y, 
//						(int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
//						(int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
//						(int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
//						(int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
////					if (ChooseLevelModule2.gt.pauseState()) {
////						if (ChooseLevelModule2.gt.getTeachId()==GameTeaching.TEACH_VOL5 && i == 1) {
////							anjianbutton[i] = true;													
////						}
////					} else if (!ChooseLevelModule2.gt.pauseState()) {						
//							
//					boolean co = false;
//					if(shop_index==0){ //卡片
//						co = true;
//					}else if(shopitem_price.length>i){ //upgrade 
//						if(s_upgrade[i].getIsOK())
//							co = true;
//					}
//					if(co)
//						anjianbutton[i] = true;
////					
//				}
//			}
//			
//		} else if (event.getAction() == MotionEvent.ACTION_UP) {
//			if (anjianclose && ExternalMethods.CollisionTest(x, y, 
//					453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
//					76 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
//				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
//				76 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
////				if (GameStaticImage.s_map_ship_1 == null) new ImageThread().start();
//				GameManager.ChangeModule(null);
//			}
//			for (int i=0; i<anjianbutton.length; i++) {
//				if (anjianbutton[i] && ExternalMethods.CollisionTest(x, y, 
//						(int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
//						(int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
//						(int)(Location.GameShop.shopbutton_xy[i][0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
//						(int)(Location.GameShop.shopbutton_xy[i][1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
//					if(shop_index==0){ //卡片
//						if(isBuy(i)){
//							GameMenu.takeCardModule.box_index = i;
//							GameManager.ResetToRunModule(GameMenu.takeCardModule);
//						}
//					}else if(shopitem_price.length>i){ //upgrade 
//						prop_upgrade = i;
//					}
//				}
//				anjianbutton[i] = false;
//			}
//			for (int i=0; i<Location.GameShop.title_bg_xy.length; i++) {
//				
//				if (ExternalMethods.CollisionTest(x, y, 
//						(int)(Location.GameShop.title_bg_xy[i][0] * GameConfig.f_zoom + (GameStaticImage.s_share_ui_back_04[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_back_04[1].bitmap.getWidth()/2)), 
//						(int)(Location.GameShop.title_bg_xy[i][1] * GameConfig.f_zoom  + (GameStaticImage.s_share_ui_back_04[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_back_04[1].bitmap.getHeight()/2)), 
//						(int)(Location.GameShop.title_bg_xy[i][0] * GameConfig.f_zoom + (GameStaticImage.s_share_ui_back_04[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_back_04[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoom * 1.2f), 
//						(int)(Location.GameShop.title_bg_xy[i][1] * GameConfig.f_zoom  + (GameStaticImage.s_share_ui_back_04[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_back_04[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
//						shop_index = i;
//						if(i == 0){
//							if(shopitemword == null)
//								shopitemword = new TextBox[4];
//							for (int j=0; j<shopitemword.length; j++) {
//								if(shopitemword[j] == null)
//									shopitemword[j] = new TextBox();
//								shopitemword[j].setTextAlign(TextBox.LEFT);
//								shopitemword[j].setString(GameWord.shopItem_word[GameWord.useLanguage][j]);
//								shopitemword[j].setBoxSize((int)(153 * GameConfig.f_zoomx), (int)(1000 * GameConfig.f_zoomy));
//								shopitemword[j].setTextSize((int)(20*GameConfig.f_zoom));
//								shopitemword[j].setDefaultColor(Color.argb(255, 255, 255, 255));
//								shopitemword[j].height();
//								shopitemword[j].setBoxSize((int)(153 * GameConfig.f_zoomx), (int)shopitemword[j].height());
//								word_move_y[j] = 0;
//								if (shopitemword[j].height() > 45 * GameConfig.f_zoomy) {
//									isScrollUp[j] = true;
//								}
//							}
//						}else{
//							if(shopitemword == null)
//								shopitemword = new TextBox[4];
//							for (int j=0; j<shopitemword.length; j++) {
//								if(shopitemword[j] == null)
//									shopitemword[j] = new TextBox();
//								shopitemword[j].setTextAlign(TextBox.LEFT);
//								shopitemword[j].setString(GameWord.shopItem_upgrade[GameWord.useLanguage][j]);
//								shopitemword[j].setBoxSize((int)(153 * GameConfig.f_zoomx), (int)(1000 * GameConfig.f_zoomy));
//								shopitemword[j].setTextSize((int)(20*GameConfig.f_zoom));
//								shopitemword[j].setDefaultColor(Color.argb(255, 255, 255, 255));
//								shopitemword[j].height();
//								shopitemword[j].setBoxSize((int)(153 * GameConfig.f_zoomx), (int)shopitemword[j].height());
//								word_move_y[j] = 0;
//								if (shopitemword[j].height() > 45 * GameConfig.f_zoomy) {
//									isScrollUp[j] = true;
//								}
//							}
//						}
//				}
//				 
//			}
//			anjianclose = false;
//			for (int i=0; i<anjianbutton.length; i++) {
//				anjianbutton[i] = false;
//			}
//		}
//	}
//
//	public static boolean isBuy(int i){
//		boolean temp_lack = false; //砖石 金币是否充足
//		if (shopbox[i]) { //true为金币 false位砖石、
//			if(VeggiesData.getGold()>=shopitem_price[i]){
//				temp_lack = true;
//			}else{
//				String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GOLD);
//				GameManager.setPopUp(PopUp.GOOGS,GameStaticImage.shop_shop_gold_11, new PopUp(temp) {
//				});
//			}
//		}else {
//			if(VeggiesData.getGem()>=shopitem_price[i]){
//				temp_lack = true;
//			}else{
//				String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
//				GameManager.setPopUp(PopUp.GOOGS,GameStaticImage.shop_gem_13, new PopUp(temp) {
//				});
//			}
//		}
//		if(temp_lack){//金币砖石足够
//			//扣除
//			if (shopbox[i]) 
//				VeggiesData.addGold(-shopitem_price[i]);  
//			else 
//				VeggiesData.addGem(-shopitem_price[i]);  
//		}
//		return temp_lack;
//	}
//}
