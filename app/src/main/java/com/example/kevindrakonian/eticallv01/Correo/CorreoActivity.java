package com.example.kevindrakonian.eticallv01.Correo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.kevindrakonian.eticallv01.Adatadores.CorreoAdapter;
import com.example.kevindrakonian.eticallv01.Chats.ActivityChatEstudianteDocente;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.CasosEntity;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.FiltroDocenteEntity;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.Usuarios;
import com.example.kevindrakonian.eticallv01.R;
import com.example.kevindrakonian.eticallv01.filtro.ActivityFiltro;
import com.example.kevindrakonian.eticallv01.persistencia.UsuarioDao;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CorreoActivity extends AppCompatActivity {

    public RecyclerView rvlistaCasos;
    private DatabaseReference reference;
    private CorreoAdapter adapter;
    private ArrayList<CasosEntity> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);

        rvlistaCasos = (RecyclerView) findViewById(R.id.Correo_filtro);

        reference = FirebaseDatabase.getInstance().getReference("Casos");//modulo de Usuario

        adapter = new CorreoAdapter(this);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista = adapter.retunlista();
                Toast.makeText(CorreoActivity.this, "Selecionado", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), ActivityChatEstudianteDocente.class);
                i.putExtra("SalaDeChat",lista.get(rvlistaCasos.getChildAdapterPosition(v)).getSalaChat());
                startActivity(i);
            }
        });

        LinearLayoutManager l = new LinearLayoutManager(this);

        rvlistaCasos.setLayoutManager(l);
        rvlistaCasos.setAdapter(adapter);

        String key = UsuarioDao.getInstancia().getKeyUsuario();


        Query q = reference.orderByChild(getString(R.string.campo_Correo_Estudiante)).equalTo(key);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    CasosEntity caso = dataSnapshot1.getValue(CasosEntity.class);
                    adapter.addCaso(caso);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
