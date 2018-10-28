package com.example.kevindrakonian.eticallv01;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityInicio extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Button salir;

    //menu haburguesa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.inicio);
        salir = findViewById(R.id.boton_salir);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        salir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                nextActivityToLoginIni();
                finish();
            }
        });
    }

    //menu hamburguesa
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //botones

    public void aprende(View view){

        Intent siguiente = new Intent(this,ActivityAprende.class);
        startActivity(siguiente);

    }

    private void nextActivityToLoginIni(){
        startActivity(new Intent(ActivityInicio.this,ActivityLogin.class));
        finish();
    }



}
