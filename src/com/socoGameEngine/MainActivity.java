package com.socoGameEngine;


import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.socogame.sax.RSSFeed;
import com.socogame.sax.RSSHandler;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

public abstract class MainActivity extends Activity 
//implements ServiceConnection
{
    /** Called when the activity is first created. */
	private static float version = 1.0f;
	public static GameManager gManger;
	
	private DisplayMetrics dm=new DisplayMetrics(); 
	private static MainActivity instance;
	
    public boolean b_first = true;
	
	public  static MainActivity getActivity(){
		return instance;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    	
		getWindowManager().getDefaultDisplay().getMetrics(dm); 
		GameConfig.GameScreen_Width = dm.widthPixels;//.getWidth();
		GameConfig.GameScreen_Height = dm.heightPixels;//display.getHeight();
	         
		setContentView(R.layout.main);
		
        if(b_first){
        	
//	        gManger  = new GameManager(this);
        	
        	gManger  = (GameManager)this.findViewById(R.id.gamemanager);
        	
        	gManger.init();
	        b_first = false;
	        start();
        }
        
//        setContentView(gManger);
//        GameManager.ChangeModule(new GameLogo());                 
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(Session.getActiveSession()!=null)
//        	Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
    public static float getVersion(){
    	return version;
    }
   
    @Override
    protected void onStart()
    {
        super.onStart();
    }
    
	
    @Override
    protected void onStop()
    {
        super.onStop();
    }
    
    protected  void onResume() {
    	super.onResume();
    	if(!b_first)
    		GameManager.b_GamePause = false;
    	
    	if(Resume()){
    		return;
    	}
    	
    	if(!b_first){
    	}
	}
    
    protected void onPause(){
    	super.onPause(); 
    	if(!b_first){
    		GameManager.b_GamePause = true;
    	}
    	
    	if(Pause()){
    		return;
    	}
    	
    	if(!b_first&&!GameManager.b_Logo){
    		GameMedia.pauseMusic();
    	}
    }
    
    protected void onDestroy() {
    	LogShow.d("onDestroy");
    	super.onDestroy();
    }
    
    public  void closeGame() {
    	GameManager.b_GameStart =false;
    	new VeggiesData().saveGame();
    	Destory();
    	GameMedia.dealloc();
    	GameImage.RemoveAllImage();
    	GameManager.Clear();
    	finish();
    	System.exit(0);
	}
    
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		ConfigurationChanged(newConfig);

	}
	/**
	 * 游戏启动入口
	 */
	public  abstract void start();
	/**
	 * 游戏暂停
	 */
    public  abstract boolean Pause();
    /**
	 * 游戏恢复
	 */
    public  abstract boolean Resume();
    /**
	 * 游戏关闭
	 */
    public  abstract void Destory();
    /**
	 * 游戏状态切换
	 */
    public  abstract void ConfigurationChanged(Configuration newConfig);
    /**
	 * 按键按下
	 */
    public  abstract boolean keyDown(int keyCode, KeyEvent event);
    /**
	 * 按键抬起
	 */
    public  abstract boolean keyUp(int keyCode, KeyEvent event);
   
    /**
	 * 全局的run()
	 */
    public  abstract void run();
    /**
	 * 接受handler事件根据id处理
	 */
    public  abstract void receiveMessage(int id);
    
//    /**
//     * 初始化游戏，游戏标准宽和高，游戏的缩放就以标准宽高来缩放
//     * @param mainActivity activity 实例
//     * @param Loading loading模块 实例，传空没有loading界面
//     * @param Loading exit模块实例，传空没有exit界面直接退出游戏
//     * @param sleeptime 游戏运行速率
//     * @param orgGameScreenwidth 游戏屏幕标准宽
//     * @param orgGameScreenheight 游戏屏幕标准高
//     */
//    public static void initGame(MainActivity mainActivity,Loading loading,int sleeptime,int orgGameScreenwidth,int orgGameScreenheight){
//    	
//    	 instance = mainActivity;
//    	 GameManager.loadingMoudule = loading;
//    	
//    	 if(sleeptime != 0){
//    		 GameConfig.setSleepTime(sleeptime);
//    	 }
//    	  	
//    	 GameConfig.GameScreen_Width = orgGameScreenwidth;
//    	 GameConfig.GameScreen_Height = orgGameScreenheight;
//    	  	
//	     GameConfig.f_zoomx = 1;
//	     GameConfig.f_zoomy = 1;
//	        
//	     if(GameConfig.GameScreen_Height == GameConfig.ORGScreen_Height
//	    	||GameConfig.GameScreen_Width == GameConfig.ORGScreen_Width){
//	    	  GameConfig.f_zoom = 1; 
//	     }
//	    else {
//	    	 GameConfig.f_zoom = (float)GameConfig.GameScreen_Width/GameConfig.ORGScreen_Width;
//	    	   
//	    	 if(GameConfig.f_zoom * GameConfig.ORGScreen_Height > GameConfig.GameScreen_Height){
//	    		GameConfig.f_zoom = (float)GameConfig.GameScreen_Height/GameConfig.ORGScreen_Height;
//	    	 }	    	  
//	     }
//	       
//	     GameConfig.f_zoomx = GameConfig.f_zoomy = GameConfig.f_zoom;
//    }
    
    /**
	 * 初始化游戏，游戏标准宽和高，游戏的缩放就以标准宽高来缩放
	 * 
	 * @param mainActivity
	 *            activity 实例
	 * @param Loading
	 *            loading模块 实例，传空没有loading界面
	 * @param Loading
	 *            exit模块实例，传空没有exit界面直接退出游戏
	 * @param sleeptime
	 *            游戏运行速率
	 * @param orgGameScreenwidth
	 *            游戏屏幕标准宽
	 * @param orgGameScreenheight
	 *            游戏屏幕标准高
	 */
	public static void initGame(MainActivity mainActivity, Loading loading,
			int sleeptime, int orgGameScreenwidth, int orgGameScreenheight) {

		instance = mainActivity;
		GameManager.loadingMoudule = loading;

		if (sleeptime != 0) {
			GameConfig.setSleepTime(sleeptime);
		}

		GameConfig.GameScreen_Width = orgGameScreenwidth;
		GameConfig.GameScreen_Height = orgGameScreenheight;

		GameConfig.f_zoomx = 1;
		GameConfig.f_zoomy = 1;

//		if (GameConfig.GameScreen_Height == GameConfig.ORGScreen_Height
//				|| GameConfig.GameScreen_Width == GameConfig.ORGScreen_Width) {
//			GameConfig.f_zoom = 1;
//		} else {
			GameConfig.f_zoomx = (float) GameConfig.GameScreen_Width
					/ GameConfig.ORGScreen_Width;

			GameConfig.f_zoomy = (float) GameConfig.GameScreen_Height
					/ GameConfig.ORGScreen_Height;
			
			GameConfig.f_zoom = GameConfig.f_zoomx;

//		}
	}

    
    /**
     * 发送handler指令
     * @param id
     */
    public  void sendMessage(int id){
    	
    	myHandler.sendEmptyMessage(id);
    	
    };
    
    private  Handler myHandler = new Handler(Looper.getMainLooper()){
    	public void handleMessage(Message msg){
    			receiveMessage(msg.what);	
    	}
    };
    
//    public String getCpu() {
//		  ProcessBuilder cmd;
//		  String result = "";
//		  try {
//		   String[] args = { "/system/bin/cat", "/proc/cpuinfo" };
//		   cmd = new ProcessBuilder(args);
//		   Process process = cmd.start();
//		   InputStream in = process.getInputStream();
//		   byte[] re = new byte[1024];
//		   while (in.read(re) != -1) {
//		 
//		    result = result + new String(re);
//		    return result.toString();
//		   }
//		   in.close();
//		  } catch (IOException ex) {
//		   ex.printStackTrace();
//		  }
//		  return result.toString();		 
//    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {		
				
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
				||keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			return super.onKeyDown(keyCode, event);
		}
			
		if(gManger == null||!gManger.getCanKeyDown()){
		  	return true;
		}
			
		return keyDown(keyCode,event);
	}
	  
	 public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
				||keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			return super.onKeyDown(keyCode, event);
		}
		 
		if(gManger == null||!gManger.getCanKeyDown()){
		  		return true;
		}
			
		return keyUp(keyCode,event);
			
	 }
	 

	//得到音乐焦点～
	public static void requestAudioFocus()
	{
	}

		    
	//放弃音乐焦点
	public static void abandonAudioFocus()
	{
	}

}
	