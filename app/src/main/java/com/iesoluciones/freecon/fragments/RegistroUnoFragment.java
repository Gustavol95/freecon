package com.iesoluciones.freecon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.intefaces.RegistroCallback;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroUnoFragment extends Fragment {


    @BindView(R.id.textInputCorreoElectronico)
    TextInputLayout textInputCorreoElectronico;
    @BindView(R.id.editCorreoElectronico)
    AppCompatEditText AppCompatActivity;
    @BindView(R.id.textInputContrasena)
    TextInputLayout textInputContrasena;
    @BindView(R.id.editContrasena)
    AppCompatEditText editContrasena;
    @BindView(R.id.textInputContrasenaConfirmacion)
    TextInputLayout textInputContrasenaConfirmacion;
    @BindView(R.id.editContrasenaConfirmacion)
    AppCompatEditText editContrasenaConfirmacion;
    @BindView(R.id.textInputTelefono)
    TextInputLayout textInputTelefono;
    @BindView(R.id.editTelefono)
    AppCompatEditText editTelefono;
    @BindView(R.id.textInputCalle)
    TextInputLayout textInputCalle;
    @BindView(R.id.editCalle)
    AppCompatEditText editCalle;
    @BindView(R.id.textInputColonia)
    TextInputLayout textInputColonia;
    @BindView(R.id.editColonia)
    AppCompatEditText editColonia;
    @BindView(R.id.textInputCiudad)
    TextInputLayout textInputCiudad;
    @BindView(R.id.editCiudad)
    AppCompatEditText editCiudad;
    @BindView(R.id.textInputCP)
    TextInputLayout textInputCP;
    @BindView(R.id.editCP)
    AppCompatEditText editCP;
    @BindView(R.id.textInputExterior)
    TextInputLayout textInputExterior;
    @BindView(R.id.editExterior)
    AppCompatEditText editExterior;
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
        registroCallback.pasoUno(registroCallback);

    }

    public RegistroCallback getRegistroCallback() {
        return registroCallback;
    }

    public void setRegistroCallback(RegistroCallback registroCallback) {
        this.registroCallback = registroCallback;
    }
}
