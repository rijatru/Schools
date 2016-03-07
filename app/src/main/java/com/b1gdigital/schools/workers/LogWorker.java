package com.b1gdigital.schools.workers;

import android.util.Log;

import com.b1gdigital.schools.Constants;

import javax.inject.Inject;

public class LogWorker {

    @Inject
    public LogWorker() {

    }

    public void log(String message) {

        Log.d(Constants.LogTag, message);
    }
}
