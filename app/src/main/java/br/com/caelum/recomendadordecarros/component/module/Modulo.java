package br.com.caelum.recomendadordecarros.component.module;

import javax.inject.Singleton;

import br.com.caelum.recomendadordecarros.application.CarroApplication;
import br.com.caelum.recomendadordecarros.model.CarroDao;
import dagger.Module;
import dagger.Provides;

/**
 * Created by matheusbrandino on 2/8/17.
 */

@Module
@Singleton
public class Modulo {

    @Provides
    public CarroApplication getCarroApplication() {
        return CarroApplication.getInstance();
    }

    @Provides
    public CarroDao getCarroDao(CarroApplication application) {
        return application.getSession().getCarroDao();
    }
}
