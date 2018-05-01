package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.R;
import com.facebook.FacebookOperation;
import com.facebook.UserRequest;
import com.facebook.UserRequest.UserTopList;
import com.facebook.sdk.FBInterface;
import com.facebook.sdk.FBUser;
import com.game.data.FaceBookPlayer;
import com.game.data.FaceBookPlayer.ServerCallback;
import com.game.data.TopListData;
//import com.game.item.SuccessItem;
import com.game.item.SuccessItem;
import com.game.item.TopListItem;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.protocol.response.ack.UserViewAck;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.socoGameEngine.TextBox;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

/**
 * 排行榜和成就界面
 * @author Administrator
 *
 */
public class RankingSuccessModule extends Module{
	public static Vector<TopListData> toplist;
	//每关卡的奖励金币
	public static final int getgold[] = {100, 100, 50, 500, 50, 500, 100, 100, 500, 100};
	public static final boolean isGet[] = {false, false, true, false, true, false, false, true, false, true};
	
	
//	private Vector<TopListItem> listItem;
	public static final byte NUMBER = 0; //分数排行榜
	public static final byte SUCCESS = 1; //成就排行榜
	private byte state = -1; 
	
	private Sprite gs;
	boolean anjianclose;
	boolean anjianbutton;
	
	private Sprite s_share_ui_back_01;
	private Sprite s_share_ui_back_02;
	private Sprite s_share_ui_back_02_2;
	private Sprite s_share_ui_close;
	private Sprite[] s_share_ui_back_04;
	private Sprite[] s_word_number;
	private Sprite[] s_word_honor;
	private Sprite[] loading;
	public  Sprite[] s_share_ui_button_01;
	public  Sprite[] s_share_ui_button;
	private Sprite word_title_facebook;
	private Sprite word_key_invite;
	private Sprite share_ui_back_08;
	private Sprite[] arrow;
	
	//自己的排名
	private TextBox name;
	private TextBox score;
	private TextBox rank;
	private Sprite s_icon;
	private Paint paint;
	private Typeface fontFace;
	private int tempi = 0;
	private int bgxy[][] = {{76, 67},{275, 67},};
	private int titlexy[][] = {{99, 83},{304, 83}};
	//关卡进度
	int ok_num[] = {2, 100, 95, 100, 76, 2, 10, 60, 100, 100};
	int size_num[] = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
	int state_num[];
	String success_str[];
	public String successName[] = {GameStaticImage.success_S_back_1,
		GameStaticImage.success_key_get, GameStaticImage.success_S_course_1, 
		GameStaticImage.success_S_course_2, GameStaticImage.success_S_gold, 
		GameStaticImage.share_ui_back_03, GameStaticImage.shop_reward,
		GameStaticImage.shop_reward_2, GameStaticImage.success_shop_gem_14
	};
	
	public String successIcon[] = {GameStaticImage.success_S_icon_01, GameStaticImage.success_S_icon_05,
			GameStaticImage.success_S_icon_09, GameStaticImage.success_S_icon_04, GameStaticImage.success_S_icon_06,
			GameStaticImage.success_S_icon_07, GameStaticImage.success_S_icon_03, GameStaticImage.success_S_icon_02,
			GameStaticImage.success_S_icon_08, GameStaticImage.success_S_icon_10};
	
	
	
	//这些都是公用的图片
	public Sprite success[];
	public Sprite success_S_num_1[];
//	private TextBox content[];
//	private int[] word_move_y = {0,0,0,0,0,0,0,0,0,0};
//	private boolean[] isScrollUp = {false,false,false,false,false,false,false,false,false,false};
//	boolean su_anjianbutton[];
	// 中间的icon
	private Sprite icon[];
	
	private float _library_x,_library_y,_library_w,_library_h,_library_w_s,_library_h_s;
	private float move_Y;
	private float oldY;
	private float _x,_y;
	private boolean ismove;
	boolean isCorrectCardMove;
	float correctCard_move = 5 * GameConfig.f_zoom;
	

//	private SuccessItem item[];
	private List<SuccessItem> success_library;
	private List<SuccessItem> successItem_library;	
	private List<TopListItem> _library;
	private List<TopListItem> itemLibrary;
	int textheight = 64;
	int noCardWidth;
	int noCardHeight;
	public RankingSuccessModule(byte index){
//		state = NUMBER;
		state = index;
		if(index == SUCCESS){
			VeggiesData.setShow_success(false);
		}
	}
	
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		
		if (FacebookOperation.isLanding && toplist == null 
			) {
			
			FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_TOPLIST);
			
		}else if(FacebookOperation.isLanding && toplist == null){
			FacebookOperation.gameState = FacebookOperation.GAME_STATE_INIT_LOADING;
			FacebookOperation.getFacebook().landingAndInvite( FacebookOperation.topList);
		}
		arrow = new Sprite[2];
		arrow[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_03));
		arrow[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_04));
		
		if(toplist==null){
			UserRequest.getUser().setTopListInt(new UserTopList() {
				@Override
				public void onTopList() {
					// TODO Auto-generated method stub
						  initItem();
				}
			});
		}
		// 没有好友需要请求好友
		if (toplist!=null && FacebookOperation.isLanding) { 
			initItem();
		} 
		
