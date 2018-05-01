package com.endlessvegetables2.ui;

import java.util.Random;
import java.util.Vector;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.audio.Music;
import com.kokatlaruruxi.wy.R;
import com.facebook.FacebookOperation;
import com.facebook.UserRequest;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.Module;
import com.socogame.coolEdit.CoolEditDefine;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class TakeCardModule extends Module{
	private Sprite tcm;
	private Sprite bg;
	private UISprite rainbow;
	private UISprite tomato;
	private Sprite[] box;
//	private Sprite[] card;
	private Sprite card;
//	private Sprite shine;
	private Sprite[] crossbow;
	private Sprite[] skip;
	private Sprite arrow;
	public static Sprite[] s_word_num_04;
	
	/**
	 * 抽卡的张数
	 * */
	private final int number[] = {1, 3, 6, 9};
	
	Bitmap bitmap_close;
	int chongzhi=-1;
	private long rainbow_wink_oldtime;
	private int rain_wink_scaletime;
	private int rain_wink_two;
	public int box_index = 3;
	private int card_index;
	private int card_frame;
	private float rotate_angle;
	private float tomato_speed = 40*GameConfig.f_zoomy;
	private float tomato_move_Y;
	
	private boolean isHideGetCard;
	private boolean isOpen;
	private boolean ishide;//隐藏番茄
	private boolean anjianskip;
	private boolean ishideskip;
	
	private float[] cardsize = {0.2f,0.6f,0.8f};
	private float[] bgaphlasize = {0.1f,0.2f,0.35f,0.5f};
	private float[][] card_xy = 
	{//x坐标废弃用实时得到计算
			{229,172},
			{190,178},
			{162,188},
	};
	public int[] getcard;// = {GameItem.Item03, GameItem.Item09, GameItem.Item10, 
			//GameItem.Item11, GameItem.Item12, GameItem.Item13, GameItem.Item14, 
			//GameItem.Item15, GameItem.Item16};
//	public int index=0;
	private boolean isMoveTomato;
	private boolean isUpdateCard;
	private boolean isArrowDown;
	private float oldY;
	private float move_Y;
	private float spring_speed;
	private float maxMove = 100 * GameConfig.f_zoomy;
	private float ArrowH;
	
	Paint paint1;
	Typeface fontFace ;
	
	//test
	private int testtime = 5000;
	private long starttime;
	//-------------------原getCardModule-----------
	private Sprite backbg1;
	private Sprite backbg2;
	private Sprite backbg3;
//	private Sprite close;
	private Sprite title_get;
	private Sprite[] button;
	private Sprite button_share_text;
	private Sprite button_ok_text;
	private Sprite[] smallcard;
	private Sprite[] smallcardbg;
	private Sprite share_ui_button_08;
	private Sprite share_icon_facebook_05;
	private Sprite[] shopitem_priceicon;
	
	private boolean anjianclose;
	private boolean anjianshare,anjianok,anjian_close;
	

	//道具卡
	final int propCardID[] = {GameItem.Item31,GameItem.Item32,GameItem.Item33,GameItem.Item34,GameItem.Item35,
			GameItem.Item36,GameItem.Item37,GameItem.Item38,GameItem.Item39,GameItem.Item40,GameItem.Item41,
			GameItem.Item42,GameItem.Item43,GameItem.Item44,GameItem.Item45};
	//强化卡
	final int strengtheningID[] = {GameItem.Item46,GameItem.Item47,GameItem.Item48,GameItem.Item49,GameItem.Item50,
			GameItem.Item51,GameItem.Item52,GameItem.Item53,GameItem.Item54,GameItem.Item55,GameItem.Item56,GameItem.Item57,
			GameItem.Item58,GameItem.Item59,GameItem.Item60,GameItem.Item61,GameItem.Item62,GameItem.Item63};
	Random random = new Random();
	Paint paint;
	
	int index = 0;
	int skwex[] = {10, 20,  0, -10,  0, -20, 0 };
	int skwey[] = {	0, 	0,  0,	 0,  0,   0, 0 };
	boolean isShowAnt; //显示震屏动画
	public void releaseResource() {
//		GameImage.delImage(bg.bitmap);
//		bg = null;
//		for (int i=0; i<box.length; i++) {
//			GameImage.delImage(box[i].bitmap);
//			box[i] = null;
//		}
//		box = null;
//		for (int i=0; i<card.length; i++) {
//			GameImage.delImage(card[i].bitmap);
//			card[i] = null;
//		}
//		GameImage.delImage(card.bitmap);
//		card = null;
//		GameImage.delImage(shine.bitmap);
//		shine = null;
//		for (int i=0; i<crossbow.length; i++) {
//			GameImage.delImage(crossbow[i].bitmap);
//			crossbow[i] = null;
//		}
//		crossbow = null;
//		for (int i=0; i<skip.length; i++) {
//			GameImage.delImage(skip[i].bitmap);
//			skip[i] = null;
//		}
//		skip = null;
		
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
//		for (int i=0; i<smallcard.length; i++) {
//			GameImage.delImage(smallcard[i].bitmap);
//			smallcard[i].bitmap = null;
//		}
//		smallcard = null;
	}
	
	public TakeCardModule() {
		
	}
	
	public boolean initialize() {
		SpriteLibrary.loadSpriteImage(CoolEditDefine.NPC_RAINBOW);
		SpriteLibrary.loadSpriteImage(CoolEditDefine.NPC_TOMATO);
		bitmap_close=GameImage.getImage("share/ui_close");
		index = 0;
		card_index = 0;
		tcm = new Sprite();
		bg = new Sprite(GameImage.getImage(GameStaticImage.TCard_ui_back_09));
		rainbow = new UISprite(); 
//		rainbow.initSprite(UISpriteLibrary.NPC_RAINBOW, (int)(264 * GameConfig.f_zoom), (int)((498 + 20) * GameConfig.f_zoom), 1);
		
		tomato = new UISprite();
		
		box = new Sprite[8];
		box[0] = new Sprite(GameImage.getImage(GameStaticImage.TCard_shop_box_05));								
		box[1] = new Sprite(GameImage.getImage(GameStaticImage.TCard_shop_box_06));								
		box[2] = new Sprite(GameImage.getImage(GameStaticImage.TCard_shop_box_07));								
		box[3] = new Sprite(GameImage.getImage(GameStaticImage.TCard_shop_box_08));								
		box[4] = new Sprite(GameImage.getImage(GameStaticImage.TCard_shop_box_09));								
		box[5] = new Sprite(GameImage.getImage(GameStaticImage.TCard_shop_box_10));								
		box[6] = new Sprite(GameImage.getImage(GameStaticImage.TCard_shop_box_11));								
		box[7] = new Sprite(GameImage.getImage(GameStaticImage.TCard_shop_box_12));	
		
		shopitem_priceicon = new Sprite[2];
		shopitem_priceicon[0] = new Sprite(GameImage.getImage(GameStaticImage.shop_gem_12));
		shopitem_priceicon[1] = new Sprite(GameImage.getImage(GameStaticImage.shop_gold_06));
		s_word_num_04 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_04, 12, 1, GameImage.Sort_line);
		
		
//		for (int i=0; i<box.length; i++) {
//			if (i+5 > 9) {
//				box[i] = new Sprite(GameImage.getImage("TCard/shop_box_" + (i+5)));								
//			} else {
//				box[i] = new Sprite(GameImage.getImage("TCard/shop_box_0" + (i+5)));		
//			}
//		}
//		card = new Sprite[63];
//		for (int i=0; i<card.length; i++) {
//			if (i  < 9) {
//				card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (i+1)));				
//			} else {				
//				card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_" + (i+1)));				
//			}
//		}
//		shine = new Sprite(GameImage.getImage(GameStaticImage.TCard_ui_shine_2));
//		boxeS

		crossbow = new Sprite[2];
		crossbow[0] = new Sprite(GameImage.getImage(GameStaticImage.TCard_ui_crossbow));
		crossbow[1] = new Sprite(GameImage.getImage(GameStaticImage.TCard_ui_crossbow_2));
		skip = new Sprite[2];
		skip[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_04));
		skip[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_06));
		
		
		backbg1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
		backbg2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
		backbg3 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
