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

    public Plant(int id, String name, int qty) {
        this.id = id;
        this.name = name;
        this.qty = qty;
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

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                '}';
    }
}
