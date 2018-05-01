package com.game.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

import com.facebook.sdk.FBInterface;
import com.facebook.sdk.FBInterface.UserChangedCallback;
import com.facebook.sdk.FBUser;
/**
 * 排行榜item数据
 * @author Administrator
 *
 */
public class TopListData {
	public String name; // 昵称,相当于 command2中的othername
	public int orderIndex; // 排名
	public long orderScore;// 总分
	public String otherId; // 消息来源其他id，如fbId
	public long uidarry; // 好友id,或者自己的id

	boolean isRequestingUser;
	UserChangedCallback userCallback;

	FBUser user; // facebook返回的返回的
	//String fbid; // facebook 返回为空 表示公司服务器和facebookid对不上有问题

	public TopListData(String _name, int _orderIndex, long _orderScore,
			String _otherId, long _uidarry) {
		name = _name; // 昵称,相当于 command2中的othername
		orderIndex = _orderIndex; // 排名
		orderScore = _orderScore;// 总分
		otherId = _otherId; // 消息来源其他id，如fbId
		uidarry = _uidarry; // 好友id,或者自己的id
		isRequestingUser = false;
	}

	public void getUser(UserChangedCallback userCallback) {
		if (FBInterface.allUserMap.containsKey(otherId)) {
			userCallback.onUserInfoFetched(FBInterface.allUserMap.get(otherId));
			return;
		} else if (!isRequestingUser) {
			this.userCallback = userCallback;
//			isRequestingUser();
			isRequestingUser = true;
			//FBInterface.fetchUserById(otherId, userCallback);
		} else {
			userCallback.onUserInfoFetched(FBInterface.allUserMap.get(otherId));
		}
	}

//	void isRequestingUser() {
//		isRequestingUser = true;
//		String queryString = "SELECT name FROM user WHERE uid = " + otherId;
//		Bundle params = new Bundle();
//		params.putString("q", queryString);
//		Request userRequest = new Request(Session.getActiveSession(), "/fql",
//				params, HttpMethod.GET, new Callback() {
//					@Override
//					public void onCompleted(Response response) {
//						FBUser newUser = null;
//						GraphObject graphObject = response.getGraphObject();
//						if (graphObject != null
//								&& graphObject.getProperty("data") != null) {
//							try {
//								JSONArray dataArray = new JSONArray(graphObject
//										.getProperty("data").toString());
//								if (dataArray.length() != 0) {
//									JSONObject dataObject = dataArray
//											.getJSONObject(0);
//									String name = dataObject.getString("name");
//									newUser = new FBUser(otherId, name);
//								}
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
//						}
//
//						if (newUser != null) {
//							FBInterface.allUserMap.put(otherId, newUser);
//						}
//						userCallback.onUserInfoFetched(newUser, otherId);
//						isRequestingUser = false;
//					}
//				});
//		userRequest.executeAsync();
//		// Request.executeBatchAsync(userRequest);
//	}

	/**
	 * 设置user对象
	 * 
	 * @param _user
	 */
	public void setFaceBookUser(FBUser _user) {
		user = _user;
	}

	public FBUser getFaceBookUser() {
		return user;
	}
	public void delete(){
		setFaceBookUser(null);
		userCallback = null;
	}

//	/**
//	 * 设置fbid
//	 * 
//	 * @param _fbid
//	 */
//	public void setFbid(String _fbid) {
//		fbid = _fbid;
//	}
//
//	public String getFbid() {
//		return fbid;
//	}

}// end class
