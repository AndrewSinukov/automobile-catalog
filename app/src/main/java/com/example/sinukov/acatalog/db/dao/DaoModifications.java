package com.example.sinukov.acatalog.db.dao;

import com.example.sinukov.acatalog.model.ModificationsModel;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DaoModifications extends BaseDao<ModificationsModel> {
    public DaoModifications(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, ModificationsModel.class);
    }
}