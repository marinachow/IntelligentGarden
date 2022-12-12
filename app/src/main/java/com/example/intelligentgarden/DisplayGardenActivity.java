package com.example.intelligentgarden;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DisplayGardenActivity extends AppCompatActivity {
    int numRows = 1;
    int numCols = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_garden);

        Button addPlantButton = findViewById(R.id.AddPlantButton);
        Button editSurfaceButton = findViewById(R.id.EditSurfaceButton);
        TableLayout gardenGrid = findViewById(R.id.tableLayout);

        Dimensions dimensions = getDimensions();
        if (dimensions != null) {
            numRows = dimensions.getNumRows();
            numCols = dimensions.getNumCols();
        } else {
            GardenConnectionRest gardenConnectionRest = new GardenConnectionRest();
            gardenConnectionRest.execute("CREATE_SURFACE");
        }

        addPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayGardenActivity.this, EditPlantActivity.class);
                intent.putExtra("numRows", numRows);
                intent.putExtra("numCols", numCols);
                startActivity(intent);
            }
        });

        editSurfaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayGardenActivity.this, EditSurfaceActivity.class);
                intent.putExtra("numRows", numRows);
                intent.putExtra("numCols", numCols);
                startActivity(intent);
            }
        });
        for (int i = 0; i < numRows; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            for (int j = 0; j < numCols; j++) {
                TextView square = new TextView(this);
                square.setText("");
                square.setWidth(150);
                square.setHeight(150);
                square.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
                square.setGravity(Gravity.CENTER);
                square.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                row.addView(square);
            }
            gardenGrid.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        ArrayList<Plant> allPlants = getAllPlants();
        if (allPlants != null && allPlants.size() > 0) {
            int allPlantsIdx = 0;
            int curPlantCount = 0;
            int curPlantQty = allPlants.get(allPlantsIdx).getQty();
            outerLoop:
            for (int i = 0; i < numRows; i++) {
                TableRow row = (TableRow) gardenGrid.getChildAt(i);
                for (int j = 0; j < numCols; j++) {
                    TextView square = (TextView) row.getChildAt(j);
                    Plant curPlant = allPlants.get(allPlantsIdx);
                    square.setText(curPlant.getName());
                    square.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DisplayGardenActivity.this, EditPlantActivity.class);
                            intent.putExtra("id", curPlant.getId());
                            intent.putExtra("name", curPlant.getName());
                            intent.putExtra("qty", curPlant.getQty());
                            intent.putExtra("enemyNames", curPlant.getEnemyNames());
                            intent.putExtra("numRows", numRows);
                            intent.putExtra("numCols", numCols);
                            startActivity(intent);
                        }
                    });
                    curPlantCount++;
                    if (curPlantQty == curPlantCount) {
                        allPlantsIdx++;
                        if (allPlantsIdx == allPlants.size()) {
                            break outerLoop;
                        }
                        curPlantCount = 0;
                        curPlantQty = allPlants.get(allPlantsIdx).getQty();
                    }
                }
            }
        }
    }
    public ArrayList<Plant> getAllPlants(){
        try {
            GardenConnectionRest gardenConnectionRest = new GardenConnectionRest();
            gardenConnectionRest.execute("GET");
            String listJsonObjs = gardenConnectionRest.get();
            if (listJsonObjs != null) {
                return gardenConnectionRest.parsePlant(listJsonObjs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dimensions getDimensions(){
        try {
            GardenConnectionRest gardenConnectionRest = new GardenConnectionRest();
            gardenConnectionRest.execute("GET_SURFACE");
            String jsonObj = gardenConnectionRest.get();
            if (jsonObj != null) {
                return gardenConnectionRest.parseDimensions(jsonObj);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}