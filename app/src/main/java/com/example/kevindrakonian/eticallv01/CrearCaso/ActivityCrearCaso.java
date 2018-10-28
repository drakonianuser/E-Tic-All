package com.example.kevindrakonian.eticallv01.CrearCaso;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevindrakonian.eticallv01.Entidades.Firebase.FiltroDocenteEntity;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.Usuarios;
import com.example.kevindrakonian.eticallv01.Entidades.Logica.LUsuario;
import com.example.kevindrakonian.eticallv01.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ActivityCrearCaso extends AppCompatActivity {


    //objetos de la parte grafica
    private ImageView ivFotoDocente;
    private TextView tvNombreCompleto;
    private TextView tvDepartamento;
    private TextView tvUnidad;
    private Button btnCrear;
    private LUsuario Estudiante;
    private LUsuario Docente;

    //objetos de Firebase
    private DatabaseReference referenceCasos;
    private DatabaseReference referenceDocente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_caso);
        //grafico
        ivFotoDocente = (ImageView)  findViewById(R.id.Foto_Docente_Crear_caso);
        tvNombreCompleto = (TextView) findViewById(R.id.txt_nombreDocente_Crear_Caso);
        tvDepartamento = (TextView) findViewById(R.id.txt_Departamento_Crear_Caso);
        tvUnidad = (TextView) findViewById(R.id.txt_Unidad_Crear_Caso);
        btnCrear = (Button) findViewById(R.id.btn_Crear_Crear_caso);

        //base de datos
        referenceCasos = FirebaseDatabase.getInstance().getReference("Casos");
        referenceDocente = FirebaseDatabase.getInstance().getReference("Usuarios");

        String keydocente = getIntent().getStringExtra("keyDocente");
        Query q = referenceDocente.orderByKey().equalTo(keydocente);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    FiltroDocenteEntity Docente = dataSnapshot.getValue(FiltroDocenteEntity.class);
                    tvNombreCompleto.setText(Docente.getNombre());
                    tvDepartamento.setText(Docente.getDepartamento());
                    tvUnidad.setText(Docente.getUnidad());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
