package com.endlessvegetables2.ui;

import java.util.Iterator;
import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.facebook.FacebookOperation;
import com.facebook.FacebookOperation.setFriendIcon;
import com.facebook.FriendIcon;
import com.facebook.UserRequest;
import com.facebook.sdk.FBInterface;
import com.game.data.FaceBookPlayer;
import com.game.data.FaceBookPlayer.UserReqHeart;
import com.game.item.MessageItem;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;
/**
 * 向用户索要爱心体力的界面
 * @author Administrator
 *
 */
public class ReqUserHeartModule extends Module implements FriendIcon{

	private boolean anjianclose;
	private Sprite gs; // 半透明
	private Sprite word_title_message;
	private Sprite[] loading;
	private Paint paint;
	private Typeface fontFace;
	private Vector<MessageItem> v_message;

	int tempi = 0;
	
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		if (!FacebookOperation.getFacebook().getLoadingFriend()) {
				FacebookOperation.getFacebook().setStste(
						FacebookOperation.GAME_STATE_USER_VIEW);
		}
		FacebookOperation.getFacebook().setFriendIcon(this);
		
		// 没有好友需要请求好友
		if (FacebookOperation.isLanding
				&& FacebookOperation.getFacebook().getLoadingFriend()) {
			initItem();
		} else {
			FacebookOperation.getFacebook().setFriendInt(new setFriendIcon() {

				@Override
				public void onSetFriend() {
					// TODO Auto-generated method stub
					initItem();
				}
			});
		}
		gs = new Sprite();
		word_title_message = new Sprite(
				GameImage.getImage(GameStaticImage.word_title_message));
 
		loading = GameImage.getAutoSizecutSprite(
				GameStaticImage.share_loading_03, 9, 1, GameImage.Sort_line);
		tempi = 0;

