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
 * @version ����ʱ�䣺2012-12-4 ����5:51:14
 * 
 */

public class ResponseHandler extends ResponseListener implements Runnable {

    private Http http;
    private boolean active = false;

    public void setHttp(Http http) {
        this.http = http;
        if(!active){
	     // �������߳̽���Э�� ���߷�����Ϸ���߳̽���
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
        // ������ʾ����
        if (resp.getProtocolID() < 0) {
            String error = resp.getErrorMessage();
            switch (resp.getProtocolID()) {
            //���ñ�����ʾ��Ϣ������ȷ�Ͽ�
                case ProtocolDefine.COMMOM_MESSAGE:
                    System.out.println("cmd=" + resp.getProtocolID() + " :" + error);
                    ChooseLevelModule2.sendMessage("cmd=" + resp.getProtocolID() + " :" + error);
                    break;
                //Ŀ��ͷ����ʾ��Ϣ��ֱ�ӽ��������ʾ
                case ProtocolDefine.HEAD_MESSAGE:
                    System.out.println("cmd=" + resp.getProtocolID() + " :" + error);
                    ChooseLevelModule2.sendMessage("cmd=" + resp.getProtocolID() + " :" + error);
                    break;
                //��ʾ����Ͽ���Ϣ������ȷ�Ͽ򣬱�ʶ����Ͽ�������������״̬����
                case ProtocolDefine.NET_ERROR:
                	UserRequest.getUser().setLoginok(false);
                	FacebookOperation.isOpenNet = true;
					FacebookOperation.getFacebook().cancel(); //����ʧ������Ϊδ��¼
                    System.out.println("cmd=" + resp.getProtocolID() + " :" + error);
                    ChooseLevelModule2.sendMessage("cmd=" + resp.getProtocolID() + " :" + error);
                    break;
            }
        } else {
        	UserRequest.getUser().setLoginok(true);
            //Э���߼�����
            switch (resp.getProtocolID()) {
                case ProtocolDefine.P1_UserLogin:
                    UserLoginAck userLoginAck = new UserLoginAck(resp);
                    //��ǰ�û���˾������id
                    FacebookOperation.player.setid_server(userLoginAck.getUid());
                    http.s_sessionId = userLoginAck.getSid();
					UserRequest.getUser().setLoginok(true);
					UserRequest.getUser().ReqMessage(); //��ȡ��Ϣ
                    if( UserRequest.getUser().getUserLoginRes()!=null)
                    	UserRequest.getUser().getUserLoginRes().onlogicRes(userLoginAck.getUid());
                    break;
                case ProtocolDefine.P2_UserRegister:
                    UserRegisterAck userRegisterAck = new UserRegisterAck(resp);
                    break;
                case ProtocolDefine.P10_UserView:
                    UserViewAck userViewAck = new UserViewAck(resp);
                    //���ú����ڷ�������id
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
                    int type[] = userMessageAck.getTypeArry(); //1=��������,2=��������
                    String s_msg[] = userMessageAck.getMsgArry();
                    int i_status[] = userMessageAck.getStatusArry();
                    
                    String temp = LangUtil.getLangString(LangDefineClient.REQ_MSGTEXT);
                    String temp1 = LangUtil.getLangString(LangDefineClient.GET_MSGTEXT);
                    if(type!=null)
		                for(int i=0;i<type.length;++i){
		                	if(type[i]== UserRequest.REQPS){ //��������
		                		if(i_status[i] == 1){ //��ʾ
		                			//�����п�����ˢ��
		                			if(MessageModule.message.containsKey(""+l_mid[i])){
		                				MessageData data = MessageModule.message.get(""+l_mid[i]);
		                			}else
		                				MessageModule.message.put(""+l_mid[i], new MessageData(type[i], l_mid[i], l_fromId[i], i_status[i], temp+s_msg[i]));
		                		}else if(i_status[i] == 2){//ɾ��
		                		}
		        			}else if(type[i]== UserRequest.ACCEPT){//��������
		        				 if(i_status[i] == 1){ //��ʾ
		        					 if(MessageModule.message.containsKey(""+l_mid[i])){
			                		}else
		        					  MessageModule.message.put(""+l_mid[i], new MessageData(type[i], l_mid[i], l_fromId[i], i_status[i], temp1+s_msg[i]));
		                		}else if(i_status[i] == 2){//ɾ��
		                		}
		        			}
		                }
                       break;
                case ProtocolDefine.P13_UserMessageOperate:
                    UserMessageOperateAck userMessageOperateAck = new UserMessageOperateAck(resp);
                    //�����ķ���
                    long uid = userMessageOperateAck.getMId();
                    if(userMessageOperateAck.getStatus() == 1){ //��ʾ
                    	 //long _status = userMessageOperateAck.getStatus();
                    	 //��ʾ�������uid���û��ɹ��� ��Ҫ�Է�ͬ����ܻ������ 
                    	 //MessageModule.message.add(new MessageData(uid, uid, (int)(_status), "��AAA����������"));
                    }else if(userMessageOperateAck.getStatus() == 2){ //��ɾ��
                    	 MessageModule.message.remove(""+uid);
                    }
                    break;
                case ProtocolDefine.P14_FriendHelp:
                    FriendHelpAck friendHelpAck = new FriendHelpAck(resp);
 
                    long fid = friendHelpAck.getFid();//����id
                    int time = friendHelpAck.getTime();//ʣ��ʱ��(��)
                    long _type = friendHelpAck.getType();
                    if(_type==UserRequest.GETHEART_TIME){//��������
		                  //����ϵͳʱ��
	                     long sy_time = time;
	                     sy_time = sy_time*1000; //ת������ �����ú���
	                     sy_time +=System.currentTimeMillis(); //�ټ���ϵͳ
	                    	
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
            		}else if(_type==UserRequest.FRIENDHELP_TIME){ //����Ԯ��
            			  //����ϵͳʱ��
	                     long sy_time = time;
	                     sy_time = sy_time*1000; //ת������ �����ú���
	                     sy_time +=System.currentTimeMillis(); //�ټ���ϵͳ
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
//                         long _uid = vegetableOperateAck.getUid(); //����id,�����Լ���id
//                         FarmData data = null;
//                         if(_uid != FacebookOperation.player.getid_server()){
//                         	//͵
//                         	int result = vegetableOperateAck.getResult();
//                         	if(result == 0){
//                         		temp = LangUtil.getLangString(LangDefineClient.DIALOGBOX_FAILED);
//                         		ChooseLevelModule2.sendMessage(temp);
//                         	}else if(result == 1){
//                         		//�ɹ�͵ȡ
//                         		 if(FarmModule.farmdata.containsKey(_uid)){//ˢ��
//                         			 data = FarmModule.farmdata.get(_uid);
//                                    	 data.setValue(vegetableOperateAck);
//                         		 }
//                         	}
//                         }else{ //���Լ�ũ���Ĳ���
//                         	 if(FarmModule.farmdata.containsKey(_uid)){//ˢ��
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
//                    long _uid = vegetableListAck.getUid(); //����id,�����Լ���id
//                    FarmData data = null;
//                    if(FarmModule.farmdata.containsKey(_uid)){//ˢ��
//                    	data = FarmModule.farmdata.get(_uid);
//                    	data.setValue(vegetableListAck);
//                    }else{//���
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
                    
                    String name[] = topListAck.getNameArry(); //�ǳ�,�൱�� command2�е�othername
                    int orderIndex[] = topListAck.getOrderIndexArry(); //����
                    long orderScore[] =  topListAck.getOrderScoreArry();//�ܷ�
                    String otherId[] = topListAck.getOtherIdArry(); //��Ϣ��Դ����id����fbId
                    long uidarry[] = topListAck.getUidArry(); //����id,�����Լ���id
                   
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
                	int level[] = gamelevelAck.getGameLevelArry(); //�ؿ�
                	long score[] = gamelevelAck.getScoreArry(); //����
                	int star[] = gamelevelAck.getStarArry(); //�Ǽ�
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
