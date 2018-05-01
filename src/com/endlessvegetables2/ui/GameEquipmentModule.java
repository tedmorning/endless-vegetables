package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.endlessvegetables2.turngame.TurnGameMain;
import com.facebook.FacebookOperation;
import com.facebook.FriendHelpTime;
import com.facebook.FriendIcon;
import com.facebook.UserRequest;
import com.facebook.FacebookOperation.setFriendIcon;
import com.facebook.sdk.FBInterface;
import com.game.data.FaceBookPlayer;
import com.game.data.FarmData1;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMain;
import com.kokatlaruruxi.wy.GameStory;
import com.kokatlaruruxi.wy.GameStory2;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.socoGameEngine.TextBox;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class GameEquipmentModule extends Module implements FriendIcon,FriendHelpTime{

	public static HashMap<String, FaceBookPlayer> friendHelpTime = new HashMap<String, FaceBookPlayer>();
	private int equipState;// 0包裹1好友2为登录
	private Sprite gem;
	public int CARDID[] = {1, 2, 3, 4};
	private final int PRICE = 100; //抽卡的价格
	private final int cardNumber = 2; //抽卡的数量
	public static boolean isFree; //是否免费抽卡
	// private Sprite backbg1;
	// private Sprite backbg2;
	// private Sprite backbg3;
	// private Sprite close;
	// private Sprite[] equipbg;
	// private Sprite level;
	// private Bitmap[] level_num;
	// private Sprite button1;
	// private Sprite button2;
	// private Sprite buttonbg;
	// private Sprite buttontext;
	// private Sprite[] noCard;
	// private Sprite friendCard;
	// private Sprite cardLock;
	// private Sprite unLock;
	// private Bitmap[] card_money_num;
	// private Sprite[] card;
	// private Sprite num_bg;
	// private Sprite cursor;
	// private Sprite gray;
	// private Sprite[] starbg;
	// private Sprite star;
	// private Sprite[] arrow;
	// private Sprite ask_friend_title;
	// private Sprite facebook;
	// private Sprite systemfriend_icon;
	// private Sprite defaulu_photo;
	// private Sprite defaulu_photo_clip;
	// private Bitmap[] CD_num;

	private TextBox loginfacebook;

	private List<CardItem> _card;
	private List<CardItem> card_header;
	private List<CardItem> card_partner;
	private List<CardItem> card_str;
	private List<CardItem> card_items;
	
//	private List<CardItem> card_header2;
//	private List<CardItem> card_partner2;
//	private List<CardItem> card_str2;
//	private List<CardItem> card_items2;
	
	private List<AskFriend> _friends;
	private List<AskFriend> friends;
	private Sprite[] loading;
	private Sprite ui_egg[];
	private Sprite shop_gold_06_1;
	private Sprite key_free;
	
	private Sprite share_ui_photo_04;
	private Sprite share_ui_photo_05;
	int index_egg[] = {0, 1, 2};
	int tempi_egg = 0;
	boolean isegg = false;
	int tempi = 0;
	int friendIconId;
//	int index = 0;

	// private AskFriend _friends;
	private int cursor_index;
	private int starLevel_index;
	private Paint paint;
	private Typeface fontFace;
	private boolean anjianbutton;
	private boolean[] anjian_equip = { false, false, false, false, false,
			false, false, false };

	private boolean[] anjianstar = { false, false, false };
	private boolean anjianclose;
	private boolean anjiancard;
	private boolean ismove;
	private boolean anjianfacebook;
	private boolean longtouchcard;
	private boolean anjiansystemfriend;
	private boolean anjianfriend;
	private int askfriend;// 0为系统 1+ 是好友
	private long systemCD;
	private long CD = 1000*60*1;// 1800000
	private String systemTime;
	private long oldtime, currenttime;
	private int _card_cardId;
	private int _card_index;
	
	private float _card_x,_card_y,_card_w,_card_h,_card_s;
	private float _friend_x,_friend_y,_friend_w,_friend_h,_friend_s,_friend_sc;//_friend_s 间隔距离  _friend_sc单张图片的间隔距离
	private float _x,_y;

	float move_X;
	public int oldX;
	public int sort_index; // 0主1副2奖3道
	boolean isCorrectCardMove;
	boolean isCorrectFriendMove;
	float correctCard_move = 5 * GameConfig.f_zoomx;
	float correctFriend_move = 5 * GameConfig.f_zoomx;

	static int nexde = 0;
	static boolean isGotoGame = false;
	
	public static HashMap<Integer,Sprite> s_smallcard_card;
	public static List<Integer> smallcard_load_sort;
	private int deblockingid; //解锁的id
	private int deblocking_x;
	private int deblocking_y;
	private int deblockingIndex;
	private float r = 0f;
	public static long systemFriendTime;
	public void ReleaseResource() {
		// GameImage.delImage(backbg1.bitmap);
		// backbg1 = null;
		// GameImage.delImage(backbg2.bitmap);
		// backbg2 = null;
		// GameImage.delImage(backbg3.bitmap);
		// backbg3 = null;
		// GameImage.delImage(close.bitmap);
		// close = null;
		// for (int i=0; i<equipbg.length; i++) {
		// GameImage.delImage(equipbg[i].bitmap);
		// equipbg[i].bitmap = null;
		// }
		// equipbg = null;
		// GameImage.delImage(level.bitmap);
		// level = null;
		// GameImage.delImageArray(level_num);
		// level_num = null;
		// GameImage.delImage(button1.bitmap);
		// button1 = null;
		// GameImage.delImage(button2.bitmap);
		// button2 = null;
		// GameImage.delImage(buttonbg.bitmap);
		// buttonbg = null;
		// GameImage.delImage(buttontext.bitmap);
		// buttontext = null;
		// for (int i=0; i<noCard.length; i++) {
		// GameImage.delImage(noCard[i].bitmap);
		// noCard[i].bitmap = null;
		// }
		// noCard = null;
		// GameImage.delImage(friendCard.bitmap);
		// friendCard = null;
		// GameImage.delImage(cardLock.bitmap);
		// cardLock = null;
		// GameImage.delImage(unLock.bitmap);
		// unLock = null;
		// GameImage.delImageArray(card_money_num);
		// card_money_num = null;
		// for (int i=0; i<card.length; i++) {
		// GameImage.delImage(card[i].bitmap);
		// card[i].bitmap = null;
		// }
		// card = null;
		// GameImage.delImage(num_bg.bitmap);
		// num_bg = null;
		// GameImage.delImage(cursor.bitmap);
		// cursor = null;
		// GameImage.delImage(gray.bitmap);
		// gray = null;
		// for (int i=0; i<starbg.length; i++) {
		// GameImage.delImage(starbg[i].bitmap);
		// starbg[i].bitmap = null;
		// }
		// starbg = null;
		// for (int i=0; i<arrow.length; i++) {
		// GameImage.delImage(arrow[i].bitmap);
		// arrow[i].bitmap = null;
		// }
		// arrow = null;
		// GameImage.delImage(ask_friend_title.bitmap);
		// ask_friend_title = null;
		// GameImage.delImage(facebook.bitmap);
		// facebook = null;
		// GameImage.delImage(systemfriend_icon.bitmap);
		// systemfriend_icon = null;
		// GameImage.delImage(defaulu_photo.bitmap);
		// defaulu_photo = null;
		// GameImage.delImage(defaulu_photo_clip.bitmap);
		// defaulu_photo_clip = null;
		// GameImage.delImageArray(CD_num);
		// CD_num = null;
	}

	public GameEquipmentModule() {

	}

	public boolean initialize() {
		gem = new Sprite();
		if(VeggiesData.getCardNum(VeggiesData.getCardSlot()[0])<=0){
			VeggiesData.setCardSlot(0, GameItem.Item01);
		}
		
		friendIconId = -1;
		deblockingid = -1;
		r = 1.2f;
		deblockingIndex = 0;
//		index = 0;
		// backbg1 = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
		// backbg2 = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
		// backbg3 = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
		// close = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
		// equipbg = new Sprite[6];
		// for (int i=0; i<equipbg.length; i++) {
		// equipbg[i] = new
		// Sprite(GameImage.getImage(GameStaticImage.interface_ui_line[i]));
		// }
		// level = new
		// Sprite(GameImage.getImage(GameStaticImage.word_title_level));
		// level_num =
		// GameImage.getAutoSizecutBitmap(GameStaticImage.word_num_06, 10, 1,
		// GameImage.Sort_line);
		// card_money_num =
		// GameImage.getAutoSizecutBitmap(GameStaticImage.word_num_05, 10, 1,
		// GameImage.Sort_line);

		// button1 = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05_1));
		// button2 = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05_2));
		// buttonbg = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05));
		// buttontext = new
		// Sprite(GameImage.getImage(GameStaticImage.word_play));

		// noCard = new Sprite[2];
		// noCard[0] = new
		// Sprite(GameImage.getImage(GameStaticImage.smallcard_card_s_01));
		// noCard[1] = new
		// Sprite(GameImage.getImage(GameStaticImage.smallcard_card_s_02));
		// for (int i=0; i<noCard.length; i++) {
		// noCard[i] = new Sprite(GameImage.getImage("smallcard/card_s_0" +
		// (i+1)));
		// }
		// friendCard = new
		// Sprite(GameImage.getImage(GameStaticImage.smallcard_card_friend));
		// cardLock = new
		// Sprite(GameImage.getImage(GameStaticImage.smallcard_card_lock));
		// unLock = new
		// Sprite(GameImage.getImage(GameStaticImage.interface_card_unlock));
		// card = new Sprite[63];
		// for (int i=0; i<card.length; i++) {
		// if (i < 9) {
		// card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_0" + (i+1)
		// + "_s"));
		// } else {
		// card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_" + (i+1)
		// + "_s"));
		// }
		// }
		// cursor = new
		// Sprite(GameImage.getImage(GameStaticImage.smallcard_card_s_cover));
		// gray = new
		// Sprite(GameImage.getImage(GameStaticImage.smallcard_card_gray));

		// num_bg = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_back_07));

		// starbg = new Sprite[3];
		// starbg[0] = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_back_04));
		// starbg[1] = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05));
		// starbg[2] = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05_1));
		// star = new
		// Sprite(GameImage.getImage(GameStaticImage.interface_star_15));

		// arrow = new Sprite[2];
		// arrow[0] = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_01));
		// arrow[1] = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_02));
		// for(int i=0; i<arrow.length; i++) {
		// arrow[i] = new Sprite(GameImage.getImage("share/ui_arrows_0" +
		// (i+1)));
		// }

		// ask_friend_title = new
		// Sprite(GameImage.getImage(GameStaticImage.word_ask));
		// facebook = new
		// Sprite(GameImage.getImage(GameStaticImage.interface_icon_facebook_02));
		// systemfriend_icon = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_photo_02));
		anjianbutton = false;
		loginfacebook = new TextBox();
		loginfacebook.setTextAlign(TextBox.LEFT);
		loginfacebook
				.setString(GameWord.pleaseLoginFaceBook[GameWord.useLanguage]);
		loginfacebook.setBoxSize((int) (200 * GameConfig.f_zoomx),
				(int) (1000 * GameConfig.f_zoomy));
		loginfacebook.setTextSize((int) (22 * GameConfig.f_zoom));
		loginfacebook.setDefaultColor(0xffBD6D18);
		loginfacebook.height();
		loginfacebook.setBoxSize((int) (200 * GameConfig.f_zoomx),
				(int) loginfacebook.height());

		// defaulu_photo = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_photo_01));
		// defaulu_photo_clip = new
		// Sprite(GameImage.getImage(GameStaticImage.share_ui_photo_03));
		// CD_num = GameImage.getAutoSizecutBitmap(GameStaticImage.word_num_07,
		// 11, 1, GameImage.Sort_line);

		// --------------------------------------
		// test
		systemTime = "";
		askfriend = -1;
		// systemtime = System.currentTimeMillis();

		equipState = 0;

		paint = new Paint();
		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(),
				"font/ARLRDBD.TTF");
		paint = new Paint();
		paint.setTextSize(20 * GameConfig.f_zoom);
		paint.setColor(0xffBD6D18);
		paint.setTypeface(fontFace);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);

		if (_card == null)
			_card = new ArrayList<CardItem>();
		// _card.add(new CardItem(-1, GameItem.Item01, 0));
		// _card.add(new CardItem(1, GameItem.Item03));
		// _card.add(new CardItem(2, GameItem.Item04));
		// _card.add(new CardItem(3, GameItem.Item05));
		// _card.add(new CardItem(4, GameItem.Item06));

		card_header = new ArrayList<CardItem>();
//		card_header2 = new ArrayList<CardItem>();
		for (int i = 0; i < GameItem.cardSort[0].length; i++) {
//			if(VeggiesData.getCardNum(GameItem.cardSort[0][i])>0)
			card_header.add(new CardItem(i / 3 + 1, GameItem.cardSort[0][i]));
			
		}
		card_partner = new ArrayList<CardItem>();
//		card_partner2 = new ArrayList<CardItem>();
		for (int i = 0; i < GameItem.cardSort[1].length; i++) {
//			if(VeggiesData.getCardNum(GameItem.cardSort[1][i])>0)
				card_partner.add(new CardItem(i / 3 + 1, GameItem.cardSort[1][i]));
		}
		card_str = new ArrayList<CardItem>();
//		card_str2 = new ArrayList<CardItem>();
		for (int i = 0; i < GameItem.cardSort[2].length; i++) {
//			if(VeggiesData.getCardNum(GameItem.cardSort[2][i])>0)
				card_str.add(new CardItem(i / 3 + 1, GameItem.cardSort[2][i]));
			
		}
		card_items = new ArrayList<CardItem>();
//		card_items2 = new ArrayList<CardItem>();
		for (int i = 0; i < GameItem.cardSort[3].length; i++) {
//			if(VeggiesData.getCardNum(GameItem.cardSort[3][i])>0)
				card_items.add(new CardItem(i / 3 + 1, GameItem.cardSort[3][i]));
			
		}

		move_X = 0;
		sort_index = 0;
		starLevel_index = 0;
		replaceCard(starLevel_index, sort_index);
		isCorrectCardMove = false;
		isCorrectFriendMove = false;
		
