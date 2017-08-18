package com.iesoluciones.freecon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.adapters.SolicitudesAdapter;
import com.iesoluciones.freecon.models.Solicitud;
import com.iesoluciones.freecon.models.SolicitudesResponse;
import com.iesoluciones.freecon.network.helpers.CustomResourceObserver;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

/**
 * Created by iedeveloper on 07/08/17.
 */

public class SolicitudesFragment extends Fragment {


    static final String TAG = SolicitudesFragment.class.getSimpleName();

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    SolicitudesAdapter adapter;

    public static SolicitudesFragment newInstance() {
        return new SolicitudesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_solicitudes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new SolicitudesAdapter(new ArrayList<>(), solicitud -> {

            getFragmentManager().beginTransaction().replace(R.id.frameDrawer,SolicitudDetalleFragment.newInstance(solicitud))
                    .addToBackStack("TAG")
                    .commit();

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        ObservableHelper.solicitudes()
                .subscribe(new CustomResourceObserver<SolicitudesResponse>(getContext()) {
                    @Override
                    public void onNext(SolicitudesResponse solicitudesResponse) {
                        adapter.setSolicitudes(solicitudesResponse.getSolicitudes());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //   super.onError(e);
                        Log.i(TAG, "TRONIQUI SOlicutydes" + e.toString());
                    }
                });

    }
}
