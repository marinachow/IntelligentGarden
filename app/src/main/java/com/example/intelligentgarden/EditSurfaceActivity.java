package com.example.intelligentgarden;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;


public class EditSurfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_surface);

        Spinner rowsSpinner = findViewById(R.id.spinnerRows);
        Spinner colsSpinner = findViewById(R.id.spinnerCols);

        ArrayAdapter<CharSequence> rowsAdapter = ArrayAdapter.createFromResource(this,
                R.array.rows_array, android.R.layout.simple_spinner_item);
        rowsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> colsAdapter = ArrayAdapter.createFromResource(this,
                R.array.cols_array, android.R.layout.simple_spinner_item);
        rowsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        rowsSpinner.setAdapter(rowsAdapter);
        colsSpinner.setAdapter(colsAdapter);

        int numRows = getIntent().getIntExtra("numRows", 1);
        rowsSpinner.setSelection(numRows-1);

        int numCols = getIntent().getIntExtra("numCols", 1);
        colsSpinner.setSelection(numCols-1);

        Button applyChangesButton = findViewById(R.id.applyChangesButton);

        applyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numRows = Integer.parseInt(rowsSpinner.getSelectedItem().toString());
                int numCols = Integer.parseInt(colsSpinner.getSelectedItem().toString());
                try {
                    ConnectionRest connectionRest = new ConnectionRest();
                    JSONObject jsonSurface = new JSONObject();
                    jsonSurface.put("numRows", numRows);
                    jsonSurface.put("numCols", numCols);
                    connectionRest.setJsonObj(jsonSurface);
                    connectionRest.execute("EDIT_SURFACE");
                } catch (JSONException e) {
                    Log.v("TAG", "[JSONException] e : " + e.getMessage());
                }
                Intent intent = new Intent(EditSurfaceActivity.this, DisplayGardenActivity.class);
                intent.putExtra("numRows", numRows);
                intent.putExtra("numCols", numCols);
                startActivity(intent);
            }
        });

    }
}