package com.example.tp3grupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrRegistrarse (View view){
        Intent i = new Intent(this, Registrarse.class);
        startActivity(i);
    }

    public void Login (View view){
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}