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

public class EditBuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button buttonCancel = (Button) findViewById(R.id.button_cancel);
        Button buttonPay = (Button) findViewById(R.id.button_pay);

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

        if(id!=0){
            idTxt.setText(""+id);
            nameEditTxt.setText(name);
            typeEditTxt.setText(type);
            qtyEditTxt.setText(""+qty);
            priceEditTxt.setText(""+price);
            buttonPay.setText("Pay");
        }

        nameEditTxt.setEnabled(false);
        typeEditTxt.setEnabled(false);
        qtyEditTxt.setEnabled(false);
        priceEditTxt.setEnabled(false);

       buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id!=0){ // Suppression
                    try {
                        ProductConnectionRest productConnectionRest = new ProductConnectionRest();
                        JSONObject product = new JSONObject();
                        product.put("id", id);
                        productConnectionRest.setJsonObj(product);
                        productConnectionRest.execute("DELETE");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(EditBuyActivity.this, PayActivity.class);
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