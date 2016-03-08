package com.b1gdigital.schools.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.R;
import com.b1gdigital.schools.databinding.AddStudentFragmentBinding;
import com.b1gdigital.schools.model.Message;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddStudent extends Fragment {

    @Inject
    BusWorker busWorker;
    @Inject
    LogWorker logWorker;
    @Inject
    Student student;

    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.grade)
    EditText grade;

    AddStudentFragmentBinding binding;

    public AddStudent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        ((App) getActivity().getApplication()).getNetComponent().inject(this);
        ((App) getActivity().getApplication()).getSchoolComponent().inject(this);
    }

    @Subscribe
    public void recievedMessage(Message message) {

        logWorker.log("recievedMessage Fragment: " + message.getMessage());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.add_student_fragment, container, false);

        View v = binding.getRoot();

        ButterKnife.bind(this, v);

        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        inject();

        binding.setStudent(student);
        binding.setHandlers(this);

        logWorker.log("Fragment: " + student.getName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
