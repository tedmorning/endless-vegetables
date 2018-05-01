package com.socoGameEngine;

//////////////////////////////////////////////////////////////////////////////
//								TextBox										//
//--------------------------------------------------------------------------//
//	Version: 2.0 for Android												//
//--------------------------------------------------------------------------//
//	Command List:															//
//	%n%			change line													//
//	%p%			change page													//
//	%k+number%	use a constant string to instead in.the number is the 		//
//				index of key words											//
//	%c+number%	change color.the number is the index of color.				//
//	%c%			change color to default color.								//
//--------------------------------------------------------------------------//
//	Notice: 																//
//	1.	command "%n%" and "%p%" in the end of the text will be ignore.		//	
//--------------------------------------------------------------------------//
//	Author: BingFeng														//
//--------------------------------------------------------------------------//
//	Year  2011																//
//////////////////////////////////////////////////////////////////////////////

import java.util.*;

import com.kokatlaruruxi.wy.Main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class TextBox
{
	// const
	private final int DISPLAY_SPEED = 1;
	private final String UNBROKEN_HEAD = ".,)]}:;'\"!?。，）：；’”！？";
	private final String UNBROKEN_END = "([{'\"（‘“";

	// private
	public String text;
	private String subString;

	private int textLength; // 字符串的字符个数

	public int width; // 文本框的宽度
	public int height; // 文本框的高度

	private Typeface font; // 文本框的字体

	private int maxLineNum; // 单页可显示的最大行数
	private int lineSize;	// 单行可显示的宽度（单位：像素）
	private int lineHeight;	// 行高（单位：像素）

	// line info
	private ArrayList<Integer> pagePos;			// 每一页起始行的行号
	private ArrayList<Integer> lineStartPos;	// 每一行的起始字符在字符串中的索引位置。
	private ArrayList<Integer> lineEndPos;	// 每一行的结束字符在字符串中的索引位置。

	// set string
	private int currentIndex; // current index of the text string

	private int pageIndex; // current page

	private String keyWords[];	//	关键词列表

	// 颜色调整
	private HashMap<Integer,Integer> specialColor;
	private int default_color;	// 字体默认色
	private int currentColorIndex;
	private ArrayList<Integer> colorChange;
	private ArrayList<Integer> colorStamp;	

	private int startIndex;
	private int endIndex;
	private int charLength;

	public Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public static byte LEFT		= 0;
	public static byte HCENTER	= 1;
	public static byte RIGHT	= 2;

	public byte anchor;

	// ///////////////////////////////////////////////////////
	// ///////////////////// Functions ///////////////////////
	// ///////////////////////////////////////////////////////
	public TextBox()
	{
		specialColor	= new HashMap<Integer,Integer>();		
		
//		font	= Typeface.create("宋体",Typeface.NORMAL);
		font	= Typeface.createFromAsset(Main.getActivity().getAssets(), "font/ARLRDBD.TTF");
		paint.setTypeface(font);

		setTextSize(20);

		default_color	= Color.BLACK;
		
		paint.setColor(default_color);

		anchor		= LEFT;

		setBoxSize(24,22);
	}

	public void Close()
	{
		text			= null;
		pagePos			= null;
		lineStartPos	= null;
		lineEndPos		= null;

		subString		= null;
	}

	/*
	 * 对齐方式，0左对齐，1居中对齐，2右对齐
	 */
	public void setTextAlign(byte align)
	{
		this.anchor = align;
	}

	/**
	 * 设置字体大小
	 * @param size
	 */
	public void setTextSize(int size)
	{
		Rect rect = new Rect();
		paint.setTextSize(size);	
		paint.getTextBounds("高",0,1,rect);
		lineHeight	= rect.height();
	}

	private void resetBox()
	{
		lineSize	= width-4;
		maxLineNum	= (int)((height+2)/(lineHeight+2));

		if( text != null )
			setStringInfo();
	}

	/**
	 * 设置文本
	 * @param _text
	 */
	public void setString(String _text)
	{
		text	= null;
		text	= checkString(_text,keyWords);

		setColorStamp();

		textLength	= text.length();
		
		setStringInfo();

		pageIndex		= -2;
		currentIndex	= 0;

		resetBox();
	}

	// -------------------------------- 设置颜色  -------------------------------- //

	/**
	 * 设置默认色
	 */
	public void setDefaultColor(int defaultColor)
	{
		default_color	= defaultColor;
		paint.setColor(default_color);
	}

	/**
	 * 设置制定编号的颜色
	 * @param index
	 * @param col
	 */
	public void setColor(int index,int col)
	{
		specialColor.put(index,col);
	}

	/**
	 * 设置配色方案
	 * @param col
	 */
	public void setColor(int col[])
	{
		for( int i=col.length-1;i>=0;i-- )
		{
			specialColor.put(i,col[i]);
		}
	}

	// -------------------------------- 设置关键字 -------------------------------- //

	/**
	 * 设置指定编号的关键字
	 */
	public void setKeyWord(int index,String keyword)
	{
		if( keyWords == null )
		{
			keyWords	= new String[index+1];
		}
		else if( index >= keyWords.length )
		{
			String tmp_string[]	= new String[index+1];
			System.arraycopy(keyWords,0,tmp_string,0,keyWords.length);
			keyWords	= tmp_string;
			tmp_string	= null;
		}
		
		keyWords[index]	= keyword;
	}

	/**
	 * 设置关键字列表
	 * @param keyword
	 */
	public void setKeyWord(String keyword[])
	{
		keyWords	= null;
		keyWords	= keyword;
	}

	/**
	 * 替换字符串中的关键字
	 * @param s
	 * @param keys
	 * @return
	 */
	public static String checkString(String string,String keys[])
	{
		StringBuffer tmpStringBuffer;
		int startIndex;
		int endIndex;
		int index;

		if( string == null || keys == null )
			return	string;

		tmpStringBuffer	= new StringBuffer(string);

		startIndex = string.indexOf("%k");
		while( startIndex >= 0 )
		{
			endIndex	= string.indexOf("%",startIndex+1);
			if( endIndex >= 0 )
			{
				try
				{
					index	= Integer.parseInt(string.substring(startIndex+2,endIndex));
					
					if( index < keys.length )						
					{
						tmpStringBuffer.delete(startIndex,endIndex+1);
						tmpStringBuffer.insert(startIndex,keys[index]);
						string = null;
						string = tmpStringBuffer.toString();
					}
					else	startIndex = endIndex + 1;
					
				} catch (NumberFormatException e) {
					startIndex = endIndex + 1;
				}

				if (startIndex < string.length())
					startIndex = string.indexOf("%k", startIndex);
				else
					startIndex = -1;
			} else
				startIndex = -1;
		}

		tmpStringBuffer = null;

		return string;
	}

	// -------------------------------- Private -------------------------------- //

	/**
	 * 设置颜色标签
	 */
	private void setColorStamp()
	{
		StringBuffer tmpString = new StringBuffer(text);
		int startIndex;
		int endIndex;
		int index;

		currentColorIndex = 0;

		colorChange		= null;
		colorChange		= new ArrayList<Integer>();
		colorChange.add(-1);
		colorStamp		= null;
		colorStamp		= new ArrayList<Integer>();
		colorStamp.add(0);
	
		if( specialColor.size() == 0 )
			return;

		startIndex	= text.indexOf("%c");
		while( startIndex >= 0 )
		{
			endIndex	= text.indexOf("%",startIndex+1);
			if( endIndex >= 0 )
			{
				if( endIndex == startIndex+2 )
				{
					colorChange.add(-1);
					colorStamp.add(startIndex);
					tmpString.delete(startIndex,endIndex+1);
					text	= null;
					text	= tmpString.toString();
				}
				else
				{
					try
					{
						index	= Integer.parseInt(text.substring(startIndex+2,endIndex));

						if( specialColor.get(index) != null )
						{
							colorChange.add(specialColor.get(index));
							colorStamp.add(startIndex);
							tmpString.delete(startIndex,endIndex+1);
							text = null;
							text = tmpString.toString();
						}
						else	startIndex	= endIndex+1;
					}
					catch (NumberFormatException e)
					{
						startIndex	= endIndex+1;
					}
				}

				if( startIndex < text.length() )
					startIndex	= text.indexOf("%c",startIndex);
				else	startIndex	= -1;
			}
			else	startIndex	= -1;
		}
	}

	/**
	 * 字符串排版
	 */
	private void setStringInfo()
	{
		int nextLineIndex;
		int nextPageIndex;
		int lineStringLen; 		// 模拟单行可容纳的字数
		int nextCommand 		= -1; // 0: 换行，1：换页
		int nextCommandIndex	= 0;
		int tmpInt = 0;
		char tmpChar;

		nextLineIndex = text.indexOf("%n%");
		nextPageIndex = text.indexOf("%p%");

		// 模拟单行可容纳的字数
		lineStringLen	= (int)(lineSize/paint.getTextSize());

		if( lineStringLen == 0 )
		{
			System.out.println("TextBox too small!!");
			return;
		}

		pagePos			= null;
		lineStartPos	= null;
		lineEndPos		= null;
		pagePos			= new ArrayList<Integer>();
		lineStartPos	= new ArrayList<Integer>();
		lineEndPos		= new ArrayList<Integer>();

		pagePos.add(0);
		
		if( text == null || text.equals("") )
			return;
		
		startIndex	= 0;
		endIndex	= 0;

		// 搜索最近的排版命令符
		if( nextPageIndex < 0 && nextLineIndex < 0 )
		{
			nextCommandIndex	= -1;
			nextCommand			= -1;
		}
		else if( nextPageIndex < 0
				|| (nextPageIndex > nextLineIndex && nextLineIndex > 0) )
		{
			nextCommandIndex	= nextLineIndex;
			nextCommand			= 0;
		}
		else
		{
			nextCommandIndex	= nextPageIndex;
			nextCommand			= 1;
		}

		while( endIndex < textLength )
		{
			endIndex	= Math.min(startIndex+lineStringLen,textLength);

			// 调整endIndex位置
			tmpInt	= (int)measureText(text.substring(startIndex,endIndex));

			if( tmpInt > lineSize )
			{
				do
				{
					endIndex--;
										
					tmpInt	= (int)measureText(text.substring(startIndex,endIndex));
				}
				while( tmpInt > lineSize );

				// 调整单行可容纳的字数
				lineStringLen	= endIndex-startIndex;
			}
			else if( endIndex < textLength && tmpInt < lineSize )
			{
				tmpInt	= (int)measureText(text.substring(startIndex,endIndex+1));

				while( tmpInt < lineSize )
				{
					endIndex++;
					if( endIndex == textLength )
						break;
					else	tmpInt	= (int)measureText(text.substring(startIndex,endIndex+1));
				}

				// 调整单行可容纳的字数
				lineStringLen	= endIndex-startIndex;
			}

			// 处理排版命令符
			if( nextCommandIndex > -1 && endIndex >= nextCommandIndex )
			{
				endIndex	= nextCommandIndex;

				if( endIndex == startIndex )
					tmpInt	= 0;
				else if( endIndex > startIndex )
					tmpInt	= (int)measureText(text.substring(startIndex,endIndex));

				if( tmpInt <= lineSize )
				{
					// 换页
					if( nextCommand == 1 || lineStartPos.size()-pagePos.get(pagePos.size()-1) >= maxLineNum )
						pagePos.add(lineStartPos.size());
					
					// 强制换行
					lineStartPos.add(startIndex);
					lineEndPos.add(endIndex);
					startIndex	= endIndex+3;
					
					// 刷新最近的排版命令符
					if( nextCommand == 0 )
						nextLineIndex	= text.indexOf("%n%",startIndex);
					else	nextPageIndex	= text.indexOf("%p%",startIndex);

					if( nextPageIndex < 0 && nextLineIndex < 0 )
					{
						nextCommandIndex	= -1;
						nextCommand			= -1;
					}
					else if( nextPageIndex < 0
							 || (nextPageIndex > nextLineIndex && nextLineIndex > 0) )
					{
						nextCommandIndex	= nextLineIndex;
						nextCommand			= 0;
					}
					else
					{
						nextCommandIndex	= nextPageIndex;
						nextCommand			= 1;
					}
				}
			}

			if( endIndex > startIndex && endIndex <= textLength )
			{
				tmpInt = endIndex;

				// 处理英文,数字和标点符号
				if( endIndex < textLength )
				{
					tmpChar	= Character.toLowerCase(text.charAt(endIndex));

					// 处理行首的标点符号
					while( endIndex > startIndex && UNBROKEN_HEAD.indexOf(tmpChar+"") >= 0 )
					{
						endIndex--;
						tmpChar	= text.charAt(endIndex);
					}

					// 处理英文断字
					if((tmpChar >= 'A' && tmpChar <= 'Z') ||(tmpChar >= 'a' && tmpChar <= 'z') )
					{
						if( endIndex > startIndex )
						{
							tmpChar	= Character.toLowerCase(text.charAt(endIndex-1));

							while((tmpChar >= 'A' && tmpChar <= 'Z') ||(tmpChar >= 'a' && tmpChar <= 'z') )
							{
								endIndex--;
								if( endIndex > startIndex )
									tmpChar	= text.charAt(endIndex-1);
								else
								{
									endIndex	= tmpInt;
									break;
								}
							}
						}
						else	endIndex	= tmpInt;
					}

					// 处理数字
					else if( tmpChar >= '0' && tmpChar <= '9' )
					{
						if( endIndex > startIndex )
						{
							tmpChar = text.charAt(endIndex-1);

							while( tmpChar >= '0' && tmpChar <= '9' )
							{
								endIndex--;
								if( endIndex > startIndex )
									tmpChar	= text.charAt(endIndex-1);
								else
								{
									endIndex	= tmpInt;
									break;
								}
							}
						}
						else	endIndex	= tmpInt;
					}
				}

				// 处理行尾的标点符号
				if( endIndex > startIndex )
				{
					tmpChar	= text.charAt(endIndex-1);

					while( endIndex > startIndex && UNBROKEN_END.indexOf(tmpChar+"") >= 0 )
					{
						endIndex--;
						if( endIndex > startIndex )
							tmpChar	= text.charAt(endIndex-1);
						else	break;
					}
				}

				if( endIndex == startIndex )
					endIndex	= tmpInt;
				else
				{
					// 处理行首空格
					tmpChar	= text.charAt(startIndex);
					if( tmpChar == ' ' )
					{
						if( startIndex < textLength-1 )
						{
							tmpChar	= text.charAt(startIndex+1);
							if( tmpChar != ' ' )
								startIndex++;
						}
					}
				}
				// 换页
				if( lineStartPos.size()-pagePos.get(pagePos.size()-1) >= maxLineNum )
					pagePos.add(lineStartPos.size());
				
				// 强制换行
				lineStartPos.add(startIndex);
				lineEndPos.add(endIndex);
				startIndex	= endIndex;			
			}
		}

		pagePos.add(lineStartPos.size());
	}

	/**
	 * 设置文本框的大小
	 * @param width
	 * @param height
	 */
	public void setBoxSize(int width,int height)
	{
		if( this.width == width && this.height == height )
			return;

		this.width	= width;
		this.height	= height;

		resetBox();
	}

	/**
	 * 获取文本总页数
	 * @return
	 */
	public int page()
	{
		return pagePos.size()-1;
	}
	
	/**
	 * 获取文本总高度，通常用于滚动字幕
	 * @return
	 */
	public float height()
	{
		return	lineStartPos.size()*(lineHeight+2)-2;
	}

	/**
	 * 显示全部文本，文本不分页，需要自行设置clip来显示，常用于滚动字幕
	 * @param canvas
	 * @param x
	 * @param y
	 */
	public void paintText(Canvas canvas,int x,int y)
	{
		paintText(canvas,x,y,anchor,-1,true);
	}

	/**
	 * 显示指定页码的文本
	 * @param canvas
	 * @param x
	 * @param y
	 * @param currentPage
	 */
	public void paintText(Canvas canvas,int x,int y,int currentPage)
	{
		paintText(canvas,x,y,anchor,currentPage,true);
	}

	/**
	 * 逐字显示指定页码的文本
	 * @param canvas
	 * @param x
	 * @param y
	 * @param currentPage
	 * @return
	 */
	public boolean paintText1by1(Canvas canvas,int x,int y,int currentPage)
	{
		return paintText(canvas,x,y,anchor,currentPage,false);
	}

	/**
	 * 显示文本
	 * @param canvas
	 * @param x
	 * @param y
	 * @param anchor
	 * @param currentPage
	 * @param auto
	 * @return
	 */
	public boolean paintText(Canvas canvas,int x,int y,int anchor,int currentPage,boolean auto)
	{
		if(text==null){
			return false;
		}
		int startLine;
		int endLine;
		float ex,ey;
		int current_color;
		boolean paintOver	= true;

		Rect rect	= new Rect();
		rect		= canvas.getClipBounds();
//		LogShow.d("rect.top="+rect.top+" rect.right="+rect.right +" rect.left="+rect.left+" rect.bottom="+rect.bottom);
		// 设置行
		if( currentPage >= 0 )
		{
			if( currentPage >= pagePos.size()-1 )
				return paintOver;
			
			startLine	= pagePos.get(currentPage);
			endLine		= pagePos.get(currentPage+1);

			ey	= (height-((endLine-startLine)*(lineHeight+2)-2))/2;
		}
		else
		{
			startLine	= Math.max(0,(int)((rect.top-y+2)/(lineHeight+2)));
			
			if( startLine >= lineStartPos.size() )
				return paintOver;

			endLine	= Math.min(lineStartPos.size(),(int)(rect.top+rect.bottom-rect.top-y+lineHeight+1)/(lineHeight+2));
//			LogShow.d("startLine="+startLine+"endLine="+endLine);
			if( endLine < 0 )
				return paintOver;

			ey	= 0;
		}

		if( currentPage != pageIndex )
		{
			currentIndex	= lineStartPos.get(startLine);
			pageIndex		= currentPage;
		}

		// 设置颜色
		for( currentColorIndex=colorChange.size()-1;currentColorIndex>=0;currentColorIndex-- )
		{
			if( colorStamp.get(currentColorIndex) <= lineStartPos.get(startLine) )
				break;
		}

		if( colorChange.get(currentColorIndex) != -1 )
			current_color	= colorChange.get(currentColorIndex);
		else	current_color = default_color;

		paint.setColor(current_color);
		
		for( int i=startLine;i<endLine;i++ )
		{
			startIndex	= lineStartPos.get(i);
			endIndex	= lineEndPos.get(i);

			if( startIndex >= endIndex )
				continue;

			if( anchor == RIGHT )
				ex	= width-2-measureText(text.substring(startIndex,endIndex));
			else if( anchor == HCENTER )
				ex	= (width-measureText(text.substring(startIndex,endIndex)))/2;
			else	ex	= 2;

			if( currentColorIndex+1 < colorChange.size() && colorStamp.get(currentColorIndex+1)-1 < startIndex )
				currentColorIndex++;

			if( currentColorIndex+1 < colorChange.size() && colorStamp.get(currentColorIndex+1) < endIndex)
				charLength	= colorStamp.get(currentColorIndex+1)-startIndex;
			else	charLength	= endIndex-startIndex;

			if( charLength < 0 )
				charLength	= 0;

			while(true)
			{
				if( colorChange.get(currentColorIndex) != -1 )
				{
					if( current_color != colorChange.get(currentColorIndex) )
					{
						current_color	= colorChange.get(currentColorIndex);
						paint.setColor(current_color);
					}
				}
				else
				{
					if( current_color != default_color )
					{
						current_color	= default_color;
						paint.setColor(current_color);
					}
				}

				if( !auto && currentIndex <= startIndex+charLength )
				{
					subString	= text.substring(startIndex,currentIndex);
					currentIndex	+= DISPLAY_SPEED;
					paintOver	= false;
				}
				else	subString	= text.substring(startIndex,startIndex+charLength);

				if( currentPage >= 0 )
				{
					canvas.drawText(subString,x+ex,y+ey+(i-startLine)*(lineHeight+2)+lineHeight,paint);
				}
				else
				{
					canvas.drawText(subString,x+ex,y+i*(lineHeight+2)+lineHeight,paint);
				}

				if( !paintOver )
					return	paintOver;

				if( endIndex-startIndex-charLength > 0 )
				{
					ex	+= measureText(text.substring(startIndex,startIndex+charLength));
					startIndex	+= charLength;

					if( currentColorIndex+1 < colorChange.size() && colorStamp.get(currentColorIndex+1)-1 < startIndex )
						currentColorIndex++;

					if( currentColorIndex+1 < colorChange.size() && colorStamp.get(currentColorIndex+1) < endIndex )
						charLength	= colorStamp.get(currentColorIndex+1)-startIndex;
					else	charLength	= endIndex-startIndex;
				}
				else	break;
			}
		}

		return paintOver;
	}
	
	private float measureText(String str) {
		Rect rect = new Rect();
		paint.getTextBounds(str,0,str.length(),rect);
		return rect.width();
	}
}