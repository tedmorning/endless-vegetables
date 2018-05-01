//package com.farm;
//
//import java.util.Random;
//
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Paint.FontMetrics;
//import android.view.MotionEvent;
//
//import com.endlessvegetables2.android.ExternalMethods;
//import com.endlessvegetables2.android.GameTeaching;
//import com.endlessvegetables2.android.R;
//import com.endlessvegetables2.android.Sprite;
//import com.endlessvegetables2.ui.FarmModule;
//import com.endlessvegetables2.ui.GameItem;
//import com.endlessvegetables2.ui.GameMenu;
//import com.endlessvegetables2.ui.GameStaticImage;
//import com.endlessvegetables2.ui.PopUp;
//import com.endlessvegetables2.ui.VeggiesData;
//import com.facebook.FacebookOperation;
//import com.facebook.UserRequest;
//import com.game.data.FarmData;
//import com.socoGameEngine.GameConfig;
//import com.socoGameEngine.GameImage;
//import com.socoGameEngine.GameManager;
//import com.socoGameEngine.GameMedia;
//import com.socoGameEngine.TextBox;
//import com.util.lang.LangDefineClient;
//import com.util.lang.LangUtil;
//
///**
// * 农场的植物
// * */
//public class FarmPlant {
//	
//	/**地表的状态-------*/
//	//种植中
//	public static final byte SURFACE_PLANTING = 2;
//	//植物收获
//	public static final byte SURFACE_HARVEST = 3;
//	//可以种植了
//	public static final byte SURFACE_DEMANDPLANT = 1;
//	
//	boolean isharvest; //发送收获协议
//	
////	private byte state_surface; //植物的总体状态
//	private byte state_growth; //植物生长的状态
//	private byte type; //类型
//	
//	private int x, y;
//	
//	private Sprite farm_plant[][];
//	private static Sprite farm_ui_back_11; //时间的背景
//	private TextBox timeDescribe;
//	private static Sprite share_ui_button_03; //按钮
//	private static Sprite s_word_num_07[];
//	private String buttonDescribe;//button
//	
//	private int frameNumber[] = {0, 1, 2, 1}; //帧播放的顺序
//	private int tempi = 0;
//	private int loaingIndex = 0;
//	private int icoke = 0;
//	private int title_x, title_y;
//	private long time;
//	private int button_x, button_y;
//	private boolean anjianBuuton;
//	public int id;
//	private long currenttime;
//	public String s_time;
//	public String msg;
//	public boolean isGT;
//	FarmModule _module;
//	public FarmPlant(int _id, byte _type, byte _state_growth, long _time, String _msg){
//		id = _id;
//		tempi = 0;
//		type = _type;
//		anjianBuuton = false;
//		state_growth = _state_growth;
//		time = _time;
//		msg = _msg;
//		init();
//	}
//	public void setFarmModule(FarmModule module){
//		_module = module;
//	}
//	public void settype(byte _type){
//		type = _type;
//	}
//	/**
//	 * 植物的总体状态 是否收获 是否可种植 植物在生长
//	 * */
//	public byte getStateSurface(){
//		return state_growth;
//	}
//	
//	/**
//	 * 植物的总体状态 是否收获 是否可种植 植物在生长
//	 * */
//	public void setStateSurface(byte _state_surface){
//		if(_state_surface == -1)return;
//		state_growth = _state_surface;
//	
//		//改变button
//		if(state_growth == SURFACE_HARVEST){
//			 //可以收获
//			String	temp = LangUtil.getLangString(LangDefineClient.FARM_GATHER);
////			buttonDescribe.setString(temp);
//			buttonDescribe = temp;
//		}else if(state_growth == SURFACE_DEMANDPLANT){
//			//可以种植了
//			String temp = LangUtil.getLangString(LangDefineClient.FARM_PLANT);
////			buttonDescribe.setString(temp);
//			buttonDescribe = temp;
//		}
//	}
//	
//	/**
//	 * //植物生长的状态 
//	 * */
//	public byte getStateGrowth(){
//		return state_growth;
//	}
//	
//	/**设置在种植植物的类型 初生 长大 茁壮* */
//	public void setStateGrowth(byte _state_growth){
//		if(_state_growth==-1)return;
//		state_growth = _state_growth;
//		
//		if(isharvest){ //代表是在收获后的操作
//			 if(!VeggiesData.isMuteMusic())
//				 GameMedia.playSound(R.raw.rewards, 0);
//			String name = "";
//			String temp = "";
//			switch(id+1){
//			case UserRequest.FARM_PLANTHEART:
//				name = GameStaticImage.shop_heart_01;
//				temp = LangUtil.getLangString(LangDefineClient.REWARD_HEART);
//				temp = temp.replace("X", ""+FarmData.PLANTHEART);
//				VeggiesData.addHeart(FarmData.PLANTHEART);
//				break;
//			case UserRequest.FARM_PLANTCARD:
//				
//				
//				int cardID = Math.abs(new Random().nextInt()%20) + 1;
//				if (cardID  < 10) {
//					name = "smallcard1/card_pc_0" + cardID + "_s";
//				} else {				
//					name = "smallcard1/card_pc_" + cardID + "_s";			
//				}
//				temp = LangUtil.getLangString(LangDefineClient.REWARD_CARDS);
//				temp = temp.replace("X", ""+FarmData.FARM_PLANTCARD);
//				VeggiesData.setAllCardNum(cardID, 1);
//				break;
//			case UserRequest.FARM_PLANTGOLD:
//				name = GameStaticImage.shop_shop_gold_11;
//				
//				temp = LangUtil.getLangString(LangDefineClient.REWARD_GOLD);
//				int tt =(int)((FarmData.FARM_PLANTGOLD_END-FarmData.FARM_PLANTGOLD_START)*Math.random()+FarmData.FARM_PLANTGOLD_START);
//				temp = temp.replace("X", ""+tt);
//				VeggiesData.addGold(tt);
//				 
//			}
//			
//			GameManager.setPopUp(PopUp.AWARD, name, new PopUp(temp) {
//				@Override
//				public byte onTouch(MotionEvent event) {
//					// TODO Auto-generated method stub
//					byte temp = super.onTouch(event);
//					if(temp == PopUp.onTouch_share){
//						//分享
//						if(FacebookOperation.isLanding ){
//							FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_PULISH);
//						}
//						return -1;
//					}
//					return temp;
//				}
//			});
//		}
//		
//		isharvest = false;
//	}
//	
//	public void setTimeTitle(int _x, int _y){
//		 title_x = _x;
//		 title_y = _y;
//	}
//	
//	public void setButton(int _x, int _y){
//		button_x = _x;
//		button_y = _y;
//	}
//	
//	public void setMsg(String _msg){
//		msg = _msg;
////		String temp = LangUtil.getLangString(LangDefineClient.FARM_MINUTES);
//		if(!msg.equals("")){
//			timeDescribe.setString(msg);
//			timeDescribe.setBoxSize(farm_ui_back_11.bitmap.getWidth(), farm_ui_back_11.bitmap.getHeight());
//		}else{
////			timeDescribe.setString(temp);
////			timeDescribe.setBoxSize(farm_ui_back_11.bitmap.getWidth(), farm_ui_back_11.bitmap.getHeight());
//		}
//	}
//	
//	public void setTime(long _time){
//		time = _time;
//	}
//	
//	public void delete(){
//		if(share_ui_button_03!=null && share_ui_button_03.bitmap!=null)
//			GameImage.delImage(share_ui_button_03.bitmap);
//		share_ui_button_03 = null;
//		
//		if(timeDescribe!=null)
//			timeDescribe.Close();
//		timeDescribe = null;
//
//		buttonDescribe = null;
//		
//		if(s_word_num_07!=null){
//			GameImage.delImageArray(s_word_num_07);
//		}
//		s_word_num_07 = null;
//		
//		if(farm_ui_back_11!=null && farm_ui_back_11.bitmap!=null)
//			GameImage.delImage(farm_ui_back_11.bitmap);
//		farm_ui_back_11 = null;
//		
//		if(farm_plant != null){
//			for (int i = 0; i < farm_plant.length; ++i) {
//				GameImage.delImageArray(farm_plant[i]);
//			}
//		}
//		farm_plant = null;
//	}
//	
//	//设置坐标
//	public void setXY(int _x, int _y){
//		x = _x;
//		y = _y;
//	}
//	
//	//资源
//	public void init(){
//			s_time = "00:00";
//			if(s_word_num_07 ==null){
//				s_word_num_07 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_07, 11, 1, GameImage.Sort_line);
//			}
//			
//			if(farm_ui_back_11==null)
//				farm_ui_back_11 = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_back_11));
//
//			if(share_ui_button_03==null)
//				share_ui_button_03 = new Sprite(GameImage.getImage(GameStaticImage.share_ui_button_03));
//
//			if(timeDescribe == null){
//				timeDescribe = new TextBox();
//				timeDescribe.setTextAlign(TextBox.LEFT);
////				String temp = LangUtil.getLangString(LangDefineClient.FARM_MINUTES);
//				if(!msg.equals("")){
//					timeDescribe.setString(msg);
//					timeDescribe.setBoxSize(farm_ui_back_11.bitmap.getWidth(), farm_ui_back_11.bitmap.getHeight());
//				}else{
////					timeDescribe.setString(temp);
////					timeDescribe.setBoxSize(farm_ui_back_11.bitmap.getWidth(), farm_ui_back_11.bitmap.getHeight());
//				}
//			}
//			
////			if(buttonDescribe ==null){
////				buttonDescribe =  new TextBox();
////				buttonDescribe.setTextAlign(TextBox.LEFT);
//				String temp = "";
//				if(getStateSurface() == SURFACE_HARVEST){
//					 //可以收获
//					temp = LangUtil.getLangString(LangDefineClient.FARM_GATHER);
//				}else if(getStateSurface() == SURFACE_DEMANDPLANT){
//					//可以种植了
//					temp = LangUtil.getLangString(LangDefineClient.FARM_PLANT);
//				}
//				buttonDescribe = temp;
////				buttonDescribe.setTextSize((int)(20*GameConfig.f_zoom));
////				buttonDescribe.setString(temp);
////				buttonDescribe.setBoxSize(share_ui_button_03.bitmap.getWidth(), share_ui_button_03.bitmap.getHeight());
////			}
//			
//			if(farm_plant==null){
//				farm_plant = new Sprite[3][3];
//				switch(type){
//				case UserRequest.FARM_PLANTCARD:
//					String titile[] = {GameStaticImage.farm_farm_card_03, GameStaticImage.farm_farm_card_02, GameStaticImage.farm_farm_card_01};
//					for(int i=0;i<farm_plant.length;++i)
//						farm_plant[i] =  GameImage.getAutoSizecutSprite(titile[i], 3, 1, GameImage.Sort_line);
//					break;
//				case UserRequest.FARM_PLANTGOLD:
//					String titile1[] = {GameStaticImage.farm_farm_gold_03, GameStaticImage.farm_farm_gold_02, GameStaticImage.farm_farm_gold_01};
//					for(int i=0;i<farm_plant.length;++i)
//						farm_plant[i] =  GameImage.getAutoSizecutSprite(titile1[i], 3, 1, GameImage.Sort_line);
//					break;
//				case UserRequest.FARM_PLANTHEART:
//					String titile2[] = {GameStaticImage.farm_farm_heart_03, GameStaticImage.farm_farm_heart_02, GameStaticImage.farm_farm_heart_01};
//					for(int i=0;i<farm_plant.length;++i)
//						farm_plant[i] =  GameImage.getAutoSizecutSprite(titile2[i], 3, 1, GameImage.Sort_line);
//					break;
//				}
//			}
//	}
//	
//	public void paint(Canvas canvas, int animationY){
//         farm_plant[state_growth-1][tempi].drawBitmap(canvas, farm_plant[state_growth-1][tempi].bitmap, x,  y+animationY, null);
//	}
//	
//	public void drawTimeTitle(Canvas canvas, int animationY){
//		if(FarmModule.uid == FacebookOperation.player.getid_server())
//			if(state_growth == SURFACE_HARVEST || state_growth == SURFACE_DEMANDPLANT)return; //收获不绘制提示框
////		if(state_growth == SURFACE_DEMANDPLANT){
//////			farm_farm_02.drawBitmap(canvas, farm_farm_02.bitmap, x, y+animationY, null);
////		}else{
//		   farm_ui_back_11.drawBitmap(canvas, farm_ui_back_11.bitmap, title_x, title_y+animationY, null);
// 
//			canvas.save();
//			canvas.clipRect(title_x+(int)((5)*GameConfig.f_zoomx), title_y+animationY+(int)(5*GameConfig.f_zoomy)+s_word_num_07[0].bitmap.getHeight(),
//					title_x+(int)((5)*GameConfig.f_zoomx) + farm_ui_back_11.bitmap.getWidth(),
//					title_y+animationY+(int)(5*GameConfig.f_zoomy + farm_ui_back_11.bitmap.getHeight()+s_word_num_07[0].bitmap.getHeight()));
//			timeDescribe.paintText(canvas, title_x+(int)((5)*GameConfig.f_zoomx), title_y+animationY+(int)(5*GameConfig.f_zoomy)+s_word_num_07[0].bitmap.getHeight());
//		    canvas.restore();
//		    
//			s_word_num_07[0].drawBitmap( canvas, s_word_num_07, title_x+(int)(23*GameConfig.f_zoomx), title_y+animationY+(int)(5*GameConfig.f_zoomy), GameConfig.Char_num2, s_time, null, 0, 1f);
////		}
//	}
//	
//	public void drawButton(Canvas canvas, Paint paint, int animationY){
//		
//		if(FarmModule.uid != FacebookOperation.player.getid_server() && state_growth == SURFACE_DEMANDPLANT)
//		  return;
//		if(state_growth != SURFACE_PLANTING){
//			if(isharvest){
//				int loadint_x = button_x+((share_ui_button_03.bitmap.getWidth() - _module.loading[loaingIndex].bitmap.getWidth())/2);
//				int loadint_y = button_y+animationY+((share_ui_button_03.bitmap.getHeight() - _module.loading[loaingIndex].bitmap.getHeight())/2);
//				_module.loading[loaingIndex].drawBitmap(canvas, _module.loading[loaingIndex].bitmap, loadint_x, loadint_y , null);
//			}else{
//				share_ui_button_03.drawBitmap(canvas, button_x - share_ui_button_03.bitmap.getWidth()/2*(anjianBuuton?0.2f:0f), button_y+animationY - share_ui_button_03.bitmap.getHeight()/2*(anjianBuuton?0.2f:0f), anjianBuuton?1.2f:1f, anjianBuuton?1.2f:1f, 255, 0, 0, 0);
//			    int color = paint.getColor();
//			    float size = paint.getTextSize();
//			    paint.setColor(Color.BLACK);
//			    paint.setTextSize(20);
//			    FontMetrics fm = paint.getFontMetrics();
//			    int height = (int) Math.ceil(fm.descent - fm.top);
//			    canvas.drawText(buttonDescribe, button_x+((share_ui_button_03.bitmap.getWidth() - paint.measureText(buttonDescribe))/2), button_y+animationY+height+((share_ui_button_03.bitmap.getHeight() - height)/2), paint);
//			    paint.setColor(color);
//			    paint.setTextSize(size);
//			}
//		    
//		}
//	}
//	 
//
//	public void paint(Canvas canvas, int x, int y, int animationY){
//		if(farm_plant[state_growth-1][tempi]!=null)
//			farm_plant[state_growth-1][tempi].drawBitmap(canvas, farm_plant[state_growth-1][tempi].bitmap,  x,  y+animationY,  null);
//	}
//	
//	public void run(){
//
//		if(GameConfig.i_coke % 4 == 0){
//			icoke++;
//			if(icoke>=frameNumber.length) icoke = 0;
//			tempi = frameNumber[icoke];
//		}
//		if (isharvest && GameConfig.i_coke % 2 == 0) {
//			loaingIndex++;
//			if (loaingIndex == 9)
//				loaingIndex = 0;
//		}
//		currenttime = System.currentTimeMillis();
//		String _time = "";
//		long timeee = time;
//		 
//		long cd; 
//		if (timeee>currenttime && timeee - currenttime  > 0) {
//			_time = "";
//			cd = (timeee - currenttime);
//			long tempCD = cd / 1000;
//			if (tempCD % 60 == 0)
//				_time = "00";
//			else if (tempCD % 60 < 10)
//				_time = "0" + tempCD % 60;
//			else
//				_time = _time + tempCD % 60;
//			tempCD = tempCD / 60;
//			if (tempCD % 60 == 0)
//				_time = "00:" + _time;
//			else if (tempCD % 60 < 10)
//				_time = "0" + tempCD % 60 + ":" + _time;
//			else
//				_time = tempCD % 60 + ":" + _time;
//		} else { 
//			if(state_growth == SURFACE_PLANTING)
//				setStateSurface(SURFACE_HARVEST); 
//			_time = "00:00";
//		//收成
//		}
//		s_time = _time;
//	
//	}
//	
//	public void onTouch(MotionEvent event, float ani_down){
//		int eventx = (int) event.getX();
//		int eventy = (int) event.getY(); 
//		//正在生长
//		if(state_growth == SURFACE_PLANTING || (FarmModule.uid != FacebookOperation.player.getid_server() && state_growth == SURFACE_DEMANDPLANT)){
//		 return ;
//		}
//		
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			
//			if(!isharvest && ExternalMethods.CollisionTest(eventx, eventy, button_x, button_y + ani_down * GameConfig.f_zoomy, 
//					button_x + share_ui_button_03.bitmap.getWidth(), button_y+(share_ui_button_03.bitmap.getHeight()) + ani_down * GameConfig.f_zoomy )){
//					anjianBuuton =  true;
//			}
//		}else if (event.getAction() == MotionEvent.ACTION_UP) {
//			if(!isharvest && anjianBuuton && ExternalMethods.CollisionTest(eventx, eventy, button_x, button_y + ani_down * GameConfig.f_zoomy,  button_x + share_ui_button_03.bitmap.getWidth(), button_y+(share_ui_button_03.bitmap.getHeight()) + ani_down * GameConfig.f_zoomy )){
//					if(state_growth == SURFACE_HARVEST){//植物收获
//						 if(!VeggiesData.isMuteMusic())
//							 GameMedia.xl_playSound(R.raw.rewards, 0);
//						if(isGT && GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL22){ //新手引导
//
//							GameManager.getGT().finish();
//							//新手引导假的
//							String name = GameStaticImage.shop_heart_01;
//							String temp = LangUtil.getLangString(LangDefineClient.REWARD_HEART);
//							temp = temp.replace("X", ""+FarmData.PLANTHEART);
//							GameManager.setPopUp(PopUp.AWARD, name, new PopUp(temp) {
//								@Override
//								public byte onTouch(MotionEvent event) {
//									// TODO Auto-generated method stub
//									byte temp = super.onTouch(event);
//									if(temp == PopUp.onTouch_share){
//										//分享
//										if(FacebookOperation.isLanding ){
//											EndJL();
//											FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_PULISH);
//										}
//										return -1;
//									}else if( temp == onTouch_close || temp == onTouch_awardExit){
//										EndJL();
//										return -1;
//									}
//									return temp;
//								}
//							});
//						}else{
//							isharvest = true;
//							UserRequest.getUser().reqVegetableOperate(FarmModule.uid, id, UserRequest.HarvestAndSteal, type);
//						}
//					}else if(state_growth == SURFACE_DEMANDPLANT){//种植植物
//						 if(!VeggiesData.isMuteMusic())
//							 GameMedia.xl_playSound(R.raw.clicks, 0);
//						if(VeggiesData.getGameGuanka()[9] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL23] ){ //新手引导
//							GameManager.getGT().finish();
//							new VeggiesData().saveGame();
//							
//							falseMessg();
//							
//							//自己
//							UserRequest.getUser().reqVegetableOperate(FarmModule.uid, id, UserRequest.PLANT, type);
//							
//							if(_module!=null && _module._friends!=null && GameTeaching.teachingArrary[GameTeaching.TEACH_VOL23]){
//								if(VeggiesData.getGameGuanka()[9] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL24] )
//									GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL24, (int)((111) * GameConfig.f_zoomx), (int)(751 * GameConfig.f_zoomy) , LangUtil.getLangString(LangDefineClient.GUIDE_24), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
//							}
//							
//							 
//						}else if(FarmModule.uid == FacebookOperation.player.getid_server()){
//							falseMessg();
//							//自己
//							UserRequest.getUser().reqVegetableOperate(FarmModule.uid, id, UserRequest.PLANT, type);
//						}
//					}
//				}
//			anjianBuuton =  false;
//		}
//	}
//	
//	/**
//	 *  模拟一条种植成功的消息给客户端
//	 * */
//	private void falseMessg(){
//		
//		 FarmData data = null;
//		 data = FarmModule.farmdata.get(FarmModule.uid);
//		 data.setValue(id, type, "", SURFACE_PLANTING, 18000);
//		 
//		 
//		 if( UserRequest.getUser().getFarm()!=null)
//            	UserRequest.getUser().getFarm().onUpdateFarm(data);
//		 else
//			 _module.initFarmPlant(data);
//	}
//	
//	/**
//	 新手引导弹出框结束  弹出玩家获得奖励 后的操作
//	 * */
//	private void EndJL(){
//		setStateSurface(SURFACE_DEMANDPLANT); 
//		VeggiesData.addHeart(1);
//		isGT = false;
//		new VeggiesData().saveGame();
//		if(VeggiesData.getGameGuanka()[9] >= 0 && !GameTeaching.teachingArrary[GameTeaching.TEACH_VOL23] ){
//			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL23, (int)((171) * GameConfig.f_zoomx), (int)(513 * GameConfig.f_zoomy) , LangUtil.getLangString(LangDefineClient.GUIDE_23), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
//		}
//	}
//	
//}//end class
