package com.example.sg;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.sg.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SellActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_sell);

        ArrayList<Product> listData = getListData();
        Log.v("ARRAY", "" + listData.get(0));


        if(listData!=null) {
            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new CustomListAdapter(this, listData));
        }

        Button ButtonSell = (Button) findViewById(R.id.button_buy);
        ButtonSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellActivity.this, EditProductActivity.class);
                startActivity(intent);
            }
        });

        Button ButtonBack = (Button) findViewById(R.id.button_back);
        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Product> getListData(){
        try{
            ConnectionRest connectionRest = new ConnectionRest();
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();
            Log.v("LIST", listJsonObjs);
            if(listJsonObjs != null) {
                return connectionRest.parse(listJsonObjs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}