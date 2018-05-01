package com.endlessvegetables2.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.kokatlaruruxi.wy.R;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameData;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.TextBox;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;
import android.text.method.MovementMethod;
import android.view.MotionEvent;

/**
 * 弹出框
 * @author Administrator
 *
 */
public abstract class PopUp{
	
	public final static byte AWARD = 0; //奖励界面
	public final static byte GOOGS = 1; //物品不足界面
	public final static byte UNLOCK = 2; //解锁
	public final static byte EVERYDAY = 3; //每日奖励
	public final static byte GOOGS_BUTTON = 4; //物品不足button加图片
	
	//----------------触摸返回
	//关闭
	public final static byte onTouch_close= 0;
	//奖励界面退出
	public final static byte onTouch_awardExit = 1;
	//物品不足
	public final static byte onTouch_googsExit = 2;
	//解锁星星
	public final static byte onTouch_unlockExit_SART = 3;
	//解锁钻石
	public final static byte onTouch_unlockExit_GET = 6;
	//每日奖励
	public final static byte onTouch_everyDayExit = 4;
	//奖励界面分享
	public final static byte onTouch_share = 5;
	
	
	//解锁需求数量    顺序是 星星 钻石
	public final static int UNLOCK_NUMBER[] = {30, 30}; 

	int x, y, w, h;
	private Sprite s_share_ui_back_01;
	private Sprite s_share_ui_back_02;
	private Sprite s_share_ui_back_02_2;
	private Sprite s_share_ui_close;
	private Sprite gs;
	
	private Sprite interface_ui_ribbon_01 ;
	private Sprite interface_ui_shine;
	private Sprite word_title_reward;
	private Sprite word_title_reward_2;
	
	private Sprite share_ui_back_03;
	private Sprite share_ui_back_06;
	private Sprite icon;
	private Sprite word_key_get_2;
	private Sprite icon_unlock; //解锁要两张
	public  Sprite[] s_word_num_04;
	public  Sprite[] reward_card;
	public  Sprite[] reward_title;
	public  Sprite[] word_reward_num;
	public  Sprite[] word_number;
	public  int widthRect = 0;
	int index = -1;
	
	private TextBox number;
	private TextBox nulock_1;
	 
	boolean anjianbutton[];
	private Sprite s_share_ui_button_01[];
	private Sprite share_ui_button_text[];
	private int button[][] = {{58, 253},{329, 329} };
	private int button_text[][] = { {94, 270}, {343, 343}};
	private int offexbg[];
	private int button_un[][] = {{68, 277},{306, 306} };
	private int button_text_un[][] = { {90, 299}, {311, 313}};
	private int un_bg[][]= {{47, 257},{109, 109}};
	int word_numxy[][] = { {139, 348}, {320, 320} };
	
	int bg_x[] = {133, 291, 70, 207, 344};
	int bg_y[] = {239, 239, 421, 421, 421};
	

	int everydadyNumber[] = {2, 4, 6, 8, 10};
	boolean isEveryDay[] = {false, false, false, false, false};
	
	boolean anjianclose;
	//类型
	byte type;
	Paint paint = new Paint();
	/**button上的图标*/
	String button_name;
	/**button上图标后面的字符*/
	String button_number;
	//解锁需要两个参数
	public PopUp(String text){
		if(text!=null && !text.equals("")){
			number = new TextBox();
			number.setTextAlign(TextBox.LEFT);
			number.setString(text);
			number.setTextSize((int)(22*GameConfig.f_zoom));
			number.setDefaultColor(0xffBD6D18);
			number.setBoxSize((int)(180 * GameConfig.f_zoomx), (int)number.height());
		}
	}
	int number_neirong = 0;
	boolean temp_isclose = false;
	/***
	 * 
	 * number_text    icon 后面显示加多少的数值图片路劲
	 * number_neirong    icon 后面显示加多少的数值
	 * boolean isclose 是否按键ok关掉对话框
	 * */
	public PopUp(String _text, String _number,  String text, String number_text, int number_neirong, boolean isclose){
		button_number = _number;
		button_name = _text;
		if(text!=null && !text.equals("")){
			number = new TextBox();
			number.setTextAlign(TextBox.LEFT);
			number.setString(text);
			number.setTextSize((int)(22*GameConfig.f_zoom));
			number.setDefaultColor(0xffBD6D18);
			number.setBoxSize((int)(180 * GameConfig.f_zoomx), (int)number.height());
		}
		
		if(number_text!=null && !number_text.equals("")){
			word_number = GameImage.getAutoSizecutSprite(number_text, 11, 1, GameImage.Sort_line);
			this.number_neirong = number_neirong;
		}
//		temp_isclose = isclose;
		temp_isclose = false;
	}
	 
	public void init(byte _type, String icon_file) {
		type = _type;
		offexbg = new int[4];
		if(type == AWARD){
			x = 28;
			y = 148;
			w = 476;
			h = 429;
			int award[] = {20, 63, 33, 80};
			for(int i=0;i<offexbg.length;++i){
				offexbg[i] = award[i];
			}
		}else if(type == GOOGS || type == GOOGS_BUTTON){
			x = 28;
			y = 216;
			w = 476;
			h = 316;
			int goods[] = {17, 17, 33, 34};
			for(int i=0;i<offexbg.length;++i){
				offexbg[i] = goods[i];
			}
			number.setTextAlign(TextBox.HCENTER);
			number.setBoxSize((int)((w-40) * GameConfig.f_zoomx), (int)number.height());
		}else if(type == UNLOCK){
			x = 28;
			y = 148;
			w = 476;
			h = 429;
			int unlock[] = {17, 63, 33, 80};
			for(int i=0;i<offexbg.length;++i){
				offexbg[i] = unlock[i];
			}
			String temp = LangUtil.getLangString(LangDefineClient.UNLOCK_GEMS);
			temp = temp.replace("X", ""+UNLOCK_NUMBER[1]);
			nulock_1 = new TextBox();
			nulock_1.setTextAlign(TextBox.LEFT);
			nulock_1.setString(temp);
			nulock_1.setTextSize((int)(22*GameConfig.f_zoom));
			nulock_1.setDefaultColor(0xffBD6D18);
			nulock_1.setBoxSize((int)(160 * GameConfig.f_zoomx), (int)nulock_1.height());
			number.setBoxSize((int)(160 * GameConfig.f_zoomx), (int)number.height());
		}else if(type == EVERYDAY){
			paint.setTypeface(Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF"));
			paint.setTextSize(20 * GameConfig.f_zoomx);
			x = 28;
			y = 148;
			w = 476;
			h = 478;
			int everyday[] = {20, 63, 34, 78}; //大坐标减小坐标 获取相对坐标
			for(int i=0;i<offexbg.length;++i){
				offexbg[i] = everyday[i];
			}
			for(int i=0;i<bg_x.length;++i){
				bg_x[i]  = bg_x[i] - x;
				bg_y[i]  = bg_y[i] - y;
			}
			index = -1;
		}
		
		
		gs = new Sprite();
		if(s_share_ui_back_01==null && type != EVERYDAY)
			s_share_ui_back_01 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
		else{
			s_share_ui_back_01 = new Sprite(GameImage.getImage(GameStaticImage.interface_reward_back));
		}
			
		
		if(s_share_ui_back_02==null && type != EVERYDAY)
			s_share_ui_back_02 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));

