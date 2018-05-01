package com.socoGameEngine;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import com.kokatlaruruxi.wy.Sprite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;

public class GameImage {
	

	
//	private static final int IMAGE_MAX_SIZE = 70;
	private static HashMap<String, Bitmap> hMap  = new HashMap<String, Bitmap>();
	private static HashMap<String, Bitmap[]> hMapArray  = new HashMap<String, Bitmap[]>();

	private static HashMap<String, Integer> hMap_num  = new HashMap<String, Integer>();
	private static HashMap<String, Integer> hMapArray_num  = new HashMap<String, Integer>();
	
	
	public static void showImageHashMap()
	{		
		 Iterator<String> iterator = hMap_num.keySet().iterator();
		 
		 while(iterator.hasNext()){
			 String key = (String)iterator.next();	
			 
			 System.out.println("hMap_num==================>>>>"+key);
		 }
		 
        iterator = hMapArray_num.keySet().iterator();
		 
		 while(iterator.hasNext()){
			 String key = (String)iterator.next();	
			 
			 System.out.println("hMapArray_num==================>>>>"+key);
		 }
		 
		 
		 iterator = hMap.keySet().iterator();
		 
		 while(iterator.hasNext()){
			 String key = (String)iterator.next();	
			 
			 System.out.println("hMap==================>>>>"+key);
		 }
		 
	}
	
	/*
	 * 将图片数list的值至为1
	 * */
	public static void initImageHashMap()
	{	
		Iterator<String> iterator = hMap_num.keySet().iterator();
		 
		 while(iterator.hasNext()){
			 String key = (String)iterator.next();	
			 
			 hMap_num.put(key, 1);			
		 }
		 
        iterator = hMapArray_num.keySet().iterator();
		 
		 while(iterator.hasNext()){
			 String key = (String)iterator.next();	
			 
			 hMapArray_num.put(key, 1);	
		 }		
	}
	
	/**
	 *  添加图片
	 * @param filename  图片名字
	 */
	 private static final void addImage(String fileName){
		 if(!hMap.containsKey(fileName)){
			 Bitmap bitmap = CreateZoomImage(fileName);
			 if(bitmap!= null){
				 hMap.put(fileName, bitmap);
				 hMap_num.put(fileName, 1);
				 
				 bitmap	= null;
				 LogShow.d("createImageID = "+ fileName);
			 }
			 else{
				 LogShow.d("not Exist ImageID = "+ fileName);
			 }
			
		 }
		 else{
			 int tempNum=hMap_num.get(fileName);
			 hMap_num.put(fileName, tempNum+1);
			 LogShow.d("Already exist ImageID = "+ fileName);
		 }
		 
	 }
	 
	 /**
		 *  获取图片 如果没有则创建
		 * @param fileName  图片名字 
		 */
	 
	 public static final Bitmap getImage(String fileName){
		 if(!hMap.containsKey(fileName)){
			 addImage(fileName);
		 }else{
			 int tempNum=hMap_num.get(fileName);
			 hMap_num.put(fileName, tempNum+1);
		 }
		 
		 return hMap.get(fileName);
	 }
	 
