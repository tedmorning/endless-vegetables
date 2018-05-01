package com.endlessvegetables2.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.R;
import com.facebook.FacebookOperation;
import com.facebook.UserRequest;
import com.facebook.FacebookOperation.setFriendIcon;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

/**
 * 充值爱心
 * 
 * @author Administrator
 * 
 */
public class HeartRechargeModule extends Module {

	private boolean anjianclose;
	private boolean anjianbutton[] = new boolean[2];

	private Sprite gs;
	private Sprite gem; //砖石
	private Sprite word_key_request; //发送图标
	private Sprite shop_heart_01; //充值爱心图标
	private Sprite share_ui_back_06; //充值额度的背景
	private Sprite shop_heart_02;
	private Sprite[] word_num_03;
	private Sprite icon_facebook_01;
	private Sprite s_share_ui_back_03;
	private Sprite s_word_num_04[];
	
	int bgx[] = {76, 285};
	int bgy[] = {256, 257};
	int button_x[] = {96, 305};
	int button_y[] = {454, 454};
	
	final int rec = 0; //充值
	final int get = 1; //好友索取
	final int rec_number = 10; //充值需求的钻石
	final int get_number = 5; //钻石充值可获得5可心
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		
		gs = new Sprite();
		gem = new Sprite(GameImage.getImage(GameStaticImage.shop_gem_12));
		word_key_request = new Sprite(GameImage.getImage(GameStaticImage.word_key_request));
		shop_heart_01 = new Sprite(GameImage.getImage(GameStaticImage.shop_heart_01));
		share_ui_back_06 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_06));
		shop_heart_02 = new Sprite(GameImage.getImage(GameStaticImage.shop_heart_02));
		word_num_03  = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_03, 11, 1, GameImage.Sort_line);
		icon_facebook_01  = new Sprite(GameImage.getImage(GameStaticImage.icon_facebook_01));
		s_share_ui_back_03 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_03));
		s_word_num_04 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_04, 12, 1, GameImage.Sort_line);
		
		
		//		if (FacebookOperation.isLanding
//				&& FacebookOperation.getFacebook().getLoadingFriend()) {
//			 
//		} else {
//			FacebookOperation.getFacebook().setFriendInt(new setFriendIcon() {
//
//				@Override
//				public void onSetFriend() {
//					// TODO Auto-generated method stub
//				   System.out.println("<><> 表名这里没登陆过face 登陆了进来这里");
//				   reqFriendTL();
//				}
//			});
//		}
		
		return false;
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		gs.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		
		int y = 0;
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int) (28 * GameConfig.f_zoomx), (int) (148 * GameConfig.f_zoomy + y), (int) (476 * GameConfig.f_zoomx),(int) (429 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null,	(int) (45 * GameConfig.f_zoomx), (int) (211 * GameConfig.f_zoomy + y), (int) (443 * GameConfig.f_zoomx), (int) (349 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int) (45 * GameConfig.f_zoomx), (int) (211 * GameConfig.f_zoomy + y), (int) (443 * GameConfig.f_zoomx), (int) (349 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 449 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth() / 2 * (anjianclose ? 0.2f : 0f), 141 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight() / 2 * (anjianclose ? 0.2f : 0f) + y, anjianclose ? 1.2f : 1f, anjianclose ? 1.2f : 1f, 255, 0, 0, 0);
		for(int i = 0;i<bgx.length;++i){
			s_share_ui_back_03.drawBitmap(canvas, 
					s_share_ui_back_03.bitmap, 
					bgx[i] * GameConfig.f_zoomx, 
					bgy[i] * GameConfig.f_zoomy, null);
		}
	 
		for(int i= 0;i<anjianbutton.length;++i){
				GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, 
						(int)(button_x[i] * GameConfig.f_zoomx) - GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2*(anjianbutton[i]?0.2f:0f),
						(int)(button_y[i] * GameConfig.f_zoomy) - GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2*(anjianbutton[i]?0.2f:0f), 
						anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);
		}
		gem.drawBitmap(canvas, gem.bitmap, (int)(115 * GameConfig.f_zoomx), 
				(int)(460 * GameConfig.f_zoomy),  null);
 
