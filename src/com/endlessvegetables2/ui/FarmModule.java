//package com.endlessvegetables2.ui;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Typeface;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//
//import com.endlessvegetables2.android.ExternalMethods;
//import com.endlessvegetables2.android.GameTeaching;
//import com.endlessvegetables2.android.Main;
//import com.endlessvegetables2.android.R;
//import com.endlessvegetables2.android.Sprite;
//import com.facebook.FacebookOperation;
//import com.facebook.FriendIcon;
//import com.facebook.FacebookOperation.setFriendIcon;
//import com.facebook.UserRequest;
//import com.facebook.UserRequest.FarmUpdate;
//import com.facebook.sdk.FBInterface;
//import com.farm.FarmPlant;
//import com.game.data.FaceBookPlayer;
//import com.game.data.FarmData;
//import com.socoGameEngine.GameConfig;
//import com.socoGameEngine.GameImage;
//import com.socoGameEngine.GameManager;
//import com.socoGameEngine.MainActivity;
//import com.socoGameEngine.Module;
//import com.util.lang.LangDefineClient;
//import com.util.lang.LangUtil;
//
///**
// * 农场页面
// * 
// *   ---xl
// * */
//
//public class FarmModule extends Module implements FriendIcon{
//	
//	public static HashMap<Long, FarmData> farmdata = new HashMap<Long, FarmData>();
//	public static long uid;//农场当前uid用户
//
//	public Sprite[] loading;
//	
//	private FarmPlant plant[];
//	private Typeface fontFace;
//	private Paint paint;
//	private Sprite glim;
//	private Sprite farm_ui_ribbon_02;
//	private Sprite word_title_friend;
//	private Sprite farm_01; //农场地表
//	private Sprite farm_icon_facebook_03[];
//	private Sprite farm_farm_02;
//	 
//    
//	ChooseLevelModule2类的1006行代码需要打开 跑马灯的功能
//  FacebookOperation  FarmModule.farmdata.clear();
//  FacebookOperation  FarmData data = FarmModule.farmdata.get(FacebookOperation.getFacebook().player.getid_server());
//	ResponseHandler    P15_VegetableOperate P16_VegetableList 下面的打开
//  UserRequest       public void onUpdateFarm(FarmData data);
//
//
//
//
//
//
//
//	private boolean anjianclose;
//	private float ani_up,ani_down;
//	private boolean isAni;
//	private boolean isCancel;
//	private int ani_frame = 4;
//	private float ani_y1,ani_y2;
//	private int isGo = 0;
//	private boolean anjianfacebook;
//	
//	private int w_facebook_02, h_facebook_02;
//
//	private int icoke=0;
//	
//	private int tempi = 0;
//	
//	//坐标 
//	int noloadingPlant[][] = {
//			{(int)(119 * GameConfig.f_zoomx), (int)(244 * GameConfig.f_zoomx), (int)(367 * GameConfig.f_zoomx)},
//			{(int)(484 * GameConfig.f_zoomy  ), (int)(466 * GameConfig.f_zoomy ), (int)(487 * GameConfig.f_zoomy )},
////			{(int)(64 * GameConfig.f_zoomx), (int)(64 * GameConfig.f_zoomx), (int)(64 * GameConfig.f_zoomx)},
////			{(int)(38 * GameConfig.f_zoomy), (int)(39 * GameConfig.f_zoomy), (int)(39 * GameConfig.f_zoomy)}
////	
//	};
//	
//	int timeTitle[][] = {
//			{(int)(57 * GameConfig.f_zoomx), (int)(219 * GameConfig.f_zoomx), (int)(372 * GameConfig.f_zoomx)},
//			{(int)((268+20) * GameConfig.f_zoomy), (int)((230+20) * GameConfig.f_zoomy), (int)((268+20) * GameConfig.f_zoomy)}
//	};
//	int buttonxy[][] = {
//			{(int)(102 * GameConfig.f_zoomx), (int)(231 * GameConfig.f_zoomx), (int)(354 * GameConfig.f_zoomx)},
//			{(int)((491) * GameConfig.f_zoomy), (int)((491) * GameConfig.f_zoomy), (int)((491) * GameConfig.f_zoomy)}
//	};
//	
//	//种植以后的
//	float plantOld[][] = {
//			{82* GameConfig.f_zoomx, (211* GameConfig.f_zoomx), 345* GameConfig.f_zoomx  },
//			{341 * GameConfig.f_zoomy,304 * GameConfig.f_zoomy, 339 * GameConfig.f_zoomy }
//	};
//	
//	//好友
//	private float _friend_x,_friend_y,_friend_w,_friend_h,_friend_s,_friend_sc;
//	private float _x,_y;
//	float move_X;
//	public int oldX;
//	public List<AskFriend> _friends;
//	private List<AskFriend> friends;
//	private Sprite share_ui_photo_04;
//	private Sprite share_ui_photo_05;
//	boolean isCorrectFriendMove;
//	float correctFriend_move = 5 * GameConfig.f_zoomx;
//	private boolean ismove;
//	private boolean anjianfriend;
//	
//	@Override
//	public boolean initialize() {
//		// TODO Auto-generated method stub
////		indedx = -1;
//		if(FacebookOperation.isLanding && !FacebookOperation.getFacebook().getLoadingFriend())
//			FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_USER_VIEW);
//		FacebookOperation.getFacebook().setFriendIcon(this);
//		
//		if(loading==null)
//			loading = GameImage.getAutoSizecutSprite(
//				GameStaticImage.share_loading_03, 9, 1, GameImage.Sort_line);
//		tempi = 0;
//		if (share_ui_photo_04 == null)
//			share_ui_photo_04 = new Sprite(
//					GameImage.getImage(GameStaticImage.share_ui_photo_04));
//
//		if (share_ui_photo_05 == null)
//			share_ui_photo_05 = new Sprite(
//					GameImage.getImage(GameStaticImage.share_ui_photo_05));
//		
//		if (FacebookOperation.isLanding && FacebookOperation.getFacebook().getLoadingFriend()) {
//			initFriend();
//		} else {
//			FacebookOperation.getFacebook().setFriendInt(new setFriendIcon() {
//			
//				@Override
//				public void onSetFriend() {
//					// TODO Auto-generated method stub
//					initFriend();
//					if(plant==null)
//						isLogIn();
//				}
//			});
//		}
//		 
//		w_facebook_02 = GameStaticImage.s_interface_icon_facebook_02.bitmap.getWidth();
//		h_facebook_02 = GameStaticImage.s_interface_icon_facebook_02.bitmap.getHeight();
//		
//		glim = new Sprite();
//		
//		farm_ui_ribbon_02 = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_ribbon_02));
//		word_title_friend = new Sprite(GameImage.getImage(GameStaticImage.word_title_friend));
//		farm_01 = new Sprite(GameImage.getImage(GameStaticImage.farm_farm_01));
//		if(!FacebookOperation.isLanding)
//			farm_icon_facebook_03 = GameImage.getAutoSizecutSprite(GameStaticImage.farm_icon_facebook_03, 3, 1, GameImage.Sort_line);
//		
//		if(!FacebookOperation.isLanding){
//			if(farm_farm_02==null)
//				farm_farm_02 = new Sprite(GameImage.getImage(GameStaticImage.farm_farm_02));
//		}else{
//			if(plant==null)
//				isLogIn();
//		}
//		
//		ani_y1 = 150 * GameConfig.f_zoomy + 374 * GameConfig.f_zoomy + GameStaticImage.s_interface_star_12[0].bitmap.getHeight(); 
//		ani_y2 = 260 * GameConfig.f_zoomy;
//		ani_up = -ani_y1;
//		ani_down = ani_y2;
//		isAni = true;
//		
//
//		paint = new Paint();
//		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
//		paint =new Paint();
//		paint.setTextSize(14*GameConfig.f_zoom);
//		paint.setColor(Color.WHITE);
//		paint.setTypeface(fontFace);
//		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
//	
//		tempi = 0;
//		move_X = 0;
//		isCorrectFriendMove = false;
//		anjianfriend = false;
//		
//		//未登录的新手引导
//		if(!FacebookOperation.isLanding && VeggiesData.getGameGuanka()[9] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL21]){
//			if(GameManager.getGT()==null)
//				GameManager.setGT(new GameTeaching());
//			int temp_y = (int)(GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoomy);
//			int temp_h = (int)(154 * GameConfig.f_zoomy);
//			int temp_x = (int)(9 * GameConfig.f_zoomx);
//			temp_x = (int) (temp_x+(30 * GameConfig.f_zoomx));
//			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL21, temp_x- w_facebook_02 / 2+(int)(GameStaticImage.s_interface_icon_facebook_02.bitmap.getWidth()), temp_y+(temp_h-h_facebook_02>>1)- h_facebook_02/ 2+(int)(GameStaticImage.s_interface_icon_facebook_02.bitmap.getHeight()), LangUtil.getLangString(LangDefineClient.GUIDE_21), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
//		}else if(plant !=null && FacebookOperation.isLanding  && VeggiesData.getGameGuanka()[9] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL22] ){
//			if(plant[0].id == 1){ //1 代表体力之行
//				if(plant[0].getStateSurface()!=FarmPlant.SURFACE_HARVEST){ //状态不是可收取
//					plant[0].setStateSurface(FarmPlant.SURFACE_HARVEST);
//				}
//			}
//			if(GameManager.getGT()==null)
//				GameManager.setGT(new GameTeaching());
//			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL22, (int)((171) * GameConfig.f_zoomx), (int)(513 * GameConfig.f_zoomy) , LangUtil.getLangString(LangDefineClient.GUIDE_22), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
//		}
//		 
//		return false;
//	}
//	
//	
//	//判断用户是否登录过公司服务器交换过数据
//	private void isLogIn(){
//		if(FacebookOperation.getFacebook().player!=null
//				&& FacebookOperation.getFacebook().player.getid_server()!=FaceBookPlayer.L_NULL){
//			FarmData data = farmdata.get(FacebookOperation.getFacebook().player.getid_server());
//			if(data!=null){
//				initFarmPlant(data);
//			}else{
//				UserRequest.getUser().reqVegetableList(FacebookOperation.getFacebook().player.getid_server());
//			}
//		}
//		UserRequest.getUser().setFarmInt(new FarmUpdate() {
//			
//			@Override
//			public void onUpdateFarm(FarmData data) {
//				// TODO Auto-generated method stub
//				initFarmPlant(data);
//			}
//		});
//	}
//	
//	
//	//初始化农村数据
//	public void initFarmPlant(FarmData data){
//		uid = data.uid;
//		if(plant==null)
//			plant = new FarmPlant[FarmData._size];
//		if(!data.isUpdateA){
//			
//			for(int i=0;i<FarmData._size;++i){
//				if(plant[i]==null)
//					plant[i] = new FarmPlant(i, (byte)data.itemid[i], (byte)data.status[i], data.time[i], data.msg[i]);
//				plant[i].setStateGrowth((byte)data.status[i]);
//				plant[i].setStateSurface((byte)data.status[i]);
//				plant[i].settype((byte)data.itemid[i]);
//				plant[i].setXY(noloadingPlant[0][i], noloadingPlant[1][i]);
//				plant[i].setTimeTitle(timeTitle[0][i], timeTitle[1][i]);
//				plant[i].setButton(buttonxy[0][i], buttonxy[1][i]);
//				plant[i].setMsg(data.msg[i]);
//				plant[i].setTime(data.time[i]);
//				plant[i].setStateGrowth((byte)data.status[i]);
//				plant[i].setStateSurface((byte)data.status[i]);
//				plant[i].setFarmModule(this);
//			}
//			
//		}else{
//			data.isUpdateA = false;
//			int index = data.serialNumber;
//			 
////			plant[index] = new FarmPlant(i, (byte)data.itemid[i], (byte)data.status[i]);
//			plant[index].setStateGrowth((byte)data.status[index]);
//			plant[index].setStateSurface((byte)data.status[index]);
//			plant[index].setTime(data.time[index]);
//			plant[index].setXY(noloadingPlant[0][index], noloadingPlant[1][index]);
//			plant[index].setTimeTitle(timeTitle[0][index], timeTitle[1][index]);
//			plant[index].setButton(buttonxy[0][index], buttonxy[1][index]);
//			plant[index].setMsg(data.msg[index]);
//			plant[index].settype((byte)data.itemid[index]);
//			
//			
//		}
//		if(plant !=null &&VeggiesData.getGameGuanka()[9] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL22] ){
//			if(plant[0].id == 0){ //1 代表体力之行
//				if(plant[0].getStateSurface()!=FarmPlant.SURFACE_HARVEST){ //状态不是可收取
//					plant[0].setStateSurface(FarmPlant.SURFACE_HARVEST);
//				}
//					plant[0].isGT = true;
//			}
//			if(GameManager.getGT()==null)
//				GameManager.setGT(new GameTeaching());
//			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL22, (int)((171) * GameConfig.f_zoomx), (int)(513 * GameConfig.f_zoomy) , LangUtil.getLangString(LangDefineClient.GUIDE_22), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
//		}
//	}
//	
//	private void initFriend(){
//		
//		int nexde = 0;
//		friends = new ArrayList<AskFriend>(FacebookOperation.playerMap.size());
//		_friends = new ArrayList<AskFriend>(FacebookOperation.playerMap.size());
//		
//		_friends.add(new AskFriend(-1,-1, "", null));
//		final int width = share_ui_photo_04.bitmap.getWidth();
//		final int height = share_ui_photo_04.bitmap.getHeight();
//		// 图片缩放
//		Bitmap temp = FBInterface.allIconMap.get(FacebookOperation.player.getId_facebook());//cu.getIcon();
//		if(temp!=null)
//			temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx,
//					height - 6 * GameConfig.f_zoomy);
//		//把自己添加到第一个
//		friends.add(new AskFriend(FacebookOperation.player.getid_server(), nexde + 1, FacebookOperation.player.getName(), temp));
//		_friends.add(friends.get(nexde));
//		nexde++;
//		
//		Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
//		while (iterator.hasNext()) {
//			FaceBookPlayer cu = FacebookOperation.playerMap.get(iterator.next());
//			
//			// 图片缩放
//			temp = FBInterface.allIconMap.get(cu.getId_facebook());//cu.getIcon();
//			if(temp!=null)
//				temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx,
//						height - 6 * GameConfig.f_zoomy);
//			friends.add(new AskFriend(cu.getid_server(), nexde + 1, cu.getName(), temp));
//			if(nexde<=5){
//				_friends.add(friends.get(nexde));
//			}
//			nexde++;
//		}
//		_friend_w = (493 - 28) * GameConfig.f_zoomx - GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth();
//		_friend_h = 100 * GameConfig.f_zoomy;
//		_friend_sc = 90 * GameConfig.f_zoomx;
//		_friend_s = (_friend_w - _friend_sc * 4) / 4;
//		_friend_x = 28 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_arrows_01_02[0].bitmap.getWidth() + _friend_s / 2;
//		_friend_y = GameConfig.GameScreen_Height - _friend_h - 32 * GameConfig.f_zoomy;
//		
//		if(GameManager.getGT()==null)
//			GameManager.setGT(new GameTeaching());
//		if(GameTeaching.teachingArrary[GameTeaching.TEACH_VOL23] && GameManager.getGT().getTeachId()==-1){
//			if(VeggiesData.getGameGuanka()[9] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL24] ){
//				GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL24, (int)((111) * GameConfig.f_zoomx), (int)(751 * GameConfig.f_zoomy) , LangUtil.getLangString(LangDefineClient.GUIDE_24), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
//			}
//		}
//		
//	}
//	@Override
//	public void paint(Canvas canvas) {
//		// TODO Auto-generated method stub
//		
//		glim.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
//		
//		//农场植物状态
//		paintPlantState(canvas, ani_up);
//		
//		//绘制好友
//		paintFriendScore(canvas, ani_down);
//	}
//
//	//植物状态
//	private void paintPlantState(Canvas canvas, float y){
//		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(131 * GameConfig.f_zoomy + y), (int)(476 * GameConfig.f_zoomx), (int)(428 * GameConfig.f_zoomy), -1);
//		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(198 * GameConfig.f_zoomy + y), (int)(442 * GameConfig.f_zoomx), (int)(345 * GameConfig.f_zoomy), -1);
//		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(198 * GameConfig.f_zoomy + y), (int)(442 * GameConfig.f_zoomx), (int)(345 * GameConfig.f_zoomy), -1);
//		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 125 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f) + y, anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
//		farm_ui_ribbon_02.drawBitmap(canvas, farm_ui_ribbon_02.bitmap, (int)(53 * GameConfig.f_zoomx), (int)(110 * GameConfig.f_zoomy + y), null);
//		//地表
//		farm_01.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(466 * GameConfig.f_zoomy + y ), (int)(442 * GameConfig.f_zoomx), (int)(75 * GameConfig.f_zoomy), -1);
//		
//		//未登录
//		if (!FacebookOperation.isLanding)
//			noLoadingFacebook(canvas, y);
//		else
//			farmPaint(canvas, y);
//		
//	}
//	
//	//未登录的农场状态
//	//未登录facebook农场的状态
//	private void noLoadingFacebook(Canvas canvas, float y){
//		tempi = 0;
//		if (GameConfig.i_coke % 8 < 8) {
//			tempi = GameConfig.i_coke % 8 / 4 % 4; 
//			if (tempi == 3) tempi = 1;
//			if(farm_icon_facebook_03!=null && farm_icon_facebook_03[tempi]!=null)
//				farm_icon_facebook_03[tempi].drawBitmap(canvas, 
//						farm_icon_facebook_03[tempi].bitmap, 
//						116* GameConfig.f_zoomx, 
//						342 * GameConfig.f_zoomy+y, 
//						null);
//			else
//				farm_icon_facebook_03 = GameImage.getAutoSizecutSprite(GameStaticImage.farm_icon_facebook_03, 3, 1, GameImage.Sort_line);
//			
//		}
//		
//	
//		
////		for(int i=0;i<plant.length;++i){
////			plant[i].paint(canvas, (int)(y));
////		}
//		if(farm_farm_02==null)
//			farm_farm_02 = new Sprite(GameImage.getImage(GameStaticImage.farm_farm_02));
//		for(int i=0;i<3;++i){
//			farm_farm_02.drawBitmap(canvas, farm_farm_02.bitmap, noloadingPlant[0][i], noloadingPlant[1][i]+y, null);
//		}
//		
//		String temp =LangUtil.getLangString(LangDefineClient.FARM_POINT);
//		canvas.drawText(temp, 186* GameConfig.f_zoomx, 342 * GameConfig.f_zoomy + y,
//				paint);
//	}
//	
//	//登录facebook后农场状态
//	private void farmPaint(Canvas canvas, float y){
//		
//		if(plant!=null){
//			for(int i=0;i<plant.length;++i){
//                if(plant[i]!=null){
//					plant[i].paint(canvas, (int)plantOld[0][i], (int)(plantOld[1][i]+13 * GameConfig.f_zoomy), (int)(y));
//					plant[i].drawTimeTitle(canvas, (int)y);
//				}
//			}
//			for(int i=0;i<plant.length;++i){
//				if(plant[i]!=null){
//					plant[i].drawButton(canvas, paint, (int)y);
//				}
//			}
//		}else{
//			
//			 if(!FacebookOperation.isOpenNet && !FacebookOperation.getFacebook().getFriendNet()){
//					int h = (int)(428 * GameConfig.f_zoomy);
//					int yy = (int) (131 * GameConfig.f_zoomy);
//					int tempx = ((int) ((GameConfig.GameScreen_Width - loading[0].bitmap.getWidth()) / 2));
//					int tempy = yy + ((int) ((h - loading[0].bitmap.getHeight()) / 2));
//
//					loading[tempi].drawBitmap(canvas, loading[tempi].bitmap, tempx, tempy+y , null);
//
//				} 
//		}
//		
//		if(FacebookOperation.isOpenNet && _friends==null){ // 没网络
//			 int h = (int)(428 * GameConfig.f_zoomy);
//			 int yy = (int) (131 * GameConfig.f_zoomy);
//			 String temp = LangUtil.getLangString(LangDefineClient.CHECK_NET);
//			 int tempx = ((int) ((GameConfig.GameScreen_Width - paint.measureText(temp)) / 2));
//			 int tempy = yy + ((int) ((h - paint.getTextSize()) / 2));
//			canvas.drawText(temp, tempx, tempy+y , paint);
//
//		}
//	
//	}
//	
//	//显示好友
//	private void paintFriendScore(Canvas canvas , float y) {
//		//绘制底部好友排行榜
//		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, 0 - GameStaticImage.s_share_ui_back_01.bitmap.getWidth()/3, (int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy + y ), GameConfig.GameScreen_Width + GameStaticImage.s_share_ui_back_01.bitmap.getWidth()*2/3 , (int)(240 * GameConfig.f_zoomy), -1);
//		float tempy = GameConfig.GameScreen_Height - 240 * GameConfig.f_zoomy+y;
//		
//		int temp_x = (int)(9 * GameConfig.f_zoomx);
//		int temp_y = (int)(GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoomy +y);
//		int temp_h = (int)(154 * GameConfig.f_zoomy);
//		
//		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, temp_x, temp_y, (int)(517 * GameConfig.f_zoomx), temp_h, -1);
//		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(9 * GameConfig.f_zoomx), (int)(GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoomy +y), (int)(517 * GameConfig.f_zoomx), (int)(154 * GameConfig.f_zoomy), -1);
//		
//		float temp_x1 = ((float) ((GameConfig.GameScreen_Width - word_title_friend.bitmap.getWidth()) / 2));
//		word_title_friend.drawBitmap(canvas, word_title_friend.bitmap, temp_x1, (int)(GameConfig.GameScreen_Height - (GameConfig.ORGScreen_Height - 648 )* GameConfig.f_zoomy +y), null);
//		
//		//未登录
//		if (!FacebookOperation.isLanding) {
//			// fackebook
//			temp_x = (int) (temp_x+(30 * GameConfig.f_zoomx));
//			GameStaticImage.s_interface_icon_facebook_02 .drawBitmap(canvas, temp_x- w_facebook_02 / 2* (anjianfacebook ? 0.2f : 0f),temp_y+(temp_h-h_facebook_02>>1)- h_facebook_02/ 2* (anjianfacebook ? 0.2f : 0f) , 
//					anjianfacebook ? 1.2f: 1f,anjianfacebook ? 1.2f : 1f, 255, 0, 0, 0);
//			paint.setTextSize(20*GameConfig.f_zoom);
//			paint.setColor(0xffBD6D18);
//			String no_loading =  LangUtil.getLangString(LangDefineClient.FARM_LOGIN);
//			temp_x = temp_x+w_facebook_02;
//			canvas.drawText(no_loading, temp_x + (30)* GameConfig.f_zoomx, temp_y+((temp_h-paint.getTextSize())/2)+ (10) * GameConfig.f_zoomy, paint);						
//			
//			
//		}else{
//			//绘制好友
//			canvas.save();
//			canvas.clipRect(_friend_x, _friend_y - _friend_s / 2, _friend_x + _friend_w - _friend_s, _friend_y + _friend_h + _friend_s / 2);
//			float temp_X = _friend_x;
//			float temp_Y = _friend_y+y;
//			paint.setColor(0xffBD6D18);
//			paint.setTextSize(20 * GameConfig.f_zoom);
//		 
//			if(_friends!=null){
//				for (int i=0; i<_friends.size(); i++) {			
//					if (_friends.get(i).bmp == null || _friends.get(i).bmp == null) {
//						canvas.drawBitmap(GameStaticImage.s_share_ui_photo_01.bitmap, move_X + temp_X + (-1 + i) * (_friend_sc + _friend_s), temp_Y, null);
//					}else{
//						// icon
//						canvas.drawBitmap(_friends.get(i).bmp, move_X + temp_X + (-1 + i) * (_friend_sc + _friend_s) + 3, temp_Y + 3 * GameConfig.f_zoomy, null);
//						// 背景框
//						share_ui_photo_04.drawBitmap(canvas, share_ui_photo_04.bitmap, move_X + temp_X + (-1 + i) * (_friend_sc + _friend_s), temp_Y, null);
//						if(FarmModule.uid  == _friends.get(i).uid)
//							share_ui_photo_05.drawBitmap(canvas, share_ui_photo_05.bitmap, move_X + temp_X + (-1 + i) * (_friend_sc + _friend_s), temp_Y, null);
//						
//					}
//					if (_friends.get(i).cd != 0)
//						GameStaticImage.s_word_num_07[0].drawBitmap(canvas, GameStaticImage.s_word_num_07, (int)(move_X + temp_X + (-1 + i) * (_friend_sc + _friend_s) + 2 * GameConfig.f_zoomx), (int)(temp_Y + (GameStaticImage.s_share_ui_photo_03.bitmap.getHeight() - GameStaticImage.s_word_num_07[0].bitmap.getHeight())/2), GameConfig.Char_num2, _friends.get(i).s_time, null, 0, 1f);
//					canvas.drawText(_friends.get(i).name, move_X + temp_X + (-1 + i) * (_friend_sc + _friend_s), temp_Y + 93 * GameConfig.f_zoomy, paint);
//				}
//			}
//			canvas.restore();
//			
//			if(!FacebookOperation.isOpenNet && _friends==null && !FacebookOperation.getFacebook().getFriendNet()){
//				int h = (int)(270 * GameConfig.f_zoomy);
//				int yy = (int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy);
//				float tempx = ((float) ((GameConfig.GameScreen_Width - loading[0].bitmap.getWidth()) / 2));
//			    tempy = yy + ((float) ((h - loading[0].bitmap.getHeight()) / 2))+y;
//
//				loading[tempi].drawBitmap(canvas, loading[tempi].bitmap, tempx, tempy , null);
//
//			} else if(FacebookOperation.isOpenNet && _friends==null){ // 没网络
//				 int h = (int)(270 * GameConfig.f_zoomy);
//				 int yy = (int) (GameConfig.GameScreen_Height - 230 * GameConfig.f_zoomy);
//				 String temp = LangUtil.getLangString(LangDefineClient.CHECK_NET);
//				 float tempx = ((float) ((GameConfig.GameScreen_Width - paint.measureText(temp)) / 2));
//				 tempy = yy + ((float) ((h - paint.getTextSize()) / 2))+y;
//
//				 canvas.drawText(temp, tempx, tempy , paint);
//
//			}
//			
//			if(_friends!=null){
//				if (_friends.size()>=2 && _friends.get(1).index == 1 && move_X >= 0) {
//				} else {
//					GameStaticImage.s_share_ui_arrows_01_02[0].drawBitmap(canvas, GameStaticImage.s_share_ui_arrows_01_02[0].bitmap, 28 * GameConfig.f_zoomx, tempy + 132 * GameConfig.f_zoomy,null);	
//				}
//				if (_friends.size() < 5 || (_friends.size()>=5 && _friends.get(4).index == friends.size() && move_X <= 0)) {
//				} else {
//					GameStaticImage.s_share_ui_arrows_01_02[1].drawBitmap(canvas, GameStaticImage.s_share_ui_arrows_01_02[1].bitmap, 493 * GameConfig.f_zoomx, tempy + 132 * GameConfig.f_zoomy,null);
//				}	
//			}
//		}
// 
// 
//	}
//	
//	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		if (isAni) {
//			ani_up += ani_y1 / ani_frame;
//			ani_down -= ani_y2 / ani_frame;
//			if (ani_up >= 0 || ani_down <= 0) {
//				ani_up = ani_down = 0;
//				isAni = false;
//			}
//		} else if (isCancel) {
//			ani_up -= ani_y1 / ani_frame;
//			ani_down += ani_y2 / ani_frame;
//			if (ani_up <= -ani_y1 || ani_down >= ani_y2) {
//				ani_up = -ani_y1;
//				ani_down = ani_y2;
//				isCancel = false;
//				if (isGo == 0) {
//					GameManager.ChangeModule(null);	
////					if (GameStaticImage.s_map_ship_1 == null) new ImageThread().start();
//				}
//			}
//		}else {			
//			
//			if (plant != null && FacebookOperation.isLanding) {
//				for(int i=0;i<plant.length;++i){
//					if(plant[i]!=null)
//						plant[i].run();
//				}
//				
//			}
//			
//			if(FacebookOperation.isLanding){
//				if ((_friends == null || plant==null) && !FacebookOperation.getFacebook().getFriendNet()) {
//					// tempi = 0;
//					if (GameConfig.i_coke % 2 == 0) {
//						tempi++;
//						if (tempi == 9)
//							tempi = 0;
//					}
//				}
//				
//				if (_friends!=null && isCorrectFriendMove) {
//					move_X += correctFriend_move;
//					if (move_X <= -(_friend_sc + _friend_s)) {
//						if (_friends.get(_friends.size()-2).index != friends.size())
//							addRightFriend();
//						move_X = 0;
//						correctFriend_move = Math.abs(correctFriend_move);
//						isCorrectFriendMove = false;
//					} else if (move_X > -Math.abs(correctFriend_move) && move_X < Math.abs(correctFriend_move)) {
//						move_X = 0;
//						correctFriend_move = Math.abs(correctFriend_move);
//						isCorrectFriendMove = false;
//					} else if (move_X >= _friend_sc + _friend_s) {
//						if (_friends.get(_friends.size()-1).index != 1)
//							addLeftFriend();
//						move_X = 0;
//						correctFriend_move = Math.abs(correctFriend_move);
//						isCorrectFriendMove = false;
//					}
//				}
//			}
//			
//			 
//		}
//	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent msg) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean onKeyUp(int keyCode, KeyEvent msg) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void Release() {
//		// TODO Auto-generated method stub
//		if(farm_ui_ribbon_02 != null && farm_ui_ribbon_02.bitmap!=null){
//			GameImage.delImage(farm_ui_ribbon_02.bitmap);
//		}
//		farm_ui_ribbon_02 = null;
//		
//		if(word_title_friend != null && word_title_friend.bitmap!=null){
//			GameImage.delImage(word_title_friend.bitmap);
//		}
//		word_title_friend = null;
//		
//		if(farm_01 != null && farm_01.bitmap!=null){
//			GameImage.delImage(farm_01.bitmap);
//		}
//		farm_01 = null;
//		
//		if(plant!=null)
//			for(int i=0;i<plant.length;++i){
//				if(plant[i]!=null)
//					plant[i].delete();
//				plant[i] = null;
//			}
//		plant = null;
//		
//		if(farm_icon_facebook_03 != null){
//			GameImage.delImageArray(farm_icon_facebook_03);
//		}
//		farm_icon_facebook_03 = null;
//	 
//		
//		if (loading != null)
//			GameImage.delImageArray(loading);
//		loading = null;
//		if(share_ui_photo_04!=null)
//			GameImage.delImage(share_ui_photo_04.bitmap);
//		share_ui_photo_04 = null;
//		
//		if(share_ui_photo_05!=null)
//			GameImage.delImage(share_ui_photo_05.bitmap);
//		share_ui_photo_05 = null;
//		
//		FacebookOperation.getFacebook().setFriendInt(null);
//		UserRequest.getUser().setFarmInt(null);
//		FacebookOperation.getFacebook().setFriendIcon(null);
//		
//		if(farm_farm_02!=null && farm_farm_02.bitmap!=null)
//			GameImage.delImage(farm_farm_02.bitmap);
//		farm_farm_02 = null;
//		GameManager.setGT(null);
//	}
//
//	@Override
//	public void initwordpic() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//
//		int x = (int) event.getX();
//		int y = (int) event.getY(); 
//		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){  //新手教学
//			if(plant!=null && (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL22 || GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL23)){
//				 
//					for(int i=0;i<1;++i){
//						if(plant[i]!=null)
//							plant[i].onTouch(event, ani_up);
//					}
//				 
//				return;
//			}
//			if (event.getAction() == MotionEvent.ACTION_DOWN) {
//				
//				if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL21 && !FacebookOperation.isLanding && isFaceBook(x, y)){
//					anjianfacebook = true;
//				} 
//				if (GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL24 ){
//					
//					float temp1_X = _friend_x;
//					float temp1_Y = _friend_y;
//					if(_friends!=null){
//						int i = 1;
//							if (ExternalMethods.CollisionTest(x, y, 
//									temp1_X + (-1 + i) * (_friend_sc + _friend_s), temp1_Y, 
//									temp1_X + (-1 + i) * (_friend_sc + _friend_s) + _friend_sc, temp1_Y + _friend_h)) {
//								anjianfriend = true;
//							}
//					}
//				}
//			}else if (event.getAction() == MotionEvent.ACTION_UP) {
//				if (!FacebookOperation.isLanding) { //未登录
//					if (anjianfacebook && isFaceBook(x, y)){
////						ChooseLevelModule.sendMessage("facebook被点击");
//						if(FacebookOperation.gameState!= FacebookOperation.GAME_STATE_INIT_LOADING)
//							FacebookOperation.gameState= FacebookOperation.GAME_STATE_INIT_LOADING;
//						FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.level_friend);
//						GameManager.getGT().finish();
//						new VeggiesData().saveGame();
//						anjianfacebook = false;
//					}
//				}
//				
//				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL24 ){
//					float temp1_X = _friend_x;
//					float temp1_Y = _friend_y;
//					if(_friends!=null){
//						int i=1;
////						for (int i=1; i<_friends.size(); i++) {
////						if(_friends.size()>=1){
//							if (anjianfriend && ExternalMethods.CollisionTest(x, y, 
//									temp1_X + (-1 + i) * (_friend_sc + _friend_s), temp1_Y, 
//									temp1_X + (-1 + i) * (_friend_sc + _friend_s) + _friend_sc, temp1_Y + _friend_h)) {
//										
//										if(_friends.size()>=1){
//												//查找显示的好友
//												AskFriend as = _friends.get(i);
////												indedx = i;
//												FarmData data = farmdata.get(as.uid);
////												ChooseLevelModule1.sendMessage("测试=" +as.name);
//												System.out.println("<><>"+as.uid);
//												if(data!=null){
//													initFarmPlant(data);
//												}else{
//													UserRequest.getUser().reqVegetableList(as.uid);
//													if(UserRequest.getUser().getFarm()==null){
//														UserRequest.getUser().setFarmInt(new FarmUpdate() {
//															@Override
//															public void onUpdateFarm(FarmData data) {
//																// TODO Auto-generated method stub
//																initFarmPlant(data);
//															}
//														});
//													}
//												}
//										}
//										GameManager.getGT().finish();
//										new VeggiesData().saveGame(); 
//										
//										anjianfriend = false;
//								}
////							}
//						}
//				
//				}
//				
//			}
//			
//			return;
//		}
//		if (isAni) return;
//		if (isCancel) return;
//		
//		if (FacebookOperation.isLanding && plant!=null){
//			for(int i=0;i<plant.length;++i){
//				if(plant[i]!=null)
//					plant[i].onTouch(event, ani_up);
//			}
//		}
//		
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			_x = x;
//			_y = y;
//			if (!FacebookOperation.isLanding && isFaceBook(x, y)){
//					anjianfacebook = true;
//			}
//			
//			if (isClose(x, y)) {
//				anjianclose = true;
//			} 
//			if (ExternalMethods.CollisionTest(x, y, _friend_x, _friend_y - _friend_s / 2, 
//					_friend_x + _friend_w - _friend_s, _friend_y + _friend_h + _friend_s / 2)) {
//					ismove = true;
//					oldX = (int)x;
//			}
//			
//			float temp1_X = _friend_x;
//			float temp1_Y = _friend_y;
//			if(_friends!=null)
//				for (int i=1; i<_friends.size(); i++) {
//					if (ExternalMethods.CollisionTest(x, y, 
//							temp1_X + (-1 + i) * (_friend_sc + _friend_s), temp1_Y, 
//							temp1_X + (-1 + i) * (_friend_sc + _friend_s) + _friend_sc, temp1_Y + _friend_h)) {
//						anjianfriend = true;
//					}
//				}
//		} else if (event.getAction() == MotionEvent.ACTION_UP) {
//			if (!FacebookOperation.isLanding) { //未登录
//				if (anjianfacebook && isFaceBook(x, y)){
////					ChooseLevelModule.sendMessage("facebook被点击");
//					if(FacebookOperation.gameState!= FacebookOperation.GAME_STATE_INIT_LOADING)
//						FacebookOperation.gameState= FacebookOperation.GAME_STATE_INIT_LOADING;
//					FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.level_friend);
//				}
//			
//			}
//			
//			if (anjianclose && isClose(x, y)) {
//				isCancel = true;
//				isGo = 0;
//			} 
//			if (ismove) {
//				if (move_X < -(_friend_sc + _friend_s)/2 || (move_X > 0 && move_X < (_friend_sc + _friend_s)/2)) {
//					//向_friend[0]靠近 -
//					correctFriend_move = - correctFriend_move;
//					isCorrectFriendMove = true;
//				} else if ((move_X < 0 && move_X >= -(_friend_sc + _friend_s)/2 || move_X >= (_friend_sc + _friend_s)/2)){
//					//向_friend[1]靠近 +
//					isCorrectFriendMove = true;
//				}
//			}
//			
//			float temp1_X = _friend_x;
//			float temp1_Y = _friend_y;
//			if(_friends!=null){
//				for (int i=1; i<_friends.size(); i++) {
//					if (anjianfriend && ExternalMethods.CollisionTest(x, y, 
//							temp1_X + (-1 + i) * (_friend_sc + _friend_s), temp1_Y, 
//							temp1_X + (-1 + i) * (_friend_sc + _friend_s) + _friend_sc, temp1_Y + _friend_h)) {
//								
//								//查找显示的好友
//								AskFriend as = _friends.get(i);
//								FarmData data = farmdata.get(as.uid);
////								ChooseLevelModule1.sendMessage("测试=" +as.name);
//								System.out.println("<><>"+as.uid);
//								for(int k=0;k<plant.length;++k){
//									plant[k] = null;
//								}
////								indedx = i;
//								plant = null;
//								if(data!=null){
//									initFarmPlant(data);
//								}else{
//									UserRequest.getUser().reqVegetableList(as.uid);
//									if(UserRequest.getUser().getFarm()==null){
//										UserRequest.getUser().setFarmInt(new FarmUpdate() {
//											@Override
//											public void onUpdateFarm(FarmData data) {
//												// TODO Auto-generated method stub
//												initFarmPlant(data);
//											}
//										});
//									}
//								}
//								
//								break;
//						}
//					}
//				}
//			anjianfriend = false;
//			ismove = false;
//			 
//			anjianfacebook = false;
//		}else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//			if(friends!=null && friends.size()<=4)return;
//			if (ismove) {
//				if (isCorrectFriendMove) {
//					isCorrectFriendMove = false;	//纠正移动关闭等移动释放后继续纠正
//					correctFriend_move = Math.abs(correctFriend_move);
//				}
//				
//				int tempX = (int) event.getX();
//				
//				move_X+=tempX-oldX;
//				
//				oldX = (int) event.getX();	
//				
//				if (_friends!=null && move_X > _friend_s && _friends.get(0).index == -1) {
//					move_X = _friend_s;
//				} else if (friends!=null && (move_X < -_friend_s && _friends.size() < 5 ) || (move_X < -_friend_s && _friends.get(5).index == -1)) {
//					move_X = -_friend_s;
//				}
//				if (move_X >= _friend_s + _friend_sc) {
//					move_X = 0;
//					addLeftFriend();
//				} else if (move_X <= -(_friend_s + _friend_sc)) {
//					move_X = 0;
//					addRightFriend();
//				}
//				if (tempX - _x > 5 * GameConfig.f_zoom || event.getY() - _y > 5 * GameConfig.f_zoom) {
//					anjianfriend = false;
//				}
//			}
//		}
//	}
//
//	//关闭
//	private boolean isClose(int x, int y){
//		if(ExternalMethods.CollisionTest(x, y, 
//				449 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
//				141 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
//			449 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
//			141 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)){
//			return true;
//		}
//		return false;
//	}
//	
//	//facebook是否被点击
//	private boolean isFaceBook(int x, int y){
//		int temp_x = (int)(9 * GameConfig.f_zoomx);
//		int temp_y = (int)(GameConfig.GameScreen_Height - (154 + 13) * GameConfig.f_zoomy + ani_down * GameConfig.f_zoomy);
//		int temp_h = (int)(154 * GameConfig.f_zoomy);
//		temp_x = (int) (temp_x+(30 * GameConfig.f_zoomx));
//		if(ExternalMethods //未登录
//			.CollisionTest(x, y,
//					temp_x,
//					temp_y+(temp_h-h_facebook_02>>1)
//					 + ani_down
//					* GameConfig.f_zoomy, 
//					temp_x
//					+ w_facebook_02,
//					temp_y+(temp_h-h_facebook_02>>1)
//					+ h_facebook_02 + ani_down
//					* GameConfig.f_zoomy
//					)){
//			return true;
//		}
//		
//		return false;
//	}
//	
//	private void addLeftFriend() {
//		if(friends!=null )
//			for (int j=_friends.size()-1; j>=0; j--) {
//				if (j > 0) {
//					_friends.set(j, _friends.get(j-1));				
//				} else {
//					if (_friends.get(j).index <= 1) {
//						_friends.set(j, new AskFriend(-1, -1, "", null));
//					} else {
//						_friends.set(j, friends.get((_friends.get(j).index-2)));															
//					}
//				}
//			}
//	}
//	private void addRightFriend() {
//		if(friends!=null )
//			for (int j=0; j<_friends.size(); j++) {
//				if (j + 1 <_friends.size()) {			
//					_friends.set(j, _friends.get(j+1));
//				} else {
//					if (_friends.get(j).index >= friends.size()) {
//						_friends.set(j, new AskFriend(-1, -1, "", null));
//					} else {
//						_friends.set(j, friends.get(_friends.get(j).index));
//					}
//				}
//			}
//	}
//	@Override
//	public void onIcon(FaceBookPlayer playerIcon) {
//		// TODO Auto-generated method stub
//		if(friends!=null){
//			for(int i=0;i<friends.size();++i){
//				AskFriend friend = friends.get(i);
//				if(friend.name.equals(playerIcon.getName())){
//					int width = share_ui_photo_04.bitmap.getWidth();
//					int height = share_ui_photo_04.bitmap.getHeight();
//					// 图片缩放
//					Bitmap temp = FBInterface.allIconMap.get(playerIcon.getId_facebook());//user.getIcon();
//					if(temp!=null){
//						temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx,
//								height - 6 * GameConfig.f_zoomy);
//					} 
//					friend.setIcon(temp);
//				}
//			}
//		}
//	}
//}//end class
