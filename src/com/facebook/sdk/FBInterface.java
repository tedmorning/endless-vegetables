package com.facebook.sdk;

 

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;


public class FBInterface {
    private static final String           TAG                  = "hx1111";
    public static FBUser 	              user;
    public static UserChangedCallback	  userInfoChangedCallback;
//    public static SessionTracker          sessionTracker;
//    public static Session                 currentSession;
    private static String                 loginLogoutEventName = "hx2222";
    private static final String           applicationId ="564325750314983";
    private static Fragment               parentFragment;
    private static LoginButtonProperties  properties           = new LoginButtonProperties();
    //public static Session.StatusCallback  callback;
    public static HashMap<String, FBUser> friendMap = new HashMap<String, FBUser>();
    public static HashMap<String, FBUser> allUserMap = new HashMap<String, FBUser>();
    public static HashMap<String, Bitmap> allIconMap = new HashMap<String, Bitmap>();

    public static void login(Context context) {
//        sessionTracker = new SessionTracker(context, new FBSessionTrackerCallback(), null, false);
//
//        final Session openSession = sessionTracker.getOpenSession();
//
//        if (openSession != null) {
//            // If the Session is currently open, it must mean we need to log out
//        } else {
//            Session currentSession = sessionTracker.getSession();
//            if (currentSession == null || currentSession.getState().isClosed()) {
//                sessionTracker.setSession(null);
//                Session session = new Session.Builder(context).setApplicationId(applicationId).build();
//                Session.setActiveSession(session);
//                currentSession = session;
//            }
//            if (!currentSession.isOpened()) {
//                Session.OpenRequest openRequest = null;
//                if (parentFragment != null) {
//                    openRequest = new Session.OpenRequest(parentFragment);
//                } else if (context instanceof Activity) {
//                    openRequest = new Session.OpenRequest((Activity) context);
//                }
//
//                if (openRequest != null) {
//                	properties.setPublishPermissions(Arrays.asList("publish_actions"), currentSession);
//                	openRequest.setCallback(callback);
//                    openRequest.setDefaultAudience(properties.defaultAudience);
//                    openRequest.setPermissions(properties.permissions);
//                    openRequest.setLoginBehavior(properties.loginBehavior);
//
//                    if (SessionAuthorizationType.PUBLISH.equals(properties.authorizationType)) {
//                        currentSession.openForPublish(openRequest);
//                    } else {
//                        currentSession.openForRead(openRequest);
//                    }
//                }
//            }
//        }


        Bundle parameters = new Bundle();
       // parameters.putInt("logging_in", (openSession != null) ? 0 : 1);


    }
    
