package com.example.kevindrakonian.eticallv01.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kevindrakonian.eticallv01.R;

public class CorreoHolder extends RecyclerView.ViewHolder{

    private TextView etTitulocaso;
    private TextView etNombreDocentecaso;

    public CorreoHolder(@NonNull View itemView) {
        super(itemView);

        etTitulocaso = (TextView) itemView.findViewById(R.id.txt_nombreCaso_correo);
        etNombreDocentecaso = (TextView) itemView.findViewById(R.id.txt_nombreDocente_correo);
    }

    public TextView getEtTitulocaso() {
        return etTitulocaso;
    }

    public void setEtTitulocaso(TextView etTitulocaso) {
        this.etTitulocaso = etTitulocaso;
    }

    public TextView getEtNombreDocentecaso() {
        return etNombreDocentecaso;
    }

    public void setEtNombreDocentecaso(TextView etNombreDocentecaso) {
        this.etNombreDocentecaso = etNombreDocentecaso;
    }
}
