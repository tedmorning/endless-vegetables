package com.game.item;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.endlessvegetables2.ui.GameStaticImage;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.TextBox;

/**
 * 排行榜的item
 * @author Administrator
 *
 */
public class TopListItem {

	private static Sprite share_ui_line;
	public static Sprite share_ui_photo_04;
	//排名的奖杯
	public static Sprite word_num_no1;
	public static Sprite word_num_no2;
	public static Sprite word_num_no3;
	public static Sprite share_ui_photo_01;

	private Sprite icon;
	private int list_x, list_y;
    private TextBox name;
    private TextBox score;
    private TextBox rank;
	private int ranking = 0;
    public int index;
	
	public TopListItem(int _index, int _ranking, String _name, String _score, Bitmap icon){
		index = _index;
		ranking = _ranking;
		
		
		if(share_ui_line==null)
			share_ui_line = new Sprite(GameImage.getImage(GameStaticImage.share_ui_line));
		
		if (share_ui_photo_04 == null)
			share_ui_photo_04 = new Sprite(
					GameImage.getImage(GameStaticImage.share_ui_photo_04));

		if(share_ui_photo_01 == null){
			share_ui_photo_01 = new Sprite(
					GameImage.getImage(GameStaticImage.share_ui_photo_01));
			
		}
		if(word_num_no1 == null){
			word_num_no1 = new Sprite(
					GameImage.getImage(GameStaticImage.word_num_no1));
			word_num_no2 = new Sprite(
					GameImage.getImage(GameStaticImage.word_num_no2));
			word_num_no3 = new Sprite(
					GameImage.getImage(GameStaticImage.word_num_no3));
		}
		
		
		
		int width = share_ui_photo_04.bitmap.getWidth();
		int height = share_ui_photo_04.bitmap.getHeight();
		// 图片缩放
		if(icon!=null){
			Bitmap temp =	icon;
			temp = GameImage.zoomImage(temp, width - 6
					* GameConfig.f_zoomx, height - 6
					* GameConfig.f_zoomy);
			this.icon = new Sprite(temp);
		} 
		name = new TextBox();
		name.setTextAlign(TextBox.LEFT);
		name.setString(_name);
		name.setTextSize((int)(22*GameConfig.f_zoom));
		name.setDefaultColor(0xff99673b);
		name.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)name.height());
		
		score = new TextBox();
		score.setTextAlign(TextBox.LEFT);
		score.setString(_score);
		score.setTextSize((int)(18*GameConfig.f_zoom));
		score.setDefaultColor(0xffBD6D18);
		score.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)score.height());
		
		rank = new TextBox();
		rank.setTextAlign(TextBox.LEFT);
		rank.setString(""+ranking);
		rank.setTextSize((int)(30*GameConfig.f_zoom));
		rank.setDefaultColor(0xffBD6D18);
		rank.setBoxSize((int)(153 * GameConfig.f_zoomx), (int)rank.height());
	}
	
	public void paint(Canvas canvas, int list_x, int list_y){
		if(ranking == 1){
			word_num_no1.drawBitmap(canvas,
					word_num_no1.bitmap, 
					(int)(list_x),
					(int)(list_y), null);
		}else if(ranking == 2){
			word_num_no2.drawBitmap(canvas,
					word_num_no2.bitmap, 
					(int)(list_x),
					(int)(list_y), null);
		}else if(ranking == 3){
			word_num_no3.drawBitmap(canvas,
					word_num_no3.bitmap, 
					(int)(list_x),
					(int)(list_y), null);
		}else{
			rank.paintText(canvas, 
					list_x+((word_num_no1.bitmap.getWidth()-(int)(30*GameConfig.f_zoom))/2),
					list_y+(int)(16 * GameConfig.f_zoomy));
			
		}
		list_x = list_x+(int)(50 * GameConfig.f_zoomx);
		list_y = list_y-(int)(10 * GameConfig.f_zoomx);
		this.list_x = list_x;
		this. list_y= list_y;
		if(icon!=null){
			icon.drawBitmap(canvas, icon.bitmap,
					(int)(list_x)+3 * GameConfig.f_zoomx,
					(int)(list_y)+3 * GameConfig.f_zoomy, null);
			
			share_ui_photo_04.drawBitmap(canvas,
					share_ui_photo_04.bitmap, 
					(int)(list_x),
					(int)(list_y), null);
			
		}else{
			share_ui_photo_01.drawBitmap(canvas,
					share_ui_photo_01.bitmap, 
					(int)(list_x),
					(int)(list_y), null);
		}
		
		
		
		//名字
		name.paintText(canvas, 
				(int)(list_x+70 * GameConfig.f_zoomx),
				(int)(list_y+9 * GameConfig.f_zoomy));
		//内容
		score.paintText(canvas, 
				list_x+(int)(70 * GameConfig.f_zoomx),
				list_y+(int)(36 * GameConfig.f_zoomy));
		
		share_ui_line.drawBitmap(canvas, null,
				(int)(list_x+9 * GameConfig.f_zoomx-(int)(50 * GameConfig.f_zoomx)),
				(int)(list_y+68* GameConfig.f_zoomy),
				(int) (395 * GameConfig.f_zoomx),
				 -1);
	}
	
	
	public void delete(){
		
		if(icon!=null){
			GameImage.delImage(icon.bitmap);
			if(icon.bitmap!=null)
				icon.bitmap = null;
			icon = null;
		}
		
		if(share_ui_line!=null){
			GameImage.delImage(share_ui_line.bitmap);
			if(share_ui_line.bitmap!=null)
				share_ui_line.bitmap = null;
			share_ui_line = null;
		}
		
		if(share_ui_photo_01!=null){
			GameImage.delImage(share_ui_photo_01.bitmap);
			if(share_ui_photo_01.bitmap!=null)
				share_ui_photo_01.bitmap = null;
			share_ui_photo_01 = null;
		}
		
		if(word_num_no1!=null){
			GameImage.delImage(word_num_no1.bitmap);
			if(word_num_no1.bitmap!=null)
				word_num_no1.bitmap = null;
			word_num_no1 = null;
		}
		if(word_num_no2!=null){
			GameImage.delImage(word_num_no2.bitmap);
			if(word_num_no2.bitmap!=null)
				word_num_no2.bitmap = null;
			word_num_no2 = null;
		}
		if(word_num_no3!=null){
			GameImage.delImage(word_num_no3.bitmap);
			if(word_num_no3.bitmap!=null)
				word_num_no3.bitmap = null;
			word_num_no3 = null;
		}
		 
		if(share_ui_photo_04!=null){
			GameImage.delImage(share_ui_photo_04.bitmap);
			if(share_ui_photo_04.bitmap!=null)
				share_ui_photo_04.bitmap = null;
			share_ui_photo_04 = null;
		}
		
	    name.Close();
	    score.Close();
	    rank.Close();
	}
}//end class