//		//测试需要删除的
//		if(RankingSuccessModule.toplist == null)
//        	RankingSuccessModule.toplist = new Vector<TopListData>();
//		for(int i=0;i<10;++i){
//			RankingSuccessModule.toplist.add(new TopListData("牙买加"+i, i, 400, "3434344", i));
//		}
//		initItem();
		gs = new Sprite();
		
		s_share_ui_back_01 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
		s_share_ui_back_02 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
		s_share_ui_back_02_2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
		s_share_ui_close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
		
		share_ui_back_08 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_08));
		
		s_share_ui_back_04 = new Sprite[3];
		s_share_ui_back_04[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_04));
		s_share_ui_back_04[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05));
		s_share_ui_back_04[2] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_05_1));
		
		s_word_number = new Sprite[2];
		s_word_honor = new Sprite[2];
		s_word_number[0] = new Sprite(GameImage.getImage(GameStaticImage.word_title_ranking));
		s_word_number[1] = new Sprite(GameImage.getImage(GameStaticImage.word_title_ranking_2));
		s_word_honor[0] = new Sprite(GameImage.getImage(GameStaticImage.word_title_success));
		s_word_honor[1] = new Sprite(GameImage.getImage(GameStaticImage.word_title_success_2));
		word_title_facebook = new Sprite(GameImage.getImage(GameStaticImage.word_title_facebook));
		word_key_invite = new Sprite(GameImage.getImage(GameStaticImage.word_key_invite));
		loading = GameImage.getAutoSizecutSprite(GameStaticImage.share_loading_03, 9, 1, GameImage.Sort_line);

		s_share_ui_button_01 = new Sprite[3];
		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05));
		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05_1));
		s_share_ui_button_01[2] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_05_2));
		
		s_share_ui_button = new Sprite[2];
		s_share_ui_button[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
		s_share_ui_button[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
		
		tempi = 0;
		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(),
		"font/ARLRDBD.TTF");
		paint = new Paint();
		paint.setTextSize(26 * GameConfig.f_zoom);
		paint.setTypeface(fontFace);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);

		int size = successName.length;
		success = new Sprite[size];
		for(int i=0;i<size;++i){
			success[i] = new Sprite(GameImage.getImage(successName[i]));
		}
		//这是数组 也是个数组要单独处理
		success_S_num_1  = GameImage.getAutoSizecutSprite(GameStaticImage.success_S_num_1, 11, 1, GameImage.Sort_line);
		
		int sizeicon = successIcon.length;
		icon = new Sprite[sizeicon];
		for(int i=0;i<sizeicon;++i){
		 icon[i] = new Sprite(GameImage.getImage(successIcon[i]));
		}
		
		
		
		
		success_library = new ArrayList<SuccessItem>();
	 
		
//		item = new SuccessItem[1];
//		for(int i=0;i<item.length;++i){
//			item[i] = new SuccessItem(i+1, this, i+1);
//		}
//		
		 
		if(state == NUMBER){ //分数
			 
		}else{
			_library_w_s = 202 * GameConfig.f_zoomx - success[5].bitmap.getWidth();
			_library_h_s = 268 * GameConfig.f_zoomy -success[5].bitmap.getHeight();
			_library_w = _library_w_s * 2 +success[5].bitmap.getWidth() * 2;
			_library_x = (int)(79 * GameConfig.f_zoomx);
			_library_y = (int)(139 * GameConfig.f_zoomy);
			_library_h = (int)(650 * GameConfig.f_zoomy);
		}
		 
		success_str = new String[10];
		success_str[0]= LangUtil.getLangString(LangDefineClient.SUCCESS_1);
		success_str[1]= LangUtil.getLangString(LangDefineClient.SUCCESS_2);
		success_str[2]= LangUtil.getLangString(LangDefineClient.SUCCESS_3);
		success_str[3]= LangUtil.getLangString(LangDefineClient.SUCCESS_4);
		success_str[4]= LangUtil.getLangString(LangDefineClient.SUCCESS_5);
		success_str[5]= LangUtil.getLangString(LangDefineClient.SUCCESS_6);
		success_str[6]= LangUtil.getLangString(LangDefineClient.SUCCESS_7);
		success_str[7]= LangUtil.getLangString(LangDefineClient.SUCCESS_8);
		success_str[8]= LangUtil.getLangString(LangDefineClient.SUCCESS_9);
		success_str[9]= LangUtil.getLangString(LangDefineClient.SUCCESS_10); 
		
		ok_num = VeggiesData.getAchievementSchedule();
		 
		size_num  = VeggiesData.getAchievementDes();
		state_num = VeggiesData.getAchievement();
		successItem_library = new ArrayList<SuccessItem>();
		
		int id[] = {
				//登陆FACEBOOK
				Achievement.LOGIN_FACEBOOK,
				//强化一次弹弓intensify
				Achievement.INTENSIFY_ONCE_Slingshot,
				//强化城墙到最高级
				Achievement.INTENSIFY_MAX_Fence,
				//击败一次BOSS
				Achievement.DEFEAT_ONCE_BOSS,
				//累计使用100个道具
				Achievement.USE_ONEHUNDRED_ITEMS,
				//累计评价100颗星星
				Achievement.GET_ONEHUNDRED_STARS,
				//获得1张三星卡
				Achievement.GET_THREE_STAR_CARD,
				//三星评价通过60关
				Achievement.GET_ALL_THREE_STAR_LEVLES,
				//累计使用100次燃烧
				Achievement.USE_ONEHUNDRED_BURN,
				//集齐10种三星蔬菜
				Achievement.GET_TEN_THREESTAR_CARDS,
		};
		
		for (int i=0; i<10; i++) {
			successItem_library.add(new SuccessItem(isGet[i], this, i+1,  id[i]+1, success_str[i]));
		
			successItem_library.get(i).setNum(state_num[i], ok_num[i], size_num[i]);
			successItem_library.get(i).setGold(getgold[i]);
		}
