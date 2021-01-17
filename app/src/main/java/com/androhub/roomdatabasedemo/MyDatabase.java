/*
 * Created by Androhub on 14/1/21
 * Copyright (c) 2021 . All rights reserved.
 */

package com.androhub.roomdatabasedemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Employee.class}, version = 3)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract EmployeeDao employeeDao();
}
