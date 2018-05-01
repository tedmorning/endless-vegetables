package com.kokatlaruruxi.wy;


import java.util.Random;

import com.endlessvegetables2.ui.GameStaticImage;
import com.endlessvegetables2.ui.GameWord;
import com.endlessvegetables2.ui.Location;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.Loading;
import com.socoGameEngine.TextBox;
import com.tools.GameTools;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Region;
import android.view.KeyEvent;
import android.view.MotionEvent;


public class GameLoading extends Loading{
	Bitmap loadingbg;
	Bitmap[] load2;
	Bitmap load1;
	TextBox tipBox;
	Sprite image[];
	Paint paint;
	int tempi = 0;
	private int waiting; 
	
	RectF r3;                           //RectF对象     
	RectF r2;                           //RectF对象     
	public  GameLoading(){
		
	
	}
	public void initwordpic(){
		loadingbg = CreateZoomImage(GameStaticImage.interface_BG_01);//new Sprite(GameImage.getImage(GameStaticImage.interface_BG_01));
		load2 = getAutoSizecutSprite(GameStaticImage.interface_loading_02, 3, 1, GameImage.Sort_line);//GameImage.getAutoSizecutSprite(GameStaticImage.interface_loading_02, 3, 1, GameImage.Sort_line);
		load1 =  CreateZoomImage(GameStaticImage.interface_loading_01);//new Sprite(GameImage.getImage(GameStaticImage.interface_loading_01));
		image = new Sprite[2];
		String name[] = {GameStaticImage.share_ui_back_01, GameStaticImage.share_tips};
		for(int i=0;i<image.length;++i){
			image[i] = new Sprite();
			image[i].bitmap = CreateZoomImage(name[i]);
		}
		paint = new Paint();
		r3=new RectF();
		r3.left=(int) ((35-3) * GameConfig.f_zoomx);                                 //左边      
		r3.top=(int) ((678-3) * GameConfig.f_zoomy);                                 //上边      
		r3.right=(int) ((35+472+3) * GameConfig.f_zoomx);                                   //右边     
		r3.bottom=(int) ((678+131+3) * GameConfig.f_zoomy);  

		r2=new RectF();
		r2.left=(int) (35 * GameConfig.f_zoomx);                                 //左边      
		r2.top=(int) (678 * GameConfig.f_zoomy);                                 //上边      
		r2.right=(int) ((35+472) * GameConfig.f_zoomx);                                   //右边     
		r2.bottom=(int) ((678+131) * GameConfig.f_zoomy);         
//		image[0] = CreateZoomImage();//new Sprite(GameImage.getImage(GameStaticImage.interface_BG_01));
//		image[1] = CreateZoomImage(GameStaticImage.share_tips);//new Sprite(GameImage.getImage(GameStaticImage.interface_BG_01));
		
		//		String temp = ;
		int index = 0;
		String tip[] = {
				/** 合理使用道具，能够帮助你通过关卡哦！ */
				LangDefineClient.TIPS_1,
				/** 商店中可以抽取强力卡片哦！ */
				LangDefineClient.TIPS_2,
				/** 强力的弹弓和城墙能够让你变的更强大！ */
				LangDefineClient.TIPS_3,
				/** 缺少金币的话，可以去简单的关卡打怪刷金币哦！ */
				LangDefineClient.TIPS_4,
				/** 使用乱舞只要滑动手指就能消灭怪物哦！ */
				LangDefineClient.TIPS_5,
				/** 战斗中可以尝试使用好友援助来帮助过关！ */
				LangDefineClient.TIPS_6,
				/** 菜园可以种植收获卡片、金币、爱心哦！ */
				LangDefineClient.TIPS_7,
				/** 每次使用卡片可是会消耗卡片数量的哦！ */
				LangDefineClient.TIPS_8,
				
		};
		int star = 0;
		int end = tip.length-1;
		index =new Random().nextInt((end-star)+1)+star;
		
		tipBox = new TextBox();
		tipBox.setTextAlign(TextBox.HCENTER);
		tipBox.setString(LangUtil.getLangString(tip[index]));
		tipBox.setBoxSize((int)(421 * GameConfig.f_zoomx), (int)(1000 * GameConfig.f_zoomy));
		tipBox.setTextSize((int)(20*GameConfig.f_zoom));
		tipBox.setDefaultColor(Color.argb(255, 255, 255, 255));
		tipBox.height();
		tipBox.setBoxSize((int)(421 * GameConfig.f_zoomx), (int)tipBox.height());
		
	}
	
