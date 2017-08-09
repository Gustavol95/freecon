package com.iesoluciones.freecon;

import com.iesoluciones.freecon.models.Servicio;
import com.iesoluciones.freecon.models.ServicioResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class ObservableHelper {

    public static Observable<List<Servicio>> getServicios(){
        return  App.getInstance()
                .getApiRoutes()
                .getDirections()
                .subscribeOn(Schedulers.newThread())
                .map( (ServicioResponse info)->{
                    App.getInstance().getDaoSession().getServicioDao().insertInTx(info.getData());
                    return info.getData();
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

}
