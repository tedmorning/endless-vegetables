package com.socogame.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSGroupHandler extends DefaultHandler{
	RSSGroupFeed RssGroupFeed;
    RSSGroupItem RssGroupItem;
    
    final int RSS_TIME = 1;
    final int RSS_WAVE = 2;
    final int RSS_MONSTERNUMBER = 3;
    final int RSS_WAVEGROUP = 4;
    
    final int RSS_IDGROUP = 5;
    final int RSS_MONSTERTYPE = 6;
    
    int currentstate = 0;
    
    public RSSGroupHandler(){}
    
    public RSSGroupFeed getFeed(){
        return RssGroupFeed;
    }
    
    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
    	RssGroupFeed = new RSSGroupFeed();
    	RssGroupItem = new RSSGroupItem();
    }
    
    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        if(localName.equals("channel")){
            currentstate = 0;
            return;
        }
        if(localName.equals("group")){
        	RssGroupItem = new RSSGroupItem();
            return;
        }
        if(localName.equals("time")){
            currentstate = RSS_TIME;
            return;
        }
        if(localName.equals("wave")){
            currentstate = RSS_WAVE;
            return;
        }
        if(localName.equals("monsterNumber")){
            currentstate = RSS_MONSTERNUMBER;
            return;
        }
        if(localName.equals("wavegroup")){
            currentstate = RSS_WAVEGROUP;
            return;
        }
        if(localName.equals("monstergroup")){
        	RssGroupItem.initGroup();
            return;
        }
        if(localName.equals("idgroup")){
        	currentstate = RSS_IDGROUP;
            return;
        }
        if(localName.equals("monsterType")){
        	currentstate = RSS_MONSTERTYPE;
            return;
        }
        currentstate = 0;
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        if(localName.equals("group")){
        	RssGroupFeed.addItem(RssGroupItem);
            return;
        }        
        if(localName.equals("monstergroup")){
        	RssGroupItem.setGroup();
            return;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub
        String theString = new String(ch, start, length);
        switch(currentstate){
        case RSS_TIME:
        	RssGroupItem.setTime(theString);
            currentstate = 0;
            break;
        case RSS_WAVE:
        	RssGroupItem.setWave(theString);
            currentstate = 0;
            break;
        case RSS_MONSTERNUMBER:
        	RssGroupItem.setMonsterNumber(theString);
            currentstate = 0;
            break;
        case RSS_WAVEGROUP:
        	RssGroupItem.setWaveGroup(theString);
            currentstate = 0;
            break;
        case RSS_IDGROUP:
        	RssGroupItem.setIdGroup(theString);
            currentstate = 0;
            break;
        case RSS_MONSTERTYPE:
        	RssGroupItem.setMonsterType(theString);
            currentstate = 0;
            break;    
        default:
            return;
        }        
    }
}