	public void paint(Canvas canvas) {
		
//		if(loadingbg!=null&&loadingbg.bitmap!=null)
//		loadingbg.drawBitmap(canvas, loadingbg.bitmap, GameConfig.GameScreen_Width/2-loadingbg.bitmap.getWidth()/2,GameConfig.GameScreen_Height/2-loadingbg.bitmap.getHeight()/2,null);
		if(loadingbg!=null)
			canvas.drawBitmap(loadingbg, GameConfig.GameScreen_Width/2-loadingbg.getWidth()/2,GameConfig.GameScreen_Height/2-loadingbg.getHeight()/2, null);
		
		if(load1!=null)
//		load1.drawBitmap(canvas, load1.bitmap, GameConfig.GameScreen_Width/2 - load1.bitmap.getWidth()/2, GameConfig.GameScreen_Height/2 + load1.bitmap.getHeight() + 5 * GameConfig.f_zoom, null);
		canvas.drawBitmap(load1, GameConfig.GameScreen_Width/2 - load1.getWidth()/2, GameConfig.GameScreen_Height/2 + load1.getHeight() + 5 * GameConfig.f_zoom, null);
		
		tempi = 0;
		if (GameConfig.i_coke % 8 < 8) {
			tempi = GameConfig.i_coke % 8 / 4 % 4; 
			if (tempi == 3) tempi = 1;
			
			if(load2!=null&&load2[tempi]!=null)//&&load2[tempi].bitmap!=null
				canvas.drawBitmap(load2[tempi], GameConfig.GameScreen_Width/2 - load2[tempi].getWidth()/2, GameConfig.GameScreen_Height/2 - load2[tempi].getHeight() - 5 * GameConfig.f_zoom, null);
			
		}	
		if(paint!=null){
			paint.setAntiAlias(true);                       //设置画笔为无锯齿     
			paint.setStrokeWidth((float) 3.0);              //线宽      
			paint.setColor(Color.argb(255, 1, 141, 168));                    //设置画笔颜色      
			if(r3!=null)
				canvas.drawRoundRect(r3, 40, 40, paint);        //绘制圆角矩形  
			paint.setColor(Color.argb(255, 21, 175, 208));                    //设置画笔颜色      
			if(r2!=null)
				canvas.drawRoundRect(r2, 40, 40, paint);        //绘制圆角矩形  
			canvas.drawBitmap(image[1].bitmap, 65*GameConfig.f_zoomx,  (int) (678 * GameConfig.f_zoomy)-image[1].bitmap.getHeight()/2, null);
		}
 		if(tipBox!=null && tipBox.width==(int)(421 * GameConfig.f_zoomx)){
			tipBox.paintText(canvas, (int)(60*GameConfig.f_zoomx), (int) (678 * GameConfig.f_zoomy)+image[1].bitmap.getHeight());
 		}
 	}
	
	public void run() {
		waiting ++ ;
		
		if(waiting>4)
		{
			B_state = LOADINGOVER;
		}
	}
	public byte getLoadingState(){
		return B_state;
	}
	
	long t;

	@Override
	public boolean initialize() {
		initwordpic();
//		GameMedia.pauseMusicGameLoading();
		
		waiting = 0;
		B_state = LOADING;
		
		t = System.currentTimeMillis();
		
		return false;
	}

	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public void onTouchEvent(MotionEvent event) {
//		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		return false;
	}
	public byte release() {
//		GameImage.delImage(loadingbg.bitmap);
//		loadingbg=null;
		
		 if(loadingbg != null && !loadingbg.isRecycled())
			 loadingbg.recycle();	
		 loadingbg = null;
		 
		 for(int i=0;i<load2.length;++i){
			 if(load2 != null && !load2[i].isRecycled())
				 load2[i].recycle();	
			 load2[i] = null;
		 }
		 load2 = null;
		 
		 if(load1 != null && !load1.isRecycled())
			 load1.recycle();	
		 load1 = null;
		 if(tipBox!=null)
			 tipBox.Close();
		 tipBox = null;
		 
		 for(int i=0;i<image.length;++i){
			 if(image != null)
				 image[i].recycleBitmap();	
			 image[i] = null;
		 }
		 image = null;
		 paint = null;
		 r3= null;                           //RectF对象     
		 r2= null;     
//		GameImage.delImageArray(load2);
//		load2 = null;
//		GameImage.delImage(load1.bitmap);
//		load1 = null;
		
		return 0;
	}
	public void Release() {
		
	}
	 private static final Bitmap CreateZoomImage(String fileName){
		 Bitmap bitmaptemp = GameImage.getFromAssets(fileName);
		 Bitmap resultBitmap = null; 
		 if(bitmaptemp != null){
			 resultBitmap = GameImage.zoomImage(bitmaptemp, bitmaptemp.getWidth()*GameConfig.f_zoomx, bitmaptemp.getHeight()*GameConfig.f_zoomy);
		     bitmaptemp = null;
		 }
		 return resultBitmap;
	 }
	 
    public static Bitmap[] getAutoSizecutSprite(String fileName, int line, int row, byte sort_type)
    {
       
    	Bitmap tempBit = GameImage.getFromAssets(fileName);
    	
    	Bitmap tempBitArray[] = new Bitmap[line*row];
    	for(int i = 0;i<tempBitArray.length;i++){
			Bitmap _tempbitmap = null;
			if(sort_type == GameImage.Sort_line)
				
				_tempbitmap = Bitmap.createBitmap(tempBit, i/row*tempBit.getWidth()/line, i%row*tempBit.getHeight()/row,
					tempBit.getWidth()/line, tempBit.getHeight()/row);
			if(sort_type == GameImage.Sort_row)
				_tempbitmap = Bitmap.createBitmap(tempBit, i%line*tempBit.getWidth()/line, i/line*tempBit.getHeight()/row,
    					tempBit.getWidth()/line, tempBit.getHeight()/row);
			
			tempBitArray[i] = GameImage.zoomImage(_tempbitmap ,_tempbitmap.getWidth()*GameConfig.f_zoom, _tempbitmap.getHeight()*GameConfig.f_zoom);
			_tempbitmap = null;
			
			
		}
        return tempBitArray;
    }
	 
}
