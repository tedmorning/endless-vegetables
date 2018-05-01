package com.facebook;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.NetConfig;
import com.SocoNetCallBack;
import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.Achievement;
import com.endlessvegetables2.ui.ChooseLevelModule2;
import com.endlessvegetables2.ui.Friend;
import com.endlessvegetables2.ui.MessageModule;
import com.endlessvegetables2.ui.RankingSuccessModule;
import com.endlessvegetables2.ui.VeggiesData;
import com.facebook.UserRequest.UserLoginRes;
import com.facebook.sdk.FBInterface;
import com.facebook.sdk.FBInterface.UserChangedCallback;
import com.facebook.sdk.FBUser;
import com.facebook.sdk.FBUser.IconCallback;
import com.game.data.FaceBookPlayer;
import com.game.data.FaceBookPlayer.ServerCallback;
import com.game.data.TopListData;
import com.kokatlaruruxi.wy.Main;
import com.net.Http;
import com.protocol.request.UserLoginReq;
import com.protocol.request.UserViewReq;
import com.protocol.response.ack.UserViewAck;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.MainActivity;
import com.test.ResponseHandler;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

/***
 * �� faceBook�Ĳ���
 * */
public class FacebookOperation {

	public final static int GAME_STATE_INIT = 0;
	public final static int GAME_STATE_INIT_LOADING = GAME_STATE_INIT + 1;
	public final static int GAME_STATE_LOGIN = GAME_STATE_INIT_LOADING + 1;
	public final static int GAME_STATE_USER_VIEW = GAME_STATE_LOGIN + 1;
	public final static int GAME_STATE_GAME = GAME_STATE_USER_VIEW + 1;
	public final static int GAME_STATE_FACEBOOK_INFO = GAME_STATE_GAME + 1;
	public final static int GAME_STATE_FACEBOOK_FRIENDS = GAME_STATE_FACEBOOK_INFO + 1;
	public final static int GAME_STATE_FRIEND_ICON = GAME_STATE_FACEBOOK_FRIENDS + 1;
	public final static int GAME_STATE_TOPLIST = GAME_STATE_FRIEND_ICON + 1;
	public final static int GAME_STATE_TOPLIST_PARSING = GAME_STATE_TOPLIST + 1;
	public final static int GAME_STATE_TOPLIST_PARSING_ICON = GAME_STATE_TOPLIST_PARSING + 1;
	public final static int GAME_STATE_PULISH = GAME_STATE_TOPLIST_PARSING_ICON + 1; //����
	public static int gameState = GAME_STATE_INIT;

	/**
	 * faceb ������������Ϸ�����
	 */
	public static HashMap<String, FaceBookPlayer> playerMap = new HashMap<String, FaceBookPlayer>();
	public static FaceBookPlayer player;
	public static int saveState = gameState;
	
	public final static byte invitation = 1; // ����
	public final static byte level_friend = 2; // �ؿ�����ĺ���
	public final static byte topList = 3; // ���а�
	public final static byte publish = 4; // ����
	public static boolean isLanding; // ����Ƿ��½��fb
	
	private static boolean isNetNo; // ��ȡ�������������û����
	private static FacebookOperation facebook;
	private static int topIndex = 0;
	private static int topIndexIcon = 0;
	public Http http = null;
	
	private static final String KEY_LANDING = "landing";// �Ƿ��½����
	private ResponseHandler handler = null;
	private Handler facebookHandler; // ��Ϊ������½����Ϸ�߼�������
	private boolean isFrist; // ���ص�
	private setFriendIcon intIcon; // ���ѵ�ȡ�Ľ��
	private boolean isloadingFriend; // �����Ƿ�������
	private int index_icon = 0;
	private int index_server = 0;
	public static boolean isOpenNet; //��·�Ƿ�uankai
	public FriendIcon icon; //����ͷ��
	public FriendHelpTime helpTime; //����ʱ��
	public static FacebookOperation getFacebook() {
		if (facebook == null) {
			facebook = new FacebookOperation();
			facebook.initHandler();
		}
		return facebook;
	}

