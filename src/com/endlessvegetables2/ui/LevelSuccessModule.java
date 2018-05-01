package com.endlessvegetables2.ui;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.audio.Music;
import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.RankingChange.Star;
import com.facebook.FacebookOperation;
import com.facebook.UserRequest;
import com.game.data.FaceBookPlayer;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMain;
import com.kokatlaruruxi.wy.GameMission;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Sprite;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.Module;
import com.socoGameEngine.TextBox;
import com.socogame.coolEdit.CoolEditDefine;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class LevelSuccessModule extends Module{
	private Sprite lsm;
//	private Sprite backbg1;
//	private Sprite backbg2;
//	private Sprite backbg3;
//	private Sprite close;
//	private Sprite ribbon;
//	private Sprite shine;
//	private Sprite[] button;
//	private Sprite button_share_text;
//	private Sprite button_again_text;
//	private Sprite button_next_text;
//	private Sprite[] star_big;
//	private Sprite[] star_big_hid;
//	private Sprite gameover_bg_1;
//	private Sprite newRecord;
//	private Sprite newRecordText;
//	private Sprite[] reward;
//	private Sprite rewardbg;
//	private Bitmap[] tomato;
//	private Bitmap[] throw_flower;
	
	private boolean anjianclose;
	private boolean anjianshare;
	private boolean anjianagain;
	private boolean anjiannext;
	private boolean anjianOpen;
	private boolean isOpen;
	
	int index=0;//0从上往下掉 1添加完成和星星I, 2添加完成和星星II,3添加完成和星星III 4番茄动画
	int index2=0;
	int index2_max[]={15,10,10,10,-1};
	private boolean isNewRecord = false;
	private int score = 123123;
	private int temp_score = 123123;
	private String text_temp_score = "";
	private int star2 = 0;
	private int time = 90000;
//	private String time_text;
	private int gold = 2222;
	private int gem = 2222;
	private int card = 2222;
	private String[] reward_text;
	private int[] reward_place = {0,0,0};
	private int[] star_1 = {-1,-1,-1};
	private int score_time = 3000;
	private long score_start_time = 0;
	
	TextBox bestScore; 
	TextBox textBoxs[];
	private int[] col = {Color.argb(255, 130, 77, 34), Color.RED};
	private int[] star = {-1,-1,-1};
	private int[] mission;
	int oldscore=0;
//	private Sprite ui_progress_2;
//	private Sprite ui_complete;
	
	Bitmap bitmap_title_level_2;
	Bitmap bitmap_num_09[];
	private UISprite box_gold;
	boolean isoneOver;
	private Sprite share_ui_shine_2[];
	 
	Star _star[];
	
	private float newRecord_r;
	private float newRecord_move_y;
	public static boolean isNext;
	public static boolean isSurpass;
	public void releaseResource() {
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
//		GameImage.delImage(button[0].bitmap);
//		GameImage.delImage(button[1].bitmap);
//		button = null;
//		GameImage.delImage(button_share_text.bitmap);
//		button_share_text = null;
//		GameImage.delImage(button_again_text.bitmap);
//		button_again_text = null;
//		GameImage.delImage(button_next_text.bitmap);
//		button_next_text = null;
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
//		GameImage.delImage(gameover_bg_1.bitmap);
//		gameover_bg_1 = null;
//		GameImage.delImage(newRecord.bitmap);
//		newRecord = null;
//		GameImage.delImage(newRecordText.bitmap);
//		newRecordText = null;
//		for (int i=0; i<reward.length; i++) {
//			GameImage.delImage(reward[i].bitmap);
//			reward[i] = null;
//		}
//		reward = null;
//		GameImage.delImage(rewardbg.bitmap);
//		rewardbg = null;
//		for (int i=0; i<tomato.length; i++) {
//			GameImage.delImage(tomato[i]);
//			tomato[i] = null;
//		}
//		tomato = null;
//		for (int i=0; i<throw_flower.length; i++) {
//			GameImage.delImage(throw_flower[i]);
//			throw_flower[i] = null;
//		}
//		throw_flower = null;
	}
	boolean enterHideStage; //是否是小南怪
	Sprite s_interface_ui_ribbon_01;
	Sprite s_interface_ui_shine;
	Sprite word_title_reward;
	
	public LevelSuccessModule(boolean enterHideStage ) {
		lsm = new Sprite();
		this.enterHideStage = enterHideStage;
//		backbg1 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
//		backbg2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
//		backbg3 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
//		close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
//		ribbon = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_ribbon_01));
//		shine = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_shine));
//		button = new Sprite[2];
//		button[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
//		button[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
//		button_share_text = new Sprite(GameImage.getImage(GameStaticImage.word_share));
//		button_again_text = new Sprite(GameImage.getImage(GameStaticImage.word_again));
//		button_next_text = new Sprite(GameImage.getImage(GameStaticImage.word_next));
		
//		star_big = new Sprite[3];
//		star_big[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_09));
//		star_big[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_10));
//		star_big[2] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_11));
//		star_big_hid = new Sprite[GameStaticImage.s_interface_star_09.length];
//		star_big_hid[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_12));
//		star_big_hid[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_13));
//		star_big_hid[2] = new Sprite(GameImage.getImage(GameStaticImage.interface_star_14));

//		gameover_bg_1 = new Sprite(GameImage.getImage(GameStaticImage.gameover_ui_back_10));

//		newRecord = new Sprite(GameImage.getImage(GameStaticImage.shop_reward));
//		newRecordText = new Sprite(GameImage.getImage(GameStaticImage.word_newrecord));

//		reward = new Sprite[3];
//		reward[0] = new Sprite(GameImage.getImage(GameStaticImage.shop_gold_06));
//		reward[1] = new Sprite(GameImage.getImage(GameStaticImage.shop_gem_12));
//		reward[2] = new Sprite(GameImage.getImage(GameStaticImage.gameover_shop_card_04));
//		rewardbg = new Sprite(GameImage.getImage(GameStaticImage.gameover_ui_back_09));
//		tomato = GameImage.getAutoSizecutBitmap(GameStaticImage.gameover_tomato_over_01, 3, 1, GameImage.Sort_line);
//		throw_flower = GameImage.getAutoSizecutBitmap(GameStaticImage.gameover_tomato_over_02, 3, 1, GameImage.Sort_line);
	}
	//* GameConfig.f_zoomx * GameConfig.f_zoomy
	float star_x[] = {210, 229, 270, 185, 236, 266, 288, 162};
    float star_y[] = {325, 334, 332, 391, 410, 387, 409, 422};
    int down;
	public boolean initialize() {
		GameImage.showImageHashMap();
		GameImage.showMemory();
		System.out.println("进入关卡奖励的内存");
		index=0;
		index2=0;
		text_temp_score = "1234,56789";
		temp_score = 0;
		star2 = 0;
		isNewRecord = false;
		isOpen = true;
//		ui_progress_2=new Sprite(GameImage.getImage("gameover/ui_progress_2"));
//		ui_complete=new Sprite(GameImage.getImage(Configs.filePath+"/word/ui_complete"));
		bitmap_title_level_2=GameImage.getImage("gameover/title_level_2");
		bitmap_num_09=GameImage.getAutoSizecutBitmap("gameover/num_09", 10, 1, (byte)0);
		if(enterHideStage){ //小篮怪
			
			s_interface_ui_ribbon_01 = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_ribbon_01));
			s_interface_ui_shine = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_shine));
			word_title_reward = new Sprite(GameImage.getImage(GameStaticImage.word_title_reward));
			
			box_gold = new UISprite();
			box_gold.initSprite(CoolEditDefine.GAMEOVER_BOX, GameConfig.GameScreen_Width/2, (int)(300 * GameConfig.f_zoomy), Sprite.SPRITE_STATE_NORMAL);
			SpriteLibrary.loadSpriteImage(CoolEditDefine.GAMEOVER_BOX);
			box_gold.changeAction(0);
			isoneOver = false;
			share_ui_shine_2 = GameImage.getAutoSizecutSprite(GameStaticImage.share_ui_shine_2, 3, 1, GameImage.Sort_line);
			Random ran = new Random();
			_star = new Star[star_x.length];
			for(int i=0;i<_star.length;++i){
				_star[i] = new Star();
				_star[i].time = ran.nextInt(20);
				_star[i].setXY(star_x[i]*GameConfig.f_zoomx , (star_y[i]-10)*GameConfig.f_zoomy);
			}
		}
		LevelData.testData();
		int temp_len = 0;
		int temp_start = 0;
		for(int i=0; i<LevelData.getData().size(); i++) {
			if (i == 0) {
				score = LevelData.getData().get(i);
			} else if (i == 1) {
				time = LevelData.getData().get(i);
				score += time / 25 * 1000;
			} else if (i == 5) {
				gold = LevelData.getData().get(i);
			} else if (i == 6) {
				gem = LevelData.getData().get(i);
			} else if (i == 7) {
				card = LevelData.getData().get(i);
			} else if (i == 8) {
				VeggiesData.addGold(LevelData.getData().get(i) - VeggiesData.getGold());
			} else if (i == 9) {
				VeggiesData.addGem(LevelData.getData().get(i) - VeggiesData.getGem());
			} else if (i == 11) {
				score += 10000 * LevelData.getData().get(i-1) / LevelData.getData().get(i);
			} else if (i == 13) {
//				if (LevelData.getData().get(i) == 0) {
//					score += 10000;
//				} else {
//					score += 10000 * LevelData.getData().get(i-1) / LevelData.getData().get(i);
//				}
//				if (VeggiesData.getLevelScore()[VeggiesData.getCurrentLevel()] < score) {
//					System.out.println("clpqy:getLevelScore()2="+VeggiesData.getLevelScore()[VeggiesData.getCurrentLevel()]);
//					System.out.println("clpqy:setCurrentLevelScore="+score);
//					VeggiesData.setCurrentLevelScore(score);
//					//isNewRecord = true;
//				}
			}else if (i >= 14 && i < 26) {
//				i = 14;
				mission = new int[12];
				for (int j=0; j<12; j+=4) {
					mission[j] = LevelData.getData().get(i+j);
					mission[j+1] = LevelData.getData().get(i+j+1);
					mission[j+2] = LevelData.getData().get(i+j+2);
					mission[j+3] = LevelData.getData().get(i+j+3);
					if(mission[j]==GameMission.MISSION_13){
						mission[j+2]=score;
						if(mission[j+2]>=mission[j+3]){
							List<Integer> _lData =LevelData.getData();
							_lData.set(j/4+2, 1);
						}
					}
				}
				i += 12-1;
			} 
			else if (i == 26) {
				temp_start = i+1;
				temp_len = LevelData.getData().get(i) * 3;
			} else if (i == temp_start){
				for (int j=0; j<temp_len; j+=3) {
					VeggiesData.setAllCardNum(LevelData.getData().get(temp_start+j), (LevelData.getData().get(temp_start+j+1) - LevelData.getData().get(temp_start+j+2)));
				}
				i += temp_len-1;
			}
		}
		for(int i=2;i<5;i++){
			if (i >= 2 && i <= 4) {
				if (LevelData.getData().get(i) == 1) {
					//VeggiesData.getTask_Mission()[VeggiesData.getCurrentLevel()][star] = 1;
					star_1[i-2] = 1;
					star2++;
				}
				if(!enterHideStage){
//					if(star2>VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()]){
//						VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()] = star2;
//					}
//					VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()] = star2;
					if (i == 4) {
						VeggiesData.setTask_Mission(VeggiesData.getCurrentLevel(), star_1);
						if (VeggiesData.getCurrentLevel()+1 < VeggiesData.getGameGuanka().length && VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()+1] == -1) {
							VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()+1] = 0;
							VeggiesData.setYiKaiQiMaxLevel(VeggiesData.getCurrentLevel()+1);
							ChooseLevelModule2.isKaiQiXiaYiGuanAni = true;
						}
						int tempstar=0;
						if(VeggiesData.getTask_Mission()[VeggiesData.getCurrentLevel()][0]>=1)
							tempstar++;
						if(VeggiesData.getTask_Mission()[VeggiesData.getCurrentLevel()][1]>=1)
							tempstar++;
						if(VeggiesData.getTask_Mission()[VeggiesData.getCurrentLevel()][2]>=1)
							tempstar++;
						VeggiesData.getGameGuanka()[VeggiesData.getCurrentLevel()] = tempstar;
					}
				}
			} 
		}
		initmission();
		LevelData.getData().clear();
		//TODO 处理数据
		new VeggiesData().saveGame();
		 
		//连接服务器成功就上传分数
		if(UserRequest.getUser().getLoginok() && 
				FacebookOperation.getFacebook().getLoadingFriend() && FacebookOperation.player!=null){
			//上传积分 星级到服务器
			int level = VeggiesData.getCurrentLevel();
			long id = FacebookOperation.player.getid_server();
			FaceBookPlayer.UserLeveInfo info = FacebookOperation.player.getLG().get(level);
			if(info!=null){
				info.score = score;
				info.star = star2;
			}else {
				FacebookOperation.player.addLG(level, score, star2);
			}
			UserRequest.getUser().ReqLeveInfo(level, id, score, star2);
			Iterator iterator = FriendScore.friendSurpass.keySet().iterator();
			while (iterator.hasNext()) {
				Friend frien = FriendScore.friendSurpass.get(iterator.next());
				if(this.score>frien.score){
					isSurpass = true;
					FriendScore.surpassFriendFbid = frien.fbID;
					FriendScore.mySurpass = frien.rank;
					break;
				}
			}
		 }
		VeggiesData.addHeart(1); //胜利了 需要还给玩家一颗体力
		
