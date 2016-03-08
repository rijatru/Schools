package com.b1gdigital.schools.workers;

import android.util.Log;

import com.b1gdigital.schools.models.Message;
import com.squareup.otto.Bus;

import javax.inject.Inject;

public class BusWorker {

    Bus bus;

    @Inject
    public BusWorker() {

    }

    public Bus getBus() {

        return bus;
    }

    public void register(Object object) {

        if (bus == null) {

            Log.d("Dagger", "register bus == null");

            bus = new Bus();
        }

        bus.register(object);
    }

    public void unRegister(Object object) {

        if (bus == null) {

            Log.d("Dagger", "unRegister bus == null");

            bus = new Bus();
        }

        bus.unregister(object);
    }

    public void post(Message event) {

        bus.post(event);
    }
}