	public void setFriendIcon(FriendIcon _icon){
		icon = _icon;
	}
	
	public FriendIcon getFrendIcon(){
		return icon;
	}
	public void setFriendHelpTime(FriendHelpTime _helpTime){
		helpTime = _helpTime;
	}
	
	public FriendHelpTime getFriendHelpTime(){
		return helpTime;
	}
	
	
	/**
	 * ע��
	 **/
	public void cancel(){
		FBInterface.logout();
		if(UserRequest.getUser()!=null)
			UserRequest.getUser().setLoginok(false);
		playerMap.clear();
		if(player!=null)
			player.delete();
		player = null;
		gameState = GAME_STATE_INIT;
		setFriendNet(false);
		setLoadingFriend(false);
		isLanding = false;
		new VeggiesData().saveGame();
        if( UserRequest.getUser()!=null)
        	UserRequest.getUser().setUserLoginRes(null);
        if(MessageModule.message!=null)
        	MessageModule.message.clear();
        if(RankingSuccessModule.toplist!=null){
	        for(int i=0;i<RankingSuccessModule.toplist.size();++i){
	        	RankingSuccessModule.toplist.get(i).delete();
	        	RankingSuccessModule.toplist.remove(i);
	        }
	        RankingSuccessModule.toplist.clear();
        }
        RankingSuccessModule.toplist = null;
//        FarmModule.farmdata.clear();
        index_icon = 0;
		index_server = 0;
		isFrist = false;
		topIndex = 0;
		topIndexIcon = 0;
		
	}
	/**
	 * ����
	 * */
	public boolean getFriendNet() {
		return isNetNo;
	}
	
	/**
	 * ����icon
	 * */
	public void setFriendNet(boolean isNet) {
		isNetNo = isNet;
	}
	
	/**
	 * ���ú��Ѽ���״̬
	 * */
	public void setLoadingFriend(boolean isNet) {
		isloadingFriend = isNet;
	}

	/**
	 * ��ȡ���Ѽ���״̬
	 * */
	public boolean getLoadingFriend() {
		return isloadingFriend;
	}
	
	
	// ������Ϣ����
	public void setStste(int state) {
		Message msg = new Message();
		msg.what = state;
		facebookHandler.sendMessage(msg);
	}
	
