package com.iesoluciones.freecon.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iedeveloper on 09/08/17.
 */

public class RegistroBody {

    @SerializedName("first_name")
    String nombre;
    @SerializedName("last_name")
    String apellido;
    @SerializedName("password")
    String contrasena;
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
    @Override
    public String toString() {
        return "RegistroBody{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
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

    public static RegistroBody getDefault(){
        RegistroBody body=new RegistroBody();
        body.setNombre("Gustavo");
        body.setApellido("López Sánchez");
        body.setEmail("tavols889@gmail.com");
        body.setContrasena("123");
        body.setCiudad("Culiacán");
        body.setCalle("Mi calle");
        body.setColonia("mi Colonia");
        body.setCp("80190");
        body.setEstado("Sinaloa");
        body.setProfesion("Medico");
        body.setDescripcion("Mi desc");
        body.setInterior("44");
        body.setExterior("1114");
        body.setCelular("6675124578");
        List<Integer> servs=new ArrayList<>();
        servs.add(2);
        servs.add(3);
        body.setServicios(servs);
        return body;
    }
}
