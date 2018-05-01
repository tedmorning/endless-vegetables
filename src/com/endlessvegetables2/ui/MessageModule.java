package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.facebook.FriendIcon;
import com.facebook.FacebookOperation.setFriendIcon;
import com.facebook.sdk.FBInterface;
import com.facebook.UserRequest;
import com.game.data.FaceBookPlayer;
import com.game.data.MessageData;
import com.game.item.MessageItem;
import com.game.item.TopListItem;
import com.kokatlaruruxi.wy.ExternalMethods;
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

/**
 * 信息界面
 * 
 * @author Administrator
 * 
 */
public class MessageModule extends Module implements FriendIcon{
	// public static Vector<MessageData> message = new Vector<MessageData>();
	public static HashMap<String, MessageData> message = new HashMap<String, MessageData>();

	private boolean anjianclose;
	// private boolean anjianall;
	private Sprite word_key_accept_2; // 全部接收
	private Sprite gs; // 半透明
	private Sprite word_title_message;
	private Sprite[] loading;
	private Paint paint;
	private Typeface fontFace;
//	private Vector<MessageItem> v_message;
	private Sprite[] arrow;
	private List<MessageItem> _library;
	private List<MessageItem> itemLibrary;
	
	int tempi = 0;

	private float _library_x,_library_y,_library_w,_library_h,_library_w_s,_library_h_s;
	private float move_Y;
	private float oldY;
	private float _x,_y;
	private boolean ismove;
	boolean isCorrectCardMove;
	float correctCard_move = 5 * GameConfig.f_zoom;
	int noCardWidth;
	int noCardHeight;
	
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		if (!FacebookOperation.getFacebook().getLoadingFriend()) {
			FacebookOperation.getFacebook().setStste(
					FacebookOperation.GAME_STATE_USER_VIEW);
		}
		FacebookOperation.getFacebook().setFriendIcon(this);
		
