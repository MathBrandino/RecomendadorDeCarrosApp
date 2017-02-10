package br.com.caelum.recomendadordecarros.event;

import br.com.caelum.recomendadordecarros.model.Carro;

/**
 * Created by matheusbrandino on 2/9/17.
 */
public class CarroEnviadoEvent {

    public  Carro carro;

    public CarroEnviadoEvent(Carro carro) {
        this.carro = carro;
    }
}
