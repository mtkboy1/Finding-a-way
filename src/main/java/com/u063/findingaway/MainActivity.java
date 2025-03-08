package com.u063.findingaway;

import static android.view.View.GONE;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    map mapp = new map(); //Карта
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //finding();
    }
    public void add(View view){
        LinearLayout coordinates = findViewById(R.id.coordinates);
        EditText from = findViewById(R.id.from);
        EditText to = findViewById(R.id.to);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setGravity(LinearLayout.VERTICAL);

        TextView tx = new TextView(this);
        tx.setText(from.getText()+"-");
        tx.setTextSize(35);
        linearLayout.addView(tx);

        tx = new TextView(this);
        tx.setText(to.getText());
        tx.setTextSize(35);
        linearLayout.addView(tx);

        Button b = new Button(this);
        b.setText("Delete");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(GONE);
                mapp.delete(Integer.parseInt(from.getText().toString()),Integer.parseInt(to.getText().toString()));
            }
        });
        linearLayout.addView(b);

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(Integer.parseInt(to.getText().toString()));
        mapp.put(Integer.parseInt(from.getText().toString()),arr);

        coordinates.addView(linearLayout);
    }
    public void finding(View view){
        EditText res = findViewById(R.id.result);
        TextView resulting = findViewById(R.id.Res);
        ArrayList<pathDictionary> path = new ArrayList<>(); //Лист в котором лежит словарь
        path.add(new pathDictionary(0));
        path.get(0).put(0,1); //0 - координата, 1 - был
        path.get(0).setWay(mapp.getMapDictionary().get(0)); //получаем маршруты от 0
        int find = Integer.parseInt(res.getText().toString());
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
        resulting.setText("steps: "+path.get(path.size() - 1).steps+", size of array: "+(path.size()));
        Log.e("Result","steps: "+path.get(path.size() - 1).steps+", size of array: "+(path.size()));
    }
}