package com.example.kevindrakonian.eticallv01.persistencia;

import android.support.annotation.NonNull;

import com.example.kevindrakonian.eticallv01.Entidades.Firebase.Usuarios;
import com.example.kevindrakonian.eticallv01.Entidades.Logica.LUsuario;
import com.example.kevindrakonian.eticallv01.Utilidades.Constantes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private static UsuarioDao usuarioDao;
    private FirebaseDatabase database;
    private DatabaseReference referenceUSUARIOS;

    public static UsuarioDao getInstancia(){
        if(usuarioDao==null) {usuarioDao= new UsuarioDao();}
        return usuarioDao;
    }
    public String getKeyUsuario(){
        return FirebaseAuth.getInstance().getUid();
    }

    public long fechaDeCreacionLong(){
        return FirebaseAuth.getInstance().getCurrentUser().getMetadata().getCreationTimestamp();

    }
    public void añadirFotoDePerfilAlosUsuariosSinFotoDePerfil(){
        referenceUSUARIOS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<LUsuario> LUsuarioLista = new ArrayList<>();
                for (DataSnapshot childDataSnapshot1: dataSnapshot.getChildren()){
                    Usuarios usuario = childDataSnapshot1.getValue(Usuarios.class);
                    LUsuario lUsuario = new LUsuario(usuario,childDataSnapshot1.getKey());
                    LUsuarioLista.add(lUsuario);
                }
                for(LUsuario lUsuario: LUsuarioLista){
                    referenceUSUARIOS.child(lUsuario.getKey()).child("UrlFotoPerfil").setValue(Constantes.URL_FOTO_DEFECTO_PROFESOR);
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private UsuarioDao(){
        database = FirebaseDatabase.getInstance();
        referenceUSUARIOS = database.getReference("Usuarios");
    }
}
