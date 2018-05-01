package com.game.item;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.endlessvegetables2.ui.GameStaticImage;
import com.endlessvegetables2.ui.GameWord;
import com.endlessvegetables2.ui.Location;
import com.endlessvegetables2.ui.RankingSuccessModule;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMission;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.TextBox;

/**
 * 成就的item
 * @author Administrator
 *
 */
public class SuccessItem {

	private TextBox content;
	private int word_move_y;
	private boolean isScrollUp = false;
	
	 
	private int state = 0;
	public int id;
	int textheight = 64;
	boolean anjianbutton;
	public int index;
	int gold;
	RankingSuccessModule success;
	
	//关卡进度
	int ok_num = 0;
	int size_num = 0;
	int item_x, item_y;
	
	boolean isGet;
	
	public void setNum(int _state, int _ok_num, int _size_num){
		this.ok_num = _ok_num;
		this.size_num = _size_num;
		state = _state;
	}
	public void setGold(int _gold){
		gold = _gold;
	}
	
	public SuccessItem(boolean _isGet, RankingSuccessModule _success, int index, int _id, String str){
		this.isGet = _isGet;
		id = _id;
		this.index = index;
		success = _success;
		anjianbutton = false;
		content = new TextBox();
		content.setColor(0,0xff824d22);
		content.setTextAlign(TextBox.LEFT);
		content.setString(str);
		content.setBoxSize((int)(159 * GameConfig.f_zoomx), (int)content.height());
		word_move_y = 0;
		if (content.height() > textheight * GameConfig.f_zoomy) {
			isScrollUp = true;
		}
	}
	
	
	public void drawItem(Canvas canvas, Sprite icon, Paint paint, int x, int y){
		item_x = x;
		item_y = y;
		//背景
		success.success[5].drawBitmap(canvas, success.success[5].bitmap,  x,  y, null);
		success.success[0].drawBitmap(canvas, success.success[0].bitmap,  x,  y + 128 * GameConfig.f_zoomy, null);
		icon.drawBitmap(canvas, icon.bitmap, x+36 * GameConfig.f_zoomx,  y+8 * GameConfig.f_zoomy, null);
		
		//条件达到了
//		success.success[6].drawBitmap(canvas, success.success[6].bitmap,  x + 112 * GameConfig.f_zoomx,  y - 10 * GameConfig.f_zoomy, null);
		if(ok_num!=size_num){
			success.success[7].drawBitmap(canvas, success.success[7].bitmap,  x + 112 * GameConfig.f_zoomx,  y - 10 * GameConfig.f_zoomy, null);
		}else
			success.success[6].drawBitmap(canvas, success.success[6].bitmap,  x + 112 * GameConfig.f_zoomx,  y - 10 * GameConfig.f_zoomy, null);
		
		if(!isGet){
			//金币icon
			success.success[4].drawBitmap(canvas, success.success[4].bitmap,  x + 133 * GameConfig.f_zoomx,  y + 2 * GameConfig.f_zoomy, null);
		}else
			success.success[8].drawBitmap(canvas, success.success[8].bitmap,  x + 133 * GameConfig.f_zoomx,  y + 2 * GameConfig.f_zoomy, null);
		
		
		//需要达到的值
		String number = ""+gold;
		int color = paint.getColor();
		float textsize = paint.getTextSize();
		paint.setColor(Color.WHITE);
		paint.setTextSize(20 * GameConfig.f_zoomx);
		canvas.drawText(number,  x + 112 * GameConfig.f_zoomx+((success.success[6].bitmap.getWidth()-paint.measureText(number))/2), y+44 * GameConfig.f_zoomy, paint);

		paint.setColor(color);
		paint.setTextSize(textsize);
		
		int w = success.success[3].bitmap.getWidth();
		if(ok_num<size_num){
			//白条背景
			success.success[2].drawBitmap(canvas, success.success[2].bitmap,  x + 45 * GameConfig.f_zoomx,  y + 114 * GameConfig.f_zoomy, null);
			
			int dangqian = ok_num;
			int size = size_num;
			int bfb = 100 * dangqian / size;
			int cs = 100;
			bfb = bfb * w / 100;
			cs = w;
			// 加成的经验条
			canvas.save();
			canvas.clipRect(x + 45 * GameConfig.f_zoomx,  y + 114 * GameConfig.f_zoomy, x +(w * bfb / cs) + (45) * GameConfig.f_zoomx , y+success.success[3].bitmap.getHeight()+( 114) * GameConfig.f_zoomy);
			success.success[3].drawBitmap(canvas, success.success[3].bitmap,  x + 45 * GameConfig.f_zoomx,  y + 114 * GameConfig.f_zoomy, null);
			canvas.restore();	
			success.success_S_num_1[0].drawBitmap(canvas, success.success_S_num_1, x+(int)(127 * GameConfig.f_zoomx), y + (int)(109 * GameConfig.f_zoomy), GameConfig.Char_num6, ok_num+"%", null, (int)(-3 * GameConfig.f_zoomx), 1.0f);
		}else{
			success.success_S_num_1[0].drawBitmap(canvas, success.success_S_num_1, x+(int)(118 * GameConfig.f_zoomx), y + (int)(109 * GameConfig.f_zoomy), GameConfig.Char_num6, 100+"%", null, (int)(-3 * GameConfig.f_zoomx), 1.0f);
		}
		canvas.save();
		canvas.clipRect((int)(x + 7 * GameConfig.f_zoomx), (int)(y+131 * GameConfig.f_zoomy), x +( 7  + 159) * GameConfig.f_zoomx, y+( 131+textheight) * GameConfig.f_zoomy);
		content.paintText(canvas, (int)(x +7 * GameConfig.f_zoomx), (int)(y+131 * GameConfig.f_zoomy) + word_move_y);
		canvas.restore();	
		if(state != 0 && ok_num==size_num){
			
			int x1 = x + (int)(((success.success[5].bitmap.getWidth() - (int)((134)* GameConfig.f_zoomx ))/2));
			int y1 = y + (int) (198 * GameConfig.f_zoomy);
			if (anjianbutton) {
				success.s_share_ui_button[1].drawBitmap(canvas, null,  x1, y1, (int) (134 * GameConfig.f_zoomx), -1);
			} else {
				success.s_share_ui_button[0].drawBitmap(canvas, null,  x1, y1, (int) (134 * GameConfig.f_zoomx), -1);
			}
			x1 = x + (int)(((success.success[5].bitmap.getWidth() - success.success[1].bitmap.getWidth())/2));
			y1 = y1+(success.s_share_ui_button[1].bitmap.getHeight() -  success.success[1].bitmap.getHeight()>>1);
			success.success[1].drawBitmap(canvas, x1 - (anjianbutton ? 0.2f : 0f) * success.success[1].bitmap.getWidth() / 2, y1 - (anjianbutton ? 0.2f : 0f) * success.success[1].bitmap.getHeight() / 2, anjianbutton ? 1.2f : 1.0f, anjianbutton ? 1.2f : 1.0f, 255, 0, 0, 0);
		}
	}
	
