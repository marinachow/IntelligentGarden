package com.example.intelligentgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        String type = getIntent().getStringExtra("type");
        int qty = getIntent().getIntExtra("qty", 1);
        double price = getIntent().getDoubleExtra("price", 1.0);

        final EditText nameEditTxt = (EditText) findViewById(R.id.nameEditTxt);
        final EditText typeEditTxt = (EditText) findViewById(R.id.typeEditTxt);
        final EditText qtyEditTxt = (EditText) findViewById(R.id.numberEditTxt);
        final EditText priceEditTxt = (EditText) findViewById(R.id.priceEditTxt);
        TextView idTxt = (TextView) findViewById(R.id.idEditTxt);

        Button buttonCancel = (Button) findViewById(R.id.button_cancel);
        Button buttonOk = (Button) findViewById(R.id.button_pay);

        if(id!=0){
            idTxt.setText(""+id);
            nameEditTxt.setText(name);
            typeEditTxt.setText(type);
            qtyEditTxt.setText(""+qty);
            priceEditTxt.setText(""+price);
            buttonCancel.setText("Supprimer");
            buttonOk.setText("Modifier");
        }

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=0){ // Suppression
                    try {
                        GardenConnectionRest gardenConnectionRest = new GardenConnectionRest();
                        JSONObject product = new JSONObject();
                        product.put("id", id);
                        gardenConnectionRest.setJsonObj(product);
                        gardenConnectionRest.execute("DELETE");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(EditProductActivity.this, SellActivity.class);
                startActivity(intent);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GardenConnectionRest gardenConnectionRest = new GardenConnectionRest();
                    JSONObject product = new JSONObject();
                    if(id!=0){
                        product.put("id", id);
                    }
                    product.put("name", nameEditTxt.getText().toString());
                    product.put("type", typeEditTxt.getText().toString());
                    product.put("qty", Integer.parseInt(qtyEditTxt.getText().toString()));
                    product.put("price", Double.parseDouble(priceEditTxt.getText().toString()));
                    gardenConnectionRest.setJsonObj(product);
                    if(id!=0){
                        gardenConnectionRest.execute("PUT"); // Modification
                    }else {
                        gardenConnectionRest.execute("POST"); // Création
                    }

                    Intent intent = new Intent(EditProductActivity.this, SellActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}