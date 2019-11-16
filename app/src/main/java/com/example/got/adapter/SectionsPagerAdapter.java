package com.example.got.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.got.ui.main.HistoryTranslateFragment;
import com.example.got.ui.main.TranslateFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TranslateFragment();
            case 1:
                return new HistoryTranslateFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Page 1";
            case 1:
                return "Page 2";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}