//		_card_w = (493 - 28) * GameConfig.f_zoomx - GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth();
//		_card_h = GameStaticImage.noCard[0].bitmap.getHeight();
//		_card_s = (_card_w - GameStaticImage.noCard[0].bitmap.getWidth() * 4) / 4;
//		_card_x = 28 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth() + _card_s / 2;
//		_card_y = GameConfig.GameScreen_Height - GameStaticImage.noCard[0].bitmap.getHeight() - 32 * GameConfig.f_zoomy;
		_card_w = (493 - 28) * GameConfig.f_zoomx - GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth();
		_card_h = GameStaticImage.noCard[0].bitmap.getHeight();
		_card_s = (_card_w - GameStaticImage.noCard[0].bitmap.getWidth() * 4) / 4;
		_card_x = 28 * GameConfig.f_zoomx
				+ GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth()
				+ _card_s / 2;
		_card_y = GameConfig.GameScreen_Height
				- GameStaticImage.noCard[0].bitmap.getHeight() - 32
				* GameConfig.f_zoomy;
		
		if (loading == null)
			loading = GameImage
					.getAutoSizecutSprite(GameStaticImage.share_loading_03, 9,
							1, GameImage.Sort_line);
		if (ui_egg == null)
			ui_egg = GameImage
					.getAutoSizecutSprite(GameStaticImage.share_ui_egg, 3,
							1, GameImage.Sort_line);
		if (shop_gold_06_1 == null)
			shop_gold_06_1 = new Sprite(
					GameImage.getImage(GameStaticImage.shop_gold_06_1));
		
		if(isFree){
			Bitmap temp = GameImage.getImage(GameStaticImage.word_key_free);
			temp = GameImage.zoomImage(temp, temp.getWidth()*0.7f, temp.getHeight()*0.7f);
			key_free = new Sprite(temp);
		}
		
		tempi = 0;
		if (share_ui_photo_04 == null)
			share_ui_photo_04 = new Sprite(
					GameImage.getImage(GameStaticImage.share_ui_photo_04));
		if (share_ui_photo_05 == null)
			share_ui_photo_05 = new Sprite(
					GameImage.getImage(GameStaticImage.share_ui_photo_05));
	 
		
		FacebookOperation.getFacebook().setFriendIcon(this);
		FacebookOperation.getFacebook().setFriendHelpTime(this);
		
		UserRequest.getUser().setFriendIconID("");
		if (FacebookOperation.isLanding
				&& FacebookOperation.getFacebook().getLoadingFriend()) {
			initFriend();
		} else {
			FacebookOperation.getFacebook().setFriendInt(new setFriendIcon() {

				@Override
				public void onSetFriend() {
					// TODO Auto-generated method stub
					initFriend();
					equipState = 1; // 显示好友
				}
			});
		}

		isGotoGame = false;
		
		_card_index = -1;
		cursor_index = 0;
		
//		for(int i=0; i<smallcard_load_sort.length; i++) {
//			if(i < 7) {
//				smallcard_load_sort[i] = VeggiesData.getCardSlot()[i];
//			}
//		}
		
//		if(VeggiesData.getCurrentLevel()==1 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL7]){
//			ChooseLevelModule2.gt.setGameTeaching((int)GameTeaching.TEACH_VOL7, (int)((348) * GameConfig.f_zoomx), (int)(353), LangUtil.getLangString(LangDefineClient.GUIDE_7), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);			
//		}
	 
		if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL28]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
