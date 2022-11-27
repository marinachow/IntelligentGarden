package com.example.intelligentgarden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class DisplayGardenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_garden);

        Button buttonAddPlant = (Button) findViewById(R.id.buttonAddPlant);
        Button buttonEditSurface = (Button) findViewById(R.id.buttonEditSurface);

        buttonAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayGardenActivity.this, EditPlantActivity.class);
                startActivity(intent);
            }
        });

        buttonEditSurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayGardenActivity.this, EditSurfaceActivity.class);
                startActivity(intent);
            }
        });
    }
}