package com.iesoluciones.freecon.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iedeveloper on 10/08/17.
 */

public class FinalizarRegistroFbBody {


    @SerializedName("email")
    String email;
    @SerializedName("profesion")
    String profesion;
    @SerializedName("descripcion")
    String descripcion;
    @SerializedName("celular")
    String celular;
    @SerializedName("calle")
    String calle;
    @SerializedName("num_int")
    String interior;
    @SerializedName("num_ext")
    String exterior;
    @SerializedName("colonia")
    String colonia;
    @SerializedName("cp")
    String cp;
    @SerializedName("ciudad")
    String ciudad;
    @SerializedName("estado")
    String estado;
    @SerializedName("servicios")
    List<Integer> servicios;

    @Override
    public String toString() {
        return "FinalizarRegistroFbBody{" +
                "email='" + email + '\'' +
                ", profesion='" + profesion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", celular='" + celular + '\'' +
                ", calle='" + calle + '\'' +
                ", interior='" + interior + '\'' +
                ", exterior='" + exterior + '\'' +
                ", colonia='" + colonia + '\'' +
                ", cp='" + cp + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estado='" + estado + '\'' +
                ", servicios=" + servicios +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Integer> getServicios() {
        return servicios;
    }

    public void setServicios(List<Integer> servicios) {
        this.servicios = servicios;
    }
}
