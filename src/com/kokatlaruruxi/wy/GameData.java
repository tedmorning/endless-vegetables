package com.kokatlaruruxi.wy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.socoGameEngine.GameRecord;
import com.socoGameEngine.GameSave;

public class GameData implements GameSave{
	
//	public int comboLevel;		//暴击等级
//	
//	public int gameNumber;		//游戏分数
//	
//	public int goldenNumber;	//金币数
//	
//	public int reloadLevel;		//装弹等级
//	
//	public int latticeLevel;	//篱笆等级
//	
//	public int player[];        //所选蔬菜种类	 
	
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
