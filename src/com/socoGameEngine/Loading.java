package com.socoGameEngine;

public abstract class Loading extends Module{
	public static final byte WAIT = 0;//loading 等待中
	public static final byte START = 1;//loading 开始后动画
	public static final byte LOADING = 2;////loading 加载中
	public static final byte DATALOADINGOVER = 3;////loading 游戏资料loading完毕
	public static final byte LOADINGOVER = 4;//loading 动画结束
	public byte B_state;
	public static byte Time;//loading时间
	/**
	 * 得到loading状态 有Loading.start，Loading.loading，Loading.over
	 * @return
	 */
	public abstract byte getLoadingState();
	/**
	 * loading资源释放
	 * @return
	 */
	public abstract byte release();
}