	public void initHandler() {
		// ��ȡ����Ƿ��½��
//		isLanding = getLanding(Main.getActivity());
//		new VeggiesData().loadGame();
		isFrist = isLanding;
		facebookHandler = new Handler() {
			public void handleMessage(Message msg) {
				gameState = msg.what;
				switch (gameState) {
				case GAME_STATE_INIT:
					if(handler == null)
						handler = new ResponseHandler();
					http = Http.getLoginHttp(handler, new SocoNetCallBack());
					handler.setHttp(http);
					
					gameState = GAME_STATE_INIT_LOADING;
					break;
				case GAME_STATE_INIT_LOADING:
					break;
				case GAME_STATE_FACEBOOK_FRIENDS: // ����
//					FBInterface.sendAppRequests(Main.getActivity(), "123123",
//							new RequestDialogCallback() {
//								@Override
//								public void onComplete(String id,
//										FacebookException exception) {
//									if (exception == null) {
//										Toast.makeText(Main.getActivity(), id,
//												Toast.LENGTH_SHORT).show();
//									} else {
//										Toast.makeText(Main.getActivity(),
//												exception.toString(),
//												Toast.LENGTH_SHORT).show();
//										//�����������ûؿ�ʼ״̬
//									}
//								}
//							});
					break;
				case GAME_STATE_USER_VIEW: // ��ȡ����
					// ��¼
					if (FBInterface.user==null) {
						gameState = GAME_STATE_INIT_LOADING;
						landingAndInvite(level_friend);
						return;
					}
					if (FBInterface.friendMap.size() == 0) {
//						FBInterface.requestFriends(new UserListCallback() {
//							@Override
//							public void onCompleted(Response response) {
//								getFriendInfo();
//								if(response.getError() == null)
//									isOpenNet = false;
//							}
//						});
					}else{
						// case GAME_STATE_USER_VIEW://���������˷��ͺ��ѵ�������
						Iterator iterator = playerMap.keySet().iterator();
						while (iterator.hasNext()) {
							FaceBookPlayer player = playerMap.get(iterator.next());
							if (player.getid_server() == FaceBookPlayer.L_NULL) { // ��ʾû����id
								// �����ɹ���������
								UserViewReq.request(http, 0, player.getId_facebook(), false);
							}
						}
					}
					break;
				case GAME_STATE_FRIEND_ICON: // ͷ��
					Iterator iterator = FBInterface.friendMap.keySet().iterator();
					while (iterator.hasNext()) {
						FBUser cu = FBInterface.friendMap.get(iterator.next());
						FaceBookPlayer fbplayer = playerMap.get(cu.getId()); //ֻ���ڷ�������¼�������
						 if(fbplayer!=null){
								cu.getIcon(new IconCallback() {

									@Override
									public void onComplete(FBUser user, Bitmap icon, Exception exception) {
										FaceBookPlayer player = playerMap.get(user.getId());
										if (player != null) {
											player.setIcon(icon);
											
											if(FacebookOperation.getFacebook().getFrendIcon()!=null)
												FacebookOperation.getFacebook().getFrendIcon().onIcon(player);
										}
									}
								});
						 }
					}
					break;
				case GAME_STATE_TOPLIST:
					if (FBInterface.user==null) {
						gameState = GAME_STATE_INIT_LOADING;
						landingAndInvite(topList);
						return;
					}
					if (UserRequest.getUser().getLoginok())
						UserRequest.getUser().ReqTopList(); // ���а�����
					break;
				case GAME_STATE_PULISH:

//					if (FBInterface.user==null) {
//						gameState = GAME_STATE_INIT_LOADING;
//						landingAndInvite(publish);
//						return;
//					}
//					Bundle params = new Bundle();
//					params.putString("link", "https://developers.facebook.com/ios");
//					FBInterface.publishOnFeed(Main.getActivity(), params, new RequestDialogCallback() {
//						@Override
//						public void onComplete(String id, FacebookException exception) {
//							if(exception == null){
//								Toast.makeText(Main.getActivity(), id, Toast.LENGTH_SHORT).show();
//							}else{
//								Toast.makeText(Main.getActivity(), exception.toString(), Toast.LENGTH_SHORT).show();
//							}	
//						}
//					});
					break;
				case GAME_STATE_TOPLIST_PARSING:
					topIndex = 0;
					 
					for (final TopListData data : RankingSuccessModule.toplist) {
						data.getUser(new UserChangedCallback() {

							@Override
							public void onUserInfoFetched(FBUser user) {
								// TODO Auto-generated method stub
								if (user != null) {
									data.setFaceBookUser(user);
								}
								topIndex++;
								if (topIndex >= RankingSuccessModule.toplist.size()) {
									topIndex = 0;
									setStste(GAME_STATE_TOPLIST_PARSING_ICON);
								}
							}
						});
					}
					;
					break;
				case GAME_STATE_TOPLIST_PARSING_ICON:
					for (final TopListData data : RankingSuccessModule.toplist) {
						if (data.getFaceBookUser() != null) {
							data.getFaceBookUser().getIcon(new IconCallback() {

								@Override
								public void onComplete(FBUser user, Bitmap icon, Exception exception) {
									topIndexIcon++;
									if (RankingSuccessModule.toplist!=null && topIndexIcon >= RankingSuccessModule.toplist.size()) {
										topIndexIcon = 0;
										if (UserRequest.getUser().getTopListInt() != null)
											UserRequest.getUser().getTopListInt().onTopList();
									}
								}
							});
						} else {
							topIndexIcon++;
							if (topIndexIcon >= RankingSuccessModule.toplist.size()) {
								topIndexIcon = 0;
								if (UserRequest.getUser().getTopListInt() != null)
									UserRequest.getUser().getTopListInt().onTopList();
							}
						}
					}
					//�������ܷ�������         									
					if(UserRequest.getUser().getLoginok() && (FacebookOperation.player.getid_server() == FaceBookPlayer.L_NULL || FacebookOperation.player.getOrderIndex() == FaceBookPlayer.I_NULL )){ //��ʾû����id
						UserViewReq.request(FacebookOperation.getFacebook().http, 0, FacebookOperation.player.getId_facebook(), false);
					}
					if(FacebookOperation.player!=null && FacebookOperation.player.getIcon()==null){
						FBInterface.user.getIcon(new IconCallback() {
							@Override
							public void onComplete(FBUser user, Bitmap icon, Exception exception) {
								if(FacebookOperation.player!=null)
									FacebookOperation.player.setIcon(icon);
								if(FacebookOperation.getFacebook().getFrendIcon()!=null)
									FacebookOperation.getFacebook().getFrendIcon().onIcon(player);
							}
						});
					}
					break;
				}
			}
		};
	}

