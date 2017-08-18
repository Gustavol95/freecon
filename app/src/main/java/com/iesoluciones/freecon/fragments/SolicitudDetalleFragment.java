package com.iesoluciones.freecon.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.models.Solicitud;
import com.iesoluciones.freecon.network.helpers.CustomResourceObserver;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created by iedeveloper on 17/08/17.
 */

public class SolicitudDetalleFragment extends Fragment {

    static final String TAG=SolicitudDetalleFragment.class.getSimpleName();

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



    public static SolicitudDetalleFragment newInstance(Solicitud solicitud){
        SolicitudDetalleFragment fragment = new SolicitudDetalleFragment();
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

    @OnClick(R.id.buttonRechazar)
    public void rechazar(){

        new AlertDialog.Builder(getContext())
                .setMessage("Confirme que rechaza la solicitud")
                .setTitle("Confirmación")
                .setCancelable(false)
                .setPositiveButton(App.getInstance().getResources().getString(R.string.button_confirm), (dialog, id) -> {
                    buttonContactar.setVisibility(View.VISIBLE);
                    buttonContactar.setEnabled(false);
                    ObservableHelper.respuestaSolicitud(solicitud.getId()+"","0")
                            .subscribe(new CustomResourceObserver<ResponseBody>(getContext()) {
                                @Override
                                public void onNext(ResponseBody value) {
                                    ((AppCompatActivity)getContext()).onBackPressed();
                                }
                            });

                })
                .setNegativeButton(getContext().getString(R.string.button_cancel),(dialog1, which) -> {

                } )
                .show();

    }

    @OnClick(R.id.buttonAceptar)
    public void aceptar(){
        new AlertDialog.Builder(getContext())
                .setMessage("Confirme que acepta la solicitud")
                .setTitle("Confirmación")
                .setCancelable(false)
                .setPositiveButton(App.getInstance().getResources().getString(R.string.button_confirm), (dialog, id) -> {
                    buttonContactar.setVisibility(View.VISIBLE);
                    buttonContactar.setEnabled(false);
                    ObservableHelper.respuestaSolicitud(solicitud.getId()+"","1")
                            .subscribe(new CustomResourceObserver<ResponseBody>(getContext()) {
                                @Override
                                public void onNext(ResponseBody value) {
                                    buttonContactar.setEnabled(true);
                                    buttonAceptar.setVisibility(View.GONE);
                                    buttonRechazar.setVisibility(View.GONE);
                                }
                            });

                })
                .setNegativeButton(getContext().getString(R.string.button_cancel),(dialog1, which) -> {

                } )
                .show();



    }

    @OnClick(R.id.buttonContactar)
    public void Contactar(){
        String uri = "tel:" + solicitud.getCelular().trim() ;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}