package com.endlessvegetables2.ui;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.facebook.FacebookOperation;
import com.facebook.sdk.FBInterface;
import com.game.data.FaceBookPlayer;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.Module;

/**
 * 排名变化
 * */
public class RankingChange extends Module{
	
	boolean anjianclose;
	boolean share;
	Sprite word_title_ranking_3;
	Sprite share_ui_back_12;
	private Sprite share_ui_photo_04;
	private Sprite share_num_10;
	private Sprite shareui_arrows_05;
	private Sprite shareui_arrows_06;
	private Sprite s_word_share;
	private Sprite share_ui_shine_2[];
	private Sprite word_num_11[];
	private Sprite word_num_09[];

	 
	Star star[];
	
	Paint paint ;
	Bitmap icon[]; //头像
	String name[]; //名字
    long score[]; //分数
    int level; //第几关超过了
    String facebookID;
    
    //自己 头像 名字  分数 排名的bg bg
    float myPoit_x[] = {118* GameConfig.f_zoomx, 195* GameConfig.f_zoomx, 196* GameConfig.f_zoomx, 98* GameConfig.f_zoomx, 92* GameConfig.f_zoomx, };
    float myPoit_y[] = {289 * GameConfig.f_zoomy, 294 * GameConfig.f_zoomy, 326 * GameConfig.f_zoomy, 335 * GameConfig.f_zoomy, 257 * GameConfig.f_zoomy, };
    //好友 头像 名字  分数 排名的bg bg
    float friendPoit_x[] = {335* GameConfig.f_zoomx, 224* GameConfig.f_zoomx, 165* GameConfig.f_zoomx, 401* GameConfig.f_zoomx, 124* GameConfig.f_zoomx, };
    float friendPoit_y[] = {464 * GameConfig.f_zoomy, 469 * GameConfig.f_zoomy, 501 * GameConfig.f_zoomy, 513 * GameConfig.f_zoomy, 432 * GameConfig.f_zoomy, };

	int my_x = 0;
	int my_y = 0;
	int friend_x = 0;
	int friend_y = 0;
    
    public RankingChange(int _level, String  _facebookID){
    	level = _level;
    	facebookID = _facebookID;
    }
    float star_x[] = {304* GameConfig.f_zoomx, 125* GameConfig.f_zoomx, 160* GameConfig.f_zoomx, 227* GameConfig.f_zoomx, 269* GameConfig.f_zoomx, 390* GameConfig.f_zoomx, 383* GameConfig.f_zoomx};
    float star_y[] = {318 * GameConfig.f_zoomy, 272 * GameConfig.f_zoomy, 324 * GameConfig.f_zoomy, 343 * GameConfig.f_zoomy, 257 * GameConfig.f_zoomy, 328 * GameConfig.f_zoomy, 264 * GameConfig.f_zoomy};
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Typeface font	= Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
		paint.setTypeface(font);
		paint.setColor(Color.WHITE);
		paint.setTextSize(20 * GameConfig.f_zoomx);
		if (share_ui_photo_04 == null)
			share_ui_photo_04 = new Sprite(
					GameImage.getImage(GameStaticImage.share_ui_photo_04));
		
		final int width = share_ui_photo_04.bitmap.getWidth();
		final int height = share_ui_photo_04.bitmap.getHeight();
		icon = new Bitmap[2];
		name = new String[2]; //名字
	    score = new long[2]; //分数
	    
