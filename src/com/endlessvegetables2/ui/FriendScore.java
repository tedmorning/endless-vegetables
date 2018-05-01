package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.R;
import com.facebook.FacebookOperation;
import com.facebook.FriendIcon;
import com.facebook.FacebookOperation.setFriendIcon;
import com.facebook.UserRequest;
import com.facebook.sdk.FBInterface;
import com.game.data.FaceBookPlayer;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Sprite;
import com.protocol.request.UserGameLevelReq;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.MainActivity;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class FriendScore implements FriendIcon{
	public String playerName = "Cony";
	public int score = 3140652;
	boolean anjianfacebook;

	private List<Friend> _friend;
	public List<Friend> friends;
	public float _friend_x,_friend_y,_friend_w,_friend_h,_friend_s,_friend_sc; //显示区域
	private Sprite[] loading;
	private Sprite share_ui_photo_04;
	private Sprite smallcard_card_friend;

	float move_X;
	public int oldX;
	float correctfriend_move = 5 * GameConfig.f_zoom;
	boolean isCorrectFriendMove;
	int tempi = 0;
	private boolean ismove;
	
	/**
	 * 当前关卡自己前面的数据排名
	 * */
    public static HashMap<Integer, Friend> friendSurpass =  new HashMap<Integer, Friend>();
    /**
     * 自己当前关卡的排名
     * */
    public static int mySurpass;/**
     * 自己当前超过好友的fdid
     * */
    public static String surpassFriendFbid = "";
//	static int f_nexde = 0;
	static int nexde = 0;
	/**
	 * 传入list的坐标和宽高区域，以所用底部为背景大小为准 所有x，y，w，h，都以是屏幕实际坐标值
	 * 
	 * sc 单张的大小
	 * s 间距
	 * 
	 * @param _x
	 * @param _y
	 * @param _w
	 * @param _h
	 */
	public FriendScore(float _x, float _y, float _w, float _h, float sc, float s) {
		FacebookOperation.getFacebook().setFriendIcon(this);
		testFriend();
		_friend_w = _w;
		_friend_h = _h;
		_friend_sc = sc;
		_friend_s = s;
		_friend_x = _x;
		_friend_y = _y;
	}

	public void init() {
		// testFriend();
		move_X = 0;
		oldX = 0;
		isCorrectFriendMove = false;
		loading = GameImage.getAutoSizecutSprite(GameStaticImage.share_loading_03, 9, 1, GameImage.Sort_line);
		tempi = 0;
		if (share_ui_photo_04 == null)
			share_ui_photo_04 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_photo_04));
		if(smallcard_card_friend==null){
			smallcard_card_friend = new Sprite(GameImage.getImage(GameStaticImage.smallcard_card_friend));
		}
	}

	public void testFriend() {

		if (FacebookOperation.isLanding && FacebookOperation.getFacebook().getLoadingFriend()) {
			initFriend();
		} else {
			FacebookOperation.getFacebook().setFriendInt(new setFriendIcon() {

				@Override
				public void onSetFriend() {
					// TODO Auto-generated method stub
					initFriend();
				}
			});
		}
	}
	
	private void initFriend() {
		// List<Bitmap> icon = FacebookOperation.getFacebook().getListicon();
		if(friends!=null || _friend!=null)return;
		
		//点击的关卡
		final int level = VeggiesData.getCurrentLevel();
		
		if (share_ui_photo_04 == null)
			share_ui_photo_04 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_photo_04));

		if(smallcard_card_friend==null)
			smallcard_card_friend = new Sprite(GameImage.getImage(GameStaticImage.smallcard_card_friend));
		
