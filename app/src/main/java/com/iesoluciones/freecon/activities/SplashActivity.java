package com.iesoluciones.freecon.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.firebase.iid.FirebaseInstanceId;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.models.LoginFbResponse;
import com.iesoluciones.freecon.network.helpers.CustomResourceObserver;

public class SplashActivity extends AppCompatActivity {

    static final String TAG = SplashActivity.class.getSimpleName();
    Handler handler;
    Runnable r;
    //todo Probar que entre a DrawerActivity si no cerró sesión
    //todo Cambiar la lógica del token
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        r= () -> {
            SharedPreferences prefs =
                    getSharedPreferences(getResources().getString(R.string.shared_preferences), Context.MODE_PRIVATE);
            boolean sesionCorreo = prefs.getBoolean(getResources().getString(R.string.sesion_correo), false);
            AccessToken accessToken = AccessToken.getCurrentAccessToken();

            if (accessToken != null) {
                if (accessToken.isExpired()) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                    ObservableHelper.loginFb(accessToken.getToken(), FirebaseInstanceId.getInstance().getToken())
                            .subscribe(new CustomResourceObserver<LoginFbResponse>(SplashActivity.this) {
                                @Override
                                public void onNext(LoginFbResponse value) {
                                    if (value.getUsuario().getActivado() == 1) {
                                        startActivity(new Intent(SplashActivity.this, DrawerActivity.class));
                                        finish();
                                    } else {
                                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                }

                            });

                }
            } else if (sesionCorreo) {
                startActivity(new Intent(SplashActivity.this, DrawerActivity.class));
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        };
        handler=new Handler();
        handler.postDelayed(r,1000);

        }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(r);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(r);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