//		time_text = ExternalMethods.timeToStr(time);
		reward_text = new String[3];
		reward_text[0] = ExternalMethods.IntToStr_En(gold);
		reward_text[1] = ExternalMethods.IntToStr_En(gem);
		reward_text[2] = ExternalMethods.IntToStr_En(card);
		reward_place[0] = countPlace(gold);
		reward_place[1] = countPlace(gem);
		reward_place[2] = countPlace(card);
		
		bestScore = new TextBox();
		bestScore.setDefaultColor(Color.argb(255, 121, 76, 37));
		bestScore.setTextAlign(TextBox.HCENTER);
		bestScore.setBoxSize((int)(200 * GameConfig.f_zoomx), (int)(190 * GameConfig.f_zoomy));
		bestScore.setTextSize((int)(22*GameConfig.f_zoom));
		bestScore.setString(GameWord.successLevel_Score[GameWord.useLanguage] + ExternalMethods.IntToStr_En(VeggiesData.getLevelScore()[VeggiesData.getCurrentLevel()]));
		System.out.println("clpqy:getLevelScore()="+VeggiesData.getLevelScore()[VeggiesData.getCurrentLevel()]);
		bestScore.setBoxSize((int)(300 * GameConfig.f_zoomx), (int)bestScore.height());
		
		score_start_time = System.currentTimeMillis();
		
		newRecord_r = 1.6f;
		newRecord_move_y = -30;
		if(!VeggiesData.isMuteMusic()){
			GameMedia.playMusic(R.raw.levelsuccessl, false, true);
		}
		oldscore=VeggiesData.getLevelScore()[VeggiesData.getCurrentLevel()];
		if (VeggiesData.getLevelScore()[VeggiesData.getCurrentLevel()] < score) {
			VeggiesData.setCurrentLevelScore(score);
			//isNewRecord = true;
		}
		GameImage.showImageHashMap();
		GameImage.showMemory();
		
		down = (int)(179 * GameConfig.f_zoomy);
		
		return false;
	}

	public void initmission(){
		textBoxs = new TextBox[3];
		for (int i=0; i<textBoxs.length; i++) {
			textBoxs[i] = new TextBox();
			textBoxs[i].setColor(col);
			textBoxs[i].setTextAlign(TextBox.LEFT);
			textBoxs[i].setKeyWord(0, ""+5000);
			textBoxs[i].setKeyWord(1, ""+10000);
			textBoxs[i].setDefaultColor(col[0]);
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
			}
//			else if (i >= 14 && i < 26) {
//				mission = new int[12];
//				for (int j=0; j<12; j+=4) {
//					mission[j] = LevelData.getData().get(i+j);
//					mission[j+1] = LevelData.getData().get(i+j+1);
//					mission[j+2] = LevelData.getData().get(i+j+2);
//					mission[j+3] = LevelData.getData().get(i+j+3);
//					if(mission[j]==GameMission.MISSION_13){
//						mission[j+2]=score;
//						if(mission[j+2]>=mission[j+3]){
//							List<Integer> _lData =LevelData.getData();
//							_lData.set(j/4+2, 1);
//						}
//					}
//				}
//				i += 12-1;
//			}
//			else if (i == 26) {
//				temp_start = i+1;
//				temp_len = LevelData.getData().get(i) * 3;
//				card = new int[temp_len];
//			} else if (i == temp_start){
//				for (int j=0; j<temp_len; j+=3) {
//					card[j] = LevelData.getData().get(temp_start+j);
//					card[j+1] = LevelData.getData().get(temp_start+j+1);
//					card[j+2] = LevelData.getData().get(temp_start+j+2);
////					VeggiesData.setAllCardNum(LevelData.getData()[temp_start+j], (LevelData.getData()[temp_start+j+1] - LevelData.getData()[temp_start+j+2]));
//				}
//				i += temp_len-1;
//			}
		}
