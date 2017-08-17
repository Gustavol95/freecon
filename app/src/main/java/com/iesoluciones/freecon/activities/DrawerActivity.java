package com.iesoluciones.freecon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.fragments.HistorialFragment;
import com.iesoluciones.freecon.fragments.SaldoFragment;
import com.iesoluciones.freecon.fragments.CategoriasFragment;
import com.iesoluciones.freecon.network.helpers.CustomResourceObserver;
import com.iesoluciones.freecon.services.FirebaseInstanceService;

import okhttp3.ResponseBody;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String TAG=DrawerActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);
        Fragment newFragment = CategoriasFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameDrawer, newFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cerrarSesion) {
            App.getInstance().getDaoSession().getUsuarioDao().deleteAll();
            startActivity(new Intent(DrawerActivity.this,LoginActivity.class));
            LoginManager.getInstance().logOut();
           ObservableHelper.logout(FirebaseInstanceId.getInstance().getToken())
                    .subscribe(new CustomResourceObserver<ResponseBody>(DrawerActivity.this) {
                        @Override
                        public void onNext(ResponseBody value) {
                            Log.i(TAG,"AIII "+value.toString());
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            Log.i(TAG,"FALLO "+e.toString());
                        }
                    });
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment newFragment;
        FragmentTransaction ft;
        switch(id){
            case R.id.historial:
                 newFragment = HistorialFragment.newInstance();
                 ft = getSupportFragmentManager().beginTransaction();
                 ft.replace(R.id.frameDrawer, newFragment).commit();
                break;
            case R.id.saldo:
                 newFragment = SaldoFragment.newInstance();
                 ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameDrawer, newFragment).commit();
                break;
            case R.id.solicitudes:
                 newFragment = CategoriasFragment.newInstance();
                 ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameDrawer, newFragment).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
