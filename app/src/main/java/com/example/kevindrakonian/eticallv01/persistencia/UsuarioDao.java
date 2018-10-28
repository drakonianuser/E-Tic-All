package com.example.kevindrakonian.eticallv01.persistencia;

import com.google.firebase.auth.FirebaseAuth;

public class UsuarioDao {

    public static String getKeyUsuario(){
        return FirebaseAuth.getInstance().getUid();
    }
}
