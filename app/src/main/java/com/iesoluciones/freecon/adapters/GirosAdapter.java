package com.iesoluciones.freecon.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.models.Categoria;
import com.iesoluciones.freecon.models.CategoriaDao;
import com.iesoluciones.freecon.models.Servicio;
import com.iesoluciones.freecon.models.ServicioDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class GirosAdapter extends PagerAdapter {

    static final String TAG = GirosAdapter.class.getSimpleName();
    private Context mContext;
    private List<Categoria> categorias;
    private List<List<Servicio>> servicios;
    List<Integer> seleccionados;

    public GirosAdapter(Context context) {
        mContext = context;
        categorias = App.getInstance().getDaoSession().getCategoriaDao().queryBuilder()
                .orderAsc(CategoriaDao.Properties.Id).list();
        servicios = new ArrayList<>();
        seleccionados = new ArrayList<>();
        for (Categoria c : categorias) {
            servicios.add(App.getInstance().getDaoSession().getServicioDao().queryBuilder().where(ServicioDao.Properties.CategoriaId.eq(c.getId())).list());
        }
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.holder_giros, collection, false);
        GirosViewHolder holder = new GirosViewHolder(categorias, servicios, layout, position);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return categorias.size() - 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public List<Integer> getSeleccionados() {
        return seleccionados;
    }

    public class GirosViewHolder implements CompoundButton.OnCheckedChangeListener {
        @BindView(R.id.tvTitulo)
        TextView tvTitulo;
        @BindView(R.id.linearGiros)
        LinearLayout linearGiros;

        int position;
        List<Servicio> serviciosMostrados;

        public GirosViewHolder() {
        }

        public GirosViewHolder(List<Categoria> categorias, List<List<Servicio>> servicios, View view, int position) {
            ButterKnife.bind(this, view);
            tvTitulo.setText(categorias.get(position).getNombre());
            this.position = position;
            this.serviciosMostrados = servicios.get(position);
            for (Servicio s : servicios.get(position)) {
                AppCompatCheckBox cb = new AppCompatCheckBox(mContext);
                cb.setText(s.getNombre());
                cb.setId((int) s.getId());
                cb.setOnCheckedChangeListener(this);
                linearGiros.addView(cb);
            }
        }


        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                Log.i(TAG, "se agrega " + buttonView.getId());
                seleccionados.add(buttonView.getId());
                Log.i(TAG, "Actual " + seleccionados.size());
            }
            else{
               Log.i(TAG, "Eliminado  "+seleccionados.remove(seleccionados.indexOf(buttonView.getId()))+" actual: "+seleccionados.size());
            }
        }
    }

}