//		close = new Sprite(GameImage.getImage("share/ui_close"));
		title_get = new Sprite(GameImage.getImage(GameStaticImage.word_get));
		button = new Sprite[2];
		button[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
		button[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
		button_share_text = new Sprite(GameImage.getImage(GameStaticImage.word_share));
		button_ok_text = new Sprite(GameImage.getImage(GameStaticImage.word_again));
		share_ui_button_08 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_08));
		share_icon_facebook_05 = new Sprite(GameImage.getImage(GameStaticImage.share_icon_facebook_05));
		
		arrow = new Sprite(GameImage.getImage(GameStaticImage.map_farmArrow));
		//--------------------------合并构造函数和init------------------------
//		card = new Sprite[getcard.length];
//		for (int i=0; i<getcard.length; i++) {
//			if (getcard[i]  < 10) {
//				card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (getcard[i])));				
//			} else {				
//				card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_" + (getcard[i])));				
//			}
//		}
		smallcardbg = new Sprite[3];
		for (int i=0; i<smallcardbg.length; i++) {
			smallcardbg[i] = new Sprite(GameImage.getImage(GameStaticImage.SMALLCARD + "card_s_0" + (i+5)));
		}
		
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(5*GameConfig.f_zoom);
		initdata2();
		initdata();
		return false;
	}
	
	public void initdata2() {
		int number = this.number[box_index];
		GameMenu.takeCardModule.getcard = new int[number];
		
		
		//1星的
		Vector<Integer> star_1 = new Vector<Integer>();
		for(int i=0;i<63;++i){
			int a = 4+i*3;
			if(a>=63){
				break;
			}
			star_1.add(new Integer(a));
		}
		
		//2星的
		Vector<Integer> star_2 = new Vector<Integer>();
		for(int i=0;i<63;++i){
			int a = 2+i*3;
			if(a>=63){
				break;
			}
			star_2.add(new Integer(a));
		}
		//3星的
		Vector<Integer> star_3 = new Vector<Integer>();
		for(int i=0;i<63;++i){
			int a = 3+i*3;
			if(a>63){
				break;
			}
			star_3.add(new Integer(a));
		}
		
		
//		抽卡类别	每抽1星卡概率	每抽2星卡概率	每抽3星概率	价格	备注
//		1连抽	98%	2%	0	100金币	不能抽到道具卡，强化卡
//		3连抽	98%	2%	0	300金币	至少有一张2星卡，不能抽到强化卡
//		6连抽	21%	78%	1%	6钻	至少有1张2星卡
//		9连抽	11%	88%	1%	9钻	至少有一张3星卡
		if(number == 1){ //1连抽
			int star = 1;
			int end = 100;
			int a =random.nextInt((end-star)+1)+star;
			if(a<=98){
				//一星的
				star = 0;
				end = star_1.size()-1;
				a = isOK(2, star_1, star, end);
				GameMenu.takeCardModule.getcard[0] = a;
			}else if(a>=99){
				 	  //二星的
				star = 0;
				end = star_2.size()-1;
				a = isOK(2, star_2, star, end);
				GameMenu.takeCardModule.getcard[0] = a;
			}
		}else if(number == 3){ //3连抽
			boolean isTwo = false; //至少有一张两星卡
			for(int i=0;i<number;++i){
				int star = 1;
				int end = 100;
				int a =random.nextInt((end-star)+1)+star;
				if(a<=98){
					//一星的
					star = 0;
					end = star_1.size()-1;
					a = isOK(1, star_1, star, end);
					GameMenu.takeCardModule.getcard[i] = a;
				}else if(a>=99){
					 	  //二星的
					star = 0;
					end = star_2.size()-1;
					a = isOK(1, star_2, star, end);
					isTwo = true;
					GameMenu.takeCardModule.getcard[i] = a;
				}
			}
			if(!isTwo){ //没抽到两星卡
				int star = 0;
				int end = star_2.size()-1;
				int a = isOK(1, star_2, star, end);
				isTwo = true;
				GameMenu.takeCardModule.getcard[2] = a;
			}
			
		}else if(number == 6){//6连抽	
			boolean isTwo = false; //至少有一张两星卡
			for(int i=0;i<number;++i){
				int star = 1;
				int end = 100;
				int a =random.nextInt((end-star)+1)+star;
				if(a<=21){
					//一星的
					star = 0;
					end = star_1.size()-1;
					a = isOK(3, star_1, star, end);
					GameMenu.takeCardModule.getcard[i] = a;
				}else if(a<=99){
					 	  //二星的
					star = 0;
					end = star_2.size()-1;
					a = isOK(3, star_2, star, end);
					isTwo = true;
					GameMenu.takeCardModule.getcard[i] = a;
				}else if(a==100){
				 	  //三星的
					star = 0;
					end = star_3.size()-1;
					a = isOK(3, star_3, star, end);
					GameMenu.takeCardModule.getcard[i] = a;
				}
			}
			if(!isTwo){ //没抽到两星卡
				int star = 0;
				int end = star_2.size()-1;
				int a = isOK(3, star_2, star, end);
				isTwo = true;
				GameMenu.takeCardModule.getcard[5] = a;
			}
		}else if(number == 9){ //9连抽
			boolean isSan = false; //至少有一张三星卡
			for(int i=0;i<number;++i){
				int star = 1;
				int end = 100;
				int a =random.nextInt((end-star)+1)+star;
				if(a<=11){
					//一星的
					star = 0;
					end = star_1.size()-1;
					a = isOK(3, star_1, star, end);
					GameMenu.takeCardModule.getcard[i] = a;
				}else if(a<=99){
					 	  //二星的
					star = 0;
					end = star_2.size()-1;
					a = isOK(3, star_2, star, end);
					GameMenu.takeCardModule.getcard[i] = a;
				}else if(a==100){
				 	  //三星的
					star = 0;
					end = star_3.size()-1;
					a = isOK(3, star_3, star, end);
					GameMenu.takeCardModule.getcard[i] = a;
					isSan = true;
				}
			}
			if(!isSan){ //没抽到3星卡
				int star = 0;
				int end = star_3.size()-1;
				int a = isOK(3, star_3, star, end);
				isSan = true;
				GameMenu.takeCardModule.getcard[8] = a;
			}
		}
		
		for(int i = 0; i<GameMenu.takeCardModule.getcard.length;++i){
			 VeggiesData.isVegetablesRepeat(GameMenu.takeCardModule.getcard[i]);
		}
		
//		for(int i=0;i<number;++i){
//			GameMenu.takeCardModule.getcard[i] = new Random().nextInt(20)+1;
//		}
		
//		}else{
//			GameMenu.takeCardModule.getcard = new int[3];
//			GameMenu.takeCardModule.getcard[0] = Math.abs(new Random().nextInt()%20) + 1;
//			GameMenu.takeCardModule.getcard[1] = Math.abs(new Random().nextInt()%20) + 1;
//			GameMenu.takeCardModule.getcard[2] = Math.abs(new Random().nextInt()%20) + 1;
//		}
		if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL6]){
			int temp[] = {GameItem.Item63, GameItem.Item60, GameItem.Item57, GameItem.Item54, GameItem.Item51, GameItem.Item48, GameItem.Item45, GameItem.Item42, GameItem.Item39, GameItem.Item36, GameItem.Item33, GameItem.Item30, GameItem.Item03, GameItem.Item06, GameItem.Item09, GameItem.Item12, GameItem.Item15, GameItem.Item18, GameItem.Item21, GameItem.Item24, GameItem.Item27};
			int tt = Math.abs(new Random().nextInt()%temp.length);
			GameMenu.takeCardModule.getcard = new int[]{GameItem.Item31,GameItem.Item46, temp[tt]};//
		}
		
		smallcard = new Sprite[getcard.length];
		for (int i=0; i<smallcard.length; i++) {
			if (getcard[i]  < 10) {
				smallcard[i] = new Sprite(GameImage.getImage("smallcard1/card_pc_0" + getcard[i] + "_s"));				
			} else {				
				smallcard[i] = new Sprite(GameImage.getImage("smallcard1/card_pc_" + getcard[i] + "_s"));				
			}
		}
		if (getcard[card_index]  < 10) {
			card = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (getcard[card_index])));			
		} else {
			card = new Sprite(GameImage.getImage("bigcard/card_pc_" + (getcard[card_index])));	
		}
		chongzhi=-1;
	}
	
	/**
	 * 0 表示不抽道具卡
	 * 1表示不愁强化卡
	 * 2表示两个都不抽
	 * 3表示什么都可以抽
	 * */
	private int isOK(int index,Vector<Integer> star_v,  int star, int end){
		boolean isreturn = true;
		int a = 0;
		while(isreturn){
			boolean isok = true;
			a =random.nextInt((end-star)+1)+star;
			a = star_v.get(a).intValue();
			if(index != 3 && (index==0 || index == 2)){
				for(int i=0;i<propCardID.length;++i){ 
					 if(propCardID[i]==a)
						 isok = false;
				}
			}
			if(index != 3 && (index==1 || index == 2)){
				for(int i=0;i<strengtheningID.length;++i){
					if(strengtheningID[i]==a)
						 isok = false;
				}
			}
			if(isok){
				isreturn = false;
			}
		}
		return a;
	}
	
	public void initdata() {
		rainbow.initSprite(CoolEditDefine.NPC_RAINBOW, (int)(Location.TakeCard.rainbow_xy[0] * GameConfig.f_zoomx), (int)(Location.TakeCard.rainbow_xy[1] * GameConfig.f_zoomy), UISprite.RAINBOW_STATE_NOMAL);		
		rainbow.changeAction(0);
		
		tomato.initSprite(CoolEditDefine.NPC_TOMATO, (int)(Location.TakeCard.tomato_xy[0] * GameConfig.f_zoomx), (int)(Location.TakeCard.tomato_xy[1] * GameConfig.f_zoomy), UISprite.TOMATO_STATE_NOMAL);		
		tomato.changeAction(1);
		
		rainbow_wink_oldtime = 0;
		rain_wink_scaletime = 2;
		rain_wink_two = 0;
		rain_wink_scaletime += Math.abs(Math.random()%4);
		
		isOpen = false;
		card_frame = -1;//0为无半透开始
		rotate_angle = 0;
		
		card_index = 0;
		
		if (getcard[card_index]  < 10) {
			card = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (getcard[card_index])));			
		} else {
			card = new Sprite(GameImage.getImage("bigcard/card_pc_" + (getcard[card_index])));	
		}
		
		starttime = System.currentTimeMillis();
		
		ishideskip = true;
		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
		paint1 =new Paint();
		paint1.setTextSize(20*GameConfig.f_zoom);
		paint1.setTypeface(fontFace);
		paint1.setColor(Color.WHITE);
		paint1.setFlags(Paint.ANTI_ALIAS_FLAG);
		
		isHideGetCard = true;
		isUpdateCard = false;
		isArrowDown = false;
		anjian_close=false;
		
		tomato_init();
		if(!VeggiesData.isMuteMusic())
			GameMedia.playMusic(R.raw.cardl, true, true);
		GameImage.showImageHashMap();
		
		for(int i=0; i<getcard.length; i++) {
			VeggiesData.setAllCardNum(getcard[i], 1);
		}
		new VeggiesData().saveGame();
		
		if (VeggiesData.getGameGuanka()[3] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL6]) {
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL6, (int)((273) * GameConfig.f_zoomx), (int)(733 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_6), GameTeaching.HAND_MOVE_STATE_0, GameConfig.GameScreen_Height>>1);
		}
		
	}

	public void paint(Canvas canvas) {
//		GameStaticImage.s_TCard_ui_back_09.drawBitmap(canvas, GameStaticImage.s_TCard_ui_back_09.bitmap, 0, 0, null);
		if(isShowAnt){
			if(GameConfig.i_coke%2==0)
				index++;
			if(index>=skwex.length){
				isShowAnt = false;
				canvas.translate(0, 0);
			}else
				canvas.translate(skwex[index], skwey[index]);
			
		}
		
		bg.drawBitmap(canvas, bg.bitmap, 0, 0, null);
		Paint p = new Paint();
		p.setColor(Color.argb(77, 0, 0, 0));
		paintShadowColor(canvas, Location.TakeCard.rainbow_shadow_xy[0] * GameConfig.f_zoomx, Location.TakeCard.rainbow_shadow_xy[1] * GameConfig.f_zoomy, (Location.TakeCard.rainbow_shadow_xy[0] + 205) * GameConfig.f_zoomx, (Location.TakeCard.rainbow_shadow_xy[1] + 70) * GameConfig.f_zoomy, p, true);
		paintShadowColor(canvas, Location.TakeCard.tomato_shadow_xy[0] * GameConfig.f_zoomx, Location.TakeCard.tomato_shadow_xy[1] * GameConfig.f_zoomy, (Location.TakeCard.tomato_shadow_xy[0] + 95) * GameConfig.f_zoomx, (Location.TakeCard.tomato_shadow_xy[1] + 30) * GameConfig.f_zoomy, p, true);
		rainbow.paintSprite(canvas, 0, 0);
//		if (isOpen) {
//			GameStaticImage.s_TCard_shop_box[box_index * 2 + 1].drawBitmap(canvas, GameStaticImage.s_TCard_shop_box[box_index * 2 + 1].bitmap, Location.TakeCard.box_xy[box_index * 2 + 1][0] * GameConfig.f_zoom, Location.TakeCard.box_xy[box_index * 2 + 1][1] * GameConfig.f_zoom, null);			
//		} else {			
//			GameStaticImage.s_TCard_shop_box[box_index * 2].drawBitmap(canvas, GameStaticImage.s_TCard_shop_box[box_index * 2].bitmap, Location.TakeCard.box_xy[box_index * 2][0] * GameConfig.f_zoom, Location.TakeCard.box_xy[box_index * 2][1] * GameConfig.f_zoom, null);			
//		}
		if (isOpen) {
			box[box_index * 2 + 1].drawBitmap(canvas, box[box_index * 2 + 1].bitmap, Location.TakeCard.box_xy[box_index * 2 + 1][0] * GameConfig.f_zoomx, Location.TakeCard.box_xy[box_index * 2 + 1][1] * GameConfig.f_zoomy, null);			
		} else {			
			box[box_index * 2].drawBitmap(canvas, box[box_index * 2].bitmap, Location.TakeCard.box_xy[box_index * 2][0] * GameConfig.f_zoomx, Location.TakeCard.box_xy[box_index * 2][1] * GameConfig.f_zoomy, null);			
		}
		
		//弹弓在卡片下面
		float tempx1 = Location.TakeCard.crossbow[0][0] * GameConfig.f_zoomx;
		float tempy1 = Location.TakeCard.crossbow[0][1] * GameConfig.f_zoomy;
		crossbow[0].drawBitmap(canvas, crossbow[0].bitmap, tempx1, tempy1, null);
		float tempx2 = Location.TakeCard.crossbow[1][0] * GameConfig.f_zoomx;
		float tempy2 = Location.TakeCard.crossbow[1][1] * GameConfig.f_zoomy;
		canvas.drawLine(tempx1 + 25 * GameConfig.f_zoomx, tempy1 + 64 * GameConfig.f_zoomy, tempx2 + 1 * GameConfig.f_zoomx, tempy2 + 8 * GameConfig.f_zoomy + move_Y, paint);
		canvas.drawLine(tempx1 + crossbow[0].bitmap.getWidth() - 25 * GameConfig.f_zoomx, tempy1 + 64 * GameConfig.f_zoomy, tempx2 + crossbow[1].bitmap.getWidth() - 1 * GameConfig.f_zoomx, tempy2 + 8 * GameConfig.f_zoomy + move_Y, paint);
		if (!ishide)
			tomato.paintSprite(canvas, 0, (int)(move_Y + tomato_move_Y));
		crossbow[1].drawBitmap(canvas, crossbow[1].bitmap, tempx2, tempy2 + move_Y, null);
		
		if (isHideGetCard) {
			//指引箭头
			if (isArrowDown) {
				ArrowH -= Math.max(2, 0);
				if (ArrowH <= 0) {
					ArrowH = 0;
					isArrowDown = false;
				}
			} else {
				ArrowH += Math.min(2, 15);
				if (ArrowH >= 15) {
					ArrowH = 15;
					isArrowDown = true;
				}
			}
			arrow.drawBitmap(canvas, (int)(Location.TakeCard.tomato_xy[0] * GameConfig.f_zoomx) - arrow.bitmap.getWidth()/2, (int)(Location.TakeCard.tomato_xy[1] * GameConfig.f_zoomy)-ArrowH*GameConfig.f_zoomy, 1f, 1f, 255, 0, 0, 0);
			
			if (rainbow.state == UISprite.RAINBOW_STATE_SMILE) {
//				float tempx = 0 ;
//				float tempy = 0 ;
//				if (card_frame >= 3) {
//					tempx = GameConfig.GameScreen_Width/2;
//					tempy = GameConfig.GameScreen_Height/2;
//				} else {
//					tempx = GameConfig.GameScreen_Width/2;
//					tempy = card_xy[card_frame][1] * GameConfig.f_zoom - card[card_index-1].bitmap.getHeight()/2*cardsize[card_frame];
//				}
				if (card_frame >= 0) {
					tcm.drawBitmap(canvas, new Paint(), Color.BLACK, (int)(255 * bgaphlasize[card_frame>3?3:card_frame]), 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);			
				}
//				for (int i=0; i<6; i++) {
//					if (card_frame >= 3) {					
//						shine.drawBitmap(canvas, GameConfig.GameScreen_Width/2 - shine.bitmap.getWidth()/2, GameConfig.GameScreen_Height/2 - shine.bitmap.getHeight(), 1f, 1f, 255, i * 60 + rotate_angle, shine.bitmap.getWidth()/2, shine.bitmap.getHeight());
//					} else if (card_frame >= 0){
//						shine.drawBitmap(canvas, GameConfig.GameScreen_Width/2 - shine.bitmap.getWidth()/2*cardsize[card_frame], card_xy[card_frame][1] * GameConfig.f_zoomy + card.bitmap.getHeight()/2*cardsize[card_frame] - shine.bitmap.getHeight()*cardsize[card_frame], cardsize[card_frame], cardsize[card_frame], 255, i * 60 + rotate_angle, (int)(shine.bitmap.getWidth()/2*cardsize[card_frame]), (int)(shine.bitmap.getHeight()*cardsize[card_frame]));
//					}
//				}
//				if (card_frame >= 3) {
//					ExternalMethods.drawImage(canvas, card[getcard[card_index]-1].bitmap, GameConfig.GameScreen_Width/2 - card[getcard[card_index]-1].bitmap.getWidth()/2, GameConfig.GameScreen_Height/2 - card[getcard[card_index]-1].bitmap.getHeight()/2, 1f, 1f, 255, 0, 0, 0);				
//				} else if (card_frame >= 0){
//					ExternalMethods.drawImage(canvas, card[getcard[card_index]-1].bitmap, GameConfig.GameScreen_Width/2 - card[getcard[card_index]-1].bitmap.getWidth()*cardsize[card_frame]/2, card_xy[card_frame][1] * GameConfig.f_zoom, cardsize[card_frame], cardsize[card_frame], 255, 0, 0, 0);				
//				}
				if (card_frame >= 3) {
					card.drawBitmap(canvas, GameConfig.GameScreen_Width/2 - card.bitmap.getWidth()/2, GameConfig.GameScreen_Height/2 - card.bitmap.getHeight()/2, 1f, 1f, 255, 0, 0, 0);				
				} else if (card_frame >= 0){
					card.drawBitmap(canvas, GameConfig.GameScreen_Width/2 - card.bitmap.getWidth()*cardsize[card_frame]/2, card_xy[card_frame][1] * GameConfig.f_zoomy, cardsize[card_frame], cardsize[card_frame], 255, 0, 0, 0);				
				}
			}
			if (!ishideskip) {
				skip[anjianskip?1:0].drawBitmap(canvas, null,
						(int)(Location.TakeCard.skip_xy[0] * GameConfig.f_zoomx), (int)(Location.TakeCard.skip_xy[1] * GameConfig.f_zoomy), 
						(int)((20)* GameConfig.f_zoomx + skip[anjianskip?1:0].bitmap.getWidth()), -1);
//				skip[anjianskip?1:0].drawBitmap(canvas, skip[anjianskip?1:0].bitmap, Location.TakeCard.skip_xy[0] * GameConfig.f_zoom, Location.TakeCard.skip_xy[1] * GameConfig.f_zoom, null);
				canvas.drawText(GameWord.skip[GameWord.useLanguage], (Location.TakeCard.skip_xy[0] + 29)* GameConfig.f_zoomx, (Location.TakeCard.skip_xy[1] + 22)* GameConfig.f_zoomy, paint1);
			}
			
		} else {
			
			tcm.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
			backbg1.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(85 * GameConfig.f_zoomy), (int)(476 * GameConfig.f_zoomx), (int)(671 * GameConfig.f_zoomy), -1);
			backbg2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(148 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(591 * GameConfig.f_zoomy), -1);
			backbg3.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(148 * GameConfig.f_zoomy), (int)(443 * GameConfig.f_zoomx), (int)(591 * GameConfig.f_zoomy), -1);
