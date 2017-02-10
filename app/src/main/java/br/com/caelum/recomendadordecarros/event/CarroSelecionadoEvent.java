package br.com.caelum.recomendadordecarros.event;

import br.com.caelum.recomendadordecarros.model.Carro;

/**
 * Created by matheusbrandino on 2/8/17.
 */
public class CarroSelecionadoEvent {
    public Carro carro;

    public CarroSelecionadoEvent(Carro carro) {
        this.carro = carro;
    }
}
