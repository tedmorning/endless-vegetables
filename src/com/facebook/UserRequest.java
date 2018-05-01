package com.facebook;

import java.util.Iterator;
import java.util.Random;

import android.graphics.Bitmap;

import com.NetConfig;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.net.Http;
import com.protocol.request.FriendHelpReq;
import com.protocol.request.TopListReq;
import com.protocol.request.UserGameLevelReq;
import com.protocol.request.UserLoginReq;
import com.protocol.request.UserMessageOperateReq;
import com.protocol.request.UserMessageReq;
import com.protocol.request.VegetableListReq;
import com.protocol.request.VegetableOperateReq;
import com.socoGameEngine.GameConfig;

/**
 * 用户的请求
 * */
public class UserRequest {

	// 好友
	public static final int FRIEND_BACK = 1;
	// 请求关卡 获取数据
	public static final int REQ_LEVEL = 2;
	// 提交关卡 提交数据
	public static final int RES_LEVEL = 1;

	public static final int REQPS = 1; //请求体力
	public static final int REQYD = 2; //同意删除
	public static final int ACCEPT = 2; //接受请求
	
	
	public static final int UPDATE_AID_TIME  = 1; //跟新援助时间
	public static final int GETOLD_AID_TIME = 2; //获取好友上次的援助时间
	public static final int GETOLD_HEART_TIME  = 3; // 获取上次爱心体力的时间
	
	 
	public static final int FRIENDHELP_TIME  = 0;
	public static final int GETHEART_TIME  = 1;
	
	
	//卡牌
	public static final byte FARM_PLANTCARD = 2;
	//金币
	public static final byte FARM_PLANTGOLD = 3;
	//爱心
	public static final byte FARM_PLANTHEART = 1;
	
	/**玩家对农场的操作*/
	public static final int PLANT = 1; //种植 
	public static final int HarvestAndSteal = 2; //收获自己的 或者偷取 
	
	Random ran;
	 
	private static UserRequest user;

	/**
	 * 登录是否成功
	 * */
	private boolean loginok;

	/**
	 * 用户登陆后
	 */
	UserLoginRes userlogic;

	/**
	 * //好友排行榜
	 */
	UserTopList toplist; 
	
	/**
	 * 农场刷新接口
	 * @return
	 */
	 FarmUpdate farm;
	 
	 /***
	  * 提供给游戏的好友助阵的icon
	  **/
	 String friendIconID;
	 
	 /**
	  * 是否有隐藏关卡
	  * */
	 boolean isHideLevel;
	 
	 /**
	  * 隐藏关卡的具体关卡
	  * */
	 int hideLevel;
	 
	public static UserRequest getUser() {
		if (user == null)
			user = new UserRequest();
		return user;
	}
	
	 
	
	/**
	 * 设置隐藏关卡
	 * isTeaching 是否是教学设置
	 * */
	public void setHideLevel(boolean isHide, boolean isTeaching){
		isHideLevel = isHide;
		if(isHideLevel){
			int level = 0;
			if(!isTeaching){
				int i_temp = 0;
				for(int i=0; i<VeggiesData.getGameGuanka().length; i++) {
					if(VeggiesData.getGameGuanka()[i] == -1){
						i_temp = i-1;
						break;
					}
				}
				if(i_temp>0)
					level = ExternalMethods.throwDice(0, i_temp);
				if(level == 0 && i_temp == 0){
					isHideLevel = false;
					level = -1;
				}
			}else
				level = 0;
			sethideLevel(level);
		}else
			sethideLevel(-1);
	}
	
	/**
	 * 返回是否有隐藏关卡
	 * */
	public boolean getHideLevel(){
		return isHideLevel;
	}
	
	/**
	 * 设置隐藏关卡
	 * */
	public void sethideLevel(int _level){
		hideLevel = _level;
	}
	
	/**
	 * 返回是否蓝精灵关卡
	 * */
	public int gethideLevel(){
		return hideLevel;
	}
	
	
	/**
	 * 获取当前选中的id null为系统头像 ""为没选择
	 *
	 * */
	public String getFriendIconID(){
		return friendIconID;
	}
	
