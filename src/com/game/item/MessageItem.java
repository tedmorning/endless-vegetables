package com.game.item;

import com.endlessvegetables2.ui.GameStaticImage;
import com.endlessvegetables2.ui.HeartRechargeModule;
import com.facebook.UserRequest;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.TextBox;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
/**
 *消息的item
 * @author Administrator
 *
 */
public class MessageItem {

	public long messageID;
	public long uid;
	public int list_x, list_y;
	public int itemId = 0;
	public int status;
	public int type;
	public long time;
	public String s_time;
    public boolean anjianInvalid; //按键无效
    
    
	private static Sprite word_key_accept;
	private static Sprite share_ui_line;
	private static Sprite word_key_ask_1;
	private static Sprite word_key_ask_2;
	private static Sprite share_ui_photo_04;
	private static Sprite word_key_send_1;
	private static Sprite s_word_num_07[];
	private static Sprite s_share_ui_photo_03;
	private static Sprite s_share_ui_photo_01;
	
	private Sprite share_ui_photo_01;
	private Sprite share_ui_button_02;
    public TextBox name;
    private TextBox t_content;
    private boolean anjianget;
    private boolean isHeart;
	private long currenttime;
	
	
	public MessageItem(int _type, int _itemId, long _uid, long _messageid, String _name, Bitmap icon, String _content, int _status){
		itemId = _itemId;
		messageID = _messageid;
		uid = _uid;
		status = _status;
		type = _type;
		if(word_key_accept==null)
			word_key_accept = new Sprite(GameImage.getImage(GameStaticImage.word_key_accept));

		if(share_ui_line==null)
			share_ui_line = new Sprite(GameImage.getImage(GameStaticImage.share_ui_line));
		
		if (share_ui_photo_04 == null)
			share_ui_photo_04 = new Sprite(
					GameImage.getImage(GameStaticImage.share_ui_photo_04));

		if(word_key_send_1 == null)
			word_key_send_1 = new Sprite(
					GameImage.getImage(GameStaticImage.word_key_send_1));
		if(s_share_ui_photo_01 == null)
			s_share_ui_photo_01 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_photo_01));
		
		
		int width = share_ui_photo_04.bitmap.getWidth();
		int height = share_ui_photo_04.bitmap.getHeight();
		// 图片缩放
		Bitmap temp =	icon;
		if(icon!=null){
			temp = GameImage.zoomImage(temp, width - 6
					* GameConfig.f_zoomx, height - 6
					* GameConfig.f_zoomy);
			share_ui_photo_01 = new Sprite(temp);
		}

		name = new TextBox();
		name.setTextAlign(TextBox.LEFT);
		name.setString(_name);
		name.setTextSize((int)(22*GameConfig.f_zoom));
		name.setDefaultColor(0xffBD6D18);
		name.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)name.height());
		
		t_content = new TextBox();
		t_content.setTextAlign(TextBox.LEFT);
		t_content.setString(_content);
		t_content.setTextSize((int)(18*GameConfig.f_zoom));
		t_content.setDefaultColor(0xffBD6D18);
		t_content.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)t_content.height());
		
	}
	
	public MessageItem( boolean heart,int _type, long _uid, long _messageid, Bitmap icon, String _name, String _content){
		messageID = _messageid;
		isHeart = heart;
		uid = _uid;
		type = _type;
		name = new TextBox();
		name.setTextAlign(TextBox.LEFT);
		name.setString(_name);
		name.setTextSize((int)(22*GameConfig.f_zoom));
		name.setDefaultColor(0xffBD6D18);
		name.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)name.height());
		
		t_content = new TextBox();
		t_content.setTextAlign(TextBox.LEFT);
		t_content.setString(_content);
		t_content.setTextSize((int)(18*GameConfig.f_zoom));
		t_content.setDefaultColor(0xffBD6D18);
		t_content.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)t_content.height());
		
		if(word_key_ask_1==null)
			word_key_ask_1 = new Sprite(GameImage.getImage(GameStaticImage.word_key_ask_1));
		
		if(word_key_ask_2==null)
		word_key_ask_2 = new Sprite(GameImage.getImage(GameStaticImage.word_key_ask_1));

		if(share_ui_line==null)
			share_ui_line = new Sprite(GameImage.getImage(GameStaticImage.share_ui_line));
		
		if(share_ui_button_02==null)
			share_ui_button_02 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_02));
		
		if (share_ui_photo_04 == null)
			share_ui_photo_04 = new Sprite(
					GameImage.getImage(GameStaticImage.share_ui_photo_04));

		if(s_word_num_07 ==null){
			s_word_num_07 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_07, 11, 1, GameImage.Sort_line);
		}
		
		if(s_share_ui_photo_03 == null){
			s_share_ui_photo_03 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_photo_03));
		}
		
		if(s_share_ui_photo_01 == null)
			s_share_ui_photo_01 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_photo_01));
		
				// 图片缩放
		Bitmap temp =	icon;
		if(icon != null){
			int width = share_ui_photo_04.bitmap.getWidth();
			int height = share_ui_photo_04.bitmap.getHeight();
			temp = GameImage.zoomImage(temp, width - 6
					* GameConfig.f_zoomx, height - 6
					* GameConfig.f_zoomy);
			share_ui_photo_01 = new Sprite(temp);
		}

	}
	
	public void setIcon(Bitmap icon){
		if(icon != null){
			int width = share_ui_photo_04.bitmap.getWidth();
			int height = share_ui_photo_04.bitmap.getHeight();
			Bitmap temp = GameImage.zoomImage(icon, width - 6
					* GameConfig.f_zoomx, height - 6
					* GameConfig.f_zoomy);
			share_ui_photo_01 = new Sprite(temp);
		}

	}
	
	public void setInvalid(boolean _invalib){
		anjianInvalid = _invalib;
	}
	public void paint(Canvas canvas, int list_x, int list_y, Paint paint){
		this.list_x = list_x;
		this. list_y= list_y;
		if(share_ui_photo_01!=null && share_ui_photo_01.bitmap!=null)
			share_ui_photo_01.drawBitmap(canvas, share_ui_photo_01.bitmap,
					(int)(list_x)+3 * GameConfig.f_zoomx,
					(int)(list_y)+3 * GameConfig.f_zoomy, null);
		else
			s_share_ui_photo_01.drawBitmap(canvas, s_share_ui_photo_01.bitmap,
					(int)(list_x)+3 * GameConfig.f_zoomx,
					(int)(list_y)+3 * GameConfig.f_zoomy, null);
		 
		share_ui_photo_04.drawBitmap(canvas,
				share_ui_photo_04.bitmap, 
				(int)(list_x),
				(int)(list_y), null);
		
		//名字
		name.paintText(canvas, 
				(int)(list_x+70 * GameConfig.f_zoomx),
				(int)(list_y+9 * GameConfig.f_zoomy));
		//内容
		t_content.paintText(canvas, 
				list_x+(int)(70 * GameConfig.f_zoomx),
				list_y+(int)(36 * GameConfig.f_zoomy));
		//按钮
		GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas,
				list_x+275 * GameConfig.f_zoomx
				- GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()
				/ 2 * (anjianget ? 0.2f : 0f), 
				list_y+8 * GameConfig.f_zoomy
				- GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()
				/ 2 * (anjianget ? 0.2f : 0f), anjianget ? 1.2f
				: 1f, anjianget ? 1.2f : 1f, 255, 0, 0, 0);
