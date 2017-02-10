package br.com.caelum.recomendadordecarros.webservices;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import br.com.caelum.recomendadordecarros.event.CarroEnviadoEvent;
import br.com.caelum.recomendadordecarros.event.CarroErroEvent;
import br.com.caelum.recomendadordecarros.model.Carro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by matheusbrandino on 2/9/17.
 */

public class WebClient {

    CarroService carroService;

    @Inject
    WebClient(CarroService carroService) {
        this.carroService = carroService;
    }

    public void recomenda(Carro carro) {

        Call<Carro> call = carroService.fazRecomendacao(carro);

        call.enqueue(new Callback<Carro>() {
            @Override
            public void onResponse(Call<Carro> call, Response<Carro> response) {
                EventBus.getDefault().post(new CarroEnviadoEvent(response.body()));
            }

            @Override
            public void onFailure(Call<Carro> call, Throwable t) {
                EventBus.getDefault().post(new CarroErroEvent(t));
            }
        });

    }

}
