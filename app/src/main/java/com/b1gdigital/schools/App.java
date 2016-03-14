package com.b1gdigital.schools;

import android.app.Application;
import android.util.Log;

import com.b1gdigital.schools.di.components.DaggerNetComponent;
import com.b1gdigital.schools.di.components.DaggerNetComponent2;
import com.b1gdigital.schools.di.components.DaggerSchoolComponent;
import com.b1gdigital.schools.di.components.NetComponent;
import com.b1gdigital.schools.di.components.NetComponent2;
import com.b1gdigital.schools.di.components.SchoolComponent;
import com.b1gdigital.schools.model.MessageEvent;
import com.b1gdigital.schools.workers.BusWorker;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class App extends Application {

    @Inject
    BusWorker busWorker;
    private NetComponent netComponent;
    private NetComponent2 netComponent2;
    private SchoolComponent schoolComponent;

    @Inject
    public App() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        schoolComponent = DaggerSchoolComponent.create();
        schoolComponent.inject(this);

        netComponent = DaggerNetComponent.create();
        netComponent.inject(this);

        netComponent2 = DaggerNetComponent2.create();
        netComponent2.inject(this);

        busWorker.register(this);
    }

    @Subscribe
    public void recievedMessage(MessageEvent event) {

        Log.d("Dagger", "recievedMessage App: " + event.getMessage());
    }

    public SchoolComponent getSchoolComponent() {

        return schoolComponent;
    }

    public NetComponent getNetComponent() {

        return netComponent;
    }

    public NetComponent2 getNetComponent2() {

        return netComponent2;
    }
}
