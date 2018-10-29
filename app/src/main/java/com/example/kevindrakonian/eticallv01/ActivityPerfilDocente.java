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
import android.view.View;
import android.widget.TextView;

import com.example.kevindrakonian.eticallv01.Aprende.ActivityAcercaDe;
import com.example.kevindrakonian.eticallv01.Aprende.ActivityAprende;
import com.example.kevindrakonian.eticallv01.Aprende.ActivityCreditos;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.Usuarios;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.UsuariosDocentes;
import com.example.kevindrakonian.eticallv01.LoginInicioRegistro.ActivityInicioDocente;
import com.example.kevindrakonian.eticallv01.LoginInicioRegistro.ActivityLogin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.kevindrakonian.eticallv01.Correo.CorreoActivity;

public class ActivityPerfilDocente extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView textViewNombre;
    private TextView textViewUnidad;
    private TextView textViewDepartamento;
    private String campo;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_docente);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.perfilDocente);
        navigationView = findViewById(R.id.navegationView);
        textViewNombre = findViewById(R.id.perfilDocenteNombre);
        textViewUnidad = findViewById(R.id.profesorPerfilUnidad);
        textViewDepartamento = findViewById(R.id.profesorPerfilUnidad);

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
                    case R.id.nav_salir:
                        item.setChecked(true);
                        salir();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_correo:
                        item.setChecked(true);
                        correo();
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

    public void editar(View view){

        Intent siguiente = new Intent(this,ActivityPerfilEditDocente.class);
        startActivity(siguiente);

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
    public void salir(){
        FirebaseAuth.getInstance().signOut();
        Intent siguiente = new Intent(this,ActivityLogin.class);
        startActivity(siguiente);
    }
    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UsuariosDocentes docente = dataSnapshot.getValue(UsuariosDocentes.class);
                campo = docente.getNombre();
                textViewNombre.setText(campo);
                campo = docente.getUnidad();
                textViewUnidad.setText(campo);
                campo = docente.getDepartamento();
                textViewDepartamento.setText(campo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void correo(){

        Intent siguiente = new Intent(this,CorreoActivity.class);
        startActivity(siguiente);

    }


}
