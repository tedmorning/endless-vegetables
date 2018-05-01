package com.game.item;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.MotionEvent;

import com.endlessvegetables2.ui.GameStaticImage;
import com.endlessvegetables2.ui.RechargeDiamond;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.tools.GameTools;

/**
 * 充值钻石的item
 * @author Administrator
 *
 */
public class DiamondItem {
	
	RechargeDiamond diamond;
	boolean isbonus;
	String bonus;
	String s_bonus;
	int buyDiamon; //用RMB能购买的数量
	boolean anjianbutton;
	double buyDiamonRMB;//购买钻石需要的RMB
	int item_x, item_y;
	public int index;
	public int id;
	public DiamondItem(RechargeDiamond _diamond, int _index, int _id){
		diamond = _diamond;
		index = _index;
		id = _id;
	}
	
	public void setBonus(boolean _isbonus, String _bonus, String _s_bonus){
		isbonus = _isbonus;
		bonus = _bonus;
		s_bonus = _s_bonus;
	}
	public void setBuyDiamon(int _buyDiamon, double _buyDiamonRMB){
		buyDiamon = _buyDiamon;
		buyDiamonRMB = _buyDiamonRMB;
	}
	
	public void paint(Canvas canvas, Sprite icon, int x, int y1, Paint paint) {
		int w = diamond.share_ui_back_03.bitmap.getWidth();
		int h = (int)(145*GameConfig.f_zoomy);
		int y = 0;
		
		item_x = x;
		item_y = y1;
		
		 //五星背景
		diamond.share_ui_back_03.drawBitmap(canvas, diamond.share_ui_back_03.bitmap, x, y+y1, null);
		//icon图标
		int icon_x = x + (w-icon.bitmap.getWidth()>>1);
		int icon_y = y+y1+(h-icon.bitmap.getHeight()>>1);
		icon.drawBitmap(canvas, icon.bitmap, icon_x, icon_y, null);
		if(isbonus){
			int bonusbg_x = x+(int)(118 * GameConfig.f_zoomx);
			int bonusbg_y = y+y1-(int)(7*GameConfig.f_zoomy);
			diamond.shop_reward.drawBitmap(canvas, diamond.shop_reward.bitmap, bonusbg_x, bonusbg_y, null);
			
			FontMetrics fm = paint.getFontMetrics();
			int height = (int) Math.ceil(fm.descent - fm.top);
			int text_y = (diamond.shop_reward.bitmap.getHeight()-height*2)/2+height;
			canvas.drawText(bonus, bonusbg_x+((diamond.shop_reward.bitmap.getWidth() - paint.measureText(bonus))/2), bonusbg_y+text_y, paint);
			canvas.drawText(s_bonus, bonusbg_x+((diamond.shop_reward.bitmap.getWidth() - paint.measureText(s_bonus))/2), bonusbg_y+text_y+height, paint);
		}
		//价格背景
		int goldbg_x = x + (w-diamond.share_ui_back_06.bitmap.getWidth()>>1);
		int goldbg_y = y+y1+(h);
		diamond.share_ui_back_06.drawBitmap(canvas, diamond.share_ui_back_06.bitmap, goldbg_x, goldbg_y, null);
		//充值金额
		String number = "x"+buyDiamon;
		int goldnumber_x = goldbg_x + GameTools.getLengthWidth(diamond.share_ui_back_06.bitmap, diamond.word_num_03[0].bitmap, number);
		int goldumber_y = goldbg_y+GameTools.getLengthHight(diamond.share_ui_back_06.bitmap, diamond.word_num_03[0].bitmap);
		diamond.word_num_03[0].drawBitmap(canvas, diamond.word_num_03, goldnumber_x, goldumber_y, GameConfig.Char_num7, number, null, 0, 1.0f);
		
		//button
		int button_x = x+(int)(20 * GameConfig.f_zoomx);
		int button_y = y+y1+(int)(197 * GameConfig.f_zoomy);
		if (anjianbutton) {
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, null, button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2), 
					button_y+(GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2), (int)(134 * GameConfig.f_zoomx * 1.2f), -1);
		} else {				
			GameStaticImage.s_share_ui_button_01[0].drawBitmap(canvas, null, button_x, button_y, (int)(134 * GameConfig.f_zoomx), -1);
		}
		//button上面的砖石图标
		number = "$"+Double.toString(buyDiamonRMB);
		int button_icon_x = button_x+GameTools.getLengthWidth((int)(134 * GameConfig.f_zoomx), GameStaticImage.s_word_num_04[0].bitmap, number);
		int button_icon_y = button_y+GameTools.getLengthHight(GameStaticImage.s_share_ui_button_01[0].bitmap, GameStaticImage.s_word_num_04[0].bitmap);
		GameStaticImage.s_word_num_04[0].drawBitmap(canvas, GameStaticImage.s_word_num_04, button_icon_x, button_icon_y, GameConfig.Char_num0, number, null, 0,anjianbutton?1.2f:1.0f);
 
		//button number
//		int button_number_x = x+(int)(86 * GameConfig.f_zoomx);
//		int button_number_y = y+y1+(int)(211 * GameConfig.f_zoomy);
//		GameStaticImage.s_word_num_04[0].drawBitmap(canvas, GameStaticImage.s_word_num_04, button_number_x, button_number_y, GameConfig.Char_num0, "$"+Double.toString(buyDiamonRMB), null, 0,anjianbutton?1.2f:1.0f);
//	
		
	}
	
	public int onTouch(MotionEvent event){
		float x = event.getX();
		float y = event.getY();
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int x1  = item_x;
			int y1 =  item_y;
			//button
			int button_x = x1+(int)(20 * GameConfig.f_zoomx);
			int button_y = y1+(int)(197 * GameConfig.f_zoomy);
			if (ExternalMethods.CollisionTest(x, y, 
					(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
					(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjianbutton = true;
			}
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			int x1  = item_x;
			int y1 =  item_y;
			//button
			int button_x = x1+(int)(20 * GameConfig.f_zoomx);
			int button_y = y1+(int)(197 * GameConfig.f_zoomy);
			if (anjianbutton && ExternalMethods.CollisionTest(x, y, 
					(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2)), 
					(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)), 
					(int)(button_x + (GameStaticImage.s_share_ui_button_01[0].bitmap.getWidth()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2) + 134 * GameConfig.f_zoomx * 1.2f), 
					(int)(button_y + (GameStaticImage.s_share_ui_button_01[0].bitmap.getHeight()/2 - GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2) + GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()))) {
				anjianbutton = false;
				return id;
			}
			anjianbutton = false;
		}else if(event.getAction() == MotionEvent.ACTION_MOVE){
			anjianbutton = false;
		}
		return -1;
	}
	
}//end class
