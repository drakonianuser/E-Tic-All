package com.example.kevindrakonian.eticallv01.Adatadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.kevindrakonian.eticallv01.Entidades.Logica.LMensaje;
import com.example.kevindrakonian.eticallv01.Holder.MensajeHolder;
import com.example.kevindrakonian.eticallv01.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MensajeAdapter extends RecyclerView.Adapter <MensajeHolder> {

    private List<LMensaje> listamensajes = new ArrayList<>();
    private Context c;

    public MensajeAdapter(Context c) {
        this.c = c;
    }

    public void addMensaje(LMensaje lMensaje){
     listamensajes.add(lMensaje);
     notifyItemInserted(listamensajes.size());
    }

    @Override
    public MensajeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes,parent,false);
        return new MensajeHolder(v);
    }

    @Override
    public void onBindViewHolder(MensajeHolder holder, int position) {
        LMensaje lmensaje = listamensajes.get(position);

        holder.getNombre().setText(lmensaje.getlUsuario().getUsuarios().getNombre());
        holder.getMensaje().setText(lmensaje.getMensajeEntity().getMensaje());

        if (lmensaje.getMensajeEntity().isEnviaFoto()){
            holder.getFotoMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(lmensaje.getMensajeEntity().getUrlFoto()).into(holder.getFotoMensaje());
        }else {
            holder.getFotoMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

        holder.getHora().setText(lmensaje.FechaEnvioMensaje());
    }

    @Override
    public int getItemCount() {
        return listamensajes.size();
    }
}
