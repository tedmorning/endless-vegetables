package com.kokatlaruruxi.wy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.socoGameEngine.GameRecord;
import com.socoGameEngine.GameSave;

public class GameData implements GameSave{
	
//	public int comboLevel;		//�����ȼ�
//	
//	public int gameNumber;		//��Ϸ����
//	
//	public int goldenNumber;	//�����
//	
//	public int reloadLevel;		//װ���ȼ�
//	
//	public int latticeLevel;	//��ʵȼ�
//	
//	public int player[];        //��ѡ�߲�����	 
	
//	public  void saveGame(){
//		GameRecord.saveGame("FireVeg20130422",this);
//	}
//	
//	public  void loadGame(){
//		GameRecord.loadGame("FireVeg20130422",this);
//	}

	public void loadfile(ObjectInputStream ois) throws Exception {
		// TODO Auto-generated method stub
//		money = ois.readInt();
	
//		GameAwards.setAwardsList((ArrayList<Award>) ois.readObject());		
	}

	public void writefile(ObjectOutputStream oos) throws Exception {
		// TODO Auto-generated method stub
		
//		oos.writeInt(money);
		
//		oos.writeObject(GameAwards.getAwardsList());						
	}
	
}