		fontFace = Typeface.createFromAsset(Main.getActivity().getAssets(),
				"font/ARLRDBD.TTF");
		paint = new Paint();
		paint.setTextSize(26 * GameConfig.f_zoom);
		paint.setTypeface(fontFace);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		return false;
	}

	private void initItem() {
		boolean isNo = false;
		Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
		while (iterator.hasNext()) {
			isNo = true;
			FaceBookPlayer user = FacebookOperation.playerMap.get(iterator.next());
			user.setHeartInt(new UserReqHeart() {
				@Override
				public void onHeart(FaceBookPlayer player) {
					// TODO Auto-generated method stub
						//刷新爱心时间
						if(v_message == null)
							v_message = new Vector<MessageItem>();
						if(v_message.size()==0){
							String temp = LangUtil.getLangString(LangDefineClient.REQ_TEXT);
							temp = temp.replace("{name}", player.getName()+"");
							MessageItem item = new MessageItem(true, -1, player.getid_server(), 0l, player.getIcon(),
									player.getName(),  temp);
							item.time = player.getGetHeartTime();
							v_message.add(item);
						}else{
							for(int i=0;i<v_message.size();++i){
						    	if(v_message.get(i).uid != player.getid_server()){
						    		String temp = LangUtil.getLangString(LangDefineClient.REQ_TEXT);
									temp = temp.replace("{name}", player.getName()+"");
									MessageItem item = new MessageItem(true, -1, player.getid_server(), 0l, player.getIcon(),
						    				player.getName(),  temp);
									item.time = player.getGetHeartTime();
									v_message.add(item);
						    		break;
						    	}
							}
						}
				
				}
			});
			//表示没请求过
			if(user.getGetHeartTime() == FaceBookPlayer.L_NULL){
				UserRequest.getUser().reqFriendHelp(user.getid_server(), UserRequest.GETOLD_HEART_TIME);
			}else{
				if(v_message == null)
					v_message = new Vector<MessageItem>();
				//刷新爱心时间
				String temp = LangUtil.getLangString(LangDefineClient.REQ_TEXT);
				temp = temp.replace("{name}", user.getName()+"");
				MessageItem item = new MessageItem(true, -1, user.getid_server(), 0l, user.getIcon(),
						user.getName(),  temp);
				item.time = user.getGetHeartTime();
				v_message.add(item);
			}
		}
		if(!isNo && v_message == null)
			v_message = new Vector<MessageItem>();
	}

	private void updateTime(){
		if(v_message!=null)
			for(int j=0;j<v_message.size();++j){
					v_message.get(j).run();
			}
	}
	
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		gs.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0,
				GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		int y = 0;
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null,
				(int) (28 * GameConfig.f_zoomx),
				(int) (85 * GameConfig.f_zoomy + y),
				(int) (476 * GameConfig.f_zoomx),
				(int) (671 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null,
				(int) (45 * GameConfig.f_zoomx),
				(int) (148 * GameConfig.f_zoomy + y),
				(int) (443 * GameConfig.f_zoomx),
				(int) (591 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null,
				(int) (45 * GameConfig.f_zoomx),
				(int) (148 * GameConfig.f_zoomy + y),
				(int) (443 * GameConfig.f_zoomx),
				(int) (591 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas,
				453 * GameConfig.f_zoomx
						- GameStaticImage.s_share_ui_close.bitmap.getWidth()
						/ 2 * (anjianclose ? 0.2f : 0f), 76
						* GameConfig.f_zoomy
						- GameStaticImage.s_share_ui_close.bitmap.getHeight()
						/ 2 * (anjianclose ? 0.2f : 0f) + y, anjianclose ? 1.2f
						: 1f, anjianclose ? 1.2f : 1f, 255, 0, 0, 0);

		// 标题
		word_title_message.drawBitmap(canvas, null,
				(int) (182 * GameConfig.f_zoomx),
				(int) (107 * GameConfig.f_zoomy + y),
				(int) (172 * GameConfig.f_zoomx), -1);

		if (v_message != null) {
			int list_x = (int) ((45 + 16) * GameConfig.f_zoomx);
			int list_y = (int) ((148 + 12) * GameConfig.f_zoomy);
			for (int i = 0; i < v_message.size(); ++i) {
				int listy = list_y + (int) (80 * i * GameConfig.f_zoomy);
				// MessageItem item = new MessageItem(i, "蔬菜"+i);
				v_message.get(i).paint(canvas, list_x, listy, paint);
			}

		} else if (!FacebookOperation.isOpenNet && !FacebookOperation.getFacebook().getFriendNet()) {
			// 显示loading
			float tempx = ((float) ((GameConfig.GameScreen_Width - loading[0].bitmap.getWidth()) / 2));
			float tempy = y + ((float) ((GameConfig.GameScreen_Height - loading[0].bitmap.getHeight()) / 2));

			loading[tempi].drawBitmap(canvas, loading[tempi].bitmap, tempx, tempy, null);

		} 
		if(FacebookOperation.isOpenNet && v_message==null) { // 没网络
			paint.setColor(0xff824d22);
			paint.setTextSize(22 * GameConfig.f_zoom);
			String temp = LangUtil.getLangString(LangDefineClient.CHECK_NET);
			float tempx = ((float) ((GameConfig.GameScreen_Width - paint.measureText(temp)) / 2));
			float tempy = y + ((float) ((GameConfig.GameScreen_Height - paint.getTextSize()) / 2));

			canvas.drawText(temp, tempx, tempy, paint);

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (v_message == null
				&& !FacebookOperation.getFacebook().getFriendNet()) {
			// tempi = 0;
			if (GameConfig.i_coke % 2 == 0) {
				tempi++;
				if (tempi == 9)
					tempi = 0;
			}
		}else {
//			Iterator iterator = MessageModule.message.keySet().iterator();
//			while (iterator.hasNext()) {
//				MessageData mess = MessageModule.message.get(iterator.next());
//				int time = mess.getReqTime();
//				if(time>0){
//					time--;
//					mess.setReqTime(time);
//				}
//			}
			updateTime();
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
		if (gs == null && word_title_message == null)
			return;
		GameImage.delImage(gs.bitmap);
		gs.bitmap = null;
		gs = null;

		GameImage.delImage(word_title_message.bitmap);
		if (word_title_message.bitmap != null)
			word_title_message.bitmap = null;
		word_title_message = null;

		if(loading!=null)
			GameImage.delImageArray(loading);
		loading = null;
		paint = null;
		fontFace = null;
		
		if(v_message!=null)
			for (int i = 0; i < v_message.size(); ++i) {
				v_message.get(i).delete();
				v_message.remove(i);
			}
		v_message = null;
		
		Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
		while (iterator.hasNext()) {
			FaceBookPlayer user = FacebookOperation.playerMap.get(iterator.next());
			user.setHeartInt(null);
		}
		
		FacebookOperation.getFacebook().setFriendInt(null);
		FacebookOperation.getFacebook().setFriendIcon(null);
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
			if (ExternalMethods.CollisionTest(
					x,
					y,
					453
							* GameConfig.f_zoomx
							- GameStaticImage.s_share_ui_close.bitmap
									.getWidth() / 2 * 0.2f,
					76
							* GameConfig.f_zoomy
							- GameStaticImage.s_share_ui_close.bitmap
									.getHeight() / 2 * 0.2f,
					453
							* GameConfig.f_zoomx
							+ GameStaticImage.s_share_ui_close.bitmap
									.getWidth() * 1.2f,
					76
							* GameConfig.f_zoomy
							+ GameStaticImage.s_share_ui_close.bitmap
									.getHeight() * 1.2f)) {
				anjianclose = true;
			}

			if (v_message == null)
				return;
			for (int i = 0; i < v_message.size(); ++i) {
				int index = v_message.get(i).onTouchEvent(event);
			}

		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (anjianclose
					&& ExternalMethods.CollisionTest(
							x,
							y,
							453
									* GameConfig.f_zoomx
									- GameStaticImage.s_share_ui_close.bitmap
											.getWidth() / 2 * 0.2f,
							76
									* GameConfig.f_zoomy
									- GameStaticImage.s_share_ui_close.bitmap
											.getHeight() / 2 * 0.2f,
							453
									* GameConfig.f_zoomx
									+ GameStaticImage.s_share_ui_close.bitmap
											.getWidth() * 1.2f,
							76
									* GameConfig.f_zoomy
									+ GameStaticImage.s_share_ui_close.bitmap
											.getHeight() * 1.2f)) {
				anjianclose = false;
				GameManager.ChangeModule(null);
			}

			if (v_message == null)
				return;
 
			for (int i = 0; i < v_message.size(); ++i) {
				int index = v_message.get(i).onTouchEvent(event);
				if (index != -1) { // 点击了
					reqFriendTL(i, v_message.get(i).uid);
					v_message.get(i).setInvalid(true);
					break;
				}
			}

			anjianclose = false;
		}
	}
	/**
	 * 索要体力
	 */
	private void  reqFriendTL(int index, long uid){
		if(FacebookOperation.playerMap==null){
			if(FacebookOperation.gameState!= FacebookOperation.GAME_STATE_INIT_LOADING)
				FacebookOperation.gameState= FacebookOperation.GAME_STATE_INIT_LOADING;
			FacebookOperation.getFacebook().landingAndInvite(FacebookOperation.level_friend);
			String  temp = LangUtil.getLangString(LangDefineClient.FRIEND_ZERO);
			ChooseLevelModule2.sendMessage(temp);
		}else{
			UserRequest.getUser().reqPhysicalStrength(uid);
 
			v_message.get(index).time =3600+System.currentTimeMillis();//大概1小时
			v_message.get(index).anjianInvalid = true;
			
//			String temp = LangUtil.getLangString(LangDefineClient.GUIDE_38);
//			ChooseLevelModule1.sendMessage(temp);
		}
	}
	
	@Override
	public void onIcon(FaceBookPlayer playerIcon) {
		// TODO Auto-generated method stub
		if(v_message!=null)
			for(int i=0;i<v_message.size();++i){
				MessageItem item = v_message.get(i);
				if(item.name.equals(playerIcon.getName())){
					Bitmap temp = FBInterface.allIconMap.get(playerIcon.getId_facebook());//user.getIcon();
					item.setIcon(temp);
				}
			}
	}
}// end class
