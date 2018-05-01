package com.endlessvegetables2.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Sprite;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.Module;
import com.socogame.coolEdit.CoolEditDefine;

public class Gameoverxiangzi extends Module{
	private Sprite xiangzi;
	private Sprite card_back[];
	private Sprite card[];
	private int card_id[];
	private int card_xy[];
	private byte card_isfankai[];
//	private Sprite ui_button_01_2;
	private Sprite key_ok;
	private Sprite icon[];
	public Sprite word_num_12[]; //数量
	public static int[]NUMBER; /**0 金币数量   1卡牌数量  2卡牌星数 3是卡牌id  4钻石数量*/
	private float r = 0f;
	private float[] ani_r = {0.2f,0.6f,1.2f,1.0f};
	private int indexaa=0;
	private static boolean isAni = true;
	//这是外部需要显示的卡牌路劲
//	public static String CardName;
	
	boolean getHideStageState=false;
	boolean isExternal; //是否外部使用
	int key_ok_y;
	boolean anjian_card[];
	boolean anjian_ok;
	int kapian_Alpha;
	int index=0;//0屏幕渐黑 1宝箱掉下 2宝箱开启动画 3卡片弹出 4等待翻卡 5等待点击ok
	int index2=0;
	int overtime=0;
	final int index2_max[]={//动画帧数
		5,//0屏幕渐黑 
		8,//1宝箱掉下
		28,//2宝箱开启动画
		28,//3卡片弹出
		-1,//4等待翻卡
		-1,//5等待点击ok
	};
	
	public Gameoverxiangzi(boolean getHideStageState){
		isExternal = false;
		overtime=-1;
		this.getHideStageState=getHideStageState;
		card_id=new int[LevelData.getData().get(7)];
		int temp=0;
//		for(int i=25; i<LevelData.getData().size(); i++) {
////			if(LevelData.getData().get(i)>0){
////				card_id[temp]=LevelData.getData().get(i-1);
//				System.out.println("clpqy:i="+i+","+LevelData.getData().get(i));
////				temp++;
////			}
//		}
		for(int i=28; i<LevelData.getData().size(); i+=3) {
			if(LevelData.getData().get(i)>0){
				card_id[temp]=LevelData.getData().get(i-1);
				temp++;
			}
		}
		initOverAward();
	}
	/**
	 * 提供外部调用显示卡牌获取 不需要宝箱下落 index = 3;
	 * isShowBox 是否显示宝箱  
	 * */
	public Gameoverxiangzi(int cardID[], boolean isShowBox){
		isExternal = true;
		getHideStageState = false;
		overtime=-1;
		if(cardID!=null){
			card_id=new int[cardID.length];
			for(int i=0;i<cardID.length;++i){
				if(cardID[i]>0){
					card_id[i]=cardID[i];
				}
			}
		}
		if(cardID!=null)
			initOverAward();
		else{
			kapian_Alpha=0;
			anjian_ok=false;
			key_ok_y=GameConfig.GameScreen_Height-(int)(100*GameConfig.f_zoom);
			
			icon = new Sprite[2];
			icon[0] = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_gold));
			icon[1] = new Sprite(GameImage.getImage(GameStaticImage.farm_ui_gem));
			word_num_12 = GameImage.getAutoSizecutSprite(GameStaticImage.word_num_12, 11, 1, GameImage.Sort_line);

