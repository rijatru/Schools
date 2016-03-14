package com.b1gdigital.schools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.b1gdigital.schools.databinding.ActivityMainBinding;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.Grade2;
import com.b1gdigital.schools.model.IntroRecyclerEvent;
import com.b1gdigital.schools.model.MessageEvent;
import com.b1gdigital.schools.model.RecyclerCellEvent;
import com.b1gdigital.schools.model.School;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.view.FeedContextMenu;
import com.b1gdigital.schools.view.FeedContextMenuManager;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.DbWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.NetWorker;
import com.b1gdigital.schools.workers.SharedPreferencesWorker;
import com.squareup.otto.Subscribe;

import java.util.Random;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final int ANIM_DURATION_FAB = 400;
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
    Grade2 netWorker2;
    @Inject
    Student student;

    ActivityMainBinding binding;

    private boolean pendingIntroAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        inject();

        netWorker.setScreenHeight(this);
        grade.setScreenHeight(this);
        netWorker2.setScreenHeight(this);

        grade.setName("Name");

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

        if (savedInstanceState == null) {

            pendingIntroAnimation = true;

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

    @Override
    protected void onStop() {
        super.onStop();

        if (netWorker != null) {

            netWorker.cancelAll();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Subscribe
    public void recievedMessage(IntroRecyclerEvent event) {

        if (pendingIntroAnimation) {

            pendingIntroAnimation = false;

            startIntroAnimation();
        }
    }

    private void startIntroAnimation() {

        logWorker.log("startIntroAnimation");

        binding.btnCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        binding.btnCreate.setVisibility(View.VISIBLE);

        binding.btnCreate.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.5f))
                .setStartDelay(0)
                .setDuration(ANIM_DURATION_FAB)
                .start();
    }

    @Subscribe
    public void recievedMessage(MessageEvent event) {

        logWorker.log("recievedMessage Activity: " + event.getMessage());
    }

    @Subscribe
    public void recievedMessage(RecyclerCellEvent event) {

        if (event.getString().equals(Constants.LIKE)) {

            showLikedSnackbar();

        } else if (event.getString().equals(Constants.MORE)) {

            FeedContextMenuManager.getInstance().toggleContextMenuFromView(event.getView(), event.getPosition(), new FeedContextMenu.OnFeedContextMenuItemClickListener() {

                @Override
                public void onReportClick(int feedItem) {

                }

                @Override
                public void onSharePhotoClick(int feedItem) {

                }

                @Override
                public void onCopyShareUrlClick(int feedItem) {

                }

                @Override
                public void onCancelClick(int feedItem) {

                }
            });
        }
    }

    public void showLikedSnackbar() {

        Snackbar.make(binding.content, "Liked!", Snackbar.LENGTH_SHORT).show();
    }

    public void onClickButton(View view) {

        switch (view.getId()) {

            case R.id.button:

                Random randomGenerator = new Random();

                school.setName(String.valueOf(randomGenerator.nextInt(100)));

                busWorker.post(new MessageEvent(school.getName()));

                break;

            case R.id.name:

                //logWorker.log(getString(R.string.name));

                break;

            default:

                break;
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
