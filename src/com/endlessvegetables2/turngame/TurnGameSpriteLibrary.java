package com.endlessvegetables2.turngame;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.ExternalMethods;
import com.kokatlaruruxi.wy.GameMain;
import com.kokatlaruruxi.wy.GamePowerCard;
import com.kokatlaruruxi.wy.SpriteLibrary;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameMedia;
import com.socogame.coolEdit.CoolEditDefine;

public class TurnGameSpriteLibrary extends SpriteLibrary{
	
	public static int GetGoldenPercent(int kind, TurnGameMain gameMain) {		
		
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGY:	
			return (int)(100*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHZYCS:
			return (int)(10*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHZY:
			return (int)(50*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_YGXTM:	
			return (int)(10*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_YGX:
			return (int)(50*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_YGZ:
			return (int)(30*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_YGHZ:
			return (int)(30*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_YGDS:
			return (int)(30*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_YGMM:
			return (int)(20*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHHM:	
			return (int)(30*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_SHHT:	
			return (int)(40*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHX:	
			return (int)(50*GamePowerCard.addGameGolden);
		
		case CoolEditDefine.Enemy_SHMM:
			return (int)(25*GamePowerCard.addGameGolden);	
		
		case CoolEditDefine.Enemy_SHZ:
			return (int)(25*GamePowerCard.addGameGolden);		
			
		case CoolEditDefine.Enemy_MIMI:
			return (int)(30*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_DS:
			return (int)(30*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_X:	
			return (int)(60*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_Z:	
			return (int)(36*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_HZ:	
			return (int)(30*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_MM:
			return (int)(60*GamePowerCard.addGameGolden);		
			
		case CoolEditDefine.Enemy_SIREN:	
			return (int)(50*GamePowerCard.addGameGolden);	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return (int)(10*GamePowerCard.addGameGolden);		
		case CoolEditDefine.Enemy_PANGXIE2:	
			return (int)(10*GamePowerCard.addGameGolden);	
		}
		
		return 0;
	}	
	
	public static int GetGoldenNumber(int kind, TurnGameMain gameMain) 
	{				
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGY:	
			return 1;
		
		case CoolEditDefine.Enemy_SHZYCS:
			return 1;
		
		case CoolEditDefine.Enemy_SHZY:
			return 1;
		
		case CoolEditDefine.Enemy_YGXTM:	
			return 1;	
		
		case CoolEditDefine.Enemy_YGX:
			return 1;
		
		case CoolEditDefine.Enemy_YGZ:
			return 1;
		
		case CoolEditDefine.Enemy_YGHZ:
			return 1;	
		
		case CoolEditDefine.Enemy_YGDS:
			return 1;
		
		case CoolEditDefine.Enemy_YGMM:
			return 1;
		
		case CoolEditDefine.Enemy_SHHM:	
			return 1;	
		
		case CoolEditDefine.Enemy_SHHT:	
			return 1;
			
		case CoolEditDefine.Enemy_SHX:	
			return 1;
		
		case CoolEditDefine.Enemy_SHMM:
			return 1;	
		
		case CoolEditDefine.Enemy_SHZ:
			return 1;		
			
		case CoolEditDefine.Enemy_MIMI:
			return 1;	
			
		case CoolEditDefine.Enemy_DS:
			return 1;	
			
		case CoolEditDefine.Enemy_X:	
			return 1;	
			
		case CoolEditDefine.Enemy_Z:	
			return 1;	
			
		case CoolEditDefine.Enemy_HZ:	
			return 1;	
			
		case CoolEditDefine.Enemy_MM:
			return 1;	
			
		case CoolEditDefine.Enemy_SIREN:	
			return 1;	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return 1;		
		case CoolEditDefine.Enemy_PANGXIE2:	
			return 1;	
		}
		
		return 0;
	}
	
	public static int GetNumber(int kind,boolean isturn) {
		float tempaddGameNumber;
		tempaddGameNumber=TurnGamePowerCard.addGameNumber;
		
		switch (kind) {
		case CoolEditDefine.Enemy_YGY:	
			return (int)(ExternalMethods.throwDice(150, 250)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_SHZYCS:
			return (int)(ExternalMethods.throwDice(30, 60)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_SHZY:
			return (int)(ExternalMethods.throwDice(150, 250)*tempaddGameNumber);
			
		case CoolEditDefine.Enemy_YGXTM:	
			return (int)(ExternalMethods.throwDice(30, 60)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_YGX:
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_YGZ:
			return (int)(ExternalMethods.throwDice(25, 40)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_YGHZ:
			return (int)(ExternalMethods.throwDice(30, 60)*tempaddGameNumber);	
		
		case CoolEditDefine.Enemy_YGDS:
			return (int)(ExternalMethods.throwDice(30, 50)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_YGMM:
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);
		
		case CoolEditDefine.Enemy_SHHM:	
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);	
		
		case CoolEditDefine.Enemy_SHHT:	
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);	
		
		case CoolEditDefine.Enemy_SHMM:
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);	
		
		case CoolEditDefine.Enemy_SHZ:
			return (int)(ExternalMethods.throwDice(25, 40)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_SHX:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);
			
		case CoolEditDefine.Enemy_MIMI:
			return (int)(ExternalMethods.throwDice(20, 35)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_DS:
			return (int)(ExternalMethods.throwDice(30, 50)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_X:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_Z:	
			return (int)(ExternalMethods.throwDice(25, 40)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_HZ:
			return (int)(ExternalMethods.throwDice(30, 60)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_MM:
			return (int)(ExternalMethods.throwDice(150, 250)*tempaddGameNumber);		
			
		case CoolEditDefine.Enemy_SIREN:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);	
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);		
			
		case CoolEditDefine.Enemy_PANGXIE2:	
			return (int)(ExternalMethods.throwDice(60, 100)*tempaddGameNumber);
		}
		
		return 0;
	}
	
	public static int GetHP(int kind) {
		float tempBloodLower=0;
		tempBloodLower=TurnGamePowerCard.monsterBloodLower;
		
		switch (kind) {
		
		case CoolEditDefine.Enemy_YGYJF:	
			return 999999;	
		
		case CoolEditDefine.Enemy_YGYKS:
			return 10;
			
		case CoolEditDefine.Enemy_YGYYM:
			return 1;
		
		case CoolEditDefine.Enemy_YGY:	
			
			return (int)(5000*tempBloodLower);
		
		case CoolEditDefine.Enemy_SHZYCS:
			return (int)(1*tempBloodLower);	
		
		case CoolEditDefine.Enemy_SHZY:
			return (int)(800*tempBloodLower);	
		
		case CoolEditDefine.Enemy_YGXTM:	
			return (int)(10*tempBloodLower);	
		
		case CoolEditDefine.Enemy_YGX:
			return (int)(250*tempBloodLower);
		
		case CoolEditDefine.Enemy_YGZ:
			return (int)(80*tempBloodLower);
			
		case CoolEditDefine.Enemy_YGHZ:
			return (int)(60*tempBloodLower);
			
		case CoolEditDefine.Enemy_YGDS:
			return (int)(70*tempBloodLower);
			
		case CoolEditDefine.Enemy_YGMM:
			return (int)(60*tempBloodLower);
		
		case CoolEditDefine.Enemy_SHHM:				
			return (int)(24*tempBloodLower);
		
		case CoolEditDefine.Enemy_SHHT:	
			return (int)(35*tempBloodLower);	
		
		case CoolEditDefine.Enemy_SHX:
			return (int)(48*tempBloodLower);
			
		case CoolEditDefine.Enemy_SHZ:
			return (int)(12*tempBloodLower);	
			
		case CoolEditDefine.Enemy_SHMM:	
			return (int)(6*tempBloodLower);	
		
		case CoolEditDefine.Enemy_MIMI:		
			return (int)(18*tempBloodLower);	
			
		case CoolEditDefine.Enemy_DS:
			return (int)(25*tempBloodLower);	
			
		case CoolEditDefine.Enemy_X:	
			return (int)(84*tempBloodLower);
			
		case CoolEditDefine.Enemy_Z:	
			return (int)(40*tempBloodLower);	
			
		case CoolEditDefine.Enemy_HZ:	
			return (int)(20*tempBloodLower);	
			
		case CoolEditDefine.Enemy_HZXJ:	
			return 1;	
		case CoolEditDefine.Enemy_PANGXIEZIDAN:	
			return 1;	
			
		case CoolEditDefine.Enemy_MM:
			return (int)(1200*tempBloodLower);
			
		case CoolEditDefine.Enemy_MMJS:
			return 1;	
			
		case CoolEditDefine.Enemy_MMB1:
		case CoolEditDefine.Enemy_MMB2:
		case CoolEditDefine.Enemy_MMB3:
		case CoolEditDefine.Enemy_MMB4:
			return 1;	
			
		case CoolEditDefine.Enemy_CHG:
			return 1;
			
		case CoolEditDefine.Enemy_SHZYXZ:	
			return 1;	
			
		case CoolEditDefine.Enemy_SIREN:	
			return (int)(100*tempBloodLower);
			
		case CoolEditDefine.Enemy_MEGICWATER:	
			return (int)(60*tempBloodLower);	
			
		case CoolEditDefine.Enemy_PANGXIE1:
			return (int)(50*tempBloodLower);	
		case CoolEditDefine.Enemy_PANGXIE2:
			return (int)(30*tempBloodLower);	
		}
		
		return 1;
	}
	
	
	public static int GetAttack(int kind) {		
		
		byte rate = 1;
				
		if(ExternalMethods.throwDice(0, 99)<(TurnGamePowerCard.critRate+VeggiesData.getSlingshot_crit()))
			rate = 2;		
		
		switch (kind) {
		
		case CoolEditDefine.Player_FQ:
                        return 6*rate;
		case CoolEditDefine.Player_FQ_2:
                        return 12*rate;
		case CoolEditDefine.Player_FQ_3:
                        return 24*rate;
		case CoolEditDefine.Player_WD:
                        return 6*rate;
		case CoolEditDefine.Player_WD_2:
                        return 10*rate;
		case CoolEditDefine.Player_WD_3:
                        return 14*rate;
		case CoolEditDefine.Player_LJ:
                        return 12*rate;
		case CoolEditDefine.Player_LJ_2:
                        return 24*rate;
		case CoolEditDefine.Player_LJ_3:
                        return 36*rate;
		case CoolEditDefine.Player_YC:
                        return 0;
		case CoolEditDefine.Player_YC_2:
                        return 0;
		case CoolEditDefine.Player_YC_3:
                        return 0;
		case CoolEditDefine.Player_LB:
                        return 7*rate;
		case CoolEditDefine.Player_LB_2:
                        return 11*rate;
		case CoolEditDefine.Player_LB_3:
                        return 15*rate;
		case CoolEditDefine.Player_TD:
                        return 11*rate;
		case CoolEditDefine.Player_TD_2:
                        return 19*rate;
		case CoolEditDefine.Player_TD_3:
                        return 26*rate;
		case CoolEditDefine.Player_MG:
                        return 14*rate;
		case CoolEditDefine.Player_MG_2:
                        return 22*rate;
		case CoolEditDefine.Player_MG_3:
                        return 30*rate;
		case CoolEditDefine.Player_HC:
                        return 18*rate;
		case CoolEditDefine.Player_HC_2:
                        return 33*rate;
		case CoolEditDefine.Player_HC_3:
                        return 51*rate;
		case CoolEditDefine.Player_ZS:
                        return 20*rate;
		case CoolEditDefine.Player_ZS_2:
                        return 40*rate;
		case CoolEditDefine.Player_ZS_3:
                         return 60*rate;
//			if(ExternalMethods.throwDice(0, 99)<GamePowerCard.critRate)
				//rate = 2;						
			//return ExternalMethods.throwDice(10, 30)*rate;	
			
		case CoolEditDefine.Player_NG:
		case CoolEditDefine.Player_NG_2:
		case CoolEditDefine.Player_NG_3:					
			return ExternalMethods.throwDice(250, 300)*rate;
			
		case CoolEditDefine.Enemy_YGYKS:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 15;	
			
		case CoolEditDefine.Enemy_YGYYM:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 20;	
			
		case CoolEditDefine.Enemy_YGY:	
			return 15;	
			
		case CoolEditDefine.Enemy_SHZYCS:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 2;	
			
		case CoolEditDefine.Enemy_SHZYXZ:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 5;			
			
		case CoolEditDefine.Enemy_YGX:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 80;	
			
		case CoolEditDefine.Enemy_YGZ:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 45;	
			
		case CoolEditDefine.Enemy_YGHZ:
			return 30;	
			
		case CoolEditDefine.Enemy_YGDS:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 25;	
			
		case CoolEditDefine.Enemy_YGMM:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 20;	
			
		case CoolEditDefine.Enemy_SHHM:	
			return 0;	
			
		case CoolEditDefine.Enemy_SHHT:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 10;		
			
		case CoolEditDefine.Enemy_SHX:	
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 8;
			
		case CoolEditDefine.Enemy_SHZ:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 2;		
			
		case CoolEditDefine.Enemy_SHMM:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 2;						
			
		case CoolEditDefine.Enemy_MIMI:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 4;	
			
		case CoolEditDefine.Enemy_DS:		
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 5;	
			
		case CoolEditDefine.Enemy_X:	
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 12;
			
		case CoolEditDefine.Enemy_Z:	
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 4;	
			
		case CoolEditDefine.Enemy_HZ:	
			return 3;	
			
		case CoolEditDefine.Enemy_MMJS:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_02, 0);
			return 10;	
			
		case CoolEditDefine.Enemy_HZXJ:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 3;	
		case CoolEditDefine.Enemy_PANGXIE1:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 3;
		case CoolEditDefine.Enemy_PANGXIE2:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 3;
		case CoolEditDefine.Enemy_PANGXIEZIDAN:
			if(!VeggiesData.isMuteSound())
			GameMedia.playSound(R.raw.hitwalls_01, 0);
			return 3;
		}
		
		return 0;
	}
	
	public static int Getnengliang(int kind) {
		switch (kind) {
		
		case CoolEditDefine.Player_FQ:
			return 15;
		case CoolEditDefine.Player_FQ_2:
			return 17;
		case CoolEditDefine.Player_FQ_3:
			return 20;
			
		case CoolEditDefine.Player_WD:
			return 17;
		case CoolEditDefine.Player_WD_2:
			return 20;
		case CoolEditDefine.Player_WD_3:
			return 25;
			
		case CoolEditDefine.Player_LJ:
			return 17;
		case CoolEditDefine.Player_LJ_2:
			return 18;
			
		case CoolEditDefine.Player_LJ_3:
			return 20;
			
		case CoolEditDefine.Player_YC:
			return 20;
		case CoolEditDefine.Player_YC_2:
			return 25;
			
		case CoolEditDefine.Player_YC_3:
			return 30;
			
		case CoolEditDefine.Player_LB:
			return 20;
		case CoolEditDefine.Player_LB_2:
			return 25;
			
		case CoolEditDefine.Player_LB_3:
			return 30;
			
		case CoolEditDefine.Player_TD:
			return 60;
		case CoolEditDefine.Player_TD_2:
			return 65;
			
		case CoolEditDefine.Player_TD_3:
			return 70;
			
		case CoolEditDefine.Player_MG:
			return 60;
		case CoolEditDefine.Player_MG_2:
			return 65;
			
		case CoolEditDefine.Player_MG_3:
			return 70;
			
		case CoolEditDefine.Player_HC:
			return 60;
		case CoolEditDefine.Player_HC_2:
			return 65;
					
		case CoolEditDefine.Player_HC_3:
			return 70;	
			
		case CoolEditDefine.Player_ZS:
			return 20;
		case CoolEditDefine.Player_ZS_2:
			return 25;	
			
		case CoolEditDefine.Player_ZS_3:
			return 30;			
			
		case CoolEditDefine.Player_NG:
			return 60;
		case CoolEditDefine.Player_NG_2:
			return 65;
		case CoolEditDefine.Player_NG_3:	
			return 70;
		}
		
		return 0;
	}
	
	public static int Getfantan(int kind) {
		switch (kind) {
		
		case CoolEditDefine.Player_FQ:
			return 5;
		case CoolEditDefine.Player_FQ_2:
			return 6;
		case CoolEditDefine.Player_FQ_3:
			return 8;
			
		case CoolEditDefine.Player_WD:
			return 4;
		case CoolEditDefine.Player_WD_2:
			return 5;
		case CoolEditDefine.Player_WD_3:
			return 6;
			
		case CoolEditDefine.Player_LJ:
			return 4;
		case CoolEditDefine.Player_LJ_2:
			return 5;
			
		case CoolEditDefine.Player_LJ_3:
			return 6;
			
		case CoolEditDefine.Player_YC:
			return 3;
		case CoolEditDefine.Player_YC_2:
			return 4;
			
		case CoolEditDefine.Player_YC_3:
			return 5;
			
		case CoolEditDefine.Player_LB:
			return 2;
		case CoolEditDefine.Player_LB_2:
			return 3;
			
		case CoolEditDefine.Player_LB_3:
			return 4;
			
		case CoolEditDefine.Player_TD:
			return 0;
		case CoolEditDefine.Player_TD_2:
			return 0;
			
		case CoolEditDefine.Player_TD_3:
			return 0;
			
		case CoolEditDefine.Player_MG:
			return 0;
		case CoolEditDefine.Player_MG_2:
			return 0;
			
		case CoolEditDefine.Player_MG_3:
			return 0;
			
		case CoolEditDefine.Player_HC:
			return 0;
		case CoolEditDefine.Player_HC_2:
			return 0;
					
		case CoolEditDefine.Player_HC_3:
			return 0;	
			
		case CoolEditDefine.Player_ZS:
			return 2;
		case CoolEditDefine.Player_ZS_2:
			return 3;	
			
		case CoolEditDefine.Player_ZS_3:
			return 4;			
			
		case CoolEditDefine.Player_NG:
			return 0;
		case CoolEditDefine.Player_NG_2:
			return 0;
		case CoolEditDefine.Player_NG_3:	
			return 0;
		}
		
		return 0;
	}
}
