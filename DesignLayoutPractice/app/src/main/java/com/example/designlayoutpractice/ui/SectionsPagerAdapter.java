package com.example.designlayoutpractice.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.designlayoutpractice.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.grid1, R.string.grid2, R.string.grid3, R.string.grid4, R.string.grid5, R.string.grid6, R.string.linear1, R.string.linear2, R.string.relative1, R.string.relative2, R.string.relative3, R.string.relative4, R.string.relative5, R.string.relative6};
    private static final int[] TAB_TITLES = new int[]{R.string.grid1, R.string.grid2, R.string.grid3, R.string.grid4, R.string.grid5, R.string.grid6, R.string.grid7, R.string.linear1};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new grid1Fragment();
            case 1:
                return new grid2Fragment();
            case 2:
                return new grid3Fragment();
            case 3:
                return new grid4Fragment();
            case 4:
                return new grid5Fragment();
            case 5:
                return new grid6Fragment();
            case 6:
                return new grid7Fragment();
            case 7:
                return new linier1Fragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}