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
 * �û�������
 * */
public class UserRequest {

	// ����
	public static final int FRIEND_BACK = 1;
	// ����ؿ� ��ȡ����
	public static final int REQ_LEVEL = 2;
	// �ύ�ؿ� �ύ����
	public static final int RES_LEVEL = 1;

	public static final int REQPS = 1; //��������
	public static final int REQYD = 2; //ͬ��ɾ��
	public static final int ACCEPT = 2; //��������
	
	
	public static final int UPDATE_AID_TIME  = 1; //����Ԯ��ʱ��
	public static final int GETOLD_AID_TIME = 2; //��ȡ�����ϴε�Ԯ��ʱ��
	public static final int GETOLD_HEART_TIME  = 3; // ��ȡ�ϴΰ���������ʱ��
	
	 
	public static final int FRIENDHELP_TIME  = 0;
	public static final int GETHEART_TIME  = 1;
	
	
	//����
	public static final byte FARM_PLANTCARD = 2;
	//���
	public static final byte FARM_PLANTGOLD = 3;
	//����
	public static final byte FARM_PLANTHEART = 1;
	
	/**��Ҷ�ũ���Ĳ���*/
	public static final int PLANT = 1; //��ֲ 
	public static final int HarvestAndSteal = 2; //�ջ��Լ��� ����͵ȡ 
	
	Random ran;
	 
	private static UserRequest user;

	/**
	 * ��¼�Ƿ�ɹ�
	 * */
	private boolean loginok;

	/**
	 * �û���½��
	 */
	UserLoginRes userlogic;

	/**
	 * //�������а�
	 */
	UserTopList toplist; 
	
	/**
	 * ũ��ˢ�½ӿ�
	 * @return
	 */
	 FarmUpdate farm;
	 
	 /***
	  * �ṩ����Ϸ�ĺ��������icon
	  **/
	 String friendIconID;
	 
	 /**
	  * �Ƿ������عؿ�
	  * */
	 boolean isHideLevel;
	 
	 /**
	  * ���عؿ��ľ���ؿ�
	  * */
	 int hideLevel;
	 
	public static UserRequest getUser() {
		if (user == null)
			user = new UserRequest();
		return user;
	}
	
	 
	
	/**
	 * �������عؿ�
	 * isTeaching �Ƿ��ǽ�ѧ����
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
	 * �����Ƿ������عؿ�
	 * */
	public boolean getHideLevel(){
		return isHideLevel;
	}
	
	/**
	 * �������عؿ�
	 * */
	public void sethideLevel(int _level){
		hideLevel = _level;
	}
	
	/**
	 * �����Ƿ�������ؿ�
	 * */
	public int gethideLevel(){
		return hideLevel;
	}
	
	
	/**
	 * ��ȡ��ǰѡ�е�id nullΪϵͳͷ�� ""Ϊûѡ��
	 *
	 * */
	public String getFriendIconID(){
		return friendIconID;
	}
	
	/**
	 * ����ͷ��id
	 * */
	public void setFriendIconID(String uid){
		friendIconID = uid;
	}
	 
 
	 /**
	  * �������� 
	  */
	public void reqPhysicalStrength(long uid){
		UserMessageOperateReq aa = UserMessageOperateReq.request(FacebookOperation.getFacebook().http,
				uid, REQPS, false);
		 
//		aa.request.hasResponse; //û���ؾ���false  true�ͷ���
		
	}
	
	 /**
	 * ��ȡ�����ϴε�����ʱ��
	 * @param uid
	 * @param opType
	 */
	public void reqFriendHelp(long uid, int opType){
		FriendHelpReq.request(FacebookOperation.getFacebook().http,
				uid, opType, false);
	}
	
	/**
	 * ����ũ���߲��б�
	 */
	public void reqVegetableList(long uid){
		VegetableListReq.request(FacebookOperation.getFacebook().http, uid, false);
	}
	
	/**
	 * 
	 * @param uid ����id,�����Լ���id
	 * @param index �߲˱��
	 * @param opType 1=��ֲ��2=��ȡ�Լ��Ļ�͵���ѵ�
	 * @param itemId�߲�����ID 1=����֮��,2=��Ƭ֮�ˣ�3=���֮��
	 */
	public void reqVegetableOperate(long uid, int index, int opType, int itemId){
		VegetableOperateReq.request(FacebookOperation.getFacebook().http, uid, index, opType, itemId, true);
	}
	
	/**
	  *   �ظ����
	  *  
	  */
	public void reqBackUser(long messid){
		UserMessageOperateReq.request(FacebookOperation.getFacebook().http,
				messid, REQYD, false);
	}
	
	
	/**
	 * 
	 * @param level��ȡָ���ؿ�����
	 */
	public void ReqLeveInfo(int level, long serverid) {
		UserGameLevelReq.request(FacebookOperation.getFacebook().http,
				serverid, REQ_LEVEL, level, 0, 0, false);
	}

	/**
	 * �ύ
	 * 
	 * @param level
	 *            �ؿ�
	 * @param serverid
	 *            �ڹ�˾��������id
	 * @param score
	 *            ����
	 * @param star
	 *            �Ǽ�
	 */

	public void ReqLeveInfo(int level, long serverid, long score, int star) {
		UserGameLevelReq.request(FacebookOperation.getFacebook().http,
				serverid, RES_LEVEL, level, score, star, false);
		
	}
	
	/**
	 * ��¼�Ժ����Ҫ��ȡ��Ϣ
	 * */
	public void ReqMessage(){
		UserMessageReq.request(FacebookOperation.getFacebook().http, false);
	}
	
	/**
	 * ������ѵ�Ԯ��ʱ��
	 * opType
	 *  /����Ԯ��ʱ��
	 *  //��ȡ�����ϴε�Ԯ��ʱ��
	 *  // ��ȡ�ϴΰ���������ʱ��
	 * 
	 * */
	public void ReqFriendTime(long serverid, int opType){
		FriendHelpReq.request(FacebookOperation.getFacebook().http, 
				serverid, opType, false);
	}
	 
	
	/**
	 * ���а�����
	 */
	public void ReqTopList(){
		TopListReq.request(FacebookOperation.getFacebook().http, false);
	}
	

	/** ������ҵ�¼��˾������ */
	public void setUserLoginRes(UserLoginRes _user) {
		this.userlogic = _user;
	}

	public UserLoginRes getUserLoginRes() {
		return this.userlogic;
	}

	/** ������ҵ�¼�Ƿ�ɹ� */
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
	 * ������ҵ�¼
	 * 
	 */
	public interface UserLoginRes {
		public void onlogicRes(long server_id);
	}

	/**
	 *�û����Լ�������Ϣ
	 * */
	public interface UserReqTL{
		public void onResTL(long uid);
	}
	/**
	 * ���а�
	 * @param context
	 * @return
	 */
	public interface UserTopList {
		public void onTopList();
	}
	 /**
	   * ũ��ˢ��
	  */
	 public interface FarmUpdate {
//		 public void onUpdateFarm(FarmData data);
	 }
}// end class
