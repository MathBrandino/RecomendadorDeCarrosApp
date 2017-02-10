package br.com.caelum.recomendadordecarros.event;

/**
 * Created by matheusbrandino on 2/9/17.
 */
public class CarroErroEvent {
    public  Throwable t;

    public CarroErroEvent(Throwable t) {
        this.t = t;
    }
}
