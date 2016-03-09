/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.b1gdigital.schools.recycler_views;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.BR;
import com.b1gdigital.schools.R;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.Student;

import javax.inject.Inject;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class StudentsRecyclerViewAdapter extends RecyclerView.Adapter<StudentsRecyclerViewAdapter.BindingHolder> {

    @Inject
    Grade grade;
    StudentsRecyclerViewAdapter adapter;
    RecyclerViewAdapterListener listener;
    Activity activity;

    public StudentsRecyclerViewAdapter(Activity activity, RecyclerViewAdapterListener listener) {

        this.activity = activity;
        this.listener = listener;

        inject();
    }

    void inject() {

        ((App) activity.getApplication()).getSchoolComponent().inject(this);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_student_row, parent, false);

        BindingHolder holder = new BindingHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {

        final Student student = grade.getStudent(position);

        holder.getBinding().setVariable(BR.student, student);

        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {

        return grade.getStudents().size();
    }

    public interface RecyclerViewAdapterListener {

        void onListItemClicked(Student student);

        void onEmptyAddressesList();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public BindingHolder(View rowView) {

            super(rowView);

            binding = DataBindingUtil.bind(rowView);
        }

        public ViewDataBinding getBinding() {

            return binding;
        }
    }
}
