package com.example.kevindrakonian.eticallv01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kevindrakonian.eticallv01.Entidades.UsuariosEstudiantes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegistro extends AppCompatActivity {

    private EditText etNombre,etApellidos,etCorreo,etContraseña,etConfir;
    private Spinner Spgrado;
    private Button btnRegistro;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText) findViewById(R.id.txt_NameRegistroEs);
        etApellidos = (EditText) findViewById(R.id.txt_ApellidosRegistroEs);
        etCorreo = (EditText) findViewById(R.id.txt_correoRegistroEs);
        etContraseña = (EditText) findViewById(R.id.passwordRegistroEs);
        etConfir = (EditText) findViewById(R.id.passwordConfirRegistroEs);
        Spgrado = (Spinner) findViewById(R.id.Sp_gradoRegistroEs);
        btnRegistro= (Button) findViewById(R.id.btnRegistrarEs);



        String[] Grados = {"6","7","8","9","10","11"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Grados);
        Spgrado.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityRegistro.this, "Boton presionado", Toast.LENGTH_SHORT).show();
                final String nombre = etNombre.getText().toString();
                final String apellidos = etApellidos.getText().toString();
                final String correo = etCorreo.getText().toString().trim();
                if (isValidEmail(correo) && Validarcontraseña() && Validarnombre(nombre)) {
                    String contraseña = etContraseña.getText().toString();

                    mAuth.createUserWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(ActivityRegistro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(ActivityRegistro.this, "Se a registrado corretacmente", Toast.LENGTH_SHORT).show();
                                        UsuariosEstudiantes usuarioEstudiante = new UsuariosEstudiantes();
                                        usuarioEstudiante.setCorreo(correo);
                                        usuarioEstudiante.setNombre(nombre);
                                        usuarioEstudiante.setApellidos(apellidos);
                                        usuarioEstudiante.setGrado(SelecGrado());
                                        //agregue estas 3 lineas de codigo
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                        reference.setValue(usuarioEstudiante);
                                        //caca
                                        //agrege esta linea
                                        nextActivityToLoginEs();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(ActivityRegistro.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(ActivityRegistro.this, "fallo", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    //MUENTES aca segun el video habia que borrar esto public final static y ponerlo privado
    private boolean isValidEmail(CharSequence target) {
        Toast.makeText(ActivityRegistro.this, String.valueOf(!TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()), Toast.LENGTH_SHORT).show();
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean Validarcontraseña(){
        String Contraseña, confirmacion;
        Contraseña = etContraseña.getText().toString();
        confirmacion=etConfir.getText().toString();
        if (Contraseña.equals(confirmacion)){
            if (Contraseña.length()>=8 && Contraseña.length()<=20){
                return true;
            }else{
                Toast.makeText(ActivityRegistro.this, "contraseña larga", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(ActivityRegistro.this, "las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    public boolean Validarnombre(String nombre){
        Toast.makeText(ActivityRegistro.this, String.valueOf(!nombre.isEmpty()), Toast.LENGTH_SHORT).show();
        return !nombre.isEmpty();
    }

    public String SelecGrado(){
        return Spgrado.getSelectedItem().toString();
    }

    private void nextActivityToLoginEs(){
        startActivity(new Intent(ActivityRegistro.this,ActivityLogin.class));
        finish();
    }

}
