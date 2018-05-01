package com.socogame.sax;

public class RSSItem {
    public static String ID = "id";
    public static String TYPE = "type";
    
    public String id;
    public String type;
    public String flashTime;
    public String coordinate;
    public String number;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFlashTime() {
        return flashTime;
    }
    public void setFlashTime(String flashTime) {
        this.flashTime = flashTime;
    }
    public String getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }    
}