//			(int)(514 * GameConfig.f_zoomy) //原始坐标
			int y = GameConfig.GameScreen_Height-(GameManager.getGT().getteachBg()*2)+GameManager.getGT().getteachBg()/2+(int)(20*GameConfig.f_zoomy);
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL28, (int)((270) * GameConfig.f_zoomx), (int)(514 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_28), GameTeaching.HAND_MOVE_STATE_1, y);
		}
		else if (VeggiesData.getGameGuanka()[1] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL7]) {
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			deblockingid = 1;  //解锁
		}else if(VeggiesData.getCurrentLevel() == 4 &&VeggiesData.getGameGuanka()[4] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL9]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			deblockingid = 5;
			r = 1.2f;
			deblockingIndex = 0;
		}
		else if(VeggiesData.getCurrentLevel() == 2 && VeggiesData.getGameGuanka()[2] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL16]){
			if(GameManager.getGT()==null)
				GameManager.setGT(new GameTeaching());
			int y = GameConfig.GameScreen_Height-(GameManager.getGT().getteachBg()*2)+GameManager.getGT().getteachBg()/2+(int)(20*GameConfig.f_zoomy);
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL16, (int)((412) * GameConfig.f_zoomx), (int)(372 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_16), GameTeaching.HAND_MOVE_STATE_1, y);
		}
		
		for(int i=0;i<VeggiesData.getCardSlot().length;++i){
			int cardID = VeggiesData.getCardSlot()[i];
			if(cardID!=-1 && cardID!=0){ //-1未解锁  0 没有卡片
				int number = VeggiesData.getCardNum(cardID);
				if(number<=0){
					VeggiesData.setCardSlot(i, 0);
				}
			}
		}
		return false;
	}

	private void initFriend() {
		// 点击的关卡
		final int level = VeggiesData.getCurrentLevel();
		nexde = 0;

		final int width = share_ui_photo_04.bitmap.getWidth();
		final int height = share_ui_photo_04.bitmap.getHeight();
		
		final List<AskFriend> f_type = new ArrayList<AskFriend>();
	
		
		
		 
		Bitmap temp = GameImage.zoomImage(GameStaticImage.s_share_ui_photo_02.bitmap, width - 6 * GameConfig.f_zoomx, height - 6
				* GameConfig.f_zoomy);
		//加入系统的
		AskFriend friend = new AskFriend(-100, "-100", nexde + 1, "系统好友",
				temp);
		friend.setendTime(systemFriendTime);
		f_type.add(friend);
		nexde++;
		
		Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
		while (iterator.hasNext()) {
			FaceBookPlayer player = FacebookOperation.playerMap.get(iterator.next());
			if (!player.isHave(level)) { // 没有需要请求
				UserRequest.getUser().ReqLeveInfo(level, player.getid_server());
				player.setUserlevel(new FaceBookPlayer.UserLevel() {

					@Override
					public void onLevelRes(FaceBookPlayer player, int level, long score,
							int star) {
						// TODO Auto-generated method stub
						player.addLG(level, score, star);
						
						// 图片缩放
						Bitmap temp = FBInterface.allIconMap.get(player.getId_facebook());//user.getIcon();
						if(temp!=null)
							temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx, height - 6
								* GameConfig.f_zoomy);
						AskFriend friend = new AskFriend(player.getid_server(), player.getId_facebook(), nexde + 1, player.getName(),
								temp);
						long time = player.getFriendHelpTime();
						if(time == FaceBookPlayer.L_NULL){
							UserRequest.getUser().ReqFriendTime(player.getid_server(), UserRequest.GETOLD_AID_TIME);
							time  = 18000*30;
						}
						friend.setendTime(time);
						f_type.add(friend);

						nexde++;

						info(f_type);
					}
				});
			} else {
			 
				// 图片缩放
				temp =FBInterface.allIconMap.get(player.getId_facebook());
				if(temp!=null)
					temp = GameImage.zoomImage(temp,
							width - 6 * GameConfig.f_zoomx, height - 6
									* GameConfig.f_zoomy);
				friend = new AskFriend(player.getid_server(), player.getId_facebook(), nexde + 1, player.getName(), temp);
				long time = player.getFriendHelpTime();
				if(time == FaceBookPlayer.L_NULL){
					UserRequest.getUser().ReqFriendTime(player.getid_server(), UserRequest.GETOLD_AID_TIME);
//					time  = System.currentTimeMillis()+18000;
					time = 0;
				}
				friend.setendTime(time);
				f_type.add(friend);
				nexde++;
			}
		}

		info(f_type);

		_friend_w = (493 - 28) * GameConfig.f_zoomx
				- GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth();
		_friend_h = 100 * GameConfig.f_zoomy;
		_friend_sc = 90 * GameConfig.f_zoomx;
		_friend_s = (_friend_w - _friend_sc * 4) / 4;
		_friend_x = 28 * GameConfig.f_zoomx
				+ GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth()
				+ _friend_s / 2;
		_friend_y = GameConfig.GameScreen_Height - _friend_h - 32
				* GameConfig.f_zoomy;

	}

	private void info(List<AskFriend> f_type) {
		int size = FacebookOperation.playerMap.size();
		if ((nexde ) >= size) {
			friends = new ArrayList<AskFriend>(size);
			_friends = new ArrayList<AskFriend>(size);
			Object[] mInfo = f_type.toArray();
			_friends.add(new AskFriend(-1, "", -1, "", null));
			for (int i = 0; i < mInfo.length; i++) {
				AskFriend friend = (AskFriend) mInfo[i];
				friend.index = i+1;
				friends.add(friend);
				if (i <= 5) {
					_friends.add(friends.get(i));
				}
			}
		}
	}

	public void paint(Canvas canvas) {
		gem.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0,
				GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null,
				(int) (28 * GameConfig.f_zoomx),
				(int) (49 * GameConfig.f_zoomy),
				(int) (476 * GameConfig.f_zoomx),
				(int) (510 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null,
				(int) (45 * GameConfig.f_zoomx),
				(int) (66 * GameConfig.f_zoomy),
				(int) (442 * GameConfig.f_zoomx),
				(int) (402 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null,
				(int) (45 * GameConfig.f_zoomx),
				(int) (66 * GameConfig.f_zoomy),
				(int) (442 * GameConfig.f_zoomx),
				(int) (402 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas,
				453 * GameConfig.f_zoomx
						- GameStaticImage.s_share_ui_close.bitmap.getWidth()
						/ 2 * (anjianclose ? 0.2f : 0f), 44
						* GameConfig.f_zoomy
						- GameStaticImage.s_share_ui_close.bitmap.getHeight()
						/ 2 * (anjianclose ? 0.2f : 0f), anjianclose ? 1.2f
						: 1f, anjianclose ? 1.2f : 1f, 255, 0, 0, 0);

		for (int i = 0; i < GameStaticImage.s_interface_ui_line.length; i++) {
			GameStaticImage.s_interface_ui_line[i].drawBitmap(canvas,
					GameStaticImage.s_interface_ui_line[i].bitmap,
					Location.GameEquip.equipbgXY[i][0] * GameConfig.f_zoomx,
					Location.GameEquip.equipbgXY[i][1] * GameConfig.f_zoomy,
					null);
		}

		GameStaticImage.s_word_title_level.drawBitmap(canvas,
				GameStaticImage.s_word_title_level.bitmap,
				200 * GameConfig.f_zoomx, 87 * GameConfig.f_zoomy, null);
		if (VeggiesData.getCurrentLevel() >= 10) {
			GameStaticImage.s_word_num_06[0]
					.drawBitmap(canvas, GameStaticImage.s_word_num_06,
							(int) (306 * GameConfig.f_zoomx),
							(int) (88 * GameConfig.f_zoomy),
							GameConfig.Char_num1,
							Integer.toString(VeggiesData.getCurrentLevel()+1),
							null, 0, 1);
		} else {
			GameStaticImage.s_word_num_06[0]
					.drawBitmap(canvas, GameStaticImage.s_word_num_06,
							(int) (316 * GameConfig.f_zoomx),
							(int) (88 * GameConfig.f_zoomy),
							GameConfig.Char_num1,
							Integer.toString(VeggiesData.getCurrentLevel()+1),
							null, 0, 1);
		}
		paint.setTextSize(20 * GameConfig.f_zoom);
		paint.setColor(0xffBD6D18);
		for (int i = 0; i < GameWord.GameEquip[GameWord.useLanguage].length; i++) {
			canvas.drawText(GameWord.GameEquip[GameWord.useLanguage][i],
					Location.GameEquip.equip_xy[i][0] * GameConfig.f_zoomx,
					Location.GameEquip.equip_xy[i][1] * GameConfig.f_zoomy,
					paint);
		}

		GameStaticImage.s_share_ui_button_05.drawBitmap(canvas,
				55 * GameConfig.f_zoomx, (477) * GameConfig.f_zoomy, 1f, 1f,
				255, 0, 0, 0);
		if (anjianbutton) {
			GameStaticImage.s_share_ui_button_05_2.drawBitmap(canvas,
					GameStaticImage.s_share_ui_button_05_2.bitmap,
					55 * GameConfig.f_zoomx, (477) * GameConfig.f_zoomy, null);
		} else {
			GameStaticImage.s_share_ui_button_05_1.drawBitmap(canvas,
					GameStaticImage.s_share_ui_button_05_1.bitmap,
					55 * GameConfig.f_zoomx, (477) * GameConfig.f_zoomy, null);
		}
		GameStaticImage.s_word_play.drawBitmap(canvas,
				GameStaticImage.s_word_play.bitmap, 229 * GameConfig.f_zoomx,
				(497) * GameConfig.f_zoomy, null);

		paintEquip(canvas);

		paintDown(canvas);
		
//		if (!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL7]) {
//			ChooseLevelModule2.gt.paint(canvas);
//		}
		
		float act_y = 55*GameConfig.f_zoomy ;
		ui_egg[index_egg[tempi_egg]].drawBitmap(canvas,
				53*GameConfig.f_zoomx
						- ui_egg[index_egg[tempi_egg]].bitmap.getWidth()
						/ 2 * (isegg ? 0.2f : 0f), act_y- ui_egg[index_egg[tempi_egg]].bitmap.getHeight() / 2 * (isegg ? 0.2f : 0f), isegg ? 1.2f
						: 1f, isegg ? 1.2f : 1f, 255, 0, 0, 0);
		
		if(!isFree){
			float geticon_x = 82*GameConfig.f_zoomx - shop_gold_06_1.bitmap.getWidth() / 2 * (isegg ? 0.2f : 0f);
			float geticon_y = act_y+ui_egg[index_egg[tempi_egg]].bitmap.getHeight()-shop_gold_06_1.bitmap.getHeight()-(int)(4*GameConfig.f_zoomy) - shop_gold_06_1.bitmap.getHeight() / 2 * (isegg ? 1.2f : 0f)
			+(isegg ? 15*GameConfig.f_zoomy : 0);
			shop_gold_06_1.drawBitmap(canvas, geticon_x, geticon_y, isegg ? 1.2f : 1f, isegg ? 1.2f : 1f, 255, 0, 0, 0);
			GameStaticImage.s_word_num_05[0].drawBitmap(
				canvas,
				GameStaticImage.s_word_num_05,
				(int) (geticon_x+shop_gold_06_1.bitmap.getWidth()),
				(int) (geticon_y+(shop_gold_06_1.bitmap.getHeight() - GameStaticImage.s_word_num_05[0].bitmap.getHeight()>>1)),
				GameConfig.Char_num1, ""+PRICE, null, -1, isegg ? 1.2f : 1f);
		}else {
			key_free.drawBitmap(canvas,
					53*GameConfig.f_zoomx+(ui_egg[index_egg[tempi_egg]].bitmap.getWidth() - key_free.bitmap.getWidth()>>1)
							- key_free.bitmap.getWidth()
							/ 2 * (isegg ? 0.2f : 0f) , 
							act_y+ui_egg[index_egg[tempi_egg]].bitmap.getHeight() - key_free.bitmap.getHeight() -(int)(5*GameConfig.f_zoomy), isegg ? 1.2f
							: 1f, isegg ? 1.2f : 1f, 255, 0, 0, 0);
			
			
		}
	}

	// 绘制装备区域
	private void paintEquip(Canvas canvas) {
		for (int i = 0; i < VeggiesData.getCardSlot().length; i++) {
			if (VeggiesData.getCardSlot()[i] == -1) { //未解锁
//				if(i == 0 || i == 1 || i == 2 || i == 4 || i == 5){ //
					GameStaticImage.noCard[0].drawBitmap(canvas, //图片id 1是没星星 0 是有星星
							GameStaticImage.noCard[0].bitmap,
							Location.GameEquip.cardSlot_xy[i][0]
									* GameConfig.f_zoomx,
							Location.GameEquip.cardSlot_xy[i][1]
									* GameConfig.f_zoomy, null);
//				}else
//					GameStaticImage.noCard[1].drawBitmap(canvas, //图片id 1是没星星 0 是有星星
//							GameStaticImage.noCard[1].bitmap,
//							Location.GameEquip.cardSlot_xy[i][0]
//									* GameConfig.f_zoomx,
//							Location.GameEquip.cardSlot_xy[i][1]
//									* GameConfig.f_zoomy, null);
				//有解锁的了
				if(i == deblockingid){
					if (deblockingIndex !=4) {
						GameStaticImage.s_smallcard_card_lock.drawBitmap(canvas,
								GameStaticImage.s_smallcard_card_lock.bitmap,
								(Location.GameEquip.cardSlot_xy[i][0] + 22)
										* GameConfig.f_zoomx,
								(Location.GameEquip.cardSlot_xy[i][1] + 38)
										* GameConfig.f_zoomy, null);
						
						//播放动画了
						GameStaticImage.s_smallcard_card_lock_key.drawBitmap(canvas,
								GameStaticImage.s_smallcard_card_lock_key.bitmap,
								(Location.GameEquip.cardSlot_xy[i][0] + 22+18+deblocking_x)
						 				* GameConfig.f_zoomx,
								(Location.GameEquip.cardSlot_xy[i][1] + 38-5+deblocking_y)
										* GameConfig.f_zoomy, null);
					}else if(deblockingIndex == 4){ //解锁动画完了 该是？号图片了
						GameStaticImage.s_interface_card_unlock.drawBitmap(canvas, (Location.GameEquip.cardSlot_xy[i][0] + 22+11)
				 				* GameConfig.f_zoomx + GameStaticImage.s_interface_card_unlock.bitmap.getWidth()/2*(1-r), 
				 				(Location.GameEquip.cardSlot_xy[i][1] + 38+8)
							    * GameConfig.f_zoomy + GameStaticImage.s_interface_card_unlock.bitmap.getHeight()/2*(1-r), r, r, 255, 0, 0, 0);
					}
				}else{
					GameStaticImage.s_smallcard_card_lock.drawBitmap(canvas,
							GameStaticImage.s_smallcard_card_lock.bitmap,
							(Location.GameEquip.cardSlot_xy[i][0] + 22)
									* GameConfig.f_zoomx,
							(Location.GameEquip.cardSlot_xy[i][1] + 38)
									* GameConfig.f_zoomy, null);
				}
//				if(i == 3 || i == 6){
//					GameStaticImage.s_word_num_05[0]
//					  							.drawBitmap(
//					  									canvas,
//					  									GameStaticImage.s_word_num_05,
//					  									(int) ((Location.GameEquip.cardSlot_xy[i][0] + 31) * GameConfig.f_zoomx),
//					  									(int) ((Location.GameEquip.cardSlot_xy[i][1] + 8) * GameConfig.f_zoomy),
//					  									GameConfig.Char_num1,
//					  									Integer.toString(GameItem.equipPrice[i]),
//					  									null, -1, 1);
//				}
			} else if (VeggiesData.getCardSlot()[i] == 0) {//这个装备区域没有卡片
//				GameStaticImage.noCard[0].drawBitmap(canvas, GameStaticImage.noCard[0].bitmap, Location.GameEquip.cardSlot_xy[i][0] * GameConfig.f_zoomx, Location.GameEquip.cardSlot_xy[i][1] * GameConfig.f_zoomy, null);
				GameStaticImage.noCard[cursor_index == i ? 2 : 0].drawBitmap(canvas, GameStaticImage.noCard[cursor_index == i ? 2 : 0].bitmap, Location.GameEquip.cardSlot_xy[i][0] * GameConfig.f_zoomx, Location.GameEquip.cardSlot_xy[i][1] * GameConfig.f_zoomy, null);
			} else { //卡片的id
				GameStaticImage.noCard[(cursor_index == i ? 7 : 4) + ((VeggiesData.getCardSlot()[i]-1) % 3)].drawBitmap(canvas, GameStaticImage.noCard[(cursor_index == i ? 7 : 4) + ((VeggiesData.getCardSlot()[i]-1) % 3)].bitmap, Location.GameEquip.cardSlot_xy[i][0] * GameConfig.f_zoomx, Location.GameEquip.cardSlot_xy[i][1] * GameConfig.f_zoomy, null);
				if (s_smallcard_card.containsKey(VeggiesData.getCardSlot()[i]))
					s_smallcard_card.get(VeggiesData.getCardSlot()[i]).drawBitmap(canvas, s_smallcard_card.get(VeggiesData.getCardSlot()[i]).bitmap, Location.GameEquip.cardSlot_xy[i][0] * GameConfig.f_zoomx, Location.GameEquip.cardSlot_xy[i][1] * GameConfig.f_zoomy, null);

				if (i > 3) {
					GameStaticImage.s_share_ui_back_07.drawBitmap(canvas,
							GameStaticImage.s_share_ui_back_07.bitmap,
							(Location.GameEquip.cardSlot_xy[i][0] + 43)
									* GameConfig.f_zoomx,
							(Location.GameEquip.cardSlot_xy[i][1] + 86)
									* GameConfig.f_zoomy, null);
					paint.setTextSize(14 * GameConfig.f_zoom);
					paint.setColor(Color.WHITE);
					if (VeggiesData.getAllCardNum()[VeggiesData.getCardSlot()[i] - 1] > 99) {
						canvas.drawText(
								Integer.toString(VeggiesData.getAllCardNum()[VeggiesData
										.getCardSlot()[i] - 1]),
								(Location.GameEquip.cardSlot_xy[i][0] + 55 - 3)
										* GameConfig.f_zoomx,
								(Location.GameEquip.cardSlot_xy[i][1] + 106 - 3)
										* GameConfig.f_zoomy, paint);
					} else if (VeggiesData.getAllCardNum()[VeggiesData
							.getCardSlot()[i] - 1] > 9) {
						canvas.drawText(
								Integer.toString(VeggiesData.getAllCardNum()[VeggiesData
										.getCardSlot()[i] - 1]),
								(Location.GameEquip.cardSlot_xy[i][0] + 59 - 3)
										* GameConfig.f_zoomx,
								(Location.GameEquip.cardSlot_xy[i][1] + 106 - 3)
										* GameConfig.f_zoomy, paint);
					} else if (VeggiesData.getAllCardNum()[VeggiesData
							.getCardSlot()[i] - 1] >= 0) {
						canvas.drawText(
								Integer.toString(VeggiesData.getAllCardNum()[VeggiesData
										.getCardSlot()[i] - 1]),
								(Location.GameEquip.cardSlot_xy[i][0] + 63 - 3)
										* GameConfig.f_zoomx,
								(Location.GameEquip.cardSlot_xy[i][1] + 106 - 3)
										* GameConfig.f_zoomy, paint);
					}
				}

				// 灰色遮盖
				if (VeggiesData.getAllCardNum()[VeggiesData.getCardSlot()[i] - 1] == 0) {
					GameStaticImage.s_smallcard_card_gray.drawBitmap(canvas,
							GameStaticImage.s_smallcard_card_gray.bitmap,
							Location.GameEquip.cardSlot_xy[i][0]
									* GameConfig.f_zoomx,
							Location.GameEquip.cardSlot_xy[i][1]
									* GameConfig.f_zoomy, null);
				}
			}
		}

		//光标
//		if (cursor_index < anjian_equip.length-1)
//			GameStaticImage.s_smallcard_card_s_cover.drawBitmap(canvas, GameStaticImage.s_smallcard_card_s_cover.bitmap, Location.GameEquip.cardSlot_xy[cursor_index][0] * GameConfig.f_zoomx, Location.GameEquip.cardSlot_xy[cursor_index][1] * GameConfig.f_zoomy, null);
		float friend_bgx = Location.GameEquip.cardSlot_xy[7][0] * GameConfig.f_zoomx;
		float friend_bgy = Location.GameEquip.cardSlot_xy[7][1] * GameConfig.f_zoomy;
		//绘制好友
		if (cursor_index == 7) 
		 GameStaticImage.s_smallcard_card_friend1.drawBitmap(canvas, GameStaticImage.s_smallcard_card_friend.bitmap, friend_bgx, friend_bgy, null);
		else
		 GameStaticImage.s_smallcard_card_friend.drawBitmap( canvas, GameStaticImage.s_smallcard_card_friend.bitmap, friend_bgx, friend_bgy, null);
		
		
		 if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL16]){
				GameStaticImage.s_smallcard_card_lock.drawBitmap(canvas,
						GameStaticImage.s_smallcard_card_lock.bitmap, friend_bgx+(GameStaticImage.s_smallcard_card_friend.bitmap.getWidth()-GameStaticImage.s_smallcard_card_lock.bitmap.getWidth())/2, friend_bgy+(GameStaticImage.s_smallcard_card_friend.bitmap.getHeight()-GameStaticImage.s_smallcard_card_lock.bitmap.getHeight())/2, null);
		}
		
		
		//friend icon

		if (equipState == 2 && askfriend == 0)
			GameStaticImage.s_share_ui_photo_02.drawBitmap(canvas,
					GameStaticImage.s_share_ui_photo_02.bitmap,
					Location.GameEquip.askfriend_icon_xy[0]
							* GameConfig.f_zoomx,
					Location.GameEquip.askfriend_icon_xy[1]
							* GameConfig.f_zoomy, null);
		else if (friends!=null && friends.size()>=friendIconId && equipState == 1 && friendIconId >= 0) {
			if (friends.get(friendIconId).bmp == null) {
				GameStaticImage.s_share_ui_photo_01.drawBitmap(canvas,
						GameStaticImage.s_share_ui_photo_01.bitmap,
						Location.GameEquip.askfriend_icon_xy[0]
								* GameConfig.f_zoomx,
						Location.GameEquip.askfriend_icon_xy[1]
								* GameConfig.f_zoomy, null);
			} else {
				canvas.drawBitmap(friends.get(friendIconId).bmp,
						Location.GameEquip.askfriend_icon_xy[0]
								* GameConfig.f_zoomx,
						Location.GameEquip.askfriend_icon_xy[1]
								* GameConfig.f_zoomy, null);
			}
		}else if(askfriend == 0)
			GameStaticImage.s_share_ui_photo_02.drawBitmap(canvas,
					GameStaticImage.s_share_ui_photo_02.bitmap,
					Location.GameEquip.askfriend_icon_xy[0]
							* GameConfig.f_zoomx,
					Location.GameEquip.askfriend_icon_xy[1]
							* GameConfig.f_zoomy, null);
	}

	private void paintDown(Canvas canvas) {

		switch (equipState) {
		case 0:
			paintPackage(canvas);
			break;
		case 1:
			if(GameManager.getGT() !=null &&
				GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL17){
				equipState = 2; // 体转未登录状态
//				return;
				paintSystemFriend(canvas);
			}else 
			paintFriend(canvas);
			break;
		case 2:
			if (FacebookOperation.isLanding && !(GameManager.getGT() !=null &&
					GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL17)) {
				equipState = 1; // 登陆过了就显示好友 这没登陆会显示
//				return;
				paintFriend(canvas);
			}else
				paintSystemFriend(canvas);
			break;
		}
	}

	private void paintFriend(Canvas canvas) {
		// 绘制底部
		GameStaticImage.s_share_ui_back_01
				.drawBitmap(
						canvas,
						null,
						0 - GameStaticImage.s_share_ui_back_01.bitmap
								.getWidth() / 3,
						(int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy),
						GameConfig.GameScreen_Width
								+ GameStaticImage.s_share_ui_back_01.bitmap
										.getWidth() * 2 / 3,
						(int) (270 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null,
				(int) (9 * GameConfig.f_zoomx),
				(int) (GameConfig.GameScreen_Height - (154 + 13)
						* GameConfig.f_zoomy),
				(int) (517 * GameConfig.f_zoomx),
				(int) (154 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null,
				(int) (9 * GameConfig.f_zoomx),
				(int) (GameConfig.GameScreen_Height - (154 + 13)
						* GameConfig.f_zoomy),
				(int) (517 * GameConfig.f_zoomx),
				(int) (154 * GameConfig.f_zoomy), -1);
		float tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
		GameStaticImage.s_word_ask.drawBitmap(canvas,
				GameStaticImage.s_word_ask.bitmap,
				Location.GameEquip.ask_friend_title_xy[0] * GameConfig.f_zoomx,
				tempy + 24 * GameConfig.f_zoomy, null);

		canvas.save();
		canvas.clipRect(_friend_x, _friend_y - _friend_s / 2, _friend_x
				+ _friend_w - _friend_s, _friend_y + _friend_h + _friend_s / 2);
		float temp_X = _friend_x;
		float temp_Y = _friend_y;
		paint.setColor(0xffBD6D18);
		paint.setTextSize(20 * GameConfig.f_zoom);

		if (_friends != null) {
			for (int i = 0; i < _friends.size(); i++) {
				// defaulu_photo.drawBitmap(canvas, defaulu_photo.bitmap, 59 *
				// GameConfig.f_zoom, tempy + 102 * GameConfig.f_zoom, null);
				if (_friends.get(i).bmp == null || _friends.get(i).bmp == null) {
					canvas.drawBitmap(
							GameStaticImage.s_share_ui_photo_01.bitmap, move_X
									+ temp_X + (-1 + i)
									* (_friend_sc + _friend_s), temp_Y, null);
				} else {
					// icon
					canvas.drawBitmap(_friends.get(i).bmp, move_X + temp_X
							+ (-1 + i) * (_friend_sc + _friend_s) + 3, temp_Y
							+ 3 * GameConfig.f_zoomy, null);
					// 背景框
					share_ui_photo_04.drawBitmap(canvas,
							share_ui_photo_04.bitmap, move_X + temp_X
									+ (-1 + i) * (_friend_sc + _friend_s),
							temp_Y, null);
					if(friendIconId>=0 && friends.get(friendIconId) !=null ){//&& (index) == i
						if(friends.get(friendIconId).name.equals(_friends.get(i).name))
							share_ui_photo_05.drawBitmap(canvas,
									share_ui_photo_05.bitmap, move_X + temp_X
									+ (-1 + i) * (_friend_sc + _friend_s),
							temp_Y, null);
					}

				}
//				canvas.save();
//				canvas.clipRect(
//						move_X + temp_X + (-1 + i) * (_friend_sc + _friend_s),
//						temp_Y
//								+ GameStaticImage.s_share_ui_photo_03.bitmap
//										.getHeight()
//								* ((float) (CD - _friends.get(i).cd) / CD),
//						move_X
//								+ temp_X
//								+ (-1 + i)
//								* (_friend_sc + _friend_s)
//								+ GameStaticImage.s_share_ui_photo_03.bitmap
//										.getWidth(),
//						temp_Y
//								+ GameStaticImage.s_share_ui_photo_03.bitmap
//										.getHeight());
				if(_friends.get(i).s_time !=null && !_friends.get(i).s_time.equals("00:00"))
					GameStaticImage.s_share_ui_photo_03.drawBitmap(canvas,
							GameStaticImage.s_share_ui_photo_03.bitmap, move_X
									+ temp_X + (-1 + i) * (_friend_sc + _friend_s),
							temp_Y, null);
//				canvas.restore();
				if (_friends.get(i).cd != 0)
					GameStaticImage.s_word_num_07[0]
							.drawBitmap(
									canvas,
									GameStaticImage.s_word_num_07,
									(int) (move_X + temp_X + (-1 + i)
											* (_friend_sc + _friend_s) + 2 * GameConfig.f_zoomx),
									(int) (temp_Y + (GameStaticImage.s_share_ui_photo_03.bitmap
											.getHeight() - GameStaticImage.s_word_num_07[0].bitmap
											.getHeight()) / 2),
									GameConfig.Char_num2, _friends.get(i).s_time,
									null, 0, 1f);
				canvas.drawText(_friends.get(i).name, move_X + temp_X
						+ (-1 + i) * (_friend_sc + _friend_s), temp_Y + 93
						* GameConfig.f_zoomy, paint);
			}
		}
		canvas.restore();

		if (!FacebookOperation.isOpenNet && _friends == null && !FacebookOperation.getFacebook().getFriendNet()) {
			int h = (int) (270 * GameConfig.f_zoomy);
			int y = (int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy);
			float tempx = ((float) ((GameConfig.GameScreen_Width - loading[0].bitmap.getWidth()) / 2));
			tempy = y + ((float) ((h - loading[0].bitmap.getHeight()) / 2));

			loading[tempi].drawBitmap(canvas, loading[tempi].bitmap, tempx, tempy, null);

		} 
		if (FacebookOperation.isOpenNet && _friends == null ) { // 没网络
			int h = (int) (270 * GameConfig.f_zoomy);
			int y = (int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy);
			String temp = LangUtil.getLangString(LangDefineClient.CHECK_NET);
			float tempx = ((float) ((GameConfig.GameScreen_Width - paint.measureText(temp)) / 2));
			tempy = y + ((float) ((h - paint.getTextSize()) / 2));

			canvas.drawText(temp, tempx, tempy, paint);

		}

		if (_friends != null) {
			if (_friends.size() >= 2 && _friends.get(1).index == 1
					&& move_X >= 0) {
			} else {
				GameStaticImage.s_share_ui_arrows_01_02[0].drawBitmap(canvas,
						GameStaticImage.s_share_ui_arrows_01_02[0].bitmap,
						28 * GameConfig.f_zoomx, tempy + 132
								* GameConfig.f_zoomy, null);
			}
			if (_friends.size() < 5
					|| (_friends.size() >= 5
							&& _friends.get(4).index == friends.size() && move_X <= 0)) {
			} else {
				GameStaticImage.s_share_ui_arrows_01_02[1].drawBitmap(canvas,
						GameStaticImage.s_share_ui_arrows_01_02[1].bitmap,
						493 * GameConfig.f_zoomx, tempy + 132
								* GameConfig.f_zoomy, null);
			}
		}
	}

	private void paintSystemFriend(Canvas canvas) {
		// 绘制底部
		GameStaticImage.s_share_ui_back_01
				.drawBitmap(
						canvas,
						null,
						0 - GameStaticImage.s_share_ui_back_01.bitmap
								.getWidth() / 3,
						(int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy),
						GameConfig.GameScreen_Width
								+ GameStaticImage.s_share_ui_back_01.bitmap
										.getWidth() * 2 / 3,
						(int) (270 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null,
				(int) (9 * GameConfig.f_zoomx),
				(int) (GameConfig.GameScreen_Height - (154 + 13)
						* GameConfig.f_zoomy),
				(int) (517 * GameConfig.f_zoomx),
				(int) (154 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null,
				(int) (9 * GameConfig.f_zoomx),
				(int) (GameConfig.GameScreen_Height - (154 + 13)
						* GameConfig.f_zoomy),
				(int) (517 * GameConfig.f_zoomx),
				(int) (154 * GameConfig.f_zoomy), -1);
		float tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
		GameStaticImage.s_word_ask.drawBitmap(canvas,
				GameStaticImage.s_word_ask.bitmap,
				Location.GameEquip.ask_friend_title_xy[0] * GameConfig.f_zoomx,
				tempy + 24 * GameConfig.f_zoomy, null);

		// fackebook
		GameStaticImage.s_interface_icon_facebook_02
				.drawBitmap(
						canvas,
						357
								* GameConfig.f_zoomx
								- GameStaticImage.s_interface_icon_facebook_02.bitmap
										.getWidth() / 2
								* (anjianfacebook ? 0.2f : 0f),
						tempy
								+ 101
								* GameConfig.f_zoomy
								- GameStaticImage.s_interface_icon_facebook_02.bitmap
										.getHeight() / 2
								* (anjianfacebook ? 0.2f : 0f),
						anjianfacebook ? 1.2f : 1f, anjianfacebook ? 1.2f : 1f,
						255, 0, 0, 0);

		GameStaticImage.s_share_ui_photo_02
				.drawBitmap(canvas, GameStaticImage.s_share_ui_photo_02.bitmap,
						Location.GameEquip.systemfriend_icon_xy[0]
								* GameConfig.f_zoomx, tempy + 102
								* GameConfig.f_zoomy, null);
		paint.setColor(Color.argb(77, 0, 0, 0));
		// canvas.drawRect(Location.GameEquip.systemfriend_icon_xy[0] *
		// GameConfig.f_zoom, tempy + 102 * GameConfig.f_zoom +
		// systemfriend_icon.bitmap.getHeight() * ((float)(CD - systemCD) / CD),
		// Location.GameEquip.systemfriend_icon_xy[0] * GameConfig.f_zoom +
		// systemfriend_icon.bitmap.getWidth(), tempy + 102 * GameConfig.f_zoom
		// + systemfriend_icon.bitmap.getHeight(), paint);
//		canvas.save();
//		canvas.clipRect(
//				Location.GameEquip.systemfriend_icon_xy[0] * GameConfig.f_zoomx,
//				tempy
//						+ 102
//						* GameConfig.f_zoomy
//						+ GameStaticImage.s_share_ui_photo_02.bitmap
//								.getHeight() * ((float) (CD - systemCD) / CD),
//				Location.GameEquip.systemfriend_icon_xy[0] * GameConfig.f_zoomx
//						+ GameStaticImage.s_share_ui_photo_02.bitmap.getWidth(),
//				tempy
//						+ 102
//						* GameConfig.f_zoomy
//						+ GameStaticImage.s_share_ui_photo_02.bitmap
//								.getHeight());
		int ui_3x = (int)(Location.GameEquip.systemfriend_icon_xy[0]* GameConfig.f_zoomx);
		int ui_3y = (int)(tempy + 102 * GameConfig.f_zoomy);
		if (systemCD != 0)
			GameStaticImage.s_share_ui_photo_03.drawBitmap(canvas, GameStaticImage.s_share_ui_photo_03.bitmap, ui_3x, ui_3y, null);
//		canvas.restore();
		float tempx = Location.GameEquip.systemfriend_icon_xy[0]
				* GameConfig.f_zoomx + 5 * GameConfig.f_zoomx;
		paint.setTextSize(20 * GameConfig.f_zoom);
		paint.setColor(0xffBD6D18);
		if (systemCD != 0)
			GameStaticImage.s_word_num_07[0].drawBitmap(canvas,
					GameStaticImage.s_word_num_07, ui_3x+(int)((GameStaticImage.s_share_ui_photo_03.bitmap.getWidth() - (systemTime.length()*GameStaticImage.s_word_num_07[0].bitmap.getWidth()+(systemTime.length()-1)*GameConfig.f_zoomx))/2),
					ui_3y+(GameStaticImage.s_share_ui_photo_03.bitmap.getHeight() - GameStaticImage.s_word_num_07[0].bitmap.getHeight()>>1),
					GameConfig.Char_num2, systemTime, null, 0, 1f);
		// canvas.drawText(systemTime, tempx, tempy + (102 + 78) *
		// GameConfig.f_zoom, paint);
		loginfacebook.paintText(canvas,
				(int) (tempx + 76 * GameConfig.f_zoomx),
				(int) (tempy + (102) * GameConfig.f_zoomy));

	}

	private void paintPackage(Canvas canvas) {
		// 绘制底部装备包裹
		GameStaticImage.s_share_ui_back_01
				.drawBitmap(
						canvas,
						null,
						0 - GameStaticImage.s_share_ui_back_01.bitmap
								.getWidth() / 3,
						(int) (GameConfig.GameScreen_Height - 253 * GameConfig.f_zoomy),
						GameConfig.GameScreen_Width
								+ GameStaticImage.s_share_ui_back_01.bitmap
										.getWidth() * 2 / 3,
						(int) (270 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null,
				(int) (9 * GameConfig.f_zoomx),
				(int) (GameConfig.GameScreen_Height - (154 + 13)
						* GameConfig.f_zoomy),
				(int) (517 * GameConfig.f_zoomx),
				(int) (154 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null,
				(int) (9 * GameConfig.f_zoomx),
				(int) (GameConfig.GameScreen_Height - (154 + 13)
						* GameConfig.f_zoomy),
				(int) (517 * GameConfig.f_zoomx),
				(int) (154 * GameConfig.f_zoomy), -1);
		float tempy = GameConfig.GameScreen_Height - 253 * GameConfig.f_zoomy;
		// 星级选项
		for (int i = 0; i < Location.GameEquip.starLevel_xy.length; i++) {
			if (i == starLevel_index) {
				GameStaticImage.s_share_ui_back_04[1].drawBitmap(canvas,
						GameStaticImage.s_share_ui_back_04[1].bitmap,
						Location.GameEquip.starLevel_xy[i][0]
								* GameConfig.f_zoomx, tempy + 17
								* GameConfig.f_zoomy, null);
				GameStaticImage.s_share_ui_back_04[2].drawBitmap(canvas,
						GameStaticImage.s_share_ui_back_04[2].bitmap,
						(Location.GameEquip.starLevel_xy[i][0] + 62)
								* GameConfig.f_zoomx, tempy + (17 + 55)
								* GameConfig.f_zoomy, null);
			} else {
				GameStaticImage.s_share_ui_back_04[0].drawBitmap(canvas,
						GameStaticImage.s_share_ui_back_04[0].bitmap,
						Location.GameEquip.starLevel_xy[i][0]
								* GameConfig.f_zoomx, tempy + 17
								* GameConfig.f_zoomy, null);
			}
		}
		for (int i = 0; i < Location.GameEquip.star_xy.length; i++) {
			GameStaticImage.s_interface_star_15.drawBitmap(canvas,
					GameStaticImage.s_interface_star_15.bitmap,
					Location.GameEquip.star_xy[i][0] * GameConfig.f_zoomx,
					tempy + 22 * GameConfig.f_zoomy, null);
		}

		if(_card.size()>=2 ){
			// 箭头
			if ( _card.get(1).index == 1 && move_X >= 0) {
			} else {
				GameStaticImage.s_share_ui_arrows_01_02[0].drawBitmap(canvas,
						GameStaticImage.s_share_ui_arrows_01_02[0].bitmap,
						28 * GameConfig.f_zoomx, tempy + 155 * GameConfig.f_zoomy,
						null);
			}
		}
	
		switch (sort_index) {
		case 0:
			if (_card.size() < 5
					|| (_card.get(4).index == card_header.size() / 3 && move_X <= 0)) {
			} else {
				GameStaticImage.s_share_ui_arrows_01_02[1].drawBitmap(canvas,
						GameStaticImage.s_share_ui_arrows_01_02[1].bitmap,
						493 * GameConfig.f_zoomx, tempy + 155
								* GameConfig.f_zoomy, null);
			}
			break;
		case 1:
			if (_card.size() < 5
					|| (_card.get(4).index == card_partner.size() / 3 && move_X <= 0)) {
			} else {
				GameStaticImage.s_share_ui_arrows_01_02[1].drawBitmap(canvas,
						GameStaticImage.s_share_ui_arrows_01_02[1].bitmap,
						493 * GameConfig.f_zoomx, tempy + 155
								* GameConfig.f_zoomy, null);
			}
			break;
		case 2:
			if (_card.size() < 5
					|| (_card.get(4).index == card_str.size() / 3 && move_X <= 0)) {
			} else {
				GameStaticImage.s_share_ui_arrows_01_02[1].drawBitmap(canvas,
						GameStaticImage.s_share_ui_arrows_01_02[1].bitmap,
						493 * GameConfig.f_zoomx, tempy + 155
								* GameConfig.f_zoomy, null);
			}
			break;
		case 3:
			if (_card.size() < 5
					|| (_card.get(4).index == card_items.size() / 3 && move_X <= 0)) {
			} else {
				GameStaticImage.s_share_ui_arrows_01_02[1].drawBitmap(canvas,
						GameStaticImage.s_share_ui_arrows_01_02[1].bitmap,
						493 * GameConfig.f_zoomx, tempy + 155
								* GameConfig.f_zoomy, null);
			}
			break;
		}

		canvas.save();
		canvas.clipRect(_card_x, _card_y - _card_s / 2, _card_x + _card_w
				- _card_s, _card_y + _card_h + _card_s / 2);
		float temp_X = _card_x;
		float temp_Y = _card_y;
		for (int i = 0; i < _card.size(); i++) {
			if (_card.get(i).index != -1) {
				if (VeggiesData.getCardNum(_card.get(i).cardId) == -1) {
					GameStaticImage.noCard[0].drawBitmap(
							canvas,
							GameStaticImage.noCard[0].bitmap,
							move_X
									+ temp_X
									+ (-1 + i)
									* (GameStaticImage.noCard[0].bitmap
											.getWidth() + _card_s), temp_Y,
							null);
					GameStaticImage.s_interface_card_unlock.drawBitmap(
							canvas,
							GameStaticImage.s_interface_card_unlock.bitmap,
							move_X
									+ temp_X
									+ (-1 + i)
									* (GameStaticImage.noCard[0].bitmap
											.getWidth() + _card_s) + 32
									* GameConfig.f_zoomx, temp_Y + 46
									* GameConfig.f_zoomy, null);
				} else {
					GameStaticImage.noCard[(_card_index == i ? 7 : 4) + starLevel_index].drawBitmap(canvas, GameStaticImage.noCard[(_card_index == i ? 7 : 4) + starLevel_index].bitmap, move_X + temp_X + (-1 + i) * (GameStaticImage.noCard[(_card.get(i).cardId-1)%3].bitmap.getWidth() + _card_s), temp_Y, null);
					if(s_smallcard_card.containsKey(_card.get(i).cardId) && VeggiesData.getCardNum(_card.get(i).cardId) > 0)
					s_smallcard_card.get(_card.get(i).cardId).drawBitmap(canvas, s_smallcard_card.get(_card.get(i).cardId).bitmap, move_X + temp_X + (-1 + i) * (s_smallcard_card.get(_card.get(i).cardId).bitmap.getWidth() + _card_s), temp_Y, null);
					if (_card.get(i).cardId != GameItem.cardSort[0][0]) {
						GameStaticImage.s_share_ui_back_07.drawBitmap(canvas, GameStaticImage.s_share_ui_back_07.bitmap, move_X + temp_X + (-1 + i) * (GameStaticImage.noCard[0].bitmap.getWidth() + _card_s) + 43* GameConfig.f_zoomx, temp_Y + 86* GameConfig.f_zoomy, null);
						paint.setColor(0xffffffff);
						if (VeggiesData.getAllCardNum()[_card.get(i).cardId-1] > 99) {
							canvas.drawText(Integer.toString(VeggiesData.getAllCardNum()[_card.get(i).cardId-1]), move_X + temp_X + (-1 + i) * (GameStaticImage.noCard[0].bitmap.getWidth() + _card_s) + (55 - 3)* GameConfig.f_zoomx, temp_Y + (106-3)* GameConfig.f_zoomy, paint);						
						} else if (VeggiesData.getAllCardNum()[_card.get(i).cardId-1] > 9) {						
							canvas.drawText(Integer.toString(VeggiesData.getAllCardNum()[_card.get(i).cardId-1]), move_X + temp_X + (-1 + i) * (GameStaticImage.noCard[0].bitmap.getWidth() + _card_s) + (59 - 3)* GameConfig.f_zoomx, temp_Y + (106-3)* GameConfig.f_zoomy, paint);						
						} else if (VeggiesData.getAllCardNum()[_card.get(i).cardId-1] >= 0) {						
							canvas.drawText(Integer.toString(VeggiesData.getAllCardNum()[_card.get(i).cardId-1]), move_X + temp_X + (-1 + i) * (GameStaticImage.noCard[0].bitmap.getWidth() + _card_s) + (63 - 3)* GameConfig.f_zoomx, temp_Y + (106-3)* GameConfig.f_zoomy, paint);						
						}

						// 灰色遮盖
						if (VeggiesData.getAllCardNum()[_card.get(i).cardId - 1] == 0) {
							GameStaticImage.s_smallcard_card_gray
									.drawBitmap(
											canvas,
											GameStaticImage.s_smallcard_card_gray.bitmap,
											move_X
													+ temp_X
													+ (-1 + i)
													* (GameStaticImage.noCard[0].bitmap
															.getWidth() + _card_s),
											temp_Y, null);
						}
					}
				}
			}
		}
		canvas.restore();
	}

	public void run() {
//		ChooseLevelModule2.gt.updata();
//		if (ChooseLevelModule2.gt.pauseState()) {
//			return;
//		}
		if(GameConfig.i_coke%2==0){
			tempi_egg++;
			if(tempi_egg>=index_egg.length){
				tempi_egg = 0;
			}
		}
		switch (sort_index) {
		case 0:
		case 1:
		case 2:
		case 3:
			if(equipState!=1){
				int tempw=(int)(GameStaticImage.noCard[0].bitmap.getWidth() + _card_s);
				int tempnum=_card.size();
				if(move_X>0||tempnum<=4){
					move_X=0;
				}else if(move_X<-(tempnum-5)*tempw){
					move_X=-(tempnum-5)*tempw;
				}	
			}
		
			break;
		}
		if(deblockingid!=-1){ //动画钥匙的位置
			if (deblockingIndex!=4 && GameConfig.i_coke % 3 == 0) {
				if(deblockingIndex == 0){
					deblocking_x = -2;
					deblocking_y = -2;
				}else if(deblockingIndex == 1){
					deblocking_x = -1;
					deblocking_y = -3;
				}else if(deblockingIndex == 2){
					deblocking_x = 2;
					deblocking_y = 2;
				}
				deblockingIndex++;
			}else if(deblockingIndex == 4){
				if (GameConfig.i_coke % 3 < 1) {
					 r = 1.0f;
					 deblocking_x = 0;
					 deblocking_y = 0;
					 VeggiesData.getCardSlot()[deblockingid] = 0;
					 replaceCard(starLevel_index, sort_index);
					 if(GameItem.equipPrice[deblockingid]==-1){
						 if(VeggiesData.getGameGuanka()[1] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL7]){
							 GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL7, (int)((234) * GameConfig.f_zoomx), (int)(237 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_7), GameTeaching.HAND_MOVE_STATE_1, (GameConfig.GameScreen_Height>>1)+(int)(20*GameConfig.f_zoomy));
						 }else if(VeggiesData.getGameGuanka()[1] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL9]){
							 GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL9, (int)((220) * GameConfig.f_zoomx), (int)(385 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_9), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
						 }else if(VeggiesData.getGameGuanka()[4] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL40]){
							 GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL40, (int)((124) * GameConfig.f_zoomx), (int)(383 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_40), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
						 }
					 }
					 deblockingid = -1;
					 deblockingIndex = 0;
				}
			}
		}
		
//		run_smallcardLoadList();
		
		currenttime = System.currentTimeMillis();
		switch (equipState) {
		case 0:
			if (isCorrectCardMove) {
//				move_X += correctCard_move;
				if (move_X <= -(GameStaticImage.noCard[0].bitmap.getWidth() + _card_s)) {
					switch (sort_index) {
					case 0:
						if (_card.get(_card.size() - 2).index != card_header
								.size())
							addRightCard(sort_index);
						break;
					case 1:
						if (_card.get(_card.size() - 2).index != card_partner
								.size())
							addRightCard(sort_index);
						break;
					case 2:
						if (_card.get(_card.size() - 2).index != card_str
								.size())
							addRightCard(sort_index);
						break;
					case 3:
						if (_card.get(_card.size() - 2).index != card_items
								.size())
							addRightCard(sort_index);
						break;
					}
//					move_X = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				} else if (move_X > -Math.abs(correctCard_move)
						&& move_X < Math.abs(correctCard_move)) {
//					move_X = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				} else if (move_X >= GameStaticImage.noCard[0].bitmap
						.getWidth() + _card_s) {
					if (_card.get(_card.size() - 1).index != 1)
						addLeftCard(sort_index);
//					move_X = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				}
			}

			if (longtouchcard) {
				if (currenttime - oldtime > 1000) {
					anjiancard = false;
					// TODO 跳转图鉴
					GameManager.forbidModule(new BigCardModule(_card_cardId,false));
//					ChooseLevelModule.sendMessage("弹图鉴");
					longtouchcard = false;
				}
			}
			break;
		case 1:

			if (_friends == null
					&& !FacebookOperation.getFacebook().getFriendNet()) {
				// tempi = 0;
				if (GameConfig.i_coke % 2 == 0) {
					tempi++;
					if (tempi == 9)
						tempi = 0;
				}
			}

			if (_friends != null) {
				for (int i = 0; i < _friends.size(); i++) {
					_friends.get(i).runTime();
				}
			}

			if (_friends != null && isCorrectFriendMove) {
				move_X += correctFriend_move;
				if (move_X <= -(_friend_sc + _friend_s)) {
					if (_friends.get(_friends.size() - 2).index != friends
							.size())
						addRightFriend();
					move_X = 0;
					correctFriend_move = Math.abs(correctFriend_move);
					isCorrectFriendMove = false;
				} else if (move_X > -Math.abs(correctFriend_move)
						&& move_X < Math.abs(correctFriend_move)) {
					move_X = 0;
					correctFriend_move = Math.abs(correctFriend_move);
					isCorrectFriendMove = false;
				} else if (move_X >= _friend_sc + _friend_s) {
					if (_friends.get(_friends.size() - 1).index != 1)
						addLeftFriend();
					move_X = 0;
					correctFriend_move = Math.abs(correctFriend_move);
					isCorrectFriendMove = false;
				}
			}
			break;
		case 2:
			// test计算systemiconCD时间
			if (currenttime - VeggiesData.systemtime < CD && currenttime - VeggiesData.systemtime >= 0) {
					systemTime = "";
					systemCD = CD - (currenttime - VeggiesData.systemtime);
					long tempCD = systemCD / 1000;
					if (tempCD % 60 == 0)
						systemTime = "00";
					else if (tempCD % 60 < 10)
						systemTime = "0" + tempCD % 60;
					else
						systemTime = systemTime + tempCD % 60;
					tempCD = tempCD / 60;
					if (tempCD % 60 == 0)
						systemTime = "00:" + systemTime;
					else if (tempCD % 60 < 10)
						systemTime = "0" + tempCD % 60 + ":" + systemTime;
					else
						systemTime = tempCD % 60 + ":" + systemTime;
					
					tempCD = tempCD / 60;
					if (tempCD % 60 == 0)
						systemTime = "00:" + systemTime;
					else if (tempCD % 60 < 10)
						systemTime = "0" + tempCD % 60 + ":" + systemTime;
					else
						systemTime = tempCD % 60 + ":" + systemTime;
			} else {
				systemCD = 0;
				systemTime = "00:00";
			}
			String temp = systemTime.substring(0, 2);
			if(systemTime.length()>=5 && temp.equals("00")){
				systemTime = systemTime.substring(3, systemTime.length());
//				temp = systemTime.substring(0, 2);
//				if(temp.equals("00")){
//					systemTime = systemTime.substring(3, systemTime.length());
//				}
			}
//			if (longtouchcard) {
//				if (currenttime - oldtime > 1000) {
//					anjiancard = false;
//					// TODO 跳转图鉴
//					GameManager.forbidModule(new BigCardModule(GameItem.Item04));
//					longtouchcard = false;
//					GameManager.getGT().finish();
//					new VeggiesData().saveGame(); 
//				}
//			}
			break;
		}

	}

	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			GameManager.ChangeModule(null);
			GameLevelInfoModule.isAni = true;
		}
		return false;
	}
	@Override
	public void onreStart() {
		// TODO Auto-generated method stub
		super.onreStart();
		if(VeggiesData.getGameGuanka()[1] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL8] && GameTeaching.teachingArrary[GameTeaching.TEACH_VOL35]){
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL8, (int)((100) * GameConfig.f_zoomx), (int)(768 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_8), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
		}
	}
	public void Release() {
		if (isGotoGame) {
			// GameStaticImage.delSmallCard();
			// GameStaticImage.releaseStaticImage();
			// System.gc();
		}
		if(loginfacebook!=null)
			loginfacebook.Close();
		loginfacebook = null;
		if(loading!=null)
			GameImage.delImageArray(loading);
		loading = null;

		if(ui_egg!=null)
			GameImage.delImageArray(ui_egg);
		ui_egg = null;
		
		GameImage.delImage(share_ui_photo_04.bitmap);
		share_ui_photo_04 = null;
		
		GameImage.delImage(shop_gold_06_1.bitmap);
		shop_gold_06_1 = null;
		
		GameImage.delImage(share_ui_photo_05.bitmap);
		share_ui_photo_05 = null;
//		if(key_free!=null)
//			GameImage.delImage(key_free.bitmap);
//		key_free = null;
		
		if(key_free!=null){
			if(key_free.bitmap!=null){
				if(!key_free.bitmap.isRecycled())
					key_free.bitmap.recycle();
			}
		}
		
		FacebookOperation.getFacebook().setFriendIcon(null);
		FacebookOperation.getFacebook().setFriendInt(null);
		FacebookOperation.getFacebook().setFriendHelpTime(null);
		gem= null;
		GameManager.setGT(null);
		
	}

	public void initwordpic() {

	}

	public void onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL35){
					longtouchcard = true;
					oldtime = System.currentTimeMillis();
				}else if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL7){
					equipCardDown(true, x, y);
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL8){
					onTouchDown(true, x, y);
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL9){
					equipCardDown(true, x, y);
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL40){
					equipCardDown(true, x, y);
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL41){
					onTouchDown(true, x, y);
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL10){
					onTouchDown(true, x, y);
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL11 
						||GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL18){
					if (ExternalMethods.CollisionTest(x, y, 55 * GameConfig.f_zoomx, 477 * GameConfig.f_zoomy, 55 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_button_05_1.bitmap.getWidth(),	477 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_button_05_1.bitmap.getHeight())) {
						anjianbutton = true;
					}
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL16){
					equipCardDown(true, x, y);
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL17){
					float tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
					 if (ExternalMethods.CollisionTest(x,y,Location.GameEquip.systemfriend_icon_xy[0]* GameConfig.f_zoomx - 10 * GameConfig.f_zoomx,tempy + 102 * GameConfig.f_zoomy - 10* GameConfig.f_zoomy,Location.GameEquip.systemfriend_icon_xy[0]* GameConfig.f_zoomx+ GameStaticImage.s_share_ui_photo_02.bitmap.getWidth() + 20 * GameConfig.f_zoomx,tempy+ 102* GameConfig.f_zoomy+ GameStaticImage.s_share_ui_photo_02.bitmap.getHeight() + 20 * GameConfig.f_zoomy)) {
							anjiansystemfriend = true;
					}
				}
					
			}else if (event.getAction() == MotionEvent.ACTION_UP) {
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL35){
					longtouchcard = false;
					oldtime = System.currentTimeMillis();
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL7){
					int index = equipCardUP(true, x, y);
					if(index != -1){
						GameManager.getGT().finish();
//						new VeggiesData().saveGame(); 
//						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL35, (int)((118) * GameConfig.f_zoomx), (int)(750 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_35), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
						
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL8, (int)((100) * GameConfig.f_zoomx), (int)(768 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_8), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
					}
				}else if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL8) {
					int index = onTouchCardUP(true, x, y);
					if(index != -1){
						GameManager.getGT().finish();
//						new VeggiesData().saveGame(); 
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL11, (int)((270) * GameConfig.f_zoomx), (int)(514 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_11), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
					}
				}else if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL9) {
					int index = equipCardUP(true, x, y);
					if(index != -1){
						GameManager.getGT().finish();
//						new VeggiesData().saveGame(); 
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL10, (int)((100) * GameConfig.f_zoomx), (int)(768 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_10), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
					}
				}else if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL40) {
					int index = equipCardUP(true, x, y);
					if(index != -1){
						GameManager.getGT().finish();
//						new VeggiesData().saveGame(); 
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL41, (int)((100) * GameConfig.f_zoomx), (int)(768 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_41), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
					}
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL41){
					int index = onTouchCardUP(true, x, y);
					if(index != -1){
						GameManager.getGT().finish();
//						new VeggiesData().saveGame(); 
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL11, (int)((270) * GameConfig.f_zoomx), (int)(514 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_11), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
					}
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL10){
					int index = onTouchCardUP(true, x, y);
					if(index != -1){
						GameManager.getGT().finish();
//						new VeggiesData().saveGame(); 
//						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL11, (int)((309) * GameConfig.f_zoomx), (int)(514 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_11), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
						deblockingid = 4;
						r = 1.2f;
						deblockingIndex = 0;
					}
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL28 ||
						 GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL11 ||
						 GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL18){
					if (ExternalMethods.CollisionTest(x,y,55 * GameConfig.f_zoomx,477 * GameConfig.f_zoomy,55* GameConfig.f_zoomx+ GameStaticImage.s_share_ui_button_05_1.bitmap.getWidth(),477* GameConfig.f_zoomy+ GameStaticImage.s_share_ui_button_05_1.bitmap.getHeight())) {
						GameManager.getGT().finish();
						new VeggiesData().saveGame(); 
						play_button();
					}
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL16){
					int index = equipCardUP(true, x, y);
					if(index != -1){
						GameManager.getGT().finish();
//						new VeggiesData().saveGame(); 
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL17, (int)((118) * GameConfig.f_zoomx), (int)(750 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_17), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
//						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL35, (int)((118) * GameConfig.f_zoomx), (int)(750 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_35), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
					}
					
					
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL17){
					float tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
				    if (anjiansystemfriend&& ExternalMethods.CollisionTest(x,y,Location.GameEquip.systemfriend_icon_xy[0]* GameConfig.f_zoomx- 10* GameConfig.f_zoomx,tempy + 102 * GameConfig.f_zoomy - 10* GameConfig.f_zoomy,Location.GameEquip.systemfriend_icon_xy[0]* GameConfig.f_zoomx+ GameStaticImage.s_share_ui_photo_02.bitmap.getWidth()+ 20* GameConfig.f_zoomx,tempy+ 102* GameConfig.f_zoomy+ GameStaticImage.s_share_ui_photo_02.bitmap.getHeight() + 20* GameConfig.f_zoomy)) {
							// TODO 换系统好友
							if (askfriend == 0) {
								askfriend = -1;
							} else {
								if (systemCD == 0)
									askfriend = 0;
								UserRequest.getUser().setFriendIconID(null);
							}
							GameManager.getGT().finish();
							GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL18, (int)((270) * GameConfig.f_zoomx), (int)(514 * GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_18), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
				    }
				}
				
			}
			return;
		}
		
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_x = x;
			_y = y;
			if (ExternalMethods.CollisionTest(
					x,
					y,
					453
							* GameConfig.f_zoomx
							- GameStaticImage.s_share_ui_close.bitmap
									.getWidth() / 2 * 0.2f,
					44
							* GameConfig.f_zoomy
							- GameStaticImage.s_share_ui_close.bitmap
									.getHeight() / 2 * 0.2f,
					453
							* GameConfig.f_zoomx
							+ GameStaticImage.s_share_ui_close.bitmap
									.getWidth() * 1.2f,
					44
							* GameConfig.f_zoomy
							+ GameStaticImage.s_share_ui_close.bitmap
									.getHeight() * 1.2f)) {
				anjianclose = true;
			} else if (ExternalMethods.CollisionTest(
					x,
					y,
					55 * GameConfig.f_zoomx,
					477 * GameConfig.f_zoomy,
					55
							* GameConfig.f_zoomx
							+ GameStaticImage.s_share_ui_button_05_1.bitmap
									.getWidth(),
					477
							* GameConfig.f_zoomy
							+ GameStaticImage.s_share_ui_button_05_1.bitmap
									.getHeight())) {
				anjianbutton = true;
			}else if(ExternalMethods.CollisionTest(
					x,
					y,
					53*GameConfig.f_zoomx,
					68*GameConfig.f_zoomy,
					53*GameConfig.f_zoomx
							+ ui_egg[index_egg[tempi_egg]].bitmap
									.getWidth(),
									68*GameConfig.f_zoomy
							+ ui_egg[index_egg[tempi_egg]].bitmap
									.getHeight())){
				isegg = true;
			}
			equipCardDown(false, x, y);
			float tempy = 0;
			switch (equipState) {
			case 0:
				if (ExternalMethods.CollisionTest(x, y, _card_x, _card_y
						- _card_s / 2, _card_x + _card_w - _card_s, _card_y
						+ _card_h + _card_s / 2)) {
					ismove = true;
					oldX = (int) x;
				}
				
				onTouchDown(false, x, y);
				
				tempy = GameConfig.GameScreen_Height - 253 * GameConfig.f_zoomy;
				for (int i = 0; i < Location.GameEquip.starLevel_xy.length; i++) {
					if (ExternalMethods
							.CollisionTest(
									x,
									y,
									Location.GameEquip.starLevel_xy[i][0]
											* GameConfig.f_zoomx,
									tempy + 17 * GameConfig.f_zoomy,
									Location.GameEquip.starLevel_xy[i][0]
											* GameConfig.f_zoomx
											+ GameStaticImage.s_share_ui_back_04[1].bitmap
													.getWidth(),
									tempy
											+ 17
											* GameConfig.f_zoomy
											+ GameStaticImage.s_share_ui_back_04[1].bitmap
													.getHeight())) {
						anjianstar[i] = true;
					}
				}
				break;
			case 1:
				if (ExternalMethods.CollisionTest(x, y, _friend_x, _friend_y
						- _friend_s / 2, _friend_x + _friend_w - _friend_s,
						_friend_y + _friend_h + _friend_s / 2)) {
					ismove = true;
					oldX = (int) x;
				}

				float temp1_X = _friend_x;
				float temp1_Y = _friend_y;
				if(_friends!=null)
					for (int i = 1; i < _friends.size(); i++) {
						if (ExternalMethods.CollisionTest(x, y, temp1_X + (-1 + i)
								* (_friend_sc + _friend_s), temp1_Y, temp1_X
								+ (-1 + i) * (_friend_sc + _friend_s) + _friend_sc,
								temp1_Y + _friend_h)) {
							anjianfriend = true;
						}
					}
				break;
			case 2:
				tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
				if (ExternalMethods
						.CollisionTest(
								x,
								y,
								357
										* GameConfig.f_zoomx
										- GameStaticImage.s_interface_icon_facebook_02.bitmap
												.getWidth() / 2 * 0.2f,
								tempy
										+ 101
										* GameConfig.f_zoomy
										- GameStaticImage.s_interface_icon_facebook_02.bitmap
												.getHeight() / 2 * 0.2f,
								357
										* GameConfig.f_zoomx
										+ GameStaticImage.s_interface_icon_facebook_02.bitmap
												.getWidth() * 1.2f,
								tempy
										+ 101
										* GameConfig.f_zoomy
										+ GameStaticImage.s_interface_icon_facebook_02.bitmap
												.getHeight() * 1.2f)) {
					anjianfacebook = true;
				} else if (ExternalMethods.CollisionTest(
						x,
						y,
						Location.GameEquip.systemfriend_icon_xy[0]
								* GameConfig.f_zoomx - 10 * GameConfig.f_zoomx,
						tempy + 102 * GameConfig.f_zoomy - 10
								* GameConfig.f_zoomy,
						Location.GameEquip.systemfriend_icon_xy[0]
								* GameConfig.f_zoomx
								+ GameStaticImage.s_share_ui_photo_02.bitmap
										.getWidth() + 20 * GameConfig.f_zoomx,
						tempy
								+ 102
								* GameConfig.f_zoomy
								+ GameStaticImage.s_share_ui_photo_02.bitmap
										.getHeight() + 20 * GameConfig.f_zoomy)) {
					anjiansystemfriend = true;
				}
				break;
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (ExternalMethods.CollisionTest(
					x,
					y,
					453
							* GameConfig.f_zoomx
							- GameStaticImage.s_share_ui_close.bitmap
									.getWidth() / 2 * 0.2f,
					44
							* GameConfig.f_zoomy
							- GameStaticImage.s_share_ui_close.bitmap
									.getHeight() / 2 * 0.2f,
					453
							* GameConfig.f_zoomx
							+ GameStaticImage.s_share_ui_close.bitmap
									.getWidth() * 1.2f,
					44
							* GameConfig.f_zoomy
							+ GameStaticImage.s_share_ui_close.bitmap
									.getHeight() * 1.2f)) {
				GameManager.ChangeModule(null);
				GameLevelInfoModule.isAni = true;
			}else if(isegg && ExternalMethods.CollisionTest(
					x,
					y,
					53*GameConfig.f_zoomx,
					68*GameConfig.f_zoomy,
					53*GameConfig.f_zoomx
							+ ui_egg[index_egg[tempi_egg]].bitmap
									.getWidth(),
									68*GameConfig.f_zoomy
							+ ui_egg[index_egg[tempi_egg]].bitmap
									.getHeight())){
				
				if(VeggiesData.getGold()>=PRICE || isFree){
					if(!isFree)
						VeggiesData.addGold(-PRICE);
					int card_number = cardNumber;
					Vector<Integer> temp = new Vector<Integer>();
					for(int i=0;i<card_number;++i){
						int star = 0;
						int end = CARDID.length-1;
						int a =new Random().nextInt((end-star)+1)+star;
						temp.add(CARDID[a]);
					}
					int[] temp_ = new int[temp.size()];
					for(int i=0;i<temp_.length;++i){
						temp_[i] = temp.get(i).intValue();
					}
					GameManager.forbidModule(new Gameoverxiangzi(temp_, false));
					if(isFree){
						isFree = false;
						new VeggiesData().saveGame();
					}
					isFree = false;
				}else{
//					String temp1 = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
//					GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
					String temp1 = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GOLD);
					GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
					
				}
				
				
				
			}else if (ExternalMethods.CollisionTest(
					x,
					y,
					55 * GameConfig.f_zoomx,
					477 * GameConfig.f_zoomy,
					55
							* GameConfig.f_zoomx
							+ GameStaticImage.s_share_ui_button_05_1.bitmap
									.getWidth(),
					477
							* GameConfig.f_zoomy
							+ GameStaticImage.s_share_ui_button_05_1.bitmap
									.getHeight())) {
				play_button();
			}
			equipCardUP(false, x, y);
			float tempy = 0;
			switch (equipState) {
			case 0:
				for (int i = 0; i < anjian_equip.length; i++) {
					anjian_equip[i] = false;
				}

				if (ismove) {
					if (move_X < -(GameStaticImage.noCard[0].bitmap.getWidth() + _card_s) / 2
							|| (move_X > 0 && move_X < (GameStaticImage.noCard[0].bitmap
									.getWidth() + _card_s) / 2)) {
						// 向_friend[0]靠近 -
						correctCard_move = -correctCard_move;
						isCorrectCardMove = true;
					} else if ((move_X < 0
							&& move_X >= -(GameStaticImage.noCard[0].bitmap
									.getWidth() + _card_s) / 2 || move_X >= (GameStaticImage.noCard[0].bitmap
							.getWidth() + _card_s) / 2)) {
						// 向_friend[1]靠近 +
						isCorrectCardMove = true;
					}
				}
				
				onTouchCardUP(false, x, y);

				tempy = GameConfig.GameScreen_Height - 253 * GameConfig.f_zoomy;
				for (int i = 0; i < Location.GameEquip.starLevel_xy.length; i++) {
					if (anjianstar[i]
							&& ExternalMethods
									.CollisionTest(
											x,
											y,
											Location.GameEquip.starLevel_xy[i][0]
													* GameConfig.f_zoomx,
											tempy + 17 * GameConfig.f_zoomy,
											Location.GameEquip.starLevel_xy[i][0]
													* GameConfig.f_zoomx
													+ GameStaticImage.s_share_ui_back_04[1].bitmap
															.getWidth(),
											tempy
													+ 17
													* GameConfig.f_zoomy
													+ GameStaticImage.s_share_ui_back_04[1].bitmap
															.getHeight())) {
						starLevel_index = i;
						replaceCard(starLevel_index, sort_index);
						continue;
					}
				}

				for (int i = 0; i < anjianstar.length; i++) {
					anjianstar[i] = false;
				}
				anjiancard = false;
				ismove = false;
				longtouchcard = false;
				break;
			case 1:
				if (ismove) {
					if (move_X < -(_friend_sc + _friend_s) / 2
							|| (move_X > 0 && move_X < (_friend_sc + _friend_s) / 2)) {
						// 向_friend[0]靠近 -
						correctFriend_move = -correctFriend_move;
						isCorrectFriendMove = true;
					} else if ((move_X < 0
							&& move_X >= -(_friend_sc + _friend_s) / 2 || move_X >= (_friend_sc + _friend_s) / 2)) {
						// 向_friend[1]靠近 +
						isCorrectFriendMove = true;
					}
				}

				float temp1_X = _friend_x;
				float temp1_Y = _friend_y;
				if (_friends != null) {
					for (int i = 1; i < _friends.size(); i++) {
						if (anjianfriend
								&& ExternalMethods.CollisionTest(x, y, temp1_X
										+ (-1 + i) * (_friend_sc + _friend_s),
										temp1_Y, temp1_X + (-1 + i)
												* (_friend_sc + _friend_s)
												+ _friend_sc, temp1_Y
												+ _friend_h)) {
							
							if (askfriend == i) {
								askfriend = -1;
								// 查找显示的好友
								AskFriend as = _friends.get(i);
								for (int k = 0; k < friends.size(); ++k) {
									if (as.name.equals(friends.get(k).name)) {
										if (friendIconId == k) {
											friendIconId = -1;
//											index = -1;
										} else{
											friendIconId = k;
//											index = i;
										}
										break;
									}
								}
							} else {
								if (_friends.get(i).cd == 0) {
									askfriend = i;

									// 查找显示的好友
									AskFriend as = _friends.get(i);
									for (int k = 0; k < friends.size(); ++k) {
										if (as.name.equals(friends.get(k).name)) {
											if (friendIconId == k) {
												friendIconId = -1;
//												index = -1;
											} else
												friendIconId = k;
//											index = i;
											break;
										}
									}

								}
							}
							//设置游戏中的头像
							if (friends!=null && friends.size()>=friendIconId && equipState == 1 && friendIconId >= 0) {
//								if (friends.get(friendIconId).bmp == null) {
								if(friends.get(friendIconId).facebookId.equals("-100")){
									UserRequest.getUser().setFriendIconID(null); //系统的
								}else
									UserRequest.getUser().setFriendIconID(friends.get(friendIconId).facebookId);
//								} else {
//									UserRequest.getUser().setFriendIconID(friends.get(friendIconId).bmp);
//								}
							}else
								UserRequest.getUser().setFriendIconID("");
							
							
						}
					}
				}

				anjianfriend = false;
				ismove = false;
				break;
			case 2:
				tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
				if (anjianfacebook
						&& ExternalMethods
								.CollisionTest(
										x,
										y,
										357
												* GameConfig.f_zoomx
												- GameStaticImage.s_interface_icon_facebook_02.bitmap
														.getWidth() / 2 * 0.2f,
										tempy
												+ 101
												* GameConfig.f_zoomy
												- GameStaticImage.s_interface_icon_facebook_02.bitmap
														.getHeight() / 2 * 0.2f,
										357
												* GameConfig.f_zoomx
												+ GameStaticImage.s_interface_icon_facebook_02.bitmap
														.getWidth() * 1.2f,
										tempy
												+ 101
												* GameConfig.f_zoomy
												+ GameStaticImage.s_interface_icon_facebook_02.bitmap
														.getHeight() * 1.2f)) {
					// TODO 登录facebook
					if(FacebookOperation.gameState!= FacebookOperation.GAME_STATE_INIT_LOADING)
						FacebookOperation.gameState= FacebookOperation.GAME_STATE_INIT_LOADING;
					FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.level_friend);
				} else if (anjiansystemfriend
						&& ExternalMethods
								.CollisionTest(
										x,
										y,
										Location.GameEquip.systemfriend_icon_xy[0]
												* GameConfig.f_zoomx
												- 10
												* GameConfig.f_zoomx,
										tempy + 102 * GameConfig.f_zoomy - 10
												* GameConfig.f_zoomy,
										Location.GameEquip.systemfriend_icon_xy[0]
												* GameConfig.f_zoomx
												+ GameStaticImage.s_share_ui_photo_02.bitmap
														.getWidth()
												+ 20
												* GameConfig.f_zoomx,
										tempy
												+ 102
												* GameConfig.f_zoomy
												+ GameStaticImage.s_share_ui_photo_02.bitmap
														.getHeight() + 20
												* GameConfig.f_zoomy)) {
					// TODO 换系统好友
					if (askfriend == 0) {
						askfriend = -1;
					} else {
						if (systemCD == 0){
							askfriend = 0;
							UserRequest.getUser().setFriendIconID(null);
						}
					}
				}

				anjianfacebook = false;
				break;
			}
			anjianbutton = false;
			anjianclose = false;
			isegg = false;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			switch (equipState) {
			case 0:
				if (ismove) {
					if (isCorrectCardMove) {
						isCorrectCardMove = false; // 纠正移动关闭等移动释放后继续纠正
						correctCard_move = Math.abs(correctCard_move);
					}
					int tempX = (int) event.getX();

					move_X += tempX - oldX;

					oldX = (int) event.getX();

					if (move_X > _card_s && _card.get(0).index == -1) {
						move_X = _card_s;
					} else if ((move_X < -_card_s && _card.size() < 5)
							|| (move_X < -_card_s && _card.get(5).index == -1)) {
						move_X = -_card_s;
					}
					if (move_X >= _card_s
							+ GameStaticImage.noCard[0].bitmap.getWidth()) {
//						move_X = 0;
						addLeftCard(sort_index);
					} else if (move_X <= -(_card_s + GameStaticImage.noCard[0].bitmap
							.getWidth())) {
//						move_X = 0;
						addRightCard(sort_index);
					}
					if (tempX - _x > 5 * GameConfig.f_zoom
							|| event.getY() - _y > 5 * GameConfig.f_zoom) {
						anjiancard = false;
					}
				}
				break;
			case 1:
				if(friends != null && friends.size()<=4)return;
				if (ismove) {
					if (isCorrectFriendMove) {
						isCorrectFriendMove = false; // 纠正移动关闭等移动释放后继续纠正
						correctFriend_move = Math.abs(correctFriend_move);
					}
					
					int tempX = (int) event.getX();

					move_X += tempX - oldX;

					oldX = (int) event.getX();

					if (move_X > _friend_s && _friends.get(0).index == -1) {
						move_X = _friend_s;
					} else if ((move_X < -_friend_s && _friends.size() < 5)
							|| (move_X < -_friend_s && _friends.get(5).index == -1)) {
						move_X = -_friend_s;
					}
					if (move_X >= _friend_s + _friend_sc) {
						move_X = 0;
						addLeftFriend();
					} else if (move_X <= -(_friend_s + _friend_sc)) {
						move_X = 0;
						addRightFriend();
					}
					if (tempX - _x > 5 * GameConfig.f_zoom
							|| event.getY() - _y > 5 * GameConfig.f_zoom) {
						anjianfriend = false;
					}
				}
				break;
			case 2:
				break;
			}

		}
	}

	private void replaceCard(int starLevel, int _sort_index) {
		_card.clear();
//		card_header2.clear();
//		card_partner2.clear();
//		card_str2.clear();
//		card_items2.clear();
		_card.add(new CardItem(-1, -1));
		switch (_sort_index) {
		case 0:
//			for (int i = starLevel; i < card_header.size() && i < 3 * 5; i += 3) {
			for (int i = starLevel; i < card_header.size(); i += 3) {
				if(VeggiesData.getCardNum(card_header.get(i).cardId)>0){
					_card.add(card_header.get(i));
//					card_header2.add(card_header.get(i));
				}
			}
			break;
		case 1:
//			for (int i = starLevel; i < card_partner.size() && i < 3 * 5; i += 3) {
			for (int i = starLevel; i < card_partner.size(); i += 3) {
				if(VeggiesData.getCardNum(card_partner.get(i).cardId)>0){
					_card.add(card_partner.get(i));
//					card_partner2.add(card_partner.get(i));
				}
			}
			break;
		case 2:
//			for (int i = starLevel; i < card_str.size() && i < 3 * 5; i += 3) {
			for (int i = starLevel; i < card_str.size() ; i += 3) {
				if(VeggiesData.getCardNum(card_str.get(i).cardId)>0){
					_card.add(card_str.get(i));
//					card_str2.add(card_str.get(i));
				}
			}
			break;
		case 3:
//			for (int i = starLevel; i < card_items.size() && i < 3 * 5; i += 3) {
			for (int i = starLevel; i < card_items.size() ; i += 3) {
				if(VeggiesData.getCardNum(card_items.get(i).cardId)>0){
					_card.add(card_items.get(i));
//					card_items2.add(card_items.get(i));
				}
			}
			break;
		}
		// _card.add(new CardItem(-1, -1));
	}

	private void addLeftCard(int _sort_index) {
//		for (int j = _card.size() - 1; j >= 0; j--) {
//			if (j > 0) {
//				_card.set(j, _card.get(j - 1));
//			} else {
//				if (_card.get(j).index <= 1) {
//					_card.set(j, new CardItem(-1, 0));
//				} else {
//					switch (_sort_index) {
//					case 0:
////						_card.set(
////								j,
////								card_header.get((_card.get(j).index - 2) * 3
////										+ starLevel_index));
//						_card.set(
//								j,
//								card_header2.get(j-1));
//						break;
//					case 1:
////						_card.set(
////								j,
////								card_partner.get((_card.get(j).index - 2) * 3
////										+ starLevel_index));
//						_card.set(
//								j,
//								card_partner2.get(j-1));
//						break;
//					case 2:
////						_card.set(
////								j,
////								card_str.get((_card.get(j).index - 2) * 3
////										+ starLevel_index));
//						_card.set(
//								j,
//								card_str2.get(j-1));
//						break;
//					case 3:
////						_card.set(
////								j,
////								card_items.get((_card.get(j).index - 2) * 3
////										+ starLevel_index));
//						_card.set(
//								j,
//								card_items2.get(j-1));
//						break;
//					}
//				}
//			}
//		}
	}

	private void addRightCard(int _sort_index) {
//		for (int j = 0; j < _card.size(); j++) {
//			if (j + 1 < _card.size()) {
//				_card.set(j, _card.get(j + 1));
//			} else {
//				switch (_sort_index) {
//				case 0:
//					if (_card.get(j).index >= card_header.size() / 3) {
//						_card.set(j, new CardItem(-1, 0));
//					} else {
////						_card.set(
////								j,
////								card_header.get(_card.get(j).index * 3
////										+ starLevel_index));
//						_card.set(
//								j,
//								card_header2.get(j+1));
//					}
//					break;
//				case 1:
//					if (_card.get(j).index >= card_partner.size() / 3) {
//						_card.set(j, new CardItem(-1, 0));
//					} else {
////						_card.set(
////								j,
////								card_partner.get(_card.get(j).index * 3
////										+ starLevel_index));
//						_card.set(
//								j,
//								card_partner2.get(j+1));
//					}
//					break;
//				case 2:
//					if (_card.get(j).index >= card_str.size() / 3) {
//						_card.set(j, new CardItem(-1, 0));
//					} else {
////						_card.set(
////								j,
////								card_str.get(_card.get(j).index * 3
////										+ starLevel_index));
//						_card.set(
//								j,
//								card_str2.get(j+1));
//					}
//					break;
//				case 3:
//					if (_card.get(j).index >= card_items.size() / 3) {
//						_card.set(j, new CardItem(-1, 0));
//					} else {
////						_card.set(
////								j,
////								card_items.get(_card.get(j).index * 3
////										+ starLevel_index));
//						_card.set(
//								j,
//								card_items2.get(j+1));
//					}
//					break;
//				}
//			}
//		}
	}

	private void addLeftFriend() {
		for (int j = _friends.size() - 1; j >= 0; j--) {
			if (j > 0) {
				_friends.set(j, _friends.get(j - 1));
			} else {
				if (_friends.get(j).index <= 1) {
					_friends.set(j, new AskFriend(-1, "", -1, "", null));
				} else {
					_friends.set(j, friends.get((_friends.get(j).index - 2)));
				}
			}
		}
	}

	private void addRightFriend() {
	
		for (int j = 0; j < _friends.size(); j++) {
			if (j + 1 < _friends.size()) {
				_friends.set(j, _friends.get(j + 1));
			} else {
				if (_friends.get(j).index >= friends.size()) {
					_friends.set(j, new AskFriend(-1, "", -1, "", null));
				} else {
					_friends.set(j, friends.get(_friends.get(j).index));
				}
			}
		}
	}
	//排列出优先读取首页卡片顺序
	public static void init_smallcardLoadList() {
		List<Integer> defaultAllCardSort = new ArrayList<Integer>();
		for(int i=0; i<VeggiesData.getAllCardNum().length; i++) {
			defaultAllCardSort.add(i+1);
		}
		for(int i=0; i<VeggiesData.getCardSlot().length; i++) {
			if(VeggiesData.getCardSlot()[i] > 0) {
				smallcard_load_sort.add(VeggiesData.getCardSlot()[i]);
			}
		}
		int[] oneStarZhuVeggies = {GameItem.cardSort[0][0],GameItem.cardSort[0][3],GameItem.cardSort[0][6]};
		for(int i=0; i<oneStarZhuVeggies.length; i++) {
			if(smallcard_load_sort.get(0) != oneStarZhuVeggies[i]) {
				smallcard_load_sort.add(oneStarZhuVeggies[i]);	
			}
		}
		for(int i=0; i<smallcard_load_sort.size(); i++) {
			for(int j=0; j<defaultAllCardSort.size(); j++) {
				if(smallcard_load_sort.get(i) == defaultAllCardSort.get(j)) {
					defaultAllCardSort.remove(j);
					continue;
				}
			}
		}
		smallcard_load_sort.addAll(defaultAllCardSort);
		if(Configs.isDebug) {
			for(int i=0; i<smallcard_load_sort.size(); i++) {
				System.out.print(">>"+smallcard_load_sort.get(i) + ",");
			}
		}
	}
	
	private int equipCardDown(boolean isGT, float x, float y){
		
		for (int i=0; i<Location.GameEquip.cardSlot_xy.length; i++) {
			if ( ExternalMethods.CollisionTest(x, y, 
					Location.GameEquip.cardSlot_xy[i][0] * GameConfig.f_zoomx, 
					Location.GameEquip.cardSlot_xy[i][1] * GameConfig.f_zoomy, 
					Location.GameEquip.cardSlot_xy[i][0] * GameConfig.f_zoomx + GameStaticImage.noCard[0].bitmap.getWidth(), 
					Location.GameEquip.cardSlot_xy[i][1] * GameConfig.f_zoomy + GameStaticImage.noCard[0].bitmap.getHeight())) {
				if(isGT ){
					if((GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL7 && i == 1)
					|| (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL9 && i == 5)
					|| (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL16 && i == 7)
					|| (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL40 && i == 4)){
						
					}else
						return -1;
				}	
				if(i == Location.GameEquip.cardSlot_xy.length-1 ){ //好友新手引导完毕
					 if(VeggiesData.getGameGuanka()[6] >= 0){
						 anjian_equip[i] = true;
					 }
				}else{
					if((i == 3 || i == 6 ) || VeggiesData.getCardSlot()[i] != -1) //3 6 是可以拿钱买的
						anjian_equip[i] = true;
					else if(VeggiesData.getCardSlot()[i] == -1){
							String temp = "";
							switch(i){
							case 1:
								temp = LangUtil.getLangString(LangDefineClient.UNLOCK_slot1);
								break;
							case 2:
								temp = LangUtil.getLangString(LangDefineClient.UNLOCK_slot2);
								break;
							case 4:
								temp = LangUtil.getLangString(LangDefineClient.UNLOCK_slot4);
								break;
							case 5:
								temp = LangUtil.getLangString(LangDefineClient.UNLOCK_slot5);
								break;
							}
							GameManager.setPopUp(PopUp.GOOGS, GameStaticImage.interface_card_lock, new PopUp(temp) {
							});
					}
				}
				return i;
			}
		}
		
		return -1;
	}

	private int equipCardUP(boolean isGT, float x, float y){
		for (int i=0; i<Location.GameEquip.cardSlot_xy.length; i++) {
			if (anjian_equip[i] && ExternalMethods.CollisionTest(x, y, 
					Location.GameEquip.cardSlot_xy[i][0] * GameConfig.f_zoomx, 
					Location.GameEquip.cardSlot_xy[i][1] * GameConfig.f_zoomy, 
					Location.GameEquip.cardSlot_xy[i][0] * GameConfig.f_zoomx + GameStaticImage.noCard[0].bitmap.getWidth(), 
					Location.GameEquip.cardSlot_xy[i][1] * GameConfig.f_zoomy + GameStaticImage.noCard[0].bitmap.getHeight())) {
				if(isGT ){
					if((GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL7 && i == 1)
					|| (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL9 && i == 5)
					|| (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL16 && i == 7)
					|| (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL40 && i == 4)){
						
					}else
						return -1;
				}
				if (i != 7 && equipState != 0) {
					equipState = 0;
				}
				int index_temp = cursor_index;
				cursor_index = i;
				switch (cursor_index) {
				case 0:
					sort_index = 0;
					break;
				case 1:
				case 2:
					sort_index = 1;
					break;
				case 3:
					//是否解锁
					if(VeggiesData.getCardSlot()[i] == -1){
						if(VeggiesData.getGem()<GameItem.equipPrice[i]){
							String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
							GameManager.setPopUp(PopUp.GOOGS,GameStaticImage.shop_gem_13, new PopUp(temp) {
							});
							cursor_index = index_temp;
						}else{
							final int  i_index = i;
							String temp = LangUtil.getLangString(LangDefineClient.UNLOCK_slot3);
							GameManager.setPopUp(PopUp.GOOGS_BUTTON, GameStaticImage.interface_card_lock, new PopUp(GameStaticImage.shop_gem_12, "*"+GameItem.equipPrice[i], temp, null, 0, false) {
								@Override
								public byte onTouch(MotionEvent event) {
									// TODO Auto-generated method stub
									byte temp = super.onTouch(event);
									if(temp == PopUp.onTouch_googsExit){
										VeggiesData.addGem(-GameItem.equipPrice[i_index]);
										deblockingid = i_index;
										r = 1.2f;
										deblockingIndex = 0;
										sort_index = 1;
										return -1;
									}
									return temp;
								}
							});
						}
					}else
						sort_index = 1;
					break;
				case 4:
					sort_index = 2;
					break;
				case 5:
					sort_index = 3;
					break;
				case 6:
					//是否解锁
					if(VeggiesData.getCardSlot()[i] == -1){
						if(VeggiesData.getGem()<GameItem.equipPrice[i]){
							String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
							GameManager.setPopUp(PopUp.GOOGS,GameStaticImage.shop_gem_13, new PopUp(temp) {
							});
							cursor_index = index_temp;
						}else{
							final int  i_index = i;
							String temp = LangUtil.getLangString(LangDefineClient.UNLOCK_slot6);
							GameManager.setPopUp(PopUp.GOOGS, GameStaticImage.interface_card_lock, new PopUp(temp) {
								@Override
								public byte onTouch(MotionEvent event) {
									// TODO Auto-generated method stub
									byte temp = super.onTouch(event);
									if(temp == PopUp.onTouch_googsExit){
										VeggiesData.addGem(-GameItem.equipPrice[i_index]);
										deblockingid = i_index;
										r = 1.2f;
										deblockingIndex = 0;
										sort_index = 3;
										return -1;
									}
									return temp;
								}
							});
							
						
							
							
							
						}
					}else
						sort_index = 3;
					break;
				case 7:
//					if (ChooseLevelModule2.gt.getTeachId()==GameTeaching.TEACH_VOL7) {
//						ChooseLevelModule2.gt.finish();
//						new VeggiesData().saveGame();
//					}
					if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL16]){
						if(!(GameManager.getGT()!=null && GameManager.getGT().pauseState())){
							String temp = LangUtil.getLangString(LangDefineClient.UNLOCK_slot7);
							GameManager.setPopUp(PopUp.GOOGS, GameStaticImage.interface_card_lock, new PopUp(temp) {
							});
						}
					}
					if (equipState != 2) {
						// 判断是否登陆过
						if (!FacebookOperation.isLanding) { // 没有登录
							equipState = 2; // 2为登录
						} else if (FacebookOperation.isLanding
								&& !FacebookOperation.getFacebook()
										.getLoadingFriend()) {
							// 因为 在关卡详情界面肯定发送好友请求了
							// FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_USER_VIEW);
							equipState = 1; // 显示好友
						} else {
							equipState = 1; // 显示好友
						}

					}
					break;
				}
				if (i != 7 && VeggiesData.getCardSlot()[i] != -1) {
					replaceCard(starLevel_index, sort_index);
				} 
				return i;
			}
		}
		return -1;
	}
	
	public void onTouchDown(boolean isGT, float x, float y){
		float temp_X = _card_x;
		float temp_Y = _card_y;
		for (int i=1; i<(_card.size()); i++) {
			if (ExternalMethods.CollisionTest(x, y, 
					move_X + temp_X + (-1 + i) * (GameStaticImage.noCard[0].bitmap.getWidth() + _card_s), temp_Y, 
					move_X + temp_X + (-1 + i) * (GameStaticImage.noCard[0].bitmap.getWidth() + _card_s) + GameStaticImage.noCard[0].bitmap.getWidth(), temp_Y + GameStaticImage.noCard[0].bitmap.getHeight())) {
				if(isGT ){
					if( i == 1){
						anjiancard = true;
						_card_cardId = _card.get(i).cardId;
						_card_index = i;
					}
				}else{
					anjiancard = true;
					
					//长按跳图鉴
					_card_cardId = _card.get(i).cardId;
					longtouchcard = true;
					oldtime = System.currentTimeMillis();
					_card_index = i;
				}
			}
	}
	}
	
	public int onTouchCardUP(boolean isGT, float x, float y){
		float temp_X = _card_x;
		float temp_Y = _card_y;
		for (int i=1; i<_card.size(); i++) {
			if (anjiancard && ExternalMethods.CollisionTest(x, y, 
					move_X +temp_X + (-1 + i) * (GameStaticImage.noCard[0].bitmap.getWidth() + _card_s), temp_Y, 
					move_X +temp_X + (-1 + i) * (GameStaticImage.noCard[0].bitmap.getWidth() + _card_s) + GameStaticImage.noCard[0].bitmap.getWidth(), temp_Y + GameStaticImage.noCard[0].bitmap.getHeight())) {
//				ChooseLevelModule.sendMessage("卡片索引为 " + _card.get(i).cardId);
				if(isGT){
					if(i ==1){
					
					}else
						return -1;
				}
				if (cursor_index!= -1 && 
						VeggiesData.getCardSlot()[cursor_index] != -1 
						&& VeggiesData.getAllCardNum()[_card.get(i).cardId-1] > 0 && VeggiesData.getCardSlot()[cursor_index] != _card.get(i).cardId) {
					switch(sort_index) {
					case 0:
					case 2:
						VeggiesData.getCardSlot()[cursor_index] = _card.get(i).cardId;
						break;
					case 1:
						if (cursor_index == 1) {
//							if (VeggiesData.getCardSlot()[2] != _card.get(i).cardId
//									&& VeggiesData.getCardSlot()[3] != _card.get(i).cardId) {
//								// VeggiesData.cardSlot[cursor_index] =
//								// _card.get(i).cardId;
//								VeggiesData.setCardSlot(cursor_index,_card.get(i).cardId);
//							}
							VeggiesData.setCardSlot(cursor_index,_card.get(i).cardId);
							if((_card.get(i).cardId-1)/3==(VeggiesData.getCardSlot()[2]-1)/3){
								VeggiesData.getCardSlot()[2]=0;
							}
							if((_card.get(i).cardId-1)/3==(VeggiesData.getCardSlot()[3]-1)/3){
								VeggiesData.getCardSlot()[3]=0;
							}
						} else if (cursor_index == 2) {
//							if (VeggiesData.getCardSlot()[1] != _card.get(i).cardId
//									&& VeggiesData.getCardSlot()[3] != _card.get(i).cardId) {
//								// VeggiesData.cardSlot[cursor_index] =
//								// _card.get(i).cardId;
//								VeggiesData.setCardSlot(cursor_index,_card.get(i).cardId);
//							}
							VeggiesData.setCardSlot(cursor_index,_card.get(i).cardId);
							if((_card.get(i).cardId-1)/3==(VeggiesData.getCardSlot()[1]-1)/3){
								VeggiesData.getCardSlot()[1]=0;
							}
							if((_card.get(i).cardId-1)/3==(VeggiesData.getCardSlot()[3]-1)/3){
								VeggiesData.getCardSlot()[3]=0;
							}
						} else if (cursor_index == 3) {
//							if (VeggiesData.getCardSlot()[2] != _card.get(i).cardId
//									&& VeggiesData.getCardSlot()[1] != _card.get(i).cardId) {
//								// VeggiesData.cardSlot[cursor_index] =
//								// _card.get(i).cardId;
//								VeggiesData.setCardSlot(cursor_index,_card.get(i).cardId);
//							}
							VeggiesData.setCardSlot(cursor_index,_card.get(i).cardId);
//							System.out.println("clpqy:"+_card.get(i).cardId);
//							System.out.println("clpqy:[1]"+VeggiesData.getCardSlot()[1]);
//							System.out.println("clpqy:[2]"+VeggiesData.getCardSlot()[2]);
							if((_card.get(i).cardId-1)/3==(VeggiesData.getCardSlot()[1]-1)/3){
								VeggiesData.getCardSlot()[1]=0;
							}
							if((_card.get(i).cardId-1)/3==(VeggiesData.getCardSlot()[2]-1)/3){
								VeggiesData.getCardSlot()[3]=0;
							}
						}
						break;
					case 3:
						if (cursor_index == 5) {
//							if (VeggiesData.getCardSlot()[6] != _card
//									.get(i).cardId) {
//								// VeggiesData.cardSlot[cursor_index] =
//								// _card.get(i).cardId;
//								VeggiesData.setCardSlot(cursor_index,
//										_card.get(i).cardId);
//							}
							VeggiesData.setCardSlot(cursor_index,_card.get(i).cardId);
							if((_card.get(i).cardId-1)/3==(VeggiesData.getCardSlot()[6]-1)/3){
								VeggiesData.getCardSlot()[6]=0;
							}
						} else if (cursor_index == 6) {
//							if (VeggiesData.getCardSlot()[5] != _card
//									.get(i).cardId) {
//								// VeggiesData.cardSlot[cursor_index] =
//								// _card.get(i).cardId;
//								VeggiesData.setCardSlot(cursor_index,
//										_card.get(i).cardId);
//							}
							VeggiesData.setCardSlot(cursor_index,_card.get(i).cardId);
							if((_card.get(i).cardId-1)/3==(VeggiesData.getCardSlot()[5]-1)/3){
								VeggiesData.getCardSlot()[5]=0;
							}
						}
						break;
					}
					return i;
				} else if (!isGT && cursor_index!= -1 && VeggiesData.getCardSlot()[cursor_index] != -1 && VeggiesData.getAllCardNum()[_card.get(i).cardId-1] > 0 && VeggiesData.getCardSlot()[cursor_index] == _card.get(i).cardId) {
					//VeggiesData.cardSlot[cursor_index] = 0;
					VeggiesData.setCardSlot(cursor_index, 0);
					if(cursor_index==0){
						VeggiesData.setCardSlot(cursor_index, GameItem.Item01);
					}
				}
				return i;
			}
		}
		return -1;
	}
	
	private void play_button(){
		if(GameTeaching.teachingArrary[GameTeaching.TEACH_VOL9] && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL40]){
			return;
		}
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
					return temp;
				}
			});
			anjianbutton = false;
			return;
		}

		// 减掉装备卡片
		for (int i = 0; i < VeggiesData.getCardSlot().length - 2; i++) {
			if(i == 0 && VeggiesData.getCardSlot()[0] == GameItem.Item01){
				 //表示箩卜不减少次数
			}else if (VeggiesData.getCardSlot()[i] > 0 ) {
				if (VeggiesData.getCardNum(VeggiesData.getCardSlot()[i]) > 0) {
					int _index = VeggiesData.getCardSlot()[i];
					VeggiesData.setAllCardNum(_index, -1);
					Vector<Integer> card = VeggiesData.getCardnewIcon();
					for(int k=0;k<card.size();++k){
						if(VeggiesData.getCardIndex(_index) == (card.get(k).intValue())){
							VeggiesData.setDeleteCardNewId(VeggiesData.getCardIndex(_index));
							break;
						 }
					}
				} 
			}
		}

		if (askfriend == 0 && systemCD == 0) {
			VeggiesData.systemtime = System.currentTimeMillis();
		} else if (askfriend > 0 && _friends.get(askfriend).cd == 0) {
			_friends.get(askfriend).endTime = System
					.currentTimeMillis();
			friends.get(_friends.get(askfriend).index - 1).endTime = System
					.currentTimeMillis();
		}

		//空为系统邮箱
		if(UserRequest.getUser().getFriendIconID() == null){
			systemFriendTime = System.currentTimeMillis()+(18000*30);
		}else{
			FaceBookPlayer player = FacebookOperation.playerMap.get(UserRequest.getUser().getFriendIconID());
			if(player!=null){
				player.setFriendHelpTime(18000*30);
				UserRequest.getUser().ReqFriendTime(player.getid_server(), UserRequest.UPDATE_AID_TIME);
			}
		}
		
		
		if(!VeggiesData.isStory[1] && VeggiesData.getCurrentLevel() == 30){
			GameManager.ResetToRunModule(new GameStory2());
		}else{
			new VeggiesData().saveGame();

			isGotoGame = true;
			Log.e("new GameMain()", "new GameMain()=0");
			GameManager.ResetToRunModule(new GameMain());
//			GameManager.ResetToRunModule(new TurnGameMain());
		}
	}
	
	@Override
	public void onIcon(FaceBookPlayer playerIcon) {
		// TODO Auto-generated method stub
		if(friends!=null)
			for(int i=0;i<friends.size();++i){
				AskFriend friend = friends.get(i);
				if(friend.name.equals(playerIcon.getName())){
					int width = share_ui_photo_04.bitmap.getWidth();
					int height = share_ui_photo_04.bitmap.getHeight();
					// 图片缩放
					Bitmap temp = FBInterface.allIconMap.get(playerIcon.getId_facebook());//user.getIcon();
					if(temp!=null){
						temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx,
								height - 6 * GameConfig.f_zoomy);
					} 
					friend.setIcon(temp);
				}
			}
	}
	Vector<FaceBookPlayer> temp  = null;
	@Override
	public void onFreindHelpTime(FaceBookPlayer helptime) {
		// TODO Auto-generated method stub
		if(friends!=null){ //有可能数据过来了 还没初始化完整
			for(int i=0;i<friends.size();++i){
				AskFriend friend = friends.get(i);
				if(friend.name.equals(helptime.getName())){
					friend.setendTime(helptime.getFriendHelpTime());
				}
			}
		}else{ //先保存起来
			if(temp == null)
				temp = new Vector<FaceBookPlayer>();
			temp.add(helptime);
		}
		
		
		if(temp!=null && friends!=null){
			for(int j=0;j<temp.size();++j){
					for(int i=0;i<friends.size();++i){
						AskFriend friend = friends.get(i);
						if(friend.name.equals(temp.get(j).getName())){
							friend.setendTime(temp.get(j).getFriendHelpTime());
							temp.remove(j);
							break;
						}
					}
			}
		}
		
		
	}
	
}
