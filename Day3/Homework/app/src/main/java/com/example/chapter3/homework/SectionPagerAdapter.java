package com.example.chapter3.homework;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SectionPagerAdapter extends FragmentStatePagerAdapter {

    private static final int PAGE_COUNT = 3;
    private static String[] tabs = {"hello 0", "hello 1", "hello 2", "hello 3"};

    public SectionPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount(){
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position){
        return PlaceholderFragment.newInstance(tabs[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