    public static void logout() {
//    	Session openSession = Session.getActiveSession();
//    	if(openSession != null && openSession.isOpened())
//    	{
//    		openSession.close();
////    		openSession.closeAndClearTokenInformation();
//    		currentSession = null;
//    		user = null;
//    		friendMap = new HashMap<String, FBUser>();
//    	}
    }
    
//    public static void logoutAndClearCache () {
//    	Session openSession = Session.getActiveSession();
//    	if(openSession != null && openSession.isOpened())
//    	{
////    		openSession.close();
//    		openSession.closeAndClearTokenInformation();
//    		currentSession = null;
//    		user = null;
//    		friendMap = new HashMap<String, FBUser>();
//    	}
//    }

//    public static void fetchUserInfo() {
//        final Session currentSession = sessionTracker.getOpenSession();
//        if (currentSession != null) {
////            if (currentSession != userInfoSession)
//            {
//                Request request = Request.newMeRequest(currentSession, new Request.GraphUserCallback() {
//                    @Override
//                    public void onCompleted(GraphUser me, Response response) {
//
//                        if (me == null || response.getError() != null) {
////                            handleError(response.getError().getException());
//                        	if(userInfoChangedCallback != null)
//                        		userInfoChangedCallback.onUserInfoFetched(null);
//                        }
//                        else if (currentSession == sessionTracker.getOpenSession()) {
//                        	user = new FBUser(me.getId(), me.getName());
//                            if (userInfoChangedCallback != null) {
//                                userInfoChangedCallback.onUserInfoFetched(user);
//                            }
//                        }
//                    }
//                });
//                Request.executeBatchAsync(request);
////                userInfoSession = currentSession;
//            }
//        } else {
//            user = null;
//            if (userInfoChangedCallback != null) {
//                userInfoChangedCallback.onUserInfoFetched(user);
//            }
//        }
//    }
    
    
//    public static void fetchUserById (final String uid, final UserChangedCallback userCallback){
//    	if(allUserMap.containsKey(uid)){
//    		userCallback.onUserInfoFetched(allUserMap.get(uid));
//    		return;
//    	}
//    	String queryString = "SELECT name FROM user WHERE uid = "+uid;
//    	Bundle params = new Bundle();
//    	params.putString("q", queryString);
//    	Request userRequest = new Request(Session.getActiveSession(), "/fql", params, HttpMethod.GET, new Callback() {
//			@Override
//			public void onCompleted(Response response) {
//				FBUser newUser = null;
//				GraphObject graphObject = response.getGraphObject();
//				if(graphObject != null && graphObject.getProperty("data")!=null){
//					try {
//						JSONArray dataArray = new JSONArray(graphObject.getProperty("data").toString());
//						if(dataArray.length() != 0){
//							JSONObject dataObject = dataArray.getJSONObject(0);
//							String name = dataObject.getString("name");
//							newUser = new FBUser(uid, name);
//						}	
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				}
//				
//				if(newUser != null){
//					allUserMap.put(uid, newUser);
//				}
//				userCallback.onUserInfoFetched(newUser);
//			}
//		}) ;
//    	userRequest.executeAsync();
//    }
    
//    public static void sendAppRequests(Context context, String message, final FBInterface.RequestDialogCallback callback){
//    	Bundle params = new Bundle();
//    	params.putString("message", message);
//    	sendAppRequests(context, params, callback);
//    }
    
//    public static void sendAppRequests(Context context, Bundle params, final FBInterface.RequestDialogCallback callback){
//    	WebDialog webDialog = new WebDialog.RequestsDialogBuilder(context, Session.getActiveSession(), params)
//    	.setOnCompleteListener(new OnCompleteListener() {
//			@Override
//			public void onComplete(Bundle values, FacebookException error) {
//				if(callback != null)
//					if(error != null){
//						callback.onComplete(null, error);
//					}else{
//						String requestId = values.getString("request");
//						if(requestId==null){
//							callback.onComplete(null, new FacebookOperationCanceledException());
//						}else{
//							callback.onComplete(requestId, null);
//						}
//					}
//			}
//		}).build();
//    	webDialog.show();
//    }
//    public static void publishOnFeed(Context context, Bundle params, final FBInterface.RequestDialogCallback callback){
//    	WebDialog webDialog = new WebDialog.FeedDialogBuilder(context, Session.getActiveSession(), params)
//    	.setOnCompleteListener(new OnCompleteListener() {
//			@Override
//			public void onComplete(Bundle values, FacebookException error) {
//				if(callback != null)
//					if(error != null){
//						callback.onComplete(null, error);
//					}else{
//						String postId = values.getString("post_id");
//						if(postId == null)
//							callback.onComplete(null, new FacebookOperationCanceledException());
//						else
//							callback.onComplete(postId, null);
//					}
//			}
//		}).build();
//    	webDialog.show();
//    }
    
    public interface UserChangedCallback{
    	public void onUserInfoFetched(FBUser user);
    }
    
//    public interface UserListCallback{
//    	public void onCompleted(Response response);
//    }
//    
//    public interface RequestDialogCallback{
//    	public void onComplete(String id, FacebookException exception);
//    }

    static class LoginButtonProperties {}
}
