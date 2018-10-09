package com.example.kevindrakonian.eticallv01.Chats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kevindrakonian.eticallv01.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityChatEstudianteDocente extends AppCompatActivity {

    // creando los objetos
    private CircleImageView cifoto;
    private TextView tvnombre;
    private RecyclerView rvMensajes;
    private EditText etMensaje;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_estudiante_docente);

        //trallendo desde la vista
        cifoto = (CircleImageView) findViewById(R.id.Foto_Docente);
        tvnombre = (TextView) findViewById(R.id.txt_nombreDocente);
        rvMensajes = (RecyclerView) findViewById(R.id.rvMensajes);
        etMensaje = (EditText) findViewById(R.id.mensaje);
        btnEnviar =  (Button) findViewById(R.id.enviar);

    }

}
