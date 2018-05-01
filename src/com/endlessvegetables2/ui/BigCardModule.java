package com.endlessvegetables2.ui;

import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.R;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Main;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class BigCardModule extends Module{
//	private Sprite[] card;
	private Sprite card;
	private int currentindex;
	
	private boolean isSell;
	
	int sell_num=0;
	int sell_num_max=0;
	int jiage=10;
	Bitmap bitmap_ui_button_01;
	Bitmap bitmap_key_sell;
	Bitmap bitmap_ui_close;
	Bitmap bitmap_Gold_01;
	Bitmap bitmap_key_add;
	Bitmap bitmap_key_add_2;
	Bitmap bitmap_key_minus;
	Bitmap bitmap_ui_back_11;
	Bitmap bitmap_shop_gold_07;
	
	boolean anjian_close=false;
	int anjian_jia=0;
	int anjian_jian=0;
	boolean anjian_sell=false;
	Paint paint;
	int textsize=0;
	int textstate=0;
	public BigCardModule(int index,boolean isSell) {
		currentindex = index;
		this.isSell=isSell;
		Vector<Integer> card = VeggiesData.getCardnewIcon();
		for(int k=0;k<card.size();++k){
			if((index-1) == card.get(k).intValue()){
				VeggiesData.setDeleteCardNewId((index-1));
				break;
			 }
		}
		
	}
	
	public boolean initialize() {
//		card = new Sprite[63];
//		for (int i=0; i<card.length; i++) {
//			if (i  < 9) {
//				card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (i+1)));				
//				card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (i+1)));				
//			} else {				
//				card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_" + (i+1)));				
//			}
//		}
		if (currentindex  < 10) {
			card = new Sprite(GameImage.getImage("bigcard/card_pc_0" + (currentindex)));				
		} else {				
			card = new Sprite(GameImage.getImage("bigcard/card_pc_" + (currentindex)));				
		}
		
		if(isSell){
			bitmap_Gold_01=GameImage.getImage("newui/Gold_01");
			bitmap_ui_button_01=GameImage.getImage("share/ui_button_01_2");
			bitmap_ui_close=GameImage.getImage("share/ui_close");
			bitmap_key_sell=GameImage.getImage("word/key_sell");
			bitmap_key_add=GameImage.getImage("Interface/key_add");
			bitmap_key_add_2=GameImage.getImage("Interface/key_add_2");
			bitmap_key_minus=GameImage.getImage("Interface/key_minus");
			bitmap_ui_back_11=GameImage.getImage("Interface/ui_back_11");
			bitmap_shop_gold_07=GameImage.getImage("shop/shop_gold_07");
			paint = new Paint();
			paint.setTypeface(Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF"));
//			paint.setTextSize(size);
			anjian_close=false;
			anjian_jia=0;
			anjian_jian=0;
			anjian_sell=false;
			sell_num_max=VeggiesData.getCardNum(currentindex);
			if(sell_num_max>=1){
				sell_num=1;
			}else{
				sell_num=0;
			}
			switch (currentindex%3) {
			case 1:
				jiage=25;
				break;
			case 2:
				jiage=50;
				break;
			case 3:
				jiage=100;
				break;
			default:
				jiage=10;
				break;
			}
			int tempY=(int)(115*GameConfig.f_zoom);
			//确定数量的底框
			tempY+=card.bitmap.getHeight()+20*GameConfig.f_zoom;
			tempY+=bitmap_ui_back_11.getHeight()/2;
			tempY+=bitmap_ui_back_11.getHeight()/2+25*GameConfig.f_zoom+bitmap_ui_button_01.getHeight()/2;
			int tempX=GameConfig.GameScreen_Width/2;
			if(!GameTeaching.teachingArrary[GameTeaching.TEACH_VOL48] && GameTeaching.teachingArrary[GameTeaching.TEACH_VOL47]){
				if(GameManager.getGT()==null)
					GameManager.setGT(new GameTeaching());
				GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL48, (int)(tempX), (int)(tempY), LangUtil.getLangString(LangDefineClient.GUIDE_48), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
			}
		}
		textsize=0;
		textstate=0;
		return false;
	}

	public void paint(Canvas canvas) {
		int tempX=0,tempY=0;
		if(isSell){
			tempY=(int)(115*GameConfig.f_zoom);
		}else{
			tempY=(GameConfig.GameScreen_Height - card.bitmap.getHeight())/2;
		}
		card.drawBitmap(canvas, new Paint(), Color.BLACK, 150, 0, 0,
				GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		//ExternalMethods.drawImage(canvas, card[currentindex-1].bitmap, (GameConfig.GameScreen_Width - card[currentindex-1].bitmap.getWidth())/2, (GameConfig.GameScreen_Height - card[currentindex-1].bitmap.getHeight())/2, 1f, 1f, 255, 0, 0, 0);
		card.drawBitmap(canvas, (GameConfig.GameScreen_Width - card.bitmap.getWidth())/2, tempY, 1f, 1f, 255, 0, 0, 0);
		
		if(isSell){
			//确定数量的底框
			tempY+=card.bitmap.getHeight()+20*GameConfig.f_zoom;
			tempX=GameConfig.GameScreen_Width/2-bitmap_ui_back_11.getWidth()/2;
			ExternalMethods.drawImage(canvas, bitmap_ui_back_11, tempX, tempY, 1f, 1f, 255, 0, 0, 0);
			//-号
			tempY+=bitmap_ui_back_11.getHeight()/2;
			tempX+=10*GameConfig.f_zoom;
			ExternalMethods.drawImage(canvas, bitmap_key_minus, tempX-(anjian_jian>0?bitmap_key_minus.getWidth()/10:0), tempY-bitmap_key_minus.getHeight()/2-(anjian_jian>0?bitmap_key_minus.getHeight()/10:0), anjian_jian>0?1.2f:1f, anjian_jian>0?1.2f:1f, 255, 0, 0, 0);
			//卡牌数量
			paint.setTextSize(20*GameConfig.f_zoom);
			paint.setColor(0xff99673B);
			
			Rect rect = new Rect();              
			paint.getTextBounds(sell_num+"/"+sell_num_max, 0, (sell_num+"/"+sell_num_max).length(), rect);
			tempX=GameConfig.GameScreen_Width/2-rect.width()/2;
			canvas.drawText(sell_num+"/"+sell_num_max, tempX, tempY+10*GameConfig.f_zoom, paint);
			//+号
			tempX=GameConfig.GameScreen_Width/2+bitmap_ui_back_11.getWidth()/2-(int)(10*GameConfig.f_zoom)-bitmap_key_add.getWidth();
			ExternalMethods.drawImage(canvas, bitmap_key_add, tempX-(anjian_jia>0?bitmap_key_add.getWidth()/10:0), tempY-bitmap_key_add.getHeight()/2-(anjian_jia>0?bitmap_key_add.getHeight()/10:0), anjian_jia>0?1.2f:1f, anjian_jia>0?1.2f:1f, 255, 0, 0, 0);
			
			//卖出按钮
			int tempW=bitmap_ui_back_11.getWidth();
			tempY+=bitmap_ui_back_11.getHeight()/2+25*GameConfig.f_zoom+bitmap_ui_button_01.getHeight()/2;
			tempX=GameConfig.GameScreen_Width/2-tempW/2;
			ExternalMethods.drawImage(canvas, bitmap_ui_button_01, tempX-(anjian_sell?tempW/10:0), tempY-bitmap_ui_button_01.getHeight()/2-(anjian_sell?bitmap_ui_button_01.getHeight()/10:0), anjian_sell?tempW/(float)bitmap_ui_button_01.getWidth()+0.2f:tempW/(float)bitmap_ui_button_01.getWidth(), anjian_sell?1.2f:1f, 255, 0, 0, 0);
			ExternalMethods.drawImage(canvas, bitmap_key_sell, tempX+tempW/2-bitmap_key_sell.getWidth()/2-(anjian_sell?bitmap_key_sell.getWidth()/10:0), tempY-bitmap_key_sell.getHeight()/2-(anjian_sell?bitmap_key_sell.getHeight()/10:0), anjian_sell?1.2f:1f, anjian_sell?1.2f:1f, 255, 0, 0, 0);
			
			//当前金币数显示
			tempX=(int)(10*GameConfig.f_zoom);
			tempY=(int)(10*GameConfig.f_zoom)+bitmap_Gold_01.getHeight()/2;
			canvas.drawBitmap(bitmap_Gold_01, tempX, tempY-bitmap_Gold_01.getHeight()/2, null);
			tempX+=bitmap_Gold_01.getWidth()+5;
			
			paint.setTextSize(30*GameConfig.f_zoom);
			paint.setColor(0xffffffff);
			canvas.drawText(""+VeggiesData.getGold(), tempX, tempY+15*GameConfig.f_zoom, paint);
			
			paint.getTextBounds(""+VeggiesData.getGold(), 0, (""+VeggiesData.getGold()).length(), rect);
			tempX+=rect.width()+10*GameConfig.f_zoom;
			canvas.drawBitmap(bitmap_key_add_2, tempX, tempY-bitmap_key_add_2.getHeight()/2, null);
			
			//可增加金币数
			paint.setColor(0xffffe552);
			paint.setTextSize(42*GameConfig.f_zoom+textsize*4f*GameConfig.f_zoom);
			tempX+=bitmap_key_add_2.getWidth()+10*GameConfig.f_zoom;;
			canvas.drawText(""+sell_num*jiage, tempX, tempY+15*GameConfig.f_zoom, paint);
			
			//关闭按钮
			tempX=(int)(453 * GameConfig.f_zoomx);
			tempY=(int)(76 * GameConfig.f_zoomy);
			ExternalMethods.drawImage(canvas, bitmap_ui_close, tempX-(anjian_close?bitmap_ui_close.getWidth()/10:0), tempY-(anjian_close?bitmap_ui_close.getHeight()/10:0), anjian_close?1.2f:1f, anjian_close?1.2f:1f, 255, 0, 0, 0);
		}
		
	}

	public void run() {
		if(textstate>0){
			textstate--;
			textsize++;
			if(textstate<=0){
				textstate=-5;
				textsize=0;
			}
		}else if(textstate<0){
			textstate++;
			textsize--;
			if(textstate>=0){
				textstate=0;
				textsize=0;
			}
		}
		if(anjian_jia>0){
			anjian_jia++;
			runanjian(anjian_jia,true);
		}
		if(anjian_jian>0){
			anjian_jian++;
			runanjian(anjian_jian,false);
		}
		GameMedia.clearBuffer();
	}
	
	public void runanjian(int num,boolean isjia) {
		if(num<22){
			if(num%10==1){
				runanjian2(isjia);
			}
		}else if(num<42){
			if(num%5==1){
				runanjian2(isjia);
			}
		}else if(num<62){
			if(num%2==1){
				runanjian2(isjia);
			}
		}else{
			runanjian2(isjia);
		}
	}
	
	public void runanjian2(boolean isjia) {
		if(isjia){
			sell_num++;
			if(sell_num>sell_num_max){
				sell_num=sell_num_max;
			}
			textstate=-5;
			textsize=5;
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.clicks, 0);
		}else{
			sell_num--;
			if(sell_num<0){
				sell_num=0;
			}
			textstate=-5;
			textsize=5;
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.clicks, 0);
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			GameManager.ChangeModule(null);
		}
		return false;
	}

	public void Release() {
//		for (int i=0; i<card.length; i++) {
//			GameImage.delImage(card[i].bitmap);
//			card[i].bitmap = null;
//		}
//		card = null;
		GameImage.delImage(card.bitmap);
		card = null;
		
		GameImage.delImage(bitmap_Gold_01);
		bitmap_Gold_01 = null;
		GameImage.delImage(bitmap_ui_button_01);
		bitmap_ui_button_01 = null;
		GameImage.delImage(bitmap_ui_close);
		bitmap_ui_close = null;
		GameImage.delImage(bitmap_key_sell);
		bitmap_key_sell = null;
		GameImage.delImage(bitmap_key_add);
		bitmap_key_add = null;
		GameImage.delImage(bitmap_shop_gold_07);
		bitmap_shop_gold_07 = null;
		GameImage.delImage(bitmap_key_add_2);
		bitmap_key_add_2 = null;
		GameImage.delImage(bitmap_key_minus);
		bitmap_key_minus = null;
		GameImage.delImage(bitmap_ui_back_11);
		bitmap_ui_back_11 = null;
		paint = null;
	}

	public void initwordpic() {
		
	}

	public void onTouchEvent(MotionEvent event) {
		int x=(int)event.getX();
		int y=(int)event.getY();
		if(isSell){
			if(GameManager.getGT()!=null && VeggiesData.getGameGuanka()[7] >= 0 && GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL48){
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int tempY=(int)(115*GameConfig.f_zoom);
					//确定数量的底框
					tempY+=card.bitmap.getHeight()+20*GameConfig.f_zoom;
					tempY+=bitmap_ui_back_11.getHeight()/2;
					//卖出按钮
					int tempW=bitmap_ui_back_11.getWidth();
					tempY+=bitmap_ui_back_11.getHeight()/2+25*GameConfig.f_zoom+bitmap_ui_button_01.getHeight()/2;
					int tempX=GameConfig.GameScreen_Width/2-tempW/2;
					if(ExternalMethods.CollisionTest(x, y,tempX,tempY-bitmap_ui_button_01.getHeight()/2,tempX+tempW,tempY+bitmap_ui_button_01.getHeight()/2)){
						anjian_sell=true;
					}
				}else if(event.getAction() == MotionEvent.ACTION_UP){
					int tempY=(int)(115*GameConfig.f_zoom);
					//确定数量的底框
					tempY+=card.bitmap.getHeight()+20*GameConfig.f_zoom;
					tempY+=bitmap_ui_back_11.getHeight()/2;
					//卖出按钮
					int tempW=bitmap_ui_back_11.getWidth();
					tempY+=bitmap_ui_back_11.getHeight()/2+25*GameConfig.f_zoom+bitmap_ui_button_01.getHeight()/2;
					int tempX=GameConfig.GameScreen_Width/2-tempW/2;
					if(anjian_sell&&ExternalMethods.CollisionTest(x, y,tempX,tempY-bitmap_ui_button_01.getHeight()/2,tempX+tempW,tempY+bitmap_ui_button_01.getHeight()/2)){
						if(sell_num>0){
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.buy, 0);
							String temp = LangUtil.getLangString(LangDefineClient.REWARD_GOLD);
							temp = temp.replace("X", ""+(jiage*sell_num));
							GameManager.setPopUp(PopUp.GOOGS, "shop/shop_gold_07", new PopUp(temp) {
								@Override
								public byte onTouch(MotionEvent event) {
									// TODO Auto-generated method stub
									byte temp = super.onTouch(event);
									if(temp == PopUp.onTouch_googsExit){
//										GameManager.ChangeModule(null);
										return -1;
									}
									return temp;
								}
							});
							VeggiesData.setAllCardNum(currentindex,-sell_num);
							VeggiesData.addGold(jiage*sell_num);
							sell_num_max-=sell_num;
//							sell_num=0;
							if(sell_num_max>=1){
								sell_num=1;
							}else{
								sell_num=0;
							}
							if(GameManager.getGT()!=null && VeggiesData.getGameGuanka()[7] >= 0 && GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL48){
								GameManager.getGT().finish();
								new VeggiesData().saveGame(); 
							}
						}
					}
					
					anjian_jian=0;
					anjian_jia=0;
					anjian_sell=false;
					anjian_close=false;
				}
			}else{
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int tempY=(int)(115*GameConfig.f_zoom);
					//确定数量的底框
					tempY+=card.bitmap.getHeight()+20*GameConfig.f_zoom;
					int tempX=GameConfig.GameScreen_Width/2-bitmap_ui_back_11.getWidth()/2;
					tempY+=bitmap_ui_back_11.getHeight()/2;
					tempX+=10*GameConfig.f_zoom;
					if(ExternalMethods.CollisionTest(x, y,tempX,tempY-bitmap_key_minus.getHeight()/2,tempX+bitmap_key_minus.getWidth(),tempY+bitmap_key_minus.getHeight()/2)){
						anjian_jian=1;
					}
					tempX=GameConfig.GameScreen_Width/2+bitmap_ui_back_11.getWidth()/2-(int)(10*GameConfig.f_zoom)-bitmap_key_add.getWidth();
					//+号
					if(ExternalMethods.CollisionTest(x, y,tempX,tempY-bitmap_key_add.getHeight()/2,tempX+bitmap_key_minus.getWidth(),tempY+bitmap_key_add.getHeight()/2)){
						anjian_jia=1;
					}
				
					//卖出按钮
					int tempW=bitmap_ui_back_11.getWidth();
					tempY+=bitmap_ui_back_11.getHeight()/2+25*GameConfig.f_zoom+bitmap_ui_button_01.getHeight()/2;
					tempX=GameConfig.GameScreen_Width/2-tempW/2;
					if(ExternalMethods.CollisionTest(x, y,tempX,tempY-bitmap_ui_button_01.getHeight()/2,tempX+tempW,tempY+bitmap_ui_button_01.getHeight()/2)){
						anjian_sell=true;
					}
					
					//关闭按钮
					tempX=(int)(453 * GameConfig.f_zoomx);
					tempY=(int)(76 * GameConfig.f_zoomy);
					if(ExternalMethods.CollisionTest(x, y,tempX,tempY,tempX+tempW,tempY+bitmap_ui_close.getHeight())){
						anjian_close=true;
					}
				}else if(event.getAction() == MotionEvent.ACTION_UP){
					int tempY=(int)(115*GameConfig.f_zoom);
					//确定数量的底框
					tempY+=card.bitmap.getHeight()+20*GameConfig.f_zoom;
					int tempX=GameConfig.GameScreen_Width/2-bitmap_ui_back_11.getWidth()/2;
					tempY+=bitmap_ui_back_11.getHeight()/2;
					tempX+=10*GameConfig.f_zoom;
					if(anjian_jian>0&&ExternalMethods.CollisionTest(x, y,tempX,tempY-bitmap_key_minus.getHeight()/2,tempX+bitmap_key_minus.getWidth(),tempY+bitmap_key_minus.getHeight()/2)){
						sell_num--;
						if(sell_num<0){
							sell_num=0;
						}
						textstate=5;
						textsize=0;
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.clicks, 0);
					}
					tempX=GameConfig.GameScreen_Width/2+bitmap_ui_back_11.getWidth()/2-(int)(10*GameConfig.f_zoom)-bitmap_key_add.getWidth();
					//+号
					if(anjian_jia>0&&ExternalMethods.CollisionTest(x, y,tempX,tempY-bitmap_key_add.getHeight()/2,tempX+bitmap_key_minus.getWidth(),tempY+bitmap_key_add.getHeight()/2)){
						sell_num++;
						if(sell_num>sell_num_max){
							sell_num=sell_num_max;
						}
						textstate=5;
						textsize=0;
						if(!VeggiesData.isMuteSound())
						GameMedia.playSound(R.raw.clicks, 0);
					}
					
					//卖出按钮
					int tempW=bitmap_ui_back_11.getWidth();
					tempY+=bitmap_ui_back_11.getHeight()/2+25*GameConfig.f_zoom+bitmap_ui_button_01.getHeight()/2;
					tempX=GameConfig.GameScreen_Width/2-tempW/2;
					if(anjian_sell&&ExternalMethods.CollisionTest(x, y,tempX,tempY-bitmap_ui_button_01.getHeight()/2,tempX+tempW,tempY+bitmap_ui_button_01.getHeight()/2)){
						if(sell_num>0){
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.buy, 0);
							String temp = LangUtil.getLangString(LangDefineClient.REWARD_GOLD);
							temp = temp.replace("X", ""+(jiage*sell_num));
							
							GameManager.setPopUp(PopUp.GOOGS, "shop/shop_gold_07", new PopUp(temp) {
								@Override
								public byte onTouch(MotionEvent event) {
									// TODO Auto-generated method stub
									byte temp = super.onTouch(event);
									if(temp == PopUp.onTouch_googsExit){
//										GameManager.ChangeModule(null);
										return -1;
									}
									return temp;
								}
							});
							VeggiesData.setAllCardNum(currentindex,-sell_num);
							VeggiesData.addGold(jiage*sell_num);
							sell_num_max-=sell_num;
							sell_num=0;
						}
					}
					
					//关闭按钮
					tempX=(int)(453 * GameConfig.f_zoomx);
					tempY=(int)(76 * GameConfig.f_zoomy);
					if(anjian_close&&ExternalMethods.CollisionTest(x, y,tempX,tempY,tempX+tempW,tempY+bitmap_ui_close.getHeight())){
						if(GameManager.getGT()!=null &&GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL35){
							GameManager.getGT().finish();
							new VeggiesData().saveGame(); 
						}
						GameManager.ChangeModule(null);
					}
					anjian_jian=0;
					anjian_jia=0;
					anjian_sell=false;
					anjian_close=false;
				}
			}
		}else{
			if (event.getAction() == MotionEvent.ACTION_UP) {
				if(GameManager.getGT()!=null && VeggiesData.getGameGuanka()[1] >= 0 && GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL35){
					GameManager.getGT().finish();
					new VeggiesData().saveGame(); 
				}
				GameManager.ChangeModule(null);
			}
		}
	}

}
