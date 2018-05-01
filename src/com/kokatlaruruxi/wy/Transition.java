//package com.endlessvegetables2.android;
//
//import android.graphics.Canvas;
//import android.graphics.Matrix;
//import android.graphics.Path;
//
//import com.socoGameEngine.GameConfig;
//
//public class Transition {
//
//	private float transitionSize;	
//	
//	private int transitionAngle;
//	
//	private boolean transitionState;
//	
//	private byte transitionStep;
//	
//	public Transition()
//	{
//		transitionSize = 7;
//		
//		transitionAngle = 0;	
//		
//		transitionState = false;
//		
//		transitionStep = 3;
//	}
//	
//	public void startTransition()
//	{
//		if(!transitionState)
//		{
//			transitionSize = 7;
//			
//			transitionAngle = 0;	
//			
//			transitionState = true;
//			
//			transitionStep = 0;
//		}
//	}
//	
//	public void paint(Canvas canvas)
//	{
//		if(transitionState)
//		{					
//			if(transitionStep == 0)
//			{
//				if(transitionSize>0)
//				{
//					transitionSize -= 0.2;
//					transitionAngle += 5;
//					
//					if(transitionSize<0)
//					{
//					   transitionSize = 0;					   
//					   transitionStep = 1;
//					}
//				}
//			}
//			else if(transitionStep == 2)
//			{				
//				transitionSize -= 0.2;
//				transitionAngle += 5;
//				
//				if(transitionSize<-7)
//				{					
//					transitionState = false;					
//					transitionStep = 3;
//				}
//			}
//				
//			Path path = new Path();
//			
//			path.moveTo(GameConfig.GameScreen_Width/2, GameConfig.GameScreen_Height/2-120*transitionSize*GameConfig.f_zoom);   
//			path.lineTo(GameConfig.GameScreen_Width/2+41*transitionSize*GameConfig.f_zoom, GameConfig.GameScreen_Height/2-44*transitionSize*GameConfig.f_zoom);   
//			path.lineTo(GameConfig.GameScreen_Width/2+126*transitionSize*GameConfig.f_zoom, GameConfig.GameScreen_Height/2-28*transitionSize*GameConfig.f_zoom);   	
//			path.lineTo(GameConfig.GameScreen_Width/2+67*transitionSize*GameConfig.f_zoom, GameConfig.GameScreen_Height/2+34*transitionSize*GameConfig.f_zoom);
//			path.lineTo(GameConfig.GameScreen_Width/2+78*transitionSize*GameConfig.f_zoom, GameConfig.GameScreen_Height/2+120*transitionSize*GameConfig.f_zoom);
//			path.lineTo(GameConfig.GameScreen_Width/2, GameConfig.GameScreen_Height/2+84*transitionSize*GameConfig.f_zoom);
//			path.lineTo(GameConfig.GameScreen_Width/2-78*transitionSize*GameConfig.f_zoom, GameConfig.GameScreen_Height/2+120*transitionSize*GameConfig.f_zoom);
//			path.lineTo(GameConfig.GameScreen_Width/2-67*transitionSize*GameConfig.f_zoom, GameConfig.GameScreen_Height/2+34*transitionSize*GameConfig.f_zoom);
//			path.lineTo(GameConfig.GameScreen_Width/2-126*transitionSize*GameConfig.f_zoom, GameConfig.GameScreen_Height/2-28*transitionSize*GameConfig.f_zoom);
//			path.lineTo(GameConfig.GameScreen_Width/2-41*transitionSize*GameConfig.f_zoom, GameConfig.GameScreen_Height/2-44*transitionSize*GameConfig.f_zoom);		
//			path.close();  
//			
//			Matrix matrix = new Matrix();
//			
//			matrix.postRotate(transitionAngle, GameConfig.GameScreen_Width/2, GameConfig.GameScreen_Height/2);
//			
//			path.transform(matrix);
//
//			canvas.clipPath(path);
//		}			
//	}
//	
//	public boolean getTransitionStop()
//	{
//		if(transitionStep==1)
//			return true;
//		else
//			return false;
//	}
//	
//	public void goOnTransition()
//	{
//		if(transitionStep==1)
//		   transitionStep = 2;
//	}
//	
//	public boolean getTransitionEnd()
//	{
//		if(transitionStep==3)
//			return true;
//		else
//			return false;
//	}		
//	
//	public boolean getTransitionState()
//	{
//		return transitionState;
//	}		
//	
//	public boolean openTransition()
//	{
//		if(transitionStep==2)
//			return true;
//		else
//			return false;
//	}
//}

package com.kokatlaruruxi.wy;

public class Transition {
	
//	private boolean transitionState;
//	
//	private byte transitionStep;
//	
//	public Transition()
//	{
//		transitionState = false;
//		
//		transitionStep = 3;
//	}
//	
//	public void startTransition()
//	{
//		if(!transitionState)
//		{
//			transitionState = true;
//			
//			transitionStep = 0;
//		}
//	}
//	
//	public void upData()
//	{
//		if(transitionState)
//		{					
//			if(transitionStep == 0)
//			{							  
//				transitionStep = 1;
//			}
//			else if(transitionStep == 2)
//			{											
//				transitionState = false;					
//				transitionStep = 3;
//			}
//		}
//	}
//	
//	public boolean getTransitionStop()
//	{
//		if(transitionStep==1)
//			return true;
//		else
//			return false;
//	}
//	
//	public void goOnTransition()
//	{
//		if(transitionStep==1)
//		   transitionStep = 2;
//	}
//	
//	public boolean getTransitionEnd()
//	{
//		if(transitionStep==3)
//			return true;
//		else
//			return false;
//	}		
//	
//	public boolean getTransitionState()
//	{
//		return transitionState;
//	}		
//	
//	public boolean openTransition()
//	{
//		if(transitionStep==2)
//			return true;
//		else
//			return false;
//	}
}
