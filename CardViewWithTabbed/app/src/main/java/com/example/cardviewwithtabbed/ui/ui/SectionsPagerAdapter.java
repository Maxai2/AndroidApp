package com.example.cardviewwithtabbed.ui.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cardviewwithtabbed.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.main, R.string.card1, R.string.card2Landscape, R.string.card2Portret, R.string.card3, R.string.card4, R.string.card5};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new mainFragment();
            case 1:
                return new card1Fragment();
            case 2:
                return new card2lFragment();
            case 3:
                return new card2pFragment();
            case 4:
                return new card3Fragment();
            case 5:
                return new card4Fragment();
            case 6:
                return new card5Fragment();
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
        // Show 2 total pages.
        return TAB_TITLES.length; // 2
    }
}