	// isBKFirst ע����Ҫ��������Ϊfalse
	public void landingAndInvite(final byte _state) {
		switch (gameState) {
		case GAME_STATE_INIT_LOADING:
//			FBInterface.callback = new Session.StatusCallback() {
//				@Override
//				public void call(Session session, SessionState state, Exception exception) {
//					Log.i("FBTEST", "called gamemain");
//					Log.i("state.toString() ", state.toString());
//					GameConfig.isFacebook = true;
//					if(exception!=null)
//						GameConfig.isFacebook = false;
//					if (session != null && session.isOpened()) {
//						isLanding = true;
//						new VeggiesData().saveGame();
//						FBInterface.fetchUserInfo();
//						if (isLanding && isFrist || (_state == level_friend || _state == topList || _state == publish)) {// ��½�ɹ��´ν������Ǻ�������
//							switch (_state) {
//							case invitation:
//								setStste(GAME_STATE_FACEBOOK_FRIENDS);
//								break;
//							case level_friend:
//								setStste(GAME_STATE_USER_VIEW);
//								break;
//							case topList:
//								setStste(GAME_STATE_TOPLIST);
//								break;
//							case publish:
//								setStste(GAME_STATE_PULISH);
//								break;
//							}
//						}
//						isFrist = true;
//					} else {
//						setStste(GAME_STATE_INIT);
//						// ����ʧ�ܸ�����ʾ�û�
//						if (exception != null && exception instanceof FacebookAuthorizationException) {
//							String temp = ((FacebookAuthorizationException) exception) .getMessage();
//							temp = temp + LangUtil.getLangString(LangDefineClient.CHECK_NET);
//							ChooseLevelModule2.sendMessage(temp);
//							isOpenNet = true;
//							cancel(); //����ʧ������Ϊδ��¼
//						}
//					}
//				}
//			};
//
//			FBInterface.userInfoChangedCallback = new FBInterface.UserChangedCallback() {
//				@Override
//				public void onUserInfoFetched(FBUser user) {
//					GameConfig.isFacebook = false;
//					MySave();
//						
//					switch (_state) {
//					case invitation:
////						if (!isFrist)
//							setStste(GAME_STATE_FACEBOOK_FRIENDS);
//						break;
//					case level_friend:
////						if (!isFrist)
//							setStste(GAME_STATE_USER_VIEW);
//						break;
//					case topList:
////						if (!isFrist)
//							setStste(GAME_STATE_TOPLIST);
//						break;
//					case publish:
//						setStste(GAME_STATE_PULISH);
//						break;
//					}
//					if (user != null) {
//						VeggiesData.addAchievementNum(Achievement.LOGIN_FACEBOOK, 1);
//						// ��¼������
//						isOpenNet = false;
//						loadingServer(user);
//					} else {
//						String temp = LangUtil.getLangString(LangDefineClient.LOADING_FACE);
//						ChooseLevelModule2.sendMessage(temp);
//						isOpenNet = true;
//						setFriendNet(false);
//						setLoadingFriend(false);
//						cancel(); //����ʧ������Ϊδ��¼
//					}
//				}
//			};
//			FBInterface.login(Main.getActivity());
			break;
		case GAME_STATE_FACEBOOK_FRIENDS: // ��ʾ�Ѿ���½��ȥ��������
			setStste(GAME_STATE_FACEBOOK_FRIENDS);
			break;
		case GAME_STATE_USER_VIEW:
			setStste(GAME_STATE_USER_VIEW);
			break;
		case GAME_STATE_TOPLIST:
				setStste(GAME_STATE_TOPLIST);
			break;
		}
	}

