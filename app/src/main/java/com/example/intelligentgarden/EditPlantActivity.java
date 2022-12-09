package com.example.intelligentgarden;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class EditPlantActivity extends AppCompatActivity {
    private List<String> allPlantNamesList = new ArrayList<>();
    private String[] allPlantNamesArray;
    private boolean[] selectedEnemies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plant);

        if (getAllPlants() != null) {
            allPlantNamesList = getAllPlants().stream().map(Plant::getName).collect(Collectors.toList());
            allPlantNamesArray = allPlantNamesList.toArray(new String[allPlantNamesList.size()]);
            selectedEnemies = new boolean[allPlantNamesArray.length];
        }

        final int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        int qty = getIntent().getIntExtra("qty", 0);
        String[] enemyNames = getIntent().getStringArrayExtra("enemyNames");

        final EditText nameEditTxt = findViewById(R.id.editTextName);
        final EditText qtyEditTxt = findViewById(R.id.editTextQty);
        TextView idTxt = findViewById(R.id.textViewId);

        Button buttonCancel = findViewById(R.id.buttonCancel);
        Button buttonOk = findViewById(R.id.buttonOk);

        int numRows = getIntent().getIntExtra("numRows", 1);
        int numCols = getIntent().getIntExtra("numCols", 1);

        TextView textViewEnemies = findViewById(R.id.textViewEnemies);

        ArrayList<String> finalSelectedEnemies = new ArrayList<>();
        ArrayList<Integer> enemyList = new ArrayList<>();

        if (id!=0) {
            idTxt.setText(""+id);
            nameEditTxt.setText(name);
            qtyEditTxt.setText(""+qty);
            buttonCancel.setText("Supprimer");
            buttonOk.setText("Modifier");
            allPlantNamesList.removeIf(str -> str.equals(name));
            allPlantNamesArray = allPlantNamesList.toArray(new String[allPlantNamesList.size()]);
            if (enemyNames != null) {
                StringBuilder enemiesSB = new StringBuilder();
                int curEnemy = 0;
                for (int i = 0; i < allPlantNamesArray.length; i++) {
                    if (curEnemy >= enemyNames.length) {
                        break;
                    }
                    String curName = allPlantNamesArray[i];
                    if (curName.equals(enemyNames[curEnemy])) {
                        selectedEnemies[i] = true;
                        enemyList.add(i);
                        enemiesSB.append(curName);
                        if (curEnemy != enemyNames.length - 1) {
                            enemiesSB.append(", ");
                        }
                        curEnemy++;
                    }
                }
                textViewEnemies.setText(enemiesSB);
            }
        }

        textViewEnemies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditPlantActivity.this);
                builder.setTitle("Select Enemies");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(allPlantNamesArray, selectedEnemies, new DialogInterface.OnMultiChoiceClickListener() {
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
                            stringBuilder.append(allPlantNamesArray[enemyList.get(j)]);
                            finalSelectedEnemies.add(allPlantNamesArray[enemyList.get(j)]);
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

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=0){ // Suppression
                    try {
                        ConnectionRest connectionRest = new ConnectionRest();
                        JSONObject plant = new JSONObject();
                        plant.put("id", id);
                        connectionRest.setJsonObj(plant);
                        connectionRest.execute("DELETE");
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
                try {
                    ConnectionRest connectionRest = new ConnectionRest();
                    JSONObject plant = new JSONObject();
                    if(id!=0){
                        plant.put("id", id);
                    }
                    plant.put("name", nameEditTxt.getText().toString());
                    plant.put("qty", Integer.parseInt(qtyEditTxt.getText().toString()));
                    plant.put("enemyNames", finalSelectedEnemies);
                    connectionRest.setJsonObj(plant);
                    if(id!=0){
                        connectionRest.execute("PUT"); // Modification
                    }else {
                        connectionRest.execute("POST"); // Création
                    }

                    Intent intent = new Intent(EditPlantActivity.this, DisplayGardenActivity.class);
                    intent.putExtra("numRows", numRows);
                    intent.putExtra("numCols", numCols);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public ArrayList<Plant> getAllPlants(){
        try{
            ConnectionRest connectionRest = new ConnectionRest();
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();
            if (listJsonObjs != null) {
                return connectionRest.parsePlant(listJsonObjs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}