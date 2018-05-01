package com.kokatlaruruxi.wy;

import com.endlessvegetables2.ui.GameItem;
import com.endlessvegetables2.ui.VeggiesData;
import com.socogame.coolEdit.CoolEditDefine;

public class GameReadData {
//
//	public static int bombLevel;
//	
	public void stageNumber(GameMain gameMain)
	{
		gameMain.stageIndex = VeggiesData.getCurrentLevel();
	}
	
	public void setLeftPlayerID(GameMain gameMain)
	{
		switch(VeggiesData.getCardSlot()[0])
		{
			case GameItem.Item01:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_FQ;
				break;
				
			case GameItem.Item02:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_FQ_2;
				break;	
				
			case GameItem.Item03:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_FQ_3;
				break;	
				
			case GameItem.Item13:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_WD;
				break;		
				
			case GameItem.Item14:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_WD_2;
				break;		
				
			case GameItem.Item15:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_WD_3;
				break;		
				
			case GameItem.Item19:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_LJ;
				break;		
				
			case GameItem.Item20:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_LJ_2;
				break;		
				
			case GameItem.Item21:
				gameMain.gameMainLeftPlayerID = CoolEditDefine.Player_LJ_3;
				break;					
		}
	}
		
	public void setRightPlayerID(GameMain gameMain)
	{
		int slot = 0;
		
		for(int i=1;i<4;i++)
		{
			int kind = -100;
			
			if(VeggiesData.getCardSlot()[i]>0)
			{
				switch(VeggiesData.getCardSlot()[i])
				{
					case GameItem.Item04:
						kind = CoolEditDefine.Player_LB;
						break;
						
					case GameItem.Item05:
						kind = CoolEditDefine.Player_LB_2;
						break;	
						
					case GameItem.Item06:
						kind = CoolEditDefine.Player_LB_3;
						break;	
						
					case GameItem.Item07:
						kind = CoolEditDefine.Player_YC;
						break;		
						
					case GameItem.Item08:
						kind = CoolEditDefine.Player_YC_2;
						break;		
						
					case GameItem.Item09:
						kind = CoolEditDefine.Player_YC_3;
						break;		
						
					case GameItem.Item10:
						kind = CoolEditDefine.Player_TD;
						break;		
						
					case GameItem.Item11:
						kind = CoolEditDefine.Player_TD_2;
						break;		
						
					case GameItem.Item12:
						kind = CoolEditDefine.Player_TD_3;
						break;	
						
					case GameItem.Item16:
						kind = CoolEditDefine.Player_MG;
						break;		
						
					case GameItem.Item17:
						kind = CoolEditDefine.Player_MG_2;
						break;		
						
					case GameItem.Item18:
						kind = CoolEditDefine.Player_MG_3;
						break;	
						
					case GameItem.Item22:
						kind = CoolEditDefine.Player_HC;
						break;		
						
					case GameItem.Item23:
						kind = CoolEditDefine.Player_HC_2;
						break;		
						
					case GameItem.Item24:
						kind = CoolEditDefine.Player_HC_3;
						break;	
						
					case GameItem.Item25:
						kind = CoolEditDefine.Player_ZS;
						break;		
						
					case GameItem.Item26:
						kind = CoolEditDefine.Player_ZS_2;
						break;		
						
					case GameItem.Item27:
						kind = CoolEditDefine.Player_ZS_3;
						break;	
						
					case GameItem.Item28:
						kind = CoolEditDefine.Player_NG;
						break;		
						
					case GameItem.Item29:
						kind = CoolEditDefine.Player_NG_2;
						break;		
						
					case GameItem.Item30:
						kind = CoolEditDefine.Player_NG_3;
						break;												
				}
				
				gameMain.waitingSpriteBulletRight.addWaitingSpriteBullet(kind, 
						gameMain.getPlayerRightCdtime(kind), 
						gameMain.playerRightSpecial(kind), slot);
				
				slot ++;
			}
		}
	}
	
	public byte[] slot;
	public int[] slot_level;
	private int[] slot_Item_id;
	
