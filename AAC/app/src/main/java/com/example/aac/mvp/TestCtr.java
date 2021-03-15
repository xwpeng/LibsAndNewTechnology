package com.example.aac.mvp;

import androidx.lifecycle.LifecycleOwner;

import com.example.aac.mvp.BasePresenter;
import com.example.aac.mvp.BaseView;

public interface TestCtr {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void netData2(LifecycleOwner owner);
        void netData(LifecycleOwner owner);
        void netData3(LifecycleOwner owner);
        void stopAnim();

    }

}
