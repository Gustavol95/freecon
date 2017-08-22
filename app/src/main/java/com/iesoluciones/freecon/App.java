package com.iesoluciones.freecon;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.FacebookSdk;
import com.iesoluciones.freecon.models.CategoriaResponse;
import com.iesoluciones.freecon.models.DaoMaster;
import com.iesoluciones.freecon.models.DaoSession;
import com.iesoluciones.freecon.models.FinalizarRegistroFbBody;
import com.iesoluciones.freecon.models.LoginFbResponse;
import com.iesoluciones.freecon.models.RegistroBody;
import com.iesoluciones.freecon.models.Servicio;
import com.iesoluciones.freecon.models.ServicioResponse;
import com.iesoluciones.freecon.models.SolicitudesResponse;
import com.iesoluciones.freecon.network.interceptors.LogInterceptor;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class App extends Application {

    static final String[] URLS={
            "http://10.112.32.244/freecon/v1/",
            "http://10.112.32.135/freecon/backend/public/"
    };
    public static final String BASE_URL =URLS[0];
    private static App shareInstance;
    private SharedPreferences prefs;
    private DaoSession daoSession;
    ApiRoutes apiRoutes;
    String token;

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

        prefs = getSharedPreferences(getResources().getString(R.string.shared_preferences), Context.MODE_PRIVATE);


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

    public String getToken() {
        return prefs.getString(getResources().getString(R.string.token),"");
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getResources().getString(R.string.token), token);
        editor.commit();
        this.token = token;
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public interface ApiRoutes{

        @GET("proveedores/servicios")
        Observable<ServicioResponse> getServicios();

        @GET("proveedores/categorias")
        Observable<CategoriaResponse> getCategorias();

        @POST("proveedores/register")
        Observable<LoginFbResponse> registrar(@Body RegistroBody servicio);

        @FormUrlEncoded
        @POST("proveedores/login")
        Observable<LoginFbResponse> login(@Field("email") String email, @Field("password") String password, @Field("token") String tokenFirebase);

        @FormUrlEncoded
        @POST("proveedores/confirmaccount")
        Observable<LoginFbResponse> confirmarRegistro(@Field("email") String email, @Field("codigo") String codigo);

        @FormUrlEncoded
        @POST("proveedores/login-fb")
        Observable<LoginFbResponse> loginFb(@Field("token") String facebookToken, @Field("token_firebase") String firebaseToken);

        @PUT("proveedores/finalizarregistro-fb")
        Observable<LoginFbResponse> finalizarRegistroFb(@Body RegistroBody registroFbBody);

        @FormUrlEncoded
        @POST("proveedores/logoutfirebase")
        Observable<ResponseBody> logout(@Header("Authorization") String jwtToken, @Field("token") String firebaseToken);

        @GET("proveedores/solicitudes")
        Observable<SolicitudesResponse> solicitudes(@Header("Authorization") String token);

        @FormUrlEncoded
        @POST("proveedores/solicitudes/{solicitud_id}/respuesta")
        Observable<ResponseBody> respuestaSolicitud(@Header("Authorization") String token,@Path("solicitud_id") String idSolicitud, @Field("respuesta") String respuesta);

        @GET("proveedores/solicitudes/historial")
        Observable<SolicitudesResponse> historial(@Header("Authorization") String token);

    }




}
