package com.endlessvegetables2.ui;

import android.graphics.Canvas;

import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;

/***
 * �̵�������Ʒ
 * */
public class ShopUpgrade {

	private MySprite mySprite; // ������ͼƬ
	private String nextName;// ��һ��ͼƬ·��
	private float[] ani_r = { 0.0f, 0.2f, 0.6f, 1.0f };
	private int index = 0;
	private float r;
	private boolean start_min;
	private boolean start_max;
	private int x, y;
	private int type;
	private int quality;
	String file;
	private boolean isOK = true; //���������Ƿ����
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
	 * ��ȡ����
	 * */
	public int getType() {
		return type;
	}

	/**
	 * ��ȡƷ��
	 * */
	public int getQuality() {
		return quality;
	}

	/**
	 * ����Ʒ��
	 * */
	public void setQuality(int _quality) {
		quality = _quality;
	}

	public void setPoint(int _x, int _y) {
		x = _x;
		y = _y;
	}

	/**
	 * �����ɹ����nextName
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
	 * ��ʼ��������
	 * */
	public void setStatr_animation() {
		start_min = true;
		isOK = false;
	}

	/**
	 * ���������Ƿ����
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
					start_min = false; // ����ȫ����С��
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
						// ������ȫ��� ���Ʒ�ʵȼ�
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
