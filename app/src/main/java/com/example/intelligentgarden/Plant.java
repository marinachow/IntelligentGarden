package com.example.intelligentgarden;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Plant {
    private final int id;
    private final String name;
    private int qty;
    private ArrayList<Plant> enemies;
    private int[] rows;
    private int[] cols;

    public Plant(JSONObject jObject) throws JSONException {
        this.id =jObject.optInt("id");
        this.name = jObject.optString("name");
        this.qty = jObject.optInt("qty");
        this.enemies = new ArrayList<>();
        JSONArray jArray = jObject.optJSONArray("enemies");
        if (jArray != null) {
            for (int i=0;i<jArray.length();i++){
                this.enemies.add((Plant) jArray.get(i));
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public ArrayList<Plant> getEnemies() {
        return enemies;
    }
}
