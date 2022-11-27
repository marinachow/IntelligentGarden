package com.example.intelligentgarden;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

        textViewEnemies = findViewById(R.id.textViewEnemies);
        selectedEnemies = new boolean[enemyArray.length];

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
        Spinner spinnerQty = (Spinner) findViewById(R.id.spinnerQty);
        final EditText qtyEditTxt = (EditText) spinnerQty.getSelectedItem();
        TextView idTxt = (TextView) findViewById(R.id.textViewId);

        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        Button buttonOk = (Button) findViewById(R.id.buttonOk);

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
                startActivity(intent);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //ConnectionRest connectionRest = new ConnectionRest();
                    JSONObject plant = new JSONObject();
                    if (id != 0){
                        plant.put("id", id);
                    }
                    plant.put("name", nameEditTxt.getText().toString());
                    plant.put("qty", qtyEditTxt.getText().toString());
                    //connectionRest.setJsonObj(plant);
                    if (id != 0){
                        //connectionRest.execute("PUT");
                    } else {
                        //connectionRest.execute("POST");
                    }

                    Intent intent = new Intent(EditPlantActivity.this, DisplayGardenActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}