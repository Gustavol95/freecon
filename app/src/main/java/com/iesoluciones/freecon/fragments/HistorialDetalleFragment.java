package com.iesoluciones.freecon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.models.Solicitud;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iedeveloper on 18/08/17.
 */

public class HistorialDetalleFragment extends Fragment {

    static final String TAG=HistorialDetalleFragment.class.getSimpleName();

    Solicitud solicitud;
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tvNombre)
    TextView tvNombre;
    @BindView(R.id.tvContacto)
    TextView tvContacto;
    @BindView(R.id.buttonContactar)
    AppCompatButton buttonContactar;
    @BindView(R.id.tvTitulo)
    TextView tvTitulo;
    @BindView(R.id.tvDescripcion)
    TextView tvDescripcion;
    @BindView(R.id.buttonRechazar)
    AppCompatButton buttonRechazar;
    @BindView(R.id.buttonAceptar)
    AppCompatButton buttonAceptar;



    public static HistorialDetalleFragment newInstance(Solicitud solicitud){
        HistorialDetalleFragment fragment = new HistorialDetalleFragment();
        fragment.setSolicitud(solicitud);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_solicitud_detalle,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        tvNombre.setText(solicitud.getNombre()+ " "+solicitud.getApellidos() );
        tvTitulo.setText(solicitud.getServicio());
        tvDescripcion.setText(solicitud.getDescripcion());

    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }


}