//		word_key_request.drawBitmap(
//				canvas, null, 
//				(int)(320 * GameConfig.f_zoomx), 
//				(int)(469 * GameConfig.f_zoomy), 
//				(int)(107 * GameConfig.f_zoomx), 
//				 -1);
		int i = 1;
//		word_key_request.drawBitmap(canvas, 
//				(int)(button_x[i] * GameConfig.f_zoomx)+(GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth() - word_key_request.bitmap.getWidth()>>1)- GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2*(anjianbutton[i]?0.2f:0f),
//				(int)(button_y[i] * GameConfig.f_zoomy)+(GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight() - word_key_request.bitmap.getHeight()>>1) - GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2*(anjianbutton[i]?0.2f:0f), 
//				anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);
		
		s_word_num_04[0].drawBitmap(canvas, 
				s_word_num_04, (int)(160 * GameConfig.f_zoomx),(int)(468 * GameConfig.f_zoomy), 
				GameConfig.Char_num1, ""+rec_number, 
				null, 0, anjianbutton[0]?1.2f:1.0f);
		
		shop_heart_01.drawBitmap(canvas, shop_heart_01.bitmap, (int)(120 * GameConfig.f_zoomx), (int)(232 * GameConfig.f_zoomy),  null);
		
		share_ui_back_06.drawBitmap(
				canvas, null, 
				(int)(121 * GameConfig.f_zoomx), 
				(int)(396 * GameConfig.f_zoomy), 
				(int)(95 * GameConfig.f_zoomx), 
				 -1);
		
		shop_heart_02.drawBitmap(canvas, shop_heart_02.bitmap, (int)(109 * GameConfig.f_zoomx), 
				(int)(389 * GameConfig.f_zoomy),  null);
		
		word_num_03[0].drawBitmap(
				canvas, 
				word_num_03, 
				(int)(171 * GameConfig.f_zoomx), 
				(int)(403 * GameConfig.f_zoomy), 
				GameConfig.Char_num1, 
				""+get_number, 
				null, 0, 1.0f);
//		icon_facebook_01.drawBitmap(
//				canvas, null, 
//				(int)(321 * GameConfig.f_zoomx), 
//				(int)(305 * GameConfig.f_zoomy), 
//				(int)(104 * GameConfig.f_zoomx), 
//				 -1);
		//icon_facebook_01.drawBitmap(canvas, icon_facebook_01.bitmap, (bgx[1] * GameConfig.f_zoomx + (s_share_ui_back_03.bitmap.getWidth() - icon_facebook_01.bitmap.getWidth() >>1)), (int)(305 * GameConfig.f_zoomy), null);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

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
		if(gs==null && gem==null)return;  //已经清理过了
		if(gs!=null && gs.bitmap!=null){
			GameImage.delImage(gs.bitmap);
			gs.bitmap = null;
		}
		gs = null;

		 
		GameImage.delImage(gem.bitmap);
		if(gem.bitmap!=null)
			gem.bitmap = null;
		gem = null;
		
		GameImage.delImageArray(s_word_num_04);
		s_word_num_04 = null;
		
		GameImage.delImage(s_share_ui_back_03.bitmap);
		s_share_ui_back_03 = null;
		
		GameImage.delImage(shop_heart_02.bitmap);
		if(shop_heart_02.bitmap!=null)
			shop_heart_02.bitmap = null;
		shop_heart_02 = null;
		
		GameImage.delImage(word_key_request.bitmap);
		if(word_key_request.bitmap!=null)
			word_key_request.bitmap = null;
		word_key_request = null;

		GameImage.delImage(shop_heart_01.bitmap);
		if(shop_heart_01.bitmap!=null)
			shop_heart_01.bitmap = null;
		shop_heart_01 = null;

		GameImage.delImage(share_ui_back_06.bitmap);
		if(share_ui_back_06.bitmap!=null)
			share_ui_back_06.bitmap = null;
		share_ui_back_06 = null;
	 
		if(word_num_03!=null)
			GameImage.delImageArray(word_num_03);
		 
		word_num_03 = null;
		
		GameImage.delImage(icon_facebook_01.bitmap);
		if(icon_facebook_01.bitmap!=null)
			icon_facebook_01.bitmap = null;
		icon_facebook_01 = null;
	 
		bgx = null;
		bgy = null;
		anjianbutton = null;
		button_x = null;
		button_y = null;
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
					449 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					141 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
					449 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
					141 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			}
			
			for (int i=0; i<anjianbutton.length; i++) {
				if (ExternalMethods.CollisionTest(x, y, 
						(int)(button_x[i] * GameConfig.f_zoomx), 
						(int)(button_y[i] * GameConfig.f_zoomy),
						(int)(button_x[i] * GameConfig.f_zoomx+ 134 * GameConfig.f_zoomx ), 
						(int)(button_y[i] * GameConfig.f_zoomy)+GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight())) {
					anjianbutton[i] = true;
				}
			}
			
