package com.example.fob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    static List<JSONObject> sortedPrice(){
        JSONArray jsonArr = null;
        try {
            jsonArr = new JSONArray(item);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<JSONObject> getItems = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            try {
                getItems.add(jsonArr.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        getItems.sort((a, b) -> {
            int valA = 0;
            int valB = 0;
            try {
                valA = a.getInt("price");
                valB = b.getInt("price");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return valB - valA;
        });

        return getItems;
    }

    static class Singleton {
        private static Singleton singleton;

        private Singleton() {}

        public static Singleton getInstance() {
            if (singleton == null) singleton = new Singleton();
            return singleton;
        }
    }

}