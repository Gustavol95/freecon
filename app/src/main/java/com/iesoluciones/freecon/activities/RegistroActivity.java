package com.iesoluciones.freecon.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.fragments.RegistroDosFragment;
import com.iesoluciones.freecon.fragments.RegistroTresFragment;
import com.iesoluciones.freecon.fragments.RegistroUnoFragment;
import com.iesoluciones.freecon.intefaces.RegistroCallback;
import com.iesoluciones.freecon.models.RegistroBody;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroActivity extends AppCompatActivity implements RegistroCallback {

    static String TAG = RegistroActivity.class.getCanonicalName();
    static final String UNO="uno";
    static final String DOS="dos";
    static final String TRES="tres";
    int index;
    @BindView(R.id.toolbarRegistro)
    Toolbar toolbar;
    RegistroBody registro;
    boolean facebookUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        registro=new RegistroBody();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        facebookUser=getIntent().getBooleanExtra("fb",false);
        Log.i(TAG," facebook "+facebookUser);

        if (savedInstanceState == null) {
            Fragment newFragment = RegistroUnoFragment.newInstance(this);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frameRegistro, newFragment,UNO).commit();
            index=0;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Fragment newFragment;
                FragmentTransaction ft;
                switch(index){
                    case 0:
                        return(super.onOptionsItemSelected(item));
                    case 1:
                        newFragment = RegistroUnoFragment.newInstance(this);
                         ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frameRegistro, newFragment,UNO).commit();
                        index=0;
                        break;
                    case 2:
                        newFragment = RegistroDosFragment.newInstance(this);
                         ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frameRegistro, newFragment,DOS).commit();
                        index=1;
                        break;
                }
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void pasoUno(RegistroCallback registroCallback) {
            Fragment newFragment = RegistroDosFragment.newInstance(registroCallback);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.addToBackStack("TAG");
            ft.replace(R.id.frameRegistro, newFragment,DOS).commit();
        index=1;

    }

    @Override
    public void pasoDos( RegistroCallback registroCallback) {
        Fragment newFragment = RegistroTresFragment.newInstance(registroCallback);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack("TAG");
        ft.replace(R.id.frameRegistro, newFragment, TRES).commit();
        index=2;
    }

    @Override
    public void finalizar() {

    }

    @Override
    public RegistroBody getRegistro() {
        return registro;
    }

    @Override
    public boolean isFacebookUser() {
        return facebookUser;
    }
}
