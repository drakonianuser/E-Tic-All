package com.example.kevindrakonian.eticallv01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityEnsayo extends AppCompatActivity{
    private Button Btnsalir;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensayo);

        Btnsalir = (Button) findViewById(R.id.salir);
        Btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ActivityEnsayo.this,ActivityLogin.class));
                finish();
            }
        });
    }
}
