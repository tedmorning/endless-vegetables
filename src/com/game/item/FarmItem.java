package com.game.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import com.endlessvegetables2.ui.FarmModule1;
import com.endlessvegetables2.ui.GameItem;
import com.endlessvegetables2.ui.GameStaticImage;
import com.endlessvegetables2.ui.Gameoverxiangzi;
import com.endlessvegetables2.ui.LevelData;
import com.endlessvegetables2.ui.Location;
import com.endlessvegetables2.ui.PopUp;
import com.endlessvegetables2.ui.VeggiesData;
import com.game.data.FarmData1;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 *农场的item 
 * */
public class FarmItem {
	/**
	 * 土壤颜色 -1表示未解锁
	 * */
	private byte soil_type = -1;
	
	public byte state; //当前的状态
	
	FarmModule1 module;
	
	Sprite [] farm_plant; //植物
	PropPath sun;
	int soil_x, soil_y;
	boolean isSoil;
	boolean isProp[];
	boolean isShow[];
	int icoke = 0;
	int tempi = 0;
	
	public byte id;

	public long TIME = 1000*20;
	long ok_num; //当前的时间
//	long size_num = TIME; //总的时间
	

	private int[] flowerStep;
	private float[] flowerSize;
	private Bitmap[] bm;
	private int[] rotateAngle; 
	int number;
	int speed;
	long slowTime = 60*1000*10; //进度固定每次时间
	long saveTime; //储存减少的时间
	boolean isMax; //是否减少时间
//	int numbetTemp = 20;
	private ArrayList<HashMap<String, String>> parabola[];
	boolean isDown; //手指上下移动的状态
	int finger_y;
	boolean endl = true;
	int inex_animation;  //序列针
	boolean isAnimation; //动画开始播放
	boolean isdelete; //是否是铲子需要播放动画或者是水壶
	
	private final int JumpH[] = {70,43,30,23,16,0,
			 0,14,10,28,34,28,20,14,0,13,16,22,16,13,0,13,16,13,
			 0};
	private int waitingTime;	
	private int prop_x, prop_y;
	
	private void infoFlow(int num){
		parabola = new ArrayList[num];
		flowerStep = new int[num];
		flowerSize = new float[num];
		rotateAngle = new int[num];
		bm = new Bitmap[num];
		speed = 0;
		number = 0;
		for(int i=0;i<num;i++)
		{
			flowerStep[i] = 0;
			int angle = ExternalMethods.throwDice(0, 360);
			int move = 0; 
			flowerSize[i] = GameLibrary.getFloatRandom(1.0f, 2.5f);
			bm[i] = module.flower[ExternalMethods.throwDice(0, module.flower.length-1)].bitmap;
			rotateAngle[i] = ExternalMethods.throwDice(10, 360);
			parabola[i] = new ArrayList<HashMap<String, String>>();
			for(int j=0;j<num;j++)
			{				
				HashMap<String, String> temp = new HashMap<String, String>();
				
				temp.put("x", (int)ExternalMethods.getAngleX(angle, move)+"");
				temp.put("y", (int)ExternalMethods.getAngleY(angle, move)+"");	
				
				parabola[i].add(temp);
				
				move += 10;
			}
		}
	}
	
	public void flowerUpdata()
	{
			for(int i=0;i<parabola.length;i++)
			{						
				if(flowerStep[i]<parabola[i].size())
					flowerStep[i] ++;
				
				rotateAngle[i] += 20;				
			}
	}
	public FarmItem(FarmModule1 _module, byte _id, byte type, byte _state){

		inex_animation = 0;
		waitingTime = GameLibrary.getIntRandom(5, 10);
		
		waitingTime = JumpH.length;
		
		isProp = new boolean[2];
		isShow = new boolean[2];
		isShow[0] = false;
		isShow[1] = false;
		
		soil_type = type;
		module = _module;
		id = _id;
		setStste(_state);
		farm_plant = plan(soil_type, state);
	}
	
	public byte getType(){
		return soil_type;
	}
	public byte getState(){
		return state;
	}
	public long getOk_num(){//当前的时间
		return ok_num;
	}
	public long getSize_num(){//总的时间
		return TIME;
	}
	
	public void setOk_num(long _ok_num){//当前的时间
		 ok_num = _ok_num;
	}
	public void setSize_num(long _size_num){//总的时间
//		size_num = _size_num;
		TIME = _size_num;
	}
	
	
	/**
	 * 是用施肥功能 减少半小时
	 * */
	public void setManure(){
//		size_num-=(FarmData1.MANURE);
//		ok_num -=(FarmData1.MANURE);
		saveTime -= FarmData1.MANURE; //机率当前减少的时间
		isMax = true;
	}
	
	/**
	 * 一键成熟
	 * */
	public void setMature(){
//		size_num = System.currentTimeMillis();
		ok_num = System.currentTimeMillis();
	}
	
	public void setStste(byte _state){
		state = _state;
		if(state==FarmData1.unplanted ){ //未种植
			isShow[0] = true;  
			isShow[1] = true;   
		}else if(  state==FarmData1.get ){ //
			isShow[0] = true; 
			isShow[1] = false; 
//			flowerStep = null;
//			flowerSize = null;
		}else{
			isShow[0] = false; 
			isShow[1] = false; 
		}
		
		if(FarmData1.getDelete()<=0)
			isShow[1] = true;
		
		saveTime = 0 ;
		isMax = false;
	}
	
	/**
	 * 设置土壤种植的类型 
	 */
	public void setType(byte type){
		soil_type = type;
 
		farm_plant = plan(soil_type, state);
		
		
		if(soil_type!=FarmData1.notUnlock){
//			size_num = TIME;
			ok_num = System.currentTimeMillis()+TIME;
		}
		module.saveData();
	}
	
	public void paint(Canvas canvas, Paint paint){
		
	}
	
