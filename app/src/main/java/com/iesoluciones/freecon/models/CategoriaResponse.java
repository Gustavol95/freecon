package com.iesoluciones.freecon.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

/**
 * Created by iedeveloper on 09/08/17.
 */

public class CategoriaResponse {

    @SerializedName("data")
    List<Categoria> data;

    public List<Categoria> getData() {
        return data;
    }

    public void setData(List<Categoria> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CategoriaResponse{" +
                "data=" + data +
                '}';
    }


}