	public void run(){
//		for (int i=0;i<content.length; i++) {
			if (isScrollUp) {
				if (word_move_y < - content.height()) {
					word_move_y = (int) ((textheight + 10) * GameConfig.f_zoomy); 
				} else {
					word_move_y -= 2;
				}			
			}
//		}
	}
	
	public int onTouch(MotionEvent event){
		float x_touch = event.getX();
		float y_touch = event.getY();
		if(state == 0 && ok_num!=size_num)return -1;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if(ok_num==size_num){
					int x1 = item_x + (int)(((success.success[5].bitmap.getWidth() - (int)((134)* GameConfig.f_zoomx ))/2));
					int y1 = item_y + (int) (198 * GameConfig.f_zoomy);
					if (ExternalMethods.CollisionTest(x_touch, y_touch, x1, y1, x1+(int) (134 * GameConfig.f_zoomx), y1 + success.s_share_ui_button[1].bitmap.getHeight())) {
						anjianbutton = true;
					}
				}
		}else if (event.getAction() == MotionEvent.ACTION_UP) {
				if(ok_num==size_num){
					int x1 = item_x + (int)(((success.success[5].bitmap.getWidth() - (int)((134)* GameConfig.f_zoomx ))/2));
					int y1 = item_y + (int) (198 * GameConfig.f_zoomy);
					if (anjianbutton && ExternalMethods.CollisionTest(x_touch, y_touch, x1, y1, x1+(int) (134 * GameConfig.f_zoomx), y1 + success.s_share_ui_button[1].bitmap.getHeight())) {
						anjianbutton = false;
						return id;
					}
				}
				anjianbutton = false;
		}
			return -1;
	}
	
	public void delete(){
 

	 
		 
	}
 
	
}//end class