		if(s_share_ui_back_02_2==null && type != EVERYDAY)
			s_share_ui_back_02_2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));

		if(s_share_ui_close==null && type != EVERYDAY)
			s_share_ui_close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
		
		if(type == AWARD){ //奖励界面的标题
			infoAward(icon_file);
		}else if(type == GOOGS){ //物品不足
			infoGoods(icon_file);
		}else if(type == UNLOCK){
			infoUnlock();
		}else if(type == EVERYDAY){ //每日登陆
			infoEveryday();
		}else if(type == GOOGS_BUTTON){//物品不足button自定义
			infoGoodsButton(icon_file);
		}
	}
	
	public void draw(Canvas canvas, boolean isShow){
		if(isShow)
			gs.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
	
		if(s_share_ui_back_01!=null)
			s_share_ui_back_01.drawBitmap(canvas, null, (int)(x * GameConfig.f_zoomx), (int)(y * GameConfig.f_zoomy), (int)(w * GameConfig.f_zoomx), (int)(h * GameConfig.f_zoomy), -1);
		 
		if(s_share_ui_back_02!=null)
			s_share_ui_back_02.drawBitmap(canvas, null, (int)((x+offexbg[0]) * GameConfig.f_zoomx), (int)((y+offexbg[1]) * GameConfig.f_zoomy), (int)((w-offexbg[2]) * GameConfig.f_zoomx), (int)((h-offexbg[3]) * GameConfig.f_zoomy), -1);

		if(s_share_ui_back_02_2!=null)
			s_share_ui_back_02_2.drawBitmap(canvas, null, (int)((x+offexbg[0]) * GameConfig.f_zoomx), (int)((y+offexbg[1]) * GameConfig.f_zoomy), (int)((w-offexbg[2]) * GameConfig.f_zoomx), (int)((h-offexbg[3]) * GameConfig.f_zoomy), -1);
		
		if(type != EVERYDAY)
			s_share_ui_close.drawBitmap(canvas, (w-x)* GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), (y-9) * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
    	
		if(type == AWARD){ //奖励界面
			drawAward(canvas);
		}else if(type == GOOGS){
			drawGoods(canvas);
		}else if(type == UNLOCK){
			drawUnlock(canvas);
		}else if(type == EVERYDAY){
			drawEveryday(canvas);
		}else if(type == GOOGS_BUTTON){
			drawGoodsButton(canvas);
		}
		
    };
    
    /**
     * 每日奖励
     */
    private void infoEveryday(){ 
//    	if(GameStaticImage.s_interface_ui_ribbon_01==null)
//    		GameStaticImage.s_interface_ui_ribbon_01 = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_ribbon_01));
//    	if(GameStaticImage.s_interface_ui_shine==null)
//    		GameStaticImage.s_interface_ui_shine = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_shine));
//    	if(word_title_reward == null)
//			word_title_reward = new Sprite(GameImage.getImage(GameStaticImage.word_title_reward));
    	if(word_title_reward == null)
    		word_title_reward_2 = new Sprite(GameImage.getImage(GameStaticImage.word_title_reward_2));
		
    	
    	reward_card = new Sprite[4];
    	reward_card[0] =  new Sprite(GameImage.getImage(GameStaticImage.interface_reward_card_01));
    	reward_card[1] =  new Sprite(GameImage.getImage(GameStaticImage.interface_reward_card_02));
    	reward_card[2] =  new Sprite(GameImage.getImage(GameStaticImage.interface_reward_card_03));
    	reward_card[3] =  new Sprite(GameImage.getImage(GameStaticImage.interface_ui_tick));
    	reward_title = new Sprite[5];
    	reward_title[0] =  new Sprite(GameImage.getImage(GameStaticImage.word_title_first));
    	reward_title[1] =  new Sprite(GameImage.getImage(GameStaticImage.word_title_second));
    	reward_title[2] =  new Sprite(GameImage.getImage(GameStaticImage.word_title_third));
    	reward_title[3] =  new Sprite(GameImage.getImage(GameStaticImage.word_title_fourth));
    	reward_title[4] =  new Sprite(GameImage.getImage(GameStaticImage.word__title_fifth));

    	widthRect = -reward_card[3].bitmap.getWidth();
    	
    	word_reward_num = GameImage.getAutoSizecutSprite(GameStaticImage.word_reward_num, 10, 1, GameImage.Sort_line);
    
    	anjianbutton = new boolean[1];
    	 
    	s_share_ui_button_01 = new Sprite[2];
		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(GameStaticImage.interface_reward_button));
		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(GameStaticImage.interface_reward_button));
    	
		share_ui_button_text = new Sprite[1];
		share_ui_button_text[0] = new Sprite(GameImage.getImage(GameStaticImage.word_key_get_2));
		
    	paint.setColor(Color.WHITE);
    	for(int i=0;i<5;++i){
    		if(VeggiesData.everyDay>i)
    			isEveryDay[i] = true;
    		else
    			isEveryDay[i] = false;
    	}
    }
    
    private void drawEveryday(Canvas canvas){//
    	 
//    	GameStaticImage.s_interface_ui_ribbon_01.drawBitmap(canvas, GameStaticImage.s_interface_ui_ribbon_01.bitmap, (int)((x+40) * GameConfig.f_zoomx), (int)((y-34) * GameConfig.f_zoomy), null);
    	word_title_reward_2.drawBitmap(canvas, word_title_reward_2.bitmap, (int)((x+40) * GameConfig.f_zoomx), (int)((y-34) * GameConfig.f_zoomy), null);
//    	GameStaticImage.s_interface_ui_shine.drawBitmap(canvas, GameStaticImage.s_interface_ui_shine.bitmap, (int)((x+127) * GameConfig.f_zoomx), (int)((y+9) * GameConfig.f_zoomy), null);
    	int  _frist = -1;
    	for(int i=0;i<bg_x.length;++i){
    		reward_title[i].drawBitmap(canvas,reward_title[i].bitmap,  (int)((x +bg_x[i]) * GameConfig.f_zoomx)+((reward_card[0].bitmap.getWidth() - reward_title[i].bitmap.getWidth() )/2), (int)((bg_y[i]+ y) * GameConfig.f_zoomy )- reward_title[i].bitmap.getHeight(), null);
    		if(_frist == -1 && !isEveryDay[i]){
    			_frist = i;
    		}
//            if(i<=1){
    		reward_card[1].drawBitmap(canvas,reward_card[1].bitmap, (int)((x + bg_x[i]) * GameConfig.f_zoomx), (int)((bg_y[i]+ y) * GameConfig.f_zoomy), null);
//    		}else{
//    			reward_card[2].drawBitmap(canvas,reward_card[2].bitmap, (int)((x + bg_x[i]) * GameConfig.f_zoomx), (int)((bg_y[i]+ y) * GameConfig.f_zoomy), null);
//    		}
    		if(_frist==i){
    			reward_card[2].drawBitmap(canvas,reward_card[2].bitmap, (int)((x + bg_x[i]) * GameConfig.f_zoomx), (int)((bg_y[i]+ y) * GameConfig.f_zoomy), null);
//        		
    		}
            if(isEveryDay[i]){ //以领取
    			reward_card[0].drawBitmap(canvas,reward_card[0].bitmap,  (int)((x +bg_x[i]) * GameConfig.f_zoomx), (int)((bg_y[i]+ y) * GameConfig.f_zoomy ), null);
    			
    			int ui_tick_x = (int)((x +bg_x[i]) * GameConfig.f_zoomx)+((reward_card[0].bitmap.getWidth() - reward_card[3].bitmap.getWidth() )/2);
    			int ui_tick_y =  (int)((bg_y[i]+ y) * GameConfig.f_zoomy )+(((reward_card[0].bitmap.getHeight() - reward_card[3].bitmap.getHeight() )))-(int)(5* GameConfig.f_zoomy);
    			
    			if(index==i && widthRect<0){
    				canvas.save();
        			canvas.clipRect(ui_tick_x, ui_tick_y, ui_tick_x+reward_card[3].bitmap.getWidth()+widthRect, ui_tick_y+reward_card[3].bitmap.getHeight());
        			reward_card[3].drawBitmap(canvas,reward_card[3].bitmap,  ui_tick_x, ui_tick_y, null);
        			canvas.restore();
    			}else{
    				reward_card[3].drawBitmap(canvas,reward_card[3].bitmap,  ui_tick_x, ui_tick_y, null);
    			}
    		}
    		if(!isEveryDay[i]){
    			String number = ""+everydadyNumber[i];
    			int width = word_reward_num[0].bitmap.getWidth() * (number.length())+(number.length()-1);
    			word_reward_num[0].drawBitmap(canvas, word_reward_num, (int)((x+bg_x[i] +20) * GameConfig.f_zoomx)+(int)((79* GameConfig.f_zoomx - width)/2), (int)((87+bg_y[i]+ y) * GameConfig.f_zoomy ), GameConfig.Char_num1, number, null, -2,1);
//    			canvas.drawText(""+everydadyNumber[i], (int)((x+bg_x[i] +20) * GameConfig.f_zoomx)+(int)((79* GameConfig.f_zoomx - paint.measureText(""+everydadyNumber[i]))/2),  (int)((86+bg_y[i]+ y) * GameConfig.f_zoomy )+(int)((20 * GameConfig.f_zoomx)), paint);
    		}
    	}
    	
    	int button_x = (int)(x * GameConfig.f_zoomx+((w * GameConfig.f_zoomx - s_share_ui_button_01[0].bitmap.getWidth())/2));
    	int button_y = (int)(y * GameConfig.f_zoomy+h * GameConfig.f_zoomy-s_share_ui_button_01[0].bitmap.getHeight()/2 - (18* GameConfig.f_zoomy));
       	int i = 0;
		if(s_share_ui_button_01[1]!=null)
			s_share_ui_button_01[1].drawBitmap(canvas, button_x - s_share_ui_button_01[0].bitmap.getWidth()/2*(anjianbutton[i]?0.2f:0f), button_y - s_share_ui_button_01[0].bitmap.getHeight()/2*(anjianbutton[i]?0.2f:0f), anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);
 
		if(share_ui_button_text!=null && share_ui_button_text[i]!=null)
			share_ui_button_text[i].drawBitmap(canvas, (int)(button_x +((s_share_ui_button_01[i].bitmap.getWidth() -share_ui_button_text[i].bitmap.getWidth()) )/2) - share_ui_button_text[i].bitmap.getWidth()/2*(anjianbutton[i]?0.2f:0f), (int)(button_y+((s_share_ui_button_01[i].bitmap.getHeight() -share_ui_button_text[i].bitmap.getHeight()) )/2) - share_ui_button_text[i].bitmap.getHeight()/2* (anjianbutton[i]?0.2f:0f), anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);
    	
    }
    /**
     * 奖励加载
     */
    private void infoAward(String icon_file){
    	anjianbutton = new boolean[2];
		if(interface_ui_ribbon_01 == null)
			interface_ui_ribbon_01 = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_ribbon_01));
		if(word_title_reward == null)
			word_title_reward = new Sprite(GameImage.getImage(GameStaticImage.word_title_reward));
		if(share_ui_back_03 == null)
			share_ui_back_03 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_03));
		if(share_ui_back_06 == null)
			share_ui_back_06 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_06));
		if(interface_ui_shine == null)
			interface_ui_shine = new Sprite(GameImage.getImage(GameStaticImage.interface_ui_shine));
		if(icon==null && icon_file!=null && !icon_file.equals(""))
			icon = new Sprite(GameImage.getImage(icon_file));
		if(word_key_get_2==null)
			word_key_get_2 = new Sprite(GameImage.getImage(GameStaticImage.word_key_get_2));
		
		s_share_ui_button_01 = new Sprite[2];
		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
		
		share_ui_button_text = new Sprite[2];
		share_ui_button_text[0] = new Sprite(GameImage.getImage(GameStaticImage.word_share));
		share_ui_button_text[1] = new Sprite(GameImage.getImage(GameStaticImage.word_continue));
		
    }
   
    /**
    * 物品不足的情况
    * */
    private void infoGoods(String icon_file){
    	anjianbutton = new boolean[1];
    	if(icon==null && icon_file!=null && !icon_file.equals(""))
			icon = new Sprite(GameImage.getImage(icon_file));
    	s_share_ui_button_01 = new Sprite[2];
		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
		
		share_ui_button_text = new Sprite[1];
		share_ui_button_text[0] = new Sprite(GameImage.getImage(GameStaticImage.word_ok));
    }
    /**
     * 物品不足button 自定义的情况
     * */
     private void infoGoodsButton(String icon_file){
     	anjianbutton = new boolean[1];
     	if(icon==null && icon_file!=null && !icon_file.equals(""))
 			icon = new Sprite(GameImage.getImage(icon_file));
     	s_share_ui_button_01 = new Sprite[2];
 		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
 		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
 		
 		share_ui_button_text = new Sprite[1];
 		share_ui_button_text[0] = new Sprite(GameImage.getImage(button_name));
		 
     }
    
    
    /**
     *关卡解锁 
     * */
    private void infoUnlock(){
    	anjianbutton = new boolean[2];
    	
    	//用这个代替标题
    	if(interface_ui_ribbon_01 == null)
			interface_ui_ribbon_01 = new Sprite(GameImage.getImage(GameStaticImage.word_title_unlock));
	
    	
    	if(share_ui_back_03 == null)
			share_ui_back_03 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_03));
    	s_share_ui_button_01 = new Sprite[2];
		s_share_ui_button_01[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
		s_share_ui_button_01[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
		
		share_ui_button_text = new Sprite[2];
		share_ui_button_text[0] = new Sprite(GameImage.getImage(GameStaticImage.shop_star_2));
		share_ui_button_text[1] = new Sprite(GameImage.getImage(GameStaticImage.shop_gem_12));
		
		icon_unlock = new Sprite(GameImage.getImage(GameStaticImage.shop_star_1));
		icon = new Sprite(GameImage.getImage(GameStaticImage.shop_gem_11));
		s_word_num_04 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_04, 12, 1, GameImage.Sort_line);
		
    }
    
    /**
     * 绘制奖励界面
     * @param canvas
     */
    private void drawAward(Canvas canvas){
    	 //奖励界面的标题
		interface_ui_ribbon_01.drawBitmap(canvas,interface_ui_ribbon_01.bitmap, (int)((x+40) * GameConfig.f_zoomx), (int)((y-35) * GameConfig.f_zoomy), null);
		word_title_reward.drawBitmap(canvas, word_title_reward.bitmap, (int)((x+144) * GameConfig.f_zoomx), (int)((y+10) * GameConfig.f_zoomy), null);
		
		interface_ui_shine.drawBitmap(canvas, interface_ui_shine.bitmap, (int)((x+127) * GameConfig.f_zoomx), (int)((y+9) * GameConfig.f_zoomy), null);
		
		share_ui_back_03.drawBitmap(canvas, null, (int)((x+156) * GameConfig.f_zoomx), (int)((y+88) * GameConfig.f_zoomy), (int)((172) * GameConfig.f_zoomx), (int)((215) * GameConfig.f_zoomy), -1);
	
		share_ui_back_06.drawBitmap(canvas, null, (int)((x+167) * GameConfig.f_zoomx), (int)((y+237) * GameConfig.f_zoomy), (int)((151) * GameConfig.f_zoomx), (int)((37) * GameConfig.f_zoomy), -1);
		
		if(icon!=null){
			icon.drawBitmap(canvas,icon.bitmap, (int)((x) * GameConfig.f_zoomx)+((int)(w * GameConfig.f_zoomx)-icon.bitmap.getWidth()>>1), (int)((y+88) * GameConfig.f_zoomy)+((int)(((y+237) * GameConfig.f_zoomy)-(int)((y+88) * GameConfig.f_zoomy))-icon.bitmap.getHeight()>>1), null);
		}
		 
		if(number!=null)
			number.paintText(canvas, (int)((x+177) * GameConfig.f_zoomx), (int)((y+250) * GameConfig.f_zoomy));
		
		
		for(int i=0;i<anjianbutton.length;++i){
			if(anjianbutton[i]){
				s_share_ui_button_01[1].drawBitmap(canvas, null, (int)((int)((x+button[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)((int)((y+button[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2)), (int)(170 * GameConfig.f_zoomx * 1.2f), -1);
			} else {				
				s_share_ui_button_01[0].drawBitmap(canvas, null, (int)((x+button[0][i]) * GameConfig.f_zoomx), (int)((y+button[1][i]) * GameConfig.f_zoomy), (int)(170 * GameConfig.f_zoomx), -1);
			}
			if(share_ui_button_text[i]!=null)
				share_ui_button_text[i].drawBitmap(canvas, (int)((x+button_text[0][i]) * GameConfig.f_zoomx) - share_ui_button_text[i].bitmap.getWidth()/2*(anjianbutton[i]?0.2f:0f), (int)((y+button_text[1][i]) * GameConfig.f_zoomy) - share_ui_button_text[i].bitmap.getHeight()/2* (anjianbutton[i]?0.2f:0f), anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);
		}
	
    }
    
    /**
     * 绘制物品不足界面
     * @param canvas
     */
    private void drawGoods(Canvas canvas){
    	if(icon!=null){
			icon.drawBitmap(canvas,icon.bitmap, (int)((x) * GameConfig.f_zoomx)+((int)(w * GameConfig.f_zoomx)-icon.bitmap.getWidth()>>1), (int)((y+41) * GameConfig.f_zoomy), null);
		}
//    	 FontMetrics fm = number.paint.getFontMetrics();
		 int height = (int)(number.height());//(int) Math.ceil(fm.descent - fm.top);
    	int start = (int)((y) * GameConfig.f_zoomy);
    	int end_y = (int)((y+222) * GameConfig.f_zoomy);
    	if(number!=null){
    		if(icon==null){
    			number.paintText(canvas, (int)((x+(w-(w-40))/2) * GameConfig.f_zoomx), start+((end_y-start)-height)/2);
    		}else{
    			number.paintText(canvas, (int)((x+(w-(w-40))/2) * GameConfig.f_zoomx), (int)((y+41) * GameConfig.f_zoomy)+icon.bitmap.getHeight()+((end_y - ((int)((y+41) * GameConfig.f_zoomy)+icon.bitmap.getHeight())) -height )/2);
    		}
    	}
		
    	for(int i=0;i<anjianbutton.length;++i){
			if(anjianbutton[i]){		
				if(s_share_ui_button_01[1]!=null)
					s_share_ui_button_01[1].drawBitmap(canvas, null, (int)((x+138) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), (int)((y+222) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2), (int)(200 * GameConfig.f_zoomx * 1.2f), -1);
			} else {			
				if(s_share_ui_button_01!=null && s_share_ui_button_01[0]!=null)
					s_share_ui_button_01[0].drawBitmap(canvas, null, (int)((x+138) * GameConfig.f_zoomx), (int)((y+222) * GameConfig.f_zoomy), (int)(200 * GameConfig.f_zoomx), -1);
			}
			if(share_ui_button_text!=null && share_ui_button_text[i]!=null)
				share_ui_button_text[i].drawBitmap(canvas, (int)((x+217) * GameConfig.f_zoomx) - share_ui_button_text[i].bitmap.getWidth()/2*(anjianbutton[i]?0.2f:0f), (int)((y+235) * GameConfig.f_zoomy) - share_ui_button_text[i].bitmap.getHeight()/2* (anjianbutton[i]?0.2f:0f), anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);
		}
    	
    	
    }
    
    /**
     * 绘制物品不足界面button自定义
     * */
   public void drawGoodsButton(Canvas canvas){
	   	if(icon!=null){
	   		int x1 = (int)((x) * GameConfig.f_zoomx)+((int)(w * GameConfig.f_zoomx)-icon.bitmap.getWidth()>>1);
	   		int y1 = (int)((y+41) * GameConfig.f_zoomy);
	   		if(word_number!=null){
	   			String temp = "x"+number_neirong;
	   			int start_x = (int)(x * GameConfig.f_zoomx) + ((int)(w * GameConfig.f_zoomx) - (int)(icon.bitmap.getWidth()+temp.length()*word_number[0].bitmap.getWidth()+(temp.length()-1)*GameConfig.f_zoomx)>>1);
	   			icon.drawBitmap(canvas,icon.bitmap, start_x, y1, null);
	   			word_number[0].drawBitmap(canvas,
						word_number, start_x+icon.bitmap.getWidth(),
						y1 +(icon.bitmap.getHeight() - word_number[0].bitmap.getHeight()>>1)+(int)(10*GameConfig.f_zoomy),
						GameConfig.Char_num7, temp, null, 0, 1f);
	   		}else{
	   			icon.drawBitmap(canvas,icon.bitmap, x1, y1, null);
	   		}
	   	
	   	}
	//	 FontMetrics fm = number.paint.getFontMetrics();
		 int height = (int)(number.height());//(int) Math.ceil(fm.descent - fm.top);
		int start = (int)((y) * GameConfig.f_zoomy);
		int end_y = (int)((y+222) * GameConfig.f_zoomy);
		if(number!=null){
			if(icon==null){
				number.paintText(canvas, (int)((x+(w-(w-40))/2) * GameConfig.f_zoomx), start+((end_y-start)-height)/2);
			}else{
				number.paintText(canvas, (int)((x+(w-(w-40))/2) * GameConfig.f_zoomx), (int)((y+41) * GameConfig.f_zoomy)+icon.bitmap.getHeight()+((end_y - ((int)((y+41) * GameConfig.f_zoomy)+icon.bitmap.getHeight())) -height )/2);
			}
		}
		
		for(int i=0;i<anjianbutton.length;++i){
			if(anjianbutton[i]){		
				if(s_share_ui_button_01[1]!=null)
					s_share_ui_button_01[1].drawBitmap(canvas, null, (int)((x+138) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), (int)((y+222) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2), (int)(200 * GameConfig.f_zoomx * 1.2f), -1);
			} else {			
				if(s_share_ui_button_01!=null && s_share_ui_button_01[0]!=null)
					s_share_ui_button_01[0].drawBitmap(canvas, null, (int)((x+138) * GameConfig.f_zoomx), (int)((y+222) * GameConfig.f_zoomy), (int)(200 * GameConfig.f_zoomx), -1);
			}
			float sizep = paint.getTextSize();
			if(share_ui_button_text!=null && share_ui_button_text[i]!=null){
				int xx = (int)((x+200) * GameConfig.f_zoomx - share_ui_button_text[i].bitmap.getWidth()/2*(anjianbutton[i]?0.2f:0f));
				int yy = (int)((y+230) * GameConfig.f_zoomy - share_ui_button_text[i].bitmap.getHeight()/2* (anjianbutton[i]?0.2f:0f));
				share_ui_button_text[i].drawBitmap(canvas, xx, yy, anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);
				paint.setTextSize(25 * GameConfig.f_zoomx*(anjianbutton[i]?1.2f:1f));
				if(anjianbutton[i]){
					canvas.drawText(button_number, xx+share_ui_button_text[i].bitmap.getWidth()+(int)(5 * GameConfig.f_zoomx), yy+(int)(30 * GameConfig.f_zoomy), paint);
				}else
					canvas.drawText(button_number, xx+share_ui_button_text[i].bitmap.getWidth(), yy+(int)(25 * GameConfig.f_zoomy), paint);
			}
			paint.setTextSize(sizep);
		}
   }
    /**
     * 绘制关卡解锁界面
     * */
    private void drawUnlock(Canvas canvas){
    	//标题
    	interface_ui_ribbon_01.drawBitmap(canvas,interface_ui_ribbon_01.bitmap, (int)((x+180) * GameConfig.f_zoomx), (int)((y+23) * GameConfig.f_zoomy), null);
		
    	for(int i=0;i<anjianbutton.length;++i){
    		share_ui_back_03.drawBitmap(canvas, share_ui_back_03.bitmap, (int)((x+un_bg[0][i]) * GameConfig.f_zoomx), (int)((y+un_bg[1][i]) * GameConfig.f_zoomy), null);
			
			if(anjianbutton[i]){
				s_share_ui_button_01[1].drawBitmap(canvas, null, (int)((int)((x+button_un[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2)), (int)((int)((y+button_un[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2)), (int)(134 * GameConfig.f_zoomx * 1.2f), -1);
			} else {				
				s_share_ui_button_01[0].drawBitmap(canvas, null, (int)((x+button_un[0][i]) * GameConfig.f_zoomx), (int)((y+button_un[1][i]) * GameConfig.f_zoomy), (int)(134 * GameConfig.f_zoomx), -1);
			}
			if(share_ui_button_text[i]!=null)
				share_ui_button_text[i].drawBitmap(canvas, (int)((x+button_text_un[0][i]) * GameConfig.f_zoomx) - share_ui_button_text[i].bitmap.getWidth()/2*(anjianbutton[i]?0.2f:0f), (int)((y+button_text_un[1][i]) * GameConfig.f_zoomy) - share_ui_button_text[i].bitmap.getHeight()/2* (anjianbutton[i]?0.2f:0f), anjianbutton[i]?1.2f:1f, anjianbutton[i]?1.2f:1f, 255, 0, 0, 0);
		
			s_word_num_04[0].drawBitmap(canvas, s_word_num_04, (int)((x+word_numxy[0][i]) * GameConfig.f_zoomx), (int)((y+word_numxy[1][i]) * GameConfig.f_zoomy), GameConfig.Char_num0, Integer.toString(UNLOCK_NUMBER[i]), null, 0,anjianbutton[i]?1.2f:1.0f);
			
    	}
    	
    	icon_unlock.drawBitmap(canvas, icon_unlock.bitmap, (int)((x+68) * GameConfig.f_zoomx), (int)((y+127) * GameConfig.f_zoomy),  null);
    	icon.drawBitmap(canvas, icon.bitmap, (int)((x+291) * GameConfig.f_zoomx), (int)((y+133) * GameConfig.f_zoomy), null);
    	
    	number.paintText(canvas, (int)((x+61) * GameConfig.f_zoomx), (int)((y+242) * GameConfig.f_zoomy));
    	nulock_1.paintText(canvas, (int)((x+267) * GameConfig.f_zoomx), (int)((y+242) * GameConfig.f_zoomy));
    	
    }
    
    boolean isclose;
    //run
    public boolean run(){
    	if(isclose){
			return true;
    	}
    	if(type == EVERYDAY){
	    	if(index != -1){
	    		widthRect+=10*GameConfig.f_zoomx;
	    		if(widthRect>=10*GameConfig.f_zoomx){
	    			isclose = true;
	    		}
	    	}
    	}
    	
		return false;
    }
    
    public byte onTouch(MotionEvent event){
    	
    	if(anjianbutton==null)return -1;
    	
    	float x = event.getX();
		float y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			
			if ((type != EVERYDAY) && ExternalMethods.CollisionTest(x, y, 
					(w-this.x)* GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					(this.y-9) * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight()/2*0.2f,
					(w-this.x+53) * GameConfig.f_zoomx + s_share_ui_close.bitmap.getWidth()*1.2f, 
					(this.y-9+53) * GameConfig.f_zoomy + s_share_ui_close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			}
			
			if(type == AWARD){ //奖励界面的按钮
				for(int i=0;i<anjianbutton.length;++i){
					if (ExternalMethods.CollisionTest(x, y, 
							(int)((this.x+button[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							(int)((this.y+button[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
							(int)(170 * GameConfig.f_zoomx * 1.2f)+(int)((this.x+button[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							s_share_ui_button_01[0].bitmap.getHeight()+(int)((this.y+button[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
						anjianbutton[i] = true;
					}
				}
			}else if(type == GOOGS || type == GOOGS_BUTTON){ //物品不足
				for(int i=0;i<anjianbutton.length;++i){
					if (ExternalMethods.CollisionTest(x, y, 
							(int)((this.x+138) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							(int)((this.y+222) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
							(int)(200 * GameConfig.f_zoomx * 1.2f)+(int)((this.x+button[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							s_share_ui_button_01[0].bitmap.getHeight()+(int)((this.y+button[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
						anjianbutton[i] = true;
					}
				}
			}else if(type == UNLOCK){
				for(int i=0;i<anjianbutton.length;++i){
					if (ExternalMethods.CollisionTest(x, y, 
							(int)((this.x+button_un[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							(int)((this.y+button_un[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
							(int)(134 * GameConfig.f_zoomx * 1.2f)+(int)((this.x+button_un[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							s_share_ui_button_01[0].bitmap.getHeight()+(int)((this.y+button_un[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
						anjianbutton[i] = true;
					}
				}
			}else if(type == EVERYDAY){ //每日奖励
				int  _frist = -1;
		    	for(int i=0;i<bg_x.length;++i){
		    		if(_frist == -1 && !isEveryDay[i]){
		    			_frist = i;
		    		}
		    	}
		    	int i = 0;
		    	int button_x = (int)(this.x * GameConfig.f_zoomx+((w * GameConfig.f_zoomx - s_share_ui_button_01[0].bitmap.getWidth())/2));
		    	int button_y = (int)(this.y * GameConfig.f_zoomy+h * GameConfig.f_zoomy-s_share_ui_button_01[0].bitmap.getHeight()/2 - (18* GameConfig.f_zoomy));
//		    	for(int i=0;i<anjianbutton.length;++i){
					if (ExternalMethods.CollisionTest(x, y, 
							(int)(button_x) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							(int)(button_y)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
							(int)(button_x)+(int)((s_share_ui_button_01[0].bitmap.getWidth()) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							s_share_ui_button_01[0].bitmap.getHeight()+(int)(button_y)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
						anjianbutton[i] = true;
					}
//				}
			}
			
			
		}else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (anjianclose && ExternalMethods.CollisionTest(x, y, 
					(w-this.x)* GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					(this.y-9) * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight()/2*0.2f,
					(w-this.x+53) * GameConfig.f_zoomx + s_share_ui_close.bitmap.getWidth()*1.2f, 
					(this.y-9+53) * GameConfig.f_zoomy + s_share_ui_close.bitmap.getHeight()*1.2f)) {
						isclose = true;
						if(GameManager.getGT()!=null &&
								GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL53) //新手教程
							isclose = false ; //特殊处理 赶进度
					
					return onTouch_close;
			}
			
			if(type == AWARD){ //奖励界面
				for(int i=0;i<anjianbutton.length;++i){
					if (anjianbutton[i] && ExternalMethods.CollisionTest(x, y, 
							(int)((this.x+button[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							(int)((this.y+button[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
							(int)(170 * GameConfig.f_zoomx * 1.2f)+(int)((this.x+button[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							s_share_ui_button_01[0].bitmap.getHeight()+(int)((this.y+button[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
							if(i == 0){
								  //分享
								isclose = true;
								return onTouch_share;
							}else if(i == 1){ //退出
								isclose = true;
								return onTouch_awardExit;
							}
					}
				}
	    	}else if(type == GOOGS || type == GOOGS_BUTTON){ //物品不足
	    		for(int i=0;i<anjianbutton.length;++i){
					if (anjianbutton[i] && ExternalMethods.CollisionTest(x, y, 
							(int)((this.x+138) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							(int)((this.y+222) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
							(int)(200 * GameConfig.f_zoomx * 1.2f)+(int)((this.x+button[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							s_share_ui_button_01[0].bitmap.getHeight()+(int)((this.y+button[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
							
							if(!temp_isclose){
								isclose = true;
							}else
								anjianbutton[i] = false;
							return onTouch_googsExit;
					}
				}
			}else if(type == UNLOCK){
				for(int i=0;i<anjianbutton.length;++i){
					if (anjianbutton[i] && ExternalMethods.CollisionTest(x, y, 
							(int)((this.x+button_un[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							(int)((this.y+button_un[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
							(int)(134 * GameConfig.f_zoomx * 1.2f)+(int)((this.x+button_un[0][i]) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							s_share_ui_button_01[0].bitmap.getHeight()+(int)((this.y+button_un[1][i]) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
						anjianbutton[i] = false;
						isclose = true;
						if(i ==0){ //用星星解锁
							return onTouch_unlockExit_SART;
						}else if(i==1){ //砖石解锁

							return onTouch_unlockExit_GET;
						}
						
						 
					}
				}
			}else if(type == EVERYDAY && index == -1){ //每日奖励
				int  _frist = -1;
		    	for(int i=0;i<bg_x.length;++i){
		    		if(_frist == -1 && !isEveryDay[i]){
		    			_frist = i;
		    		}
		    	}
		    	
		    	
		    	int i = 0;
		    	int button_x = (int)(this.x * GameConfig.f_zoomx+((w * GameConfig.f_zoomx - s_share_ui_button_01[0].bitmap.getWidth())/2));
		    	int button_y = (int)(this.y * GameConfig.f_zoomy+h * GameConfig.f_zoomy-s_share_ui_button_01[0].bitmap.getHeight()/2 - (18* GameConfig.f_zoomy));
//		    	for(int i=0;i<anjianbutton.length;++i){
					if (anjianbutton[i] && ExternalMethods.CollisionTest(x, y, 
							(int)(button_x) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							(int)(button_y)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
							(int)(button_x)+(int)((s_share_ui_button_01[0].bitmap.getWidth()) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
							s_share_ui_button_01[0].bitmap.getHeight()+(int)(button_y)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
					    isEveryDay[_frist] = true;
					    index = _frist;
					    VeggiesData.addGem(everydadyNumber[_frist]);
//					    isclose = true;
					    VeggiesData.everyDay+=1;
					    if(VeggiesData.everyDay>=5)
					    	VeggiesData.everyDay = 0;
					    VeggiesData.ztNyr = System.currentTimeMillis();
				        Calendar lastDate = Calendar.getInstance();    
				        lastDate.set(Calendar.DATE,1);//设为当前月的1号    
				        lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号    
				        lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天    
				        int day =  lastDate.get(Calendar.DAY_OF_MONTH); //当月最后天
						VeggiesData.endT = day;
						new VeggiesData().saveGame();
					    return -1;
					}
//				}
		    	
//		    	for(int i=0;i<anjianbutton.length;++i){
//					if (anjianbutton[i] && ExternalMethods.CollisionTest(x, y, 
//							(int)((this.x+bg_x[_frist]+24) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
//							(int)((this.y+bg_y[_frist]+133) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2),
//							(int)((this.x+bg_x[_frist]+24) * GameConfig.f_zoomx)+(int)((74) * GameConfig.f_zoomx) + (s_share_ui_button_01[0].bitmap.getWidth()/2 - s_share_ui_button_01[1].bitmap.getWidth()/2), 
//							s_share_ui_button_01[0].bitmap.getHeight()+(int)((this.y+bg_y[_frist]+133) * GameConfig.f_zoomy)  + (s_share_ui_button_01[0].bitmap.getHeight()/2 - s_share_ui_button_01[1].bitmap.getHeight()/2))) {
//					    System.out.println("<><>");
//					    isEveryDay[_frist] = true;
//					    VeggiesData.addGem(everydadyNumber[_frist]);
//					    isclose = true;
//					    VeggiesData.everyDay+=1;
//					    if(VeggiesData.everyDay>=6)
//					    	VeggiesData.everyDay = 0;
//					    VeggiesData.ztNyr = System.currentTimeMillis();
//				        Calendar lastDate = Calendar.getInstance();    
//				        lastDate.set(Calendar.DATE,1);//设为当前月的1号    
//				        lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号    
//				        lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天    
//				        int day =  lastDate.get(Calendar.DAY_OF_MONTH); //当月最后天
//						VeggiesData.endT = day;
//						new VeggiesData().saveGame();
//					    return onTouch_everyDayExit;
//					}
//				}
			}
			
			anjianclose = false;
			for(int i=0;i<anjianbutton.length;++i){
				anjianbutton[i] = false;	
			}
			
		}
    	
    	
    	return -1;
    	
    };
    
    public void delete(){
    	if (s_share_ui_back_01 == null)
			return;
    	if (gs != null)
			GameImage.delImage(gs.bitmap); 
		gs = null;
    	
    	GameImage.delImage(s_share_ui_back_01.bitmap);
		if (s_share_ui_back_01.bitmap != null)
			s_share_ui_back_01.bitmap = null;
		s_share_ui_back_01 = null;
    	
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
			s_share_ui_back_02_2 = null;
		}
		if(s_share_ui_close!=null){
			GameImage.delImage(s_share_ui_close.bitmap);
			if (s_share_ui_close.bitmap != null)
				s_share_ui_close.bitmap = null;
			s_share_ui_close = null;
		}
		
		if(interface_ui_ribbon_01 != null){
			GameImage.delImage(interface_ui_ribbon_01.bitmap);
			if (interface_ui_ribbon_01.bitmap != null)
				interface_ui_ribbon_01.bitmap = null;
			interface_ui_ribbon_01 = null;
		}
		
		if(word_number!=null)
			GameImage.delImageArray(word_number);
		word_number = null;
		
		if(word_title_reward != null){
			GameImage.delImage(word_title_reward.bitmap);
			if (word_title_reward.bitmap != null)
				word_title_reward.bitmap = null;
			word_title_reward = null;
		}
		if(word_title_reward_2 != null){
			GameImage.delImage(word_title_reward_2.bitmap);
			if (word_title_reward_2.bitmap != null)
				word_title_reward_2.bitmap = null;
			word_title_reward_2 = null;
		}
		
		if(share_ui_back_03 != null){
			GameImage.delImage(share_ui_back_03.bitmap);
			if (share_ui_back_03.bitmap != null)
				share_ui_back_03.bitmap = null;
			share_ui_back_03 = null;
		}
		
		if(share_ui_back_06 != null){
			GameImage.delImage(share_ui_back_06.bitmap);
			if (share_ui_back_06.bitmap != null)
				share_ui_back_06.bitmap = null;
			share_ui_back_06 = null;
		}
		
		if(interface_ui_shine != null){
			GameImage.delImage(interface_ui_shine.bitmap);
			if (interface_ui_shine.bitmap != null)
				interface_ui_shine.bitmap = null;
			interface_ui_shine = null;
		}
		if(icon != null){
			GameImage.delImage(icon.bitmap);
			if (icon.bitmap != null)
				icon.bitmap = null;
			icon = null;
		}

		if(word_key_get_2 != null){
			GameImage.delImage(word_key_get_2.bitmap);
			if (word_key_get_2.bitmap != null)
				word_key_get_2.bitmap = null;
			word_key_get_2 = null;
		}
		
		if(paint!=null)
			paint = null;
		
		if(icon_unlock != null){
			GameImage.delImage(icon_unlock.bitmap);
			if (icon_unlock.bitmap != null)
				icon_unlock.bitmap = null;
			icon_unlock = null;
		}
		
		if(s_share_ui_button_01!=null)
			for(int i=0;i<s_share_ui_button_01.length;++i){
				if(s_share_ui_button_01[i] != null){
					GameImage.delImage(s_share_ui_button_01[i].bitmap);
					if (s_share_ui_button_01[i].bitmap != null)
						s_share_ui_button_01[i].bitmap = null;
					s_share_ui_button_01[i] = null;
				}
			}
		s_share_ui_button_01 = null;
		
		if(s_word_num_04!=null)
			GameImage.delImageArray(s_word_num_04);
		s_word_num_04 = null;
		

		if(reward_card!=null)
			for(int i=0;i<reward_card.length;++i){
				if(reward_card[i] != null){
					GameImage.delImage(reward_card[i].bitmap);
					if (reward_card[i].bitmap != null)
						reward_card[i].bitmap = null;
					reward_card[i] = null;
				}
			}
		reward_card = null;

		if(reward_title!=null)
			for(int i=0;i<reward_title.length;++i){
				if(reward_title[i] != null){
					GameImage.delImage(reward_title[i].bitmap);
					if (reward_title[i].bitmap != null)
						reward_title[i].bitmap = null;
					reward_title[i] = null;
				}
			}
		reward_title = null;
		if(word_reward_num!=null)
			for(int i=0;i<word_reward_num.length;++i){
				if(word_reward_num[i] != null){
					GameImage.delImage(word_reward_num[i].bitmap);
					if (word_reward_num[i].bitmap != null)
						word_reward_num[i].bitmap = null;
					word_reward_num[i] = null;
				}
			}
		word_reward_num = null;
		
		if(share_ui_button_text!=null)
			for(int i=0;i<share_ui_button_text.length;++i){
				if(share_ui_button_text[i] != null){
					GameImage.delImage(share_ui_button_text[i].bitmap);
					if (share_ui_button_text[i].bitmap != null)
						share_ui_button_text[i].bitmap = null;
					share_ui_button_text[i] = null;
				}
			}
		share_ui_button_text = null;


		if(number!=null)
			number.Close();
		number = null;
		
		if(nulock_1!=null)
			nulock_1.Close();
		nulock_1 = null;
		
	 
		button = null;
		button_text = null;
		un_bg = null;
		anjianbutton = null;
		offexbg = null;
		word_numxy = null;
    };
    
}//end class
