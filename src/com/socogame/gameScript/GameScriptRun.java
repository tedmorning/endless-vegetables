package com.socogame.gameScript;

import java.util.ArrayList;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.Combo;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMain;
import com.kokatlaruruxi.wy.GameTeaching;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class GameScriptRun {

	private GameScript gameScript;
	
	private int stageId;

	private int layer;
	
	private int layerIndex;
	
	private int monsterNumber;
	
	private int waitTime;
	
	private ArrayList<Integer> stageMonsterKind;
	
	private int stageLength;
	
	private boolean gameScriptFinish;
	
	private int gotoIndex;
	
	public void initScript(GameMain gameMain, int stage_ID)
	{
		gameScript = new GameScript();
		
		stageId = stage_ID;
		
		layer = 0;
		
		layerIndex = 0;
		
		monsterNumber = -1;
		
		waitTime = 0;
		
		stageMonsterKind = new ArrayList<Integer>();
		
		setStageMonsterKind();	
		
		setStageLength(gameMain);
		
		gameScriptFinish = false;
		
		gotoIndex = -1;
	}
	
	public void initGameStage()
	{
		
	}
	
	public void loadSpriteImage() 
	{
		for(int i=0;i<gameScript.ScriptData[stageId].length;i++)
		{
			for(int j=0;j<gameScript.ScriptData[stageId][i].length;j++)
			{
				if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh||
				   gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh1)
				{												
					SpriteLibrary.loadSpriteImage(gameScript.ScriptData[stageId][i][j][1]);
					
					if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_MM)
						SpriteLibrary.loadSpriteImage(CoolEditDefine.Enemy_MMJS);
					else if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_HZ||
							gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_YGHZ)
						SpriteLibrary.loadSpriteImage(CoolEditDefine.Enemy_HZXJ);
					else if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_YGX)
						SpriteLibrary.loadSpriteImage(CoolEditDefine.Enemy_YGXTM);
					else if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_SHZY)
					{						
						SpriteLibrary.loadSpriteImage(CoolEditDefine.Enemy_SHZYCS);
						SpriteLibrary.loadSpriteImage(CoolEditDefine.Enemy_SHZYXZ);
					}
					else if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_YGY)
					{
						SpriteLibrary.loadSpriteImage(CoolEditDefine.Enemy_YGYJF);
						SpriteLibrary.loadSpriteImage(CoolEditDefine.Enemy_YGYKS);
						SpriteLibrary.loadSpriteImage(CoolEditDefine.Enemy_YGYYM);
					}
				}
//				else if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Teleport)
//				{										                                    
//					SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_TELEPORT);                                    					                                         					                                       
//				}
//				else if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Board)
//				{										                                    
//					SpriteLibrary.loadSpriteImage(CoolEditDefine.Effect_SPRINGBOARD);                                    					                                         					                                       
//				}
//				else if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Fog)
//				{										                                    
//					                      					                                         					                                       
//				}
			}
		}
	}
	
	public void releaseSpriteImage()
	{
		for(int i=0;i<gameScript.ScriptData[stageId].length;i++)
		{
			for(int j=0;j<gameScript.ScriptData[stageId][i].length;j++)
			{
				if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh||
				   gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh1)
				{										                                    
					SpriteLibrary.DelSpriteImage(gameScript.ScriptData[stageId][i][j][1]); 
					
					if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_MM)
						SpriteLibrary.DelSpriteImage(CoolEditDefine.Enemy_MMJS);
					else if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_HZ||
							gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_YGHZ)
						SpriteLibrary.DelSpriteImage(CoolEditDefine.Enemy_HZXJ);
					else if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_YGX)
						SpriteLibrary.DelSpriteImage(CoolEditDefine.Enemy_YGXTM);
					else if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_SHZY)
					{
						SpriteLibrary.DelSpriteImage(CoolEditDefine.Enemy_SHZYCS);
						SpriteLibrary.DelSpriteImage(CoolEditDefine.Enemy_SHZYXZ);
					}
					else if(gameScript.ScriptData[stageId][i][j][1]==CoolEditDefine.Enemy_YGY)
					{
						SpriteLibrary.DelSpriteImage(CoolEditDefine.Enemy_YGYJF);
						SpriteLibrary.DelSpriteImage(CoolEditDefine.Enemy_YGYKS);
						SpriteLibrary.DelSpriteImage(CoolEditDefine.Enemy_YGYYM);
					}
				}
