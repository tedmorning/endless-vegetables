package com.socoGameEngine;


/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.LogShow;

import android.graphics.*;

public class ColorChange {
    

 
//    private ColorMatrix mCM = new ColorMatrix();
//    private float mSaturation;
//    private float mAngle;
    
  
    /**
     *  设置颜色修正 取值范围0 -255
     *  dr 红色
     *	dg 绿色
     * db 蓝色
     * da  alpha通道
     */
    public static  void setTranslate(ColorMatrix cm, float dr, float dg,
                                     float db, float da) {
    	cm.set(new float[] {
               1, 0, 0, 0, dr,
               0, 1, 0, 0, dg,
               0, 0, 1, 0, db,
               0, 0, 0, 1, da });
    }
    /**
     * 设置对比度
     * @param cm
     * @param contrast  -180，180
     */
    public static   void setContrast(ColorMatrix cm,  float contrast) {
    
         if (contrast > 180) {
        	 contrast = 180;
         }
         if (contrast < -180) {
        	 contrast = -180;
         }
    	  contrast = contrast / 180.f;
        float scale = contrast + 1.f;
           float translate = (-.5f * scale + .5f) * 255.f;
           cm.set(new float[] {
               scale, 0, 0, 0, translate,
               0, scale, 0, 0, translate,
               0, 0, scale, 0, translate,
               0, 0, 0, 1, 0 });
    }
    
    public static    void setContrastTranslateOnly( ColorMatrix cm, float contrast) {
    	   if (contrast > 180) {
          	 contrast = 180;
           }
           if (contrast < -180) {
          	 contrast = -180;
           }
    	  contrast = contrast / 180.f;
        float scale = contrast + 1.f;
          float translate = (-.5f * scale + .5f) * 255.f;
           cm.set(new float[] {
               1, 0, 0, 0, translate,
               0, 1, 0, 0, translate,
               0, 0, 1, 0, translate,
               0, 0, 0, 1, 0 });
    }
    
    public static  void setContrastScaleOnly( ColorMatrix cm, float contrast) {
    	   if (contrast > 180) {
          	 contrast = 180;
           }
           if (contrast < -180) {
          	 contrast = -180;
           }
    	  contrast = contrast / 180.f;
        float scale = contrast + 1.f;
    
         cm.set(new float[] {
               scale, 0, 0, 0, 0,
               0, scale, 0, 0, 0,
               0, 0, scale, 0, 0,
               0, 0, 0, 1, 0 });
    }
    /**
     * 图片变色处理ColorMatrix原理
     * @param canvas  画笔
     * @param mBitmap 图片资源
     * @param x  图片卯点坐标x
     * @param y  图片锚点坐标y
     * @param cm colorMatrix
     * @param paint Paint画笔
     */
    public static void DrawBitmap(Canvas canvas,Sprite mBitmap,float x, float y,int anchor,ColorMatrix cm, Paint paint) {
      
    	if(paint == null){
    		LogShow.d("ColorMatrix paint can't be null");
    		return ;
    	}
//        mAngle += 2;
//        if (mAngle > 180) {
//            mAngle = 0;
//        }
        
        //convert our animated angle [-180...180] to a contrast value of [-1..1]
//        float contrast = mAngle / 180.f;
        
//        setContrast(cm, contrast);
    	
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        Matrix matrix = new Matrix();
        mBitmap.drawBitmap(canvas, mBitmap.bitmap, x, y, matrix,anchor, paint);
      
    }

}

