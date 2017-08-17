package com.iesoluciones.freecon.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.LoginStatusCallback;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.models.Categoria;
import com.iesoluciones.freecon.models.LoginFbResponse;
import com.iesoluciones.freecon.models.Servicio;
import com.iesoluciones.freecon.network.helpers.CustomResourceObserver;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.subjects.Subject;
import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {

    static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.inputLayoutUsuario)
    TextInputLayout inputLayoutUsuario;
    @BindView(R.id.editUsuario)
    TextInputEditText editUsuario;
    @BindView(R.id.textInputContrasena)
    TextInputLayout textInputConstrasena;
    @BindView(R.id.editContrasena)
    TextInputEditText editContrasena;
    @BindView(R.id.tvOlvidarContrasena)
    TextView tvOlvidarContrasena;
    @BindView(R.id.buttonIniciarSesion)
    AppCompatButton buttonIniciarSesion;
    @BindView(R.id.linearRegistrate)
    LinearLayout linearRegistrate;
    @BindView(R.id.login_button)
    LoginButton loginButton;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
        Log.i(TAG,"FIREBASE TOKEENNN -----------> "+ FirebaseInstanceId.getInstance().getToken());

        ObservableHelper.getServicios().subscribe(new CustomResourceObserver<List<Servicio>>(this) {
            @Override
            public void onNext(List<Servicio> value) {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.i(TAG,e+"");
            }
        });
        ObservableHelper.getCatgorias().subscribe(new CustomResourceObserver<List<Categoria>>(LoginActivity.this) {
            @Override
            public void onNext(List<Categoria> value) {

            }
        });




        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i(TAG, loginResult.getAccessToken().getToken() + " TOKEN FB");
                ObservableHelper.loginFb(loginResult.getAccessToken().getToken(), FirebaseInstanceId.getInstance().getToken())
                        .subscribe(new CustomResourceObserver<LoginFbResponse>(LoginActivity.this) {
                            @Override
                            public void onNext(LoginFbResponse value) {
                                if (value.getUsuario().getActivado() == 1) {
                                    //Pasa directito al dashboard
                                    startActivity(new Intent(LoginActivity.this,DrawerActivity.class));
                                    finish();
                                    Log.i(TAG, "ACTIVADO TRUE");
                                } else {
                                    //Pasa directito al registro CON EXTRAS
                                    Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                                    i.putExtra("fb", true);
                                    startActivity(i);
                                    Log.i(TAG, "ACTIVADO FALSE");
                                }
                                Log.i(TAG, "ACTIVADO aasas");
                            }

                        });
            }

            @Override
            public void onCancel() {
                // App code
                Log.i(TAG, " CANCELÓOOO");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i(TAG, exception.getMessage() + " VALIO V FB");
            }
        });
    }


    @OnClick(R.id.linearRegistrate)
    public void registrarse() {
        startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
    }

    @OnClick(R.id.buttonIniciarSesion)
    public void iniciarSesion() {
        ObservableHelper.login(editUsuario.getText().toString().trim(), editContrasena.getText().toString(), FirebaseInstanceId.getInstance().getToken())
                .subscribe(new CustomResourceObserver<ResponseBody>(this) {
                    @Override
                    public void onNext(ResponseBody value) {
                        startActivity(new Intent(LoginActivity.this,DrawerActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
        //  startActivity(new Intent(LoginActivity.this,DrawerActivity.class));
        //  this.finish();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
