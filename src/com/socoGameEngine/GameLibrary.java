package com.socoGameEngine;

import java.util.Random;

import com.endlessvegetables2.turngame.TurnGameSprite;
import com.kokatlaruruxi.wy.Sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;

public class GameLibrary {
	private static Random random = new Random();
	public static float getAngle(float x1,float y1,float x2, float y2){
		
		if(x1 == x2&&y1 == y2){
			return 0;
		}
		
		float angle =(float) Math.toDegrees(Math.acos((x2- x1)/sqrtValue(x1, y1, x2, y2)));//(float) Math.toDegrees();
		if(y2 < y1){
			angle = 360 - angle;
		}
//		LogShow.d("angle="+angle);
		return angle;
	}
	public static float sqrtValue(float x1,float y1,float x2,float y2){//任意2点间的距离
		
		return (float) Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	/**
	 * 得到百分比
	 * @param curvalue 当前值
	 * @param Percent  比例
	 * @param maxvalue 最大值
	 * @return
	 */
	public static float getPercent(float curvalue,float Percent,float maxvalue){

		if(maxvalue == 0){
			return 0;
		}
		return (float) (curvalue * Percent/maxvalue);
	}

	public static int getIntRandom(int min,int max){
		
		if(max- min == 0){
			return 0;
		}
		
		return min+(random.nextInt()>>>1)%(max- min+1);
	}
	public static float getFloatRandom(float min,float max){
		
		if(max- min == 0){
			return 0;
		}
		
		return min+ Math.abs(random.nextFloat())%(max- min+1);
	}
	public static float sin(double angle,float r){
		if(angle > 360){
			angle = angle%360;
		}
		else if(angle < 0 ){
			angle = angle %360+360;
		}
		  	angle = angle*Math.PI/180;
		  	
//		  	 if (angle< -3.14159265)
//		  		angle+= 6.28318531;
//				else
//				if (angle>  3.14159265)
//					angle-= 6.28318531;
//			double sin = 0;
//				if (angle< 0)
//			      sin= 1.27323954 * angle+ 0.405284735 * angle* angle;
//			  else
//			      sin= 1.27323954 * angle- 0.405284735 * angle* angle;
				
//			Math.sin(sin)
				 return (float)(Math.sin(angle)*r);  
//	       return (float)(sin*r);  
	  }
	  public static float cos(double angle,float r){
		  
		
		  if(angle > 360){
				angle = angle%360;
			}
			else if(angle < 0 ){
				angle = angle %360+360;
			}
		  if(angle == 90){
			 return 0; 
		  }
		  
		  angle = angle*Math.PI/180;
//		  double cos = 0;
//		  angle+= 1.57079632;
//		  if (angle>  3.14159265)
//			  angle-= 6.28318531;
//	
//		  if (angle< 0)
//		      cos= 1.27323954 * angle+ 0.405284735 * angle* angle;
//		  else
//		      cos= 1.27323954 * angle- 0.405284735 * angle* angle;
		
		  return (float)(Math.cos(angle)*r);  
//	      return  (float)(cos*r);
	 }
	  


	  
	/**
	 * 旋转任意点
	 * @param angle 角度
	 * @param x 原始坐标x
	 * @param y 原始坐标y
	 * @param initx 围绕旋转的中心点坐标x
	 * @param inity 围绕旋转的中心点坐标y
	 * @return 
	 */
	  public static float [] getRotateCoordinates(float angle,float x,float y ,float initx,float inity){
		  if(angle > 360){
			  angle = angle - 360;
		  }
		  if(angle < 0){
			  angle = 360 +angle;
		  }
		  float temp[] = {x,y};
		  angle = (float) (angle*Math.PI/180);
		  temp[0] = (float) (Math.cos(angle)*(x - initx) - Math.sin(angle)*(y- inity) + initx);
		  temp[1] = (float) (Math.sin(angle)*(x- initx) + Math.cos(angle)*(y- inity) + inity);
//		  temp[0]   =   (float) (x  * Math.cos(angle)   -   y   *   Math.sin(angle)); 
//		  temp[1]   =   (float) (x  * Math.sin(angle)   +   y   *   Math.cos(angle)); 
		  return temp;
	  }
	  public static void DrawNumber(Canvas canvas, Sprite img_Number[],
			  float x, float y,char Chars[],String n,Paint paint,byte anchor,float fixwidth) {
		  	Matrix matrix = new Matrix();
		  	matrix.setScale(1, 1);
		  	
			float temp[] = getAnchor(img_Number[0].bitmap.getWidth(),img_Number[0].bitmap.getHeight(),anchor);
			temp[0] = temp[0]*n.length();
		int w =fixwidth == 0? img_Number[0].bitmap.getWidth():img_Number[0].bitmap.getWidth()+ (int)(fixwidth*GameConfig.f_zoomy);
//		   Log.dln("i_x="+x+" i_y="+y+" w="+w+" row="+row);
		    for (int i = 0; i < n.length(); i++) {
		      for (int j = 0; j < Chars.length; j++) {
		        if (n.charAt(i) == Chars[j]) {
		        
		        	img_Number[j].drawBitmap(canvas,img_Number[j].bitmap, x + i * w +temp[0] , y +temp[1],paint);
		        	break;
		        }
		      }
		    }

		  
		  }	  
	  public static void DrawNumber(Canvas canvas, Sprite img_Number[],
			  float x, float y,char Chars[],String n,Paint paint,byte anchor,float fixwidth,float scalex,float scaley) {
		  	Matrix matrix = new Matrix();
		  
		  	
			float temp[] = getAnchor(img_Number[0].bitmap.getWidth(),img_Number[0].bitmap.getHeight(),anchor);
			temp[0] = temp[0]*n.length();
		int w =fixwidth == 0? img_Number[0].bitmap.getWidth():img_Number[0].bitmap.getWidth()+ (int)(fixwidth*GameConfig.f_zoomy);
//		   Log.dln("i_x="+x+" i_y="+y+" w="+w+" row="+row);
		matrix.setScale(scalex, scaley,w/2,img_Number[0].bitmap.getHeight()/2);
	
		    for (int i = 0; i < n.length(); i++) {
		      for (int j = 0; j < Chars.length; j++) {
		        if (n.charAt(i) == Chars[j]) {
		        	canvas.setDrawFilter(GameLibrary.pDrawFilter);   
		    
		        	img_Number[j].drawBitmap(canvas,img_Number[j].bitmap, x + i * w*scalex +temp[0] , y +temp[1],matrix,anchor,paint);
		        	break;
		        }
		      }
		    }

		  
		  }
	  public static void DrawRoundRect(Canvas canvas,int x,int y,int w,int h,int rx,int ry,Paint paint){
		  
		  RectF rect =  new RectF(x,y,w + x ,h+ y);
		   canvas.drawRoundRect(rect, rx, ry, paint);
		  
	  }
	  
	  public static void DrawArc(Canvas canvas,int i_x,int i_y,int i_fcolor,int i_bcolor,int i_r,int i_rgap,Paint paint){
			paint.setColor(i_bcolor);
			canvas.drawCircle(i_x, i_y, i_r, paint);
			paint.setColor(i_fcolor);
			canvas.drawCircle(i_x, i_y, i_r - i_rgap, paint);
	  }
	public static  void paintDrawRoundRect(Canvas canvas,int i_x,int i_y,int w,int h,int fcolor,int bcolor,
				int gap,
				Paint paint){
			int rad = 12;
			paint.setColor(fcolor);
			GameLibrary.DrawRoundRect(canvas, i_x, i_y, w, h, rad, rad, paint);
			paint.setColor(bcolor);
			GameLibrary.DrawRoundRect(canvas, i_x + gap, i_y + gap, w - gap*2, h - gap*2, rad, rad, paint);
		}
	
	public final static byte TL = 0;//左上角
	public final static byte BL = 1;//左下角
	public final static byte TR = 2;//右上角
	public final static byte BR = 3;//右下角
	public final static byte VH = 4;//居中
	public final static byte TH = 5;//上居中
	public final static byte BH = 6;//下居中
	public final static byte LV = 7;//左垂直居中
	public final static byte RV = 8;//右垂直居中
	public static void DrawBitmap(Canvas canvas,Bitmap bitmap,float i_x,float i_y,
			Matrix matrix,int anchor,
			Paint paint ){
		if(matrix == null){
			matrix = new Matrix();
		}
		float temp[] = getAnchor(bitmap.getWidth(),bitmap.getHeight(),anchor);
		matrix.postTranslate(i_x+temp[0], i_y+temp[1]);
	
		
//		if(paint == null){
//			paint = new Paint();
//		}
//		paint.setAntiAlias(true);
		if(GameManager.getAnti())
		canvas.setDrawFilter(GameLibrary.pDrawFilter);   
		canvas.drawBitmap(bitmap, matrix, paint);
	
		matrix.postTranslate(-(i_x+temp[0]), -(i_y+temp[1]));
	}

	public static float []getAnchor(float _width,float _height,int anchor){
		float tempx = 0;
		float tempy = 0;
		float bitmapwidth = _width;
		float bitmapheight = _height;

		switch(anchor){
		case TL:
			break;
		case BL:
			tempy -= bitmapheight;
			break;
		case TR:
			tempx = -bitmapwidth;
		
			break;
		case BR:
			tempx = -bitmapwidth;
			tempy = -bitmapheight;
			
			break;
		case VH:
			tempx = -bitmapwidth/2;
			tempy = -bitmapheight/2;
			

			break;
		case TH:
			tempx = -bitmapwidth/2;
			
			break;
		case BH:
			tempx = -bitmapwidth/2;
			tempy = -bitmapheight;
		
			break;
		case LV:
			tempy = -bitmapheight/2;
			
			break;
		case RV:
			tempx = -bitmapwidth;
			tempy = -(bitmapheight)/2;
			
			break;
		
		}
		float array[] ={tempx,tempy};
			return array;
	}
	public static void drawText(String strMsg, Canvas g, Paint paint, float setx,

    		float sety, int fg, int bg,int anchor) {

    		float  temp[] = getanchor (strMsg,anchor,paint);
    		
    		if(bg != fg){
            paint.setColor(bg);
         
            g.drawText(strMsg, setx + 1 + temp[0], sety + temp[1], paint);

            g.drawText(strMsg, setx + temp[0], sety - 1+ temp[1], paint);

            g.drawText(strMsg, setx + temp[0], sety + 1+ temp[1], paint);

            g.drawText(strMsg, setx + temp[0] - 1, sety+ temp[1], paint);

    		}
            paint.setColor(fg);
            

            g.drawText(strMsg, setx + temp[0], sety+ temp[1], paint);
        

    }

		
	public static void drawText(String strMsg, Canvas g, Paint paint, float setx,

    		float sety, int fg, int bg,int anchor,int StrokeWidth) {

    		float  temp[] = getanchor (strMsg,anchor,paint);
    		
    		 paint.setColor(fg);
    		 g.drawText(strMsg, setx + temp[0], sety+ temp[1], paint);
            paint.setColor(bg);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(StrokeWidth);
            g.drawText(strMsg, setx + temp[0], sety+ temp[1], paint);
            
            
            
//            g.drawText(strMsg, setx + 1 + temp[0], sety + temp[1], paint);
//
//            g.drawText(strMsg, setx + temp[0], sety - 1+ temp[1], paint);
//
//            g.drawText(strMsg, setx + temp[0], sety + 1+ temp[1], paint);
//
//            g.drawText(strMsg, setx + temp[0] - 1, sety+ temp[1], paint);

//            paint.setColor(fg);

    }
	public static PaintFlagsDrawFilter pDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG); 