	/**
	 * 设置头像id
	 * */
	public void setFriendIconID(String uid){
		friendIconID = uid;
	}
	 
 
	 /**
	  * 请求体力 
	  */
	public void reqPhysicalStrength(long uid){
		UserMessageOperateReq aa = UserMessageOperateReq.request(FacebookOperation.getFacebook().http,
				uid, REQPS, false);
		 
//		aa.request.hasResponse; //没返回就是false  true就返回
		
	}
	
	 /**
	 * 获取好友上次的请求时间
	 * @param uid
	 * @param opType
	 */
	public void reqFriendHelp(long uid, int opType){
		FriendHelpReq.request(FacebookOperation.getFacebook().http,
				uid, opType, false);
	}
	
	/**
	 * 请求农场蔬菜列表
	 */
	public void reqVegetableList(long uid){
		VegetableListReq.request(FacebookOperation.getFacebook().http, uid, false);
	}
	
	/**
	 * 
	 * @param uid 好友id,或者自己的id
	 * @param index 蔬菜编号
	 * @param opType 1=种植，2=收取自己的或偷朋友的
	 * @param itemId蔬菜种类ID 1=体力之心,2=卡片之运，3=金币之财
	 */
	public void reqVegetableOperate(long uid, int index, int opType, int itemId){
		VegetableOperateReq.request(FacebookOperation.getFacebook().http, uid, index, opType, itemId, true);
	}
	
	/**
	  *   回复玩家
	  *  
	  */
	public void reqBackUser(long messid){
		UserMessageOperateReq.request(FacebookOperation.getFacebook().http,
				messid, REQYD, false);
	}
	
	
	/**
	 * 
	 * @param level获取指定关卡数据
	 */
	public void ReqLeveInfo(int level, long serverid) {
		UserGameLevelReq.request(FacebookOperation.getFacebook().http,
				serverid, REQ_LEVEL, level, 0, 0, false);
	}

	/**
	 * 提交
	 * 
	 * @param level
	 *            关卡
	 * @param serverid
	 *            在公司服务器的id
	 * @param score
	 *            分数
	 * @param star
	 *            星级
	 */

	public void ReqLeveInfo(int level, long serverid, long score, int star) {
		UserGameLevelReq.request(FacebookOperation.getFacebook().http,
				serverid, RES_LEVEL, level, score, star, false);
		
	}
	
	/**
	 * 登录以后就需要获取信息
	 * */
	public void ReqMessage(){
		UserMessageReq.request(FacebookOperation.getFacebook().http, false);
	}
	
	/**
	 * 请求好友的援助时间
	 * opType
	 *  /跟新援助时间
	 *  //获取好友上次的援助时间
	 *  // 获取上次爱心体力的时间
	 * 
	 * */
	public void ReqFriendTime(long serverid, int opType){
		FriendHelpReq.request(FacebookOperation.getFacebook().http, 
				serverid, opType, false);
	}
	 
	
	/**
	 * 排行榜请求
	 */
	public void ReqTopList(){
		TopListReq.request(FacebookOperation.getFacebook().http, false);
	}
	

	/** 设置玩家登录公司服务器 */
	public void setUserLoginRes(UserLoginRes _user) {
		this.userlogic = _user;
	}

	public UserLoginRes getUserLoginRes() {
		return this.userlogic;
	}

	/** 设置玩家登录是否成功 */
	public void setLoginok(boolean isok) {
		this.loginok = isok;
	}

	public boolean getLoginok() {
		return this.loginok;
	}
	
	public void setTopListInt(UserTopList _toplist) {
		this.toplist = _toplist;
	}
	public UserTopList getTopListInt() {
		return this.toplist;
	}
	
	public void setFarmInt(FarmUpdate _farm) {
		this.farm = _farm;
	}
		
	public FarmUpdate getFarm(){
			return farm;
	}
	
	/**
	 * 返回玩家登录
	 * 
	 */
	public interface UserLoginRes {
		public void onlogicRes(long server_id);
	}

	/**
	 *用户跟自己请求信息
	 * */
	public interface UserReqTL{
		public void onResTL(long uid);
	}
	/**
	 * 排行榜
	 * @param context
	 * @return
	 */
	public interface UserTopList {
		public void onTopList();
	}
	 /**
	   * 农场刷新
	  */
	 public interface FarmUpdate {
//		 public void onUpdateFarm(FarmData data);
	 }
}// end class
