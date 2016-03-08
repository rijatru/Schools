package com.b1gdigital.schools.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.R;
import com.b1gdigital.schools.models.Message;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class FragmentTest extends Fragment {

    @Inject
    BusWorker busWorker;
    @Inject
    LogWorker logWorker;

    public FragmentTest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject();
    }

    @Override
    public void onPause() {
        super.onPause();

        busWorker.unRegister(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        busWorker.register(this);
    }

    void inject() {

        ((App) getActivity().getApplication()).getGeneralComponent().inject(this);
    }

    @Subscribe
    public void recievedMessage(Message message) {

        logWorker.log("recievedMessage Fragment: " + message.getMessage());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
