package com.u063.findingaway;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class map {
    private Dictionary<Integer, ArrayList<Integer>> mapDictionary = new Hashtable<>();
    public map(){
        /*ArrayList<Integer> soMap = new ArrayList<>();
        soMap.add(1);
        soMap.add(2);
        mapDictionary.put(0,soMap);
        soMap = new ArrayList<>();
        soMap.add(3);
        soMap.add(4);
        mapDictionary.put(1,soMap);
        soMap = new ArrayList<>();
        soMap.add(8);
        soMap.add(2);
        mapDictionary.put(3,soMap);
        soMap = new ArrayList<>();
        soMap.add(9);
        soMap.add(5);
        mapDictionary.put(4,soMap);*/
    }

    public Dictionary<Integer, ArrayList<Integer>> getMapDictionary() {
        return mapDictionary;
    }

    public void setMapDictionary(Dictionary<Integer, ArrayList<Integer>> mapDictionary) {
        this.mapDictionary = mapDictionary;
    }
    public void put(int key, ArrayList<Integer> arrayList){
        if(mapDictionary.get(key)!=null){
            ArrayList<Integer> arrayList1 = mapDictionary.get(key);
            arrayList1.add(arrayList.get(0));
            mapDictionary.put(key,arrayList1);
        } else {
            mapDictionary.put(key, arrayList);
        }
    }
    public void delete(int key, int element){
        if(mapDictionary.get(key)!=null){
            ArrayList<Integer> arrayList = mapDictionary.get(key);
            for(int i = 0; i<arrayList.size(); i++){
                if(arrayList.get(i)==element){
                    arrayList.remove(i);
                    break;
                }
            }
            mapDictionary.remove(key);
            mapDictionary.put(key, arrayList);
        }
    }
}
