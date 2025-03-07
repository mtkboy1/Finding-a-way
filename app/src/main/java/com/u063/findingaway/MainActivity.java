package com.u063.findingaway;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    //ArrayList<ArrayList<Dictionary<Integer,Integer>>> path = new ArrayList<>(); //Лист с листом в котором лежит словарь
    ArrayList<pathDictionary> path = new ArrayList<>(); //Лист в котором лежит словарь
    map mapp = new map(); //Карта
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        finding();
    }
    /*void finding(){
        path.add(new pathDictionary(0));
        path.get(0).put(0,1); //0 - координата, 1 - был
        int find = 100000;
        boolean result = false;
        int steps=0;
        while(!result) {
            for (int i = 0; i < path.size(); i++) {
                Dictionary dictionary = path.get(0).getDictionary();
                if (dictionary.get(path.get(0).getLastCoord() + 1) == null) {
                    path.add(new pathDictionary(path.get(0)));
                    path.get(path.size() - 1).put(path.get(0).getLastCoord() + 1, 1);
                    path.get(path.size() - 1).steps=path.get(0).steps+1;
                }
                if (dictionary.get(path.get(0).getLastCoord() - 1) == null) {
                    path.add(new pathDictionary(path.get(0)));
                    path.get(path.size() - 1).put(path.get(0).getLastCoord() - 1, 1);
                    path.get(path.size() - 1).steps=path.get(0).steps+1;
                }
                path.remove(0);
                if(path.get(path.size() - 1).getLastCoord()==find){
                    result=true;
                }
                if(result) break;
            }
        }
        Log.e("Result","steps: "+path.get(path.size() - 1).steps+", size of array: "+(path.size()));
    }*/
    void finding(){
        path.add(new pathDictionary(0));
        path.get(0).put(0,1); //0 - координата, 1 - был
        path.get(0).setWay(mapp.getMapDictionary().get(0)); //получаем маршруты от 0
        int find = 8;
        boolean result = false;
        int steps=0;
        while(!result) {
            for (int i = 0; i < path.size(); i++) {
                Dictionary dictionary = path.get(0).getDictionary();
                ArrayList<Integer> way = path.get(0).getWay();
                if(way != null) {
                    for (int z = 0; z < way.size(); z++) {
                       // Log.e("", "" + mapp.getMapDictionary().get(way.get(z)));
                        if (dictionary.get(way.get(z)) == null) {
                            path.add(new pathDictionary(path.get(0)));
                            path.get(path.size() - 1).put(way.get(z), 1);
                            path.get(path.size() - 1).setWay(mapp.getMapDictionary().get(way.get(z)));
                            path.get(path.size() - 1).setLastCoord(way.get(z));
                            path.get(path.size() - 1).steps = path.get(0).steps + 1;
                            //Log.e("", "" + way.get(z));
                        }
                        //Log.e("", "" + path.get(path.size() - 1).getWay());
                        if (path.get(path.size() - 1).getLastCoord() == find) {
                            result = true;
                        }
                        if (result) break;
                    }
                }
                path.remove(0);
            }
        }
        Log.e("Result","steps: "+path.get(path.size() - 1).steps+", size of array: "+(path.size()));
    }
}