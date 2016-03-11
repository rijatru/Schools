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

package com.b1gdigital.schools.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.Constants;
import com.b1gdigital.schools.R;
import com.b1gdigital.schools.databinding.ShowStudentRowBinding;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.RecyclerCellEvent;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.view.LoadingFeedItemView;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.LogWorker;

import javax.inject.Inject;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class StudentsRecyclerViewAdapter extends RecyclerView.Adapter<StudentsRecyclerViewAdapter.BindingHolder> {

    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";
    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;
    @Inject
    LogWorker logWorker;
    @Inject
    BusWorker busWorker;
    @Inject
    Grade grade;

    Activity activity;
    private int itemsCount = 0;
    private int lastAnimatedPosition = -1;
    private int avatarSize;
    private boolean animationsLocked = false;
    private boolean delayEnterAnimation = true;

    private boolean showLoadingView = false;

    public StudentsRecyclerViewAdapter(Activity activity) {

        this.activity = activity;

        inject();
    }

    void inject() {

        ((App) activity.getApplication()).getNetComponent().inject(this);
        ((App) activity.getApplication()).getSchoolComponent().inject(this);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ShowStudentRowBinding binding = ShowStudentRowBinding.inflate(inflater, parent, false);

        return new BindingHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final BindingHolder holder, int position) {

        final Student student = grade.getStudent(position);

        holder.binding.setStudent(student);

        holder.binding.setClick(new StudentClickHandler() {

            @Override
            public void onClick(View view) {

                onClickButton(view, holder);
            }
        });

        holder.getBinding().executePendingBindings();

        if (getItemViewType(position) == VIEW_TYPE_LOADER) {

            bindLoadingFeedItem((LoadingCellFeedViewHolder) holder);
        }
    }

    private void bindLoadingFeedItem(final LoadingCellFeedViewHolder holder) {
        holder.loadingFeedItemView.setOnLoadingFinishedListener(new LoadingFeedItemView.OnLoadingFinishedListener() {
            @Override
            public void onLoadingFinished() {
                showLoadingView = false;
                notifyItemChanged(0);
            }
        });
        holder.loadingFeedItemView.startLoading();
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoadingView && position == 0) {
            return VIEW_TYPE_LOADER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    public void onClickButton(View view, BindingHolder holder) {

        switch (view.getId()) {

            case R.id.btnLike:

                int adapterPosition = holder.getAdapterPosition();
                int likes = holder.binding.getStudent().getLikes() + 1;
                holder.binding.getStudent().setLikes(likes);
                holder.binding.getStudent().setIsLiked(true);
                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);

                busWorker.getBus().post(new RecyclerCellEvent(Constants.LIKE));

                logWorker.log("Click on btnLike " + holder.binding.getStudent().getName() + " " + holder.binding.getStudent().getGrade());

                break;

            case R.id.btnComments:

                logWorker.log("Click on btnComments " + holder.binding.getStudent().getName() + " " + holder.binding.getStudent().getGrade());

                break;

            case R.id.btnMore:

                busWorker.getBus().post(new RecyclerCellEvent(Constants.MORE, holder.getBinding().btnMore, holder.getAdapterPosition()));

                logWorker.log("Click on btnMore " + holder.binding.getStudent().getName() + " " + holder.binding.getStudent().getGrade());

                break;

            case R.id.ivFeedCenter:

                int adapterPos = holder.getAdapterPosition();
                int lik = holder.binding.getStudent().getLikes() + 1;
                holder.binding.getStudent().setLikes(lik);
                holder.binding.getStudent().setIsLiked(true);
                notifyItemChanged(adapterPos, ACTION_LIKE_IMAGE_CLICKED);

                busWorker.getBus().post(new RecyclerCellEvent(Constants.LIKE));

                break;

            default:

                break;
        }
    }

    @Override
    public void onViewDetachedFromWindow(BindingHolder holder) {

        holder.clearAnimation();
    }

    @Override
    public int getItemCount() {

        return grade.getStudents().size();
    }

    public void setAnimationsLocked(boolean animationsLocked) {
        this.animationsLocked = animationsLocked;
    }

    public interface StudentClickHandler {

        void onClick(View view);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        ShowStudentRowBinding binding;

        public BindingHolder(View v) {

            super(v);

            binding = DataBindingUtil.bind(v);
        }

        public ShowStudentRowBinding getBinding() {

            return binding;
        }

        public void clearAnimation() {

            binding.getRoot().clearAnimation();
        }
    }

    public static class LoadingCellFeedViewHolder extends BindingHolder {

        LoadingFeedItemView loadingFeedItemView;

        public LoadingCellFeedViewHolder(LoadingFeedItemView view) {
            super(view);
            this.loadingFeedItemView = view;
        }
    }
}
