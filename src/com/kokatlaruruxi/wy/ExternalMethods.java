package com.kokatlaruruxi.wy;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Random;

import com.socoGameEngine.ColorChange;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.MainActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.net.Uri;

public class ExternalMethods {
	private static Random random = new Random();
	
	public static void RandomArray(int Array[],int RandomMun)
	{
		int index1=0,index2=0,temp=0;
		for(int i=0;i<RandomMun;i++){
			index1=throwDice(0, Array.length-1);
			index2=throwDice(0, Array.length-1);
			temp=Array[index1];
			Array[index1]=Array[index2];
			Array[index2]=temp;
		}
	}
	
	public static boolean RectCollision(RectF rectF1, RectF rectF2)
	{
		return RectF.intersects(rectF1, rectF2);
	}
	
	public static boolean CollisionTest(float x,float y, float x2,float y2,float x3,float y3)
	{
		if(x>=x2&&x<=x3&&y>=y2&&y<=y3){
			return true;
		}
		return false;
	}
	
	//按比例返回W
	public static int getProportionW(int maxW,int index,int Maxindex)
	{
		if(index<0){
			index=0;
		}else if(index>Maxindex){
			index=Maxindex;
		}
		return maxW*index/Maxindex;
	}
	
	//10进制每位拆开返回。例如：163 返回byte[]={1,6,3}
	public static byte[] caicaikan(int a)
	{
		String str=""+a;
		byte temp[]=new byte[str.length()];
		for(int i=0;i<temp.length;i++){
			temp[i]=Byte.parseByte(str.substring(i, i+1));
		}
		
		return temp;
	}
	
	//测试第几位是否被用到。例如：1101，0110 true  1001，0110 false
	public static boolean BTest2(int a,int b)
	{
		return ((a&b)==0?false:true);
	}
	
