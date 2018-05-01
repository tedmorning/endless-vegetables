package com.endlessvegetables2.ui;

import java.util.Random;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.game.data.FarmData1;
import com.game.item.FarmItem;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class FarmModule1 extends Module{
	public FarmItem item[] = new FarmItem[4];
//	public static FarmData1 data[];
	public Sprite back; //背景
	public Sprite skinLayer[];//土地表层
	public Sprite farm_ui_icon_open; //开启图标
	public Sprite farm_ui_farm_02; //未种植
	public Sprite[] icon;
	public Sprite word_num_07[]; //时间
	public Sprite word_num_03[]; 
	public Sprite[] delete; //铲子
	public Sprite[] kettle;   //水壶
	
//	public Sprite word_num_12[]; //数量
    public Sprite flower[];
	
    private boolean anjianclose;
    private boolean anjianItem[];
    final int skin_y = 30;
	/**
	 * 土壤的xy
	 * */
	int skinLayerX[] = {55,  284,  55,  284};
	int skinLayerY[] = {319-skin_y, 319-skin_y,  584-skin_y, 584-skin_y};

	public int frameNumber[] = {0, 1, 2, 1}; //帧播放的顺序
	public int oneKey[] = {0, 1, 2, 1}; //一键城守帧播放的顺序
	public Sprite[] oneKeySprite;
	int index_one_key;
	
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		anjianclose = false;
		anjianItem = new boolean[4];
		
		back = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_back));
		skinLayer = new Sprite[4];
		skinLayer[0] = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_soil_1));
		skinLayer[1] = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_soil_2));
		skinLayer[2] = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_soil_3));
		skinLayer[3] = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_soil_4));
		farm_ui_icon_open = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_icon_open));
		farm_ui_farm_02 = new Sprite(GameImage.getImage(GameStaticImage.farm_farm_02));
		word_num_07 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_07, 11, 1, GameImage.Sort_line);
		word_num_03 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_03, 11, 1, GameImage.Sort_line);
