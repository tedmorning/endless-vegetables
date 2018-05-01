package com.endlessvegetables2.turngame;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.kokatlaruruxi.wy.R;
import com.endlessvegetables2.ui.VeggiesData;
import com.kokatlaruruxi.wy.Sprite;
import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameImage;
import com.socoGameEngine.GameLibrary;
import com.socoGameEngine.GameMedia;

public class TurnGameBubble {
	
	private ArrayList<Bubble> bubbleList;
	
	private TurnGameSprite bubbleMap;
	
	private int showTime;
		
	private boolean show; 
	
	public TurnGameBubble()
	{
		init();
	}
	
	private void init()
	{
		bubbleList = new ArrayList<Bubble>();
		
		showTime = 0;
		
		show = false;
	}
	
	public void loadImage()
	{
		bubbleMap = new TurnGameSprite(GameImage.getImage("newEffect/bubble"));
	}
	
	public void delImage()
	{
		GameImage.delImage(bubbleMap.bitmap);
		
		bubbleMap.recycleBitmap();
	}
	
	public void openBubble()
	{		
		init();
		
		show = true;		
	}
	
	public void closeBubble()
	{
		show = false;		
	}
	
	public void updata()
	{		
		if(!show)
			return;
				
		if(showTime%50==0)
		{
			showTime = 0;
			
			Bubble bubble = new  Bubble(bubbleMap);
			
			bubbleList.add(bubble);						
		}
		
		showTime ++;		
		
		for(int i=0;i<bubbleList.size();i++)
		{						
			if(bubbleList.get(i).getMoveY()<=0)
			{
				bubbleList.remove(i);
				
				if(!VeggiesData.isMuteSound())
				GameMedia.playSound(R.raw.bubbles, 0);
			}
		}
	}
	
	public void paint(Canvas canvas)
	{
		if(!show)
			return;
		
		for(int i=0;i<bubbleList.size();i++)
		{
			bubbleList.get(i).paint(canvas, bubbleMap);
		}
	}
	
	class Bubble
	{
		private float size;
		
		private int move_x;
		private int move_y;
		
		private int speed;
		
//		private Paint paint;
//		
//		private Matrix matrix;
		
		public Bubble(TurnGameSprite bubble)
		{
			size = GameLibrary.getFloatRandom(0.5f, 1f);
			
			move_y = GameConfig.GameScreen_Height; 
			
			move_x = GameLibrary.getIntRandom(0, GameConfig.GameScreen_Width-bubble.bitmap.getWidth());
			
			speed = GameLibrary.getIntRandom(3, 5);
			
//			paint = new Paint();
//			
//			matrix = new Matrix();
		}
		
		public void paint(Canvas canvas, TurnGameSprite bubble)
		{						
//			matrix.reset();
//			
//			matrix.setScale(size, size);
//			
//			bubble.drawBitmap(canvas, bubble.bitmap, move_x, move_y, matrix, GameLibrary.TL, paint);
			
			Paint paint = new Paint();
			
			bubble.drawBitmap(canvas, bubble.bitmap, move_x, move_y, paint);
			
			move_y -= speed;
		}
		
		public int getMoveY()
		{
			return move_y;
		}
	}
}
