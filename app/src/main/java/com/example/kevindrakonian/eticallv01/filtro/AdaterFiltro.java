package com.example.kevindrakonian.eticallv01.filtro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.kevindrakonian.eticallv01.Chats.HolderMensaje;
import com.example.kevindrakonian.eticallv01.Entidades.FiltroDocenteEntity;
import com.example.kevindrakonian.eticallv01.R;

import java.util.ArrayList;
import java.util.List;

public class AdaterFiltro  extends RecyclerView.Adapter <HolderFitro> implements View.OnClickListener {

    private List<FiltroDocenteEntity> listaDocente = new ArrayList<>();
    private View.OnClickListener listener;
    private Context c;

    public AdaterFiltro (Context c){
        this.c = c;
    }

    public void addDocente(FiltroDocenteEntity docente){
        listaDocente.add(docente);
        notifyItemInserted(listaDocente.size());
    }

    public void ClearList(){
        listaDocente.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HolderFitro onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v  = LayoutInflater.from(c).inflate(R.layout.card_view_filtro,viewGroup,false);
        v.setOnClickListener(this);
        return new HolderFitro(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFitro holderFitro, int i) {
        holderFitro.getNombreFiltro().setText(listaDocente.get(i).getNombre());
        holderFitro.getDepartementoFiltro().setText(listaDocente.get(i).getDepartamento());
        holderFitro.getUnidadFiltro().setText(listaDocente.get(i).getUnidad());
        Glide.with(c).load(listaDocente.get(i).getUrlImagen()).into(holderFitro.getImagenFiltro());
    }

    @Override
    public int getItemCount() {
        return listaDocente.size();
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
}
