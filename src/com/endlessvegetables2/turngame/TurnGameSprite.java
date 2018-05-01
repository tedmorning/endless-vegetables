package com.endlessvegetables2.turngame;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditData;
import com.socogame.coolEdit.CoolEditDefine;


public class TurnGameSprite extends Sprite{
	
	public TurnGameSprite()
	{
	}
	
	public TurnGameSprite(Bitmap bitmap)
	{
		 
		this.bitmap = bitmap;
	}
	
	public void updataSprite(){						
		if(state>0&&kind>-1){			
//			frames++;						
					
			sirenLight.run();
	
//			if(speedDownTime>0)
//			{
//				if(speedDownTime%5!=0)
//				{
//					return;
//				}	
//			}
			
			if(kind == CoolEditDefine.Enemy_Z||
			   kind == CoolEditDefine.Enemy_SHZ||
			   kind == CoolEditDefine.Enemy_YGZ)
			{							
				if(actionName==Enemy_action05)//冲刺
				{			
					if(dizzinessTime<=0)//没有眩晕
					{
						frames++;
						
						if(y!=orgbyMoveStop)
						{
							frames++;
							
							if(frames>5)
							{
								y += 10;
								
								if(y>=orgbyMoveStop)
								{
									y=orgbyMoveStop;
								}
							}
						}
					}
					else
					{					
						int l = (int)(y+ExternalMethods.throwDice(150, 200));
						
						byMoveStop = l>orgbyMoveStop?orgbyMoveStop:l;
						
						changeAction(Enemy_action03);
						
						setState(SPRITE_STATE_STAND);
					}
				}
				else
				{					
					if(dizzinessTime<=0||//没有眩晕
					   actionName==Enemy_action0||
					   actionName==Enemy_action02)
					{
						frames++;						
					}		
				}
			}
			else if(kind == CoolEditDefine.Enemy_SHHM)
			{				
				if(actionName==Enemy_action18)
				{
					if(dizzinessTime<=0)//没有眩晕
					{
						frames++;	
						
						TurnGameMonsterMove.Jump(this, false);
					}
					else
					{
						changeAction(Enemy_action03);
						
						setState(SPRITE_STATE_STAND);
					}
				}
				else if(actionName==Enemy_action19)
				{
					if(dizzinessTime<=0)//没有眩晕
					{
						frames++;	
						
						TurnGameMonsterMove.Jump(this, true);
					}
					else
					{
						changeAction(Enemy_action03);
						
						setState(SPRITE_STATE_STAND);
					}
				}
				else
				{
					if(dizzinessTime<=0||//没有眩晕
					   actionName==Enemy_action0||
					   actionName==Enemy_action02)
					{
						frames++;						
					}	
				}								
			}
			else
			{								
				if(dizzinessTime<=0||//没有眩晕
				   actionName==Enemy_action0||
				   actionName==Enemy_action02)
				{
					frames++;						
				}										
			}
					
//			System.out.println("====>>>>"+kind+"====>>>>"+actionName);
			
			if(frames>=CoolEditData.npcItem0[kind][actionName].length*framesjiange){
				frames=0;
				
				if(TurnGameSpriteLibrary.isEnemy(kind) == TurnGameSpriteLibrary.isPlayer)
				{
					if(kind == CoolEditDefine.Player_FQ||
					   kind == CoolEditDefine.Player_FQ_2||
					   kind == CoolEditDefine.Player_FQ_3||
					   kind == CoolEditDefine.Player_WD||
					   kind == CoolEditDefine.Player_WD_2||
					   kind == CoolEditDefine.Player_WD_3||
					   kind == CoolEditDefine.Player_LJ||
					   kind == CoolEditDefine.Player_LJ_2||
					   kind == CoolEditDefine.Player_LJ_3||
					   kind == CoolEditDefine.Player_YC||
					   kind == CoolEditDefine.Player_YC_2||
					   kind == CoolEditDefine.Player_YC_3||
					   kind == CoolEditDefine.Player_LB||
					   kind == CoolEditDefine.Player_LB_2||
					   kind == CoolEditDefine.Player_LB_3||
					   kind == CoolEditDefine.Player_TD||
					   kind == CoolEditDefine.Player_TD_2||
					   kind == CoolEditDefine.Player_TD_3||
					   kind == CoolEditDefine.Player_MG||
					   kind == CoolEditDefine.Player_MG_2||
					   kind == CoolEditDefine.Player_MG_3||
					   kind == CoolEditDefine.Player_HC||
					   kind == CoolEditDefine.Player_HC_2||
					   kind == CoolEditDefine.Player_HC_3||
					   kind == CoolEditDefine.Player_ZS||
					   kind == CoolEditDefine.Player_ZS_2||
					   kind == CoolEditDefine.Player_ZS_3||
					   kind == CoolEditDefine.Player_NG||
					   kind == CoolEditDefine.Player_NG_2||
					   kind == CoolEditDefine.Player_NG_3)
					{
//						if(actionName==5||actionName==4)
//						{
//							setState(SPRITE_STATE_NONE);
//						}
					}
					
					if(kind == CoolEditDefine.Player_TD||
					   kind == CoolEditDefine.Player_TD_2||
					   kind == CoolEditDefine.Player_TD_3)
					{
						if(actionName==7)
						{
							setState(SPRITE_STATE_NONE);
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.presss, 0);
						}
					}
					else 
					if(kind==CoolEditDefine.Player_MG||
					   kind==CoolEditDefine.Player_MG_2||
					   kind==CoolEditDefine.Player_MG_3)	
					{												
						if(actionName==7)
						{
							if(lifeTime>0)
							{
								changeAction(6);
								
								clearTouchSprite();
							}
							else	
								setState(SPRITE_STATE_NONE);
						}
					}
					else 
					if(kind==CoolEditDefine.Player_HC||
					   kind==CoolEditDefine.Player_HC_2||
					   kind==CoolEditDefine.Player_HC_3)	
					{												
						if(actionName==7)
						{								
							setState(SPRITE_STATE_NONE);
						}
					}					
				}
				
				if(TurnGameSpriteLibrary.isEnemy(kind) == TurnGameSpriteLibrary.isEnemy)
				{
					if(kind == CoolEditDefine.Enemy_SHHM)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01)
						{					    						    						    	
					    	changeAction(Enemy_action03);
								
							setState(SPRITE_STATE_STAND);	
									
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
							
//							size = 1f;
						}
						else if(actionName==Enemy_action05)
						{																	
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
							
//							size = 1.2f;
						}
						else if(actionName==Enemy_action18||
								actionName==Enemy_action19)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}						
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_SHHT)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01||
					    		actionName==Enemy_action15)
						{					    						    	
					    	if(byMoveWaitingTime>0)
					    	{
					    		changeAction(Enemy_action03);
					    		
								setState(SPRITE_STATE_STAND);
					    	}
					    	else
					    	{
					    		if(actionName==Enemy_action01)
					    			changeAction(Enemy_action04);
					    		else 
					    			changeAction(Enemy_action16);
								
								setState(SPRITE_STATE_MOVE);
					    	}
//					    	
//					    	if(attackTime<=0)
//							{
//								attackTime = orgAttackTime;
//							}
					    	
					    	if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05||actionName==Enemy_action17)
						{			
							if(!isUpState)
							TurnGameMain.spriteLattice.spriteLatticeLostBlood(this);
							
							setState(SPRITE_STATE_NONE);	
							
							if(!VeggiesData.isMuteSound())
							GameMedia.playSound(R.raw.littlebombs, 0);
							
							life = 0;
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_MIMI||
					   kind == CoolEditDefine.Enemy_SHMM||
					   kind == CoolEditDefine.Enemy_YGMM)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01)
						{					    						    	
					    	if(byMoveWaitingTime>0)
					    	{
					    		changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
					    	}
					    	else
					    	{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
					    	}
					    	
					    	if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
					    	
					    	if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05)
						{			
							if(!isUpState)
							TurnGameMain.spriteLattice.spriteLatticeLostBlood(this);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_DS||
					   kind == CoolEditDefine.Enemy_YGDS)
					{
						if(actionName==Enemy_action0||
						   actionName==Enemy_action01||
						   actionName==Enemy_action07)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(actionName==Enemy_action01)
							{
								if(speedDownTime>0)
						    	{
						    		setSlowDown(true);
						    	}
							}
						}
						else if(actionName==Enemy_action05)
						{										
							if(!isUpState)
							TurnGameMain.spriteLattice.spriteLatticeLostBlood(this);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
						else if(actionName==Enemy_action06)
						{
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
						}
					}
					else 
					if(kind == CoolEditDefine.Enemy_X||
					   kind == CoolEditDefine.Enemy_SHX||
					   kind == CoolEditDefine.Enemy_YGX)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01)
						{
					    	if(byMoveWaitingTime>0)
					    	{
					    		changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
					    	}
					    	else
					    	{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
					    	}
					    	
					    	if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
					    	
					    	if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05)
						{			
//							if(y!=orgbyMoveStop)
//							{
//								changeAction(Enemy_action03);
//								
//								setState(SPRITE_STATE_STAND);
//								
//								if(kind == SpriteLibrary.Enemy_SHX)
//								GameMonster.shxSkillTime = 75;
//							}
//							else
//							{
								if(!isUpState)								
								TurnGameMain.spriteLattice.spriteLatticeLostBlood(this);
								
								attackTime = orgAttackTime;
//							}
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}
						else if(actionName==Enemy_action20)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
							
							if(kind == CoolEditDefine.Enemy_SHX)
								TurnGameMonster.shxSkillTime = 75;
							
							if(kind == CoolEditDefine.Enemy_X)
							{
								TurnGameMonster.xIsSkill = true;
								
								if(!VeggiesData.isMuteSound())
								GameMedia.playSound(R.raw.bear1s, 0);
							}
						}
					}
					else
					if(kind == CoolEditDefine.Enemy_Z||
					   kind == CoolEditDefine.Enemy_SHZ||
					   kind == CoolEditDefine.Enemy_YGZ)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
					    else if(actionName==Enemy_action01)
						{
					    	if(byMoveWaitingTime>0)
					    	{
					    		int l = (int)(y+ExternalMethods.throwDice(150, 200));
								
								byMoveStop = l>orgbyMoveStop?orgbyMoveStop:l;
					    		
					    		changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
					    	}
					    	else
					    	{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
					    	}
					    	
					    	if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
					    	
					    	if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05)
						{			
							if(y!=orgbyMoveStop)
							{
								int l = (int)(y+ExternalMethods.throwDice(150, 200));
								
								byMoveStop = l>orgbyMoveStop?orgbyMoveStop:l;
								
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}
							else
							{
								if(!isUpState)
								TurnGameMain.spriteLattice.spriteLatticeLostBlood(this);
								
								attackTime = orgAttackTime;
							}
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}
						else if(actionName==Enemy_action21)
						{
							
						}						
					}
					else
					if(kind == CoolEditDefine.Enemy_HZ||
					   kind == CoolEditDefine.Enemy_YGHZ)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{
							if(attackTime>0)
							{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
							}
							else
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}	
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05)
						{				
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_HZXJ
							||kind == CoolEditDefine.Enemy_PANGXIEZIDAN)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action05);
							
							setState(SPRITE_STATE_ATTACK);							
						}					
					}
					else
					if(kind == CoolEditDefine.Enemy_MM)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{							
							if(attackTime>0)
							{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
							}
							else
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}	
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05||
								actionName==Enemy_action08||
								actionName==Enemy_action09||
								actionName==Enemy_action10)
						{				
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_MMJS||
					   kind == CoolEditDefine.Enemy_SHZYXZ)
					{
						
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action05);
							
							setState(SPRITE_STATE_ATTACK);							
						}
					}
					else
					if(kind == CoolEditDefine.Enemy_MMB1||
					   kind == CoolEditDefine.Enemy_MMB2||
					   kind == CoolEditDefine.Enemy_MMB3||
				       kind == CoolEditDefine.Enemy_MMB4)
					{
						if(actionName==Enemy_action12)
						{
							changeAction(Enemy_action11);
							
							setState(SPRITE_STATE_MOVE);							
						}
						else if(actionName==Enemy_action14)
						{
							changeAction(Enemy_action13);
							
							setState(SPRITE_STATE_MOVE);							
						}
					}
					else
					if(kind == CoolEditDefine.Enemy_CHG)
					{
						if(actionName==Enemy_action01)
						{
							life = 0;
							
							setState(SPRITE_STATE_NONE);													
						}
					}else 
						if(kind == CoolEditDefine.Enemy_PANGXIE1||
								kind == CoolEditDefine.Enemy_PANGXIE2)
						{
							if(actionName==Enemy_action0)
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);							
							}
							else if(actionName==Enemy_action01)
							{							
								if(attackTime>0)
								{
									changeAction(Enemy_action04);
									
									setState(SPRITE_STATE_MOVE);
								}
								else
								{
									changeAction(Enemy_action03);
									
									setState(SPRITE_STATE_STAND);
								}	
								
								if(attackTime<=0)
								{
									attackTime = orgAttackTime;
								}
								
								if(speedDownTime>0)
						    	{
						    		setSlowDown(true);
						    	}
							}
							else if(actionName==Enemy_action05||
									actionName==Enemy_action08||
									actionName==Enemy_action10)
							{				
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
								
								attackTime = orgAttackTime;											
							}
							else if(actionName==Enemy_action02
									||actionName==Enemy_action07)
							{
								setState(SPRITE_STATE_NONE);
							}	
					}	
					else 
					if(kind == CoolEditDefine.Enemy_YGXTM)//月光熊藤蔓
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{							
							changeAction(Enemy_action03);
								
							setState(SPRITE_STATE_STAND);
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}
					}	
					else
					if(kind == CoolEditDefine.Enemy_SHZY)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{
							if(attackTime>0)
							{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
							}
							else
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}	
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05||
								actionName==Enemy_action22||
								actionName==Enemy_action23)
						{				
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else 
					if(kind == CoolEditDefine.Enemy_SHZYCS)//深海章鱼触手
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{							
							changeAction(Enemy_action03);
								
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action05)
						{				
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);			
							
							if(!isUpState)
							TurnGameMain.spriteLattice.spriteLatticeLostBlood(this);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else
					if(kind == CoolEditDefine.Enemy_YGY)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}
						else if(actionName==Enemy_action01)
						{							
							if(attackTime>0)
							{
								changeAction(Enemy_action04);
								
								setState(SPRITE_STATE_MOVE);
							}
							else
							{
								changeAction(Enemy_action03);
								
								setState(SPRITE_STATE_STAND);
							}	
							
							if(attackTime<=0)
							{
								attackTime = orgAttackTime;
							}
							
							if(speedDownTime>0)
					    	{
					    		setSlowDown(true);
					    	}
						}
						else if(actionName==Enemy_action05||
								actionName==Enemy_action24||
								actionName==Enemy_action25||
								actionName==Enemy_action26)
						{				
							changeAction(Enemy_action04);
							
							setState(SPRITE_STATE_MOVE);
							
							attackTime = orgAttackTime;											
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}	
					}
					else
					if(kind == CoolEditDefine.Enemy_YGYYM)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action05);
							
							setState(SPRITE_STATE_ATTACK);							
						}
						else if(actionName==Enemy_action02)
						{
							setState(SPRITE_STATE_NONE);
						}								
					}
					else
					if(kind == CoolEditDefine.Enemy_YGYKS)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action05);
							
							setState(SPRITE_STATE_ATTACK);							
						}											
					}	
					else
					if(kind == CoolEditDefine.Enemy_YGYJF)
					{
						if(actionName==Enemy_action0||
						   actionName==Enemy_action01)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);							
						}											
					}
					else
					if(kind == CoolEditDefine.Enemy_SIREN)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}						
						else if(actionName==Enemy_action02)
						{							
							setState(SPRITE_STATE_NONE);													
						}
						else if(actionName==Enemy_action01
//								||actionName==Enemy_action28
								)
						{
//							if(actionName==Enemy_action01)
								changeAction(Enemy_action03);
//							else
//								changeAction(Enemy_action29);
																										
//							if(attackTime>125)
//								setState(SPRITE_STATE_MOVE);
//							else
//								setState(SPRITE_STATE_ATTACK);
//							
//							byMoveWaitingTime = 25;
								
							setState(SPRITE_STATE_STAND);
							
							byMoveWaitingTime = 25;
							
							sirenLight.setShow(false);
						}						
					}
					else
					if(kind == CoolEditDefine.Enemy_MEGICWATER)
					{
						if(actionName==Enemy_action0)
						{
							changeAction(Enemy_action03);
							
							setState(SPRITE_STATE_STAND);
						}
//						else if(actionName==Enemy_action01)
//						{		
//							if(linkNumber==0)
//							{
//								changeAction(Enemy_action03);
//								
//								setState(SPRITE_STATE_STAND);	
//							}
//							else
//							{
//								changeAction(Enemy_action27);
//								
//								setState(SPRITE_STATE_MOVE);	
//							}
//						}
						else if(actionName==Enemy_action02)
						{							
							setState(SPRITE_STATE_NONE);													
						}
					}					
				}
				
				if(TurnGameSpriteLibrary.isEnemy(kind)==TurnGameSpriteLibrary.isEffect)
				{
//					if(kind==SpriteLibrary.Effect_ICESTATE)
//					{
//						if(actionName==1)
//						{
//							setState(SPRITE_STATE_NONE);
//						}
//					}
//					else if(kind==SpriteLibrary.Effect_COLD)
//					{
//						if(actionName==0)
//						{
//							changeAction(2);
//						}
//						else if(actionName==1)
//						{
//							setState(SPRITE_STATE_NONE);
//						}						
//					}
					if(kind==CoolEditDefine.SMALL_CARD_BOX)
					{
						if(actionName==1)
						{
							changeAction(0);
						}
					}
					else if(kind==CoolEditDefine.SMALL_CARD)
					{
						
					}
					else if(kind==CoolEditDefine.Effect_ICESTATELV1||
					   kind==CoolEditDefine.Effect_ICESTATELV2||
					   kind==CoolEditDefine.Effect_ICESTATELV3)
					{
						if(actionName==1)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else if(/*kind==SpriteLibrary.Effect_SUMMON||*/
							kind==CoolEditDefine.Effect_SHIELD||
							kind==CoolEditDefine.Effect_SLOWDOWN||
							kind==CoolEditDefine.Effect_DIZZINESS||
							kind==CoolEditDefine.Effect_HEADFIRE)
					{
						
					}
					else
					{
						if(actionName==0)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
				}
				
				if(TurnGameSpriteLibrary.isEnemy(kind)==TurnGameSpriteLibrary.isObstruct)
				{
					if(/*kind==SpriteLibrary.Effect_COLDBOMB||*/
					   kind==CoolEditDefine.Effect_BOMB||					  
					   kind==CoolEditDefine.Effect_BOMBLV1||
					   kind==CoolEditDefine.Effect_BOMBLV2||
					   kind==CoolEditDefine.Effect_BOMBLV3)
					{
						if(actionName == 0)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
					else 
					if(kind==CoolEditDefine.Effect_SPRINGBOARD)
					{
						if(actionName == 1)
						{
							changeAction(0);
						}
					}
					else 
					if(/*kind==SpriteLibrary.Effect_COLDBOMB||*/
					   kind==CoolEditDefine.Effect_COLD)
					{
						if(actionName == 1)
						{
							setState(SPRITE_STATE_NONE);
						}
					}
				}
			}
		}
	}
		
}
