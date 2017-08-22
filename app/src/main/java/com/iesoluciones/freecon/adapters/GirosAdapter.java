package com.iesoluciones.freecon.adapters;

import android.content.Context;
import android.graphics.Color;
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
import com.iesoluciones.freecon.intefaces.RegistroCallback;
import com.iesoluciones.freecon.models.Categoria;
import com.iesoluciones.freecon.models.CategoriaDao;
import com.iesoluciones.freecon.models.RegistroBody;
import com.iesoluciones.freecon.models.Servicio;
import com.iesoluciones.freecon.models.ServicioBody;
import com.iesoluciones.freecon.models.ServicioDao;

import java.util.ArrayList;
import java.util.Iterator;
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
    List<ServicioBody> seleccionados;
    List<Integer> idServicios = new ArrayList<>();
    RegistroCallback registroCallback;

    public GirosAdapter(Context context, RegistroCallback registroCallback) {
        mContext = context;
        categorias = App.getInstance().getDaoSession().getCategoriaDao().queryBuilder()
                .orderAsc(CategoriaDao.Properties.Id).list();
        servicios = new ArrayList<>();
        this.registroCallback = registroCallback;
        seleccionados = new ArrayList<>();
        if (registroCallback.getRegistro().getServicios() != null) {
            seleccionados = registroCallback.getRegistro().getServicios();
            for (ServicioBody s : seleccionados) {
                idServicios.add(s.getIdServicio());
            }
        }

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
        return categorias.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public List<ServicioBody> getSeleccionados() {
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
            for (int i = 0; i < servicios.get(position).size() - 1; i++) {
                Servicio s = servicios.get(position).get(i);
                AppCompatCheckBox cb = new AppCompatCheckBox(mContext);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(30, 30, 30, 30);
                cb.setTextColor(Color.BLACK);
                cb.setPadding(10, 10, 10, 10);
                cb.setLayoutParams(lp);
                cb.setTextSize(16f);
                cb.setText(s.getNombre());
                cb.setId(i);
                Log.i(TAG,"set ID "+i);
                if (idServicios.contains((int) s.getId())) {
                    cb.setChecked(true);
                }
                cb.setOnCheckedChangeListener(this);
                linearGiros.addView(cb);
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                seleccionados.add(new ServicioBody((int) categorias.get(position).getId(), (int) servicios.get(position).get(buttonView.getId()).getId()));
                idServicios.add((int) servicios.get(position).get(buttonView.getId()).getId());
            } else {
                Iterator<ServicioBody> iter = seleccionados.iterator();
                while (iter.hasNext()) {
                    ServicioBody s = iter.next();
                    if (s.getIdServicio()==(int)servicios.get(position).get(buttonView.getId()).getId())
                        iter.remove();
                }
                Log.i(TAG, "Actual " + seleccionados.size());
                idServicios.remove(idServicios.indexOf((int) servicios.get(position).get(buttonView.getId()).getId()));
            }
            registroCallback.getRegistro().setServicios(seleccionados);
        }
    }

}
