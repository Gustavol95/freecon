package com.iesoluciones.freecon.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * Created by iedeveloper on 09/08/17.
 */

public class RegistroResponse {
    long id;
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

}
