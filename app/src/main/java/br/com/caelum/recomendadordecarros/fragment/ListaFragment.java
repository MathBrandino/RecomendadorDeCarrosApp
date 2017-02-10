package br.com.caelum.recomendadordecarros.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.recomendadordecarros.R;
import br.com.caelum.recomendadordecarros.adapter.CarroAdapter;
import br.com.caelum.recomendadordecarros.application.CarroApplication;
import br.com.caelum.recomendadordecarros.event.NovoEvent;
import br.com.caelum.recomendadordecarros.model.Carro;
import br.com.caelum.recomendadordecarros.model.CarroDao;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by matheusbrandino on 2/8/17.
 */

public class ListaFragment extends Fragment {

    @BindView(R.id.lista_carros)
    RecyclerView listaCarros;

    @Inject
    CarroDao dao;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        ButterKnife.bind(this, view);
        CarroApplication.getInstance().getComponent().inject(this);

        listaCarros.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        List<Carro> carros = dao.loadAll();
        listaCarros.setAdapter(new CarroAdapter(carros));
    }


    @OnClick(R.id.fab)
    public void novo() {
        EventBus.getDefault().post(new NovoEvent());
    }
}
