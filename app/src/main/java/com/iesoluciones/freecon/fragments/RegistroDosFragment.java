package com.iesoluciones.freecon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.intefaces.RegistroCallback;
import com.iesoluciones.freecon.models.Servicio;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroDosFragment extends Fragment {

    static final String TAG = RegistroDosFragment.class.getCanonicalName();

    RegistroCallback registroCallback;
    @BindView(R.id.inputLayoutProfesion)
    TextInputLayout inputLayoutProfesion;
    @BindView(R.id.editProfesion)
    TextInputEditText editProfesion;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.buttonContinuar)
    AppCompatButton buttonContinuar;

    public static RegistroDosFragment newInstance(RegistroCallback registroCallback){
        RegistroDosFragment fragment = new RegistroDosFragment();
        fragment.setRegistroCallback(registroCallback);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro_dos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        ObservableHelper.getServicios().subscribe((List<Servicio> data)->{
            for(Servicio s:data){
                Log.i(TAG,s.toString());
            }
        });
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

