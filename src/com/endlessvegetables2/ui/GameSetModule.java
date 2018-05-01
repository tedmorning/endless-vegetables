package com.endlessvegetables2.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.audio.Music;
import com.kokatlaruruxi.wy.R;
import com.facebook.FacebookOperation;
import com.facebook.sdk.FBInterface;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.MainActivity;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

/**
 * 设置页面
 * @author Administrator
 *
 */
public class GameSetModule extends Module{
	
	private Sprite s_share_ui_back_01;
	private Sprite s_share_ui_back_02;
	private Sprite s_share_ui_back_02_2;
	private Sprite s_share_ui_close;
	private Sprite gs;
	private Sprite word_title_options;
	private Sprite share_ui_line;
	private Sprite interface_icon_facebook_03;
	private Sprite share_ui_button_07;
	private Sprite share_ui_button_06;
	private Sprite share_ui_button_04;
	private Sprite share_ui_button_03;
	
	private Paint paint;
	private boolean anjianclose;
	private boolean login;
	//设置
    public static boolean isOpen[] = {true, true};
    private final int music = 0;
    private final int sound = 1;
    private boolean temp[] = new boolean[2];
	int switchxy[][] = {
			{355, 355},
			{277, 364}
	};
	//开关上的点 第一个的开点
	int switchPoit1[] = {
		415, 273	
	};
	//第二个点关闭
	int switchPoit2[] = {
			354, 360	
	};
	//文字第一个点的
	int switchText[] = {375, 288};
	int switchText1[] = {402, 376};

	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		isOpen[0] = !VeggiesData.isMuteMusic();
		isOpen[1] = !VeggiesData.isMuteSound();
		gs = new Sprite();
		if(s_share_ui_back_01==null)
			s_share_ui_back_01 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_01));

		if(s_share_ui_back_02==null)
			s_share_ui_back_02 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02));

		if(s_share_ui_back_02_2==null)
			s_share_ui_back_02_2 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_back_02_2));

		if(s_share_ui_close==null)
			s_share_ui_close = new Sprite(GameImage.getImage(GameStaticImage.share_ui_close));
		
		if(word_title_options==null)
			word_title_options = new Sprite(GameImage.getImage(GameStaticImage.word_title_options));
	
		if(share_ui_line==null)
			share_ui_line = new Sprite(GameImage.getImage(GameStaticImage.share_ui_line));
		
		if(interface_icon_facebook_03==null)
			interface_icon_facebook_03 = new Sprite(GameImage.getImage(GameStaticImage.interface_icon_facebook_03));
		
		if(share_ui_button_07==null)
			share_ui_button_07 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_07));
		
		if(share_ui_button_06==null)
			share_ui_button_06 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_06));
		
		if(share_ui_button_04==null)
			share_ui_button_04 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_04));
		
		if(share_ui_button_03==null)
			share_ui_button_03 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_03));
	
		
		Typeface font	= Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
		paint = new Paint();	
		paint.setTypeface(font);
		paint.setTextSize(25*GameConfig.f_zoomx);
		paint.setColor(0xffBD6D18);
		
		return false;
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		gs.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
    	
    	s_share_ui_back_01.drawBitmap(canvas, null, (int)(28 * GameConfig.f_zoomx), (int)(169 * GameConfig.f_zoomy), (int)(476 * GameConfig.f_zoomx), (int)(472 * GameConfig.f_zoomy), -1);
		s_share_ui_back_02.drawBitmap(canvas, null, (int)((45) * GameConfig.f_zoomx), (int)((232) * GameConfig.f_zoomy), (int)((442) * GameConfig.f_zoomx), (int)((394) * GameConfig.f_zoomy), -1);
		s_share_ui_back_02_2.drawBitmap(canvas, null, (int)((45) * GameConfig.f_zoomx), (int)((232) * GameConfig.f_zoomy), (int)((442) * GameConfig.f_zoomx), (int)((394) * GameConfig.f_zoomy), -1);
		s_share_ui_close.drawBitmap(canvas, (455)* GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth()/2*(anjianclose?0.2f:0f), (159) * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight()/2*(anjianclose?0.2f:0f), anjianclose?1.2f:1f, anjianclose?1.2f:1f, 255, 0, 0, 0);
		word_title_options.drawBitmap(canvas, word_title_options.bitmap, (int)(200 * GameConfig.f_zoomx), (int)(193 * GameConfig.f_zoomy), null);
		
		paint.setTextSize(25*GameConfig.f_zoomx);
		paint.setColor(0xffBD6D18);
		String temp = LangUtil.getLangString(LangDefineClient.OPTIONS_MUSIC);
		canvas.drawText(temp, (int)(74 * GameConfig.f_zoomx),(int)((292) * GameConfig.f_zoomy), paint);
		share_ui_line.drawBitmap(canvas, share_ui_line.bitmap, (int)(67 * GameConfig.f_zoomx), (int)(337 * GameConfig.f_zoomy), null);
		
		
		temp = LangUtil.getLangString(LangDefineClient.OPTIONS_SOUND);
		canvas.drawText(temp, (int)(74 * GameConfig.f_zoomx),(int)(380 * GameConfig.f_zoomy), paint);
		share_ui_line.drawBitmap(canvas, share_ui_line.bitmap, (int)(67 * GameConfig.f_zoomx), (int)(419 * GameConfig.f_zoomy), null);
		
		
