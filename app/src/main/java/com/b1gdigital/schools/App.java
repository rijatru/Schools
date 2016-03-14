package com.b1gdigital.schools;

import android.app.Application;
import android.util.Log;

import com.b1gdigital.schools.di.components.AppComponent;
import com.b1gdigital.schools.di.components.DaggerAppComponent;
import com.b1gdigital.schools.model.MessageEvent;
import com.b1gdigital.schools.workers.BusWorker;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class App extends Application {

    @Inject
    BusWorker busWorker;

    private AppComponent appComponent;

    @Inject
    public App() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
        appComponent.inject(this);

        busWorker.register(this);
    }

    @Subscribe
    public void recievedMessage(MessageEvent event) {

        Log.d("Dagger", "recievedMessage App: " + event.getMessage());
    }

    public AppComponent getAppComponent() {

        return appComponent;
    }
}
