package com.example.sinukov.acatalog.db.dao;

import com.example.sinukov.acatalog.model.CarModel;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DaoCar extends BaseDao<CarModel> {
    public DaoCar(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, CarModel.class);
    }
}