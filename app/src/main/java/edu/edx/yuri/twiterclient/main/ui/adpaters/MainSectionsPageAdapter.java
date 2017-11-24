package edu.edx.yuri.twiterclient.main.ui.adpaters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by yuri_ on 24/11/2017.
 */

public class MainSectionsPageAdapter extends FragmentPagerAdapter{

    private String[] titles;
    private Fragment[] fragments;

    public MainSectionsPageAdapter(FragmentManager fm, String[] titles, Fragment[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return this.fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return super.getPageTitle(position);
        return this.titles[position];
    }

    @Override
    public int getCount() {
        return this.fragments.length;
    }
}