			r = 0.0f;
			indexaa = 0;
			isAni = true;
		}
		if(!isShowBox)
		  index = 3;
	}
	
	private void initOverAward(){
		kapian_Alpha=0;
		anjian_ok=false;
		key_ok_y=GameConfig.GameScreen_Height-(int)(100*GameConfig.f_zoom);
		card_isfankai=new byte[card_id.length];
		for(int i=0;i<card_isfankai.length;i++){
			card_isfankai[i]=0;
		}
		anjian_card=new boolean[card_id.length];
		for(int i=0;i<anjian_card.length;i++){
			anjian_card[i]=false;
		}
		
		card_xy=new int[card_id.length*2];
		switch (card_id.length) {
		case 1:
			card_xy[0]=GameConfig.GameScreen_Width/2;
			card_xy[1]=GameConfig.GameScreen_Height/2;
			break;
		case 2:
			card_xy[0]=GameConfig.GameScreen_Width/4;
			card_xy[1]=GameConfig.GameScreen_Height/2;
			
			card_xy[2]=GameConfig.GameScreen_Width*3/4;
			card_xy[3]=GameConfig.GameScreen_Height/2;
			break;
		case 3:
			card_xy[0]=GameConfig.GameScreen_Width/2;
			card_xy[1]=GameConfig.GameScreen_Height/4;
			
			card_xy[2]=GameConfig.GameScreen_Width/4;
			card_xy[3]=GameConfig.GameScreen_Height*6/10;
			
			card_xy[4]=GameConfig.GameScreen_Width*3/4;
			card_xy[5]=GameConfig.GameScreen_Height*6/10;
			break;
		}
		index=0;
		index2=0;
	}
	
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		xiangzi = new Sprite();
		xiangzi.initSprite(CoolEditDefine.SUPER_BOX, GameConfig.GameScreen_Width/2, (int)(-50*GameConfig.f_zoom), Sprite.SPRITE_STATE_NORMAL);
		SpriteLibrary.loadSpriteImage(CoolEditDefine.SUPER_BOX);
		
		xiangzi.changeAction(0);
		card_back = new Sprite[3];
		for (int i = 1; i < card_back.length+1; i++) {
			card_back[i-1] = new Sprite(GameImage.getImage("bigcard/card_back_"+i));
		}
		if(card_id!=null){
			card = new Sprite[card_id.length];
			for (int i = 0; i < card.length; i++) {
				if(card_id[i]>9){
	//				card[i] = new Sprite(GameImage.zoomImage(GameImage.getImage("bigcard/card_pc_"+card_id[i]),0.52f,0.52f));
					card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_"+card_id[i]));
	//				card[i].bitmap=GameImage.zoomImage(card[i].bitmap,card[i].bitmap.getWidth()*0.52f,card[i].bitmap.getHeight()*0.52f);
				}else{
					card[i] = new Sprite(GameImage.getImage("bigcard/card_pc_0"+card_id[i]));
	//				card[i].bitmap=GameImage.zoomImage(card[i].bitmap,card[i].bitmap.getWidth()*0.52f,card[i].bitmap.getHeight()*0.52f);
	//				card[i] = new Sprite(GameImage.zoomImage(GameImage.getImage("bigcard/card_pc_0"+card_id[i]),0.52f,0.52f));
				}
			}
		}
		
