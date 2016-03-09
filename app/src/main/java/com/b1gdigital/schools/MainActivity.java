package com.b1gdigital.schools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.b1gdigital.schools.databinding.ActivityMainBinding;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.Message;
import com.b1gdigital.schools.model.School;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.DbWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.NetWorker;
import com.b1gdigital.schools.workers.SharedPreferencesWorker;
import com.squareup.otto.Subscribe;

import java.util.Random;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    BusWorker busWorker;
    @Inject
    SharedPreferencesWorker sharedPreferences;
    @Inject
    NetWorker netWorker;
    @Inject
    DbWorker dbWorker;
    @Inject
    LogWorker logWorker;
    @Inject
    School school;
    @Inject
    Grade grade;
    @Inject
    Student student;

    //@Bind(R.id.name)
    //TextView name;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //ButterKnife.bind(this);

        inject();

        binding.setSchool(school);
        binding.setHandlers(this);

        sharedPreferences.saveString(this, R.string.name, "Ricardo");

        String name = sharedPreferences.getString(this, R.string.name);

        logWorker.log(name);

        if (school.getName().length() > 0) {

            school.setName(school.getName());

        } else {

            school.setName(getString(R.string.button));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        busWorker.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        busWorker.unRegister(this);
    }

    @Subscribe
    public void recievedMessage(Message message) {

        logWorker.log("recievedMessage Activity: " + message.getMessage());
    }

    public void onClickButton(View view) {

        switch (view.getId()) {

            case R.id.button:

                Random randomGenerator = new Random();

                school.setName(String.valueOf(randomGenerator.nextInt(100)));

                busWorker.post(new Message(school.getName()));

                break;

            case R.id.name:

                //logWorker.log(getString(R.string.name));

                break;

            default:

                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (netWorker != null) {

            netWorker.cancelAll();
        }
    }

    void inject() {

        ((App) getApplication()).getNetComponent().inject(this);
        ((App) getApplication()).getSchoolComponent().inject(this);
    }

    void getUrl(NetWorker netWorker) {

        Log.d("Dagger", "getUrl: " + Constants.FIXER_URL);

        netWorker.get(this, Constants.FIXER_URL, new NetWorker.Listener() {
            @Override
            public void onDataRetrieved(String result) {

                Log.d("Dagger", "Result: " + result);
            }
        });
    }
}
