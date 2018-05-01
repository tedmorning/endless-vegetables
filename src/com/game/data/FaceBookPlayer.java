package com.game.data;

import java.util.HashMap;
import android.graphics.Bitmap;
import com.protocol.response.ack.UserViewAck;

/**
 * facebook在我们游戏的玩家
 * @author Administrator
 *
 */
public class FaceBookPlayer {
	
	public static final long L_NULL = -100;
	public static final int I_NULL = -100;

	//好友援助时间，1=请求体力时间
	long friendhelp_time = L_NULL;
	long getheart_time = L_NULL;
	
	private Bitmap icon;
	
	/**
	 * facebook的id
	 */
	private String id_facebook;
	
	/**
	 * 名称
	 */
    private String name;
    
	/**
	 * 玩家在公司服务器的id
	 */
	private long id_server = L_NULL;
	
	/**
	 * 设置服务器玩家信息
	 */
	private ServerCallback server;
	/**
	 * 爱心
	 * @return
	 */
	UserReqHeart heart;
	
	/**
	 * 排名
	 */
	private int orderIndex = I_NULL;

	/**
	 * 总分
	 */
	private long orderScore = L_NULL;

	/**
	 * 当前的最高关卡
	 * */
	private int maxLevel = I_NULL;
	
	/**
	 * 玩家请求过的关卡以及分数
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
	 * 返回好友援助的剩余时间
	 * @return
	 */
	public long getFriendHelpTime(){
		return friendhelp_time;
	}
 
	public void setGetHeartTime(long _time){
		getheart_time = _time;
	}
	
	/**
	 * 返回请求体力的剩余时间
	 * @return
	 */
	public long getGetHeartTime(){
		return getheart_time;
	}
	 
	
	/**
	 * 是否有该关卡数据
	 * 
	 * @param level
	 * @return
	 */
	public boolean isHave(int level) {
		if (lg.containsKey(level)) // 有
			return true;
		return false;
	}

	/**
	 * 
	 * @param level
	 *            关卡
	 * @param grade
	 *            分数
	 */
	public void addLG(int level, long score, int star) {
		if (!lg.containsKey(level)) {
			lg.put(level, new UserLeveInfo(score, star));
		} else
			System.out.println("<><> 已经获取过当前关卡了");
	}

	public HashMap<Integer, UserLeveInfo> getLG(){
		return lg;
	}
	/**
	 * 指定关卡获取分数
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
	 * 指定关卡星级
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
	 * 公司服务器id
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
	 * 设置总分
	 * */
	public void setOrderScore(long _orderScore) {
		orderScore = _orderScore;
	}
	
	/**
	 * 获取总分
	 * */
	public long getOrderScore() {
		return orderScore;
	}
	/**
	 * 最高的关卡
	 * */
	public void setMaxLevel(int _level){
		maxLevel = _level;
	}
	/**
	 * 获取最高关卡
	 * */
	public int getMaxLevel() {
		return maxLevel;
	}
	
	

	/**
	 * 设置排名
	 * */
	public void setOrderIndex(int _orderIndex) {
		orderIndex = _orderIndex;
	}

	/**
	 * 获取排名
	 * */
	public long getOrderIndex() {
		return orderIndex;
	}

	
	/**
	 * 设置在网络返回的接口
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
	 * 公司服务器
	 * */
	public void setServer(ServerCallback server) {
		this.server = server;

	}
	
	
	public ServerCallback getServer() {
		return this.server;
	}
	
	/**
	 * 返回用户关卡数据
	 * 
	 */
	public interface UserLevel {
		public void onLevelRes(FaceBookPlayer player, int level, long score, int star);
	}
	
	
	/**
	 * 服务器的好友
	 * @author Administrator
	 *
	 */
	public interface ServerCallback {
		public void onComplete(String fb_id, UserViewAck userViewAck,
				Exception exception);
	}
	
	/**
	 * 爱心倒计时的刷新
	 */
	public interface UserReqHeart {
		public void onHeart(FaceBookPlayer user);
	}
	
	
	public class UserLeveInfo{
		public long score; //分数
		public int star; //星级评分
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
