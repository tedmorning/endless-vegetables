package com.game.data;
/**
 * ��Ϣ��������
 * @author Administrator
 *
 */
public class MessageData {

	long messageid; //��Ϣid �п�����uid
	long fromid;
	int status; //״̬1=��������,2=��������
	String s_msg;
	int type; //����
	int reqTime; //ʣ������ʱ�䣨�룩
	public MessageData(int _type, long _messageid, long _fromid,
			int _status, String _s_msg){
		messageid = _messageid;
		fromid = _fromid;
		status = _status;
		s_msg = _s_msg;
		type = _type;
		
	}
	/**
	 * ��Ϣid �п�����uid
	 * @return
	 */
	public long getMessageid(){
		return messageid;
	}
	/**
	 * ��������
	 * @return
	 */
	public int getType(){
		return type;
	}
	/**
	 * facebookid 
	 * @return
	 */
	public long getFromid(){
		return fromid;
	}

	/**
	 * ״̬1=��������,2=��������
	 * @return
	 */
	public int getStatus(){
		return status;
	}

	/**
	 * ��Ϣ����
	 * @return
	 */
	public String getS_msg(){
		return s_msg;
	}
	
	/**
	 * ʣ�µ�����ʱ��
	 * @return
	 */
	public int getReqTime(){
		return reqTime;
	}
	public void setReqTime(int time){
		  reqTime = time;
	}
	
	
}//end class