		Bitmap temp = FBInterface.allIconMap.get(facebookID);//user.getIcon();
		if(temp!=null){
			temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx, height - 6 * GameConfig.f_zoomy);
			icon[1] =  temp;
		}else
			icon[1] =  GameStaticImage.s_share_ui_photo_01.bitmap;
		
		if(FacebookOperation.player==null || FacebookOperation.player.getIcon()== null){
			icon[0] =  GameStaticImage.s_share_ui_photo_01.bitmap;
		}else{
			temp = FacebookOperation.player.getIcon();
			temp = GameImage.zoomImage(temp, width - 6 * GameConfig.f_zoomx, height - 6 * GameConfig.f_zoomy);
			icon[0] =  temp;
		}
		
		if( FacebookOperation.player != null)
			name[0] = FacebookOperation.player.getName();
		else
			name[0] = "my出错";
		FaceBookPlayer player = FacebookOperation.playerMap.get(facebookID);
		if(player!=null)
			name[1] = player.getName();
		else
			name[1] ="friend出错";
		
		if(FacebookOperation.player!=null){
			FaceBookPlayer.UserLeveInfo info = FacebookOperation.player.getLG().get(level);
			if(info!=null)
				score[0] = info.score;
			
			if(player!=null){
				info = player.getLG().get(level);
				if(info!=null)
				 score[1] = info.score;
			}
		}else{
			score[0] =545432;
			score[1] =215355;
		}
		
		Random ran = new Random();
		star = new Star[star_x.length];
		for(int i=0;i<star.length;++i){
			star[i] = new Star();
			star[i].time = ran.nextInt(20);
			star[i].setXY(star_x[i], star_y[i]);
		}
		
		word_title_ranking_3 = new Sprite(GameImage.getImage(GameStaticImage.word_title_ranking_3));
		share_ui_back_12 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_12));
		share_num_10 = new Sprite(GameImage.getImage(GameStaticImage.share_num_10));
		shareui_arrows_05 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_05));
		shareui_arrows_06 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_arrows_06));
		s_word_share = new Sprite(GameImage.getImage(GameStaticImage.word_share));
		share_ui_shine_2 = GameImage.getAutoSizecutSprite(GameStaticImage.share_ui_shine_2, 3, 1, GameImage.Sort_line);

		word_num_11 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_11, 11, 1, GameImage.Sort_line);
		word_num_09 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_09, 10, 1, GameImage.Sort_line);
		return false;
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		GameStaticImage.s_share_ui_back_01.drawBitmap(canvas, null, (int) (29 * GameConfig.f_zoomx), (int) (115 * GameConfig.f_zoomy), (int) (476 * GameConfig.f_zoomx), (int) (612 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02.drawBitmap(canvas, null, (int) (46 * GameConfig.f_zoomx), (int) (178 * GameConfig.f_zoomy), (int) (442 * GameConfig.f_zoomx), (int) (456 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_back_02_2.drawBitmap(canvas, null, (int) (46 * GameConfig.f_zoomx), (int) (178 * GameConfig.f_zoomy), (int) (442 * GameConfig.f_zoomx), (int) (456 * GameConfig.f_zoomy), -1);
		GameStaticImage.s_share_ui_close.drawBitmap(canvas, 453 * GameConfig.f_zoomx - GameStaticImage.s_share_ui_close.bitmap.getWidth() / 2 * (anjianclose ? 0.2f : 0f), 106 * GameConfig.f_zoomy - GameStaticImage.s_share_ui_close.bitmap.getHeight() / 2 * (anjianclose ? 0.2f : 0f), anjianclose ? 1.2f : 1f, anjianclose ? 1.2f : 1f, 255, 0, 0, 0);
		word_title_ranking_3.drawBitmap(canvas, word_title_ranking_3.bitmap, 196 * GameConfig.f_zoomx, 137 * GameConfig.f_zoomy, null);
		
		
		float bg_x = 56 * GameConfig.f_zoomx;
		float bg_y = 644 * GameConfig.f_zoomy;
		GameStaticImage.s_share_ui_button_05.drawBitmap(canvas,  bg_x, bg_y,  1f, 1f, 255, 0, 0, 0);
		 
		bg_x = bg_x+(GameStaticImage.s_share_ui_button_05.bitmap.getWidth() - GameStaticImage.s_share_ui_button_05_1.bitmap.getWidth())/2;
		bg_y = bg_y+(GameStaticImage.s_share_ui_button_05.bitmap.getHeight() - GameStaticImage.s_share_ui_button_05_1.bitmap.getHeight())/2;
		if(!share){
			GameStaticImage.s_share_ui_button_05_1.drawBitmap(canvas, bg_x , bg_y,  1f, 1f, 255, 0, 0, 0);
		}else
			GameStaticImage.s_share_ui_button_05_2.drawBitmap(canvas, bg_x , bg_y,  1f, 1f, 255, 0, 0, 0);
		
		bg_x = bg_x+(GameStaticImage.s_share_ui_button_05.bitmap.getWidth() - s_word_share.bitmap.getWidth())/2;
		bg_y = bg_y+(GameStaticImage.s_share_ui_button_05.bitmap.getHeight() - s_word_share.bitmap.getHeight())/2;
		s_word_share.drawBitmap(canvas, bg_x , bg_y,  1f, 1f, 255, 0, 0, 0);
		
		paint.setTextSize(20*GameConfig.f_zoom);
		paint.setColor(0xffffffff);
		FontMetrics fm = paint.getFontMetrics();
	    int textHeight = (int) Math.ceil(fm.descent - fm.top);
		for(int i=0;i<icon.length;++i){
			if(!isStop){
				if(i ==1){ //好友
					share_ui_back_12.drawBitmap(canvas, share_ui_back_12.bitmap, myPoit_x[4]+friend_x, myPoit_y[4]+friend_y, null);
					canvas.drawBitmap( icon[i], myPoit_x[0]+friend_x	, myPoit_y[0]+friend_y, null);
					share_ui_photo_04.drawBitmap(canvas, share_ui_photo_04.bitmap, myPoit_x[0]+friend_x, myPoit_y[0]+friend_y, null);
					canvas.drawBitmap( share_num_10.bitmap, myPoit_x[3]+friend_x	, myPoit_y[3]+friend_y, null);
					
					String rank = ""+(FriendScore.mySurpass-1);
					int x = (int)(myPoit_x[3]+friend_x)+(( share_num_10.bitmap.getWidth() - (word_num_09[0].bitmap.getWidth()*rank.length() + 1*(rank.length()-1)))/2);
					word_num_09[0].drawBitmap(canvas, word_num_09, x, (int)(myPoit_y[3]+friend_y+((share_num_10.bitmap.getHeight() - word_num_09[0].bitmap.getHeight())/2)), GameConfig.Char_num1, rank, null, 0, 1);
					
					canvas.drawText(name[i], myPoit_x[1]+friend_x, myPoit_y[0]+textHeight+friend_y, paint);
//					canvas.drawText(""+score[i], myPoit_x[2]+friend_x, myPoit_y[2]+friend_y+textHeight, paint);
					String score = ExternalMethods.LongToStr_En(this.score[i]);
					x = (int)(myPoit_x[2])+friend_x;//-(word_num_11[0].bitmap.getWidth()*score.length() + 1*(score.length()-1))
					word_num_11[0].drawBitmap(canvas,word_num_11, x,  (int)(myPoit_y[2]+friend_y), GameConfig.Char_num5,score, null, 0, 1);
					
				}else if(i ==0){ //自己
					share_ui_back_12.drawBitmap(canvas, share_ui_back_12.bitmap, friendPoit_x[4]+my_x, friendPoit_y[4]+my_y, null);
					canvas.drawBitmap( icon[i], friendPoit_x[0]+my_x	, friendPoit_y[0]+my_y, null);
					share_ui_photo_04.drawBitmap(canvas, share_ui_photo_04.bitmap, friendPoit_x[0]+my_x, friendPoit_y[0]+my_y, null);
					canvas.drawBitmap(share_num_10.bitmap, friendPoit_x[0]+icon[i].getWidth()-15*GameConfig.f_zoomx	+my_x, friendPoit_y[3]+my_y, null);

					String rank = ""+(FriendScore.mySurpass);
					int x = (int)(friendPoit_x[0]+icon[i].getWidth()-15*GameConfig.f_zoomx	+my_x)+((( share_num_10.bitmap.getWidth() - (word_num_09[0].bitmap.getWidth()*rank.length() + 1*(rank.length()-1)))/2));
					word_num_09[0].drawBitmap(canvas, word_num_09, x, (int)(friendPoit_y[3]+my_y+((share_num_10.bitmap.getHeight() - word_num_09[0].bitmap.getHeight())/2)), GameConfig.Char_num1, rank, null, 0, 1);
					
					canvas.drawText(name[i],  friendPoit_x[0]-paint.measureText(name[i])+my_x, friendPoit_y[0]+textHeight+my_y, paint);
//					canvas.drawText(""+score[i], friendPoit_x[0]-paint.measureText(""+score[i])+my_x, friendPoit_y[2]+textHeight+my_y, paint);
					String score = ExternalMethods.LongToStr_En(this.score[i]);
					x = (int)(friendPoit_x[0]-(word_num_11[0].bitmap.getWidth()*score.length() + 1*(score.length()-1)));
					word_num_11[0].drawBitmap(canvas,word_num_11, x+my_x,  (int)(friendPoit_y[2]+my_y), GameConfig.Char_num5,score, null, 0, 1);
				}
			}else{
				if(i ==0){ //自己
					share_ui_back_12.drawBitmap(canvas, share_ui_back_12.bitmap, myPoit_x[4]+friend_x, myPoit_y[4]+friend_y, null);
					canvas.drawBitmap( icon[i], myPoit_x[0]+friend_x	, myPoit_y[0]+friend_y, null);
					share_ui_photo_04.drawBitmap(canvas, share_ui_photo_04.bitmap, myPoit_x[0]+friend_x, myPoit_y[0]+friend_y, null);
					canvas.drawBitmap( share_num_10.bitmap, myPoit_x[3]+friend_x	, myPoit_y[3]+friend_y, null);

					String rank = ""+(FriendScore.mySurpass);
					int x = (int)( myPoit_x[3]+friend_x)+((( share_num_10.bitmap.getWidth() - (word_num_09[0].bitmap.getWidth()*rank.length() + 1*(rank.length()-1)))/2));
					word_num_09[0].drawBitmap(canvas, word_num_09, x, (int)(myPoit_y[3]+friend_y+((share_num_10.bitmap.getHeight() - word_num_09[0].bitmap.getHeight())/2)), GameConfig.Char_num1, rank, null, 0, 1);
				
					canvas.drawText(name[i], myPoit_x[1]+friend_x, myPoit_y[0]+textHeight+friend_y, paint);
//					canvas.drawText(""+score[i], myPoit_x[2]+friend_x, myPoit_y[2]+textHeight+friend_y, paint);
					String score = ExternalMethods.LongToStr_En(this.score[i]);
					x = (int)(myPoit_x[2]);//-(word_num_11[0].bitmap.getWidth()*score.length() + 1*(score.length()-1))
					word_num_11[0].drawBitmap(canvas,word_num_11, x+friend_x,  (int)(myPoit_y[2]+friend_y), GameConfig.Char_num5,score, null, 0, 1);
				
				}else if(i ==1){ //好友
					share_ui_back_12.drawBitmap(canvas, share_ui_back_12.bitmap, friendPoit_x[4]+my_x, friendPoit_y[4]+my_y, null);
					canvas.drawBitmap( icon[i], friendPoit_x[0]+my_x	, friendPoit_y[0]+my_y, null);
					share_ui_photo_04.drawBitmap(canvas, share_ui_photo_04.bitmap, friendPoit_x[0]+my_x, friendPoit_y[0]+my_y, null);
					canvas.drawBitmap(share_num_10.bitmap, friendPoit_x[0]+my_x+icon[i].getWidth()-15*GameConfig.f_zoomx, friendPoit_y[3]+my_y, null);
					String rank = ""+(FriendScore.mySurpass-1);
					int x = (int)(friendPoit_x[0]+my_x+icon[i].getWidth()-15*GameConfig.f_zoomx)+((( share_num_10.bitmap.getWidth() - (word_num_09[0].bitmap.getWidth()*rank.length() + 1*(rank.length()-1)))/2));
					word_num_09[0].drawBitmap(canvas, word_num_09, x, (int)(friendPoit_y[3]+my_y+((share_num_10.bitmap.getHeight() - word_num_09[0].bitmap.getHeight())/2)), GameConfig.Char_num1, rank, null, 0, 1);
					
					canvas.drawText(name[i],  friendPoit_x[0]-paint.measureText(name[i])+my_x, friendPoit_y[0]+textHeight+my_y, paint);
//					canvas.drawText(""+score[i], friendPoit_x[0]-paint.measureText(""+score[i])+my_x, friendPoit_y[2]+textHeight+my_y, paint);
					String score = ExternalMethods.LongToStr_En(this.score[i]);
					x = (int)(friendPoit_x[0]-(word_num_11[0].bitmap.getWidth()*score.length() + 1*(score.length()-1)));
					word_num_11[0].drawBitmap(canvas,word_num_11, x+my_x,  (int)(friendPoit_y[2]+my_y), GameConfig.Char_num5,score, null, 0, 1);
				}
			} 
				
		}
		
		
		if(isStop){
			canvas.drawBitmap( shareui_arrows_05.bitmap, arrows2_x[arrows]	, arrows2_y[arrows], null);
			canvas.drawBitmap( shareui_arrows_06.bitmap, arrows1_x[arrows]	, arrows1_y[arrows], null);
			
			if(arrows == 2){
				for(int i=0;i<star.length;++i){
					star[i].draw(canvas);
				}
			}
			
		}
		
		
	    
	}

	int interval[] = {40, 50, 50};
	int index = 0;
	boolean isStop = false;
	
	//蓝色
	float arrows1_x[] = { -24* GameConfig.f_zoomx, 37* GameConfig.f_zoomx, 72* GameConfig.f_zoomx};
	float arrows1_y[] = { 150* GameConfig.f_zoomy, 305* GameConfig.f_zoomy, 419* GameConfig.f_zoomy};

	//黄色
	float arrows2_x[] = { 492* GameConfig.f_zoomx, 430* GameConfig.f_zoomx, 370* GameConfig.f_zoomx};
	float arrows2_y[] = { 479* GameConfig.f_zoomy, 346* GameConfig.f_zoomy, 220* GameConfig.f_zoomy};
	int arrows = 0;
	boolean isArrowsStop = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!isStop){
			if(GameConfig.i_coke % 3 ==0){
				if(index<interval.length){
					my_x -=8*GameConfig.f_zoomx;
					my_y -= interval[index]*GameConfig.f_zoomy;
					friend_x += 8*GameConfig.f_zoomx;
					friend_y += interval[index]*GameConfig.f_zoomy;
					index++;
				}else{
					 isStop = true;
					 my_x = 0;
					 my_y = 0;
					 friend_x = 0;
					 friend_y = 0;
				}
				
			}
		}else if(!isArrowsStop){
			if(GameConfig.i_coke % 3 ==0){
				arrows++;
				if(arrows==2)
					isArrowsStop = true;
			}
		}else if(isArrowsStop){
				for(int i=0;i<star.length;++i){
					star[i].run();
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
		GameImage.delImage(word_title_ranking_3.bitmap);
		word_title_ranking_3 = null;
		
		GameImage.delImage(share_ui_photo_04.bitmap);
		share_ui_photo_04 = null;
		
		GameImage.delImage(share_ui_back_12.bitmap);
		share_ui_back_12 = null;
		GameImage.delImage(share_num_10.bitmap);
		share_num_10 = null;
		

		GameImage.delImage(shareui_arrows_05.bitmap);
		shareui_arrows_05 = null;
		
		GameImage.delImage(shareui_arrows_06.bitmap);
		shareui_arrows_06 = null;

		GameImage.delImage(s_word_share.bitmap);
		s_word_share = null;
		
		GameImage.delImageArray(share_ui_shine_2);
		share_ui_shine_2 = null;
		
		GameImage.delImageArray(word_num_11);
		word_num_11 = null;
		
		GameImage.delImageArray(word_num_09);
		word_num_09 = null;
		
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
					x, y,
					456* GameConfig.f_zoomx- GameStaticImage.s_share_ui_close.bitmap.getWidth() / 2 * 0.2f,
					106* GameConfig.f_zoomy- GameStaticImage.s_share_ui_close.bitmap.getHeight() / 2 * 0.2f,
					456* GameConfig.f_zoomx+ GameStaticImage.s_share_ui_close.bitmap.getWidth() * 1.2f,
					106* GameConfig.f_zoomy+ GameStaticImage.s_share_ui_close.bitmap.getHeight() * 1.2f)) {
				anjianclose = true;
			}else if (ExternalMethods.CollisionTest(
					x, y,
					56 * GameConfig.f_zoomx,
					644 * GameConfig.f_zoomy ,
					56* GameConfig.f_zoomx+ GameStaticImage.s_share_ui_button_05.bitmap.getWidth(),
					644* GameConfig.f_zoomy+ GameStaticImage.s_share_ui_button_05.bitmap.getHeight())) {
				share = true;
			}
		 
			
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			if(anjianclose && ExternalMethods.CollisionTest(
					x,y,
					456* GameConfig.f_zoomx- GameStaticImage.s_share_ui_close.bitmap.getWidth() / 2 * 0.2f,
					106* GameConfig.f_zoomy- GameStaticImage.s_share_ui_close.bitmap.getHeight() / 2 * 0.2f,
					456* GameConfig.f_zoomx+ GameStaticImage.s_share_ui_close.bitmap.getWidth() * 1.2f,
					106* GameConfig.f_zoomy+ GameStaticImage.s_share_ui_close.bitmap.getHeight() * 1.2f)){
				GameManager.ChangeModule(null);
			}else if (share && ExternalMethods.CollisionTest(
					x, y,
					56 * GameConfig.f_zoomx,
					644 * GameConfig.f_zoomy,
					56* GameConfig.f_zoomx+ GameStaticImage.s_share_ui_button_05.bitmap.getWidth(),
					644* GameConfig.f_zoomy+ GameStaticImage.s_share_ui_button_05.bitmap.getHeight())) {
				if(FacebookOperation.isLanding ){
					FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_PULISH);
				}
			}
			share = false;
		}
	}
	class Star{
		int index; 
		int time;
		float x,y;
		public void setXY(float _x, float _y){
			x = _x;
			y = _y;
		}
		public void run(){
				time--;
			if(time <= 0){
				if(Math.abs(time)%3 == 0){
					if(index==2)
						index = -1;
						index++;
				}
			}
		}
		
		public void draw(Canvas canvas){
			if(time <= 0){
				canvas.drawBitmap( share_ui_shine_2[index].bitmap, x	, y, null);
			}
			
		}
			
			
		
	}
}//end class
