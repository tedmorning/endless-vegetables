//package com.game.data;
//
//import com.farm.FarmPlant;
//import com.protocol.response.ack.VegetableListAck;
//import com.protocol.response.ack.VegetableOperateAck;
//
///**
// * ũ��������
// * @author Administrator
// */
//public class FarmData {
//	
//     //��ֲ�ɹ��Ժ��ý���
//	 public final static int PLANTHEART = 1; //����
//	 public final static int FARM_PLANTGOLD_START = 50; //�����ʼ
//	 public final static int FARM_PLANTGOLD_END = 100; //�������
//	 public final static int FARM_PLANTCARD = 1; //�������һ��
//	 
//	 public int index[];     //�߲˱��
//	 public int itemid[];    //�߲�����ID 1=����֮��,2=��Ƭ֮�ˣ�3=���֮��
//	 public String msg[];    //��Ϣ����
//	 public int status[];    //1=����ֲ��2=��ֲ�У�3=����ȡ
//	 private int timearry[];  //ʣ��ʱ��(��)
//	 public long uid ;    //����id,�����Լ���id
//	 public long time[];
//	 
// 
//	 public boolean isUpdateA; //ˢ�µ������� 
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
//		 int _index[] = vegetableListAck.getIndexArry(); //�߲˱��
//		 int _itemid[] = vegetableListAck.getItemIdArry();//�߲�����ID 1=����֮��,2=��Ƭ֮�ˣ�3=���֮��
//		 String _msg[] = vegetableListAck.getMsgArry(); //��Ϣ����
//		 int _status[] = vegetableListAck.getStatusArry(); //1=����ֲ��2=��ֲ�У�3=����ȡ
//		 int _timearry[] = vegetableListAck.getTimeArry(); //ʣ��ʱ��(��)
//		 for(int i=0;i<_size;++i){
//				if(_index!=null && _index.length>i)
//					index[i] = _index[i];
//				else
//					index[i] = -1;
//				
//				if(_itemid!=null && _itemid.length>i && _itemid[i]!=0)
//					itemid[i] = _itemid[i];
//				else
//					itemid[i] = (i+1);//�߲�����ID 1=����֮��,2=��Ƭ֮�ˣ�3=���֮��
//
//				if(_msg!=null && _msg.length>i)
//					msg[i] = _msg[i];
//				else
//					msg[i] = "";
//
//				if(_status!=null && _status.length>i)
//					status[i] = _status[i];
//				else
//					status[i] = FarmPlant.SURFACE_DEMANDPLANT;  //����ֲ
//
//				if(_timearry!=null && _timearry.length>i)
//					timearry[i] = _timearry[i];
//				else
//					timearry[i] = -1;
//				 
//		 }
//		 
//          uid = vegetableListAck.getUid(); //����id,�����Լ���id
//          time = new long[_size];
//          
//          for(int i=0;i<_size;++i){
//        	  if(timearry[i]!=-1){
//	              long sy_time = timearry[i];
//	              sy_time = sy_time*1000; //ת������ �����ú���
//	              sy_time +=System.currentTimeMillis(); //�ټ���ϵͳ
//	              time[i] = sy_time;
//        	  }
//          }
//	 }
//	 
//	 
//	 public void setValue(VegetableListAck vegetableListAck){
//		 serialNumber = -1;
//		 int _index[] = vegetableListAck.getIndexArry(); //�߲˱��
//		 int _itemid[] = vegetableListAck.getItemIdArry();//�߲�����ID 1=����֮��,2=��Ƭ֮�ˣ�3=���֮��
//		 String _msg[] = vegetableListAck.getMsgArry(); //��Ϣ����
//		 int _status[] = vegetableListAck.getStatusArry(); //1=����ֲ��2=��ֲ�У�3=����ȡ
//		 int _timearry[] = vegetableListAck.getTimeArry(); //ʣ��ʱ��(��)
//		 for(int i=0;i<_size;++i){
//				if(_index!=null && _index.length>i)
//					index[i] = _index[i];
//				else
//					index[i] = -1;
//				
//				if(_itemid!=null && _itemid.length>i && _itemid[i]!=0)
//					itemid[i] = _itemid[i];
//				else
//					itemid[i] = (i+1);//�߲�����ID 1=����֮��,2=��Ƭ֮�ˣ�3=���֮��
//
//				if(_msg!=null && _msg.length>i)
//					msg[i] = _msg[i];
//				else
//					msg[i] = "";
//
//				if(_status!=null && _status.length>i)
//					status[i] = _status[i];
//				else
//					status[i] = FarmPlant.SURFACE_DEMANDPLANT;  //����ֲ;
//
//				if(_timearry!=null && _timearry.length>i)
//					timearry[i] = _timearry[i];
//				else
//					timearry[i] = -1;
//				 
//		 }
//		 
//          uid = vegetableListAck.getUid(); //����id,�����Լ���id
//          time = new long[_size];
//          
//          for(int i=0;i<_size;++i){
//        	  if(timearry[i]!=-1){
//	              long sy_time = timearry[i];
//	              sy_time = sy_time*1000; //ת������ �����ú���
//	              sy_time +=System.currentTimeMillis(); //�ټ���ϵͳ
//	              time[i] = sy_time;
//        	  }
//          }
//     }
//	 
//	 public void setValue(VegetableOperateAck vegetableOperateAck){
//		
//		 isUpdateA = true;
//		 int _index = vegetableOperateAck.getIndex(); //�߲˱��;
//		 serialNumber = _index;  //ˢ�µ����ı��
//		 
//		 index[serialNumber] = _index;     //�߲˱��
//		 itemid[serialNumber]  = vegetableOperateAck.getItemId();    //�߲�����ID 1=����֮��,2=��Ƭ֮�ˣ�3=���֮��
//		 
//		 if(itemid[serialNumber] == 0)
//			 itemid[serialNumber] = serialNumber+1;
//		 
//		 msg[serialNumber] = vegetableOperateAck.getMsg();    //��Ϣ����
//		 status[serialNumber] = vegetableOperateAck.getStatus();    //1=����ֲ��2=��ֲ�У�3=����ȡ
//		 timearry[serialNumber] = vegetableOperateAck.getTime();  //ʣ��ʱ��(��)
//		
//		 time[serialNumber] = timearry[serialNumber];
//		 time[serialNumber] =  time[serialNumber]*1000; //ת������ �����ú���
//		 time[serialNumber] +=System.currentTimeMillis(); //�ټ���ϵͳ
//		 
//         
//	 }
//	 /**
//	  * �ṩ����ģ����ֲˢ�µ���ֲ����û�
//	  * */
//	 public void setValue(int i_index, int _itemid, String _msg, int _status, int _time){
//			
//		 isUpdateA = true;
//		 int _index = i_index; //�߲˱��;
//		 serialNumber = _index;  //ˢ�µ����ı��
//		 
//		 index[serialNumber] = _index;     //�߲˱��
//		 itemid[serialNumber]  = _itemid;    //�߲�����ID 1=����֮��,2=��Ƭ֮�ˣ�3=���֮��
//		 
//		 if(itemid[serialNumber] == 0)
//			 itemid[serialNumber] = serialNumber+1;
//		 
//		 msg[serialNumber] = _msg;    //��Ϣ����
//		 status[serialNumber] = _status;    //1=����ֲ��2=��ֲ�У�3=����ȡ
//		 timearry[serialNumber] = _time;  //ʣ��ʱ��(��)
//		
//		 time[serialNumber] = timearry[serialNumber];
//		 time[serialNumber] =  time[serialNumber]*1000; //ת������ �����ú���
//		 time[serialNumber] +=System.currentTimeMillis(); //�ټ���ϵͳ
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
//					    //1=����ֲ��2=��ֲ�У�3=����ȡ
//					if(status[i] == 2 || status[i]==3)
//						return true;
//					 
//				//�ճ�
//				}
//			}
//			return false;
//	 }
//	 
//	 
//}// end class
