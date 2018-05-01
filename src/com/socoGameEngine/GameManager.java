package com.socoGameEngine;



import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.PopUp;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Sprite;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


 public class GameManager extends SurfaceView implements Runnable,SurfaceHolder.Callback{   
	 static final byte MODULETYPE_RUN = 0;//其它模块继续run 
	 static final byte MODULETYPE_STOP = 1;//其它模块不执行run
	 
	 static final byte STATE_MAINACTIVE = 0;//主激活状态
	 static final byte STATE_ACTIVE = 1;//次激活状态
	 static  final byte STATE_WAIT = 2;//等待状态
	 static  final byte STATE_STOP = 3;//停止状态
     static  final byte STATE_CLOSE = 4;//关闭状态
     
	 static boolean b_GameStart = true;//退出游戏
	 public static boolean b_GamePause = false;//游戏暂停
     private static int i_currentModuleID = -1;//当然运行模块ID
     static byte B_reset ;//重置模块
     public  static final byte reset_over = 0;//重置结束
     public  static final byte reset_begin = 1;//重置开始
     public static final byte reset_beginover = 2;//重置开始结束
     public static final byte reset_loading = 3;//重置加载
     public static final byte reset_loadingover = 4;//重置加载结束
     static Loading loadingMoudule;
     private static Module tempModule ;
     private static ArrayList<PopUp>  pop_up = new ArrayList<PopUp>(); //弹出框
     private static GameTeaching gt; //新手教程 
     
     private static ArrayList<Module> modules;
     private static ArrayList<Module> operationModules = new ArrayList<Module>();
     private static boolean b_canKeyDown =  true;//是否可以按键
     private static boolean b_canTouchKeyDown =  true;//是否可以触屏
     private static boolean b_GameOver = false;
     private static boolean b_showdialog = false;

     long currenttime ;
     Object object;
     public static GameManager iGameManager;
	 boolean b_first = true;
	 public static Thread thread;
		
     public GameManager(Context context, AttributeSet attrs) {   
    	 super(context, attrs);
     }   
        
     public GameManager(Context context) {   
         super(context);
         
         init();
     }   
        
     public void init()
     {
        holder = getHolder();
    	holder.addCallback(this);
    	setFocusable(true);//设置键盘焦
    	setFocusableInTouchMode(true);//设置触摸屏焦点
    	setEnabled(true);
    	requestFocus();
        currenttime = System.currentTimeMillis();
        if(b_first)
           initialize();
        iGameManager = this;
      }
        
      public static GameManager getGameManager(){
        return iGameManager;
      }
        
      int i_angle = 0;
	  private int i_alpha = 0;
	  SurfaceHolder	holder ;
		
	  Sprite bufferbitmap = null;
	  static Canvas bufferCanvas = null;
	  
      private void initialize() {   
        object = new Object();
        modules = new ArrayList<Module>(); 
    		
    	b_first = false;
    	bufferbitmap = new Sprite(Bitmap.createBitmap((int)GameConfig.GameScreen_Width, (int)GameConfig.GameScreen_Height, Config.RGB_565)); 
    	bufferCanvas = new Canvas(bufferbitmap.bitmap);
      }  
       public static void setPopUp(byte type, String icon_file, PopUp _popup){
    	   if(_popup!=null){
    		   _popup.init(type, icon_file);
        	   pop_up.add(_popup);
    	   }else{
    		   pop_up.get(pop_up.size()-1).delete();
    		   pop_up.remove(pop_up.size()-1);
    	   }
       }
       public static ArrayList<PopUp> getpop_up(){
    	   return pop_up;
       }
       
       public static void setGT(GameTeaching _gt){
    	   if(_gt==null && gt!=null)
    		   gt.delImage();
    	   gt = _gt;
    	   if(gt!=null)
    		   gt.loadImage();
       }
       
       public static GameTeaching getGT(){
    	   return gt;
       }
       
      /**
        * //切换到指定序列的模块
        * @param ModuleID 模块ID
        */        
       public static  void ChangeToModule(int ModuleID){//切换到指定序列的模块
    	   try {
    		   if(!VeggiesData.isMuteSound())
    		   GameMedia.playSound(R.raw.clicks, 0);
			} catch (Exception e) {
				// TODO: handle exception
			}
    	   
        	if(ModuleID == i_currentModuleID){
        		LogShow.d("已经是当前模块");
        		return;
        	}
        	modules.get(i_currentModuleID).setState(STATE_ACTIVE);
        	modules.get(i_currentModuleID).i_nextID = modules.get(ModuleID).i_curID;
        	modules.get(ModuleID).setState(STATE_MAINACTIVE);
        	if(modules.get(ModuleID).i_preID != -1)
				modules.get(modules.get(ModuleID).i_preID).i_nextID = modules.get(ModuleID).i_nextID;
				if(modules.get(ModuleID).i_nextID != -1)
				modules.get(modules.get(ModuleID).i_nextID).i_preID = modules.get(ModuleID).i_preID;
				
				modules.get(ModuleID).i_preID = i_currentModuleID;
				modules.get(ModuleID).i_nextID = -1;
				i_currentModuleID = modules.get(ModuleID).i_curID;	
        }
        
        private ExecutorService executorService = Executors.newFixedThreadPool(5);
		private boolean ForbidMode;   
        	
        private void reset(){//loading逻辑
        
        	 executorService.submit(new Runnable() {
                 public void run() {
                     try {
        
                      HandlerThread hThread = new HandlerThread("Handler1");	 
                    	 
	                  hThread.start();
	                  
                      Handler handler = new Handler(hThread.getLooper());
                      
                      handler.post(new Runnable() {
                             public void run() {
                           
                            	 int j = modules.size();
                            	 
                            	 for(int i = 0;i< j;i++){
                            		 modules.get(i).Release();
                            	 }
                            	 
//                            	 Loading.Time += 30;
//                            	 
//                  				 Loading.Time += 20;
                  				 
                  				modules.clear();
                  				
//                  				 Loading.Time += 30;
                  				 
                  				System.gc();
                  				 
                  				changeMoudelogic(tempModule);
                  			 
                  				if(_pauseModule != null){
                  					changeMoudelogic(_pauseModule);
                  					
                  					_pauseModule = null;
                  				}
                  				
//                  				 Loading.Time += 20;
                  				 
                  				B_reset = reset_loadingover;                  	        
                             }
                         }
                      );    
//                      
//                      handler.removeCallbacks(hThread);
//                      hThread.interrupt();
//                      
                     } catch (Exception e) {
                    	 LogShow.d("reset error");
                         LogShow.d(e.toString());
                     }                                         
                 }
             });           	         	         		        
        }
        
       /**
        * //清除所有模块并运行添加的模块
        * @param _m 需要添加的模块
        * @param type 模块类型，MODULETYPE_RUN和MODULETYPE_STOP 两种
        */
        public static void ResetToRunModule(Module _m){    
        	try {
        		if(!VeggiesData.isMuteSound())
     		   GameMedia.playSound(R.raw.clicks, 0);
 			} catch (Exception e) {
 				// TODO: handle exception
 			}
        	operationModules.clear();
        	B_reset = reset_begin;
        	setCanKeyDown(false);
          	tempModule = _m;
        	if(GameManager.loadingMoudule != null){
            	GameManager.loadingMoudule.initialize();
        	}        	
        }
        
        /**
         *  // 添加一个可以禁止其它模块运行的模块，其它模块依然repaint()
         * @param _m
         */
    
        public static void forbidModule(Module _m){
        	try {
        		if(!VeggiesData.isMuteSound())
     		   GameMedia.playSound(R.raw.clicks, 0);
 			} catch (Exception e) {
 				// TODO: handle exception
 			}
        	if(_m!= null){
        		_m.setType(MODULETYPE_STOP);
	        	ChangeModule( _m);
        	}
        	else{
        		ChangeModule( _m);
        	}
        	
        }
        public static byte getLoadState(){
        	return B_reset;
        	
        }
        
        
        /**
         * //添加一个模块并运行,
         * @param _m   添加一个模块
         * @param type 模块类型，MODULETYPE_RUN和MODULETYPE_STOP 两种
         */
       private static Module _pauseModule;
       
        public static void ChangeModule(Module _m){//对外接口
       	   try {
       		   if(!VeggiesData.isMuteSound())
    		   GameMedia.playSound(R.raw.clicks, 0);
			} catch (Exception e) {
				// TODO: handle exception
			}
        	if(B_reset == reset_over)
        		operationModules.add(_m);
        	else {
        		_pauseModule = _m;
        	}
        	
        }
        
        
        private void changeMoudelogic(Module _m){
        	if(_m != null){
        		_m.setState(STATE_MAINACTIVE);
        		if(_m.getType() != MODULETYPE_STOP)
        		_m.setType(MODULETYPE_RUN);
        		LogShow.d("BeforeLoading");
        		GameImage.showMemory();
        		_m.initialize();//初始化模块
        		LogShow.d("AfterLoading");
        		GameImage.showMemory();
            	if(modules.isEmpty()){
            		_m.i_curID = 0;
            		_m.i_preID = -1;
            		_m.i_nextID = -1;
            	}
            	else{
            		int tempcurrentID = i_currentModuleID;
            		int num = 0;
            		 int j = modules.size();
                	 for(int i = 0;i< j;i++){
                		
                		 Module _mModule =  modules.get(i);
                		 if(_mModule.getState() == STATE_MAINACTIVE){
         					
         					_mModule.setState(STATE_ACTIVE);
         					_m.i_preID = _mModule.i_curID;
         					
         				}
         				num++;
                	 }
        			_m.i_curID = num;
        			modules.get(tempcurrentID).i_nextID = _m.i_curID;
            	}
            	i_currentModuleID = _m.i_curID;   
    			modules.add(_m);
    			
        	}
        	else{
        		
        		int tempcurrentID = i_currentModuleID; 
        		
        		if(i_currentModuleID != -1&&modules.get(i_currentModuleID).i_preID != -1){
        		
	        		modules.get(modules.get(i_currentModuleID).i_preID).i_nextID = -1;
					modules.get(modules.get(i_currentModuleID).i_preID).setState(STATE_MAINACTIVE);
				}
        		if(i_currentModuleID != -1){
        			modules.get(i_currentModuleID).Release();
	        		modules.remove(i_currentModuleID);
	        		modules.get(modules.size()-1).onreStart();
        		}
    			if(modules.size()!=0){//id 更新
    					int j = modules.size();
	    				for(int i = 0;i< j;i++){
	    					if(modules.get(i).i_curID>tempcurrentID){
	    						modules.get(i).i_curID --;
	    					}
	    					if(modules.get(i).i_preID>tempcurrentID){
	    						modules.get(i).i_preID --;
	    					}
	    					if(modules.get(i).i_nextID>tempcurrentID){
	    						modules.get(i).i_nextID--;
	    					}
	    				}
	    			for(int i = 0;i< j;i++){
	    				
	    				if(modules.get(i).getState() == STATE_MAINACTIVE){
	    					i_currentModuleID = i;
	    					break;
	    				}
	    				
	    			}
    			}
    			else{
    				i_currentModuleID = -1;
    			}
    			
        	}
        	
        	if(i_currentModuleID != -1&&!modules.isEmpty()){
        		ForbidMode = false;
        		if(modules.get(i_currentModuleID).getType() == MODULETYPE_STOP){
		        	try{
		        		bufferCanvas.drawRGB(0, 0, 0);
		        		int j = modules.size();
		        		for(int i = 0;i< j;i++){
		               		 Module _mModule =  modules.get(i);
		               		 if(_mModule.getState() != STATE_MAINACTIVE)
								_mModule.paint(bufferCanvas);
				        	}
		        		
		        	}catch(Exception e){
		        		e.printStackTrace();
		        		LogShow.d(e.toString());
		        	}
					ForbidMode = true;
	    		}
        	}
        }
     
        private Paint  paint = new Paint();
        private long time;
    	private long fps;
    	private String fpsString ="";
		private int B_anti;
	
        protected void Paint(Canvas canvas) {   
        	
        	if(b_GamePause||b_GameOver||canvas ==null)
				return ; 
        	
        	if(B_reset != reset_loading){
        		if(!ForbidMode){
	        		canvas.drawRGB(0, 0, 0);
	        		if(i_currentModuleID != -1&&!modules.isEmpty()){
			        	try{
			        		
			        		int j = modules.size();
			        		for(int i = 0;i< j;i++){
	               		
			               		 Module _mModule =  modules.get(i);
			               		 if(_mModule.getState() != STATE_MAINACTIVE)
			               			_mModule.paint(canvas);
					        	}
			        		
			        	}catch(Exception e){
			        		e.printStackTrace();
			        		LogShow.d(e.toString());
			        	}
	        		}
        		}
	        		else {
	        			bufferbitmap.drawBitmap(canvas, bufferbitmap.bitmap, 0,0, null);
	        		}
        			if(!modules.isEmpty())
					modules.get(i_currentModuleID).paint(canvas);
        	}
        	
           if(B_reset != reset_over&&loadingMoudule!=null)
        		loadingMoudule.paint(canvas);
           else{
        	   if(gt!=null && gt.getTeachId() != -1){ //新手教程
        		   if(GameManager.getGT().getTeachId()!=GameTeaching.TEACH_VOL53) //特殊处理
        			   gt.paint(canvas);
        	   }
	           if(pop_up!=null){ //弹出框
	        	   for(int i=0;i<pop_up.size();++i){
	        		   pop_up.get(i).draw(canvas, i==0?true:false);
	        	   }
	           }
	           //特殊处理-------！
	           if(gt!=null && gt.getTeachId() != -1) //新手教程
	        	   if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL53)
	        		   gt.paint(canvas);
	            
           }
    	   if(GameConfig.ShowFps){
    		   if(System.currentTimeMillis() - time > 1000){
    		    	
    	    		fpsString = ""+fps;
    	    		time = System.currentTimeMillis();
    	    		fps = 0;
    	    	}
    	    	fps++;  
    			paint = new Paint();
        		paint.setColor(Color.RED);
        		paint.setTextSize(30);
        		canvas.drawText("fps="+fpsString,0, 30, paint);
        	}
    		
        }
        
        public  static long lThreadTime=0;
        
		public void run() {
			// TODO Auto-generated method stub
			while (b_GameStart) {
				
			
				 if(b_GameOver ){
					 MainActivity.getActivity().closeGame();
					 break;
				 }
				 else if(!b_GamePause){
						lThreadTime = System.currentTimeMillis();
		
						Canvas canvas = null;
		
						try {						
							GameConfig.i_coke++;
							if(GameConfig.i_coke>999999999){
								GameConfig.i_coke=0;
							}
							canvas = holder.lockCanvas();
							synchronized (holder) {
								try{
									MainActivity.getActivity().run();
									logic();
									Paint(canvas);
								}catch(Exception e){
									e.printStackTrace();
									LogShow.d(e.toString());
								}
							}
							GameMedia.clearBuffer();
						} catch (Exception e) {
							// TODO: handle exception
						} finally {
							if (canvas != null)
								holder.unlockCanvasAndPost(canvas);
		
						}
						long templ=System.currentTimeMillis() - lThreadTime;

						if(templ < GameConfig.getSleepTime()){
							try {
								Thread.sleep(GameConfig.getSleepTime()-templ);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
//						if(templ < GameConfig.getSleepTime()-10){
//							try {
//								Thread.sleep(GameConfig.getSleepTime()-templ);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}else{
//							try {
//								Thread.sleep(10);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
				}
				 else{
					 try {
						Thread.sleep(GameConfig.getSleepTime());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						LogShow.d(e.toString());
					}
				 }
			}
		}

		private void logic() {
			// TODO Auto-generated method stub
			
			if(operationModules.size() != 0){
				LogShow.d("operationModules.size()="+operationModules.size());
				System.out.println("clpqy:operationModules.size()="+operationModules.size());
				int j = operationModules.size();
				for(int i = 0;i< j;i++){
					tempModule = operationModules.get(i);
					changeMoudelogic(tempModule);
					operationModules.remove(tempModule);
					
					j = operationModules.size();
				}
			}
			
			if(B_reset == reset_begin){
				if(loadingMoudule == null||(loadingMoudule!=null&&loadingMoudule.getLoadingState() == Loading.LOADING))
					B_reset = reset_beginover;

			}
			else if(B_reset == reset_beginover){
				B_reset  = reset_loading;
				reset();
			}
			else if(B_reset == reset_loadingover){
				
				if(loadingMoudule == null||(loadingMoudule!=null&&loadingMoudule.getLoadingState() == Loading.LOADINGOVER)){
					
					if(!GameManager.b_GamePause){
						setCanKeyDown(true);
						if(loadingMoudule != null)
						loadingMoudule.release();
						B_reset = reset_over;
					}
				}
			}
			
			if(B_reset == reset_over){
				
				if(i_currentModuleID != -1&&!modules.isEmpty()){
					
					if(modules.get(i_currentModuleID).getType() == MODULETYPE_STOP){
						modules.get(i_currentModuleID).run();
						
					}					
					else{
						int j = modules.size();
			        	for(int i = 0;i< j;i++){
							Module _mModule  = modules.get(i);
							if(_mModule.getState() != STATE_STOP)
								_mModule.run();
			        	}
					}
				}
			}
			
			if(B_reset != reset_over&&loadingMoudule!=null){
				loadingMoudule.run();
			}else{		
				if(pop_up!=null){
					for(int i=0;i<pop_up.size();++i){
						if(pop_up.get(i).run()){
							pop_up.get(i).delete();
							pop_up.remove(i);
							break;
						} 
					}
		    	}
				if(gt!=null){
					gt.updata();
					if (gt.pauseState()) {
						return;
					}
				}
			}
		}

		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			LogShow.d("surfaceChanged");
		}

		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			b_GameStart =true;
			new Thread(this).start();
			LogShow.d("surfaceCreated");
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			b_GameStart = false;
			LogShow.d("surfaceDestroyed");
		} 
		
		/**
		 * 处理按键
		 */
		public boolean onKeyUp(int keyCode, KeyEvent msg)	{
			
			if(gt!=null) //新手教程
				return true;
			
			if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
				||keyCode == KeyEvent.KEYCODE_VOLUME_UP){
				return super.onKeyDown(keyCode, msg);
			}
			
			if(!b_GameStart||b_GameOver||!b_canKeyDown				
				||operationModules.size()!= 0||islock(keyCode))
				return true;
			   
			if(i_currentModuleID != -1||B_reset != reset_over ){
				   if( modules.size()>0&&modules.get(i_currentModuleID).onKeyUp(keyCode, msg)){
//					   repaint();
				   }
			 }
			
			
			 return super.onKeyUp(keyCode, msg);
		}
		
		/**
		 * 是否可以按键
		 * @param _iscan 
		 */
		public static void setCanKeyDown(boolean _iscan){
			b_canKeyDown = _iscan;
		}
		
		/**
		 * 得到是否可以按键的状态
		 * @return 返回true可以按键 返回false不可以
		 */
		public static boolean getCanKeyDown(){
			return b_canKeyDown;
		}
	
		public static final byte KEYLOCK = 0;
		public static final byte KEYUNLOCK = 1;
		private static HashMap<Integer, Byte> keyState = new HashMap<Integer, Byte>();
		private static boolean b_Anti = true    ;//抗锯齿
		public static boolean b_Logo = false;
		
		
		/**锁定某个键
		 * @param keycode 需要被锁定的键值
		 * @param state 锁定还是解锁 KeyLock 为锁定 KeyunLock 为解锁
		 */
		public static void LockKeyDown(int keycode,int state){
			if(state == KEYLOCK){
				if(keyState.containsKey(keycode)){
					
					return;
				}
				else{
					keyState.put(keycode, (byte)state);
				}
			}
			else if(state == KEYUNLOCK){
				
				if(!keyState.isEmpty()){
					if(keyState.containsKey(keycode))
					keyState.remove(keycode);
				}
			}
		}
		/**
		 * 释放所有锁定按键
		 */
		public static void ReleaseLockKey(){
			keyState.clear();
		}
	
		
		/**
		 * 是否可以触屏
		 * @param _iscan 
		 */
		public static void setCanTouchKeyDown(boolean _iscan){
			b_canTouchKeyDown = _iscan;
		}
		
		/**
		 * 得到是否可以触屏的状态
		 * @return 返回true可以触屏 返回false不可以
		 */
		public static boolean getCanTouchKeyDown(){
			return b_canTouchKeyDown;
		}
		
		public boolean onKeyDown(int keyCode, KeyEvent msg) {		
			if(gt!=null) //新手教程
				return true;
			
			if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
					||keyCode == KeyEvent.KEYCODE_VOLUME_UP){
					return super.onKeyDown(keyCode, msg);
				}
			
			if((!b_GameStart||b_GameOver||!b_canKeyDown
					||operationModules.size()!= 0||islock(keyCode))){
		    	 return true;
		     }
		
			 if (keyCode == KeyEvent.KEYCODE_BACK ) {	
				 Log.e("keyCode == KeyEvent.KEYCODE_BACK ", "keyCode == KeyEvent.KEYCODE_BACK ");
//				 if(tempModule==null){
//					 Log.e("tempModule ", "tempModule==null");
//				 }else
				 modules.get(i_currentModuleID).onKeyDown(keyCode, msg);
			    	return false;			    
			 }
			
			 if(b_GamePause||b_GameOver||b_showdialog)
					return false;
				
			 if(i_currentModuleID != -1||B_reset != reset_over ){					   
				if( modules.size()>0&&modules.get(i_currentModuleID).onKeyDown(keyCode, msg)){
							return true;
					 }
			  }
			
		      return super.onKeyDown(keyCode, msg);
		}
		
		/**
		 * 是否锁定特定按键
		 * @param keyCode
		 * @return true锁定 false 解锁
		 */
		private boolean islock(int keyCode) {
			// TODO Auto-generated method stub
			if(keyState.isEmpty()||!keyState.containsKey(keyCode)){
				return false;
			}
			return true;
		}

		/**
		 * 处理触屏事件
		 */
		@Override
		public boolean onTouchEvent(MotionEvent event) {
		    if(event==null)
		    return true;
		    	 
		    if(!b_GameStart||b_GameOver||!b_canTouchKeyDown
		    	||B_reset != reset_over||operationModules.size()!= 0)
					return true;
		    
		    if(pop_up!=null){ //弹出框
		    	for(int i=pop_up.size()-1;i>=0;i--){
		    		if(i>=0)
		    			pop_up.get(i).onTouch(event);
					return true;
				}
		    }
		    
		    if(gt!=null && !gt.getIsTouch()) //新手教程
				return true;
			if (gt!=null && gt.getOnTouchState()) { //新手教程
				gt.onTouchEvent(event);
				return true;
			}
		    
		    if(i_currentModuleID != -1&&!modules.isEmpty()){
		    	if( modules.size()>0){
		    		try {
		    			modules.get(i_currentModuleID).onTouchEvent(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
		    	}
		    }
		    
		    return true;
		 }
		     
		 public static void Clear(){
		    modules.clear();	 
		 }

		 public static boolean getAnti() {
				// TODO Auto-generated method stub
				return b_Anti;
		 }
		 
		 public static void setAnti(boolean flag) {
				// TODO Auto-generated method stub
				b_Anti = flag;
		 }
		 /**
		  * 刷新buff模版
		  * */
		 public void refreshForbidMode(){
			 ForbidMode = false;
		 }
    }  
 
 

 