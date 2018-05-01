package com.kokatlaruruxi.wy;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.turngame.TurnGamePowerCard;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditData;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;

public class SpriteLibrary{
	public static final byte isPlayer = 0;
	public static final byte isEnemy = 1;
	public static final byte isObstruct  = 2;
	public static final byte isEffect = 3;

	public static Bitmap image[][] = null;
	
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
			image=new Bitmap[CoolEditData.imageName.length][];
		}
		
		if(image[kind]==null){			
			image[kind]=new Bitmap[CoolEditData.imageName[kind].length];
		}
		
		for(int i=0;i<CoolEditData.imageName[kind].length;i++){
			if(image[kind][i]==null){		
				
				image[kind][i] = GameImage.getOrgImage(CoolEditData.imageName[kind][i]);				
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
			int mun=CoolEditData.npcItem0[kind][actionIndex][currentFrame].length/3;
			for(int i=0;i<mun;i++){
				
//				int tempW=image[kind][npcItem0[kind][actionIndex][currentFrame][i*3]].getWidth();
//				int tempH=image[kind][npcItem0[kind][actionIndex][currentFrame][i*3]].getHeight();
				int tempX=CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3+1];
				int tempY=CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3+2];
				int tempW=CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][3];
				int tempH=CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][4];
				
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
					
