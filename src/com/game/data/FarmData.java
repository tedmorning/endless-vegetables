//package com.game.data;
//
//import com.farm.FarmPlant;
//import com.protocol.response.ack.VegetableListAck;
//import com.protocol.response.ack.VegetableOperateAck;
//
///**
// * 农场的数据
// * @author Administrator
// */
//public class FarmData {
//	
//     //种植成功以后获得奖励
//	 public final static int PLANTHEART = 1; //爱心
//	 public final static int FARM_PLANTGOLD_START = 50; //随机开始
//	 public final static int FARM_PLANTGOLD_END = 100; //随机结束
//	 public final static int FARM_PLANTCARD = 1; //卡牌随机一张
//	 
//	 public int index[];     //蔬菜编号
//	 public int itemid[];    //蔬菜种类ID 1=体力之心,2=卡片之运，3=金币之财
//	 public String msg[];    //消息内容
//	 public int status[];    //1=可种植，2=种植中，3=可收取
//	 private int timearry[];  //剩余时间(秒)
//	 public long uid ;    //好友id,或者自己的id
//	 public long time[];
//	 
// 
//	 public boolean isUpdateA; //刷新单个数据 
//	 public static final int _size = 3;
//	 public int serialNumber;
//	 
//	 public FarmData(VegetableListAck vegetableListAck){
// 		 serialNumber = -1;
//		 index = new int[_size];
//		 itemid = new int[_size];
//		 msg = new String[_size];
//		 status = new int[_size];
//		 timearry = new int[_size];
//		 time = new long[_size];
//		 
//		 int _index[] = vegetableListAck.getIndexArry(); //蔬菜编号
//		 int _itemid[] = vegetableListAck.getItemIdArry();//蔬菜种类ID 1=体力之心,2=卡片之运，3=金币之财
//		 String _msg[] = vegetableListAck.getMsgArry(); //消息内容
//		 int _status[] = vegetableListAck.getStatusArry(); //1=可种植，2=种植中，3=可收取
//		 int _timearry[] = vegetableListAck.getTimeArry(); //剩余时间(秒)
//		 for(int i=0;i<_size;++i){
//				if(_index!=null && _index.length>i)
//					index[i] = _index[i];
//				else
//					index[i] = -1;
//				
//				if(_itemid!=null && _itemid.length>i && _itemid[i]!=0)
//					itemid[i] = _itemid[i];
//				else
//					itemid[i] = (i+1);//蔬菜种类ID 1=体力之心,2=卡片之运，3=金币之财
//
//				if(_msg!=null && _msg.length>i)
//					msg[i] = _msg[i];
//				else
//					msg[i] = "";
//
//				if(_status!=null && _status.length>i)
//					status[i] = _status[i];
//				else
//					status[i] = FarmPlant.SURFACE_DEMANDPLANT;  //可种植
//
//				if(_timearry!=null && _timearry.length>i)
//					timearry[i] = _timearry[i];
//				else
//					timearry[i] = -1;
//				 
//		 }
//		 
//          uid = vegetableListAck.getUid(); //好友id,或者自己的id
//          time = new long[_size];
//          
//          for(int i=0;i<_size;++i){
//        	  if(timearry[i]!=-1){
//	              long sy_time = timearry[i];
//	              sy_time = sy_time*1000; //转换毫秒 都是用毫秒
//	              sy_time +=System.currentTimeMillis(); //再加上系统
//	              time[i] = sy_time;
//        	  }
//          }
//	 }
//	 
//	 
//	 public void setValue(VegetableListAck vegetableListAck){
//		 serialNumber = -1;
//		 int _index[] = vegetableListAck.getIndexArry(); //蔬菜编号
//		 int _itemid[] = vegetableListAck.getItemIdArry();//蔬菜种类ID 1=体力之心,2=卡片之运，3=金币之财
//		 String _msg[] = vegetableListAck.getMsgArry(); //消息内容
//		 int _status[] = vegetableListAck.getStatusArry(); //1=可种植，2=种植中，3=可收取
//		 int _timearry[] = vegetableListAck.getTimeArry(); //剩余时间(秒)
//		 for(int i=0;i<_size;++i){
//				if(_index!=null && _index.length>i)
//					index[i] = _index[i];
//				else
//					index[i] = -1;
//				
//				if(_itemid!=null && _itemid.length>i && _itemid[i]!=0)
//					itemid[i] = _itemid[i];
//				else
//					itemid[i] = (i+1);//蔬菜种类ID 1=体力之心,2=卡片之运，3=金币之财
//
//				if(_msg!=null && _msg.length>i)
//					msg[i] = _msg[i];
//				else
//					msg[i] = "";
//
//				if(_status!=null && _status.length>i)
//					status[i] = _status[i];
//				else
//					status[i] = FarmPlant.SURFACE_DEMANDPLANT;  //可种植;
//
//				if(_timearry!=null && _timearry.length>i)
//					timearry[i] = _timearry[i];
//				else
//					timearry[i] = -1;
//				 
//		 }
//		 
//          uid = vegetableListAck.getUid(); //好友id,或者自己的id
//          time = new long[_size];
//          
//          for(int i=0;i<_size;++i){
//        	  if(timearry[i]!=-1){
//	              long sy_time = timearry[i];
//	              sy_time = sy_time*1000; //转换毫秒 都是用毫秒
//	              sy_time +=System.currentTimeMillis(); //再加上系统
//	              time[i] = sy_time;
//        	  }
//          }
//     }
//	 
//	 public void setValue(VegetableOperateAck vegetableOperateAck){
//		
//		 isUpdateA = true;
//		 int _index = vegetableOperateAck.getIndex(); //蔬菜编号;
//		 serialNumber = _index;  //刷新单个的编号
//		 
//		 index[serialNumber] = _index;     //蔬菜编号
//		 itemid[serialNumber]  = vegetableOperateAck.getItemId();    //蔬菜种类ID 1=体力之心,2=卡片之运，3=金币之财
//		 
//		 if(itemid[serialNumber] == 0)
//			 itemid[serialNumber] = serialNumber+1;
//		 
//		 msg[serialNumber] = vegetableOperateAck.getMsg();    //消息内容
//		 status[serialNumber] = vegetableOperateAck.getStatus();    //1=可种植，2=种植中，3=可收取
//		 timearry[serialNumber] = vegetableOperateAck.getTime();  //剩余时间(秒)
//		
//		 time[serialNumber] = timearry[serialNumber];
//		 time[serialNumber] =  time[serialNumber]*1000; //转换毫秒 都是用毫秒
//		 time[serialNumber] +=System.currentTimeMillis(); //再加上系统
//		 
//         
//	 }
//	 /**
//	  * 提供给想模拟种植刷新单个植物的用户
//	  * */
//	 public void setValue(int i_index, int _itemid, String _msg, int _status, int _time){
//			
//		 isUpdateA = true;
//		 int _index = i_index; //蔬菜编号;
//		 serialNumber = _index;  //刷新单个的编号
//		 
//		 index[serialNumber] = _index;     //蔬菜编号
//		 itemid[serialNumber]  = _itemid;    //蔬菜种类ID 1=体力之心,2=卡片之运，3=金币之财
//		 
//		 if(itemid[serialNumber] == 0)
//			 itemid[serialNumber] = serialNumber+1;
//		 
//		 msg[serialNumber] = _msg;    //消息内容
//		 status[serialNumber] = _status;    //1=可种植，2=种植中，3=可收取
//		 timearry[serialNumber] = _time;  //剩余时间(秒)
//		
//		 time[serialNumber] = timearry[serialNumber];
//		 time[serialNumber] =  time[serialNumber]*1000; //转换毫秒 都是用毫秒
//		 time[serialNumber] +=System.currentTimeMillis(); //再加上系统
//		 
//         
//	 }
//	 
//	 public boolean timeNumber(){
//		 	long currenttime = System.currentTimeMillis();
//			String _time = "";
//			for(int i=0;i<time.length;++i){
//				long timeee = time[i];
//				 
//				long cd; 
//				if (timeee>currenttime && timeee - currenttime  > 0) {
//					_time = "";
//					cd = (timeee - currenttime);
//					long tempCD = cd / 1000;
//					if (tempCD % 60 == 0)
//						_time = "00";
//					else if (tempCD % 60 < 10)
//						_time = "0" + tempCD % 60;
//					else
//						_time = _time + tempCD % 60;
//					tempCD = tempCD / 60;
//					if (tempCD % 60 == 0)
//						_time = "00:" + _time;
//					else if (tempCD % 60 < 10)
//						_time = "0" + tempCD % 60 + ":" + _time;
//					else
//						_time = tempCD % 60 + ":" + _time;
//				} else { 
//					    //1=可种植，2=种植中，3=可收取
//					if(status[i] == 2 || status[i]==3)
//						return true;
//					 
//				//收成
//				}
//			}
//			return false;
//	 }
//	 
//	 
//}// end class
