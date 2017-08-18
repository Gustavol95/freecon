package com.iesoluciones.freecon.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iedeveloper on 17/08/17.
 */

public class SolicitudesResponse {
    @SerializedName("data")
    List<Solicitud> solicitudes;

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public String toString() {
        return "SolicitudesResponse{" +
                "solicitudes=" + solicitudes +
                '}';
    }
}