	 public static final Bitmap getOrgImage(String fileName){
		 if(!hMap.containsKey(fileName)){
			 Bitmap bitmap = getFromAssets(fileName);
			 if(bitmap!= null){
				 hMap.put(fileName, bitmap);
				 hMap_num.put(fileName, 1);
				 LogShow.d("createImageID = "+ fileName);
			 }
			 else{
				 LogShow.d("File = "+ fileName+" can not found");
			 }
			 bitmap = null;
		 }
		 else{
			 int tempNum=hMap_num.get(fileName);
			 hMap_num.put(fileName, tempNum+1);
			 LogShow.d("Already exist ImageID = "+ fileName);
		 }
		 return hMap.get(fileName);
	 }
	 
//	 /**
//	  * 获得图片数组
//	  * @param fileName 图片名字
//	  * @return
//	  */
//	 public static final Bitmap[] getImageArray(String fileName){
//		 if(!hMapArray.containsKey(fileName)){
//			 return null;
//		 }
//		 
//		 return hMapArray.get(fileName);
//	 }
	 /**
	  * 根据图片首名自动获得图片数组
	  * @param fileName  图片名字 ，图片名字从0 开始起 比如a0,a1,a2,那fileName就写a即可
	  * @param width 图片的宽度
	  * @param height 图片的高度
	  * @return bitm
	  */
	 public static final Bitmap[] getNolimitAutoSizeImage(String fileName){

	    	
	    	if(!hMapArray.containsKey(fileName)){
	    
//	    		LogShow.dln("curfileName ="+fileName );
//	        	showMemory();
	    		int num = 0;
				while(true){
					try {
						String tempstr= ".png";
						if(!GameConfig.b_PngRead){
							tempstr = ".rsp";
						}
						
						InputStream is = MainActivity.getActivity().getAssets()
						  .open(fileName+num+tempstr);
						is.close();
						num++;
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						LogShow.d(fileName+" getNolimitAutoSizeImage error");
						LogShow.d(e.toString());
						break;
					}
				}
				LogShow.d(fileName+" nolimit pic num is="+num);
	    		Bitmap tempBitArray[] = new Bitmap[num];
	    		
	    		for(int i = 0;i<tempBitArray.length;i++){
	    			 tempBitArray[i] =CreateZoomImage(fileName+i);
	    		}
	    		
//	    		LogShow.dln("AfterloadfileName ="+fileName );
//	        	showMemory();
				 if(tempBitArray!= null){
					 hMapArray.put(fileName, tempBitArray);
					 hMapArray_num.put(fileName,1);
					 LogShow.d("createImageArrayID = "+ fileName);
				 }
				return tempBitArray;
			 }
			 else{
				 int tempNum = hMapArray_num.get(fileName);
				 hMapArray_num.put(fileName,tempNum+1);
				 LogShow.d("existImageArrayID = "+ fileName);
				 return hMapArray.get(fileName);
			 }
	    
	 }
	 /** 
	  * 得到未知长度的图片数组
	  * @param fileName 图片名字
	  * @param changesize 缩放尺寸
	  * @return
	  */
	 public static final Bitmap[] getNolimitImage(String fileName){

	    	
	    	if(!hMapArray.containsKey(fileName)){
	    
//	    		LogShow.d("curfileName ="+fileName );
//	        	showMemory();
	    		int num = 0;
				while(true){
					try {
						String tempstr= ".png";
						if(!GameConfig.b_PngRead){
							tempstr = ".rsp";
						}
						InputStream is = MainActivity.getActivity().getAssets()
						  .open(fileName+num+tempstr);
						is.close();
						num++;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						break;
					}
				}
				LogShow.d(fileName+" nolimit pic num is="+num);
	    		Bitmap tempBitArray[] = new Bitmap[num];
	    		
	    		for(int i = 0;i<tempBitArray.length;i++){
	    		
////	                   opt.inTempStorage = new byte[5*1024*1024] ;
//	             
					try {
//						 InputStream	is = MainActivity.getActivity().getAssets()
//						      .open(fileName+i+".png");
							
							if(!GameConfig.b_PngRead){
								 tempBitArray[i] = getFromAssetsRSP(fileName+i);
							}
							else{
								 tempBitArray[i] = getFromAssets(fileName+i+".png");
							}
//						 tempBitArray[i] = BitmapFactory.decodeStream(is,null,opt);
//						  is.close();
//						   is = null;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						LogShow.d("getNolimitImage error");
						LogShow.d(e.toString());
					}
	                 
	              
	    			// tempBitArray[i] = getFromAssets(fileName+i);//CreateZoomImage(fileName+i);//getFromAssets(fileName+i);
	    			
//	    			tempBitArray[i] = ChangeColor.TintThePicture(tempBitArray[i], new int[]{100,0,0});
	    		}
	    		
//	    		LogShow.d("AfterloadfileName ="+fileName );
//	        	showMemory();
				 if(tempBitArray!= null){
					 hMapArray.put(fileName, tempBitArray);
					 hMapArray_num.put(fileName,1);
					 LogShow.d("createImageArrayID = "+ fileName);
				 }
				return tempBitArray;
			 }
			 else{
				 int tempNum = hMapArray_num.get(fileName);
				 hMapArray_num.put(fileName,tempNum+1);
				 LogShow.d("existImageArrayID = "+ fileName);
				 return hMapArray.get(fileName);
			 }
	    
	 }
	
//	/**
//	 * 删除指定图片
//	 * @param fileName 删除的图片名字
//	 */
//	
//	 public static final void  delImage(String  fileName){
//		 if(!hMap.isEmpty()&&hMap.containsKey(fileName))
//		 hMap.remove(fileName);
//	 }
	 