//		ui_button_01_2 = new Sprite(GameImage.getImage("share/ui_button_01_2"));
		key_ok = new Sprite(GameImage.getImage(Configs.filePath+"/word/key_ok"));
		return false;
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		if(index==0){
			xiangzi.drawBitmap(canvas, new Paint(), Color.BLACK, 100*index2/index2_max[0], 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		}else{
			xiangzi.drawBitmap(canvas, new Paint(), Color.BLACK, 100, 0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		}
		if(isExternal){
			xiangzi.paintSprite(canvas, 0, 0);
		}else
			if(index==1||index==2||index==3){
				xiangzi.paintSprite(canvas, 0, 0);
			}
		if(index==3 && card_id!=null){
			for (int i = 0; i < card_id.length; i++) {
//				float size=0.1f+0.42f*index2/index2_max[3];
				float size=0.1f+0.8f*index2/index2_max[3];
				card_back[(GameConfig.i_coke%4)/2].drawBitmap(canvas, GameConfig.GameScreen_Width/2+(card_xy[i*2]-GameConfig.GameScreen_Width/2)*index2/index2_max[3]-card_back[(GameConfig.i_coke%4)/2].bitmap.getWidth()*size/2, GameConfig.GameScreen_Height/2+(card_xy[i*2+1]-GameConfig.GameScreen_Height/2)*index2/index2_max[3]-card_back[(GameConfig.i_coke%4)/2].bitmap.getHeight()*size/2, size, size, kapian_Alpha, 0, 0, 0, 0, 0, 0);
			}
		}else if(index>3 && card_id!=null){
			for (int i = 0; i < card_id.length; i++) {
				if(card_isfankai[i]>5){
					float size=0.52f;
					card[i].drawBitmap(canvas,card_xy[i*2]-card[i].bitmap.getWidth()*size/2, card_xy[i*2+1]-card[i].bitmap.getHeight()*size/2, size, size, 255, 0, 0, 0, 0, 0, 0);
				}else{
					card_back[card_isfankai[i]/2].drawBitmap(canvas,card_back[card_isfankai[i]/2].bitmap, card_xy[i*2]-card_back[card_isfankai[i]/2].bitmap.getWidth()/2, card_xy[i*2+1]-card_back[card_isfankai[i]/2].bitmap.getHeight()/2,null);
				}
			}
		}else if(index>3 && isExternal && card_id==null){ //农场奖励界面
//			word_num_12[0].drawBitmap(canvas,
//					word_num_12, (int)(button_x - icon[i].bitmap.getWidth() / 2 * (anjianItem[i-5] ? 0.2f : 0f)+6*GameConfig.f_zoomx),
//					(int)(button_y - icon[i].bitmap.getHeight() / 2 * (anjianItem[i-5] ? 0.2f : 0f)+8*GameConfig.f_zoomy),
//					GameConfig.Char_num2, number, null, 0, 1f);

//			public static int[]NUMBER; /**0 金币数量   1卡牌数量  2卡牌星数 3是卡牌id  4钻石数量*/
			String goldNumber = ""+NUMBER[0];
			String cardNumber = ""+NUMBER[1];
			String getNumber = ""+NUMBER[4];
			 
			float icon_x = xiangzi.x-140*GameConfig.f_zoomx;
			float icon_y = xiangzi.y-110*GameConfig.f_zoomy;
//			icon[0].drawBitmap(canvas, icon[0].bitmap, icon_x, icon_y, null);
			icon[0].drawBitmap(canvas, 
					icon_x + icon[0].bitmap.getWidth()/2*(1-r), 
					icon_y + icon[0].bitmap.getHeight()/2*(1-r), 
					r, r, 255, 0, 0, 0);
			icon_x = icon_x+(icon[0].bitmap.getWidth() - (goldNumber.length()*word_num_12[0].bitmap.getWidth()+goldNumber.length()) >>1);
			if(!isAni)
				word_num_12[0].drawBitmap(canvas, word_num_12, (int)(icon_x), (int)(icon_y+icon[0].bitmap.getHeight()+10*GameConfig.f_zoomy), GameConfig.Char_num2, goldNumber, null, 0, 1f);
			

			if(NUMBER[3]>=1){
				icon_x = xiangzi.x-30*GameConfig.f_zoomx;
				icon_y = xiangzi.y-162*GameConfig.f_zoomy;
				int temp = 0;
				if(NUMBER[2] == 0)
					temp = 0;
				else if(NUMBER[2] == 1)
					temp = 4;
				else if(NUMBER[2] == 2)
					temp = 5;
				else if(NUMBER[2] == 3)
					temp = 6;
//				GameStaticImage.noCard[temp].drawBitmap(canvas, GameStaticImage.noCard[temp].bitmap, icon_x, icon_y, null);
				GameStaticImage.noCard[temp].drawBitmap(canvas, 
						icon_x + GameStaticImage.noCard[temp].bitmap.getWidth()/2*(1-r), 
						icon_y + GameStaticImage.noCard[temp].bitmap.getHeight()/2*(1-r), 
						r, r, 255, 0, 0, 0);
				
//				GameEquipmentModule.s_smallcard_card.get(NUMBER[3]).drawBitmap(canvas, 
//				GameEquipmentModule.s_smallcard_card.get(NUMBER[3]).bitmap, icon_x, icon_y, null);
				GameEquipmentModule.s_smallcard_card.get(NUMBER[3]).drawBitmap(canvas, 
						icon_x + GameEquipmentModule.s_smallcard_card.get(NUMBER[3]).bitmap.getWidth()/2*(1-r), 
						icon_y + GameEquipmentModule.s_smallcard_card.get(NUMBER[3]).bitmap.getHeight()/2*(1-r), 
						r, r, 255, 0, 0, 0);
				icon_x = icon_x+(GameStaticImage.noCard[0].bitmap.getWidth() - (cardNumber.length()*word_num_12[0].bitmap.getWidth()+cardNumber.length()) >>1);
				if(!isAni)
					word_num_12[0].drawBitmap(canvas, word_num_12, (int)(icon_x), (int)(icon_y+GameStaticImage.noCard[0].bitmap.getHeight()+10*GameConfig.f_zoomy), GameConfig.Char_num2, cardNumber, null, 0, 1f);
			
			}
			icon_x = xiangzi.x+(int)(70*GameConfig.f_zoomx);
			icon_y = xiangzi.y-80*GameConfig.f_zoomy;
//			icon[1].drawBitmap(canvas, icon[1].bitmap, icon_x, icon_y, null);
			icon[1].drawBitmap(canvas, 
					icon_x + icon[1].bitmap.getWidth()/2*(1-r), 
					icon_y + icon[1].bitmap.getHeight()/2*(1-r), 
					r, r, 255, 0, 0, 0);
			icon_x = icon_x+(icon[1].bitmap.getWidth() - (getNumber.length()*word_num_12[0].bitmap.getWidth()+getNumber.length()) >>1);
			if(!isAni)
				word_num_12[0].drawBitmap(canvas, word_num_12, (int)(icon_x), (int)(icon_y+icon[1].bitmap.getHeight()+10*GameConfig.f_zoomy), GameConfig.Char_num2, getNumber, null, 0, 1f);
			
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, GameConfig.GameScreen_Width/2-GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2-(anjian_ok?GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/10:0), key_ok_y-GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2-(anjian_ok?GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/10:0), anjian_ok?1.2f:1f, anjian_ok?1.2f:1f, 255, 0, 0, 0, 0, 0, 0);
			key_ok.drawBitmap(canvas, GameConfig.GameScreen_Width/2-key_ok.bitmap.getWidth()/2-(anjian_ok?key_ok.bitmap.getWidth()/10:0), key_ok_y-key_ok.bitmap.getHeight()/2-(anjian_ok?key_ok.bitmap.getWidth()/10:0), anjian_ok?1.2f:1f, anjian_ok?1.2f:1f, 255, 0, 0, 0, 0, 0, 0);
		}
		
		if(index==5){
			GameStaticImage.s_share_ui_button_01[1].drawBitmap(canvas, GameConfig.GameScreen_Width/2-GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2-(anjian_ok?GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/10:0), key_ok_y-GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2-(anjian_ok?GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/10:0), anjian_ok?1.2f:1f, anjian_ok?1.2f:1f, 255, 0, 0, 0, 0, 0, 0);
			key_ok.drawBitmap(canvas, GameConfig.GameScreen_Width/2-key_ok.bitmap.getWidth()/2-(anjian_ok?key_ok.bitmap.getWidth()/10:0), key_ok_y-key_ok.bitmap.getHeight()/2-(anjian_ok?key_ok.bitmap.getWidth()/10:0), anjian_ok?1.2f:1f, anjian_ok?1.2f:1f, 255, 0, 0, 0, 0, 0, 0);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		if(isAni && index>3 && isExternal && card_id==null) {
			if (GameConfig.i_coke % 2 < 1) {
				if (r != ani_r[ani_r.length-1]) {
					r = ani_r[indexaa++];
				} else if (r == ani_r[ani_r.length-1]) {
					isAni = false;
				}
			}
		}
		
		if(overtime>=0){
			overtime++;
			if(overtime>70 && card!=null){
				overtime=-1;
				for (int i = 0; i < card.length; i++) {
					if(card_isfankai[i]==0){
						card_isfankai[i]=1;
					}
				}
			}
		}
		if(index2_max[index]>=0){
			switch (index) {
			case 1://1宝箱掉下
				xiangzi.y=xiangzi.org_y+(GameConfig.GameScreen_Height/2-xiangzi.org_y)*index2/index2_max[1];
				break;
			case 3://3卡片弹出
				if(isExternal && card_isfankai==null ){
					
				}else{
					xiangzi.Alpha-=10;
					if(xiangzi.Alpha<0){
						xiangzi.Alpha=0;
					}
					kapian_Alpha+=6;
					if(kapian_Alpha>255){
						kapian_Alpha=255;
					}
				}
				break;
			}
			index2++;
			if(index2>index2_max[index]){
				index++;
				index2=0;
				if(index==2){//2宝箱开启动画
					xiangzi.y = GameConfig.GameScreen_Height/2;
					xiangzi.changeAction(1);
				}else if(index==3){//3卡片弹出
					xiangzi.changeAction(2);
					kapian_Alpha=0;
				}else if(index==4){
					kapian_Alpha=255;
					overtime=0;
				}
			}
		}
		if(index==1||index==2||index==3){
			xiangzi.updataSprite();
		}
		if(index==4 && card_isfankai!=null){
			int temp_fankai_num=0;
			for(int i=0;i<card_isfankai.length;i++){
				if(card_isfankai[i]>0){
					card_isfankai[i]++;
					if(card_isfankai[i]>6){
						card_isfankai[i]=6;
						temp_fankai_num++;
					}
				}
			}
			if(temp_fankai_num>=card_isfankai.length){
				index=5;
				index2=0;
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
		if(xiangzi!=null){
			xiangzi.recycleBitmap();
		}
		for(int i=0;i<card_back.length;i++){
			GameImage.delImage(card_back[i].bitmap);
			card_back[i].recycleBitmap();
		}
		if(card!=null)
			for(int i=0;i<card.length;i++){
				GameImage.delImage(card[i].bitmap);
				card[i].recycleBitmap();
			}
		
		if(icon!=null)
			for(int i=0;i<icon.length;i++){
				GameImage.delImage(icon[i].bitmap);
				icon[i].recycleBitmap();
			}
		if(word_num_12!=null)
			GameImage.delImageArray(word_num_12);
		word_num_12 = null;
		
//		GameImage.delImage(ui_button_01_2.bitmap);
//		ui_button_01_2.recycleBitmap();
		
		GameImage.delImage(key_ok.bitmap);
		key_ok.recycleBitmap();		
	}

	@Override
	public void initwordpic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int x=(int)event.getX();
		int y=(int)event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if(index==4 && card!=null){//4等待翻卡
				for (int i = 0; i < card.length; i++) {
					if(card_isfankai[i]<=0&&ExternalMethods.CollisionTest(x, y,card_xy[i*2]-card_back[0].bitmap.getWidth()/2,card_xy[i*2+1]-card_back[0].bitmap.getHeight()/2,card_xy[i*2]+card_back[0].bitmap.getWidth()/2,card_xy[i*2+1]+card_back[0].bitmap.getHeight()/2)){
						anjian_card[i]=true;
					}
				}
			}else if(index==5){//5等待点击ok
				if(ExternalMethods.CollisionTest(x, y,GameConfig.GameScreen_Width/2-GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y-GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2,GameConfig.GameScreen_Width/2+GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y+GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)){
					anjian_ok=true;
				}
			}else if(!isAni && index>3 && isExternal && card_id==null){
				if(ExternalMethods.CollisionTest(x, y,GameConfig.GameScreen_Width/2-GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y-GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2,GameConfig.GameScreen_Width/2+GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y+GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)){
					anjian_ok=true;
				}
			}
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			if(index==4 && card!=null){//4等待翻卡
				for (int i = 0; i < card.length; i++) {
					if(anjian_card[i]&&ExternalMethods.CollisionTest(x, y,card_xy[i*2]-card_back[0].bitmap.getWidth()/2,card_xy[i*2+1]-card_back[0].bitmap.getHeight()/2,card_xy[i*2]+card_back[0].bitmap.getWidth()/2,card_xy[i*2+1]+card_back[0].bitmap.getHeight()/2)){
						card_isfankai[i]=1;
						overtime=-1;
					}
				}
			}else if(index==5){//5等待点击ok
				if(!isExternal && anjian_ok&&ExternalMethods.CollisionTest(x, y,GameConfig.GameScreen_Width/2-GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y-GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2,GameConfig.GameScreen_Width/2+GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y+GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)){
					GameManager.ChangeModule(null);
					GameManager.forbidModule(new LevelSuccessModule(getHideStageState));
				}else if(isExternal && anjian_ok&&ExternalMethods.CollisionTest(x, y,GameConfig.GameScreen_Width/2-GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y-GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2,GameConfig.GameScreen_Width/2+GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y+GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)){
					GameManager.ChangeModule(null);
					if(card_id!=null){
						for(int i=0;i<card_id.length;++i){
							VeggiesData.setAllCardNum(card_id[i], 1);
						}
					}
				 
				} {
					
				}
			}else if(index>3 && isExternal && card_id==null && anjian_ok&&ExternalMethods.CollisionTest(x, y,GameConfig.GameScreen_Width/2-GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y-GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2,GameConfig.GameScreen_Width/2+GameStaticImage.s_share_ui_button_01[1].bitmap.getWidth()/2,key_ok_y+GameStaticImage.s_share_ui_button_01[1].bitmap.getHeight()/2)){
				 //先把奖励给了
				if(NUMBER[3]>=1)
					VeggiesData.setAllCardNum(NUMBER[3], NUMBER[1]);
				VeggiesData.addGem(NUMBER[4]);	
				VeggiesData.addGold(NUMBER[0]);
				GameManager.ChangeModule(null);
				for(int i=0;i<NUMBER.length;++i){
					NUMBER[i] = 0;
				}
			}
			
			anjian_ok=false;
			if(card!=null)
				for(int i = 0; i < card.length; i++){
					anjian_card[i]=false;
				}
		}
	}

}
