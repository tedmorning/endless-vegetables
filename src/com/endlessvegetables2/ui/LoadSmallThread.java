package com.endlessvegetables2.ui;

import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameImage;

public class LoadSmallThread extends Thread{
	public static boolean isLoadSmalltoExit = false;
	public void run() {
		isLoadSmalltoExit = false;
		GameStaticImage.s_smallcard_card = new Sprite[63];
		for (int i=0; i<63; i++) {
			if (isLoadSmalltoExit) return;
			if (i  < 9) {
				GameStaticImage.s_smallcard_card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_0" + (i+1) + "_s"));				
			} else {				
				GameStaticImage.s_smallcard_card[i] = new Sprite(GameImage.getImage("smallcard/card_pc_" + (i+1) + "_s"));				
			}
			if (isLoadSmalltoExit) return;
		}
	}

}
