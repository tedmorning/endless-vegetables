package com.socogame.sax;

import java.util.List;
import java.util.Vector;

public class RSSGroupItem {
	
	public String time;
    public String wave;
    public String monsterNumber;
    public String waveGroup;
    public Group group;
    public List<Group> monstergroup;
    
    public RSSGroupItem()
    {
    	monstergroup = new Vector<Group>();
    }
    
    public String getTime() {    	
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getWave() {
        return wave;
    }
    
    public void setWave(String wave) {
        this.wave = wave;
    }
    
    public String getMonsterNumber() {
        return monsterNumber;
    }
    
    public void setMonsterNumber(String monsterNumber) {
        this.monsterNumber = monsterNumber;
    }
    
    public String getWaveGroup() {
        return waveGroup;
    }
    
    public void setWaveGroup(String waveGroup) {
        this.waveGroup = waveGroup;
    }
    
    public void initGroup(){
    	group = new Group();    	
    }
    
    public List<Group> getMonsterGroup() {    	
        return monstergroup;
    }
    
    public void setIdGroup(String idgroup) {
       group.idGroup = idgroup;
    }
    
    public void setMonsterType(String monsterType) {
        group.monsterType = monsterType;
    }
    
    public void setGroup(){
    	monstergroup.add(group);
    }    
        
    public class Group
    {
    	public String idGroup;
        public String monsterType;
    }
}
