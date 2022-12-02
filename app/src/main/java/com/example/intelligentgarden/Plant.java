package com.example.intelligentgarden;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Plant {
    private final int id;
    private final String name;
    private int qty;
    private String[] enemyNames;

    public Plant(JSONObject jObject) {
        this.id =jObject.optInt("id");
        this.name = jObject.optString("name");
        this.qty = jObject.optInt("qty");
        try {
            String enemyNamesString = jObject.getString("enemyNames");
            JSONArray enemyNamesJArray = new JSONArray(enemyNamesString);
            if (enemyNamesJArray.length() != 0) {
                enemyNames = new String[enemyNamesJArray.length()];
                for (int i=0; i < enemyNamesJArray.length(); i++){
                    this.enemyNames[i] = enemyNamesJArray.get(i).toString();
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
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

    public String[] getEnemyNames() {
        return enemyNames;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", qty:" + qty +
                ", enemyNames:" + enemyNames +
                '}';
    }
}
