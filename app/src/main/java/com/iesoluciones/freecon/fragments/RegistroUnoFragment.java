package com.iesoluciones.freecon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.intefaces.RegistroCallback;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.view.RxViewGroup;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroUnoFragment extends Fragment {

    static String TAG=RegistroUnoFragment.class.getCanonicalName();



    @BindView(R.id.textInputNombre)
    TextInputLayout textInputNombre;
    @BindView(R.id.editNombre)
    TextInputEditText editNombre;
    @BindView(R.id.textInputApellido)
    TextInputLayout textInputApellido;
    @BindView(R.id.editApellido)
    TextInputEditText editApellido;
    @BindView(R.id.textInputCorreoElectronico)
    TextInputLayout textInputCorreoElectronico;
    @BindView(R.id.editCorreoElectronico)
    TextInputEditText editCorreoElectronico;
    @BindView(R.id.textInputContrasena)
    TextInputLayout textInputContrasena;
    @BindView(R.id.editContrasena)
    TextInputEditText editContrasena;
    @BindView(R.id.textInputContrasenaConfirmacion)
    TextInputLayout textInputContrasenaConfirmacion;
    @BindView(R.id.editContrasenaConfirmacion)
    TextInputEditText editContrasenaConfirmacion;
    @BindView(R.id.textInputTelefono)
    TextInputLayout textInputTelefono;
    @BindView(R.id.editTelefono)
    TextInputEditText editTelefono;
    @BindView(R.id.textInputCalle)
    TextInputLayout textInputCalle;
    @BindView(R.id.editCalle)
    TextInputEditText editCalle;
    @BindView(R.id.textInputEstado)
    TextInputLayout textInputEstado;
    @BindView(R.id.editEstado)
    TextInputEditText editEstado;
    @BindView(R.id.textInputColonia)
    TextInputLayout textInputColonia;
    @BindView(R.id.editColonia)
    TextInputEditText editColonia;
    @BindView(R.id.textInputCiudad)
    TextInputLayout textInputCiudad;
    @BindView(R.id.editCiudad)
    TextInputEditText editCiudad;
    @BindView(R.id.textInputCP)
    TextInputLayout textInputCP;
    @BindView(R.id.editCP)
    TextInputEditText editCP;
    @BindView(R.id.textInputExterior)
    TextInputLayout textInputExterior;
    @BindView(R.id.editExterior)
    TextInputEditText editExterior;
    @BindView(R.id.textInputInterior)
    TextInputLayout textInputInterior;
    @BindView(R.id.editInterior)
    TextInputEditText editInterior;
    @BindView(R.id.buttonContinuar)
    AppCompatButton buttonContinuar;
    RegistroCallback registroCallback;


    public static RegistroUnoFragment newInstance(RegistroCallback registroCallback){
        RegistroUnoFragment fragment=new RegistroUnoFragment();
        fragment.setRegistroCallback(registroCallback);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro_uno, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

    }

    @OnClick(R.id.buttonContinuar)
    public void continuar(){
        registroCallback.getRegistro().setNombre(editNombre.getText().toString());
        registroCallback.getRegistro().setApellido(editApellido.getText().toString());
        registroCallback.getRegistro().setContrasena(editContrasena.getText().toString());
        registroCallback.getRegistro().setEmail(editCorreoElectronico.getText().toString());
        registroCallback.getRegistro().setCelular(editTelefono.getText().toString());
        registroCallback.getRegistro().setCalle(editCalle.getText().toString());
        registroCallback.getRegistro().setEstado(editEstado.getText().toString());
        registroCallback.getRegistro().setColonia(editColonia.getText().toString());
        registroCallback.getRegistro().setCiudad(editCiudad.getText().toString());
        registroCallback.getRegistro().setCp(editCP.getText().toString());
        registroCallback.getRegistro().setExterior(editExterior.getText().toString());
        registroCallback.getRegistro().setInterior(editInterior.getText().toString());
        registroCallback.pasoUno(registroCallback);

    }

    public RegistroCallback getRegistroCallback() {
        return registroCallback;
    }

    public void setRegistroCallback(RegistroCallback registroCallback) {
        this.registroCallback = registroCallback;
    }
}
