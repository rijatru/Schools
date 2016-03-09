package com.b1gdigital.schools.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.R;
import com.b1gdigital.schools.databinding.AddStudentFragmentBinding;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.Message;
import com.b1gdigital.schools.model.School;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class AddStudent extends Fragment {

    @Inject
    BusWorker busWorker;
    @Inject
    LogWorker logWorker;
    @Inject
    School school;
    @Inject
    Grade grade;
    @Inject
    Student student;

    //@Bind(R.id.name)
    //EditText name;
    //@Bind(R.id.grade)
    //EditText grade;

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

        binding.setStudent(student);
        binding.setHandlers(this);

        setTextWatchers();

        //View v = binding.getRoot();

        //ButterKnife.bind(this, v);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        inject();

        logWorker.log("Fragment: " + student.getName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    void setTextWatchers() {

        binding.name.addTextChangedListener(getTextChangedListener(binding.name));
        binding.grade.addTextChangedListener(getTextChangedListener(binding.grade));
    }

    TextWatcher getTextChangedListener(final View view) {

        return new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                getView(view, s);
            }
        };
    }

    public void getView(View view, CharSequence s) {

        switch (view.getId()) {

            case R.id.name:

                student.setName(s.toString());

                break;

            case R.id.grade:

                student.setGrade(s.toString());

                break;

            default:

                break;
        }
    }

    public void onClickButton(View view) {

        switch (view.getId()) {

            case R.id.button:

                grade.addStudent(student);

                busWorker.post(new Message("Student: " + student.getName() + " from " + student.getGrade()));

                student.reset();

                binding.name.setText("");
                binding.grade.setText("");

                break;

            default:

                break;
        }
    }
}
