package com.socoGameEngine;

public abstract class Loading extends Module{
	public static final byte WAIT = 0;//loading �ȴ���
	public static final byte START = 1;//loading ��ʼ�󶯻�
	public static final byte LOADING = 2;////loading ������
	public static final byte DATALOADINGOVER = 3;////loading ��Ϸ����loading���
	public static final byte LOADINGOVER = 4;//loading ��������
	public byte B_state;
	public static byte Time;//loadingʱ��
	/**
	 * �õ�loading״̬ ��Loading.start��Loading.loading��Loading.over
	 * @return
	 */
	public abstract byte getLoadingState();
	/**
	 * loading��Դ�ͷ�
	 * @return
	 */
	public abstract byte release();
}