	//任意2点间的距离
	public static int sqrtValue(int x1,int y1,int x2,int y2){
		return (int)Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	//某点和坐标0，0的角度。3点钟方向为0度，向上增加。
	public static float getAngle(float x, float y){
		if(x==0){
			return -y>0 ? 90 : 270;
		}else if(x>0){
			return ((float)Math.toDegrees(Math.atan(-y/x))+360)%360;
		}else{
			return (float)Math.toDegrees(Math.atan(-y/x))+180;
		}
	}
	
	public static double getSin(float angle){
		return Math.sin(Math.toRadians(angle));
	}
	public static double getCos(float angle){
		return Math.cos(Math.toRadians(angle));
	}
	//坐标点0，0，往角度angle走R距离。求坐标点
	public static float getAngleX(float angle, float r){
		return (float)(r*getCos(angle));
	}
	public static float getAngleY(float angle, float r){
		return -(float)(r*getSin(angle));
	}
	
	//得到百分比的值
	public static float getPercent(int cur,int curpercent,int max){
		if(max == 0){
			return 0;
		}
		return (float) (cur * curpercent/max);
	}

	public static int throwDice(int min,int max){
		if(max- min == 0){
			return min;
		}
		return min+ Math.abs(random.nextInt())%(max- min+1);
	}
	public static float getFloatRandom(float min,float max){
		if(max- min == 0){
			return 0;
		}
		return min+ Math.abs(random.nextFloat())%(max- min+1);
	}
	public static float sin(double angle,float r){
		  	if(angle < 0){
		  		angle = 360 + angle;
		  	}
		  	
		  	angle = angle*Math.PI/180;
		  	
		  	 if (angle< -3.14159265)
		  		angle+= 6.28318531;
				else
				if (angle>  3.14159265)
					angle-= 6.28318531;
			double sin = 0;
				if (angle< 0)
			      sin= 1.27323954 * angle+ 0.405284735 * angle* angle;
			  else
			      sin= 1.27323954 * angle- 0.405284735 * angle* angle;
	       return (float)(sin*r);  
	  }
	  public static float cos(double angle,float r){
		  
		  if(angle < 0){
			  angle = 360 + angle;
		  	}
		  
		  angle = angle*Math.PI/180;
		  double cos = 0;
		  angle+= 1.57079632;
		  if (angle>  3.14159265)
			  angle-= 6.28318531;
	
		  if (angle< 0)
		      cos= 1.27323954 * angle+ 0.405284735 * angle* angle;
		  else
		      cos= 1.27323954 * angle- 0.405284735 * angle* angle;
		
	      return  (float)(cos*r);
	 }
	
	  
//	  public static void DrawNumber(Canvas canvas, Bitmap img_Number,int x, int y, int h,int row,char Chars[],String n,Paint paint) {
//		  
//		  float w = ((float)img_Number.getWidth())/Chars.length;
////		   System.out.println("img_Number.getWidth()="+img_Number.getWidth()+",i_x="+x+" i_y="+y+" w="+w+" row="+row+" h="+h);
//		    for (int i = 0; i < n.length(); i++) {
//		      for (int j = 0; j < Chars.length; j++) {
//		        if (n.charAt(i) == Chars[j]) {
//		        	canvas.save();
//		        	canvas.clipRect(x+ i*w,y,x+ i*w+w,y+h);
//		        	canvas.drawBitmap(img_Number, x + i * w - j *w, y - row*h,paint);
//		        	canvas.restore();
//		          break;
//		        }
//		      }
//		    }
//	  }
	//synge	暂定使用，可以缩放单行数字
	  public static void DrawNumber1(Canvas canvas, Bitmap img_Number[],int x, int y,char Chars[],String n,Paint paint,int jianju,float size) {
		  DrawNumber1(canvas, img_Number,x, y, img_Number[0].getHeight(),Chars,n,paint,jianju,size);
	  }
	  //synge	暂定使用，可以缩放单行数字
	  public static void DrawNumber1(Canvas canvas, Bitmap img_Number[],int x, int y, int h,char Chars[],String n,Paint paint,int jianju,float size) {
		  float w = (float)img_Number[0].getWidth()*size;
		    for (int i = 0; i < n.length(); i++) {
		      for (int j = 0; j < Chars.length; j++) {
		        if (n.charAt(i) == Chars[j]) {
		        	drawImage(canvas, img_Number[j], (int)(x + i * (w+jianju*size)) , y, size, size, 255, 0, 0, 0);
		          break;
		        }
		      }
		    }
	  }
	  public static void DrawNumber(Canvas canvas, Sprite img_Number,int x, int y, int h,int row,char Chars[],String n,Paint paint,int jianju) {
		  
		  float w = ((float)img_Number.bitmap.getWidth())/Chars.length;
//		   System.out.println("img_Number.getWidth()="+img_Number.getWidth()+",i_x="+x+" i_y="+y+" w="+w+" row="+row+" h="+h);
		    for (int i = 0; i < n.length(); i++) {
		      for (int j = 0; j < Chars.length; j++) {
		        if (n.charAt(i) == Chars[j]) {
		        	canvas.save();
		        	canvas.clipRect(x+ i*(w+jianju),y,x+ i*(w+jianju)+w,y+h);
		        	img_Number.drawBitmap(canvas, img_Number.bitmap, x + i * (w+jianju) - j * w, y - row*h,paint);
		        	canvas.restore();
		          break;
		        }
		      }
		    }
	  }
	  
	  public static void DrawNumber(Canvas canvas, Sprite img_Number[],int x, int y, int h,int row,char Chars[],String n,Paint paint,int jianju) {
		  float w = (float)img_Number[0].bitmap.getWidth();
//		   System.out.println("img_Number.getWidth()="+img_Number.getWidth()+",i_x="+x+" i_y="+y+" w="+w+" row="+row+" h="+h);
		    for (int i = 0; i < n.length(); i++) {
		      for (int j = 0; j < Chars.length; j++) {
		        if (n.charAt(i) == Chars[j]) {
//		        	canvas.save();
//		        	canvas.clipRect(x+ i*(w+jianju),y,x+ i*(w+jianju)+w,y+h);
		        	img_Number[j].drawBitmap(canvas, img_Number[j].bitmap, x + i * (w+jianju) , y - row*h,paint);
//		        	canvas.restore();
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
			ExternalMethods.DrawRoundRect(canvas, i_x, i_y, w, h, rad, rad, paint);
			paint.setColor(bcolor);
			ExternalMethods.DrawRoundRect(canvas, i_x + gap, i_y + gap, w - gap*2, h - gap*2, rad, rad, paint);
	}
	
	public static void drawString(Canvas canvas,Paint paint,String s,int x,int y,int foreColor,int backColor)
	{
		paint.setColor(backColor);
		canvas.drawText(s,x-1,y,paint);
		canvas.drawText(s,x+1,y,paint);
		canvas.drawText(s,x,y-1,paint);
		canvas.drawText(s,x,y+1,paint);
		
		paint.setColor(foreColor);
		canvas.drawText(s,x,y,paint);
	}
	
//	static int paintNumber;
	
	static Matrix matrix=new Matrix();
	static Paint paint=new Paint();
	static int HCENTER = -978654321;
	private static ColorMatrix mCM = new ColorMatrix();
	//sizeW，sizeH：放大缩小，负数为镜像翻转(1为正常大小)
	//Alpha：透明度255-0（0为透明）
	//Rotate,RotateX,RotateY 旋转角度，旋转点(图片中的点)。
	//r,g,b变色
	public static void drawImage(Canvas canvas,Bitmap img,float x,float y,float sizeW,float sizeH,int Alpha,float Rotate,float RotateX,float RotateY,int r,int g,int b)
	{
		if(img==null)
			return;
	
//		paintNumber ++;
		
//		Float imgW=Math.abs(img.getWidth()*sizeW),imgH=Math.abs(img.getHeight()*sizeH);
//		matrix.reset();
//		paint.reset();
//		
////		boolean isbianse=false;
//		if(r!=0||g!=0||b!=0){
////			isbianse=true;
//			ColorChange.setTranslate(mCM, r, g, b, 0);
//			paint.setColorFilter(new ColorMatrixColorFilter(mCM));
//		}
//		
//		if(Alpha<255)
//			paint.setAlpha(Alpha);
//		else if(Alpha==0){
//			return;
//		}
//		if(sizeW!=1||sizeH!=1){
//			matrix.postScale(sizeW, sizeH);
////			if(GameManager.getAnti()){
////				canvas.setDrawFilter(GameLibrary.pDrawFilter);
////			}
//		}
//
//		
//		matrix.postTranslate(x+(sizeW<0?imgW:0), y+(sizeH<0?imgH:0));
//		if(Rotate!=0)
//			matrix.postRotate(Rotate, RotateX==HCENTER?(x+imgW/2):x+RotateX,  RotateY==HCENTER?(y+imgH/2):y+RotateY);
//		
//		canvas.drawBitmap(img, matrix, paint);
		
		
		Float imgW=Math.abs(img.getWidth()*sizeW),imgH=Math.abs(img.getHeight()*sizeH);
		matrix.reset();
		paint.reset();
		
//		boolean isbianse=false;
		if(r!=0||g!=0||b!=0){
//			isbianse=true;
			ColorChange.setTranslate(mCM, r, g, b, 0);
			paint.setColorFilter(new ColorMatrixColorFilter(mCM));
		}
		
		if(Alpha<255)
			paint.setAlpha(Alpha);
		else if(Alpha==0){
			return;
		}
		if(sizeW!=1||sizeH!=1){
			matrix.postScale(sizeW, sizeH);
			if(GameManager.getAnti()){
				canvas.setDrawFilter(GameLibrary.pDrawFilter);
			}
		}

		
		matrix.postTranslate(x+(sizeW<0?imgW:0), y+(sizeH<0?imgH:0));
		if(Rotate!=0)
			matrix.postRotate(Rotate, RotateX==HCENTER?(x+imgW/2):x+RotateX,  RotateY==HCENTER?(y+imgH/2):y+RotateY);
		
		canvas.drawBitmap(img, matrix, paint);
		
	}
//	public static void drawImage(Canvas canvas,Bitmap img,Float x,Float y,Float sizeW,Float sizeH,int Alpha,int Rotate,Float RotateX,Float RotateY)
//	{
//		Float imgW=Math.abs(img.getWidth()*sizeW),imgH=Math.abs(img.getHeight()*sizeH);
//		matrix.reset();
//		paint.reset();
//		if(Alpha<255)
//			paint.setAlpha(Alpha);
//		if(sizeW!=1||sizeH!=1)
//			matrix.postScale(sizeW, sizeH);
//		canvas.save();
//		
//		float value[]	= new float[9];
//		canvas.getMatrix().getValues(value);
//		float ex	= value[Matrix.MTRANS_X];
//		float ey	= value[Matrix.MTRANS_Y];
////		matrix.postTranslate(ex+x+(sizeW<0?imgW:0),ey+y+(sizeH<0?imgH:0));
//
//		if(Rotate!=0)
//			matrix.postRotate(Rotate, RotateX==GameConfig.HCENTER?(x+imgW/2):x+RotateX,  RotateY==GameConfig.HCENTER?(y+imgH/2):y+RotateY);
//		
//		canvas.setMatrix(matrix);
//		
////		canvas.drawBitmap(img, matrix, paint);
//		canvas.drawBitmap(img,ex+x+(sizeW<0?imgW:0),ey+y+(sizeH<0?imgH:0),
//						  paint);
//		canvas.restore();
//	}
	public static void drawImage(Canvas canvas,Bitmap img,int x,int y,float sizeW,float sizeH,int Alpha,float Rotate,int RotateX,int RotateY)
	{
		drawImage(canvas,img,x+0f,y+0f,sizeW,sizeH,Alpha,Rotate,RotateX+0f,RotateY+0f,0,0,0);
	}
	public static void drawImage(Canvas canvas,Bitmap img,float x,Float y,float sizeW,Float sizeH,int Alpha,float Rotate,int RotateX,int RotateY)
	{
		drawImage(canvas,img,x+0f,y+0f,sizeW,sizeH,Alpha,Rotate,RotateX+0f,RotateY+0f,0,0,0);
	}
	
	public static void MoreGame(String str)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW);								
		intent.addCategory(Intent.CATEGORY_DEFAULT);									
		intent.setData(Uri.parse(str));							
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);									
		Main.getActivity().startActivity(intent);
	}
	
	public static void setClip(Canvas canvas,int x,int y,int w,int h,int x2,int y2,int w2,int h2){
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
	
	public static void paintUI1(Canvas canvas,Paint paint,Sprite imageID,int x,int y,int w,int h ,int bgcol) {
		int unit_w	= imageID.bitmap.getWidth()/3;
		if(bgcol>=0){
			paint.setColor(bgcol);
			canvas.drawRect(x+4,y+4,x+4+w-8,y+4+h-8, paint);
		}else if(bgcol!=-100){
			for( int i=unit_w;i<w-unit_w;i+=unit_w ) {
				canvas.save();
				setClip(canvas, x+i,y,unit_w,h, x+unit_w, y,w-unit_w*2,h);
				imageID.drawBitmap(canvas, imageID.bitmap, x+i-unit_w,y,paint);
	        	canvas.restore();
			}
			
			// 四个角
			canvas.save();
	    	canvas.clipRect(x,y,unit_w+x,h+y);
	    	imageID.drawBitmap(canvas, imageID.bitmap,x,y,paint);
	    	canvas.restore();
	    	
	    	canvas.save();
	    	canvas.clipRect(x+w-unit_w,y,x+w,y+h); 
	    	imageID.drawBitmap(canvas, imageID.bitmap,x+w-imageID.bitmap.getWidth(),y,paint);
	    	canvas.restore();
		}
	}
	
	//画九宫格
	public static void paintUI(Canvas canvas,Paint paint,Sprite imageID,int x,int y,int w,int h ,int bgcol)
	{
		int unit_w	= imageID.bitmap.getWidth()/3;
		int unit_h	= imageID.bitmap.getHeight()/3;

		if(bgcol>=0){
			paint.setColor(bgcol);
			canvas.drawRect(x+4,y+4,x+4+w-8,y+4+h-8, paint);
		}else if(bgcol!=-100){
			// 中间
			for( int i=unit_w;i<w-unit_w;i+=unit_w )
			for( int j=unit_h;j<h-unit_h;j+=unit_h )
			{
				canvas.save();
				setClip(canvas, x+i,y+j,unit_w,unit_h, x+unit_w, y+unit_h,w-unit_w*2,h-unit_h*2);
//	        	canvas.clipRect( x+i,y+j,unit_w+x+i,unit_h+y+j);
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
			imageID.drawBitmap(canvas, imageID.bitmap,x,y+j-unit_h,paint);
        	canvas.restore();
			canvas.save();
			setClip(canvas,x+w-unit_w,y+j,unit_w,unit_h, x+w-unit_w, y+unit_h, unit_w,h-unit_h*2);
//        	canvas.clipRect(x+w-unit_w,y+j,unit_w+x+w-unit_w,unit_h+y+j ); 
			imageID.drawBitmap(canvas, imageID.bitmap,x+w-imageID.bitmap.getWidth(),y+j-unit_h,paint);
        	canvas.restore();
		}

		// 四个角
		canvas.save();
    	canvas.clipRect(x,y,unit_w+x,unit_h +y);
    	imageID.drawBitmap(canvas, imageID.bitmap,x,y,paint);
    	canvas.restore();
    	
		canvas.save();
    	canvas.clipRect(x+w-unit_w,y,unit_w+x+w-unit_w,unit_h +y); 
    	imageID.drawBitmap(canvas, imageID.bitmap,x+w-imageID.bitmap.getWidth(),y,paint);
    	canvas.restore();

		canvas.save();
    	canvas.clipRect( x,y+h-unit_h,x+unit_w,unit_h+y+h-unit_h ); 
    	imageID.drawBitmap(canvas, imageID.bitmap,x,y+h-imageID.bitmap.getHeight(),paint);
    	canvas.restore();
    	
		canvas.save();
    	canvas.clipRect(x+w-unit_w,y+h-unit_h,unit_w+x+w-unit_w,unit_h+y+h-unit_h); 
    	imageID.drawBitmap(canvas, imageID.bitmap,x+w-imageID.bitmap.getWidth(),y+h-imageID.bitmap.getHeight(),paint);
    	canvas.restore();
	}
	
	//临时使用～
	public static void paintjiantou(Canvas canvas,Paint paint,Sprite imageID,int x,int y,int w,int h)
	{
		int unit_w	= imageID.bitmap.getWidth()/3;
		int unit_h	= imageID.bitmap.getHeight()/3;

			canvas.save();
			canvas.clipRect(x,y,x+w,y+unit_h);
			imageID.drawBitmap(canvas, imageID.bitmap, x+w/2-imageID.bitmap.getWidth()/2,y,paint);
	    	canvas.restore();
			canvas.save();
        	canvas.clipRect(x,y+h-unit_h,x+w,y+h);
        	imageID.drawBitmap(canvas, imageID.bitmap,x+w/2-imageID.bitmap.getWidth()/2,y+h-imageID.bitmap.getHeight(),paint);
        	canvas.restore();

			canvas.save();
        	canvas.clipRect( x,y,unit_w+x,y+h); 
        	imageID.drawBitmap(canvas, imageID.bitmap,x,y+h/2-imageID.bitmap.getHeight()/2,paint);
        	canvas.restore();
			canvas.save();
        	canvas.clipRect( x+w-unit_w,y,x+w,y+h); 
        	imageID.drawBitmap(canvas, imageID.bitmap,x+w-imageID.bitmap.getWidth(),y+h/2-imageID.bitmap.getHeight()/2,paint);
        	canvas.restore();
	}
	
	public static void paintzhao(Canvas canvas,Paint paint,int Col,int i_alpha,int x,int y,int x2,int y2)
	{
	  	paint.setColor(Col);
		paint.setAlpha(i_alpha);
		Rect rect = new Rect(x,y,x2,y2);
		canvas.drawRect(rect, paint);
	}
	
	
//	paint{
//	g.setColor(0);
//	g.fillRect(0,0,200,300);
//	if(yanhua!=null)
//		paintyanhua(g,yanhua,0xffffff,-1);
//	
//	if(yanhua!=null)
//		if(updatayanhua(yanhua)){
//			yanhua=null;
//		}
//}

//KeyPressed( int keyValue )
//{
//	addyanhua(30,Environment.SCREEN_WIDTH/2,Environment.SCREEN_HEIGHT/2,20,20,15);
//}
	
	public static int yanhua[][][]=new int[10][][];
	static void addyanhua(int id,int k,int x,int y,int maxspeedx,int maxspeedy,int shijian,boolean isMenu){
		int t=-1;
		if(id<0||id>yanhua.length-1){
			for(int i=0;i<yanhua.length;i++){
				if(yanhua[i]==null){
					yanhua[i]=new int [k][6];
					t=i;
					break;
				}
			}
			if(t<0)
				return;
		}else{
			yanhua[id]=new int [k][6];
			t=id;
		}
		
		
		for(int i=0;i<k;i++){
			yanhua[t][i][0]=x;
			yanhua[t][i][1]=y;
			if(isMenu){
				yanhua[t][i][2]=ExternalMethods.throwDice(0,maxspeedx-1);
				yanhua[t][i][3]=ExternalMethods.throwDice(0,maxspeedy-1);
			}else{
				if(i<k/4){
					yanhua[t][i][2]=-ExternalMethods.throwDice(0,maxspeedx-1);
					yanhua[t][i][3]=-ExternalMethods.throwDice(0,maxspeedy-1);
				}else if(i<k/2){
					yanhua[t][i][2]=-ExternalMethods.throwDice(0,maxspeedx-1);
					yanhua[t][i][3]=ExternalMethods.throwDice(0,maxspeedy-1);
				}else if(i<k*3/4){
					yanhua[t][i][2]=ExternalMethods.throwDice(0,maxspeedx-1);
					yanhua[t][i][3]=-ExternalMethods.throwDice(0,maxspeedy-1);
				}else{
					yanhua[t][i][2]=ExternalMethods.throwDice(0,maxspeedx-1);
					yanhua[t][i][3]=ExternalMethods.throwDice(0,maxspeedy-1);
				}
			}
			
			yanhua[t][i][4]=ExternalMethods.throwDice(0,4)+shijian;
			yanhua[t][i][5]=ExternalMethods.throwDice(0,2);
			if(yanhua[t][i][2]==0){
				int throwtemp=ExternalMethods.throwDice(0,99);
				if(throwtemp<33)
					yanhua[t][i][2]++;
				else if(throwtemp<66)
					yanhua[t][i][2]--;
			}
		}
	}
	
	static boolean updatayanhua(int yanhua[][]){
		boolean kong=true;
			for(int i=0;i<yanhua.length;i++){
				if(yanhua[i][4]>0){
					kong=false;
					yanhua[i][0]+=yanhua[i][2];
					yanhua[i][1]+=yanhua[i][3];
					
					if(yanhua[i][2]>1){
						int throwtemp=ExternalMethods.throwDice(0,99);
						if(throwtemp<33)
							yanhua[i][2]--;
						else if(throwtemp<66)
							yanhua[i][2]-=2;
					}else if(yanhua[i][2]<-1){
						int throwtemp=ExternalMethods.throwDice(0,99);
						if(throwtemp<33)
							yanhua[i][2]++;
						else if(throwtemp<66)
							yanhua[i][2]+=2;
					}
					if(yanhua[i][3]>4){
						if(ExternalMethods.throwDice(0,99)<50)
							yanhua[i][3]--;
					}else if(yanhua[i][3]<0){
						if(ExternalMethods.throwDice(0,99)<50)
							yanhua[i][3]+=2;
					}
					else if(yanhua[i][3]<4){
						if(ExternalMethods.throwDice(0,99)<50)
							yanhua[i][3]++;
					}
					yanhua[i][4]--;
				}
			}
		return kong;
	}
		
	static void paintyanhua(Canvas g,int yanhua[][],int color,int color2,int size,boolean weiba){
		paint.reset();
		for(int i=0;i<yanhua.length;i++){
			if(yanhua[i][4]>0){
				if(weiba){
					if(color2>=0)
						paint.setColor(color2);
					else{
						int col[]=new int[3];
						for(int j=0;j<3;j++){
							col[j]=ExternalMethods.throwDice(0,99)+150;
						}
						col[ExternalMethods.throwDice(0,2)]=255;
						
						paint.setARGB(255, col[0],col[1],col[2]);
					}
					
					g.drawLine(yanhua[i][0],yanhua[i][1],yanhua[i][0]+yanhua[i][2]+size/2,yanhua[i][1]+yanhua[i][3]+size/2,paint);
					g.drawLine(yanhua[i][0],yanhua[i][1]-1,yanhua[i][0]+yanhua[i][2]+size/2,yanhua[i][1]+yanhua[i][3]+size/2-1,paint);
					paint.setColor(color);
//					g.fillRect(yanhua[i][0]+yanhua[i][2],yanhua[i][1]+yanhua[i][3],size,size);
					g.drawRect(yanhua[i][0]+yanhua[i][2],yanhua[i][1]+yanhua[i][3],size+yanhua[i][0]+yanhua[i][2],size+yanhua[i][1]+yanhua[i][3],paint);
				}else{
//					if(color2>0){
//						g.setColor(color2);
//						g.fillArc(yanhua[i][0]+yanhua[i][2],yanhua[i][1]+yanhua[i][3],size+2,size+2,0,360);
//						g.setColor(color);
//						g.fillArc(yanhua[i][0]+yanhua[i][2]+1,yanhua[i][1]+yanhua[i][3]+1,size,size,0,360);
//					}else{
//						g.setColor(color);
//						g.fillRect(yanhua[i][0]+yanhua[i][2],yanhua[i][1]+yanhua[i][3],size,size);
//					}
				}
			}
		}
	}
	
	static void paintjianbian(Canvas canvas, int startColor, int endColor){
		LinearGradient sweepGradient = new LinearGradient(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, 
				startColor, endColor, TileMode.REPEAT);
		Paint paint = new Paint();
		paint.setShader(sweepGradient);
		canvas.drawPaint(paint);
	}
	
	//int值转美国式数值如:  1234转1,234
	public static String IntToStr_En(int count) {
		return new DecimalFormat(",###").format(count);
	}
	//long值转美国式数值如:  1234转1,234
	public static String LongToStr_En(long count) {
		return new DecimalFormat(",###").format(count);
	}
	
	public static String timeToStr(long time) {
		String str = "";
		long tempCD = time/1000;
		if (tempCD%60 == 0) str = "00";
		else if (tempCD%60 < 10) str = "0" + tempCD%60;
		else str = str + tempCD%60;
		tempCD = tempCD/60;
		if (tempCD%60 == 0) str = "00:" + str;
		else if (tempCD%60 < 10) str = "0" + tempCD%60 + ":" + str;
		else str = tempCD%60 + ":" + str;
		return str;
	}
	
	public static String getFromAssets(String fileName) {
		try {
			InputStreamReader inputReader = new InputStreamReader(
					MainActivity.getActivity().getResources().getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			String Result = "";
			while ((line = bufReader.readLine()) != null)
				Result += line;
			return Result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
