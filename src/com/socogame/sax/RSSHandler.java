package com.socogame.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler{
    RSSFeed RssFeed;
    RSSItem RssItem;
    
    final int RSS_ID = 1;
    final int RSS_TYPE = 2;
    final int RSS_FLASHTIME = 3;
    final int RSS_COORDINATE = 4;
    final int RSS_NUMBER = 5;
    
    int currentstate = 0;
    
    public RSSHandler(){}
    
    public RSSFeed getFeed(){
        return RssFeed;
    }
    
    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        RssFeed = new RSSFeed();
        RssItem = new RSSItem();
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
        if(localName.equals("item")){
            RssItem = new RSSItem();
            return;
        }
        if(localName.equals("id")){
            currentstate = RSS_ID;
            return;
        }
        if(localName.equals("type")){
            currentstate = RSS_TYPE;
            return;
        }
        if(localName.equals("flashTime")){
            currentstate = RSS_FLASHTIME;
            return;
        }
        if(localName.equals("coordinate")){
            currentstate = RSS_COORDINATE;
            return;
        }
        if(localName.equals("number")){
            currentstate = RSS_NUMBER;
            return;
        }
        currentstate = 0;
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        if(localName.equals("item")){
            RssFeed.addItem(RssItem);
            return;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub
        String theString = new String(ch, start, length);
        switch(currentstate){
        case RSS_ID:
            RssItem.setId(theString);
            currentstate = 0;
            break;
        case RSS_TYPE:
            RssItem.setType(theString);
            currentstate = 0;
            break;
        case RSS_FLASHTIME:
            RssItem.setFlashTime(theString);
            currentstate = 0;
            break;
        case RSS_COORDINATE:
            RssItem.setCoordinate(theString);
            currentstate = 0;
            break;
        case RSS_NUMBER:
            RssItem.setNumber(theString);
            currentstate = 0;
            break;
        default:
            return;
        }
    }
}
