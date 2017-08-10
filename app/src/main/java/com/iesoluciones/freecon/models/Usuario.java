package com.iesoluciones.freecon.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by iedeveloper on 10/08/17.
 */

@Entity
public class Usuario {
    @Id
    long id;
    @SerializedName("first_name")
    String nombre;
    @SerializedName("last_name")
    String apellido;
    @SerializedName("password")
    String contrasena;
    @SerializedName("email")
    String email;
    @SerializedName("avatar")
    String avatar;
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
    @SerializedName("activado")
    boolean activado;





    @Generated(hash = 108333564)
    public Usuario(long id, String nombre, String apellido, String contrasena,
            String email, String avatar, String profesion, String descripcion,
            String celular, String calle, String interior, String exterior,
            String colonia, String cp, String ciudad, String estado,
            boolean activado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.email = email;
        this.avatar = avatar;
        this.profesion = profesion;
        this.descripcion = descripcion;
        this.celular = celular;
        this.calle = calle;
        this.interior = interior;
        this.exterior = exterior;
        this.colonia = colonia;
        this.cp = cp;
        this.ciudad = ciudad;
        this.estado = estado;
        this.activado = activado;
    }

    @Generated(hash = 562950751)
    public Usuario() {
    }


    


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
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
                ", activado=" + activado +
                '}';
    }

    public boolean getActivado() {
        return this.activado;
    }
}
