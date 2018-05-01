package com.test;

import java.util.Iterator;
import java.util.Vector;

import com.endlessvegetables2.ui.ChooseLevelModule2;
import com.endlessvegetables2.ui.GameEquipmentModule;
import com.endlessvegetables2.ui.MessageModule;
import com.endlessvegetables2.ui.RankingSuccessModule;
import com.facebook.FacebookOperation;
import com.facebook.UserRequest;
import com.game.data.FaceBookPlayer;
import com.game.data.MessageData;
import com.game.data.TopListData;
import com.net.Http;
import com.net.Response;
import com.protocol.ProtocolDefine;
import com.protocol.ResponseListener;
import com.protocol.response.ack.FriendHelpAck;
import com.protocol.response.ack.TopListAck;
import com.protocol.response.ack.UserGameLevelAck;
import com.protocol.response.ack.UserLoginAck;
import com.protocol.response.ack.UserMessageAck;
import com.protocol.response.ack.UserMessageOperateAck;
import com.protocol.response.ack.UserRegisterAck;
import com.protocol.response.ack.UserViewAck;
import com.protocol.response.ack.VegetableListAck;
import com.protocol.response.ack.VegetableOperateAck;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

/**
 * @author 
 * @version 创建时间：2012-12-4 下午5:51:14
 * 
 */

public class ResponseHandler extends ResponseListener implements Runnable {

    private Http http;
    private boolean active = false;

    public void setHttp(Http http) {
        this.http = http;
        if(!active){
	     // 开辟新线程解析协议 或者放入游戏主线程解析
        	Thread t= new Thread(this);
        	t.setName("ResponseHandler");
        	t.start();
			active = true;
        }
    }
    
    public void close()
    {
    	active = false;
    }
    

