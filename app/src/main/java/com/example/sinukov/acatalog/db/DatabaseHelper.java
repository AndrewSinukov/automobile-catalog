package com.example.sinukov.acatalog.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sinukov.acatalog.Constants;
import com.example.sinukov.acatalog.db.dao.DaoBrand;
import com.example.sinukov.acatalog.db.dao.DaoCar;
import com.example.sinukov.acatalog.db.dao.DaoLineUp;
import com.example.sinukov.acatalog.db.dao.DaoModifications;
import com.example.sinukov.acatalog.db.dao.DaoNews;
import com.example.sinukov.acatalog.db.dao.DaoTechnicalProperties;
import com.example.sinukov.acatalog.model.BrandModel;
import com.example.sinukov.acatalog.model.CarModel;
import com.example.sinukov.acatalog.model.LineUpModel;
import com.example.sinukov.acatalog.model.ModificationsModel;
import com.example.sinukov.acatalog.model.NewsModel;
import com.example.sinukov.acatalog.model.TechnicalPropertiesModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private DaoBrand daoBrand = null;
    private DaoLineUp daoLineUp = null;
    private DaoCar daoCar = null;
    private DaoNews daoNews = null;
    private DaoModifications daoModifications = null;
    private DaoTechnicalProperties daoTechnicalProperties = null;

    public DatabaseHelper(Context context) {
        super(context, Constants.DB.DATABASE_NAME, null, Constants.DB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BrandModel.class);
            TableUtils.createTable(connectionSource, LineUpModel.class);
            TableUtils.createTable(connectionSource, CarModel.class);
            TableUtils.createTable(connectionSource, NewsModel.class);
            TableUtils.createTable(connectionSource, ModificationsModel.class);
            TableUtils.createTable(connectionSource, TechnicalPropertiesModel.class);
        } catch (SQLException e) {
            Log.d("SQLException", "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, BrandModel.class, true);
            TableUtils.createTable(connectionSource, LineUpModel.class);
            TableUtils.createTable(connectionSource, CarModel.class);
            TableUtils.createTable(connectionSource, NewsModel.class);
            TableUtils.createTable(connectionSource, ModificationsModel.class);
            TableUtils.createTable(connectionSource, TechnicalPropertiesModel.class);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.d("SQLException", "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public DaoBrand getBrandDao() throws SQLException {
        if (daoBrand == null) {
            daoBrand = new DaoBrand(getConnectionSource());
        }
        return daoBrand;
    }

    public DaoNews getNewsDao() throws SQLException {
        if (daoNews == null) {
            daoNews = new DaoNews(getConnectionSource());
        }
        return daoNews;
    }

    public DaoModifications getModificationsDao() throws SQLException {
        if (daoModifications == null) {
            daoModifications = new DaoModifications(getConnectionSource());
        }
        return daoModifications;
    }

    public DaoTechnicalProperties getTechnicalPropertiesDao() throws SQLException {
        if (daoTechnicalProperties == null) {
            daoTechnicalProperties = new DaoTechnicalProperties(getConnectionSource());
        }
        return daoTechnicalProperties;
    }

    public DaoLineUp getLineUpDao() throws SQLException {
        if (daoLineUp == null) {
            daoLineUp = new DaoLineUp(getConnectionSource());
        }
        return daoLineUp;
    }

    @Override
    public void close() {
        super.close();
        daoBrand = null;
        daoLineUp = null;
        daoCar = null;
        daoNews = null;
        daoTechnicalProperties = null;
    }
}