		arrow = new Sprite[2];
		arrow[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_03));
		arrow[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_04));
		
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
		word_key_accept_2 = new Sprite(
				GameImage.getImage(GameStaticImage.word_key_accept_2));
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
//		v_message = new Vector<MessageItem>();
		
		_library = new ArrayList<MessageItem>();
		itemLibrary = new ArrayList<MessageItem>();
		
		Bitmap _temp = null;
		int i = 0;
		Iterator iterator = MessageModule.message.keySet().iterator();
		while (iterator.hasNext()) {
			MessageData data = MessageModule.message.get(iterator.next());
			FaceBookPlayer user = null;
			Iterator iterator1 = FacebookOperation.playerMap.keySet()
					.iterator();
			while (iterator1.hasNext()) {
				FaceBookPlayer temp = FacebookOperation.playerMap.get(iterator1
						.next());
				if (temp.getid_server() == data.getFromid()) {
					user = temp;
					break;
				}
			}
			Bitmap temp = null;
			if(user!=null){
				// 图片缩放
				//temp = FBInterface.allIconMap.get(user.getId_facebook());// user.getIcon();
				if(temp!=null)
					_temp = temp;
			}
			itemLibrary.add(new MessageItem(data.getType(), i+1, user
					.getid_server(), data.getMessageid(), user.getName(), temp,
					data.getS_msg(), data.getStatus()));
			itemLibrary.add(new MessageItem(data.getType(), i+1, user
					.getid_server(), data.getMessageid(), user.getName(), temp,
					data.getS_msg(), data.getStatus()));
			itemLibrary.add(new MessageItem(data.getType(), i+1, user
					.getid_server(), data.getMessageid(), user.getName(), temp,
					data.getS_msg(), data.getStatus()));
			i++;
		}
		for (int j=0; j<itemLibrary.size(); j++) {
			itemLibrary.get(j).itemId = j+1;
		}
		_library.clear();
		_library.add(new MessageItem(-1, -1, -1, -1, "", null, "", -1));	
		_library.add(new MessageItem(-1, -1, -1, -1, "", null, "", -1));	
		_library.add(new MessageItem(-1, -1, -1, -1, "", null, "", -1));	
		for (int j=0; j<itemLibrary.size()&& j<18; j++) {
			_library.add(itemLibrary.get(j));			
		}
		isCorrectCardMove = false;
		
		if(_temp!=null){
			noCardWidth  = _temp.getWidth();
			noCardHeight = _temp.getHeight();
		}else{

			noCardWidth  = (int)(45*GameConfig.f_zoomx);
			noCardHeight = (int)(45*GameConfig.f_zoomy);
		}
		
		_library_w_s = (226 - 104) * GameConfig.f_zoomx - noCardWidth;
		_library_h_s = ((714 - 183) * GameConfig.f_zoomy - 6 * noCardHeight - arrow[0].bitmap.getHeight()) / 6;
		_library_w = _library_w_s * 4 + noCardWidth * 4;
		_library_h = 100*GameConfig.f_zoomy+_library_h_s * 6 + noCardHeight * 6-30* GameConfig.f_zoomy;
		_library_x = (45 + 6) * GameConfig.f_zoomx - _library_w_s / 2;
		_library_y = (148 + 12) * GameConfig.f_zoomy - _library_h_s / 2;

		
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

		// if (v_message != null) {
		// // 全部接收按钮
		// if (anjianall) {
		// GameStaticImage.s_share_ui_button_01[1]
		// .drawBitmap(
		// canvas,
		// null,
		// (int) (168 * GameConfig.f_zoomx +
		// (GameStaticImage.s_share_ui_button_01[0].bitmap
		// .getWidth() / 2 - GameStaticImage.s_share_ui_button_01[1].bitmap
		// .getWidth() / 2)),
		// (int) (663 * GameConfig.f_zoomy +
		// (GameStaticImage.s_share_ui_button_01[0].bitmap
		// .getHeight() / 2 - GameStaticImage.s_share_ui_button_01[1].bitmap
		// .getHeight() / 2)),
		// (int) (195 * GameConfig.f_zoomx * 1.2f), -1);
		// } else {
		// GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas,
		// null, (int) (168 * GameConfig.f_zoomx),
		// (int) (663 * GameConfig.f_zoomy),
		// (int) (195 * GameConfig.f_zoomx), -1);
		// }
		// word_key_accept_2.drawBitmap(canvas,
		// (int) (195 * GameConfig.f_zoomx) - (anjianall ? 0.2f : 0f)
		// * word_key_accept_2.bitmap.getWidth() / 2,
		// (int) (680 * GameConfig.f_zoomy + y)
		// - (anjianall ? 0.2f : 0f)
		// * word_key_accept_2.bitmap.getHeight() / 2,
		// anjianall ? 1.2f : 1.0f, anjianall ? 1.2f : 1.0f, 255, 0,
		// 0, 0);
		// }

		// 标题
		word_title_message.drawBitmap(canvas, null,
				(int) (182 * GameConfig.f_zoomx),
				(int) (107 * GameConfig.f_zoomy + y),
				(int) (172 * GameConfig.f_zoomx), -1);

		if (_library != null) {
//			int list_x = (int) ((45 + 16) * GameConfig.f_zoomx);
//			int list_y = (int) ((148 + 12) * GameConfig.f_zoomy);
//			for (int i = 0; i < v_message.size(); ++i) {
//				int listy = list_y + (int) (80 * i * GameConfig.f_zoomy);
//				// MessageItem item = new MessageItem(i, "蔬菜"+i);
//				v_message.get(i).paint(canvas, list_x, listy, paint);
//			}
			if (_library.size()>3 && _library.get(3).itemId == 1 && move_Y >= 0) {
			} else {
				arrow[0].drawBitmap(canvas, arrow[0].bitmap, 253 * GameConfig.f_zoomx, 145 * GameConfig.f_zoomy,null);	
			}
			if (_library.size() <= 18 || (_library.get(_library.size() - 3 - 1).itemId == itemLibrary.size() && move_Y <= 0)) {
			} else {
				arrow[1].drawBitmap(canvas, arrow[1].bitmap, 253 * GameConfig.f_zoomx, 700 * GameConfig.f_zoomy,null);
			}
			canvas.save();
			canvas.clipRect(_library_x, _library_y + _library_h_s/2, _library_x + _library_w, _library_y + _library_h-_library_h_s/2);
			for (int i=0; i<_library.size(); i++) {
				float tempx = _library_x + _library_w_s / 2 + (i % 3) * (_library_w_s + noCardWidth);
				float tempy = _library_y + _library_h_s / 2 + (i/3 - 1) * (noCardHeight + _library_h_s);
				if (i%3==0 && _library.get(i).itemId != -1) {
					_library.get(i).paint(canvas, (int)(tempx), (int)(move_Y + tempy+(int)(10*GameConfig.f_zoomy)), paint);
				}
			}
			canvas.restore();
			
			

		} else if (!FacebookOperation.isOpenNet && !FacebookOperation.getFacebook().getFriendNet()) {
			// 显示loading
			float tempx = ((float) ((GameConfig.GameScreen_Width - loading[0].bitmap.getWidth()) / 2));
			float tempy = y + ((float) ((GameConfig.GameScreen_Height - loading[0].bitmap.getHeight()) / 2));

			loading[tempi].drawBitmap(canvas, loading[tempi].bitmap, tempx, tempy, null);

		} 
		if(FacebookOperation.isOpenNet && _library==null) { // 没网络
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
		if (_library == null
				&& !FacebookOperation.getFacebook().getFriendNet()) {
			// tempi = 0;
			if (GameConfig.i_coke % 2 == 0) {
				tempi++;
				if (tempi == 9)
					tempi = 0;
			}
		}else{

			
			if (isCorrectCardMove) {
				move_Y += correctCard_move;
				if (move_Y <= -(noCardHeight + _library_h_s)) {
					 
					if (_library.get(_library.size()-4).itemId != itemLibrary.size())
						addDownCard();
				 
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				} else if (move_Y > -Math.abs(correctCard_move) && move_Y < Math.abs(correctCard_move)) {
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				} else if (move_Y >=noCardWidth + _library_h_s) {
					if (_library.get(3).itemId != 1)
						addUpCard();
					move_Y = 0;
					correctCard_move = Math.abs(correctCard_move);
					isCorrectCardMove = false;
				}
			}
			
			
		
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
						if (_library.get(j+2).itemId < itemLibrary.size()) {
							_library.set(j+0, itemLibrary.get(_library.get(j).itemId + 2));
							_library.set(j+1, itemLibrary.get(_library.get(j+1).itemId + 2));
							_library.set(j+2, itemLibrary.get(_library.get(j+2).itemId + 2));
						} else {
							_library.set(j, new MessageItem(-1, -1, -1, -1, "", null, "", -1));										
							_library.set(j+1, new MessageItem(-1, -1, -1, -1, "", null, "", -1));										
							_library.set(j+2, new MessageItem(-1, -1, -1, -1, "", null, "", -1));																
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
				if (_library.get(j-1).itemId == 3) {
					_library.set(j-1, new MessageItem(-1, -1, -1, -1, "", null, "", -1));						
					_library.set(j-2, new MessageItem(-1, -1, -1, -1, "", null, "", -1));						
					_library.set(j-3, new MessageItem(-1, -1, -1, -1, "", null, "", -1));										
				} else {
						_library.set(j-1, itemLibrary.get(_library.get(j-1).itemId - 4));
						_library.set(j-2, itemLibrary.get(_library.get(j-2).itemId - 4));
						_library.set(j-3, itemLibrary.get(_library.get(j-3).itemId - 4));				
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
		if (gs == null && word_key_accept_2 == null)
			return;
		GameImage.delImage(gs.bitmap);
		gs.bitmap = null;
		gs = null;

		GameImage.delImage(word_key_accept_2.bitmap);
		if (word_key_accept_2.bitmap != null)
			word_key_accept_2.bitmap = null;
		word_key_accept_2 = null;

		GameImage.delImage(word_title_message.bitmap);
		if (word_title_message.bitmap != null)
			word_title_message.bitmap = null;
		word_title_message = null;

		if (loading!=null ) {
			GameImage.delImageArray(loading);
		}
		loading = null;
		paint = null;
		fontFace = null;
		
		if(arrow!=null){
			for (int i=0; i<arrow.length; i++) {
				GameImage.delImage(arrow[i].bitmap);
				arrow[i].bitmap = null;
			}
		}
		arrow = null;
 
		if(itemLibrary!=null)
			for (int i = 0; i < itemLibrary.size(); ++i) {
				itemLibrary.get(i).delete();
				itemLibrary.remove(i);
			}
		itemLibrary = null;
		FacebookOperation.getFacebook().setFriendIcon(null);
		FacebookOperation.getFacebook().setFriendInt(null);
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

			if (_library == null)
				return;
			if (ExternalMethods.CollisionTest(x, y, 
					_library_x, _library_y,
					_library_x + _library_w, _library_y + _library_h)) {
				oldY = y;
				ismove = true;
			}
			// if (ExternalMethods.CollisionTest(
			// x,
			// y,
			// 168
			// * GameConfig.f_zoomx
			// - GameStaticImage.s_share_ui_button_01[0].bitmap
			// .getWidth() / 2 * 0.2f,
			// 663
			// * GameConfig.f_zoomy
			// - GameStaticImage.s_share_ui_button_01[0].bitmap
			// .getHeight() / 2 * 0.2f,
			// 168
			// * GameConfig.f_zoomx
			// + GameStaticImage.s_share_ui_button_01[0].bitmap
			// .getWidth() * 1.2f,
			// 663
			// * GameConfig.f_zoomy
			// + GameStaticImage.s_share_ui_button_01[0].bitmap
			// .getHeight() * 1.2f)) {
			// anjianall = true;
			// }

			for (int i = 0; i < _library.size(); ++i) {
				int index = _library.get(i).onTouchEvent(event);
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

			if (_library == null)
				return;
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
			// if (anjianall
			// && ExternalMethods
			// .CollisionTest(
			// x,
			// y,
			// 168
			// * GameConfig.f_zoomx
			// - GameStaticImage.s_share_ui_button_01[0].bitmap
			// .getWidth() / 2 * 0.2f,
			// 663
			// * GameConfig.f_zoomy
			// - GameStaticImage.s_share_ui_button_01[0].bitmap
			// .getHeight() / 2 * 0.2f,
			// 168
			// * GameConfig.f_zoomx
			// + GameStaticImage.s_share_ui_button_01[0].bitmap
			// .getWidth() * 1.2f,
			// 663
			// * GameConfig.f_zoomy
			// + GameStaticImage.s_share_ui_button_01[0].bitmap
			// .getHeight() * 1.2f)) {
			// anjianall = false;
			// System.out.println("<><>");
			// }
			for (int i = 0; i < _library.size(); ++i) {
				int index = _library.get(i).onTouchEvent(event);
				if (index != -1) { // 点击了
					int status = _library.get(i).status;
					// if (status == UserRequest.REQPS) {// 回复请求体力
					// long messageID = v_message.get(i).messageID;
					// UserRequest.getUser().reqBackUser(messageID);
					// v_message.get(i).setInvalid(true);
					// v_message.remove(i);
					// } else if (status == UserRequest.ACCEPT) {// 接受请求
					// // v_message.add(new MessageItem(i, user.getid_server(),
					// // user.getName(), user.getIcon(), data.getS_msg()));
					// // }2975047189369061377
					// }
					int type = _library.get(i).type;
					long messageID = _library.get(i).messageID;
					if (type == UserRequest.REQPS) { // 回复请求体力
						if (status == 1) { // 显示
							UserRequest.getUser().reqBackUser(messageID);
						} else if (status == 2) {// 删除
							// MessageModule.message.add(new
							// MessageData(l_uid[i], l_fromId[i], i_status[i],
							// "请求体力删除"+s_msg[i]));
						}
						message.remove(""+_library.get(i).messageID);
						_library.get(i).setInvalid(true);
						_library.get(i).deleteI();
						_library.remove(i);
					} else if (type == UserRequest.ACCEPT) {// 回复接受请求
						if (status == 1) { // 显示
							UserRequest.getUser().reqBackUser(messageID);
							message.remove(""+_library.get(i).messageID);
							_library.get(i).setInvalid(true);
							_library.get(i).deleteI();
							_library.remove(i);
							// 这里会的一颗爱心体力
							VeggiesData.addHeart(1);
						} else if (status == 2) {// 删除
						// MessageModule.message.add(new MessageData(l_uid[i],
						// l_fromId[i], i_status[i], "接受请求删除"+s_msg[i]));
						}
					}

					break;
				}
			}

			anjianclose = false;
		}else if(event.getAction() == MotionEvent.ACTION_MOVE){
			if (ismove) {
				if (isCorrectCardMove) {
					isCorrectCardMove = false;	//纠正移动关闭等移动释放后继续纠正
					correctCard_move = Math.abs(correctCard_move);
				}
				int tempY = (int) event.getY();
				
				move_Y+=tempY-oldY;
				
				oldY = (int) event.getY();	
				
				if (move_Y > _library_h_s && _library.get(0).itemId == -1) {
					move_Y = _library_h_s;
				} else if ((move_Y < -_library_h_s && _library.size() < 18 ) || (move_Y < -_library_h_s && _library.get(18).itemId == -1)) {
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
	@Override
	public void onIcon(FaceBookPlayer playerIcon) {
		// TODO Auto-generated method stub
		if(_library!=null)
			for(int i=0;i<_library.size();++i){
				MessageItem item = _library.get(i);
				if(item.name.equals(playerIcon.getName())){
					Bitmap temp = FBInterface.allIconMap.get(playerIcon.getId_facebook());//user.getIcon();
					item.setIcon(temp);
				}
			}
	}
}// end class
