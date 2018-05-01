package com.endlessvegetables2.ui;

import java.util.ArrayList;
import java.util.List;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * 底部菜单
 * */
public class BottomMenu {
	
	MySprite sprite1;
	MySprite sprite2;
	Sprite heidi;
	/**
	 * 退出
	 * */
	public static final byte EXIT = 0;
	
	/**
	 * 卡牌库
	 * */
	public static final byte CARDDEPOT = 1;
	
	/**
	 * 商店
	 * */
	public static final byte SHOP = 2;
	
	/**
	 * 农场
	 * */
	public static final byte FARM = 3;
	
	private List<Byte> list = new ArrayList<Byte>();
	
	
	int run_x = 0;
	int sizeWidth = 0;
	int interval = 25; //间隔
	boolean anjianOPen; //打开
	boolean open;
	boolean isok;
	
	int number = 0;
	boolean isLeft;
	boolean isRight;
	boolean isMagnify; //是否放大
	
	public BottomMenu(MySprite sprite1, MySprite sprite2){
		this.sprite1 = sprite1;
		this.sprite2 = sprite2;
	    heidi = new Sprite(GameImage.getImage(GameStaticImage.interface_heidi));
		run_x = 0;
		if(GameTeaching.teachingArrary[GameTeaching.TEACH_VOL20] ){
	           list.add(FARM);
	    }
		if(GameTeaching.teachingArrary[GameTeaching.TEACH_VOL4] ){
		     list.add(SHOP);
		}
	    if(GameTeaching.teachingArrary[GameTeaching.TEACH_VOL31] ){
           list.add(CARDDEPOT);
	    }
	    if(GameManager.getGT()!=null){
			if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL4){
				 list.add(SHOP);
				 GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL4, (int)(getXYWH(SHOP)[0]+getXYWH(SHOP)[2]/2), (int)(getXYWH(SHOP)[1]+getXYWH(SHOP)[3]/2), LangUtil.getLangString(LangDefineClient.GUIDE_4), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
			}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL20){
				 list.add(FARM);
	            GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL20, (int)(getXYWH(FARM)[0]+getXYWH(FARM)[2]/2), (int)(getXYWH(FARM)[1]+getXYWH(FARM)[3]/2), LangUtil.getLangString(LangDefineClient.GUIDE_20), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
			}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL31){
				 list.add(CARDDEPOT);
				 GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL31, (int)(getXYWH(CARDDEPOT)[0]+getXYWH(CARDDEPOT)[2]/2), (int)(getXYWH(CARDDEPOT)[1]+getXYWH(CARDDEPOT)[3]/2), LangUtil.getLangString(LangDefineClient.GUIDE_31), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
			}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL46){
				GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL46, (int)(getXYWH(CARDDEPOT)[0]+getXYWH(CARDDEPOT)[2]/2), (int)(getXYWH(CARDDEPOT)[1]+getXYWH(CARDDEPOT)[3]/2), LangUtil.getLangString(LangDefineClient.GUIDE_46), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height/2);
			}
		}
	    
	    list.add(EXIT);
	    sizeWidth = (getXYWH(EXIT)[0]+(int)(sprite1.getImageWidth("back_3.png"))) - ((int)(9*GameConfig.f_zoomx) + (int)(sprite2.getImageWidth("caidan1.png")+(interval>>1)*GameConfig.f_zoomx))+(int)(10*GameConfig.f_zoomx);
	    
	    run_x = -sizeWidth; 
	    isRight = true;
	    open = true;
	}
	public void delete(){
		GameImage.delImage(heidi.bitmap);
		heidi = null;
	}
	
	float menuMagnify1[] = {0.6f, 0f, 0.2f, 0f, 0.1f};
	float menuMagnify2[] = {1.6f, 0f, 1.2f, 0f, 1.1f};
	int index = 0;
	public void draw(Canvas canvas){
//		int y = GameConfig.GameScreen_Height;
		int open_x = (int)(36*GameConfig.f_zoomx);
		int open_y = (int)(779*GameConfig.f_zoomy);
		int height =heidi.bitmap.getHeight();
		
		if(run_x==0)
			heidi.drawBitmap(canvas, heidi.bitmap, open_x, open_y, null);
		
		open_x = (int)(9*GameConfig.f_zoomx);
		
		if(!isok && menuMagnify1[index]!=0){
	//		if(!open)
			float xx = - sprite2.getImageWidth("caidan1.png") / 2 * (open ? menuMagnify1[index] : 0f);
			float yy = - sprite2.getImageHeight("caidan1.png") / 2 * (open ? menuMagnify1[index] : 0f);
			sprite2.drawBitmap(canvas, "caidan1.png", open_x+xx , open_y+(((height - sprite2.getImageHeight("caidan1.png"))/2))+yy, open ? menuMagnify2[index] : 1f, open ? menuMagnify2[index] : 1f, 255, 0, 0, 0);
	//		else
	//			sprite2.drawBitmap(canvas, "caidan3", open_x , open_y+(((height - sprite2.getImageHeight("caidan3.png"))/2)), 1f, 1f, 255, 0, 0, 0);	
		}else{
			
			sprite2.drawBitmap(canvas, "caidan1.png", open_x , open_y+(((height - sprite2.getImageHeight("caidan1.png"))/2)),  1f, 1f, 255, 0, 0, 0);
			//		
		}
			
		int x = open_x + (int)(sprite2.getImageWidth("caidan1.png") );
		
		canvas.save();
		canvas.clipRect(x+10*GameConfig.f_zoomx, open_y +(((height - sprite2.getImageHeight("caidan1.png"))/2)), GameConfig.GameScreen_Width, GameConfig.GameScreen_Height);
		x = open_x + (int)(sprite2.getImageWidth("caidan1.png")+(interval>>1)*GameConfig.f_zoomx)+run_x;
		for(int i=0;i<list.size();++i){
			int temp_x =  (int)(i*(interval*GameConfig.f_zoomx + sprite1.getImageWidth("farmbutton2.png")));
			switch(list.get(i)){
			case FARM: //农场
				float xx = - sprite1.getImageWidth("farmbutton2.png") / 2 * (isMagnify ? 0.2f : 0f);
				float yy = - sprite1.getImageHeight("farmbutton2.png") / 2 * (isMagnify ? 0.2f : 0f);
				sprite1.drawBitmap(canvas, "farmbutton2.png", x+temp_x+xx , open_y +((height - sprite1.getImageHeight("farmbutton2.png"))/2)+yy , isMagnify ? 1.2f : 1f, isMagnify ? 1.2f : 1f, 255, 0, 0, 0);
				break;
			case SHOP: //商店
				xx = - sprite2.getImageWidth("shop.png") / 2 * (isMagnify ? 0.2f : 0f);
				yy = - sprite2.getImageHeight("shop.png") / 2 * (isMagnify ? 0.2f : 0f);
				
				sprite2.drawBitmap(canvas, "shop.png", x+temp_x+xx , open_y +((height - sprite2.getImageHeight("shop.png"))/2)+yy , isMagnify ? 1.2f : 1f, isMagnify ? 1.2f : 1f, 255, 0, 0, 0);			
				break;
			case CARDDEPOT:  //卡牌
				xx = - sprite2.getImageWidth("card.png") / 2 * (isMagnify ? 0.2f : 0f);
				yy = - sprite2.getImageHeight("card.png") / 2 * (isMagnify ? 0.2f : 0f);
				
				sprite2.drawBitmap(canvas, "card.png", x+temp_x+xx , open_y +((height - sprite2.getImageHeight("card.png"))/2)+yy , isMagnify ? 1.2f : 1f, isMagnify ? 1.2f : 1f, 255, 0, 0, 0);			
				break;
			case EXIT: //退出
				xx = - sprite1.getImageWidth("back_3.png") / 2 * (isMagnify ? 0.2f : 0f);
				yy = - sprite1.getImageHeight("back_3.png") / 2 * (isMagnify ? 0.2f : 0f);
				
				sprite1.drawBitmap(canvas, "back_3.png", x+temp_x+xx , open_y +((height - sprite1.getImageHeight("back_3.png"))/2)+yy , isMagnify ? 1.2f : 1f, isMagnify ? 1.2f : 1f, 255, 0, 0, 0);
				break;
			}
		}
		canvas.restore();
		isMagnify = false;
	}
	
	public void run(){
		if(isLeft){
			number+=(number+20);
//			for(int i=0;i<number;++i){
				run_x-=number;
				if(run_x<=-sizeWidth){
					run_x = -sizeWidth;
					number = 0;
					isLeft = false;
//					break;
				}
//			}
		}else if(isRight){
			number+=(number+20);
			for(int i=0;i<number;++i){
				run_x+=1;
				if(run_x>=(int)(20*GameConfig.f_zoomx)){
					run_x = 0;
					number = 0;
					isRight = false;
					isMagnify = true;
					break;
				}
			}
		}
		if(open && !isok){
			if(GameConfig.i_coke%2==0){
				index++;
				if(index>=menuMagnify1.length){
					index = 0;
					isok = true;
				}
			}
		}
			
	}
	
	/**
	 * 返回当前类型的XY宽高
	 * */
	public int[] getXYWH(int type){
		int[] temp= {-1, -1, -1, -1};
		
		
		int open_x = (int)(9*GameConfig.f_zoomx);
		int open_y = (int)(779*GameConfig.f_zoomy);
		int height =heidi.bitmap.getHeight();
		int x = open_x + (int)(sprite2.getImageWidth("caidan1.png")+(interval>>1)*GameConfig.f_zoomx)+run_x;
		 
		for(int i=0;i<list.size();++i){
			if(list.get(i) == type){
				int temp_x =  (int)(i*(interval*GameConfig.f_zoomx + sprite1.getImageWidth("farmbutton2.png")));
				switch(list.get(i)){
				case FARM: //农场
					temp[0] = x+temp_x;
					temp[3] = (int)(sprite1.getImageHeight("farmbutton2.png"));
					temp[1] = (int)(open_y +((height -sprite1.getImageHeight("farmbutton2.png"))/2));
					temp[2] = (int)(sprite1.getImageWidth("farmbutton2.png"));
					break;
				case SHOP: //商店
					temp[0] = x+temp_x;
					temp[3] = (int)(sprite2.getImageHeight("shop.png"));
					temp[1] = (int)(open_y +((height -sprite2.getImageHeight("shop.png"))/2));
					temp[2] = (int)(sprite2.getImageWidth("shop.png"));
					break;
				case CARDDEPOT:  //卡牌
					temp[0] = x+temp_x;
					temp[3] = (int)(sprite2.getImageHeight("card.png"));
					temp[1] = (int)(open_y +((height - sprite2.getImageHeight("card.png"))/2));
					temp[2] = (int)(sprite2.getImageWidth("card.png"));
					break;
				case EXIT: //退出
					temp[0] = x+temp_x;
					temp[3] = (int)(sprite1.getImageHeight("back_3.png"));
					temp[1] = (int)(open_y +((height - sprite1.getImageHeight("back_3.png"))/2));
					temp[2] = (int)(sprite1.getImageWidth("back_3.png"));
					break;
				}
			}
		}
		return temp; 
	}
	 
 
	public boolean onThou(MotionEvent event){
		float startX_1 = event.getX(event.getActionIndex());			//获取第一个触点的 X 坐标
		float startY_1 = event.getY(event.getActionIndex());	
		switch(event.getActionMasked())			 
		{
		case MotionEvent.ACTION_DOWN:
			
			int open_x = (int)(9*GameConfig.f_zoomx);
			int open_y = (int)(779*GameConfig.f_zoomy);
			int height =heidi.bitmap.getHeight();
			open_y = (int)(open_y+(((height - sprite2.getImageHeight("caidan1.png"))/2)));
			//打开按钮
			if (ExternalMethods.CollisionTest(startX_1, startY_1, 
					open_x,
					open_y, 
					open_x+sprite2.getImageWidth("caidan1.png"),
					open_y +sprite2.getImageHeight("caidan1.png"))
				 ) {
				anjianOPen = true;
			}
			 
			break;
		case MotionEvent.ACTION_UP:	
			open_x = (int)(9*GameConfig.f_zoomx);
			open_y = (int)(779*GameConfig.f_zoomy);
			height =heidi.bitmap.getHeight();
			open_y = (int)(open_y+(((height - sprite2.getImageHeight("caidan1.png"))/2)));
			//打开按钮
			if (anjianOPen && ExternalMethods.CollisionTest(startX_1, startY_1, 
					open_x,
					open_y, 
					open_x+sprite2.getImageWidth("caidan1.png"),
					open_y +sprite2.getImageHeight("caidan1.png"))
				 ) {
				 open = !open;
				 if(open){
					 isok = false;
					 isRight = true;
					 isLeft = false;
				 }else{
					 isRight = false;
					 isLeft = true;
				 }
				 anjianOPen = false;
				 return true;
			}
			anjianOPen = false;
			break;
		case MotionEvent.ACTION_MOVE:
			if(anjianOPen)
					return true;
		}
		return false;
	}
	
	
}// end class;