	/**
	 * 绘制土壤
	 * */
	public void paintSoil(Canvas canvas, int x, int y){
		soil_x = x;
		soil_y = y;
		module.skinLayer[id].drawBitmap(canvas, module.skinLayer[id].bitmap, x, y, null);
		
		if(soil_type == FarmData1.notUnlock){ //未解锁
			module.farm_ui_icon_open.drawBitmap(canvas, module.farm_ui_icon_open.bitmap, x+(module.skinLayer[id].bitmap.getWidth() - module.farm_ui_icon_open.bitmap.getWidth()>>1)+(int)(8*GameConfig.f_zoomx), y+(module.skinLayer[id].bitmap.getHeight() - (module.farm_ui_icon_open.bitmap.getHeight()*2)>>1), null);
			if(id==1){
				module.icon[12].drawBitmap(canvas, module.icon[12].bitmap, x+(module.skinLayer[id].bitmap.getWidth() - module.farm_ui_icon_open.bitmap.getWidth()>>1)+(int)(27*GameConfig.f_zoomx), y+(module.skinLayer[id].bitmap.getHeight() - (module.farm_ui_icon_open.bitmap.getHeight()*2)>>1)+(int)(21*GameConfig.f_zoomy), null);
			}else if(id==2){
				module.icon[13].drawBitmap(canvas, module.icon[13].bitmap, x+(module.skinLayer[id].bitmap.getWidth() - module.farm_ui_icon_open.bitmap.getWidth()>>1)+(int)(27*GameConfig.f_zoomx), y+(module.skinLayer[id].bitmap.getHeight() - (module.farm_ui_icon_open.bitmap.getHeight()*2)>>1)+(int)(21*GameConfig.f_zoomy), null);
			}else if(id==3){
				module.icon[14].drawBitmap(canvas, module.icon[14].bitmap, x+(module.skinLayer[id].bitmap.getWidth() - module.farm_ui_icon_open.bitmap.getWidth()>>1)+(int)(27*GameConfig.f_zoomx), y+(module.skinLayer[id].bitmap.getHeight() - (module.farm_ui_icon_open.bitmap.getHeight()*2)>>1)+(int)(21*GameConfig.f_zoomy), null);
			}
		}else{
			//水壶
			int plant_x = (int)(x - module.icon[4].bitmap.getWidth()/2+30*GameConfig.f_zoomx);
			int plant_y = (int)(y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
			module.icon[4].drawBitmap(canvas, plant_x - module.icon[4].bitmap.getWidth() / 2 * (isProp[0] ? 0.2f : 0f), plant_y - module.icon[4].bitmap.getHeight() / 2 * (isProp[0] ? 0.2f : 0f), isProp[0] ? 1.2f : 1f, isProp[0] ? 1.2f : 1f, 255, 0, 0, 0);
			if(isShow[0])
				module.icon[11].drawBitmap(canvas, plant_x - module.icon[4].bitmap.getWidth() / 2 * (isProp[0] ? 0.2f : 0f), plant_y - module.icon[4].bitmap.getHeight() / 2 * (isProp[0] ? 0.2f : 0f), isProp[0] ? 1.2f : 1f, isProp[0] ? 1.2f : 1f, 255, 0, 0, 0);
			
			//铲子
			plant_x = (int)(x +module.skinLayer[id].bitmap.getWidth() - module.icon[4].bitmap.getWidth()/2-30*GameConfig.f_zoomx);
			plant_y = (int)(y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
			module.icon[3].drawBitmap(canvas, plant_x - module.icon[3].bitmap.getWidth() / 2 * (isProp[1] ? 0.2f : 0f), plant_y - module.icon[3].bitmap.getHeight() / 2 * (isProp[1] ? 0.2f : 0f), isProp[1] ? 1.2f : 1f, isProp[1] ? 1.2f : 1f, 255, 0, 0, 0);
			if(isShow[1])
				module.icon[10].drawBitmap(canvas, plant_x - module.icon[3].bitmap.getWidth() / 2 * (isProp[1] ? 0.2f : 0f), plant_y - module.icon[3].bitmap.getHeight() / 2 * (isProp[1] ? 0.2f : 0f), isProp[1] ? 1.2f : 1f, isProp[1] ? 1.2f : 1f, 255, 0, 0, 0);
			
			
			switch(state){
			case FarmData1.unplanted: //未种植
				module.farm_ui_farm_02.drawBitmap(canvas, module.farm_ui_farm_02.bitmap, x+(module.skinLayer[id].bitmap.getWidth() - module.farm_ui_farm_02.bitmap.getWidth()>>1), y+(module.skinLayer[id].bitmap.getHeight() - (module.farm_ui_farm_02.bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy), null);
				if(endl && sun == null)
					module.icon[18].drawBitmap(canvas, module.icon[18].bitmap, soil_x+(module.skinLayer[id].bitmap.getWidth() - module.icon[18].bitmap.getWidth() >>1), soil_y - module.icon[18].bitmap.getHeight()+finger_y, null);
				
				break;
			case FarmData1.grow: //生长状态
				if(farm_plant!=null){
					plant_x = x+(module.skinLayer[id].bitmap.getWidth() - farm_plant[tempi].bitmap.getWidth()>>1)-(int)(14*GameConfig.f_zoomx);
					plant_y = y+(module.skinLayer[id].bitmap.getHeight() - (farm_plant[tempi].bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy);
					farm_plant[tempi].drawBitmap(canvas, farm_plant[tempi].bitmap, plant_x, plant_y, null);
				
					plant_x = x+(module.skinLayer[id].bitmap.getWidth() - farm_plant[tempi].bitmap.getWidth()>>1)-(int)(14*GameConfig.f_zoomx);
					plant_y = y+(module.skinLayer[id].bitmap.getHeight() - (farm_plant[tempi].bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy);
					plant_x = plant_x+(farm_plant[tempi].bitmap.getWidth() - module.icon[8].bitmap.getWidth()>>1);
					plant_y = plant_y-module.icon[8].bitmap.getHeight()*2;
					module.icon[8].drawBitmap(canvas, module.icon[8].bitmap, plant_x, plant_y, null);
				 
//					if(size_num>0){
//						long currenttime = System.currentTimeMillis();
//						long cd = (ok_num - currenttime);
//						long tempCD = cd / 1000;
//						tempCD  = size_num/1000 - tempCD;
//						int w = module.icon[8].bitmap.getWidth();
//						long dangqian = tempCD;
//						long size = size_num/1000; //换算为秒
//						long bfb = 100 * dangqian / size;
//						long cs = 100;
//						bfb = bfb * w / 100;
//						cs = w;
//						// 加成的经验条
//						canvas.save();
//						canvas.clipRect(plant_x,  plant_y, plant_x +(w * bfb / cs), plant_y+module.icon[9].bitmap.getHeight());
//						module.icon[9].drawBitmap(canvas, module.icon[9].bitmap,plant_x, plant_y, null);
//						canvas.restore();
//					}
					long currenttime = System.currentTimeMillis();
					String _time = "";
					long timeee = ok_num;
					
//					if(-ok_num>=(saveTime-currenttime) && saveTime!=0)
//						saveTime = (-ok_num);
					if(saveTime<0){
						saveTime+=60*1000;
						timeee=(timeee-(FarmData1.MANURE+saveTime));
					}else
						saveTime = 0;
					if(saveTime == 0 && isMax){
						isMax = false;
						ok_num -=(FarmData1.MANURE);
						timeee = ok_num;
					}
					
					
					
					
					if (timeee>currenttime && timeee - currenttime  > 0) {
						_time = "";
						long cd = (timeee - currenttime);
//						long tempCD = cd / 1000;
//						if (tempCD % 60 == 0)
//							_time = "00";
//						else if (tempCD % 60 < 10)
//							_time = "0" + tempCD % 60;
//						else
//							_time = _time + tempCD % 60;
//						tempCD = tempCD / 60;
//						if (tempCD % 60 == 0)
//							_time = "00:" + _time;
//						else if (tempCD % 60 < 10)
//							_time = "0" + tempCD % 60 + ":" + _time;
//						else
//							_time = tempCD % 60 + ":" + _time;
						
	 
						long tempCD = cd / 1000;
						if (tempCD % 60 == 0)
							_time = "00";
						else if (tempCD % 60 < 10)
							_time = "0" + tempCD % 60;
						else
							_time = _time + tempCD % 60;
						tempCD = tempCD / 60;
						if (tempCD % 60 == 0)
							_time = "00:" + _time;
						else if (tempCD % 60 < 10)
							_time = "0" + tempCD % 60 + ":" + _time;
						else
							_time = tempCD % 60 + ":" + _time;
						
						tempCD = tempCD / 60;
						if (tempCD % 60 == 0)
							_time = "00:" + _time;
						else if (tempCD % 60 < 10)
							_time = "0" + tempCD % 60 + ":" + _time;
						else
							_time = tempCD % 60 + ":" + _time;
						
						currenttime = System.currentTimeMillis();
						long size = TIME/1000; //换算为秒
						cd = ((timeee) - currenttime);//-saveTime
						tempCD = size - cd / 1000;
						int w = module.icon[8].bitmap.getWidth();
						long dangqian = tempCD;
						long bfb = 100 * dangqian / size;
						long cs = 100;
						bfb = bfb * w / 100;
						cs = w;
						// 加成的经验条
						canvas.save();
						canvas.clipRect(plant_x,  plant_y, plant_x +(w * bfb / cs), plant_y+module.icon[9].bitmap.getHeight());
						module.icon[9].drawBitmap(canvas, module.icon[9].bitmap,plant_x, plant_y, null);
						canvas.restore();
						
						
						
						
					} else { 
						 setStste(FarmData1.get); //收获了
	                        setType(soil_type);
						_time = "00";
						 
						module.icon[9].drawBitmap(canvas, module.icon[9].bitmap,plant_x, plant_y, null);
					//收成
					}
//					String temp = _time.substring(0, 2);
//					if(temp.equals("00")){
//						_time = _time.substring(3, _time.length());
//						temp = _time.substring(0, 2);
//						if(temp.equals("00")){
//							_time = _time.substring(3, _time.length());
//						}
//					}
					module.word_num_07[0].drawBitmap( canvas, 
							module.word_num_07, 
							plant_x+(module.icon[9].bitmap.getWidth() - (_time.length()*module.word_num_07[0].bitmap.getWidth()+(int)((_time.length()-1)*GameConfig.f_zoomx))>>1)+(int)((3)*GameConfig.f_zoomx), 
							plant_y+(module.icon[9].bitmap.getHeight() - module.word_num_07[0].bitmap.getHeight() >>1 )+(int)(2*GameConfig.f_zoomy),
							GameConfig.Char_num2, _time, null, 0, 1f);
				}
				break;
				case FarmData1.get: //收获
					plant_x = x+(module.skinLayer[id].bitmap.getWidth() - farm_plant[tempi].bitmap.getWidth()>>1)-(int)(14*GameConfig.f_zoomx);
					plant_y = y+(module.skinLayer[id].bitmap.getHeight() - (farm_plant[tempi].bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy);
					farm_plant[tempi].drawBitmap(canvas, farm_plant[tempi].bitmap, plant_x, plant_y, null);
				
//					plant_x = x - module.icon[4].bitmap.getWidth()/2;
//					plant_y = y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1);
//					module.icon[4].drawBitmap(canvas, plant_x - module.icon[4].bitmap.getWidth() / 2 * (isProp[0] ? 0.2f : 0f), plant_y - module.icon[4].bitmap.getHeight() / 2 * (isProp[0] ? 0.2f : 0f), isProp[0] ? 1.2f : 1f, isProp[0] ? 1.2f : 1f, 255, 0, 0, 0);
//
//					plant_x = x +module.skinLayer[id].bitmap.getWidth() - module.icon[4].bitmap.getWidth()/2;
//					plant_y = y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1);
//					module.icon[3].drawBitmap(canvas, plant_x - module.icon[3].bitmap.getWidth() / 2 * (isProp[1] ? 0.2f : 0f), plant_y - module.icon[3].bitmap.getHeight() / 2 * (isProp[1] ? 0.2f : 0f), isProp[1] ? 1.2f : 1f, isProp[1] ? 1.2f : 1f, 255, 0, 0, 0);
				
				
				break;
			}
		}
		
		if(!isOver() && flowerStep!=null){
			int flow_x = soil_x+(module.skinLayer[id].bitmap.getWidth() - bm[0].getWidth()>>1)+(int)(20*GameConfig.f_zoomx);
			int flow_y = soil_y;
			for(int i=0;i<parabola.length;i++)
			{
					if(flowerStep[i]<parabola[i].size())
						ExternalMethods.drawImage(canvas, bm[i], flow_x-bm[i].getWidth()/2+Integer.parseInt(parabola[i].get(flowerStep[i]).get("x")), flow_y-bm[i].getHeight()+Integer.parseInt(parabola[i].get(flowerStep[i]).get("y")), flowerSize[i], flowerSize[i], 255, rotateAngle[i], bm[i].getWidth()/2, bm[i].getHeight()/2);				
			}
			
//			String _time = "x"+number;
//			module.word_num_03[0].drawBitmap( canvas, 
//					module.word_num_03, 
//					soil_x+(module.skinLayer[id].bitmap.getWidth() - (_time.length()*module.word_num_03[0].bitmap.getWidth()+(int)((_time.length()-1)*GameConfig.f_zoomy))>>1), 
//					soil_y+(module.skinLayer[id].bitmap.getHeight() - module.word_num_03[0].bitmap.getHeight() >>1 )-(int)(100*GameConfig.f_zoomy)+speed,//
//					GameConfig.Char_num7, _time, null, 0, 1f);
//			
			
		}
		
		if(isAnimation && isdelete){
			module.delete[inex_animation].drawBitmap(canvas, module.delete[inex_animation].bitmap, x+65*GameConfig.f_zoomx, y-15*GameConfig.f_zoomy, null);
		}else if(isAnimation && !isdelete){
			module.kettle[inex_animation].drawBitmap(canvas, module.kettle[inex_animation].bitmap, x-42*GameConfig.f_zoomx, y-52*GameConfig.f_zoomy, null);
		}
		
		if(!endl){
			if(soil_type==FarmData1.FARM_PLANTHEART){
				module.icon[19].drawBitmap(canvas, module.icon[19].bitmap, soil_x+(module.skinLayer[id].bitmap.getWidth() - module.icon[19].bitmap.getWidth()>>1)+prop_x, soil_y+prop_y-(int)(80*GameConfig.f_zoomy), null);
			}else if(soil_type==FarmData1.FARM_PLANTGOLD){
				module.icon[17].drawBitmap(canvas, module.icon[17].bitmap, soil_x+(module.skinLayer[id].bitmap.getWidth() - module.icon[17].bitmap.getWidth()>>1)+prop_x, soil_y+prop_y-(int)(80*GameConfig.f_zoomy), null);
			}
			String _time = "x"+number;
			module.word_num_03[0].drawBitmap( canvas, 
					module.word_num_03, 
					soil_x+(module.skinLayer[id].bitmap.getWidth() - (_time.length()*module.word_num_03[0].bitmap.getWidth()+(int)((_time.length()-1)*GameConfig.f_zoomy))>>1), 
					soil_y+(module.skinLayer[id].bitmap.getHeight() - module.word_num_03[0].bitmap.getHeight() >>1 )-(int)(100*GameConfig.f_zoomy)+speed,//
					GameConfig.Char_num7, _time, null, 0, 1f);
			
		}
		if(sun!=null)
		  sun.draw(canvas);
	}

	
	
	 
	public void run(){
		
		if(isAnimation){
			if(GameConfig.i_coke%3==0)
				inex_animation++;
			if(isdelete && inex_animation>=module.delete.length){
				isAnimation = false;
				inex_animation = 0;
                //铲子
				FarmData1.addDelete(-FarmData1.DELETEPROP);
			
				setStste(FarmData1.unplanted);
			}else if(!isdelete && inex_animation>=module.kettle.length){
				isAnimation = false;
				inex_animation = 0;
                //水壶
				FarmData1.manureNumber-=FarmData1.MANURE_POIT[id];
				setManure();
			}
		}
		
		if(FarmData1.getDelete()<=0){
			isShow[1] = true;
		}else{
			if(state==FarmData1.unplanted ){ //未种植
				isShow[1] = true;   
			}else if(  state==FarmData1.get ){ //
				isShow[1] = false; 
			}else{
				isShow[1] = false; 
			}
		}
			
		if(farm_plant != null && GameConfig.i_coke % 4 == 0){
			icoke++;
			if(icoke>=farm_plant.length) icoke = 0;
			tempi = module.frameNumber[icoke]; 
		}
		if(flowerStep!=null)
			flowerUpdata();
			
		if(isDown){
			finger_y+=2;
			if(finger_y >= 0){
				isDown = false;
			}
		}else {
			finger_y-=1;
			if(finger_y<=-20){
				isDown = true;
			}
		}
		
		if(!endl){
			if(waitingTime>0)		
			{
				prop_y = 0;
				prop_y -= JumpH[JumpH.length-waitingTime];
				waitingTime --;	
			}else {
				if(soil_type==FarmData1.FARM_PLANTHEART){
					sun = new PropPath(FarmData1.FARM_PLANTHEART, module.icon[19].bitmap, 25, (int)(soil_x+(module.skinLayer[id].bitmap.getWidth() - module.icon[17].bitmap.getWidth()>>1)+prop_x), (int)(soil_y+prop_y-(int)(80*GameConfig.f_zoomy)), (int)(Location.BigMapMenu.heart_x * GameConfig.f_zoomx),(int)(Location.BigMapMenu.heart_y * GameConfig.f_zoomy));
				}else if(soil_type==FarmData1.FARM_PLANTGOLD){
					sun = new PropPath(FarmData1.FARM_PLANTGOLD, module.icon[17].bitmap, 25, (int)(soil_x+(module.skinLayer[id].bitmap.getWidth() - module.icon[17].bitmap.getWidth()>>1)+prop_x), (int)(soil_y+prop_y-(int)(80*GameConfig.f_zoomy)), 0, 0);
				}
				endl = true;
			}
		}
		 if(sun!=null)
			 if(sun.isEndPoint()){
				 if(sun.type==FarmData1.FARM_PLANTHEART){
					 VeggiesData.addHeart(number);
				 }else if(sun.type==FarmData1.FARM_PLANTGOLD){
				 		 VeggiesData.addGold(number);
				 }
				 sun = null;
			 }
	}
	
	private boolean isOver(){
		boolean isok =false;
		if(parabola!=null){
			for(int i=0;i<parabola.length;i++)
			{		
				if(parabola[i]!=null)
					for(int j=0;j<parabola[i].size();j++)
					{		
						if(flowerStep[i]==parabola[i].size())
							isok = true;
					}
			}
			if(!isok)
				speed-=10*GameConfig.f_zoomy;
		}
		return isok;
	}
	
	public void delete(){
		if(farm_plant != null){
				GameImage.delImageArray(farm_plant);
		}
		farm_plant = null;
		if(parabola!=null)
			for(int i=0;i<parabola.length;++i){
				parabola[i].clear();
				parabola[i] = null;
			}
		parabola = null;
	}
	
	public void onTouchEvent(MotionEvent event){
		float x = event.getX(event.getActionIndex());			//获取第一个触点的 X 坐标
		float y = event.getY(event.getActionIndex());	
		
		if(GameManager.getGT()!=null && GameManager.getGT().pauseState()){  //新手教学
			if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
				if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL49 &&
						ExternalMethods.CollisionTest(x, y, soil_x, soil_y, soil_x+module.skinLayer[id].bitmap.getWidth(), soil_y+module.skinLayer[id].bitmap.getHeight() )) {
					 isSoil = true;
				 }else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL50 ){
					 
					 	float plant_x = soil_x+(module.skinLayer[id].bitmap.getWidth() - farm_plant[tempi].bitmap.getWidth()>>1)-(int)(14*GameConfig.f_zoomx);
						float plant_y = soil_y+(module.skinLayer[id].bitmap.getHeight() - (farm_plant[tempi].bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy);
						plant_x = plant_x+(farm_plant[tempi].bitmap.getWidth() - module.icon[8].bitmap.getWidth()>>1);
						plant_y = plant_y-module.icon[8].bitmap.getHeight()*2;
						if(ExternalMethods.CollisionTest(x, y, (int)(plant_x), (int)(plant_y), (int)(plant_x)+module.skinLayer[id].bitmap.getWidth(), (int)(plant_y)+module.skinLayer[id].bitmap.getHeight() )) {

							GameManager.getGT().finish();
							new VeggiesData().saveGame();
							
							plant_x = (int)(soil_x - module.icon[4].bitmap.getWidth()/2+30*GameConfig.f_zoomx);
							plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
//							module.icon[4].drawBitmap(canvas, plant_x - module.icon[4].bitmap.getWidth() / 2 * (isProp[0] ? 0.2f : 0f), plant_y - module.icon[4].bitmap.getHeight() / 2 * (isProp[0] ? 0.2f : 0f), isProp[0] ? 1.2f : 1f, isProp[0] ? 1.2f : 1f, 255, 0, 0, 0);
							GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL51, (int)(plant_x)+(module.icon[4].bitmap.getWidth()>>1), (int)(plant_y)+(module.icon[4].bitmap.getHeight()>>1), LangUtil.getLangString(LangDefineClient.GUIDE_51), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height-GameConfig.GameScreen_Height/4);
							
							
						}
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL51 ){
					 int plant_x = (int)(soil_x - module.icon[4].bitmap.getWidth()/2+30*GameConfig.f_zoomx);
					 int plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
					 if(ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+module.icon[4].bitmap.getWidth(), plant_y+module.icon[4].bitmap.getHeight() )) {
						 isProp[0] = true;
					 }
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL52){
					
					
					if (state == FarmData1.get && farm_plant!=null && farm_plant[tempi]!=null){
						int plant_x = soil_x+(module.skinLayer[id].bitmap.getWidth() - farm_plant[tempi].bitmap.getWidth()>>1)-(int)(14*GameConfig.f_zoomx);
						int plant_y = soil_y+(module.skinLayer[id].bitmap.getHeight() - (farm_plant[tempi].bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy);
						 if(ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+farm_plant[tempi].bitmap.getWidth(), plant_y+farm_plant[tempi].bitmap.getHeight() )){

							 String temp1 = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM6);
							 //获得响应的奖励
							 GameManager.setPopUp(PopUp.GOOGS_BUTTON, GameStaticImage.shop_box_01, new PopUp(GameStaticImage.shop_gem_12, "*100", temp1, null, 0, false) {
								 @Override
				     			public byte onTouch(MotionEvent event) {
				     				// TODO Auto-generated method stub
				     				byte temp = super.onTouch(event);
				     				if(temp == PopUp.onTouch_googsExit){
				     					
				     					if(VeggiesData.getGem()>=FarmData1.BOXPROP){
				     						 VeggiesData.addGem(-FarmData1.BOXPROP);
				     						 //打开宝箱了
				     						 setStste(FarmData1.unplanted);
				     						 
				     						int star = 0;
				     						int end = FarmData1.Random_BOX[id][0].length-1;
				     						int a =new Random().nextInt((end-star)+1)+star;
				     						int cardID = FarmData1.Random_BOX[id][0][a]; //卡id
				     						
				     						star = FarmData1.Random_BOX[id][1][0];
				     						end = FarmData1.Random_BOX[id][1][1];
				     						a =new Random().nextInt((end-star)+1)+star;
				     						int gold = a; //金币数量
				     						
				     						star = FarmData1.Random_BOX[id][2][0];
				     						end = FarmData1.Random_BOX[id][2][1];
				     						a =new Random().nextInt((end-star)+1)+star;
				     						int get = a; //金币数量
				     						
				     						// /**0 金币数量   1卡牌数量  2卡牌星数 3是卡牌id  4钻石数量*/
				     						int temp_[] = {gold, 1, cardID%3==0?3:cardID%3, cardID, get};
				     						Gameoverxiangzi.NUMBER = new int[temp_.length];
				     						for(int i=0;i<temp_.length;++i){
				     							Gameoverxiangzi.NUMBER[i] = temp_[i];
				     						}
				     						GameManager.forbidModule(new Gameoverxiangzi(null, true));
				     						GameManager.getGT().finish();
											new VeggiesData().saveGame();
											
				     					}else{
				     						 String temp1 = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
					                    	 GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
				     					 }
										return -1;
				     				}
				     				return temp;
				     			}
							 });
							 
							 
							 setStste(FarmData1.unplanted);
							 
							 GameManager.getGT().finish();
							 new VeggiesData().saveGame();
							 GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL53, GameConfig.GameScreen_Width>>1, (GameConfig.GameScreen_Height>>1)+(int)(20*GameConfig.f_zoomy), LangUtil.getLangString(LangDefineClient.GUIDE_53), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height);
									
								 
							  
							  
						 }
					 }
					
				}else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL54 ){
					 int plant_x = (int)(soil_x +module.skinLayer[id].bitmap.getWidth() - module.icon[4].bitmap.getWidth()/2-30*GameConfig.f_zoomx);
					 int plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
					 if(ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+module.icon[3].bitmap.getWidth(), plant_y+module.icon[3].bitmap.getHeight() )) {
						 isProp[1] = true;
					 }
				}
			}else if(event.getActionMasked() == MotionEvent.ACTION_UP){
				 if(isSoil && ExternalMethods.CollisionTest(x, y, soil_x, soil_y, soil_x+module.skinLayer[id].bitmap.getWidth(), soil_y+module.skinLayer[id].bitmap.getHeight() )){
						  if (state == FarmData1.unplanted){ //未种植
							setStste(FarmData1.grow);
						       //每块地种出来的植物都相同
					    	TIME =  FarmData1.Random_Plan[id][2][0];
							setType(FarmData1.FARM_PLANTBox);
							GameManager.getGT().finish();
							new VeggiesData().saveGame();
							
							float plant_x = soil_x+(module.skinLayer[id].bitmap.getWidth() - farm_plant[tempi].bitmap.getWidth()>>1)-(int)(14*GameConfig.f_zoomx);
							float plant_y = soil_y+(module.skinLayer[id].bitmap.getHeight() - (farm_plant[tempi].bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy);
							plant_x = plant_x+(farm_plant[tempi].bitmap.getWidth() - module.icon[8].bitmap.getWidth()>>1);
							plant_y = plant_y-module.icon[8].bitmap.getHeight()*2;
							GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL50, (int)(plant_x)+(module.icon[8].bitmap.getWidth()>>1), (int)(plant_y)+(module.icon[8].bitmap.getHeight()>>1), LangUtil.getLangString(LangDefineClient.GUIDE_50), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height-GameConfig.GameScreen_Height/4);
							
							
						}
				 }else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL51 ){
					 int plant_x = (int)(soil_x - module.icon[4].bitmap.getWidth()/2+30*GameConfig.f_zoomx);
					 int plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
					 if( isProp[0] && ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+module.icon[4].bitmap.getWidth(), plant_y+module.icon[4].bitmap.getHeight() )) {
						 System.out.println("<><> 浇水了"+id);
//						 if(FarmData1.manureNumber>=FarmData1.MANURE_POIT[id]){
//							 FarmData1.manureNumber-=FarmData1.MANURE_POIT[id];
							 setManure();
							 
							 GameManager.getGT().finish();
							 new VeggiesData().saveGame();
							 
							plant_x = (int)(soil_x+(module.skinLayer[id].bitmap.getWidth() - farm_plant[tempi].bitmap.getWidth()>>1)-(int)(14*GameConfig.f_zoomx));
							plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight() - (farm_plant[tempi].bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy));
							GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL52, (int)(plant_x)+(farm_plant[tempi].bitmap.getWidth()>>1), (int)(plant_y)+(farm_plant[tempi].bitmap.getHeight()>>1), LangUtil.getLangString(LangDefineClient.GUIDE_52), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height-GameConfig.GameScreen_Height/4);
							 
						 }