	/**�Լ��ͷ������Ľ���*/
	private void MySave(){
		if (player == null && FBInterface.user!=null)
			player = new FaceBookPlayer(FBInterface.user.getId(),FBInterface.user.getName());
		if(player!=null && player.getOrderIndex() == FaceBookPlayer.I_NULL){
		
			player.setServer(new ServerCallback() {
				
				@Override
				public void onComplete(String fb_id, UserViewAck userViewAck, Exception exception) {
					if(userViewAck.getUid()!=0){
						FacebookOperation.player.setid_server(userViewAck.getUid());
						FacebookOperation.player.setOrderIndex(userViewAck.getOrderIndex());
						FacebookOperation.player.setOrderScore(userViewAck.getOrderScore());
						FacebookOperation.player.setMaxLevel(userViewAck.getGameLevel());
						if(FacebookOperation.player!=null
								&& FacebookOperation.player.getid_server()!=FaceBookPlayer.L_NULL){
//							FarmData data = FarmModule.farmdata.get(FacebookOperation.getFacebook().player.getid_server());
//							if(data==null){
//								UserRequest.getUser().reqVegetableList(FacebookOperation.getFacebook().player.getid_server());
//							} 
						}
					}
				}
			});
			if(FacebookOperation.player.getIcon()==null){
				FBInterface.user.getIcon(new IconCallback() {
					@Override
					public void onComplete(FBUser user, Bitmap icon, Exception exception) {
						if(FacebookOperation.player!=null)
							FacebookOperation.player.setIcon(icon);
					}
				});
			}
		}
	}
	/**
	 * ��ȡ����
	 * */
	private void getFriendInfo() {

		index_icon = 0;
		index_server = 0;
		Iterator iterator = FBInterface.friendMap.keySet().iterator();
		while (iterator.hasNext()) {
			FBUser cu = FBInterface.friendMap.get(iterator.next());

			FaceBookPlayer player = new FaceBookPlayer(cu.getId(), cu.getName());
			playerMap.put(cu.getId(), player);

			player.setServer(new ServerCallback() {

				@Override
				public void onComplete(String fb_id, UserViewAck userViewAck,
						Exception exception) {
					// TODO Auto-generated method stub
					// FBUser fbuser = FBInterface.friendMap.get(fb_id);
					FaceBookPlayer player = playerMap.get(fb_id);
					if (player != null && userViewAck.getUid() != 0) {
						player.setid_server(userViewAck.getUid());
						player.setOrderIndex(userViewAck.getOrderIndex());
						player.setOrderScore(userViewAck.getOrderScore());
						player.setMaxLevel(userViewAck.getGameLevel());
					} else if (userViewAck.getUid() == 0) { // ��������ʾľ�и��û�
						if (player != null)
							playerMap.remove(fb_id);
						if (index_server >= playerMap.size()) {
							index_server = 0;
							saveState = gameState;
							setStste(GAME_STATE_FRIEND_ICON);
							setLoadingFriend(true);
							if (intIcon != null)
								intIcon.onSetFriend();
						}
						return;
					}
					index_server++;
					if (index_server >= playerMap.size()) {
						index_server = 0;
						saveState = gameState;
						setStste(GAME_STATE_FRIEND_ICON);
						setLoadingFriend(true);
						if (intIcon != null)
							intIcon.onSetFriend();
					}
				}
			});
			// �������ܷ�������
			if (UserRequest.getUser().getLoginok()) { // ��ʾû����id
				UserViewReq.request(http, 0, cu.getId(), false);
			}
		}
	}

