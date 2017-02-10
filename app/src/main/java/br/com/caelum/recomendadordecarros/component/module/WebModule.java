package br.com.caelum.recomendadordecarros.component.module;

import javax.inject.Singleton;

import br.com.caelum.recomendadordecarros.webservices.CarroService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matheusbrandino on 2/9/17.
 */

@Module
public class WebModule {

    private String url = "http://192.168.3.228:8080/";

    @Provides
    @Singleton
    public Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Provides
    public CarroService getService(Retrofit retrofit) {
        return retrofit.create(CarroService.class);
    }

}
