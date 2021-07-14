package com.example.chapter3.homework;

public class Friend {
    private String Name;
    private String Speak;
    private int IconIndex;

    public Friend(){}

    public Friend(String aname, String aspeak, int aindex){
        this.Name = aname;
        this.Speak = aspeak;
        this.IconIndex = aindex;
    }
    public int getIcon() {
        return this.IconIndex;
    }
    public String getName(){
        return this.Name;
    }
    public String getSpeak(){
        return this.Speak;
    }


}