//				else if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Teleport)
//				{										                                    
//					SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_TELEPORT);                                    					                                         					                                       
//				}
//				else if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Board)
//				{										                                    
//					SpriteLibrary.DelSpriteImage(CoolEditDefine.Effect_SPRINGBOARD);                                    					                                         					                                       
//				}
//				else if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Fog)
//				{										                                    
//					                      					                                         					                                       
//				}
			}
		}
	}
	
	private void setStageLength(GameMain gameMain)
	{
		stageLength = 0;
		
		for(int i=0;i<gameScript.ScriptData[stageId].length;i++)
		{
			for(int j=0;j<gameScript.ScriptData[stageId][i].length;j++)
			{
				if(j==0)
				{
					if(gameScript.ScriptData[stageId][i][j][0]==GameScript.EnterTeach)
					{
						if(gameMain.gameTeaching.teachingArrary[gameScript.ScriptData[stageId][i][j][1]])
							break;						
					}
					else
					{
						if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh||
						   gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh1)
						{
							stageLength ++;
						}
					}
				}
				else
				{
					if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh||
					   gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh1)
					{
						stageLength ++;
					}
				}
				
			}
		}			
	}
	
	public int getStageLength()
	{
		return stageLength;
	}
	
	public void runScript(GameMain gameMain)
	{
		if(waitTime>0)
		{
			waitTime --;
			
			if(waitTime==0 && monsterNumber==-1)
				layerIndex ++;
			
			return;
		}
		
		if(layer<gameScript.ScriptData[stageId].length)
		{
			if(layerIndex<gameScript.ScriptData[stageId][layer].length)
			{
				setOpenMonsters(gameScript.ScriptData[stageId][layer][layerIndex], gameMain);
			}
			else
			{		
				int num = 0;
				
				for(int i=0;i<gameMain.gameMonster.getEnemyList().size();i++)
				{					
					if(gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_HZXJ&&
						gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MMJS&&
						gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_SHZYXZ&&
						gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_SHZYCS&&
						gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_YGYKS&&
						gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_YGYYM&&
						gameMain.gameMonster.getEnemyList().get(i).kind!=CoolEditDefine.Enemy_MEGICWATER)					
					{
						num ++;
					}
				}
				
				if(num==0)
				{
					layer ++;
					
					layerIndex = 0;
					
//					setStageMonsterKind();
				}
			}
		}
		else
		{
			gameScriptFinish = true;
			//关卡数据读完，游戏结束
		}					
	}		
	
	public boolean gameScriptFinish()
	{
		return gameScriptFinish;
	}
	
	public void setOpenMonsters(short[] type, GameMain gameMain)
	{
		switch(type[0])
		{
			case GameScript.Refresh:				
				for(int i=0;i<type[3];i++)
				{
					addMonsters(type[1], gameMain, (int)(type[4+i]*GameConfig.f_zoomx), (int)(type[2]*GameConfig.f_zoomy));
				}		
				
				if(gotoIndex==-1)
				{
					layerIndex ++;
					
					stageLength --;
				}
				else
				{
					layerIndex = gotoIndex;
				}				
				break;
				
			case GameScript.Refresh1:
				if(monsterNumber==-1)
				{
					monsterNumber = type[4];										
				}
				
				addMonsters(type[1], gameMain, (int)(type[3]*GameConfig.f_zoomx), (int)(type[2]*GameConfig.f_zoomy));
				
				monsterNumber --;
				
				if(monsterNumber>0)
				{
					waitTime = type[5];
				}
				else
				{
					monsterNumber = -1;
				
					if(gotoIndex==-1)
					{
						layerIndex ++;										
						
						stageLength --;
					}
					else
					{
						layerIndex = gotoIndex;
					}
				}							
				break;
				
			case GameScript.Wait:	
				if(type[1]>=0)
					waitTime = type[1];
				else
					waitTime = ExternalMethods.throwDice(0, Math.abs(type[1]));
				break;	
				
			case GameScript.Fog:								
				gameMain.gameSmoke.setShow(true);
				
				layerIndex ++;				
				break;		
				
			case GameScript.Teleport:								
				gameMain.gameTeleport.setShow(gameMain/*, (byte)type[1], (byte)type[2]*/);
				
				layerIndex ++;				
				break;	
				
			case GameScript.Board:					
				int[] tmp = new int[type.length-1];
				
				for(int i=0;i<tmp.length;i++)
				{
					tmp[i] = type[i+1];
				}
				
				gameMain.gameSpringboard.setSpringboard(tmp, 18);
				
				layerIndex ++;				
				break;		
				
			case GameScript.Banana:									
				gameMain.gameBanana.setBanana(type[1], type[2]);
				
				layerIndex ++;				
				break;		
				
			case GameScript.Cobweb:									
				gameMain.gameCobweb.setCobweb(type[1], type[2]);
				
				layerIndex ++;				
				break;	
				
			case GameScript.Rose:									
				gameMain.gameRose.setRose(type[1], type[2]);
				
				layerIndex ++;				
				break;			
				
			case GameScript.MagicDoor:									
				gameMain.gameMagicDoor.setMagicDoor(type[1], type[2], type[3], type[4]);
				
				layerIndex ++;				
				break;		
				
			case GameScript.OpenFight1Music:							
				GameMedia.playMusic(R.raw.levell_01, true, true);									
				
				if(VeggiesData.isMuteMusic())	
					GameMedia.pauseMusic();
				
				layerIndex ++;				
				break;	
				
			case GameScript.OpenFight2Music:					
				GameMedia.playMusic(R.raw.levell_02, true, true);
				
				if(VeggiesData.isMuteMusic())	
					GameMedia.pauseMusic();
				
				layerIndex ++;				
				break;	
				
			case GameScript.OpenFight3Music:
				GameMedia.playMusic(R.raw.levell_03, true, true);
				
				if(VeggiesData.isMuteMusic())	
					GameMedia.pauseMusic();
				
				layerIndex ++;				
				break;	
				
			case GameScript.OpenFight4Music:
				GameMedia.playMusic(R.raw.levell_04, true, true);
				
				if(VeggiesData.isMuteMusic())	
					GameMedia.pauseMusic();
				
				layerIndex ++;				
				break;	
				
			case GameScript.OpenBossMusic:		
				GameMedia.playMusic(R.raw.bossl, true, true);
				
				if(VeggiesData.isMuteMusic())	
					GameMedia.pauseMusic();
				
				layerIndex ++;				
				break;	
				
			case GameScript.OpenBubble:
				gameMain.gameBubble.openBubble();
				
				layerIndex ++;	
				break;
				
			case GameScript.CloseBubble:
				gameMain.gameBubble.closeBubble();
				
				layerIndex ++;	
				break;
				
			case GameScript.TeachState:
				if(type[1]==GameTeaching.TEACH_VOL1)
				{					
					if(!gameMain.gameTeaching.teachingArrary[type[1]])
					gameMain.gameTeaching.setGameTeaching(type[1], gameMain.slingshot.slingShotPiece_x, gameMain.slingshot.slingShotPiece_y, LangUtil.getLangString(LangDefineClient.GUIDE_1), GameTeaching.HAND_MOVE_STATE_0, (int)(500*GameConfig.f_zoomy));
				}
				else if(type[1]==GameTeaching.TEACH_VOL2)
				{
					if(!gameMain.gameTeaching.teachingArrary[type[1]])
					gameMain.gameTeaching.setGameTeaching(type[1], gameMain.slingshot.slingShotPiece_x, gameMain.slingshot.slingShotPiece_y, LangUtil.getLangString(LangDefineClient.GUIDE_2), GameTeaching.HAND_MOVE_STATE_0, (int)(500*GameConfig.f_zoomy));
				}
//				else if(type[1]==GameTeaching.TEACH_VOL3)
//				{
//					if(!gameMain.gameTeaching.teachingArrary[type[1]])
//					{
//						gameMain.gameTeaching.setGameTeaching(type[1], gameMain.slingshot.slingShotPiece_x, gameMain.slingshot.slingShotPiece_y, LangUtil.getLangString(LangDefineClient.GUIDE_3), GameTeaching.HAND_MOVE_STATE_0, (int)(500*GameConfig.f_zoomy));
//						
//						gameMain.combo.completeCombo();
//					}
//				}
				else if(type[1]==GameTeaching.TEACH_VOL12)
				{					
					if(!gameMain.gameTeaching.teachingArrary[type[1]])
					{
						int x = (int)(gameMain.gameUI.uiPls[0]+gameMain.gameUI.icon1.bitmap.getWidth()/2);
						
						int y = (int)(gameMain.gameUI.uiPls[1]+gameMain.gameUI.icon1.bitmap.getHeight()/2);						
						
						gameMain.gameTeaching.setGameTeaching(type[1], x, y, LangUtil.getLangString(LangDefineClient.GUIDE_12), GameTeaching.HAND_MOVE_STATE_1, (int)(500*GameConfig.f_zoomy));
					}
				}
				else if(type[1]==GameTeaching.TEACH_VOL13)
				{															
					if(!gameMain.gameTeaching.teachingArrary[type[1]])
					{
						int x = (int)(gameMain.slingshot.SLINGSHOT_X+gameMain.waitingSpriteBulletRight.spriteBulletWaitingPls[0]*GameConfig.f_zoomx);
						
						int y = (int)(gameMain.slingshot.SLINGSHOT_Y+gameMain.waitingSpriteBulletRight.spriteBulletWaitingPls[1]*GameConfig.f_zoomy);
						
						gameMain.gameTeaching.setGameTeaching(type[1], x, y, LangUtil.getLangString(LangDefineClient.GUIDE_13), GameTeaching.HAND_MOVE_STATE_1, (int)(500*GameConfig.f_zoomy));
					}
				}
				else if(type[1]==GameTeaching.TEACH_VOL14)
				{															
					if(!gameMain.gameTeaching.teachingArrary[type[1]])
					{
						int x = (int)(gameMain.gameUI.uiPls[4]+gameMain.gameUI.icon1.bitmap.getWidth()/2);
						
						int y = (int)(gameMain.gameUI.uiPls[5]+gameMain.gameUI.icon1.bitmap.getHeight()/2);						
						
						gameMain.gameTeaching.setGameTeaching(type[1], x, y, LangUtil.getLangString(LangDefineClient.GUIDE_14), GameTeaching.HAND_MOVE_STATE_1, (int)(500*GameConfig.f_zoomy));
					}
				}
				else if(type[1]==GameTeaching.TEACH_VOL19)
				{															
					if(!gameMain.gameTeaching.teachingArrary[type[1]])
					{
						int x = (int)(gameMain.gameUI.uiPls[6]+gameMain.gameUI.userHelp.bitmap.getWidth()/2);
						
						int y = (int)(gameMain.gameUI.uiPls[7]+gameMain.gameUI.userHelp.bitmap.getHeight()/2);
						
						gameMain.gameTeaching.setGameTeaching(type[1], x, y, LangUtil.getLangString(LangDefineClient.GUIDE_19), GameTeaching.HAND_MOVE_STATE_1, (int)(500*GameConfig.f_zoomy));
					}
				}
				else if(type[1]==GameTeaching.TEACH_VOL43)
				{															
					if(!gameMain.gameTeaching.teachingArrary[type[1]])
					{
						gameMain.combo.completeCombo();
						
						int x = gameMain.combo.getCombox()+gameMain.combo.getComboWidth()/2;
						
						int y = gameMain.combo.getComboy()+gameMain.combo.getComboHeight()/2;
						
						gameMain.gameTeaching.setGameTeaching(type[1], x, y, gameMain.combo.getComboWidth(), gameMain.combo.getComboHeight(),
								LangUtil.getLangString(LangDefineClient.GUIDE_43), GameTeaching.HAND_MOVE_STATE_1, (int)(500*GameConfig.f_zoomy));						
					}
				}
				
				layerIndex ++;	
				break;	
				
			case GameScript.ClothTouch:				
				gameMain.gameTeaching.setIsTouch(false);
				
				layerIndex ++;	
				break;	
				
			case GameScript.OpenTouch:				
//				gameMain.isTouch = true;
				
				layerIndex ++;	
				break;		
				
			case GameScript.EnterTeach:	
				if(gameMain.gameTeaching.teachingArrary[type[1]])
				{
					layer ++;				
					layerIndex = 0;
				}
				else layerIndex ++;				
				break;
			
			case GameScript.GoTo:
				gotoIndex = type[3];				
				layerIndex = ExternalMethods.throwDice(type[1], type[2]);												
				break;
		}
	}
	
	public void addMonsters(int kind, GameMain gameMain, int x, int y)
	{
//		x = (int)(x*GameConfig.f_zoomx); 
//		
//		y = (int)(y*GameConfig.f_zoomy); 
		
		switch(kind)
		{
			case CoolEditDefine.Enemy_CHG:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_CHG, x, y, 0, 
						(int)(500*GameConfig.f_zoomy), 4, 0, 1);
				break;
				
			case CoolEditDefine.Enemy_DS:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_DS, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_HZ:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_HZ, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_MEGICWATER:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_MEGICWATER, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 4, 125, 1);
				break;	
				
			case CoolEditDefine.Enemy_MIMI:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_MIMI, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 2, 50, 1);						
				break;	
				
			case CoolEditDefine.Enemy_MM:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_MM, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 100, 1);
				break;	
				
			case CoolEditDefine.Enemy_MMB1:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_MMB1, 0, 0, 0, 
						y, 20, 0, 1);	
				break;	
				
			case CoolEditDefine.Enemy_MMB2:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_MMB2, 0, 0, 0, 
						y, 16, 0, 1);	
				break;	
				
			case CoolEditDefine.Enemy_MMB3:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_MMB3, 0, 0, 0, 
						y, 12, 0, 1);	
				break;	
				
			case CoolEditDefine.Enemy_MMB4:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_MMB4, 0, 0, 0, 
						y, 12, 0, 1);	
				break;	
				
			case CoolEditDefine.Enemy_SHHM:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_SHHM, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 5, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_SHHT:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_SHHT, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;		
				
			case CoolEditDefine.Enemy_SHMM:
				if(gameMain.stageIndex==0)
				{
					gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_SHMM+10000, x, y, 1, 
							gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 2, 50, 1);						
				}	
				else
				{
					gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_SHMM, x, y, 1, 
							gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 2, 50, 1);
				}
				break;	
				
			case CoolEditDefine.Enemy_SHX:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_SHX, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_SHZ:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_SHZ, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_SHZY:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_SHZY, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 250, 1);
				break;	
				
			case CoolEditDefine.Enemy_SIREN:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_SIREN, 0, 0, 1, 
						(int)(GameLibrary.getIntRandom(200, 350)*GameConfig.f_zoomy), 8, 50, -100);
				break;	
				
			case CoolEditDefine.Enemy_X:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_X, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_YGDS:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_YGDS, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_YGHZ:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_YGHZ, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_YGMM:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_YGMM, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 2, 50, 1);	
				break;		
				
			case CoolEditDefine.Enemy_YGX:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_YGX, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;		
				
			case CoolEditDefine.Enemy_YGY:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_YGY, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 250, 1);
				break;	
				
			case CoolEditDefine.Enemy_YGZ:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_YGZ, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;	
				
			case CoolEditDefine.Enemy_Z:
				gameMain.gameMonster.addEnemy(CoolEditDefine.Enemy_Z, x, y, 1, 
						gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 1);
				break;		
		}				
	}
	
	public ArrayList<Integer> getStageMonsterKind()
	{		
		return stageMonsterKind;
	}
	
	public void setStageMonsterKind()
	{	
//		if(layer<gameScript.ScriptData[stageId].length)
		for(int i=0;i<gameScript.ScriptData[stageId].length;i++)		
		{
			for(int j=0;j<gameScript.ScriptData[stageId][i].length;j++)
			{
				if(gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh||
				   gameScript.ScriptData[stageId][i][j][0]==GameScript.Refresh1)
				{
					if(gameScript.ScriptData[stageId][i][j][1]!=CoolEditDefine.Enemy_YGY&&
					   gameScript.ScriptData[stageId][i][j][1]!=CoolEditDefine.Enemy_SIREN&&
					   gameScript.ScriptData[stageId][i][j][1]!=CoolEditDefine.Enemy_SHZY&&
					   gameScript.ScriptData[stageId][i][j][1]!=CoolEditDefine.Enemy_MM&&
					   gameScript.ScriptData[stageId][i][j][1]!=CoolEditDefine.Enemy_MEGICWATER&&
					   gameScript.ScriptData[stageId][i][j][1]!=CoolEditDefine.Enemy_CHG)
					{					
						int index = stageMonsterKind.size();
						
						while(index>0)
						{
							index --;
							
							if(stageMonsterKind.get(index)==gameScript.ScriptData[stageId][i][j][1])
							{
								if(index==0)
									index = 1;
								
								break;
							}
						}
						
						if(index==0)
						{
							int kind = gameScript.ScriptData[stageId][i][j][1];
							
							stageMonsterKind.add(kind);
						}
					}
				}
			}	
		}
	}
	
	public void summonsMonster(int num, GameMain gameMain)
	{
		for(int i=0;i<num;i++)
		{
			int kind = GameLibrary.getIntRandom(0, getStageMonsterKind().size()-1);
			
			addMonsters(getStageMonsterKind().get(kind), gameMain, GameLibrary.getIntRandom(50, GameConfig.GameScreen_Width-50), 
					 (int)(GameLibrary.getIntRandom(200, 350)*GameConfig.f_zoomy));
		}
	}
	
	public void summonsMonster(int num, GameMain gameMain, int x, int y)
	{
		for(int i=0;i<num;i++)
		{
			int kind = GameLibrary.getIntRandom(0, getStageMonsterKind().size()-1);
			
			addMonsters(getStageMonsterKind().get(kind), gameMain, x, y);
		}
	}
	
	public int getGameTime()
	{
		return gameScript.ScriptTime[stageId];
	}	
}
