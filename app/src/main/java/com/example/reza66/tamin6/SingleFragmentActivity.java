package com.example.reza66.tamin6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by www.NooR26.com on 1/12/2019.
 */

public abstract  class SingleFragmentActivity extends AppCompatActivity {
    public abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager =getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container)==null)
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, createFragment())
                    .commit();
    }


}