//		f_nexde = 0;
		nexde = 0;
		final List<Friend> f_type = new ArrayList<Friend>(FacebookOperation.playerMap.size());
		
		
		
		final int width = share_ui_photo_04.bitmap.getWidth();
		final int height = share_ui_photo_04.bitmap.getHeight();
		if(!FacebookOperation.player.isHave(level)){
			UserRequest.getUser().ReqLeveInfo(level, FacebookOperation.player.getid_server());
			FacebookOperation.player.setUserlevel(new FaceBookPlayer.UserLevel() {
				
				@Override
				public void onLevelRes(FaceBookPlayer player, int level, long score, int star) {
					// TODO Auto-generated method stub
					player.addLG(level, score, star);
					// 图片缩放
					Bitmap temp = FBInterface.allIconMap.get(player.getId_facebook());//user.getIcon();
					if(temp!=null){
						temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx,
								height - 6 * GameConfig.f_zoomy);
					} 
					Friend friend =	new Friend(nexde+1, player.getName(), player.getScore(level), nexde + 1,
							temp);
					friend.setfbID(player.getId_facebook());
					f_type.add(friend);
					nexde++;
					info(f_type);
				}
			}); 
		}else{
			Bitmap temp = FBInterface.allIconMap.get(FacebookOperation.player.getId_facebook());//user.getIcon();
			if(temp!=null){
				temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx,
						height - 6 * GameConfig.f_zoomy);
			}
			Friend friend = new Friend(nexde+1, FacebookOperation.player.getName(), FacebookOperation.player.getScore(level), nexde + 1,
					temp);
			friend.setfbID(FacebookOperation.player.getId_facebook());
			f_type.add(friend);
			nexde++;
		}
		
		Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
		while (iterator.hasNext()) {
			FaceBookPlayer player = FacebookOperation.playerMap.get(iterator.next());
			if(!player.isHave(level)){ //没有需要请求
				UserRequest.getUser().ReqLeveInfo(level, player.getid_server());
				player.setUserlevel(new FaceBookPlayer.UserLevel() {
					
					@Override
					public void onLevelRes(FaceBookPlayer player, int level, long score, int star) {
						// TODO Auto-generated method stub
						player.addLG(level, score, star);
						// 图片缩放
						Bitmap temp = FBInterface.allIconMap.get(player.getId_facebook());//user.getIcon();
						if(temp!=null){
							temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx,
									height - 6 * GameConfig.f_zoomy);
						} 
						
						Friend freind = new Friend(nexde+1, player.getName(), player.getScore(level), nexde + 1,
								temp);
						freind.setfbID(player.getId_facebook());
						f_type.add(freind);
						nexde++;
						info(f_type);
					}
				}); 
			}else{ 
				// 图片缩放
				Bitmap temp = FBInterface.allIconMap.get(player.getId_facebook());//cu.getIcon();
				if(temp!=null)
					temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx, height - 6 * GameConfig.f_zoomy);
				Friend friend = new Friend(nexde+1, player.getName(), player.getScore(level), nexde + 1,
						temp);
				friend.setfbID(player.getId_facebook());
				f_type.add(friend);
				nexde++;
			}
		}

		info(f_type);
		
	}

	private void info(List<Friend> f_type){
		friendSurpass.clear();
		int size = FacebookOperation.playerMap.size()+1; //还有个自己
		if((nexde)>=size){
			friends = new ArrayList<Friend>(size);
			_friend = new ArrayList<Friend>(size);
			Object[] mInfo = f_type.toArray();
			Arrays.sort(mInfo, new FacebookOperation.comparatorFriendByScore());
//			
			_friend.add(new Friend(-1, "", 0, 0, null));
			boolean temp = false;
			for (int i = 0; i < mInfo.length; i++){
				Friend friend = (Friend) mInfo[i];
				friend.rank = i+1;
				friend.index = i+1;
				
				if(friend.name.equals(FacebookOperation.player.getName())){
					i = friend.rank;
					temp = true;
				}else if(!temp)
					friendSurpass.put(friend.rank, friend);
//					mySurpass.put(friend.rank, friend.score);
				
				friends.add(friend);
				if(i<=5){
					_friend.add(friend);
				}
			}
		}
	}
	
	public void paint(Canvas canvas, Paint paint, float y1) {
		// photo.drawBitmap(canvas, photo.bitmap, x, y, null);
		// paint.setColor(0xff824d22);
		// paint.setTextSize(22*GameConfig.f_zoom);
		// canvas.drawText(name, x - 1 * GameConfig.f_zoom, y +
		// photo.bitmap.getHeight() + 20 * GameConfig.f_zoom, paint);
		// canvas.drawText(scoreText, x - 1 * GameConfig.f_zoom, y +
		// photo.bitmap.getHeight() + 46 * GameConfig.f_zoom, paint);
		// if (rank <= 3 && rank >0)
		// ExternalMethods.DrawNumber1(canvas, rank_number, (int)(x + 72 *
		// GameConfig.f_zoom), (int)(y + 9 * GameConfig.f_zoom),
		// GameConfig.Char_num1, Integer.toString(rank), null, 1,1);
		float temp_X = _friend_x;
		float temp_Y = _friend_y+y1;
		float tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy+y1;
		
		// 登陆
		if (FacebookOperation.isLanding) {
			canvas.save();
			canvas.clipRect(_friend_x, _friend_y - _friend_s / 2+y1, _friend_x
					+ _friend_w - _friend_s, _friend_y + _friend_h + _friend_s / 2+y1);
			paint.setColor(0xffBD6D18);
			paint.setTextSize(20 * GameConfig.f_zoom);
			if (_friend != null) {
				int onew = GameStaticImage.s_word_num_02[0].bitmap.getWidth();
				int oneh = GameStaticImage.s_word_num_02[0].bitmap.getHeight();
				for (int i = 0; i < _friend.size(); i++) {
					if (_friend.get(i).icon == null || _friend.get(i).icon.bitmap == null) {
						canvas.drawBitmap(
								GameStaticImage.s_share_ui_photo_01.bitmap, move_X
										+ temp_X + (-1 + i)
										* (_friend_sc + _friend_s), temp_Y, null);
					} else {
						// icon
						canvas.drawBitmap(_friend.get(i).icon.bitmap, move_X + temp_X
								+ (-1 + i) * (_friend_sc + _friend_s) + 3+onew, temp_Y
								+ 3 * GameConfig.f_zoomy, null);
						// 背景框
						share_ui_photo_04.drawBitmap(canvas,
								share_ui_photo_04.bitmap, move_X + temp_X
										+ (-1 + i) * (_friend_sc + _friend_s)+onew,
								temp_Y, null);

						canvas.drawText(_friend.get(i).name, move_X + temp_X
								+ (-1 + i) * (_friend_sc + _friend_s)+onew, temp_Y + 83
								* GameConfig.f_zoomy, paint);
						if(_friend.get(i).score<=0)
							_friend.get(i).score = 0;
						canvas.drawText(""+_friend.get(i).score, move_X + temp_X
								+ (-1 + i) * (_friend_sc + _friend_s)+onew, temp_Y + 103
								* GameConfig.f_zoomy, paint);
						if (_friend.get(i).rank <= 3 && _friend.get(i).rank > 0)
							GameStaticImage.s_word_num_02[0].drawBitmap(
									canvas, 
									GameStaticImage.s_word_num_02,
									(int)(move_X + temp_X
									+ (-1 + i) * (_friend_sc + _friend_s) + 3), (int)(temp_Y
									+ 3 * GameConfig.f_zoomy)+((share_ui_photo_04.bitmap.getHeight()-oneh)/2), GameConfig.Char_num1, Integer.toString(_friend.get(i).rank), null, 1, 1);
					}
					
				}

				canvas.restore();
				if (_friend.size() >= 2 && _friend.get(1).index == 1
						&& move_X >= 0) {
				} else {
					GameStaticImage.s_share_ui_arrows_01_02[0].drawBitmap(canvas,
							GameStaticImage.s_share_ui_arrows_01_02[0].bitmap,
							28 * GameConfig.f_zoomx, tempy + 132
									* GameConfig.f_zoomy, null);
				}
				if (_friend.size() < 5
						|| (_friend.size() >= 5
								&& _friend.get(4).index == friends.size() && move_X <= 0)) {
				} else {
					GameStaticImage.s_share_ui_arrows_01_02[1].drawBitmap(canvas,
							GameStaticImage.s_share_ui_arrows_01_02[1].bitmap,
							493 * GameConfig.f_zoomx, tempy + 132
									* GameConfig.f_zoomy, null);
				}
			} else if (!FacebookOperation.isOpenNet && !FacebookOperation.getFacebook().getFriendNet()) {
				// 显示loading
				int h = (int) (270 * GameConfig.f_zoomy);
				int y = (int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy);
				float tempx = ((float) ((GameConfig.GameScreen_Width - loading[0].bitmap.getWidth()) / 2));
				tempy = y + ((float) ((h - loading[0].bitmap.getHeight()) / 2));

				loading[tempi].drawBitmap(canvas, loading[tempi].bitmap, tempx, tempy + y1 * GameConfig.f_zoomy, null);

			} 

			if(FacebookOperation.isOpenNet && _friend == null){ // 没网络

				int h = (int) (270 * GameConfig.f_zoomy);
				int y = (int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy);
				String temp = LangUtil.getLangString(LangDefineClient.CHECK_NET);
				float tempx = ((float) ((GameConfig.GameScreen_Width - paint.measureText(temp)) / 2));
				tempy = y + ((float) ((h - paint.getTextSize()) / 2));

				canvas.drawText(temp, tempx, tempy, paint);

			}
		} else { 
			
			// TODO 未登录绘制
			paint.setColor(0xff824d22);
			paint.setTextSize(22 * GameConfig.f_zoom);
			float tempx = _friend_x + (_friend_s * 2 + GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth());
			tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
			
			// GameStaticImage.s_share_ui_photo_01.drawBitmap(canvas,
			// GameStaticImage.s_share_ui_photo_01.bitmap, move_X + tempx, tempy
			// + y1 * GameConfig.f_zoomy, null);
			if(FBInterface.allIconMap!=null && FBInterface.user!=null){
				Bitmap icon = FBInterface.allIconMap.get(FBInterface.user.getId());
				if (FBInterface.user !=null && icon != null) {
					canvas.drawBitmap(icon,  Location.GameEquip.systemfriend_icon_xy[0] * GameConfig.f_zoomx, tempy + 102 * GameConfig.f_zoomy+ y1 * GameConfig.f_zoomy, null);
					canvas.drawText(FBInterface.user.getName(), Location.GameEquip.systemfriend_icon_xy[0] * GameConfig.f_zoomx, tempy + 102 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_photo_01.bitmap.getHeight() + _friend_s  + y1 * GameConfig.f_zoomy, paint);
				}
			} else {
				GameStaticImage.s_share_ui_photo_01.drawBitmap(canvas, GameStaticImage.s_share_ui_photo_01.bitmap,
						Location.GameEquip.systemfriend_icon_xy[0] * GameConfig.f_zoomx, tempy + 102 * GameConfig.f_zoomy+ y1 * GameConfig.f_zoomy, null);
				canvas.drawText(playerName, Location.GameEquip.systemfriend_icon_xy[0] * GameConfig.f_zoomx, tempy + 102 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_photo_01.bitmap.getHeight() + _friend_s + y1 * GameConfig.f_zoomy, paint);
			}

			canvas.drawText(ExternalMethods.IntToStr_En(score), Location.GameEquip.systemfriend_icon_xy[0] * GameConfig.f_zoomx, tempy + 102 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_photo_01.bitmap.getHeight()  + (_friend_s ) * 2  + y1 * GameConfig.f_zoomy, paint);

			// fackebook
			GameStaticImage.s_interface_icon_facebook_02.drawBitmap(
					canvas,
					357
							* GameConfig.f_zoomx
							- GameStaticImage.s_interface_icon_facebook_02.bitmap
									.getWidth() / 2
							* (anjianfacebook ? 0.2f : 0f),
					tempy+ y1 * GameConfig.f_zoomy
							+ 101
							* GameConfig.f_zoomy
							- GameStaticImage.s_interface_icon_facebook_02.bitmap
									.getHeight() / 2
							* (anjianfacebook ? 0.2f : 0f),
					anjianfacebook ? 1.2f : 1f, anjianfacebook ? 1.2f : 1f,
					255, 0, 0, 0);
		}
	}

	public void run() {
		if (_friend == null && !FacebookOperation.getFacebook().getFriendNet()) {
			// tempi = 0;
			if (GameConfig.i_coke % 2 == 0) {
				tempi++;
				if (tempi == 9)
					tempi = 0;
			}
		}

		if (_friend != null && FacebookOperation.isLanding
				&& isCorrectFriendMove) {
			move_X += correctfriend_move;
			if (move_X <= -(_friend_sc + _friend_s)) {
				if (_friend.get(_friend.size() - 2).index != friends
						.size())
					addRightFriend();
				move_X = 0;
				correctfriend_move = Math.abs(correctfriend_move);
				isCorrectFriendMove = false;
			} else if (move_X > -Math.abs(correctfriend_move)
					&& move_X < Math.abs(correctfriend_move)) {
				move_X = 0;
				correctfriend_move = Math.abs(correctfriend_move);
				isCorrectFriendMove = false;
			} else if (move_X >= _friend_sc + _friend_s) {
				if (_friend.get(_friend.size() - 1).index != 1)
					addLeftFriend();
				move_X = 0;
				correctfriend_move = Math.abs(correctfriend_move);
				isCorrectFriendMove = false;
			}
		}
	}

	public void onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (FacebookOperation.isLanding) {

			} else {	
				float tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
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
				}
			}
			if (ExternalMethods.CollisionTest(x, y, _friend_x, _friend_y - _friend_s / 2, 
					_friend_x + _friend_w - _friend_s, _friend_y + _friend_h + _friend_s / 2)) {
					ismove = true;
					oldX = (int)x;
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (FacebookOperation.isLanding && _friend != null) {
				if(ismove){
					if (move_X < -(_friend_sc + _friend_s) / 2
							|| (move_X > 0 && move_X < (_friend_sc + _friend_s) / 2)) {
						// 向_friend[0]靠近 -
						correctfriend_move = -correctfriend_move;
						isCorrectFriendMove = true;
					} else if ((move_X < 0 && move_X >= -(_friend_sc + _friend_s) / 2 || move_X >= (_friend_sc + _friend_s) / 2)) {
						// 向_friend[1]靠近 +
						isCorrectFriendMove = true;
					}
				}
			} else {
				float tempy = GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy;
				
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
//					ChooseLevelModule.sendMessage("facebook被点击");
					if(FacebookOperation.gameState!= FacebookOperation.GAME_STATE_INIT_LOADING)
						FacebookOperation.gameState= FacebookOperation.GAME_STATE_INIT_LOADING;
					FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.level_friend);
				}
				anjianfacebook = false;
			}
			ismove = false;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if(friends!=null && friends.size()<=4)return;
			if (FacebookOperation.isLanding && _friend != null) {
				if (ismove) {
					if (isCorrectFriendMove) {
						isCorrectFriendMove = false; // 纠正移动关闭等移动释放后继续纠正
						correctfriend_move = Math.abs(correctfriend_move);
					}
	
					int tempX = (int) event.getX();
	
					move_X += tempX - oldX;
	
					oldX = (int) event.getX();
	
					if (move_X > _friend_s && _friend.get(0).index == -1) {
						move_X = _friend_s;
					} else if ((move_X < -_friend_s && _friend.size() < 5)
							|| (move_X < -_friend_s && _friend.get(5).index == -1)) {
						move_X = -_friend_s;
					}
					if (move_X >= _friend_s + _friend_sc) {
						move_X = 0;
						addLeftFriend();
					} else if (move_X <= -(_friend_s + _friend_sc)) {
						move_X = 0;
						addRightFriend();
					}
				}
			} else {

			}
		}
	}

	private void addLeftFriend() {
		// for (int i=_friend.size()-1; i<friends.size(); i++) {
		// for (int j=0; j<_friend.size(); j++) {
		// if (j + 1 <_friend.size()) {
		// _friend.set(j, _friend.get(j+1));
		// } else {
		// if (_friend.get(j).index <= 0) {
		// _friend.set(j, new Friend(-1, "", 0, 0));
		// } else {
		// _friend.set(j, friends.get(i));
		// }
		// }
		// }
		// }

		for (int j = _friend.size() - 1; j >= 0; j--) {
			if (j > 0) {
				_friend.set(j, _friend.get(j - 1));
			} else {
				if (_friend.get(j).index <= 1) {
					_friend.set(j, new Friend(-1, "", 0, 0, null));
				} else {
					_friend.set(j, friends.get(_friend.get(j).index - 2));
				}
			}
		}
	}

	private void addRightFriend() {
		// for (int j = _friend.size() - 1; j >= 0; j--) {
		// if (j - 1 >= 0) {
		// _friend.set(j, _friend.get(j - 1));
		// } else {
		// if (_friend.get(j).index >= friends.size()) {
		// _friend.set(j, new Friend(-1, "", 0, 0));
		// } else {
		// _friend.set(j, friends.get(_friend.get(j).index));
		// }
		// }
		// }
		for (int j = 0; j < _friend.size(); j++) {
			if (j + 1 < _friend.size()) {
				_friend.set(j, _friend.get(j + 1));
			} else {
				if (_friend.get(j).index >= friends.size()) {
					_friend.set(j, new Friend(-1, "", 0, 0, null));
				} else {
					_friend.set(j, friends.get(_friend.get(j).index));
				}
			}
		}
	}

	public void release() {
		if(loading != null){
			GameImage.delImageArray(loading);
		}
		loading = null;
		if(share_ui_photo_04 != null && share_ui_photo_04.bitmap!=null){
			GameImage.delImage(share_ui_photo_04.bitmap);
		}
		
 		if(smallcard_card_friend != null && smallcard_card_friend.bitmap!=null){
			GameImage.delImage(smallcard_card_friend.bitmap);
		}
		smallcard_card_friend = null;
		

		FacebookOperation.getFacebook().setFriendIcon(null);
		FacebookOperation.getFacebook().setFriendInt(null);
		// GameImage.delImage(GameStaticImage.s_share_ui_photo_01.bitmap);
		// GameStaticImage.s_share_ui_photo_01 = null;
		// GameImage.delImageArray(rank_number);
		// rank_number = null;
		// for (int i=0; i<arrow.length; i++) {
		// GameImage.delImage(arrow[i].bitmap);
		// arrow[i] = null;
		// }
		// arrow = null;
	}

	@Override
	public void onIcon(FaceBookPlayer playerIcon) {
		// TODO Auto-generated method stub
		if(friends!=null)
			for(int i=0;i<friends.size();++i){
				Friend friend = friends.get(i);
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
}
