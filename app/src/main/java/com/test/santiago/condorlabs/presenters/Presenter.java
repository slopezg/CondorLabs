package com.test.santiago.condorlabs.presenters;

import com.test.santiago.condorlabs.view.interfaces.BaseView;

/**
 * Created by Santiago on 10/25/17.
 */

public abstract class Presenter<T extends BaseView> {

    public abstract void init();

    public void addView(T view) {
        this.view = view;
    }

    T view;



}