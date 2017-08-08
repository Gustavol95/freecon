package com.iesoluciones.freecon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.intefaces.RegistroCallback;

import butterknife.OnClick;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroDosFragment extends Fragment {

    RegistroCallback registroCallback;

    public static RegistroDosFragment newInstance(RegistroCallback registroCallback){
        RegistroDosFragment fragment = new RegistroDosFragment();
        fragment.setRegistroCallback(registroCallback);
        return new RegistroDosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro_dos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public RegistroCallback getRegistroCallback() {
        return registroCallback;
    }

    public void setRegistroCallback(RegistroCallback registroCallback) {
        this.registroCallback = registroCallback;
    }

    @OnClick(R.id.buttonContinuar)
    public void continuar(){
        registroCallback.pasoDos(registroCallback);
    }
}