//		word_num_12 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_12, 10, 1, GameImage.Sort_line);

        delete = GameImage.getAutoSizecutSprite(GameStaticImage.farm_item_41, 4, 1, GameImage.Sort_line);; //铲子
		kettle = GameImage.getAutoSizecutSprite(GameStaticImage.farm_item_51, 4, 1, GameImage.Sort_line);; //水壶
		oneKeySprite = GameImage.getAutoSizecutSprite(GameStaticImage.farm_ui_icon_1, 3, 1, GameImage.Sort_line);; //水壶
		
		flower = new Sprite[6];
		for(int i=0;i<flower.length;i++)
		{
			flower[i] = new Sprite(GameImage.getImage("newEffect/s"+i));
		}
		
		String assname[] = {GameStaticImage.farm_ui_icon_back, GameStaticImage.farm_ui_icon_1,
				GameStaticImage.farm_ui_icon_2, GameStaticImage.farm_ui_icon_3,
				GameStaticImage.farm_ui_icon_4, GameStaticImage.farm_ui_item_1,
				GameStaticImage.farm_ui_item_2, GameStaticImage.farm_ui_item_3,
				GameStaticImage.farm_ui_icon_course_1, GameStaticImage.farm_ui_icon_course_2,
				GameStaticImage.farm_ui_icon_5, GameStaticImage.farm_ui_icon_6,
				GameStaticImage.farm_ui_icon_open_2,GameStaticImage.farm_ui_icon_open_3,
				GameStaticImage.farm_ui_icon_open_1, GameStaticImage.farm_item_4,
				GameStaticImage.farm_item_5, GameStaticImage.farm_ui_gold,//17
				GameStaticImage.farm_hand, GameStaticImage.map_heart,
				
		 };
		
		icon = new Sprite[assname.length];
		for(int i=0;i<assname.length;++i){
			icon[i] = new Sprite(GameImage.getImage(assname[i]));
		}
		
		item[0] = new FarmItem(this, (byte)0, FarmData1.TYPE[0], FarmData1.STATE[0]);
		boolean isLock = false;
		if(VeggiesData.getGameGuanka()[19] >= 0){
			isLock = true;
			if(FarmData1.TYPE[1]==FarmData1.notUnlock)
				FarmData1.TYPE[1] = 0;
		}
		item[1] = new FarmItem(this, (byte)1, isLock?FarmData1.TYPE[1]:FarmData1.notUnlock, FarmData1.STATE[1]);
		if(VeggiesData.getGameGuanka()[39] >= 0){
			isLock = true;
			if(FarmData1.TYPE[2]==FarmData1.notUnlock)
				FarmData1.TYPE[2] = 0;
		}else
			isLock = false;
		item[2] = new FarmItem(this, (byte)2, isLock?FarmData1.TYPE[2]:FarmData1.notUnlock, FarmData1.STATE[2]);
		if(FarmData1.isunLOCK){
			if(FarmData1.TYPE[3] == FarmData1.notUnlock){
				FarmData1.TYPE[3] = 0;
			}
		}
		item[3] = new FarmItem(this, (byte)3, FarmData1.isunLOCK?FarmData1.TYPE[3]:FarmData1.notUnlock, FarmData1.STATE[3]);
		
		for(int i=0;i<item.length;++i){
			item[i].setOk_num(FarmData1.DQ_TIME[i]);
			item[i].setSize_num(FarmData1.SIZE_TIME[i]);
		}
		
		if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL49]){
				if(GameManager.getGT()==null)
					GameManager.setGT(new GameTeaching());
				GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL49, (int)(skinLayerX[0]*GameConfig.f_zoomx)+(skinLayer[0].bitmap.getWidth()>>1), (int)(skinLayerY[0]*GameConfig.f_zoomy)+(skinLayer[0].bitmap.getHeight()>>1), LangUtil.getLangString(LangDefineClient.GUIDE_49), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height-GameConfig.GameScreen_Height/4);
		}
		
		return false;
	}
	@Override
	public void onreStart() {
		// TODO Auto-generated method stub
		super.onreStart();
		if(GameManager.getGT()!=null && GameManager.getGT().teachingArrary[(int)GameTeaching.TEACH_VOL53] && !GameManager.getGT().teachingArrary[(int)GameTeaching.TEACH_VOL54]){
			int plant_x = (int)((int)(skinLayerX[0]*GameConfig.f_zoomx) +skinLayer[0].bitmap.getWidth() - icon[4].bitmap.getWidth()/2-30*GameConfig.f_zoomx);
			int plant_y = (int)((int)(skinLayerY[0]*GameConfig.f_zoomy)+(skinLayer[0].bitmap.getHeight()-icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
			GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL54, (int)(plant_x)+(icon[4].bitmap.getWidth()>>1), (int)(plant_y)+(icon[4].bitmap.getHeight()>>1), LangUtil.getLangString(LangDefineClient.GUIDE_54), GameTeaching.HAND_MOVE_STATE_1,  GameConfig.GameScreen_Height-GameConfig.GameScreen_Height/4);
		}
		
	}
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		back.drawBitmap(canvas, back.bitmap, 0, 0, null);
		GameMenu.chooseLevelModule.paintTop(canvas);
		
		float button_y = GameConfig.GameScreen_Height - icon[0].bitmap.getHeight()-6*GameConfig.f_zoomy;
		float button_x = GameConfig.GameScreen_Width-icon[0].bitmap.getWidth()-6*GameConfig.f_zoomx;
		icon[0].drawBitmap(canvas, button_x - icon[0].bitmap.getWidth() / 2 * (anjianclose ? 0.2f : 0f), button_y - icon[0].bitmap.getHeight() / 2 * (anjianclose ? 0.2f : 0f), anjianclose ? 1.2f : 1f, anjianclose ? 1.2f : 1f, 255, 0, 0, 0);
		
		//三个item
		button_y = GameConfig.GameScreen_Height - icon[5].bitmap.getHeight()-6*GameConfig.f_zoomy;
		for(int i=5;i<7;++i){
			button_x = 14*GameConfig.f_zoomx+(icon[i].bitmap.getWidth()+9)*(i-5);
			icon[i].drawBitmap(canvas, button_x - icon[i].bitmap.getWidth() / 2 * (anjianItem[i-5] ? 0.2f : 0f), button_y - icon[i].bitmap.getHeight() / 2 * (anjianItem[i-5] ? 0.2f : 0f), anjianItem[i-5] ? 1.2f : 1f, anjianItem[i-5] ? 1.2f : 1f, 255, 0, 0, 0);
		    String number = "";
			if(i-5 == 0)
				number = ""+FarmData1.manureNumber;
			else if(i-5 == 1)
				number = ""+FarmData1.getDelete();
			else
				number = ""+FarmData1.liquidMedicineNuber;
			word_num_07[0].drawBitmap(canvas,
					word_num_07, (int)(button_x - icon[i].bitmap.getWidth() / 2 * (anjianItem[i-5] ? 0.2f : 0f)+6*GameConfig.f_zoomx),
					(int)(button_y - icon[i].bitmap.getHeight() / 2 * (anjianItem[i-5] ? 0.2f : 0f)+8*GameConfig.f_zoomy),
					GameConfig.Char_num2, number, null, 0, 1f);
			
		}
		//一键成熟
		button_y = GameConfig.GameScreen_Height - oneKeySprite[oneKey[index_one_key]].bitmap.getHeight()-6*GameConfig.f_zoomy;
//		button_x = 14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+15)*(7-5);
		button_x = 14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+85)*(6-5);
