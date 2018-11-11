package com.example.sinukov.acatalog.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.adapter.NewsRecyclerAdapter;
import com.example.sinukov.acatalog.db.DatabaseManager;
import com.example.sinukov.acatalog.db.dao.DaoNews;
import com.example.sinukov.acatalog.model.NewsModel;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements NewsRecyclerAdapter.ClickListener {

    private int brandId;
    private String brandName;
    private List<NewsModel> recyclerNews = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        if (getArguments() != null) {
            brandId = getArguments().getInt("brandId", 0);
            brandName = getArguments().getString("brandName", "");
        }

        recyclerNews = getBrands("");
        RecyclerView recyclerViewNews = (RecyclerView) view.findViewById(R.id.listNews);

        LinearLayoutManager lLM = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewNews.setLayoutManager(lLM);
        NewsRecyclerAdapter newsRecyclerAdapter = new NewsRecyclerAdapter(getContext(), recyclerNews);
        newsRecyclerAdapter.setClickListener(this);
        registerForContextMenu(recyclerViewNews);
        recyclerViewNews.setAdapter(newsRecyclerAdapter);

        setUI(view);
        return view;
    }

    private List<NewsModel> getBrands(String search) {
        DaoNews daoNews;
        try {
            daoNews = DatabaseManager.getInstance().getHelper().getNewsDao();
            QueryBuilder<NewsModel, Integer> queryBuilderGoal = daoNews.queryBuilder();

            if (!search.isEmpty()) {
                queryBuilderGoal.where().like("title", "%" + search + "%");
            }
            queryBuilderGoal.where().eq("brand_id", brandId).query();
            queryBuilderGoal.orderBy("date_created", true);

            recyclerNews = queryBuilderGoal.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recyclerNews;
    }

    private void setUI(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddNewsFragment nextFrag = new AddNewsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("brandId", brandId);
                bundle.putString("brandName", brandName);
                nextFrag.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, "TAG_FRAGMENT_ADD_NEWS")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {
        int currentPosition = position;
    }
}