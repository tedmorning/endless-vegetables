package com.endlessvegetables2.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.R;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

/**
 * 充值金币
 * @author Administrator
 *
 */
public class RechargeGold extends Module{
	//用砖石购买金币的数量
	public static final int buyGold[] = {100, 220, 600, 1500};
	//砖石购买金币需要的砖石数量
	public static final int diamondNum[] = {10, 20, 50, 100};
	boolean anjianclose;
	Sprite share_ui_back_03;
	Sprite shop_icon[];
	Sprite share_ui_back_06;
	Sprite[] word_num_03;
	boolean[] anjianbutton = {false,false,false,false};
	Sprite shop_gem_12;
	Sprite shop_reward;
	String bonus[] = {"10%", "20%", "50%"};
	Paint paint ;
	String s_bonus ;
	Sprite gs; 
	Sprite bitmap_Gold_01;
	Sprite bitmap_key_add_2;

	int textsize=0;
	int textstate=0;
	int addGold = 0;
	int i_Gold;
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		if(GameStaticImage.s_share_ui_back_01 == null)
			GameStaticImage.s_share_ui_back_01 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));
		if(GameStaticImage.s_share_ui_back_02 == null)
			GameStaticImage.s_share_ui_back_02 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));
		if(GameStaticImage.s_share_ui_back_02_2 == null)
			GameStaticImage.s_share_ui_back_02_2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));
		if(GameStaticImage.s_share_ui_close == null)
			GameStaticImage.s_share_ui_close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
		if(bitmap_Gold_01==null)
			bitmap_Gold_01= new Sprite(GameImage.getImage("newui/Gold_01"));
		if(bitmap_key_add_2==null)
			bitmap_key_add_2= new Sprite(GameImage.getImage("Interface/key_add_2"));
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Typeface font	= Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
		paint.setTypeface(font);
		paint.setColor(Color.WHITE);
		paint.setTextSize(18 * GameConfig.f_zoomx);
		
		share_ui_back_03 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_03));
		
		shop_icon = new Sprite[4];
		String icon[] = {GameStaticImage.shop_gold_07, GameStaticImage.shop_gold_08, GameStaticImage.shop_gold_09, GameStaticImage.shop_gold_10};
		for(int i=0;i<icon.length;++i){
			shop_icon[i] = new Sprite(GameImage.getImage(icon[i]));
		}
		i_Gold = VeggiesData.getGold();
		share_ui_back_06 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_06));
		word_num_03  = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_03, 11, 1, GameImage.Sort_line);
		
		if(GameStaticImage.s_share_ui_button_01 == null){
			GameStaticImage.s_share_ui_button_01 = new Sprite[2];
			GameStaticImage.s_share_ui_button_01[0] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01));
			GameStaticImage.s_share_ui_button_01[1] = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_01_2));
		}
		shop_gem_12 = new Sprite(GameImage.getImage(GameStaticImage.shop_gem_12));
		shop_reward = new Sprite(GameImage.getImage(GameStaticImage.shop_reward));
		if(GameStaticImage.s_word_num_04 == null)
			GameStaticImage.s_word_num_04 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_04, 12, 1, GameImage.Sort_line);
	
		s_bonus = LangUtil.getLangString(LangDefineClient.BONUS);
		gs = new Sprite();
		return false;
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		gs.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0,GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		int y = 0;
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(85 * GameConfig.f_zoomy + y), (int)(476 * GameConfig.f_zoomx), (int)(671 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(148 * GameConfig.f_zoomy + y), (int)(443 * GameConfig.f_zoomx), (int)(591 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int)(45 * GameConfig.f_zoomx), (int)(148 * GameConfig.f_zoomy + y), (int)(443 * GameConfig.f_zoomx), (int)(591 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), 76 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f) + y, anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
	
		Rect rect = new Rect();     
		//当前金币数显示
		int tempX=(int)(10*GameConfig.f_zoom);
		int tempY=(int)(10*GameConfig.f_zoom)+bitmap_Gold_01.bitmap.getHeight()/2;
		canvas.drawBitmap(bitmap_Gold_01.bitmap, tempX, tempY-bitmap_Gold_01.bitmap.getHeight()/2, null);
		tempX+=bitmap_Gold_01.bitmap.getWidth()+5;
		
		float size = paint.getTextSize();
		paint.setTextSize(30*GameConfig.f_zoom);
		paint.setColor(0xffffffff);
		canvas.drawText(""+i_Gold, tempX, tempY+15*GameConfig.f_zoom, paint);
		
		paint.getTextBounds(""+i_Gold, 0, (""+i_Gold).length(), rect);
		tempX+=rect.width()+10*GameConfig.f_zoom;
		canvas.drawBitmap(bitmap_key_add_2.bitmap, tempX, tempY-bitmap_key_add_2.bitmap.getHeight()/2, null);
		
		//可增加金币数
		paint.setColor(0xffffe552);
		paint.setTextSize(42*GameConfig.f_zoom+textsize*4f*GameConfig.f_zoom);
		tempX+=bitmap_key_add_2.bitmap.getWidth()+10*GameConfig.f_zoom;;
		canvas.drawText(""+addGold, tempX, tempY+15*GameConfig.f_zoom, paint);
		paint.setTextSize(size);
		
		int w = share_ui_back_03.bitmap.getWidth();
		int h = (int)(145*GameConfig.f_zoomy);
		for(int i=0;i<4;++i){
			int x  = (int)(76 * GameConfig.f_zoomx + (34*GameConfig.f_zoomx) / 2 + (i % 2) * (34*GameConfig.f_zoomx + share_ui_back_03.bitmap.getWidth())-34*GameConfig.f_zoomx/2);
			int y1 = (int)(200 * GameConfig.f_zoomy + (53*GameConfig.f_zoomy) / 2 + (i/2) * (share_ui_back_03.bitmap.getHeight() + 53*GameConfig.f_zoomy) - 53*GameConfig.f_zoomy/2);
			//五星背景
			share_ui_back_03.drawBitmap(canvas, share_ui_back_03.bitmap, x, y+y1, null);
			//icon图标
			int icon_x = x + (w-shop_icon[i].bitmap.getWidth()>>1);
			int icon_y = y+y1+(h-shop_icon[i].bitmap.getHeight()>>1);
			shop_icon[i].drawBitmap(canvas, shop_icon[i].bitmap, icon_x, icon_y, null);
			//bonus
			if(i>=1){
				int bonusbg_x = x+(int)(118 * GameConfig.f_zoomx);
				int bonusbg_y = y+y1-(int)(7*GameConfig.f_zoomy);
				shop_reward.drawBitmap(canvas, shop_reward.bitmap, bonusbg_x, bonusbg_y, null);
				canvas.drawText(bonus[i-1], bonusbg_x+((shop_reward.bitmap.getWidth() - paint.measureText(bonus[i-1]))/2), bonusbg_y+23*GameConfig.f_zoomy, paint);
				canvas.drawText(s_bonus, bonusbg_x+((shop_reward.bitmap.getWidth() - paint.measureText(s_bonus))/2), bonusbg_y+23*GameConfig.f_zoomy+paint.getTextSize(), paint);
			}
			//价格背景
			int goldbg_x = x + (w-share_ui_back_06.bitmap.getWidth()>>1);
			int goldbg_y = y+y1+(h);
			share_ui_back_06.drawBitmap(canvas, share_ui_back_06.bitmap, goldbg_x, goldbg_y, null);
			//充值金额
			int goldnumber_x = goldbg_x + (i==3?(int)(20 * GameConfig.f_zoomx):(int)(25 * GameConfig.f_zoomx));
			int goldumber_y = goldbg_y+(int)( 9 * GameConfig.f_zoomy);
			word_num_03[0].drawBitmap(canvas, word_num_03, goldnumber_x, goldumber_y, GameConfig.Char_num7, "x"+buyGold[i], null, 0, 1.0f);
			//button
			int button_x = x+(int)(20 * GameConfig.f_zoomx);
			int button_y = y+y1+(int)(197 * GameConfig.f_zoomy);
			if (anjianbutton[i]) {
				GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2), 
						button_y+(GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2), (int)(134 * GameConfig.f_zoomx * 1.2f), -1);
			} else {				
				GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, button_x, button_y, (int)(134 * GameConfig.f_zoomx), -1);
			}
			//button上面的砖石图标
			int button_icon_x = x+(int)(40 * GameConfig.f_zoomx);
			int button_icon_y = y+y1+(int)(204 * GameConfig.f_zoomy);
			shop_gem_12.drawBitmap(canvas, button_icon_x - (anjianbutton[i]?0.2f:0f) *shop_gem_12.bitmap.getWidth()/2, button_icon_y - (anjianbutton[i]?0.2f:0f) * shop_gem_12.bitmap.getHeight()/2, anjianbutton[i]?1.2f:1.0f, anjianbutton[i]?1.2f:1.0f, 255, 0, 0, 0);
			
			//button number
			int button_number_x = x+(int)(86 * GameConfig.f_zoomx);
			int button_number_y = y+y1+(int)(211 * GameConfig.f_zoomy);
			GameStaticImage.s_word_num_04[0].drawBitmap(canvas, GameStaticImage.s_word_num_04, button_number_x, button_number_y, GameConfig.Char_num0, Integer.toString(diamondNum[i]), null, 0,anjianbutton[i]?1.2f:1.0f);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(textstate>0){
			textstate--;
			textsize++;
			if(textstate<=0){
				textstate=-5;
				textsize=0;
				i_Gold+=addGold;
				addGold = 0;
			}
		}else if(textstate<0){
			textstate++;
			textsize--;
			if(textstate>=0){
				textstate=0;
				textsize=0;
				i_Gold+=addGold;
				addGold = 0;
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
		if(gs!=null){
			GameImage.delImage(gs.bitmap);
			gs.bitmap = null;
		}
		gs = null;
		
		if(share_ui_back_03!=null)
			GameImage.delImage(share_ui_back_03.bitmap);
		share_ui_back_03 = null;
		if(shop_icon!=null){
			for(int i=0;i<shop_icon.length;++i){
				GameImage.delImage(shop_icon[i].bitmap);
			}
		}
		shop_icon = null;
		
		if(bitmap_Gold_01!=null)
			GameImage.delImage(bitmap_Gold_01.bitmap);
		bitmap_Gold_01 = null;
		
		if(bitmap_key_add_2!=null)
			GameImage.delImage(bitmap_key_add_2.bitmap);
		bitmap_key_add_2 = null;
		
		if(share_ui_back_06!=null)
			GameImage.delImage(share_ui_back_06.bitmap);
		share_ui_back_06 = null;
		
		if(word_num_03!=null)
			for (int i=0; i<word_num_03.length; i++) {
				GameImage.delImage(word_num_03[i].bitmap);
				word_num_03[i].bitmap = null;
			}
		word_num_03 = null;
		
		if(shop_gem_12!=null)
			GameImage.delImage(shop_gem_12.bitmap);
		shop_gem_12 = null;
		
		if(shop_reward!=null)
			GameImage.delImage(shop_reward.bitmap);
		shop_reward = null;
		
		
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
				453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
				76 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				76 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
				anjianclose = true;
			}
			 
			for(int i=0;i<4;++i){
				int x1  = (int)(76 * GameConfig.f_zoomx + (34*GameConfig.f_zoomx) / 2 + (i % 2) * (34*GameConfig.f_zoomx + share_ui_back_03.bitmap.getWidth()));
				int y1 = (int)(200 * GameConfig.f_zoomy + (53*GameConfig.f_zoomy) / 2 + (i/2) * (share_ui_back_03.bitmap.getHeight() + 53*GameConfig.f_zoomy) - 53*GameConfig.f_zoomy/2);
				//button
				int button_x = x1+(int)(20 * GameConfig.f_zoomx);
				int button_y = y1+(int)(197 * GameConfig.f_zoomy);
				if (ExternalMethods.CollisionTest(x, y, 
						(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
						(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
						(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
						(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
					anjianbutton[i] = true;
				}
			}
			
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			
			if (anjianclose && ExternalMethods.CollisionTest(x, y, 
				453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth()/2*0.2f, 
				76 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight()/2*0.2f,
				453 * GameConfig.f_zoomx + GameStaticImage.s_share_ui_close.bitmap.getWidth()*1.2f, 
				76 * GameConfig.f_zoomy + GameStaticImage.s_share_ui_close.bitmap.getHeight()*1.2f)) {
//				if (GameStaticImage.s_map_ship_1 == null) new ImageThread().start();
				GameManager.ChangeModule(null);
			}
			for(int i=0;i<4;++i){
				int x1  = (int)(76 * GameConfig.f_zoomx + (34*GameConfig.f_zoomx) / 2 + (i % 2) * (34*GameConfig.f_zoomx + share_ui_back_03.bitmap.getWidth()));
				int y1 = (int)(200 * GameConfig.f_zoomy + (53*GameConfig.f_zoomy) / 2 + (i/2) * (share_ui_back_03.bitmap.getHeight() + 53*GameConfig.f_zoomy) - 53*GameConfig.f_zoomy/2);
				//button
				int button_x = x1+(int)(20 * GameConfig.f_zoomx);
				int button_y = y1+(int)(197 * GameConfig.f_zoomy);
				if (ExternalMethods.CollisionTest(x, y, 
						(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
						(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
						(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
						(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
					anjianbutton[i] = true;
					if(VeggiesData.getGem()>=diamondNum[i]){
						VeggiesData.addGem(-diamondNum[i]);
						int buyGold = this.buyGold[i];
						if(i == 1){ //奖励
							buyGold +=(buyGold*0.1);
						}else if(i == 2){ //奖励
							buyGold +=(buyGold*0.2);
						}else if(i == 3){ //奖励
							buyGold +=(buyGold*0.5);
						}
						VeggiesData.addGold(buyGold);
						addGold = buyGold;
						String temp = LangUtil.getLangString(LangDefineClient.REWARD_GOLD);
						temp = temp.replace("X", ""+buyGold);
						GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
							@Override
							public byte onTouch(MotionEvent event) {
								// TODO Auto-generated method stub
								byte temp = super.onTouch(event);
								if(temp == PopUp.onTouch_googsExit || temp == PopUp.onTouch_close){
									textstate=-5;
									textsize=5;
									return -1;
								}
								return temp;
							}							
						});
					}else{
						String temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
						GameManager.setPopUp(PopUp.GOOGS,GameStaticImage.shop_gem_13, new PopUp(temp) {
						});
					}
				}
			}
			
			anjianclose = false;
			for (int i=0; i<anjianbutton.length; i++) {
				anjianbutton[i] = false;
			}
			
		}
		
	}

	
	
}//end class