//			close.drawBitmap(canvas, 453 * GameConfig.f_zoom - close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 76 * GameConfig.f_zoom - close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
			title_get.drawBitmap(canvas, title_get.bitmap, Location.GetCard.title_xy[0] * GameConfig.f_zoomx, Location.GetCard.title_xy[1] * GameConfig.f_zoomy, null);
			
//			if (anjianshare) {
//				button[1].drawBitmap(canvas, null, (int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), (int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), (int)(152 * GameConfig.f_zoomx * 1.2f), -1);
//			} else {				
//				button[0].drawBitmap(canvas, null, (int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx), (int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy), (int)(152 * GameConfig.f_zoomx), -1);
//			}
			
			share_ui_button_08.drawBitmap(canvas,
					(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx)
							- share_ui_button_08.bitmap.getWidth()
							/ 2 * (anjianshare ? 0.2f : 0f), (int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy)
							- share_ui_button_08.bitmap.getHeight()
							/ 2 * (anjianshare ? 0.2f : 0f), anjianshare ? 1.2f
							: 1f, anjianshare ? 1.2f : 1f, 255, 0, 0, 0);
			share_icon_facebook_05.drawBitmap(canvas, 
					(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx)+(share_ui_button_08.bitmap.getWidth()-share_icon_facebook_05.bitmap.getWidth()>>1) - share_icon_facebook_05.bitmap.getWidth()/2*(anjianshare?0.2f:0.0f), 
					(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy)+(8*GameConfig.f_zoomy) - share_icon_facebook_05.bitmap.getHeight()/2*(anjianshare?0.2f:0.0f), 
					anjianshare?1.2f:1.0f, anjianshare?1.2f:1.0f, 255, 0, 0, 0);
