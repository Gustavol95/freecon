package com.iesoluciones.freecon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.activities.DrawerActivity;
import com.iesoluciones.freecon.activities.LoginActivity;
import com.iesoluciones.freecon.intefaces.RegistroCallback;
import com.iesoluciones.freecon.models.LoginFbResponse;
import com.iesoluciones.freecon.models.RegistroBody;
import com.iesoluciones.freecon.network.helpers.CustomResourceObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroTresFragment  extends Fragment {


    static final String TAG=RegistroTresFragment.class.getSimpleName();

    RegistroCallback registroCallback;
    @BindView(R.id.textInputDescripcion)
    TextInputLayout textInputDescripcion;
    @BindView(R.id.editDescripcion)
    TextInputEditText editDescripcion;
    @BindView(R.id.buttonFinalizar)
    AppCompatButton buttonFinalizar;
    @BindView(R.id.inputLayoutProfesion)
    TextInputLayout inputLayoutProfesion;
    @BindView(R.id.editProfesion)
    TextInputEditText editProfesion;

    public static RegistroTresFragment newInstance(RegistroCallback registroCallback){
        RegistroTresFragment fragment=new RegistroTresFragment();
        fragment.setRegistroCallback(registroCallback);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro_tres,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        if(registroCallback.getRegistro().getProfesion()!=null){
            editProfesion.setText(registroCallback.getRegistro().getProfesion());
            editDescripcion.setText(registroCallback.getRegistro().getDescripcion());
        }
    }

    public RegistroCallback getRegistroCallback() {
        return registroCallback;
    }

    public void setRegistroCallback(RegistroCallback registroCallback) {
        this.registroCallback = registroCallback;
    }


    @Override
    public void onStop() {
        registroCallback.getRegistro().setProfesion(editProfesion.getText().toString());
        registroCallback.getRegistro().setDescripcion(editDescripcion.getText().toString());
        super.onStop();
    }

    @OnClick(R.id.buttonFinalizar)
    public void finalizar(){
        registroCallback.getRegistro().setProfesion(editProfesion.getText().toString());
        registroCallback.getRegistro().setDescripcion(editDescripcion.getText().toString());
        Log.i(TAG,registroCallback.getRegistro().toString());
        //PETICION Y KILL ACTIVITY;
        if(registroCallback.isFacebookUser()){
            ObservableHelper.finalizarRegistroFb(registroCallback.getRegistro())
                    .subscribe(new CustomResourceObserver<LoginFbResponse>(getContext()) {
                        @Override
                        public void onNext(LoginFbResponse value) {
                            //Fin de registro, ya tengo el token
                            new AlertDialog.Builder(getContext())
                                    .setMessage("Tu cuenta ha sido activada.")
                                    .setTitle("Registro exitoso")
                                    .setCancelable(false)
                                    .setPositiveButton(App.getInstance().getResources().getString(R.string.button_send), (dialog, id) -> {
                                        //Enviar a drawer activity
                                        startActivity(new Intent(getContext(),LoginActivity.class));
                                        ((AppCompatActivity)getContext()).finish();
                                    })
                                    .show();

                        }
                    });
        }else{
            ObservableHelper.registrar(registroCallback.getRegistro())
                    .subscribe(new CustomResourceObserver<LoginFbResponse>(getContext()) {
                        @Override
                        public void onNext(LoginFbResponse value) {
                            //  new AlertDialog.Builder(context)
                            final AppCompatEditText input = new AppCompatEditText(getContext());
                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                            input.setLayoutParams(lp);
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Registro exitoso")
                                    .setMessage("Se envió un código de activación al correo electrónico: "+value.getUsuario().getEmail())
                                    .setCancelable(false)
                                    .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), (dialog, id) -> {
                                        //Enviar a drawer activity
                                        new AlertDialog.Builder(getContext())
                                                .setMessage("Ingresa el código que se envió a tu correo.")
                                                .setTitle("Ingresar código de activación")
                                                .setCancelable(false)
                                                .setView(input)
                                                .setPositiveButton(App.getInstance().getResources().getString(R.string.button_send), (dialog2, id2) -> {
                                                    //MANDAR A COMPA SERVICIO DE LUISITO
                                                    ObservableHelper.activarCuenta(input.getText().toString())
                                                            .subscribe(new CustomResourceObserver<LoginFbResponse>(getContext()) {
                                                                @Override
                                                                public void onNext(LoginFbResponse value) {
                                                                    new AlertDialog.Builder(getContext())
                                                                            .setMessage("Tu cuenta ha sido activada.")
                                                                            .setTitle("Registro exitoso")
                                                                            .setCancelable(false)
                                                                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_send), (dialog, id) -> {
                                                                                //Enviar a drawer activity
                                                                                getContext().startActivity(new Intent(getContext(),LoginActivity.class));
                                                                                ((AppCompatActivity)getContext()).finish();
                                                                            })
                                                                            .setNegativeButton(getContext().getString(R.string.button_cancel),(dialog1, which) -> {
                                                                                ((AppCompatActivity)getContext()).finish();
                                                                            } )
                                                                            .show();
                                                                }
                                                            });
                                                })
                                                .setNegativeButton(getContext().getResources().getString(R.string.button_cancel),(dialog3, which) -> {
                                                    dialog.dismiss();
                                                }).show();


                                    }).show();
                        }
                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            Log.i(TAG,e.getMessage());
                        }
                    });
        }

    }


}
