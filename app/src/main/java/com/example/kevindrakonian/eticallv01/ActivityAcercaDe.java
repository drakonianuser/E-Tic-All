package com.example.kevindrakonian.eticallv01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityAcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }

    public void tips(View view){

        Intent siguiente = new Intent(this,ActivityTips.class);
        startActivity(siguiente);

    }
}
