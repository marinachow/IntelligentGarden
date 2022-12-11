package com.example.sg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class EditBuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        String type = getIntent().getStringExtra("type");
        int number = getIntent().getIntExtra("number", 1);
        double price = getIntent().getDoubleExtra("price", 1.0);
/*
        final EditText nameEditTxt = (EditText) findViewById(R.id.nameEditTxt);
        final EditText typeEditTxt = (EditText) findViewById(R.id.typeEditTxt);
        final EditText numberEditTxt = (EditText) findViewById(R.id.numberEditTxt);
        final EditText priceEditTxt = (EditText) findViewById(R.id.priceEditTxt);
        TextView idTxt = (TextView) findViewById(R.id.idEditTxt);
*/
        Button buttonCancel = (Button) findViewById(R.id.button_cancel);
        Button buttonPay = (Button) findViewById(R.id.button_pay);
/*
        if(id!=0){
            idTxt.setText(""+id);
            nameEditTxt.setText(name);
            typeEditTxt.setText(type);
            numberEditTxt.setText(""+number);
            priceEditTxt.setText(""+price);
            buttonCancel.setText("Cancel");
            buttonPay.setText("Pay");
        }
*/
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=0){ // Suppression
                    try {
                        ConnectionRest connectionRest = new ConnectionRest();
                        JSONObject product = new JSONObject();
                        product.put("id", id);
                        connectionRest.setJsonObj(product);
                        connectionRest.execute("DELETE");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(EditBuyActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(EditBuyActivity.this, BuyActivity.class);
                    startActivity(intent);
            }
        });
    }
}