//			
			button_share_text.drawBitmap(canvas, Location.GetCard.share_xy[0] * GameConfig.f_zoomx +(share_ui_button_08.bitmap.getWidth()-button_share_text.bitmap.getWidth()>>1)- button_share_text.bitmap.getWidth()/2*(anjianshare?0.2f:0.0f), ((int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy)+share_ui_button_08.bitmap.getHeight() -button_share_text.bitmap.getHeight() -8*GameConfig.f_zoomy) - button_share_text.bitmap.getHeight()/2*(anjianshare?0.2f:0.0f), anjianshare?1.2f:1.0f, anjianshare?1.2f:1.0f, 255, 0, 0, 0);
//			if (anjianok) {
//				button[1].drawBitmap(canvas, null, (int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), (int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy  + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), (int)(152 * GameConfig.f_zoomx * 1.2f), -1);
//			} else {				
//				button[0].drawBitmap(canvas, null, 
//						(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx), 
//						(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy), 
//						(int)(152 * GameConfig.f_zoomx), -1);
//			}
			share_ui_button_08.drawBitmap(canvas,
					(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx)
							- share_ui_button_08.bitmap.getWidth() / 2 * (anjianok ? 0.2f : 0f),
							(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy)
							- share_ui_button_08.bitmap.getHeight() / 2 * (anjianok ? 0.2f : 0f), anjianok ? 1.2f : 1f, 
									anjianok ? 1.2f : 1f, 255, 0, 0, 0);
			
			float anjianok_x = (int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx);
			float anjianok_y = (int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy)+8*GameConfig.f_zoomy;
			float width = 0;
			float height = 0;
			String number = Integer.toString(GameShop1.shopitem_price[box_index]);
			if (GameShop1.shopbox[box_index]) {
				width = shopitem_priceicon[1].bitmap.getWidth();
				height = shopitem_priceicon[1].bitmap.getHeight();
			}else{
				width = shopitem_priceicon[0].bitmap.getWidth();
				height = shopitem_priceicon[0].bitmap.getHeight();
			}
			anjianok_x = anjianok_x+(share_ui_button_08.bitmap.getWidth() - (width+10*GameConfig.f_zoomx+(number.length()*s_word_num_04[0].bitmap.getWidth()+(number.length()-1))))/2;
			
			if (GameShop1.shopbox[box_index]) {
				shopitem_priceicon[1].drawBitmap(canvas, (int)(anjianok_x) - shopitem_priceicon[1].bitmap.getWidth() / 2 * (anjianok ? 0.2f : 0f), (int)(anjianok_y) - shopitem_priceicon[1].bitmap.getHeight() / 2 * (anjianok ? 0.2f : 0f), anjianok ? 1.2f : 1f, anjianok?1.2f:1.0f,  255, 0, 0, 0);
			} else {
				shopitem_priceicon[0].drawBitmap(canvas, (int)(anjianok_x) - shopitem_priceicon[0].bitmap.getWidth() / 2 * (anjianok ? 0.2f : 0f), (int)(anjianok_y) - shopitem_priceicon[0].bitmap.getHeight() / 2 * (anjianok ? 0.2f : 0f), anjianok ? 1.2f : 1f, anjianok?1.2f:1.0f, 255, 0, 0, 0);
			}
			anjianok_x = anjianok_x+(width+10*GameConfig.f_zoomx);
			
			s_word_num_04[0].drawBitmap(canvas, s_word_num_04, (int)(anjianok_x), (int)(anjianok_y+(height-s_word_num_04[0].bitmap.getHeight())/2), GameConfig.Char_num0, number, null, 0, anjianok?1.2f:1.0f);
			
			anjianok_x = (int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx);
			button_ok_text.drawBitmap(canvas,anjianok_x+(share_ui_button_08.bitmap.getWidth()-button_ok_text.bitmap.getWidth()>>1) - button_ok_text.bitmap.getWidth()/2*(anjianok?0.2f:0.0f), ((int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy) + share_ui_button_08.bitmap.getHeight() - button_ok_text.bitmap.getHeight())-8*GameConfig.f_zoomy - button_ok_text.bitmap.getHeight()/2*(anjianok?0.2f:0.0f), anjianok?1.2f:1.0f, anjianok?1.2f:1.0f, 255, 0, 0, 0);
			
			
			for (int i=0; i<getcard.length; i++) {
				smallcardbg[0].drawBitmap(canvas, smallcardbg[(getcard[i]-1)%3].bitmap, Location.GetCard.card_xy[i][0] * GameConfig.f_zoomx, Location.GetCard.card_xy[i][1] * GameConfig.f_zoomy, null);
				smallcard[i].drawBitmap(canvas, smallcard[i].bitmap, Location.GetCard.card_xy[i][0] * GameConfig.f_zoomx, Location.GetCard.card_xy[i][1] * GameConfig.f_zoomy, null);
			}
			
			int tempX=(int)(453 * GameConfig.f_zoomx);
			int tempY=(int)(76 * GameConfig.f_zoomy);
			ExternalMethods.drawImage(canvas, bitmap_close, tempX-(anjian_close?bitmap_close.getWidth()/10:0), tempY-(anjian_close?bitmap_close.getHeight()/10:0), anjian_close?1.2f:1f, anjian_close?1.2f:1f, 255, 0, 0, 0);
			
		}
		
	}

	public void run() {
		
//		if (System.currentTimeMillis() - starttime > testtime && testtime > 0) {
//			rainbow.state = UISprite.RAINBOW_STATE_TERRIFIED;
//			rainbow.actionName = 2;
//			starttime = System.currentTimeMillis();
//			
//			testtime = 0;
//		}
		if(chongzhi>0){
			chongzhi--;
			if(chongzhi<=0){
				chongzhi=-1;
				initdata2();
				initdata();
			}
		}
		if (isHideGetCard) {
			rainbowUpdata();
			tomatoUpdata();			
		}
		
		//音效缓存清理
		GameMedia.clearBuffer();
	}
	
	private void rainbowUpdata() {
		switch(rainbow.state) {
		case UISprite.RAINBOW_STATE_NOMAL:
			if (rainbow_wink_oldtime == 0 && rain_wink_two <= 0) {
				rain_wink_scaletime = (int) (2 + Math.abs(Math.random()*10%5));
				if (rain_wink_scaletime <=  3) {
					rain_wink_two = 2;
				}
			} else if (rain_wink_two > 0 || rainbow_wink_oldtime > rain_wink_scaletime * 33) {
				rainbow_wink_oldtime = -1;
				rainbow.changeAction(1);
				rainbow.state = UISprite.RAINBOW_STATE_WINK;
				rain_wink_two--;
			}
			rainbow_wink_oldtime++;
			break;
		case UISprite.RAINBOW_STATE_WINK:
			break;
		case UISprite.RAINBOW_STATE_TERRIFIED:
			if (System.currentTimeMillis() - starttime > 1000) {
				isOpen = true;
				rainbow.state = UISprite.RAINBOW_STATE_SMILE;
//				rainbow.actionName = 3;
				rainbow.changeAction(3);
				if(!VeggiesData.isMuteSound())
					   GameMedia.playSound(R.raw.boxs, 0);
				isShowAnt = true;
			}
			break;
		case UISprite.RAINBOW_STATE_SMILE:
			if (card_frame < 4 && GameConfig.i_coke % 3 >= 2) {
				card_frame++;
				
				starttime = System.currentTimeMillis();//最后大图的时间
			}
			if (card_frame >= 4) {
				if ( GameConfig.i_coke % 2 >= 1) {	
					card_frame++;
					if (card_frame == 7) card_frame -= 3;
					
					rotate_angle += 60/3;
					if (rotate_angle == 60) rotate_angle = 0;
				}
				if (card_index == 0) ishideskip = false;
				if (System.currentTimeMillis() - starttime > 3000) {
					if (card_index+1 >= getcard.length) {
						//TODO 跳转到get界面
						//return;
//						GameManager.ChangeModule(null);
//						GameManager.forbidModule(ChooseLevelModule.getCardModule);
						isHideGetCard = false;
						if (VeggiesData.getGameGuanka()[3] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL36]) {
							if(GameManager.getGT()==null)
								GameManager.setGT(new GameTeaching());
							int temp_x =  (int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx)+((int)(152 * GameConfig.f_zoomx)>>1);
							int temp_y = (int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy +20 * GameConfig.f_zoomy);
							GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL36, temp_x, temp_y, LangUtil.getLangString(LangDefineClient.GUIDE_36), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
						}
					} else {
						
						card_index++;
						reGetNewCard();
						GameImage.delImage(card.bitmap);
						card = null;
						GameImage.showImageHashMap();
						if (getcard[card_index]  < 10) {
							card = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (getcard[card_index])));			
						} else {
							card = new Sprite(GameImage.getImage("bigcard/card_pc_" + (getcard[card_index])));	
						}
					}
				}
			}
			if (isUpdateCard) {
				updateCard();
				isUpdateCard = false;
			}
			break;
		}
		rainbow.updataSprite();
	}
	
	private void tomatoUpdata() {
		if (!isMoveTomato && move_Y >= maxMove - 20 * GameConfig.f_zoomy) {
//			tomato.actionName = 3;
			tomato.changeAction(3);
			tomato.state = UISprite.TOMATO_STATE_ROLL;
		}
		if (!isMoveTomato && move_Y > 0) {
			move_Y -= spring_speed;
			if (move_Y < 0) move_Y = 0;
		}
		switch(tomato.state) {
		case UISprite.TOMATO_STATE_NONE:
			break;
		case UISprite.TOMATO_STATE_NOMAL:
			break;
		case UISprite.TOMATO_STATE_ROLL:
			if (move_Y <= 0) {
				if (tomato_move_Y >= -Location.TakeCard.tomato_xy[1] * GameConfig.f_zoomy )
					tomato_move_Y -= tomato_speed;
			}
			if (Location.TakeCard.tomato_xy[1] * GameConfig.f_zoomy + tomato_move_Y <= Location.TakeCard.rainbow_xy[1] * GameConfig.f_zoomy) {
				rainbow.state = UISprite.RAINBOW_STATE_TERRIFIED;
//				rainbow.actionName = 2;
				rainbow.changeAction(2);
				starttime = System.currentTimeMillis();
				ishide = true;
				tomato.state = UISprite.TOMATO_STATE_NONE;
				
			}
			break;
		}
		//tomato.setCollisionRect(tomato.x, tomato.y+move_Y + tomato_move_Y, tomato.x+120*GameConfig.f_zoom, tomato.y+430*GameConfig.f_zoom/4+move_Y + tomato_move_Y);
		tomato.updataSprite();
	}

	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if (!isHideGetCard) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				GameManager.ChangeModule(null);
			}			
		}
		return false;
	}

	public void Release() {
//		for (int i=0; i<card.length; i++) {
//			GameImage.delImage(card[i].bitmap);
//			card[i] = null;
//		}
		//TODO
		
		GameImage.delImage(bitmap_close);
		bitmap_close = null;
		GameImage.delImage(bg.bitmap);
		bg = null;
		SpriteLibrary.DelSpriteImage(CoolEditDefine.NPC_RAINBOW);
		SpriteLibrary.DelSpriteImage(CoolEditDefine.NPC_TOMATO);
		GameImage.delImage(card.bitmap);
		card = null;
		for (int i=0; i<box.length; i++) {
			GameImage.delImage(box[i].bitmap);
			box[i] = null;
		}
		box = null;
		for (int i=0; i<crossbow.length; i++) {
			GameImage.delImage(crossbow[i].bitmap);
			crossbow[i] = null;
		}
//		GameImage.delImage(shine.bitmap);
//		shine = null;
		for (int i=0; i<skip.length; i++) {
			GameImage.delImage(skip[i].bitmap);
			skip[i] = null;
		}
		skip = null;
		
		
		
		//-----------------------
		GameImage.delImage(backbg1.bitmap);
		backbg1 = null;
		GameImage.delImage(backbg2.bitmap);
		backbg2 = null;
		GameImage.delImage(backbg3.bitmap);
		backbg3 = null;
		GameImage.delImage(title_get.bitmap);
		title_get = null;
		GameImage.delImage(button[0].bitmap);
		GameImage.delImage(button[1].bitmap);
		button = null;
		GameImage.delImageArray(s_word_num_04);
		s_word_num_04 = null;
		GameImage.delImage(button_share_text.bitmap);
		button_share_text = null;
		GameImage.delImage(button_ok_text.bitmap);
		button_ok_text = null;
		for (int i=0; i<shopitem_priceicon.length; i++) {
			GameImage.delImage(shopitem_priceicon[i].bitmap);
			shopitem_priceicon[i].bitmap = null;
		}
		shopitem_priceicon = null;
		GameImage.delImage(share_ui_button_08.bitmap);
		share_ui_button_08 = null;
		GameImage.delImage(share_icon_facebook_05.bitmap);
		share_icon_facebook_05 = null;
		for (int i=0; i<smallcard.length; i++) {
			GameImage.delImage(smallcard[i].bitmap);
			smallcard[i].bitmap = null;
		}
		smallcard = null;
		for (int i=0; i<smallcardbg.length; i++) {
			GameImage.delImage(smallcardbg[i].bitmap);
			smallcardbg[i].bitmap = null;
		}
		smallcardbg = null;
		if(!VeggiesData.isMuteMusic()){
			GameMedia.playMusic(R.raw.loginl, true, true);
		}
		GameManager.setGT(null);
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){  //新手教学
			if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL36){
					if (ExternalMethods.CollisionTest(x, y, 
							(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
							(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
							(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoomx * 1.2f), 
							(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + share_ui_button_08.bitmap.getHeight()))) {
						anjianshare = true;
					}
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL37){
					int tempX=(int)(453 * GameConfig.f_zoomx);
					int tempY=(int)(76 * GameConfig.f_zoomy);
					if(ExternalMethods.CollisionTest(x, y,tempX,tempY,tempX+bitmap_close.getWidth(),tempY+bitmap_close.getHeight())){
						anjian_close=true;
					}
				}
				
			}else if(event.getActionMasked() == MotionEvent.ACTION_UP){
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL36){
					if (anjianshare && ExternalMethods.CollisionTest(x, y, 
							(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
							(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
							(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoomx * 1.2f), 
							(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + share_ui_button_08.bitmap.getHeight()))) {
						anjianshare = false;
						if(FacebookOperation.isLanding  && UserRequest.getUser().getLoginok()){
								FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_PULISH);
						}
						GameManager.getGT().finish();
						new VeggiesData().saveGame();
						
						int tempX=(int)(453 * GameConfig.f_zoomx)+bitmap_close.getWidth()/2;
						int tempY=(int)(76 * GameConfig.f_zoomy)+bitmap_close.getHeight()/2;
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL37, tempX, tempY, LangUtil.getLangString(LangDefineClient.GUIDE_37), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
						
					}
					return;
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL37){
					int tempX=(int)(453 * GameConfig.f_zoomx);
					int tempY=(int)(76 * GameConfig.f_zoomy);
					if(anjian_close&&ExternalMethods.CollisionTest(x, y,tempX,tempY,tempX+bitmap_close.getWidth(),tempY+bitmap_close.getHeight())){
						GameManager.getGT().finish();
						new VeggiesData().saveGame();
						GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
					}
					anjian_close=false;
					return;
				}
				
			}
			
		}
		if (isHideGetCard) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (tomato.state == UISprite.TOMATO_STATE_NOMAL &&ExternalMethods.CollisionTest(x, y, 
						Location.TakeCard.crossbow[0][0] * GameConfig.f_zoomx, 
						Location.TakeCard.crossbow[0][1] * GameConfig.f_zoomy, 
						Location.TakeCard.crossbow[0][0] * GameConfig.f_zoomx + crossbow[0].bitmap.getWidth(), 
						Location.TakeCard.crossbow[0][1] * GameConfig.f_zoomy + crossbow[0].bitmap.getHeight())) {
					oldY = y;
					isMoveTomato = true;
					
//				tomato.actionName = 2;
					tomato.changeAction(2);
				} else if (!ishideskip && ExternalMethods.CollisionTest(x, y, 
						Location.TakeCard.skip_xy[0] * GameConfig.f_zoomx, Location.TakeCard.skip_xy[1] * GameConfig.f_zoomy, 
						(Location.TakeCard.skip_xy[0] + 20)* GameConfig.f_zoomx + skip[0].bitmap.getWidth(), Location.TakeCard.skip_xy[1] * GameConfig.f_zoomy + skip[0].bitmap.getHeight())) {
					anjianskip = true;
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				if (isMoveTomato) {
//				tomato.actionName = 1;
					tomato.changeAction(1);
					spring_speed = move_Y / 4;
					if (GameManager.getGT()!=null && GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL6) {
						GameManager.getGT().finish();
						new VeggiesData().saveGame();
						GameManager.setGT(null);
					}
					
				}
				if (anjianskip && ExternalMethods.CollisionTest(x, y, 
						Location.TakeCard.skip_xy[0] * GameConfig.f_zoomx, Location.TakeCard.skip_xy[1] * GameConfig.f_zoomy, 
						(Location.TakeCard.skip_xy[0] + 20)* GameConfig.f_zoomx + skip[0].bitmap.getWidth(), Location.TakeCard.skip_xy[1] * GameConfig.f_zoomy + skip[0].bitmap.getHeight())) {
					//TODO 直接跳转至获取卡片界面
					
//				GameManager.ChangeModule(null);
//					GameManager.forbidModule(ChooseLevelModule.getCardModule);
					isHideGetCard = false;
					if (VeggiesData.getGameGuanka()[3] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL36]) {
						if(GameManager.getGT()==null)
							GameManager.setGT(new GameTeaching());
						int temp_x =  (int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx)+((int)(152 * GameConfig.f_zoomx)>>1);
						int temp_y = (int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy +20 * GameConfig.f_zoomy);
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL36, temp_x, temp_y, LangUtil.getLangString(LangDefineClient.GUIDE_36), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
					}
				} else if (rainbow.state == UISprite.RAINBOW_STATE_SMILE){//当不在拖动番茄的时候，在抽卡的时候
					
					if (card_index+1 < getcard.length && card_frame >= 3) {//当后面还有卡片的时候					
						if (card_index == 0) ishideskip = false;
						isUpdateCard = true;
					} else if (card_index+1 >= getcard.length && card_frame >= 3) {
						isHideGetCard = false;
						if (VeggiesData.getGameGuanka()[3] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL36]) {
							if(GameManager.getGT()==null)
								GameManager.setGT(new GameTeaching());
							int temp_x =  (int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx)+((int)(152 * GameConfig.f_zoomx)>>1);
							int temp_y = (int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy +20 * GameConfig.f_zoomy);
							GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL36, temp_x, temp_y, LangUtil.getLangString(LangDefineClient.GUIDE_36), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
						}
					}
				}
				anjianskip = false;
				isMoveTomato = false;
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				if (isMoveTomato) {
					int tempY = (int) event.getY();
					
					move_Y+= (tempY-oldY);
					
					oldY = (int) event.getY();
					
					if (move_Y < 0) {
						move_Y = 0;
					}
					if (move_Y > maxMove) {
						move_Y = maxMove;
					}
				}
			}
			
		} else {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
//				if (ExternalMethods.CollisionTest(x, y, 
//						453 * GameConfig.f_zoom - close.bitmap.getWidth()/2*0.2f, 
//						76 * GameConfig.f_zoom - close.bitmap.getHeight()/2*0.2f,
//					453 * GameConfig.f_zoom + close.bitmap.getWidth()*1.2f, 
//					4476 * GameConfig.f_zoom + close.bitmap.getHeight()*1.2f)) {
//					anjianclose = true;
//				} else 
				if (ExternalMethods.CollisionTest(x, y, 
						(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
						(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
						(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoomx * 1.2f), 
						(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + share_ui_button_08.bitmap.getHeight()))) {
					anjianshare = true;
				} else if (ExternalMethods.CollisionTest(x, y, 
						(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
						(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
						(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoomx * 1.2f), 
						(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + share_ui_button_08.bitmap.getHeight()))) {
					anjianok = true;
				}//关闭按钮
				int tempX=(int)(453 * GameConfig.f_zoomx);
				int tempY=(int)(76 * GameConfig.f_zoomy);
				if(ExternalMethods.CollisionTest(x, y,tempX,tempY,tempX+bitmap_close.getWidth(),tempY+bitmap_close.getHeight())){
					anjian_close=true;
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
//				if (ExternalMethods.CollisionTest(x, y, 
//						453 * GameConfig.f_zoom - close.bitmap.getWidth()/2*0.2f, 
//						44 * GameConfig.f_zoom - close.bitmap.getHeight()/2*0.2f,
//					453 * GameConfig.f_zoom + close.bitmap.getWidth()*1.2f, 
//					44 * GameConfig.f_zoom + close.bitmap.getHeight()*1.2f)) {
//					GameManager.ChangeModule(null);
////					GameShop.isHideShop = false;
//				} else 
				if (anjianshare && ExternalMethods.CollisionTest(x, y, 
						(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
						(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
						(int)(Location.GetCard.share_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoomx * 1.2f), 
						(int)(Location.GetCard.share_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + share_ui_button_08.bitmap.getHeight()))) {
					//TODO share
					if(FacebookOperation.isLanding  && UserRequest.getUser().getLoginok()){
						FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_PULISH);
					}
				} else if (anjianok && ExternalMethods.CollisionTest(x, y, 
						(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2)), 
						(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2)), 
						(int)(Location.GetCard.ok_xy[0] * GameConfig.f_zoomx + (button[0].bitmap.getWidth()/2 - button[1].bitmap.getWidth()/2) + 152 * GameConfig.f_zoomx * 1.2f), 
						(int)(Location.GetCard.ok_xy[1] * GameConfig.f_zoomy + (button[0].bitmap.getHeight()/2 - button[1].bitmap.getHeight()/2) + share_ui_button_08.bitmap.getHeight()))) {
					//TODO ok
//					GameManager.ChangeModule(null);
//					GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
					if(GameShop1.isBuy(box_index)){
						chongzhi=5; 
					}
				}
				int tempX=(int)(453 * GameConfig.f_zoomx);
				int tempY=(int)(76 * GameConfig.f_zoomy);
				if(anjian_close&&ExternalMethods.CollisionTest(x, y,tempX,tempY,tempX+bitmap_close.getWidth(),tempY+bitmap_close.getHeight())){
					GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
				}
				anjian_close=false;
				anjianshare = false;
				anjianok = false;
			}
		}
	}
	
	private void tomato_init() {
		isMoveTomato = false;
		move_Y = 0;
		spring_speed = 0;
		tomato_move_Y = 0;
		ishide = false;
	}
	
	private void reGetNewCard() {
//		rainbow.state = UISprite.RAINBOW_STATE_TERRIFIED;
//		rainbow.actionName = 2;
//		starttime = System.currentTimeMillis();
//		ishide = true;
//		tomato.state = UISprite.TOMATO_STATE_NONE;
		
		card_frame = -1;
		rotate_angle = 0;
	}
	
	//画椭圆草地、背景色
	private void paintShadowColor(Canvas canvas, float x1, float y1, float x2, float y2, Paint p, boolean isOval) {
		RectF oval2 = new RectF(x1, y1, x2, y2);
        if (isOval)
        	canvas.drawOval(oval2, p);
        else
        	canvas.drawRect(oval2, p);
	}
	
	private void updateCard() {
		card_index++;
		reGetNewCard();
		GameImage.delImage(card.bitmap);
		card = null;
		GameImage.showImageHashMap();
		if (getcard[card_index]  < 10) {
			card = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (getcard[card_index])));			
		} else {
			card = new Sprite(GameImage.getImage("bigcard/card_pc_" + (getcard[card_index])));	
		}
	}
}
