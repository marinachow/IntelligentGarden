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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_garden);

        Button addPlantButton = findViewById(R.id.AddPlantButton);
        Button editSurfaceButton = findViewById(R.id.EditSurfaceButton);
        TableLayout gardenGrid = findViewById(R.id.tableLayout);

        int numRows = getIntent().getIntExtra("numRows", 1);
        int numCols = getIntent().getIntExtra("numCols", 1);

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
                square.setText("Empty");
                square.setWidth(150);
                square.setHeight(150);
                square.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
                square.setGravity(Gravity.CENTER);
                square.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                row.addView(square);
            }
            gardenGrid.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        ArrayList<Plant> allPlants = getGridData();
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
    public ArrayList<Plant> getGridData(){
        try{
            ConnectionRest connectionRest = new ConnectionRest();
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();
            if (listJsonObjs != null) {
                return connectionRest.parse(listJsonObjs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}