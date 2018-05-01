package com.game.item;
 
 
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 路径
 * */
public class PropPath {

	/**
	 * time整个飞行过程时间 start 起点 end 重点
	 * */
	private int time, start_x, start_y, end_x, end_y;

	private long dx;

	private long dy; // dx 为精灵在 x 方向的移动�?度，dy 为精灵在 y 方向的移动�?度，

	/** 距离 */
	private long distance;

	/** 速度 */
	private long speed;

	 
	private static Bitmap bit;
	public boolean isRun = false;
 
    public byte type;
	/**
	 * 初始化
	 * */
	public PropPath(byte type, Bitmap bit, int time, int start_x, int start_y, int end_x, int end_y) {
		this.type = type;
		this.time = time;
		this.start_x = start_x;
		this.start_y = start_y;
		this.end_x = end_x;
		this.end_y = end_y;
		this.bit = bit;
	 
		long temp = (end_x - start_x) * (end_x - start_x) + (end_y - start_y)
				* (end_y - start_y);
		distance = sqrt(temp);
		speed = (distance / (time));
		if (speed == 0) {
			speed = 1;
		}
		dx = (end_x - start_x) / speed;
		dy = (end_y - start_y) / speed;

 
	}
 
	/**
	 * 是否到达终点 终点有可能改
	 * */
	public boolean isEndPoint(int end_x, int end_y) {
		this.end_x = end_x;
		this.end_y = end_y;

		long temp = (end_x - start_x) * (end_x - start_x) + (end_y - start_y)
				* (end_y - start_y);

		distance = sqrt(temp);

		speed = (int) (distance / (time));
		if (speed == 0) {
			speed = 1;
		}
		dx = (end_x - start_x) / speed;
		dy = (end_y - start_y) / speed;

		if (speed >= 0) {
			start_x += dx;
			start_y += dy;
		}

		if (start_x == end_x && start_y == end_y)
			return true;
		else
			return false;
	}
	/**
	 * 是否到达终点 终点有可能改
	 * */
	public boolean isEndPoint() {
		long temp = (end_x - start_x) * (end_x - start_x) + (end_y - start_y)
				* (end_y - start_y);

		distance = sqrt(temp);

		speed = (int) (distance / (time));
		if (speed == 0) {
			speed = 1;
		}
		dx = (end_x - start_x) / speed;
		dy = (end_y - start_y) / speed;

		if (speed >= 0) {
			start_x += dx;
			start_y += dy;
		}

		if (start_x == end_x && start_y == end_y)
			return true;
		else
			return false;
	}
	public static long sqrt(long temp) {
		int i, j, k;
		i = 1;

		while (i * i < temp) {
			i *= 3;
		}

		j = i / 3;

		while (i - j > 1) {
			k = (i + j) / 2;

			if (k * k > temp) {
				i = k;
			} else {
				j = k;
			}
		}

		return i - 1; // //need sub 1
	}


	/**绘制 */
	public void draw(Canvas g) {
		g.drawBitmap(bit,  start_x, start_y, null);
	}

}// end class
