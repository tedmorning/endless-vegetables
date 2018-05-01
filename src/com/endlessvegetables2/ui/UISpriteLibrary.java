package com.endlessvegetables2.ui;

import com.kokatlaruruxi.wy.ExternalMethods;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;

public class UISpriteLibrary{
	public static final byte isPlayer = 0;
	public static final byte isEnemy = 1;
	public static final byte isObstruct  = 2;
	public static final byte isEffect = 3;

	public static final byte NPC_RAINBOW= 0;//�ʺ��
	public static final byte NPC_TOMATO= 1;//����

	
	public static String imageName[][]={

		{"TCard/ui_enemy_rainbow",},//�ʺ��	
		{"TCard/PC_TomatoG_2","TCard/PC_TomatoG"},//�ƽ���
	};
	
	public static Bitmap image[][] = null;
	
	public static short nDrawPos[][][]=
	{				
		{//�ʺ��
			{0,1,8,167,186},    //����:[0]
			{0,178,-1,95,46},   //����:[1]
			{0,283,158,122,30}, //����:[2]
			{0,178,53,95,46},   //����:[3]
			{0,0,8,168,186},    //����:[4]
			{0,168,111,115,97}, //����:[5]
			{0,178,53,95,45},   //����:[6]
			{0,283,3,121,41},   //����:[7]
			{0,289,48,95,45},   //����:[8]
			{0,283,96,121,43}   //����:[9]
		},
		{//�ƽ���
			{1,131,-1,34,33},   //����:[0]
			{1,-2,182,84,39},   //����:[1]
			{1,-2,221,84,22},   //����:[2]
			{1,0,241,84,15},    //����:[3]
			{1,157,173,37,39},  //����:[4]
			{1,153,99,40,35},   //����:[5]
			{1,98,77,56,41},    //����:[6]
			{1,166,-1,27,31},   //����:[7]
			{1,97,0,35,35},     //����:[8]
			{1,83,134,14,30},   //����:[9]
			{1,165,29,28,34},   //����:[10]
			{1,165,63,26,36},   //����:[11]
			{1,-2,182,84,41},   //����:[12]
			{1,132,35,32,32},   //����:[13]
			{1,0,182,83,73},    //����:[14]
			{1,98,119,54,46},   //����:[15]
			{1,85,165,71,48},   //����:[16]
			{1,96,35,35,32},    //����:[17]
			{1,84,211,71,48},   //����:[18]
			{1,96,35,35,33},    //����:[19]
			{1,131,36,32,30},   //����:[20]
			{1,-1,110,84,72},   //����:[21]
			{1,98,76,54,42},    //����:[22]
			{1,165,-1,29,31},   //����:[23]
			{1,97,35,34,32},    //����:[24]
			{0,0,0,124,100},    //����:[25]
			{0,0,109,122,100},  //����:[26]
			{0,1,213,117,108},  //����:[27]
			{0,-1,322,125,108}  //����:[28]
		},
	};
	
	public static short npcItem0[][][][]={//����Ԫ��:{״̬��->֡��->��ͼ��}				
		UISpriteLibraryNpcItemArray.npcItem0,
		UISpriteLibraryNpcItemArray.npcItem1,
	};
	
	public static void DelSpriteImage(int kind) {
		if(image==null||image[kind]==null)
			return;
		for(int i=0;i<image[kind].length;i++){
			GameImage.delImage(image[kind][i]);
			image[kind][i]=null;
		}
		image[kind]=null;
	}
	
	public static void DelAllSpriteImage() {
		if(image==null)
			return;
		
		for(int i=0;i<image.length;i++){
			if(image[i]!=null){
				for(int j=0;j<image[i].length;j++){
					GameImage.delImage(image[i][j]);
					image[i][j]=null;
				}
			}
		}
	}
	
	public static void loadSpriteImage(int kind) {
		if(image==null){
			image=new Bitmap[imageName.length][];
		}
		if(image[kind]==null){
			image[kind]=new Bitmap[imageName[kind].length];
		}
		for(int i=0;i<imageName[kind].length;i++){
			if(image[kind][i]==null){
				image[kind][i] = GameImage.getOrgImage(imageName[kind][i]);
			}
		}
	}
	
