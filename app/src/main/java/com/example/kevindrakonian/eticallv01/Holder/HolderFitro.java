package com.example.kevindrakonian.eticallv01.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevindrakonian.eticallv01.R;

public class HolderFitro extends RecyclerView.ViewHolder {

    private TextView NombreFiltro;
    private TextView DepartementoFiltro;
    private TextView UnidadFiltro;
    private ImageView ImagenFiltro;

    public HolderFitro(@NonNull View itemView) {
        super(itemView);

        NombreFiltro = (TextView) itemView.findViewById(R.id.nombre_filtro);
        DepartementoFiltro = (TextView)  itemView.findViewById(R.id.departamento_filtro);
        UnidadFiltro = (TextView) itemView.findViewById(R.id.unidad_filtro);
        ImagenFiltro = (ImageView) itemView.findViewById(R.id.imagen_filtro);
    }


    public TextView getNombreFiltro() {
        return NombreFiltro;
    }

    public void setNombreFiltro(TextView nombreFiltro) {
        NombreFiltro = nombreFiltro;
    }

    public TextView getDepartementoFiltro() {
        return DepartementoFiltro;
    }

    public void setDepartementoFiltro(TextView departementoFiltro) {
        DepartementoFiltro = departementoFiltro;
    }

    public TextView getUnidadFiltro() {
        return UnidadFiltro;
    }

    public void setUnidadFiltro(TextView unidadFiltro) {
        UnidadFiltro = unidadFiltro;
    }

    public ImageView getImagenFiltro() {
        return ImagenFiltro;
    }

    public void setImagenFiltro(ImageView imagenFiltro) {
        ImagenFiltro = imagenFiltro;
    }
}