//		interface_icon_facebook_03.drawBitmap(canvas, interface_icon_facebook_03.bitmap, (int)(67 * GameConfig.f_zoomx), (int)(433 * GameConfig.f_zoomy), null);
//		//share_ui_button_03.drawBitmap(canvas, null, (int)(348 * GameConfig.f_zoomx), (int)(433 * GameConfig.f_zoomy), (int)(112 * GameConfig.f_zoomx), -1);
//		share_ui_button_03.drawBitmap(canvas, (348)* GameConfig.f_zoomx - share_ui_button_03.bitmap.getWidth()/2*(login?0.2f:0f), (433) * GameConfig.f_zoomy - share_ui_button_03.bitmap.getHeight()/2*(login?0.2f:0f), login?1.2f:1f, login?1.2f:1f, 255, 0, 0, 0);
		
		paint.setTextSize(20*GameConfig.f_zoomx);
		paint.setColor(0xffffffff);
		
		if(FacebookOperation.isLanding){
			//注销
			temp = LangUtil.getLangString(LangDefineClient.CANCEL);
			int xx = (int)(348 * GameConfig.f_zoomx);
			xx = (int) (xx+(((int)(112 * GameConfig.f_zoomx) - paint.measureText(temp))/2));
			int yy = (int)(433 * GameConfig.f_zoomy);
			yy = (int)(yy+(share_ui_button_03.bitmap.getHeight()-paint.getTextSize()));
			canvas.drawText(temp, xx, yy, paint);
			
			
		}else{
			//登录
			temp = LangUtil.getLangString(LangDefineClient.LOG_IN);
			int xx = (int)(348 * GameConfig.f_zoomx);
			xx = (int) (xx+(((int)(112 * GameConfig.f_zoomx) - paint.measureText(temp))/2));
			int yy = (int)(433 * GameConfig.f_zoomy);
			yy = (int)(yy+(share_ui_button_03.bitmap.getHeight()-paint.getTextSize()));
			canvas.drawText(temp, xx, yy, paint);
		}
		
		String no = LangUtil.getLangString(LangDefineClient.ON);
		String off = LangUtil.getLangString(LangDefineClient.OFF);
		for(int i=0;i<switchxy[0].length;++i){
			if(isOpen[i]){
				share_ui_button_04.drawBitmap(canvas, null, (int)(switchxy[0][i] * GameConfig.f_zoomx), (int)(switchxy[1][i] * GameConfig.f_zoomy), (int)(102 * GameConfig.f_zoomx), -1);
				if(i == 0){
					share_ui_button_07.drawBitmap(canvas, share_ui_button_07.bitmap, (int)(switchPoit1[0] * GameConfig.f_zoomx), (int)(switchPoit1[1] * GameConfig.f_zoomy), null);
					canvas.drawText(no, (int)(switchText[0] * GameConfig.f_zoomx),(int)((switchText[1]+13) * GameConfig.f_zoomy), paint);
				}else if(i == 1){
					share_ui_button_07.drawBitmap(canvas, share_ui_button_07.bitmap, (int)(switchPoit1[0] * GameConfig.f_zoomx), (int)(switchPoit2[1] * GameConfig.f_zoomy), null);
					canvas.drawText(no, (int)(switchText[0] * GameConfig.f_zoomx),(int)((switchText1[1]+13) * GameConfig.f_zoomy), paint);
				}
			}else{
				share_ui_button_06.drawBitmap(canvas, null, (int)(switchxy[0][i] * GameConfig.f_zoomx), (int)(switchxy[1][i] * GameConfig.f_zoomy), (int)(102 * GameConfig.f_zoomx), -1);
				if(i == 0){
					share_ui_button_07.drawBitmap(canvas, share_ui_button_07.bitmap, (int)(switchPoit2[0] * GameConfig.f_zoomx), (int)(switchPoit1[1] * GameConfig.f_zoomy), null);
					canvas.drawText(off, (int)(switchText1[0] * GameConfig.f_zoomx),(int)((switchText[1]+13) * GameConfig.f_zoomy), paint);
				}else if(i == 1){
					share_ui_button_07.drawBitmap(canvas, share_ui_button_07.bitmap, (int)(switchPoit2[0] * GameConfig.f_zoomx), (int)(switchPoit2[1] * GameConfig.f_zoomy), null);
					canvas.drawText(off, (int)(switchText1[0] * GameConfig.f_zoomx),(int)((switchText1[1]+13) * GameConfig.f_zoomy), paint);
				}
			}
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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
		
	    	if (s_share_ui_back_01 == null)
				return;
			GameImage.delImage(gs.bitmap);
			gs.bitmap = null;
			gs = null;
	    	
	    	GameImage.delImage(s_share_ui_back_01.bitmap);
			if (s_share_ui_back_01.bitmap != null)
				s_share_ui_back_01.bitmap = null;
			s_share_ui_back_01 = null;
	    	
	    	GameImage.delImage(s_share_ui_back_02.bitmap);
			if (s_share_ui_back_02.bitmap != null)
				s_share_ui_back_02.bitmap = null;
			s_share_ui_back_02 = null;
			
			GameImage.delImage(s_share_ui_back_02_2.bitmap);
			if (s_share_ui_back_02_2.bitmap != null)
				s_share_ui_back_02_2.bitmap = null;
			s_share_ui_back_02_2 = null;
			
			GameImage.delImage(s_share_ui_close.bitmap);
			if (s_share_ui_close.bitmap != null)
				s_share_ui_close.bitmap = null;
			s_share_ui_close = null;
			
			GameImage.delImage(word_title_options.bitmap);
			if (word_title_options.bitmap != null)
				word_title_options.bitmap = null;
			word_title_options = null;
			
			GameImage.delImage(share_ui_line.bitmap);
			if (share_ui_line.bitmap != null)
				share_ui_line.bitmap = null;
			share_ui_line = null;
			
			GameImage.delImage(interface_icon_facebook_03.bitmap);
			if (interface_icon_facebook_03.bitmap != null)
				interface_icon_facebook_03.bitmap = null;
			interface_icon_facebook_03 = null;
			
			
			GameImage.delImage(share_ui_button_07.bitmap);
			if (share_ui_button_07.bitmap != null)
				share_ui_button_07.bitmap = null;
			share_ui_button_07 = null;
			
			GameImage.delImage(share_ui_button_06.bitmap);
			if (share_ui_button_06.bitmap != null)
				share_ui_button_06.bitmap = null;
			share_ui_button_06 = null;
			
			GameImage.delImage(share_ui_button_04.bitmap);
			if (share_ui_button_04.bitmap != null)
				share_ui_button_04.bitmap = null;
			share_ui_button_04 = null;
			
			GameImage.delImage(share_ui_button_03.bitmap);
			if (share_ui_button_03.bitmap != null)
				share_ui_button_03.bitmap = null;
			share_ui_button_03 = null;
			
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
					(455)* GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					(159) * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight()/2*0.2f,
					(455+55) * GameConfig.f_zoomx + s_share_ui_close.bitmap.getWidth()*1.2f, 
					(159+57) * GameConfig.f_zoomy + s_share_ui_close.bitmap.getHeight()*1.2f)) {
					anjianclose = true;
			}
			for(int i=0;i<switchxy[0].length;++i){
				if(ExternalMethods.CollisionTest(x, y, 
						(int)((switchxy[0][i]-30) * GameConfig.f_zoomx),
						(int)(switchxy[1][i] * GameConfig.f_zoomy),
						(int)((102+30) * GameConfig.f_zoomx)+(int)(switchxy[0][i] * GameConfig.f_zoomx), 
						 share_ui_button_04.bitmap.getHeight()+(int)(switchxy[1][i] * GameConfig.f_zoomy))) {
						temp[i] = true;
						return;
				}
 
			}
			if (ExternalMethods.CollisionTest(x, y, 
					(348)* GameConfig.f_zoomx - share_ui_button_03.bitmap.getWidth()/2*0.2f, 
					(433) * GameConfig.f_zoomy - share_ui_button_03.bitmap.getHeight()/2*0.2f,
					(348+112) * GameConfig.f_zoomx + share_ui_button_03.bitmap.getWidth()*1.2f, 
					(433+50) * GameConfig.f_zoomy + share_ui_button_03.bitmap.getHeight()*1.2f)) {
				login = true;
			}
		 
			
		}else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (anjianclose && ExternalMethods.CollisionTest(x, y, 
					(455)* GameConfig.f_zoomx - s_share_ui_close.bitmap.getWidth()/2*0.2f, 
					(159) * GameConfig.f_zoomy - s_share_ui_close.bitmap.getHeight()/2*0.2f,
					(455+55) * GameConfig.f_zoomx + s_share_ui_close.bitmap.getWidth()*1.2f, 
					(159+57) * GameConfig.f_zoomy + s_share_ui_close.bitmap.getHeight()*1.2f)) {
					anjianclose = false;
					GameManager.ChangeModule(null);
					return;
			}
			
			for(int i=0;i<switchxy[0].length;++i){
				if(temp[i] && ExternalMethods.CollisionTest(x, y, 
						(int)((switchxy[0][i]-30) * GameConfig.f_zoomx),
						(int)(switchxy[1][i] * GameConfig.f_zoomy),
						(int)((102+30) * GameConfig.f_zoomx)+(int)(switchxy[0][i] * GameConfig.f_zoomx), 
						 share_ui_button_04.bitmap.getHeight()+(int)(switchxy[1][i] * GameConfig.f_zoomy))) {
						 isOpen[i] = !isOpen[i];
						 if(i == 0){
							 VeggiesData.setMuteMusic(!VeggiesData.isMuteMusic());
							 if(!VeggiesData.isMuteMusic())
									GameMedia.playMusic(R.raw.loginl, true, true);
							 else
								 Music.stop();
						 }else
							 VeggiesData.setMuteSound(!VeggiesData.isMuteSound());
				}
 
			}
			if (login && ExternalMethods.CollisionTest(x, y, 
					(348)* GameConfig.f_zoomx - share_ui_button_03.bitmap.getWidth()/2*0.2f, 
					(433) * GameConfig.f_zoomy - share_ui_button_03.bitmap.getHeight()/2*0.2f,
					(348+112) * GameConfig.f_zoomx + share_ui_button_03.bitmap.getWidth()*1.2f, 
					(433+50) * GameConfig.f_zoomy + share_ui_button_03.bitmap.getHeight()*1.2f)) {
				login = false;
				if(FacebookOperation.isLanding){
					//注销
					FacebookOperation.getFacebook().cancel();
				}else{
					//登录
					FacebookOperation.gameState =  FacebookOperation.GAME_STATE_INIT_LOADING;
					FacebookOperation.getFacebook().landingAndInvite((byte)-1);
				}
			}
			
			
			login = false;
			anjianclose = false;
			for(int i=0;i<temp.length;++i){
				temp[i] = false;
			}
		}
		
	}

}//end class