	public static void paintSprite(Canvas canvas,int kind,float x,float y,int actionIndex,int currentFrame,float size,int Alpha,float jiaodu,int r,int g,int b,Rect rect){
		try {
			if(size!=1&&rect!=null){
				rect.left/=size;
				rect.right/=size;
				rect.top/=size;
				rect.bottom/=size;
			}
			int mun=npcItem0[kind][actionIndex][currentFrame].length/3;
			for(int i=0;i<mun;i++){
				
//				int tempW=image[kind][npcItem0[kind][actionIndex][currentFrame][i*3]].getWidth();
//				int tempH=image[kind][npcItem0[kind][actionIndex][currentFrame][i*3]].getHeight();
				int tempX=npcItem0[kind][actionIndex][currentFrame][i*3+1];
				int tempY=npcItem0[kind][actionIndex][currentFrame][i*3+2];
				int tempW=nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][3];
				int tempH=nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][4];
				
//				Library2.drawImage(canvas, image[kind][npcItem0[kind][actionIndex][currentFrame][i*3]], x, y, sizeW, sizeH, Alpha, Rotate, RotateX, RotateY);
				float tempxxx=x;
				float tempyyy=y;
				canvas.save();
				if(size!=1){
					canvas.scale(size, size);
					x/=size;
					y/=size;
				}
				
				if(jiaodu!=0){
					canvas.rotate(-jiaodu, x, y);
				}
				
//				if(nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]].length>=6&&(nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][5]==1||nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][5]==3)){
//					canvas.clipRect(x+tempX-tempW/2,y+tempY-tempH/2,x+tempX+tempW/2,y+tempY+tempH/2);
//					Library2.drawImage(canvas,image[kind][nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][0]], x+tempX-tempH/2-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][2], y+tempY -tempW/2-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][1], 1f, 1f, Alpha, nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][5]==1?270:90, tempW/2, tempH/2,r,g,b);
//				}
//				else 
//				{
					if(rect!=null){
						ExternalMethods.setClip(canvas, rect.left, rect.top, rect.right-rect.left, rect.bottom-rect.top, (int)(x+tempX-tempW/2),(int)(y+tempY-tempH/2),tempW+(tempW%2==1?1:0),tempH+(tempH%2==1?1:0));
					}else{
						canvas.clipRect(x+tempX-tempW/2,y+tempY-tempH/2,x+tempX+tempW/2+(tempW%2==1?1:0),y+tempY+tempH/2+(tempH%2==1?1:0));						
					}
					if(nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]].length>=6&&nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][5]==4){
						//ˮƽ����
						int temp=image[kind][nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][0]].getWidth() - nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][1]-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][3];
						ExternalMethods.drawImage(canvas, image[kind][nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][0]], x+tempX-tempW/2-temp, y+tempY-tempH/2-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][2], -1f, 1f, Alpha, 0, 0, 0,r,g,b);
					}else if(nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]].length>=6&&nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][5]==5){
						//��ֱ����
						int temp=image[kind][nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][0]].getHeight() - nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][2]-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][4];
						ExternalMethods.drawImage(canvas, image[kind][nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][0]], x+tempX-tempW/2-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][1], y+tempY-tempH/2-temp, 1f, -1f, Alpha, 0, 0, 0,r,g,b);
					}else{
						ExternalMethods.drawImage(canvas,image[kind][nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][0]], x+tempX-tempW/2-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][1], y+tempY-tempH/2-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][2] , 1f, 1f, Alpha, 0, 0, 0,r,g,b);
//						canvas.drawBitmap(image[kind][nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][0]], x+tempX-tempW/2-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][1], y+tempY-tempH/2-nDrawPos[kind][npcItem0[kind][actionIndex][currentFrame][i*3]][2], null);
					}
//				}
				
				
				canvas.restore();
				x=tempxxx;
				y=tempyyy;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static int isEnemy(int kind) {
		switch (kind) {
		default:			
			return -1;
		}
	}
	
	public static int GetW(int kind) {
		switch (kind) {
		}
		return 0;
	}
	
	public static int GetH(int kind) {
		switch (kind) {
		
		}
		return 0;
	}
	
	public static int GetHP(int kind) {			
		switch (kind) {
		
		}
		
		return 1;
	}
	
	public static int GetAttack(int kind) {			
		switch (kind) {
		
		}
		
		return 0;
	}
	
	public static int GetAttackTime(int kind, int level) {				
		switch (kind) {
		
		}
		
		return 0;
	}
	
	public static int GetNumber(int kind) {
		switch (kind) {
		
		}
		
		return 0;
	}
	
	public static int GetSpeed(int kind, int level) {			
		switch (kind) {
		
		}
		
		return 0;
	}
	
	public static int GetGoldenNumber(int kind) {
		switch (kind) {
		
		}
		
		return 0;
	}
	
	public static int GetGoldenPercent(int kind) {
		switch (kind) {
		
		}
		
		return 0;
	}	
}