	public static final void delImage(Bitmap bitmap){
		if(bitmap != null&&!hMap.isEmpty()){
			 Iterator<String> iterator = hMap.keySet().iterator();				 
			 Vector<String> vector=new Vector<String>();			 
			 while(iterator.hasNext()){
				 String key = (String)iterator.next();		
//				 LogShow.d("key="+key+" bitmap="+bitmap+"===>>"+hMap.get(key).equals(bitmap));
				 if(hMap.get(key).equals(bitmap)){
					 int tempNum=hMap_num.get(key);
					 if(tempNum>1){
						 hMap_num.put(key, tempNum-1);
					 }else{
						 if(!bitmap.isRecycled())
							 bitmap.recycle();						 
						 vector.add(key);
//						 iterator.remove();//删除key				
//						 hMap.remove(key); //删除值
						 hMap_num.remove(key);
						 LogShow.d("del hasmap key="+key);
					 }
				 }
			 }
			 
			 for(int i=0;i<vector.size();i++){
				 hMap.remove(vector.get(i)); //删除值
			 }
			 vector.clear();
			 vector=null;
		 }
	 }
	
//	/**
//	 * 删除图片数组
//	 * @param fileName 关键key
//	 */
//	
//	 public static final void  delImageArray(String  fileName){
//		 if(!hMapArray.isEmpty()&&hMapArray.containsKey(fileName))
//			 hMapArray.remove(fileName);
//	 }
    public static void delImageArray(Sprite[] sprite)
    {
        Bitmap[] bitmap = new Bitmap[sprite.length];
        for (int i = 0; i < bitmap.length; i++)
        {
            bitmap[i] = sprite[i].bitmap;
        }
        delImageArray1(bitmap);
    } 
	
	 /**
	  * 删除图片数组
	  * @param bitmap 图片数组
	  */
	public static final void delImageArray(Bitmap[] bitmap){
		 if(bitmap != null&&!hMapArray.isEmpty()){
			 Iterator<String> iterator = hMapArray.keySet().iterator();	
			 Vector<String> vector=new Vector<String>();			 
			 while(iterator.hasNext()){
				 String key = (String)iterator.next();		
//				 LogShow.d("key="+key+" bitmap="+bitmap);
				 if(hMapArray.get(key).equals(bitmap)){
					 int tempNum=hMapArray_num.get(key);
					 if(tempNum>1){
						 hMapArray_num.put(key, tempNum-1);
					 }else{
						 for(int i=0;i<bitmap.length;i++){
							 if(bitmap[i]!=null&&!bitmap[i].isRecycled())
								 bitmap[i].recycle();
						 }
						 vector.add(key);						 
//						 iterator.remove();//删除key				
//						 hMapArray.remove(key); //删除值
						 hMapArray_num.remove(key);
						 LogShow.d("del hasmapArray key="+key);
					}
				}	
			 }
			 
			 for(int i=0;i<vector.size();i++){
				 hMapArray.remove(vector.get(i)); //删除值
			 }
			 vector.clear();
			 vector=null;
		 }
	 }
	
	private static final void delImageArray1(Bitmap[] bitmap) {
		if(bitmap != null&&!hMapArray.isEmpty()){
			 Iterator<String> iterator = hMapArray.keySet().iterator();	
			 Vector<String> vector=new Vector<String>();
			 while(iterator.hasNext()){
				 String key = (String)iterator.next();		
				 if(hMapArray.get(key)[0].equals(bitmap[0])){
					 int tempNum=hMapArray_num.get(key);
					 if(tempNum>1){
						 hMapArray_num.put(key, tempNum-1);
					 }else{
						 for(int i=0;i<bitmap.length;i++){
							 if(bitmap[i]!=null&&!bitmap[i].isRecycled())
								 bitmap[i].recycle();
						 }
						 vector.add(key);
//						 iterator.remove();//删除key				
//						 hMapArray.remove(key); //删除值
						 hMapArray_num.remove(key);
						 LogShow.d("del hasmapArray key="+key);
					}
				}	
			 }
			 
			 for(int i=0;i<vector.size();i++){
				 hMapArray.remove(vector.get(i)); //删除值
			 }
			 vector.clear();
			 vector=null;
		 }
	}
	
