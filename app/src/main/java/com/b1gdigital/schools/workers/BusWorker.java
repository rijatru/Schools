package com.b1gdigital.schools.workers;

import android.util.Log;

import com.b1gdigital.schools.models.Message;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by Ricardo on 29/02/2016.
 */
public class BusWorker {

    Bus bus;

    @Inject
    public BusWorker() {

        bus = new Bus();
    }

    public void register(Object object) {

        Log.d("Dagger", "Registering");

        bus.register(object);
    }

    public void unRegister(Object object) {

        Log.d("Dagger", "Registering");

        bus.unregister(object);
    }

    public void post(Message event) {

        bus.post(event);
    }
}
