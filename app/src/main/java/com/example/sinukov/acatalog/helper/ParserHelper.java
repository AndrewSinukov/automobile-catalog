package com.example.sinukov.acatalog.helper;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class ParserHelper {

    private static ParserHelper instance;
    private Context context;

    public ParserHelper() {
    }

    public static ParserHelper getInstance() {
        if (instance == null) {
            instance = new ParserHelper();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        SharedPreferences preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }
}