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
	private final String UNBROKEN_HEAD = ".,)]}:;'\"!?������������������";
	private final String UNBROKEN_END = "([{'\"������";

	// private
	public String text;
	private String subString;

	private int textLength; // �ַ������ַ�����

	public int width; // �ı���Ŀ��
	public int height; // �ı���ĸ߶�

	private Typeface font; // �ı��������

	private int maxLineNum; // ��ҳ����ʾ���������
	private int lineSize;	// ���п���ʾ�Ŀ�ȣ���λ�����أ�
	private int lineHeight;	// �иߣ���λ�����أ�

	// line info
	private ArrayList<Integer> pagePos;			// ÿһҳ��ʼ�е��к�
	private ArrayList<Integer> lineStartPos;	// ÿһ�е���ʼ�ַ����ַ����е�����λ�á�
	private ArrayList<Integer> lineEndPos;	// ÿһ�еĽ����ַ����ַ����е�����λ�á�

	// set string
	private int currentIndex; // current index of the text string

	private int pageIndex; // current page

	private String keyWords[];	//	�ؼ����б�

	// ��ɫ����
	private HashMap<Integer,Integer> specialColor;
	private int default_color;	// ����Ĭ��ɫ
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
		
//		font	= Typeface.create("����",Typeface.NORMAL);
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
	 * ���뷽ʽ��0����룬1���ж��룬2�Ҷ���
	 */
	public void setTextAlign(byte align)
	{
		this.anchor = align;
	}

	/**
	 * ���������С
	 * @param size
	 */
	public void setTextSize(int size)
	{
		Rect rect = new Rect();
		paint.setTextSize(size);	
		paint.getTextBounds("��",0,1,rect);
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
	 * �����ı�
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

	// -------------------------------- ������ɫ  -------------------------------- //

	/**
	 * ����Ĭ��ɫ
	 */
	public void setDefaultColor(int defaultColor)
	{
		default_color	= defaultColor;
		paint.setColor(default_color);
	}

	/**
	 * �����ƶ���ŵ���ɫ
	 * @param index
	 * @param col
	 */
	public void setColor(int index,int col)
	{
		specialColor.put(index,col);
	}

	/**
	 * ������ɫ����
	 * @param col
	 */
	public void setColor(int col[])
	{
		for( int i=col.length-1;i>=0;i-- )
		{
			specialColor.put(i,col[i]);
		}
	}

	// -------------------------------- ���ùؼ��� -------------------------------- //

	/**
	 * ����ָ����ŵĹؼ���
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
	 * ���ùؼ����б�
	 * @param keyword
	 */
	public void setKeyWord(String keyword[])
	{
		keyWords	= null;
		keyWords	= keyword;
	}

	/**
	 * �滻�ַ����еĹؼ���
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
	 * ������ɫ��ǩ
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
	 * �ַ����Ű�
	 */
	private void setStringInfo()
	{
		int nextLineIndex;
		int nextPageIndex;
		int lineStringLen; 		// ģ�ⵥ�п����ɵ�����
		int nextCommand 		= -1; // 0: ���У�1����ҳ
		int nextCommandIndex	= 0;
		int tmpInt = 0;
		char tmpChar;

		nextLineIndex = text.indexOf("%n%");
		nextPageIndex = text.indexOf("%p%");

		// ģ�ⵥ�п����ɵ�����
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

		// ����������Ű������
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

			// ����endIndexλ��
			tmpInt	= (int)measureText(text.substring(startIndex,endIndex));

			if( tmpInt > lineSize )
			{
				do
				{
					endIndex--;
										
					tmpInt	= (int)measureText(text.substring(startIndex,endIndex));
				}
				while( tmpInt > lineSize );

				// �������п����ɵ�����
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

				// �������п����ɵ�����
				lineStringLen	= endIndex-startIndex;
			}

			// �����Ű������
			if( nextCommandIndex > -1 && endIndex >= nextCommandIndex )
			{
				endIndex	= nextCommandIndex;

				if( endIndex == startIndex )
					tmpInt	= 0;
				else if( endIndex > startIndex )
					tmpInt	= (int)measureText(text.substring(startIndex,endIndex));

				if( tmpInt <= lineSize )
				{
					// ��ҳ
					if( nextCommand == 1 || lineStartPos.size()-pagePos.get(pagePos.size()-1) >= maxLineNum )
						pagePos.add(lineStartPos.size());
					
					// ǿ�ƻ���
					lineStartPos.add(startIndex);
					lineEndPos.add(endIndex);
					startIndex	= endIndex+3;
					
					// ˢ��������Ű������
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

				// ����Ӣ��,���ֺͱ�����
				if( endIndex < textLength )
				{
					tmpChar	= Character.toLowerCase(text.charAt(endIndex));

					// �������׵ı�����
					while( endIndex > startIndex && UNBROKEN_HEAD.indexOf(tmpChar+"") >= 0 )
					{
						endIndex--;
						tmpChar	= text.charAt(endIndex);
					}

					// ����Ӣ�Ķ���
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

					// ��������
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

				// ������β�ı�����
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
					// �������׿ո�
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
				// ��ҳ
				if( lineStartPos.size()-pagePos.get(pagePos.size()-1) >= maxLineNum )
					pagePos.add(lineStartPos.size());
				
				// ǿ�ƻ���
				lineStartPos.add(startIndex);
				lineEndPos.add(endIndex);
				startIndex	= endIndex;			
			}
		}

		pagePos.add(lineStartPos.size());
	}

	/**
	 * �����ı���Ĵ�С
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
	 * ��ȡ�ı���ҳ��
	 * @return
	 */
	public int page()
	{
		return pagePos.size()-1;
	}
	
	/**
	 * ��ȡ�ı��ܸ߶ȣ�ͨ�����ڹ�����Ļ
	 * @return
	 */
	public float height()
	{
		return	lineStartPos.size()*(lineHeight+2)-2;
	}

	/**
	 * ��ʾȫ���ı����ı�����ҳ����Ҫ��������clip����ʾ�������ڹ�����Ļ
	 * @param canvas
	 * @param x
	 * @param y
	 */
	public void paintText(Canvas canvas,int x,int y)
	{
		paintText(canvas,x,y,anchor,-1,true);
	}

	/**
	 * ��ʾָ��ҳ����ı�
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
	 * ������ʾָ��ҳ����ı�
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
	 * ��ʾ�ı�
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
		// ������
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

		// ������ɫ
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