package com.example.got.ui;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.got.R;
import com.example.got.adapter.SectionsPagerAdapter;
import com.example.got.adapter.SignInAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    SignInAdapter signInAdapter ;
    private static final int RC_SIGN_IN = 42;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        signInAdapter = new SignInAdapter();
        if(!signInAdapter.checkIfSignedIn()){
            startActivityForResult(signInAdapter.getSignInIntent(), RC_SIGN_IN);
        }
    }



}