//		if(!anjianInvalid){
		if(!isHeart && !anjianInvalid){
			if (anjianget) {
				GameStaticImage.s_share_ui_button_01[1].drawBitmap(
						canvas, null, 
						(int)(list_x+275 * GameConfig.f_zoomx + 
						(GameStaticImage.s_share_ui_button_01[0].bitmap
						.getWidth()/2 - GameStaticImage.s_share_ui_button_01
						[1].bitmap.getWidth()/2)), 
						(int)(list_y+8 * GameConfig.f_zoomy + (GameStaticImage.
						s_share_ui_button_01[0].bitmap
						.getHeight()/2 - GameStaticImage.
						s_share_ui_button_01[1].bitmap.getHeight()/2)), 
						(int)(139 * GameConfig.f_zoomx * 1.2f), -1);
			} else {				
				GameStaticImage.s_share_ui_button_01[0].drawBitmap(
						canvas, null, (int)(list_x+275 * GameConfig.f_zoomx), 
						(int)(list_y+8 * GameConfig.f_zoomy), 
						(int)(139 * GameConfig.f_zoomx), -1);
			}
		}else if(isHeart && anjianInvalid){
			share_ui_button_02.drawBitmap(
					canvas, null, (int)(list_x+275 * GameConfig.f_zoomx), 
					(int)(list_y+8 * GameConfig.f_zoomy), 
					(int)(139 * GameConfig.f_zoomx), -1);
		}
		if(!isHeart){
			if(type== UserRequest.ACCEPT && status == 1){
				 //绘制set按钮
				word_key_send_1.drawBitmap(canvas,(int) 
						(list_x+(275) * GameConfig.f_zoomx) - (anjianget?0.2f:0f) * 
						word_key_accept.bitmap.getWidth()/2, 
						(int) (list_y+(8+12) * GameConfig.f_zoomy ) - 
						(anjianget?0.2f:0f) * word_key_accept.bitmap.getHeight()/2, 
						anjianget?1.2f:1.0f, anjianget?1.2f:1.0f, 255, 0, 0, 0);
			}else{
				word_key_accept.drawBitmap(canvas,(int) 
						(list_x+(275) * GameConfig.f_zoomx) - (anjianget?0.2f:0f) * 
						word_key_accept.bitmap.getWidth()/2, 
						(int) (list_y+(8+12) * GameConfig.f_zoomy ) - 
						(anjianget?0.2f:0f) * word_key_accept.bitmap.getHeight()/2, 
						anjianget?1.2f:1.0f, anjianget?1.2f:1.0f, 255, 0, 0, 0);
			}
		}else{
			if(anjianInvalid){
				word_key_ask_2.drawBitmap(canvas,(int) 
						(list_x+(275+17) * GameConfig.f_zoomx) - (anjianget?0.2f:0f) * 
						word_key_ask_2.bitmap.getWidth()/2, 
						(int) (list_y+(8+12) * GameConfig.f_zoomy ) - 
						(anjianget?0.2f:0f) * word_key_ask_2.bitmap.getHeight()/2, 
						anjianget?1.2f:1.0f, anjianget?1.2f:1.0f, 255, 0, 0, 0);
			}else{
				word_key_ask_1.drawBitmap(canvas,(int) 
						(list_x+(275+17) * GameConfig.f_zoomx) - (anjianget?0.2f:0f) * 
						word_key_ask_1.bitmap.getWidth()/2, 
						(int) (list_y+(8+12) * GameConfig.f_zoomy ) - 
						(anjianget?0.2f:0f) * word_key_ask_1.bitmap.getHeight()/2, 
						anjianget?1.2f:1.0f, anjianget?1.2f:1.0f, 255, 0, 0, 0);
			}
			  
			if (anjianInvalid){
				s_share_ui_photo_03.drawBitmap(canvas,
						s_share_ui_photo_03.bitmap, 
						(int)(list_x),
						(int)(list_y), null);
			 if(anjianInvalid)
				s_word_num_07[0]
						.drawBitmap(
								canvas,
								s_word_num_07,
								(int) (list_x+63 * GameConfig.f_zoomx + (-1 + itemId)
										* (s_share_ui_photo_03.bitmap.getWidth()) + 2 * GameConfig.f_zoomx),
								(int) (list_y+20 * GameConfig.f_zoomy + (s_share_ui_photo_03.bitmap
										.getHeight() - s_word_num_07[0].bitmap
										.getHeight()) / 2),
								GameConfig.Char_num2, s_time,
								null, 0, 1f);
				 
			}
		}
		
