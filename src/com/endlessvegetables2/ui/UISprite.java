package com.endlessvegetables2.ui;

import java.util.ArrayList;

import com.kokatlaruruxi.wy.Sprite;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameLibrary;
import com.socogame.coolEdit.CoolEditData;
import com.socogame.coolEdit.CoolEditDefine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;

public class UISprite extends Sprite{
	public static final int RAINBOW_STATE_NOMAL = 101;	//��ͨ����״̬
	public static final int RAINBOW_STATE_WINK = 102;		//գ�۴���״̬
	public static final int RAINBOW_STATE_TERRIFIED = 103;//գ�۴���״̬
	public static final int RAINBOW_STATE_SMILE = 104;	//΢Ц����״̬
	
	public static final int TOMATO_STATE_NONE = 105; 		//������״̬
	public static final int TOMATO_STATE_NOMAL = 106; 	//�������Ŵ���״̬
	public static final int TOMATO_STATE_ROLL = 107; 		//���ѷ���״̬
	
	public void updataSprite(){
			
		if(state>0&&kind>-1){
			frames++;						
		
			if(frames>=CoolEditData.npcItem0[kind][actionName].length*framesjiange){
				frames=0;
				if(kind == CoolEditDefine.NPC_RAINBOW){
					switch(state) {
					case UISprite.RAINBOW_STATE_NOMAL:
						break;
					case UISprite.RAINBOW_STATE_WINK:
						changeAction(0);
						state = UISprite.RAINBOW_STATE_NOMAL;
						break;
					}
				}
				else if(kind == CoolEditDefine.GAMEOVER_BOX)
				{
					if(actionName == 1)
					{
						changeAction(0);
					}
				}
			}
		}
	}

}
