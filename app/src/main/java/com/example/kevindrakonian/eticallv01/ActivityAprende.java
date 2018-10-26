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

public class ActivityAprende extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprende);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.aprende);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void bullyng(View view){

        Intent siguiente = new Intent(this,ActivityBullyng.class);
        startActivity(siguiente);

    }

    public void grooming(View view){

        Intent siguiente = new Intent(this,ActivityGrooming.class);
        startActivity(siguiente);

    }

    public void sexting(View view) {

        Intent siguiente = new Intent(this,ActivitySexting.class);
        startActivity(siguiente);

    }

    public void crime(View view){

        Intent siguiente = new Intent(this,ActivityCrime.class);
        startActivity(siguiente);

    }


    public void tips(View view){

        Intent siguiente = new Intent(this,ActivityTips.class);
        startActivity(siguiente);

    }

}
