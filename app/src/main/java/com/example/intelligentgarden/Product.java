package com.example.intelligentgarden;

import org.json.JSONObject;

public class Product {
    private final int id;
    private final String name;
    private final String type;
    private final int qty;
    private final double price;

    public Product(JSONObject jObject) {
        this.id =jObject.optInt("id");
        this.name = jObject.optString("name");
        this.type = jObject.optString("type");
        this.qty = jObject.optInt("qty");
        this.price = jObject.optDouble("price");
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", qty='" + qty + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }
}
