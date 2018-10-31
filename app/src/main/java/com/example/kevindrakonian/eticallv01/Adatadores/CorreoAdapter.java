package com.example.kevindrakonian.eticallv01.Adatadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kevindrakonian.eticallv01.Entidades.Firebase.CasosEntity;
import com.example.kevindrakonian.eticallv01.Holder.CorreoHolder;
import com.example.kevindrakonian.eticallv01.R;

import java.util.ArrayList;

public class CorreoAdapter extends RecyclerView.Adapter <CorreoHolder> implements View.OnClickListener  {

    private ArrayList<CasosEntity> listacasos = new ArrayList<>();
    private View.OnClickListener listener;
    private Context c;

    public CorreoAdapter(Context c) {
        this.c = c;
    }

    public void addCaso(CasosEntity caso){
        listacasos.add(caso);
        notifyItemInserted(listacasos.size());
    }

    @NonNull
    @Override
    public CorreoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_casos,viewGroup,false);
        v.setOnClickListener(this);
        return new CorreoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CorreoHolder holder, int i) {
        holder.getEtTitulocaso().setText(listacasos.get(i).getTitulo());
        holder.getEtNombreDocentecaso().setText(listacasos.get(i).getKeyDocente());

    }

    @Override
    public int getItemCount() {
        return listacasos.size();
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if  (listener != null){
            listener.onClick(v);
        }

    }

    public ArrayList<CasosEntity> retunlista(){
        return listacasos;
    }
}
