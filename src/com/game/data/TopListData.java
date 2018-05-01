package com.game.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

import com.facebook.sdk.FBInterface;
import com.facebook.sdk.FBInterface.UserChangedCallback;
import com.facebook.sdk.FBUser;
/**
 * ���а�item����
 * @author Administrator
 *
 */
public class TopListData {
	public String name; // �ǳ�,�൱�� command2�е�othername
	public int orderIndex; // ����
	public long orderScore;// �ܷ�
	public String otherId; // ��Ϣ��Դ����id����fbId
	public long uidarry; // ����id,�����Լ���id

	boolean isRequestingUser;
	UserChangedCallback userCallback;

	FBUser user; // facebook���صķ��ص�
	//String fbid; // facebook ����Ϊ�� ��ʾ��˾��������facebookid�Բ���������

	public TopListData(String _name, int _orderIndex, long _orderScore,
			String _otherId, long _uidarry) {
		name = _name; // �ǳ�,�൱�� command2�е�othername
		orderIndex = _orderIndex; // ����
		orderScore = _orderScore;// �ܷ�
		otherId = _otherId; // ��Ϣ��Դ����id����fbId
		uidarry = _uidarry; // ����id,�����Լ���id
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
	 * ����user����
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
//	 * ����fbid
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
