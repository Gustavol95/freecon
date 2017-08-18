package com.iesoluciones.freecon;

import android.util.Log;

import com.iesoluciones.freecon.models.Categoria;
import com.iesoluciones.freecon.models.CategoriaResponse;
import com.iesoluciones.freecon.models.FinalizarRegistroFbBody;
import com.iesoluciones.freecon.models.LoginFbResponse;
import com.iesoluciones.freecon.models.RegistroBody;
import com.iesoluciones.freecon.models.Servicio;
import com.iesoluciones.freecon.models.ServicioResponse;
import com.iesoluciones.freecon.models.SolicitudesResponse;
import com.iesoluciones.freecon.models.Usuario;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class ObservableHelper {

    static final String TAG = ObservableHelper.class.getSimpleName();

    public static Observable<List<Servicio>> getServicios() {
        return App.getInstance()
                .getApiRoutes()
                .getServicios()
                .subscribeOn(Schedulers.newThread())
                .map((ServicioResponse info) -> {
                    Log.i(TAG, "AHI ESTA "+info.toString());
                    App.getInstance().getDaoSession().getServicioDao().insertOrReplaceInTx(info.getData());
                    return info.getData();
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<Categoria>> getCatgorias() {
        return App.getInstance().getApiRoutes().getCategorias()
                .subscribeOn(Schedulers.newThread())
                .map((CategoriaResponse info) -> {
                    Log.i(TAG, "AHI ESTA "+info.toString());
                    App.getInstance().getDaoSession().getCategoriaDao().insertOrReplaceInTx(info.getData());
                    return info.getData();
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<LoginFbResponse> registrar(RegistroBody registroBody) {
        return App.getInstance().getApiRoutes().registrar(registroBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data)->{
                    Log.i(TAG,"SE metió a la BD "+data.toString());
                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    user.setProfesion(data.getUsuario().getProfesion());
                    user.setDescripcion(data.getUsuario().getDescripcion());
                    user.setCelular(data.getUsuario().getCelular());
                    user.setCalle(data.getUsuario().getCalle());
                    user.setInterior(data.getUsuario().getNum_int());
                    user.setExterior(data.getUsuario().getNum_ext());
                    user.setColonia(data.getUsuario().getColonia());
                    user.setCp(data.getUsuario().getCp());
                    user.setCiudad(data.getUsuario().getCiudad());
                    user.setEstado(data.getUsuario().getEstado());
                    
                    App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);

                    App.getInstance().setToken(data.getToken());
                    Log.i(TAG,"ESTA ES MI TOKEN "+data.getToken());
                    return data;
                });
    }


    public static Observable<LoginFbResponse> login(String email, String password, String firebaseToken) {
        return App.getInstance().getApiRoutes().login(email, password,firebaseToken)
                .map((LoginFbResponse data)->{
                    Log.i(TAG,"SE metió a la BD "+data.toString());
                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    user.setProfesion(data.getUsuario().getProfesion());
                    user.setDescripcion(data.getUsuario().getDescripcion());
                    user.setCelular(data.getUsuario().getCelular());
                    user.setCalle(data.getUsuario().getCalle());
                    user.setInterior(data.getUsuario().getNum_int());
                    user.setExterior(data.getUsuario().getNum_ext());
                    user.setColonia(data.getUsuario().getColonia());
                    user.setCp(data.getUsuario().getCp());
                    user.setCiudad(data.getUsuario().getCiudad());
                    user.setEstado(data.getUsuario().getEstado());

                    App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);

                    App.getInstance().setToken(data.getToken());
                    return data;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public static Observable<LoginFbResponse> loginFb(String facebookToken, String firebaseToken) {
        return App.getInstance().getApiRoutes().loginFb(facebookToken,firebaseToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data) -> {

                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    Log.i(TAG, data.toString());
                    App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);
                    App.getInstance().setToken(data.getToken());
                    return data;
                });

    }

    public static Observable<LoginFbResponse> finalizarRegistroFb(RegistroBody body) {
        return App.getInstance().getApiRoutes().finalizarRegistroFb(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data)->{
                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    user.setProfesion(data.getUsuario().getProfesion());
                    user.setDescripcion(data.getUsuario().getDescripcion());
                    user.setCelular(data.getUsuario().getCelular());
                    user.setCalle(data.getUsuario().getCalle());
                    user.setInterior(data.getUsuario().getNum_int());
                    user.setExterior(data.getUsuario().getNum_ext());
                    user.setColonia(data.getUsuario().getColonia());
                    user.setCp(data.getUsuario().getCp());
                    user.setCiudad(data.getUsuario().getCiudad());
                    user.setEstado(data.getUsuario().getEstado());
                    if (data.getUsuario().getActivado() == 0)
                        user.setActivado(false);
                    else
                        user.setActivado(true);
                    App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);
                    App.getInstance().setToken(data.getToken());
                    return data;
                });
    }

    public static Observable<LoginFbResponse> activarCuenta(String codigo){
        return App.getInstance().getApiRoutes().
                confirmarRegistro(App.getInstance().getDaoSession().getUsuarioDao().loadAll().get(0).getEmail(),codigo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data)->{
                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    user.setProfesion(data.getUsuario().getProfesion());
                    user.setDescripcion(data.getUsuario().getDescripcion());
                    user.setCelular(data.getUsuario().getCelular());
                    user.setCalle(data.getUsuario().getCalle());
                    user.setInterior(data.getUsuario().getNum_int());
                    user.setExterior(data.getUsuario().getNum_ext());
                    user.setColonia(data.getUsuario().getColonia());
                    user.setCp(data.getUsuario().getCp());
                    user.setCiudad(data.getUsuario().getCiudad());
                    user.setEstado(data.getUsuario().getEstado());
                    if (data.getUsuario().getActivado() == 0)
                        user.setActivado(false);
                    else
                        user.setActivado(true);
                    App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);
                    App.getInstance().setToken(data.getToken());
                    return data;
                });

    }

     public static Observable<ResponseBody> logout(String firebaseToken){
         return App.getInstance().getApiRoutes().logout("Bearer "+App.getInstance().getToken(),firebaseToken)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread());
     }

     public static Observable<SolicitudesResponse> solicitudes (){
         return App.getInstance().getApiRoutes().solicitudes("Bearer "+App.getInstance().getToken())
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .map(solicitudesResponse -> {
                     Log.i(TAG,solicitudesResponse.toString());
                     return solicitudesResponse;
                 });
     }

     public static Observable<ResponseBody> respuestaSolicitud(String idSolicitud,String respuesta){
         return App.getInstance().getApiRoutes().respuestaSolicitud("Bearer "+App.getInstance().getToken(),idSolicitud,respuesta)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread());
     }

     public static Observable<SolicitudesResponse> historialSolicitudes(){
         return App.getInstance().getApiRoutes().historial("Bearer "+App.getInstance().getToken())
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread());
     }

}



