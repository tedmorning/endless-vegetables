package com.kokatlaruruxi.wy;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.socoGameEngine.GameConfig;
import com.socoGameEngine.GameMedia;
import com.socoGameEngine.MainActivity;
import com.socogame.sax.RSSFeed;
import com.socogame.sax.RSSGroupFeed;
import com.socogame.sax.RSSGroupHandler;
import com.socogame.sax.RSSHandler;

public class GameReadMap {

//	private RSSFeed feed;
//	
//	private RSSGroupFeed groupFeed;		
//	
//	public void readMapInfo(String fileDir)
//	{
//		feed = getFeed(fileDir);
//		
////		for(int i=0;i<feed.getItemlist().size();i++)
////		{
////			System.out.println(feed.getItemlist().get(i).getId());
////			System.out.println(feed.getItemlist().get(i).getType());
////			System.out.println(feed.getItemlist().get(i).getFlashTime());
////			System.out.println(feed.getItemlist().get(i).getCoordinate());
////			System.out.println(feed.getItemlist().get(i).getNumber());
////			
////			System.out.println("--------------------------------------------------------------");
////		}				
//	}
//	
//	public void readMapGroupInfo(String fileDir)
//	{
//		groupFeed = getGroupFeed(fileDir);
//		
//		waveTime = new int[groupFeed.getItemlist().size()];
//		
//		waveOpen = new boolean[groupFeed.getItemlist().size()];
//		
//		for(int i=0;i<groupFeed.getItemlist().size();i++)
//		{
//			waveTime[i] = Integer.parseInt(groupFeed.getItemlist().get(i).getTime())*25;
//			
//			waveOpen[i] = true;
//			
////			System.out.println(groupFeed.getItemlist().get(i).getTime());
////			System.out.println(groupFeed.getItemlist().get(i).getWave());
////			System.out.println(groupFeed.getItemlist().get(i).getMonsterNumber());
////			System.out.println(groupFeed.getItemlist().get(i).getWaveGroup());
////			
////			System.out.println("******************************************************");
////			
////			for(int j=0;j<groupFeed.getItemlist().get(i).getMonsterGroup().size();j++)
////			{
////				System.out.println(groupFeed.getItemlist().get(i).getMonsterGroup().get(j).idGroup);				
////				System.out.println(groupFeed.getItemlist().get(i).getMonsterGroup().get(j).monsterType);
////			}
////			
////			System.out.println("--------------------------------------------------------------");
//		}			
//	}
//	
//	private RSSFeed getFeed(String fileDir) {
//        try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser parser = factory.newSAXParser();
//            XMLReader reader = parser.getXMLReader();
//            RSSHandler handler = new RSSHandler();
//            reader.setContentHandler(handler);
//            InputSource is = new InputSource(MainActivity.getActivity().getClassLoader().getResourceAsStream(fileDir));//取得本地xml文件            
//            reader.parse(is);
//            return handler.getFeed();
//        } catch (ParserConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SAXException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
//	
//	private RSSGroupFeed getGroupFeed(String fileDir) {
//        try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser parser = factory.newSAXParser();
//            XMLReader reader = parser.getXMLReader();
//            RSSGroupHandler handler = new RSSGroupHandler();
//            reader.setContentHandler(handler);
//            InputSource is = new InputSource(MainActivity.getActivity().getClassLoader().getResourceAsStream(fileDir));//取得本地xml文件            
//            reader.parse(is);
//            return handler.getFeed();
//        } catch (ParserConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SAXException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
//	
//	private int waveTime[];
//	
//	private boolean waveOpen[];
//		
//	private int goldenMonsterShowTime=1000;
//	
//	private int rainbowMonsterShowTime=500;
//	
////	private List<Integer> monsterCoordinate;
//	
//	public void upData(GameMain gameMain)
//	{
////		if(gameMain.gameCold.getState())
////			return;
//				
//		//------------------------------ 彩虹怪 ----------------------------------
////		rainbowMonsterShowTime --;
////		
////		if(rainbowMonsterShowTime<=0)
////		{
////			int height[] = {200, 300, 400, 500};
////			
////			int id = ExternalMethods.throwDice(0, 3);
////			
////			gameMain.gameMonster.addEnemy(SpriteLibrary.Enemy_CHG, 0, 0, 0, 
////											(int)(height[id]*GameConfig.f_zoom), 4, 0, 1);	
////			
////			rainbowMonsterShowTime = 500;
////		}
//		
//		//------------------------------ 黄金怪 -----------------------------------
////		goldenMonsterShowTime --;
////		
////		if(goldenMonsterShowTime<=0)
////		{
////			int height[] = {200, 300, 400, 500};
////			
////			int id = ExternalMethods.throwDice(0, 3);
////			
////			gameMain.gameMonster.addEnemy(SpriteLibrary.Enemy_MMB1, 0, 0, 0, 
////											(int)(height[id]*GameConfig.f_zoom), 8, 1, 1);	
////			
////			goldenMonsterShowTime = 1000;
////		}
//		
//		
//		//------------------------------ 一般怪物 -----------------------------------
//		if(waveTime==null)
//			return;
//		
//		for(int i=0;i<waveTime.length;i++)
//		{
//			if(waveTime[i]<=0&&waveOpen[i])
//			{
//				String tmp;
//				
//				waveOpen[i] = false;
//				
//
//				int rmd = ExternalMethods.throwDice(0, groupFeed.getItemlist().get(i).getMonsterGroup().size()-1);
//				
//				tmp = groupFeed.getItemlist().get(i).getMonsterGroup().get(rmd).idGroup;
//				
//				String p[] = tmp.split("\\|");
//				
////				for(int j=0;j<p.length;j++)
////					System.out.println(j+"===========>>"+p[j]);
//				
//				
//				
//				tmp = groupFeed.getItemlist().get(i).getMonsterGroup().get(rmd).monsterType;
//				
//				String MN[] =  tmp.split("\\|");
//				
//				List<Monster> monster = new Vector<Monster>();
//				
//				for(int j=0;j<MN.length;j++)
//				{
////					System.out.println(j+"===========>>"+MN[j]);
//					
//					String tmp1[] = MN[j].split("\\*");
//					
//					Monster m = new Monster();
//					
//					m.id = tmp1[0];
//					
////					System.out.println(m.id);
//					
//					m.number = Integer.parseInt(tmp1[1]);
//					
////					System.out.println(m.number+"");
//					
//					monster.add(m);
//				}
//				
//				
//				
//				List<Integer> monsterCoordinate = new Vector<Integer>();
//				
//				for(int g=0;g<p.length;g++)
//				{
//					for(int j=0;j<feed.getItemlist().size();j++)
//					{
//						if(p[g].equals(feed.getItemlist().get(j).getId()))
//						{
//							tmp = feed.getItemlist().get(j).getCoordinate();
//							
//							String tmp2[] =  tmp.split("\\|");
//							
//							for(int k=0;k<tmp2.length;k++)
//							{
//								monsterCoordinate.add(Integer.parseInt(tmp2[k].split("\\,")[0]));
//								monsterCoordinate.add(Integer.parseInt(tmp2[k].split("\\,")[1]));
//							}
//						}
//					}
//				}
//							
////				System.out.println("==========================================================");
//				
//				for(int j=0;j<monster.size();j++)
//				{
//					while(monster.get(j).number>0)
//					{
//						monster.get(j).number --;
//						
//						int point = ExternalMethods.throwDice(0, monsterCoordinate.size()/2-1);
//						
//						while(monsterCoordinate.get(point*2)==-1)
//						{
//							point = ExternalMethods.throwDice(0, monsterCoordinate.size()/2-1);
//						}
//						
//						addMonster(gameMain, (int)(monsterCoordinate.get(point*2)*GameConfig.f_zoom), (int)((monsterCoordinate.get(point*2+1)+150)*GameConfig.f_zoom), monster.get(j).id);
//						
////						System.out.println(monster.get(j).id+"=========>>"+monsterCoordinate.get(point*2)+"===========>>>"+monsterCoordinate.get(point*2+1));
//						
//						monsterCoordinate.set(point*2, -1);
//					}
//				}											
//			}
//			else
//			{
//				waveTime[i] --;
//			}
//		}
//	}
//	
//	class Monster
//	{
//		public String id;
//		
//		public int number;
//		
//	}
//	
//	private void addMonster(GameMain gameMain, int x, int y, String index)
//	{
//		//	G1：	石器咪咪	
//		//	G2：	石器地鼠	石器猪
//		//	G3：	石器地熊	石器猴
//		//	G4：	石器猛犸	
//
//		int rmd = 0;
//		
//		if(index.equals("G1"))
//		{
//			gameMain.gameMonster.addEnemy(SpriteLibrary.Enemy_MIMI, x, y, 1, 
//					gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 10);
//		}
//		else if(index.equals("G2"))
//		{
//			rmd = ExternalMethods.throwDice(0, 1);
//			
//			if(rmd==0)
//				gameMain.gameMonster.addEnemy(SpriteLibrary.Enemy_DS, x, y, 1,
//					gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, ExternalMethods.throwDice(25, 75));	
//			else
//				gameMain.gameMonster.addEnemy(SpriteLibrary.Enemy_Z, x, y, 1, 
//					gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 10);
//		}
//		else if(index.equals("G3"))
//		{
//			rmd = ExternalMethods.throwDice(0, 1);
//			
//			if(rmd==0)
//				gameMain.gameMonster.addEnemy(SpriteLibrary.Enemy_X, x, y, 1, 
//					gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 50, 10);
//			else
//				gameMain.gameMonster.addEnemy(SpriteLibrary.Enemy_HZ, x, y, 1, 
//					gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 100, 10);
//
//		}
//		else if(index.equals("G4"))
//		{
//			gameMain.gameMonster.addEnemy(SpriteLibrary.Enemy_MM, x, y, 1, 
//					gameMain.slingshot.SLINGSHOT_Y-gameMain.spriteLattice.getSpriteLatticeHeight(), 1, 100, 10);			
//		}			
//	}
}