    public void handle(Response resp) {
        // 错误提示处理
        if (resp.getProtocolID() < 0) {
            String error = resp.getErrorMessage();
            switch (resp.getProtocolID()) {
            //常用报错提示信息，弹出确认框。
                case ProtocolDefine.COMMOM_MESSAGE:
                    System.out.println("cmd=" + resp.getProtocolID() + " :" + error);
                    ChooseLevelModule2.sendMessage("cmd=" + resp.getProtocolID() + " :" + error);
                    break;
                //目标头顶提示信息，直接界面滚动显示
                case ProtocolDefine.HEAD_MESSAGE:
                    System.out.println("cmd=" + resp.getProtocolID() + " :" + error);
                    ChooseLevelModule2.sendMessage("cmd=" + resp.getProtocolID() + " :" + error);
                    break;
                //提示网络断开信息，弹出确认框，标识网络断开，程序处于离线状态运行
                case ProtocolDefine.NET_ERROR:
                	UserRequest.getUser().setLoginok(false);
                	FacebookOperation.isOpenNet = true;
					FacebookOperation.getFacebook().cancel(); //连接失败设置为未登录
                    System.out.println("cmd=" + resp.getProtocolID() + " :" + error);
                    ChooseLevelModule2.sendMessage("cmd=" + resp.getProtocolID() + " :" + error);
                    break;
            }
        } else {
        	UserRequest.getUser().setLoginok(true);
            //协议逻辑处理
            switch (resp.getProtocolID()) {
                case ProtocolDefine.P1_UserLogin:
                    UserLoginAck userLoginAck = new UserLoginAck(resp);
                    //当前用户公司服务器id
                    FacebookOperation.player.setid_server(userLoginAck.getUid());
                    http.s_sessionId = userLoginAck.getSid();
					UserRequest.getUser().setLoginok(true);
					UserRequest.getUser().ReqMessage(); //获取消息
                    if( UserRequest.getUser().getUserLoginRes()!=null)
                    	UserRequest.getUser().getUserLoginRes().onlogicRes(userLoginAck.getUid());
                    break;
                case ProtocolDefine.P2_UserRegister:
                    UserRegisterAck userRegisterAck = new UserRegisterAck(resp);
                    break;
                case ProtocolDefine.P10_UserView:
                    UserViewAck userViewAck = new UserViewAck(resp);
                    //设置好友在服务器的id
                     FaceBookPlayer fbuser = FacebookOperation.playerMap.get(userViewAck.getOtherId());
                     if(fbuser!=null && fbuser.getServer()!=null)
                    	 fbuser.getServer().onComplete(fbuser.getId_facebook(), userViewAck, null);
                     
                     if(FacebookOperation.player!=null && FacebookOperation.player.getServer()!=null && FacebookOperation.player.getId_facebook().equals(userViewAck.getOtherId())  ){
                    	 FacebookOperation.player.getServer().onComplete(FacebookOperation.player.getId_facebook(), userViewAck, null);
                     }
                     
                    break;
                case ProtocolDefine.P12_UserMessage:
                    UserMessageAck userMessageAck = new UserMessageAck(resp);
                    long l_mid[] = userMessageAck.getMIdArry(); 
                    long l_fromId[] = userMessageAck.getFromIdArry();
                    int type[] = userMessageAck.getTypeArry(); //1=请求体力,2=接受请求
                    String s_msg[] = userMessageAck.getMsgArry();
                    int i_status[] = userMessageAck.getStatusArry();
                    
                    String temp = LangUtil.getLangString(LangDefineClient.REQ_MSGTEXT);
                    String temp1 = LangUtil.getLangString(LangDefineClient.GET_MSGTEXT);
                    if(type!=null)
		                for(int i=0;i<type.length;++i){
		                	if(type[i]== UserRequest.REQPS){ //请求体力
		                		if(i_status[i] == 1){ //显示
		                			//这里有可能是刷新
		                			if(MessageModule.message.containsKey(""+l_mid[i])){
		                				MessageData data = MessageModule.message.get(""+l_mid[i]);
		                			}else
		                				MessageModule.message.put(""+l_mid[i], new MessageData(type[i], l_mid[i], l_fromId[i], i_status[i], temp+s_msg[i]));
		                		}else if(i_status[i] == 2){//删除
		                		}
		        			}else if(type[i]== UserRequest.ACCEPT){//接受请求
		        				 if(i_status[i] == 1){ //显示
		        					 if(MessageModule.message.containsKey(""+l_mid[i])){
			                		}else
		        					  MessageModule.message.put(""+l_mid[i], new MessageData(type[i], l_mid[i], l_fromId[i], i_status[i], temp1+s_msg[i]));
		                		}else if(i_status[i] == 2){//删除
		                		}
		        			}
		                }
                       break;
                case ProtocolDefine.P13_UserMessageOperate:
                    UserMessageOperateAck userMessageOperateAck = new UserMessageOperateAck(resp);
                    //操作的返回
                    long uid = userMessageOperateAck.getMId();
                    if(userMessageOperateAck.getStatus() == 1){ //显示
                    	 //long _status = userMessageOperateAck.getStatus();
                    	 //表示请求这个uid的用户成功了 需要对方同意就能获得体力 
                    	 //MessageModule.message.add(new MessageData(uid, uid, (int)(_status), "向AAA请求体力了"));
                    }else if(userMessageOperateAck.getStatus() == 2){ //已删除
                    	 MessageModule.message.remove(""+uid);
                    }
                    break;
                case ProtocolDefine.P14_FriendHelp:
                    FriendHelpAck friendHelpAck = new FriendHelpAck(resp);
 
                    long fid = friendHelpAck.getFid();//好友id
                    int time = friendHelpAck.getTime();//剩余时间(秒)
                    long _type = friendHelpAck.getType();
                    if(_type==UserRequest.GETHEART_TIME){//爱心体力
		                  //加速系统时间
	                     long sy_time = time;
	                     sy_time = sy_time*1000; //转换毫秒 都是用毫秒
	                     sy_time +=System.currentTimeMillis(); //再加上系统
	                    	
	                     Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
	                     while (iterator.hasNext()) {
	            			FaceBookPlayer player = FacebookOperation.playerMap.get(iterator.next());
		            			if(player.getid_server() == fid){
		            				if(player!=null){ 
		    		            		player.setGetHeartTime(sy_time);
		    		            		player.getHeart().onHeart(player);
		    		            	}
		            				return;
		            			}
	                     }
            		}else if(_type==UserRequest.FRIENDHELP_TIME){ //好友援助
            			  //加速系统时间
	                     long sy_time = time;
	                     sy_time = sy_time*1000; //转换毫秒 都是用毫秒
	                     sy_time +=System.currentTimeMillis(); //再加上系统
	                     Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
	                     while (iterator.hasNext()) {
	            			FaceBookPlayer player = FacebookOperation.playerMap.get(iterator.next());
		            			if(player.getid_server() == fid){
		            				if(player!=null){ 
		    		            		player.setFriendHelpTime(sy_time);
		    		            		if(FacebookOperation.getFacebook().
				    		            		getFriendHelpTime() != null)
			    		            		FacebookOperation.getFacebook().
			    		            		getFriendHelpTime().onFreindHelpTime(player);
		    		            	}
		            				return;
		            			}
	                     }
            		}
             
                    break;
                case ProtocolDefine.P15_VegetableOperate:
//                	try {
//                		 VegetableOperateAck vegetableOperateAck = new VegetableOperateAck(resp);
//                         long _uid = vegetableOperateAck.getUid(); //好友id,或者自己的id
//                         FarmData data = null;
//                         if(_uid != FacebookOperation.player.getid_server()){
//                         	//偷
//                         	int result = vegetableOperateAck.getResult();
//                         	if(result == 0){
//                         		temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_FAILED);
//                         		ChooseLevelModule2.sendMessage(temp);
//                         	}else if(result == 1){
//                         		//成功偷取
//                         		 if(FarmModule.farmdata.containsKey(_uid)){//刷新
//                         			 data = FarmModule.farmdata.get(_uid);
//                                    	 data.setValue(vegetableOperateAck);
//                         		 }
//                         	}
//                         }else{ //对自己农场的操作
//                         	 if(FarmModule.farmdata.containsKey(_uid)){//刷新
//                         		 data = FarmModule.farmdata.get(_uid);
//                              	 data.setValue(vegetableOperateAck);
//                     		 }
//                         }
//                         if(data==null){
//     		            	 data = FarmModule.farmdata.get(_uid);
//     		             }
//                         if( UserRequest.getUser().getFarm()!=null)
//                         	UserRequest.getUser().getFarm().onUpdateFarm(data);
//                         
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
                   
                    break;
                case ProtocolDefine.P16_VegetableList:
//                    VegetableListAck vegetableListAck = new VegetableListAck(resp);
//                    long _uid = vegetableListAck.getUid(); //好友id,或者自己的id
//                    FarmData data = null;
//                    if(FarmModule.farmdata.containsKey(_uid)){//刷新
//                    	data = FarmModule.farmdata.get(_uid);
//                    	data.setValue(vegetableListAck);
//                    }else{//添加
//                    	FarmModule.farmdata.put(_uid, new FarmData(vegetableListAck));
//                    }
//                    
//		            if(data==null){
//		            	 data = FarmModule.farmdata.get(_uid);
//		            }
//		            
//                   if( UserRequest.getUser().getFarm()!=null)
//                    	UserRequest.getUser().getFarm().onUpdateFarm(data);
//                    
                    break;
                case ProtocolDefine.P17_TopList:
                    TopListAck topListAck = new TopListAck(resp);
                    
                    String name[] = topListAck.getNameArry(); //昵称,相当于 command2中的othername
                    int orderIndex[] = topListAck.getOrderIndexArry(); //排名
                    long orderScore[] =  topListAck.getOrderScoreArry();//总分
                    String otherId[] = topListAck.getOtherIdArry(); //消息来源其他id，如fbId
                    long uidarry[] = topListAck.getUidArry(); //好友id,或者自己的id
                   
                    if(RankingSuccessModule.toplist == null)
                    	RankingSuccessModule.toplist = new Vector<TopListData>();
                    
                    for(int i=0;i<otherId.length;++i){
                    	RankingSuccessModule.toplist.add(new TopListData(name[i], orderIndex[i], orderScore[i], otherId[i], uidarry[i]));
                    }
                    FacebookOperation.getFacebook().setStste(FacebookOperation.GAME_STATE_TOPLIST_PARSING);
                    
                    break;
                case ProtocolDefine.P11_UserGameLevel:
                	UserGameLevelAck gamelevelAck = new UserGameLevelAck(resp);
                	uid = gamelevelAck.getUid();
                	int level[] = gamelevelAck.getGameLevelArry(); //关卡
                	long score[] = gamelevelAck.getScoreArry(); //分数
                	int star[] = gamelevelAck.getStarArry(); //星级
                	Iterator iterator = FacebookOperation.playerMap.keySet().iterator();
                	while (iterator.hasNext()) {
            			FaceBookPlayer user = FacebookOperation.playerMap.get(iterator.next());
            			if(user.getid_server()==uid){
            				if(user.getUserlevel()!=null){
            					user.getUserlevel().onLevelRes(user, level[0], score[0], star[0]);
            				}
            				 return;
            			}
                	}
                	
                	FaceBookPlayer user = FacebookOperation.player;
                	if(user.getid_server()==uid){
        				if(user.getUserlevel()!=null){
        					user.getUserlevel().onLevelRes(user, level[0], score[0], star[0]);
        				}
        			}
                	break;
            }
        }
    }

    @Override
    public void run() {
        while (active) {
            if (http == null){
            	System.out.println("http changed");
                continue;
            }
            handleMessage(http, true);
        }
    }
}
