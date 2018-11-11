package com.example.sinukov.acatalog.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.sinukov.acatalog.Constants;
import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.adapter.BrandsRecyclerAdapter;
import com.example.sinukov.acatalog.async_task.ParseBrands;
import com.example.sinukov.acatalog.db.DatabaseManager;
import com.example.sinukov.acatalog.db.dao.DaoBrand;
import com.example.sinukov.acatalog.model.BrandModel;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements BrandsRecyclerAdapter.ClickListener {

    //    protected SearchView searchView;
    private List<BrandModel> recyclerBrandsAuto = new ArrayList<>();
    private int currentPosition;
    private BrandsRecyclerAdapter brandsRecyclerAdapter;
    private GridLayoutManager lLM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brand, container, false);
        DatabaseManager.getInstance().init(getActivity().getApplicationContext());

        setUI(view);
        recyclerBrandsAuto = getBrands("");
        RecyclerView recyclerViewBrands = (RecyclerView) view.findViewById(R.id.brandsAuto);

        if (recyclerBrandsAuto.size() != 0) {
            lLM = new GridLayoutManager(getActivity().getApplicationContext(), 3);
            recyclerViewBrands.setLayoutManager(lLM);
            brandsRecyclerAdapter = new BrandsRecyclerAdapter(getContext(), recyclerBrandsAuto);
            brandsRecyclerAdapter.setClickListener(this);
            registerForContextMenu(recyclerViewBrands);
            recyclerViewBrands.setAdapter(brandsRecyclerAdapter);
        }
        return view;
    }

    public List<BrandModel> getBrands(String searchTitle) {
        DaoBrand daoArticle;
        try {
            daoArticle = DatabaseManager.getInstance().getHelper().getBrandDao();
            QueryBuilder<BrandModel, Integer> queryBuilderGoal = daoArticle.queryBuilder();

            if (!searchTitle.isEmpty()) {
                queryBuilderGoal.where().like("title", "%" + searchTitle + "%");
            }
            queryBuilderGoal.orderBy("date_created", true);
            recyclerBrandsAuto = queryBuilderGoal.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recyclerBrandsAuto;
    }

    private void setUI(final View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBrandFragment nextFrag = new AddBrandFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, "TAG_FRAGMENT_ADD_BRAND")
                        .addToBackStack(null)
                        .commit();
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle(getResources().getString(R.string.alert_dialog_title));
                alert.setIcon(R.drawable.download);
                alert.setCancelable(false);

                alert.setPositiveButton(getResources().getString(R.string.alert_dialog_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        new ParseBrands(getContext(), Constants.URL.CATALOG, getActivity()).execute();
                        dialog.cancel();
                    }
                });
                alert.setNegativeButton(getResources().getString(R.string.alert_dialog_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                alert.show();
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.brandsAuto:
                menu.add(0, Constants.MENU.MENU_DELETE, 0, getResources().getString(R.string.menu_delete));
                menu.add(0, Constants.MENU.MENU_EDIT, 0, getResources().getString(R.string.menu_edit));
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        int currentId = brandsRecyclerAdapter.getItem(currentPosition).getId();
//        BrandModel brandModel = brandsRecyclerAdapter.getItem(currentPosition);

        switch (item.getItemId()) {
            case Constants.MENU.MENU_DELETE:
                if (recyclerBrandsAuto.size() != 0) {
                    recyclerBrandsAuto.remove(currentPosition);
                }
                lLM.removeViewAt(currentPosition);
                brandsRecyclerAdapter.notifyItemRemoved(currentPosition);
                brandsRecyclerAdapter.notifyItemRangeChanged(currentPosition, recyclerBrandsAuto.size());
                brandsRecyclerAdapter.notifyDataSetChanged();
                break;
            case Constants.MENU.MENU_EDIT:
                //To do
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void itemClicked(View view, int position) {
        currentPosition = position;
        BrandModel currentBrandModel = brandsRecyclerAdapter.getItem(currentPosition);
        int currentBrandId = currentBrandModel.getId();
        String currentBrandName = currentBrandModel.getName();

        if (currentBrandId != 0) {
            LineUpAndNewsTabs lineUpFrag = new LineUpAndNewsTabs();
            Bundle bundle = new Bundle();
            bundle.putInt("brandId", currentBrandId);
            bundle.putString("brandName", currentBrandName);
            lineUpFrag.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, lineUpFrag, "TAG_FRAGMENT_TABS")
                    .addToBackStack(null)
                    .commit();
        }
    }
}