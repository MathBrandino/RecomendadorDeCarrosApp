package br.com.caelum.recomendadordecarros.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.caelum.recomendadordecarros.R;
import br.com.caelum.recomendadordecarros.application.CarroApplication;
import br.com.caelum.recomendadordecarros.model.Carro;
import br.com.caelum.recomendadordecarros.model.CarroDao;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by matheusbrandino on 2/8/17.
 */
public class FormularioFragment extends Fragment {

    @BindView(R.id.formulario_ano)
    EditText ano;

    @BindView(R.id.formulario_cor)
    EditText cor;

    @BindView(R.id.formulario_marca)
    EditText marca;

    @BindView(R.id.formulario_modelo)
    EditText modelo;

    @Inject
    CarroDao dao;

    private Carro carro = new Carro();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario, container, false);
        ButterKnife.bind(this, view);
        CarroApplication.getInstance().getComponent().inject(this);

        Bundle arguments = getArguments();
        if (arguments != null) {
            carro = (Carro) arguments.getSerializable("carro");
            preencheCampos(this.carro);
        }

        return view;
    }

    private void preencheCampos(Carro carro) {

        ano.setText(carro.getAno().toString());
        cor.setText(carro.getCor());
        marca.setText(carro.getMarca());
        modelo.setText(carro.getModelo());
    }


    @OnClick(R.id.formulario_salvar)
    public void salva() {
        if (camposPreenchidos()) {

            populaCampos();
            if (carro.getId() == null) {
                dao.insert(carro);
            } else {
                dao.update(carro);
            }
            getActivity().onBackPressed();
        }
    }


    private void populaCampos() {
        carro.setAno(Integer.valueOf(ano.getText().toString()));
        carro.setCor(cor.getText().toString());
        carro.setModelo(modelo.getText().toString());
        carro.setMarca(marca.getText().toString());
    }


    private boolean camposPreenchidos() {

        return !anoVazio() && !marcaVazio() && !modeloVazio();
    }

    private boolean marcaVazio() {
        return marca.getText().toString().trim().isEmpty();
    }

    private boolean modeloVazio() {
        return modelo.getText().toString().trim().isEmpty();
    }

    private boolean anoVazio() {
        return ano.getText().toString().trim().isEmpty();
    }


}
