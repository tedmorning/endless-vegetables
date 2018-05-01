package com.socoGameEngine;


import java.util.ArrayList;
import java.util.Iterator;

import android.R.integer;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.MotionEvent;
public class PressButton{
	
	private ArrayList<KeyButton>gButtons;
	private byte MAXPressed = 1;
	public PressButton(){
		gButtons = new ArrayList<KeyButton>();
	}
	public void setMaxPressed(byte _maxpressed){
		MAXPressed = _maxpressed;
	}
	public void PressedButton(MotionEvent event){//��������ڣ��������ontouchevent�¼�
		
				int max =  (event.getPointerCount()>MAXPressed?MAXPressed: event.getPointerCount());
			  for(int i = 0;i< max;i++){
				  int pointID	= (event.getAction()&MotionEvent.ACTION_POINTER_ID_MASK)>>MotionEvent.ACTION_POINTER_ID_SHIFT;
				  addPressedButton(pointID, event.getX(i), event.getY(i), event.getAction());
			  }
			  if(event.getPointerCount() == 0){
				  delAllPressedButton();
			  }
	}
	
	public int size(){
		return gButtons.size();
	}
	
	private void addPressedButton(int _index,float x,float y,int state){//���ڲ���������Ӱ������б�
		
		 if(gButtons.size() == 0){
			  gButtons.add(new KeyButton(_index,x, y,state));		
		 }
		 else{
			 int j = gButtons.size();
			 for(int i = 0;i< j;i++){
				  KeyButton _gbButton = gButtons.get(i);
				  if(_gbButton.i_pointindex == _index){
					  _gbButton.reset( _index, x, y, state);
					  break;
				  }
				  else if(i == j -1){
					  if(gButtons.size() < MAXPressed)
					  gButtons.add(new KeyButton(_index,x, y,state));
				  }
				  
			 }
		 }	
		
	}
	public KeyButton[] getButtons(){//������а����¼�

		KeyButton[]kb = new KeyButton[gButtons.size()];
		int j = gButtons.size();
		for(int i = 0;i< j;i++){
			kb[i] = gButtons.get(i);
		}
		
		return kb;
	}
	
	public void delAllPressedButton(){//�ͷ����а���
		gButtons.clear();
	}
	public void delPressedButton(){//�ͷ����б�̧�����İ���
		
		
		  Iterator<KeyButton> iter = gButtons.iterator();    
		  while(iter.hasNext()){    
			  KeyButton _gbButton = iter.next();    
		      if(_gbButton.isPressedUp()){    
		          iter.remove(); 
		          gButtons.remove(_gbButton);
		      }    
		  } 
		  
		  if(gButtons.size() == 1){
			  KeyButton _kButton = gButtons.get(0);
			  if(_kButton.i_state== MotionEvent.ACTION_POINTER_1_UP
						||_kButton.i_state == MotionEvent.ACTION_POINTER_3_UP
						||_kButton.i_state == MotionEvent.ACTION_UP
						||_kButton.i_state == MotionEvent.ACTION_POINTER_UP
						||_kButton.i_state == MotionEvent.ACTION_POINTER_2_UP
						 ){
				  delAllPressedButton();
			  }
		  }
		
	}
	
}
