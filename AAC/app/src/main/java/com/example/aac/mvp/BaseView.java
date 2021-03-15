package com.example.aac.mvp;


import com.example.aac.mvp.BasePresenter;

public interface BaseView<T extends BasePresenter> {


    default void showNoNet() {

    }

    default void showEmpty() {

    }

    default void showLoadError() {

    }

    default void showLoading() {

    }

    default void showSuccess() {

    }

    default void showProgressDialog() {

    }


    default void closeProgressDialog() {

    }
}
