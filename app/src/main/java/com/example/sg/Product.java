package com.example.sg;

import org.json.JSONObject;

public class Product {
    private final int id;
    private final String name;
    private final String type;
    private final String number;
    private final double price;

    public Product(JSONObject jObject) {
        this.id =jObject.optInt("id");
        this.name = jObject.optString("name");
        this.type = jObject.optString("type");
        this.number = jObject.optString("number");
        this.price = jObject.optDouble("price");
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
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

    public String getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }
}
