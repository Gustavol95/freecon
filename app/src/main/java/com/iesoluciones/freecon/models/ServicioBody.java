package com.iesoluciones.freecon.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iedeveloper on 11/08/17.
 */

public class ServicioBody {

    @SerializedName("categoria")
    int idCategoria;
    @SerializedName("servicio")
    int idServicio;

    public ServicioBody(int idCategoria, int idServicio) {
        this.idCategoria = idCategoria;
        this.idServicio = idServicio;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public String toString() {
        return "ServicioBody{" +
                "idCategoria=" + idCategoria +
                ", idServicio=" + idServicio +
                '}';
    }
}
