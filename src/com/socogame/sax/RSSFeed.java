package com.socogame.sax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class RSSFeed {
    private String title;
    private int itemcount;
    private List<RSSItem> itemlist;
    
    public RSSFeed(){
        itemlist = new Vector<RSSItem>(0);
    }
    
    /**
     * ����һ��RSSItem���뵽RSSFeed����
     * @param item
     * @return
     */
    public int addItem(RSSItem item){
        itemlist.add(item);
        itemcount++;
        return itemcount;
    }
    
    public RSSItem getItem(int location){
        return itemlist.get(location);
    }
    
    public List<RSSItem> getAllItems(){
        return itemlist;
    }
    
    /**
     * �����RSSFeed���������б�����Ҫ������
     * @return
     */
    public List getAllItemForListView(){
        List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
        int size = itemlist.size();
        for(int i=0 ; i<size ; i++){
            HashMap<String , Object> item = new HashMap<String, Object>();
            item.put(RSSItem.ID, itemlist.get(i).getId());
            item.put(RSSItem.TYPE, itemlist.get(i).getType());
            data.add(item);
        }
        return data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItemcount() {
        return itemcount;
    }

    public void setItemcount(int itemcount) {
        this.itemcount = itemcount;
    }

    public List<RSSItem> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<RSSItem> itemlist) {
        this.itemlist = itemlist;
    }
    
}
