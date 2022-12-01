package com.example.intelligentgarden;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Plant implements Parcelable {
    private final int id;
    private final String name;
    private int qty;
    private ArrayList<Plant> enemies = new ArrayList<>();

    public Plant(JSONObject jObject) {
        this.id =jObject.optInt("id");
        this.name = jObject.optString("name");
        this.qty = jObject.optInt("qty");
        JSONArray jsonArray = jObject.optJSONArray("enemies");
        if (jsonArray != null) {
            try {
                for (int i=0;i<jsonArray.length();i++){
                    enemies.add((Plant)jsonArray.get(i));
                }
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }
    }

    protected Plant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        qty = in.readInt();
        enemies = in.createTypedArrayList(Plant.CREATOR);
    }

    public static final Creator<Plant> CREATOR = new Creator<Plant>() {
        @Override
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        @Override
        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(qty);
        parcel.writeTypedList(enemies);
    }
}
