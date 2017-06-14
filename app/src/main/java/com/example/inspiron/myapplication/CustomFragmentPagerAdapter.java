package com.example.inspiron.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3"};
    private Context context;

    public CustomFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        //return Fragment1.newInstance(position + 1);
        switch(position) {
            case 0:
                return Fragment1.newInstance(position + 1);
            case 1:
                return Fragment1.newInstance(position + 1);
            case 2:
                return Fragment1.newInstance(position + 1);
            case 3:
                return Fragment1.newInstance(position + 1);
            case 4:
                return Fragment1.newInstance(position + 1);
            default:
                return new Fragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
