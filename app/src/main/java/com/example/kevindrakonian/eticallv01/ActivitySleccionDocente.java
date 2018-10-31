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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevindrakonian.eticallv01.Aprende.ActivityAcercaDe;
import com.example.kevindrakonian.eticallv01.Aprende.ActivityAprende;
import com.example.kevindrakonian.eticallv01.Aprende.ActivityCreditos;
import com.example.kevindrakonian.eticallv01.Correo.CorreoActivity;
import com.example.kevindrakonian.eticallv01.CrearCaso.ActivityCrearCaso;
import com.example.kevindrakonian.eticallv01.CrearCaso.ActivitySelecionarDocente;
import com.example.kevindrakonian.eticallv01.Entidades.Logica.LUsuario;
import com.example.kevindrakonian.eticallv01.LoginInicioRegistro.ActivityInicio;
import com.example.kevindrakonian.eticallv01.LoginInicioRegistro.ActivityInicioDocente;
import com.example.kevindrakonian.eticallv01.LoginInicioRegistro.ActivityLogin;
import com.example.kevindrakonian.eticallv01.filtro.ActivityFiltro;
import com.example.kevindrakonian.eticallv01.persistencia.UsuarioDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivitySleccionDocente extends AppCompatActivity {

    //objetos de la parte grafica
    private ImageView ivFotoDocente;
    private TextView tvNombreCompleto;
    private TextView tvDepartamento;
    private TextView tvUnidad;
    private Button btnCrear;
    private LUsuario Estudiante;
    private LUsuario Docente;

    //objetos de Firebase

    private DatabaseReference referenceDocente;

    //menu hamburguesa
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleccion_docente);

        //grafico
        ivFotoDocente = (ImageView)  findViewById(R.id.Foto_Docente_Selecionar_docente);
        tvNombreCompleto = (TextView) findViewById(R.id.txt_nombreDocente_Selecionar_docente);
        tvDepartamento = (TextView) findViewById(R.id.txt_Departamento_Selecionar_docente);
        tvUnidad = (TextView) findViewById(R.id.txt_Unidad_Selecionar_docente);
        btnCrear = (Button) findViewById(R.id.btn_Selecionar_Selecionar_docente);

        //base de datos

        referenceDocente = FirebaseDatabase.getInstance().getReference("Usuarios");

        final String keyEstudiante = UsuarioDao.getInstancia().getKeyUsuario();
        final String keydocente = getIntent().getStringExtra("keyDocente_selecionado");
        final String DocenteNombre = getIntent().getStringExtra("Docente_nombre");
        final String DocenteDepartamento = getIntent().getStringExtra("Docente_Departamento");
        final String DocenteUnidad = getIntent().getStringExtra("Docente_Unidad");
        final String DocenteFoto = getIntent().getStringExtra("Docente_imagen");

        tvNombreCompleto.setText(DocenteNombre);
        tvDepartamento.setText(DocenteDepartamento);
        tvUnidad.setText(DocenteUnidad);


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivitySleccionDocente.this , ActivityCrearCaso.class);
                i.putExtra("keyDocente_crear",keydocente );
                i.putExtra("keyEstudiante_crear", keyEstudiante);
                startActivity(i);
            }
        });

        //menu hamburguesa

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.selectDocente);
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
                    case R.id.nav_salir:
                        item.setChecked(true);
                        salir();
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

    public void correo(){

        Intent siguiente = new Intent(this,CorreoActivity.class);
        startActivity(siguiente);}


    public void salir(){
        FirebaseAuth.getInstance().signOut();
        Intent siguiente = new Intent(this,ActivityLogin.class);
        startActivity(siguiente);
    }


}
