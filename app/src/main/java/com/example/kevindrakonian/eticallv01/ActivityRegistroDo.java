package com.example.kevindrakonian.eticallv01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevindrakonian.eticallv01.Entidades.UsuariosDocentes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegistroDo extends AppCompatActivity {

    private EditText etNombre,etApellidos,etCorreo,etContraseña,etConfir,etUnidad,etDepartamento,etDocumento;
    private Button btnRegistro;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_do);

            etNombre = (EditText) findViewById(R.id.txt_NameRegistroDo);
            etApellidos = (EditText) findViewById(R.id.txt_ApellidosRegistroDo);
            etCorreo = (EditText) findViewById(R.id.txt_correoRegistroDo);
            etContraseña = (EditText) findViewById(R.id.passwordRegistroDo);
            etConfir = (EditText) findViewById(R.id.passwordConfirRegistroDo);
            etUnidad = (EditText) findViewById(R.id.txt_UnidadRegistroDo);
            etDepartamento = (EditText) findViewById(R.id.txt_DepartamentoRegistroDo);
            etDocumento = (EditText) findViewById(R.id.txt_DocumentoRegistroDo);
            btnRegistro= (Button) findViewById(R.id.btnRegistrarDo);
            mAuth = FirebaseAuth.getInstance();
            database= FirebaseDatabase.getInstance();
            final String nombre = etNombre.getText().toString();
            final String apellidos = etApellidos.getText().toString();
            final String unidad = etUnidad.getText().toString();
            final String Departamento = etDepartamento.getText().toString();
            final String Documento = etDocumento.getText().toString();

            btnRegistro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityRegistroDo.this, "Entro al boton", Toast.LENGTH_SHORT).show();
                    final String correo = etCorreo.getText().toString().trim();
                    if (isValidEmail(correo) && Validarcontraseña() && Validarnombre(nombre)) {
                        String contraseña = etContraseña.getText().toString();

                        mAuth.createUserWithEmailAndPassword(correo, contraseña)
                                .addOnCompleteListener(ActivityRegistroDo.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Toast.makeText(ActivityRegistroDo.this, "Se a registrado corretacmente", Toast.LENGTH_SHORT).show();
                                            UsuariosDocentes usuarioDocente = new UsuariosDocentes();
                                            usuarioDocente.setCorreo(correo);
                                            usuarioDocente.setNombre(nombre);
                                            usuarioDocente.setApellidos(apellidos);
                                            usuarioDocente.setUnidad(unidad);
                                            usuarioDocente.setDepartamento(Departamento);
                                            usuarioDocente.setDocumento(Documento);
                                            FirebaseUser currentUser = mAuth.getCurrentUser();
                                            DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                            reference.setValue(usuarioDocente);
                                            startActivity(new Intent(ActivityRegistroDo.this,ActivityLogin.class));
                                            Toast.makeText(ActivityRegistroDo.this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                                            nextActivityToLoginDo();
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(ActivityRegistroDo.this, "hola al registrarse", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(ActivityRegistroDo.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }

        private boolean isValidEmail(CharSequence target) {
            Toast.makeText(ActivityRegistroDo.this, String.valueOf(!TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()), Toast.LENGTH_SHORT).show();
            return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }

        public boolean Validarcontraseña(){
            String Contraseña, confirmacion;
            Contraseña = etContraseña.getText().toString();
            confirmacion=etConfir.getText().toString();
            if (Contraseña.equals(confirmacion)){
                if (Contraseña.length()>=6 && Contraseña.length()<=16){

                    return true;
                }else{
                    Toast.makeText(ActivityRegistroDo.this, "contraseña larga", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(ActivityRegistroDo.this, "contraseñas no iguales", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        public boolean Validarnombre(String nombre){
            Toast.makeText(ActivityRegistroDo.this, String.valueOf(!nombre.isEmpty()), Toast.LENGTH_SHORT).show();
            return !nombre.isEmpty();
        }
        private void nextActivityToLoginDo(){
            startActivity(new Intent(ActivityRegistroDo.this,ActivityLogin.class));
            finish();
        }


    }

