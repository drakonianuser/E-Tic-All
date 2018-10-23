package com.example.kevindrakonian.eticallv01.filtro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kevindrakonian.eticallv01.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ActivityFiltro extends AppCompatActivity {

    private Button btnfiltro;

    DatabaseReference bd;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        btnfiltro = (Button) findViewById(R.id.filtro);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Usuarios");//salas de los chats
        storage = FirebaseStorage.getInstance();



        btnfiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query q=reference.orderByChild(getString(R.string.campo_Filtro)).equalTo("Documento");
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int contador=0;
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                            contador++;
                            Toast.makeText(ActivityFiltro.this, "he encontrado"+contador,Toast.LENGTH_LONG).show();
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