	public void setGameUITool(GameMain gameMain)
	{		 		                                    
		slot = new byte[2];
		slot_level = new int[2];
		slot_Item_id = new int[2];
		
//		bombLevel = -1;
		
		for(int i=0;i<2;i++)
		{
			if(VeggiesData.getCardSlot()[i+5]>0)
			{
				slot_Item_id[i] = VeggiesData.getCardSlot()[i+5];
				
				switch(VeggiesData.getCardSlot()[i+5])
				{
					case GameItem.Item31:
						slot[i] = GameUI.ITEM_TYPE_2;
						slot_level[i] = 1;
					break;
					
					case GameItem.Item32:
						slot[i] = GameUI.ITEM_TYPE_2;
						slot_level[i] = 2;
					break;
					
					case GameItem.Item33:
						slot[i] = GameUI.ITEM_TYPE_2;
						slot_level[i] = 3;
					break;
					
					case GameItem.Item34:
						slot[i] = GameUI.ITEM_TYPE_1;
						slot_level[i] = 1;
					break;
					
					case GameItem.Item35:
						slot[i] = GameUI.ITEM_TYPE_1;
						slot_level[i] = 2;
					break;
					
					case GameItem.Item36:
						slot[i] = GameUI.ITEM_TYPE_1;
						slot_level[i] = 3;
					break;
					
					case GameItem.Item37:
						slot[i] = GameUI.ITEM_TYPE_3;
						slot_level[i] = 1;						
//						bombLevel = 1;
					break;
					
					case GameItem.Item38:
						slot[i] = GameUI.ITEM_TYPE_3;
						slot_level[i] = 2;						
//						bombLevel = 2;
					break;
					
					case GameItem.Item39:
						slot[i] = GameUI.ITEM_TYPE_3;
						slot_level[i] = 3;						
//						bombLevel = 3;
					break;
					
					case GameItem.Item40:
						slot[i] = GameUI.ITEM_TYPE_7;
						slot_level[i] = 1;
					break;
					
					case GameItem.Item41:
						slot[i] = GameUI.ITEM_TYPE_7;
						slot_level[i] = 2;
					break;
					
					case GameItem.Item42:
						slot[i] = GameUI.ITEM_TYPE_7;
						slot_level[i] = 3;
					break;
					
					case GameItem.Item43:
						slot[i] = GameUI.ITEM_TYPE_6;
						slot_level[i] = 1;
					break;
					
					case GameItem.Item44:
						slot[i] = GameUI.ITEM_TYPE_6;
						slot_level[i] = 2;
					break;
					
					case GameItem.Item45:
						slot[i] = GameUI.ITEM_TYPE_6;
						slot_level[i] = 3;
					break;
				}
			}
			else
			{
				//没有道具
				slot_Item_id[i] = VeggiesData.getCardSlot()[i+5];
				slot[i] = GameUI.ITEM_TYPE_0;
				slot_level[i] = 1;								
			}
		}
		
		gameMain.gameUI.initGameUITool(new byte[]{slot[0], slot[1], (gameMain.stageIndex<4?GameUI.ITEM_TYPE_0:GameUI.ITEM_TYPE_5)},
				 new int[]{slot_level[0], slot_level[1], 1}, 
				 new int[]{(slot_Item_id[0]<=0?0:VeggiesData.getAllCardNum()[VeggiesData.getCardIndex(slot_Item_id[0])]),
						   (slot_Item_id[1]<=0?0:VeggiesData.getAllCardNum()[VeggiesData.getCardIndex(slot_Item_id[1])]), 
						   (gameMain.stageIndex<4?0:5)});
	}
	
	public void setLatticeLife(GameMain gameMain)
	{
		gameMain.latticeLife = VeggiesData.getFence_HP();
		
		gameMain.latticeLife = (int)(gameMain.latticeLife*GamePowerCard.latticeBlood);
	}
	
	public int getLatticeLevel()
	{
		return VeggiesData.getFence_HP_level();
	}
	
	public void setGemNumber(GameMain gameMain)
	{
		gameMain.gemNumber = VeggiesData.getGem();
	}
	
	public void setGoldNumber(GameMain gameMain)
	{
		gameMain.goldenNumber = VeggiesData.getGold();				
	}
	
	public void setComboShowTime(GameMain gameMain)
	{
		gameMain.comboShowTime = (int)(VeggiesData.getCombus_time()*25);
	}
	
	public void setPowerCard()
	{
		GamePowerCard.setPowerCard(VeggiesData.getCardSlot()[4]);
	}
	
	public void setSlingShotLevel(GameMain gameMain)
	{		
		gameMain.slingshot.setSlingShotLevel(VeggiesData.getSlingshot_crit_level());
	}
}
