package com.iesoluciones.freecon;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.inputLayoutUsuario)
    TextInputLayout inputLayoutUsuario;
    @BindView(R.id.editUsuario)
    AppCompatEditText editUsuario;
    @BindView(R.id.textInputContrasena)
    TextInputLayout textInputConstrasena;
    @BindView(R.id.editContrasena)
    AppCompatEditText editContrasena;
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
        startActivity(new Intent(LoginActivity.this,DrawerActivity.class));
        this.finish();
    }




}
