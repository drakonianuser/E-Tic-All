package com.example.kevindrakonian.eticallv01.Aprende;

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

import com.example.kevindrakonian.eticallv01.Correo.CorreoActivity;
import com.example.kevindrakonian.eticallv01.filtro.ActivityFiltro;
import com.example.kevindrakonian.eticallv01.LoginInicioRegistro.ActivityInicio;
import com.example.kevindrakonian.eticallv01.ActivityPerfilEstudiante;
import com.example.kevindrakonian.eticallv01.LoginInicioRegistro.ActivityLogin;
import com.example.kevindrakonian.eticallv01.R;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityAprende extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprende);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.aprende);
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

                    case R.id.nav_crear:
                        item.setChecked(true);
                        crearCaso();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_correo:
                        item.setChecked(true);
                        correo();
                        drawerLayout.closeDrawers();
                        return true;


                    case R.id.nav_salir:
                        item.setChecked(true);
                        salir();
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

    //botones

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


    public void crearCaso(){

        Intent siguiente = new Intent(this,ActivityFiltro.class);
        startActivity(siguiente);

    }

    public void correo() {

        Intent siguiente = new Intent(this, CorreoActivity.class);
        startActivity(siguiente);
    }
    public void salir(){
        FirebaseAuth.getInstance().signOut();
        Intent siguiente = new Intent(this,ActivityLogin.class);
        startActivity(siguiente);
    }

}
