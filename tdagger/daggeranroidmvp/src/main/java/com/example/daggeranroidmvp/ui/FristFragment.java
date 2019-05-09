package com.example.daggeranroidmvp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daggeranroidmvp.R;
import com.example.daggeranroidmvp.data.Student;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
public class FristFragment extends Fragment implements FristFragmentContract.View {
    @Inject
    Context mContext;
    @Inject
    Student mStudent;
    @Inject
    FristFragmentContract.Presenter mPresenter;
    private TextView mTv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTv = view.findViewById(R.id.tv_content);
        mPresenter.showTasks();
    }

    @Override
    public void onAttach(Context context) {
        Log.e("xwpeng16", "onAttach");
        AndroidSupportInjection.inject(this);
        mPresenter.takeView(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("xwpeng16", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        mPresenter.dropView();
        super.onDestroy();
    }

    @Override
    public void showTasks(String tasksString) {
        mTv.setText(mContext.toString() + "\n"
                + mStudent.toString() + "\n"
                + tasksString + "\n"
        );
    }
}
