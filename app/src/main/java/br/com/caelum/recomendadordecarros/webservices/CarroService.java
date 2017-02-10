package br.com.caelum.recomendadordecarros.webservices;

import br.com.caelum.recomendadordecarros.model.Carro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by matheusbrandino on 2/9/17.
 */

public interface CarroService {

    @POST("recomendacao")
    Call<Carro> fazRecomendacao(@Body Carro carro);
}