	 /**
	  *  删除所有图片
	  * @param picId
	  */
	public static final void RemoveAllImage(){
		Vector<String> vector=new Vector<String>();		
		 if(!hMap.isEmpty()){
			Iterator<String> iterator = hMap.keySet().iterator();	
			while(iterator.hasNext()){
				String key = (String)iterator.next();
				Bitmap bitmap = hMap.get(key);
				if(!bitmap.isRecycled())
					bitmap.recycle();
				bitmap = null;
				vector.add(key);
//				iterator.remove();//删除key
//				hMap.remove(key); //删除值 
				hMap_num.remove(key);
			}
			 for(int i=0;i<vector.size();i++){
				 hMapArray.remove(vector.get(i)); //删除值
			 }
			 vector.clear();
			 hMap.clear();
			 hMap_num.clear();
			 LogShow.d("del all Image");
		 }
		 if(!hMapArray.isEmpty()){
			 Iterator<String> iterator = hMapArray.keySet().iterator();	
			 while(iterator.hasNext()){
				 String key = (String)iterator.next();		
//				 LogShow.d("key="+key+" bitmap="+bitmap);
				 Bitmap[] bitmap = hMapArray.get(key);
				 if(hMapArray.get(key).equals(bitmap)){	
					 for(int i=0;i<bitmap.length;i++){
						 if(bitmap[i]!=null&&!bitmap[i].isRecycled())
							 bitmap[i].recycle();
						 bitmap[i]=null;
					 }
					 bitmap=null;
					 vector.add(key);
//					 iterator.remove();//删除key				
//					 hMapArray.remove(key); //删除值 
					 hMapArray_num.remove(key);
					 LogShow.d("del hasmapArray key="+key);
					}	
			 }
			 for(int i=0;i<vector.size();i++){
				 hMapArray.remove(vector.get(i)); //删除值
			 }
			 vector.clear();
			 hMapArray.clear();
			 hMapArray_num.clear();
			 LogShow.d("del all ImageArray");
		 }
//		 System.gc();
	 }
	 
	 private static final Bitmap CreateZoomImage(String fileName){
		 Bitmap bitmaptemp = getFromAssets(fileName);
		 Bitmap resultBitmap = null; 
		 if(bitmaptemp != null){
//			 float temp = GameConfig.f_zoom;
//			 if(GameConfig.ORGScreen_Width > GameConfig.ORGScreen_Height){
//				 temp = GameConfig.f_zoom;
//			 }
//			 LogShow.d("GameConfig.f_zoomx="+GameConfig.f_zoomx);
//			 LogShow.d("GameConfig.f_zoomy="+GameConfig.f_zoomy);
//			 resultBitmap = zoomImage(bitmaptemp, bitmaptemp.getWidth()*temp, bitmaptemp.getHeight()*temp);
			 
			 resultBitmap = zoomImage(bitmaptemp, bitmaptemp.getWidth()*GameConfig.f_zoomx, bitmaptemp.getHeight()*GameConfig.f_zoomy);
			 
//			 if(!bitmaptemp.isRecycled())
//				 bitmaptemp.recycle();
			 
				 bitmaptemp = null;
//				 System.gc();
		 }
		 return resultBitmap;
	 }
	 
	 
//	 private static Bitmap decodeFile(String  fileName){    
//		 Bitmap b = null;      
//		 try {
//			
//			 //Decode image size          
//			 BitmapFactory.Options o = new BitmapFactory.Options();        
//			 o.inJustDecodeBounds = true;            
//			 InputStream is = Main.getActivity().getAssets().open(fileName+".png");
//			 BitmapFactory.decodeStream(is, null, o);        
//			
//			 is.close();
//		
//			 int scale = 1;        
//			 if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {     
//				 scale = (int) Math.pow(2, (int) Math.round(
//						 Math.LogShow(IMAGE_MAX_SIZE / 
//						 (double) Math.max(o.outHeight, o.outWidth)) / Math.LogShow(0.5)));   
//				 }           
//			 //Decode with inSampleSize         
//			 BitmapFactory.Options o2 = new BitmapFactory.Options();      
//			 o2.inSampleSize = scale;        
//			 is = Main.getActivity().getAssets().open(fileName+".png");
//			 b = BitmapFactory.decodeStream(is, null, o2);          
//			 is.close();     
//			 } catch (Exception e) {   
//				 	LogShow.d(fileName+" is not exist");
//			 }   
//			 	return b; 
//			 }  
		 
	 

  //从assets 文件夹中获取文件并读取数据   
    public static Bitmap getFromAssets(String fileName){   
    	Bitmap result = null;   
            try {
            	if(GameConfig.b_PngRead){
            	      InputStream is = MainActivity.getActivity().getAssets()
              	      .open(fileName+".png");
		                result = BitmapFactory.decodeStream(is);
		                is.close();
		                is = null;
//		                System.gc();
		            	LogShow.d("getFromAssets is "+fileName);
		                return result;   
            	}
            	else {
            		return getFromAssetsRSP(fileName);
            	}
                
             
            
            } catch (Exception e) {   
            	LogShow.d(fileName+" is not exist");
              	return null;
            }   
           
        
    }   
   

  


//图片缩放，对当前图片进行缩放处理

