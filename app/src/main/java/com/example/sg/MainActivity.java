package com.example.sg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// import com.example.sg.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // binding = ActivityMainBinding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());

        setContentView(R.layout.activity_main);

        Button buttonsell = findViewById(R.id.button_sell);
        buttonsell.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, SellActivity.class);
                startActivity(intent);
            }
        });

        Button buttonbuy = findViewById(R.id.button_buy);
        buttonbuy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });
    }
}