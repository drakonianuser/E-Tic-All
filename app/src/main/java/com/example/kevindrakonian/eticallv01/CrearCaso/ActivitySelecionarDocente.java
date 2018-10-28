package com.example.kevindrakonian.eticallv01.CrearCaso;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevindrakonian.eticallv01.Entidades.Firebase.FiltroDocenteEntity;
import com.example.kevindrakonian.eticallv01.Entidades.Logica.LUsuario;
import com.example.kevindrakonian.eticallv01.R;
import com.example.kevindrakonian.eticallv01.persistencia.UsuarioDao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ActivitySelecionarDocente extends AppCompatActivity {


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_docente);
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


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivitySelecionarDocente.this , ActivityCrearCaso.class);
                i.putExtra("keyDocente_crear",keydocente );
                i.putExtra("keyEstudiante_crear", keyEstudiante);
                startActivity(i);
            }
        });

    }
}
