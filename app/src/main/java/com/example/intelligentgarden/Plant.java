package com.example.intelligentgarden;

import org.json.JSONObject;

public class Plant {
    private final int id;
    private final String name;
    private int qty;

    public Plant(JSONObject jObject) {
        this.id =jObject.optInt("id");
        this.name = jObject.optString("name");
        this.qty = jObject.optInt("qty");
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
}
