package com.example.kevindrakonian.eticallv01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevindrakonian.eticallv01.Entidades.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLogin extends AppCompatActivity {



    //objetos
    private Button btnLogin;
    private EditText textmail,textpasw;
    private ProgressDialog progressDialog;
    private FirebaseDatabase database;
    static String perfil;
    //objeto fireBase
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inicializar objeto firebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //iniciar instancia de database
        database= FirebaseDatabase.getInstance();

        //Referencias de los views
        textmail = (EditText) findViewById(R.id.txt_Email);
        textpasw = (EditText) findViewById(R.id.txt_Password);

        btnLogin = (Button) findViewById(R.id.btn_inicio);

        progressDialog = new ProgressDialog(this);


        progressDialog.setMessage("Iniciando sesión");
        //asociar oyentes
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = textmail.getText().toString();
                if(isValidEmail(correo) && Validarcontraseña()){

                    String contraseña = textpasw.getText().toString();
                    //loguear usuario
                    mAuth.signInWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.show();
                                    if (task.isSuccessful()) {
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());

                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                progressDialog.dismiss();
                                                Usuarios usuario = dataSnapshot.getValue(Usuarios.class);
                                                Toast.makeText(ActivityLogin.this, "Este Usuario es: " + usuario.getPerfil(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                        //nextActivity();
                                    } else {
                                        Toast.makeText(ActivityLogin.this,"Los datos no coinciden con ninguna cuenta registrada",Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }

                                    // ...
                                }
                            });

                }else{
                    Toast.makeText(ActivityLogin.this,"Los datos estan mal ingresados",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void RegEst(View view){

        Intent regest = new Intent(this, ActivityRegistro.class);
        startActivity(regest);

    }

    public void RegDoc(View view){

        Intent regdoc = new Intent(this, ActivityRegistroDo.class);
        startActivity(regdoc);

    }


    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    public boolean Validarcontraseña(){
        String Contraseña;
        Contraseña = textpasw.getText().toString();
        if (Contraseña.length()>=8 && Contraseña.length()<=20){
            return true;
        }else return false;

    }


    /*protected void onResume(){
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            DatabaseReference reference = database.getReference("Estudiantes/"+currentUser.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UsuariosEstudiantes usuario = dataSnapshot.getValue(UsuariosEstudiantes.class);
                    Toast.makeText(ActivityLogin.this,"Este Usuario es: "+usuario.getPerfil(),Toast.LENGTH_LONG);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //Toast.makeText(this,"Usuario logeado",Toast.LENGTH_SHORT).show();
            //nextActivity();
        }
    }*/

    private void nextActivity(){
        startActivity(new Intent(ActivityLogin.this,ActivityDocente.class));
        finish();
    }

}