    /***

     * 图片的缩放方法
     * * @param bgimage
     *：源图片资源
     * @param newWidth
     *：缩放后宽度
     * @param newHeight
     *：缩放后高度
     * @return
     */

    public static Bitmap zoomImage(Bitmap bgimage, float newWidth, float newHeight) {
    	
    	
//    	LogShow.d("newWidth="+newWidth+" newHeight="+newHeight);
    	Bitmap	bitmap = Bitmap.createScaledBitmap(bgimage,(int) newWidth,(int) newHeight, true);
  
    
            // 获取这个图片的宽和高

//            int width = bgimage.getWidth();

//            int height = bgimage.getHeight();

         
//            // 创建操作图片用的matrix对象
//
//            Matrix matrix = new Matrix();
//
//            // 计算缩放率，新尺寸除原始尺寸
//          
//            float scaleWidth = ((float) newWidth) / width;
//
//            float scaleHeight = ((float) newHeight) / height;
//
//            // 缩放图片动作
//
//            matrix.postScale(scaleWidth, scaleHeight);
//          
//            
//            Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, width, height,
//
//                            matrix, true);

            return bitmap;

    }
    /**  
    * 获得圆角图片的方法  
    */  
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {   
      
       Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);   
       Canvas canvas = new Canvas(output);   
      
