package com.endlessvegetables2.ui;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.Module;
import com.util.lang.LangDefineClient;
import com.util.lang.LangUtil;

public class GameOutEvent extends Module{

	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		
		GameManager.setPopUp(PopUp.GOOGS, null, new PopUp(LangUtil.getLangString(LangDefineClient.EXTERNAL_EVENT)) {
			@Override
			public byte onTouch(MotionEvent event) {
				// TODO Auto-generated method stub
				byte temp = super.onTouch(event);
				if(temp == PopUp.onTouch_googsExit||
				    temp == PopUp.onTouch_close)
				{
					GameManager.ChangeModule(null);
					
					if(!GameConfig.b_gamePause)
					{						
						if(!VeggiesData.isMuteMusic())
							GameMedia.resumeMusic();
					}
					
					return -1;
				}				
				return temp;
			}
		});

		return false;
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub				
//		if(!VeggiesData.isMuteMusic())
//		GameMedia.resumeMusic();
//		
//		GameManager.ChangeModule(null);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initwordpic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
