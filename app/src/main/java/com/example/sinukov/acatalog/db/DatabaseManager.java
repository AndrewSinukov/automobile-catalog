package com.example.sinukov.acatalog.db;

import android.content.Context;
import android.util.Log;

import com.example.sinukov.acatalog.db.dao.DaoBrand;
import com.example.sinukov.acatalog.model.BrandModel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static volatile DatabaseManager instance;
    private DatabaseHelper helper;

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public static void deleteCar(long id) throws SQLException {
        DaoBrand daoProgress = DatabaseManager.getInstance().getHelper().getBrandDao();
        DeleteBuilder<BrandModel, Integer> deleteBuilder = daoProgress.deleteBuilder();
        deleteBuilder.where().eq("_id", (int) id);
        deleteBuilder.delete();
    }

    public static List getBrand(int articleId) {
        List<BrandModel> listBrand = new ArrayList<>();
        try {
            DaoBrand daoArticle = DatabaseManager.getInstance().getHelper().getBrandDao();
            QueryBuilder<BrandModel, Integer> queryBuilderGoal = daoArticle.queryBuilder();
            queryBuilderGoal.where().eq("_id", articleId).query();
            queryBuilderGoal.orderBy("date_created", true);
            listBrand = queryBuilderGoal.query();
        } catch (SQLException e) {
            Log.i("DatabaseManager", "error " + e);
            e.printStackTrace();
        }
        return listBrand;
    }

    public void init(Context context) {
        if (helper == null) helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    public DatabaseHelper getHelper() {
        return helper;
    }
}