	/**
	 * ��¼������
	 * */
	public void loadingServer(FBUser user) {
		UserRequest.getUser().setUserLoginRes(new UserLoginRes() {

			@Override
			public void onlogicRes(long server_id) {
				// TODO Auto-generated method stub
				if (gameState == GAME_STATE_TOPLIST) {
					UserRequest.getUser().ReqTopList(); // ���а�����
				}
				
				// case GAME_STATE_USER_VIEW://���������˷��ͺ��ѵ�������
				Iterator iterator = playerMap.keySet().iterator();
				while (iterator.hasNext()) {
					FaceBookPlayer player = playerMap.get(iterator.next());
					if (player.getid_server() == FaceBookPlayer.L_NULL) { // ��ʾû����id
						// �����ɹ���������
						UserViewReq.request(http, 0, player.getId_facebook(), false);
					}
				}
				
				if(player != null && player.getOrderIndex() == FaceBookPlayer.I_NULL){
						// �����ɹ���������
						UserViewReq.request(http, 0, player.getId_facebook(), false);
					 
				}
			}
		});
		if (!UserRequest.getUser().getLoginok()) {
			UserLoginReq.request(http, user.getId(), "111111", user.getName(),
					NetConfig.lang, NetConfig.getVersion(), false);
		}
	}

	public interface setFriendIcon {
		public void onSetFriend();
	}

	public void setFriendInt(setFriendIcon icon) {
		this.intIcon = icon;
	}

	public setFriendIcon getFriendInt() {
		return this.intIcon;
	}

//	private boolean getLanding(Context context) {
//		Rms rms = new Rms(context, context.getPackageName());
//		return rms.readBoolean(KEY_LANDING, false);
//	}
//
//	private void saveLanding(Context context) {
//		Rms rms = new Rms(context, context.getPackageName());
//		rms.saveBoolean(KEY_LANDING, isLanding);
//
//	}

	public static class comparatorByScore implements Comparator<Object> {

		// @Override
		public int compare(Object object1, Object object2) {
			FaceBookPlayer.UserLeveInfo file1 = (FaceBookPlayer.UserLeveInfo) object1;
			FaceBookPlayer.UserLeveInfo file2 = (FaceBookPlayer.UserLeveInfo) object2;
			long diff = file1.score - file2.score;
			if (diff < 0)
				return 1;
			else if (diff == 0)
				return 0;
			else
				return -1;
		}
	}

	public static class comparatorFriendByScore implements Comparator<Object> {

		// @Override
		public int compare(Object object1, Object object2) {
			Friend file1 = (Friend) object1;
			Friend file2 = (Friend) object2;
			long diff = file1.score - file2.score;
			if (diff < 0)
				return 1;
			else if (diff == 0)
				return 0;
			else
				return -1;
		}
	}

	public static class comparatorBystar implements Comparator<Object> {

		// @Override
		public int compare(Object object1, Object object2) {
			FaceBookPlayer.UserLeveInfo file1 = (FaceBookPlayer.UserLeveInfo) object1;
			FaceBookPlayer.UserLeveInfo file2 = (FaceBookPlayer.UserLeveInfo) object2;
			long diff = file1.star - file2.star;
			if (diff < 0)
				return 1;
			else if (diff == 0)
				return 0;
			else
				return -1;
		}
	}

	public static class comparatorTopList implements Comparator<Object> {
		// @Override
		public int compare(Object object1, Object object2) {
			TopListData file1 = (TopListData) object1;
			TopListData file2 = (TopListData) object2;
			long diff = file1.orderIndex - file2.orderIndex;
			if (diff < 0)
				return 1;
			else if (diff == 0)
				return 0;
			else
				return -1;
		}
	}
}// end class
