package com.example.kevindrakonian.eticallv01.LoginInicioRegistro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.UsuariosEstudiantes;
import com.example.kevindrakonian.eticallv01.R;
import com.example.kevindrakonian.eticallv01.Utilidades.Constantes;
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
    private DatabaseReference reference;
    private static int x =0;
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
                if (isValidEmail(correo) && Validarcontraseña() && ValidarCampos(nombre,apellidos)) {
                    String contraseña = etContraseña.getText().toString();

                    mAuth.createUserWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(ActivityRegistro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(ActivityRegistro.this, "Se a registrado corretacmente", Toast.LENGTH_SHORT).show();

                                        UsuariosEstudiantes u = new UsuariosEstudiantes();
                                        u.setCorreo(correo);
                                        u.setNombre(nombre);
                                        u.setApellidos(apellidos);
                                        u.setGrado(SelecGrado());
                                        u.setUrlFotoPerfil(Constantes.URL_FOTO_DEFECTO_ESTUDIANTE);
                                        //agregue estas 3 lineas de codigo
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                        reference.setValue(u);
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
                }
            }

        });
    }


    //MUENTES aca segun el video habia que borrar esto public final static y ponerlo privado
    private boolean isValidEmail(CharSequence target) {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()){
            return true;
        }else{
            Toast.makeText(ActivityRegistro.this, "Debe ingresar un correo valido", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean Validarcontraseña(){
        String Contraseña, confirmacion;
        Contraseña = etContraseña.getText().toString();
        confirmacion=etConfir.getText().toString();
        if (Contraseña.equals(confirmacion)){
            if (Contraseña.length()>=6 && Contraseña.length()<=16){
                return true;
            }else{
                Toast.makeText(ActivityRegistro.this, "la contraseña es minimo 6 y maximo 16 caracteres", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(ActivityRegistro.this, "las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public boolean ValidarCampos(String nombre, String apellidos){
        if(!nombre.isEmpty() && !apellidos.isEmpty()){
            return true;
        }else{
            Toast.makeText(ActivityRegistro.this, "Debe rellenar los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public String SelecGrado(){
        return Spgrado.getSelectedItem().toString();
    }

    private void nextActivityToLoginEs(){
        startActivity(new Intent(ActivityRegistro.this,ActivityLogin.class));
        finish();
    }

}