       final int color = 0xff424242;   
       final Paint paint = new Paint();   
       final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());   
       final RectF rectF = new RectF(rect);   
      
       paint.setAntiAlias(true);   
       canvas.drawARGB(0, 0, 0, 0);   
       paint.setColor(color);   
       canvas.drawRoundRect(rectF, roundPx, roundPx, paint);   
      
       paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));   
       canvas.drawBitmap(bitmap, rect, rect, paint);   
      
       return output;   
    }   
      
    /**  
    * 获得带倒影的图片方法  
    */  
    public static Bitmap createReflectionImageWithOrigin(Sprite bitmap) {   
       final int reflectionGap = 4;   
       int width = bitmap.bitmap.getWidth();   
       int height = bitmap.bitmap.getHeight();   
      
       Matrix matrix = new Matrix();   
       matrix.preScale(1, -1);   
      
       Sprite reflectionImage = new Sprite(Bitmap.createBitmap(bitmap.bitmap, 0, height / 2, width, height / 2, matrix, false));   
      
       Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);   
      
       Canvas canvas = new Canvas(bitmapWithReflection);   
       bitmap.drawBitmap(canvas,bitmap.bitmap, 0, 0, null);   
       Paint deafalutPaint = new Paint();   
       canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);   
      
       reflectionImage.drawBitmap(canvas, reflectionImage.bitmap, 0, height + reflectionGap, null);   
      
       Paint paint = new Paint();   
       LinearGradient shader = new LinearGradient(0, bitmap.bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);   
       paint.setShader(shader);   
       // Set the Transfer mode to be porter duff and destination in   
       paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));   
       // Draw a rectangle using the paint with our linear gradient   
       canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);   
       return bitmapWithReflection;   
    }   
    
    public static BitmapDrawable getBitmapDrawable(String filenameString){
    	
    	Bitmap tempbitmap = GameImage.CreateZoomImage(filenameString);
    	BitmapDrawable bDrawable =  new BitmapDrawable(tempbitmap);
		return bDrawable;
		
    }
    
   public static final byte Sort_line = 0;//按列来切割图
   public static final byte Sort_row = 1;//按行来切割图
    /**
     * 得到切割后的所有图片数组
     * @param fileName  图片名字
     * @param line    总列数
     * @param row    总行数
     * @param sort_type 切割方式
     * @return
     */
    public static final Bitmap[] getcutBitmap(String fileName,int line ,int row,byte sort_type){
    	
    	if(!hMapArray.containsKey(fileName)){
    	LogShow.d("curfileName ="+fileName );
//    	showMemory();
//		 Bitmap tempBit = decodeFile(fileName);
    		Bitmap tempBit = getFromAssets(fileName);
//    		LogShow.d("AfterloadfileName ="+fileName );
//        	showMemory();
    		Bitmap tempBitArray[] = new Bitmap[line*row];
    		
    		for(int i = 0;i<tempBitArray.length;i++){
    			Bitmap _tempbitmap = null;
    			if(sort_type == Sort_line)
    				
    				_tempbitmap = Bitmap.createBitmap(tempBit, i/row*tempBit.getWidth()/line, i%row*tempBit.getHeight()/row,
    					tempBit.getWidth()/line, tempBit.getHeight()/row);
    			if(sort_type == Sort_row)
    				_tempbitmap = Bitmap.createBitmap(tempBit, i%line*tempBit.getWidth()/line, i/line*tempBit.getHeight()/row,
        					tempBit.getWidth()/line, tempBit.getHeight()/row);
    			tempBitArray[i]= _tempbitmap;
//    			tempBitArray[i] = zoomImage(_tempbitmap ,_tempbitmap.getWidth()*GameConfig.f_zoomy, _tempbitmap.getHeight()*GameConfig.f_zoomy);
    			_tempbitmap = null;
    			
    			
    		}
			 if(tempBitArray!= null){
				 hMapArray.put(fileName, tempBitArray);
				 hMapArray_num.put(fileName,1);
//				 if(!tempBit.isRecycled())
//					 tempBit.recycle();
					 tempBit = null;
//					 System.gc();
				 
				 LogShow.d("createImageArrayID = "+ fileName);
			 }
			return tempBitArray;
		 }
		 else{
			 int tempNum = hMapArray_num.get(fileName);
			 hMapArray_num.put(fileName,tempNum+1);
			 LogShow.d("existImageArrayID = "+ fileName);
			 return hMapArray.get(fileName);
		 }
    }
    

    public static Sprite[] getAutoSizecutSprite(String fileName, int line, int row, byte sort_type)
    {
        Bitmap[] bms = getAutoSizecutBitmap(fileName, line, row, sort_type);
        Sprite[] s = new Sprite[bms.length];
        for (int i = 0; i < bms.length; i++)
        {
            s[i] = new Sprite(bms[i]);
        }
        return s;
    }
    public static final Bitmap[] getAutoSizecutBitmap(String fileName,int line ,int row,byte sort_type){
    	
    	if(!hMapArray.containsKey(fileName)){
    	LogShow.d("curfileName ="+fileName );
    		
    	
//    	showMemory();
//		 Bitmap tempBit = decodeFile(fileName);
    		Bitmap tempBit = getFromAssets(fileName);
    	
//        	showMemory();
        	LogShow.d("loadCutfileName ="+fileName +" line="+line+" row="+row);
    		Bitmap tempBitArray[] = new Bitmap[line*row];
    		
    		for(int i = 0;i<tempBitArray.length;i++){
    			Bitmap _tempbitmap = null;
    			if(sort_type == Sort_line)
    				
    				_tempbitmap = Bitmap.createBitmap(tempBit, i/row*tempBit.getWidth()/line, i%row*tempBit.getHeight()/row,
    					tempBit.getWidth()/line, tempBit.getHeight()/row);
    			if(sort_type == Sort_row)
    				_tempbitmap = Bitmap.createBitmap(tempBit, i%line*tempBit.getWidth()/line, i/line*tempBit.getHeight()/row,
        					tempBit.getWidth()/line, tempBit.getHeight()/row);
    			
    			tempBitArray[i] = zoomImage(_tempbitmap ,_tempbitmap.getWidth()*GameConfig.f_zoom, _tempbitmap.getHeight()*GameConfig.f_zoom);
    			_tempbitmap = null;
    			
    			
    		}
			 if(tempBitArray!= null){
				 hMapArray.put(fileName, tempBitArray);
				 hMapArray_num.put(fileName, 1);
//				 if(!tempBit.isRecycled())
//					 tempBit.recycle();
					 tempBit = null;
//					 System.gc();
				 
				 LogShow.d("createImageArrayID = "+ fileName);
			 }
			return tempBitArray;
		 }
		 else{
			 int tempNum = hMapArray_num.get(fileName);
			 hMapArray_num.put(fileName,tempNum+1);
			 LogShow.d("existImageArrayID = "+ fileName);
			 return hMapArray.get(fileName);
		 }
    }
    
