package br.com.caelum.recomendadordecarros.application;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import br.com.caelum.recomendadordecarros.component.CarroComponent;
import br.com.caelum.recomendadordecarros.component.DaggerCarroComponent;
import br.com.caelum.recomendadordecarros.model.DaoMaster;
import br.com.caelum.recomendadordecarros.model.DaoSession;

/**
 * Created by matheusbrandino on 2/8/17.
 */

public class CarroApplication extends Application {

    private static CarroApplication app;
    private CarroComponent component;

    public static CarroApplication getInstance() {
        return app;
    }

    private DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        Database db = new DaoMaster.DevOpenHelper(this, "Carros").getWritableDb();
        session = new DaoMaster(db).newSession();

        component = DaggerCarroComponent.builder().build();

    }

    public DaoSession getSession() {
        return session;
    }

    public CarroComponent getComponent() {
        return component;
    }
}
