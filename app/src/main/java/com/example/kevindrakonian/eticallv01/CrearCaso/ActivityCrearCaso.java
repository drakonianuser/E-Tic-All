package com.example.kevindrakonian.eticallv01.CrearCaso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevindrakonian.eticallv01.Chats.ActivityChatEstudianteDocente;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.CasosEntity;
import com.example.kevindrakonian.eticallv01.Entidades.Firebase.MensajeEntity;
import com.example.kevindrakonian.eticallv01.R;
import com.example.kevindrakonian.eticallv01.persistencia.UsuarioDao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityCrearCaso extends AppCompatActivity {

    //objetos de lo Grafico
    private EditText etTitulo;
    private EditText etDescricion;
    private Button btnCrear;

    //objetos de firebase
    private DatabaseReference referenceCasos;
    private DatabaseReference referenceChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_caso);
        //conexion con lo grafico

        etTitulo= (EditText) findViewById(R.id.txt_titulo_crear_caso);
        etDescricion= (EditText) findViewById(R.id.txt_descripcion_Crear_caso);
        btnCrear = (Button) findViewById(R.id.btn_crear_Crear_caso);

        referenceCasos = FirebaseDatabase.getInstance().getReference("Casos");

        final String keyDocente = getIntent().getStringExtra("keyDocente_crear");
        final String keyEstudiante = getIntent().getStringExtra("keyEstudiante_crear");


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = etTitulo.getText().toString();
                String Descripcion = etDescricion.getText().toString();
                if (!titulo.isEmpty() && !Descripcion.isEmpty()){
                    CasosEntity caso = new CasosEntity();
                    caso.setDescripcion(Descripcion);
                    caso.setKeyDocente(keyDocente);
                    caso.setKeyEsutudinate(keyEstudiante);
                    caso.setTitulo(titulo);
                    caso.setSalaChat(""+keyDocente+"/"+keyEstudiante);

                    //envio y Creasion
                    referenceCasos.push().setValue(caso);

                    //enviar a la sala

                Intent i = new Intent(ActivityCrearCaso.this , ActivityChatEstudianteDocente.class);
                i.putExtra("SalaDeChat",caso.getSalaChat());
                startActivity(i);

                }else {
                    Toast.makeText(ActivityCrearCaso.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