//    public static  byte[] bitmap2Bytes(Bitmap img) {
//        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
//        try {
//        	img.compress(CompressFormat.PNG, 100, imageStream);
//        } catch (Exception e) {
//         // TODO Auto-generated catch block
//         LogShow.d(e.toString());
//        }
//        byte[] tagInfo = imageStream.toByteArray();
//
//        return tagInfo;
//       
//   }
    
    /**
     * 得到切割后的所有图片数组
     * @param fileName  图片名字
     * @param line    总列数
     * @param row    总行数
     * @param zoomx x轴缩放倍数
     * @param zoomy y轴缩放倍数
     * @param sort_type 切割方式
     * @return
     */
    public static final Bitmap[] getcutZoomBitmap(String fileName,int line ,int row,float zoomx,float zoomy,byte sort_type){
    	
    	if(!hMapArray.containsKey(fileName)){
    		
//    		Bitmap tempBit = decodeFile(fileName);
    		
    		Bitmap tempBit = getFromAssets(fileName);
    		Bitmap tempBitArray[] = new Bitmap[line*row];
    		
    		for(int i = 0;i<tempBitArray.length;i++){
    			if(sort_type == Sort_line)
    				tempBitArray[i] = Bitmap.createBitmap(tempBit, i/row*tempBit.getWidth()/line, i%row*tempBit.getHeight()/row,
    					tempBit.getWidth()/line, tempBit.getHeight()/row);
    			if(sort_type == Sort_row)
        			tempBitArray[i] = Bitmap.createBitmap(tempBit, i%line*tempBit.getWidth()/line, i/line*tempBit.getHeight()/row,
        					tempBit.getWidth()/line, tempBit.getHeight()/row);			
    			tempBitArray[i] = zoomImage(tempBitArray[i] , tempBitArray[i].getWidth()*GameConfig.f_zoom*zoomx, tempBitArray[i].getHeight()*GameConfig.f_zoom*zoomy);
    		}
			 if(tempBitArray!= null){
				 hMapArray.put(fileName, tempBitArray);
				 hMapArray_num.put(fileName,1);
//				 if(!tempBit.isRecycled())
//					 tempBit.recycle();
					 tempBit = null;
//					 System.gc();
				 
				 LogShow.d("createImageArrayID = "+ fileName);
			 }
			return tempBitArray;
		 }
		 else{
			 int tempNum = hMapArray_num.get(fileName);
			 hMapArray_num.put(fileName,tempNum+1);
			 LogShow.d("existImageArrayID = "+ fileName);
			 return hMapArray.get(fileName);
		 }
    }
    
    
    
    static final byte TransType_H = 0;//水平翻转
    static final byte TransType_V = 1;//垂直翻转
    static final byte TransType_90 = 2;//顺时针旋转90
    static final byte TransType_270 = 3;//顺时针旋转270
    /**
     * 得到特殊处理后的图
     * @param fileName 图片名字
     * @param Transtype 特殊参数，参考上方
     * @return
     */
    public static final Bitmap getTransBitmap(String fileName,byte Transtype){
    	Bitmap bitmap = getImage(fileName);
//    	Matrix matrix =new Matrix();
//    	switch(Transtype){
//	    	case TransType_H:
//	    	
//	    		matrix.setScale(-1, 1,bitmap.getWidth(),bitmap.getHeight());
//	    		break;
//	    	case TransType_V:
//	    		matrix.setScale(1, -1,bitmap.getWidth(),bitmap.getHeight());
//	    		break;
//	    	case TransType_90:
//	    		matrix.setRotate(90);
//	    		break;
//    	}
//    	bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, true);
    	return getTransBitmap(bitmap,Transtype);
    }
 public static final Bitmap getTransBitmap(Bitmap _bitmap,Matrix matrix){
    	
    	int w = _bitmap.getWidth();
    	int h = _bitmap.getHeight();
    	Bitmap bitmap = Bitmap.createBitmap(_bitmap, 0, 0,w,h,  matrix, true);
    	return bitmap;
    }
    public static final Bitmap getTransBitmap(Bitmap _bitmap,byte Transtype){
    	
    	Matrix matrix =new Matrix();
    	int w = _bitmap.getWidth();
    	int h = _bitmap.getHeight();
    	switch(Transtype){
	    	case TransType_H:
	    	
	    		matrix.setScale(-1, 1,_bitmap.getWidth(),_bitmap.getHeight());
	    		break;
	    	case TransType_V:
	    		matrix.setScale(1, -1,_bitmap.getWidth(),_bitmap.getHeight());
	    		break;
	    	case TransType_90:
	    		matrix.setRotate(90);

	    		break;
	    	case TransType_270:
	    		matrix.setRotate(270);

	    		break;
    	}
    	Bitmap bitmap = Bitmap.createBitmap(_bitmap, 0, 0,w,h,  matrix, true);
    	return bitmap;
    }
    
    public static void showMemory(){
    	if(!GameConfig.b_showMemory){
    		return;
    	}
    	 Method _readProclines = null;
 		try {
 			Class procClass;
 			procClass = Class.forName("android.os.Process");
 			Class parameterTypes[]= new Class[] {String.class, String[].class, long[].class };
 			_readProclines = procClass.getMethod("readProcLines", parameterTypes);
             Object arglist[] = new Object[3];
             final String[] mMemInfoFields = new String[] {"MemTotal:",
                     "MemFree:", "Buffers:", "Cached:"};
             long[] mMemInfoSizes = new long[mMemInfoFields.length];
             mMemInfoSizes[0] = 30;
             mMemInfoSizes[1] = -30;

             arglist[0] = new String("/proc/meminfo");
             arglist[1] = mMemInfoFields;
             arglist[2] = mMemInfoSizes;

             if(_readProclines!=null){
             	_readProclines.invoke(null, arglist);

                 for (int i=0; i<mMemInfoSizes.length; i++) {
                     LogShow.d( mMemInfoFields[i]+" : "+mMemInfoSizes[i]+" KB");
                 }
             }
 		} catch (Exception e) {
 			LogShow.d("getNolimitImage error");
 			LogShow.d(e.toString());
 		}
    }
    
    /**
     * 图片变成单一色
     * @param _bitmap
     * @param color
     * @return
     */
    public static Bitmap ChangeColor(Bitmap _bitmap,int color){
    	
    	int pixels[] = new int[_bitmap.getWidth()* _bitmap.getHeight()];
    	_bitmap.getPixels(pixels, 0, _bitmap.getWidth(), 0, 0, _bitmap.getWidth(),  _bitmap.getHeight());
    	
    	if(pixels != null)
    	for(int i = 0;i<pixels.length;i++){
    	    int alpha = (pixels[i] >>24) & 0xff;
    	    int a  = (color>>24) & 0xff;
            int r  = (color>>16) & 0xff;
            int g = (color>> 8) & 0xff;
            int b = (color) & 0xff;
            
            if(alpha != 0){
           
            	pixels[i] = ((a << 24) | (r << 16) | (g << 8) | b);
            }

    	}
    	Bitmap bitmap = Bitmap.createBitmap(pixels, _bitmap.getWidth(),  _bitmap.getHeight(), Config.ARGB_4444);
    	return bitmap;
    	
    }
	private static	int readInt(byte[]data)
	{
		int ret	= (data[index++] + 256 & 0xFF)
				+ ((data[index++] + 256 & 0xFF) << 8)
				+ ((data[index++] + 256 & 0xFF) << 16)
				+ ((data[index++] + 256 & 0xFF) << 24);
		return ret;
	}
	
	static int index	= 0;
