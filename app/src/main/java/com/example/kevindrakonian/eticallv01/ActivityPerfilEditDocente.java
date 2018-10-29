package com.example.kevindrakonian.eticallv01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.kevindrakonian.eticallv01.Aprende.ActivityAcercaDe;
import com.example.kevindrakonian.eticallv01.Aprende.ActivityAprende;
import com.example.kevindrakonian.eticallv01.Aprende.ActivityCreditos;
import com.example.kevindrakonian.eticallv01.LoginInicioRegistro.ActivityInicioDocente;

public class ActivityPerfilEditDocente extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_edit_docente);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.editDocente);
        navigationView = findViewById(R.id.navegationView);

        //acciones del menu amburguesa

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_inicio:
                        item.setChecked(true);
                        inicio();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_perfil:
                        item.setChecked(true);
                        prefilDocentes();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_aprende:
                        item.setChecked(true);
                        aprende();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_acerca_de:
                        item.setChecked(true);
                        acercaDe();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_creditos:
                        item.setChecked(true);
                        creditos();
                        drawerLayout.closeDrawers();
                        return true;

                }

                return false;
            }
        });

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
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

    //metodos para el menu hamburguesa

    public void inicio(){

        Intent siguiente = new Intent(this,ActivityInicioDocente.class);
        startActivity(siguiente);

    }

    public void aprende(){

        Intent siguiente = new Intent(this,ActivityAprende.class);
        startActivity(siguiente);

    }

    public void prefilDocentes(){

        Intent siguiente = new Intent(this,ActivityPerfilDocente.class);
        startActivity(siguiente);

    }

    public void acercaDe(){

        Intent siguiente = new Intent(this,ActivityAcercaDe.class);
        startActivity(siguiente);

    }

    public void creditos(){

        Intent siguiente = new Intent(this,ActivityCreditos.class);
        startActivity(siguiente);

    }
}
