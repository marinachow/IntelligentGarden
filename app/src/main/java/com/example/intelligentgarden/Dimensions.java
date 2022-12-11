package com.example.intelligentgarden;

import org.json.JSONObject;

public class Dimensions {
    private final int numRows;
    private final int numCols;

    public Dimensions(JSONObject jObject) {
        this.numRows =jObject.optInt("numRows");
        this.numCols =jObject.optInt("numCols");
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}
