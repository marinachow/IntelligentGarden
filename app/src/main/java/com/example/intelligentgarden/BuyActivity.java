package com.example.intelligentgarden;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        ArrayList<Product> listData = getListData();
        Log.v("ARRAY", "" + listData.get(0));


        if(listData!=null) {
            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new CustomListAdapter(this, listData));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = listView.getItemAtPosition(position);
                    Product upload = (Product) o;
                    Intent intent = new Intent(BuyActivity.this, EditBuyActivity.class);
                    intent.putExtra("id", upload.getId());
                    intent.putExtra("name", upload.getName());
                    intent.putExtra("type", upload.getType());
                    intent.putExtra("qty", upload.getQty());
                    intent.putExtra("price", upload.getPrice());
                    startActivity(intent);
                }
            });
        }

        Button ButtonBack = (Button) findViewById(R.id.button_back);
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Product> getListData(){
        try{
            ProductConnectionRest productConnectionRest = new ProductConnectionRest();
            productConnectionRest.execute("GET");
            String listJsonObjs = productConnectionRest.get();
            Log.v("LIST", listJsonObjs);
            if(listJsonObjs != null) {
                return productConnectionRest.parse(listJsonObjs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}