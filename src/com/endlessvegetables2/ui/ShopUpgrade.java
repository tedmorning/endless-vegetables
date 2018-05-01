package com.endlessvegetables2.ui;

import android.graphics.Canvas;

import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;

/***
 * 商店升级物品
 * */
public class ShopUpgrade {

	private MySprite mySprite; // 升级的图片
	private String nextName;// 下一集图片路劲
	private float[] ani_r = { 0.0f, 0.2f, 0.6f, 1.0f };
	private int index = 0;
	private float r;
	private boolean start_min;
	private boolean start_max;
	private int x, y;
	private int type;
	private int quality;
	String file;
	private boolean isOK = true; //升级动画是否完成
	/***
	 * icon
	 * */
	public ShopUpgrade(MySprite s_upgrade, String file, int _type, int _quality) {
		this.mySprite = s_upgrade;
		this.file = file;
		index = ani_r.length - 1;
		start_min = false;
		start_max = false;
		r = 1f;
		this.type = _type;
		this.quality = _quality;

	}

	/**
	 * 获取类型
	 * */
	public int getType() {
		return type;
	}

	/**
	 * 获取品质
	 * */
	public int getQuality() {
		return quality;
	}

	/**
	 * 设置品质
	 * */
	public void setQuality(int _quality) {
		quality = _quality;
	}

	public void setPoint(int _x, int _y) {
		x = _x;
		y = _y;
	}

	/**
	 * 升级成功后的nextName
	 * */
	public void setNextName(String _nextName) {
		this.nextName = _nextName;
	}

	public void paint(Canvas canvas) {
		if (r != 0) {
			mySprite.drawBitmap(canvas, file+".png", x
					+ mySprite.getImageWidth(file+".png") / 2 * (1 - r), (y )
					+ mySprite.getImageHeight(file+".png") / 2
					* (1 - r), r, r, 255, 0, 0, 0);
		}
 
	}

	/**
	 * 开始升级动画
	 * */
	public void setStatr_animation() {
		start_min = true;
		isOK = false;
	}

	/**
	 * 升级动画是否完成
	 * */
	public boolean getIsOK(){
		return isOK;
	}
	public void logic() {
		if (start_min) {
			if (GameConfig.i_coke % 2 < 1) {
				if (r != ani_r[0]) {
					
					r = ani_r[index-->=ani_r.length?ani_r.length-1:index--];
				} else if (r == ani_r[0]) {
					start_min = false; // 这里全部缩小了
					start_max = true;
					index = 0;
					file = nextName;
//					GameImage.delImage(s_upgrade.bitmap);
//					s_upgrade.bitmap = null;
//					s_upgrade = null;
//					s_upgrade = new Sprite(GameImage.getImage(nextName));
				}
			}
		} else if (start_max) {
			if (GameConfig.i_coke % 2 < 1) {
				if (GameConfig.i_coke % 2 < 1) {
					if (r != ani_r[ani_r.length - 1]) {
						r = ani_r[index++];
					} else if (r == ani_r[ani_r.length - 1]) {
						start_max = false;
						// 升级完全完成 添加品质等级
						quality += 1;
						isOK = true;
					}
				}
			}
		}
	}

	public void ReleaseResource() {

//		GameImage.delImage(s_upgrade.bitmap);
//		s_upgrade.bitmap = null;
//		s_upgrade = null;

		ani_r = null;
	}

}// end class
