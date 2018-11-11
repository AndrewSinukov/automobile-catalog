package com.example.sinukov.acatalog.db.dao;

import com.example.sinukov.acatalog.model.NewsModel;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DaoNews extends BaseDao<NewsModel> {
    public DaoNews(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, NewsModel.class);
    }
}