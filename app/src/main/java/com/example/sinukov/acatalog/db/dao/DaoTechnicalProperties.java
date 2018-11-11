package com.example.sinukov.acatalog.db.dao;

import com.example.sinukov.acatalog.model.TechnicalPropertiesModel;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DaoTechnicalProperties extends BaseDao<TechnicalPropertiesModel> {

    public DaoTechnicalProperties(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, TechnicalPropertiesModel.class);
    }
}