private static byte HEAD[] = { (byte)(0x89),0x50,0x4E,0x47,0x0D,0x0A,0x1A,0x0A,0x00,0x00,0x00,0x0D,0x49,0x48,0x44,0x52 };
//从assets 文件夹中获取文件并读取数据   
private static Bitmap getFromAssetsRSP(String fileName){
	Bitmap result = null;   
	System.out.println("load:"+fileName);
	int dataSize=0;
	byte data[]	= new byte[8];
	  try {
          InputStream res_png =  MainActivity.getActivity().getAssets().open(fileName+".rsp");
          
      	if(res_png == null)
		{
//#ifdef DEBUG_MODE
			System.out.println(fileName+".rsp not found!");
//#endif
			return null;
		}
//#ifdef DEBUG_MODE
		else	System.out.println("Loading "+fileName+".rsp");
//#endif

      	
		res_png.read(data, 0, 8);
		index	= 4;

		dataSize	= 16 + readInt(data);
System.out.println("************************dataSize="+dataSize);
		data	= null;
		data	= new byte[dataSize];

		res_png.read( data,16,dataSize-16 );

		res_png.close();
		
		for( int i=16;i<dataSize;i+=8 )
			data[i]	= (byte)( ~(data[i]-0xAA) );

		for( int i=0;i<16;i++ )
			data[i]	= HEAD[i];

//		System.gc();
		result	= BitmapFactory.decodeByteArray( data,0,dataSize );
	}
	catch(IOException e){}

    return result;   
}   

	public static Bitmap CreateZoomImage(Bitmap bitmaptemp){
	 Bitmap resultBitmap = null; 
	 if(bitmaptemp != null){
		 float temp = GameConfig.f_zoom;
		 if(GameConfig.ORGScreen_Width > GameConfig.ORGScreen_Height){
			 temp = GameConfig.f_zoom;
		 }
		 resultBitmap = zoomImage(bitmaptemp, bitmaptemp.getWidth()*temp, bitmaptemp.getHeight()*temp);
//		 System.gc();
	 }
	 return resultBitmap;
}
}
