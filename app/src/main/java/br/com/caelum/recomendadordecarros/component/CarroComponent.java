package br.com.caelum.recomendadordecarros.component;

import javax.inject.Singleton;

import br.com.caelum.recomendadordecarros.activity.MainActivity;
import br.com.caelum.recomendadordecarros.component.module.Modulo;
import br.com.caelum.recomendadordecarros.component.module.WebModule;
import br.com.caelum.recomendadordecarros.fragment.FormularioFragment;
import br.com.caelum.recomendadordecarros.fragment.ListaFragment;
import dagger.Component;

/**
 * Created by matheusbrandino on 2/8/17.
 */

@Component(modules = {Modulo.class, WebModule.class})
@Singleton
public interface CarroComponent {

    void inject(ListaFragment listaFragment);

    void inject(FormularioFragment formularioFragment);

    void inject(MainActivity mainActivity);
}