//		icon[2].drawBitmap(canvas, button_x - icon[2].bitmap.getWidth() / 2 * (anjianItem[3] ? 0.2f : 0f), button_y - icon[2].bitmap.getHeight() / 2 * (anjianItem[3] ? 0.2f : 0f), anjianItem[3] ? 1.2f : 1f, anjianItem[3] ? 1.2f : 1f, 255, 0, 0, 0);
//		button_x = button_x+(icon[2].bitmap.getWidth() - icon[1].bitmap.getWidth() >> 1);
//		button_y = button_y+(icon[2].bitmap.getHeight() - icon[1].bitmap.getHeight() >> 1);
//		icon[1].drawBitmap(canvas, button_x - icon[1].bitmap.getWidth() / 2 * (anjianItem[3] ? 0.2f : 0f), button_y - icon[1].bitmap.getHeight() / 2 * (anjianItem[3] ? 0.2f : 0f), anjianItem[3] ? 1.2f : 1f, anjianItem[3] ? 1.2f : 1f, 255, 0, 0, 0);
		oneKeySprite[oneKey[index_one_key]].drawBitmap(canvas, button_x - oneKeySprite[oneKey[index_one_key]].bitmap.getWidth() / 2 * (anjianItem[3] ? 0.2f : 0f), button_y - oneKeySprite[oneKey[index_one_key]].bitmap.getHeight() / 2 * (anjianItem[3] ? 0.2f : 0f), anjianItem[3] ? 1.2f : 1f, anjianItem[3] ? 1.2f : 1f, 255, 0, 0, 0);
		
		for(int i=0;i<item.length;++i){
			item[i].paintSoil(canvas, (int)(skinLayerX[i]*GameConfig.f_zoomx), (int)(skinLayerY[i]*GameConfig.f_zoomy));
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<item.length;++i){
			item[i].run();
		}
		if(GameConfig.i_coke%3==0)
		index_one_key++;
		if(index_one_key>=oneKey.length){
			index_one_key = 0;
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

	/**
	 * 植物每次状态变化需要保存一下
	 * */
	public void saveData(){
		for(int i=0;i<item.length;++i){
	    	FarmData1.TYPE[i] = item[i].getType();
	    	FarmData1.STATE[i] = item[i].getState();
	    	FarmData1.DQ_TIME[i] = item[i].getOk_num();
	    	FarmData1.SIZE_TIME[i] = item[i].getSize_num();
		}
		new VeggiesData().saveGame();
	}
	
	@Override
	public void Release() {
		// TODO Auto-generated method stub

		if(back != null && back.bitmap!=null){
			GameImage.delImage(back.bitmap);
		}
		back = null;
		
		if(skinLayer != null)
			for(int i=0;i<skinLayer.length;++i){
	
				if(skinLayer[i].bitmap!=null){
					GameImage.delImage(skinLayer[i].bitmap);
				}
			}
		skinLayer = null;
		
		if(farm_ui_icon_open != null && farm_ui_icon_open.bitmap!=null){
			GameImage.delImage(farm_ui_icon_open.bitmap);
		}
		farm_ui_icon_open = null;
		
		if(farm_ui_farm_02 != null && farm_ui_farm_02.bitmap!=null){
			GameImage.delImage(farm_ui_farm_02.bitmap);
		}
		farm_ui_farm_02 = null;
		
		
		for(int i=0;i<icon.length;++i){
			GameImage.delImage(icon[i].bitmap);
		}
		icon = null;
		
		saveData();
		
		for(int i=0;i<item.length;++i){
			item[i].delete();
			item[i] = null;
		}
		item = null;
		
		GameImage.delImageArray(word_num_07);
		word_num_07 = null;
//		GameImage.delImageArray(word_num_12);
//		word_num_12 = null;
		GameImage.delImageArray(word_num_03);
		word_num_03 = null;
		

		GameImage.delImageArray(delete);
		delete = null;
		
		GameImage.delImageArray(kettle);
		kettle = null;

		GameImage.delImageArray(oneKeySprite);
		oneKeySprite = null;
		
		for(int i=0;i<flower.length;i++)		
			GameImage.delImage(flower[i].bitmap);
		
		for(int i=0;i<flower.length;i++)
			flower[i].recycleBitmap();
			
	}

	@Override
	public void initwordpic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX(event.getActionIndex());			//获取第一个触点的 X 坐标
		float y = event.getY(event.getActionIndex());	
		
		for(int i=0;i<item.length;++i){
			item[i].onTouchEvent(event);
		}
		
		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){  //新手教学
			if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL55){
					int button_y = (int)(GameConfig.GameScreen_Height - icon[5].bitmap.getHeight()-6*GameConfig.f_zoomy);
					int	button_x = (int)(14*GameConfig.f_zoomx+(icon[6].bitmap.getWidth()+9)*(6-5));
					int w = (int)(14*GameConfig.f_zoomx+(icon[6].bitmap.getWidth()+9)*(6-5))+icon[6].bitmap.getWidth()*2;
					int h = icon[6].bitmap.getHeight();
					if(ExternalMethods.CollisionTest(x, y, button_x, button_y, (int)(w), button_y+(int)(h) )) {
						 GameManager.getGT().finish();
						 new VeggiesData().saveGame();
						 
						 button_y = (int)(GameConfig.GameScreen_Height - icon[1].bitmap.getHeight()-6*GameConfig.f_zoomy);
						 button_x = (int)(14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+85)*(6-5));
						 GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL56, button_x+(icon[1].bitmap.getWidth()>>1), button_y+(icon[1].bitmap.getHeight()>>1), LangUtil.getLangString(LangDefineClient.GUIDE_56), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
					}
					
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL56){
					float button_y = GameConfig.GameScreen_Height - icon[1].bitmap.getHeight()-6*GameConfig.f_zoomy;
					float button_x = 14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+85)*(6-5);
					if(ExternalMethods.CollisionTest(x, y, button_x, button_y, button_x+oneKeySprite[oneKey[index_one_key]].bitmap.getWidth(), button_y+oneKeySprite[oneKey[index_one_key]].bitmap.getHeight() )) {
						 anjianItem[3] = true;	   
					}
				}
			}else if(event.getActionMasked() == MotionEvent.ACTION_UP){
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL56){
					float button_y = GameConfig.GameScreen_Height - icon[1].bitmap.getHeight()-6*GameConfig.f_zoomy;
					float button_x = 14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+85)*(6-5);
					if(anjianItem[3] && ExternalMethods.CollisionTest(x, y, button_x, button_y, button_x+oneKeySprite[oneKey[index_one_key]].bitmap.getWidth(), button_y+oneKeySprite[oneKey[index_one_key]].bitmap.getHeight() )) {
						 GameManager.getGT().finish();
						 new VeggiesData().saveGame();
					}
				}
				for(int i=0;i<anjianItem.length;++i){
					anjianItem[i] = false;
				}
				anjianclose = false;
			}
			return;
		}
		
		GameMenu.chooseLevelModule.onTouchTop(event);
		
		if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
			
			float button_x = GameConfig.GameScreen_Width-icon[0].bitmap.getWidth()-6*GameConfig.f_zoomx;
			float button_y = GameConfig.GameScreen_Height - icon[0].bitmap.getHeight()-6*GameConfig.f_zoomy;
			if(ExternalMethods.CollisionTest(x, y, button_x, button_y, button_x+icon[0].bitmap.getWidth(), button_y+icon[0].bitmap.getHeight() )) {
				anjianclose = true;	   
			}
			//三个item
			button_y = GameConfig.GameScreen_Height - icon[0].bitmap.getHeight()-6*GameConfig.f_zoomy;
			for(int i=5;i<7;++i){
				 button_x = 14*GameConfig.f_zoomx+(icon[i].bitmap.getWidth()+9)*(i-5);
				 if(ExternalMethods.CollisionTest(x, y, button_x, button_y, button_x+icon[i].bitmap.getWidth(), button_y+icon[i].bitmap.getHeight() )) {
					 anjianItem[i-5] = true;	   
					}
			}
			button_y = GameConfig.GameScreen_Height - icon[1].bitmap.getHeight()-6*GameConfig.f_zoomy;
			button_x = 14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+85)*(6-5);
			if(ExternalMethods.CollisionTest(x, y, button_x, button_y, button_x+oneKeySprite[oneKey[index_one_key]].bitmap.getWidth(), button_y+oneKeySprite[oneKey[index_one_key]].bitmap.getHeight() )) {
				 anjianItem[3] = true;	   
			}
			
		} else if(event.getActionMasked() == MotionEvent.ACTION_UP){
			float button_x = GameConfig.GameScreen_Width-icon[0].bitmap.getWidth()-6*GameConfig.f_zoomx;
			float button_y = GameConfig.GameScreen_Height - icon[0].bitmap.getHeight()-6*GameConfig.f_zoomy;
			if(anjianclose && ExternalMethods.CollisionTest(x, y, button_x, button_y, button_x+icon[0].bitmap.getWidth(), button_y+icon[0].bitmap.getHeight() )) {
				GameManager.ChangeModule(null);			   
			}
			//三个item
			button_y = GameConfig.GameScreen_Height - icon[0].bitmap.getHeight()-6*GameConfig.f_zoomy;
			for(int i=5;i<7;++i){
				 button_x = 14*GameConfig.f_zoomx+(icon[i].bitmap.getWidth()+9)*(i-5);
				 if(anjianItem[i-5] && ExternalMethods.CollisionTest(x, y, button_x, button_y, button_x+icon[i].bitmap.getWidth(), button_y+icon[i].bitmap.getHeight() )) {
					System.out.println("<><> 点击Item"+(i-5)); 
					
					if(VeggiesData.getGem()>=FarmData1.BUYPROP){
						 final int itemid = i-5;
						if(itemid == 0)
							buyPropWindows(false);
						else if(itemid == 1)
							buyPropWindows(true);
//						String temp = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM4);
//						GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
//							@Override
//							public byte onTouch(MotionEvent event) {
//								// TODO Auto-generated method stub
//								byte temp = super.onTouch(event);
//								if(temp == PopUp.onTouch_googsExit){
//									VeggiesData.addGem(-FarmData1.BUYPROP);
//									if(itemid == 0){
//										//肥料
//										FarmData1.manureNumber += 1;
//									}else if(itemid == 1){
//										//铲子的个数
//										FarmData1.deleteNumber += 1;
//									}else{
//										//药水
//										FarmData1.liquidMedicineNuber += 1;
//									}
//									return -1;
//								}
//								return temp;
//							}
//						});
					}else{
						 String temp1 = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
						 GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
					}
				 }
			}
//			button_y = GameConfig.GameScreen_Height - icon[2].bitmap.getHeight();
////			button_x = 14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+15)*(7-5);
//			button_x = 14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+55)*(6-5);
			button_y = GameConfig.GameScreen_Height - icon[1].bitmap.getHeight()-6*GameConfig.f_zoomy;
			button_x = 14*GameConfig.f_zoomx+(icon[2].bitmap.getWidth()+85)*(6-5);
			if(anjianItem[3] && ExternalMethods.CollisionTest(x, y, button_x, button_y, button_x+oneKeySprite[oneKey[index_one_key]].bitmap.getWidth(), button_y+oneKeySprite[oneKey[index_one_key]].bitmap.getHeight() )) {
				System.out.println("<><> 点击Item"+(3)); 
				if(VeggiesData.getGem()>=FarmData1.ONE_EY){
					String temp1 = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM8);
					 GameManager.setPopUp(PopUp.GOOGS_BUTTON, GameStaticImage.farm_ui_item_6, new PopUp(GameStaticImage.shop_gem_12, "*"+FarmData1.ONE_EY, temp1, null, 0, false) {
							@Override
							public byte onTouch(MotionEvent event) {
								// TODO Auto-generated method stub
								byte temp = super.onTouch(event);
								if(temp == PopUp.onTouch_googsExit){
									VeggiesData.addGem(-FarmData1.ONE_EY);
									for(int i=0;i<item.length;++i){
										  if (item[i].state == FarmData1.unplanted){ //未种植
											  item[i].setStste(FarmData1.grow);
												int star = 1;
												int end = 100;
												int a =new Random().nextInt((end-star)+1)+star;
												long jilv[] =  (FarmData1.Random_Plan[item[i].id][1]);
												if(a<=jilv[0]){
										    	  a = (int)(FarmData1.Random_Plan[item[i].id][0][0]);
												}else if(a <=(jilv[1]+jilv[0]) && a >jilv[0]){
													a = (int)(FarmData1.Random_Plan[item[i].id][0][1]);
												}else if(a <=(jilv[2]+jilv[0]+jilv[1]) && a >(jilv[0]+jilv[1])){
													a = (int)(FarmData1.Random_Plan[item[i].id][0][2]);
												}else if(a <=(jilv[3]+jilv[0]+jilv[1]+jilv[2]) && a >(jilv[0]+jilv[1]+jilv[2])){
													a = (int)(FarmData1.Random_Plan[item[i].id][0][3]);
												}
							                    //每块地种出来的植物都相同
												item[i].TIME =  FarmData1.Random_Plan[item[i].id][2][0];
												item[i].setType((byte)a);
											}
										  	item[i].setMature();
									}
								}
								return temp;
							}
					 });
				}else {
					String temp1 = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
					GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
				}
			}
			for(int i=0;i<anjianItem.length;++i){
				anjianItem[i] = false;
			}
			anjianclose = false;
		}
			
	}

	/**
	 * 购买水壶 和铲子的windows
	 * */
	public void buyPropWindows(boolean isdelete){

		String temp1 = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM4);
		
		if(isdelete){ //铲子
			GameManager.setPopUp(PopUp.GOOGS_BUTTON, GameStaticImage.farm_item_4, new PopUp(GameStaticImage.shop_gem_12, "*"+FarmData1.BUYPROP, temp1, GameStaticImage.word_num_12, 1, true) {
	  			@Override
	 			public byte onTouch(MotionEvent event) {
	 				// TODO Auto-generated method stub
	 				byte temp = super.onTouch(event);
	 				if(temp == PopUp.onTouch_googsExit){
   					    //铲子的个数
						FarmData1.addDelete(1);
                        VeggiesData.addGem(-FarmData1.BUYPROP);
	 					return -1;
	 				}
	 				return temp;
	 			}
	 		});
		}else{ //水壶
			GameManager.setPopUp(PopUp.GOOGS_BUTTON, GameStaticImage.farm_item_5, new PopUp(GameStaticImage.shop_gem_12, "*"+FarmData1.BUYPROP, temp1, GameStaticImage.word_num_12, 1, true) {
	  			@Override
	 			public byte onTouch(MotionEvent event) {
	 				// TODO Auto-generated method stub
	 				byte temp = super.onTouch(event);
	 				if(temp == PopUp.onTouch_googsExit){
	 					FarmData1.manureNumber += 1;
	                    VeggiesData.addGem(-FarmData1.BUYPROP);
	 					return -1;
	 				}
	 				return temp;
	 			}
	 		});
		}
		
		 
		 
 	
	}
	
}//end class
