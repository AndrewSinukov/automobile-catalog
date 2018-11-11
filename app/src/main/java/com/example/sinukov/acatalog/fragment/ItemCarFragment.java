package com.example.sinukov.acatalog.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.adapter.ItemImageCarAdapter;

public class ItemCarFragment extends Fragment {

    private ListView lvModif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_car, container, false);

        String[] modif = {"1.9D(150)", "2.4D(210)", "1.8", "1.9", "2.0D(136)", "2.4D(200) AWD",
                "1.9D(120)", "2.0D(170)", "1.7", "3.2 AWD", "3.2", "3.2 AWD AT",
                "2.4D(200) AT", "2.4D(210) AWD", "2.2 AT", "2.2", "3.3", "1.9D(150) AT"};

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pagerSlider);
        lvModif = (ListView) view.findViewById(R.id.lvModif);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, modif);
        lvModif.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvModif.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onClickTe", "view " + lvModif.getCheckedItemPosition());
                // To do open modification
                view.getId();
            }
        });

        lvModif.setAdapter(adapter);
        ItemImageCarAdapter itemImageCarAdapter = new ItemImageCarAdapter(getContext());
        viewPager.setAdapter(itemImageCarAdapter);

        return view;
    }
}