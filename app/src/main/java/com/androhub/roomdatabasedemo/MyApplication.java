/*
 * Created by Androhub on 14/1/21
 * Copyright (c) 2021 . All rights reserved.
 */

package com.androhub.roomdatabasedemo;

import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {

    private static MyDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    /**
     * initialising the database
     */
    private void initDB() {
        db = Room.databaseBuilder(getApplicationContext(),
                MyDatabase.class, "my_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static MyDatabase getMyDatabase() {
        return db;
    }
}
