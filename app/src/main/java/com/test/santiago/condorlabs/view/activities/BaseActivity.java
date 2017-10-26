package com.test.santiago.condorlabs.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.test.santiago.condorlabs.presenters.Presenter;

/**
 * Created by Santiago on 10/25/17.
 */

public class BaseActivity<T extends Presenter> extends AppCompatActivity {

    T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}