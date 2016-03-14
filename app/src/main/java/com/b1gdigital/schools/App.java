package com.b1gdigital.schools;

import android.app.Application;
import android.util.Log;

import com.b1gdigital.schools.di.components.DaggerNetComponent;
import com.b1gdigital.schools.di.components.DaggerSchoolComponent;
import com.b1gdigital.schools.di.components.NetComponent;
import com.b1gdigital.schools.di.components.SchoolComponent;
import com.b1gdigital.schools.model.MessageEvent;
import com.b1gdigital.schools.workers.BusWorker;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class App extends Application {

    @Inject
    BusWorker busWorker;
    private NetComponent netComponent;
    private SchoolComponent schoolComponent;

    @Inject
    public App() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.create();
        netComponent.inject(this);

        schoolComponent = DaggerSchoolComponent.create();
        schoolComponent.inject(this);

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
}
