package com.iesoluciones.freecon.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by iedeveloper on 17/08/17.
 */

@Entity
public class Solicitud {
    @Id
    long id;
    @SerializedName("fecha_solicitud")
    String fechaSolicitud;
    String categoria;
    String servicio;
    @SerializedName("first_name")
    String nombre;
    @SerializedName("last_name")
    String apellidos;
    String email;
    String avatar;
    String profesion;
    @SerializedName("solicitud_descripcion")
    String descripcion;
    String celular;
    String calle;
    @SerializedName("num_int")
    String numeroInterior;
    @SerializedName("num_ext")
    String numeroExterior;
    String colonia;
    String cp;
    String ciudad;
    String estado;


    @Generated(hash = 2112171869)
    public Solicitud(long id, String fechaSolicitud, String categoria,
            String servicio, String nombre, String apellidos, String email,
            String avatar, String profesion, String descripcion, String celular,
            String calle, String numeroInterior, String numeroExterior,
            String colonia, String cp, String ciudad, String estado) {
        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
        this.categoria = categoria;
        this.servicio = servicio;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.avatar = avatar;
        this.profesion = profesion;
        this.descripcion = descripcion;
        this.celular = celular;
        this.calle = calle;
        this.numeroInterior = numeroInterior;
        this.numeroExterior = numeroExterior;
        this.colonia = colonia;
        this.cp = cp;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    @Generated(hash = 1764112260)
    public Solicitud() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
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

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", fechaSolicitud=" + fechaSolicitud +
                ", categoria='" + categoria + '\'' +
                ", servicio='" + servicio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", profesion='" + profesion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", celular='" + celular + '\'' +
                ", calle='" + calle + '\'' +
                ", numeroInterior='" + numeroInterior + '\'' +
                ", numeroExterior='" + numeroExterior + '\'' +
                ", colonia='" + colonia + '\'' +
                ", cp='" + cp + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