//					try{
//						if(image[kind][CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][0]]==null)
//						{}
//					} catch (Exception e){
//						System.out.println("======================>>>"+kind+"==================>>>"+CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][0]);
//					}
					
					if(CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]].length>=6&&CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][5]==4){
						//水平镜象
						int temp=image[kind][CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][0]].getWidth() - CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][1]-CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][3];
						ExternalMethods.drawImage(canvas, image[kind][CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][0]], x+tempX-tempW/2-temp, y+tempY-tempH/2-CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][2], -1f, 1f, Alpha, 0, 0, 0,r,g,b);
					}else if(CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]].length>=6&&CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][5]==5){
						//垂直镜象
						int temp=image[kind][CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][0]].getHeight() - CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][2]-CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][4];
						ExternalMethods.drawImage(canvas, image[kind][CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][0]], x+tempX-tempW/2-CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][1], y+tempY-tempH/2-temp, 1f, -1f, Alpha, 0, 0, 0,r,g,b);
					}else{						
						ExternalMethods.drawImage(canvas, image[kind][CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][0]], x+tempX-tempW/2-CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][1], y+tempY-tempH/2-CoolEditData.nDrawPos[kind][CoolEditData.npcItem0[kind][actionIndex][currentFrame][i*3]][2] , 1f, 1f, Alpha, 0, 0, 0,r,g,b);
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
			
			System.out.println("draw errer sprite kind: "+kind);
		}
	}
	
	public static int isEnemy(int kind) {
		switch (kind) {
		case CoolEditDefine.Enemy_MIMI:
		case CoolEditDefine.Enemy_DS:
		case CoolEditDefine.Enemy_X:
		case CoolEditDefine.Enemy_Z:
		case CoolEditDefine.Enemy_YGHZ:
		case CoolEditDefine.Enemy_HZ:	
		case CoolEditDefine.Enemy_HZXJ:
		case CoolEditDefine.Enemy_MM:
		case CoolEditDefine.Enemy_MMJS:
		case CoolEditDefine.Enemy_MMB1:
		case CoolEditDefine.Enemy_MMB2:
		case CoolEditDefine.Enemy_MMB3:
		case CoolEditDefine.Enemy_MMB4:
		case CoolEditDefine.Enemy_CHG:
		case CoolEditDefine.Enemy_SHMM:
		case CoolEditDefine.Enemy_SHZ:
		case CoolEditDefine.Enemy_SHX:
		case CoolEditDefine.Enemy_SHHT:
		case CoolEditDefine.Enemy_SHHM:
		case CoolEditDefine.Enemy_YGMM:
		case CoolEditDefine.Enemy_YGDS:
		case CoolEditDefine.Enemy_YGZ:
		case CoolEditDefine.Enemy_YGX:
		case CoolEditDefine.Enemy_YGXTM:
		case CoolEditDefine.Enemy_SHZY:
		case CoolEditDefine.Enemy_SHZYXZ:
		case CoolEditDefine.Enemy_SHZYCS:
		case CoolEditDefine.Enemy_YGY:
		case CoolEditDefine.Enemy_YGYYM:
		case CoolEditDefine.Enemy_YGYKS:
		case CoolEditDefine.Enemy_YGYJF:
		case CoolEditDefine.Enemy_SIREN:
		case CoolEditDefine.Enemy_MEGICWATER:
		case CoolEditDefine.Enemy_PANGXIE1:
		case CoolEditDefine.Enemy_PANGXIE2:
		case CoolEditDefine.Enemy_PANGXIEZIDAN:
			return isEnemy;
			
		case CoolEditDefine.Player_FQ:
		case CoolEditDefine.Player_FQ_2:
		case CoolEditDefine.Player_FQ_3:
		case CoolEditDefine.Player_WD:
		case CoolEditDefine.Player_WD_2:
		case CoolEditDefine.Player_WD_3:
		case CoolEditDefine.Player_LJ:
		case CoolEditDefine.Player_LJ_2:
		case CoolEditDefine.Player_LJ_3:
		case CoolEditDefine.Player_YC:
		case CoolEditDefine.Player_YC_2:
		case CoolEditDefine.Player_YC_3:
		case CoolEditDefine.Player_LB:
		case CoolEditDefine.Player_LB_2:
		case CoolEditDefine.Player_LB_3:
		case CoolEditDefine.Player_TD:
		case CoolEditDefine.Player_TD_2:
		case CoolEditDefine.Player_TD_3:
		case CoolEditDefine.Player_MG:
		case CoolEditDefine.Player_MG_2:
		case CoolEditDefine.Player_MG_3:
		case CoolEditDefine.Player_HC:
		case CoolEditDefine.Player_HC_2:
		case CoolEditDefine.Player_HC_3:
		case CoolEditDefine.Player_ZS:
		case CoolEditDefine.Player_ZS_2:
		case CoolEditDefine.Player_ZS_3:
		case CoolEditDefine.Player_NG:
		case CoolEditDefine.Player_NG_2:
		case CoolEditDefine.Player_NG_3:
			return isPlayer;
			
		case CoolEditDefine.Lattice:
		case CoolEditDefine.Lattice_2:
		case CoolEditDefine.Lattice_3:
		case CoolEditDefine.Lattice_4:
		case CoolEditDefine.Effect_BOMB:
		case CoolEditDefine.Effect_COLD:
		case CoolEditDefine.Effect_TELEPORT:
		case CoolEditDefine.Effect_SPRINGBOARD:
		case CoolEditDefine.Effect_BOMBLV1:			
		case CoolEditDefine.Effect_BOMBLV2:			
		case CoolEditDefine.Effect_BOMBLV3:
		case CoolEditDefine.Effect_BANANA:
		case CoolEditDefine.Effect_COBWEB:
		case CoolEditDefine.Effect_ROSE:		
		case CoolEditDefine.Effect_ROSESECOND:
		case CoolEditDefine.Effect_ROSETHIRD:
		case CoolEditDefine.Effect_ROSETHIRDATTACK:
		case CoolEditDefine.Effect_MAGICDOOR:
			return isObstruct;	
			
		case CoolEditDefine.Effect_ICESTATELV1:
		case CoolEditDefine.Effect_ICESTATELV2:
		case CoolEditDefine.Effect_ICESTATELV3:
		case CoolEditDefine.Effect_SHIELD:
		case CoolEditDefine.Effect_SLOWDOWN:
		case CoolEditDefine.Effect_RESTORE:
		case CoolEditDefine.Effect_EMPTYBOX:		
		case CoolEditDefine.Effect_ATTACK2:
		case CoolEditDefine.Effect_DIZZINESS:
		case CoolEditDefine.Effect_HEADFIRE:
		case CoolEditDefine.Effect_HAMMER:
		case CoolEditDefine.SMALL_CARD_BOX:
		case CoolEditDefine.SMALL_CARD:
		case CoolEditDefine.Effect_ROSEFIRST:
		case CoolEditDefine.Effect_MAGICHALO:
		case CoolEditDefine.SLINGSHOT1:
		case CoolEditDefine.SLINGSHOT2:
		case CoolEditDefine.SLINGSHOT3:
		case CoolEditDefine.SLINGSHOT4:
			return isEffect;
			
		default:			
			return -1;
		}
	}
	
	public static int GetW(int kind) {
		switch (kind) {
		
		case CoolEditDefine.Player_FQ:
		case CoolEditDefine.Player_FQ_2:
		case CoolEditDefine.Player_FQ_3:
			return (int)(60*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Player_WD:
		case CoolEditDefine.Player_WD_2:
		case CoolEditDefine.Player_WD_3:
			return (int)(45*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Player_LJ:
		case CoolEditDefine.Player_LJ_2:
			return (int)(90*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Player_LJ_3:
			return (int)(90*GameConfig.f_zoomx);
			
		case CoolEditDefine.Player_YC:
		case CoolEditDefine.Player_YC_2:
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Player_YC_3:
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Player_LB:
		case CoolEditDefine.Player_LB_2:
			return (int)(55*GameConfig.f_zoomx);
			
		case CoolEditDefine.Player_LB_3:
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Player_TD:
		case CoolEditDefine.Player_TD_2:
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Player_TD_3:
			return (int)(72*GameConfig.f_zoomx);
			
		case CoolEditDefine.Player_MG:
		case CoolEditDefine.Player_MG_2:
			return (int)(71*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Player_MG_3:
			return (int)(71*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Player_HC:
		case CoolEditDefine.Player_HC_2:
			return (int)(72*GameConfig.f_zoomx);	
					
		case CoolEditDefine.Player_HC_3:
			return (int)(72*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Player_ZS:
		case CoolEditDefine.Player_ZS_2:
			return (int)(56*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Player_ZS_3:
			return (int)(60*GameConfig.f_zoomx);					
			
		case CoolEditDefine.Player_NG:
		case CoolEditDefine.Player_NG_2:
			return (int)(80*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Player_NG_3:	
			return (int)(84*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Lattice:
		case CoolEditDefine.Lattice_2:
		case CoolEditDefine.Lattice_3:
		case CoolEditDefine.Lattice_4:	
			return (int)(590*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Enemy_MIMI:
		case CoolEditDefine.Enemy_SHMM:
		case CoolEditDefine.Enemy_YGMM:
			return (int)(60*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Enemy_DS:
		case CoolEditDefine.Enemy_YGDS:
			return (int)(70*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Enemy_X:	
			return (int)(80*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_SHX:
			return (int)(80*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_YGX:
			return (int)(80*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Enemy_Z:
		case CoolEditDefine.Enemy_SHZ:
		case CoolEditDefine.Enemy_YGZ:
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_HZ:
		case CoolEditDefine.Enemy_YGHZ:
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_HZXJ:	
			return (int)(20*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_MM:
			return (int)(160*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_MMJS:
			return (int)(139*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_MMB1:
		case CoolEditDefine.Enemy_MMB2:
		case CoolEditDefine.Enemy_MMB3:
		case CoolEditDefine.Enemy_MMB4:
			return (int)(70*GameConfig.f_zoomx);
					
		case CoolEditDefine.Effect_BOMB:
			return (int)(150*GameConfig.f_zoomx);		
			
		case CoolEditDefine.Effect_COLD:
			return (int)(150*GameConfig.f_zoomx);
					
		case CoolEditDefine.Enemy_CHG:
			return (int)(50*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_SHHT:	
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_SHHM:	
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_YGXTM:	
			return (int)(100*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_SHZY:
			return (int)(200*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_SHZYXZ:	
			return (int)(60*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_SHZYCS:
			return (int)(50*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_YGY:	
			return (int)(120*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_YGYYM:
			return (int)(20*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_YGYKS:
			return (int)(20*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_YGYJF:	
			return (int)(120*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_SIREN:	
			return (int)(120*GameConfig.f_zoomx);
			
		case CoolEditDefine.Effect_TELEPORT:	
			return (int)(30*GameConfig.f_zoomx);
			
		case CoolEditDefine.Effect_SPRINGBOARD:	
			return (int)(48*GameConfig.f_zoomx);
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return (int)(40*GameConfig.f_zoomx);
			
		case CoolEditDefine.Effect_BOMBLV1:	
			return (int)(100*GameConfig.f_zoomx);
			
		case CoolEditDefine.Effect_BOMBLV2:
			return (int)(125*GameConfig.f_zoomx);
			
		case CoolEditDefine.Effect_BOMBLV3:	
			return (int)(150*GameConfig.f_zoomx);
			
		case CoolEditDefine.SMALL_CARD_BOX:
			return (int)(82*GameConfig.f_zoomx);
			
		case CoolEditDefine.SMALL_CARD:
			return (int)(36*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Effect_BANANA:
			return (int)(52*GameConfig.f_zoomx);
			
		case CoolEditDefine.Effect_COBWEB:
			return (int)(48*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Effect_ROSE:
		case CoolEditDefine.Effect_ROSEFIRST:	
		case CoolEditDefine.Effect_ROSETHIRDATTACK:		
			return (int)(41*GameConfig.f_zoomx);
			
		case CoolEditDefine.Effect_ROSESECOND:
			return (int)(140*GameConfig.f_zoomx);
			
		case CoolEditDefine.Effect_ROSETHIRD:
			return (int)(140*GameConfig.f_zoomx);			

		case CoolEditDefine.Effect_MAGICDOOR:
			return (int)(100*GameConfig.f_zoomx);	
			
		case CoolEditDefine.Enemy_PANGXIE1:
			return (int)(140*GameConfig.f_zoomx);	
		case CoolEditDefine.Enemy_PANGXIE2:
			return (int)(140*GameConfig.f_zoomx);	
		case CoolEditDefine.Enemy_PANGXIEZIDAN:
			return (int)(30*GameConfig.f_zoomx);	
		}
		
		return 0;
	}
	
	public static int GetH(int kind) {
		switch (kind) {
		
		case CoolEditDefine.Player_FQ:
		case CoolEditDefine.Player_FQ_2:
			return (int)(55*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Player_FQ_3:
			return (int)(68*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Player_WD:
		case CoolEditDefine.Player_WD_2:
		case CoolEditDefine.Player_WD_3:	
			return (int)(48*GameConfig.f_zoomy);				
			
		case CoolEditDefine.Player_LJ:
		case CoolEditDefine.Player_LJ_2:
			return (int)(90*GameConfig.f_zoomy);
			
		case CoolEditDefine.Player_LJ_3:
			return (int)(90*GameConfig.f_zoomy);
			
		case CoolEditDefine.Player_YC:
		case CoolEditDefine.Player_YC_2:
			return (int)(62*GameConfig.f_zoomy);
			
		case CoolEditDefine.Player_YC_3:
			return (int)(64*GameConfig.f_zoomy);
			
		case CoolEditDefine.Player_LB:
		case CoolEditDefine.Player_LB_2:
			return (int)(65*GameConfig.f_zoomy);
			
		case CoolEditDefine.Player_LB_3:
			return (int)(98*GameConfig.f_zoomy);
			
		case CoolEditDefine.Player_TD:
		case CoolEditDefine.Player_TD_2:
			return (int)(67*GameConfig.f_zoomy);
			
		case CoolEditDefine.Player_TD_3:
			return (int)(77*GameConfig.f_zoomy);
			
		case CoolEditDefine.Player_MG:
		case CoolEditDefine.Player_MG_2:
			return (int)(52*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Player_MG_3:
			return (int)(71*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Player_HC:
		case CoolEditDefine.Player_HC_2:
			return (int)(60*GameConfig.f_zoomy);				
			
		case CoolEditDefine.Player_HC_3:
			return (int)(73*GameConfig.f_zoomy);		
			
		case CoolEditDefine.Player_ZS:
		case CoolEditDefine.Player_ZS_2:
			return (int)(86*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Player_ZS_3:
			return (int)(86*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Player_NG:
		case CoolEditDefine.Player_NG_2:
			return (int)(70*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Player_NG_3:	
			return (int)(86*GameConfig.f_zoomy);		
			
		case CoolEditDefine.Lattice:		
		case CoolEditDefine.Lattice_2:
		case CoolEditDefine.Lattice_3:
		case CoolEditDefine.Lattice_4:
			return (int)(62*GameConfig.f_zoomy);		
		
		case CoolEditDefine.Enemy_MIMI:
		case CoolEditDefine.Enemy_SHMM:
		case CoolEditDefine.Enemy_YGMM:
			return (int)(60*GameConfig.f_zoomy);		
			
		case CoolEditDefine.Enemy_DS:
		case CoolEditDefine.Enemy_YGDS:
			return (int)(70*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_X:
			return (int)(80*GameConfig.f_zoomy);
			
		case CoolEditDefine.Enemy_SHX:
			return (int)(80*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_YGX:
			return (int)(80*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_Z:
		case CoolEditDefine.Enemy_SHZ:
		case CoolEditDefine.Enemy_YGZ:
			return (int)(60*GameConfig.f_zoomy);
			
		case CoolEditDefine.Enemy_HZ:
		case CoolEditDefine.Enemy_YGHZ:
			return (int)(60*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_HZXJ:	
			return (int)(20*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_MM:
			return (int)(200*GameConfig.f_zoomy);	
		
		case CoolEditDefine.Enemy_MMJS:
			return (int)(139*GameConfig.f_zoomy);
			
		case CoolEditDefine.Enemy_MMB1:
		case CoolEditDefine.Enemy_MMB2:
		case CoolEditDefine.Enemy_MMB3:
		case CoolEditDefine.Enemy_MMB4:
			return (int)(70*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Effect_BOMB:
			return (int)(150*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_COLD:
			return (int)(150*GameConfig.f_zoomy);
			
		case CoolEditDefine.Enemy_CHG:
			return (int)(50*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_SHHT:	
			return (int)(50*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_SHHM:	
			return (int)(60*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_YGXTM:	
			return (int)(50*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_SHZY:
			return (int)(170*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_SHZYXZ:	
			return (int)(60*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_SHZYCS:
			return (int)(60*GameConfig.f_zoomy);
			
		case CoolEditDefine.Enemy_YGY:	
			return (int)(120*GameConfig.f_zoomy);
			
		case CoolEditDefine.Enemy_YGYYM:
			return (int)(40*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_YGYKS:
			return (int)(30*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_YGYJF:	
			return (int)(90*GameConfig.f_zoomy);
			
		case CoolEditDefine.Enemy_SIREN:	
			return (int)(120*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_TELEPORT:	
			return (int)(80*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Effect_SPRINGBOARD:	
			return (int)(27*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return (int)(30*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_BOMBLV1:	
			return (int)(100*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_BOMBLV2:
			return (int)(125*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_BOMBLV3:	
			return (int)(150*GameConfig.f_zoomy);
			
		case CoolEditDefine.SMALL_CARD_BOX:
			return (int)(82*GameConfig.f_zoomy);
			
		case CoolEditDefine.SMALL_CARD:
			return (int)(50*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Effect_BANANA:
			return (int)(30*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Effect_COBWEB:
			return (int)(48*GameConfig.f_zoomy);	
			
		case CoolEditDefine.Effect_ROSE:	
		case CoolEditDefine.Effect_ROSETHIRDATTACK:	
			return (int)(41*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_ROSEFIRST:	
			return (int)(132*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_ROSESECOND:
			return (int)(140*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_ROSETHIRD:
			return (int)(140*GameConfig.f_zoomy);
			
		case CoolEditDefine.Enemy_PANGXIE1:
			return (int)(100*GameConfig.f_zoomy);	
		case CoolEditDefine.Enemy_PANGXIE2:
			return (int)(100*GameConfig.f_zoomy);	
		case CoolEditDefine.Enemy_PANGXIEZIDAN:
			return (int)(30*GameConfig.f_zoomy);
			
		case CoolEditDefine.Effect_MAGICDOOR:
			return (int)(30*GameConfig.f_zoomy);	
		}
		return 0;
	}
	
	public static int GetHP(int kind) {
		float tempBloodLower=0;
		tempBloodLower=GamePowerCard.monsterBloodLower;
		
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGYJF:	
			return 999999;	
		
		case CoolEditDefine.Enemy_YGYKS:
			return 10;
			
		case CoolEditDefine.Enemy_YGYYM:
			return 1;
		
		case CoolEditDefine.Enemy_YGY:	
			
			return (int)(5000*tempBloodLower);
		
		case CoolEditDefine.Enemy_SHZYCS:
			return (int)(1*tempBloodLower);	
		
		case CoolEditDefine.Enemy_SHZY:
			return (int)(800*tempBloodLower);	
		
		case CoolEditDefine.Enemy_YGXTM:	
			return (int)(10*tempBloodLower);	
		
		case CoolEditDefine.Enemy_YGX:
			return (int)(250*tempBloodLower);
		
		case CoolEditDefine.Enemy_YGZ:
			return (int)(80*tempBloodLower);
			
		case CoolEditDefine.Enemy_YGHZ:
			return (int)(60*tempBloodLower);
			
		case CoolEditDefine.Enemy_YGDS:
			return (int)(70*tempBloodLower);
			
		case CoolEditDefine.Enemy_YGMM:
			return (int)(60*tempBloodLower);
		
		case CoolEditDefine.Enemy_SHHM:				
			return (int)(24*tempBloodLower);
		
		case CoolEditDefine.Enemy_SHHT:	
			return (int)(35*tempBloodLower);	
		
		case CoolEditDefine.Enemy_SHX:
			return (int)(48*tempBloodLower);
			
		case CoolEditDefine.Enemy_SHZ:
			return (int)(12*tempBloodLower);	
			
		case CoolEditDefine.Enemy_SHMM:	
			return (int)(6*tempBloodLower);	
		
		case CoolEditDefine.Enemy_MIMI:		
			return (int)(18*tempBloodLower);	
			
		case CoolEditDefine.Enemy_DS:
			return (int)(25*tempBloodLower);	
			
		case CoolEditDefine.Enemy_X:	
			return (int)(84*tempBloodLower);
			
		case CoolEditDefine.Enemy_Z:	
			return (int)(40*tempBloodLower);	
			
		case CoolEditDefine.Enemy_HZ:	
			return (int)(20*tempBloodLower);	
			
		case CoolEditDefine.Enemy_HZXJ:	
			return 1;	
			
		case CoolEditDefine.Enemy_MM:
			return (int)(1200*tempBloodLower);
			
		case CoolEditDefine.Enemy_MMJS:
			return 1;	
			
		case CoolEditDefine.Enemy_MMB1:
		case CoolEditDefine.Enemy_MMB2:
		case CoolEditDefine.Enemy_MMB3:
		case CoolEditDefine.Enemy_MMB4:
			return 1;	
			
		case CoolEditDefine.Enemy_CHG:
			return 1;
			
		case CoolEditDefine.Enemy_SHZYXZ:	
			return 1;	
			
		case CoolEditDefine.Enemy_SIREN:	
			return (int)(100*tempBloodLower);
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return (int)(60*tempBloodLower);	
		}
		
		return 1;
	}
	
	public static int GetAttack(int kind) {		
			
		byte rate = 1;
				
		if(ExternalMethods.throwDice(0, 99)<(GamePowerCard.critRate+VeggiesData.getSlingshot_crit()))
			rate = 2;		
		
		switch (kind) {
		
		case CoolEditDefine.Player_FQ:
                        return 6*rate;
		case CoolEditDefine.Player_FQ_2:
                        return 12*rate;
		case CoolEditDefine.Player_FQ_3:
                        return 24*rate;
		case CoolEditDefine.Player_WD:
                        return 6*rate;
		case CoolEditDefine.Player_WD_2:
                        return 10*rate;
		case CoolEditDefine.Player_WD_3:
                        return 14*rate;
		case CoolEditDefine.Player_LJ:
                        return 12*rate;
		case CoolEditDefine.Player_LJ_2:
                        return 24*rate;
		case CoolEditDefine.Player_LJ_3:
                        return 36*rate;
		case CoolEditDefine.Player_YC:
                        return 0;
		case CoolEditDefine.Player_YC_2:
                        return 0;
		case CoolEditDefine.Player_YC_3:
                        return 0;
		case CoolEditDefine.Player_LB:
                        return 7*rate;
		case CoolEditDefine.Player_LB_2:
                        return 11*rate;
		case CoolEditDefine.Player_LB_3:
                        return 15*rate;
		case CoolEditDefine.Player_TD:
                        return 11*rate;
		case CoolEditDefine.Player_TD_2:
                        return 19*rate;
		case CoolEditDefine.Player_TD_3:
                        return 26*rate;
		case CoolEditDefine.Player_MG:
                        return 14*rate;
		case CoolEditDefine.Player_MG_2:
                        return 22*rate;
		case CoolEditDefine.Player_MG_3:
                        return 30*rate;
		case CoolEditDefine.Player_HC:
                        return 18*rate;
		case CoolEditDefine.Player_HC_2:
                        return 33*rate;
		case CoolEditDefine.Player_HC_3:
                        return 51*rate;
		case CoolEditDefine.Player_ZS:
                        return 20*rate;
		case CoolEditDefine.Player_ZS_2:
                        return 40*rate;
		case CoolEditDefine.Player_ZS_3:
                         return 60*rate;
//			if(ExternalMethods.throwDice(0, 99)<GamePowerCard.critRate)
				//rate = 2;						
			//return ExternalMethods.throwDice(10, 30)*rate;	
			
		case CoolEditDefine.Player_NG:
		case CoolEditDefine.Player_NG_2:
		case CoolEditDefine.Player_NG_3:					
			return ExternalMethods.throwDice(250, 300)*rate;
			
		case CoolEditDefine.Enemy_YGYKS:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 15;	
			
		case CoolEditDefine.Enemy_YGYYM:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 20;	
			
		case CoolEditDefine.Enemy_YGY:	
			return 15;	
			
		case CoolEditDefine.Enemy_SHZYCS:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 2;	
			
		case CoolEditDefine.Enemy_SHZYXZ:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 5;			
			
		case CoolEditDefine.Enemy_YGX:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 80;	
			
		case CoolEditDefine.Enemy_YGZ:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 45;	
			
		case CoolEditDefine.Enemy_YGHZ:
			return 30;	
			
		case CoolEditDefine.Enemy_YGDS:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 25;	
			
		case CoolEditDefine.Enemy_YGMM:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 20;	
			
		case CoolEditDefine.Enemy_SHHM:	
			return 0;	
			
		case CoolEditDefine.Enemy_SHHT:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 10;		
			
		case CoolEditDefine.Enemy_SHX:	
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 8;
			
		case CoolEditDefine.Enemy_SHZ:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 2;		
			
		case CoolEditDefine.Enemy_SHMM:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 2;						
			
		case CoolEditDefine.Enemy_MIMI:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 4;	
			
		case CoolEditDefine.Enemy_DS:		
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 5;	
			
		case CoolEditDefine.Enemy_X:	
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 12;
			
		case CoolEditDefine.Enemy_Z:	
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 4;	
			
		case CoolEditDefine.Enemy_HZ:	
			return 3;	
			
		case CoolEditDefine.Enemy_MMJS:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 10;	
			
		case CoolEditDefine.Enemy_HZXJ:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 3;	
			
		case CoolEditDefine.Effect_ROSEFIRST:	
			return 10*rate;	
			
		case CoolEditDefine.Effect_ROSESECOND:	
			return ExternalMethods.throwDice(2, 5)*rate;		
		}
		
		return 0;
	}
	
	public static int GetAttackTime(int kind, int level) {				
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGY:	
			return 75;
		
		case CoolEditDefine.Enemy_SHZY:
			return 50;	
			
		case CoolEditDefine.Enemy_YGX:
			return 75;	
		
		case CoolEditDefine.Enemy_YGZ:
			return 75;
		
		case CoolEditDefine.Enemy_YGHZ:
			return 125;
		
		case CoolEditDefine.Enemy_YGDS:
			return 75;
		
		case CoolEditDefine.Enemy_YGMM:
			return 75;
		
		case CoolEditDefine.Enemy_SHHM:	
			return 75;
		
		case CoolEditDefine.Enemy_SHHT:	
			return 75;		
		
		case CoolEditDefine.Enemy_SHX:	
			return 75;
		
		case CoolEditDefine.Enemy_SHZ:
			return 75;
		
		case CoolEditDefine.Enemy_SHMM:
			return 50;	
		
		case CoolEditDefine.Enemy_MIMI:
			return 50;	
			
		case CoolEditDefine.Enemy_DS:
			return 50;	
			
		case CoolEditDefine.Enemy_X:	
			return 75;	
			
		case CoolEditDefine.Enemy_Z:	
			return 75;	
			
		case CoolEditDefine.Enemy_HZ:	
			return 50;	
			
		case CoolEditDefine.Enemy_MM:
			return 50;	
			
		case CoolEditDefine.Enemy_SIREN:	
			return 150;	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return 50;		
		}
		
		return 0;
	}
	
	public static int GetNumber(int kind,boolean isturn) {
		float tempaddGameNumber;
		tempaddGameNumber=GamePowerCard.addGameNumber;
		
		switch (kind) {
		case CoolEditDefine.Enemy_YGY:	
			return (int)(ExternalMethods.throwDice(150, 250)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_SHZYCS:
			return (int)(ExternalMethods.throwDice(30, 60)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_SHZY:
			return (int)(ExternalMethods.throwDice(150, 250)*tempaddGameNumber);
			
		case CoolEditDefine.Enemy_YGXTM:	
			return (int)(ExternalMethods.throwDice(30, 60)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_YGX:
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_YGZ:
			return (int)(ExternalMethods.throwDice(25, 40)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_YGHZ:
			return (int)(ExternalMethods.throwDice(30, 60)*tempaddGameNumber);	
		
		case CoolEditDefine.Enemy_YGDS:
			return (int)(ExternalMethods.throwDice(30, 50)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_YGMM:
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_SHHM:	
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);	
		
		case CoolEditDefine.Enemy_SHHT:	
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);	
		
		case CoolEditDefine.Enemy_SHMM:
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);	
		
		case CoolEditDefine.Enemy_SHZ:
			return (int)(ExternalMethods.throwDice(25, 40)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_SHX:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);
			
		case CoolEditDefine.Enemy_MIMI:
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_DS:
			return (int)(ExternalMethods.throwDice(30, 50)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_X:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_Z:	
			return (int)(ExternalMethods.throwDice(25, 40)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_HZ:	
			return (int)(ExternalMethods.throwDice(30, 60)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_MM:
			return (int)(ExternalMethods.throwDice(150, 250)*tempaddGameNumber);		
			
		case CoolEditDefine.Enemy_SIREN:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);		
		}
		
		return 0;
	}
	
	public static int GetSpeed(int kind, int level) {			
		switch (kind) {
		
		}
		
		return 0;
	}
	
	public static int GetGoldenNumber(int kind, GameMain gameMain) {
		

		if(gameMain.combo.getComboState())
		{
			if(GamePowerCard.addGameComboGolden>0)			
				return GamePowerCard.addGameComboGolden;
		}
		
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGY:	
			return 1;
		
		case CoolEditDefine.Enemy_SHZYCS:
			return 1;
		
		case CoolEditDefine.Enemy_SHZY:
			return 1;
		
		case CoolEditDefine.Enemy_YGXTM:	
			return 1;	
		
		case CoolEditDefine.Enemy_YGX:
			return 1;
		
		case CoolEditDefine.Enemy_YGZ:
			return 1;
		
		case CoolEditDefine.Enemy_YGHZ:
			return 1;	
		
		case CoolEditDefine.Enemy_YGDS:
			return 1;
		
		case CoolEditDefine.Enemy_YGMM:
			return 1;
		
		case CoolEditDefine.Enemy_SHHM:	
			return 1;	
		
		case CoolEditDefine.Enemy_SHHT:	
			return 1;
			
		case CoolEditDefine.Enemy_SHX:	
			return 1;
		
		case CoolEditDefine.Enemy_SHMM:
			return 1;	
		
		case CoolEditDefine.Enemy_SHZ:
			return 1;		
			
		case CoolEditDefine.Enemy_MIMI:
			return 1;	
			
		case CoolEditDefine.Enemy_DS:
			return 1;	
			
		case CoolEditDefine.Enemy_X:	
			return 1;	
			
		case CoolEditDefine.Enemy_Z:	
			return 1;	
			
		case CoolEditDefine.Enemy_HZ:	
			return 1;	
			
		case CoolEditDefine.Enemy_MM:
			return 1;	
			
		case CoolEditDefine.Enemy_SIREN:	
			return 1;	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return 1;			
		}
		
		return 0;
	}
	
	public static int GetGoldenPercent(int kind, GameMain gameMain) {
		if(gameMain.combo.getComboState())
		{				
			return 100;
		}
		
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGY:	
			return (int)(100*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHZYCS:
			return (int)(10*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHZY:
			return (int)(50*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_YGXTM:	
			return (int)(10*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_YGX:
			return (int)(50*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_YGZ:
			return (int)(30*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_YGHZ:
			return (int)(30*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_YGDS:
			return (int)(30*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_YGMM:
			return (int)(20*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHHM:	
			return (int)(30*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_SHHT:	
			return (int)(40*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHX:	
			return (int)(50*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHMM:
			return (int)(25*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_SHZ:
			return (int)(25*GamePowerCard.addGameGolden);		
			
		case CoolEditDefine.Enemy_MIMI:
			return (int)(30*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_DS:
			return (int)(30*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_X:	
			return (int)(60*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_Z:	
			return (int)(36*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_HZ:	
			return (int)(30*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_MM:
			return (int)(60*GamePowerCard.addGameGolden);		
			
		case CoolEditDefine.Enemy_SIREN:	
			return (int)(50*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return (int)(10*GamePowerCard.addGameGolden);			
		}
		
		return 0;
	}	
	
	/*
	 * -1：为没有卡片，0：1星卡，1：2星卡，2：3星卡
	 * */
	public static int GetCardsPercent(int kind) {		
		switch (kind) {
		
//		case CoolEditDefine.Enemy_YGY:	
//			if(ExternalMethods.throwDice(0, 9999)<8000)			
//				return -1;
//			else if(ExternalMethods.throwDice(0, 99)<9999)			
//				return 0;
//			else if(ExternalMethods.throwDice(0, 99)<90)			
//				return 1;
//			else 	
//				return 2;
		
		case CoolEditDefine.Enemy_SHZYCS:
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else			
				return 0;
			
		case CoolEditDefine.Enemy_SHZY:
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else	
				return 0;
			
//		case CoolEditDefine.Enemy_YGXTM:	
//			return (int)(10*GamePowerCard.addGameGolden);	
//		
//		case CoolEditDefine.Enemy_YGX:
//			return (int)(50*GamePowerCard.addGameGolden);
//		
//		case CoolEditDefine.Enemy_YGZ:
//			return (int)(30*GamePowerCard.addGameGolden);
//		
//		case CoolEditDefine.Enemy_YGHZ:
//			return (int)(30*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_YGDS:
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else			
				return 0;
			
//		case CoolEditDefine.Enemy_YGMM:
//			return (int)(20*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHHM:	
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 
				return 0;
		
		case CoolEditDefine.Enemy_SHHT:	
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 	
				return 0;
			
		case CoolEditDefine.Enemy_SHX:	
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 			
				return 0;
			
		case CoolEditDefine.Enemy_SHMM:
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 	
				return 0;
			
		case CoolEditDefine.Enemy_SHZ:
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 			
				return 0;
//			else if(ExternalMethods.throwDice(0, 9999)<9999)			
//				return 1;
//			else 	
//				return 2;	
			
		case CoolEditDefine.Enemy_MIMI:
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 	
				return 0;	
			
		case CoolEditDefine.Enemy_DS:
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 	
				return 0;
			
		case CoolEditDefine.Enemy_X:	
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 
				return 0;
				
		case CoolEditDefine.Enemy_Z:	
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 			
				return 0;
			
		case CoolEditDefine.Enemy_HZ:	
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 
				return 0;
			
		case CoolEditDefine.Enemy_MM:
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 			
				return 0;
			
		case CoolEditDefine.Enemy_SIREN:	
			if(ExternalMethods.throwDice(0, 9999)<8000)			
				return -1;
			else 
				return 0;
			
//		case CoolEditDefine.Enemy_MEGICWATER:	
//			return -1;		
		}
		
		return -1;
	}	
	
	public static int GetGemNumber(int kind) {
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGY:	
			return 1;
		
		case CoolEditDefine.Enemy_SHZYCS:
			return 1;
		
		case CoolEditDefine.Enemy_SHZY:
			return 1;
		
		case CoolEditDefine.Enemy_YGXTM:	
			return 1;	
		
		case CoolEditDefine.Enemy_YGX:
			return 1;
		
		case CoolEditDefine.Enemy_YGZ:
			return 1;
		
		case CoolEditDefine.Enemy_YGHZ:
			return 1;	
		
		case CoolEditDefine.Enemy_YGDS:
			return 1;
		
		case CoolEditDefine.Enemy_YGMM:
			return 1;
		
		case CoolEditDefine.Enemy_SHHM:	
			return 1;	
		
		case CoolEditDefine.Enemy_SHHT:	
			return 1;
			
		case CoolEditDefine.Enemy_SHX:	
			return 1;
		
		case CoolEditDefine.Enemy_SHMM:
			return 1;	
		
		case CoolEditDefine.Enemy_SHZ:
			return 1;		
			
		case CoolEditDefine.Enemy_MIMI:
			return 1;	
			
		case CoolEditDefine.Enemy_DS:
			return 1;	
			
		case CoolEditDefine.Enemy_X:	
			return 1;	
			
		case CoolEditDefine.Enemy_Z:	
			return 1;	
			
		case CoolEditDefine.Enemy_HZ:	
			return 1;	
			
		case CoolEditDefine.Enemy_MM:
			return 1;	
			
		case CoolEditDefine.Enemy_SIREN:	
			return 1;	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return 1;			
		}
		
		return 0;
	}
	
	public static int GetGemPercent(int kind) {
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGY:	
			return 100;
		
		case CoolEditDefine.Enemy_SHZYCS:
			return 50;
		
		case CoolEditDefine.Enemy_SHZY:
			return 100;
		
		case CoolEditDefine.Enemy_YGXTM:	
			return 50;	
		
		case CoolEditDefine.Enemy_YGX:
			return 75;
		
		case CoolEditDefine.Enemy_YGZ:
			return 75;
		
		case CoolEditDefine.Enemy_YGHZ:
			return 50;	
		
		case CoolEditDefine.Enemy_YGDS:
			return 50;
		
		case CoolEditDefine.Enemy_YGMM:
			return 50;
		
		case CoolEditDefine.Enemy_SHHM:	
			return 30;	
		
		case CoolEditDefine.Enemy_SHHT:	
			return 30;
		
		case CoolEditDefine.Enemy_SHX:	
			return 50;
		
		case CoolEditDefine.Enemy_SHMM:
			return 25;	
		
		case CoolEditDefine.Enemy_SHZ:
			return 25;		
			
		case CoolEditDefine.Enemy_MIMI:
			return 30;	
			
		case CoolEditDefine.Enemy_DS:
			return 30;	
			
		case CoolEditDefine.Enemy_X:	
			return 60;	
			
		case CoolEditDefine.Enemy_Z:	
			return 36;	
			
		case CoolEditDefine.Enemy_HZ:	
			return 30;	
			
		case CoolEditDefine.Enemy_MM:
			return 60;		
			
		case CoolEditDefine.Enemy_SIREN:	
			return 30;	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return 20;			
		}
		
		return 0;
	}	
}
