package com.b1gdigital.schools.model;

import android.content.Context;

import com.b1gdigital.schools.Utils;

/**
 * Created by Ricardo on 14/03/2016.
 */
public class Measurements {

    private int rootHeight;

    public int getScreenHeight() {

        return rootHeight;
    }

    public void setScreenHeight(Context context) {

        rootHeight = Utils.getScreenHeight(context);
    }
}