	//画九宫格
	public static void paintUI(Canvas canvas,Sprite imageID,int x,int y,int w,int h,int anchor,int bgcol,Paint paint)
	{
		
		float coor[] = getAnchor(w, h, anchor);
		x+=coor[0];
		y+=coor[1];
		
		
		int unit_w	= imageID.bitmap.getWidth()/3;
		int unit_h	= imageID.bitmap.getHeight()/3;
//		LogShow.d("bgcol="+bgcol);
		
		if(bgcol<0){
			paint.setColor(bgcol);
			canvas.drawRect(x+4,y+4,x+4+w-8,y+4+h-8, paint);
		}else {
			// 中间
			for( int i=unit_w;i<w-unit_w;i+=unit_w )
			for( int j=unit_h;j<h-unit_h;j+=unit_h )
			{
				canvas.save();
				setClip(canvas, x+i,y+j,unit_w,unit_h, x+unit_w, y+unit_h,w-unit_w*2,h-unit_h*2);
//	        	canvas.clipRect( x+i,y+j,unit_w+x+i,unit_h+y+j);
			
//					canvas.setDrawFilter(pDrawFilter);   
				imageID.drawBitmap(canvas, imageID.bitmap, x+i-unit_w,y+j-unit_h,paint);
	        	canvas.restore();
//				g.setClip( x+i,y+j,unit_w,unit_h );
//				g.drawImage( Resource.img[imageID],x+i-unit_w,y+j-unit_h,Graphics.TOP|Graphics.LEFT );
			}
		}
		
		// 四条边
		for( int i=unit_w;i<w-unit_w;i+=unit_w )
		{
			canvas.save();
//        	canvas.clipRect(  x+i,y,unit_w+x+i,unit_h+y ); 
			setClip(canvas, x+i,y,unit_w,unit_h, x+unit_w, y ,w-unit_w*2, unit_h);
//			canvas.setDrawFilter(pDrawFilter);   
			imageID.drawBitmap(canvas, imageID.bitmap, x+i-unit_w,y,paint);
        	canvas.restore();
			canvas.save();
			setClip(canvas,x+i,y+h-unit_h,unit_w,unit_h, x+unit_w, y+h-unit_h, w-unit_w*2,unit_h);
//        	canvas.clipRect(x+i,y+h-unit_h,unit_w+x+i,unit_h+y+h-unit_h); 
			imageID.drawBitmap(canvas, imageID.bitmap,x+i-unit_w,y+h-imageID.bitmap.getHeight(),paint);
        	canvas.restore();
		}
		
		for (int j=unit_h;j<h-unit_h;j+=unit_h )
		{
			canvas.save();
//        	canvas.clipRect( x,y+j,unit_w+x,unit_h +y+j ); 
			setClip(canvas,x,y+j,unit_w,unit_h , x, y+unit_h, unit_w,h-unit_h*2);
		
//			canvas.setDrawFilter(pDrawFilter);   
			imageID.drawBitmap(canvas, imageID.bitmap,x,y+j-unit_h,paint);
        	canvas.restore();
			canvas.save();
			setClip(canvas,x+w-unit_w,y+j,unit_w,unit_h, x+w-unit_w, y+unit_h, unit_w,h-unit_h*2);
//        	canvas.clipRect(x+w-unit_w,y+j,unit_w+x+w-unit_w,unit_h+y+j ); 
//			canvas.setDrawFilter(pDrawFilter);   
			imageID.drawBitmap(canvas, imageID.bitmap,x+w-imageID.bitmap.getWidth(),y+j-unit_h,paint);
        	canvas.restore();
		}

		// 四个角
		canvas.save();
    	canvas.clipRect(x,y,unit_w+x,unit_h +y);
//    	canvas.setDrawFilter(pDrawFilter);   
    	imageID.drawBitmap(canvas, imageID.bitmap,x,y,paint);
    	canvas.restore();
    	
		canvas.save();
    	canvas.clipRect(x+w-unit_w,y,unit_w+x+w-unit_w,unit_h +y); 
//    	canvas.setDrawFilter(pDrawFilter);   
    	imageID.drawBitmap(canvas, imageID.bitmap,x+w-imageID.bitmap.getWidth(),y,paint);
    	canvas.restore();

		canvas.save();
    	canvas.clipRect( x,y+h-unit_h,x+unit_w,unit_h+y+h-unit_h ); 
  
//		canvas.setDrawFilter(pDrawFilter);   
    	imageID.drawBitmap(canvas, imageID.bitmap,x,y+h-imageID.bitmap.getHeight(),paint);
    	canvas.restore();
    	
		canvas.save();
    	canvas.clipRect(x+w-unit_w,y+h-unit_h,unit_w+x+w-unit_w,unit_h+y+h-unit_h); 
    	
//		canvas.setDrawFilter(pDrawFilter);   
    	imageID.drawBitmap(canvas, imageID.bitmap,x+w-imageID.bitmap.getWidth(),y+h-imageID.bitmap.getHeight(),paint);
    	canvas.restore();
	}
	static void setClip(Canvas canvas,int x,int y,int w,int h,int x2,int y2,int w2,int h2){
		int [] cc={0,0,0,0};
		if( Math.abs( (x-x2<<1)+w-w2 )<w+w2&& Math.abs( (y-y2<<1)+h-h2 )<h+h2 )
		{
			if(x<x2){
				cc[0]=x2;
				if(x+w<x2+w2){
					cc[2]=w-(x2-x);
				}else{
					cc[2]=w2;
				}
				
			}else{
				cc[0]=x;
				if(x+w<x2+w2){
					cc[2]=w;
				}else{
					cc[2]=w2-(x-x2);
				}
			}
			if(y<y2){
				cc[1]=y2;
				if(y+h<y2+h2){
					cc[3]=h-(y2-y);
				}else{
					cc[3]=h2;
				}
			}else{
				cc[1]=y;
				if(y+h<y2+h2){
					cc[3]=h;
				}else{
					cc[3]=h2-(y-y2);
				}
			}
		}
		canvas.clipRect(cc[0],cc[1],cc[0]+cc[2],cc[1]+cc[3]);
	}
	private static float[] getanchor(String msg,int anchor,Paint paint) {
		// TODO Auto-generated method stub

		float tempx = 0;
		float tempy = 0;
		Rect rect = new Rect();
	
		paint.getTextBounds(msg, 0, msg.length(), rect);

		switch(anchor){
		case GameLibrary.TL:
			
			break;
		case GameLibrary.BL:
			tempy -= rect.height();
			break;
		case GameLibrary.TR:
			tempx -= rect.width();
			break;
		case GameLibrary.BR:
			tempx -= rect.width();
			tempy -= rect.height();
			break;
		case GameLibrary.VH:
			tempx -= rect.width()/2;
			tempy -= rect.height()/2;
			break;
		case GameLibrary.TH:
			tempx -= rect.width()/2;
			break;
		case GameLibrary.BH:
			tempx -= rect.width()/2;
			tempy -= rect.height();
			break;
		case GameLibrary.LV:
		
			tempy -= rect.height()/2;
			break;
		case GameLibrary.RV:
			tempx -= rect.width();
			tempy -= rect.height()/2;
			break;
		
		}

		float array[] ={tempx,tempy};
			return array;
	
	}
	
}
class ImageState {   
	   float Left;   
	   float Top;   
	   float Right;   
	   float Bottom;   
	   public ImageState(){
		   
	   }
	} 
