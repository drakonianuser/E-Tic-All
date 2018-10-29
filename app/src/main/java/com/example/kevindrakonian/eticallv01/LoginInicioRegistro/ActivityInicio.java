package com.example.kevindrakonian.eticallv01.LoginInicioRegistro;

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
import android.view.View;
import android.widget.Button;

import com.example.kevindrakonian.eticallv01.Aprende.ActivityAcercaDe;
import com.example.kevindrakonian.eticallv01.Aprende.ActivityCreditos;
import com.example.kevindrakonian.eticallv01.ActivityPerfilEstudiante;
import com.example.kevindrakonian.eticallv01.Aprende.ActivityAprende;
import com.example.kevindrakonian.eticallv01.R;
import com.google.firebase.auth.FirebaseAuth;
import com.example.kevindrakonian.eticallv01.filtro.ActivityFiltro;


public class ActivityInicio extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private Button salir;

    private NavigationView navigationView;


    //menu haburguesa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.inicio);
        salir = findViewById(R.id.boton_salir);

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
                        prefilEstudiantes();
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

    public void prefilEstudiantes(View view){

        Intent siguiente = new Intent(this,ActivityPerfilEstudiante.class);
        startActivity(siguiente);

    }


    private void nextActivityToLoginIni(){
        startActivity(new Intent(ActivityInicio.this,ActivityLogin.class));
        finish();
    }


    public void Crearcaso(View view){
        Intent siguiente = new Intent(this,ActivityFiltro.class);
        startActivity(siguiente);
    }
    public void acercaDe(View view){

        Intent siguiente = new Intent(this,ActivityAcercaDe.class);
        startActivity(siguiente);

    }

    //metodos para el menu hamburguesa

    public void inicio(){

        Intent siguiente = new Intent(this,ActivityInicio.class);
        startActivity(siguiente);

    }

    public void aprende(){

        Intent siguiente = new Intent(this,ActivityAprende.class);
        startActivity(siguiente);

    }

    public void prefilEstudiantes(){

        Intent siguiente = new Intent(this,ActivityPerfilEstudiante.class);
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
