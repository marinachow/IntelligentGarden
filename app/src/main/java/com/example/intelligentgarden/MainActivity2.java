package com.example.intelligentgarden;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class MainActivity2 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
        public  void logout(View view){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
}


