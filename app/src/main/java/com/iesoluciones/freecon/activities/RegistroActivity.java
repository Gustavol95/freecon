package com.iesoluciones.freecon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.iesoluciones.freecon.fragments.RegistroDosFragment;
import com.iesoluciones.freecon.fragments.RegistroTresFragment;
import com.iesoluciones.freecon.fragments.RegistroUnoFragment;
import com.iesoluciones.freecon.intefaces.RegistroCallback;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroActivity extends AppCompatActivity implements RegistroCallback {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        if (savedInstanceState == null) {
            Fragment newFragment = RegistroUnoFragment.newInstance(this);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frameRegistro, newFragment).commit();
        }
    }

    @Override
    public void pasoUno(RegistroCallback registroCallback) {
            Fragment newFragment = RegistroDosFragment.newInstance(registroCallback);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameRegistro, newFragment).commit();

    }

    @Override
    public void pasoDos( RegistroCallback registroCallback) {
        Fragment newFragment = RegistroTresFragment.newInstance(registroCallback);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameRegistro, newFragment).commit();
    }

    @Override
    public void finalizar() {

    }
}
