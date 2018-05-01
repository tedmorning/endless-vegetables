package com.kokatlaruruxi.wy;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.endlessvegetables2.ui.GameMenu;
import com.endlessvegetables2.ui.VeggiesData;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameManager;
import com.socoGameEngine.Module;

public class GameStory extends Module{

	private Paint paint;
	
	private ArrayList<Sprite> stroyImage;
	
	private int screenLayerNextImage; 
	
	private final String ImagePath[] = {"stroy/P1", "stroy/P2", "stroy/P3", "stroy/P4", "stroy/P5"};
		
	private int nextWaitingTime;
	
	private final int imageCoordinate[] = {26, 97, 26, 299, 192, 299, 26, 499, 47, 494};
	
	//移动方式1：从上往下，2：从下往上，3：从左往右，4：从右往左，0：不动
	private final byte moveDirect[] = {1,3,4,2,0};
	
	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		
		paint = new Paint();
		
		stroyImage = new ArrayList<Sprite>();
		
		screenLayerNextImage = 0;
		
		nextWaitingTime = 10;
		
		loadingImage();
		
		return false;
	}

	public void loadingImage()
	{
		for(int i=0;i<ImagePath.length;i++)
		{
			Sprite img = new Sprite(GameImage.getImage(ImagePath[i]));
			
			img.org_x = imageCoordinate[i*2]*GameConfig.f_zoomx;
			
			img.org_y = imageCoordinate[i*2+1]*GameConfig.f_zoomy;	
				
			img.x = img.org_x;
			
			img.y = img.org_y;	
									
			if(moveDirect[i]==1)
			{
				img.y = -img.bitmap.getHeight();
				
				img.byMoveSpeed = (int)(img.org_y - img.y)/5;
			}
			else if(moveDirect[i]==2)
			{
				img.y = GameConfig.GameScreen_Height;
				
				img.byMoveSpeed = (int)(img.y - img.org_y)/5;
			}
			else if(moveDirect[i]==3)
			{
				img.x = -img.bitmap.getWidth();
				
				img.byMoveSpeed = (int)(img.org_x - img.x)/5;
			}
			else if(moveDirect[i]==4)
			{
				img.x = GameConfig.GameScreen_Width;
				
				img.byMoveSpeed = (int)(img.x - img.org_x)/5;
			}
			
			stroyImage.add(img);
		}
	}
	
	public void delImage()
	{
		for(int i=0;i<stroyImage.size();i++)
		{	
			stroyImage.get(i).recycleBitmap();
			
			stroyImage.remove(i);
		}
	}
		
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		paint.reset();
		
		paint.setColor(Color.BLACK);
		
		canvas.drawRect(0, 0, GameConfig.GameScreen_Width, GameConfig.GameScreen_Height, paint);
		
		for(int i=0;i<stroyImage.size();i++)
		{			
			if(i<=screenLayerNextImage)
			stroyImage.get(i).drawBitmap(canvas, stroyImage.get(i).x, stroyImage.get(i).y, 1f, 1f, stroyImage.get(i).Alpha, 0, 0, 0, 0, 0, 0);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		switch(screenLayerNextImage)
//		{
//			case 0:
//				
//				
//				break;
//		}
		
		if(moveDirect[screenLayerNextImage]==1)
		{
			stroyImage.get(screenLayerNextImage).y += stroyImage.get(screenLayerNextImage).byMoveSpeed;
			
			if(stroyImage.get(screenLayerNextImage).y>=stroyImage.get(screenLayerNextImage).org_y)
			{
				stroyImage.get(screenLayerNextImage).y=stroyImage.get(screenLayerNextImage).org_y;
				
				nextWaitingTime --;
				
				if(nextWaitingTime==0)
				{
					screenLayerNextImage ++;
					
					nextWaitingTime = 10;
				}
			}
		}
		else if(moveDirect[screenLayerNextImage]==2)
		{
			stroyImage.get(screenLayerNextImage).y -= stroyImage.get(screenLayerNextImage).byMoveSpeed;
			
			if(stroyImage.get(screenLayerNextImage).y<=stroyImage.get(screenLayerNextImage).org_y)
			{
				stroyImage.get(screenLayerNextImage).y=stroyImage.get(screenLayerNextImage).org_y;
				
				nextWaitingTime --;
				
				if(nextWaitingTime==0)
				{
					screenLayerNextImage ++;
					
					nextWaitingTime = 10;
				}
			}
		}
		else if(moveDirect[screenLayerNextImage]==3)
		{
			stroyImage.get(screenLayerNextImage).x += stroyImage.get(screenLayerNextImage).byMoveSpeed;
			
			if(stroyImage.get(screenLayerNextImage).x>=stroyImage.get(screenLayerNextImage).org_x)
			{
				stroyImage.get(screenLayerNextImage).x=stroyImage.get(screenLayerNextImage).org_x;
				
				nextWaitingTime --;
				
				if(nextWaitingTime==0)
				{
					screenLayerNextImage ++;
					
					nextWaitingTime = 10;
				}
			}
		}
		else if(moveDirect[screenLayerNextImage]==4)
		{
			stroyImage.get(screenLayerNextImage).x -= stroyImage.get(screenLayerNextImage).byMoveSpeed;
			
			if(stroyImage.get(screenLayerNextImage).x<=stroyImage.get(screenLayerNextImage).org_x)
			{
				stroyImage.get(screenLayerNextImage).x=stroyImage.get(screenLayerNextImage).org_x;
				
				nextWaitingTime --;
				
				if(nextWaitingTime==0)
				{
					screenLayerNextImage ++;
					
					nextWaitingTime = 10;
				}
			}
		}
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
		delImage();
	}

	@Override
	public void initwordpic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getAction() == MotionEvent.ACTION_UP)
		{						
			if(screenLayerNextImage!=moveDirect.length-1)
			{
				screenLayerNextImage = moveDirect.length-1;
				
				for(int i=0;i<stroyImage.size();i++)
				{				
					stroyImage.get(i).x = stroyImage.get(i).org_x;
					
					stroyImage.get(i).y = stroyImage.get(i).org_y;								
				}
			}
			else 
			{
				//剧情结束
//				initialize();
				VeggiesData.isStory[0] = true;
				new VeggiesData().saveGame();
				GameManager.ResetToRunModule(new GameMain());
			}
		}
	}
}
