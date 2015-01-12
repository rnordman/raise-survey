package com.onclick.raisesurvey;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        if (savedInstanceState == null) {
            fm.beginTransaction()
                    .add(R.id.container, new ListCardsFragment())
                    .commit();
        }

    }

}

