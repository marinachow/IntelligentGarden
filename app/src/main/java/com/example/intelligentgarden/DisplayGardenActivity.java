package com.example.intelligentgarden;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class DisplayGardenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_garden);

        Button addPlantButton = (Button) findViewById(R.id.AddPlantButton);
        Button editSurfaceButton = (Button) findViewById(R.id.EditSurfaceButton);
        TableLayout gardenGrid = (TableLayout) findViewById(R.id.tableLayout);
        int numRows = getIntent().getIntExtra("numRows", 1);
        int numCols = getIntent().getIntExtra("numCols", 1);

        addPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayGardenActivity.this, EditPlantActivity.class);
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
                TextView plant = new TextView(this);
                plant.setText("Empty");
                plant.setWidth(150);
                plant.setHeight(150);
                plant.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
                plant.setGravity(Gravity.CENTER);
                plant.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                row.addView(plant);
            }
            gardenGrid.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}