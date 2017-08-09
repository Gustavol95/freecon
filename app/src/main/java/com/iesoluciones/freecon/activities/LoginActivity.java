package com.iesoluciones.freecon.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.network.helpers.CustomResourceObserver;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.linearRegistrate)
    public void registrarse(){
        startActivity(new Intent(LoginActivity.this,RegistroActivity.class));
    }

    @OnClick(R.id.buttonIniciarSesion)
    public void iniciarSesion(){
        ObservableHelper.login(editUsuario.getText().toString().trim(),editContrasena.getText().toString())
                .subscribe(new CustomResourceObserver<ResponseBody>(this) {
                    @Override
                    public void onNext(ResponseBody value) {

                    }
                });
        //  startActivity(new Intent(LoginActivity.this,DrawerActivity.class));
        //  this.finish();
    }




}
