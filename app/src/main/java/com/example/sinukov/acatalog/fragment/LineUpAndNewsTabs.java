package com.example.sinukov.acatalog.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.adapter.TabAdapter;

public class LineUpAndNewsTabs extends Fragment {

    private int brandId;
    private String brandName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_up, container, false);

        if (getArguments() != null) {
            brandId = getArguments().getInt("brandId", 0);
            brandName = getArguments().getString("brandName", "");
        }

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Models"));
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);

        TabAdapter tabAdapter = new TabAdapter(getFragmentManager(), 2, brandId, brandName);

        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }
}