package com.example.sinukov.acatalog.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sinukov.acatalog.fragment.CarFragment;
import com.example.sinukov.acatalog.fragment.NewsFragment;

public class TabAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;
    private int brandId;
    private String brandName;

    public TabAdapter(FragmentManager fragmentManager, int numberOfTabs, int brandId, String brandName) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
        this.brandId = brandId;
        this.brandName = brandName;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("brandId", brandId);
        bundle.putString("brandName", brandName);

        switch (position) {
            case 0:
                CarFragment tab1 = new CarFragment();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                NewsFragment tab2 = new NewsFragment();
                tab2.setArguments(bundle);
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}