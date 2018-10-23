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

import com.example.kevindrakonian.eticallv01.Entidades.UsuariosDocentes;
import com.example.kevindrakonian.eticallv01.Entidades.UsuariosEstudiantes;
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

    //private DatabaseReference referenciaUsuarios; MUENTES esto ya no se usa


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_do);

            etNombre = (EditText) findViewById(R.id.txt_Name);
            etApellidos = (EditText) findViewById(R.id.txt_Apellidos);
            etCorreo = (EditText) findViewById(R.id.txt_correo);
            etContraseña = (EditText) findViewById(R.id.pw);
            etConfir = (EditText) findViewById(R.id.txt_confir);
            etUnidad = (EditText) findViewById(R.id.txt_Unidad);
            etDepartamento = (EditText) findViewById(R.id.txt_Departamento);
            etDocumento = (EditText) findViewById(R.id.txt_Documento);
            btnRegistro= (Button) findViewById(R.id.btnRegistrar);
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
                                            UsuariosDocentes u = new UsuariosDocentes();
                                            u.setCorreo(correo);
                                            u.setNombre(nombre);
                                            u.setApellidos(apellidos);
                                            u.setUnidad(unidad);
                                            u.setDepartamento(Departamento);
                                            u.setDocumento(Documento);
                                            //agregue estas 3 lineas de codigo MUENTES
                                            FirebaseUser currentUser = mAuth.getCurrentUser();
                                            DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                            reference.setValue(u);
                                            //caca
                                            //agregue esta linea MUENTES
                                            startActivity(new Intent(ActivityRegistroDo.this,ActivityLogin.class));
                                            Toast.makeText(ActivityRegistroDo.this, "SE ha registrado correctamente", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(ActivityRegistroDo.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }

            });
        }

        public final static boolean isValidEmail(CharSequence target) {
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
                    return false;
                }
            }else {
                return false;
            }

        }
        public boolean Validarnombre(String nombre){

            return !nombre.isEmpty();
        }


    }

