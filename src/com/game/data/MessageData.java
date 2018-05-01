package com.game.data;
/**
 * 消息的数据类
 * @author Administrator
 *
 */
public class MessageData {

	long messageid; //消息id 有可能是uid
	long fromid;
	int status; //状态1=请求体力,2=接受请求
	String s_msg;
	int type; //类型
	int reqTime; //剩下请求时间（秒）
	public MessageData(int _type, long _messageid, long _fromid,
			int _status, String _s_msg){
		messageid = _messageid;
		fromid = _fromid;
		status = _status;
		s_msg = _s_msg;
		type = _type;
		
	}
	/**
	 * 消息id 有可能是uid
	 * @return
	 */
	public long getMessageid(){
		return messageid;
	}
	/**
	 * 返回类型
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
	 * 状态1=请求体力,2=接受请求
	 * @return
	 */
	public int getStatus(){
		return status;
	}

	/**
	 * 消息内容
	 * @return
	 */
	public String getS_msg(){
		return s_msg;
	}
	
	/**
	 * 剩下的请求时间
	 * @return
	 */
	public int getReqTime(){
		return reqTime;
	}
	public void setReqTime(int time){
		  reqTime = time;
	}
	
	
}//end class
