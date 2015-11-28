package com.krsk.actionobservertoy;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            final ListFragment fragment = ListFragment.newInstance();
            final FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, fragment);
            transaction.commit();
        }
    }
}
