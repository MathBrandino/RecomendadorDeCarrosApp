package br.com.caelum.recomendadordecarros.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import br.com.caelum.recomendadordecarros.R;
import br.com.caelum.recomendadordecarros.application.CarroApplication;
import br.com.caelum.recomendadordecarros.event.CarroEnviadoEvent;
import br.com.caelum.recomendadordecarros.event.CarroErroEvent;
import br.com.caelum.recomendadordecarros.event.CarroSelecionadoEvent;
import br.com.caelum.recomendadordecarros.event.CarroSelecionadoLongEvent;
import br.com.caelum.recomendadordecarros.event.NovoEvent;
import br.com.caelum.recomendadordecarros.fragment.FormularioFragment;
import br.com.caelum.recomendadordecarros.fragment.ListaFragment;
import br.com.caelum.recomendadordecarros.model.Carro;
import br.com.caelum.recomendadordecarros.model.CarroDao;
import br.com.caelum.recomendadordecarros.webservices.WebClient;

public class MainActivity extends AppCompatActivity {

    @Inject
    WebClient client;

    @Inject
    CarroDao dao;
    private ProgressDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarroApplication.getInstance().getComponent().inject(this);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame, new ListaFragment());
            transaction.commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void irParaForm(NovoEvent event) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new FormularioFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Subscribe
    public void irParaFormCom(CarroSelecionadoEvent event) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FormularioFragment fragment = criaFormulario(event.carro);
        transaction.replace(R.id.main_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Subscribe
    public void mostraOpcoes(final CarroSelecionadoLongEvent event) {

        new AlertDialog.Builder(this)
                .setMessage("O que você deseja fazer ?")
                .setTitle(event.carro.getModelo())
                .setPositiveButton("Recomendar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fazRecomendacao();
                        client.recomenda(event.carro);
                    }
                })
                .setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.delete(event.carro);
                        recreate();
                    }
                })
                .setNeutralButton("Cancelar", null)
                .show();
    }

    @Subscribe
    public void recebeRespostaServer(CarroEnviadoEvent event) {
        tiraAlert();
    }

    @Subscribe
    public void recebeRespostaServer(CarroErroEvent event) {
        tiraAlert();
        new AlertDialog.Builder(this)
                .setTitle("Tivemos problemas no envio")
                .setMessage(event.t.getMessage())
                .show();
    }

    private void fazRecomendacao() {
        alert = ProgressDialog.show(this, "Fazendo Recomendação", "Mandando o carro para o servidor", false, false);
    }

    private void tiraAlert() {
        alert.dismiss();
    }

    @NonNull
    private FormularioFragment criaFormulario(Carro carro) {
        FormularioFragment fragment = new FormularioFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("carro", carro);
        fragment.setArguments(arguments);
        return fragment;
    }


}
