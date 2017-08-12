package com.iesoluciones.freecon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.models.LoginFbResponse;
import com.iesoluciones.freecon.network.helpers.CustomResourceObserver;

public class SplashActivity extends AppCompatActivity {

    static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            if (accessToken.isExpired()) {
                Toast.makeText(this, "Expiró, pidelo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "JALEESE COMPA", Toast.LENGTH_SHORT).show();
                ObservableHelper.loginFb(accessToken.getToken())
                        .subscribe(new CustomResourceObserver<LoginFbResponse>(SplashActivity.this) {
                            @Override
                            public void onNext(LoginFbResponse value) {
                                if (value.getUsuario().getActivado() == 1) {
                                    //Pasa directito al dashboard
                                    startActivity(new Intent(SplashActivity.this,DrawerActivity.class));
                                    finish();
                                    Log.i(TAG, "ACTIVADO TRUE");
                                } else {
                                    Log.i(TAG," Si hay facebookNo esta activado, que lo intente en el Login");
                                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                                    finish();
                                }
                                Log.i(TAG, "ACTIVADO aasas");
                            }

                        });

            }
        } else {
            Toast.makeText(this, "PIDELOO", Toast.LENGTH_SHORT).show();
        }




    }

}
