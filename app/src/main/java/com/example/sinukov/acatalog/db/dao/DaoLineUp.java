package com.example.sinukov.acatalog.db.dao;

import com.example.sinukov.acatalog.model.LineUpModel;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DaoLineUp extends BaseDao<LineUpModel> {
    public DaoLineUp(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, LineUpModel.class);
    }
}