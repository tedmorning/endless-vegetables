package com.game.data;

import java.util.HashMap;
import android.graphics.Bitmap;
import com.protocol.response.ack.UserViewAck;

/**
 * facebook��������Ϸ�����
 * @author Administrator
 *
 */
public class FaceBookPlayer {
	
	public static final long L_NULL = -100;
	public static final int I_NULL = -100;

	//����Ԯ��ʱ�䣬1=��������ʱ��
	long friendhelp_time = L_NULL;
	long getheart_time = L_NULL;
	
	private Bitmap icon;
	
	/**
	 * facebook��id
	 */
	private String id_facebook;
	
	/**
	 * ����
	 */
    private String name;
    
	/**
	 * ����ڹ�˾��������id
	 */
	private long id_server = L_NULL;
	
	/**
	 * ���÷����������Ϣ
	 */
	private ServerCallback server;
	/**
	 * ����
	 * @return
	 */
	UserReqHeart heart;
	
	/**
	 * ����
	 */
	private int orderIndex = I_NULL;

	/**
	 * �ܷ�
	 */
	private long orderScore = L_NULL;

	/**
	 * ��ǰ����߹ؿ�
	 * */
	private int maxLevel = I_NULL;
	
	/**
	 * ���������Ĺؿ��Լ�����
	 * */
	private HashMap<Integer, UserLeveInfo> lg;

	UserLevel userlevel;

	public FaceBookPlayer(String bf_id, String _name){
		id_facebook = bf_id;
		name = _name;
		lg = new HashMap<Integer, UserLeveInfo>();
	}
	
	public String getName(){
		return name;
	}
	
	public void setIcon(Bitmap _icon){
		  icon = _icon;
	}
	public Bitmap getIcon(){
		return icon;
	}
	
	public void setHeartInt(UserReqHeart _heart) {
		this.heart = _heart;
	}
	
	public UserReqHeart getHeart(){
		return heart;
	}
	
	public void setFriendHelpTime(long _time){
		friendhelp_time = _time;
	}
	/**
	 * ���غ���Ԯ����ʣ��ʱ��
	 * @return
	 */
	public long getFriendHelpTime(){
		return friendhelp_time;
	}
 
	public void setGetHeartTime(long _time){
		getheart_time = _time;
	}
	
	/**
	 * ��������������ʣ��ʱ��
	 * @return
	 */
	public long getGetHeartTime(){
		return getheart_time;
	}
	 
	
	/**
	 * �Ƿ��иùؿ�����
	 * 
	 * @param level
	 * @return
	 */
	public boolean isHave(int level) {
		if (lg.containsKey(level)) // ��
			return true;
		return false;
	}

	/**
	 * 
	 * @param level
	 *            �ؿ�
	 * @param grade
	 *            ����
	 */
	public void addLG(int level, long score, int star) {
		if (!lg.containsKey(level)) {
			lg.put(level, new UserLeveInfo(score, star));
		} else
			System.out.println("<><> �Ѿ���ȡ����ǰ�ؿ���");
	}

	public HashMap<Integer, UserLeveInfo> getLG(){
		return lg;
	}
	/**
	 * ָ���ؿ���ȡ����
	 * 
	 * @param level
	 * @return
	 */
	public long getScore(int level) {
		if (lg.containsKey(level)) {
			long score = lg.get(level).score;
			return score;
		} else
			return L_NULL;
	}

	/**
	 * ָ���ؿ��Ǽ�
	 * 
	 * @param level
	 * @return
	 */
	public int getStar(int level) {
		if (lg.containsKey(level)) {
			int score = lg.get(level).star;
			return score;
		} else
			return I_NULL;
	}
	
	
	/**
	 * ��˾������id
	 * */
	public void setid_server(long _id) {
		this.id_server = _id;

	}

	public long getid_server() {
		return this.id_server;
	}
	
	public String getId_facebook() {
		return this.id_facebook;
	}
	
	/**
	 * �����ܷ�
	 * */
	public void setOrderScore(long _orderScore) {
		orderScore = _orderScore;
	}
	
	/**
	 * ��ȡ�ܷ�
	 * */
	public long getOrderScore() {
		return orderScore;
	}
	/**
	 * ��ߵĹؿ�
	 * */
	public void setMaxLevel(int _level){
		maxLevel = _level;
	}
	/**
	 * ��ȡ��߹ؿ�
	 * */
	public int getMaxLevel() {
		return maxLevel;
	}
	
	

	/**
	 * ��������
	 * */
	public void setOrderIndex(int _orderIndex) {
		orderIndex = _orderIndex;
	}

	/**
	 * ��ȡ����
	 * */
	public long getOrderIndex() {
		return orderIndex;
	}

	
	/**
	 * ���������緵�صĽӿ�
	 * 
	 * @param _level
	 */
	public void setUserlevel(UserLevel _level) {
		userlevel = _level;
	}
	
	public UserLevel getUserlevel() {
		return userlevel;
	}

	
	
	/**
	 * ��˾������
	 * */
	public void setServer(ServerCallback server) {
		this.server = server;

	}
	
	
	public ServerCallback getServer() {
		return this.server;
	}
	
	/**
	 * �����û��ؿ�����
	 * 
	 */
	public interface UserLevel {
		public void onLevelRes(FaceBookPlayer player, int level, long score, int star);
	}
	
	
	/**
	 * �������ĺ���
	 * @author Administrator
	 *
	 */
	public interface ServerCallback {
		public void onComplete(String fb_id, UserViewAck userViewAck,
				Exception exception);
	}
	
	/**
	 * ���ĵ���ʱ��ˢ��
	 */
	public interface UserReqHeart {
		public void onHeart(FaceBookPlayer user);
	}
	
	
	public class UserLeveInfo{
		public long score; //����
		public int star; //�Ǽ�����
		public UserLeveInfo(long _score, int _star){
			score = _score;
			star = _star;
		}
	}
	
	public void delete(){
		lg.clear();
		setHeartInt(null);
		setUserlevel(null); 
		setServer(null);
	}
	
}//end class