//		LevelData.getData().clear();
		
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
//				textBoxs[i].setKeyWord(17, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
//				textBoxs[i].setKeyWord(17, ""+(star[i] == 0?mission[i*4+3]:mission[i*4+2]));
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
				if(mission[i*4]==GameMission.MISSION_13)
					textBoxs[i].setString(GameWord.missionText[GameWord.useLanguage][mission[i*4]][0] + GameWord.missionState[GameWord.useLanguage][0]+GameWord.missionText[GameWord.useLanguage][mission[i*4]][2]);
				else
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
	}
	
	public static char Char_num1[]	 = {'0','1','2','3','4','5','6','7','8','9'};
	public void paint(Canvas canvas) {
		lsm.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		int tempYi=0;
		if(index==0){
			tempYi=(int)((GameConfig.GameScreen_Height-300*GameConfig.f_transy)*index2/index2_max[0]-(GameConfig.GameScreen_Height-300*GameConfig.f_transy));
		}
		
		if(enterHideStage){ //小篮怪
		 
			GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(29 * GameConfig.f_zoomx), (int)(163 * GameConfig.f_zoomy)+tempYi, (int)(476 * GameConfig.f_zoomx), (int)(471 * GameConfig.f_zoomy), -1);
			GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(46 * GameConfig.f_zoomx), (int)(224 * GameConfig.f_zoomy)+tempYi, (int)(442 * GameConfig.f_zoomx), (int)(395 * GameConfig.f_zoomy), -1);
			GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(46 * GameConfig.f_zoomx), (int)(224 * GameConfig.f_zoomy)+tempYi, (int)(442 * GameConfig.f_zoomx), (int)(395 * GameConfig.f_zoomy), -1);
			GameStaticImage.s_share_ui_close.drawBitmap(canvas, 456 * GameConfig.f_zoom - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 154 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f)+tempYi, anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
			
			s_interface_ui_ribbon_01.drawBitmap(canvas, s_interface_ui_ribbon_01.bitmap, (int)(71 * GameConfig.f_zoomx), (int)(129 * GameConfig.f_zoomy)+tempYi, null);
			s_interface_ui_shine.drawBitmap(canvas, s_interface_ui_shine.bitmap, (int)(155 * GameConfig.f_zoomx), (int)(174 * GameConfig.f_zoomy)+tempYi, null);
			word_title_reward.drawBitmap(canvas, word_title_reward.bitmap, (int)(166 * GameConfig.f_zoomx), (int)(169 * GameConfig.f_zoomy)+tempYi, null);
			
			int yy = 20;
			box_gold.paintSprite(canvas, 0, (int)((40-yy)*GameConfig.f_zoomy)+tempYi);
			if(index>0 && !isoneOver){
				isoneOver = true;
				box_gold.changeAction(1);
			}else if(isoneOver && box_gold.getActionName() == 0){
				
				for(int i=0;i<_star.length;++i){
					_star[i].draw(canvas, (int)(-yy*GameConfig.f_zoomy));
				}
			}
			
			if(index<=0){
				
			}else{
				GameStaticImage.s_word_num_08[0].drawBitmap(canvas,	GameStaticImage.s_word_num_08, (int)(GameConfig.GameScreen_Width/2-0.5*text_temp_score.length()*GameStaticImage.s_word_num_08[0].bitmap.getWidth()), (int)((459-yy) * GameConfig.f_zoomy)+tempYi, GameConfig.Char_num5,text_temp_score, null, 0, 1);
			}
			
			if (anjianshare) {
				GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, 
						 GameConfig.GameScreen_Width -(int)(164 * GameConfig.f_zoomx * 1.2f) >>1, 
						 (int)((550-yy) * GameConfig.f_zoomy)+tempYi, 
						(int)(164 * GameConfig.f_zoomx * 1.2f), -1);
			} else {				
				GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, 
						GameConfig.GameScreen_Width - (int)(164 * GameConfig.f_zoomx)>>1, 
						(int)((550-yy) * GameConfig.f_zoomy)+tempYi, (int)(164 * GameConfig.f_zoomx), -1);
			}
			GameStaticImage.s_word_share.drawBitmap(canvas,  
					(GameConfig.GameScreen_Width - GameStaticImage.s_word_share.bitmap.getWidth()>>1)  - GameStaticImage.s_word_share.bitmap.getWidth()/2*(anjianshare?0.2f:0.0f), 
					(((int)((550-yy) * GameConfig.f_zoomy))+(GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight() - GameStaticImage.s_word_share.bitmap.getHeight() >>1) +tempYi) +tempYi- GameStaticImage.s_word_share.bitmap.getHeight()/2*(anjianshare?0.2f:0.0f), anjianshare?1.2f:1.0f, anjianshare?1.2f:1.0f, 255, 0, 0, 0);
			
		  return;
		}
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(148 * GameConfig.f_zoomy)+tempYi, (int)(476 * GameConfig.f_zoomx), (int)((549-30) * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(211 * GameConfig.f_zoomy)+tempYi, (int)(443 * GameConfig.f_zoomx), (int)((470-30) * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(211 * GameConfig.f_zoomy)+tempYi, (int)(443 * GameConfig.f_zoomx), (int)((470-30) * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 453 * GameConfig.f_zoom - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f)+tempYi, anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
		
		GameStaticImage.s_interface_ui_ribbon_01.drawBitmap(canvas, GameStaticImage.s_interface_ui_ribbon_01.bitmap, (int)(67 * GameConfig.f_zoomx), (int)(122 * GameConfig.f_zoomy)+tempYi, null);
		GameStaticImage.s_interface_ui_shine.drawBitmap(canvas, GameStaticImage.s_interface_ui_shine.bitmap, (int)(145 * GameConfig.f_zoomx), (int)(157 * GameConfig.f_zoomy)+tempYi, null);
		
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, 0 - GameStaticImage.s_share_ui_back_01.bitmap.getWidth()/3, (int) (GameConfig.GameScreen_Height - 169 * GameConfig.f_zoomy + down ), GameConfig.GameScreen_Width + GameStaticImage.s_share_ui_back_01.bitmap.getWidth()*2/3 , (int)(179 * GameConfig.f_zoomy), -1);
		int tempy = (int)(GameConfig.GameScreen_Height - 179 * GameConfig.f_zoomy+down);
		int temp_x = (int)(9 * GameConfig.f_zoomx);
		int temp_y = (int)(GameConfig.GameScreen_Height - (147) * GameConfig.f_zoomy +down - (8) * GameConfig.f_zoomy);
		int temp_h = (int)(147 * GameConfig.f_zoomy);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, temp_x, temp_y, (int)(517 * GameConfig.f_zoomx), temp_h, -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, temp_x, temp_y, (int)(517 * GameConfig.f_zoomx), temp_h, -1);
		
		
		if (anjianshare) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.LevelSuccess.share_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.LevelSuccess.share_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)+tempYi), (int)(122 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.LevelSuccess.share_xy[0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.share_xy[1] * GameConfig.f_zoomy)+tempYi, (int)(122 * GameConfig.f_zoomx), -1);
		}
		GameStaticImage.s_word_share.drawBitmap(canvas, Location.LevelSuccess.share_word_xy[0] * GameConfig.f_zoomx - GameStaticImage.s_word_share.bitmap.getWidth()/2*(anjianshare?0.2f:0.0f), Location.LevelSuccess.share_word_xy[1] * GameConfig.f_zoomy +tempYi- GameStaticImage.s_word_share.bitmap.getHeight()/2*(anjianshare?0.2f:0.0f), anjianshare?1.2f:1.0f, anjianshare?1.2f:1.0f, 255, 0, 0, 0);
		if (anjianagain) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.LevelSuccess.again_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.LevelSuccess.again_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)+tempYi), (int)(122 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.LevelSuccess.again_xy[0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.again_xy[1] * GameConfig.f_zoomy+tempYi), (int)(122 * GameConfig.f_zoomx), -1);
		}
		GameStaticImage.s_word_again.drawBitmap(canvas, Location.LevelSuccess.share_again_xy[0] * GameConfig.f_zoomx - GameStaticImage.s_word_again.bitmap.getWidth()/2*(anjianagain?0.2f:0.0f), Location.LevelSuccess.share_again_xy[1] * GameConfig.f_zoomy+tempYi - GameStaticImage.s_word_again.bitmap.getHeight()/2*(anjianagain?0.2f:0.0f), anjianagain?1.2f:1.0f, anjianagain?1.2f:1.0f, 255, 0, 0, 0);
		if (anjiannext) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, (int)(Location.LevelSuccess.next_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)(Location.LevelSuccess.next_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)+tempYi), (int)(122 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, (int)(Location.LevelSuccess.next_xy[0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.next_xy[1] * GameConfig.f_zoomy)+tempYi, (int)(122 * GameConfig.f_zoomx), -1);
		}
		GameStaticImage.s_word_next.drawBitmap(canvas, Location.LevelSuccess.share_next_xy[0] * GameConfig.f_zoomx - GameStaticImage.s_word_next.bitmap.getWidth()/2*(anjiannext?0.2f:0.0f), Location.LevelSuccess.share_next_xy[1] * GameConfig.f_zoomy - GameStaticImage.s_word_next.bitmap.getHeight()/2*(anjiannext?0.2f:0.0f)+tempYi, anjiannext?1.2f:1.0f, anjiannext?1.2f:1.0f, 255, 0, 0, 0);
		
		if (Configs.isDebug) {
			lsm.paintCollisionRect(canvas, (int)(Location.LevelSuccess.share_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.share_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.share_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.share_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()));
			lsm.paintCollisionRect(canvas, (int)(Location.LevelSuccess.again_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.again_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.again_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.again_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()));
			lsm.paintCollisionRect(canvas, (int)(Location.LevelSuccess.next_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.next_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.next_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.next_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()));
		}
		
		//中间画星星
		for(int i=1;i<4;i++){
			if(star2>=i&&index>=i){
				if(index==i){
					GameStaticImage.s_interface_star_12[0].drawBitmap(canvas, GameStaticImage.s_interface_star_12[0].bitmap, (int)(Location.LevelSuccess.star[i-1][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[i-1][1] * GameConfig.f_zoomy+tempYi), null);
					float size=3f-2f*index2/(float)index2_max[i];
					GameStaticImage.s_interface_star_09[0].drawBitmap(canvas, (int)(Location.LevelSuccess.star[i-1][0] * GameConfig.f_zoomx)-(size-1)/2f*GameStaticImage.s_interface_star_09[0].bitmap.getWidth(), (int)(Location.LevelSuccess.star[i-1][1] * GameConfig.f_zoomy+tempYi)-(size-1)/2f*GameStaticImage.s_interface_star_09[0].bitmap.getHeight(), size, size, 255*index2/index2_max[i], 0, 0, 0, 0, 0, 0);
				}else{
					GameStaticImage.s_interface_star_09[0].drawBitmap(canvas, GameStaticImage.s_interface_star_09[0].bitmap, (int)(Location.LevelSuccess.star[i-1][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[i-1][1] * GameConfig.f_zoomy+tempYi), null);
				}
			}else{
				GameStaticImage.s_interface_star_12[0].drawBitmap(canvas, GameStaticImage.s_interface_star_12[0].bitmap, (int)(Location.LevelSuccess.star[i-1][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[i-1][1] * GameConfig.f_zoomy+tempYi), null);
			}
		}
		
//		switch (star2) {
//		case 0:
//			GameStaticImage.s_interface_star_12[0].drawBitmap(canvas, GameStaticImage.s_interface_star_12[0].bitmap, (int)(Location.LevelSuccess.star[0][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[0][1] * GameConfig.f_zoomy+tempYi), null);
//			GameStaticImage.s_interface_star_12[1].drawBitmap(canvas, GameStaticImage.s_interface_star_12[1].bitmap, (int)(Location.LevelSuccess.star[1][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[1][1] * GameConfig.f_zoomy+tempYi), null);
//			GameStaticImage.s_interface_star_12[2].drawBitmap(canvas, GameStaticImage.s_interface_star_12[2].bitmap, (int)(Location.LevelSuccess.star[2][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[2][1] * GameConfig.f_zoomy+tempYi), null);
//			break;
//		case 1:
//			GameStaticImage.s_interface_star_09[0].drawBitmap(canvas, GameStaticImage.s_interface_star_09[0].bitmap, (int)(Location.LevelSuccess.star[0][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[0][1] * GameConfig.f_zoomy+tempYi), null);
//			GameStaticImage.s_interface_star_12[1].drawBitmap(canvas, GameStaticImage.s_interface_star_12[1].bitmap, (int)(Location.LevelSuccess.star[1][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[1][1] * GameConfig.f_zoomy+tempYi), null);
//			GameStaticImage.s_interface_star_12[2].drawBitmap(canvas, GameStaticImage.s_interface_star_12[2].bitmap, (int)(Location.LevelSuccess.star[2][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[2][1] * GameConfig.f_zoomy+tempYi), null);
//			break;
//		case 2:
//			GameStaticImage.s_interface_star_09[0].drawBitmap(canvas, GameStaticImage.s_interface_star_09[0].bitmap, (int)(Location.LevelSuccess.star[0][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[0][1] * GameConfig.f_zoomy+tempYi), null);
//			GameStaticImage.s_interface_star_09[1].drawBitmap(canvas, GameStaticImage.s_interface_star_09[1].bitmap, (int)(Location.LevelSuccess.star[1][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[1][1] * GameConfig.f_zoomy+tempYi), null);
//			GameStaticImage.s_interface_star_12[2].drawBitmap(canvas, GameStaticImage.s_interface_star_12[2].bitmap, (int)(Location.LevelSuccess.star[2][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[2][1] * GameConfig.f_zoomy+tempYi), null);
//			break;
//		case 3:
//			GameStaticImage.s_interface_star_09[0].drawBitmap(canvas, GameStaticImage.s_interface_star_09[0].bitmap, (int)(Location.LevelSuccess.star[0][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[0][1] * GameConfig.f_zoomy+tempYi), null);
//			GameStaticImage.s_interface_star_09[1].drawBitmap(canvas, GameStaticImage.s_interface_star_09[1].bitmap, (int)(Location.LevelSuccess.star[1][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[1][1] * GameConfig.f_zoomy+tempYi), null);
//			GameStaticImage.s_interface_star_09[2].drawBitmap(canvas, GameStaticImage.s_interface_star_09[2].bitmap, (int)(Location.LevelSuccess.star[2][0] * GameConfig.f_zoomx), (int)(Location.LevelSuccess.star[2][1] * GameConfig.f_zoomy+tempYi), null);
//			break;
//		}
		
		//中间橙色背景
//		GameStaticImage.s_gameover_ui_back_10.drawBitmap(canvas, null, (int)(83 * GameConfig.f_zoomx), (int)(391 * GameConfig.f_zoomy), (int)(370 * GameConfig.f_zoomx), -1);
		GameStaticImage.s_gameover_ui_back_10.drawBitmap(canvas, null, (int)(83 * GameConfig.f_zoomx), (int)(490 * GameConfig.f_zoomy)+tempYi, (int)(370 * GameConfig.f_zoomx), (int)(99 * GameConfig.f_zoomy), -1);
		
		if (isNewRecord) {
			GameStaticImage.s_shop_reward.drawBitmap(canvas, Location.LevelSuccess.newRecord_bg_xy[0] * GameConfig.f_zoomx + GameStaticImage.s_shop_reward.bitmap.getWidth() /2 * (1 - newRecord_r) , Location.LevelSuccess.newRecord_bg_xy[1] * GameConfig.f_zoomy+tempYi + GameStaticImage.s_shop_reward.bitmap.getHeight() /2 * (1 - newRecord_r) + newRecord_move_y * GameConfig.f_zoomy, newRecord_r, newRecord_r, 255, 0, 0, 0);
//			GameStaticImage.s_shop_reward.drawBitmap(canvas, GameStaticImage.s_shop_reward.bitmap, Location.LevelSuccess.newRecord_bg_xy[0] * GameConfig.f_zoomx, Location.LevelSuccess.newRecord_bg_xy[1] * GameConfig.f_zoomy, null);
			GameStaticImage.s_word_newrecord.drawBitmap(canvas, Location.LevelSuccess.newRecord_xy[0] * GameConfig.f_zoomx + GameStaticImage.s_word_newrecord.bitmap.getWidth() /2 * (1 - newRecord_r), Location.LevelSuccess.newRecord_xy[1] * GameConfig.f_zoomy +tempYi+ GameStaticImage.s_word_newrecord.bitmap.getHeight() /2 * (1 - newRecord_r) + newRecord_move_y * GameConfig.f_zoomy, newRecord_r, newRecord_r, 255, 0, 0, 0);
//			GameStaticImage.s_word_newrecord.drawBitmap(canvas, GameStaticImage.s_word_newrecord.bitmap, Location.LevelSuccess.newRecord_xy[0] * GameConfig.f_zoomx, Location.LevelSuccess.newRecord_xy[1] * GameConfig.f_zoomy, null);
		}
		
		bestScore.paintText(canvas, (int)(83 * GameConfig.f_zoomx + 370 * GameConfig.f_zoomx / 2 - bestScore.width/2), (int)(490 * GameConfig.f_zoomy - bestScore.height() - 5 * GameConfig.f_zoomy+tempYi));
//		GameStaticImage.setPaint(20, Color.argb(255, 121, 76, 37));
//		canvas.drawText(GameWord.successLevel_Score[GameWord.useLanguage] + VeggiesData.getLevelScore()[VeggiesData.getCurrentLevel()], Location.LevelSuccess.text_score_xy[0] * GameConfig.f_zoomx, Location.LevelSuccess.text_score_xy[1] * GameConfig.f_zoomy, GameStaticImage.paint);
//		canvas.drawText(GameWord.successLevel_Score[GameWord.useLanguage] + ExternalMethods.IntToStr_En(score), Location.LevelSuccess.text_score_xy[0] * GameConfig.f_zoomx, Location.LevelSuccess.text_score_xy[1] * GameConfig.f_zoomy, GameStaticImage.paint);
//		canvas.drawText(GameWord.successLevel_Time[GameWord.useLanguage] + time_text, Location.LevelSuccess.text_time_xy[0] * GameConfig.f_zoomx, Location.LevelSuccess.text_time_xy[1] * GameConfig.f_zoomy, GameStaticImage.paint);
		if(index<=0){
			
		}else{
			GameStaticImage.s_word_num_08[0].drawBitmap(canvas,	GameStaticImage.s_word_num_08, (int)(GameConfig.GameScreen_Width/2-0.5*text_temp_score.length()*GameStaticImage.s_word_num_08[0].bitmap.getWidth()), (int)(399 * GameConfig.f_zoomy)+tempYi, GameConfig.Char_num5,text_temp_score, null, 0, 1);
		}
		GameStaticImage.setPaint(20, Color.WHITE);
		for(int i=0; i<GameStaticImage.reward.length; i++) {
			GameStaticImage.reward[i].drawBitmap(canvas, GameStaticImage.reward[i].bitmap, Location.LevelSuccess.gold_gem_card_xy[i][0] * GameConfig.f_zoomx, Location.LevelSuccess.gold_gem_card_xy[i][1] * GameConfig.f_zoomy+tempYi, null);
			
			GameStaticImage.s_gameover_ui_back_09.drawBitmap(canvas, GameStaticImage.s_gameover_ui_back_09.bitmap, Location.LevelSuccess.reward_bg_xy[i][0] * GameConfig.f_zoomx, Location.LevelSuccess.reward_bg_xy[i][1] * GameConfig.f_zoomy+tempYi, null);
			
			if (reward_place[i] == 4) {
				canvas.drawText(reward_text[i], Location.LevelSuccess.reward_num_si_xy[i][0] * GameConfig.f_zoomx, Location.LevelSuccess.reward_num_si_xy[i][1] * GameConfig.f_zoomy+tempYi, GameStaticImage.paint);
			} else {
				Rect rect = new Rect();              
				GameStaticImage.paint.getTextBounds(reward_text[i], 0, reward_text[i].length(), rect);
				
				canvas.drawText(reward_text[i], (Location.LevelSuccess.reward_num_ge_xy[i][0] - (reward_place[i]-1) * 7)* GameConfig.f_zoomx-rect.width()/2, Location.LevelSuccess.reward_num_ge_xy[i][1] * GameConfig.f_zoomy+tempYi, GameStaticImage.paint);
			}
		}
		
		int tempw=bitmap_title_level_2.getWidth()+bitmap_num_09[0].getWidth()*(""+(VeggiesData.getCurrentLevel()+1)).length();
		int tempx=(GameConfig.GameScreen_Width-tempw)/2;
		tempy=(int)(70*GameConfig.f_zoom);
		canvas.drawBitmap(bitmap_title_level_2, tempx, tempy+tempYi, null);
		tempx+=bitmap_title_level_2.getWidth();
		ExternalMethods.DrawNumber1(canvas, bitmap_num_09, tempx, tempy+tempYi, 0, Char_num1, ""+(VeggiesData.getCurrentLevel()+1), null, 0, 1f);
//		int star_num=0;
		for (int i=0; i<star.length; i++) {
			if (star[i] == 1) {
				GameStaticImage.s_interface_star_05.drawBitmap(canvas, GameStaticImage.s_interface_star_05.bitmap, (int)(Location.Pause.star_xy[i][0] * GameConfig.f_zoomx), (int)(Location.Pause.star_xy[i][1] * GameConfig.f_zoomy)+down, null);
			} else {
				GameStaticImage.s_interface_star_08.drawBitmap(canvas, GameStaticImage.s_interface_star_08.bitmap, (int)(Location.Pause.star_xy[i][0] * GameConfig.f_zoomx), (int)(Location.Pause.star_xy[i][1] * GameConfig.f_zoomy)+down, null);
			}
//			ui_progress_2.drawBitmap(canvas, (Location.LevelSuccess.mission_xy[i][0] - 10) * GameConfig.f_zoomx, Location.LevelSuccess.mission_xy[i][1] * GameConfig.f_zoomy - ui_progress_2.bitmap.getHeight()/2+tempYi, 1f, 1f, 255, 0, 0, 0, 0, 0, 0);
			textBoxs[i].paintText(canvas, (int)((Location.LevelSuccess.mission_xy[i][0]) * GameConfig.f_zoomx), (int)((Location.LevelSuccess.mission_xy[i][1]) * GameConfig.f_zoomy +down- textBoxs[i].height()/2 ));
			
//			if (star[i] == 1) {
//				if(index-1==star_num){
//					float size=3f-2f*index2/(float)index2_max[i];
//					ui_complete.drawBitmap(canvas, Location.LevelSuccess.mission_xy[i][0]* GameConfig.f_zoomx - ui_complete.bitmap.getWidth()-(ui_complete.bitmap.getWidth()*(size-1)/2f), (Location.LevelSuccess.mission_xy[i][1])*GameConfig.f_zoomy-ui_complete.bitmap.getHeight()/2-(ui_complete.bitmap.getHeight()*(size-1)/2f)+tempYi, size, size, 255*index2/index2_max[index], 0, 0, 0, 0, 0, 0);
//				}else if(index-1>star_num){
//					ui_complete.drawBitmap(canvas, Location.LevelSuccess.mission_xy[i][0]* GameConfig.f_zoomx - ui_complete.bitmap.getWidth(), (Location.LevelSuccess.mission_xy[i][1])*GameConfig.f_zoomy-ui_complete.bitmap.getHeight()/2+tempYi, 1f, 1f, 255, 0, 0, 0, 0, 0, 0);
//				}
//				star_num++;
//			}
		}
		
		//番茄动画
		int tempi = 0;
		if (GameConfig.i_coke % 12 < 12) {
			tempi = GameConfig.i_coke /2 % 6;
		}
		if(index<=3){
			tempi = 0;
		}
		lsm.drawBitmap(canvas, GameStaticImage.s_gameover_tomato_over_01[tempi>2?2:tempi], Location.LevelSuccess.tomato_xy[0] * GameConfig.f_zoomx, Location.LevelSuccess.tomato_xy[1] * GameConfig.f_zoomy+tempYi, 1f, 1f, 255, 0, 0, 0, 0, 0, 0);
		if (tempi > 1) {
			lsm.drawBitmap(canvas, GameStaticImage.s_gameover_tomato_over_02[tempi-2], Location.LevelSuccess.throw_flower_xy[0] * GameConfig.f_zoomx, Location.LevelSuccess.throw_flower_xy[1] * GameConfig.f_zoomy+tempYi, 1f, 1f, 255, 0, 0, 0, 0, 0, 0);
		}
		if(GameManager.getGT() == null)
			if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL30]){
				if(GameManager.getGT()==null)
					GameManager.setGT(new GameTeaching());
				temp_x = (int)(83 * GameConfig.f_zoomx)+((int)(370 * GameConfig.f_zoomx)>>1);
				temp_y = (int)(490 * GameConfig.f_zoomy)+((int)(99 * GameConfig.f_zoomy)>>1);
				
//				(int)(83 * GameConfig.f_zoomx), 
//				(int)(490 * GameConfig.f_zoomy), 
//				(int)(370 * GameConfig.f_zoomx), 
//				(int)(99 * GameConfig.f_zoomy)
				
				GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL30, temp_x, temp_y,(int)(370 * GameConfig.f_zoomx), (int)(99 * GameConfig.f_zoomy),  LangUtil.getLangString(LangDefineClient.GUIDE_30), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
			
			}
		
		int y = (int) (GameConfig.GameScreen_Height - 179 * GameConfig.f_zoomy + down -GameStaticImage.ui_renwu.bitmap.getHeight()+ 14 * GameConfig.f_zoomy);
		lsm.drawBitmap(canvas, GameStaticImage.ui_renwu.bitmap, 0, y, 1f, 1f, 255, 0, 0, 0, 0, 0, 0);
		
		
	}
	int indexHide = 0;
	public void run() {
		if(enterHideStage){//小篮怪
//			 if(box_gold.getActionName() != 1 && GameConfig.i_coke%2 == 0){
//				  
//				 box_gold.changeAction(1);
//			 }
			for(int i=0;i<_star.length;++i){
				_star[i].run();
			}
			 box_gold.updataSprite();
		}
		if(isOpen){
			down-=15*GameConfig.f_zoomy;
			if(down<=0){
				down = 0;
			}
		}else {
			down+=15*GameConfig.f_zoomy;
			if(down>=(int)(179 * GameConfig.f_zoomy)){
				down = (int)(179 * GameConfig.f_zoomy);
			}
		}
		if(index2_max[index]>-1){
			index2++;
			if(index2>index2_max[index]){
				index++;
				index2=0;
				if(index==1){
					score_start_time = System.currentTimeMillis();
					if(star2<0){
						index2=index2_max[1];
					}
				}else if(index==2){
					if(star2<1){
						index2=index2_max[2];
					}
				}else if(index==3){
					if(star2<2){
						index2=index2_max[3];
					}
				}
			}
		}
		if(index>=1){
			if (temp_score < score) {
				temp_score += (System.currentTimeMillis() - score_start_time) * score / score_time;
				if (temp_score > score) {
					temp_score = score;
					if (oldscore < score) {
						isNewRecord = true;
					}
				}
				text_temp_score = ExternalMethods.IntToStr_En(temp_score);
				score_start_time = System.currentTimeMillis();
			}
			if (isNewRecord && newRecord_r > 1.0f) {
				if (newRecord_move_y < 0)
					newRecord_move_y += 10;
				newRecord_r -= 0.2f;
				if (newRecord_move_y >= 0) {
					newRecord_move_y = 0;
				}
				if (newRecord_r <= 1.0f) {
					newRecord_r = 1.0f;
				}
			}
		}
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
		lsm = null;
		if(bestScore!=null)
			bestScore.Close();
		bestScore = null;
		
//		GameImage.delImage(ui_progress_2.bitmap);
//		ui_progress_2=null;
//		
//		GameImage.delImage(ui_complete.bitmap);
//		ui_complete=null;
		
		GameImage.delImage(bitmap_title_level_2);
		bitmap_title_level_2=null;
		GameImage.delImageArray(bitmap_num_09);
		bitmap_num_09=null;
		GameManager.setGT(null);
		
		if(box_gold!=null){
			box_gold.recycleBitmap();
			box_gold = null;
			GameImage.delImageArray(share_ui_shine_2);
			share_ui_shine_2 = null;
			_star = null;
		}
		
       if(s_interface_ui_ribbon_01!=null){
    	   	GameImage.delImage(s_interface_ui_ribbon_01.bitmap);
   			s_interface_ui_ribbon_01=null;
    	   	GameImage.delImage(s_interface_ui_shine.bitmap);
    	   	s_interface_ui_shine=null;
    	   	GameImage.delImage(word_title_reward.bitmap);
    	   	word_title_reward=null;
    	   	
       }
		
		
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
		if(index<=3){
			return;
		}
		float x = event.getX();
		float y = event.getY();
		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL42){
					if (ExternalMethods.CollisionTest(x, y, 
							453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
							140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
						453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
						140 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
						anjianclose = true;
					}
				}
				
			}else if (event.getAction() == MotionEvent.ACTION_UP) {
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL30){
					int temp_x = (int)(83 * GameConfig.f_zoomx);
					int temp_y = (int)(490 * GameConfig.f_zoomy);
					int temp_w = temp_x+(int)(370 * GameConfig.f_zoomx);
					int temp_h = temp_y+(int)(99 * GameConfig.f_zoomy);
					if (ExternalMethods.CollisionTest(x, y,
							temp_x, temp_y, temp_w, temp_h)) {
						GameManager.getGT().finish();
						new VeggiesData().saveGame(); 
						 
						GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL42, (int)((453) * GameConfig.f_zoomx)+GameStaticImage.s_share_ui_close.bitmap.getWidth()/2, (int)((140) * GameConfig.f_zoomy)+GameStaticImage.s_share_ui_close.bitmap.getHeight()/2, LangUtil.getLangString(LangDefineClient.GUIDE_42), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
						
					}  	
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL42){
					if (anjianclose && ExternalMethods.CollisionTest(x, y, 
							453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
							140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
						453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
						140 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {

						GameManager.getGT().finish();
						new VeggiesData().saveGame(); 
						
						GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
						if(!VeggiesData.isMuteMusic()){
							GameMedia.playMusic(R.raw.loginl, true, true);
						}
					}
				}
			}
			
			return;
		}
		
		
		
		if(enterHideStage){ //小篮怪
//			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, 
//					, 
//					+tempYi, (int)(164 * GameConfig.f_zoomx), -1);
//	   }
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (ExternalMethods.CollisionTest(x, y, 
						453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
						140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
					453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
					140 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
					anjianclose = true;
				}else if (ExternalMethods.CollisionTest(x, y,
						GameConfig.GameScreen_Width - (int)(164 * GameConfig.f_zoomx)>>1, 
						(int)(550 * GameConfig.f_zoomy), 
						(GameConfig.GameScreen_Width - (int)(164 * GameConfig.f_zoomx)>>1)+(164 * GameConfig.f_zoomx), 
						(int)(550 * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight() )))) {
					anjianshare = true;
				} 
			}else if(event.getAction() == MotionEvent.ACTION_UP){
				if (ExternalMethods.CollisionTest(x, y, 
						453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
						140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
					453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
					140 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
					GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
					if(!VeggiesData.isMuteMusic()){
						GameMedia.playMusic(R.raw.loginl, true, true);
					}
				} else if(anjianshare && 
						ExternalMethods.CollisionTest(x, y,
								GameConfig.GameScreen_Width - (int)(164 * GameConfig.f_zoomx)>>1, 
								(int)(550 * GameConfig.f_zoomy), 
								(GameConfig.GameScreen_Width - (int)(164 * GameConfig.f_zoomx)>>1)+(164 * GameConfig.f_zoomx), 
								(int)(550 * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight() )))){
					if(FacebookOperation.isLanding ){
						FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_PULISH);
					}
				}
				anjianshare = false;
				anjianclose = false;
			}
			
			return;
		}
		
		
		
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (ExternalMethods.CollisionTest(x, y, 
					453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					140 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				140 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			}else if(ExternalMethods.CollisionTest(x, y, 
					0, 
				(int) (GameConfig.GameScreen_Height - 179 * GameConfig.f_zoomy + down -GameStaticImage.ui_renwu.bitmap.getHeight()+ 14 * GameConfig.f_zoomy),
				GameStaticImage.ui_renwu.bitmap.getWidth(), 
				GameStaticImage.ui_renwu.bitmap.getHeight()+(int) (GameConfig.GameScreen_Height - 179 * GameConfig.f_zoomy + down -GameStaticImage.ui_renwu.bitmap.getHeight()+ 14 * GameConfig.f_zoomy))){
				anjianOpen = true;
			}
			else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.LevelSuccess.share_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.share_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.share_xy[0] * GameConfig.f_zoomx - (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.share_xy[1] * GameConfig.f_zoomy - (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjianshare = true;
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.LevelSuccess.again_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.again_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.again_xy[0] * GameConfig.f_zoomx - (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.again_xy[1] * GameConfig.f_zoomy - (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjianagain = true;
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.LevelSuccess.next_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.next_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.next_xy[0] * GameConfig.f_zoomx - (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.next_xy[1] * GameConfig.f_zoomy - (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjiannext = true;
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
				if(!VeggiesData.isMuteMusic()){
					GameMedia.playMusic(R.raw.loginl, true, true);
				}
			}else if(anjianOpen && ExternalMethods.CollisionTest(x, y, 
						0, 
					(int) (GameConfig.GameScreen_Height - 179 * GameConfig.f_zoomy + down -GameStaticImage.ui_renwu.bitmap.getHeight()+ 14 * GameConfig.f_zoomy),
					GameStaticImage.ui_renwu.bitmap.getWidth(), 
					GameStaticImage.ui_renwu.bitmap.getHeight()+(int) (GameConfig.GameScreen_Height - 179 * GameConfig.f_zoomy + down -GameStaticImage.ui_renwu.bitmap.getHeight()+ 14 * GameConfig.f_zoomy))){
					isOpen = !isOpen;
					if(!isOpen){
						down = 0;
					}else
						down = (int)(179 * GameConfig.f_zoomy);
					anjianOpen = false;
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.LevelSuccess.share_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.share_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.share_xy[0] * GameConfig.f_zoomx - (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.share_xy[1] * GameConfig.f_zoomy - (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				//TODO share
				if(FacebookOperation.isLanding ){
					FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_PULISH);
				}
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.LevelSuccess.again_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.again_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.again_xy[0] * GameConfig.f_zoomx - (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.again_xy[1] * GameConfig.f_zoomy - (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				//TODO again
//				GameManager.ResetToRunModule(new GameMain());
				
				GameConfig.b_gameReset = true;
				
				GameManager.ChangeModule(null);
				
			} else if (ExternalMethods.CollisionTest(x, y,
					(int)(Location.LevelSuccess.next_xy[0] * GameConfig.f_zoomx + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(Location.LevelSuccess.next_xy[1] * GameConfig.f_zoomy + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(Location.LevelSuccess.next_xy[0] * GameConfig.f_zoomx - (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 122 * GameConfig.f_zoomx * 1.2f), 
					(int)(Location.LevelSuccess.next_xy[1] * GameConfig.f_zoomy - (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				//TODO next
				if (VeggiesData.getCurrentLevel() != VeggiesData.getGameGuanka().length-1) {
					isNext = true;					
				}
				GameManager.ResetToRunModule(GameMenu.chooseLevelModule);
				if(!VeggiesData.isMuteMusic()){
					GameMedia.playMusic(R.raw.loginl, true, true);
				}
			}
			anjianagain = false;
			anjiannext = false;
			anjianshare = false;
			anjianclose = false;
			anjianOpen = false;
		}
	}
	private int countPlace(int num) {
		int temp = 0;
		if (num == 0) {
			temp = 1;
		} else if(num>=0&&num<=9999) {
            for (int i=0;num!=0; i++) {
                num/=10;
                temp++;
            }
        }
		return temp;
	}
	class Star{
		int index; 
		int time;
		float x,y;
		public void setXY(float _x, float _y){
			x = _x;
			y = _y;
		}
		public void run(){
				time--;
			if(time <= 0){
				if(Math.abs(time)%3 == 0){
					if(index==2)
						index = -1;
						index++;
				}
			}
		}
		
		public void draw(Canvas canvas, int _y){
			if(time <= 0){
				canvas.drawBitmap( share_ui_shine_2[index].bitmap, x	, y+_y, null);
			}
			
		}
			
			
		
	}
}