//		content = new TextBox[10];
//		su_anjianbutton = new boolean[10];
		
	 
		
		
		
		
		replaceCard();
 
		return false;
	}
	private void replaceCard() {
		success_library.clear();
		success_library.add(new SuccessItem(false, null, -1, -1, ""));
		success_library.add(new SuccessItem(false, null, -1, -1, ""));
		for (int i=0; i<successItem_library.size()&& i<6; i++) {
			success_library.add(successItem_library.get(i));			
		}
	}
	private void initItem() {
//		listItem = new Vector<TopListItem>();
 
		
		_library = new ArrayList<TopListItem>();
		itemLibrary = new ArrayList<TopListItem>();
//		for (int i=0; i<GameItem.cardLibrary[1].length; i++) {
//			itemLibrary.add(new CardItem(i+1, GameItem.cardLibrary[1][i]));
//		}
		int j = 0;
		for(TopListData data:toplist){
			FBUser user = data.getFaceBookUser();
			if(user!=null){
				itemLibrary.add(new TopListItem(j+1, data.orderIndex, user.getName(), ""+data.orderScore, FBInterface.allIconMap.get(user.getId())));
				itemLibrary.add(new TopListItem(j+1, data.orderIndex, user.getName(), ""+data.orderScore, FBInterface.allIconMap.get(user.getId())));
				itemLibrary.add(new TopListItem(j+1, data.orderIndex, user.getName(), ""+data.orderScore, FBInterface.allIconMap.get(user.getId())));
			}else{
				itemLibrary.add(new TopListItem(j+1, data.orderIndex, data.otherId, ""+data.orderScore, null));
				itemLibrary.add(new TopListItem(j+1, data.orderIndex, data.otherId, ""+data.orderScore, null));
				itemLibrary.add(new TopListItem(j+1, data.orderIndex, data.otherId, ""+data.orderScore, null));
			}
			j++;
		}
		
		for (int i=0; i<itemLibrary.size(); i++) {
			itemLibrary.get(i).index = i+1;
		}
		
		_library.clear();
		_library.add(new TopListItem(-1, -1, "", "", null));	
		_library.add(new TopListItem(-1, -1, "", "", null));	
		_library.add(new TopListItem(-1, -1, "", "", null));	
		for (int i=0; i<itemLibrary.size()&& i<18; i++) {
			_library.add(itemLibrary.get(i));			
		}
		isCorrectCardMove = false;
		
		if(FBInterface.user != null){
			name = new TextBox();
			name.setTextAlign(TextBox.LEFT);
			name.setString(FBInterface.user.getName());
			name.setTextSize((int)(22*GameConfig.f_zoom));
			name.setDefaultColor(0xff99673b);
			name.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)name.height());
			
			if(FacebookOperation.player.getOrderIndex() != FaceBookPlayer.I_NULL){
				score = new TextBox();
				score.setTextAlign(TextBox.LEFT);
				score.setString(""+FacebookOperation.player.getOrderScore());
				score.setTextSize((int)(18*GameConfig.f_zoom));
				score.setDefaultColor(0xffBD6D18);
				score.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)score.height());
				
				rank = new TextBox();
				rank.setTextAlign(TextBox.LEFT);
				rank.setString(""+FacebookOperation.player.getOrderIndex());
				rank.setTextSize((int)(30*GameConfig.f_zoom));
				rank.setDefaultColor(0xffBD6D18);
				rank.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)rank.height());
			 
			}
			
			if(FacebookOperation.player.getOrderIndex() == FaceBookPlayer.I_NULL)
				FacebookOperation.player.setServer(new ServerCallback() {
					
					@Override
					public void onComplete(String fb_id, UserViewAck userViewAck, Exception exception) {
						if(userViewAck.getUid()!=0){
							FacebookOperation.player.setid_server(userViewAck.getUid());
							FacebookOperation.player.setOrderIndex(userViewAck.getOrderIndex());
							FacebookOperation.player.setOrderScore(userViewAck.getOrderScore());
							FacebookOperation.player.setMaxLevel(userViewAck.getGameLevel());
							score = new TextBox();
							score.setTextAlign(TextBox.LEFT);
							score.setString(""+FacebookOperation.player.getOrderScore());
							score.setTextSize((int)(18*GameConfig.f_zoom));
							score.setDefaultColor(0xffBD6D18);
							score.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)score.height());
							
							rank = new TextBox();
							rank.setTextAlign(TextBox.LEFT);
							rank.setString(""+FacebookOperation.player.getOrderIndex());
							rank.setTextSize((int)(30*GameConfig.f_zoom));
							rank.setDefaultColor(0xffBD6D18);
							rank.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)rank.height());
						 
						}
					}
				});
		}
		
		noCardWidth = TopListItem.share_ui_photo_04.bitmap.getWidth();
		noCardHeight = TopListItem.share_ui_photo_04.bitmap.getHeight();
		if(state == NUMBER){ //分数
			_library_w_s = (226 - 104) * GameConfig.f_zoomx - noCardWidth;
			_library_h_s = ((714 - 183) * GameConfig.f_zoomy - 6 * noCardHeight - arrow[0].bitmap.getHeight()) / 6;
			_library_w = _library_w_s * 4 + noCardWidth * 4;
			_library_h = _library_h_s * 6 + noCardHeight * 6-30* GameConfig.f_zoomy;
			_library_x = (45 + 6) * GameConfig.f_zoomx - _library_w_s / 2;
			_library_y = (148 + 12) * GameConfig.f_zoomy - _library_h_s / 2;
		}
		
	}
	
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		gs.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		int y = 0;
		s_share_ui_back_01.drawBitmap(canvas, null, (int)(29 * GameConfig.f_zoomx), (int)(50 * GameConfig.f_zoomy), (int)(472 * GameConfig.f_zoomx), (int)(757 * GameConfig.f_zoomy), -1);
		
		if(state == NUMBER){
			s_share_ui_back_02.drawBitmap(canvas, null, (int)(46 * GameConfig.f_zoomx), (int)(135 * GameConfig.f_zoomy), (int)(438 * GameConfig.f_zoomx), (int)(578 * GameConfig.f_zoomy), -1);
		}else
			s_share_ui_back_02.drawBitmap(canvas, null, (int)(46 * GameConfig.f_zoomx), (int)(135 * GameConfig.f_zoomy), (int)(438 * GameConfig.f_zoomx), (int)(650 * GameConfig.f_zoomy), -1);
		
		
		if(state == NUMBER){
			share_ui_back_08.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)((50+575) * GameConfig.f_zoomy), (int)(438 * GameConfig.f_zoomx), (int)(86 * GameConfig.f_zoomy), -1);
		}
		if(state == NUMBER){
	 		s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(135 * GameConfig.f_zoomy), (int)(438 * GameConfig.f_zoomx), (int)(578 * GameConfig.f_zoomy), -1);
		}else
			s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(135 * GameConfig.f_zoomy), (int)(438 * GameConfig.f_zoomx), (int)(650 * GameConfig.f_zoomy), -1);
		
		
		s_share_ui_close.drawBitmap(canvas, 450 * GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 41 * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
		
//		for (int i=0; i<bgxy.length; i++) {
//			if (i == state) {
//				s_share_ui_back_04[1].drawBitmap(canvas, null, (int)(bgxy[i][0] * GameConfig.f_zoomx), (int)((bgxy[i][1]) * GameConfig.f_zoomy), (int)(168 * GameConfig.f_zoomx), (int)(54 * GameConfig.f_zoomy), -1);
//				s_share_ui_back_04[2].drawBitmap(canvas, s_share_ui_back_04[2].bitmap, (bgxy[i][0] + 73)* GameConfig.f_zoomx, (bgxy[i][1] + 55)* GameConfig.f_zoomy, null);
//			} else {
//				s_share_ui_back_04[0].drawBitmap(canvas, null, (int)(bgxy[i][0] * GameConfig.f_zoomx), (int)((bgxy[i][1]) * GameConfig.f_zoomy), (int)(168 * GameConfig.f_zoomx), (int)(54 * GameConfig.f_zoomy), -1);
//				
//			}
//			if (state == NUMBER) {
//				s_word_number[0].drawBitmap(canvas, s_word_number[0].bitmap, titlexy[0][0] * GameConfig.f_zoomx, (titlexy[0][1]) * GameConfig.f_zoomy, null);					
//				s_word_honor[1].drawBitmap(canvas, s_word_honor[1].bitmap, titlexy[1][0] * GameConfig.f_zoomx, (titlexy[1][1]) * GameConfig.f_zoomy, null);
//			} else {
//				s_word_number[1].drawBitmap(canvas, s_word_number[1].bitmap, titlexy[0][0] * GameConfig.f_zoomx, (titlexy[0][1]) * GameConfig.f_zoomy, null);
//				s_word_honor[0].drawBitmap(canvas, s_word_honor[0].bitmap, titlexy[1][0] * GameConfig.f_zoomx, (titlexy[1][1]) * GameConfig.f_zoomy, null);
//			}
//		}
		
		if (state == NUMBER) {
			s_word_number[1].drawBitmap(canvas, s_word_number[1].bitmap, GameConfig.GameScreen_Width/2-s_word_number[1].bitmap.getWidth()/2, (titlexy[0][1]) * GameConfig.f_zoomy, null);
		}else if(state == SUCCESS){
			s_word_honor[1].drawBitmap(canvas, s_word_honor[1].bitmap, GameConfig.GameScreen_Width/2-s_word_honor[1].bitmap.getWidth()/2, (titlexy[1][1]) * GameConfig.f_zoomy, null);
		}
		
		switch(state){
		case NUMBER://分数排行榜
			drawRanking(canvas, y);
			break;
		case SUCCESS://成就排行榜
			
			if (success_library.get(2).index == 1 && move_Y >= 0) {
			} else {
				arrow[0].drawBitmap(canvas, arrow[0].bitmap, 253 * GameConfig.f_zoomx, 153 * GameConfig.f_zoomy,null);	
			}
			
			if (success_library.size() <= 6 || (success_library.get(success_library.size() - 2-1).index == successItem_library.size() && move_Y <= 0)) {
			} else {
				arrow[1].drawBitmap(canvas, arrow[1].bitmap, 253 * GameConfig.f_zoomx, 754 * GameConfig.f_zoomy,null);
			}
			
			canvas.save();
			canvas.clipRect(_library_x, _library_y + _library_h_s/2, _library_x + _library_w, _library_y + _library_h-_library_h_s/2);
			for (int i=0; i<success_library.size(); i++) {
				int x  = (int)(_library_x + _library_w_s / 2 + (i % 2) * (_library_w_s + success[5].bitmap.getWidth())-15 * GameConfig.f_zoomx);
				int y1 = (int)(_library_y + _library_h_s / 2 + (i/2 - 1) * (success[5].bitmap.getHeight() + _library_h_s)+10 * GameConfig.f_zoomy);
				if (success_library.get(i).index != -1) {
					success_library.get(i).drawItem(canvas, icon[success_library.get(i).id-1], paint, x, (int)(move_Y + y1));
				}
			}
			canvas.restore();
			break;
		}
		
		
	}
	
	private void drawRanking(Canvas canvas, int y){
//		if (anjianbutton) {
//			s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(29 * GameConfig.f_zoomx)+ (((int)((472 - 412)* GameConfig.f_zoomx ))/2) , (int) (727 * GameConfig.f_zoomy + (s_share_ui_button_01[0].bitmap.getHeight() / 2 - s_share_ui_button_01[1].bitmap.getHeight() / 2)), (int) (412 * GameConfig.f_zoomx), -1);
//		} else {
//			s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(29 * GameConfig.f_zoomx)+ (((int)((472 - 412)* GameConfig.f_zoomx ))/2), (int) (727 * GameConfig.f_zoomy), (int) (412 * GameConfig.f_zoomx), -1);
//		}
		s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(29 * GameConfig.f_zoomx)+ (((int)((472 - 412)* GameConfig.f_zoomx ))/2), (int) (723 * GameConfig.f_zoomy), (int) (412 * GameConfig.f_zoomx), -1);
		if (anjianbutton) {
			s_share_ui_button_01[2].drawBitmap(canvas, null, (int)(29 * GameConfig.f_zoomx)+ (((int)((472 - 412)* GameConfig.f_zoomx ))/2), (int) (723 * GameConfig.f_zoomy + (s_share_ui_button_01[0].bitmap.getHeight() / 2 - s_share_ui_button_01[1].bitmap.getHeight() / 2)), (int) (412 * GameConfig.f_zoomx), -1);
			} else {
			s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(29 * GameConfig.f_zoomx)+ (((int)((472 - 412)* GameConfig.f_zoomx ))/2), (int) (723 * GameConfig.f_zoomy + (s_share_ui_button_01[0].bitmap.getHeight() / 2 - s_share_ui_button_01[1].bitmap.getHeight() / 2)), (int) (412 * GameConfig.f_zoomx), -1);
		}
		
		
