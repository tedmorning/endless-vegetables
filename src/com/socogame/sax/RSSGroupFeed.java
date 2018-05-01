package com.socogame.sax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class RSSGroupFeed {
    private int itemcount;
    private List<RSSGroupItem> itemlist;
    
    public RSSGroupFeed(){
        itemlist = new Vector<RSSGroupItem>(0);
    }
    
    /**
     * 负责将一个RSSItem加入到RSSFeed类中
     * @param item
     * @return
     */
    public int addItem(RSSGroupItem item){
        itemlist.add(item);
        itemcount++;
        return itemcount;
    }
    
    public RSSGroupItem getItem(int location){
        return itemlist.get(location);
    }
    
    public int getItemcount() {
        return itemcount;
    }

    public void setItemcount(int itemcount) {
        this.itemcount = itemcount;
    }

    public List<RSSGroupItem> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<RSSGroupItem> itemlist) {
        this.itemlist = itemlist;
    }
}
