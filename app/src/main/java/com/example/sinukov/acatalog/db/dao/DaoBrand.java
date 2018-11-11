package com.example.sinukov.acatalog.db.dao;

import com.example.sinukov.acatalog.model.BrandModel;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DaoBrand extends BaseDao<BrandModel> {

    public DaoBrand(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, BrandModel.class);
    }
}