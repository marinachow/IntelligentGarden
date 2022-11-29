package com.example.intelligentgarden;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class EditPlantActivity extends AppCompatActivity {

    TextView textViewEnemies;
    boolean[] selectedEnemies;
    ArrayList<Integer> enemyList = new ArrayList<>();
    String[] enemyArray = {"Java", "C++", "Kotlin", "C", "Python", "Javascript"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plant);

        int numRows = getIntent().getIntExtra("numRows", 1);
        int numCols = getIntent().getIntExtra("numCols", 1);

        textViewEnemies = findViewById(R.id.textViewEnemies);
        selectedEnemies = new boolean[enemyArray.length];

        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        Button buttonOk = (Button) findViewById(R.id.buttonOk);
        Button buttonMyGarden = (Button) findViewById(R.id.buttonMyGarden);

        textViewEnemies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditPlantActivity.this);
                builder.setTitle("Select Enemies");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(enemyArray, selectedEnemies, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            enemyList.add(i);
                            Collections.sort(enemyList);
                        } else {
                            enemyList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < enemyList.size(); j++) {
                            stringBuilder.append(enemyArray[enemyList.get(j)]);
                            if (j != enemyList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        textViewEnemies.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < selectedEnemies.length; j++) {
                            selectedEnemies[j] = false;
                            enemyList.clear();
                            textViewEnemies.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        final int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        int qty = getIntent().getIntExtra("qty", 0);

        final EditText nameEditTxt = (EditText) findViewById(R.id.editTextName);
        final EditText qtyEditTxt = (EditText) findViewById(R.id.editTextQty);
        TextView idTxt = (TextView) findViewById(R.id.textViewId);

        if(id != 0) {
            idTxt.setText(""+id);
            nameEditTxt.setText(name);
            qtyEditTxt.setText(qty);
            buttonCancel.setText("Delete");
            buttonOk.setText("Edit");
        }

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != 0) {
                    try {
                        //ConnectionRest connectionRest = new ConnectionRest();
                        JSONObject plant = new JSONObject();
                        plant.put("id", id);
                        //connectionRest.setJsonObj(plant);
                        //connectionRest.execute("DELETE");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(EditPlantActivity.this, DisplayGardenActivity.class);
                intent.putExtra("numRows", numRows);
                intent.putExtra("numCols", numCols);
                startActivity(intent);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ContentValues values = new ContentValues();

                    values.put(PlantProvider.NAME, nameEditTxt.getText().toString());
                    values.put(PlantProvider.QTY, qtyEditTxt.getText().toString());

                    Uri uri = getContentResolver().insert(PlantProvider.CONTENT_URI, values);

                    Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
            }
        });

        buttonMyGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPlantActivity.this, DisplayGardenActivity.class);
                intent.putExtra("numRows", numRows);
                intent.putExtra("numCols", numCols);
                startActivity(intent);
            }
        });
    }
}