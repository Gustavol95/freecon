package com.iesoluciones.freecon;

import android.util.Log;

import com.iesoluciones.freecon.models.Categoria;
import com.iesoluciones.freecon.models.CategoriaResponse;
import com.iesoluciones.freecon.models.RegistroBody;
import com.iesoluciones.freecon.models.Servicio;
import com.iesoluciones.freecon.models.ServicioResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class ObservableHelper {

    public static Observable<List<Servicio>> getServicios(){
        return  App.getInstance()
                .getApiRoutes()
                .getServicios()
                .subscribeOn(Schedulers.newThread())
                .map( (ServicioResponse info)->{
                    App.getInstance().getDaoSession().getServicioDao().insertOrReplaceInTx(info.getData());
                    return info.getData();
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<Categoria>> getCatgorias(){
        return App.getInstance().getApiRoutes().getCategorias()
                .subscribeOn(Schedulers.newThread())
                .map((CategoriaResponse info)->{
                    App.getInstance().getDaoSession().getCategoriaDao().insertOrReplaceInTx(info.getData());
                        return info.getData();
                })
             .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ResponseBody> registrar(RegistroBody registroBody){
        return App.getInstance().getApiRoutes().registrar(registroBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<ResponseBody> login(String email, String password){
        return App.getInstance().getApiRoutes().login(email,password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
