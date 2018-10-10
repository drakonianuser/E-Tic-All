package com.example.kevindrakonian.eticallv01.Chats;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kevindrakonian.eticallv01.Entidades.MensajeEntity;
import com.example.kevindrakonian.eticallv01.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMensajes extends RecyclerView.Adapter <HolderMensaje> {

    private List<MensajeEntity> listamensajes = new ArrayList<>();
    private Context c;

    public AdapterMensajes(Context c) {
        this.c = c;
    }

    public void addMensaje(MensajeEntity me){
     listamensajes.add(me);
     notifyItemInserted(listamensajes.size());
    }

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_mensajes,parent,false);
        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        holder.getNombre().setText(listamensajes.get(position).getNombre());
        holder.getMensaje().setText(listamensajes.get(position).getMensaje());
        holder.getHora().setText(listamensajes.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return listamensajes.size();
    }
}