//					 }
				 }else if(GameManager.getGT().getTeachId()==GameTeaching.TEACH_VOL54){
					 int plant_x = (int)(soil_x +module.skinLayer[id].bitmap.getWidth() - module.icon[4].bitmap.getWidth()/2-30*GameConfig.f_zoomx);
					 int plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
					 if(isProp[1] && ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+module.icon[3].bitmap.getWidth(), plant_y+module.icon[3].bitmap.getHeight() )) {
						 GameManager.getGT().finish();
						 new VeggiesData().saveGame();


						int button_y = (int)(GameConfig.GameScreen_Height - module.icon[5].bitmap.getHeight()-6*GameConfig.f_zoomy)+module.icon[6].bitmap.getHeight()/2;
						int	button_x = (int)(14*GameConfig.f_zoomx+(module.icon[6].bitmap.getWidth()+9)*(6-5));
						 GameManager.getGT().setGameTeaching((int)GameTeaching.TEACH_VOL55, button_x, button_y, (int)(14*GameConfig.f_zoomx+(module.icon[6].bitmap.getWidth()+9)*(6-5))+module.icon[6].bitmap.getWidth()*2, module.icon[6].bitmap.getHeight(),  LangUtil.getLangString(LangDefineClient.GUIDE_55), GameTeaching.HAND_MOVE_STATE_1, GameConfig.GameScreen_Height>>1);
						 GameManager.getGT().setFinalPoint((int)(button_x), (int)(button_y));
							
						
						 
					 }
				 }
				 for(int i=0;i<isProp.length;++i){
					 isProp[i] = false;
				 }
				isSoil = false;
			}
			return;
		}

		if(isAnimation)return;

		if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
			 boolean isTemp = false;
			
			 int plant_x = (int)(soil_x - module.icon[4].bitmap.getWidth()/2+30*GameConfig.f_zoomx);
			 int plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
			 if( ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+module.icon[4].bitmap.getWidth(), plant_y+module.icon[4].bitmap.getHeight() )) {
				if(!isShow[0] && saveTime==0)
				  isProp[0] = true;
				isTemp = true;
			 }
			 
			 plant_x = (int)(soil_x +module.skinLayer[id].bitmap.getWidth() - module.icon[4].bitmap.getWidth()/2-30*GameConfig.f_zoomx);
			 plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
			 if( ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+module.icon[3].bitmap.getWidth(), plant_y+module.icon[3].bitmap.getHeight() )) {
				if(!isShow[1])
				  isProp[1] = true;
				isTemp = true;
			 }	 
			 if(!isTemp && ExternalMethods.CollisionTest(x, y, soil_x, soil_y, soil_x+module.skinLayer[id].bitmap.getWidth(), soil_y+module.skinLayer[id].bitmap.getHeight() )) {
				 isSoil = true;
			 }
		}else if(event.getActionMasked() == MotionEvent.ACTION_UP){
			boolean isOne = false;
			 int plant_x = (int)(soil_x - module.icon[4].bitmap.getWidth()/2+30*GameConfig.f_zoomx);
			 int plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
			 if(!isOne && isProp[0] && ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+module.icon[4].bitmap.getWidth(), plant_y+module.icon[4].bitmap.getHeight() )) {
				 System.out.println("<><> 浇水了"+id);
				 if(FarmData1.manureNumber>=FarmData1.MANURE_POIT[id]){
					 isdelete = false;
					 isAnimation = true;
				 }else{
					 String temp1 = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM7);
                	 GameManager.setPopUp(PopUp.GOOGS, GameStaticImage.farm_item_5, new PopUp(temp1) {
                		 @Override
          	 			public byte onTouch(MotionEvent event) {
          	 				// TODO Auto-generated method stub
          	 				byte temp = super.onTouch(event);
          	 				if(temp == PopUp.onTouch_googsExit){
          	 					module.buyPropWindows(false);
          	 					return -1;
          	 				}
          	 				return temp;
          	 			}
                 	
                	 });
				 }
				 isOne = true;
			 }

			 plant_x = (int)(soil_x +module.skinLayer[id].bitmap.getWidth() - module.icon[4].bitmap.getWidth()/2-30*GameConfig.f_zoomx);
			 plant_y = (int)(soil_y+(module.skinLayer[id].bitmap.getHeight()-module.icon[4].bitmap.getHeight()>>1)-30*GameConfig.f_zoomy);
			 if(!isOne && isProp[1] && ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+module.icon[3].bitmap.getWidth(), plant_y+module.icon[3].bitmap.getHeight() )) {
				 System.out.println("<><> 铲除了"+id);
				 if(FarmData1.getDelete()>=FarmData1.DELETEPROP){
					 String temp1 = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM5);
	            	 GameManager.setPopUp(PopUp.GOOGS_BUTTON, GameStaticImage.farm_item_4, new PopUp(GameStaticImage.shop_item_delete, "*"+FarmData1.DELETEPROP, temp1, null, 0, false) {
	         			@Override
	        			public byte onTouch(MotionEvent event) {
	        				// TODO Auto-generated method stub
	        				byte temp = super.onTouch(event);
	        				if(temp == PopUp.onTouch_googsExit){
	        					 isdelete = true;
	        					 isAnimation = true;
	        					return -1;
	        				}
	        				return temp;
	        			}
	        		});
					 
				 }else{
					 String temp1 = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM7);
                	 GameManager.setPopUp(PopUp.GOOGS, GameStaticImage.farm_item_4, new PopUp(temp1) {
                		 @Override
         	 			public byte onTouch(MotionEvent event) {
         	 				// TODO Auto-generated method stub
         	 				byte temp = super.onTouch(event);
         	 				if(temp == PopUp.onTouch_googsExit){
         	 					module.buyPropWindows(true);
         	 					return -1;
         	 				}
         	 				return temp;
         	 			}
                		 
                	 });
				 }
				 isOne = true;
			 }	
			
			 if(!isOne && isSoil && ExternalMethods.CollisionTest(x, y, soil_x, soil_y, soil_x+module.skinLayer[id].bitmap.getWidth(), soil_y+module.skinLayer[id].bitmap.getHeight() )){
				if(soil_type == FarmData1.notUnlock){ //未解锁
					notUnlock();
				}else if (state == FarmData1.unplanted){ //未种植
					setStste(FarmData1.grow);
					int star = 1;
					int end = 100;
					int a =new Random().nextInt((end-star)+1)+star;
					long jilv[] =  (FarmData1.Random_Plan[id][1]);
					if(a<=jilv[0]){
			    	  a = (int)(FarmData1.Random_Plan[id][0][0]);
					}else if(a <=(jilv[1]+jilv[0]) && a >jilv[0]){
						a = (int)(FarmData1.Random_Plan[id][0][1]);
					}else if(a <=(jilv[2]+jilv[0]+jilv[1]) && a >(jilv[0]+jilv[1])){
						a = (int)(FarmData1.Random_Plan[id][0][2]);
					}else if(a <=(jilv[3]+jilv[0]+jilv[1]+jilv[2]) && a >(jilv[0]+jilv[1]+jilv[2])){
						a = (int)(FarmData1.Random_Plan[id][0][3]);
					}
                    //每块地种出来的植物都相同
			    	TIME =  FarmData1.Random_Plan[id][2][0];
//					int star = 1;
//					int end = 4;
//					int a =new Random().nextInt((end-star)+1)+star;
//					int a = 1;
					setType((byte)a);
					
					
				}else if(state == FarmData1.get){ //收获
					 //获得响应的奖励
					 if(getAward())
						 setStste(FarmData1.unplanted);
				}
				 isOne = true;
			 }else if (state == FarmData1.get && farm_plant!=null && farm_plant[tempi]!=null){
				 plant_x = soil_x+(module.skinLayer[id].bitmap.getWidth() - farm_plant[tempi].bitmap.getWidth()>>1)-(int)(14*GameConfig.f_zoomx);
				 plant_y = soil_y+(module.skinLayer[id].bitmap.getHeight() - (farm_plant[tempi].bitmap.getHeight()*2)>>1)+(int)(22*GameConfig.f_zoomy);
					
				 if(!isOne && ExternalMethods.CollisionTest(x, y, plant_x, plant_y, plant_x+farm_plant[tempi].bitmap.getWidth(), plant_y+farm_plant[tempi].bitmap.getHeight() )){
					 //获得响应的奖励
					 if(getAward())
						 setStste(FarmData1.unplanted);
					 isOne = true;
				 }
			 }
			
			
			 for(int i=0;i<isProp.length;++i){
				 isProp[i] = false;
			 }
			isSoil = false;
		}
	}
	
	/**
	 * 对应的奖励
	 * */
	private boolean getAward(){
		boolean isok = false;
		
		if(soil_type == FarmData1.FARM_PLANTCARD){//卡牌
			
			int star = FarmData1.Random_PU_CardNumber[id][0];
			int end = FarmData1.Random_PU_CardNumber[id][1];
			int card_number = new Random().nextInt((end-star)+1)+star;
			
			Vector<Integer> temp = new Vector<Integer>();
			for(int i=0;i<card_number;++i){
				star = 0;
				end = FarmData1.Random_CardId[id].length-1;
				int a =new Random().nextInt((end-star)+1)+star;
				temp.add(FarmData1.Random_CardId[id][a]);
			}
			int[] temp_ = new int[temp.size()];
			for(int i=0;i<temp_.length;++i){
				temp_[i] = temp.get(i).intValue();
			}
			GameManager.forbidModule(new Gameoverxiangzi(temp_, false));
			isok = true;
		}else if(soil_type == FarmData1.FARM_PLANTGOLD){//金币
				int star = FarmData1.Random_Gold[id][0];
				int end = FarmData1.Random_Gold[id][1];
				int a =new Random().nextInt((end-star)+1)+star;
				infoFlow(10);
				number = a; //获取的数量
				isok = true;
				endl = false;
				prop_y = 0;
				prop_x = 0;
				waitingTime = GameLibrary.getIntRandom(5, 10);
				waitingTime = JumpH.length;
		}else if(soil_type == FarmData1.FARM_PLANTHEART){//爱心
			
				int star = FarmData1.Random_LoveStar[id][0];
				int end = FarmData1.Random_LoveStar[id][1];
				int a =new Random().nextInt((end-star)+1)+star;
				infoFlow(10);
				number = a; //获取的数量
				isok = true;
				endl = false;
				prop_y = 0;
				prop_x = 0;
				waitingTime = GameLibrary.getIntRandom(5, 10);
				waitingTime = JumpH.length;
//			}
		}else if(soil_type == FarmData1.FARM_PLANTBox){//宝箱
			 String name=GameStaticImage.shop_box_01;
			 if(id == 1) 
				 name=GameStaticImage.shop_box_02;
			 if(id == 2) 
				 name=GameStaticImage.shop_box_03;
			 if(id == 3) 
				 name=GameStaticImage.shop_box_04;
			 String temp1 = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM6);
			 GameManager.setPopUp(PopUp.GOOGS_BUTTON, name, new PopUp(GameStaticImage.shop_gem_12, "*"+FarmData1.BOXPROP, temp1, null, 0, false) {
				 @Override
     			public byte onTouch(MotionEvent event) {
     				// TODO Auto-generated method stub
     				byte temp = super.onTouch(event);
     				if(temp == PopUp.onTouch_googsExit){
     					 if(VeggiesData.getGem()>=FarmData1.BOXPROP){
     						 VeggiesData.addGem(-FarmData1.BOXPROP);
     						 //打开宝箱了
     						 setStste(FarmData1.unplanted);
     						 
     						int star = 0;
     						int end = FarmData1.Random_BOX[id][0].length-1;
     						int a =new Random().nextInt((end-star)+1)+star;
     						int cardID = FarmData1.Random_BOX[id][0][a]; //卡id
     						 
     						
     						star = FarmData1.Random_BOX[id][1][0];
     						end = FarmData1.Random_BOX[id][1][1];
     						a =new Random().nextInt((end-star)+1)+star;
     						int gold = a; //金币数量
     						
     						star = FarmData1.Random_BOX[id][2][0];
     						end = FarmData1.Random_BOX[id][2][1];
     						a =new Random().nextInt((end-star)+1)+star;
     						int get = a; //金币数量
     						
     						// /**0 金币数量   1卡牌数量  2卡牌星数 3是卡牌id  4钻石数量*/
     						int temp_[] = {gold, 1, cardID%3==0?3:cardID%3, cardID, get};
     						Gameoverxiangzi.NUMBER = new int[temp_.length];
     						for(int i=0;i<temp_.length;++i){
     							Gameoverxiangzi.NUMBER[i] = temp_[i];
     						}
     						GameManager.forbidModule(new Gameoverxiangzi(null, true));
     					
     					 
     					 }else{
     						 String temp1 = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
	                    	 GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
     					 }
     					return -1;
     				}
     				return temp;
     			}
			 });

		}
		return isok;
	}
	
	/**
	 * 未解锁土地的逻辑处理
	 * */
	private void notUnlock(){
		if(id==3 && FarmData1.isunLOCK)return;
		String temp = "";
		if(id==1){
			temp = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM1);
		}else if(id==2){
			temp = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM2);
		}else if(id==3){
			temp = LangUtil.getLangString(LangDefineClient.UNLOCK_FRAM3);
		}
		GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(temp) {
			@Override
			public byte onTouch(MotionEvent event) {
				// TODO Auto-generated method stub
				byte temp = super.onTouch(event);
				if(temp == PopUp.onTouch_googsExit){
					 if(id == 3){
						 if(VeggiesData.getGem()>=FarmData1.DIALOGBOX){
							 VeggiesData.addGem(-FarmData1.DIALOGBOX);
							 setStste(FarmData1.unplanted);
							 setType((byte)100);
							 FarmData1.isunLOCK = true;
							 new VeggiesData().saveGame();
						 }else{
							 String temp1 = LangUtil.getLangString(LangDefineClient.DIALOGBOX_GEM);
	                    	 GameManager.setPopUp(PopUp.GOOGS, "", new PopUp(temp1) {});
						 }
					 }
					return -1;
				}
				return temp;
			}
		});
	}
	
	
	/**
	 * 获取植物
	 * */
	private Sprite[] plan(byte _type, byte _state){
		if(_state == FarmData1.unplanted) return null;
//		if(farm_plant != null){
//			GameImage.delImageArray(farm_plant);
//		}
//		farm_plant = null;
		
		String fien = null;
		if(_type == FarmData1.FARM_PLANTCARD){//卡牌
			String name[] = {GameStaticImage.farm_farm_card_03,
					 GameStaticImage.farm_farm_card_02,
					 GameStaticImage.farm_farm_card_01};
			fien = name[_state];
		}if(_type == FarmData1.FARM_PLANTGOLD){//金币
			String name[] = {GameStaticImage.farm_farm_gold_03,
					 GameStaticImage.farm_farm_gold_02,
					 GameStaticImage.farm_farm_gold_01};
			fien = name[_state];
		}if(_type == FarmData1.FARM_PLANTHEART){//爱心
			String name[] = {GameStaticImage.farm_farm_heart_03,
					 GameStaticImage.farm_farm_heart_02,
					 GameStaticImage.farm_farm_heart_01};
			fien = name[_state];
		}if(_type == FarmData1.FARM_PLANTBox){//宝箱
			if(id == 0){
				String name[] = {GameStaticImage.farm_box_03,
						 GameStaticImage.farm_box_02,
						 GameStaticImage.farm_box_01};
				fien = name[_state];
			}else if(id == 1){
				String name[] = {GameStaticImage.farm_box_03,
						 GameStaticImage.farm_box_05,
						 GameStaticImage.farm_box_04};
				fien = name[_state];
			}else if(id == 2){
				String name[] = {GameStaticImage.farm_box_03,
						 GameStaticImage.farm_box_08,
						 GameStaticImage.farm_box_07};
				fien = name[_state];
			}else  if(id == 3){

				String name[] = {GameStaticImage.farm_box_03,
						 GameStaticImage.farm_box_11,
						 GameStaticImage.farm_box_10};
				fien = name[_state];
			}
			
			
			
		
		}
		if(fien!=null)
			return	farm_plant = GameImage.getAutoSizecutSprite(fien, 3, 1, GameImage.Sort_line);
		else
			return null;
	}
}//end class
