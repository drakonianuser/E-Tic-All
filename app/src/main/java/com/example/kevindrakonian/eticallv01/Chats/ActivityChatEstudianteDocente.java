package com.example.kevindrakonian.eticallv01.Chats;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevindrakonian.eticallv01.Adatadores.AdapterMensajes;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.MensajeEnviarEntity;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.MensajeRecibirEntity;
import com.example.kevindrakonian.eticallv01.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ActivityChatEstudianteDocente extends AppCompatActivity {

    // creando los objetos
    private ImageView cifoto;
    private TextView tvnombre;
    private RecyclerView rvMensajes;
    private EditText etMensaje;
    private Button btnEnviar;
    private ImageButton enviar_imagen;

    private static final int PHOTO_SEND = 1;

    private AdapterMensajes adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_estudiante_docente);

        //trallendo desde la vista
        cifoto = (ImageView) findViewById(R.id.Foto_Docente);
        tvnombre = (TextView) findViewById(R.id.txt_nombreDocente);
        rvMensajes = (RecyclerView) findViewById(R.id.rvMensajes);
        etMensaje = (EditText) findViewById(R.id.mensaje);
        btnEnviar =  (Button) findViewById(R.id.enviar);
        enviar_imagen = (ImageButton) findViewById(R.id.enviar_imagen);

        final String  Salachat = getIntent().getStringExtra("SalaDeChat");

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(Salachat);//salas de los chats
        storage = FirebaseStorage.getInstance();


        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              reference.push().setValue(new MensajeEnviarEntity(etMensaje.getText().toString(),tvnombre.getText().toString(),"","1",1,ServerValue.TIMESTAMP));
              etMensaje.setText("");
            }
        });

        enviar_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Seleciona una foto"),PHOTO_SEND);
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MensajeRecibirEntity m = dataSnapshot.getValue(MensajeRecibirEntity.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setScrollbar(){
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final String  Salachat = getIntent().getStringExtra("SalaDeChat");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri u = data.getData();
            storageRef = storage.getReference("imagenes_"+Salachat);//Carpeta  de la imagen
            final StorageReference FOTO_REF = storageRef.child(u.getLastPathSegment());
            FOTO_REF.putFile(u).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return FOTO_REF.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri uri = task.getResult();
                        MensajeEnviarEntity m = new MensajeEnviarEntity(tvnombre.getText().toString()+" te envio una foto",uri.toString(),tvnombre.getText().toString(),"","2",2,ServerValue.TIMESTAMP);
                        reference.push().setValue(m);
                    }
                }
            });

        }

    }
}




