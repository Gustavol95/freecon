package com.iesoluciones.freecon.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.models.Solicitud;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 17/08/17.
 */

public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.ViewHolder> {


    List<Solicitud> solicitudes;
    OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Solicitud solicitud);
    }

    public SolicitudesAdapter(List<Solicitud> solicitudes, OnItemClickListener listener) {
        this.solicitudes=solicitudes;
        this.listener=listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_solicitudes, null);
        ViewHolder holder = new ViewHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(solicitudes.get(position));
    }

    @Override
    public int getItemCount() {
        return solicitudes.size();
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvCalificacion)
    TextView tvCalificacion;
    @BindView(R.id.ivEstrella)
    ImageView ivEstrella;
    @BindView(R.id.tvTitulo)
    TextView tvTitulo;   //Lo uso para la profesion
    @BindView(R.id.tvDescripcion)
    TextView tvDescripcion;
    @BindView(R.id.tvFolio)
    TextView tvFolio;    //Lo uso para el titulo
    @BindView(R.id.tvFecha)
    TextView tvFecha;    // Lo uso para hace cuanto tiempo



    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Solicitud solicitud){
        tvFolio.setText(solicitud.getNombre()+" "+solicitud.getApellidos());
        tvTitulo.setText(solicitud.getCategoria()+" > "+solicitud.getServicio());
        tvDescripcion.setText(solicitud.getDescripcion());
        //tvFecha.setText(solicitud.getFechaSolicitud().toString());
        itemView.setOnClickListener(v -> listener.onItemClick(solicitud));

    }

}
}