//			, 
//			(int)(button_y[i] * GameConfig.f_zoomy), 
//			(int)(134 * GameConfig.f_zoomx), -1)
			
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			if (anjianclose && ExternalMethods.CollisionTest(x, y, 
					449 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					141 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				141 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
//				if (GameStaticImage.s_map_ship_1 == null) new ImageThread().start();
				GameManager.ChangeModule(null);
			}
			 
			for (int i=0; i<anjianbutton.length; i++) {
				if (ExternalMethods.CollisionTest(x, y, 
						(int)(button_x[i] * GameConfig.f_zoomx), 
						(int)(button_y[i] * GameConfig.f_zoomy),
						(int)(button_x[i] * GameConfig.f_zoomx+ 134 * GameConfig.f_zoomx ), 
						(int)(button_y[i] * GameConfig.f_zoomy)+GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight())) {
					anjianbutton[i] = false;
					if(i == rec ){
						if(VeggiesData.getGem()>=rec_number){
							
							if(VeggiesData.getHeart() >= Configs.HEART_MAX){
								//体力已达上线
								String temp = LangUtil.getLangString(LangDefineClient.HEART_MAX);
								GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
								});
							}else{
								  //购买成功
								VeggiesData.addGem(-rec_number);
								VeggiesData.addHeart(get_number);
								String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_SUCCESS);
								GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
								});
								//刷新背景
								GameManager.getGameManager().refreshForbidMode();
							}
							
							
						}else{
							String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
							GameManager.setPopUp(PopUp.GOOGS,GameStaticImage.shop_gem_13, new PopUp(temp) {
							});
						}
					}else if(i == get){
						GameManager.forbidModule(new ReqUserHeartModule());
//						if(FacebookOperation.isLanding){
//							reqFriendTL();
//						}else{ //因为这里需要好友 没登陆过facebook 就需要发送请求了
//							FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.level_friend);
//						}
						
					}
				}
			}
			for (int i=0; i<anjianbutton.length; i++) {
				anjianbutton[i] = false;
			}
		}
	}

//	/**
//	 * 索要体力
//	 */
//	private void  reqFriendTL(){
//		if( FBInterface.friendMap.size()<=0){
//			FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.level_friend);
//			String  temp =  MainActivity.getActivity().getString(
//					R.string.no_friend);
//			ChooseLevelModule1.sendMessage(temp);
//		}else{
//			UserRequest.getUser().reqPhysicalStrength();
//			String temp = MainActivity.getActivity().getString(R.string.reqsend);
//			ChooseLevelModule1.sendMessage(temp);
//		}
//	}
	
}// end class
