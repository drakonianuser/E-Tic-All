package com.example.kevindrakonian.eticallv01.LoginInicioRegistro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.UsuariosDocentes;
import com.example.kevindrakonian.eticallv01.R;
import com.example.kevindrakonian.eticallv01.Utilidades.Constantes;
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
    private DatabaseReference reference;
    private boolean x = false;
    private boolean y = true;

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


            btnRegistro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityRegistroDo.this, "Entro al boton", Toast.LENGTH_SHORT).show();
                    final String nombre = etNombre.getText().toString();
                    final String apellidos = etApellidos.getText().toString();
                    final String unidad = etUnidad.getText().toString();
                    final String Departamento = etDepartamento.getText().toString();
                    final String Documento = etDocumento.getText().toString();
                    final String correo = etCorreo.getText().toString().trim();
                    final String contraseña = etContraseña.getText().toString();


                    if (isValidEmail(correo) && Validarcontraseña() && ValidarCampos(nombre,apellidos,unidad,Departamento,Documento)) {

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
                                            u.setUrlFotoPerfil(Constantes.URL_FOTO_DEFECTO_PROFESOR);
                                            //agregue estas 3 lineas de codigo MUENTES
                                            FirebaseUser currentUser = mAuth.getCurrentUser();
                                            DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                            reference.setValue(u);
                                            //caca
                                            //agrege esta linea
                                            nextActivityToLoginDo();
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(ActivityRegistroDo.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }else{

                    }
                }

            });
        }

        private boolean isValidEmail(CharSequence target) {
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()){
                return true;
            }else{
                Toast.makeText(ActivityRegistroDo.this, "Debe ingresar un correo valido", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ActivityRegistroDo.this, "la contraseña es minimo 6 y maximo 16 caracteres", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(ActivityRegistroDo.this, "las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        public boolean ValidarCampos(String nombre,String apellidos, String unidad, String Departamento, String Documento){
            if(!nombre.isEmpty() && !apellidos.isEmpty() && !unidad.isEmpty() && !Departamento.isEmpty() && !Documento.isEmpty()){
                return true;
            }else{
                Toast.makeText(ActivityRegistroDo.this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        private void nextActivityToLoginDo(){
            startActivity(new Intent(ActivityRegistroDo.this,ActivityLogin.class));
            finish();
        }








}
