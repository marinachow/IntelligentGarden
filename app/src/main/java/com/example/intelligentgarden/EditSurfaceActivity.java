package com.example.intelligentgarden;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class EditSurfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_surface);

        Spinner rowsSpinner = (Spinner) findViewById(R.id.spinnerRows);
        Spinner colsSpinner = (Spinner) findViewById(R.id.spinnerCols);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.surface_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rowsSpinner.setAdapter(adapter);
        colsSpinner.setAdapter(adapter);

    }
}