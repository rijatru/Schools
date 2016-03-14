package com.b1gdigital.schools.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.Constants;
import com.b1gdigital.schools.R;
import com.b1gdigital.schools.adapter.FeedItemAnimator;
import com.b1gdigital.schools.adapter.StudentsRecyclerViewAdapter;
import com.b1gdigital.schools.databinding.ShowStudentsFragmentBinding;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.IntroRecyclerEvent;
import com.b1gdigital.schools.model.MessageEvent;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.model.StudentEvent;
import com.b1gdigital.schools.view.FeedContextMenuManager;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.NetWorker;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class ShowStudents extends Fragment {

    public static StudentsRecyclerViewAdapter adapter;
    private final int SPAN_COUNT = 2;
    private final int DATASET_COUNT = 15;
    private final String KEY_LAYOUT_MANAGER = "layoutManager";
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Inject
    BusWorker busWorker;
    @Inject
    LogWorker logWorker;
    @Inject
    NetWorker netWorker;
    @Inject
    Grade grade;

    ShowStudentsFragmentBinding binding;

    private boolean pendingIntroAnimation = true;

    public ShowStudents() {

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


        if (pendingIntroAnimation) {

            pendingIntroAnimation = false;

            updateItems(true);
        }
    }

    void inject() {

        ((App) getActivity().getApplication()).getNetComponent().inject(this);
        ((App) getActivity().getApplication()).getSchoolComponent().inject(this);
    }

    @Subscribe
    public void recievedMessage(MessageEvent event) {

        logWorker.log("recievedMessage Fragment: " + event.getMessage());
    }

    @Subscribe
    public void recievedMessage(StudentEvent event) {

        adapter.notifyItemInserted(0);

        binding.studentsRecyclerView.smoothScrollToPosition(0);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.show_students_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initRecyclerView(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType); // Save currently selected layout manager.

        super.onSaveInstanceState(savedInstanceState);
    }

    void initRecyclerView(Bundle savedInstanceState) {

        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {

            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }

        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        adapter = new StudentsRecyclerViewAdapter(getActivity());
        binding.studentsRecyclerView.setAdapter(adapter);
        binding.studentsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });

        binding.studentsRecyclerView.setItemAnimator(new FeedItemAnimator());
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {

        int scrollPosition = 0;

        if (binding.studentsRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) binding.studentsRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        binding.studentsRecyclerView.setLayoutManager(mLayoutManager);
        binding.studentsRecyclerView.scrollToPosition(scrollPosition);
    }

    public void updateItems(boolean animated) {

        insertStudents();

        if (animated) {

            logWorker.log("updateItems netWorker: " + netWorker.getScreenHeight());
            logWorker.log("updateItems grade: " + grade.getScreenHeight());

            binding.studentsRecyclerView.setY(netWorker.getScreenHeight());
            binding.studentsRecyclerView
                    .animate()
                    .y(0f)
                    .setDuration(Constants.RECYCLER_INTRO_ANIM_DURATION)
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);

                            busWorker.getBus().post(new IntroRecyclerEvent());
                        }
                    });
        }
    }

    void insertStudents() {

        if (grade.getStudents().size() == 0) {

            for (int i = 0; i < 100; i++) {

                Student student = new Student();
                student.setName("Name");
                student.setGrade("Grade");

                grade.addStudent(student);
            }
        }
    }

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
}
