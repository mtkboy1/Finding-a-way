package com.u063.findingaway;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class pathDictionary {
    private int lastCoord;
    private ArrayList<Integer> way;
    public int steps = 0; //delete
    private Dictionary<Integer,Integer> dictionary;
    public pathDictionary(int lastCoord){
        this.lastCoord = lastCoord;
        this.dictionary = new Hashtable<>();
    }
    public pathDictionary(pathDictionary dictionary){
        this.dictionary = dictionary.getDictionary();
        this.lastCoord = dictionary.getLastCoord();
        //this.way = dictionary.getWay();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public ArrayList<Integer> getWay() {
        return way;
    }
    public void setWay(ArrayList<Integer> w) {
        this.way = w;
    }

    public int getLastCoord() {
        return lastCoord;
    }
    public void setLastCoord(int coord) {
        lastCoord = coord;
    }
    public void put(int key, int val){
        this.dictionary.put(key,val);
        this.lastCoord = key;
    }
    public int getSize(){
        return dictionary.size();
    }
}
