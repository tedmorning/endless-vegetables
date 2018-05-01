package com.socoGameEngine;

import java.io.IOException;
import java.io.InputStream;

import com.kokatlaruruxi.wy.Sprite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameLogo extends Module {
	Sprite bitmap;
	int i_time = 0;
	int i_frame = 0;
	Module module;
	boolean b_title=true;
	
	public GameLogo(Module _module){
		GameManager.b_Logo = true;
		module = _module;
		GameManager.setCanKeyDown(false);
		GameManager.setCanTouchKeyDown(false);
	}
	
	@Override
	public void Release() {
		// TODO Auto-generated method stub
		GameManager.b_Logo = false;
		GameImage.delImage(bitmap.bitmap);
		bitmap=null;
		GameManager.setCanKeyDown(true);
		GameManager.setCanTouchKeyDown(true);
	}

	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
	
		bitmap = new Sprite(getFromAsset("logo"+i_frame));

//		bitmap = GameImage.zoomImage(GameImage.getImage("logo"+i_frame),GameConfig.ORGScreen_Width,GameConfig.ORGScreen_Height);
		return false;
	}

	private Bitmap getFromAsset(String string) {
		// TODO Auto-generated method stub
		Bitmap result = null;
		try {
			  
//			  InputStream	is = MainActivity.getActivity().getAssets().open(string+".png");
//			   result = BitmapFactory.decodeStream(is);
//		          is.close();
//		          is = null;
			result = GameImage.getImage(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	   
//        System.gc();
		return result;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		
		if(bitmap != null && !bitmap.bitmap.isRecycled()){
			canvas.drawColor(Color.WHITE);
			bitmap.drawBitmap(canvas, bitmap.bitmap,GameConfig.GameScreen_Width/2 - bitmap.bitmap.getWidth()/2, 
					GameConfig.GameScreen_Height/2 - bitmap.bitmap.getHeight()/2,null);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		i_time++;
		
		if(i_time *GameConfig.getSleepTime() >= 1500){
			i_frame++;
			GameImage.delImage(bitmap.bitmap);
			bitmap = new Sprite(getFromAsset("logo"+i_frame));
			if(bitmap == null){
				if(b_title){
					b_title=false;
					bitmap = new Sprite(getFromAsset("language/title"));
					i_time = 0;
				}else{
//					Log.dln("okoko");
					
					GameManager.ResetToRunModule(module);
//					GameManager.ChangeModule(new GameExit(GameConfig.Game_isSound));
//					GameManager.ChangeModule(new GameBegin());
//					GameManager.ChangeModule(new GameLanguage());
				}
			}
			else{
				i_time = 0;
			}
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initwordpic() {
		// TODO Auto-generated method stub
		
	}

}
