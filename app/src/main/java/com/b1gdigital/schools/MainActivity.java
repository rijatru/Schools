package com.b1gdigital.schools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.b1gdigital.schools.databinding.ActivityMainBinding;
import com.b1gdigital.schools.fragments.FragmentTest;
import com.b1gdigital.schools.models.Message;
import com.b1gdigital.schools.models.School;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.DbWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.NetWorker;
import com.b1gdigital.schools.workers.SharedPreferencesWorker;
import com.squareup.otto.Subscribe;

import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //@Inject
    //App app;
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

    @Bind(R.id.name)
    TextView name;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ButterKnife.bind(this);

        inject();

        binding.setSchool(school);
        binding.setHandlers(this);

        //getGrades(school);

        sharedPreferences.saveString(this, R.string.name, "Ricardo");

        String name = sharedPreferences.getString(this, R.string.name);

        logWorker.log(name);

        if (school.getName().length() > 0) {

            school.setName(school.getName());

        } else {

            school.setName(getString(R.string.button));
        }

        //binding.button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
//
        //        school.setName("ewewknewqojewqne");
        //    }
        //});
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

        logWorker.log("recievedMessage Activity 1: " + message.getMessage());

        logWorker.log("recievedMessage Activity 2: " + busWorker.getBus().equals(FragmentTest.busWorker.getBus()));

        logWorker.log("recievedMessage Activity 3: " + FragmentTest.school.getName());
    }

    public void onClickButton(View view) {

        switch (view.getId()) {

            case R.id.button:

                Random randomGenerator = new Random();

                //logWorker.log(getString(R.string.button));

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

        ((App) getApplication()).getGeneralComponent().inject(this);
        ((App) getApplication()).getSchoolComponent().inject(this);
    }

    void getGrades(School school) {

        Log.d("Dagger", "Grades: " + school.getGrades().size());
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
