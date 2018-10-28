package com.example.kevindrakonian.eticallv01.filtro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevindrakonian.eticallv01.Adatadores.AdaterFiltro;
import com.example.kevindrakonian.eticallv01.CrearCaso.ActivitySelecionarDocente;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.FiltroDocenteEntity;
import com.example.kevindrakonian.eticallv01.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ActivityFiltro extends AppCompatActivity {
    private List<FiltroDocenteEntity> listaDocente = new ArrayList<>();
    private Button btnfiltro;
    private RecyclerView rvDocentes;
    private EditText tvTexto;

    DatabaseReference bd;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private AdaterFiltro adaterFiltro;
    private AdaterFiltro hola;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        btnfiltro = (Button) findViewById(R.id.filtro);
        rvDocentes = (RecyclerView) findViewById(R.id.listaFiltro);
        tvTexto = (EditText) findViewById(R.id.TextoFiltro);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Usuarios");//modulo de Usuario
        storage = FirebaseStorage.getInstance();

        adaterFiltro = new AdaterFiltro(this);

        adaterFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaDocente= adaterFiltro.returnLista();
                Intent i = new Intent(v.getContext(), ActivitySelecionarDocente.class);
                i.putExtra("keyDocente_selecionado",listaDocente.get(rvDocentes.getChildAdapterPosition(v)).getKey());
                startActivity(i);
            }
        });
        LinearLayoutManager l = new LinearLayoutManager(this);

        rvDocentes.setLayoutManager(l);
        rvDocentes.setAdapter(adaterFiltro);



        btnfiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query q=reference.orderByChild(getString(R.string.campo_Filtro)).equalTo(tvTexto.getText().toString());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        adaterFiltro.ClearList();
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                            FiltroDocenteEntity filtro = dataSnapshot1.getValue(FiltroDocenteEntity.class);
                            filtro.setKey(dataSnapshot1.getKey());
                            Toast.makeText(ActivityFiltro.this, "he encontrado",Toast.LENGTH_SHORT).show();
                            adaterFiltro.addDocente(filtro);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
