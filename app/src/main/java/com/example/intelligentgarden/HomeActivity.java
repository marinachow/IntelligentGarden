package com.example.intelligentgarden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            Button gardenButton = findViewById(R.id.button_garden);
            Button sellButton = findViewById(R.id.button_sell);
            Button buyButton = findViewById(R.id.button_buy);
            Button logoutButton = findViewById(R.id.button_logout);
            gardenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent( HomeActivity.this, DisplayGardenActivity.class);
                    startActivity(intent);
                }
            });

            sellButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, SellActivity.class);
                    startActivity(intent);
                }
            });

            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, BuyActivity.class);
                    startActivity(intent);
                }
            });

            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }
            });
        }
}