//		}
		share_ui_line.drawBitmap(canvas, null,
				(int)(list_x+9 * GameConfig.f_zoomx),
				(int)(list_y+68* GameConfig.f_zoomy),
				(int) (395 * GameConfig.f_zoomx),
				 -1);
	}

	public void run(){
		currenttime = System.currentTimeMillis();
		String _time = "";
		long timeee = time;
		//timeee = timeee*1000;
	 
		long cd; 
		if (timeee>currenttime && timeee - currenttime  > 0) {
			anjianInvalid = true;
			_time = "";
			cd = (timeee - currenttime);
			long tempCD = cd / 1000;
			if (tempCD % 60 == 0)
				_time = "00";
			else if (tempCD % 60 < 10)
				_time = "0" + tempCD % 60;
			else
				_time = _time + tempCD % 60;
			tempCD = tempCD / 60;
			if (tempCD % 60 == 0)
				_time = "00:" + _time;
			else if (tempCD % 60 < 10)
				_time = "0" + tempCD % 60 + ":" + _time;
			else
				_time = tempCD % 60 + ":" + _time;
		} else {
			cd = 0;
			_time = "00:00";
			anjianInvalid = false;
		}
		s_time = _time;
//		_friends.get(i).cd = cd;
	
	}
	
	public void delete(){
		
		if(share_ui_photo_01!=null){
			GameImage.delImage(share_ui_photo_01.bitmap);
			if(share_ui_photo_01.bitmap!=null)
				share_ui_photo_01.bitmap = null;
			share_ui_photo_01 = null;
		}
		
		if(word_key_accept!=null){
			GameImage.delImage(word_key_accept.bitmap);
			if(word_key_accept.bitmap!=null)
				word_key_accept.bitmap = null;
			word_key_accept = null;
		}

		if(share_ui_line!=null){
			GameImage.delImage(share_ui_line.bitmap);
			if(share_ui_line.bitmap!=null)
				share_ui_line.bitmap = null;
			share_ui_line = null;
		}
		if(name!=null)
			name.Close();
		name = null;

		if(t_content!=null)
			t_content.Close();
		t_content = null;
	 
		if(word_key_ask_1!=null){
			GameImage.delImage(word_key_ask_1.bitmap);
			if(word_key_ask_1.bitmap!=null)
				word_key_ask_1.bitmap = null;
			word_key_ask_1 = null;
		}
		if(word_key_ask_2!=null){
			GameImage.delImage(word_key_ask_2.bitmap);
			if(word_key_ask_2.bitmap!=null)
				word_key_ask_2.bitmap = null;
			word_key_ask_2 = null;
		}
		if(share_ui_button_02!=null){
			GameImage.delImage(share_ui_button_02.bitmap);
			if(share_ui_button_02.bitmap!=null)
				share_ui_button_02.bitmap = null;
			share_ui_button_02 = null;
		}
		if(share_ui_photo_04!=null){
			GameImage.delImage(share_ui_photo_04.bitmap);
			if(share_ui_photo_04.bitmap!=null)
				share_ui_photo_04.bitmap = null;
			share_ui_photo_04 = null;
		}
		if(word_key_send_1!=null){
			GameImage.delImage(word_key_send_1.bitmap);
			if(word_key_send_1.bitmap!=null)
				word_key_send_1.bitmap = null;
			word_key_send_1 = null;
		}
	}
	
	public void deleteI(){
		if(share_ui_photo_01!=null){
			GameImage.delImage(share_ui_photo_01.bitmap);
			if(share_ui_photo_01.bitmap!=null)
				share_ui_photo_01.bitmap = null;
			share_ui_photo_01 = null;
		}
		
		if(share_ui_button_02!=null){
			GameImage.delImage(share_ui_button_02.bitmap);
			if(share_ui_button_02.bitmap!=null)
				share_ui_button_02.bitmap = null;
			share_ui_button_02 = null;
		}
		if(s_word_num_07!=null){
			GameImage.delImageArray(s_word_num_07);
		}
		s_word_num_07 = null;
		
		
		if(s_share_ui_photo_03!=null){
			GameImage.delImage(s_share_ui_photo_03.bitmap);
		if(s_share_ui_photo_03.bitmap!=null)
			s_share_ui_photo_03.bitmap = null;
		}
		if(s_share_ui_photo_01!=null){
			GameImage.delImage(s_share_ui_photo_01.bitmap);
		if(s_share_ui_photo_01.bitmap!=null)
			s_share_ui_photo_01.bitmap = null;
		}
		
		
		share_ui_button_02 = null;
		
		if(name!=null)
			name.Close();
		name = null;

		if(t_content!=null)
			t_content.Close();
		t_content = null;
	}
	
	public int onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if(anjianInvalid)return -1;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			
			int get_x = (int)(list_x+275 * GameConfig.f_zoomx
			- GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()
			/ 2 * (anjianget ? 0.2f : 0f));
			int get_y = (int)(list_y+8 * GameConfig.f_zoomy
			- GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()
			/ 2 * (anjianget ? 0.2f : 0f));
			if( ExternalMethods.CollisionTest(x, y, 
					get_x, get_y,
					get_x + GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()*1.2f, 
					get_y + GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()*1.2f)){
				anjianget = true;
			}
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			int get_x = (int)(list_x+275 * GameConfig.f_zoomx
					- GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()
					/ 2 * (anjianget ? 0.2f : 0f));
					int get_y = (int)(list_y+8 * GameConfig.f_zoomy
					- GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()
					/ 2 * (anjianget ? 0.2f : 0f));
					if( ExternalMethods.CollisionTest(x, y, 
							get_x, get_y,
							get_x + GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()*1.2f, 
							get_y + GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()*1.2f)){
						anjianget = false;
						return itemId;
					}
					anjianget = false;
		}
		return -1;
	}
	
}//end class
