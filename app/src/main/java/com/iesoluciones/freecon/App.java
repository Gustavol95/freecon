package com.iesoluciones.freecon;
import android.app.Application;

import com.iesoluciones.freecon.interceptors.LogInterceptor;
import com.iesoluciones.freecon.models.CategoriaResponse;
import com.iesoluciones.freecon.models.DaoMaster;
import com.iesoluciones.freecon.models.DaoSession;
import com.iesoluciones.freecon.models.RegistroBody;
import com.iesoluciones.freecon.models.Servicio;
import com.iesoluciones.freecon.models.ServicioResponse;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class App extends Application {

    public static final String BASE_URL ="http://10.112.32.244/freecon/v1/";
    private static App shareInstance;
    private DaoSession daoSession;
    ApiRoutes apiRoutes;

    public static synchronized App getInstance(){
        return shareInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        shareInstance = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"dbFreecon");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        apiRoutes = retrofit.create(ApiRoutes.class);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public ApiRoutes getApiRoutes() {
        return apiRoutes;
    }

    public void setApiRoutes(ApiRoutes apiRoutes) {
        this.apiRoutes = apiRoutes;
    }

    public interface ApiRoutes{

        @GET("proveedores/servicios")
        Observable<ServicioResponse> getServicios();

        @GET("proveedores/categorias")
        Observable<CategoriaResponse> getCategorias();

        @POST("proveedores/register")
        Observable<ResponseBody> registrar(@Body RegistroBody servicio);

        @FormUrlEncoded
        @POST("proveedores/login")
        Observable<ResponseBody> login(@Field("email") String email, @Field("password") String password);

        @POST("proveedores/confirmaccount")
        Observable<ResponseBody> confirmarRegistro(@Field("email") String email, @Field("codigo") String codigo);

    }




}
