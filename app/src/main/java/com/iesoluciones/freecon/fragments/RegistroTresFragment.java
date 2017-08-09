package com.iesoluciones.freecon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.intefaces.RegistroCallback;
import com.iesoluciones.freecon.models.RegistroBody;

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
    }

    public RegistroCallback getRegistroCallback() {
        return registroCallback;
    }

    public void setRegistroCallback(RegistroCallback registroCallback) {
        this.registroCallback = registroCallback;
    }

    @OnClick(R.id.buttonFinalizar)
    public void finalizar(){
        registroCallback.getRegistro().setDescripcion(editDescripcion.getText().toString());
        Log.i(TAG,registroCallback.getRegistro().toString());
        //PETICION Y KILL ACTIVITY;
       ObservableHelper.registrar(RegistroBody.getDefault())
                .subscribe((ResponseBody response)->{
                    Log.i(TAG,response.toString());
                });
    }
}
