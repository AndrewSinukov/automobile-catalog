package com.example.sinukov.acatalog.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.adapter.LineUpRecyclerAdapter;
import com.example.sinukov.acatalog.db.DatabaseManager;
import com.example.sinukov.acatalog.db.dao.DaoLineUp;
import com.example.sinukov.acatalog.model.LineUpModel;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarFragment extends Fragment implements LineUpRecyclerAdapter.ClickListener {

    private List<LineUpModel> recyclerLineUp = new ArrayList<>();
    private int brandId;
    private String brandName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        DatabaseManager.getInstance().init(getActivity().getApplicationContext());
        if (getArguments() != null) {
            brandId = getArguments().getInt("brandId", 0);
            brandName = getArguments().getString("brandName", "");
        }

        setUI(view);
        recyclerLineUp = getLineUp("");

        if (recyclerLineUp != null) {
            RecyclerView recyclerViewLineUp = (RecyclerView) view.findViewById(R.id.brandsAuto);

            GridLayoutManager lLM = new GridLayoutManager(getActivity().getApplicationContext(), 3);
            recyclerViewLineUp.setLayoutManager(lLM);
            LineUpRecyclerAdapter lineUpRecyclerAdapter = new LineUpRecyclerAdapter(getContext(), recyclerLineUp);
            lineUpRecyclerAdapter.setClickListener(this);
            recyclerViewLineUp.setAdapter(lineUpRecyclerAdapter);
        }

        return view;
    }

    public List<LineUpModel> getLineUp(String searchTitle) {
        DaoLineUp daoLineUp;
        try {
            daoLineUp = DatabaseManager.getInstance().getHelper().getLineUpDao();
            QueryBuilder<LineUpModel, Integer> queryBuilderLineUp = daoLineUp.queryBuilder();

            queryBuilderLineUp.where().eq("brand_id", brandId).query();
            queryBuilderLineUp.orderBy("date_created", true);

            recyclerLineUp = queryBuilderLineUp.query();
        } catch (SQLException e) {
            Log.d("brandIdTr", "e " + e);
            e.printStackTrace();
        }

        return recyclerLineUp;
    }


    private void setUI(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddCarFragment nextFrag = new AddCarFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("brandId", brandId);
                bundle.putString("brandName", brandName);
                nextFrag.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, "TAG_FRAGMENT_ADD_CAR")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {

        ItemCarFragment currentCarFrag = new ItemCarFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.container, currentCarFrag, "TAG_FRAGMENT_LINE_UP")
                .addToBackStack(null)
                .commit();
    }
}