//		s_share_ui_button_01[0].drawBitmap(canvas, (int)(29 * GameConfig.f_zoomx)+ (((int)((472 - 412)* GameConfig.f_zoomx ))/2) - (anjianbutton ? 0.2f : 0f) * s_share_ui_button_01[0].bitmap.getWidth() / 2, (int) (723 * GameConfig.f_zoomy)- (anjianbutton ? 0.2f : 0f) * s_share_ui_button_01[0].bitmap.getHeight() / 2, anjianbutton ? 1.2f : 1.0f, anjianbutton ? 1.2f : 1.0f, 255, 0, 0, 0);
//		s_share_ui_button_01[1].drawBitmap(canvas, (int)(29 * GameConfig.f_zoomx)+ (((int)((472 - 412)* GameConfig.f_zoomx ))/2) - (anjianbutton ? 0.2f : 0f) * s_share_ui_button_01[0].bitmap.getWidth() / 2, (int) (723 * GameConfig.f_zoomy)- (anjianbutton ? 0.2f : 0f) * s_share_ui_button_01[0].bitmap.getHeight() / 2, anjianbutton ? 1.2f : 1.0f, anjianbutton ? 1.2f : 1.0f, 255, 0, 0, 0);
		
		//显示没登陆的facebook
		if(!FacebookOperation.isLanding){
			// 登陆facebook
		    word_title_facebook.drawBitmap(canvas, (int) (200 * GameConfig.f_zoomx), (int) (744 * GameConfig.f_zoomy + y), 1.0f, 1.0f, 255, 0, 0, 0);
		}else{
			word_key_invite.drawBitmap(canvas, (int) (204 * GameConfig.f_zoomx), (int) (744 * GameConfig.f_zoomy + y), 1.0f,  1.0f, 255, 0, 0, 0);
		}
		
		if (_library != null) {
			if (_library.size()>3 && _library.get(3).index == 1 && move_Y >= 0) {
			} else {
				arrow[0].drawBitmap(canvas, arrow[0].bitmap, 253 * GameConfig.f_zoomx, 145 * GameConfig.f_zoomy,null);	
			}
			if (_library.size() <= 18 || (_library.get(_library.size() - 3 - 1).index == itemLibrary.size() && move_Y <= 0)) {
			} else {
				arrow[1].drawBitmap(canvas, arrow[1].bitmap, 253 * GameConfig.f_zoomx, 630 * GameConfig.f_zoomy,null);
			}	
			canvas.save();
			canvas.clipRect(_library_x, _library_y + _library_h_s/2, _library_x + _library_w, _library_y + _library_h-_library_h_s/2);
			
//			int list_x = (int) ((45 + 16) * GameConfig.f_zoomx);
//			int list_y = (int) ((148 + 12) * GameConfig.f_zoomy);
//			int size = _library.size();
//			for (int i=0; i<size/3; i++) {
////				for (int i = 0; i < listItem.size(); ++i) {
//					int listy = list_y + (int) (80 * (i-1) * GameConfig.f_zoomy);
//					// MessageItem item = new MessageItem(i, "蔬菜"+i);
//					_library.get(i*3).paint(canvas, list_x, listy);
////				}
//			}
//				drawMyTop(canvas, list_x, list_y+(int)(496  * GameConfig.f_zoomy));

			
			for (int i=0; i<_library.size(); i++) {
				float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCardWidth);
				float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCardHeight + _library_h_s);
				if (i%3==0 && _library.get(i).index != -1) {
					_library.get(i).paint(canvas, (int)(tempx), (int)(move_Y + tempy+(int)(10*GameConfig.f_zoomy)));
				}
			}
			canvas.restore();
			drawMyTop(canvas, (int)(_library_x + _library_w_s / 2 + (0 % 3) * (_library_w_s + noCardWidth)), (int)(640  * GameConfig.f_zoomy));

		} else if (!FacebookOperation.isOpenNet && !FacebookOperation.isOpenNet && FacebookOperation.isLanding && !FacebookOperation.getFacebook().getFriendNet()) {
			// 显示loading
			float tempx = ((float) ((GameConfig.GameScreen_Width - loading[0].bitmap.getWidth()) / 2));
			float tempy = y + ((float) ((GameConfig.GameScreen_Height - loading[0].bitmap.getHeight()) / 2));

			loading[tempi].drawBitmap(canvas, loading[tempi].bitmap, tempx, tempy, null);

		} 
      if(FacebookOperation.isOpenNet && FacebookOperation.isLanding ) { // 没网络
			paint.setColor(0xff824d22);
			paint.setTextSize(22 * GameConfig.f_zoom);
			String temp = LangUtil.getLangString(LangDefineClient.CHECK_NET);
			float tempx = ((float) ((GameConfig.GameScreen_Width - paint.measureText(temp)) / 2));
			float tempy = y + ((float) ((GameConfig.GameScreen_Height - paint.getTextSize()) / 2));
			canvas.drawText(temp, tempx, tempy, paint);
		}
	}
	
	private void drawMyTop(Canvas canvas, int list_x, int list_y){
		if(FacebookOperation.player==null)return;
		//绘制自己的排行榜
		if(s_icon==null && FacebookOperation.player.getIcon()!=null){
			int width = TopListItem.share_ui_photo_04.bitmap.getWidth();
			int height =TopListItem.share_ui_photo_04.bitmap.getHeight();
			// 图片缩放
			if(FacebookOperation.player.getIcon()!=null){
				Bitmap temp =	FacebookOperation.player.getIcon();
				temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx, height - 6 * GameConfig.f_zoomy);
				this.s_icon = new Sprite(temp);
			}
		}
		int ranking = (int)FacebookOperation.player.getOrderIndex();
		if(ranking == 1 && TopListItem.word_num_no1!=null){
			TopListItem.word_num_no1.drawBitmap(canvas,
					TopListItem.word_num_no1.bitmap, 
					(int)(list_x),
					(int)(list_y), null);
		}else if(ranking == 2 && TopListItem.word_num_no2!=null){
			TopListItem.word_num_no2.drawBitmap(canvas, TopListItem.word_num_no2.bitmap, (int)(list_x), (int)(list_y), null);
		}else if(ranking == 3 && TopListItem.word_num_no3!=null){
			TopListItem.word_num_no3.drawBitmap(canvas, TopListItem.word_num_no3.bitmap, (int)(list_x), (int)(list_y), null);
		}else if(rank!=null){
			rank.paintText(canvas, list_x+((TopListItem.word_num_no1.bitmap.getWidth()-(int)(30*GameConfig.f_zoom))/2), list_y+(int)(16 * GameConfig.f_zoomy));
		}
		list_x = list_x+(int)(50 * GameConfig.f_zoomx);
		list_y = list_y-(int)(10 * GameConfig.f_zoomx);
		if(s_icon!=null){
			s_icon.drawBitmap(canvas, s_icon.bitmap, (int)(list_x)+3 * GameConfig.f_zoomx, (int)(list_y)+3 * GameConfig.f_zoomy, null);
			TopListItem.share_ui_photo_04.drawBitmap(canvas, TopListItem.share_ui_photo_04.bitmap, (int)(list_x), (int)(list_y), null);
		}else{
			TopListItem.share_ui_photo_01.drawBitmap(canvas, TopListItem.share_ui_photo_01.bitmap, (int)(list_x), (int)(list_y), null);
		}
		//名字
		if(name!=null)
			name.paintText(canvas,  (int)(list_x+70 * GameConfig.f_zoomx), (int)(list_y+9 * GameConfig.f_zoomy));
		//内容
		if(score!=null)
			score.paintText(canvas,  list_x+(int)(70 * GameConfig.f_zoomx), list_y+(int)(36 * GameConfig.f_zoomy));
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		switch(state){
		case NUMBER://分数排行榜
			if (FacebookOperation.isLanding && _library == null
					&& !FacebookOperation.getFacebook().getFriendNet()) {
				if (GameConfig.i_coke % 2 == 0) {
					tempi++;
					if (tempi == 9)
						tempi = 0;
				}
			}else{
				
				if (isCorrectCardMove) {
					move_Y += correctCard_move;
					if (move_Y <= -(noCardHeight + _library_h_s)) {
						 
						if (_library.get(_library.size()-4).index != itemLibrary.size())
							addDownCard();
					 
						move_Y = 0;
						correctCard_move = Math.abs(correctCard_move);
						isCorrectCardMove = false;
					} else if (move_Y > -Math.abs(correctCard_move) && move_Y < Math.abs(correctCard_move)) {
						move_Y = 0;
						correctCard_move = Math.abs(correctCard_move);
						isCorrectCardMove = false;
					} else if (move_Y >=noCardWidth + _library_h_s) {
						if (_library.get(3).index != 1)
							addUpCard();
						move_Y = 0;
						correctCard_move = Math.abs(correctCard_move);
						isCorrectCardMove = false;
					}
				}
				
				
			}
			break;
		case SUCCESS://成就排行榜
			for (int i=0;i<success_library.size(); i++) {
				if (success_library.get(i).index != -1) {
					success_library.get(i).run();
				}
			}
			if (isCorrectCardMove) {
				move_Y += correctCard_move;
				if (move_Y <= -(success[5].bitmap.getHeight() + _library_h_s)) {
						if (success_library.get(success_library.size()-3).index != successItem_library.size())
							addDownSucces();
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				} else if (move_Y > -Math.abs(correctCard_move) && move_Y < Math.abs(correctCard_move)) {
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				} else if (move_Y >= success[5].bitmap.getWidth() + _library_h_s) {
					if (success_library.get(2).index != 1)
						addUpSucces();
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				}
			}
			break;
		}
		
	}

	private void addDownCard() {
		for (int j=0; j<_library.size(); j+=3) {
			if (j + 3 < _library.size()) {			
				_library.set(j+0, _library.get(j+3));
				_library.set(j+1, _library.get(j+4));
				_library.set(j+2, _library.get(j+5));
			} else {
				if (j + 3 - _library.size() == 0) {
						if (_library.get(j+2).index < itemLibrary.size()) {
							_library.set(j+0, itemLibrary.get(_library.get(j).index + 2));
							_library.set(j+1, itemLibrary.get(_library.get(j+1).index + 2));
							_library.set(j+2, itemLibrary.get(_library.get(j+2).index + 2));
						} else {
							_library.set(j, new TopListItem(-1, -1, "", "", null));										
							_library.set(j+1, new TopListItem(-1, -1, "", "", null));										
							_library.set(j+2, new TopListItem(-1, -1, "", "", null));																
						}
				}
			}
		}
	}
	
	private void addUpCard() {
		for (int j=_library.size(); j>0; j-=3) {
			if (j > 3) {
				_library.set(j-1, _library.get(j-4));
				_library.set(j-2, _library.get(j-5));
				_library.set(j-3, _library.get(j-6));
			} else {
				if (_library.get(j-1).index == 3) {
					_library.set(j-1, new TopListItem(-1, -1, "", "", null));						
					_library.set(j-2, new TopListItem(-1, -1, "", "", null));						
					_library.set(j-3, new TopListItem(-1, -1, "", "", null));										
				} else {
						_library.set(j-1, itemLibrary.get(_library.get(j-1).index - 4));
						_library.set(j-2, itemLibrary.get(_library.get(j-2).index - 4));
						_library.set(j-3, itemLibrary.get(_library.get(j-3).index - 4));				
				}
			}
		}
	}
	private void addDownSucces() {
		for (int j=0; j<success_library.size(); j+=2) {
			if (j + 2 < success_library.size()) {			
				success_library.set(j+0, success_library.get(j+2));
				success_library.set(j+1, success_library.get(j+3));
			} else {
				if (j + 2 - success_library.size() == 0) {
					if (success_library.get(j+1).index < successItem_library.size()) {
						success_library.set(j+0, successItem_library.get(success_library.get(j).index + 1));
						success_library.set(j+1, successItem_library.get(success_library.get(j+1).index + 1));
					} else {
						success_library.set(j, new SuccessItem(false, null, -1, -1, ""));
						success_library.set(j+1, new SuccessItem(false, null, -1, -1, ""));
					}
				}
			}
		}
	}
	private void addUpSucces() {
		for (int j=success_library.size(); j>0; j-=2) {
			if (j > 2) {
				success_library.set(j-1, success_library.get(j-3));
				success_library.set(j-2, success_library.get(j-4));
			} else {
				if (success_library.get(j-1).index == 2) {

					success_library.set(j-1, new SuccessItem(false, null, -1, -1, ""));
					success_library.set(j-2, new SuccessItem(false, null, -1, -1, ""));
				} else {
					success_library.set(j-1, successItem_library.get(success_library.get(j-1).index - 3));
					success_library.set(j-2, successItem_library.get(success_library.get(j-2).index - 3));
					
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
		if(FBInterface.user!=null)
			FacebookOperation.player.setServer(null);
		if(UserRequest.getUser()!=null)
			UserRequest.getUser().setTopListInt(null);
		if (gs == null)
			return;
		GameImage.delImage(gs.bitmap);
		gs.bitmap = null;
		gs = null;
		
		bgxy = null;
		
		titlexy = null;
		
		if(s_share_ui_back_01!=null){
			GameImage.delImage(s_share_ui_back_01.bitmap);
			if (s_share_ui_back_01.bitmap != null)
				s_share_ui_back_01.bitmap = null;
		}
		s_share_ui_back_01 = null;

		if(word_title_facebook!=null){
			GameImage.delImage(word_title_facebook.bitmap);
			if (word_title_facebook.bitmap != null)
				word_title_facebook.bitmap = null;
		}
		word_title_facebook = null;
		
		if(arrow!=null){
			for (int i=0; i<arrow.length; i++) {
				GameImage.delImage(arrow[i].bitmap);
				arrow[i].bitmap = null;
			}
		}
		arrow = null;
		
		if(name!=null)
			name.Close();
		if(score!=null)
			score.Close();
		if(rank!=null)
			rank.Close();
		
		if(word_key_invite!=null){
			GameImage.delImage(word_key_invite.bitmap);
			if (word_key_invite.bitmap != null)
				word_key_invite.bitmap = null;
		}
		word_key_invite = null;

		if(s_share_ui_back_02!=null){
			GameImage.delImage(s_share_ui_back_02.bitmap);
			if (s_share_ui_back_02.bitmap != null)
				s_share_ui_back_02.bitmap = null;
		}
		s_share_ui_back_02 = null;

		if(s_share_ui_back_02_2!=null){
			GameImage.delImage(s_share_ui_back_02_2.bitmap);
			if (s_share_ui_back_02_2.bitmap != null)
				s_share_ui_back_02_2.bitmap = null;
		}
		s_share_ui_back_02_2 = null;
		
		if(s_share_ui_close!=null){
			GameImage.delImage(s_share_ui_close.bitmap);
			if (s_share_ui_close.bitmap != null)
				s_share_ui_close.bitmap = null;
			s_share_ui_close = null;
		}
		
		if(share_ui_back_08!=null){
			GameImage.delImage(share_ui_back_08.bitmap);
			if (share_ui_back_08.bitmap != null)
				share_ui_back_08.bitmap = null;
		}
		share_ui_back_08 = null;
		
		if(s_icon!=null){
			GameImage.delImage(s_icon.bitmap);
			if (s_icon.bitmap != null)
				s_icon.bitmap = null;
			s_icon = null;
		}
		
		if(s_share_ui_back_04!=null)
			for(int i=0;i<s_share_ui_back_04.length;++i){
				GameImage.delImage(s_share_ui_back_04[i].bitmap);
				if (s_share_ui_back_04[i].bitmap != null)
					s_share_ui_back_04[i].bitmap = null;
				s_share_ui_back_04[i] = null;
			}
		s_share_ui_back_04 = null;

		if(s_share_ui_button_01!=null)
			for(int i=0;i<s_share_ui_button_01.length;++i){
				GameImage.delImage(s_share_ui_button_01[i].bitmap);
				if (s_share_ui_button_01[i].bitmap != null)
					s_share_ui_button_01[i].bitmap = null;
				s_share_ui_button_01[i] = null;
			}
		s_share_ui_button_01 = null;
		

		if(s_share_ui_button!=null)
			for(int i=0;i<s_share_ui_button.length;++i){
				GameImage.delImage(s_share_ui_button[i].bitmap);
				if (s_share_ui_button[i].bitmap != null)
					s_share_ui_button[i].bitmap = null;
				s_share_ui_button[i] = null;
			}
		s_share_ui_button = null;
		
		if(s_word_number!=null)
			for(int i=0;i<s_word_number.length;++i){
				GameImage.delImage(s_word_number[i].bitmap);
				if (s_word_number[i].bitmap != null)
					s_word_number[i].bitmap = null;
				s_word_number[i] = null;
			}
		s_word_number = null;
		
		if(s_word_honor!=null)
			for(int i=0;i<s_word_honor.length;++i){
				GameImage.delImage(s_word_honor[i].bitmap);
				if (s_word_honor[i].bitmap != null)
					s_word_honor[i].bitmap = null;
				s_word_honor[i] = null;
			}
		s_word_honor = null;
		
		if(loading!=null)
			GameImage.delImageArray(loading);
		loading = null;
	 
		paint = null;
		fontFace = null;
		 
		if(itemLibrary!=null)
			for(int i=0;i<itemLibrary.size();++i){
				itemLibrary.get(i).delete();
				itemLibrary.remove(i);
			}
		itemLibrary = null;
		
		
		successName = null;
		successIcon = null;
		
		if(success_library!=null){
			for(int i=0;i<success_library.size();++i){
				success_library.remove(i);
			}
			success_library.clear();
		}
		success_library = null;
		
		if(successItem_library!=null){
			for(int i=0;i<successItem_library.size();++i){
				successItem_library.remove(i);
			}
			successItem_library.clear();
		}
		successItem_library = null;
		 
		 
		if(word_title_facebook!=null){
			GameImage.delImage(word_title_facebook.bitmap);
			if (word_title_facebook.bitmap != null)
				word_title_facebook.bitmap = null;
			word_title_facebook = null;
		}
		
		if(word_key_invite!=null){
			GameImage.delImage(word_key_invite.bitmap);
			if (word_key_invite.bitmap != null)
				word_key_invite.bitmap = null;
			word_key_invite = null;
		}
		
		if(share_ui_back_08!=null){
			GameImage.delImage(share_ui_back_08.bitmap);
			if (share_ui_back_08.bitmap != null)
				share_ui_back_08.bitmap = null;
			share_ui_back_08 = null;
		}
 
		if(arrow!=null)
			for(int i=0;i<arrow.length;++i){
				GameImage.delImage(arrow[i].bitmap);
				if (arrow[i].bitmap != null)
					arrow[i].bitmap = null;
				arrow[i] = null;
			}
		arrow = null;
	 
		if(success!=null)
			for(int i=0;i<success.length;++i){
				GameImage.delImage(success[i].bitmap);
				if (success[i].bitmap != null)
					success[i].bitmap = null;
				success[i] = null;
			}
		success = null;
	 
		if(success_S_num_1!=null)
			GameImage.delImageArray(success_S_num_1);
		success_S_num_1 = null;
		
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
			if (ExternalMethods.CollisionTest( x, y, 450 * GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth() / 2 * 0.2f, 41 * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight() / 2 * 0.2f, 450 * GameConfig.f_zoomx + s_share_ui_close.bitmap.getWidth() * 1.2f, 41 * GameConfig.f_zoomy + s_share_ui_close.bitmap.getHeight() * 1.2f)) {
				anjianclose = true;
			}
			switch(state){
			case NUMBER://分数排行榜
				if(FacebookOperation.isLanding && toplist==null)return;
				
				if (ExternalMethods.CollisionTest(
						x, y, 59 * GameConfig.f_zoomx - s_share_ui_button_01[0].bitmap.getWidth() / 2 * 0.2f,
						727 * GameConfig.f_zoomy - s_share_ui_button_01[0].bitmap.getHeight() / 2 * 0.2f,
						59 * GameConfig.f_zoomx + 412* GameConfig.f_zoomx,
						727 * GameConfig.f_zoomy + s_share_ui_button_01[0].bitmap.getHeight() * 1.2f)) {
					anjianbutton = true;
				}
				
				if (ExternalMethods.CollisionTest(x, y, 
						_library_x, _library_y,
						_library_x + _library_w, _library_y + _library_h)) {
					oldY = y;
					ismove = true;
				}
				
				break;
			case SUCCESS://成就排行榜
				if (ExternalMethods.CollisionTest(x, y, _library_x, _library_y, _library_x + _library_w, _library_y + _library_h)) {
					oldY = y;
					ismove = true;
				}
				for (int i=0;i<success_library.size(); i++) {
					if(success_library.get(i).index != -1 && ok_num[success_library.get(i).id-1]==size_num[success_library.get(i).id-1]){
						success_library.get(i).onTouch(event);
					}
				}
				break;
			
			}
			
		}else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (anjianclose && ExternalMethods.CollisionTest(
							x, y, 450 * GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth() / 2 * 0.2f,
							41 * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight() / 2 * 0.2f,
							450 * GameConfig.f_zoomx + s_share_ui_close.bitmap.getWidth() * 1.2f,
							41 * GameConfig.f_zoomy + s_share_ui_close.bitmap.getHeight() * 1.2f)) {
				anjianclose = false;
				GameManager.ChangeModule(null);
			}
			
//			for (int i=0; i<bgxy.length; i++) {
//				if (ExternalMethods.CollisionTest(x, y, (int)(bgxy[i][0] * GameConfig.f_zoom + (s_share_ui_back_04[0].bitmap.getWidth()/2 - s_share_ui_back_04[1].bitmap.getWidth()/2)), 
//						(int)(bgxy[i][1] * GameConfig.f_zoom  + (s_share_ui_back_04[0].bitmap.getHeight()/2 - s_share_ui_back_04[1].bitmap.getHeight()/2)), 
//						(int)(bgxy[i][0] * GameConfig.f_zoom + (s_share_ui_back_04[0].bitmap.getWidth()/2 - s_share_ui_back_04[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoom * 1.2f), 
//						(int)(bgxy[i][1] * GameConfig.f_zoom  + (s_share_ui_back_04[0].bitmap.getHeight()/2 - s_share_ui_back_04[1].bitmap.getHeight()/2) + s_share_ui_back_04[1].bitmap.getHeight()))) {
//						if(i == 0){
//							state = NUMBER;
//						}else if(i == 1){
//							state = SUCCESS;
//							ok_num = VeggiesData.getAchievementSchedule();
//							size_num  = VeggiesData.getAchievementDes();
//							state_num = VeggiesData.getAchievement();
//							for (int j=0; j<successItem_library.size(); j++) {
//								successItem_library.get(j).setNum(state_num[j], ok_num[j], size_num[j]);
//							}
//						}
//				}
//			}
//			
			switch(state){
			case NUMBER://分数排行榜
				if(FacebookOperation.isLanding && toplist==null)return;
				if (anjianbutton && ExternalMethods.CollisionTest(x, y, 59 * GameConfig.f_zoomx - s_share_ui_button_01[0].bitmap.getWidth() / 2 * 0.2f, 727 * GameConfig.f_zoomy - s_share_ui_button_01[0].bitmap.getHeight() / 2 * 0.2f, 59 * GameConfig.f_zoomx + 412* GameConfig.f_zoomx, 727 * GameConfig.f_zoomy + s_share_ui_button_01[0].bitmap.getHeight() * 1.2f)) {
					anjianbutton = false;
					if(!FacebookOperation.isLanding){ //登录
						if(FacebookOperation.gameState!= FacebookOperation.GAME_STATE_INIT_LOADING)
							FacebookOperation.gameState= FacebookOperation.GAME_STATE_INIT_LOADING;
						FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.topList);
					}else if(toplist!=null){ //邀请
						FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_FACEBOOK_FRIENDS);
					}
				}
				if (ismove) {
					if (move_Y < -(_library_h_s)/2 || (move_Y > 0 && move_Y < (_library_h_s)/2)) {
						//向_friend[0]靠近 -
						correctCard_move = - correctCard_move;
						isCorrectCardMove = true;
					} else if ((move_Y < 0 && move_Y >= -( _library_h_s)/2 || move_Y >= (_library_h_s)/2)){
						//向_friend[1]靠近 +
						isCorrectCardMove = true;
					}
				}
				break;
			case SUCCESS://成就排行榜
				for(int i=0;i<success_library.size();++i){
					
				
					if(success_library.get(i).index != -1 && ok_num[success_library.get(i).id-1]==size_num[success_library.get(i).id-1]){
						int id = success_library.get(i).onTouch(event);
						
						if(id>=1){
							VeggiesData.setAchievement(id-1, 0);
							if(isGet[id-1]){
								VeggiesData.addGem(getgold[id-1]);
							}else
								VeggiesData.addGold(getgold[id-1]);
							ok_num = VeggiesData.getAchievementSchedule();
							size_num  = VeggiesData.getAchievementDes();
							state_num = VeggiesData.getAchievement();
							for (int j=0; j<successItem_library.size(); j++) {
								successItem_library.get(j).setNum(state_num[j], ok_num[j], size_num[j]);
							}
						}
					}
				}
//				
//				if (ismove) {
//					if (move_Y < -(success[5].bitmap.getHeight() + _library_h_s)/2 || (move_Y > 0 && move_Y < (success[5].bitmap.getHeight() + _library_h_s)/2)) {
//						//向_friend[0]靠近 -
//						correctCard_move = - correctCard_move;
//						isCorrectCardMove = true;
//					} else if ((move_Y < 0 && move_Y >= -(success[5].bitmap.getHeight() + _library_h_s)/2 || move_Y >= (success[5].bitmap.getHeight() + _library_h_s)/2)){
//						//向_friend[1]靠近 +
//						isCorrectCardMove = true;
//					}
//				}
//
//				ismove = false;
//				
//			 
//				break;
//			
			}
			
		
			
			anjianclose = false;
			anjianbutton = false;
		}else if (event.getAction() == MotionEvent.ACTION_MOVE) {

			if(state == SUCCESS){
				if (ismove) {
					if (isCorrectCardMove) {
						isCorrectCardMove = false;	//纠正移动关闭等移动释放后继续纠正
						correctCard_move = Math.abs(correctCard_move);
					}
					int tempY = (int) event.getY();
					
					move_Y+=tempY-oldY;
					
					oldY = (int) event.getY();	
					
					if (move_Y > _library_h_s && success_library.get(0).index == -1) {
						move_Y = _library_h_s;
					} else if ((move_Y < -_library_h_s && success_library.size() < 6 ) || (move_Y < -_library_h_s && success_library.get(6).index == -1)) {
						move_Y = -_library_h_s;
					}
					if (move_Y >= _library_h_s + success[5].bitmap.getHeight()) {
						move_Y = 0;
						addUpSucces();
					} else if (move_Y <= -(_library_h_s + success[5].bitmap.getHeight())) {
						move_Y = 0;
						addDownSucces();
					}
				}
			}else {
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
					} else if ((move_Y < -_library_h_s && _library.size() < 18 ) || (move_Y < -_library_h_s && _library.get(18).index == -1)) {
						move_Y = -_library_h_s;
					}
					if (move_Y >= _library_h_s + noCardHeight) {
						move_Y = 0;
						addUpCard();
					} else if (move_Y <= -(_library_h_s + noCardHeight)) {
						move_Y = 0;
						addDownCard();
					}
					
				}
				
				
			}
		
		}
	}

}//end class
